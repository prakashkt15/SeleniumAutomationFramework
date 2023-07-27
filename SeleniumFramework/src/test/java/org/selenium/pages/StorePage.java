package org.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.base.BasePage;

public class StorePage extends BasePage {

    private By searchfld = By.name("s");
    private By searchBtn = By.cssSelector("button[value='Search']");
    private By title = By.cssSelector(".woocommerce-products-header__title.page-title");

//    This UI element is handled in the method(getAddToCartBtnElement), to handle dynamically
//    private By addToCartBtn = By.cssSelector("a[aria-label='Add “Faint Blue Jeans” to your cart']");
    private By clickViewCart = By.cssSelector("a[title='View cart']");


    public StorePage(WebDriver driver){
        super(driver);
    }


//    public void enterTextInSearchFld(String txt){                 //Not implemented Builder pattern
//        driver.findElement(searchfld);
//    }

    private StorePage enterTextInSearchFld(String txt){              //implemented Builder pattern
        //driver.findElement(searchfld).sendKeys(txt);                //implemented Structural page object
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchfld)).sendKeys(txt);
        return this;
    }

    private StorePage clickSearchBtn(){                              //implemented Builder pattern
        //driver.findElement(searchBtn);                              //implemented Structural page object
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return this;
    }

    public StorePage search(String txt){                            //implemented Functional page object
        //enterTextInSearchFld(txt).clickSearchBtn();           //we can call directly above methods directly (33 & 34)
//        driver.findElement(searchfld).sendKeys(txt);                //calling above 2 methods in single method
//        driver.findElement(searchBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchfld)).sendKeys(txt);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return this;
    }

    public String getTitle(){
        //return driver.findElement(title).getText();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    //creating this method to handle the dynamic UI element i.e we call add any product to the cart by passing productname
    //Declaring this method as private bcz adding product will be performing in this page itself
    private By getAddToCartBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “" +productName+ "” to your cart']");
    }

    public void clickAddToCartBtn(String productName){
        By addToCartBtn = getAddToCartBtnElement(productName);
        driver.findElement(addToCartBtn).click();

    }

    public CartPage clickViewCart(){
        //driver.findElement(clickViewCart).click();
        wait.until(ExpectedConditions.elementToBeClickable(clickViewCart)).click();
        return new CartPage(driver);
    }
}
