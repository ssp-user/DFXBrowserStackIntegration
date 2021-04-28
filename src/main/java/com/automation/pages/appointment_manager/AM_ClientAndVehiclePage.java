package com.automation.pages.appointment_manager;

import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


import java.util.List;

public class AM_ClientAndVehiclePage extends AM_FramePage {

    private static Logger log = Logger.getLogger(AM_ClientAndVehiclePage.class);


    //  ****** import element from test NG ****

//
    private static By emailCheckBox = By.xpath("(//*[@class='icon icon--btn--check checkbox__icon'])[4]");
//
//    @FindBy(id="default-phone-1500981450")
//    protected static WebElement defaultPhone;
//
//    @FindBy(id="customer-e-mail-1500981682")
//    private static WebElement emailField;																					// Email Text Field
//
//    @FindBy(xpath="//input[@id='customer-street-1500982103']")
//    protected static WebElement street;
//
//    @FindBy(xpath="//input[@id='customer-city-1500982136']")
//    protected static WebElement city;
//
//    @FindBy(xpath="//input[@id='customer-zip-1500982189']")
//    protected static WebElement zip;
//    // Odometer Input Field
//
//    @FindBy(xpath="//input[@id='vehicle-odometer-1500984044']")
//    private static WebElement odometerField;
//
//    @FindBy(xpath="//label[contains(text(),'Odometer, mi')]")
//    private static WebElement odometerText;
//
//    @FindBy(css="div.alert-message.alert-danger.ng-scope p")
//    private static WebElement odometerMandatoryMsg;																			// Odometer Mandatory Msg
//
//    @FindBy(xpath="//div[@name='manufacture']")
//    protected static WebElement make11;
//
//    @FindAll(@FindBy(xpath="//div[@name='manufacture']/div[2]/div"))
//    protected static List<WebElement> makeOptions1;

//    @FindBy(css="[class='btn change-customer-ownership_select_btn-show-customers']")
//    protected static WebElement sameCustBtn;
//
//    @FindBy(css="[class='btn change-customer-ownership_btn-replace-customers']")
//    protected static WebElement replaceCustBtn;
//
//    // Next Button
//
//    @FindBy(xpath="//button[@id='next-1500972347']")
//    private static WebElement nextBtn;
//
//    @FindBy(xpath="//div[@class='flex-row justify-content--center']")
//    protected static WebElement noTransferLayer;
//
//    @FindBy(xpath="//li[@class='services-type-tabs__unscheduled']")
//    protected static WebElement unscheduledServiceTab1;
//
//    @FindBy(xpath="//div[@class='btn change-customer-ownership_btn-close pull-right']")
//    protected static WebElement clkCloseSameVinLayer;
//
//    @FindBy(css="section > div > div > div.loader_message")
//    private static WebElement loadingMsg;
//    private static By loadingMsgLocator = By.cssSelector(".loader_message");
//
//    @FindBy(css="#vin-number")
//    private static WebElement vin1;																							// VIN
//
//    @FindBy(xpath="//input[@id='decode-vin-field-1500982728']")																//Change vehicle vin
//    private static WebElement vinChangeVehicle;
//
//    @FindBy(xpath="//div[@class='c-vehicle-info-editable__header__inner c-vehicle-info-editable__edit']//input[@class='form-element ng-untouched ng-pristine ng-valid']")																//Change vehicle vin
//    private static WebElement vin;
//
//    @FindBy(xpath="//button[@id='use-matched-customer-1500985815']")																//Change vehicle vin
//    private static WebElement useMatchedCust;
//
//    @FindBy(xpath="//button[@id='decode-vin-1500982807']")																	// Change vehicle decode
//    private static WebElement decodeBtnChangeVehicle;
//
//
//    //	@FindBy(css="div.select-driver select")
//    @FindBy(css="div.select-driver [class*='input']")
//    private static WebElement vehicleType;																					// Vehicle Type 2WD or 4WD
//
//    private static By loadingDuplicate = By.cssSelector("section.c-mono-stat:nth-child(2)  div.c-mono-stat__counter-inner");
//
//    @FindBy(xpath="//customer-vehicle-component[@class='c-vehicle-info-editable c-vehicle-info-editable--is-loading']")
//    protected static WebElement vehicleInfoLoader;

    //	@FindBy(xpath="//div[@class='c-history-table__item']")
//    @FindBy(xpath="//div[@class='c-vehicle-info-editable__history__recalls']//div[@class='c-history-table__item']")
//    protected static List<WebElement> recalllist;
    private static By bRecalllist = By.xpath("//div[@class='c-vehicle-info-editable__history__recalls']//div[@class='c-history-table__item']");

//    @FindBy(xpath="//button[@id='recalls-n-tab-1500984564']")
//    protected static WebElement recallCount;
    private static By bRecallCount = By.id("recalls-n-tab-1500984564");



//    private static By bMakeBy = By.xpath("//div[@id='vehicle-manufacturer-1500983788']");
    private static By bMake = By.id("vehicle-manufacturer-1500983788");

    //
//    @FindAll(@FindBy(xpath="//div[@id='vehicle-manufacturer-1500983788']/div[2]/div"))
//    protected static List<WebElement> makeOptions;
    private static By bMakeOptions = By.xpath("//div[@id='vehicle-manufacturer-1500983788']/div[2]/div");


    //        @FindBy(xpath="//div[@class='c-vehicle-info-editable__other-owner']//div[contains(text(),'belongs to another customer')]")
//    protected static WebElement duplicateCust;
    private static By bDuplicateCust = By.xpath("//div[@class='c-vehicle-info-editable__other-owner']//div[contains(text(),'belongs to another customer')]");

    //    @FindBy(xpath="//button[@id='use-matched-customer-1500985815']")
//    protected static WebElement clkUsedMatchVinBtn;
//     protected static By bUsedMatchVinBtn = By.xpath("//button[@id='use-matched-customer-1500985815']");
    private static By bUsedMatchVinBtn = By.id("use-matched-customer-1500985815");

    //    @FindBy(xpath="//button[@id='change-owner-1500986094']")
//    protected static WebElement clkChangeOwnerBtn;
    private static By bChangeOwnerBtn = By.xpath("//button[@id='change-owner-1500986094']");

    //    //	@FindBy(xpath="//div[@id='vehicle-year-1500983849']")
//    @FindBy(id="vehicle-year-1500983849")
//    protected static WebElement year;
//    protected static By yearBy = By.xpath("//div[@id='vehicle-year-1500983849']");
    private static By bYear = By.id("vehicle-year-1500983849");
//
//    @FindAll(@FindBy(xpath="//div[@id='vehicle-year-1500983849']/div[2]/div"))
//    protected static List<WebElement> yearOptions;
    private static By bYearOptions = By.xpath("//div[@id='vehicle-year-1500983849']/div[2]/div");

    //
//    //	@FindBy(xpath="//div[@id='vehicle-model-1500983880']/div[1]/div[1]")
//    @FindBy(id="vehicle-model-1500983880")
//    protected static WebElement model;
//    protected static By modelBy = By.xpath("//div[@id='vehicle-model-1500983880']");
    private static By bModel = By.id("vehicle-model-1500983880");
//
//    @FindAll(@FindBy(xpath="//div[@id='vehicle-model-1500983880']/div[2]/div"))
//    protected static List<WebElement> modelOptions;
    private static By bModelOptions = By.xpath("//div[@id='vehicle-model-1500983880']/div[2]/div");

    //
//    //	@FindBy(xpath="//div[@id='vehicle-transmission-1500983905']/div[1]/div[1]")
//    @FindBy(id="vehicle-transmission-1500983905")
//    protected static WebElement transmission;
    private static By bTransmission = By.id("vehicle-transmission-1500983905");
//
//    @FindAll(@FindBy(xpath="//div[@id='vehicle-transmission-1500983905']/div[2]/div"))
//    protected static List<WebElement> transmissionOptions;
     private static By bTransmissionOptions = By.xpath("//div[@id='vehicle-transmission-1500983905']/div[2]/div");
//
//    //	@FindBy(xpath="//div[@id='vehicle-engine-1500983934']/div[1]/div[1]")
//    @FindBy(id="vehicle-engine-1500983934")
//    protected static WebElement engine;
      private static By bEngine = By.id("vehicle-engine-1500983934");
//
//    @FindAll(@FindBy(xpath="//div[@id='vehicle-engine-1500983934']/div[2]/div"))
//    protected static List<WebElement> engineOptions;
      private static By bEngineOptions = By.xpath("//div[@id='vehicle-engine-1500983934']/div[2]/div");

    //    //	@FindBy(xpath="//div[@id='vehicle-drive-1500983970']/div[1]/div[1]")
//    @FindBy(id="vehicle-drive-1500983970")
//    protected static WebElement drive;
      private static By bDrive = By.id("vehicle-drive-1500983970");
//
//    @FindAll(@FindBy(xpath="//div[@id='vehicle-drive-1500983970']/div[2]/div"))
//    protected static List<WebElement> driveOptions;
    private static By bDriveOptions = By.xpath("//div[@id='vehicle-drive-1500983970']/div[2]/div");

    //    @FindBy(xpath="//div[@class='c-vehicle-info-editable__car-info']//div[@class='c-vehicle-info-editable__vin']")																//Entered vehicle vin
//    private static WebElement enteredVIN;
    private static By bEnteredVIN = By.xpath("//div[@class='c-vehicle-info-editable__car-info']//div[@class='c-vehicle-info-editable__vin']");

    // ****** end import element


    //    @FindBy(xpath="//div[@class='c-vehicle-info-editable__header__inner c-vehicle-info-editable__edit']//input[@class='form-element ng-untouched ng-pristine ng-valid']")																//Change vehicle vin
//    private static WebElement vin;
    private static By bVinText = By.xpath("//div[@class='c-vehicle-info-editable__header__inner c-vehicle-info-editable__edit']" +
            "//input[@class='form-element ng-untouched ng-pristine ng-valid']");

    private static WebElement decodeBtn;                                                                                    // VIN Decode Button
    private static By bDecodeBtn = By.xpath("//div[@class='c-vehicle-info-editable__header__inner c-vehicle-info-editable__edit']" +
            " //button[@class='c-vehicle-info-editable__decode']");

    private static By spinnerLocator = By.cssSelector("section.zi-10 div figure.radial-loader.loader_spiner");
    private static By bTryFixBtn = By.cssSelector("[ng-click*='tryFixIt']");    // Try Fix It Button
    private static By bErrorMsgLocator = By.cssSelector("section.error-page h3");                                            // Error Msg Locator

    private static By spinnerLocatorVehicleInfo1 = By.cssSelector("div figure.radial-loader.loader_spiner");
    private static By spinnerLocatorVehicleInfo = By.cssSelector("div.c-vehicle-info-editable__loader");
    private static By bVehicleInfoTitle = By.cssSelector("div.c-vehicle-info-editable__title");

    private static By bContactLanguage = By.id("contact-language-1500981325");
    private static By bLanguageOptions = By.xpath("//div[@id='contact-language-1500981325']/div[2]/div");
    private static By bCompanyNamelabel = By.xpath("//label[contains(text(),'Company name')]");
    private static By bCompanyName = By.xpath("//input[@id='company-name-1500980398']");
    private static By bLanguage(String lang) {
        String locator = String.format("//div[@id='contact-language-1500981325']/div[2]/div[contains(text(),'%s')]", lang);
        return By.xpath(locator);
    }

    // Email Text Field
    private static By bEmailField = By.id("customer-e-mail-1500981682");

    //RO push terms
    private static By bPushTermGreenCheckMark(String roPushTerm) {
        String locator = "//span[text()='" + roPushTerm + "']/../span[contains(@class,'selected')]";
        return By.xpath(locator);
    }


    //locator
    //vin input field
    private static By bVinInput = By.xpath("//div[@class='c-vehicle-info-editable__header__inner c-vehicle-info-editable__edit']" +
            "//input[contains(@class,'form-element')]");
    //blue decode vin button
    private static By bDecodeButton = By.xpath("//div[@class='c-vehicle-info-editable__header__inner c-vehicle-info-editable__edit']//div[text()='Decode']");

    //odometer
    private static By bOdometerField = By.xpath("//input[contains(@id,'vehicle-odometer')]");

    //next button on the client and vehicle page
    private static By bNextButtonOnCNV = By.xpath("//span[text()='Next']");
//    private static By bNextButton = By.xpath("//button[@id='next-1500972347']");
    private static By bNextButton = By.xpath("//button[@class='c-edit-appt-toolbar__next __c-edit-appt-toolbar__next--is-loading']");
    //business checkbox
    private static By bBusinessCheckBox2 = By.xpath("(//*[@class='icon icon--btn--check checkbox__icon'])[1]");   
    private static By bCancelButton = By.xpath("//div[@class='text' and text()='Cancel']");
    //
//    @FindBy(id="customer-first-name-1500980255")																			// Customer First Name
//    private static WebElement firstName;
//
//    @FindBy(id="customer-last-name-1500980284")
//    private static WebElement lastName;

    //first name field
//    private static By bFirstNameCV = By.xpath("//input[contains(@id,'customer-first-name')]");
    private static By bFirstNameCV = By.id("customer-first-name-1500980255");
    //last name field
//    private static By bLastNameCV = By.xpath("//input[contains(@id,'customer-last-name')]");
    private static By bLastNameCV = By.id("customer-last-name-1500980284");
    //Default phone
    private static By bPhoneCV = By.xpath("//input[contains(@id,'default-phone')]");
    //Street
    private static By bStreetCV = By.xpath("//input[contains(@id,'customer-street')]");
    //city
    private static By bCityCV = By.xpath("//input[contains(@id,'customer-city')]");
    //country
    private static By bCountryCV = By.xpath("//div[contains(@id,'customer-country')]");
    //zip
    private static By bZipCV = By.xpath("//input[contains(@id,'customer-zip')]");
    //Business checkbox
    private static By bBusinessCheckbox = By.xpath("//div[@class='c-customer-info-editable__business-checkbox']/label");
    //Private or Business customer
    private static By bCustomerProperty = By.xpath("//div[@class='c-edit-appt-tab__customer-info-editable']/section");
    private static By bCustomerType = By.xpath("//div[@class='c-edit-appt-tab__customer-info-editable']/section");

    //customer note
    private static By bCustomerNote = By.xpath("//a[@class='c-edit-appt-toolbar__notes__button']/div/div[1]");
    private static By customernote = By.xpath("//div[@class='c-comment-field__field']");
    
    private static By bsavecustomernote = By.xpath("//button[@class='notes_popup__buttons__save']");
    
    public String enteredcustomernote;
    
    
    
    
    
    
    public void decodeVinAM(String vin) {
        clearAndInputElementWithException(bVinInput, vin);
        sleep(1000);
        clickElementWithException(bDecodeButton);
        sleep(2000);
    }

    public String getDecodeVin() {
        String dVin = "";
        try{
            dVin = pWait.until(conditionVisible(bEnteredVIN)).getText().trim();
        }catch (TimeoutException te ){
            //
        }
        return dVin;
    }

    public boolean isCustomerNew() {
        if (getElementAttributeWithException(bFirstNameCV, "value").equals("")) {
            System.out.println("<====== This vin is a new customer ======>");
            return true;
        } else {
            System.out.println("<====== This vin is existing customer ======>");
            return false;
        }
    }

    public void enterOdometer(String odometer) {
        clearAndInputElementWithException(bOdometerField, odometer);
        customerName = getCustomerName();
    }

    public void inputFirstName(String firstName) {
        if (firstName.toLowerCase().equals("random")) {
            CommonMethods cm = new CommonMethods();
            firstName = cm.getRandomText(6);
        }
        clearAndInputElementWithException(bFirstNameCV, firstName);
    }

    public void inputLastName(String lastName) {
        if (lastName.toLowerCase().equals("random")) {
            CommonMethods cm = new CommonMethods();
            lastName = cm.getRandomText(6);
        }
        clearAndInputElementWithException(bLastNameCV, lastName);
    }

    public void selectLanguage(String lang) {
        pWait.until(conditionVisible(bContactLanguage)).click();
        CommonMethods.sleep(1000);
        List<WebElement> allElement = pWait.until(conditionPresenceList(bLanguageOptions));
        if (lang.equalsIgnoreCase("random")) {
            int size = allElement.size();
            if (size > 0) {
                int idx = CommonMethods.randomNum(0, size - 1);
                allElement.get(idx).click();
                ;
            }
        } else {
            pWait.until(conditionVisible(bLanguage(lang))).click();
        }

    }

    public void inputDefaultPhone(String phone) {
        if (phone.toLowerCase().equals("random")) {
            CommonMethods cm = new CommonMethods();
            phone = cm.getRandomNumber(10);
        }
        clearAndInputElementWithException(bPhoneCV, phone);
    }

    public void inputEmail(String email) {
        clearAndInput(bEmailField, email);
    }

    public void inputStreet(String street) {
        clearAndInputElementWithException(bStreetCV, street);
    }

    public void inputCity(String city) {
        clearAndInputElementWithException(bCityCV, city);
    }

    public void inputCountry(String country) {
        jsSetValue(bCountryCV, country);
    }

    public void inputZip1(String zip) {
        String[] pz = zip.split("/");
        String usZip = pz[0];
        String canadaPostCode = pz[1];
        if (driver.findElement(bCountryCV).getText().equals("Canada")) {
            System.out.println("<====== country Canada detected ======>");
            clearAndInputElementWithException(bZipCV, canadaPostCode);
        } else if (driver.findElement(bCountryCV).getText().equals("USA")) {
            System.out.println("<====== country USA detected ======>");
            clearAndInputElementWithException(bZipCV, usZip);
        }
    }

    public void inputZip(String zip) {
        if (driver.findElement(bCountryCV).getText().equals("Canada")) {
            System.out.println("<====== country Canada detected ======>");
            clearAndInputElementWithException(bZipCV, zip);
        } else if (driver.findElement(bCountryCV).getText().equals("USA")) {
            System.out.println("<====== country USA detected ======>");
            clearAndInputElementWithException(bZipCV, zip);
        }
    }

    public void setVehicleData(String field, String value) {
        switch (field) {
            case "Make":
                selectVehicleDropdown(bMake, bMakeOptions,value);
                break;
            case "Year":
                selectVehicleDropdown(bYear, bYearOptions,value);
                break;
            case "Model":
                selectVehicleDropdown(bModel, bModelOptions,value);
                break;
            case "Transmission":
                selectVehicleDropdown(bTransmission, bTransmissionOptions,value);
                sleep(1000);
                break;
            case "Engine":
                selectVehicleDropdown(bEngine, bEngineOptions,value);
                break;
            case "Drive train":
                selectVehicleDropdown(bDrive, bDriveOptions,value);
                break;
            case "Odometer":
                enterOdometer(value);
                break;
            case "License plate":
              //
                break;
            case "Color":
               //
                break;
            
            default:
            	break;
        }

    }

    public void selectVehicleDropdown(By field, By elementOptions, String value) {
        sleep(2000);
        try {
            if (driver.findElements(field).size() > 0) {
                int idx = 0;
                driver.findElement(field).click();
                sleep(3000);
                List<WebElement> elements = driver.findElements(elementOptions);
                if (value.equalsIgnoreCase("Random")) {
                    int size = elements.size();
                    if (size > 0) {
                        idx = CommonMethods.randomNum(1, size) -1 ;
                    }
                }else{
                    idx = Integer.valueOf(value) -1 ;
                }
                elements.get(idx).click();
            } else {
                //
            }			
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("Values not available for all the dropdowns-->" + ex.toString());
		}
    } 

    public void waitForCnVPageToLoad() {                                                                                // Wait for page to load
        dWait.until(SyncPage.condDomReadyState());
        pWait.until(conditionVisible(bOdometerField));
        handleErrorMsg();
    }

    private void waitForVehicleInfoToLoad() {                                                                                // Wait for page to load
        dWait.until(SyncPage.condDomReadyState());
        pWait.until(conditionVisible(bVehicleInfoTitle));
        pWait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocatorVehicleInfo));
//        dWait.until(SyncPage.condElementDisappear(spinnerLocatorVehicleInfo));
        handleErrorMsg();
    }

    // Handle the "More than one service team" warning message
    private void handleErrorMsg() {
        sleep(1000);
        try {
            if (driver.findElement(bErrorMsgLocator).isDisplayed()) {
                String msg = driver.findElement(bErrorMsgLocator).getText().toLowerCase();
                if (msg.contains("to more than one service team")) {
                    log.error("Error Message : .." + msg);
                    pWait.until(conditionClick(bTryFixBtn)).click();
                }
            }
        } catch (TimeoutException te) {
            log.info(" No Error Message not displayed !");
        }
        pWait.until(conditionVisible(bEmailField));
//       dWait.until(SyncPage.condElementDisappear(spinnerLocator));
        dWait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));

    }

    public void clickOnNext() {
        clickElementWithException(bNextButtonOnCNV);
    }

    public void clickNextBtn() {
        clickElementWithSeconds(bNextButton, 2000);
    }

    private String getCustomerName() {
        sleep(1500);
        String name = "";
        waitForVehicleInfoToLoad();
        if(!driver.findElement(bCustomerProperty).getAttribute("class").contains("business")){
            name = driver.findElement(bFirstNameCV).getAttribute("value").trim() +
                    " " + driver.findElement(bLastNameCV).getAttribute("value").trim();
        }
        if(driver.findElement(bCustomerProperty).getAttribute("class").contains("business")){
            name = driver.findElement(bCompanyName).getAttribute("value").trim();
        }        
        return name;
    }

    public String checkVehicleInfor() {
        String vehicle = "";
        pWait.until(ExpectedConditions.invisibilityOfElementLocated(bMake));
        sleep(2000);
        List<WebElement> infoList = driver.findElements(bDuplicateCust);
        if (infoList.size() > 0) {
            vehicle = infoList.get(0).getText().trim();
        }
        return vehicle;
    }

    public void clickBtncheckVehicleInfor(String btn) {
        WebElement eBtn = null;
        sleep(1000);
        if (btn.equals("USE MATCHED CUSTOMER")) {
            eBtn = pWait.until(conditionVisible(bUsedMatchVinBtn));
            jsClick(eBtn);
        } else if (btn.equals("CHANGE OWNER")) {
            eBtn = pWait.until(conditionVisible(bChangeOwnerBtn));
            jsClick(eBtn);
        } else {
            //
        }
        sleep(1000);
    }

    public String getRecallCount() {
        String recall = "";
        try{
            pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bRecalllist));
            recall = pWait.until(conditionVisible(bRecallCount)).getText().trim();
        }catch (TimeoutException te ){
            //
        }
        if (!recall.isEmpty() && recall.contains("(") ){
            recall = recall.substring(recall.indexOf("(")+1,recall.indexOf(")"));
        }
        return recall;
    }

    public int getRecallListSize() {
         return  driver.findElements(bRecalllist).size();
    }

    public void verifyElementExists(String field){
        switch (field) {
        case "Business":
            dWait.until(conditionVisible(bBusinessCheckbox));        	
        	CommonMethods.verifyElementExists(driver.findElement(bBusinessCheckbox));
        	CommonMethods.verifyElementExists(driver.findElement(bCompanyNamelabel));        	
            break;
        default:
        	break;    	
        }    
    }  
    
    public boolean isBusinessCustomer() {
		WebElement customerType = driver.findElement(bCustomerType);    	
    	if (customerType.getAttribute("class").trim().contains("is-business")) {
    		return true;
		} else {
			return false;
		}    	
    }

    public void clickOnBusinessCheckBox(){
        pWait.until(conditionVisible(bBusinessCheckBox2));
        driver.findElement(bBusinessCheckBox2).click();
        System.out.println("Clicked business checkbox");
    }    

    public void clickOnEmailCheckBox(){
        pWait.until(conditionVisible(emailCheckBox));
        driver.findElement(emailCheckBox).click();
        System.out.println("Clicked Email checkbox");
    }       
    
    public void clickCancelBtn() {
        clickElementWithSeconds(bCancelButton, 2000);
    }    

    public void clickCustomerNote() {
		// TODO Auto-generated method stub
		
		sleep(8000);
		driver.findElement(bCustomerNote).click();
		sleep(8000);
	
	}

	public void enterCustomerNote(String CustomerNote) {
		// TODO Auto-generated method stub
		if (CustomerNote.toLowerCase().equals("random")) {
            CommonMethods cm = new CommonMethods();
            CustomerNote = cm.getRandomText(6);
        }
		
		clearAndInputElementWithException(customernote, CustomerNote);
		enteredcustomernote = driver.findElement(customernote).getText();
		driver.findElement(bsavecustomernote).click();
		sleep(8000);
	}

	public void verifyCutomerNote() {
		// TODO Auto-generated method stub
		
	String SavedCustomernote = driver.findElement(customernote).getText();
	System.out.println(SavedCustomernote + enteredcustomernote);
	Assert.assertEquals(SavedCustomernote,enteredcustomernote);
	sleep(8000);
	}    


}


