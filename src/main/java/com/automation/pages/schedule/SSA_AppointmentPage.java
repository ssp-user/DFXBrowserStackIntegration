package com.automation.pages.schedule;

import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SSA_AppointmentPage extends SSA_FramePage {

    private static Logger log = Logger.getLogger(SSA_AppointmentPage.class);

//    @FindBy(xpath="//div[@class='updates-available ng-binding ng-scope']")
//    protected static WebElement getRecallCount;
private static By bRecallCount = By.xpath("//div[@class='updates-available ng-binding ng-scope']");
//
//    @FindBy(xpath="//div[@recall='recall']")
//    protected static WebElement recallList;

    private static By bTryAgainBtn = By.xpath("//div[text()='Try again']");

    //    @FindBy(xpath="//button[contains(text(),'Confirm appointment')]")
//    protected static WebElement confirmAppt;
    private static By bConfirmBtn = By.xpath("//button[contains(text(),'Confirm appointment')]");

    //    @FindBy(xpath="//div[@class='schedule-card']/div[1]")
//    protected static WebElement appointmentTime;
    private static By bAappointmentTime = By.xpath("//div[@class='schedule-card']/div[1]");

    //
//    @FindBy(xpath="//input[@name='firstName']")
//    protected static WebElement firstName;
    private static By bFirstName = By.name("firstName");
    //
//    @FindBy(xpath="//input[@name='lastName']")
//    protected static WebElement lastName;
    private static By bLastName = By.name("lastName");
    //
//    @FindBy(xpath="//input[@name='phoneNumber']")
//    protected static WebElement phoneNum;
    private static By bPhone = By.name("phoneNumber");
    //
//    @FindBy(xpath="//input[@name='email']")
//    protected static WebElement email;
    private static By bEmail = By.name("email");

    //    @FindBy(xpath="//div[@id='test_match_btn_yes']")
//    protected static WebElement yesItsMeBtn;
    private static By bItsMeBtn = By.id("test_match_btn_yes");


    //   imported from TestNG


    public void clickBtnOnAppointment(String buttonName) {
        switch (buttonName) {
            case "CONFIRM APPOINTMENT":
                clickElementWithSeconds(bConfirmBtn, 1000);
                break;
            case "PLUS":
//                clickElementWithException(bPlusIcon);
                break;
        }

    }

    public void handleTry() {
        sleep(1500);
        try {
            driver.findElement(bTryAgainBtn).click();
            System.out.println(" Try Again happen !");
            sleep(1500);
        } catch (WebDriverException we) {

        }
    }

    public String getAppointment() {
        return pWait.until(conditionVisible(bAappointmentTime)).getText();
    }

    public void waitForPageToLoad() {                                                                                            // Wait for page to finish loading
        pWait.until(SyncPage.condDomReadyState());
        pWait.until(conditionVisible(bAappointmentTime));
    }

    public void inputCustomerData(String field, String value) {
        switch (field) {
            case "FIRST NAME":
                if (value.equalsIgnoreCase("random")){
                    value = CommonMethods.getRandomText(6);
                }
                clearAndSend(bFirstName, value);
                break;
            case "LAST NAME":
                if (value.equalsIgnoreCase("random")){
                    value = CommonMethods.getRandomText(6);
                }
                clearAndSend(bLastName, value);
                break;
            case "PHONE NUMBER":
                clearAndSend(bPhone, value);
                break;
            case "EMAIL ADDRESS":
                clearAndSend(bEmail, value);
                break;
        }
    }

    public boolean hasClickMessageBtn(String buttonName) {
        boolean result = false;
        if (buttonName.toUpperCase().contains("IT IS ME")) {
            sleep(500);
            try {
                pWait.until(conditionVisible(bItsMeBtn)).click();
                result = true;
            } catch (Exception te) {
//                System.out.println(" field is toString    " + te.getMessage());
            }
        }
        return result;
    }

    public String getRecallCount() {
        String recall = pWait.until(conditionVisible(bRecallCount)).getText().trim();
        int idx = recall.indexOf("update");
        if (idx < 0){
            return "";
        }else{
            return  recall.substring(0,idx).trim();
        }
    }

}
