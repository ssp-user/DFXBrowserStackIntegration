package com.automation.pages.advisor_checkin;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ACI_FramePage extends WebPage {

    protected static ArrayList<String> selectedItemList;

    //Advisor check in version number
    private static final By bACVersion = By.xpath("//div[@class='app-version']");

    //next button on the advisor check in search page
    protected static final By bNextBtnAdVisorFrame = By.id("g-proceed");
   
    // Customer Tab
    protected static final By bCustomerTab = By.cssSelector("#menuTab>li[ng-click*='customer']");
    // Concerns Tab
    protected static final By bConcernsTab = By.cssSelector("#menuTab>li[ng-click*='concerns']");

    protected static By bTabWithName(String tabName){
        String checkLocator = "//li[@ng-click='selectForm(" + tabName.toLowerCase() + "Form)']";
        return By.xpath(checkLocator);
    }

    public boolean verifyTabActivated(String tabName) {
        return elementHasAttributeValue(bTabWithName(tabName), "class", "active");
    }

    // History Tab
    protected static final By bHistoryTab = By.cssSelector("#menuTab>li[ng-click*='history']");
    // Service Tab
//    protected static final By bServiceTab = By.cssSelector("#menuTab>li[ng-click*='services']");
    protected static final By bServiceTab = By.cssSelector("a[title*='SERVICES']");
    // Walk Round Tab
    protected static final By bWalkRoundTab = By.cssSelector("#menuTab>li[ng-click*='walkaround']");
    // Assign R.O. Tab
//    protected static final By bAssignROTab = By.cssSelector("#menuTab>li[ng-click*='roForm']");
    protected static final By bAssignROTab = By.xpath("//a[@choosed='#assign']/parent::li");
    // Authorization Tab
    protected static final By bAuthorizationTab = By.cssSelector("#menuTab>li[ng-click*='authorization']");
    protected static final By bCloseBtn = By.cssSelector("#modalCustomerResult div.modal-footer button");
    protected static final By bBackToSearchBtn = By.cssSelector("div[class='tab-pane active'] a[ng-click*='search'");
    protected static final By bAdvisorDropdown = By.cssSelector("div.advisorName.dropdown");

    protected static final By bSignOutBtn = By.cssSelector("div.advisorName.dropdown a");

    protected static final By bElsa2GoIcon = By.cssSelector("div[ng-class*='elsa2go' i]");

    protected static final By bServiceLaneReportIcon = By.cssSelector("div[ng-click*='servicelane' i]");

    protected static final By bServiceLaneLoadingSpinner = By.cssSelector("[id*='servicelanereportdialog' i] i.spinner");

    protected static final By bServiceCommReportIcon = By.cssSelector("div[ng-click*='ServiceComm']");
    
    protected static final By bNissanSelectBtn = By.xpath("//button[@type='button' and @class='btn btn-green' and text() = 'SELECT']");

    protected static final By bServiceCommReportMitsubishiIcon = By.xpath("//div[@ng-if='mrPdfButton.enabled']");

    protected static final By bServiceCommReportAudiIcon = By.xpath("//div[@class='elsa2go-button ng-scope']");

    protected static final By bServiceCommReportGMIcon = By.xpath("//div[@ng-if='visPdfButton.enabled']");

    protected static final By bServiceCommReportToyotaIcon = By.xpath("//div[@class='sl-report-button ng-scope']");

    protected static final By bAcknowledgeBtn = By.xpath("//button[@ng-click='toggleBrandedMessage()']");

    protected static final By bWorksheetIcon = By.xpath("//div[@ng-if='dwPdfButton.enabled']");

    // Nissan ONE TO ONE REWARDS NONE OF ABOVE
    protected static final By bNoneOfAboveBtn = By.xpath("//button[contains(text(),'NONE OF THE ABOVE')]");
    protected static final By bSelectBtn = By.xpath("//button[contains(text(),'SELECT')]");
    protected static final By bServiceCommLoadingSpinner = By.cssSelector("div[class*='report'] i.spinner");
    protected static final By bCancelBtn = By.cssSelector(".service-lane-report-dialog button[ng-click*='hide']");
    protected static final By bDmsErrorBar = By.cssSelector("#dmsPushErrors h3");
    protected static final By bErrorIcon = By.cssSelector("#g-DMS i");
    protected static final By bNewArrivalNotification = By.cssSelector("NewVehicleArrived");
    protected static final By bNewArrival_ClickToViewBtn = By.cssSelector("#NewVehicleArrived h3 + div button[class*='blue']");
    protected static final By bNewArrivalIcon = By.cssSelector("#g-NewVehiclArrived i");
    protected static final By bNewArrivalList_VIN = By.cssSelector("div[ng-repeat*='arrived' i] .span3 div:nth-child(4)");
    protected static final By bVehicleAcknoledgeBtnList = By.cssSelector("button[ng-click*='Acknowledged' i]");

    protected static final By bSpinner = By.cssSelector(".spinner");

    protected void waitSpinnerInvisible() {                                                                                                // Go to Customer Tab
        List<WebElement> elsSpinner = driver.findElements(bSpinner);
        pWait.until(ExpectedConditions.invisibilityOfAllElements(elsSpinner));
    }

    protected static final By bACversion = By.cssSelector("div.app-version");

    public void goToCustomerTab() {
//         waitSpinnerInvisible();
        pWait.until(ExpectedConditions.invisibilityOfElementLocated(bSpinner));
//        dWait.until(conditionVisible(bCustomerTab)).click();
        moveToClick(bCustomerTab);
//         driver.findElement(bCustomerTab).click();
    }

    public void goToConcernsTab() {                                                       // Go to Concerns Tab
        dWait.until(conditionVisible(bConcernsTab)).click();
    }

    public void goToHistoryTab() {                                                      // Go to History Tab
        dWait.until(conditionVisible(bHistoryTab)).click();
    }

    public void goToServiceTab() {                                                    // Go to Service Tab
        waitSpinnerInvisible();
        WebElement element = pWait.until(conditionVisible(bServiceTab));
        sleep(500);
        clickElementWithSeconds(bServiceTab,2000);
//        element.click();
//        moveToClick(element);
    }

    public void goToWalkRoundTab() {                                              // Go to Walk Round Tab
        dWait.until(conditionVisible(bWalkRoundTab)).click();
    }

    public void goToAssignROTab() {                                                   // Go to Assign R.O. Tab
        sleep(2000);
        dWait.until(conditionVisible(bAssignROTab)).click();
    }

    public void goToAuthorizationTab() {                                              // Go to Authorization Tab
        dWait.until(conditionVisible(bAuthorizationTab)).click();
    }

    public void goToElsa2Go() {                                                       // Go to Else2Go
        dWait.until(conditionVisible(bElsa2GoIcon)).click();
    }

    public boolean isACVersionNoMatch(String expectedVersionNo) {
        long startTime = System.currentTimeMillis();
        String actualVersionNo = "";
        while (!actualVersionNo.equals(expectedVersionNo)) {
            actualVersionNo = driver.findElement(bACVersion).getAttribute("innerHTML");
            sleep(500);
            if ((System.currentTimeMillis() - startTime) > 10000) {
                break;
            }
        }
        if (actualVersionNo.equals(expectedVersionNo)) {
            System.out.println("<====== AC version number '" + actualVersionNo + "' matches ======>");
            return true;
        } else {
            System.out.println("<====== AC version number '" + actualVersionNo + "' not match expected version number '" + expectedVersionNo + "' ======>");
            return false;
        }
    }

    public String getAdisorVersion() {
        String aVer = driver.findElement(bACVersion).getText().trim();
        int iLong = aVer.length();
        int idx = 999;
        for (int i = 0; i < iLong; i++) {
            char num = aVer.charAt(i);
            if ((num < '9') & (num > '0')) {
                idx = i;
                break;
            }
        }
        if (idx == 999) {
            return " Version Not found ";
        } else {
            return aVer.substring(idx);
        }
    }

    public void clickNextBtnOnAdvisorFrame() {
        clickElementWithException(bNextBtnAdVisorFrame);
    }

    public void signOut() {                                                                                                            // Sign Out
        waitSpinnerInvisible();
        clickElementWithSeconds(bAdvisorDropdown, 2000);
        pWait.until(conditionVisible(bSignOutBtn)).click();
    }

}


