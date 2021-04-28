package com.automation.pages.common;

import com.automation.pages.PageManager;
import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.json.ObjectRepositoryJsonParser;
import com.automation.utils.sync.SyncPage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;

public abstract class WebPage extends BasePage {

    protected static final byte PAGETIMEOUT = 2 * 15; /* seconds */
    protected static final byte DRIVERTIMEOUT = 6 * 15; /* seconds */
    protected static final byte SHORTTIMEOUT = 5; /* seconds */
    protected static final byte MEDIUMTIMEOUT = 1 * 15; /* seconds */    
    protected static final int LONGTIMEOUT = 10 * 15; /* seconds */
    protected static WebDriverWait lWait, dWait, pWait, sWait, mWait;
    private static final Logger logger = Logger.getLogger(WebPage.class);
    protected TestParameters tp = new TestParameters();
    protected static ObjectRepositoryJsonParser json;
    /**
     * The page object that was considered the current page before transition phase of this page object
     */
    protected final BasePage previousPage = (BasePage) PageManager.currentPage();

    final void doTransition() {
        super.doTransition();
        logger.info("Transitioning to a page " + this.getClass());
        switchFrame();
        try {
            updateCurrentPage();
        } catch (TimeoutException e) {
            throw new RuntimeException("Couldn't open a page", e);
        }
    }

    /**
     * This method will be called after successful wait until expectedConditionForPageLoading()
     * during transition phase.
     * Default implementation sets this page object as a current page.
     */
    protected void updateCurrentPage() {
        PageManager.setCurrentPage(this);
    }

    @Override
    protected void switchFrame() {
        driver.switchTo().defaultContent();
    }

    protected ExpectedCondition<?> conditionForPage() {
        return null;
    }

    public static void openURL(String url) {
        driver.get(url);
        sslCertHandler();
    }

    private static void sslCertHandler() {                                              // This is to handle SSL Cert expired message (wiAdvisor)
        boolean bIE =  PageManager.getBrowserName().equalsIgnoreCase("IE");
        if (bIE && driver.getTitle().contains("Certificate")) {
            driver.get("javascript:document.getElementById('overridelink').click()");
        }
    }

    public void openNewBrowserTabWithURL(String url, int expectedNumberOfWindow) {
        ((JavascriptExecutor) driver).executeScript("window.open();");
        changeToPageWithTitle("", expectedNumberOfWindow);
        openURL(url);
    }

    public static void setWait() {
        lWait = new WebDriverWait(driver, LONGTIMEOUT);
        dWait = new WebDriverWait(driver, DRIVERTIMEOUT);
        pWait = new WebDriverWait(driver, PAGETIMEOUT);
        sWait = new WebDriverWait(driver, SHORTTIMEOUT);
        mWait = new WebDriverWait(driver, MEDIUMTIMEOUT);        
    }

    public static void setWait(WebDriver driver) {
        lWait = new WebDriverWait(driver, LONGTIMEOUT);
        dWait = new WebDriverWait(driver, DRIVERTIMEOUT);
        pWait = new WebDriverWait(driver, PAGETIMEOUT);
        sWait = new WebDriverWait(driver, SHORTTIMEOUT);
        mWait = new WebDriverWait(driver, MEDIUMTIMEOUT);            
    }

    public static WebDriverWait getWait() {
        return dWait;
    }

    protected ExpectedCondition<WebElement> conditionClick(final WebElement element) {
        return ExpectedConditions.elementToBeClickable(element);
    }

    protected ExpectedCondition<WebElement> conditionClick(final By locator) {
        return ExpectedConditions.elementToBeClickable(locator);
    }

    protected ExpectedCondition<List<WebElement>> conditionPresenceList(final By locator) {
        return ExpectedConditions.presenceOfAllElementsLocatedBy(locator);
    }

    protected ExpectedCondition<WebElement> conditionVisible(final WebElement element) {
        return ExpectedConditions.visibilityOf(element);
    }

    protected ExpectedCondition<WebElement> conditionVisible(final By locator) {
        return ExpectedConditions.visibilityOfElementLocated(locator);
    }

    public void pageRefresh() {
        driver.navigate().refresh();
    }

    protected void waitForTitle(String titleName) {
        long startTime = System.currentTimeMillis();
        while (!driver.getTitle().contains(titleName)) {
            sleep(1000);
            if ((System.currentTimeMillis() - startTime) > 10000) {
                break;
            }
        }
    }

    protected void waitForUrl(String urlName) {
        long startTime = System.currentTimeMillis();
        while (!driver.getCurrentUrl().toLowerCase().contains(urlName.toLowerCase())) {
            sleep(1000);
            if ((System.currentTimeMillis() - startTime) > 30000) {
                break;
            }
        }
    }

    protected boolean verifyIfElementVisibleOnPage(By locator) {
        try {
            dWait.until(conditionVisible(locator));
            return true;
        } catch (StaleElementReferenceException e) {
            return false;
        } catch (WebDriverException e) {
            return false;
        }
    }

    //compare to the method below, this gives some wait time
    protected boolean verifyIfElementClickableOnPage(By locator) {
        try {
            sWait.until(conditionClick(locator));
            return true;
        } catch (StaleElementReferenceException e) {
            return false;
        } catch (WebDriverException e) {
            return false;
        }
    }

    protected boolean verifyIfElementNotShowOnThePage(By locator) {
        try {
            sWait.until(conditionVisible(locator));
            return false;
        } catch (WebDriverException e) {
            return true;
        }
    }

    protected boolean verifyIfElementShowOnThePage(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    //using for some loading element like circle to disappear, for example, wait for attribute display: none
    protected void waitForElementHasAttributeValue(By locator, String attributeName, String attributeValue) {
        long startTime = System.currentTimeMillis();
        String enabled = "";
        while (driver.findElements(locator).size() != 0) {
            sleep(1000);
            try {
                enabled = driver.findElement(locator).getAttribute(attributeName);
            } catch (WebDriverException e) {
                //exception happen, will try again
            }
            if (enabled.contains(attributeValue)) {
                //System.out.println("<====== The element '"+locator.toString()+"' has completed loading ======>");
                System.out.println("<====== Now page loading ... KEEP CALM AND CARRY ON ======>");
                break;
            } else if ((System.currentTimeMillis() - startTime) > 60000) {
                break;
            }
        }
    }

    protected boolean elementHasAttributeValue(By locator, String attributeName, String attributeValue) {
        long startTime = System.currentTimeMillis();
        String actualAttributeValue = "";
        boolean match = false;
        while (driver.findElements(locator).size() != 0) {
            try {
                actualAttributeValue = driver.findElement(locator).getAttribute(attributeName);
            } catch (WebDriverException e) {
                //exception happen, will try again
            }
            if (actualAttributeValue.contains(attributeValue)) {
                match = true;
                break;
            } else if ((System.currentTimeMillis() - startTime) > 5000) {
                match = false;
                break;
            }
        }
        return match;
    }

    public boolean elementHasAttribute(By locator, String attributeName) {
        long startTime = System.currentTimeMillis();
        boolean match = false;
        while (driver.findElements(locator).size() != 0) {
            try {
                driver.findElement(locator).getAttribute(attributeName);
                match = true;
                break;
            } catch (WebDriverException e) {
                //attribute doesn't exist
            }
            if ((System.currentTimeMillis() - startTime) > 3000) {
                match = false;
                break;
            }
        }
        return match;
    }

    public boolean webElementHasClass(WebElement webElement, String classValue) {
        sleep(1000);
        return Arrays.asList(webElement.getAttribute("class").split(" ")).contains(classValue);
    }

    public boolean webElementHasClass(By locator, String classValue) {
        return webElementHasClass(dWait.until(conditionVisible(locator)), classValue);
    }

    public boolean byElementHasClass(By locator, String classValue) {
        waitForElementWithException(locator);
        return Arrays.asList(getElementAttributeWithException(locator, "class").split(" ")).contains(classValue);
    }

    public boolean byElementContainText(By locator, String text) {
        waitForElementWithException(locator);
        return getElementAttributeWithException(locator, "innerHTML").toLowerCase().contains(text.toLowerCase());
    }

   protected void clearAndInput(WebElement element, String text) {
        Actions action = new Actions(driver);
        action.click(element)
                .sendKeys(Keys.END)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.HOME)
                .keyUp(Keys.SHIFT)
                .sendKeys(Keys.DELETE)
                .sendKeys(text)
                .perform();
    }

    protected  void updateTextBox(WebElement element, String text) { // Enter
            element.click();
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.chord(Keys.DELETE));
            element.sendKeys(text);
    }

    protected void clearAndSend(WebElement element, String text) {
    	//element.sendKeys(Keys.BACK_SPACE);
    	element.clear();
        element.sendKeys(text);
    }

    protected void clearAndSend(By locator, String text) {
        clearAndSend(dWait.until(conditionVisible(locator)), text);
    }

    public void clearWebField(WebElement element){
        while(!element.getAttribute("value").equals("")){
            element.sendKeys(Keys.BACK_SPACE);
        }
    }
    
    
    
    protected void clearAndInput(By locator, String text) {
        clearAndInput(dWait.until(conditionClick(locator)), text);
    }

//    protected void inputTextField(WebElement element, String text) {
//        element.sendKeys(text);
//    }
//
//    protected void inputTextField(By locator, String text) {
//        inputTextField(dWait.until(conditionVisible(locator)), text);
//    }

    protected void selectDropList(WebElement element, String text) {
        boolean stales = false;
        Select sel = new Select(element);
//        System.out.println("<==text .... is ...  " + text);
        if (!text.trim().isEmpty()) {
            if (text.toUpperCase().equals(text.toLowerCase())) {
                int idx = Integer.valueOf(text.trim())-1;
                if (idx >= 0){
                    sel.selectByIndex(idx);
                }
            } else {
                sel.selectByVisibleText(text);
            }
            try {
                stales = dWait.pollingEvery(200, TimeUnit.MILLISECONDS)
                        .withTimeout(1000, TimeUnit.MILLISECONDS)
                        .until(ExpectedConditions.stalenessOf(element));
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
            if (stales) {
                logger.info(" stales happen  " + element.toString());
            }
        }
    }

    protected void selectDropListByValue(WebElement element, String text) {
        boolean stales = false;
        Select sel = new Select(element);
        System.out.println("<==text .... is ...  " + text);
        if (!text.trim().isEmpty()) {
            if (text.toUpperCase().equals(text.toLowerCase())) {
                int idx = Integer.valueOf(text.trim()) - 1;
                if (idx >= 0){
                    sel.selectByValue(String.valueOf(idx));
//                    sel.selectByIndex(idx);
                }
            } else {
                sel.selectByVisibleText(text);
            }
            try {
                stales = dWait.pollingEvery(200, TimeUnit.MILLISECONDS)
                        .withTimeout(500, TimeUnit.MILLISECONDS)
                        .until(ExpectedConditions.stalenessOf(element));
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
            if (stales) {
                logger.info(" stales happen  " + element.toString());
            }

        }
    }


    protected void selectDropList(By locator, String text) {
        selectDropList(pWait.until(conditionClick(locator)), text);
    }

    protected String getElementAttributeWithException(By locator, String attributeName) {
        long startTime = System.currentTimeMillis();
        String elementValue = "";
        setWait();
        elementValue  = dWait.until(conditionVisible(locator)).getAttribute(attributeName);
        boolean noException = false;
        while (!noException) {
            try {
//                dWait.until(conditionVisible(locator));
                elementValue = dWait.until(conditionVisible(locator)).getAttribute(attributeName);
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on get " + locator + " attribute method ======>");
                noException = false;
                sleep(1000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException exception occurred on get " + locator + " attribute method ======>");
                noException = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 30000) {
                Assert.fail("<====== " + locator + " not able to get attribute " + attributeName + " ======>");
                break;
            }
        }
        return elementValue;
    }

    protected String getElementAttribute(By locator, String attributeName) {
//        long startTime = System.currentTimeMillis();
//        setWait();
        String elementValue = driver.findElement(locator).getAttribute(attributeName);
//        boolean noException = false;
//        while (!noException) {
//            try {
//                elementValue = driver.findElement(locator).getAttribute(attributeName);
//                noException = true;
//            } catch (StaleElementReferenceException e) {
//                System.out.println("<====== Stale Element Reference Exception occurred on get " + locator + " attribute method ======>");
//                noException = false;
//                sleep(1000);
//            } catch (WebDriverException e) {
//                System.out.println("<====== WebDriverException exception occurred on get " + locator + " attribute method ======>");
//                noException = false;
//                sleep(1000);
//            }
//            if ((System.currentTimeMillis() - startTime) > 10000) {
//                Assert.fail("<====== " + locator + " not able to get attribute " + attributeName + " ======>");
//                break;
//            }
//        }
        return elementValue;
    }

    protected void clearAndInputElementWithException(By locator, String txt) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                dWait.until(conditionVisible(locator));
                clearAndSend(locator, txt) ;
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on clearAndInput " + locator + " attribute method ======>");
                noException = false;
                sleep(500);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException occurred on clearAndInput " + locator + " attribute method, will try again ======>");
                noException = false;
                sleep(500);
            }
            if ((System.currentTimeMillis() - startTime) > 60000) {
                Assert.fail("<====== " + locator + " not found on page ======>");
                break;
            }
        }
    }

    protected String getElementTextWithException(By locator) {
        long startTime = System.currentTimeMillis();
        String elementValue = "";
        boolean noException = false;
        while (!noException) {
            try {
                pWait.until(conditionVisible(locator));
                elementValue = driver.findElement(locator).getText();
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on get " + locator + " text method ======>");
                noException = false;
                sleep(1000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException occurred on get " + locator + " text method ======>");
                noException = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 60000) {
                break;
            }
        }
        return elementValue;
    }

    protected void clickElementWithException(By locator) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        int i = 0;
        setWait();
        while (!noException) {
            try {
                sWait.until(conditionVisible(locator)).click();
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on click " + locator + " method ======>");
                noException = false;
                sleep(500);
            } catch (WebDriverException e) {
//                System.out.println("<==Exception happen ... " + e.getMessage());
//                System.out.println("<===  now we waiting for clicking '" + locator + "  ======>");
                noException = false;
                sleep(500);
            }
            i++;
            if ((System.currentTimeMillis() - startTime) > 60000) {
                Assert.fail("<====== " + locator + " not found on page, OR~ not clickable =.= ======>");
                break;
            }
        }
        System.out.println("<======  clicking  on  element " + locator + " " + i +" times  ======>");
    }

    protected void clickElementWithException(WebElement element) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        int i = 0;
        setWait();
        while (!noException) {
            try {
                sWait.until(conditionVisible(element)).click();
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred ======>");
                noException = false;
                sleep(500);
            } catch (WebDriverException e) {
                noException = false;
                sleep(500);
            }
            i++;
            if ((System.currentTimeMillis() - startTime) > 60000) {
                Assert.fail("<====== WebElement not found on page, OR~ not clickable =.= ======>");
                break;
            }
        }
        System.out.println("<======  clicking  on  WebElement " + " " + i +" times  ======>");
    }

    protected void clickElementWithSeconds(By locator, long millsecs) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
//                System.out.println(" start to click '" + locator + "  ======>" + System.currentTimeMillis() );
                driver.findElement(locator).click();
//                System.out.println(" done to click " + locator + "  ======>");
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on click " + locator + " method ======>");
                noException = false;
                sleep(500);
            } catch (WebDriverException e) {
                System.out.println("<====  Exception Happened ,  waiting for Re-clicking '" + locator + "  ======>");
                noException = false;
                sleep(500);
            }
            if ((System.currentTimeMillis() - startTime) > millsecs) {
                break;
            }
        }
    }

    protected void clickElementTimesTillDisappear(WebElement element, int iClick) {
         int i= 0;
         while (SyncPage.isElementDisplayed(element) & (i < iClick)){
          try {
              i++;
//              System.out.println("<=  Element click on next button "+ i + " times > " + SyncPage.isElementDisplayed(element));
//              System.out.println("<=   next button is .. "+ element.toString());
             element.click();
              moveToClick(element);
            } catch (WebDriverException e) {
//                logger.info(element.toString() + "  click Exception happen  " + e.getMessage());
//                System.out.println("<=  click " + i + " times Exception =>" + e.getMessage() );
               break;
            }
            sleep(500);
        }
    }

    protected void clickElementTimesTillDisappear(By locator, int iClick) {
        int i= 0;
        while ( (i < iClick)){
            try {
                i++;
//                System.out.println("\n <= start to  click "+ i + " times on locator -- " + locator.toString() );
                driver.findElement(locator).click();
                sleep(800);
//                System.out.println("\n <=  done to  click "+ i + " times on locator -- " + locator.toString() );
//                moveToClick(driver.findElement(locator));
                if (!SyncPage.isElementDisplayed(driver.findElement(locator))){
//                    System.out.println("\n  locator not displayed  -- " + locator.toString() );
                    break;
                }
            } catch (WebDriverException e) {
//                System.out.println("<=  click " + i + " Exception occurred  =>" + e.getMessage());
                break;
            }
//            sleep(500);
        }
    }

    protected void selectDropListElementWithException(By locator, String text) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                selectDropList(locator, text);
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on select drop " + locator + " method ======>");
                noException = false;
                sleep(1000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException occurred on select drop " + locator + " attribute method, will try again ======>");
                noException = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 10000) {
                Assert.fail("<====== " + locator + " either not found or drop down value '" + text + "' not available ======>");
                break;
            }
        }
    }

    protected void waitForElementVisibleWithException(By locator) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                dWait.until(conditionVisible(locator));
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on wait for " + locator + " method ======>");
                noException = false;
                sleep(1000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException exception occurred on wait " + locator + " attribute method, will try again ======>");
                noException = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 30000) {
                break;
            }
        }
    }

    protected void waitForElementWithException(By locator) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                driver.findElements(locator);
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on wait for " + locator + " method ======>");
                noException = false;
                sleep(1000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException occurred on wait " + locator + " attribute method, will try again ======>");
                noException = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 20000) {
                break;
            }
        }
    }

    protected void waitForElementWithExceptionWithSecs(By locator, long millsecs) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                driver.findElements(locator);
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on wait for " + locator + " method ======>");
                noException = false;
                sleep(1000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException occurred on wait " + locator + " attribute method, will try again ======>");
                noException = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > millsecs) {
                break;
            }
        }
    }

    protected void waitForElementWithExceptionIfNotAvailableThenRefresh(By locator) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                driver.findElements(locator);
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on wait for " + locator + " method, page will refresh ======>");
                noException = false;
                pageRefresh();
                sleep(5000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException occurred on wait " + locator + " attribute method, page will refresh, will try again ======>");
                noException = false;
                pageRefresh();
                sleep(5000);
            }
            if ((System.currentTimeMillis() - startTime) > 50000) {
                break;
            }
        }
    }

    protected String getCurrentTime(long dateAdjust) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(dateAdjust);
        String currentTime = dateFormat.format(date) + " " + timeFormat.format(date);
        return currentTime;
    }

    protected void actionClick(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//        new Actions(driver()).moveToElement(element).click().perform();
        new Actions(driver).moveToElement(element).click().perform();
    }

    protected void checkBox(WebElement element, String input) {
        List<String> TRUES = Arrays.asList("TRUE", "YES", "Y");
        List<String> FALSES = Arrays.asList("FALSE", "NO", "N");
        if (StringUtils.isNotBlank(input)) {
            if (TRUES.contains(input.toUpperCase())) {
                if (!element.isSelected()) {
                    clickBox(element);
                }
            } else if (FALSES.contains(input.toUpperCase())) {
                if (element.isSelected()) {
                    clickBox(element);
                }
            } else {
                throw new IllegalArgumentException(
                        String.format("Illegal argument %s for specifying boolean, please try one of True/False, Yes/No, Y/N", input));
            }
        }
    }

    protected void checkBox(By locator, String input) {
        List<String> TRUES = Arrays.asList("TRUE", "YES", "Y");
        List<String> FALSES = Arrays.asList("FALSE", "NO", "N");
        WebElement element = dWait.until(conditionClick(locator));
        if (StringUtils.isNotBlank(input)) {
            if (TRUES.contains(input.toUpperCase())) {
                if (!element.isSelected()) {
                    clickBox(element);
                }
            } else if (FALSES.contains(input.toUpperCase())) {
                if (element.isSelected()) {
                    clickBox(element);
                }
            } else {
                throw new IllegalArgumentException(
                        String.format("Illegal argument %s for specifying boolean, please try one of True/False, Yes/No, Y/N", input));
            }
        }
    }

    protected String getCurrentFrame() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String currentFrame = (String) jsExecutor.executeScript("return self.name");
        System.out.println("<====== The current frame is " + currentFrame + " ======>");
        return currentFrame;
    }

    protected void clickBox(WebElement element) {
        Boolean check = element.isSelected();
        dWait.until(conditionClick(element)).click();
        dWait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return (check != element.isSelected());
            }
        });
    }

    protected void hoverOnElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions action = new Actions(getDriver());
        action.moveToElement(element).build().perform();
    }

    protected void moveToClick(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element, 3, 3).click().perform();
    }

    protected void moveToClick(By locator) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(locator), 1, 1).click().perform();
    }


    protected void hoverClick(WebElement element, long seconds) {
        Actions action = new Actions(driver);
        action.moveToElement(element, 1, 1).pause(seconds).click().perform();
    }

    protected void jsClick(WebElement element) {
        JavascriptExecutor jscript = (JavascriptExecutor) driver;
        jscript.executeScript("arguments[0].click();", element);
    }

    protected void jsClick(By locator) {
        jsClick(dWait.until(conditionClick(locator)));
    }

    protected void jsSetValue(By locator, String text) {
        JavascriptExecutor jscript = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(locator);
        jscript.executeScript("arguments[0].innerHTML = arguments[1]", element, text);
    }

    protected void jsSetAttribute(By locator, String attributeName, String attributeValue) {
        JavascriptExecutor jscript = (JavascriptExecutor) driver;
        waitForElementWithException(locator);
        WebElement element = driver.findElement(locator);
        jscript.executeScript("arguments[0].setAttribute('" + attributeName + "','" + attributeValue + "');", element);
    }

    protected String jsGetAttribute(WebElement element, String attribute) {
        JavascriptExecutor jscript = (JavascriptExecutor) driver;
        String value = (String) jscript.executeScript("return arguments[0]." + attribute + "", element);
        return value;
    }

    protected String jsGetAttribute(By locator, String attribute) {
        String value = jsGetAttribute(dWait.until(conditionVisible(locator)), attribute);
        return value;
    }

    protected Boolean jsGetCheckedStatus(By locator) {
        JavascriptExecutor jscript = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(locator);
        return (Boolean) jscript.executeScript("return arguments[0].checked", element);
    }

    protected void scrollElement(By locator) {
        JavascriptExecutor jscript = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(locator);
        jscript.executeAsyncScript("arguments[0].scrollIntoView(false);", element);
    }

    protected void keyboardTab() {
        try {
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_TAB);
            r.keyRelease(KeyEvent.VK_TAB);
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void keyboardEnter() {
        try {
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void keyboardDown() {
        try {
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_DOWN);
            r.keyRelease(KeyEvent.VK_DOWN);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    protected void scrollPageUp(int upValue) {
        JavascriptExecutor jscript = (JavascriptExecutor) driver;
        jscript.executeScript("window.scrollBy(0,-" + upValue + ")");
    }

    protected void scrollPageDown(int downValue) {
        JavascriptExecutor jscript = (JavascriptExecutor) driver;
        jscript.executeScript("window.scrollBy(0," + downValue + ")");
    }


    /***
     * Scroll WebElement into the Center of the browser window. Scroll the entire page
     * @param element The target WebElement need to be scroll to the center of browser
     */
    protected void scrollElementToCenterView(WebElement element) {
        Dimension d = driver.manage().window().getSize();
        int center = d.getHeight() / 2;
        Point p = element.getLocation();
        int y = p.getY();
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String jscript = "scroll(" + p.getX() + ", " + (y - center) + ");";
        js.executeScript(jscript);

    }

    /***
     * Scroll WebElement into the Center of the browser window. Scroll the entire page
     * @param element The target WebElement need to be scroll to the center of browser
     */
    protected void scrollElementToCenterViewClick(WebElement element) {
        scrollElementToCenterView(element);
        element.click();
    }

    protected  void browserClick(WebElement element) {

        if (tp.getBrowserName().equalsIgnoreCase("chrome")) {
            logger.info("Chrome click");
            element.click();
        } else if (tp.getBrowserName().equalsIgnoreCase("IE")) { //
            logger.info("IE click");
            element.sendKeys("\n"); // Can not select customer list.
        } else if (tp.getBrowserName().equalsIgnoreCase("firefox")) {
            logger.info("Firefox click");
            element.click();
        }

    }


    /***
     * Scroll To WebElement Scroll the entire page
     * @param element scroll to the  target WebElement
     */
    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String jscript = "return arguments[0].scrollIntoView();";
        js.executeScript(jscript, element);

    }

    protected void scrollPageRight(int rightValue) {
        JavascriptExecutor jscript = (JavascriptExecutor) driver;
        jscript.executeScript("window.scrollBy(" + rightValue + ",0)");
    }

    protected void mouseHoverOnElement(By locator) {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(locator);
        action.moveToElement(element).perform();
    }


    protected void mouseScrollPageDown(int xAxis, int yAxis, int mouseWheelValue) throws AWTException {
        Robot robot = new Robot();
        robot.setAutoDelay(500);
        robot.mouseMove(xAxis, yAxis);
        robot.mouseWheel(mouseWheelValue);
    }

    protected void mouseClickElementDown(By locator) throws AWTException {
        WebElement el = driver.findElement(locator);
        Robot robot = new Robot();
        robot.setAutoDelay(500);
        Point pi = el.getLocation();
        int x = pi.getX();
        int y = pi.getY() + 130; //this is to move down to year minus location
        System.out.println("the current mouse location" + pi);
        robot.mouseMove(x, y);
        robot.mouseWheel(-1);
    }

    protected void mouseClickElement(By locator) throws AWTException {
        WebElement el = driver.findElement(locator);
        Robot robot = new Robot();
        robot.setAutoDelay(500);
        Point pi = el.getLocation();
        int x = pi.getX() + 25;
        int y = pi.getY() + 200;
        System.out.println("the current mouse location" + pi);
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void closeCurrentTab() throws AWTException {
        Robot robot = new Robot();
        robot.setAutoDelay(500);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_W);
    }

    public void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    protected void waitForPageLoading() {
//        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//    }


    public String getFileName(String fileLocation, String fileType) {
        File file = new File(fileLocation);
        String fileNameFinal = null;
        String[] files = file.list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if (name.toLowerCase().endsWith(fileType)) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        for (String fileName : files) {
            fileNameFinal = fileName;
            System.out.println("<====== The file name is " + fileName + " ======>");
        }
        return fileNameFinal;
    }


    public int readRowNumberFromFile(String fileLocation) {
        File file = new File(fileLocation);
        int finalCount = 0;
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                LineNumberReader lr = new LineNumberReader(fr);
                int lineNumberCount = 0;
                while (lr.readLine() != null) {
                    lineNumberCount++;
                }
                finalCount = lineNumberCount;
                System.out.println("<====== The total row number of the file should be " + lineNumberCount + " ======>");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("<====== The file is not there ======>");
        }
        return finalCount;
    }

    public int getNumberOfTabOpen() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        return tabs.size();
    }

    public void changeToPageWithTitle(String expectedTitle, int expectedNumberOfWindows) {
        pWait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
        String currentTitle;
        String currentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            currentTitle = driver.getTitle();
            if (currentTitle.equals(expectedTitle) || currentTitle.toLowerCase().contains("working")) {
                break;
            } else if (currentTitle.toLowerCase().contains("Response")) {
                System.out.println("<====== The target page is not loading, test fail here ======>");
                break;
            }
        }
    }

    /**
     * An expectation for checking WebElement with given locator has specific text
     *
     * @param locator used to find the element
     * @param value   used as expected text
     * @return Boolean true when element has text value equal to @value
     */
    protected   ExpectedCondition<Boolean> conditionTextNotToBe(final By locator, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    currentValue = driver.findElement(locator).getText();
                    return !currentValue.equals(value);
                } catch (Exception e) {
                    return false;
                }
            }
        };
    }

    /***
     * Wait for Condition: WebElement display inner text
     * @param locator used to find the element
     * @return true once the element display the inner text
     */
    protected  ExpectedCondition<Boolean> conditionElementTextNotEmpty(final By locator){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver){
                if(!driver.findElement(locator).getText().isEmpty()){
                    return true;
                } else {
                    return false;
                }
            }
            @Override
            public String toString(){
                return "condWebElementTextDisplayed";
            }
        };
    }

    protected void hLightElement(By locator) {
        WebElement iAU = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("element = arguments[0];" +
                "original_style = element.getAttribute('style');" +
                "element.setAttribute('style', original_style + \";" +
                "background: yellow; border: 2px solid red;\");" +
                "setTimeout(function(){element.setAttribute('style', original_style);}, 1000);", iAU);
//        System.out.println("Position is .. " + iAU.getLocation().toString());
    }
    
    public static void initializeJSONObject() {
    	if (json==null) {
			json = new ObjectRepositoryJsonParser(System.getProperty("user.dir") + "/config/OR.json");
		}
    }    

    protected WebElement findElement(String objectPath) {
    	return driver.findElement(json.getObjectLocator(objectPath));    	
    }    

    protected By findLocator(String objectPath) {
    	return json.getObjectLocator(objectPath);    	
    }    
    
    protected void enterCardNumber(By locator, String cardNumber) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                dWait.until(conditionVisible(locator));
                enterNumberAsPerCharacters(locator, cardNumber) ;
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on clearAndInput " + locator + " attribute method ======>");
                noException = false;
                sleep(500);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException occurred on clearAndInput " + locator + " attribute method, will try again ======>");
                noException = false;
                sleep(500);
            }
            if ((System.currentTimeMillis() - startTime) > 60000) {
                Assert.fail("<====== " + locator + " not found on page ======>");
                break;
            }
        }
    }
    
    protected void enterNumberAsPerCharacters(By locator, String text) {
    	delayedEntry(dWait.until(conditionVisible(locator)), text);
    }   
    
    protected void delayedEntry(WebElement element, String text) {
        for (int i = 0; i < text.length(); i++) {
			element.sendKeys(Character.toString(text.charAt(i)));
			sleep(100);
		}
    }    

}


