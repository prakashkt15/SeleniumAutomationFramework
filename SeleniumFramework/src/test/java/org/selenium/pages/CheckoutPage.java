package org.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.base.BasePage;
import org.selenium.pojoObjects.BillingAddress;
import org.selenium.pojoObjects.User;

import java.util.List;

public class CheckoutPage extends BasePage {
    private By firstnameFld = By.cssSelector("#billing_first_name");
    private By lastNameFld = By.cssSelector("#billing_last_name");
    private By countryDropdown = By.id("billing_country");
    private By addressOneFld = By.cssSelector("#billing_address_1");
    private By cityFld = By.cssSelector("#billing_city");
    private By zipCodeFld = By.cssSelector("#billing_postcode");
    private By stateDropdown = By.id("billing_state");
    private By emailFld = By.cssSelector("#billing_email");
    private By placeorderBtn = By.cssSelector("#place_order");
    private By successMsg = By.cssSelector(".woocommerce-notice");
    private By directBankTransfer = By.id("payment_method_bacs");
    private By alternateCountryDropdown = By.id("select2-billing_country-container");
    private By alternateStateDropdown = By.id("select2-billing_state-container");
    private By loginLink = By.cssSelector(".showlogin");
    private By usernameFld = By.cssSelector("#username");
    private By passwordFld = By.cssSelector("#password");
    private By loginBtn = By.cssSelector("button[value='Login']");
    private By overlay = By.cssSelector(".blockUI.blockOverlay");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage login(User user){
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFld)).sendKeys(user.getUsername());
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld)).sendKeys(user.getPassword());
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return this;
    }

    public CheckoutPage enterFirstName(String firstName){
        //driver.findElement(firstnameFld).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameFld)).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        //driver.findElement(lastNameFld).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFld)).sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName){
//        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropdown)));
//        select.selectByVisibleText(countryName);
        wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropdown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + countryName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }
    public CheckoutPage enterAddress(String address){
        //driver.findElement(addressOneFld).sendKeys(address);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressOneFld)).sendKeys(address);
        return this;
    }

    public CheckoutPage entercity(String city){
        //driver.findElement(cityFld).sendKeys(city);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cityFld)).sendKeys(city);
        return this;
    }

    public CheckoutPage selectState(String stateName) {
//        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(stateDropdown)));
//        select.selectByVisibleText(stateName);
        wait.until(ExpectedConditions.elementToBeClickable(alternateStateDropdown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" +  stateName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }

    public CheckoutPage enterZipCode(String zipCode){
        //driver.findElement(zipCodeFld).sendKeys(zipCode);
        wait.until(ExpectedConditions.visibilityOfElementLocated(zipCodeFld)).sendKeys(zipCode);
        return this;
    }

    public CheckoutPage enterEmail(String email){
        //driver.findElement(emailFld).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailFld)).sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
                enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterAddress(billingAddress.getAddressOne()).
                entercity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterZipCode(billingAddress.getZipCode()).
                enterEmail(billingAddress.getEmail());
                return this;
    }

    public CheckoutPage clickPlaceorderBtn(){
        //driver.findElement(placeorderBtn).click();
        waitForOverlayToDisappear(overlay);
        wait.until(ExpectedConditions.elementToBeClickable(placeorderBtn)).click();
        return this;
    }

    public CheckoutPage selectDirectBankTransfer(){
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransfer));
        if(!e.isSelected()){
            e.click();
        }
        return this;
    }

    public String getSuccessMsg(){
        //return driver.findElement(successMsg).getText();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).getText();
    }
}
