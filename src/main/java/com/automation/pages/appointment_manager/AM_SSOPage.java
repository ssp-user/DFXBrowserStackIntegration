package com.automation.pages.appointment_manager;

import com.automation.pages.common.WebPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AM_SSOPage extends WebPage {

    private static Logger log = Logger.getLogger(AM_SSOPage.class.getName());

    private static By bQuoteBtn = By.cssSelector("#services [ng-click*='generateQuote']");

    //    protected static WebElement submitBtn;
    private static By bSubmitBtn = By.id("btnTest");

    //    protected static WebElement partnerID;
    private static By bPartnerID = By.id("txtPartnerID");

    //    protected static WebElement SharedPass;
    private static By bSharedPassord = By.id("txtSharedPassword");

    //    protected static WebElement dfxID;
    private static By bDfxID = By.id("txtDealerID");

    //    protected static WebElement dfxSysUserID;
    private static By bDfxSysUserID = By.id("txtUserID");

    //    protected static WebElement custName;
    private static By bCustomerName = By.id("txtCustomerName");

    //    protected static WebElement custPhone;
    private static By bCustomerPhone = By.id("txtPhone");

    //    protected static WebElement custVIN;
    private static By bCustomerVIN = By.id("txtVIN");

    //    protected static List<WebElement> dealerName;
    private static By bDealerName = By.xpath("//td[@class='whitetxt']");

    //    protected static WebElement dealerCode;
    private static By bDealerCode = By.id("txtDealerCode");

    //    protected static WebElement userID;
    private static By buserID = By.id("txtSID");


    //    protected static WebElement banner;
    private static By bBanner = By.name("banner");

    //    protected static WebElement useToday;
    private static By bUseToday = By.id("rblDateToUse_0");

    //    protected static WebElement usePrevious;
    private static By busePrevious = By.id("rblDateToUse_1");

    //    protected static WebElement useEarlier;
    private static By buseEarlier = By.id("rblDateToUse_2");

    //    protected static List<WebElement> useEarlierErr;
    private static By bUseEarlierErr = By.xpath("//span[contains(text(),'Invalid security token error!')]");

    //    protected static WebElement customerSearchField;
    private static By bCustomerSearchField = By.id("existingCustomerSearch");

    private static WebElement spinner;                                                                                        // Loading spinner
    private static By bSpinnerLocator = By.cssSelector("section.zi-10 div figure.radial-loader.loader_spiner");

    private static WebElement loadingMsg;
    private static By loadingMsgLocator = By.cssSelector("section > div > div > div.loader_message");


//    private static By binavlidPartnerID= By.xpath("//span[contains(text(),'Invalid DFX Partner ID Error!')]");
//    private static By binvalidToken= By.xpath("//span[contains(text(),'Expired Security Token Error!')]");
//    private static By binvalidDealerID= By.xpath("//span[contains(text(),'Unknown DFX Dealer Id Error!')]");
//    private static By binvalidUserID= By.xpath("//span[contains(text(),'User Authentication or Authorization Error!')]");

//    private static By bDealerCodeErr = By.xpath("//span[contains(text(),'Unknown dealer code error!')]");
//    private static By buserIDErr = By.xpath("//span[contains(text(),'Unknown user ID!')]");


    private static By bErrorMessage(String message) {
        String locator = String.format("//span[contains(text(),'%s')]", message);
        return By.xpath(locator);
    }

//    private static By bMitErrorMessage(String message) {
//        String locator = String.format("//span[contains(text(),'%s')]", message);
//        return By.xpath(locator);
//    }


    public void setSSOData(String field, String value) {
        switch (field) {
            case "Partner ID":
                clearAndSend(bPartnerID, value);
                break;
            case "Shared Password":
                clearAndSend(bSharedPassord, value);
                break;
            case "DFX Dealer ID":
                clearAndSend(bDfxID, value);
                break;
            case "DFX System User ID":
                clearAndSend(bDfxSysUserID, value);
                break;
            case "Customer Name":
                clearAndSend(bCustomerName, value);
                break;
            case "Customer Phone":
                clearAndSend(bCustomerPhone, value);
                break;
            case "VIN":
                clearAndSend(bCustomerVIN, value);
                break;
        }

    }

//    public void setMitSSOData2(String field, String value) {
//        switch (field) {
//            case "Dealer Code":
//                clearAndSend(bPartnerID, value);
//                break;
//            case "User Id":
//                clearAndSend(bSharedPassord, value);
//                break;
//            case "Use today":
//                if (value.equalsIgnoreCase("yes")) {
//                    driver.findElement(bUseToday).click();
//                }
//                break;
//            case "Use previous day":
//                if (value.equalsIgnoreCase("yes")) {
//                    driver.findElement(busePrevious).click();
//                }
//                break;
//            case "Use earlier date":
//                if (value.equalsIgnoreCase("yes")) {
//                    driver.findElement(buseEarlier).click();
//                }
//                break;
//        }
//
//    }

    public void setMitSSOData(String field, String value) {
        switch (field) {
            case "Dealer Code":
                clearAndSend(bDealerCode, value);
                break;
            case "User Id":
                clearAndSend(buserID, value);
                break;
        }
        if (value.equalsIgnoreCase("yes")) {
            switch (field) {
                case "Use today":
                    driver.findElement(bUseToday).click();
                    break;
                case "Use previous day":
                    driver.findElement(busePrevious).click();
                    break;
                case "Use earlier date":
                    driver.findElement(buseEarlier).click();
                    break;
            }
        }

    }


    public void clickButton(String name) {
        switch (name) {
            case "TEST SSO":
                pWait.until(conditionVisible(bSubmitBtn)).click();
                break;
            case "TEST MMSCAN SSO":
                pWait.until(conditionVisible(bSubmitBtn)).click();
                break;
        }
    }

    public String getErrorMessage(String name) {
        return pWait.until(conditionVisible(bErrorMessage(name))).getText();
    }

//    public String getMitErrorMessage(String name) {
//        switch (name) {
//            case "TEST SSO":
//                pWait.until(conditionVisible(bSubmitBtn)).click();
//                break;
//            case "TEST MMSCAN SSO":
//                pWait.until(conditionVisible(bSubmitBtn)).click();
//                break;
//        }
//        return pWait.until(conditionVisible(bErrorMessage(name))).getText();
//    }


    public String getFieldvalue(String name) {
        String result ="";
        switch (name) {
            case "USERNAME":
                driver.switchTo().frame(driver.findElement(bBanner));
                result = pWait.until(conditionVisible(bDealerName)).getText();
                break;
        }
        return result;
    }


}
