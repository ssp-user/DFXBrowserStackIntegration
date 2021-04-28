package com.automation.pages.omm_admin;

import com.automation.pages.common.WebPage;
import com.automation.pages.schedule.SSA_ConfirmationPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;

public class OMM_AdminFramePage extends WebPage {

    private static Logger log = Logger.getLogger(OMM_AdminFramePage.class);


//    // Required Service Menu
//    @FindBy(css="#sMenu_topMenu > div > ul > li:nth-child(1) > a")
//    protected static WebElement ReqServices;
//
//    // Pricing Page
//    @FindBy(css="#sMenu_topMenu li:nth-child(1) li:nth-child(1) > a")
//    protected static WebElement pricing;
//
//
//    // Configure Menu
//    @FindBy(css="#sMenu_topMenu > div > ul > li:nth-child(2) > a")
//    protected static WebElement configure;
//
//    // Dealer Recommended Services
//    @FindBy(css="#sMenu_topMenu li:nth-child(2) li:nth-child(1) > a")
//    protected static WebElement dealerRecomSvc;
//
//    // Fee and Taxes
//    @FindBy(css="#sMenu_topMenu li:nth-child(2) li:nth-child(2) > a")
//    protected static WebElement feeAndTax;
//
//    // OMM Integration
//    @FindBy(css="#sMenu_topMenu li:nth-child(2) li:nth-child(3) > a")
//    protected static WebElement ommIntegration;
//
//    // Sales To Service Introduction
//    @FindBy(css="#sMenu_topMenu li:nth-child(2) li:nth-child(4) > a")
//    protected static WebElement salesToService;
//
//    // General Options
//    @FindBy(css="#sMenu_topMenu li:nth-child(2) li:nth-child(5) > a")
//    protected static WebElement generalOptions;
//
//    // EVIR Options
//    @FindBy(css="#sMenu_topMenu li:nth-child(2) li:nth-child(6) > a")
//    protected static WebElement evirOptions;
//
//    // Repairs and Concerns
//    @FindBy(css="#sMenu_topMenu li:nth-child(2) li:nth-child(7) > a")
//    protected static WebElement repairsAndConcerns;
//
//    // Discounts
//    @FindBy(css="#sMenu_topMenu li:nth-child(2) li:nth-child(8) > a")
//    protected static WebElement discounts;
//
//    // Workflow Configuration
//    @FindBy(css="#sMenu_topMenu li:nth-child(2) li:nth-child(9) > a")
//    protected static WebElement workflow;
//
//    // Loaner Configuration
//    @FindBy(xpath="//a[contains(text(),'Loaner Configuration')]")
//    protected static WebElement loaner;
//
//    // Op Codes
//    @FindBy(css="#sMenu_topMenu li:nth-child(3) li:nth-child(1) > a")
//    protected static WebElement opCodes;
//
//    // Parts
//    @FindBy(css="#sMenu_topMenu li:nth-child(3) li:nth-child(2) > a")
//    protected static WebElement parts;
//
//    // Period
//    @FindBy(css="#sMenu_topMenu li:nth-child(3) li:nth-child(3) > a")
//    protected static WebElement period;
//
//    // DMS Integration
//    @FindBy(css="#sMenu_topMenu li:nth-child(3) li:nth-child(6) > a")
//    protected static WebElement dmsIntegration;
//
//    // CRM Dashboard Options
//    @FindBy(css="#sMenu_topMenu li:nth-child(3) li:nth-child(7) > a")
//    protected static WebElement crmOptions;
//
//    // DMS Data Extraction
//    @FindBy(css="#sMenu_topMenu li:nth-child(3) li:nth-child(8) > a")
//    protected static WebElement dmsDataExtraction;
//
//    // SMS Services Configuration
//    @FindBy(css="#sMenu_topMenu li:nth-child(3) li:nth-child(9) > a")
//    protected static WebElement smsConfiguration;
//
//    // Manage Email Distribution
//    @FindBy(css="#sMenu_topMenu li:nth-child(4) li > a")
//    protected static WebElement manageEmailDistribution;

  // ***  import from TestNG


    //    // Required Service Menu
//    @FindBy(css="#sMenu_topMenu > div > ul > li:nth-child(1) > a")
//    protected static WebElement ReqServices;
    private static By bReqServicesTab = By.cssSelector("#sMenu_topMenu > div > ul > li:nth-child(1) > a");


    //   Admin
//    @FindBy(css="#sMenu_topMenu > div > ul > li:nth-child(3) > a")
//    protected static WebElement admin;
  private static By bAdminTab = By.cssSelector("#sMenu_topMenu > div > ul > li:nth-child(3) > a");


    //    // Manage Users
//    @FindBy(css="#sMenu_topMenu li:nth-child(3) li:nth-child(4) > a")
//    protected static WebElement manageUsers;
    private static By bManageUsers = By.cssSelector("#sMenu_topMenu li:nth-child(3) li:nth-child(4) > a");

    //    // Online Appointment Scheduling Options
//    @FindBy(css="#sMenu_topMenu li:nth-child(3) li:nth-child(5) > a")
//    protected static WebElement apptOptions;
    private static By bOnlineApptOptions = By.cssSelector("#sMenu_topMenu li:nth-child(3) li:nth-child(5) > a");


    //    // Chrysler Options
//    @FindBy(css="#sMenu_topMenu > div > ul > li:nth-child(4) > a")
//    protected static WebElement chryslerOptions;
    private static By bChryslerOptionsTab = By.cssSelector("#sMenu_topMenu > div > ul > li:nth-child(4) > a");

    //    // Save Button
//    @FindBy(id="ibtnSave")
//    private static WebElement saveBtn;
    private static By bSaveBtn = By.id("ibtnSave");

    // ****  import from TestNG


    public void gotoTabSelectMenu(String tab, String menu) {
        switch (tab) {
            case "required services":
                hoverOnElement(bReqServicesTab);
                sleep(2000);
                selectRequiredServicesMenu(menu);
                break;
            case "configure":
                break;
            case "admin":
                hoverOnElement(bAdminTab);
                sleep(1500);
                selectAdminMenu(menu);
                break;
            case "chrysler options":
                hoverOnElement(bReqServicesTab);
                break;
        }

    }

    private  void selectAdminMenu(String menu) {
        if (menu.equals("op codes")) {
//            pWait.until(conditionVisible(bOnlineApptOptions)).click();
        }else if (menu.equals("online appointment schedule")){
            pWait.until(conditionVisible(bOnlineApptOptions)).click();
//            driver.findElement(bOnlineApptOptions).click();
        }else {
            //
        }

    }

    private void selectRequiredServicesMenu(String menu) {
        if (menu.equals("pricing")) {
//            hoverOnElement(bAdminTab);
        }else if (menu.equals("new pricing")){
        }else {
        }

    }

    public void clickSaveBtn() {
        pWait.until(conditionVisible(bSaveBtn)).click();
//        clickElementWithSeconds(bNextBtn,1000);
    }

 }


