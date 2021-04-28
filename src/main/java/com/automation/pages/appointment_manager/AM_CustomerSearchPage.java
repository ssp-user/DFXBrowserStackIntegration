package com.automation.pages.appointment_manager;

import com.automation.pages.common.WebPage;
import com.automation.utils.log.LogUtils;
import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AM_CustomerSearchPage extends WebPage {


    private static Logger log = Logger.getLogger(AM_CustomerSearchPage.class);
    //page object


    // **** element import from testng *****

//    @FindBy(css="[class='no-data no-data--al shadow-wrap']")													//shefali::No data populated after search
//    protected static WebElement noCustomer;
//
//    @FindBy(css="[class='whitetxt']")
//    protected static WebElement custName1;
//
//    @FindBy(css="[text()='Client & Vehicle']")
//    protected static WebElement custInfo;
//
//
//    @FindBy(css="[text()='AL 2.0 3-rd Party Integration SSO - Invalid DFX Partner ID Error!']")
//    protected static WebElement custErr;
//
//    @FindBy(xpath="//div[contains(text(),'Enter the search criteria')]")
//    protected static List<WebElement> onlyMandatory;
//
//    @FindBy(xpath="//span[contains(text(),'Invalid DFX Partner ID Error!')]")
//    protected static List<WebElement> inavlidPartnerID;
//
//    @FindBy(xpath="//span[contains(text(),'Expired Security Token Error!')]")
//    protected static List<WebElement> invalidPassword;
//
//    @FindBy(xpath="//span[contains(text(),'Unknown DFX Dealer Id Error!')]")
//    protected static List<WebElement> invalidDealerID;
//
//    @FindBy(xpath="//span[contains(text(),'User Authentication or Authorization Error!')]")
//    protected static List<WebElement> invalidUserID;
//
//    @FindBy(xpath="//div[contains(@data-after,'found')]")
//    protected static List<WebElement> searchPagePhnNotFound;

//    @FindBy(xpath="//div[contains(@data-after,'found')]")
//    protected static List<WebElement> searchPageVINNotFound;
//
//
//    @FindBy(css="div.table-row.ng-scope:first-child .btn-action.new")
//    private static WebElement firstVehicleNewAppt;																// First Vehicle New Appointment
//
//    @FindBy(css="div.result-vin-and-license-plate__info")
//    private static WebElement customerVehicleWithVIN;
//
//    //	@FindBy(css="div.search-output div.text")
//    @FindBy(css="div[ng-show='notFound && !customersSearch.pending'] div.sa-title")
//    private static WebElement customerNotFoundText;																// Customer Not Found Text
//    private static By bCustomerNotFoundText = By.cssSelector("div[ng-show='notFound && !customersSearch.pending'] div.sa-title");
    private static By customerNotFoundLoator = By.cssSelector("div[ng-show='notFound && !customersSearch.pending'] div.sa-title");

//
//    @FindBy(css="label[ng-click*='vin']")
//    private static WebElement searchVinBtn;
    private static By bSearchVinBtn = By.cssSelector("label[ng-click*='vin']");

//    @FindBy(css="label[ng-click*='name']")
//    private static WebElement searchNameBtn;																	// Search Customer By Name
    private static By bSearchNameBtn = By.cssSelector("label[ng-click*='name");

//    @FindBy(css="label[ng-click*='phone']")
//    private static WebElement searchPhoneBtn;
    private static By bSearchPhoneBtn = By.cssSelector("label[ng-click*='phone']");

    //	@FindBy(css="[class='button-el ng-isolate-scope']")															//Add appointment for new customer
//    @FindBy(css="div.search-form__control > button.button-el")
//    protected static WebElement newCustAppointmentBtn;
    private static By bNewCustAppointmentBtn = By.cssSelector("div.search-form__control > button.button-el");
    private static By bNewCustAppointmentBtn1 = By.cssSelector("[ng-click*='newCustomer']");

    //    @FindBy(id="customerFirstName")
//    protected static WebElement custFName;
    private static By bCustFName = By.id("customerFirstName");

    //    //	@FindBy(css="div.table-body.list div.table-row.ng-scope")
//    @FindBy(css="[id*='sa_block']")
//    protected static List<WebElement> customerResults;															// List of Customer Search Result
    private static By bCustomerResults = By.cssSelector("[id*='sa_block']");

    //    @FindBy(id="existingCustomerSearch")
//    protected static WebElement customerSearchField;																// Customer Search Field
    private static By bCustomerSearchField = By.id("existingCustomerSearch");
//    //span[contains(text(),'AL 2.0 3-rd Party Integration SSO - Invalid DFX Partner ID Error!')]
////	@FindBy(css="div.table-body.list div.table-row.ng-scope:first-child")
//    @FindBy(css="[id='sa_block_0']")
//    private static WebElement firstCustomer;																	// First Customer from Search Result
    private static By bFirstCustomer = By.cssSelector("[id='sa_block_0']");

    //    @FindBy(css="#sa_block_0 button.sa__link")
//    private static WebElement newVehicleAppt;
    private static By bNewVehicleAppt = By.cssSelector("#sa_block_0 button.sa__link");

    // Customeer Vehicle (Only for customer has 1 vehicle)
//    @FindBy(css="div.choose-car-detail__info img")
//    private static WebElement customerVehicle;
    private static By bCustomerVehicle = By.cssSelector("div.choose-car-detail__info img");

//    @FindBy(css="div.choose-car")
//    private static List<WebElement> customerVehicles;															// Customer Vehicles (More than 1 vehicles)
     private static By bCustomerVehicles = By.cssSelector("div.choose-car");

    //
//    @FindBy(css=".button-app-plus")
//    private static WebElement addNewAppt;																		// Add New Appt for customer with only 1 vehicle
     private static By bAddNewAppt = By.cssSelector(".button-app-plus");


    //    @FindBy(xpath="//div[@class='vin-and-license-plate ng-binding']")
//    protected static WebElement searchPageVIN;
    private static By bSearchPageVIN = By.xpath("//div[@class='vin-and-license-plate ng-binding']");

//    @FindBy(xpath="//div[@class='phone-info ng-binding ng-scope']")
//    protected static List<WebElement> searchPagePhone;
    private static By bSearchPagePhone = By.xpath("//div[@class='phone-info ng-binding ng-scope']");


    //    @FindBy(css=".car-appointment--plus")
//    private static WebElement addNewApptWithVIN;
    private static By bAddNewApptVIN = By.cssSelector(".car-appointment--plus");
    private static By bNewApptVINIcon = By.cssSelector(".car-appointment--plus div.icon-wrap");

    // ****** end of import from test Ng ********


    //vin number on AppointmentManager Page
    private static By bVINOnLookUp = By.xpath("//div[@class='vin-and-license-plate ng-binding']");

    //search field
    private static By bSearchField = By.xpath("//input[@id='existingCustomerSearch']");

    //VIN button
    private static By bVINButton = By.xpath("//span[text()='VIN']");

    //delete icon on the appointment
    private static By bDeleteIcon = By.xpath("//div[@class='car-appointment car-appointment--booked']//button[contains(@ng-show,'booked')]/*[@class='icon icon--trash']");

    //plus icon
    private static By bPlusIcon = By.xpath("//*[@class='icon icon--btn--plus button-icon']");

    //business icon
    private static By bBusinessIcon = By.xpath("//div[@class='user-column']/div/span");    


    
    private static By bCustomerNo(String no) {
        String locator = String.format("[id='sa_block_%s']", no);
        return By.id(locator);
    }

    public String verifyAMPageTiTle(String pageName){
        changeToPageWithTitle(pageName,3);
        waitForTitle(pageName);
        String pageTitle = driver.getTitle();
        return pageTitle;
    }

    public String getVINOnAMLookup(){
    	sleep(5000);
        String vinOnAM = getElementAttributeWithException(bVINOnLookUp,"innerHTML");
        System.out.println("<====== the current vin on Appointment manager look up is "+vinOnAM+" ======>");
        return vinOnAM;
    }

    public void inputSearch(String searchTerm){
        clearAndInputElementWithException(bSearchField, searchTerm);
    }

    public void clickOnVINButton(){
        clickElementWithException(bVINButton);
    }

    public void clickOnDeleteIconOnAppointment(){
        long startTime = System.currentTimeMillis();
        try{
            sWait.until(conditionVisible(bDeleteIcon)).click();
            System.out.println("<====== The delete icon clicked ======>");
        }catch (WebDriverException e){
            while (isDeleteIconDisplayed()){
                clickElementWithException(bDeleteIcon);
                sleep(1000);
                if((System.currentTimeMillis()-startTime)> 30000){
//                    System.out.println("<====== Not able to click the delete icon, test fail ======>");
                    break;
                }
            }
        }
    }

    public boolean isDeleteIconDisplayed(){
        try{
            driver.findElement(bDeleteIcon).isDisplayed();
            return true;
        }catch (WebDriverException e){
            return false;
        }
    }

    public void clickBtnOnSearch(String buttonName){
        switch (buttonName) {
            case "APPOINTMENT FOR A NEW CUSTOMER":
                actionClick(bNewCustAppointmentBtn);
                break;
            case "PLUS":
                clickElementWithException(bPlusIcon);
                break;
            case "+":
                clickElementWithSeconds(bAddNewAppt,3000);
                break;
            case "+VIN":
                WebElement element = pWait.until(conditionClick(bNewApptVINIcon));
//                jsClick(element);
//                moveToClick(element);
                clickElementWithSeconds(bNewApptVINIcon,28000);
                break;
        }

    }

    public void searchWithOnCustomerPage(String searchField, String searchTerm) {
        clearAndSend(bSearchField, searchTerm);
        switch (searchField) {
            case "VIN":
                clickElementWithSeconds(bSearchVinBtn,3000);
                break;
            case "LICENSE PLATE":
                clickElementWithSeconds(bSearchPhoneBtn,3000);
                break;
            case "NAME":
                clickElementWithSeconds(bSearchNameBtn,3000);
//                customerNotFoundHandler();
                break;
            case "PHONE":
                clickElementWithSeconds(bSearchPhoneBtn,3000);
                break;
        }

    }

    public String getSearchWithCustomerPage(String searchField, String value) {
        String result = "";
        switch (searchField) {
            case "VIN":
                result = pWait.until(conditionVisible(bSearchPageVIN)).getText().trim();
                break;
            case "LICENSE PLATE":
                break;
            case "NAME":
//                customerNotFoundHandler();
                break;
            case "PHONE":
                List<WebElement> ePhones = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bSearchPagePhone));
                int size =  ePhones.size();
                System.out.println(size);
                System.out.println(ePhones);
                if ( size > 0){
                    result = value ;
                    for (int i= 0; i< size;i++) {
                        result = ePhones.get(i).getText().trim();
                        
                        result = result.replace("(", "").replace(")", "");
                        result = result.replace("-", "").replace(" ", "");
                        System.out.println(result.contains(value));
                        if (result.contains(value)) {
                            break;
                        }
                    }
                }
                break;
        }
        return result;

    }

    private void customerNotFoundHandler(){
        int attempt = 0;
        boolean customerFound = false;
        do{
            try{
                pWait.until(condCustomerNotFound(driver.findElements(bCustomerResults), customerNotFoundLoator));
                customerFound = true;
            } catch(TimeoutException e){
                log.warn("Searching for Customers Not Found");
                attempt++;
                driver.findElement(bCustomerSearchField).sendKeys("\n");
                customerFound = false;
            }
        } while(!customerFound && attempt < 5);

        if(attempt >= 5){
            LogUtils.logFailMsgAndTakeScreenshot("Search for Customer", "", "Customer Not Found");
            LogUtils.logError("Customer Not Found");
        }
    }


    public static ExpectedCondition<Boolean> condCustomerNotFound(final List<WebElement> elementListToVerify, final By customerNotFoundLocator){
        return new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(WebDriver driver){
                try{
                    try{
                        if(elementListToVerify.size() >= 1){
                            return true;
                        } else {
                            throw new NoSuchElementException("Customer List Not Found");
                        }
                    } catch (NoSuchElementException e){
                        if(SyncPage.isElementPresentAndVisible(customerNotFoundLocator)){
                            LogUtils.logError("customer Not Found");
                            throw new TimeoutException("Customer Not Found");
                        } else {
                            return null;
                        }

					/*
						if(!isElementPresentAndVisible(driver, pageTitleLocator)){
							return true;									// Page changing.. meaning customer found
						} else if(isElementPresentAndVisible(driver, customerNotFoundLocator)){
							LogUtils.logError("Customer Not Found");		// Page not changing, customer not found displayed
							throw new TimeoutException("Customer Not Found");
						}
						else {
							return null;									// Page not changing, nothing is displaying, meaning system loading, start over the wait method.
						}
						*/
                    }
                } catch(StaleElementReferenceException e){
                    LogUtils.logError("Stale Element Reference Exception Occurred while determining number of customer found. " + e.getMessage());
                    return null;
                } catch(WebDriverException ee){
                    if(ee.getMessage().contains("Customer Not Found")){
                        throw new TimeoutException(ee.getMessage());
                    } else {
                        LogUtils.logError("WebDriverException Occurred but handled. " + ee.getMessage());
                        return null;
                    }
                }
            }
            @Override
            public String toString(){
                return "Time out on condSearchResultFound method";
            }
        };
    }

    public void selectTheNumCustomer(String order ) {
        sleep(1000);
        int num = driver.findElements(bCustomerResults).size();
        if (num == 0){
            try{
                num = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bCustomerResults)).size();
            }catch (TimeoutException te){
                num = 0;
            }
        }
        if (num > 1 ){
            if (order.equals("1")){
                clickElementWithSeconds(bFirstCustomer,3000);
            }else{
                order =String.valueOf(Integer.valueOf(order) -1);
                clickElementWithSeconds(bCustomerNo(order),3000);
            }
        }
        pWait.until(conditionVisible(bNewVehicleAppt));
    }

    public void selectNumCustomerVehicle(int order ) {
        int num = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bCustomerVehicles)).size();
        if (num > 1 ){
            WebElement element = driver.findElements(bCustomerVehicles).get(0);
            clickElementTimesTillDisappear(element,6);
        }else if (num == 1 ){
            clickElementWithSeconds(bAddNewAppt,3000);
        }else{
            System.err.println("Customer Vehicle un clear. ");
        }
    }

    public void clickBtnOrSelectCustomerVehicle(String btnNsme, int order ) {
        pWait.until(SyncPage.condPageLoaded(bCustomerVehicle, bCustomerVehicles));
        List<WebElement> elements = driver.findElements(bCustomerVehicles);
         if (elements.size() > 1 ){
             WebElement  element = elements.get(order-1);
            clickElementTimesTillDisappear(element,6);
        }else if (elements.size() == 1 ){
            clickBtnOnSearch(btnNsme);
        }else{
            System.err.println("Customer Vehicle un clear. ");
        }
    }
    
    public void verifyElementExists(String field){
        switch (field) {
        case "Business Icon":
            dWait.until(conditionVisible(bBusinessIcon));         	
        	CommonMethods.verifyElementExists(driver.findElement(bBusinessIcon));
            break;
        default:
        	break;    	
        }    
    }
    



}
