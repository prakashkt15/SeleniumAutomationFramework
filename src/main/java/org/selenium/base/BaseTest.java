package org.selenium.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.factory.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public WebDriver getDriver() {
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(String browser) {
        ///browser = System.getProperty("browser");//, browser);
        setDriver(new DriverManager().initializeDriver(browser));
        System.out.println("CURRENT THREAD: " + Thread.currentThread().threadId() + " ," + " DRIVER = " + getDriver());
    }

    @AfterMethod
    public void quitDriver() {
        System.out.println("CURRENT THREAD: " + Thread.currentThread().threadId() + " ," + " DRIVER = " + getDriver());
        getDriver().quit();
    }

    public String takeScreenshot(WebDriver driver, String testName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        String destinationPath = System.getProperty("user.dir") + "\\screenshots\\" + testName + ".png";
        FileUtils.copyFile(sourceFile, new File(destinationPath));
        return destinationPath;
    }
}
