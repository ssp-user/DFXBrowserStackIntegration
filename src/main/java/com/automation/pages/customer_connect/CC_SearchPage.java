package com.automation.pages.customer_connect;

import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CC_SearchPage extends WebPage {

	public static String customerName;
	
    //page object

    public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	//Email button
    private static By bEmailBtn = By.xpath("//div[@ng-click='changeSearchParam(type.id)' and contains(text(), 'Email')]/parent::div");

    //Phone button
    private static By bPhoneBtn = By.xpath("//div[@ng-click='changeSearchParam(type.id)' and contains(text(), 'Phone')]/parent::div");

    //> button
    private static By bRightArrowBtn = By.xpath("//span[@class='glyphicon glyphicon-menu-right ng-scope']");
    
    private static By bAddTaskBtn = By.xpath("//div[@ng-click='createInboundTask(customer)']");  
    
    private static By bCancelBtn = By.xpath("//div[@ng-click='closeTaskDetails()' and text() = 'Cancel']");     

    //task tab on the header
    private static By bTasksTab = By.xpath("//li[@class='headerLink']/a/span[text()='TASKS']");

    //search field on the search page
    private static By bSearchField = By.id("inputSearch");

    //search button name
    private static By bSearchButtonName(String buttonName){
        String checkLocator = "//div[@ng-repeat='type in searchTypes']/div[contains(text(),'"+buttonName+"')]";
        return By.xpath(checkLocator);
    }

    //Name button
    private static By bNameBtn = By.xpath("//div[@ng-repeat='type in searchTypes']/div[contains(text(),'Name')]");

    private static By bSearchResult(String searchWord){
        String checkLocator = "//td[@class='searchTableCell ng-binding'][contains(text(),'"+searchWord+"')]";
        return By.xpath(checkLocator);
    }

    private static By bSearchResultName = By.xpath("//td[@class='searchTableCell ng-binding'][1]");

    private static By bSearchResultVIN = By.xpath("//td[@class='searchTableCell ng-binding'][3]/following-sibling::td[1]/div[2]");

    private static By bInfoIcons = By.xpath("//div[@class='detail-maxWidth taskDetailRecallInfoIcon']");

    private static By bCampaigns = By.xpath("//div[@class='taskTileCampaignName ng-binding']");

    private static By bServiceHistoryWindow = By.xpath("//div[@id='serviceHistoryDetails-form']/h1");

    private static By bVINOnServiceHistoryWindow = By.xpath("//label[contains(text(), 'Vehicle Details')]/following-sibling::label[2]");

    private static By bSearchResultForVIN(String searchWord){
        String checkLocator = "//td[@class='searchTableCell']/div[contains(text(),'"+searchWord+"')]";
        return By.xpath(checkLocator);
    }

    private static By bModifyIconAssociatedWithName(String customerName){
        String checkLocator = "//td[contains(text(),'"+customerName+"')]/..//*[contains(@ng-href,'icon--edit')]";
        return By.xpath(checkLocator);
    }

//    private static By bCloseButtonOnMessagePopup = By.xpath("//div[@id='alertMsgBox']//button[contains(@class,'alertMsgClose')]");
    private static By bCloseButtonOnMessagePopup = By.xpath("//div[@id='alertMsgBox']//button");

    //SERVICE HISTORY tab on expended view
    private static By bServiceHistoryTab = By.xpath("//div[contains(text(), 'Service History')]");

    //TASKS tab on expended view
    private static By bTasksTabOnExpendedView = By.xpath("//div[contains(text(), 'Tasks')]");

    //note button on the search page
//    private static By bFirstNoteOnSearch = By.xpath("//div[contains(@ng-click,'openCustomerNote') and not(contains(@class,'ng-hide'))]");
    private static WebElement bNoteOnSearchWithName(String customerName) {
        String checkLocator = "//td[@class='searchTableCell ng-binding'][contains(text(),'" + customerName + "')]//following::div[contains(@ng-click,'openCustomerNote') and not(contains(@class,'ng-hide'))]";
        List<WebElement> notes = driver.findElements(By.xpath(checkLocator));
        return notes.get(0);
    }

    //Sticky notes text field
    private static By bStickyNote = By.xpath("//div[contains(@id,'PIApostit') and contains(@style,'display: block;')]//div[contains(@id,'pia_editable')]");

    //close icon on the sticky notes
    private static By bCloseOnNote = By.xpath("//div[contains(@id,'PIApostit') and contains(@style,'display: block;')]//*[@ng-click='saveNote()']");

    //customer name on Search page with search result
    private static By bCustomerNameResultContains(String searchWord){
        String locator = "//td[contains(text(), '" + searchWord + "')]";
        return By.xpath(locator);
    }

    private static By bAddressLine(String customerName){
        String checkLocator = "//td[contains(text(),'"+customerName+"')]/following-sibling::td[3]";
        return By.xpath(checkLocator);
    }

    private static By bCustomerNotesTab(String customerName) {
        String checkLocator = "//td[contains(text(), '" + customerName + "')]//ancestor::tbody//following-sibling::div[contains(text(), 'Customer Notes')]";
        return By.xpath(checkLocator);
    }

    private static By bCellPhone(String customerName){
        String checkLocator = "//td[contains(text(),'"+customerName+"')]/following-sibling::td[2]/div[2]";
        return By.xpath(checkLocator);
    }

    private static By bCustomerNameOnSearch = By.xpath("//tbody[contains(@ng-repeat,'customer in customers')]/tr/td[3]");

    public void searchKeyWord(String searchWord){
    	sleep(5000);
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bSearchField).click();
                noException = true;
            }catch (WebDriverException e) {
                noException = false;
                try{
                    driver.findElement(bCloseButtonOnMessagePopup).click();
                }catch (WebDriverException ex){
                    //the popup is not there
                }
                sleep(200);
            }
            if((System.currentTimeMillis()-startTime) > 10000){
                break;
            }
        }
        clearAndInputElementWithException(bSearchField, searchWord);
    }

    public void clickOnSearchButton(String buttonName){
        long startTime = System.currentTimeMillis();
        dWait.until(conditionVisible(bSearchButtonName(buttonName))).click();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bSearchField).click();
                noException = true;
            }catch (WebDriverException e) {
                noException = false;
                try{
                    driver.findElement(bCloseButtonOnMessagePopup).click();
                }catch (WebDriverException ex){
                    //the popup is not there
                }
                sleep(200);
            }
            if((System.currentTimeMillis()-startTime)> 40000){
                break;
            }
        }
    }

    public String searchResult(String searchWord, String fieldName){
        String result = "";
        switch(fieldName){
            case "Name":
                result = getElementTextWithException(bSearchResultName);
                break;
            case "Phone":
                result = getElementTextWithException(bSearchResultForVIN(searchWord));
                break;
            case "Email":
                result = getElementTextWithException(bSearchResult(searchWord));
                break;
            case "Vehicle":
                result = getElementTextWithException(bSearchResultForVIN(searchWord));
                break;
        }
        return result;
    }

    public void clickOnModifyAssociatedWithName(String customerName){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bModifyIconAssociatedWithName(customerName)).click();
                noException = true;
            }catch (WebDriverException e) {
                noException = false;
                try{
                    driver.findElement(bCloseButtonOnMessagePopup).click();
                }catch (WebDriverException ex){
                    //the popup is not there
                }
                sleep(200);
            }
            if((System.currentTimeMillis()-startTime)> 40000){
                break;
            }
        }
    }

    public String getHomeIconCustomerDetailsSearch(String customerName){
        String address = getElementTextWithException(bAddressLine(customerName));
        return address;
    }

    public String getCellPhoneIconCustomerDetailsSearch(String customerName){
        String phoneNumber = getElementTextWithException(bCellPhone(customerName));
        return phoneNumber;
    }

    public String getCustomerNameOnSearch(){
        String result = getElementTextWithException(bCustomerNameOnSearch);
        int index = result.indexOf("DFX");
        tp.customerNameOnSearch = result.substring(0, index).trim();
        System.out.println("<====== The customer name on the search page is "+tp.customerNameOnSearch+" ======>");
        return tp.customerNameOnSearch;
    }

    private static By bNoteOnSearch = By.xpath("//div[@class='customer-note-button commandButtonNoHover scheduleButton commandButton customer-note-button-on-search']");

    public void clickOnNoteButton(){
//        driver.findElement(bNoteOnSearchWithName(tp.customerNameOnSearch)).click();
//        clickElementWithException(bNoteOnSearchWithName(tp.customerNameOnSearch));

        driver.findElement(bNoteOnSearch).click();

//        long startTime = System.currentTimeMillis();
//        boolean noException = false;
//        while(!noException){
//            try{
//                driver.findElement(bFirstNoteOnSearch).click();
//                noException = true;
//            }catch (WebDriverException e){
//                noException = false;
//                sleep(1000);
//            }
//            if((System.currentTimeMillis()-startTime)> 60000){
//                break;
//            }
//        }
    }

    //input text on sticky notes
    public void inputTextOnNotes(String text){
        if(text.equals("Test random")){
            CommonMethods cm = new CommonMethods();
            text = "Test"+" "+cm.getRandomText(5);
        }
        tp.customerNoteTxt = text;
        clearAndInputElementWithException(bStickyNote, text);
        System.out.println("<====== The text on the sticky notes on Search page is "+text+" ======>");
    }

    //close icon on the sticky note
    public void clickOnCloseOnNotes(){
        clickElementWithException(bCloseOnNote);
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

    public void clickTaskTabOnSearchPage(){
        clickElementWithException(bTasksTab);
    }

    public void clickOnName() {
        dWait.until(conditionVisible(bNameBtn)).click();
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                sWait.until(conditionVisible(bCloseButtonOnMessagePopup)).click();
                noException = true;
            } catch (WebDriverException ex) {
                noException = false;
                //the popup is not there
                sleep(200);
            }
            if ((System.currentTimeMillis() - startTime) > 10000) {
                break;
            }
        }
    }

    //click on customer with the name
    public void clickCustomerWithNameOnSearch(){
        String customerName = tp.customerNameOnSearch;
        clickElementWithException(bCustomerNameResultContains(customerName));
        System.out.println("<====== Customer '"+customerName+"' clicked ======>");
    }

    public void clickOnCustomerNotesTabWithName(){
        String customerName = tp.customerNameOnSearch;
        clickElementWithException(bCustomerNotesTab(customerName));
        System.out.println("<====== CUSTOMER NOTES tab under '"+ customerName +"' clicked ======>");
    }

    public void clickEmailBtn() {clickElementWithException(bEmailBtn);}

    public void clickPhoneBtn() {clickElementWithException(bPhoneBtn);}

    public void clickRightArrowBtn() {clickElementWithException(bRightArrowBtn);}
    
    public void clickAddTaskBtn() {clickElementWithException(bAddTaskBtn);}    
    
    public void clickCancelBtn() {
    	clickElementWithException(bCancelBtn);
    	lWait.until(ExpectedConditions.visibilityOfElementLocated(bAddTaskBtn));    	
    }      

    public String getVINFromResult() {return getElementTextWithException(bSearchResultVIN);}

    public void clickServiceHistoryTab() {clickElementWithException(bServiceHistoryTab);}

    public void clickTasksTab() {clickElementWithException(bTasksTabOnExpendedView);}

    public void clickInfoIcon(int index) {
        waitForElementVisibleWithException(bInfoIcons);
        driver.findElements(bInfoIcons).get(index).click();
    }

    public void clickCampaign(int index) {
        waitForElementVisibleWithException(bCampaigns);
        driver.findElements(bCampaigns).get(index).click();
    }

    public boolean isServiceHistoryWindowDisplayed() {
        sleep(1000);
        try{
            if(driver.findElement(bServiceHistoryWindow).isDisplayed()){
            return true;
            }
            return false;
        }catch (WebDriverException e){
            return false;
        }
    }

    public String getVINFromServiceHistoryWindow() {return getElementTextWithException(bVINOnServiceHistoryWindow);}

    public void validateVIN(String VIN) {
    	lWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vehicleDetailDiv leftFlex rightFlex']/label[text()='"+VIN+"']")));
    	CommonMethods.verifyElementExists(driver.findElement(By.xpath("//div[@class='vehicleDetailDiv leftFlex rightFlex']/label[text()='"+VIN+"']")));
    }    
    
    public void captureCustomerName() {
    	lWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//td[@class='searchTableCell ng-binding'])[1]")));
    	String returnedCustomerName = driver.findElement(By.xpath("(//td[@class='searchTableCell ng-binding'])[1]")).getText().toString().trim();
    	String[] lineSplitted = returnedCustomerName.split("\n");
    	System.out.println("Length of array "+ lineSplitted.length);
    	for (int i = 0; i < lineSplitted.length; i++) {
			System.out.println(lineSplitted[i]);
		}
    	setCustomerName(lineSplitted[0].trim());
    	System.out.println("*************Captured customer name is ****************** "+ getCustomerName());
    }    
}
