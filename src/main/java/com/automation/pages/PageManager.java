package com.automation.pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import com.automation.utils.log.DfxLog;
import cucumber.api.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import com.automation.pages.common.BasePage;
import com.automation.pages.common.WebPage;
import com.automation.pages.common.WebPage;

import javax.imageio.ImageIO;

/**
 * Created by David Tao  on 01/18/2018.
 */
public class PageManager {

    private static boolean applicationCacheDirty;
    private static boolean loggedIn;
    private static WebPage currentPage;
    private static String  testRUL;
    private static WebDriver driver;
    private static String browserName;
    public static int waitInSeconds = 70;
    private static Set<String> windowHandles = null;
    private static String preHandle, currentHandle = null ;
    private static final String repoFile = "config/prodENV.properties";

    private static final Logger log = Logger.getLogger(PageManager.class);
//    DfxLog.logger = Logger.getLogger(PageManager.class);
    private PageManager() {
        throw new IllegalStateException("QAWebPages is a utility class use static members");
    }

    public static WebPage currentPage() {
        return currentPage;
    }

    public static void setCurrentPage(WebPage page) {
        currentPage = page;
    }

    public static void setDriver(WebDriver initDriver) {
        driver = initDriver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static byte[] takeScreenshot(Scenario scenario)  {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
        Date date = new Date();
        String timeStamp = dateFormat.format(date)+"-"+timeFormat.format(date);
        try{
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(screenshot));
            Graphics g = img.getGraphics();
            g.setColor(Color.red);
            g.setFont(new Font( "SansSerif", Font.BOLD, 25));
            g.drawString("The failing URL: "+driver.getCurrentUrl(), 20, 25);
            ImageIO.write(img, "png", new File("screenshot/"+scenario.getName()+" "+timeStamp+".png"));
        }catch (IOException ex){
            System.out.println(ex);
        }
        return screenshot;
    }

    public static void quitBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        currentPage = null;
    }

    public static String getBrowserName() {
//            return BrowserType.valueOf(System.getProperty("wb.cucumber.browserType").toUpperCase());
        return browserName;
    }

    public static void launchBrowser(String browser,String head) {
//        DfxLog.logger = Logger.getLogger(PageManager.class);
        if(browser.equalsIgnoreCase("FireFox")){
            browserName  =  "FireFox";
        } else if(browser.equalsIgnoreCase("Chrome")){
            browserName  = "Chrome";
        } else if(browser.equalsIgnoreCase("IE")){
            browserName  = "IE";
        } else {
            browserName  = browser;
        }
        initPage(head);
    }

    private static void initPage(String head){
        driver =  DriverMaker.initDriver(browserName,head);
        BasePage.setDriver(driver);
        WebPage.setWait(driver);
        driver.manage().window().maximize();
        //driver.manage().window().setSize(new Dimension(1600, 1000));
        driver.manage().deleteAllCookies();
    }

    public static void setApplicationCacheDirty(boolean applicationCacheDirty) {
        PageManager.applicationCacheDirty = applicationCacheDirty;
    }

    public static boolean isApplicationCacheDirty() {
        return applicationCacheDirty;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static String getEnvURL(String envURL) {
        testRUL = initEvnRUL(envURL);
        if (testRUL==null){
            System.out.println(" The PROD for " + envURL + "  not found , return this ") ;
            testRUL = envURL;
        }
        return testRUL;
    }

    private static String initEvnRUL(String urlName){
        String urlProperty = urlName;
        try {
            FileInputStream stream = new FileInputStream(repoFile);
            Properties propertyFile = new Properties();
            propertyFile.load(stream);
            urlProperty = propertyFile.getProperty(urlName.toUpperCase());
        }catch (IOException ioE){
            log.error("The property files failed to load ");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return urlProperty;
    }


    // Set current windows reading
    public static void setWindowHandles(){
        windowHandles = driver.getWindowHandles();
    }

    public static Set<String> getWindowHandles(){
        return windowHandles;
    }

    // Set windowIDs
    public static void setCurrentHandle(){
        currentHandle = driver.getWindowHandle();
    }

    public static void setPreHandle(){
        preHandle = driver.getWindowHandle();
    }

    // Get windowIDs
    public static String getCurrentHandle(){
        return currentHandle;
    }

    public static String  getPreHandle(){
        return   preHandle;
    }

    // This is to check if new window with specific window title has been generated
    public static void  switchWindow(String handle){
        driver.switchTo().window(handle);
    }

    // This is to check if new window with specific window title has been generated
    public static void switchWindow(){
        setPreHandle();
        setWindowHandles();
        System.out.println("<====== The page is changed from '"+driver.getTitle()+"' to a new window ======>");
        for (String handle :  windowHandles ){
            if (!handle.equals(preHandle)){
                //System.out.println("CONSOLE INFORMATION ===>  \n" + " From window :  " + preHandle +  " --> to this handle : " + handle);
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    // This is to check if new window with specific window title has been generated
    public static void  closewindow(String handle){
        if (windowHandles.contains(handle)){
            driver.switchTo().window(handle).close();
            setWindowHandles();
        }else{
            log.warn(" not this handle ");
        }
    }

    public static void setLoggedIn(boolean loggedIn) {
        PageManager.loggedIn = loggedIn;
    }

    private static void killBrowser() {
        Process p;
        String chrome = "chromedriver.exe";
        String ie = "IEDriverServer.exe";
        try {
            p = Runtime.getRuntime().exec("tasklist");
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
//		  		System.out.println(line);
                if (line.contains(chrome)) {
                    Runtime.getRuntime().exec("taskkill /f /im " + chrome);
                    System.out.println("ChromeDriver process Found and Ended");
                } else if (line.contains(ie)) {
                    Runtime.getRuntime().exec("taskkill /f /im " + ie);
                    System.out.println("IE Driver process Found and Ended");
                }
            }
            System.out.println("Kill Process Completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
