package org.selenium.extentReportListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports extentReport;

    public static ExtentReports getExtentReport() {
        String extentReportPath = System.getProperty("user.dir") + "//reports//extentReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportPath);
        reporter.config().setReportName("E-commerce Selenium Test Automation Results");
        reporter.config().setDocumentTitle("E-commerce Selenium Test Automation Framework");

        extentReport = new ExtentReports();
        extentReport.attachReporter(reporter);
        extentReport.setSystemInfo("Operating System", "Windows 10");
        extentReport.setSystemInfo("Tested By", "Prakash KT");

        return extentReport;
    }
}
