package org.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;

public class HomePage extends BasePage {
    private By storeMenuLink = By.cssSelector("li[id='menu-item-1227'] a[class='menu-link']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage load() {
        load("");
        return this;
    }

//    public void clickStoreMenuLink(){                     //Not implemented Fluent interface
//        driver.findElement(storeMenuLink).click();
//    }

    public StorePage clickStoreMenuLink() {                  //implemented Fluent interface
        //driver.findElement(storeMenuLink).click();
        wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
        return new StorePage(driver);
    }
}
