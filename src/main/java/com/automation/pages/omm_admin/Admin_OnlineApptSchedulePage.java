package com.automation.pages.omm_admin;

import com.automation.pages.schedule.SSA_FramePage;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Admin_OnlineApptSchedulePage extends OMM_AdminFramePage {

    private static Logger log = Logger.getLogger(Admin_OnlineApptSchedulePage.class);


//    // Page Title
//    @FindBy(id="lblPageTitle")
//    private static WebElement pageTitle;
//
//    // Submit Result Successful Msg
//    @FindBy(id="lblStatusMessage")
//    private static WebElement resultMsg;
//
//    @FindBy(css="#rbListEnabledRecallsInOSS_0")
//    protected static WebElement enableOssRecall;
       private static By  bEnableOssRecall = By.cssSelector("#rbListEnabledRecallsInOSS_0");
//
//    @FindBy(css="#rbListEnabledRecallsInOSS_1")
//    protected static WebElement disableOssRecall;
      private static By  bDisableOssRecall = By.cssSelector("#rbListEnabledRecallsInOSS_1");
//
//    @FindBy(css="#rbListEnableAppointmentWithoutVIN_0")
//    protected static WebElement enableVehicleSpecs;
      private static By  bEnableApptNotWIN = By.cssSelector("#rbListEnableAppointmentWithoutVIN_0");
//
//    @FindBy(css="#rbListEnableAppointmentWithoutVIN_1")
//    protected static WebElement disableVehicleSpecs;
      private static By  bDisableApptNotWIN = By.cssSelector("#rbListEnableAppointmentWithoutVIN_1");

    //   imported from TestNG



    public void waitForPageToLoad(){																							// Wait for page to finish loading
        pWait.until(SyncPage.condDomReadyState());
//        dWait.until(conditionVisible(bWeekRightBtn));
//        dWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bSelectTime));
    }


    public void setConfigureData(String field, String value) {
        if (field.equalsIgnoreCase("Enable recalls in OSS")) {
            setEnableRecallOSS(value.toLowerCase());
        }else if (field.equalsIgnoreCase("Enable Appointment Without VIN in OSS")){
            setEnableApptNoWINOSS(value.toLowerCase());
        }else {
            //
        }

    }

    private void setEnableRecallOSS(String value) {
        if (value.equals("yes")) {
            pWait.until(conditionVisible(bEnableOssRecall)).click();
        }else if (value.equals("no")){
            pWait.until(conditionVisible(bDisableOssRecall)).click();
        }else {
            //
        }

    }

    private void setEnableApptNoWINOSS(String value) {
        if (value.equals("yes")) {
            pWait.until(conditionVisible(bEnableApptNotWIN)).click();
        }else if (value.equals("no")){
            pWait.until(conditionVisible(bDisableApptNotWIN)).click();
        }else {
            //
        }

    }


}
