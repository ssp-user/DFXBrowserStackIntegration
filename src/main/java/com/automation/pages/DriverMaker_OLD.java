package com.automation.pages;


import org.apache.commons.io.FileUtils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by David Tao on 01/21/2018.
 */
public class DriverMaker_OLD {

    public static File downloadFolder = new File("test-output\\temp_download");
    private static WebDriver driver;
    private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
    private Class<? extends WebDriver> CuWebDriverClass;
    private String browser = "Chrome";
    public static final String USERNAME = "vishaljoshi9";
    public static final String AUTOMATE_KEY = "AnPRmqvUGjvW78WbSxFp";    
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";    
    public static boolean runBrowserStack = true;
    /*
     * Manager  browsers in Maps
     */
    public  WebDriver getBrowserDriver(String browser,String headless) {
        WebDriver driver = null;
        switch (browser) {
            case "Firefox":
                driver = drivers.get("Firefox");
                if (driver == null) {
                    driver = initFireFoxDriver();
                    drivers.put("Firefox", driver);
                }
                break;
            case "IE":
                driver = drivers.get("IE");
                if (driver == null) {
                    driver = initIEDriver();
                    drivers.put("IE", driver);
                }
                break;
            case "Chrome":
                driver = drivers.get("Chrome");
                if (driver == null) {
                    driver = initChromeDrive(headless);
                    drivers.put("Chrome", driver);
                }
                break;
        }
        return driver;
    }


//    @SeleniumDriverHack(browserType = IE, version = "All")
    public WebDriver createDriverInstance(String browser) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        /* Deal with Mouse behivior in IE browser
         * Potentially reduce the amount of failure for IE driver. */
        if(browser.equalsIgnoreCase("IE")) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
            return CuWebDriverClass.getDeclaredConstructor(Capabilities.class).newInstance(capabilities);
        }else {
            return CuWebDriverClass.newInstance();
        }
    }

    public static  WebDriver initDriver(String browser,String head){
        if(browser.equals("FireFox")){
        	if (runBrowserStack) {
                driver = initRemoteDrive("Firefox");				
			} else {
	            driver =  initFireFoxDriver();
			}            
        } else if(browser.equals("Chrome")){
        	if (runBrowserStack) {
                driver = initRemoteDrive("Chrome");				
			} else {
	            driver = initChromeDrive(head);
			}
        } else if(browser.equals("IE")){
            driver =initIEDriver();
        } else {
            driver = initChromeDrive(head);
        }
        removeDownloadedFile();
        return driver ;
    }

    private static void removeDownloadedFile(){																						// Remove previous test downloaded files
        try{
            FileUtils.cleanDirectory(downloadFolder);
        } catch(IllegalArgumentException e){
            // Folder not exists
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private static WebDriver initChromeDrive(String head){

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_win32/chromedriver.exe");
/*
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=" + userProfile);
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
	*/
        // Below is to solve the new version of Chrome V42 that download PDF instead of view PDF in browser.

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", downloadFolder.getAbsolutePath());					// This is to defined a target folder to store download PDFs.
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--disable-notifications");
        //options.addArguments("--disable-web-security");
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");
        //options.addArguments("--use-file-for-fake-video-capture=media/4ppl.mp4");
        if (head.equalsIgnoreCase("headless")){
            options.addArguments("--headless");
            options.addArguments("window-size=1920x1080");
        }
//		options.setExperimentalOption("excludeSwitches", Arrays.asList("test-type", "ignore-certificate-errors"));		// This is conflict with Accept_SSL_CERTS
//		options.setExperimentalOption("excludeSwitches", Arrays.asList("test-type"));
        options.setExperimentalOption("prefs", prefs);
        //options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        driver = new ChromeDriver(options);
//        driver = new ChromeDriver();
        return  driver;
    }

    private static WebDriver initRemoteDrive(String browser){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "86.0");  
        caps.setCapability("resolution", "1920x1080"); 
        caps.setCapability("build", "DFX-parallel-build");         
        caps.setCapability("name", "Parallel-DFX-Test");    
		
        try {
			driver = new RemoteWebDriver(new URL(URL), caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return  driver;
    }    
    
    private static WebDriver initChromeDrive1(){

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_win32/chromedriver.exe");
/*
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=" + userProfile);
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
	*/
        // Below is to solve the new version of Chrome V42 that download PDF instead of view PDF in browser.

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", downloadFolder.getAbsolutePath());					// This is to defined a target folder to store download PDFs.
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--disable-notifications");
        //options.addArguments("--disable-web-security");
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");
        //options.addArguments("--use-file-for-fake-video-capture=media/4ppl.mp4");
        //this is control browser headless options.addArguments("--headless");
        //this is control browser headless options.addArguments("window-size=1920x1080");
//		options.setExperimentalOption("excludeSwitches", Arrays.asList("test-type", "ignore-certificate-errors"));		// This is conflict with Accept_SSL_CERTS
//		options.setExperimentalOption("excludeSwitches", Arrays.asList("test-type"));
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
//        driver = new ChromeDriver();
        return  driver;
    }

    private static WebDriver initIEDriver(){

        /*
         IE Browser 64bit or 32bit option, IE 64bit not compatible with selenium 3 , using 32 bit / Feb-02-2018
         */
//        System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer_x64_3.8.0/IEDriverServer.exe");
         System.setProperty("webdriver.ie.driver", "drivers//IEDriverServer_Win32_3.8.0/IEDriverServer.exe");
        InternetExplorerOptions option = new InternetExplorerOptions();
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        return  new InternetExplorerDriver(option);
    }

    private static WebDriver initFireFoxDriver(){
        return  new FirefoxDriver();
    }

    public static boolean isBrowserLogContains(String logs){
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> logList = logEntries.getAll();
        Boolean found = false;
        for (LogEntry eachEntry : logList){
//        	System.out.println(eachEntry.toString());
            if (eachEntry.toString().contains(logs)) {
                found = true;
                break;
            }
        }
        return found ;
    }


}
