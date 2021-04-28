package com.automation.pages.common;

import com.automation.pages.PageManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PageAction extends BasePage {

    protected static final byte PAGETIMEOUT = 2 * 30; /* seconds */
    protected static final byte DRIVERTIMEOUT = 2 * 6; /* seconds */
    protected static final byte SHORTTIMEOUT = 3; /* seconds */
    protected static WebDriverWait dWait, pWait, sWait;
    private static final Logger logger = Logger.getLogger(PageAction.class);


    @Override
    protected void switchFrame() {
        driver.switchTo().defaultContent();
    }

    protected ExpectedCondition<?> conditionForPage() {
        return null;
    }

    public static void openURL(String url) {
        driver.get(url);
    }

    public void openNewBrowserTabWithURL(String url, int expectedNumberOfWindow) {
        ((JavascriptExecutor) driver).executeScript("window.open();");
        changeToPageWithTitle("", expectedNumberOfWindow);
        openURL(url);
    }

    public static void setWait() {
        dWait = new WebDriverWait(driver, DRIVERTIMEOUT);
        pWait = new WebDriverWait(driver, PAGETIMEOUT);
        sWait = new WebDriverWait(driver, SHORTTIMEOUT);
    }

    public static void setWait(WebDriver driver) {
        dWait = new WebDriverWait(driver, DRIVERTIMEOUT);
        pWait = new WebDriverWait(driver, PAGETIMEOUT);
        sWait = new WebDriverWait(driver, SHORTTIMEOUT);
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
            if ((System.currentTimeMillis() - startTime) > 30000) {
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
            sWait.until(conditionVisible(locator));
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
            dWait.until(conditionVisible(locator));
            return false;
        } catch (WebDriverException e) {
            return true;
        }
    }


    protected boolean verifyIfElementNotShowOnThePage1(By locator) {

       Boolean dd =  dWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

        try {
            dWait.until(conditionVisible(locator));
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

    protected boolean webElementHasClass(WebElement webElement, String classValue) {
        sleep(2000);
        return Arrays.asList(webElement.getAttribute("class").split(" ")).contains(classValue);
    }

    protected boolean webElementHasClass(By locator, String classValue) {
        return webElementHasClass(dWait.until(conditionVisible(locator)), classValue);
    }



    protected void clearAndInput(WebElement element, String text) {
        Actions act = new Actions(driver);
        act.click(element)
                .sendKeys(Keys.END)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.HOME)
                .keyUp(Keys.SHIFT)
                .sendKeys(Keys.DELETE)
                .sendKeys(text)
                .perform();
    }

    protected void clearAndInput(By locator, String text) {
        clearAndInput(dWait.until(conditionClick(locator)), text);
    }

    protected void clearAndInputTextField(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    protected void clearAndInputMultipleLine(By locator, String text) {
        clearAndInputTextField(dWait.until(conditionVisible(locator)), text);
    }

    protected void inputTextField(WebElement element, String text) {
        element.sendKeys(text);
    }

    protected void inputTextField(By locator, String text) {
        inputTextField(dWait.until(conditionVisible(locator)), text);
    }

      protected void clearAndSend(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }


    protected void clearnAndInputElementWithException(By element, String txt) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                dWait.until(conditionVisible(element));
                clearAndInputMultipleLine(element, txt);
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on clearAndInput " + element + " attribute method ======>");
                noException = false;
                sleep(1000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException occurred on clearAndInput " + element + " attribute method, will try again ======>");
                noException = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 60000) {
                break;
            }
        }
    }

    protected String getElementTextWithException(By element) {
        long startTime = System.currentTimeMillis();
        String elementValue = "";
        boolean noException = false;
        while (!noException) {
            try {
                dWait.until(conditionVisible(element));
                elementValue = driver.findElement(element).getText();
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on get " + element + " text method ======>");
                noException = false;
                sleep(1000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException occurred on get " + element + " text method ======>");
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
        while (!noException) {
            try {
                dWait.until(conditionVisible(locator));
                dWait.until(conditionVisible(locator)).click();
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on click " + locator + " method ======>");
                noException = false;
                sleep(1000);
            } catch (WebDriverException e) {
                /*if(element.toString().contains("modalCustomerResult")){
                    System.out.println("<====== waiting for Advisor Check-in PROCESS YOUR REQUEST to complete ======> ");
                }else{
                    System.out.println("<====== wait and be patient make you healthy, now we waiting for clicking '"+element+"' (○'◡'○)  ======>");
                }*/
                System.out.println("<====== wait and be patient make you healthy, now we waiting for clicking '" + locator + "' (○'◡'○)  ======>");
                noException = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 60000) {
//                Assert.fail("<====== " + locator + " not found on page, OR~ not clickable =.= ======>");
                break;
            }
        }
    }

    protected void clickElementWithSeconds(By element, long millsecs) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                driver.findElement(element).click();
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on click " + element + " method ======>");
                noException = false;
                sleep(500);
            } catch (WebDriverException e) {
                System.out.println("<====== wait and be patient make you healthy, now we waiting for clicking '" + element + "' (○'◡'○)  ======>");
                noException = false;
                sleep(500);
            }
            if ((System.currentTimeMillis() - startTime) > millsecs) {
                break;
            }
        }
    }

    protected void waitForElementVisibleWithException(By element) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                dWait.until(conditionVisible(element));
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on wait for " + element + " method ======>");
                noException = false;
                sleep(1000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException exception occurred on wait " + element + " attribute method, will try again ======>");
                noException = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 20000) {
                break;
            }
        }
    }

    protected void waitForElementWithException(By element) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                driver.findElements(element);
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on wait for " + element + " method ======>");
                noException = false;
                sleep(1000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException occurred on wait " + element + " attribute method, will try again ======>");
                noException = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 20000) {
                break;
            }
        }
    }

    protected void waitForElementWithExceptionWithSecs(By element, long millsecs) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                driver.findElements(element);
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on wait for " + element + " method ======>");
                noException = false;
                sleep(1000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException occurred on wait " + element + " attribute method, will try again ======>");
                noException = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > millsecs) {
                break;
            }
        }
    }

    protected void waitForElementWithExceptionIfNotAvailableThenRefresh(By element) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                driver.findElements(element);
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on wait for " + element + " method, page will refresh ======>");
                noException = false;
                pageRefresh();
                sleep(5000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException occurred on wait " + element + " attribute method, page will refresh, will try again ======>");
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

    protected void clearAndSend(By locator, String text) {
        clearAndInput(dWait.until(conditionClick(locator)), text);
    }

    protected void actClick(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 25);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        new Actions(driver()).moveToElement(element).click().perform();
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

    protected void moveToElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions builder = new Actions(getDriver());
        builder.moveToElement(element).build().perform();
    }

    protected void moveToClick(WebElement element) {
        Actions act = new Actions(driver);
        act.moveToElement(element, 1, 1).click().perform();
    }

    protected void hoverClick(WebElement element, long seconds) {
        Actions act = new Actions(driver);
        act.moveToElement(element, 1, 1).pause(seconds).click().perform();
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

    protected void mouseClickElementDown(By element) throws AWTException {
        WebElement el = driver.findElement(element);
        Robot robot = new Robot();
        robot.setAutoDelay(500);
        Point pi = el.getLocation();
        int x = pi.getX();
        int y = pi.getY() + 130; //this is to move down to year minus location
        System.out.println("the current mouse location" + pi);
        robot.mouseMove(x, y);
        robot.mouseWheel(-1);
    }

    protected void mouseClickElement(By element) throws AWTException {
        WebElement el = driver.findElement(element);
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

    protected void waitForPageLoading() {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }


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

}


