package com.automation.pages.tech_inspection.ServiceTab;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;

public class WalkAroundTab extends WebPage {

    //Walk around page locator
    private static By commenetsWithDescription(String description){
        String locator = "//span[contains(text(),'"+description+"')]/parent::td/following-sibling::td[2]/textarea";
        return By.xpath(locator);
    }

    //Save button on the walk around page
    private static By saveOnWalkAround = By.id("WalkAroundTab_btnSaveWalkAround");

    public void setCommentsWithDescription(String comments, String description){
        if(driver.findElements(commenetsWithDescription(description)).size()!=0){
            scrollPageDown(1000);
        }
        clearAndSend(commenetsWithDescription(description),comments);
    }

    public void clickOnSaveONWalkAround(){
        dWait.until(conditionClick(saveOnWalkAround)).click();
    }

    public String verifyCommentsWithDescription(String description){
        String actualComments=dWait.until(conditionVisible(commenetsWithDescription(description))).getText();
        return actualComments;
    }
}
