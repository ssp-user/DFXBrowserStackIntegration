package com.automation.pages.tech_inspection.dashboard;

import com.automation.pages.tech_inspection.ServiceTab.InspectionTab;
import com.automation.utils.dataProvider.TestParameters;
import org.apache.log4j.Logger;

//import org.bouncycastle.util.io.StreamOverflowException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.pages.common.WebPage;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static com.automation.utils.dataProvider.TestParameters.randomText;

public class TechInspectSearchPage extends WebPage {

    InspectionTab iTab = new InspectionTab();

    private final String attributeElement = "Value", attributeValue = "Search";

    /*
     *    Below Page Elemnts are hide after fire the search function
     */

    private final String CREATEIFRAME = "createEvirIframe";   //  frame id

    @FindBy(id = "notif-counter-text")      //  result Counter link
    private WebElement searchCounterLink;
    private static By bSearchCounterLink = By.id("notif-counter-text");

    @FindBy(css = "#bs-example-navbar-collapse-1>div>ul>li:nth-child(3)>a")     // search Link
    private WebElement searchPageLink;
    private static By bSearchPageLink = By.cssSelector("#bs-example-navbar-collapse-1>div>ul>li:nth-child(3)>a");

    // fade in version of searchIcon
    private static By bFadeInSearchIcon = By.xpath("//div[@class='modal fade in'and @id='search-modal']");

    // Promiss time By include text = 'PROMISE TIME'
    private static By bPromisTime = By.id("dgEVIRList_ctl02_lnkPromiseTime");

    //Service Advisor on the header right
    private static By bServiceAdvisor = By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul/li/div[1]");

    // searched First line Vehicle Status  information
    @FindBy(xpath = "//*[@id='dgEVIRList']/tbody/tr[2]/td[1]/table/tbody/tr[1]/td[2]/b")
    private WebElement tagNumber;
    private static By bTagNumber = By.xpath("//*[@id='dgEVIRList']/tbody/tr[2]/td[1]/table/tbody/tr[1]/td[2]/b");

    @FindBy(xpath = "//*[@id='dgEVIRList']/tbody/tr[2]/td[2]/div/b")
    private WebElement customerName;
    private static By bCustomerName = By.xpath("//*[@id='dgEVIRList']/tbody/tr[2]/td[2]/div/b");

    private static By bVIN = By.xpath("//*[@id='dgEVIRList']/tbody/tr[2]/td[2]/table/tbody/tr[3]/td/b");

    @FindBy(css = "#dgEVIRList tr:nth-child(2)>td:nth-child(3)>p:nth-child(1)")      //  RO Number
    private WebElement ROnumber;
    private static By bROnumber = By.cssSelector("#dgEVIRList tr:nth-child(2)>td:nth-child(3)>p:nth-child(1)");

    @FindBy(id = "dgEVIRList_ctl03_litStatus")       //   Status src="images/EVIR25/Status/EN/status_1.png"
    private WebElement inpectStatus;
    private static By bInpectStatus = By.id("dgEVIRList_ctl03_litStatus");
    private static By bImgDelete = By.id("dgEVIRList_ctl03_imgDeleteEVIR");

    //get status by VIN
    private static By bInspectStatusByVin(String vIN){
        //String checkLocator = "(//b[contains(text(),'"+vIN+"')]/parent::td/parent::tr/parent::tbody/parent::table/parent::td/following-sibling::td[5]/div/img)[1]";
        String checkLocator = "//b[contains(text(),'"+vIN+"')]/parent::td/parent::tr/parent::tbody/parent::table/parent::td/preceding-sibling::td/table/tbody/tr/td/b[contains(text(),'"+randomText+"')]/parent::td/parent::tr/parent::tbody/parent::table/parent::td/following-sibling::td[6]/div/img";
        return By.xpath(checkLocator);
    }

    //get status by VIN with substatus
    private static By bInspectRevisedStatusByVin(String vIN){
        //String checkLocator = "(//b[contains(text(),'"+vIN+"')])[1]/parent::td/parent::tr/parent::tbody/parent::table/parent::td/following-sibling::td[5]/div/img/following-sibling::span[contains(text(),'Revised')]";
        String checkLocator = "//b[contains(text(),'"+vIN+"')]/parent::td/parent::tr/parent::tbody/parent::table/parent::td/preceding-sibling::td/table/tbody/tr/td/b[contains(text(),'"+randomText+"')]/parent::td/parent::tr/parent::tbody/parent::table/parent::td/following-sibling::td[6]/div/img/following-sibling::span[contains(text(),'Revised')]";
        return By.xpath(checkLocator);
    }

    //status STARTED
    private static By bInspectStatusStarted = By.xpath("//img[@src='images/EVIR25/Status/EN/status_2.png']");

    //status CREATED
    private static By bInspectStatusCreated = By.xpath("//img[@src='images/EVIR25/Status/EN/status_1.png']");

    //status COMPLETED
    private static By bInspectStatusCompleted = By.xpath("//img[@src='images/EVIR25/Status/EN/status_3.png']");

    // OK button in Popup window`
    private static By bOkBtn = By.xpath("//div[@id='ui-id-1']/following-sibling::div[1]/div/button[1]");
    // Cancel button in Popup window
    private static By bCancelBtn = By.xpath("//button[contains(text(),'Cancel')]");

    //click on Created By drop down
    private static By bCreatedBy = By.xpath("(//span[@class='multiselect-selected-text'])[2]");

    //click on Select All
    private static By bSelectAll = By.xpath("(//span[@id='select-all-text'])[2]");

    // click +20x, +200y  to dismiss search Popup Windows
    private static By bSearchModel = By.cssSelector("#search-modal");

    /*
     *    Below Page search pop up page Elemnts are hide before click the search function
     */

    @FindBy(id = "txtVIN")      // VIN Text Field
    private WebElement vinTextBox;
    private static By bVinTextBox = By.id("txtVIN");

    @FindBy(id = "txtTagNumber")     // Tag Text Field
    private WebElement tagTextBox;
    private static By bTagTextBox = By.id("txtTagNumber");

    @FindBy(id = "txtLastName")      // Tag Text Field
    private WebElement lastNameTextBox;
    private static By bTextLastName = By.id("txtLastName");

    @FindBy(id = "txtRONumber")        // Tag Text Field
    private WebElement ROtextBox;
    private static By bTxtRONumber = By.id("txtRONumber");

    @FindBy(id = "ddlStatus")       // Status Select Box
    private WebElement statusSelect;
    private static By bStatusSelect = By.id("ddlStatus");

    @FindBy(id = "ddlFUIDCreated")     // CreateBy Select Box
    private WebElement createdSelect;
    private static By bCreatedSelect = By.id("ddlFUIDCreated");

    @FindBy(id = "btnEVIRSearch")   // Search Button
    private WebElement searchBtn;
    private static By bSearchBtn = By.id("btnEVIRSearch");

    //(xpath = "//div[@id='btnEVIRSearch']/preceding-sibling::input[1]")
    @FindBy(xpath = "//*[@id='btnEVIRSearch']/../input[@id='btnReset']")      //  Clear Selection Button
    private WebElement clearselectionBtn;
    private static By bClearselectionBtn = By.xpath("//*[@id='btnEVIRSearch']/../input[@id='btnReset']");

    //No matching records found
    private static By bNoMatchFound = By.xpath("//div[contains(text(),'No matching records found')]");

    @FindBy(id = "createEvirBtn")      //  Create MPI button
    private WebElement createMPIBtn;
    private static By bCreateMPIBtn = By.id("createEvirBtn");

    @FindBy(xpath = "(//span[text()='–'])[3]")
    private WebElement fromYearMinus;
    private static By bFromYearMinus = By.xpath("//label[@id='lblSearchByDateCreated']/parent::div//div[@class='dwwl dwrc dwwl2']//div[@class='dw-ul']");
    private static By bFromYearMinusSign = By.xpath("//label[@id='lblSearchByDateCreated']/parent::div//div[@class='dwwl dwrc dwwl2']//div[@class='dwwb dwwbm']");

    //label[@id='lblDateCreatedFrom']/..//div[@class='dwwl dwrc dwwl2']/div[1]

    @FindBy(xpath = "//label[@id='lblDateCreatedFrom']/..//div[@class='dwwl dwrc dwwl2']/div[1]")
    private WebElement fromYearPlus;
    private static By bFromYearPlus = By.xpath("//label[@id='lblDateCreatedFrom']/..//div[@class='dwwl dwrc dwwl2']/div[1]");

    @FindBy(id = "noSearchResultDiv")     // search no matched
    private WebElement noRecord;
    private static By bNoRecordMeg = By.id("noSearchResultDiv");

    private static By deleteErrorMsg = By.xpath("//h2[contains(text(),'This vehicle is currently with')]");

    private static By vINNUmber(String vIN){
        String checkLocator = "//tbody/tr[3]/td/b[contains(text(),'"+vIN+"')]";
        return By.xpath(checkLocator);
    }

    private static By bServiceLaneCount = By.id("notif-counter-text");

    private static Logger log = Logger.getLogger(TechInspectSearchPage.class.getName());

    // Constructor
    public TechInspectSearchPage() {
        PageFactory.initElements(driver, this);
    }

    private Boolean isPageLoad() {
        return dWait.until(ExpectedConditions.presenceOfElementLocated(bFadeInSearchIcon)).isDisplayed();
    }

    public String isUserLoginWithAD(){
        String userText = "";
        try{
            userText = dWait.until(ExpectedConditions.presenceOfElementLocated(bServiceAdvisor)).getText();
        }catch(org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
            try{
                System.out.println("<====== I see blank page after login TI ======>");
                pageRefresh();
                userText = pWait.until(ExpectedConditions.presenceOfElementLocated(bServiceAdvisor)).getText();
            }catch(org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException tex){
                userText = dWait.until(ExpectedConditions.presenceOfElementLocated(bServiceAdvisor)).getText();
            }
        }
        return userText;
    }

    public void clearSelection() {
        dWait.until(conditionVisible(bClearselectionBtn)).click();
    }

    public void pageRefresh(){
        driver.navigate().refresh();
    }

    @Override
    protected ExpectedCondition<WebElement> conditionForPage() {
        return ExpectedConditions.elementToBeClickable(bPromisTime);
    }

    public void openSearchPanel() {
        if (!isUserLoginWithAD().contains("Service Advisor")){
            //Boolean loaded = isPageLoad();
            //dWait.until(conditionVisible(bSearchPageLink)).click();
            if (driver.findElements(bPromisTime).size()!= 0) {
                dWait.until(conditionVisible(bSearchPageLink)).click();
            }
        }
    }

    public void createMPI() {
        //dWait.until(ExpectedConditions.invisibilityOfElementLocated(bCreateMPIBtn));
        long startTime = System.currentTimeMillis();
        try{
            dWait.until(conditionClick(bCreateMPIBtn)).click();
        }catch (org.openqa.selenium.WebDriverException ex){
            while(verifyIfElementShowOnThePage(bCreateMPIBtn)){
                dWait.until(conditionClick(bCreateMPIBtn)).click();
                if(!verifyIfElementShowOnThePage(bCreateMPIBtn)){
                    break;
                }else if((System.currentTimeMillis()-startTime)> 10000){
                    System.out.println("<====== Create MPI button can not be found ======>");
                    break;
                }
            }
        }
        switchFrame();
    }

    public void setVIN(String text) {
//        clearAndInput(bVinTextBox, text);
    }

    public void setTag(String text) {
        clearAndSend(bTagTextBox, text);
    }

    public void setLastName(String text) {
        clearAndSend(bTextLastName, text);
    }

    public void setRO(String text) {
        clearAndSend(bTxtRONumber, text);
    }

    public void selectStatus(String text) {
//        selectDropList(inpectStatus, text);
    }

    public void selectCreatedBy(String text) {
        //selectDropList(createdSelect, text);
    }

    public void selectAssignedTo(String text) {
//        selectDropList(createdSelect, text);
    }

    public String getVIN() {
        sleep(5000);
        return dWait.until(conditionVisible(bVIN)).getText().trim();
    }

    public String getTag() {
        return tagNumber.getText().trim();
    }

    public String getCustomerName() {
        return customerName.getText().trim();
    }

    public String getRO() {
        return dWait.until(conditionVisible(bROnumber)).getText().trim();
    }

    public String getAdvisor() {
        return "";
    }

    public String getTechnician() {
        return "";
    }

    public String getArrivalTime() {
        return "";
    }

    public String getPromiseTime() {
        return "";
    }

    public void setFromYear(String ch){
        int changedYear = 0;
        if(!(Integer.parseInt(ch)>0)){
            changedYear = 3960-(Integer.parseInt(ch.replaceAll("-",""))*40);
        }else{
            System.out.println("<====== please enter negative value in feature file ======>");
        }
        jsSetAttribute(bFromYearMinusSign,"style","dwwb dwwbm dwb-a");
        jsSetAttribute(bFromYearMinus,"style","-webkit-transform:translate3d(0,-"+Integer.toString(changedYear)+"px,0);");
    }

    public void setFromMonth(String add) {
        int change = Integer.valueOf(add.trim().substring(1));
    }

    public void setFromDay(String add) {
        int change = Integer.valueOf(add.trim().substring(1));
    }

    public void setToYear(String add) {
        int change = Integer.valueOf(add.trim().substring(1));
    }

    public void setToMonth(String add) {
        int change = Integer.valueOf(add.trim().substring(1));
    }

    public void setToDay(String add) {
        int change = Integer.valueOf(add.trim().substring(1));
    }

    public String performSearch() {
        String  result = "";
        dWait.until(conditionVisible(bSearchBtn)).click();
        try {
            result = dWait.until(conditionVisible(bNoRecordMeg)).getText();
        } catch (NoSuchElementException nex) {
            result = " ";
        } catch (Exception ex) {
            result = "";
            ex.getStackTrace();
        }
        return result ;
    }

    public String getInspectionStatus() {
        try{
            dWait.until(ExpectedConditions.stalenessOf(driver.findElement(bInpectStatus)));
        }catch (Exception ex){
            ex.getStackTrace();
        }
        String image = dWait.until(conditionVisible(bInpectStatus))
                .getAttribute("src");
        String status = "";
        if (!image.isEmpty()) {
            int begin = image.indexOf("/status_");
            int end = image.indexOf(".png");
            String imgSn = image.substring(begin + 8, end);
            switch (imgSn) {
                case "1":
                    status = "CREATED";
                    break;
                case "2":
                    status = "STARTED";
                    break;
                case "3":
                    status = "COMPLETED";
                    break;
                case "4":
                    status = "REVIEWED";
                    break;
                case "5":
                    status = "READY";
                    break;
                case "7":
                    status = "CONFIRMED";
                    break;
                case "9":
                    status = "PARTS REQUESTED";
                    break;
                case "10":
                    status = "READY FOR PROCESSING";
                    break;
                case "11":
                    status = "IN-REVISION";
                    break;
            }
        }
        return status;
    }

    public String getInspectionStatusByVin(String vIN){
        waitForTitle("MPI List");
//        iTab.waitForLoadingCircleToDisappearMPI();
//        String image = getElementAttributeWithException(bInspectStatusByVin(vIN),"src");
        String image = getElementAttribute(bInspectStatusByVin(vIN),"src");
        String status = "";
        if (!image.isEmpty()) {
            int begin = image.indexOf("/status_");
            int end = image.indexOf(".png");
            String imgSn = image.substring(begin + 8, end);
            switch (imgSn) {
                case "1":
                    status = "CREATED";
                    break;
                case "2":
                    status = "STARTED";
                    break;
                case "3":
                    status = "COMPLETED";
                    if(driver.findElements(bInspectRevisedStatusByVin(vIN)).size()!=0){
                        status = "COMPLETED - revised";
                    }
                    break;
                case "4":
                    status = "REVIEWED";
                    if(driver.findElements(bInspectRevisedStatusByVin(vIN)).size()!=0){
                        status = "REVIEWED - revised";
                    }
                    break;
                case "5":
                    status = "READY";
                    break;
                case "7":
                    status = "CONFIRMED";
                    if(driver.findElements(bInspectRevisedStatusByVin(vIN)).size()!=0){
                        status = "CONFIRMED - revised";
                    }
                    break;
                case "9":
                    status = "PARTS REQUESTED";
                    if(driver.findElements(bInspectRevisedStatusByVin(vIN)).size()!=0){
                        status = "PARTS REQUESTED - revised";
                    }
                    break;
                case "10":
                    status = "READY FOR PROCESSING";
                    break;
                case "11":
                    status = "IN-REVISION";
                    break;
            }
        }
        return status;
    }

    public void clickOnStatus(){
        dWait.until(conditionVisible(bInpectStatus)).click();
        waitForTitle("MPI Details");
    }

    public void clickOnStatusWithVin(String vIN){
//        try{
//            iTab.waitForLoadingCircleToDisappearMPI();
//        }catch(org.openqa.selenium.NoSuchElementException ex){
//            System.out.println("<====== no waiting circle on the page, page already completed load ======>");
//        }
        clickElementWithException(bInspectStatusByVin(vIN));
    }

    public void verifyEVIRLaunchTimeWithVin(String vIN, String expectedSeconds){
        long startTime = System.currentTimeMillis();
        long expectedSec = Integer.parseInt(expectedSeconds)*1000;
        try{
            iTab.waitForLoadingCircleToDisappearMPI();
        }catch(org.openqa.selenium.NoSuchElementException ex){
            System.out.println("<====== no waiting circle on the page, page already completed load ======>");
        }
        clickElementWithException(bInspectStatusByVin(vIN));

        long endTime = System.currentTimeMillis();
        if((endTime-startTime)<expectedSec){
            System.out.println("<====== EVIR launched in "+ TimeUnit.MILLISECONDS.toSeconds(endTime-startTime)+" seconds, test passed ======>");
        }else{
//            Assert.fail("<====== EVIR launched in "+ TimeUnit.MILLISECONDS.toSeconds(endTime-startTime)+" seconds, test failed ======>");
            System.err.println(" Warning !!! <====== EVIR launched in "+ TimeUnit.MILLISECONDS.toSeconds(endTime-startTime)+" seconds, test failed ======>");
        }
    }

    public void searchVehicleWithTag(String tAG){
        if(tAG.toLowerCase().equals("random")){
            tAG = randomText;
        }
        dWait.until(conditionVisible(bSearchPageLink)).click();
        setTag(tAG);
        performSearch();
    }

    public void selectCreatedByAllUser(String dropDownText){
        clickElementWithException(bCreatedBy);
        clickElementWithException(bSelectAll);
        clickElementWithException(bSearchBtn);
        iTab.waitForMPISearchToDisappearMPI();
        iTab.waitForLoadingCircleToDisappearMPI();
    }

    public void clickOnStarted(){
        dWait.until(conditionVisible(bInspectStatusStarted)).click();
    }

    public void clickOnCreated(){
        dWait.until(conditionVisible(bInspectStatusCreated)).click();
    }

    public void clickonCompleted(){
        dWait.until(conditionVisible(bInspectStatusCompleted)).click();
    }

    public void clickOnNoneCompletedStatus(){
        String result = getInspectionStatus();
        switch(result){
            case "STARTED":
                clickOnStarted();
                break;
            case "CREATED":
                clickOnCreated();
                break;
            case "COMPLETED":
                break;
        }
    }

    public void deleteFirstRecord() {
        dWait.until(conditionClick(bImgDelete)).click();
        dWait.until(conditionClick(bOkBtn)).click();
//        Boolean  sta  = dWait.pollingEvery(150, TimeUnit.MILLISECONDS)
//                 .withTimeout(500,TimeUnit.MILLISECONDS)
//                 .until(ExpectedConditions.stalenessOf(tagNumber));
    }

    public Boolean isCurrentlyWithMsgDisplayed(){
        if(driver.findElements(deleteErrorMsg).size()!= 0){
            return true;
        }else{
            return false;
        }
    }

    public void deleteAllRecord(){
        sleep(5000);
        String serviceLaneCountString = driver.findElement(bServiceLaneCount).getAttribute("innerHTML");
        int serviceLaneCount = Integer.parseInt(serviceLaneCountString);
        System.out.println("<====== There are "+serviceLaneCountString+" service lanes total ======>");
        for (int i = 1; i < serviceLaneCount; i++) {
            deleteFirstRecord();
            keyboardTab();
            System.out.println("<====== I delete No."+i+" records ======>");
        }
    }

    public void clickOnServiceLane(String vIN){
        dWait.until(conditionVisible(vINNUmber(vIN))).click();
    }

    public Boolean isRecordExist(){
        if(driver.findElements(bImgDelete).size()== 0 || isCurrentlyWithMsgDisplayed()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void switchFrame() {
        driver.switchTo().frame(CREATEIFRAME);
    }

}


