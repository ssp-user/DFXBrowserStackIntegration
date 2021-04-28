package com.automation.pages.schedule;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SSA_ConcernsPage extends SSA_FramePage {

    private static Logger log = Logger.getLogger(SSA_ConcernsPage.class);

    private static By bTryAgainBtn = By.xpath("//div[text()='Try again']");

      private static By bAddConcernsList1 = By.xpath("//div[@class='btn btn--add']/span");
    private static By bAddConcernsList = By.xpath("//div[@class='btn btn--add']");

    private static By bConcernsCommentList = By.xpath("//input[@ng-model='service.comment']");

      private static By bNextBtn = By.xpath("//div[@class='centering']/button[@ng-click='next()']");

      private static By bConcernsTab = By.xpath("//span[contains(text(),'Customer Сoncerns')]");


    public void clickTab(String tab) {
        switch (tab){
            case "CUSTOMER CONCERNS":
                clickElementWithSeconds(bConcernsTab,1000);
                break;
            case "Factory Scheduled Maintenance":
                break;
            case "Schedule":
                break;
        }

    }

    public void addConcernsComment(int item, String comment) {
//        int size = driver.findElements(bAddConcernsList).size();
        List<WebElement> elements = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bAddConcernsList));
//        int size = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bAddConcernsList)).size();
        if (elements.size() > 0 ){
            pWait.until(conditionClick(elements.get(item-1))).click();;
//            driver.findElements(bAddConcernsList).get(item-1).click();
            sleep(800);
            if (!comment.isEmpty()){
                WebElement element = pWait.until(ExpectedConditions.visibilityOf(driver.findElements(bConcernsCommentList).get(item-1)));
                clearAndSend(element,comment);
            }
        }

    }


}
