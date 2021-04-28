package com.automation.pages.tech_inspection.dashboard;

import com.automation.utils.otherUtils.CommonMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.pages.common.WebPage;

public class CreateMPIPage extends WebPage {

    private final String  attributeElement="Value", attributeValue = "Complete";



     //  Below Page Elemnts are hide after close , Search section
    private static By bTextVIN= By.id("txtVINDecode");   //
    private static By bBtnDecodeVIN = By.id("btnDecodeVIN");  // Decode Vin Button
    private static By bTxtCustomerName = By.id("txtCustomerNameSearch");
    private static By bTxtCustomerPhone = By.id("txtCustomerPhoneSearch");
    private static By bBtnCustomerSearch = By.id("btnCustomerNamePhoneSearch");

    // Vehicle section
    @FindBy(id = "Make")
    private WebElement selectMake;
    private static By bMakeSelect = By.id("Make");
    @FindBy(id = "Year")
    private WebElement selectYear;
    private static By bYearSelect = By.id("Year");
    private static By bModelSelect = By.id("Model");
    private static By bTransmissionSelect = By.id("Transmission");
    private static By bCylinderSelect = By.id("Cylinder");
    private static By bDriveSelect = By.id("Drive");

    @FindBy(id = "rblistMileageMonth_0")
    private WebElement mileageCheckBox;
    private static By bMileageRB = By.id("rblistMileageMonth_0");
    private static By bMileagesTextBox = By.id("txtMileages");

    // Customer Information section
    private static By bFNameTextBox = By.id("txtFName");
    private static By bLNameTextBox = By.id("txtLName");
    private static By bAddressText = By.id("txtCAddress");
    private static By bCityText = By.id("txtCCity");
    private static By bPostalText = By.id("txtCPostal");
    private static By bCountrySelect= By.id("ddlCCountry");
    private static By bStateSelect = By.id("ddlCStates");
    private static By bCountySelect= By.id("ddlCCounty");
    private static By bDefaultPhoneText = By.id("txtDefaultPhone");
    private static By bCellPhoneText = By.id("txtCelPhone");
    private static By bEmailText= By.id("txtCEmail");
    private static By bContactMethodSelect = By.id("ddlContactMethod");
    private static By bPreferlanguageSelect = By.id("ddlPreferedLanguage");

    // Customer Information section
    private static By bSAdvisorSelect = By.id("ddlSA");
    private static By bSTechnicianSelect = By.id("ddlTechnician");
    private static By bTagText = By.id("txtTag");
    private static By bROtext = By.id("txtRONumberForEvir");
    private static By bLotNumberText= By.id("txtLotNumber");
    private static By bPlateText = By.id("txtNumberPlate");
    private static By bPromiseDateSelect= By.id("ddlPromiseDate");
    private static By bPromiseTimeSelect = By.id("ddlPromiseTime");
    private static By bInspectionTypeSelect= By.id("ddlInspectionType");
    private static By bTransportationSelect = By.id("ddlTransportation");
    private static By bWashCheckBox = By.id("chkWash");
    private static By bCommentsText = By.id("txtCustComments");

    // Three Buttons
    private static By bCompleteBtn = By.id("btnComplete");
    private static By bClearAllBtn = By.id("resetEvirBtn");
    private static By bCloseBtn = By.cssSelector("#create-evir-model-content>div.modal-footer>button:nth-child(2)");

    //VIN Search Section -- update for french version
    //private static By bVINSection = By.xpath("//a[contains(text(),'Customer/VIN Search')]/preceding-sibling::span");
    private static By bVINSection = By.xpath("(//form[@id='form1']/div/h3/a)[1]");

    private static Logger log = Logger.getLogger(TechInspectSearchPage.class.getName());

    //page body (use for detect if modal open)
    private static By bPageBody = By.xpath("//body");

    //car infomation (use for decode vin load)
    private static By bCarInfo = By.id("lblVehicleInfoHeader");

    // Constructor
    public CreateMPIPage() {
        PageFactory.initElements(driver, this);
    }

    private Boolean isPageLoad() {
        return dWait.until(conditionForPage()).isDisplayed();
    }



    @Override
    protected ExpectedCondition<WebElement> conditionForPage() {
        return ExpectedConditions.visibilityOfElementLocated(bBtnCustomerSearch);
    }

    //wait for the open modal to disappear
    public void waitForOpenModalDisappear(){
        waitForElementHasAttributeValue(bPageBody,"class","");
    }

    // VIN Search Section
    public void verifyVINSearchSectionIsClosed(){
        try{
            if(webElementHasClass(bVINSection,"ui-icon-triangle-1-e")){
                dWait.until(conditionVisible(bVINSection)).click();
                System.out.println("<======VIN Search section is closed now clicked Open======>");
            }
        }catch (org.openqa.selenium.TimeoutException ex){
            System.out.println("<====== Not finding triangle ======>");
        }
    }

    public void setVIN(String text) {
        verifyVINSearchSectionIsClosed();
        clickElementWithException(bTextVIN);
        clearAndInputElementWithException(bTextVIN, text);
    }

    public void decodeVin(){
        dWait.until(conditionClick(bBtnDecodeVIN)).click();
        try{
            dWait.until(conditionVisible(bCarInfo)); //this is to verify if vin decoded
        }catch(org.openqa.selenium.TimeoutException ex){
            clickElementWithException(bBtnDecodeVIN);
            dWait.until(conditionVisible(bCarInfo));
        }
    }

    public void setCustomerName(String text) {
        clearAndInputElementWithException(bTxtCustomerName,text);
    }

    public void setCustomerPhone(String text) {
        clearAndSend(bTxtCustomerPhone, text);
    }


    // Vehicle section
    public void selectMake(String text) throws Throwable {
        selectDropList(bMakeSelect, text);
    }

    public void selectYear(String text)throws Throwable {
        selectDropList(bYearSelect, text);
    }

    public void selectModel(String text) throws Throwable {
        selectDropList(bModelSelect, text);
    }

    public void selectTransmission(String text)  {
        selectDropList(bTransmissionSelect, text);
    }

    public void selectEngineSize(String text) {
        selectDropList(bCylinderSelect, text);
    }

    public void selectDriveTrain(String text) {
        selectDropList(bDriveSelect, text);
    }


    public void setMileages(String text) {
        clearAndInputElementWithException(bMileagesTextBox, text);
    }

    public void checkMileages() {
        dWait.until(conditionClick(bMileageRB)).click();
    }

    public void setMonths(String text) {
//        clearAndSend(bMileagesTextBox, text);
    }

    public void checkMonths() {
//        dWait.until(conditionClick(bMileagesTextBox)).click();
    }

    // Customer Information / Service Hostory  section
    public void setFirstName(String text) {
        clearAndSend(bFNameTextBox, text);
    }

    public void setLastName(String text) {
        clearAndSend(bLNameTextBox, text);
    }

    public void checkBusiness(String text) {
//        checkBox(bWashCheckBox, text);
    }

    public void setAddress(String text) {
        clearAndSend(bAddressText, text);
    }

    public void setCity(String text) {
        clearAndSend(bCityText, text);
    }

    public void setPostalCode(String text) {
        clearAndSend(bPostalText, text);
    }

    public void selectCountry(String text) {
        selectDropList(bCountrySelect, text);
    }
    public void selectState(String text) {
        pWait.until(conditionClick(bStateSelect));
        selectDropList(bStateSelect, text);
    }

    public void selectCounty(String text) {
        pWait.until(conditionClick(bCountySelect));
        selectDropList(bCountySelect, text);
    }

    public void setDefaultPhone(String text) {
        clearAndSend(bDefaultPhoneText, text);
    }

    public void setCellPhone(String text) {
        clearAndSend(bCellPhoneText, text);
    }

    public void setEmail(String text) {
        clearAndSend(bEmailText, text);
    }

    public void selectContactMethod(String text) {
        selectDropList(bContactMethodSelect, text);
    }
    public void selectLanguage(String text) {
        selectDropList(bPreferlanguageSelect, text);
    }


    // Customer Service Information section
    public void selectAdvisor(String text) {
        selectDropList(bSAdvisorSelect, text);
    }

    public void selectTechnician(String text) {
        selectDropList(bSTechnicianSelect, text);
    }

    public void setTag(String text) {
        long startTime = System.currentTimeMillis();
        CommonMethods cm = new CommonMethods();
        cm.getRandomText(5);
        while(!tagStatus()){
            clearAndInputElementWithException(bTagText,tp.randomText);
            if((System.currentTimeMillis()-startTime)> 20000){
                break;
            }
            sleep(1000);
        }
    }

    public boolean tagStatus(){
        return jsGetAttribute(bTagText,"value").equals(tp.randomText);
    }

    public void setRO(String text) {
        clearAndInputElementWithException(bROtext,text);
        scrollPageDown(500);
    }

    public void setLotNumber(String text) {
        clearAndSend(bLotNumberText, text);
    }

    public void setLicensePlate(String text) {
        clearAndSend(bPlateText, text);
    }

    public void selectPromiseDate(String text) {
        if (text.startsWith("+")){
            int itext = Integer.valueOf(text.substring(1)) + 1;
            text = String.valueOf(itext);
        }
        selectDropListElementWithException(bPromiseDateSelect, text);
    }

    public void selectPromiseTime(String text) {
        selectDropListElementWithException(bPromiseTimeSelect, text);
    }

    public void selectQuarterDate(String text) {
//        selectDropList(bPromiseDateSelect, text.substring(1));
    }

    public void selectQuarterTime(String text) {
//        selectDropList(bPromiseTimeSelect, text);
    }

    public void selectInspectionType(String text) {
        long startTime = System.currentTimeMillis();
        if(text.equals("Audi Canada 29 Point Inspection")){
            try{
                selectDropList(bInspectionTypeSelect,text);
            }catch (WebDriverException e){
                text = "Audi Canada 29 Point Inspection template";
            }
        }
        selectDropListElementWithException(bInspectionTypeSelect, text);
        while(!tagStatus()){
            clearAndInputElementWithException(bTagText,tp.randomText);
            if((System.currentTimeMillis()-startTime)> 20000){
                break;
            }
            sleep(1000);
        }
    }

    public void selectTransportation(String text) {
        selectDropList(bTransportationSelect, text);
    }

    public void checkSaveParts(String text) {
        checkBox(bWashCheckBox, text);
    }

    public void checkCarWash(String text) {
        checkBox(bWashCheckBox, text);
    }

    public void checkGlovebox(String text) {
        checkBox(bWashCheckBox, text);
    }

    public void checkSparetire(String text) {
        checkBox(bWashCheckBox, text);
    }

    public void setComments(String text) {
        clearAndSend(bCommentsText, text);
    }

    public void doComplete() {
//        long startTime = System.currentTimeMillis();
        clickElementWithException(bCompleteBtn);
//        try{
//            while(driver.findElements(bCompleteBtn).size()!=0){
//                driver.findElement(bCompleteBtn).click();
//                System.out.println("<====== Complete button still showing up, will click again ======>");
//                if((System.currentTimeMillis()-startTime)> 5000){
//                    break;
//                }
//                sleep(1000);
//            }
//        }catch(WebDriverException e){
//            System.out.println("<====== search/create modal already closed ======>");
//        }
//        switchFrame();
//        try {
//            waitForOpenModalDisappear();
//        }catch (org.openqa.selenium.StaleElementReferenceException ex){
//            System.out.println("<====== search/create modal already closed ======>");
//        }
    }

    public void doClearAll() {
        WebElement clearAllBtn = dWait.until(conditionClick(bClearAllBtn));
        moveToClick(clearAllBtn);
    }

    public void doClose() {
        WebElement closeBtn = dWait.until(conditionClick(bCloseBtn));
        moveToClick(closeBtn);
        switchFrame();
    }

//    public void openCreateMPIwindow() {
//        txtdName.clear();
//        txtdName.sendKeys(dealerName);
//        txtdCode.clear();
//        txtdCode.sendKeys(dealerCode);
////        dWait.until(conditionClick(searchBtn)).click();
//        searchBtn.click();
//        firstDealer.click();
////        dWait.until(conditionClick(selectBtn)).click();
//        selectBtn.click();
//    }

    @Override
    protected void switchFrame() {
        driver.switchTo().defaultContent();
    }

}


