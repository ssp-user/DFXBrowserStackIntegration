package com.automation.pages.customer_connect;

import com.automation.pages.PageManager;
import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CC_TasksPage extends WebPage {

    public static int[] indexOfCheckbox;

    //CCDashboardPage cPage = new CCDashboardPage();

    public By bColumnAfterSave(String name, String vehicle, String value) {
        sleep(3000);
        String noAnswerLocator = "//td[contains(text(),'" + name + "')]/following-sibling::td[contains(text(),'" + vehicle + "')]/following-sibling::td[contains(text(),'" + value + "')]";
        String lastAttemptLocator = noAnswerLocator + "/preceding-sibling::td[1]";
        String lastAttempt = dWait.until(conditionVisible(By.xpath(lastAttemptLocator))).getAttribute("innerHTML");
        String callAttemptsLocator = "//td[contains(text(),'" + name + "')]/following-sibling::td[contains(text(),'" + vehicle + "')]/following-sibling::td[contains(text(),'" + value + "')]/preceding-sibling::td[contains(text(), '" + lastAttempt +"')]" + "/preceding-sibling::td[1]";
        tp.callAttempts = dWait.until(conditionVisible(By.xpath(callAttemptsLocator))).getAttribute("innerHTML");
        System.out.println("Call Attempts locator is: " + callAttemptsLocator);
        System.out.println("Call Attempts is : " + tp.callAttempts);
        return By.xpath(noAnswerLocator);
    }

    //Assigned Agent column
    private static By bAssignedAgentColumn(String index){
        String locator = "//tr[" + index +"][@ng-repeat='task in tasks']/td[9]";
        return By.xpath(locator);
    }

    //Cell Phone Field
    private static By bCellPhoneField = By.xpath("//input[@ng-model='selectedTaskTemp.CustCellPhone']");
        
    //Email field
    private static By bEmailField = By.xpath("//input[@ng-model='selectedTaskTemp.CustEmail']");
            
    //Sender email field
    private static By bComposeSenderEmailField = By.xpath("//input[@ng-model='emailSenderEmailAddress']");
    
    //Email editor field
    private static By bEmailContentsField = By.xpath("//div[@class='Editor-editor blueScrollBar']"); 
    
    //SMS editor field
    private static By bSMSContentsField = By.xpath("//div/textarea[@id='TextEditor']");         
    
    //Email checkbox
    private static By bEmailChkBox = By.xpath("//label[@for='enableDisableAuthMethods2']//parent::div/input");
    
    //Text checkbox
    private static By bTextChkBox = By.xpath("//label[@for='enableDisableAuthMethods0']//parent::div/input");    
    
    //Export button
    private static By bExport = By.xpath("//button[@ng-click='openExportCampaignPopup()']");

    //Select all to export
    private static By bSelectAllExport = By.xpath("//button[@ng-click='selectAll()']");

    //export button on export page
    private static By bExportOnExportPage = By.xpath("//div[@onclick='exportCampaignDataJS()']");

    //dashboard Progress chart
    private static By loadingOnCustomerConnect = By.id("loadingSpinner");

    //save on the task detail page
    private static By bSaveButton = By.id("saveButton");

    //cancel on the task detail page
    private static By bCancelButton = By.id("cancelButton");

    //add notes
    private static By bAddNotes = By.xpath("//textarea[@placeholder='add notes']");

    //note or add note button
    private static By bNoteButton = By.xpath("//div[@ng-click='openCustomerNote()' and not(contains(@class,'ng-hide'))]");

    //note input field
    private static By bStickyNote = By.xpath("//div[contains(@id,'PIApostit') and contains(@style,'display: block;')]//div[contains(@id,'pia_editable')]");

    //close icon on the sticky notes
    private static By bCloseOnNote = By.xpath("//div[contains(@id,'PIApostit') and contains(@style,'display: block;')]//*[@ng-click='saveNote()']");

    //The appointment has been changed
    private static By bNoOnAppointmentChanged = By.xpath("//button[@class='alertMsgBoxButton' and contains(text(),'No')]");

    //Schedule Appointment on Task detail page
    private static By bScheduleAppointment = By.id("scheduleButton");
    
    //OK button on popup
    private static By bOKBtnOnPopupBy = By.xpath("//button[@class='alertMsgBoxMainButton'][@ng-click='callbackAccept()']");

    //user name on customer detail on task detail
    private static By bCustomerNameOnTaskDetail = By.xpath("//span[@class='panel-subText taskDetailCustomerName text-uppercase ng-binding']");

    //vehicle name on vehicle vitals on task detail
    private static By bVehicleNameOnTaskDetail = By.xpath("//label[@class='vehicleDetailDivName ng-binding']");

    //call logs number on task detail page
//    private static By bCallLogsNumber = By.xpath("//div[contains(text(),'Call Log')]/div[@ng-show='tab.tabInfoCount']");

    private static By bCallAttemptsNumber = By.xpath("//table[@class='table table-hover taskTable taskTableWidth']/tbody/tr[1]/td[5]");

//    private static By bCallLogsNumber = By.xpath("//table[@class='table table-hover fixedTable historyTable table-hover-default-cursor']/tbody/tr[1]/td[3]");
    private static By bCallLogsNumber = By.xpath("(//div[@class='circleBase tabInfoCountIcon ng-binding'])[1]");    
    
    //vin on task detail
    private static By bVinOnTaskDetail = By.xpath("//label[@class='vehicleDetailDivVin ng-binding']");

    //dialog on taskDetail
    private static By dialog = By.xpath("//span[@class='ui-dialog-title']");
    
    //Save button Customerdetails
    private static By bCustDetailsSaveBtn = By.xpath("//div[@class='commandButton saveButton taskDetailsbtnSmaller']");    

    //No on dialog
    private static By noButton = By.xpath("//button[contains(text(),'No')]");

    //Yes on dialog
    private static By yesButton = By.xpath("//button[contains(text(),'Yes')]");
    
    //CommunicationType Header on Customerdetails
    private static By bCommHeader = By.xpath("//div[@class='dropdown-toggle ng-binding']"); 
    
    //Email option on Customerdetails
    private static By bCommEmail = By.xpath("//span[text()='dfxcucumbertest@yahoo.com']");
    
    //SMS option on Customerdetails
    private static By bCommSMS = By.xpath("//span[text()='(289) 536-3513']");    
    
    //chat submit button on Customerdetails
    private static By bChatSubmit = By.xpath("//div[@class='chat-submit-button']");         

    //Reassign Tasks button
    private static By bReassignTasksBtn = By.xpath("//button[@ng-click='toggleReassign()']");

    //Service Coordinators search field
    private static By bBDCAgentSearchField = By.xpath("//input[@ng-focus='agentSearchFocus = true']");

    //Global agent search field
    private static By bGlobalAgentSearchField = By.xpath("//input[@ng-focus='bulkAgentSearchFocus = true']");

    //Left arrow of BDC Agent search result
    private static By bLeftArrowBtnBDC = By.xpath("//button[@class='configSvgButton configSvgCopy']");
    
    //Text unauthorized message
    private static By bTextUnauthorized = By.xpath("//div[text()='Text is not authorized. Please select an alternate communication method.']");    
    
    private static By bSearchTextBox = By.xpath("//input[@id='inputSearch' and @ng-model='searchString']");    
    
    //Email unauthorized message
    private static By bEmailUnauthorized = By.xpath("//div[text()='Email is not authorized. Please select an alternate communication method.']");      

    //Right green arrow button of global agent search result
    private static By bRightGreenArrowBtnWithAgentName(String agentName) {
        String locator = "//div[contains(text(), '" + agentName + "')]/parent::div/parent::div/following-sibling::button";
        return By.xpath(locator);
    }

    //Left red arrow button of global agent search result
    private static By bLeftRedArrowBtnWithAgentName(String agentName) {
        String locator = "//div[contains(text(), '" + agentName + "')]/parent::div/parent::div/preceding-sibling::button";
        return By.xpath(locator);
    }

    //Campaign name and number header
    private static By bCampaignNameAndNumberHeader = By.xpath("//div[@class='pageHeaderText leftFlex ng-binding']");

    //OK button on popup
    private static By bCustDetailsOKBtnOnPopupBy = By.xpath("//button[@class='alertMsgBoxMainButton'][@ng-click='closeUpdateResultPopup()']");
    
    //Save button
    private static By bSaveBtn = By.xpath("//div[@class='commandButton saveButton']");

    //Apply button
    private static By bApplyBtn = By.xpath("//div[@ng-click='trySelectiveReassignment()']");
    
    //Send EMAIL button on Compose message
    private static By bComposeSend = By.xpath("//button[@ng-click='sendEmail()']");
    
    //Send SMSL button on Compose message
    private static By bComposeSMSSend = By.xpath("//button[@ng-click='sendText()']");      
    
    //Message pop up
    private static By bCloseMessagePopUp = By.xpath("//*[contains(text(),'Message')]/button[@class='alertMsgClose close']/span[text()='×']");     
    
    //Email Subject on Cumpose message
    private static By bComposeEmailSubject = By.xpath("//input[@ng-model='emailSubject']");      

    //Task list checkbox
    private static By bTaskListCheckbox(int index){
        String checkLocator = "//tr[@class='tableRowBorders ng-scope'][" + String.valueOf(index) + "]/td/input";
        return By.xpath(checkLocator);
    }
    
    //Authorize OK button on popup
    private static By bCustDetailsAuthorizeOKBtnOnPopupBy = By.xpath("//button[@class='alertMsgBoxMainButton'][@ng-click='callbackAccept()']");
        

    //Data Loading message
    private static By bDataLoadingMsg = By.xpath("//div[@class='loader_message']");

    //Assigned Agent name
    private static By bAssignedAgentName(int index) {
        String checkLocator = "//tr[@class='tableRowBorders ng-scope'][" + String.valueOf(index) +"]/td[9]";
        return By.xpath(checkLocator);
    }

    //Task list checkboxes set
    private static List<WebElement> getTaskListCheckboxes() {
        return driver.findElements(By.xpath("//input[@ng-model='task.selected']"));
    }

    //drop down value
    private static By bDropDownValue(String value){
        String checkLocator = "//option[contains(text(),'"+value+"')]";
        return By.xpath(checkLocator);
    }

    //drop down value for campaign
    private static By bDropDownValueCampaign(String value){
        String checkLocator = "//label[contains(text(),'"+value+"')]";
        return By.xpath(checkLocator);
    }

    //Dropdown value's checkbox
    private static By bDropdownValueCheckbox(String value){
        String checkLocator = "//span[contains(text(),'"+value+"')]/parent::span/input";
        return By.xpath(checkLocator);
    }

    //Drop down value for Campaign Type dropdown
    private static By bDropdownValueCampaignType(String value) {
        String locator = "//span[@class='checkbox__text ng-binding' and contains(text(), '" + value + "')]";
        return By.xpath(locator);
    }

    //Drop down value for Assigned Agent dropdown
    private static By bDropdownValueAssignedAgent(String value) {
        String locator = "//span[@class='checkbox__text ng-binding' and contains(text(), '" + value + "')]";
        return By.xpath(locator);
    }

    //Customer Details Label
    private static By bCustomerDetail = By.xpath("//label[contains(text(),'Customer Details')]");

    //actions drop down on task detail
    private static By bSelectAction = By.id("callLogResult");

    //action drop down on task detail with empty selection
    private static By bSelectActionNothing = By.xpath("//select[@id='callLogResult']/option[@value='?']");

    //campaign drop down
    private static By bCampaignDropDown = By.xpath("//div[@class='customSelectText ng-binding']");

    //Campaign type dropdown
    private static By bCampaignTypeDropdown = By.xpath("//div[@ng-click='toggleCampaignTypeFilter()']");

    //Assigned Agent dropdown
    private static By bAssignedAgentDropdown = By.id("filter-assigned-agent");

    //Left Arrow: Include All Campaigns
    private static By bIncludeAllCampaignsArrow = By.xpath("//span[contains(text(), 'Include All Campaigns')]/preceding-sibling::button");

    //Reassign Agents button
    private static By bReassignAgentsBtn = By.xpath("//button[@ng-click='toggleMultiReassignmentOptions()']");

    //Next button
    private static By bNextBtn = By.xpath("//div[@ng-click='configTabChange(1)']");

    //campaign text
    private static By bCampaignText = By.xpath("//div[@class='panel-campaignText ng-binding']");

    //Deferred recommendations under History section
    private static By bDeferredRecommendations = By.xpath("//div[contains(text(),'Deferred Recommendations') and contains(@ng-repeat,'configTabs')]");

    //Deferred recommendations number under History section
    private static By bDeferredRecommendationsNumber = By.xpath("//div[contains(text(),'Deferred Recommendations') and contains(@ng-repeat,'configTabs')]/div");

    //use to verify if record existed under deferred recommendations
    private static By bDetailDeferredRecommendation = By.xpath("//tr[@ng-repeat='details in deferredRecommendations']");

    //modify icon on the customer details section
    private static By bModifyOnCustomerDetails = By.xpath("//div[@class='taskDetailsEditIcon']");

    //no button on task detail page
    private static By bNoButton = By.xpath("//button[text()='No']");

    //first name field on the customer details pop up
    private static By bFirstNameCustomerDetails = By.xpath("//div[contains(@style,'display: block;')]//div[contains(text(),'First Name:')]/following-sibling::input");

    //last name field on the customer details pop up
    private static By bLastNameCustomerDetails = By.xpath("//div[contains(@style,'display: block;')]//div[contains(text(),'Last Name:')]/following-sibling::input");

    //cell phone field on the customer details pop up
    private static By bCellPhoneCustomerDetails = By.xpath("//div[contains(@style,'display: block;')]//div[contains(text(),'Cell Phone:')]/following-sibling::input");

    //address field on the customer details pop up
    private static By bAddressCustomerDetails = By.xpath("//div[contains(@style,'display: block;')]//div[contains(text(),'Address:')]/following-sibling::input");

    //city field on the customer details pop up
    private static By bCityCustomerDetails = By.xpath("//div[contains(@style,'display: block;')]//div[contains(text(),'City:')]/following-sibling::input");

    //country field on the customer details pop up
    private static By bCountryCustomerDetails = By.xpath("//div[contains(@style,'display: block;')]//div[contains(text(),'Country')]/following-sibling::select");

    //zip field on the customer details pop up
    private static By bZipCustomerDetails = By.xpath("//div[contains(@style,'display: block;')]//div[contains(text(),'Zip:')]/following-sibling::input");

    //state field on the customer details pop up
    private static By bStateCustomerDetails = By.xpath("//div[contains(@style,'display: block;')]//div[contains(text(),'State/Province:')]/following-sibling::select");

    //save button on the customer details pop up
    private static By bSaveOnCustomerDetailPopup = By.xpath("//div[contains(@style,'display: block;')]//div[contains(@class,'saveButton taskDetailsbtnSmaller')]");

    //OK button on Message pop up
    private static By bOKOnMessagePopup = By.xpath("//button[@ng-click='closeUpdateResultPopup()']");

    //1st line address on customer details
    private static By bHomeIconCustomerDetails1stLine = By.xpath("//span[@ng-show='addressExists()']");

    //2nd line address on customer details
    private static By bHomeIconCustomerDetails2ndLine = By.xpath("//span[@ng-show='cityStateZipExists()']");

    //cell phone number on customer details
    private static By bCellIconCustomerDetails = By.xpath("//*[@class='icon icon--phone-cell taskDetailCustomerDetailIconAdjustOne']/../following-sibling::span");

    //Tasks List table header
    private static By bTasksListHeader = By.xpath("//span[@class='tableHeaderLink']");

    //Task rows
    private static By bTasksList = By.xpath("//tr[@ng-repeat='task in tasks']");

    //Assigned Agent column
    private static By bAgentName(String agentName){
        String locator = "//td[@class='ng-binding' and contains(text(),'"+agentName+"')]";
        return By.xpath(locator);
    }

    //customer name associated with agent
    private static By bCustomerNameAssociatedWithAgentName(String agentName){
        String locator = "//td[contains(text(),'"+agentName+"')]/../td[1]";
        return By.xpath(locator);
    }

    //customer name on the first row
    private static By bCustomerNameFirstRow = By.xpath("//tr[@ng-click='openTaskDetails(task)']/td");

    //customer name
    private static By bCustomerName(String customerName){
        String locator = "//tr[@ng-click='openTaskDetails(task)']/td[contains(text(),'"+customerName+"')]";
        return By.xpath(locator);
    }

    //drop down with name
    private static By bDropDownWithName(String dropDownName){
        String locator = "//div[@class='dropdown-toggle' and contains(.,'"+dropDownName+"')]";
        return By.xpath(locator);
    }

    //checkbox inside the drop down
    private static By bCheckBox(String checkBoxName){
        String locator = "//span[contains(@class,'checkbox') and text()='"+checkBoxName+"']";
        return By.xpath(locator);
    }

    //customer with order
    private static By bCustomerWithOrder(String order){
        String locator = "(//tr[@ng-click='openTaskDetails(task)']/td)["+order+"]";
        return By.xpath(locator);
    }

    //clear all button
    private static By bClearAll = By.xpath("//button[@ng-click='clearFilters()' and text()='Clear All']");

    //static String
    public String vinOnTaskDetail;
    private String[] customerNameWithAgentAssigned1;
    private String[] customerNameWithAgentAssigned2;

    //VIN under Vehicle Vitals on Task Details page
    private static By bVIN = By.xpath("//div[@class='vehicleDetailDiv leftFlex rightFlex']/label[@class='vehicleDetailDivVin ng-binding']");

    //SERVICE HISTORY tab on Task Details page
    private static By bServiceHistoryTab = By.xpath("//div[contains(text(), 'Service History')]");

    //Information icons under SERVICE HISTORY section on Task Details page
    private static By bInfoIcons = By.xpath("//div[@class='detail-maxWidth taskDetailRecallInfoIcon']");

    public void waitForLoadingCircleToDisappearCC(){
        waitForElementHasAttributeValue(loadingOnCustomerConnect,"style","display: none;");
    }

    public void clickOnSaveOnTaskDetail(){
        sleep(5000);    	
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bSaveButton).click();
                sleep(10000);
                noException = true;
            }catch (WebDriverException e){
                try{
                    driver.findElement(bNoButton).click();
                }catch (WebDriverException ex){
                    //the Appointment close pop up is not there
                }
            }
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
        waitForLoadingCircleToDisappearCC();
    }

    public void clickCancelOnTaskDetail(){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bCancelButton).click();
                sleep(10000);
                noException = true;
            }catch (WebDriverException e){
                try{
                    driver.findElement(bNoButton).click();
                }catch (WebDriverException ex){
                    //the Appointment close pop up is not there
                }
            }
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
        waitForLoadingCircleToDisappearCC();

    }

    public boolean verifyIfUserOnTaskDetail(){
        try{
            dWait.until(conditionClick(bSaveButton));
            System.out.println("<====== user on Task Detail page ======>");
            return true;
        }catch(WebDriverException e){
            System.out.println("<====== Save button not found on the task detail page ======>");
            //pageRefresh();
            return false;
        }
    }

    public void closeAppointmentChangePopUp(){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bAddNotes).click();
                noException = true;
            }catch (WebDriverException e){
                try{
                    driver.findElement(bNoButton).click();
                }catch (WebDriverException ex){
                    //the Appointment close pop up is not there
                }
            }
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
    }

    public void clickOnNoteButton(){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bNoteButton).click();
                noException = true;
            }catch (WebDriverException e){
                try{
                    driver.findElement(bNoButton).click();
                }catch (WebDriverException ex){
                    //the Appointment close pop up is not there
                }
            }
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
    }

    //input text on sticky notes
    public void inputTextOnNotes(String text){
        if(text.equals("Test random")){
            CommonMethods cm = new CommonMethods();
            text = "Test"+" "+cm.getRandomText(5);
        }
        tp.customerNoteTxt = text;
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bStickyNote).clear();
                driver.findElement(bStickyNote).sendKeys(text);
                noException = true;
            }catch (WebDriverException e){
                clickNoOnAppointmentClosePopUp();
            }
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
        System.out.println("<====== The text on the sticky notes on Tasks page is "+text+" ======>");
    }

    //click no on the appointment close pop up
    public void clickNoOnAppointmentClosePopUp(){
        try{
            driver.findElement(bNoButton).click();
            System.out.println("<====== No button on the appointment has been changed pop up clicked ======>");
        }catch (WebDriverException ex){
            //the Appointment close pop up is not there
        }
    }

    //close icon on the sticky note
    public void clickOnCloseOnNotes(){
        clickElementWithException(bCloseOnNote);
    }

    //sticky note text
    public String getTextOnNote(){
//        String notesText = "";
//        long startTime = System.currentTimeMillis();
//        boolean noException = false;
//        while(!noException || !notesText.equals(tp.customerNoteTxt)){
//            try{
//                notesText = driver.findElement(bStickyNote).getText();
//                noException = true;
//            }catch (WebDriverException e){
//                try{
//                    driver.findElement(bNoButton).click();
//                }catch (WebDriverException ex){
//                    //the Appointment close pop up is not there
//                }
//            }
//            if((System.currentTimeMillis()-startTime)> 30000){
//                break;
//            }
//        }
        return driver.findElement(bStickyNote).getText();
    }

    public String getUserName(){
        sleep(5000);
        tp.userName = getElementTextWithException(bCustomerNameOnTaskDetail);
        System.out.println("<====== the current user name on task detail is "+tp.userName+" ======>");
        return tp.userName;
    }

    public String getVehicleName(){
        tp.vehicleName = driver.findElement(bVehicleNameOnTaskDetail).getAttribute("innerHTML");
        System.out.println("<====== the current vehicle name on task detail is "+tp.vehicleName+" ======>");
        return tp.vehicleName;
    }

    public String getCallLogsNumber(){
        long startTime = System.currentTimeMillis();
        String campaignText = getElementTextWithException(bCampaignText);
        boolean noException = false;
        if(campaignText.equals("Deferred MPI Recommendations")) {
            while(!noException){
                tp.callLogsNumber = getElementTextWithException(bCallLogsNumber);
                System.out.println("<====== the number of calls made on CALL LOG on task detail is "+tp.callLogsNumber+" ======>");
                try{
                    Integer.parseInt(tp.callLogsNumber);
                    noException = true;
                }catch (java.lang.NumberFormatException nex){
                    //not able to get the call logs number, will try again
                }
                if((System.currentTimeMillis()-startTime)> 20000){
                    break;
                }
            }
        }
        return tp.callLogsNumber;
    }

//    public String getCallAttemptsNumber(){
//        long startTime = System.currentTimeMillis();
//        boolean noException = false;
//        while(!noException){
//                tp.callAttemptsNumber = getElementTextWithException(bCallAttemptsNumber);
//                System.out.println("<====== the number of calls on task list is "+tp.callAttemptsNumber+" ======>");
//                try{
//                    Integer.parseInt(tp.callAttemptsNumber);
//                    noException = true;
//                }catch (java.lang.NumberFormatException nex){
//                    //not able to get the call attempts number, will try again
//                }
//                if((System.currentTimeMillis()-startTime)> 20000){
//                    break;
//                }
//            }
//        return tp.callAttemptsNumber;
//    }

    public String getVinOnTaskDetail(){
        tp.vinOnTaskDetail = driver.findElement(bVinOnTaskDetail).getAttribute("innerHTML");
        System.out.println("<====== the current vin on task detail is " + tp.vinOnTaskDetail + " ======>");
        return tp.vinOnTaskDetail;
    }

    public void selectValueFromDropDown(String value, String dropDown){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                switch(dropDown){
                    case "Select Action":
                    	if (value.equals("GLOBAL")) {
							value = CC_ConfigPage.englishTxtOtherClosedCall;
						}                    	
                        while(!driver.findElement(bDropDownValue(value)).isSelected()){
                            clickElementWithException(bSelectAction);
                            clickElementWithException(bDropDownValue(value));
                            sleep(1000);
                            if((System.currentTimeMillis()-startTime)> 10000){
                                break;
                            }
                        }
                        break;
                    case "Campaign Dropdown":
                        clickElementWithException(bCampaignDropDown);
                        clickElementWithException(bDropDownValueCampaign(value));
                        break;
                    case "Campaign Type":
                        clickElementWithException(bCampaignTypeDropdown);
                        sleep(2000);
                        clickElementWithException(bDropdownValueCampaignType(value));
                        while(elementHasAttribute(bDropdownValueCheckbox(value),"checked") == false){
                            clickElementWithException(bDropdownValueCampaignType(value));
                            if((System.currentTimeMillis()-startTime)> 10000) break;
                        }
                        break;
                    case "Assigned Agent":
                        clickElementWithException(bAssignedAgentDropdown);
                        clickElementWithException(bDropdownValueAssignedAgent(value));
                        break;
                }
                noException = true;
            }catch(org.openqa.selenium.WebDriverException ex){
                if(driver.findElements(dialog).size()!=0){
                    dWait.until(conditionVisible(noButton)).click();
                }
                sleep(1000);
                noException = false;
            }
            if((System.currentTimeMillis()-startTime)> 20000){
                break;
            }
        }
    }

    public void clickOnScheduleAppointment(){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bScheduleAppointment).click();
                System.out.println("<====== schedule appointment clicked ======>");
                noException = true;
            }catch (WebDriverException e) {
                clickNoOnAppointmentClosePopUp();
                noException = false;
            }
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 30000){
                System.out.println("<====== Not able to click the schedule appointment, test fail ======>");
                break;
            }
        }
    }
    
    public void clickOKBtnOnPopup() {
    	 long startTime = System.currentTimeMillis();
         boolean noException = false;
         while(!noException){
             try{
                 driver.findElement(bOKBtnOnPopupBy).click();
                 System.out.println("<====== OK button on popup clicked ======>");
                 noException = true;
             }catch (WebDriverException e) {
                 clickNoOnAppointmentClosePopUp();
                 noException = false;
             }
             sleep(1000);
             if((System.currentTimeMillis()-startTime)> 30000){
                 System.out.println("<====== Not able to click the OK button, test fail ======>");
                 break;
             }
         }
    }

    public void clickOnDeferredRecommendationsTab(){
        dWait.until(conditionClick(bDeferredRecommendations)).click();
    }

    public boolean verifyIfRecordsUnderDeferredRecommendations(){
        if(Integer.parseInt(getElementTextWithException(bDeferredRecommendationsNumber))>0){
            if(byElementContainText(bDetailDeferredRecommendation,"201")){
                System.out.println("<====== Deferred Recommendation '"+getElementTextWithException(bDetailDeferredRecommendation)+"' show on the page =====>");
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    public void clickModifyIconOnCustomerDetails(){
        try{
            sWait.until(conditionClick(bNoButton)).click();
        }catch (WebDriverException e){
            //no pop up there
        }
        clickElementWithException(bModifyOnCustomerDetails);
    }

    public void updateFirstNameField(String firstName){
        if(firstName.equals("Random")){
            CommonMethods cm = new CommonMethods();
            cm.getRandomText(5);
            firstName = tp.randomText;
        }
        clickElementWithException(bFirstNameCustomerDetails);
        clearAndInputElementWithException(bFirstNameCustomerDetails,firstName);
    }

    public void updateLastNameField(String lastName){
        if(lastName.equals("Random")){
            CommonMethods cm = new CommonMethods();
            cm.getRandomText(5);
            lastName = tp.randomText;
        }
        clearAndInputElementWithException(bLastNameCustomerDetails,lastName);
    }

    public void updateCellPhoneField(String phoneNumber){clearAndInputElementWithException(bCellPhoneCustomerDetails,phoneNumber);}

    public void updateAddressField(String address){clearAndInputElementWithException(bAddressCustomerDetails,address);}

    public void updateCityField(String city){
        clearAndInputElementWithException(bCityCustomerDetails,city);
    }

    public void updateCountryField(String country){selectDropListElementWithException(bCountryCustomerDetails,country);}

    public void updateZipField(String zip){
        clearAndInputElementWithException(bZipCustomerDetails,zip);
    }

    public void updateStateProvinceField(String state){selectDropListElementWithException(bStateCustomerDetails,state);}

    public void clickSaveOnCustomerDetailsPopup(){
        scrollPageDown(1000);
        clickElementWithException(bSaveOnCustomerDetailPopup);
    }

    public void clickOnOKOnMessagePopup(){
        clickElementWithException(bOKOnMessagePopup);
    }

    public String getHomeIconCustomerDetails(){
        String address = getElementTextWithException(bHomeIconCustomerDetails1stLine)+" "+getElementTextWithException(bHomeIconCustomerDetails2ndLine);
        return address;
    }

    public String getCellPhoneIconCustomerDetails(){
        String phoneNumber = getElementTextWithException(bCellIconCustomerDetails);
        return phoneNumber;
    }

    public boolean verifyAgentNameExistOnTask(String agentName){
        waitForElementVisibleWithException(bAgentName(agentName));
        try{
            dWait.until(conditionVisible(bAgentName(agentName)));
            return true;
        }catch (WebDriverException e){
            return false;
        }
    }

    public boolean verifyAgentNameNotExistOnTask(String agentName){
        try{
            driver.findElement(bAgentName(agentName)).isDisplayed();
            return false;
        }catch (WebDriverException e){
            return true;
        }
    }

    public void getCustomerNameAgentNameArray(String agentName){
        if(agentName.equals("Kathy Guan")){
            customerNameWithAgentAssigned1 = getCustomerNameAssoicatedWithAgent(agentName);
        }else if(agentName.equals("Darren Deng")){
            customerNameWithAgentAssigned2 = getCustomerNameAssoicatedWithAgent(agentName);
        }
    }

    public String[] getCustomerNameAssoicatedWithAgent(String agentName){
        String[] customerNameWithAgent = {getElementTextWithException(bCustomerNameAssociatedWithAgentName(agentName)),agentName};
        return customerNameWithAgent;
    }

    public String getCustomerNameOnTasks(){
        tp.customerNameOnTask = getElementTextWithException(bCustomerNameFirstRow);
        return tp.customerNameOnTask;
    }

    public void clickCustomerNameOnTask(){
        clickElementWithException(bCustomerName(tp.customerNameOnTask));
    }

    public void clickDropDown(String dropDownName){
        clickElementWithException(bDropDownWithName(dropDownName));
    }

    public void clickCheckBox(String checkBoxName){
        clickElementWithException(bCheckBox(checkBoxName));
    }

    public void selectCustomerWithOrder(String order){
        sleep(3000);
        if(order.equals("first")){
            order = "1";
        }
        if(order.equals("second")){
            order = "2";
        }  
        sleep(10000);
        clickElementWithException(bCustomerWithOrder(order));
    }

    public void clickOnClearAll(){clickElementWithException(bClearAll);}

    public boolean ifTasksListPresented(){return dWait.until(conditionVisible(bTasksListHeader)).isDisplayed();}

    public boolean isTasksListEmpty(){
        try{
            waitForLoadingCircleToDisappearCC();
            sleep(1000);
            driver.findElement(bTasksList).isDisplayed();
            return false;
        }catch(WebDriverException e){return true;}
    }

    public void randomlySelectTaskLists(int numOfRandomTasks) {
        indexOfCheckbox = CommonMethods.setIndexNumber(getTaskListCheckboxes(), numOfRandomTasks);
        clickTaskListCheckboxes(numOfRandomTasks);
    }

    public void clickTaskListCheckboxes(int numOfRandomTasks) {
        for(int i=0; i<numOfRandomTasks; i++){
//            System.out.println("Now click checkbox no." + indexOfCheckbox[i]);
            driver.findElement(bTaskListCheckbox(indexOfCheckbox[i])).click();
        }
    }

    public void clickReassignTasksBtn() {driver.findElement(bReassignTasksBtn).click();}

    public void searchBDCAgent(String agentName) {driver.findElement(bBDCAgentSearchField).sendKeys(agentName);}

    public void clickLeftArrowBtn() {driver.findElement(bLeftArrowBtnBDC).click();}

    public void clickApplyBtn() {driver.findElement(bApplyBtn).click();}

    public void clickYesBtnOnPopup() {clickElementWithException(yesButton);}

    public void pauseForDataLoading(int millSec) {sleep(millSec);}

    public String getAssignedAgentName(int index) {return driver.findElement(bAssignedAgentName(indexOfCheckbox[index])).getText();}

    public void clickReassignAgentsBtn() {clickElementWithException(bReassignAgentsBtn);}

    public void clickIncludeAllCampaignsArrow() {clickElementWithException(bIncludeAllCampaignsArrow);}

    public void clickNextBtn() {clickElementWithException(bNextBtn);}

    public void searchGlobalAgent(String agentName) {driver.findElement(bGlobalAgentSearchField).sendKeys(agentName);}

    public void clickRightGreenArrowBtn(String agentName) {clickElementWithException(bRightGreenArrowBtnWithAgentName(agentName));}

    public void clickLeftRedArrowBtn(String agentName) {clickElementWithException(bLeftRedArrowBtnWithAgentName(agentName));}

    public void clickSaveBtn() {clickElementWithException(bSaveBtn);}

    public String getNumberOfTasks() {
        waitForLoadingCircleToDisappearCC();
        sleep(2000);
        String numOfTasks = getElementTextWithException(bCampaignNameAndNumberHeader);
        numOfTasks = numOfTasks.substring(0, numOfTasks.indexOf(" "));
        return numOfTasks;
    }

    public String getAssignedAgentColumnValue(int index){return getElementTextWithException(bAssignedAgentColumn(String.valueOf(index)));}

    public String getURL() {
        sleep(1000);
        return driver.getCurrentUrl();
    }

    public String getVIN() {return driver.findElement(bVIN).getText();}

    public void clickServiceHistoryTab() {clickElementWithException(bServiceHistoryTab);}

    public void clickInfoIcon(int index) {
        waitForElementVisibleWithException(bInfoIcons);
        driver.findElements(bInfoIcons).get(index).click();
    }

    public void clickOnExport() {
        setWait();
        dWait.until(conditionVisible(bExport)).click();
        clickElementTimesTillDisappear(bSelectAllExport, 2);
        dWait.until(conditionVisible(bExportOnExportPage)).click();
    }

    public String getColumnValueAfterSave(String columnName, String columnValue) {
        String actualValue = "";
        switch (columnName) {
            case "Call Attempts":
//                waitForColumnScrollDownWithException(bColumnName(columnName), bCallAttemptColumnWithNameAndVehicle(tp.userName, tp.vehicleName));
                actualValue = tp.callAttempts;
                break;
            case "Last Action":
//                waitForColumnScrollDownWithException(bColumnName(columnName), bColumnAfterSave(tp.userName, tp.vehicleName, columnValue));
                actualValue = dWait.until(conditionVisible(bColumnAfterSave(tp.userName, tp.vehicleName, columnValue))).getAttribute("innerHTML");
                break;
        }
        return actualValue;
    }
    
    public void validateOtherEnglishOption(String option){
    	if (option.equals("GLOBAL")) {
			option = CC_ConfigPage.getEnglishTxtOtherCall();
		}
        CommonMethods.verifyElementExists(driver.findElement(By.xpath("//select[@id='callLogResult']/optgroup[@label='"+option+"']")));        
    }  
    
    public void iSelectEmailAndTextCheckboxIfNotSelectedAlready() {
        sleep(5000);      	
        String classValueTextChkBox = driver.findElement(By.xpath("//label[@for='enableDisableAuthMethods0']//parent::div/input")).getAttribute("class").toString();
    	System.out.println("Text Checkbox - Value of getClass().toString() "+ classValueTextChkBox);
    	if (!classValueTextChkBox.contains("not-empty")) {
	        sleep(2000);    		
			System.out.println("Text checkbox is not checked, click the checkbox");
			CommonMethods.JavaScriptClick(bTextChkBox);
		} 
    	
        String classValueEmailChkBox = driver.findElement(By.xpath("//label[@for='enableDisableAuthMethods2']//parent::div/input")).getAttribute("class").toString();
    	System.out.println("Email Checkbox - Value of getClass().toString() "+ classValueEmailChkBox);
    	if (!classValueEmailChkBox.contains("not-empty")) {
	        sleep(2000);    		
			System.out.println("Checkbox is not checked, click the checkbox");
			CommonMethods.JavaScriptClick(bEmailChkBox);
		} 
    }
    
    public void modifyCellPhoneField(String phoneNo){
        clearAndInputElementWithException(bCellPhoneField, phoneNo);
    }   
    
    public void modifyEmailField(String email){
        clearAndInputElementWithException(bEmailField, email);
    }     

    public void clickCustDetailsSaveBtn() {clickElementWithException(bCustDetailsSaveBtn);}    

    
    public void clickCustDetailsOKPopUpBtn() {clickElementWithException(bCustDetailsOKBtnOnPopupBy);}   
    
    public void clickEMail() {
    	clickElementWithException(bCommHeader);
    	clickElementWithException(bCommEmail);    	
    }    

    public void clickChatSubmitBtn() {clickElementWithException(bChatSubmit);}       

    public void enterEmail() {
        clearAndInputElementWithException(bComposeSenderEmailField, "contact@dfx.com");  	
    }    
    
    public void enterEmailContents() {
        clearAndInputElementWithException(bEmailContentsField, "Please contact DFX for any issue");  	
    }     
    
    public void clickMailSendBtn() {
    	clickElementWithException(bComposeSend);
    	dWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Email sent. The current task will be closed.']")));    	
    }   
    
    public void inputTextOnEmailSubject(String text) {
        if(text.equals("TEST random")){
            CommonMethods cm = new CommonMethods();
            tp.newCreatedEmailSubject = "Test"+" "+cm.getRandomText(5);
        }
        else {
            tp.newCreatedEmailSubject = text;        	
        }
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bComposeEmailSubject).clear();
                driver.findElement(bComposeEmailSubject).sendKeys(tp.newCreatedEmailSubject);
                noException = true;
            }catch (WebDriverException e){
                noException = false;
            }
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
        System.out.println("<====== The Email Subject is: " + tp.newCreatedEmailSubject + " ======>");
    }
    
    public void closeMessagePopUp() {
    	List<WebElement> list = driver.findElements(bCloseMessagePopUp);
    	int size = list.size();
    	System.out.println("Size of elements are "+ size);
    	clickElementWithException(list.get(size - 1));
    	dWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'The current task will be closed.')]")));  	
    }
    
    public void clickMailSendSMSBtn() {
    	clickElementWithException(bComposeSMSSend);
    	dWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='SMS sent. The current task will be closed.']")));	
    }    
    
    public void enterSMSContents() {
        CommonMethods cm = new CommonMethods();
        tp.sms = "TEST" + " " + cm.getRandomText(5);    	
        clearAndInputElementWithException(bSMSContentsField, tp.sms);  	
    }    
    
    public void clickSMS() {
    	clickElementWithException(bCommHeader);
    	clickElementWithException(bCommSMS);    	
    }    
    
    public void iDeSelectEmailAndTextCheckboxIfNotDeSelectedAlready() {
        sleep(5000);      	
        String classValueTextChkBox = driver.findElement(By.xpath("//label[@for='enableDisableAuthMethods0']//parent::div/input")).getAttribute("class").toString();
    	System.out.println("Text Checkbox - Value of getClass().toString() "+ classValueTextChkBox);
    	if (classValueTextChkBox.contains("not-empty")) {
	        sleep(2000);    		
			System.out.println("Text checkbox is not checked, click the checkbox");
			CommonMethods.JavaScriptClick(bTextChkBox);
		} 
    	
        String classValueEmailChkBox = driver.findElement(By.xpath("//label[@for='enableDisableAuthMethods2']//parent::div/input")).getAttribute("class").toString();
    	System.out.println("Email Checkbox - Value of getClass().toString() "+ classValueEmailChkBox);
    	if (classValueEmailChkBox.contains("not-empty")) {
	        sleep(2000);    		
			System.out.println("Checkbox is not checked, click the checkbox");
			CommonMethods.JavaScriptClick(bEmailChkBox);
		} 
    }
    
    public void validateUnauthorizedEmailMessage() {
    	CommonMethods.verifyElementExists(driver.findElement(bEmailUnauthorized));
    } 
    
    public void validateUnauthorizedSMSMessage() {
    	CommonMethods.verifyElementExists(driver.findElement(bTextUnauthorized));
    }   
    
    public void clickCustDetailsAuthorizeOKPopUpBtn() {clickElementWithException(bCustDetailsAuthorizeOKBtnOnPopupBy);}
    
    public void searchCustomerByName(){
        clearAndInputElementWithException(bSearchTextBox, CC_SearchPage.customerName);  	
    }
    
}
