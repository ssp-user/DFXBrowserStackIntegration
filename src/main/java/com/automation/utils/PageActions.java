package com.automation.utils;

import com.automation.pages.PageManager;
import com.automation.pages.common.WebPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;

public class PageActions {

    WebDriver driver;

    private static final Logger log = Logger.getLogger(PageActions.class);

    public static void clearAndSetText(By by, String text) {
        WebDriver driver = PageManager.getDriver();
        clearAndInput(WebPage.getWait().until(ExpectedConditions.elementToBeClickable(by)), text);
    }

    public PageActions() {
        this.driver = PageManager.getDriver();
    }

    public static void clearAndInput(WebElement element, String text) {
        WebDriver driver = PageManager.getDriver();
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

    public static void clearAndSend(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public static boolean webElementHasClass(WebElement webElement, String classValue) {
        return Arrays.asList(webElement.getAttribute("class").split(" ")).contains(classValue);
    }

    public void actClick(WebElement element) {
        Actions act = new Actions(driver);
        act.click(element).perform();
    }

    public void moveToClick(WebElement element) {
        Actions act = new Actions(driver);
        act.moveToElement(element, 1, 1).click().perform();
    }

    public void hoverClick(WebElement element, long seconds) {
        Actions act = new Actions(driver);
        act.moveToElement(element, 1, 1).pause(seconds).click().perform();
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor jscript = (JavascriptExecutor) driver;
        jscript.executeScript("arguments[0].click();", element);
    }

    public String jsPageState(){
        JavascriptExecutor jsc = (JavascriptExecutor) driver;
        String state = jsc.executeScript("return document.readyState").toString();
        return state;
    }

}

