package com.automation.utils.sync;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import com.automation.pages.PageManager;
import com.automation.utils.log.LogUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utils.elementUI.CSSUtils;
import com.automation.utils.otherUtils.CommonMethods;
//import com.dealerfx.test.BaseTestClass;

/*
 * @Function: Custom methods for synchronizing elements with page load
 * @author: Update By david  for Dealer-FX
 * */


public class SyncPage {

    protected static Set<String> allWindowsID = null;
    protected static String parentWindowID = null;
    protected static String currentUrl = null;
    private static int currentPosition = 0;
    private static int temp = 0;


    private static Logger log = Logger.getLogger(SyncPage.class.getName());

    // This is to check a web object innerText is displayed
    public static boolean isElementTextDisplayed(WebElement webElement) {
//			if(!webElement.getText().isEmpty() || !webElement.getAttribute("value").isEmpty()){
        if (!webElement.getText().isEmpty()) {
            log.info("Element Text Displayed : " + webElement.getText());
            return true;
        } else {
            return false;
        }
    }


    // This is to check if new window with specific window title has been generated
    public static boolean windowTitleEquals(WebDriver driver, String strWindowTitle) {
        getWindowsHandles(driver);
//		getParentWindowID(driver);
        log.info("total windows of " + allWindowsID.size());

        boolean result = false;
//		driver.close();	// This is to close the parent window
        for (String windowID : allWindowsID) {
            try {
                if (driver.switchTo().window(windowID).getTitle().contains(strWindowTitle)) {
                    log.info("Title Matched: " + driver.getTitle());
//					driver.switchTo().window(parentWindowID).close();
                    closeParentWindow(driver);
                    driver.switchTo().window(windowID);
                    result = true;
                    break;
                }
            } catch (NoSuchWindowException e) {
                log.info("The current window title is : " + driver.getTitle());
                continue;
            }
        }
        return result;
    }


    // This is a condition to wait for DOM to finish loading a page
    public static ExpectedCondition<Boolean> condDomReadyState() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {

                log.info("Before executing Javascript");

                try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    return js.executeScript("return document.readyState").equals("complete");
                } catch (Exception e) {
                    if (e.toString().contains("JavaScript error")) {
                        log.error("JavaScript Executor fail");
                        return null;
                    } else {
                        System.out.println("java err");
                        log.error(e.getMessage());
                        return null;
                    }
                }
            }

            @Override
            public String toString() {
                return "Condition Dom Ready State";
            }
        };
    }

    /***
     * Wait for Condition: WebElement display inner text
     * @param webElement WebElement
     * @return true once the element display the inner text
     */
    public static ExpectedCondition<Boolean> condWebElementTextDisplayed(final WebElement webElement) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return isElementTextDisplayed(webElement);
            }

            @Override
            public String toString() {
                return "condWebElementTextDisplayed";
            }
        };
    }

    /***
     * Wait for Condition: WebElement inner text updated
     * @param element WebElement inner text to be verify
     * @param textBeforeUpdated Text string before updating
     * @return true if WebElement inner text updated from provided text
     */
    public static ExpectedCondition<Boolean> condWebElementTextUpdated(final WebElement element, final String textBeforeUpdated) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                if (!element.getText().trim().equalsIgnoreCase(textBeforeUpdated)) {
                    return true;
                } else {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "condWebElementTextUpdated";
            }
        };

    }


    // Get current windows reading, call this method before a pop up window opens
    public static void getWindowsHandles(WebDriver driver) {
        allWindowsID = driver.getWindowHandles();
    }

    // Get Parent windowID
    public static void getParentWindowID(WebDriver driver) {
        parentWindowID = driver.getWindowHandle();
    }


    public static void prepareWindowPopup() {        // Call this method before any windows popup
        WebDriver dr = PageManager.getDriver();
        getWindowsHandles(dr);
        getParentWindowID(dr);
    }


    /***
     * Wait condition for a new browser tag pop up, need to execute getWindowsHandles method first
     * @param driver WebDriver
     * @return true when a new browser tag pop up
     */
    public static ExpectedCondition<Boolean> condWindowPopUp(WebDriver driver) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                log.info("Current Windows Count: " + allWindowsID.size());
                int windowsCount = allWindowsID.size();
//                return (driver.getWindowHandles().size() == windowsCount + 1);
                return true;
            }
        };
    }

    /**
     * Switch to a new popup window after the actual window has popuped by url
     *
     * @param driver
     * @param strUrl
     */
    public static void switchToPopupWindowAfterWindowPopupByUrl(WebDriver driver, String strUrl) {
        getWindowsHandles(driver);
        log.info("The Windows Handles Size : " + allWindowsID.size());
        if (!allWindowsID.isEmpty()) {
            for (String windowID : allWindowsID) {
                try {
                    if (driver.switchTo().window(windowID).getCurrentUrl().toLowerCase().contains(strUrl)) {
                        break;
                    } else {

                    }
                } catch (NoSuchWindowException e) {
                    continue;
                }
            }
        }
    }


    /**
     * Switch to a new popup window by window title, after the actual window has popuped.
     *
     * @param driver
     * @param strTitle New window page title in lower case
     */
    public static void switchToPopupWindowAfterWindowPopupByTitle(WebDriver driver, String strTitle) {
        getWindowsHandles(driver);
        String title = strTitle.toLowerCase();
        log.info("The Windows Handles Size : " + allWindowsID.size());
        if (!allWindowsID.isEmpty()) {
            for (String windowID : allWindowsID) {
                try {
                    if (driver.switchTo().window(windowID).getTitle().toLowerCase().contains(title)) {
                        log.info("Title Matched: " + driver.getTitle());
//						driver.switchTo().window(parentWindowID).close();
                        driver.switchTo().window(windowID);
                        break;
                    } else {

                    }
                } catch (NoSuchWindowException e) {
                    log.error("Window " + strTitle + " Not Found");
                    continue;
                }
            }
        }
    }


    // This switch to a new pop up window before actual window has PopUped
    public static void switchToPopupWindowByUrl(WebDriverWait wait, WebDriver driver, String strUrl) {
         wait.until(condWindowPopUp(driver));
        log.info("A new window Pop Up");
        switchToPopupWindowAfterWindowPopupByUrl(driver, strUrl);
        driver.manage().window().maximize();
        log.info("Switched to new window");
    }


    /**
     * A method to wait to popup a new window then switch to the new window by it's windows title.
     *
     * @param wait
     * @param driver
     * @param strTitle new window page title in  lower case
     */
    public static void switchToPopupWindowByTitle(WebDriverWait wait, WebDriver driver, String strTitle) {
        wait.until(condWindowPopUp(driver));
        log.info("A new window Pop Up");
        switchToPopupWindowAfterWindowPopupByTitle(driver, strTitle);
        driver.manage().window().maximize();
        log.info("Switched to new window");
    }


    // This is to close the PDF window then switch back to the parent window
    public static void closePDFAndSwitchToParentWindow(WebDriver driver) {
        driver.close();
        log.info("PDF Window closed and now switching to parent window");
        driver.switchTo().window(parentWindowID);
    }


    // This is to read the current URL address
    public static void getCurrentUrl(WebDriver driver) {
        currentUrl = driver.getCurrentUrl();
    }

    /**
     * Check to verify number of customers found
     *
     * @param driver                 WebDriver
     * @param webElementListToVerify List<WebElement> Customer List
     * @param pageTitleLocator                     page title element locator to verify element no longer present
     * @return true if found more than one customer; false if found only one customer
     */
    public static boolean isMoreThanOneCustomer(WebDriver driver, List<WebElement> webElementListToVerify, By pageTitleLocator) {
        try {
            new WebDriverWait(driver, PageManager.waitInSeconds).until(condSearchResultFound(driver, webElementListToVerify, pageTitleLocator));
            log.info("Found More than One customer");
            return true;    // true = Found more than one customer, follow by select customer action
        } catch (TimeoutException e) {
            if (e.getMessage().contains("System found only one customer")) {
                log.info("Found Only One customer, Re-Directing to the Customer Info Page");
                return false;    // false = Only One Customer Found, System re-direct to customer info page
            } else {
                throw new TimeoutException(e.getMessage());
            }
        }

    }

    // Wait condition for customer result come up
    public static ExpectedCondition<Boolean> condSearchResultFound(WebDriver driver, final List<WebElement> elementListToVerify, final By pageTitleLocator) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {

                try {
                    try {
                        if (elementListToVerify.size() > 1) {
                            if (PageManager.getBrowserName().equals("internet explorer")) {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            return true;
                        } else {
                            throw new NoSuchElementException("Customer List Not Found");
                        }
                    } catch (NoSuchElementException e) {
                        if (!isElementPresentAndVisible(pageTitleLocator)) {            // Page re-directed, meaning 1 customer found
                            log.info("Page title is not displayed, System found only one customer");
                            if (PageManager.getBrowserName().equals("internet explorer")) {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            throw new TimeoutException("System found only one customer");
                        } else {
                            return null;
                        }
                    }
                } catch (StaleElementReferenceException e) {
                    log.error("Stale Element Reference Exception Occurred while determining number of customer found. " + e.getMessage());
                    return null;
                } catch (WebDriverException ee) {
                    if (ee.getMessage().contains("System found only one customer")) {
                        throw new TimeoutException(ee.getMessage());
                    } else {
                        log.error("WebDriverException Occurred but handled. " + ee.getMessage());
                        return null;
                    }
                }
            }

            @Override
            public String toString() {
                return "Time out on condSearchResultFound method";
            }
        };
    }

    /**
     * Check if a customer is a new customer or an existing customer
     *
     * @param driver              WebDriver
     * @param elementToVerify     CustomerNotFound Title
     * @param pageTitleLocator    AdvisorDashboard page title
     * @param elementListToVerify WebElement List
     * @return
     */
    public static boolean isNewCustomer(WebDriver driver, WebElement elementToVerify, By pageTitleLocator, List<WebElement> elementListToVerify) {
        try {
            new WebDriverWait(driver, PageManager.waitInSeconds).until(condNewCustomer_OrNot(driver, elementToVerify, pageTitleLocator, elementListToVerify));
            log.info("Customer Not Found, creating a new customer");
            return true;
        } catch (TimeoutException e) {
            if (e.getMessage().contains("Existing customer, page redirected")) {
                log.info("Page title is not displayed OR Customer List Displayed.");
                return false;
            } else {
                throw new TimeoutException(e.getMessage());
            }
        }
    }


    public static ExpectedCondition<Boolean> condNewCustomer_OrNot(WebDriver driver, final WebElement customerNotFoundTitle, final By pageTitleLocator, final List<WebElement> elementListToVerify) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    try {
                        if (customerNotFoundTitle.isDisplayed()) {                /* Customer Not Found - to create new customer */
                            return true;
                        } else {
                            throw new NoSuchElementException("CustomerNotFound Title NOT FOUND");
                        }

                    } catch (NoSuchElementException e) {
                        if (!isElementPresentAndVisible(pageTitleLocator) || isListOfElementsPresentAndVisible(elementListToVerify)) {        /* Page re-directed OR List of Customer displayed = Not a new customer */
                            log.info("Page title is not displayed OR Customer List Displayed.");
                            throw new TimeoutException("Existing customer, page redirected");
                        } else {
                            return null;
                        }
                    }

                } catch (StaleElementReferenceException e) {
                    log.error("Stale Element Reference Exception Occurred while determining whether a customer is new Customer or existing customer. " + e.getMessage());
                    return null;
                } catch (WebDriverException ee) {
                    if (ee.getMessage().contains("Existing customer, page redirected")) {
                        throw new TimeoutException(ee.getMessage());
                    } else {
                        log.error("WebDriver Exception occurred but handled. " + ee.getMessage());
                        return null;
                    }
                }
            }

            @Override
            public String toString() {
                return "Time out on condNewCustomer_OrNot method";
            }
        };
    }


    // Wait condition for a String list update

    /***
     * Wait condition for WebElement List to be updated
     * @param driver WebDriver
     * @param originalSize The WebElement List original size before any action
     * @param destElementsLocator WebElement List Locator
     * @return true if the size is changed
     */
    public static ExpectedCondition<Boolean> condWebElementListUpdated(WebDriver driver, final int originalSize, final By destElementsLocator) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                int listSize = driver.findElements(destElementsLocator).size();
//				if(driver.findElements(destElementsLocator).size()!=originalSize){
                if (listSize != originalSize) {
                    log.info("List Original size is: " + originalSize);
                    log.info("Update list size is: " + listSize);
                    return true;
                } else {
                    log.info("Selected Item list have not changed");
                    return null;
                }
            }

            @Override
            public String toString() {
                return "Timeout on condWebElementListUdpated.";
            }
        };
    }


    // Wait condition to wait for WebElement List size > 0
    public static ExpectedCondition<Boolean> condListToBeDisplayed(final List<WebElement> elementListToBeDisplayed) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                if (elementListToBeDisplayed.size() > 0) {
                    return true;
                } else {
                    return null;
                }
            }
        };
    }


    // Wait condition to wait for Total amount is changed
    public static ExpectedCondition<Boolean> condTotalAmountChanged(final WebElement targetElementTotalAmount) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String total = targetElementTotalAmount.getText().trim();
                log.info("WebDriverWait looping ..... " + total);
//				if(!currentTotalAmount.equals(targetWebElementTotalAmount.getText().trim())){
                if (!CommonMethods.currentTotalAmount.equals(total) && !total.equals("")) {
                    return true;
                } else {
                    log.info("Total amount has not yet changed");
//					return false;
                    return null;
                }
            }

            @Override
            public String toString() {
                return "Time out on condTotalAmountChanged";
            }
        };
    }


    /**
     * Check to see if an element is present, but this doesn't mean the element is visible.
     *
     * @param driver WebDriver
     * @param by     element locator
     * @return true if element found; false if element not found
     */
    public static boolean isElementPresent(WebDriver driver, By by) {

        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    /**
     * Check to see if an element is present, and it also visible.
     *
     * @param by element locator
     * @return true if the element is exists and visible; false if the element is either not exists or invisible
     */
    public static boolean isElementPresentAndVisible(By by) {                                // Looks like this method is conflict with IE.
        WebDriver driver = PageManager.getDriver();
        try {
            if (isElementPresent(driver, by)) {
                if (driver.findElement(by).isDisplayed()) {
                    log.info("Element is present and displayed. Returning true");
                    return true;
                } else {
                    log.info("Element is present but not displayed. Returning false");
                    return false;
                }
            } else {
                log.info("Element is not present, returning false");
                return false;
            }
        } catch (WebDriverException e) {
            if (e.getMessage().contains("Unable to find element") || e.getMessage().contains("Element is no longer valid")
                    || e.getMessage().contains("Error determining if element is displayed")        // IE somehow throw WebDriverException instead of NoSuchElementException
                    || e.getMessage().contains("no such element")) {
                return false;
            } else {
//					log.error("This is inside the SyncPage method: -- " + e.getMessage());
                throw new WebDriverException(e.getMessage());
            }
        }
    }

    /***
     * Check to see if a List of Elements is present and visible
     * @param elementList WebElement List
     * @return true if WebElement List Size > 1
     */
    public static boolean isListOfElementsPresentAndVisible(List<WebElement> elementList) {
        try {
            if (elementList.size() > 1) {
                log.info("Element List is present and displayed. Returning true");
                return true;
            } else {
                log.info("Element List found, but not greater than 1, Element List Size is:" + elementList.size());
                return false;
            }
        } catch (WebDriverException e) {
            if (e.getMessage().contains("Unable to find element") || e.getMessage().contains("Element is no longer valid")
                    || e.getMessage().contains("Error determining if element is displayed")        // IE somehow throw WebDriverException instead of NoSuchElementException
                    || e.getMessage().contains("no such element")) {
                return false;
            } else {
                throw new WebDriverException(e.getMessage());
            }
        }
    }


    /***
     * Check to see if a WebElement is displayed
     * @param element WebElement
     * @return true if element is displayed; return false if element is not displayed
     */
    public static boolean isElementDisplayed(WebElement element) {                // Check to see if WebElement is displayed
        boolean result = false;
        try {
            if (element.isDisplayed()) {
                result = true;
            }
        } catch (WebDriverException e) {
            result = false;
        }
        return result;
    }


    /***
     * Wait Condition for WebElement's class to contain specific string
     * @param element WebElement
     * @param classValue String for the class value
     * @return true when the WebElement's class contain the specific string
     */
    public static ExpectedCondition<Boolean> condElementClassContains(final WebElement element, final String classValue) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                if (CSSUtils.getClassAttributeValue(element).contains(classValue)) {
                    return true;
                } else {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "Time out on condElementClassContains";
            }
        };
    }


    /***
     * Wait Condition for WebElement's class is no longer in active
     * @param element WebElement
     * @return true when WebElemetn class is not containing "Active"
     */
    public static ExpectedCondition<Boolean> condElementClassNotActive(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                if (!CSSUtils.getClassAttributeValue(element).toLowerCase().contains("active")) {
                    return true;
                } else {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "Timeout on condElementClassNotActive";
            }
        };
    }


    /***
     * Wait Condition for WebElement's Cursor Value change to desired string.
     * @param element WebElement
     * @param cursorValue Desired string value
     * @return
     */
    public static ExpectedCondition<Boolean> condCssCursorValueChangeTo(final WebElement element, final String cursorValue) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                if (CSSUtils.getCssCursorValue(element).equalsIgnoreCase(cursorValue)) {
                    return true;
                } else {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "Timeout on condCssCursorValuechangeTo method";
            }
        };
    }


    /***
     * Wait condition for WebElement is no longer display on the screen
     * @param locator WebElement locator
     * @return true if the element is gone
     */
    public static ExpectedCondition<Boolean> condElementDisappear(final By locator) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    if (isElementPresentAndVisible(locator)) {
                        return null;
                    } else {
                        return true;
                    }
                } catch (WebDriverException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "condElementDisappear";
            }
        };
    }


    /***
     * Override the invisibilityOfElementLocator to handle IE exception.
     * @param locator
     * @return
     */
    public static ExpectedCondition<Boolean> condInvisibilityOfElementLocated(final By locator) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return !(driver.findElement(locator).isDisplayed());
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return true;
                } catch (WebDriverException e) {
                    if (e.getMessage().contains("Unable to find element") || e.getMessage().contains("Element is no longer valid")
                            || e.getMessage().contains("Error determining if element is displayed")) {
                        return true;
                    } else {
                        throw new WebDriverException(e.getMessage());
                    }
                }
            }

            @Override
            public String toString() {
                return "condInvisibilityOfElementLocated";
            }
        };
    }


    /***
     * Wait for WebElement become invisible
     * @param element WebElemetn to be invisible
     * @return true if the WebElement is become invisible
     */
    public static ExpectedCondition<Boolean> condElementInvisible(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    if (element.isDisplayed()) {
                        return null;
                    } else {
                        return true;
                    }
                } catch (StaleElementReferenceException e) {
                    log.info("Stale element happen on CondElementInvisibile");
                    return null;    // This has to be return null.
                } catch (NoSuchElementException e) {
                    return true;
                } catch (WebDriverException e) {
                    if (e.getMessage().contains("Unable to find element") || e.getMessage().contains("Element is no longer valid")
                            || e.getMessage().contains("Error determining if element is displayed")) {
                        return true;
                    } else {
                        throw new WebDriverException(e.getMessage());
                    }
                }
            }

            @Override
            public String toString() {
                return "condElementInvisible timeout";
            }
        };
    }


    /***
     * Wait Condition to String value of a WebElement to change from original text value
     * @param string Original String value before any actions
     * @param elementToVerify WebElement text to be verify
     * @return true if the String value has changed
     */
    public static ExpectedCondition<Boolean> condWebElementChangeStringValueFrom(final WebElement elementToVerify, final String string) {
        return new ExpectedCondition<Boolean>() {
            String strText = null;

            @Override
            public Boolean apply(WebDriver driver) {
                strText = elementToVerify.getText().trim();
                if (!string.isEmpty()) {
                    if (string.equals(strText)) {
                        log.info("Refreshed String : " + strText);
                        return null;
                    } else if (!string.equals(strText)) {
                        log.info("Refreshed String : " + strText);
                        return true;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "Time out on condWebElementChangeStringValueFrom";
            }
        };
    }


    /***
     * Wait Condition to String value of a WebElement change to desire Text value, case insensitive
     * @param string Desire Text Value after any actions
     * @param elementToVerify WebElement text to be verify
     * @return true if WebElement String value equals to desire value
     */
    public static ExpectedCondition<Boolean> condWebElementChangeStringValueTo(final WebElement elementToVerify, final String string) {
        return new ExpectedCondition<Boolean>() {
            String strText = null;

            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    strText = elementToVerify.getText().trim().toLowerCase();
                    if (!string.isEmpty()) {
                        if (string.toLowerCase().equals(strText)) {
                            return true;
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "Time out on condWebElementChangeStringValueTo";
            }
        };

    }


    /***
     * Wait Condition to String value of a WebElement change to desire contain text value, case insensitive
     * @param string Desire Contain Text Value after any actions
     * @param elementToVerify WebElement text to be verify
     * @return true if WebElement String value equals to desire value
     */
    public static ExpectedCondition<Boolean> condWebElementChangeStringValueToContain(final WebElement elementToVerify, final String string) {
        return new ExpectedCondition<Boolean>() {
            String strText = null;

            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    strText = elementToVerify.getText().trim();
                    if (!string.isEmpty()) {
                        if (strText.toLowerCase().contains(string.toLowerCase())) {
                            return true;
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "Time out on condWebElementChangeStringValueToContain";
            }
        };

    }


    /**
     * Base method for Wait condition, to wait for a Tab switch by checking the Class value Equals desired String value
     *
     * @param tabElementToVerify element to check
     * @param strClass           desired class value
     * @return true if element class value Equals String value
     */
    public static boolean verifyTabSwitchedByClassEqualsString(WebElement tabElementToVerify, String strClass) {
        String elementClassValue = CSSUtils.getClassAttributeValue(tabElementToVerify);
        log.info("Checking Tab Page Class Value : " + elementClassValue);
        return (elementClassValue.equalsIgnoreCase(strClass));
    }


    /**
     * Base method for Wait condition, to wait for a Tab switch by checking the Class value Contains desired String value
     *
     * @param tabElementToVerify element to check
     * @param strClass           desired class value
     * @return true if element class value contains String value
     */
    protected static boolean verifyTabSwitchedByClassContainsString(WebElement tabElementToVerify, String strClass) {
        String elementClassValue = CSSUtils.getClassAttributeValue(tabElementToVerify);
        log.info("Checking Tab Page Class Value : " + elementClassValue);
        return (elementClassValue.toLowerCase().contains(strClass));
    }


    /**
     * Wait the WebDriver shutdown finished
     *
     * @return true when WebDriver is done
     */
    public static ExpectedCondition<Boolean> condProcessEnd() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String serviceName, driverName;
                if (PageManager.getBrowserName().equalsIgnoreCase("internet explorer")) {
                    serviceName = "iexplorer.exe";
                    driverName = "IEDriverServer.exe";
                    try {
                        if (!CommonMethods.isProcessRunning(serviceName) && !CommonMethods.isProcessRunning(driverName)) {
                            return true;
                        } else {
                            log.error("IE Driver process still running");
                            return null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                } else if (PageManager.getBrowserName().equalsIgnoreCase("chrome")) {
                    driverName = "chromedriver.exe";
                    try {
                        if (!CommonMethods.isProcessRunning(driverName)) {
                            return true;
                        } else {
                            log.error("Chrome Driver process still running");
                            return null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                } else {
                    return true;
                }
            }

            @Override
            public String toString() {
                return "Timeout on condProcessEnd";
            }
        };
    }


    public static void closeParentWindow(WebDriver driver) {
        if (PageManager.getBrowserName().equals("internet explorer")) {
            int count = processCount();
            driver.switchTo().window(parentWindowID).close();
            new WebDriverWait(driver, PageManager.waitInSeconds).until(condParentWindowClose(count));
            log.info("Parent Window Closed");
        } else {
            driver.switchTo().window(parentWindowID).close();
        }
    }


    private static int processCount() {
        int count = 0;
        String processName = "iexplore.exe";

        Process p;
        try {
            p = Runtime.getRuntime().exec("tasklist");

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
//		  System.out.println(line);
                if (line.contains(processName)) {
                    count++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static ExpectedCondition<Boolean> condParentWindowClose(final int processCount) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                int currentProcess = processCount();
//				log.info("Waiting for parent window to close");
                if (currentProcess == (processCount - 1)) {
                    return true;
                } else {
                    return null;
                }
            }
        };
    }


    /***
     * Same as ExpectedConditions.visibility of Element. This also handle the StaleElementReference Exception
     * @param element WebElement for visibility
     * @return return when WebElement become visible
     */
    public static ExpectedCondition<Boolean> condElementVisible(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    if (element.isDisplayed()) {
                        return true;
                    } else {
                        return null;
                    }
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "condElementVisible Timeout";
            }
        };
    }

    /**
     * Wait Condition for page to load by identifying WebElement 1 or WebElement 2 is present
     *
     * @param element1 WebElement 1
     * @param element2 WebElement 2
     * @return
     */
    public static ExpectedCondition<Boolean> condPageLoaded(final WebElement element1, final WebElement element2) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    try {
                        if (element1.isDisplayed()) {
                            return true;
                        } else {
                            throw new NoSuchElementException("");
                        }
                    } catch (NoSuchElementException e) {
                        try {
                            if (element2.isDisplayed()) {
                                return true;
                            } else {
                                throw new NoSuchElementException("");
                            }
                        } catch (NoSuchElementException ee) {
                            return null;
                        }
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "condPageLoaded Timeout";
            }
        };
    }

    /**
     * Wait Condition for page to load by identifying Locate  1 or Locate  2 is present
     *
     * @param by1 Locate 1
     * @param by2 Locate 2
     * @return
     */
    public static ExpectedCondition<Boolean> condPageLoaded(final By by1, final By by2) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    try {
                        if (driver.findElement(by1).isDisplayed()) {
                            return true;
                        } else {
                            throw new NoSuchElementException("");
                        }
                    } catch (NoSuchElementException e) {
                        try {
                            if (driver.findElement(by2).isDisplayed()) {
                                return true;
                            } else {
                                throw new NoSuchElementException("");
                            }
                        } catch (NoSuchElementException ee) {
                            return null;
                        }
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "condPageLoaded Timeout";
            }
        };
    }


    /***
     * Wait Condition for WebElement to scroll to the position
     * @param element WebElement to scroll
     * @param // destPosition Destination Y Position
     * @return
     */
    public static ExpectedCondition<Boolean> condDoneScrolling(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                if (currentPosition != element.getLocation().getY()) {
                    currentPosition = element.getLocation().getY();
                    return null;
                } else {
                    currentPosition = 0;
                    log.info("Scrolling completed");
                    return true;
                }
            }

            @Override
            public String toString() {
                return "Time out on condDoneScrolling";
            }
        };
    }

    /***
     * Wait Condition for Dropdown List Update
     * @param  // element
     * @return
     */
    public static ExpectedCondition<Boolean> condDropDownListUpdate(final Select select) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                List<WebElement> elementList = select.getOptions();
                int size = elementList.size();
                if (size > 1 && size == temp) {
                    log.info("size is > 1");
                    temp = 0;
                    return true;
//				} else if(!elementList.get(0).getText().isEmpty() && size ==temp){
//					log.info("Value is not ? and the size is : " + elementList.size());
//					temp = 0;
//					return true;
                } else {
                    temp = size;
                    return null;
                }
            }

            @Override
            public String toString() {
                return "Time out on condDropDownListUpdate";
            }
        };
    }





}
