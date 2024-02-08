package org.selenium.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.constants.DriverType;

public class DriverManager {
    public WebDriver initializeDriver(String browser) {
        WebDriver driver;
        switch (DriverType.valueOf(browser)) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browser);
        }
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
}
