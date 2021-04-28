package com.automation.pages.wiadvisor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class WiAdvisorCustomerInfoTab extends WiAdvisorROPage {

    //choose customer from DMS or VIP
    private static By bCustomerSection(String sectionName){
        String locator = "//div[@class='modal-content']//h4[text()='"+sectionName+"']";
        return By.xpath(locator);
    }

    //yes on the pop up
    private static By bYesPopup = By.xpath("//button[contains(text(),'Yes')]");
    //warings vip ok pop up
    private static By bVIPOk = By.xpath("//button[contains(text(),'OK')]");
    //customer info body
    private static By bCustomerInfoBody = By.xpath("//body");
    //Customer communications pop up
    private static By bVehicleInfoLoadingLocator = By.cssSelector("div.progress-bar");
    //close icon on the odometer error message pop up
    private static By bCloseIconOdometerPopUp = By.xpath("//button[@ng-click='closableModalHeader.closeModal()']");
    //edit button under vehicle information section
    private static By bEditUnderVehicleInfo = By.xpath("//button[@ng-show='!customerInfo.editOn']");
    //odometer field under vehicle information
    private static By bOdometerField = By.xpath("//input[@name='odometer']");
    //save under vehicle information
    private static By bSaveUnderVehicleInfo = By.xpath("//button[@ng-show='customerInfo.editOn']");
    //save under vehicle information
    private static By bSaveBtnVehicleInfo = By.xpath("(//button[text()='Edit'])[2]");
    //edit button under vehicle information section
    private static By bEditUnderCustomerInfo = By.xpath("(//button[@ng-show='!customerInfo.editOn'])[1]");
    //odometer field under vehicle information
    private static By bHomeNumberField = By.xpath("//input[@name='customerHomeNumber']");
    //save under vehicle information
    private static By bSaveUnderCustomerInfo = By.xpath("//a[text()='Customer Information']/..//button[@ng-show='customerInfo.editOn' and text()='Save']");
    //save under vehicle information
    private static By bSaveBtnCustomerInfo = By.xpath("(//button[text()='Save'])[1]");
    //continue button under customer info tab
    private static By bContinueCustomerInfo = By.xpath("//button[@ng-click='repairOrder.continue()']");

    private static By bField(String sBy) {
        String sBy1 = sBy.toLowerCase().trim();
        String sBy2 = "odometer";
        switch(sBy1){
            case "odometer": sBy2 = "odometer";
                break;
            case "last name": sBy2 = "customerLastName";
                break;
            case "first name": sBy2 = "customerFirstName";
                break;
            case "street": sBy2 = "customerStreet";
                break;
        }
        String locator = "//input[@name='"+sBy2+"']";
        return By.xpath(locator) ;
    }


    public void selectCustomerFromSection(String sectionName){
        waitForElementWithException(bCustomerSection(sectionName));
        waitForElementHasAttributeValue(bCustomerInfoBody,"class","modal-open");
        pWait.until(ExpectedConditions.invisibilityOfElementLocated(bVehicleInfoLoadingLocator));
        clickElementWithSeconds(bCustomerSection(sectionName),1500);
        System.out.println("<====== Customer from '"+sectionName+"' selected  ======>");
    }

    public void handlePagePopUp(){
        long startTime = System.currentTimeMillis();
        try{
            dWait.until(ExpectedConditions.invisibilityOfElementLocated(bVehicleInfoLoadingLocator));
            System.out.println("<====== vehicle information progress bar now loading (｡・`ω´･) ======>");
        }catch (WebDriverException e){
            pageRefresh();
        }
        while(byElementHasClass(bCustomerInfoBody,"modal-open")){
            //in case the customer communications pop up
            try{
                driver.findElement(bYesPopup).click();
                System.out.println("<====== clicked the customer communication pop up <(￣︶￣)> ======>");
            }catch (WebDriverException eex){
                //the pop up is not there, do nothing
            }
            //in case the customer communications pop up
            try{
                driver.findElement(bVIPOk).click();
                System.out.println("<====== clicked the VIP OK pop up <(￣︶￣)> ======>");
            }catch (WebDriverException eex){
                //the pop up is not there, do nothing
            }
            //in case the "Please correct the following errors" pop up windows show up, have to close it
            try{
                driver.findElement(bCloseIconOdometerPopUp).click();
                System.out.println("<====== clicked Odometer invalid pop up <(￣︶￣)> ======>");
            }catch(WebDriverException ex){
                // the pop up is not there, do nothing
            }
            if((System.currentTimeMillis()-startTime)> 60000){
                Assert.fail("<====== Pop up page never finish loading (→_→) ======>");
                break;
            }
        }
    }

    public void updateOdometerUnderVehicleInfo(String oDometerNo){
        handlePagePopUp();
        clickElementWithException(bEditUnderVehicleInfo);
        clearAndInputElementWithException(bOdometerField,oDometerNo);
        clickElementWithException(bSaveUnderVehicleInfo);
    }

    public void updateFieldInCustomerInfor(String field, String value){
        handlePagePopUp();
        clickElementWithException(bEditUnderVehicleInfo);
        clearAndInputElementWithException(bField(field),value);
        clickElementWithException(bSaveUnderVehicleInfo);
    }


    public void updateNumberUnderCustomerInfo(String homeNumber){
        handlePagePopUp();
        clickElementWithException(bEditUnderCustomerInfo);
        clearAndInputElementWithException(bHomeNumberField,homeNumber);
        clickElementWithException(bSaveUnderCustomerInfo);
    }

    public void clickOnContinueCustomerInfo(){
        clickElementWithException(bContinueCustomerInfo);
    }

    public void clickOnContinueVIPHistory(){
        //David: add code to wait for VIP history page loaded
        clickElementWithException(bContinueCustomerInfo);
        System.out.println("<====== continue butotn on the VIP/History clicked ======>");
    }
}
