package com.automation.pages.advisor_checkin;

import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;


public class ACI_SearchPage extends ACI_FramePage {

    public void AdvisorCheckInSearchPage(){
        setWait();
    }

    private static Logger log = Logger.getLogger(ACI_SearchPage.class);

    private static final By bBodyPage = By.tagName("body");

    //create new customer after decode the vin
//    private static final By bCreateNewCustomer = By.xpath("//span[contains(text(),'CREATE NEW CUSTOMER')]");
    private static final By bCreateNewCustomer = By.cssSelector("div.in a.createNewCustomer");

    //Advisor check in version number
    private static final By bACVersion = By.xpath("//div[@class='app-version']");
    //odometer field
    private static final By bOdometer = By.xpath("//input[@title='odometer']");
    //vin input field
    private static final By bVINField = By.id("vehicle_vin_input");
    //VIN Decode field
    private static final By bVINDecodeField = By.xpath("//input[@placeholder='VIN']");
    //customer name input field
    private static final By bCustomerNameField = By.xpath("//input[@ng-model='customer_name']");
    //customer number input field
    private static final By bCustomerNumberField = By.xpath("//input[@ng-model='customer_phone']");
    //plate number input field
    private static final By bPlateNumberField = By.id("vehicle_license_plate_input");

    //search icon with field
    private static By bIconWithField(String fieldName){
        String locator= "//input[@title='"+fieldName+"']/../button";
        return By.xpath(locator);
    }

    //search result
//    private static final By bSearchResult = By.xpath("//div[@class='row-fluid']/span");
    private static final By bSearchResult = By.xpath("//div[contains(@class,'table-row ng-scope ng-isolate-scope')]");
    private static final By bSearchResultCustomerNameList = By.xpath("//div[contains(@class,'table-row ng-scope ng-isolate-scope')]/div/span[1]");

//    //search result with order
//    private static By bSearchResultWithOrder1 (String order){
//        String locator = "";
//        String node = "";
//        order = order.toLowerCase();
//        switch (order){
//            case "first":
//                node = "1";
//                break;
//        }
//        if (order.contains("th")){
//            node = order.replace("th","");
//        }
////        locator = "(//div[@class='row-fluid']/span)["+node+"]";
//        locator = "(//div[contains(@class,'table-row ng-scope ng-isolate-scope')])["+node+"]";
//        return By.xpath(locator);
//    }

    private static By bSearchResultWithOrder (String order){
        String  locator = "(//div[contains(@class,'table-row ng-scope ng-isolate-scope')])["+order+"]";
        return By.xpath(locator);
    }

    //decode vin button
    private static final By bVINDecode = By.id("decodevin");
    private static final By bDecodeBtn = By.xpath("//button[@class='btn btn-green']");

    //service tab on the header
    public static final By bHeaderService = By.xpath("//a[contains(@id,'header-services-tab-button')]");
    private static final By bFirstCustomerImg = By.cssSelector("div.search-results div.scrollcontent>div:nth-child(1) img");
    private static final By bSecondCustomerImg = By.cssSelector("div.search-results div.scrollcontent>div:nth-child(2) img");
    private static final By bThirdCustomerImg = By.cssSelector("div.search-results div.scrollcontent>div:nth-child(3) img");

//    @FindBy(css="div.search-results div.scrollcontent>div:first-child")
//    private static WebElement firstCustomer;																											// First Customer from the list
    private static final By bFirstCustomerLocator = By.cssSelector("div.search-results div.scrollcontent>div:nth-child(1)");

//    private static WebElement secondCustomer;																											// Second Customer from the list
    private static final By bSecondCustomerLocator = By.cssSelector("div.search-results div.scrollcontent>div:nth-child(2)");

    //	@FindBy(css=".btn.btn-black.dmsNext")
//    @FindBy(css=".btn.btn-black")
    private static final By bNextBtn = By.cssSelector(".btn.btn-black");
//    private static WebElement nextBtn;																													// Next Button

    private static final By bCustomerResults = By.cssSelector("div.scrollcontent>div[ng-repeat='one in search_results']>div");
//    private static List<WebElement> customerResults;																									// Customer Search Results, index starts from 1

    private static final By bCustomerResultVINList = By.cssSelector("div[ng-repeat*='search_results'] span:nth-child(4)");
//    private static List<WebElement> customerResultVINList;

    //    //spin load
//    private static final By bSpinnerLoad = By.xpath("//i[@class='spinner']");

//    @FindBy(id="search-toggle")
//    private static WebElement customerSearchTitle;																										// Customer Search Title
    private static By customerSearchTitleLocator = By.id("search-toggle");

    public void waitForLoadingCompletedOnAdvisorCheckIn(){
        waitForElementHasAttributeValue(bSpinner, "style","display: none; height: 961px;");
    }

    public void clearAllSearchField(){
        clearAndInputElementWithException(bCustomerNameField,"");
        clearAndInputElementWithException(bCustomerNumberField, "");
    }

    public void searchCustomerName(String customerName){
        clearAndInputElementWithException(bCustomerNameField, customerName);
    }

    public void searchCustomerNameClick(String customerName){
        clearAndInputElementWithException(bCustomerNameField, customerName);
//        driver.findElement(bCustomerNameField).sendKeys("\n");
    }

    public void searchCustomerNumber(String customerNumber){
        clearAndInputElementWithException(bCustomerNumberField, customerNumber);
    }

    public void searchPlateNumber(String plateNumber){
        clearAndInputElementWithException(bPlateNumberField, plateNumber);
    }

    public void clickOnSearchIconWithField(String fieldName){
        clickElementWithException(bIconWithField(fieldName));
    }

    public void clickOnRightArrowWithField(String fieldName){
        clickElementWithException(bIconWithField(fieldName));
    }

    public void clickSearchResultWithOrder(String order){
        clickElementWithException(bSearchResultWithOrder(order));
    }

//    public void selectSearchResultWithOrder(String order){
//        String url = driver.getCurrentUrl().toLowerCase();
//        if (url.contains("test2nissanad") || url.contains("*****") ) {
//            order = "1";
//        }else {
//            order = "2";
//        }
//        clickElementWithException(bSearchResultWithOrder(order));
//    }

//    public boolean isCustomerNameMatch(String customerName){
//        long currentTime = System.currentTimeMillis();
//        while(!(getElementAttributeWithException(bSearchResult,"innerHTML").length()>0)) {
//            sleep(1000);// wait for search result to return text
//            if ((System.currentTimeMillis() - currentTime) > 10000) {
//                break;
//            }
//        }
//        if(getElementAttributeWithException(bSearchResult,"innerHTML").toLowerCase().contains(customerName.toLowerCase())){
//            System.out.println("<====== The search result contains '"+customerName+"' ======>");
//            return true;
//        }else{
//            System.out.println("<====== The search result is "+getElementAttributeWithException(bSearchResult,"innerHTML")+", no match, test fail ======>");
//            return false;
//        }
//    }

    public boolean hasCustomerNameInSearch(String customerName){
        List<WebElement> emList = dWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bSearchResultCustomerNameList));
        int iSize = emList.size();
        int i= 0;
        while (i < iSize) {
            if (!emList.get(i).getText().toLowerCase().contains(customerName.toLowerCase())) {
                break;
            }else{
                i++;
            }
        }
        if (i==iSize){
            return true;
        }else{
            return false;
        }
    }

    public boolean isACVersionNoMatch(String expectedVersionNo){
        long startTime = System.currentTimeMillis();
        String actualVersionNo = "";
        while(!actualVersionNo.equals(expectedVersionNo)){
            actualVersionNo = driver.findElement(bACVersion).getAttribute("innerHTML");
            sleep(500);
            if((System.currentTimeMillis()-startTime)>10000){
                break;
            }
        }
        if(actualVersionNo.equals(expectedVersionNo)){
            System.out.println("<====== AC version number '"+actualVersionNo+"' matches ======>");
            return true;
        }else{
            System.out.println("<====== AC version number '"+actualVersionNo+"' not match expected version number '"+expectedVersionNo+"' ======>");
            return false;
        }
    }

    public boolean isBodyPageContains(String classValue){																													// Check if a VIN is new Vehicle or existing Vehicle
//        waitForLoadingCompletedOnAdvisorCheckIn();
        waitSpinnerInvisible();
        return webElementHasClass(bBodyPage, classValue);
    }

    public void decodeVIN(String text) {
        long startTime = System.currentTimeMillis();
        clearAndInputElementWithException(bVINDecodeField, text);
        clickElementWithException(bDecodeBtn);
        //use to click create new customer if new vehicle pop up show
        while(!isOdometerFieldDisplayed()){
            clickOnCreateNewCustomer();
            sleep(1000);
            if((System.currentTimeMillis()-startTime)>20000){
                break;
            }
        }
    }

    public void searchCustomerByVIN(String text) {
        clearAndInputElementWithException(bVINField,text);
        clickElementWithException(bVINDecode);
        //use to click create new customer if new vehicle pop up show
        if (!handleNewCustomerBtn()){
//            handleMoreThenOneCustomer();
            if(isMoreThenOneCustomer()){
                selectVehicle_ByVIN(text);
                clickNextBtnOnAdvisorFrame();
            }
        }
    }

    public boolean handleNewCustomerBtn() {
         boolean result = true;
        //use to click create new customer if new vehicle pop up show
        try {
            WebElement elemnt = pWait.until(conditionClick(bCreateNewCustomer));
            jsClick(elemnt);
//            elemnt.click();
        }catch(TimeoutException te){
            result = false;
        }
        return result;
    }

    public boolean isMoreThenOneCustomer() {
        boolean result = true;
        try {
            sWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(bCustomerResults,1));
//            driver.findElements(bCustomerResultVINList).get(0).click();
        }catch(TimeoutException te){
            result = false;
        }
        return result;
    }

    public boolean hasMoreThenOneCustomer() {
        boolean result = true;
        try {
            List<WebElement> elementList = driver.findElements(bCustomerResults);
            result = sWait.until(SyncPage.condSearchResultFound(driver,elementList,customerSearchTitleLocator));
        }catch(TimeoutException te){
            result = false;
        }
        return result;
    }

//    public void handleMoreThenOneCustomer() {
//        long startTime = System.currentTimeMillis();
//        try {
//            pWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(bCustomerResults,1));
//            driver.findElements(bCustomerResultVINList).get(0).click();
//            clickNextBtnOnAdvisorFrame();
//        }catch(TimeoutException te){
//            //
//        }
//    }

    private  void selectVehicle_ByVIN(String vin){																								// Select Vehicle based on provided VIN
        String vehicle = null;
        List<WebElement>  elements = driver.findElements(bCustomerResultVINList);
        for(int i = 0; i < elements.size(); i++){
            vehicle = elements.get(i).getText().trim();
            if(vehicle.equalsIgnoreCase(vin)){
                elements.get(i).click();
                break;
            }
        }
    }

    public boolean isOdometerFieldDisplayed(){
        try{
            driver.findElement(bOdometer).isDisplayed();
            return true;
        }catch (WebDriverException e){
            return false;
        }
    }

    public void clickOnCreateNewCustomer(){
        try{
            driver.findElement(bCreateNewCustomer).click();
            System.out.println("<====== Create new customer button clicked ======>");
        }catch (WebDriverException e){
            //this customer is existing
        }
    }

    public void clickServicesOnHeader1(){
        clickElementWithException(bHeaderService);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(90, SECONDS)
                .pollingEvery(200, MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        dWait.pollingEvery(200,MILLISECONDS).ignoring(StaleElementReferenceException.class).until(conditionClick(bHeaderService)).click();
    }



}


