package com.automation.pages.wiadvisor;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class WiAdvisorROPage extends WebPage {
    
    //page object

    //Customer Info tab
    private static By bCustomerInfoTab = By.xpath("//a[text()=' Customer Info ']");
    //VIP/History tab
    private static By bVipHistoryTab = By.xpath("//a[text()=' VIP/History ']");
    //Build RO tab
    private static By bBuildRoTab = By.xpath("//a[text()=' Build RO ']");
    // Assign RO tab
    private static By bAssignRoTab = By.xpath("//a[text()=' Assign RO ']");
    // Walk Around  tab
    private static By bWalkAroundTab = By.xpath("//a[text()=' Walk Around ']");
    // Finalize RO  tab
    private static By bFinalizeRoTab = By.xpath("//a[text()=' Finalize RO ']");
    //Continue Button
    private static By bContinueBtn = By.xpath("//button[text()=' Continue ']");

    private static By bCompleteBtn = By.xpath("//button[text()='Complete']");
    //MVP Plans Button
    private static By bMVPPlansBtn = By.xpath("//button[text()='MVP Plans']");
    //VIP Report Button
    private static By bVIPReportBtn = By.xpath("//button[text()='VIP Report']");
    //Maint. Menu Button
    private static By bMaintMenuBtn = By.xpath("//button[text()='Maint. Menu']");
    //Continue Button

    //a[text()=' Customer Info ']

    private static By bButton(String sBy) {
        String sBy1 = sBy.toLowerCase().trim();
        String sBy2 = "Continue";
        switch(sBy1){
            case "continue": sBy2 = "Continue";
                break;
            case "complete": sBy2 = "Complete";
                break;
            case "mvp plans": sBy2 = "MVP Plans";
                break;
            case "vip report": sBy2 = "VIP Report";
                break;
        }
        String locator = "//button[text()='"+sBy2+"']";
        return By.xpath(locator) ;
    }


    public void clickButtonInROPage(String btn){
        String sBy1 = btn.toLowerCase().trim();
        switch(sBy1){
            case "continue":
                clickElementWithException(bContinueBtn);
                break;
            case "mvp plans":
                clickElementWithException(bMVPPlansBtn);
                break;
            case "vip report":
                clickElementWithException(bVIPReportBtn);
                break;
            case "Mmint. menu":
                clickElementWithException(bMaintMenuBtn);
                break;
            case "complete":
                clickElementWithException(bCompleteBtn);
                break;

        }
       //clickElementWithException(bButton(btn));
    }


    public void clickMenuTabInROPage(String btn){
        String sBy1 = btn.toLowerCase().trim();
        String sBy2 = "Continue";
        switch(sBy1){
            case "customer info":
                clickElementWithException(bCustomerInfoTab);
                break;
            case "vip/history":
                clickElementWithException(bVipHistoryTab);
                break;
            case "build ro":
                clickElementWithException(bBuildRoTab);
                break;
            case "assign ro":
                clickElementWithException(bAssignRoTab);
                break;
            case "walk around":
                clickElementWithException(bWalkAroundTab);
                break;
            case "finalize ro":
                clickElementWithException(bFinalizeRoTab);
                break;
        }

    }

}

