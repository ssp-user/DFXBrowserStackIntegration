package com.automation.utils.log;


import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.automation.pages.PageManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

//import com.dealerfx.test.BaseTestClass;

//import atu.testng.reports.ATUReports;
//import atu.testng.reports.logging.LogAs;
//import atu.testng.selenium.reports.CaptureScreen;
//import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
/*
 * @Function: Test event listener and test log configurations
 * @author: David Tao
 * */


public class LogUtils {

    private static WebDriver driver = null;
    private static String screenShotFilePath = null;
    public static ArrayList<String> screenShotFileList = new ArrayList<String>();
    public static String strFileBasePath = "PDFReport/";
//	public static String strFileBasePath = "E:\\Automation Test Report\\PDFReport\\";
//	public static String strFileBasePath = ".\\PDF Report\\";

    public static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
    public static Date date;
    public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm.ss");


    public static String browserName = null;
    public static String env = null;
    public static String product = null;

    public static Logger log;

    public static StringBuilder verificationErrors;
    public static StringBuilder verificationErrorsBackup;


    public static ArrayList<File> failScrPath;
    private static ITestResult testResult;
    public static ArrayList<String> failCID;    // CID is content-ID for email screenshot

    public static void testResult() {
        if (!verificationErrors.toString().isEmpty()) {
//			testResult = Reporter.getCurrentTestResult();
            failCID.add(testResult.getAttribute("CID").toString());
            fail(verificationErrors.toString());
        }
    }


    public static String getScreenshotPath(String strScreenshotTitle) {                                                                // Return the Screenshot file path
        date = new Date();

//		String strScreenShotPath = strFileBasePath + "Screenshots\\" + dateFormat.format(date) + "\\";
        String strScreenShotPath = strFileBasePath + "Screenshots/" + dateFormat.format(date) + "/";
        String filePath = strScreenShotPath + strScreenshotTitle + "-" + timeFormat.format(date) + ".png";
        return filePath;
    }

    public static File getFailureScreenshotPath() {                                                                                    // Return failure screenshot as File
        date = new Date();
        testResult = Reporter.getCurrentTestResult();
        String dms = testResult.getAttribute("DMS").toString();
        String dealerID = testResult.getAttribute("DealerID").toString();
        String prod = testResult.getAttribute("ProductCategory").toString();

        String strScreenShotPath = "TestReport\\FailureScreenshot\\" + prod + "_" + dms + "_" + dealerID + "_"
                + timeFormat.format(date) + ".png";
        return new File(strScreenShotPath);
    }


    public static void takeScreenShot() {                                                                                            // This is to take screen shot
        driver = PageManager.getDriver();
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File ScrFile = scrShot.getScreenshotAs(OutputType.FILE);
//				File DestFile=new File(fileWithPath);
        File destFile = getFailureScreenshotPath();
        try {
            FileUtils.copyFile(ScrFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.fatal(destFile);
        failScrPath.add(destFile);
    }

    public static void logError(String strError) {                                                                                    // Log Error Msg
        System.err.println(strError);
        log.error(strError);
    }


    public static void logConsoleMsg(String strLogMsg) {                                                                                // Log Console Msg and Log file Msg
        System.out.println(strLogMsg);
        logMsgOnly(strLogMsg);
    }


    public static void logMsgOnly(String strLogMsg) {                                                                                // Log msg to the log file
        log.info("- " + strLogMsg);
    }

    public static void logWarnOnly(String strWarnMsg) {                                                                                // Log Warn to the log file
        log.warn("* " + strWarnMsg);
    }


    public static void logInfo(String description, String actualStatus, boolean takeScreenshot) {                                    // System Status Msg
        System.out.println("***** " + description + ": " + actualStatus + " *****\n");
        log.info("***** " + description + ": " + actualStatus + " *****");

        if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("firefox")) {
            if (takeScreenshot) {
//                ATUReports.add(description, "", "", actualStatus, LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
            } else {
//                ATUReports.add(description, "", "", actualStatus, LogAs.INFO, null);
            }
        } else {        // Override IE not to take screenshot, due to IE has extremely slow performance on taking screenshot
//            ATUReports.add(description, "", "", actualStatus, LogAs.INFO, null);
        }
    }


    public static void logWarningInfo(String description, String actualStatus) {
        log.warn("***** " + description + " : " + actualStatus + " *****");
        System.err.println("***** " + description + " : " + actualStatus + " *****");
        if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("firefox")) {
//            ATUReports.add(description, "", "", actualStatus, LogAs.WARNING, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
        } else {        // Override IE not to take screenshot, due to IE has extremely slow performance on taking screenshot
//            ATUReports.add(description, "", "", actualStatus, LogAs.WARNING, null);

        }
    }


    public static void logActions(String strNow_XXXXX) {                                                                                // Log Selenium Actions
        System.out.println("  -> Now " + strNow_XXXXX + "\n");
        log.info("  -> Now " + strNow_XXXXX);
    }

    public static void logPassMsg(String Verify_XXX_Passed, String expectedValue, String actualValue, boolean takeScreenshot) {        // Log Check Point Pass Msg

        System.out.println("\t<===== Verify " + Verify_XXX_Passed + " ===> Passed =====>\n");
        log.info("\t<===== Verify " + Verify_XXX_Passed + " ===> Passed =====>");

        if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("firefox")) {
            if (takeScreenshot) {
//                ATUReports.add("Verify " + Verify_XXX_Passed, "", expectedValue, actualValue, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
            } else {
//                ATUReports.add("Verify " + Verify_XXX_Passed, "", expectedValue, actualValue, LogAs.PASSED, null);
            }
        } else {        // Override IE not to take screenshot, due to IE has extremely slow performance on taking screenshot
//            ATUReports.add("Verify " + Verify_XXX_Passed, "", expectedValue, actualValue, LogAs.PASSED, null);
        }
    }

    public static void logFailMsg(String Check_XXXX, String expectedValue, String actualValue) {
        System.err.println("\t<===== Verify " + Check_XXXX + " ::: Failed =====>\n");
        log.error("\t<===== Verify " + Check_XXXX + " " + actualValue + " ::: Failed =====>");

        if (!verificationErrors.toString().isEmpty()) {
            verificationErrors.append("\n");
        }
        verificationErrors.append(Check_XXXX + " -> Failed");
//        ATUReports.add("Verify " + Check_XXXX, "", expectedValue, actualValue, LogAs.FAILED, null);
    }

    public static void logFailMsgAndTakeScreenshot(String Check_XXXX, String expectedValue, String actualValue) {                    // Log Check Point Fail Msg and Take Screenshot
        logFailMsgAndTakeScreenshotInnerMethod(Check_XXXX, expectedValue, actualValue);
    }


    public static void logFailMsgAndTakeScreenshot(String Check_XXXX, String expectedValue, String actualValue, Exception e) {        // Log Check Point Fail Msg and Take Screenshot, and Record Exception
        logFailMsgAndTakeScreenshotInnerMethod(Check_XXXX, expectedValue, actualValue);
        verificationErrorsBackup.append(e.getMessage());
        log.error(e);
    }


    public static void logFailMsgAndTakeScreenshot(String Check_XXXX, String expectedValue, String actualValue, Error e) {            // Log Check Point Fail Msg and Take Screenshot, and Record Error
        logFailMsgAndTakeScreenshotInnerMethod(Check_XXXX, expectedValue, actualValue);
        verificationErrorsBackup.append(e.getMessage());
        log.error(e);
    }


    private static void logFailMsgAndTakeScreenshotInnerMethod(String Check_XXXX, String expectedValue, String actualValue) {
        System.err.println("\t<===== Verify " + Check_XXXX + " " + actualValue + " ::: Failed =====>\n");
        log.error("\t<===== Verify " + Check_XXXX + " " + actualValue + " ::: Failed =====>");

        if (!verificationErrors.toString().isEmpty()) {
            verificationErrors.append("\n");
        }
        verificationErrors.append(Check_XXXX + " -> Failed");

        takeScreenShot();

//        ATUReports.add("Verify " + Check_XXXX, "", expectedValue, actualValue, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
    }


    public static void testSuiteBegin() {
        System.out.println(" ===============================================================================");
        System.out.println("\t\t\t     Start of Cucumber Test Features ");
        System.out.println(" ===============================================================================\n");
        log.info("\n ===============================================================================");
        log.info("\n\t\t\t     Cucumber Test Features ");
        log.info("\n ===============================================================================");
    }


    public static void testSuiteEnd() {
        System.out.println(" ===============================================================================");
        System.out.println("\t\t\t     Complete of Test Suite");
        System.out.println(" ===============================================================================\n");
        log.info("\n ===============================================================================");
        log.info("\n\t\t\t     Complete of Test Suite");
        log.info("\n ===============================================================================");
    }


    public static void testBegin() {
        System.out.println(" ===============================================================================");
        System.out.println("\t\t\t\t Test Begin");
        System.out.println(" ===============================================================================");
        log.info("\n ===============================================================================");
        log.info("\n\t\t\t\t Test Begin");
        log.info("\n ===============================================================================");
    }

    public static void testEnd() {
        System.out.println(" ===============================================================================");
        System.out.println("\t\t\t\t Test End");
        System.out.println(" ===============================================================================");
        log.info("\n ===============================================================================");
        log.info("\n\t\t\t\t Test End");
        log.info("\n ===============================================================================");
    }


}