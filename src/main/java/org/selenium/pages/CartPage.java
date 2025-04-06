package org.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;

public class CartPage extends BasePage {
    private final By productName = By.xpath("//a[normalize-space()='Blue Shoes']");
    private final By checkoutBtn = By.cssSelector(".checkout-button.button.alt.wc-forward");

    /*@FindBy(css = ".checkout-button.button.alt.wc-forward")
    private WebElement checkoutBtn1;*/

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        //return driver.findElement(productName).getText();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }

    public CheckoutPage clickCheckoutBtn() {
        //driver.findElement(checkoutBtn).click();
        //checkoutBtn1.click();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckoutPage(driver);
    }
}
