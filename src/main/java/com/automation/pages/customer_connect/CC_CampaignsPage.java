package com.automation.pages.customer_connect;

import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.util.Random;
import org.openqa.selenium.JavascriptExecutor;

public class CC_CampaignsPage extends WebPage {

    CC_TasksPage tPage = new CC_TasksPage();

    //menu with status
    private static By bMenuWithStatus(String menuName) {
        String checkLocator = "//span[contains(text(),'" + menuName + "')]/parent::a/parent::li";
        return By.xpath(checkLocator);
    }

    //locator
    private static By bCampaignTypeDropDown = By.id("campaignTypeInput");

    //Email Templates dropdown
    private static By bEmailTemplatesDropDown = By.xpath("//select[@ng-model='selectedCampaignData.EmailScriptId']");

    //Email Templates options
    private static By bEmailTemplatesOptions = By.xpath("//select[@ng-model='selectedCampaignData.EmailScriptId']/option");

    //new campaign button
    private static By bNewCampaign = By.xpath("//div[contains(text(),'New Campaign')]");

    //new campaign name
    private static By bCampaignName = By.id("campaignNameInput");

    //new campaign description
    private static By bCampaignDescription = By.id("campaignDescriptionInput");

    //Email Subject on Create Script
    private static By bEmailSubjectOnCreateScript = By.xpath("//input[@ng-show='EmailCampaign.isEnabled']");

    //Email Subject on Preview popup
    private static By bEmailSubjectOnPreview = By.xpath("//input[@ng-model='emailPreviewSubject']");

    //Email Body Content
    private static By bEmailBodyContent = By.xpath("//div[@class='Editor-editor blueScrollBar']");

    //Save button on Create Script popup
    private static By bSaveBtn = By.xpath("//div[@class='configButton configGreenButton configNewButton']");

    //Selected option under Email Templates dropdown
    private static By bSelectedOptionUnderEmailTemplates = By.xpath("//select[@ng-model='selectedCampaignData.EmailScriptId']//child::option[@selected='selected']");

    //Preview popup title
    private static By bPreviewPopupTitle = By.xpath("//span[contains(text(), 'Preview')]");

    //Email Body Content field
    private static By bEmailBodyContentOnPreview = By.xpath("//iframe[@id='preview-email-iframe']");

    //Recipient field
    private static By bRecipient = By.id("emailAddressSection-tokenfield");

    //Send Test Email
    private static By bSendTestEmail = By.xpath("//div[@ng-click='sendPreviewEmail()']");

    //campaign name in the left side
    private static By bCampaignByName(String campaignName){
        String locator = "//span[contains(@ng-class,'campaign.Id') and text()='"+campaignName+"']";
        return By.xpath(locator);
    }

    //next button on the campaign page
    private static By bNextOnCampaign = By.xpath("//*[contains(text(),'Next') and contains(@class,'configGreenButton')]");

    //New Template button on the campaigns page
    private static By bNewTemplate = By.xpath("//div[contains(text(), 'New Template')]");

    //Preview and Test button on the campaigns page
    private static By bPreviewAndTest = By.xpath("//div[@ng-click='showEmailPreview()']");

    //create button on the campaign page
    private static By bCreateOnCampaign = By.xpath("//*[contains(@class,'configGreenButton') and contains(text(),'Create')]");

    //OK button on the campaign page
    private static By bOKOnPopUp = By.xpath("//*[contains(@class,'alertMsgBoxMainButton ng-binding') and contains(text(),'OK')]");

    //message dialog box
    private static By bMessageBox = By.xpath("//div[@aria-describedby='alertMsgBox']");

    //Yes button on the pop up on campaign page
    private static By bYesOnPopUp = By.xpath("//button[@ng-if='msgBoxConfig.useAcceptButton' and contains(text(),'Yes')]");

    //scroll bar
    private static By bCampaignListScroll = By.xpath("//section[contains(@class,'blueScrollBar')]");

    //campaign record
    private static By bCampaignRecord(String campaignName){
        String checkLocator = "//span[contains(@ng-class,'campaign.Id') and contains(text(),'"+campaignName+"')]";
        return By.xpath(checkLocator);
    }

    //campaign default selected
    private static By bCampaignDefault(String campaignName){
        String checkLocator = "//span[contains(@ng-class,'bold') and contains(text(),'"+campaignName+"')]/following-sibling::span[contains(text(),'Default')]";
        return By.xpath(checkLocator);
    }

    //Visibility on Dashboard toggle
    private static By bVisibilityOnDashboardToggle = By.xpath("//label[@for='enableDisableShowDashboard']");

    //Visibility on Dashboard toggle Status
    private static By bVisibilityOnDashboardToggleStatus(String status){
//        String locator = "//div[@class='configInputLabel' and contains(text(),'Visibility on Dashboard')]/following-sibling::div/div[contains(text(),'"+status+"')]";
        String locator = "//div[@class='campaignEnableDisableToggle']/following-sibling::div/[contains(text(),'"+status+"')]";
        return By.xpath(locator);
    }

    //delete icon
    private static By bDeleteOnCampaign = By.xpath("//button[@ng-click='setUpAndShowDeleteAlert()']");

    //dynamic tab
    private static By bTab(String tabName){
        String locator = "//div[contains(text(), '" + tabName + "')]";
        return By.xpath(locator);
    }

    //scripts tab
    private static By bScriptTab = By.xpath("//div[contains(text(),'SCRIPTS')]");

    //script preview
    private static By bScriptPreview = By.xpath("//textarea[contains(text(),'Thank you for your business and have a great day!')]");

    //task assignment tab
    private static By bTaskAssignmentTab = By.xpath("//div[contains(text(),'TASK ASSIGNMENT')]");

    //import tab
    private static By bImportTab = By.xpath("//div[contains(text(),'Import') and contains(@class,'configTabItem')]");

    //clear task tab
    private static By bClearTask = By.xpath("//div[contains(text(),'Clear task')]");

    //recent campaign
    private static By bRecentCampaign = By.xpath("//div[contains(@class,'configRecentRow')]");

    //bdc agent search field under Service coordinator available for assignment
    private static By bAgentNameField = By.id("campaignUserSearchInput");

    //bdc name on the left side
    private static By bCampaignNameLeftSide(String agentName){
        String locator = "//div[contains(text(),'"+agentName+"') and contains(@class,'campaignManageRosterName')]/../following-sibling::button//*[@ng-href='#icon--arrow-incircle-right']";
        return By.xpath(locator);
    }

    //campaign task move left svg icon
    private static By bCampaignTaskMoveLeft = By.xpath("//*[@ng-href='#icon--arrow-incircle-left']/..");

    //Save button on the Campaigns page
    private static By bSaveCampaign = By.xpath("//*[contains(@class,'ng-scope') and contains(text(),'Save')]");

    //choose file
    private static By bChooseFile = By.xpath("//label[@class='campaignImportButton']/../input");

    //import green button
    private static By bImportButton = By.xpath("//*[contains(@class,'configGreenButton') and contains(text(),'Import')]");

    //apply green button
    private static By bApplyButton = By.xpath("//div[contains(@class,'configGreenButton') and contains(text(),'Apply')]");

    //the first campaign in the list
    private static By bFirstCampaign = By.xpath("(//div[contains(@class,'configCampaignListRow')])[1]");

    public String campaignName;

    //Communication Type
    private static By bCommunicationType(String communicationType) {
        String checkLocator = "//label[contains(text(), '" + communicationType + "')]";
        return By.xpath(checkLocator);
    }

    public void selectValueFromCampaignDropDownOnCampaign(String dropDownValue){
        selectDropListElementWithException(bCampaignTypeDropDown, dropDownValue);
    }

    public void selectValueFromEmailTemplatesDropDownOnCampaign(String dropDownValue){
        switch (dropDownValue){
            case "first option":
                selectDropListElementWithException(bEmailTemplatesDropDown, "1");
                break;
            default:
                selectDropListElementWithException(bEmailTemplatesDropDown, dropDownValue);
                break;
        }
    }

    public void clickCampaignOnCampaignPage(String campaignName){
        long startTime = System.currentTimeMillis();
        clickElementWithException(bCampaignByName(campaignName));
        boolean noException = false;
        while(!noException){
            try{
                byElementHasClass(bCampaignByName(campaignName),"bold");
                //sWait.until(conditionVisible(bImportTab));
                noException = true;
            }catch (WebDriverException e){
                dWait.until(conditionClick(bCampaignByName(campaignName))).click();
                noException = false;
            }
            if((System.currentTimeMillis()-startTime)> 10000){
                break;
            }
        }
    }

    public void clickOnNewCampaign(){
        dWait.until(conditionClick(bNewCampaign)).click();
        waitForElementWithExceptionIfNotAvailableThenRefresh(bNextOnCampaign);
    }

    public String createRandomLastName(){
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        final int n = 6;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        String randomLastName = sb.toString();
        return randomLastName;
    }

    public void setCampaignName(String firstName){
        campaignName = firstName + " " +createRandomLastName();
        clearAndInputElementWithException(bCampaignName,campaignName);
    }

    public void writeCampaignDescription(String campaignDescription){
        clearAndInputElementWithException(bCampaignDescription, campaignDescription);
    }

    public void clickOnNextOnCampaignPage(){
        clickElementWithException(bNextOnCampaign);
    }

    public void clickOnNewTemplateOnCampaignPage(){
        clickElementWithException(bNewTemplate);
    }

    public void clickPreviewAndTestOnCampaignPage() {
        clickElementWithException(bPreviewAndTest);
    }

    public void clickSendTestEmailOnCampaignPage() {
        dWait.until(conditionClick(bSendTestEmail)).click();
        clickElementWithException(bOKOnPopUp);
    }

    public void clickOnCreateOnCampaign(){
    	sleep(5000);
        long startTime = System.currentTimeMillis();
        dWait.until(conditionClick(bCreateOnCampaign)).click();
        while(!isMessagePopUpShow()){
            try{
                driver.findElement(bCreateOnCampaign).click();
            }catch (WebDriverException e) {
                //the create button is already clicked
            }
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 20000){
                break;
            }
        }
    }

    public void clickOnOKOnPopUpOnCampaign(){
        clickElementWithException(bOKOnPopUp);
        try{
            driver.findElement(bOKOnPopUp).click();
        }catch(WebDriverException e){
            //this is to prevent ok button not getting clicked
        }
        sleep(5000);
    }

    public boolean verifyIfCampaignRecordCreated(){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bCampaignRecord(campaignName)).isDisplayed();
                noException = true;
            }catch (WebDriverException e) {
                System.out.println("<====== Not able to find campaign "+campaignName+" , will refresh the page and try again ======>");
                driver.navigate().refresh();
                sleep(8000);
            }
            if((System.currentTimeMillis()-startTime)> 20000){
                break;
            }
        }
        //waitForElementVisibleWithException(bCampaignRecord(campaignName));
        if(verifyIfElementClickableOnPage(bCampaignRecord(campaignName))){
            System.out.println("<====== New campaign '"+campaignName+"' created ======>");
            return true;
        }else{
            return false;
        }
    }

    public boolean verifyIfCampaignRecordDeleted(){
        tPage.waitForLoadingCircleToDisappearCC();
        if(verifyIfElementNotShowOnThePage(bCampaignRecord(campaignName))){
            System.out.println("<====== New campaign '"+campaignName+"' deleted ======>");
            return true;
        }else{
            return false;
        }
    }

    public void clickCampaignRecord(){
        clickElementWithException(bCampaignRecord(campaignName));
    }

    public void clickDeleteOnCampaign(){
        dWait.until(conditionClick(bDeleteOnCampaign)).click();
    }

    public boolean verifyCampaignSelectedAsDefault(String campaignType){
        waitForElementVisibleWithException(bCampaignDefault(campaignType));
        if(verifyIfElementShowOnThePage(bCampaignDefault(campaignType))){
            System.out.println("<====== Campaign '"+campaignType+"' preselected ======>");
            return true;
        }else{
            return false;
        }
    }

    public void toggleStatusOnField(String fieldName, String status){
        switch (fieldName){
            case "Visiblity on Dashboard":
                if(byElementHasClass(bVisibilityOnDashboardToggleStatus(status),"bold")){
                    System.out.println("<====== Visibility on Dashboard has already toggled on "+status+"======>");
                }else{
                    clickElementWithException(bVisibilityOnDashboardToggle);
                }
                break;
        }
    }

    public void clickOnScriptsTab(){
        clickElementWithException(bScriptTab);
        waitForElementVisibleWithException(bScriptPreview);
    }

    public void clickOnTaskAssignmentTab(){
        clickElementWithException(bTaskAssignmentTab);
    }

    public void clickOnImportTab(){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                sWait.until(conditionClick(bImportTab)).click();
                noException = true;
            }catch(WebDriverException e){
                clickCampaignOnCampaignPage("Test Import");
                noException = false;
            }
            if((System.currentTimeMillis()-startTime)>20000){
                break;
            }
        }
    }

    public void clickOnClearTaskTab(){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bClearTask).click();
                noException = true;
            }catch (WebDriverException e) {
                try{
                    driver.findElement(bRecentCampaign).click();
                }catch (WebDriverException ex){
                    //don't see recent campaign. ignore
                }
                sleep(1000);
            }
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
    }

    public void searchBDCAgent(String agentName){
        long startTime = System.currentTimeMillis();
        waitForElementWithException(bCampaignTaskMoveLeft);
        try{
            sWait.until(conditionVisible(bCampaignNameLeftSide(agentName)));
        }catch (WebDriverException e){
            //do nothing
        }
        while(!verifyIfElementVisibleOnPage(bCampaignNameLeftSide(agentName))){
            clearAndInputElementWithException(bAgentNameField,agentName);
            clickElementWithSeconds(bCampaignTaskMoveLeft,2000);
            if((System.currentTimeMillis()-startTime)>10000){
                break;
            }
        }
    }

    public void clickOnSaveOnCampaign(){
        clickElementWithException(bSaveCampaign);
        clickElementWithException(bOKOnPopUp);
    }

    public void clickOnChooseFile(){
        waitForElementWithException(bChooseFile);
    }

    public void uploadCampaignFile(String fileName){
        File file = new File("./testData/"+fileName+"");
        driver.findElement(bChooseFile).sendKeys(file.getAbsolutePath());
    }

    public void clickOnImportButton(){
        long startTime = System.currentTimeMillis();
        Boolean noException = false;
        while(!noException){
            try{
                sWait.until(conditionClick(bImportButton)).click();
                noException = true;
            }catch (WebDriverException e){
                sleep(1000);
                noException = false;
            }
            if((System.currentTimeMillis()-startTime)>10000){
                break;
            }
        }
        clickElementWithException(bOKOnPopUp);
    }

    public boolean isMessagePopUpShow(){
        try{
            if(driver.findElement(bMessageBox).getAttribute("style").contains("display: flex")){
                return true;
            }else{
                return false;
            }
        }catch (WebDriverException e){
            return false;
        }
    }

    public void clickOnApplyButton(){
        long startTime = System.currentTimeMillis();
        Boolean noException = false;
        //in case user is not on the clear task tab
        while(!noException){
            try{
                driver.findElement(bApplyButton).click();
                noException = true;
            }catch (WebDriverException e){
                clickOnClearTaskTab();
            }
            sleep(1000);
            if((System.currentTimeMillis()-startTime)>10000){
                break;
            }
        }
        //in case there is confirm pop up and data base error message
        while(isMessagePopUpShow()){
            try{
                driver.findElement(bYesOnPopUp).click();
            }catch (WebDriverException e) {
                //the message confirm is not there
            }
            try{
                driver.findElement(bOKOnPopUp).click();
            }catch (WebDriverException e) {
                //the database message is not there
            }
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 20000){
                break;
            }
        }
    }

    public boolean isOnTab(String tabName){
        try{
            if(driver.findElement(bTab(tabName)).getAttribute("class").contains("configActiveTab")){
                return true;
            }else{
                return false;
            }
        }catch (WebDriverException e){
            return false;
        }
    }

    public void selectCommunicationType(String communicationType){
        dWait.until(conditionClick(bCommunicationType(communicationType))).click();
    }

    public void inputTextOnCampaignName(String text){
        if(text.equals("random character")){
            CommonMethods cm = new CommonMethods();
            text = cm.getRandomText(5);
        }
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bCampaignName).clear();
                driver.findElement(bCampaignName).sendKeys(text);
                noException = true;
            }catch (WebDriverException e){
                noException = false;
            }
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
        System.out.println("<====== The Campaign Name is: " + text + " ======>");
    }

    public void inputTextOnCampaignDescription(String text){
        if(text.equals("random character")){
            CommonMethods cm = new CommonMethods();
            text = cm.getRandomText(5);
        }
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bCampaignDescription).clear();
                driver.findElement(bCampaignDescription).sendKeys(text);
                noException = true;
            }catch (WebDriverException e){
                noException = false;
            }
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
        System.out.println("<====== The Campaign Description is: " + text + " ======>");
    }

    public void inputTextOnEmailSubjectOnCreateScript(String text) {
        if(text.equals("TEST random")){
            CommonMethods cm = new CommonMethods();
            tp.newCreatedEmailSubject = "Test"+" "+cm.getRandomText(5);
        }
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bEmailSubjectOnCreateScript).clear();
                driver.findElement(bEmailSubjectOnCreateScript).sendKeys(tp.newCreatedEmailSubject);
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

    public void inputTextOnEmailBodyContent(String text) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bEmailBodyContent).clear();
                driver.findElement(bEmailBodyContent).sendKeys(text);
                noException = true;
            }catch (WebDriverException e){
                noException = false;
            }
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
        System.out.println("<====== The Email Body Content is: " + text + " ======>");
    }

    public void inputTextOnRecipientOnPreview(String text) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bRecipient).clear();
                driver.findElement(bRecipient).sendKeys(text);
                noException = true;
            }catch (WebDriverException e){
                noException = false;
            }
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
        System.out.println("<====== The Recipient is: " + text + " ======>");
    }

    public void clickOnSaveBtn(){
        clickElementWithException(bSaveBtn);
        clickElementWithException(bOKOnPopUp);
    }

    public String getSelectedOption(){
        return getElementTextWithException(bSelectedOptionUnderEmailTemplates);
    }

    public String getPopupTitle() {
//        JavascriptExecutor js = (JavascriptExecutor)driver;
//        String text = (String) js.executeScript("return arguments[0].text;", driver.findElement(bPreviewPopupTitle));
//        return text;
        return driver.findElement(bPreviewPopupTitle).getText();
    }

    public String getSendTestEmailBtnText() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String text = (String) js.executeScript("return arguments[0].text;", driver.findElement(bSendTestEmail));
        return text;
//        return driver.findElement(bSendTestEmail).getText();
    }

    public boolean isSendTestEmailBtnDisplayed() {
        boolean btnDisplayed;
        try{
            if(driver.findElement(bSendTestEmail).isEnabled()){
                btnDisplayed = true;
            }
            else btnDisplayed = false;
        }catch (WebDriverException e){
            btnDisplayed = false;
        }
        return btnDisplayed;
    }

    public String getEmailBodyContent() {
        driver.switchTo().frame("preview-email-iframe");
        String emailBodyContent = getElementTextWithException(bEmailBodyContentOnPreview);
        driver.switchTo().defaultContent();
        return emailBodyContent;
    }

    public boolean emailTemplateNotEmpty() {
        if (driver.findElements(bEmailTemplatesOptions).size() > 0) {
            return true;
        } else return false;
    }

    public void inputTextOnEmailSubjectOnPreview(String text) {
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
                driver.findElement(bEmailSubjectOnPreview).clear();
                driver.findElement(bEmailSubjectOnPreview).sendKeys(tp.newCreatedEmailSubject);
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

    public boolean verifyHeaderMenuStatus(String menuName, String status) {
        if (status.equals("Enabled")) {
            if (byElementHasClass(bMenuWithStatus(menuName), "activeHeader")) {
                return true;
            } else {
                return false;
            }
        } else {
            if (!byElementHasClass(bMenuWithStatus(menuName), "activeHeader")) {
                return true;
            } else {
                return false;
            }
        }
    }
}
