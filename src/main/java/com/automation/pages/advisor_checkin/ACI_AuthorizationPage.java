package com.automation.pages.advisor_checkin;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;

import java.util.Iterator;
import java.util.List;

public class ACI_AuthorizationPage extends ACI_FramePage {

    private static Logger log = Logger.getLogger(ACI_AuthorizationPage.class);
    //page object
    //authorized on the authorization page
    private static By bAuthorized = By.xpath("//label[@title='I AUTHORIZE COMPLETION OF REQUESTED SERVICES']");

    //registration status terms
    private static By bRegField(String regField) {
        String locator = "//div[@class='modal-footer']//*[contains(text(),'" + regField + "')]";
        return By.xpath(locator);
    }

    //PDF signature location
    private static By bSignature = By.id("signature");

    //PDF save
    private static By bSaveSignature = By.id("saveSign");

    //maintenance menu pdf show
    private static By bMMShow = By.xpath("//button[@ng-click='showMmPdf()']");

    //pre-service write-up pdf show
    private static By bRorShow = By.xpath("//button[@ng-click='showRorPdf()']");

    //EVIR pdf show
    private static By bEvirShow = By.xpath("//button[@ng-click='showEvirPdf()']");

    //parts pick list
    private static By bPartsPickShow = By.xpath("//button[@ng-click='showPartsPickPdf()']");

    //Complete the AD flow
    //private static By bCompleteOnAuthorization = By.xpath("//span[contains(text(),'COMPLETE')]");
    private static By bCompleteOnAuthorization = By.cssSelector(".botButton [ng-click*='RepairOrder']");

    //RO push load
    private static By bROPushLoad = By.xpath("//div[@class='modal-footer']//i[@class='spinner']");

    //RO posh error message
    private static By bROPushError = By.xpath("//label[@ng-show='!rorCompleted && rorError']");

    //RO push check mark
    private static By bROPushCheckMark = By.xpath("//div[@id='modalCustomerResult']//span[contains(@class,'custom-checkbox')]/../../../div[not(contains(@class,'ng-hide'))]");

    //RO push check mark selected
    private static By bPDFCheckMarkSelected = By.xpath("//div[@id='modalCustomerResult']//span[contains(@class,'custom-checkbox selected')]");

    //	@FindBy(css="#modalCustomerResult.modal span.custom-checkbox")
//    @FindBy(css="div[style*='block'] #modalCustomerResult span.custom-checkbox.selected")
//    protected static List<WebElement> pdfReadyCheckMarkList;
    private static By pdfReadyCheckMarkList = By.cssSelector("div[style*='block'] #modalCustomerResult span.custom-checkbox.selected");

    //    @FindBy(css="#authorization #g-sGenerate")
//    private static WebElement quoteBtn;
    private static By bquoteBtn = By.cssSelector("#authorization #g-sGenerate");     // Generate Quote Button

    //    @FindBy(css="button[ng-click*='generateMenu']")
//    private static WebElement menuBtn;
    private static By bmenuBtn = By.cssSelector("button[ng-click*='generateMenu']");    // Generate Maintenance Menu Button

    //    @FindBy(css="#modalCustomerResult button[ng-click*='customer']")
//    private static WebElement customerQuoteBtn;
    private static By bcustomerQuoteBtn = By.cssSelector("#modalCustomerResult button[ng-click*='customer']");  // Generate Customer Service Quote Button


    //    @FindBy(css="#modalCustomerResult button[ng-click*='dealer']")
//    private static WebElement internalQuoteBtn;
    private static By binternalQuoteBtn = By.cssSelector("#modalCustomerResult button[ng-click*='dealer']"); // Generate Internal Service Quote Button


    //    @FindBy(css="#modalCustomerResult button[ng-click*='hide']")
//    private static WebElement serviceQuoteCloseBtn;
    private static By bserviceQuoteCloseBtn = By.cssSelector("#modalCustomerResult button[ng-click*='hide']");

    // Warning Message Title
//    @FindBy(css="div.modal.hide.fade.ng-scope.in > div > div > div.modal-header > h3")
//    private static WebElement warningMsg;
    private static By bwarningMsg = By.cssSelector("div.modal.hide.fade.ng-scope.in > div > div > div.modal-header > h3");

    // Authorize check mark
//    @FindBy(css=".custom-checkbox.checkbox-signature")
//    private static WebElement authorizeCheck;
    private static By bauthorizeCheckLocator = By.cssSelector(".custom-checkbox.checkbox-signature");


    // Override Button on Warning Msg
//    @FindBy(css="div.modal.hide.fade.ng-scope.in > div > div > form > div > button:nth-child(2)")
//    private static WebElement overrideBtn;
    private static By boverrideBtn = By.cssSelector("div.modal.hide.fade.ng-scope.in > div > div > form > div > button:nth-child(2)");

    // Spinner for page to load
    private static By spinnerLocator = By.cssSelector("div.modal.hide.fade.in.ng-scope div.modal-footer i.spinner");

    private static By menuSpinnerLocator = By.cssSelector("#serviceAppointments > i");											// Spinner locator for Generate Menu

    private static By quoteSpinnerLocator = By.cssSelector("div.modal.in #modalCustomerResult i.spinner");

//    @FindBy(css="#dmsPushErrors h3")
//    protected static WebElement dmsErrorBar;						// DMS Push Error Message
    private  static By bDmsErrorBar = By.cssSelector("#dmsPushErrors h3");     	// DMS Push Error Message

//    @FindBy(css="#g-DMS i")
//    protected static WebElement errorIcon;							// Error Icon indicating error occurred
    private  static By bErrorIcon = By.cssSelector("#g-DMS i"); 						// Error Icon indicating error occurred

    //RO push terms
    private static By bPushTermGreenCheckMark(String roPushTerm) {
        String locator = "//span[text()='" + roPushTerm + "']/../span[contains(@class,'selected')]";
        return By.xpath(locator);
    }

    //back to search blue button after completion
    public static By bBackToSearch = By.xpath("//*[@id='modalCustomerResult']/form/div/div[3]/a/span[contains(text(),'BACK TO SEARCH')]");

    public void authorized() {
//        dWait.until(conditionVisible(bAuthorized)).click();
//        clickElementWithException(bauthorizeCheckLocator);
        hLightElement(bauthorizeCheckLocator);
//        actionClick(bauthorizeCheckLocator);
//        moveToClick(bauthorizeCheckLocator);
        jsClick(bauthorizeCheckLocator);
//        dWait.until(conditionClick(bauthorizeCheckLocator)).click();
    }

    public boolean verifyRegistrationField(String regField) {
        try {
            sWait.until(conditionVisible(bRegField(regField)));
            return true;
        } catch (WebDriverException e) {
            System.out.println("<====== Field '" + regField + "' not found on page, test failed ======>'");
            return false;
        }
    }

    public void clickOnRegistrationYesNoToggle(String toggleValue) {
        clickElementWithException(bRegField(toggleValue));
    }

    public void signed() {
        clickElementWithException(bSignature);
        dWait.until(conditionVisible(bSaveSignature)).click();
    }

    public void clickOnCompletedAfterSign() {
        waitForElementVisibleWithException(bCompleteOnAuthorization);
        try {
            driver.findElement(bCompleteOnAuthorization).click();
        } catch (WebDriverException e) {
            try {
                sWait.until(conditionClick(bSignature)).click();
                sWait.until(conditionClick(bSaveSignature)).click();
            } catch (WebDriverException ex) {
                //already signed
            }
            jsClick(bCompleteOnAuthorization);
        }
        waitForROPush();
        warningMsgHandler();
    }

    public void waitForROPush() {
        waitForElementHasAttributeValue(bROPushLoad, "style", "display: none;");
        pWait.until(conditionClick(bBackToSearch));
    }

    public boolean verifyROPushStatus() {
        waitForROPush();
        int checkMarkTotal = driver.findElements(bROPushCheckMark).size();
        int checkMarkCompleted = driver.findElements(bPDFCheckMarkSelected).size();
        System.out.println("checkMarkTotal  ... is   " + checkMarkTotal);
        System.out.println("checkMarkTotalcheckMarkCompleted  ... is   " + checkMarkCompleted);
        if (driver.findElement(bROPushError).isDisplayed()) {
            String errorMsg = driver.findElement(bROPushError).getText();
            System.out.println("<====== " + errorMsg + " Error show, RO push failed ======>");
            return false;
        }
        if (driver.findElement(bROPushLoad).getAttribute("style").equals("display: none;") && (checkMarkTotal == checkMarkCompleted)) {
            System.out.println("<====== RO push completed ======>");
            return true;
        } else {
            System.out.println("<====== RO push failed ======>");
            return false;
        }
    }

    public String  msgPushError() {
        if (driver.findElement(bDmsErrorBar).isDisplayed() ){
            return "DMS Push Error Bar Showing ";
        }else if (driver.findElement(bErrorIcon).isDisplayed()) {
            return "DMS Push Error Icon Showing ";
        } else if ( driver.findElement(bROPushError).isDisplayed()) {
            String errorMsg = driver.findElement(bROPushError).getText();
            return errorMsg;
        }else {
          return "PASS";
        }
    }

    public boolean verifyRoPushTermsGreenCheckMark(String roPushTerms) {
        try {
            driver.findElement(bPushTermGreenCheckMark(roPushTerms)).isDisplayed();
            System.out.println("<====== '" + roPushTerms + "' shows green check mark ======>");
            return true;
        } catch (WebDriverException e) {
            System.out.println("<====== '" + roPushTerms + "' check mark grey out, test fail ======>");
            return false;
        }
    }

    public void clickMaintMenuBtn() {
        dWait.until(conditionClick(bmenuBtn)).click();
    }

    public void clickQUOTEButton() {
        dWait.until(conditionClick(bquoteBtn)).click();
    }

    public boolean clickOnMMenuPDFShow() {
        try {
            WebElement element = driver.findElement(bMMShow);
            if (element.isDisplayed()){
                moveToClick(element);
                return true;
            }else{
                return false;
            }
        }catch (NoSuchElementException ne){
            return false;
        }
    }

    public boolean clickOnWriteUpPDFShow() {
        try {
            WebElement element = driver.findElement(bRorShow);
            if (element.isDisplayed()){
                element.click();
                return true;
            }else{
                return false;
            }
        }catch (NoSuchElementException ne){
            return false;
        }
    }

    public boolean clickOnEvirPDFShow() {
        try {
//        clickElementWithException(bEvirShow);
            WebElement element = driver.findElement(bEvirShow);
            if (element.isDisplayed()){
                element.click();
                return true;
            }else{
                return false;
            }
        }catch (NoSuchElementException ne){
            return false;
        }
    }

    public boolean clickOnPartsPickPDFShow() {
        try {
//        dWait.until(conditionClick(bPartsPickShow)).click();
			dWait.until(ExpectedConditions.elementToBeClickable(bPartsPickShow));			
            WebElement element = driver.findElement(bPartsPickShow);
            if (element.isDisplayed()){
                element.click();
                return true;
            }else{
                return false;
            }
        }catch (NoSuchElementException ne){
            return false;
        }
    }

    public void clickShowCustomerQuote() {
        dWait.until(conditionClick(bcustomerQuoteBtn)).click();
    }

    public void clickShowInternalQuote() {
        dWait.until(conditionClick(binternalQuoteBtn)).click();
    }

    public void clickCloseBtnGenerateQuote() {
        dWait.until(conditionClick(bserviceQuoteCloseBtn)).click();
    }

    private void warningMsgHandler() {                                                                                    // click override if warning message come up
        sleep(1000);
        try {
            if (driver.findElement(bwarningMsg).isDisplayed()) {
                log.info("Warning Msg Found");
                moveToClick(boverrideBtn);
            }
        } catch (NoSuchElementException e) {

        }
    }

    public void waitForPDFReady(){																								// Wait for all PDF are generated
        //		wait.until(ExpectedConditions.elementToBeClickable(maintenanceMenuShowBtn));
        try {
            sWait.until(conditionVisible(spinnerLocator));
        }catch (TimeoutException ee ){
            //
        }
        setWait(driver);
//        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
        dWait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
        SyncPage.getWindowsHandles(driver);
        SyncPage.getParentWindowID(driver);
    }

    public void waitForMaintMenuReady(){																						// Wait for Maintenance Menu PDF generated
        setWait();
        dWait.until(ExpectedConditions.visibilityOfElementLocated(menuSpinnerLocator));
        System.out.println("   ---   this is ... diaply " + driver.findElement(menuSpinnerLocator).isDisplayed());
        dWait.until(ExpectedConditions.invisibilityOfElementLocated(menuSpinnerLocator));
        SyncPage.getWindowsHandles(driver);
        SyncPage.getParentWindowID(driver);
    }

    public void waitForServiceQuoteReady(){																						// Wait for Quote PDF Generated
//		wait.until(ExpectedConditions.visibilityOfElementLocated(quoteSpinnerLocator));
        setWait();
        dWait.until(ExpectedConditions.visibilityOfElementLocated(bcustomerQuoteBtn));
        System.out.println("this is ... diaply " + driver.findElement(quoteSpinnerLocator).isDisplayed());
        dWait.until(ExpectedConditions.invisibilityOfElementLocated(quoteSpinnerLocator));
        SyncPage.getWindowsHandles(driver);
        SyncPage.getParentWindowID(driver);
    }

    private boolean isPdfGenerated(List<WebElement> webElementList){		// This is to check all PDF has been generated
        int count = 0;
        int listSize = webElementList.size();
        for(Iterator<WebElement> ii = webElementList.iterator(); ii.hasNext();){
            WebElement temp = ii.next();
            WebElement tempText = temp.findElement(By.xpath(".//following-sibling::span"));

            if(!tempText.getText().trim().toUpperCase().contains("SERVICE LANE UPDATED")){		// Service Lane Update is new added, and for now not checking anything.

                if((temp.getCssValue("background-position")).contains("0px -58px")){
                    count++;
                }
            } else {
                listSize--;
            }
        }
        if(count==listSize){
            return true;
        } else {
            return false;
        }
    }

    public boolean isGenerateQuotePdf(){		// This is to check all PDF has been generated
        waitForServiceQuoteReady();
        List<WebElement> list = driver.findElements(pdfReadyCheckMarkList);
        return isPdfGenerated(list);
    }

    public boolean isGenerateROPushPdf(){		// This is to check all PDF has been generated
        waitForPDFReady();
        List<WebElement> list = driver.findElements(pdfReadyCheckMarkList);
        return isPdfGenerated(list);
    }
    
    public void validateSurveyHeader(String surveyType) {
        CommonMethods.verifyElementExists(driver.findElement(By.xpath("//span[@class='title ng-binding' and text()='"+surveyType+"']")));        
    }    

}
