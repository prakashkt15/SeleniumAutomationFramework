package org.selenium.extentReportListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.selenium.base.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extentReport = ExtentReporterNG.getExtentReport();
    ExtentTest extentTest;
    ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    WebDriver driver;


    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport.createTest(result.getName());
        extentTestThreadLocal.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTestThreadLocal.get().log(Status.PASS, result.getName() + " Test case passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTestThreadLocal.get().fail(result.getThrowable());
        /*try {
            driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        Object test = result.getInstance();
        driver = ((BaseTest) test).getDriver();

        try {
            String screenShotFilepath = takeScreenshot(driver, result.getName());
            extentTestThreadLocal.get().addScreenCaptureFromPath(screenShotFilepath, result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTestThreadLocal.get().log(Status.SKIP, result.getName() + " Test case skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
        File htmlPath = new File("D:\\SeleniumFramework\\reports\\extentReport.html");
        try {
            Desktop.getDesktop().browse(htmlPath.toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
