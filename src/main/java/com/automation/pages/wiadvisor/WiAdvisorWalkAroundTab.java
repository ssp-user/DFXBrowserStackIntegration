package com.automation.pages.wiadvisor;

import org.openqa.selenium.By;

public class WiAdvisorWalkAroundTab extends WiAdvisorROPage {
    //locator
    private static By bContinueONWalkAround = By.xpath("//button[@ng-click='repairOrder.continue()']");

    public void clickOnContinueWalkAround(){
        clickElementWithException(bContinueONWalkAround);
    }
}
