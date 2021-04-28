package com.automation.pages.advisor_checkin;

import com.automation.pages.appointment_manager.AM_DashboardPage;
import com.automation.pages.payment.CreateInvoicePage;
import com.automation.pages.payment.PaymentHomePage;
import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ACI_CustomerPage extends ACI_FramePage {
	
	public static String apptNumber = null;	
	
	public static String getApptNumber() {
		return apptNumber;
	}

	public static void setApptNumber(String apptNumber) {
		ACI_CustomerPage.apptNumber = apptNumber;
	}
	private String firstName;
	private String lastName;
	private String customerNotes;
	public static String customerName = null;

    public static String getCustomerName() {
		return customerName;
	}

	public static void setCustomerName(String customerName) {
		ACI_CustomerPage.customerName = customerName;
	}

	public String getCustomerNotes() {
		return customerNotes;
	}

	public void setCustomerNotes(String customerNotes) {
		this.customerNotes = customerNotes;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void AdvisorCheckInCustomerPage(){
        setWait();
    }
    private String mandatoroyRequiredText;

    private static Logger log = Logger.getLogger(ACI_CustomerPage.class.getName());

    //DEFAULT PHONE NUMBER field
    private static final By bDefaultPhoneNumber = By.xpath("//input[@name='homephone']");
    //LICENSE PLATE Field
    private static final By bLicensePlate = By.xpath("//input[@name='licensePlate']");
    //vehicle information vin
//    private static final By bVechicleInfo = By.xpath("//form[@name='customerForm']//h3[contains(text(),'VEHICLE INFORMATION')]");
    private static final By bVechicleInfo = By.cssSelector("div.boder-pad.fly h3.ng-binding");

    //vin on the customer info page
    private  By bVinONCustomerInFo(String vin){      // no good testing approch need removed
        String locator = "//*[contains(text(),'"+vin+"')]";
        return By.xpath(locator);
    }

    private static final By bapptNumber = By.xpath("//span[@class='blue-text ng-binding']");    
    private static final By bCustomerNotes = By.xpath("//button[contains(@class, 'notes-trigger')]");    
    private static final By bCustomerNotesView = By.xpath("//button[@type='button' and @class='notes-trigger to-save']");    
    private static final By bCustomerNotesEditor = By.xpath("//div[@id='editor']");    
    private static final By bCustomerNotesSave = By.xpath("//button[@type='button' and @class = 'btn btn-green']/span[text()='Save']");    
    //odometer field
    private static final By bOdometer = By.xpath("//input[@name='odometer']");
    //close button on the pop up
    private static final By bCloseOnPopup = By.xpath("//div[@style='display: block;']//span[text()='CLOSE ']");
    //cell phone number field
    private static final By bCellPhoneNumberOnCustomer = By.xpath("//input[@name='cellphone']");
    //phone number field
    private static final By bPhonePhoneNumberOnCustomer = By.xpath("//input[@name = 'homephone']");
    //email field on customer page
    private static final By bEmailOnCustomer = By.xpath("//input[@name='email']");
    //use to verify if this user is a business account
    private static final By bBusinessUser = By.xpath("//div[@id='g-busaccount']/span");
    //first name field on customer page
    private static final By bFirstNameOnCustomer = By.xpath("//form[@name='customerForm']//input[@name='firstName']");
    //last name field on customer page
    private static final By bLastNameOnCustomer = By.xpath("//form[@name='customerForm']//input[@name='lastName']");
    //footer name of customer 
    private static final By bfooterNameOfCustomer = By.xpath("(//span[@class='ng-binding'])[1]");    
    //address on customer page
    private static final By bAddressOnCustomer = By.xpath("//form[@name='customerForm']//input[@name='address']");
    //city on customer page
    private static final By bCityOnCustomer = By.xpath("//form[@name='customerForm']//input[@name='state']");
    //Postal/Zip code field on customer page
    private static final By bZipPostalOnCustomer = By.xpath("//form[@name='customerForm']//input[@name='zipcode']");    
    //car image on the customer page
    private static final By bCarImage = By.xpath("//img[@ng-show='infoObject.vehicle.image']");
    //load prompt
    private static final By loadButton = By.xpath("//span[contains(text(),'LOAD')]");
    //load prompt
    private static final By ignoreButton = By.xpath("//span[contains(text(),'IGNORE')]");
    //use appointment button on the existing appointment details pop up
    private static final By bUseAppointmentButton = By.xpath("//span[text()='Use appointment']");
    //overwrite prompt
    private static final By overwriteButton = By.xpath("//span[contains(text(),'Overwrite')]");
    //overwrite prompt cancel
    private static final By bOverwriteCancelButton = By.xpath("//span[contains(text(),'Cancel')]");
    //update existing customer on customer page
    private static final By bUpdateExistingCustomer = By.xpath("//div[@class='modal hide fade ng-scope in' and @style='display: block;']//span[contains(text(),'UPDATE EXISTING CUSTOMER')]");
    //customer match pop up on the customer page
    private static final By bCustomerMatch = By.xpath("//h3[text()='CUSTOMER MATCH']/../../../..");
    //transfer green button on the customer match pop up
    private static final By bTransferCustomerMatch = By.xpath("//span[text()='TRANSFER ']");
    //SERVICE CONTRACT button
    private static final By bServiceContract = By.xpath("//div[contains(@class,'service-contract') and text()='Service contract']");
    //Vehicle Recall Item List
    private static final By bVehicleRecallItemList = By.xpath("//div[contains(@ng-repeat,'recall')]");
    //Recall icon with number of recall/action required
    private static final By bRecallNum = By.xpath("//span[@class='vh_table__status-count vh_table__status-count--red ng-binding']");
    //Warranty Information Item List
    private static final By bVehicleWarrantyItemList = By.xpath("//div[contains(@ng-repeat,'warrantyItem')]/div[1]");
    //back to search on the customer page
    private static final By bBackToSearchOnCustomer = By.xpath("//form[@name='customerForm']//span[text()='BACK TO SEARCH']");
    //VIEW WARRANTY button
    private static final By bViewWarrantyOnCustomer = By.xpath("//div[@class='warranty-status']");
    //close on the warranty information pop up
    private static final By bCloseOnWarrantyInformationPopup = By.xpath("//button[@ng-click='hide()']/span[contains(text(),'Close')]");

    private static final By bSpinnerCustomer = By.cssSelector("#customer i.spinner"); // Spinner Locator indicating page is
    private static final By bSpinnerService = By.cssSelector("#serviceAppointments > i.spinner"); // 2.8
    private static final By bStoredDataWarningText = By.cssSelector("[id*='appstaterestoredialog'] div.modal-header h3");

    private static final By bCreateNewCustomerIcon = By.xpath("//span[contains(text(), 'CREATE NEW CUSTOMER')]");
    private static final By bIgnoreBtn = By.cssSelector("[id*='appstaterestoredialog'] button[ng-click*='overwrite']");
    private static final By bExistingAppointmentTitle = By.cssSelector("div[loader*='historyloader' i] h3");
    private static final By bUseApptBtn = By.cssSelector("button[ng-click*='useappointment' i]");
    private static final By bCloseApptBtn = By.xpath("//button[@type='button' and @class='btn btn-grey']/span[@class='vertical-align-middle' and text()='CLOSE ']");    
    private static final By bserviceLaneOverwriteText = By.cssSelector("[id*='servicelaneoverwrite'] div.modal-body > div:nth-child(2) h4");
    private static final By bSvcLaneOverWriteBtn = By.cssSelector("[id*='servicelaneoverwrite'] button:nth-child(1)");
    private static final By bCountyOptions = By.cssSelector("#customer select[title='Select County']");
    private static final By bOdometerMandatoryMsgList = By.cssSelector("div.control-group.span12.align-center label.error");
    private static final By bAcknowledgeBtns = By.xpath("//button/span[text()='I Acknowledge']");
    private static final By bVehicleType = By.cssSelector("div:nth-child(6)>div.control-group.span11 span");

    private static final By bmanufacture = By.cssSelector("select[name='manafacture']");
    private static Select manufactureField;
    private static final By byear = By.cssSelector("select[name='year']");
    private static Select yearField;

    private static final By bmodel = By.cssSelector("select[name='model']");
    private static Select modelField;

    private static final By btransmission = By.cssSelector("select[name='transmission']");
    private static Select transmissionField;

    private static final By bcylinder = By.cssSelector("select[name='cylinder']");
    private static Select cylinderField;

    private static final By bdrive = By.cssSelector("select[name='drive']");
    private static Select driveField;

    //Concern
    private static final By bConcern = By.xpath("(//span[@class='title ng-binding'])[1]");    

    //Symptom Survey 
    private static final By bSymptomSurvey = By.xpath("(//div/div/select[@name='surveys'])[1]");        
    
    @Override
    protected void init() {
        super.init();
        ACI_HistoryPage helper = new ACI_HistoryPage();
    }

    public  void waitCustomerPageLoad(){																								// Go to Customer Tab
        setWait();
        waitSpinnerInvisible();
        waitCustomerPageSpinnerInvisible();
    }

    private  void waitCustomerPageSpinnerInvisible(){
        dWait.until(conditionVisible(bVehicleType));
        dWait.until(ExpectedConditions.invisibilityOfElementLocated(bSpinnerCustomer));
        initSelectionField();
        dWait.until(SyncPage.condDropDownListUpdate(manufactureField));
        dWait.until(ExpectedConditions.invisibilityOfElementLocated(bSpinnerService));
    }

    private static void initSelectionField() { // Initialize dropdown objects
        manufactureField = new Select(driver.findElement(bmanufacture));
        yearField = new Select(driver.findElement(byear));
        modelField = new Select(driver.findElement(bmodel));
        transmissionField = new Select(driver.findElement(btransmission));
        cylinderField = new Select(driver.findElement(bcylinder));
        driveField = new Select(driver.findElement(bdrive));
    }

    public void storedDataWarningHandler(){																								// Handle the Stored Data Warnign Message
        sleep(2000);
        try{
            if(driver.findElement(bStoredDataWarningText).isDisplayed()){
                clickElementWithException(bIgnoreBtn);
                log.info("Store Data Warning - selected Ignore button");
                sleep(500);
            }
        } catch (NoSuchElementException e){
            log.info("Store Data Warning Message not displayed");
        }
    }

    public void handleStoredDataWarning(){																								// Handle the Stored Data Warnign Message
        sleep(1000);
        try{
                WebElement eIgnore = sWait.until(conditionClick(bIgnoreBtn));
                moveToClick(eIgnore);
                log.info("Store Data Warning - selected Ignore button");
                sleep(500);
        } catch (TimeoutException te ){
            log.info("Store Data Warning Message not displayed");
        }
    }

    public void handleCreateNewCustomer(){																								// Handle the Stored Data Warnign Message
        sleep(1000);
        try{
            WebElement eIgnore = sWait.until(conditionClick(bCreateNewCustomerIcon));
            moveToClick(eIgnore);
            log.info("New Vehicle Warning - selected Create New Customer button");
            sleep(500);
        } catch (TimeoutException te ){
            log.info("New Vehicle Warning Message not displayed");
        }
    }

    public  void existingAppointmentHandler(){
        try{
            if(driver.findElement(bExistingAppointmentTitle).isDisplayed()){
                clickElementWithException(bUseApptBtn);
                log.info(" -- Customer with existing appointment --- ");
                sleep(1000);
            }
        } catch (NoSuchElementException e){
            log.info("Customer without existing appointment");
        }
    }

    public  void handleExistingAppointment(){
        try{
            WebElement elUser = sWait.until(conditionClick(bUseApptBtn));
//            clickElementWithException(bUseApptBtn);
//            actionClick(bUseApptBtn);
            apptNumber = driver.findElement(bapptNumber).getText().trim();
            System.out.println("Appointment number on Existing appointment pop up is "+ apptNumber);
            clickElementTimesTillDisappear(elUser,8);
           log.info(" -- Customer with existing appointment --- ");
        } catch (TimeoutException te){
            System.out.println(te.getMessage());
            log.info("Customer without existing appointment");
        }
    }

    public void svcLaneMsgHandler(){																										// Handle the Service Lane Over Write Message
        sleep(1000);
        try{
             WebElement elService = driver.findElement(bserviceLaneOverwriteText);
            if(elService.isDisplayed() && elService.getText().toLowerCase().contains("service lane")){
                clickElementWithException(bSvcLaneOverWriteBtn);
                log.info("Selected Service Lane Over Write");
                sleep(1000);
            }
        } catch (NoSuchElementException e){
            e.printStackTrace();
            System.out.println("*** Service Lane Over Write Message not displayed ****");
            log.info("Service Lane Over Write Message not displayed");
        }
    }

    public void handleSvcLaneMsg(){																										// Handle the Service Lane Over Write Message
        sleep(1000);
        try{
            WebElement overBtn = new WebDriverWait(driver,3).until(ExpectedConditions.elementToBeClickable(bSvcLaneOverWriteBtn));
//            moveToClick(overBtn);
            clickElementTimesTillDisappear(overBtn,4);
//            actionClick(bSvcLaneOverWriteBtn);
//            WebElement elService = driver.findElement(bserviceLaneOverwriteText);
//            if(elService.isDisplayed() && elService.getText().toLowerCase().contains("service lane")){
//                clickElementWithException(bSvcLaneOverWriteBtn);
//                log.info("Selected Service Lane Over Write");
//                sleep(1000);
//            }
        } catch (TimeoutException te ){
            System.out.println("*** Service Lane Over Write Message not displayed ****");
            log.info("Service Lane Over Write Message not displayed");
        }
    }

    public void countyBlankHandler(){																										// Handler when County value is blank
        goToCustomerTab();
        Select countyselect = new Select(driver.findElement( bCountyOptions));
        if(countyselect.getFirstSelectedOption().getText().equalsIgnoreCase("show all")){
            countyselect.selectByIndex(1);
        }
    }

    public void checkSalvagedTitle() {
        String url = driver.getCurrentUrl().toLowerCase();
        if (url.contains("lexusad") || url.contains("toyotaad") ) {
            sleep(1000);
            List<WebElement> elmiAcknowledgeBtns = driver.findElements(bAcknowledgeBtns);
            if (elmiAcknowledgeBtns.size() > 0) {
                elmiAcknowledgeBtns.get(0).click();
            }
        }
    }

    public boolean  isMandatoryMsgDisplayed() {																									// Verify The Mandatory Message is displayed
        boolean display = true;
        String svalue = driver.findElement(bOdometer).getAttribute("value");
        if(svalue .trim().isEmpty()){
            clickNextBtnOnAdvisorFrame();
            List<WebElement> elementList = dWait.until(conditionPresenceList(bOdometerMandatoryMsgList));
               if(isMandatoryRequireMessageTextDisplayed(elementList)){
                 System.out.println("Odometer Required Mandatory Message Displayed : " + mandatoroyRequiredText);
                 log.info("Odometer Required Mandatory Message Displayed : " + mandatoroyRequiredText);
            } else {
                   String sClass = dWait.until(conditionVisible(bCustomerTab)).getAttribute("class");
                  if(sClass.equals("")){
                      System.out.println("Odometer Mandatory Field is Failed to display");
                      display = false;
//                     throw new AssertionError("Odometer Required Fail to Display");
                } else {
                      log.error("Exception occurred, customer tab class value is : " + sClass);
                }
                goToCustomerTab();
            }
        } else {
            System.out.println("Odometer auto populated " + svalue);
        }
        return display;
    }

    public String getAttributeOdoMeterValue() {
       String value =  getElementAttributeWithException(bOdometer, "value");
       return value ;
    }


    public String getCssBackgroundColorValue(String field ) {
        String value = "";
        if (field.equalsIgnoreCase("nextButton")){
            WebElement el = dWait.until(conditionVisible(bNextBtnAdVisorFrame));
            value = el.getCssValue("background-color");
        }
        return value ;
    }

    private  boolean isMandatoryRequireMessageTextDisplayed(List<WebElement> webElementList){	// This is check if Mandatory Msg is displayed. Can check Not providing or Over Maximun
//			int count = 0;
        boolean result = false;
        for(Iterator<WebElement> ii = webElementList.iterator(); ii.hasNext();){
            WebElement element = ii.next();
            if(! webElementHasClass(element,"ng-hide")){
                log.info("Mandatory Text Displayed : " + element.getText());
                mandatoroyRequiredText = element.getText();
                result = true;
//					count++;
            }
            break; // ?? it would be above
        }
        return result;
    }

    public void waitForLoadingCompletedOnAdvisorCheckIn(){
        waitForElementHasAttributeValue(bSpinner, "style","display: none; height: 961px;");
    }

    public void clickOnIgnoreInfo(){
        waitForElementWithException(bCarImage);
        try{
            driver.findElement(loadButton).isDisplayed();
            pWait.until(conditionVisible(ignoreButton)).click();
        }catch(WebDriverException e){
            //clean up the load pop up
        }
    }

    public void clickOnOverwrite(){
        try{
            driver.findElement(overwriteButton).click();
        }catch (WebDriverException e){
            //do nothing
        }
    }

    public void clickOnOverwriteCancel(){
        try{
            driver.findElement(bOverwriteCancelButton).click();
        }catch (WebDriverException e){
            //do nothing
        }
    }

    public boolean isOverWritePopupDisplayed(){
        try{
            driver.findElement(overwriteButton).isDisplayed();
            return true;
        }catch (WebDriverException e){
            return false;
        }
    }

    public boolean isExistingAppointmentPopUpDisplayed(){
        try{
            driver.findElement(bUseAppointmentButton).isDisplayed();
            return true;
        }catch (WebDriverException e){
            return false;
        }
    }

    public void clickUseAppointmentOnExistingAppointmentPopUp(){
        long startTime = System.currentTimeMillis();
         waitForLoadingCompletedOnAdvisorCheckIn();
        while(!isExistingAppointmentPopUpDisplayed()){
            clickOnIgnoreInfo();
            sleep(1500);
            if((System.currentTimeMillis()-startTime)> 30000){
                System.out.println("<====== Not able to clicked on use appointment button, test fail ======>");
                break;
            }
        }
        clickElementWithException(bUseAppointmentButton);
        try{
            driver.findElement(bOdometer).click();
        }catch (WebDriverException e){
            while(isOverWritePopupDisplayed()){
                clickOnOverwriteCancel();
                sleep(1500);
                if((System.currentTimeMillis()-startTime)> 60000){
                    System.out.println("<====== Overwrite pop up still blocked, test fail ======>");
                    break;
                }
            }
        }
    }

    public boolean isPhoneNumberMatch(String expectedNumber){  //  wrong logic, need to remove in the future
        long startTime = System.currentTimeMillis();
        String actualNumber = "";
        waitForLoadingCompletedOnAdvisorCheckIn();
        boolean noException = false;
        while((!actualNumber.equals(expectedNumber))||!noException){
            try{
                driver.findElement(bDefaultPhoneNumber).click();
                actualNumber=jsGetAttribute(bDefaultPhoneNumber,"value");
                System.out.println("<====== The phone number on the page is "+actualNumber+" ======>");
                noException = true;
            }catch (WebDriverException e){
                clickOnIgnoreInfo();
                clickOnOverwrite();
                noException = false;
            }
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 60000){
                System.out.println("<====== Not able to get phone number, test failed ======>");
                break;
            }
        }
        if(actualNumber.equals(expectedNumber)){
            System.out.println("<====== The phone number matches ======>");
            return true;
        }else{
            return false;
        }
    }

    public String getPhoneNumberCustomerInfo(){
        String actual = "";
        sWait.until(conditionVisible(bDefaultPhoneNumber)).click();
        actual=jsGetAttribute(bDefaultPhoneNumber,"value");
        return actual;
    }

    public boolean isPlateNumberMatch(String expectedPlate){
        long startTime = System.currentTimeMillis();
        String actualPlate = "";
        waitForLoadingCompletedOnAdvisorCheckIn();
        boolean noException = false;
        while((!actualPlate.equals(expectedPlate))||!noException){
            try{
                driver.findElement(bLicensePlate).click();
                actualPlate=jsGetAttribute(bLicensePlate,"value");
                System.out.println("<====== The plate number on the page is "+actualPlate+" ======>");
                noException = true;
            }catch (WebDriverException e){
                clickOnIgnoreInfo();
                clickOnOverwrite();
                noException = false;
            }
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 60000){
                System.out.println("<====== Not able to get plate number, test failed ======>");
                break;
            }
        }
        if(actualPlate.equals(expectedPlate)){
            System.out.println("<====== The plate number matches ======>");
            return true;
        }else{
            return false;
        }
    }

    public String getPlateNumberCustomerInfo(){
        String actual = "";
        sWait.until(conditionVisible(bLicensePlate)).click();
        actual=jsGetAttribute(bLicensePlate,"value");
        return actual;
    }

    public boolean isVinMatch(String expectedVIN){
        long startTime = System.currentTimeMillis();
        String actualVin = "";
        waitForLoadingCompletedOnAdvisorCheckIn();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bVechicleInfo).click();
                noException = true;
            }catch (WebDriverException e){
                clickOnIgnoreInfo();
                clickOnOverwrite();
                noException = false;
            }
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 60000){
                System.out.println("<====== Not able to get vin, test failed ======>");
                break;
            }
        }
        if(driver.findElement(bVinONCustomerInFo(expectedVIN)).isDisplayed()){
            System.out.println("<====== The vin "+expectedVIN+" show on the page ======>");
            return true;
        }else{
            return false;
        }
    }

    public String getVinONCustomerInfo(){
        String actual = "";
        actual = pWait.until(conditionVisible(bVechicleInfo)).getText();
        return actual;
    }

    public void enterOdometer(String text) {
        long startTime = System.currentTimeMillis();
        waitForLoadingCompletedOnAdvisorCheckIn();
        while(!driver.findElement(bOdometer).getAttribute("value").equals(text)){
            try{
                driver.findElement(bCloseOnPopup).click();
                System.out.println("<====== Close button on EXISTING APPOINTMENT DETAILS clicked =====>");
            }catch (WebDriverException ex){
                //the EXISTING APPOINTMENT DETAILS pop up is not there
            }
            try{
                driver.findElement(bOdometer).click();
                clearAndSend(bOdometer, text);
            }catch (WebDriverException e){
                clickOnIgnoreInfo();
                clickOnOverwrite();
            }
            sleep(2000);
            if((System.currentTimeMillis()-startTime)> 60000){
                System.out.println("<====== Not able to enter odometer, test fail ======>");
                break;
            }
        }
    }

    public void updateOdometer(String text) { // Enter odometer number
        WebElement element = driver.findElement(bOdometer);
        clearAndInput(element, text);
    }

    public void updateCellNumber(String phoneNumber){
        clearAndSend(bCellPhoneNumberOnCustomer, phoneNumber);
    }

    public void updatePhoneNumber(String phoneNumber){
        if (phoneNumber.toLowerCase().equals("random")) {
            phoneNumber = CommonMethods.getRandomNumber(10);
        }    	
        WebElement customerphone = driver.findElement(By.xpath("//input[@name = 'homephone']"));  
        //clearAndSend(bPhonePhoneNumberOnCustomer, phoneNumber);
        //setPhonenumber(phoneNumber);
        clearWebField(customerphone);
        customerphone.sendKeys(phoneNumber);
    }

    public void updateEmail(String email){
        clearAndSend(bEmailOnCustomer, email);
    }

    public void updateFirstName(String firstName){
        if(byElementHasClass(bBusinessUser,"selected")){
            dWait.until(conditionClick(bBusinessUser)).click();
        }
        if (firstName.toLowerCase().equals("random")) {
            firstName = CommonMethods.getRandomText(6);
        }        
        clearAndSend(bFirstNameOnCustomer, firstName);
        setFirstName(firstName);
    }

    public void updateLastName(String lastName){
        if(byElementHasClass(bBusinessUser,"selected")){
            dWait.until(conditionClick(bBusinessUser)).click();
        }
        if (lastName.toLowerCase().equals("random")) {
            lastName = CommonMethods.getRandomText(6);
        }         
        clearAndSend(bLastNameOnCustomer, lastName);
        setLastName(lastName);
    }

    public void clickNextOnCustomer(){
        long startTime = System.currentTimeMillis();
        try{
            sWait.until(conditionClick(bNextBtnAdVisorFrame)).click();
            System.out.println("<====== The next button clicked ======>");
        }catch (WebDriverException e){
            while(isOverWritePopupDisplayed()){
                clickOnOverwriteCancel();
                sleep(1500);
                if((System.currentTimeMillis()-startTime)> 30000){
                    System.out.println("<====== Overwrite pop up still blocked, test fail ======>");
                    break;
                }
            }
            clickElementWithException(bNextBtnAdVisorFrame);
        }
        try{
            waitForLoadingCompletedOnAdvisorCheckIn();
            waitForElementWithExceptionWithSecs(bUpdateExistingCustomer,2000);
            driver.findElement(bUpdateExistingCustomer).click();
        }catch (WebDriverException ex){
            //update customer pop up does not exist
        }
        //in case the customer match pop up
        while(customerMatchExist()){
            try{
                driver.findElement(bTransferCustomerMatch).click();
            }catch (WebDriverException e){
                //do nothing, the customer match pop up is not there
            }
            if((System.currentTimeMillis()-startTime)> 60000){
                break;
            }
        }
    }

    public boolean customerMatchExist(){
        try{
            driver.findElement(bCustomerMatch);
            if(driver.findElement(bCustomerMatch).getAttribute("style").equals("display: none;")){
                return false;
            }
            return true;
        }catch(WebDriverException e){
            return false;
        }
    }

    public void pagePopUpHandle(){
        try{
            driver.findElement(bTransferCustomerMatch).click();
            System.out.println("<====== transfer customer match pop up clicked ======>");
        }catch (WebDriverException exx){
            //do nothing, the customer match pop up is not there
        }
        try{
            driver.findElement(bUpdateExistingCustomer).click();
            System.out.println("<====== update existing customer pop up clicked ======>");
        }catch (WebDriverException ex){
            //update existing customer pop up is not there
        }
    }

    public void clickBackToSearchOnCustomer(){
        clickElementWithException(bBackToSearchOnCustomer);
    }

    public void clickCloseOnWarrantyInformationPopUp(){
        clickElementWithException(bCloseOnWarrantyInformationPopup);
    }

    public boolean assertStatusOnCustomerInfo(String expectedStatus, String statusName){
        String status = "";
        switch (statusName){
            case "recall":
                if(driver.findElements(bVehicleRecallItemList).size()>0){
                    System.out.println("<====== Recall items existed on the page ======>");
                    status = "Yes";
                }else{
                    System.out.println("<====== Recall items not existed on the page ======>");
                    status = "No";
                }
                break;
            case "contract":
                if(byElementHasClass(bServiceContract,"active")){
                    System.out.println("<====== SERVICE CONTRACT button activated ======>");
                    status = "Yes";
                }else{
                    System.out.println("<====== SERVICE CONTRACT button grey out ======>");
                    status = "No";
                }
                break;
        }
        if(expectedStatus.equals(status)){
            return true;
        }else{
            return false;
        }
    }

    public int recallItemsOnCustomerInfo(){
        int result = driver.findElements(bVehicleRecallItemList).size();
        return result ;
    }

    public boolean hasServiceContactOnCustomerInfo(){
           return webElementHasClass(bServiceContract,"active");
    }

    public void clickViewWarrantyOnCustomer(){
        clickElementWithException(bViewWarrantyOnCustomer);
    }

    public void clickCustomerNotes(){
        clickElementWithException(bCustomerNotes);
    }
    
    public void clickViewCustomerNotes(){
        clickElementWithException(bCustomerNotesView);
    }    

    public void clickCustomerNotesSave(){
    	sleep(3000);
        clickElementWithException(bCustomerNotesSave);
    	sleep(5000);        
    }    
    
    public void verifyStatusOnCustomerInfo(String statusName){
        switch(statusName){
            case "WARRANTY":
                if(driver.findElements(bVehicleWarrantyItemList).size()>3){
                    System.out.println("<====== More than 3 warranties for this customer ======>");
                }else{
                    System.out.println("<====== Less than 3 warranties for this customer ======>");
                }
                break;
        }
    }

    public int itemsWARRANTYOnCustomerInfo(){
       int  result = 0;
       if (webElementHasClass(bViewWarrantyOnCustomer,"active")){
           driver.findElement(bViewWarrantyOnCustomer).click();
           result = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bVehicleWarrantyItemList)).size();
           clickCloseOnWarrantyInformationPopUp();
       }
       return result;
    }

    public void openNissanReportIconOnCustomer(){
        driver.findElement(bServiceCommReportIcon).click();
        sleep(3000);
        driver.findElement(bNissanSelectBtn).click();        
        sleep(10000);
    }

    public void openNissanWorksheetIconOnCustomer() {
        TestParameters.parentWindow = driver.getWindowHandle();
        driver.findElement(bWorksheetIcon).click();
        sleep(500);
    }

    public void openMitsubishiReportIconOnCustomer(){
        driver.findElement(bServiceCommReportMitsubishiIcon).click();
        sleep(10000);
    }

    public void openAudiReportIconOnCustomer(){
        driver.findElement(bServiceCommReportAudiIcon).click();
        sleep(10000);
    }

    public void openGMReportIconOnCustomer(){
        driver.findElement(bServiceCommReportGMIcon).click();
        sleep(10000);
    }

    public void openToyotaReportIconOnCustomer(){
        driver.findElement(bServiceCommReportToyotaIcon).click();
        sleep(10000);
    }

    public void openToyotaServiceLaneIcon(){
        driver.findElement(bServiceLaneReportIcon).click();
        sleep(1000);
        pWait.pollingEvery(5000, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(bCancelBtn));
    }

    public boolean recallFound(){
        try{
//            driver.findElement(bRecallNum);
            tp.numOfRecalls = driver.findElement(bRecallNum).getText();
            return true;
        }catch(WebDriverException e){
            return false;
        }
    }

    public String getURL(){
        Set<String> handles = driver.getWindowHandles();
        String url = "";
        for(String windowHandle: handles){
            if(!windowHandle.equals(TestParameters.parentWindow)){
                driver.switchTo().window(windowHandle);
                url = driver.getCurrentUrl();
            }
        }
        return url;
    }

    public void clickAcknowledgeBtn() {
        try {
            driver.findElement(bAcknowledgeBtn).click();
        } catch (WebDriverException e) {
            System.out.println("I ACKNOWLEDGE button is not displayed, skip this step");
        }
    }

    public void clickNextBtn() {
        try{
            dWait.until(conditionClick(bNextBtnAdVisorFrame)).click();
        }catch(WebDriverException e){
            System.out.println("NET button is not clickable!");
        }
    }

    public void backToAdvisorCheckInPage(){
        Set<String> winHandles = driver.getWindowHandles();
        for(String handle: winHandles) {
            if(driver.switchTo().window(handle).getTitle().equalsIgnoreCase("Advisor Check-In")){
                driver.switchTo().window(handle);
            }
        }
    }
    
    public void selectConcern() {
    	dWait.until(conditionClick(bConcern)).click();
    }  
    
    public void selectSymptomSurvey(String symptomSurvey) {
		lWait.until(ExpectedConditions.visibilityOfElementLocated(bSymptomSurvey));			
		lWait.until(ExpectedConditions.elementToBeClickable(bSymptomSurvey));	    	
		switch (symptomSurvey.toUpperCase()) {
		case "AUDIO/NAVIGATION/BLUETOOTH/TELEMATICS":			
			driver.findElement(bSymptomSurvey).sendKeys(symptomSurvey.toUpperCase());
			break;
		default:
			return;
		}
    }
    
    public void updateAddress(String address){
        if (address.toLowerCase().equals("random")) {
            address = CommonMethods.getRandomText(6) + " " + CommonMethods.getRandomText(4);
        }    	
        clearAndSend(bAddressOnCustomer, address);
    }  
    
    public void updateCity(String city){
        if (city.toLowerCase().equals("random")) {
        	city = CommonMethods.getRandomText(6);
        }    	
        clearAndSend(bCityOnCustomer, city);
    }
    
    public void updateZipPostalCode(String zipPostalCode){
        if (zipPostalCode.toLowerCase().equals("random")) {
        	zipPostalCode = CommonMethods.getRandomNumber(5);
        }    	
        clearAndSend(bZipPostalOnCustomer, zipPostalCode);
    }    

	public void validateCustomerDetails() {    
		Assert.assertTrue(driver.findElement(bfooterNameOfCustomer).getText().trim().equals(getFirstName() + " " + getLastName()) ,"Validate Customer Name");		
	}

    public void updateCustomerNotes(String customerNotes){
        if (customerNotes.toLowerCase().equals("random")) {
        	customerNotes = "Customer Notes " + CommonMethods.getRandomText(6) + " " + CommonMethods.getRandomText(4);
        }    	
        clearAndSend(bCustomerNotesEditor, customerNotes);
        setCustomerNotes(customerNotes);    
    }  

	public void validateCustomerNotes() {    
		Assert.assertTrue(driver.findElement(bCustomerNotesEditor).getText().trim().equals(getCustomerNotes()) ,"Validate Customer Notes");		
	}    
	
	public void validateAppointmentNumber() {    
		Assert.assertTrue(AM_DashboardPage.appointmentNumber.equals(ACI_CustomerPage.apptNumber) ,"Validate Appointment Number on Existing appointment pop up");		
	}   
	
    public  void closeExistingAppointment(){
        try{
            WebElement elUser = sWait.until(conditionClick(bCloseApptBtn));
            clickElementTimesTillDisappear(elUser,8);
            System.out.println("Clicked closed button on existing appointment");
           log.info(" -- Click close button with existing appointment --- ");
        } catch (TimeoutException te){
            System.out.println(te.getMessage());
            log.info("Catch block of closeExistingAppointment function");
        }
    }	
    
    public void captureCustomerName(){																										// Handler when County value is blank
    	setCustomerName(driver.findElement(bfooterNameOfCustomer).getText().trim());
    }    
    	
}
