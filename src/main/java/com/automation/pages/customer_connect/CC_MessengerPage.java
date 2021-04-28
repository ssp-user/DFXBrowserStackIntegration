package com.automation.pages.customer_connect;

import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;

public class CC_MessengerPage extends WebPage {

    CC_TasksPage tPage = new CC_TasksPage();

    //page object
    //search field on Messenger page
    private static By bSearchMessengerPage = By.id("inputSearch");

    //customer button on Messenger page
    private static By bCustomerButton = By.xpath("//div[@class='msg-service-type-name' and contains(text(),'Customer')]");

    //customer name on Messenger page
    private static By bCustomerNameResult = By.xpath("//div[@class='msngrListCustomerName useEllipsisText ng-binding']");

    //customer name on Messenger page with search result
    private static By bCustomerNameResultContains(String searchWord){
        String locator = "//div[@class='msngrListCustomerName useEllipsisText ng-binding' and contains(text(),'"+searchWord.toLowerCase()+"')]";
        return By.xpath(locator);
    }

    //Add Note button on the customer detail, green color
    private static By bCustomerAddNote = By.xpath("//div[@class='customer-addnote-button commandButtonNoHover scheduleButton commandButton']");

    //Note button on the customer detail, grey color
    private static By bCustomerNote = By.xpath("//div[@class='customer-note-button commandButtonNoHover scheduleButton commandButton']");

    //Sticky notes text field
    private static By bStickyNote = By.xpath("//div[contains(@id,'PIApostit') and contains(@style,'display: block;')]//div[contains(@id,'pia_editable')]");

    //close icon on sticky note
    private static By bCloseOnNote = By.xpath("//div[contains(@id,'PIApostit') and contains(@style,'display: block;')]//*[@ng-click='saveNote()']");

    //modify icon on Messenger page
    private static By bModify = By.cssSelector("#viewport > div > div.flexDiv.ng-scope > div > div.msg-info > div > div.vehicle-info.blueScrollBar.ng-isolate-scope > div:nth-child(1) > svg");

    //refresh button on the messenger page for refresh service lane customer
    private static By bRefresh = By.xpath("//div[@ng-click='getServiceLaneCustomers()' and contains(text(),'Refresh')]");

    //cell phone field after click modify icon on Messenger page
    private static By bCellPhoneField = By.xpath("//input[@ng-model='customerEditInput.CellPhone']");

    //Save on customer detail modification field pop up
    private static By bSaveOnCustomerDetail = By.xpath("//div[contains(text(),'Save')]");

    //modification confirm after save on customers detail
    private static By bUpdateConfirm = By.xpath("//button[@ng-click='closeUpdateResultPopup()']");
    
    //close button on the pop-up after save on customers detail
    private static By bUpdateClose = By.xpath("//*[@id='customer-update-result-dialog']/div[1]/div[1]/h2/button/span");
    
    //enter message field on Messenger page
    private static By bEnterMessage = By.xpath("//div[@class='flexDiv horizontalFlexOrder chat-messageInputContainer']/textarea");

    //press enter to sent toggle
    private static By bPressEnterToSend = By.xpath("//label[@class='campaignExpireTaskCheckboxBtn']");

    //SMS send icon
    private static By bSMSSendIcon = By.cssSelector("#viewport > div > div.flexDiv.ng-scope > div > div.col-xs-6 > div.chat-container.blueScrollBar.ng-scope > div > div > div > div > div.chat-bottom-bar > form > div.flexDiv.horizontalFlexOrder.chat-messageInputContainer > div > div > svg");

    //number field on ReceiveSMSPage
    private static By bNumberField = By.xpath("//p[contains(text(),'Number')]//a");

    //service lane button
    private static By bServiceLane = By.xpath("//div[text()='Service Lane']");

    //customer button
    private static By bCustomer = By.xpath("//div[text()='Customer']");
    
    //text check box on Customer Details page
    private static By bTextCheckbox = By.xpath("//input[@id='enableDisableAuthMethods0']/../label");
    
    //text check upper layer on Customer Details page
    private static By bCheckboxUpper = By.xpath("//input[@id='enableDisableAuthMethods0']");
    
    //opt in Text check box on Customer Details page
    public void optInTextCheckBox(String checkbox){
    	switch (checkbox){
	        case "Text":
	        	String classValue = driver.findElement(bCheckboxUpper).getAttribute("class");
	        	if(!classValue.contains("not")){
	        	driver.findElement(bTextCheckbox).click();
	        	}
	        	break;
    	}
	}
    
    //Message field on ReceiveSMSPage
    public static By bSMSOnReceiveSMS(String textSMS){
        String locator = "//td[contains(text(),'"+textSMS+"')]";
        return By.xpath(locator);
    }

    //Message field on ReceiveSMSPage
    private static By bTimeOnReceiveSMS(String textSMS){
        String locator = "//td[contains(text(),'"+textSMS+"')]/preceding-sibling::td[2]";
        return By.xpath(locator);
    }

    //1st line address on customer details
    private static By bHomeIconCustomerDetails1stLineMessenger = By.xpath("//*[@class='icon icon--address messengerCustomerDetailIcon']/..");

    //2nd line address on customer details
    private static By bHomeIconCustomerDetails2ndLineMessenger = By.xpath("//div[contains(@class,'chatVehicle-value-address')]");

    //cell phone number on customer details
    private static By bCellIconCustomerDetailsMessenger = By.xpath("//*[@class='icon icon--phone-cell messengerCustomerDetailIcon']/..");

    //message dialog box
    private static By bMessageBox = By.xpath("(//div[@class='chat-message']/div[2])[last()]");

    //String to store value
    //public String phoneNumber;
    //public String timeStampOnSMSPageBefore;
    //public String timeStampOnSMSPageAfter;

    //search customer
    public void searchCustomerOnMessenger(String searchWord){
        long startTime = System.currentTimeMillis();
        tPage.waitForLoadingCircleToDisappearCC();
        clickElementWithException(bCustomerButton);
        clearAndInput(bSearchMessengerPage,searchWord);
        do{
            dWait.until(conditionClick(bSearchMessengerPage)).click();
            dWait.until(conditionClick(bSearchMessengerPage)).sendKeys(Keys.ENTER);
            keyboardEnter();
            sleep(500);
            if((System.currentTimeMillis()-startTime)> 60000){
                break;
            }
        } while(!getCustomerNameSearchResult().contains(searchWord.toLowerCase()));
    }

    //get customer name searchResult
    public String getCustomerNameSearchResult(){
        tPage.waitForLoadingCircleToDisappearCC();
        tp.customerNameOnMsg = dWait.until(conditionVisible(bCustomerNameResult)).getAttribute("innerHTML").toLowerCase();
        return tp.customerNameOnMsg;
    }

    //input text on sticky notes
    public void inputTextOnNotes(String text){
        if(text.equals("Test random")){
            CommonMethods cm = new CommonMethods();
            text = "Test"+" "+cm.getRandomText(5);
        }
        tp.customerNoteTxt = text;
        clearAndInputElementWithException(bStickyNote, text);
        System.out.println("<====== The text on the sticky notes on Messenger page is "+text+" ======>");
    }

    //sticky note text
    public String getTextOnNote(){
//        long startTime = System.currentTimeMillis();
//        String notesText = "";
//        while(!notesText.equals(tp.customerNoteTxt)){
//            notesText = getElementTextWithException(bStickyNote);
//            if((System.currentTimeMillis()-startTime)> 20000){
//                break;
//            }
//        }
        return getElementTextWithException(bStickyNote);
    }

    //close icon on the sticky note
    public void clickOnCloseOnNotes(){
        clickElementWithException(bCloseOnNote);
    }

    //click on customer with the name
    public void clickCustomerWithNameOnMsg(){
        String customerName = tp.customerNameOnMsg;
        clickElementWithException(bCustomerNameResultContains(customerName));
        System.out.println("<====== Customer '"+customerName.toUpperCase()+"' clicked ======>");
    }

    public void clickOnSearchResult(String searchWord){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bCustomerNameResultContains(searchWord)).click();
                noException = true;
            }catch (WebDriverException e) {
                searchCustomerOnMessenger(searchWord);
            }
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 20000){
                break;
            }
        }
    }

    public void clickOnModifyIconOnMessenger(){
        dWait.until(conditionVisible(bModify)).click();
    }

    //this is click to refresh the service lane
    public void clickOnRefresh(){
        clickElementWithException(bRefresh);
        CC_TasksPage td = new CC_TasksPage();
        td.waitForLoadingCircleToDisappearCC();
        sleep(5000);
    }

    //this method has to execute after getting phone number from the Receive SMS page
    public void modifyCellPhoneField(String phoneNo){
        clearAndInputElementWithException(bCellPhoneField, phoneNo);
    }

    public void clickOnSaveOnCustomerDetail(){
        dWait.until(conditionClick(bSaveOnCustomerDetail)).click();
        dWait.until(conditionClick(bUpdateConfirm)).click();
    }

    public void sendMessageToCustomer(String textSMS){
        clearAndInputElementWithException(bEnterMessage, textSMS);
        dWait.until(conditionVisible(bEnterMessage)).sendKeys(Keys.ENTER);
    }

    //get number on the SMS receive page
    public String getNumberOnReceiveSMS(){
        tp.phoneNumber = getElementTextWithException(bNumberField).replace("+1","");
        tp.timeStampOnSMSPageBefore = getCurrentTime(System.currentTimeMillis() - 3600 * 1000);
        System.out.println("<====== the phone number I got from the SMS page is '"+tp.phoneNumber+"' ======>");
        return tp.phoneNumber;
    }

    //get SMS from SMS receive page
    public String getSMSFromReceiveSMSPage(String textSMS){
        long startTime = System.currentTimeMillis();
        String textSMSAssociatedWithNumber = "";
        while((!textSMSAssociatedWithNumber.contains(textSMS)) || (!verifySMSReceiveTime(textSMS))){
            pageRefresh();
            textSMSAssociatedWithNumber = getElementTextWithException(bSMSOnReceiveSMS(textSMS));
            sleep(1000);
            System.out.println("<====== SMS page refreshing, be patient ======>");
            if((System.currentTimeMillis()-startTime)> 90000){
                System.out.println("<====== SMS not getting on the page ======>");
                break;
            }
        }
        return textSMSAssociatedWithNumber;
    }

    //determine the SMS receive time
    public boolean verifySMSReceiveTime(String textSMS){
        try{
            tp.timeStampOnSMSPageAfter = (driver.findElement(bTimeOnReceiveSMS(textSMS)).getText());
        }catch (WebDriverException ex){
            return false;
        }
        long timeBefore = Long.parseLong(tp.timeStampOnSMSPageBefore.replaceAll("[^0-9]",""));
        long timeAfter = Long.parseLong(tp.timeStampOnSMSPageAfter.replaceAll("[^0-9]",""));
        if(timeAfter<timeBefore){
            return false;
        }else{
            System.out.println("<====== The SMS sent time is    "+tp.timeStampOnSMSPageBefore+" ======>");
            System.out.println("<====== The SMS receive time is "+tp.timeStampOnSMSPageAfter+" ======>");
            return true;
        }
    }

    public String getHomeIconCustomerDetailsMessenger(){
        String address = getElementTextWithException(bHomeIconCustomerDetails1stLineMessenger)+" "+getElementTextWithException(bHomeIconCustomerDetails2ndLineMessenger);
        return address;
    }

    public String getCellPhoneIconCustomerDetailsMessenger(){
        String phoneNumber = getElementTextWithException(bCellIconCustomerDetailsMessenger);
        return phoneNumber;
    }

    public void clickOnServiceLane(){
        clickElementWithException(bServiceLane);
        tPage.waitForLoadingCircleToDisappearCC();
    }

    public void clickOnCustomer(){
        clickElementWithException(bCustomer);
        tPage.waitForLoadingCircleToDisappearCC();
    }

    public void clickOnAddNoteButton(){
        try{
            sWait.until(conditionVisible(bCustomerAddNote));
            driver.findElement(bCustomerAddNote).click();
        }catch(WebDriverException e){
            sWait.until(conditionVisible(bCustomerNote));
            driver.findElement(bCustomerNote).click();
        }
    }

    public void clickOnNoteButton(){
        clickElementWithException(bCustomerNote);
    }

    public String getSMSFromMessengerPage(String textSMS){
        long startTime = System.currentTimeMillis();
        String msg = "";
        while(!msg.equals(textSMS)){
            msg = getElementTextWithException(bMessageBox);
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
        return msg;
    }
}
