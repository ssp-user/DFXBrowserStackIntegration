package com.automation.pages.customer_connect;

import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

public class CC_ScriptsPage extends WebPage {

    CC_TasksPage tPage = new CC_TasksPage();

    //locator
    //campaign type drop down
    private static By bCampaignTypeDropDown = By.xpath("//select[@name='campaignTypeInput']");

    //script type drop down
    private static By bScriptTypeDropDown = By.xpath("//select[@name='ScriptTypeInput']");

    //insert script drop down
    private static By bScriptInsertDropDown = By.xpath("//select[@ng-model='scriptVariable']");

    //new script button
    private static By bNewScriptButton = By.xpath("//div[contains(@class,'configBlueButton') and contains(text(),'New Script')]");

    //new create button on Script page
    private static By bCreateButtonScript = By.xpath("//*[contains(@class,'configGreenButton') and contains(text(),'Create')]");

    //Unsave pop msg on script page
    private static By bUnsavedMsg = By.xpath("//div[contains(text(),'There are some unsaved content on the page. Would you like to leave without saving?')]");

    //Yes button on the pop up
    private static By bYesPopup = By.xpath("//button[contains(text(),'Yes')]");

    //OK on popup on script page
    private static By bOKOnPopUpOnScript = By.xpath("//button[@class='alertMsgBoxMainButton ng-binding' and contains(text(),'OK')]");

    //Delete icon on script page
    private static By bDeleteIconScript = By.xpath("//button[contains(@class,'configSvgDelete')]");

    //script name field on script page
    private static By bScriptNameField = By.xpath("//input[contains(@class,'scriptTextInput')]");

    //error message
    private static By bErrorMsg(String errorMsg){
        String checkLocator = "//div[contains(@class,'ng-binding') and contains(text(),'"+errorMsg+"')]";
        return By.xpath(checkLocator);
    }

    //script record
    private static By bScriptRecord(String errorMsg){
        String checkLocator = "//span[contains(@ng-class,'callScript.Id') and contains(text(),'"+errorMsg+"')]";
        return By.xpath(checkLocator);
    }

    //script content
    private static By bScriptContentText = By.xpath("//textarea[@id='scriptText']");

    public String scriptName;
    public String scriptContentBefore;
    public String scriptContentAfter;

    public void selectValueFromCampaignDropDownOnScripts(String dropDownValue){
        waitForElementWithException(bCampaignTypeDropDown);
        try{
            selectDropList(bCampaignTypeDropDown,dropDownValue);
        }catch (WebDriverException e){
            pageRefresh();
            selectDropListElementWithException(bCampaignTypeDropDown,dropDownValue);
        }
    }

    public void selectValueFromScriptTypeDropDownOnScripts(String dropDownValue){
        selectDropListElementWithException(bScriptTypeDropDown,dropDownValue);
    }

    public void clickOnNewScript(){
        clickElementWithException(bNewScriptButton);
        /*waitForElementVisibleWithException(bCreateButtonScript);
        try{
            driver.findElement(bCreateButtonScript).isDisplayed();
        }catch(WebDriverException e){
            System.out.println("<====== the green create button is not visible on the page, page will refresh ======>");
            pageRefresh();
        }*/
    }

    public void clickOnCreateOnScript(){
        clickElementWithException(bCreateButtonScript);
    }

    public boolean verifyIfErrorMsgExisted(String errorMsg){
        waitForElementVisibleWithException(bErrorMsg(errorMsg));
        if(verifyIfElementShowOnThePage(bErrorMsg(errorMsg))){
            System.out.println("<====== Error message '"+errorMsg+"' shows on page ======>");
            return true;
        }else{
            return false;
        }
    }

    public void clickOnOKOnPopUpOnScript(){
        clickElementWithException(bOKOnPopUpOnScript);
    }

    public void setScriptName(){
        CommonMethods cm = new CommonMethods();
        cm.getRandomText(10);
        scriptName = tp.randomText;
        clearAndInputElementWithException(bScriptNameField, scriptName);
    }

    public void selectValueFromInsertScriptVariable(String dropDownOption){
        selectDropListElementWithException(bScriptInsertDropDown, dropDownOption);
    }

    public void getScriptContentBefore(){
        scriptContentBefore = jsGetAttribute(bScriptContentText,"value");
    }

    public void getScriptConentAfter(){
        scriptContentAfter = jsGetAttribute(bScriptContentText,"value");
    }

    public boolean verifyIfNewScriptCreated(){
        waitForElementVisibleWithException(bScriptRecord(scriptName));
        if(verifyIfElementShowOnThePage(bScriptRecord(scriptName))){
            System.out.println("<====== New script '"+scriptName+"' created ======>");
            return true;
        }else{
            return false;
        }
    }

    public boolean verifyIfScriptRecordDeleted(){
        tPage.waitForLoadingCircleToDisappearCC();
        if(verifyIfElementNotShowOnThePage(bScriptRecord(scriptName))){
            System.out.println("<====== New script '"+scriptName+"' deleted ======>");
            return true;
        }else{
            return false;
        }
    }

    public void clickScriptRecord(){
        clickElementWithException(bScriptRecord(scriptName));
    }

    public void clickDeleteOnScript(){
        try{
            driver.findElement(bUnsavedMsg);
            dWait.until(conditionClick(bYesPopup)).click();
        }catch(WebDriverException ex){
            //is to prevent unsaved message to pop up and block the page
        }
        dWait.until(conditionClick(bDeleteIconScript)).click();
        tPage.waitForLoadingCircleToDisappearCC();
    }

    public void inputContentInScript(String content){
        clearAndInputElementWithException(bScriptContentText,content);
    }
}
