package com.automation.pages.schedule;

import com.automation.pages.common.WebPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class SSA_FramePage extends WebPage {


    private static Logger log = Logger.getLogger(SSA_FramePage.class);


    private static By bTryAgainBtn = By.xpath("//div[text()='Try again']");

    //    @FindBy(xpath="//div[@class='centering']/button[@ng-click='next()']")
//    protected static WebElement nextBtn;
    private static By bNextBtn = By.xpath("//div[@class='centering']/button[@ng-click='next()']");

    public void handleTry() {
        sleep(1500);
        try {
            driver.findElement(bTryAgainBtn).click();
            System.out.println(" Try Again happen !");
            sleep(1500);
        } catch (WebDriverException we) {

        }
    }

    public void clickNextBtn() {
        pWait.until(conditionVisible(bNextBtn)).click();
//        clickElementWithSeconds(bNextBtn,1000);
    }

 }


