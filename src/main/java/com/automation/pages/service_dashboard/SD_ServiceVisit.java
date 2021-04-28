package com.automation.pages.service_dashboard;

import com.automation.pages.common.WebPage;
import com.automation.pages.customer_connect.CC_MessengerPage;
import com.automation.utils.dataProvider.TestParameters;
import org.apache.commons.lang.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SD_ServiceVisit extends WebPage {

    CC_MessengerPage cmPage = new CC_MessengerPage();

    //locator

    //current vehicle status
    private static By bCurrentVehicleStatus = By.xpath("//*[@id='app-pages--vehicle-details']//div[contains(@class,'maintenance-progress__cell--current')]//header/div");

    //document under final document
    private static By bDocumentUnderFinalDocuments(String documentType){
        String checkLocator = "//div[@id='loader-final-documents']//div[contains(text(),'"+documentType+"')]";
        return By.xpath(checkLocator);
    }

    //customer concern comments for customer error message
    private static By bConcernCustomerCommentErrorMsg(String concernName){
        String locator = "//span[text()='"+concernName+"']/../../../following-sibling::div//div[@ng-message='required']";
        return By.xpath(locator);
    }

    //comments for customer with concernName
    private static By bCommentsFieldWithConcernName(String concernName){
        String locator = "//span[text()='"+concernName+"']/../../../following-sibling::div//textarea";
        return By.xpath(locator);
    }

    //send for approval button on service visit page
    private static By bSendForApproval = By.xpath("//button[contains(text(),'SEND FOR APPROVAL')]");

    //present to customer button on service visit page
    private static By bPresentToCustomer = By.xpath("//button[contains(text(),'PRESENT TO CUSTOMER')]");

    //SMS field on the customer contact information pop up
    private static By bSMSField = By.xpath("//input[@name='phone']");

    //SMS field toggle box on the customer contact information pop up
    private static By bSMSToggleInput = By.id("cbxBySMS");
    private static By bSMSToggleField = By.xpath("//input[contains(@id,'cbxBySMS')]/following-sibling::label");

    //Email field toggle box on the customer contact information pop up
    private static By bEmailToggleInput = By.id("cbxByEmail");
    private static By bEmailToggleField = By.xpath("//input[contains(@id,'cbxByEmail')]/following-sibling::label");

    //language drop down on the customer contact information pop up
    private static By bLanguageSelection = By.xpath("//select[contains(@ng-model,'customerCulture')]");

    //blue send button on the customer contact information pop up
    private static By bSENDOnCustomerContactInformation = By.xpath("//button[@ng-disabled='inprogress' and contains(text(),'SEND')]");

    //customer full name
    private static By bCustomerFullName = By.xpath("//div[@id='loader-vehicle-visit-customer']//span");

    //promise time section title
    private static By bPromiseTimeTitle = By.xpath("//div[contains(@class,'datepicker-title') and contains(text(),'Promise Time')]");

    //answered by date
    private static By bAnsweredByDate = By.xpath("//input[@ng-model='vm.customerResponseByTimeDay']");

    //answered by time hour
    private static By bAnsweredByHour = By.xpath("//select[@ng-model='vm.customerResponseByTimeHours']");

    //answered by time minute
    private static By bAnsweredByMinute = By.xpath("//select[@ng-model='vm.customerResponseByTimeMinutes']");

    //answered by time day part
    private static By bAnsweredByDayPart = By.xpath("//select[@ng-model='vm.customerResponseByTimeDayPart']");

    //deliver by date
    private static By bDeliverByDate = By.xpath("//input[@ng-model='vm.promiseTimeForDeliveryDay']");

    //deliver by time hour
    private static By bDeliverByHour = By.xpath("//select[@ng-model='vm.promiseTimeForDeliveryHours']");

    //deliver by time minute
    private static By bDeliverByMinute = By.xpath("//select[@ng-model='vm.promiseTimeForDeliveryMinutes']");

    //deliver by time day part
    private static By bDeliverByDayPart = By.xpath("//select[@ng-model='vm.promiseTimeForDeliveryDayPart']");

    //status log
    private static By bStatusLog = By.xpath("//span[contains(@class,'ng-binding') and contains(text(),'STATUS LOG')]");

    //logs under status log
    private static By bFullLog(String partialLog){
        String locator = "//div[@ng-bind-html='msg.message' and contains(text(),'"+partialLog+"')]";
        return By.xpath(locator);
    }

    //email logs under status log
    private static By bEmailLog = By.xpath("//div[@ng-bind-html='msg']/text()[2]");

    //recommendation section, use to determine if customer contact information dialog close or not
    private static By bDialogBlock = By.xpath("//body[@data-ng-app='mpi.bridge']");

    //parts description status
    private static By bPartsDescriptionStatus(String description){
        String locator = "//strong[text()='"+description+"']/../../../..//div[contains(@ng-click,'partsStatusPopover')]";
        return By.xpath(locator);
    }

    //QTY row number
    private static By bPartsQTY(String rowNumber){
        String locator = "//div[@ng-class='{ in: isOpen }']//div[contains(@ng-repeat,'partsStatusPopover.parts')]["+rowNumber+"]/div[contains(@class,'parts-col-1')]";
        return By.xpath(locator);
    }

    //Parts# row number
    private static By bPartsNo(String rowNumber){
        String locator = "//div[@ng-class='{ in: isOpen }']//div[contains(@ng-repeat,'partsStatusPopover.parts')]["+rowNumber+"]/div[contains(@class,'parts-col-2')]";
        return By.xpath(locator);
    }

    //Description row number
    private static By bPartsDescription(String rowNumber){
        String locator = "//div[@ng-class='{ in: isOpen }']//div[contains(@ng-repeat,'partsStatusPopover.parts')]["+rowNumber+"]/div[contains(@class,'parts-col-3')]";
        return By.xpath(locator);
    }

    //Status row number
    private static By bPartsStatus(String rowNumber){
        String locator = "//div[@ng-class='{ in: isOpen }']//div[contains(@ng-repeat,'partsStatusPopover.parts')]["+rowNumber+"]/div[contains(@class,'parts-col-4')]";
        return By.xpath(locator);
    }

    //inspetion tab on recommendation section
    private static By bInspectionTab = By.xpath("//li[@ng-click='vm.loadInspections()']//span[text()='INSPECTION']");

    //tech role on pop up after click notes
    private static By bTechRoleOnPopup = By.xpath("//div[@ng-if='notesPopover.showTechNotes']/span");

    //parts clerk role on pop up after click notes
    private static By bPcRoleOnPopup = By.xpath("//div[@ng-if='notesPopover.showPCNotes']/span");

    //tech role comment on pop up after click notes
    private static By bTechCommentOnPopup = By.xpath("//div[@ng-if='notesPopover.showTechNotes']/div");

    //parts clerk role comment on pop up after click notes
    private static By bPcCommentOnPopup = By.xpath("//div[@ng-if='notesPopover.showPCNotes']/div");

    //Approve or deffer dropdown under the description
    private static By bDropDownUnderDescription(String dropDown, String description){
        String locator = "//strong[text()='"+description+"']/../../../..//select[@ng-model='recom.declinedReasonId']/option[contains(text(),'"+dropDown+"')]";
        return By.xpath(locator);
    }

    //image attached to the description
    private static By bImageCheckbox(String description){
        String locator = "//strong[text()='"+description+"']/../../../..//input[contains(@ng-model,'recom.isAttachImage')]";
        return By.xpath(locator);
    }

    //notes icon with description
    private static By bNotesWithDescription(String description){
        String locator = "//strong[text()='"+description+"']/../../..//div[@class='notes-popover']";
        return By.xpath(locator);
    }

    //confirm button on DMPI bridge
    private static By bConfirmButton = By.xpath("//button[@ng-click='vm.submitForm(vm.CONSTANTS.BUTTONS.Confirm, recomForm)']");

    public void clickOnDocumentType(String documentType){
        clickElementWithException(bDocumentUnderFinalDocuments(documentType));
        if(documentType.equals("Recommendations")){
            driver.switchTo().frame("mpiRecFrame");
            scrollPageRight(1500);
            waitForElementWithException(bSendForApproval);
        }
    }

    public void clickOnSendForApproval(){
        clickElementWithException(bSendForApproval);
    }

    public void clickOnPresentToCustomer(){
        clickElementWithException(bPresentToCustomer);
    }

    public String getAnsweredByTime(){
        long startTime = System.currentTimeMillis();
        String date = "";
        while(!date.contains("20")){
            date = getElementAttributeWithException(bAnsweredByDate,"value").replace(",","");
            if((System.currentTimeMillis()-startTime)> 60000){
                break;
            }
        }
        String hour = getElementAttributeWithException(bAnsweredByHour,"value");
        String minute = getElementAttributeWithException(bAnsweredByMinute,"value");
        String dayPart = getElementAttributeWithException(bAnsweredByDayPart,"value");
        TestParameters.answeredByTimeOnServiceDashboard = ""+date+" "+hour+":"+minute+" "+dayPart+"";
        //answeredByTimeOnServiceDashboard = ""+hour+":"+minute+" "+dayPart+"";
        System.out.println("<====== The customer answered by time on service dashboard is "+ TestParameters.answeredByTimeOnServiceDashboard +" ======>");
        return TestParameters.answeredByTimeOnServiceDashboard;
    }

    public String getDeliverByTime(){
        String date = getElementAttributeWithException(bDeliverByDate,"value").replace(",","");
        String hour = getElementAttributeWithException(bDeliverByHour,"value");
        String minute = getElementAttributeWithException(bDeliverByMinute,"value");
        String dayPart = getElementAttributeWithException(bDeliverByDayPart,"value");
        TestParameters.deliverByTimeOnServiceDashboard = ""+date+" "+hour+":"+minute+" "+dayPart+"";
        System.out.println("<====== The promise deliver by time on service dashboard is "+ TestParameters.deliverByTimeOnServiceDashboard +" ======>");
        return TestParameters.deliverByTimeOnServiceDashboard;
    }

    public void toggleSMSONCustomerContactInformation(String toggleValue){
        dWait.until(conditionClick(bSMSToggleField));
        if(toggleValue.equals("ON")){
            if(jsGetCheckedStatus(bSMSToggleInput)){
                //do nothing, it's already toggled
            }else{
                dWait.until(conditionClick(bSMSToggleField)).click();
            }
        }else{
            if(jsGetCheckedStatus(bSMSToggleInput)){
                dWait.until(conditionClick(bSMSToggleField)).click();
            }else{
                //do nothing, it's toggled off already
            }
        }
    }

    public void toggleEmailONCustomerContactInformation(String toggleValue){
        dWait.until(conditionClick(bEmailToggleField));
        if(toggleValue.equals("ON")){
            if(jsGetCheckedStatus(bEmailToggleInput)){
                //do nothing, it's already toggled
            }else{
                dWait.until(conditionClick(bEmailToggleField)).click();
            }
        }else{
            if(jsGetCheckedStatus(bEmailToggleInput)){
                dWait.until(conditionClick(bEmailToggleField)).click();
            }else{
                //do nothing, it's toggled off already
            }
        }
    }

    public void selectLanguageOnPopUp(String selectValue){
        selectDropListElementWithException(bLanguageSelection,selectValue);
    }

    public void updateSMSOnCustomerContactInformation(String number){
        if(number.equals("Phone#")){
            number= TestParameters.phoneNumber;
        }
        //number = "12262120201"; //using for debug, will delete this hard code later
        clearAndSend(bSMSField,number);
    }

    public void clickOnSendOnCustomerContactInformation(){
        long startTime = System.currentTimeMillis();
        while(byElementHasClass(bDialogBlock,"ngdialog-open")){
            try{
                driver.findElement(bSENDOnCustomerContactInformation).click();
            }catch (WebDriverException e){
                //dialog already close
            }
            sleep(5000);//click apart every 5 secs
            if((System.currentTimeMillis()-startTime)> 50000){
                break;
            }
        }
    }

    public void openURLFromSMS(String urlWithinSMS){
        String sms = getElementTextWithException(CC_MessengerPage.bSMSOnReceiveSMS(urlWithinSMS));
        int indexStartWithUrl = sms.indexOf("https");
        int indexEndAfterUrl = sms.indexOf(" Type STOP");
        String startWithUrl = sms.substring(indexStartWithUrl);
        String endAfterUrl = sms.substring(indexEndAfterUrl);
        String url = startWithUrl.replace(endAfterUrl,"");
        System.out.println("<====== The DMPI url sent to customer from SMS is "+url+" ======>");
        driver.get(url);
        //openNewBrowserTabWithURL(url,4);
    }

    public String getCustomerFullName(){
        return TestParameters.customerFullName = getElementTextWithException(bCustomerFullName);
    }

    public void clickOnStatusLog(){
        try{
            driver.switchTo().frame("mpiRecFrame");
        }catch (WebDriverException e){
            //do nothing, user on the same service visit page, no need to switch frame
        }
        clickElementWithException(bStatusLog);
    }

    public String getStatusLog(String partialLog){
        String log;
        if(partialLog.contains("mail")){
            log = getElementTextWithException(bEmailLog);
        }else{
            log = getElementTextWithException(bFullLog(partialLog));
        }
        System.out.println("<====== I see log '"+log+"' on the page ======>");
        return log;
    }

    public void adjustPromiseTime(String timeName,String timeStatus){
        String currentAnsweredByHour = getElementAttributeWithException(bAnsweredByHour,"value");
        String currentDeliverByHour = getElementAttributeWithException(bDeliverByHour,"value");
        String currentAnsweredByDayPart = getElementAttributeWithException(bAnsweredByDayPart,"value");
        String currentDeliverByDayPart =  getElementAttributeWithException(bDeliverByDayPart,"value");
        String currentAnsweredByHourAdjusted;
        String currentDeliverByHourAdjusted;
        DateFormat inputFormatDeliveryHour = new SimpleDateFormat("MMM dd yyyy hh:mm a");
        DateFormat inputFormatDeliveryDateOnly = new SimpleDateFormat("MMM dd, yyyy");
        Date promiseDate = null;
        try {
            promiseDate = inputFormatDeliveryHour.parse(TestParameters.deliverByTimeOnServiceDashboard);
        } catch (ParseException e) {
            System.out.println("<====== parse promise date exception happened ======>");
        }
        if((timeName.equals("If answered by"))&&(timeStatus.equals("before"))){
            currentAnsweredByHourAdjusted = Integer.toString(Integer.parseInt(currentAnsweredByHour)-1);
            if((currentAnsweredByHour.equals("1"))&&(currentAnsweredByDayPart.equals("PM"))){
                currentAnsweredByHourAdjusted = currentAnsweredByHour;
                selectDropListElementWithException(bAnsweredByDayPart,"AM");
            }
            selectDropListElementWithException(bAnsweredByHour,currentAnsweredByHourAdjusted);
        }else if((timeName.equals("If answered by"))&&(timeStatus.equals("after"))){
            currentAnsweredByHourAdjusted = Integer.toString(Integer.parseInt(currentAnsweredByHour)+1);
            if((currentAnsweredByHour.equals("1"))&&(currentAnsweredByDayPart.equals("AM"))){
                currentAnsweredByHourAdjusted = currentAnsweredByHour;
                selectDropListElementWithException(bAnsweredByDayPart,"PM");
            }
            selectDropListElementWithException(bAnsweredByHour,currentAnsweredByHourAdjusted);
        }else if((timeName.equals("Delivery by"))&&(timeStatus.equals("before"))){
            currentDeliverByHourAdjusted = Integer.toString(Integer.parseInt(currentDeliverByHour)-1);
            if((currentDeliverByHour.equals("1"))&&(currentDeliverByDayPart.equals("PM"))){
                currentDeliverByHourAdjusted = currentDeliverByHour;
                selectDropListElementWithException(bDeliverByDayPart,"AM");
            }else if(!DateUtils.isSameDay(promiseDate, new Date())){
                clearAndInputElementWithException(bDeliverByDate,inputFormatDeliveryDateOnly.format(new Date()));
            }
            selectDropListElementWithException(bDeliverByHour,currentDeliverByHourAdjusted);
        }else if((timeName.equals("Delivery by"))&&(timeStatus.equals("after"))){
            clearAndInputElementWithException(bDeliverByDate,inputFormatDeliveryDateOnly.format(promiseDate));
            currentDeliverByHourAdjusted = Integer.toString(Integer.parseInt(currentDeliverByHour)+1);
            if((currentDeliverByHour.equals("1"))&&(currentDeliverByDayPart.equals("AM"))){
                currentDeliverByHourAdjusted = currentDeliverByHour;
                selectDropListElementWithException(bDeliverByDayPart,"AM");
            }
            selectDropListElementWithException(bDeliverByHour,currentDeliverByHourAdjusted);
        }
    }

    public void adjustTwoTime(String timeName1,String timeName2,String timeStatus){
        if(timeStatus.equals("after")){
            clearAndInputElementWithException(bAnsweredByDate,"Oct 06, 2100");
            clickElementWithException(bPromiseTimeTitle);
        }else if(timeStatus.equals("before")){
            clearAndInputElementWithException(bDeliverByDate, "Oct 06, 2200");
            clickElementWithException(bPromiseTimeTitle);
        }else{
            System.out.println("<====== incorrect time status for promise time section ======>");
        }

    }

    public boolean verifyErrorMessageOnServiceVisit(String errorMessage,String messageStatus){
        System.out.println("//div[contains(text(),'"+errorMessage+"')]");
        if(messageStatus.equals("show")){
            if(verifyIfElementVisibleOnPage(By.xpath("//div[contains(text(),'"+errorMessage+"')]"))){
                return true;
            }else{
                System.out.println("<====== Error message '"+errorMessage+"' not showing ======>");
                return false;
            }
        }else if(messageStatus.equals("disappeared")){
            if(!verifyIfElementVisibleOnPage(By.xpath("//div[contains(text(),'"+errorMessage+"')]"))){
                return true;
            }else{
                System.out.println("<====== Error message '"+errorMessage+"' should not show but showing ======>");
                return false;
            }
        }else{
            System.out.println("<====== the status is undefined ======>");
            return false;
        }
    }

    public String getConcernFieldErrorMsg(String concernName, String fieldName){
        String errorMsgConcern = "";
        switch(fieldName){
            case "COMMENTS FOR CUSTOMER":
                errorMsgConcern = getElementTextWithException(bConcernCustomerCommentErrorMsg(concernName));
                break;
        }
        return errorMsgConcern;
    }

    public void inputCommentsOnConcernOnField(String comments, String concernName, String fieldName){
        switch (fieldName){
            case "COMMENTS FOR CUSTOMER":
                clearAndInputElementWithException(bCommentsFieldWithConcernName(concernName),comments);
                break;
        }
    }

    public String getPartsDescriptionStatus(String description){
        return getElementTextWithException(bPartsDescriptionStatus(description));
    }

    public void clickOnPartsStatus(String description){
        clickElementWithException(bPartsDescriptionStatus(description));
    }

    public String getQTY(String expectedValue,String rowNumber){
        long startTime = System.currentTimeMillis();
        String qty = "";
        while(!qty.equals(expectedValue)){
            qty = getElementTextWithException(bPartsQTY(rowNumber));
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 20000){
                break;
            }
        }
        return qty;
    }

    public String getPartNo(String expectedValue,String rowNumber){
        long startTime = System.currentTimeMillis();
        String partsNo = "";
        while(!partsNo.equals(expectedValue)){
            partsNo = getElementTextWithException(bPartsNo(rowNumber));
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 20000){
                break;
            }
        }
        return partsNo;
    }

    public String getDescription(String expectedValue, String rowNumber){
        long startTime = System.currentTimeMillis();
        String description = "";
        while(!description.equals(expectedValue)){
            description = getElementTextWithException(bPartsDescription(rowNumber));
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 20000){
                break;
            }
        }
        return description;
    }

    public String getStatus(String rowNumber){
        return getElementTextWithException(bPartsStatus(rowNumber));
    }

    public void clickOnInspectionTab(){
        clickElementWithException(bInspectionTab);
    }

    public void clickNotesWithDescription(String description){
        clickElementWithException(bNotesWithDescription(description));
    }

    public String getTechRole(){
        String role = "";
        role = getElementTextWithException(bTechRoleOnPopup);
        return role;
    }

    public String getPCRole(){
        String role = "";
        role = getElementTextWithException(bPcRoleOnPopup);
        return role;
    }

    public String getTechComment(){
        String comment = "";
        comment = getElementTextWithException(bTechCommentOnPopup);
        return comment;
    }
    public String getPCComment(){
        String comment = "";
        comment = getElementTextWithException(bPcCommentOnPopup);
        return comment;
    }

    public void clickONDropDownUnderDescription(String dropDownName, String description){
        clickElementWithException(bDropDownUnderDescription(dropDownName,description));
    }

    public boolean isOptionSelectedONDropDownUnderDescription(String dropDownName, String description){
        if(getElementAttributeWithException(bDropDownUnderDescription(dropDownName,description),"selected").equals("true")){
            System.out.println("<====== "+dropDownName+" selected under "+description+" ======>");
            return true;
        }else {
            return false;
        }
    }

    public boolean isImageChecked(String description){
        getCurrentFrame();
        if(getElementAttributeWithException(bImageCheckbox(description),"class").contains("ng-not-empty")){
            System.out.println("<====== the image is checked ======>");
            return true;
        }else {
            System.out.println("<====== the image is unchecked ======>");
            return false;
        }
    }

    public String getDropDownValue(String description, String dropDownName){
        String dropDown = getElementTextWithException(bDropDownUnderDescription(dropDownName,description));
        return dropDown;
    }

    public boolean isConfirmButtonEnabled(String status){
        if(status.equals("enabled")){
            waitForElementHasAttributeValue(bConfirmButton,"class","full-opacity");
        }else{
            waitForElementHasAttributeValue(bConfirmButton,"class","half-opacity");
        }
        return !getElementAttributeWithException(bConfirmButton, "class").contains("half-opacity");
    }

    public void clickConfirmButton(){
        clickElementWithException(bConfirmButton);
    }

    public String verifyServiceLaneStatusServiceVisit(String vin, String status){
        long startTime = System.currentTimeMillis();
        driver.switchTo().defaultContent();
        String actualStatus = "";
        while(!status.equals(actualStatus)){
            actualStatus = getElementTextWithException(bCurrentVehicleStatus);
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 20000){
                break;
            }
        }
        return actualStatus;
    }

}
