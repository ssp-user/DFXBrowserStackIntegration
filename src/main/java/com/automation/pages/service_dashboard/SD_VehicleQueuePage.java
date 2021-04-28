package com.automation.pages.service_dashboard;

import com.automation.pages.common.WebPage;
import com.automation.utils.dataProvider.TestParameters;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SD_VehicleQueuePage extends WebPage {

    //all web element locator
    //search vin field
    private static By bSearchField = By.id("search-input");

    //DASHBOARD button
    private static By bDashboardBtn = By.xpath("//a[contains(text(), 'Dashboard')]");

    //Current Lane filter button
    private static By bCurrentLaneFilterBtn = By.xpath("//button[@class='link link--primary c-lane-filter__show-dropdown']");

    //Matched lanes displayed
    private static By bLanesDisplayed = By.tagName("lane-item");

    //require future attention click icon
    private static By bVehicleQueueActived = By.xpath("//a[@class='link link--primary app-pages-nav__link active']");

    //Invalid text on VEHICLE QUEUE page
    private static By bInvalidText(String invalidText) {
        String locator = "//span[contains(text(), '" + invalidText + "')]";
        return By.xpath(locator);
    }

    //"was not found" msg on VEHICLE QUEUE page
    private static By bNotFoundMsg = By.xpath("//div[@class='h']/span[contains(text(), 'was not found')]");

    //vin number locator on vehicle queue page
    private static By bVinVehicleQueueTest2(String vin) {
        String trimedVin = vin.substring(Math.max(0, vin.length() - 8));
        //String checkLocator = "//span[2]/span[2][contains(text(),'"+trimedVin+"')]";
        String checkLocator = "//div[@title='" + vin + "']";
        return By.xpath(checkLocator);
    }

    //Last 5 digits of VIN on VEHICLE QUEUE page
    private static By bLast5DigitsOfVIN(String vin) {
        String checkLocator = "//div[@title='" + vin + "']/span";
        return By.xpath(checkLocator);
    }

    //Tag number inside service lane
    private static By bTagNumInsideServiceLane(String tagNum) {
        String checkLocator = "//span[contains(text(), '" + tagNum + "')]";
        return By.xpath(checkLocator);
    }

    //Customer name inside service lane
    private static By bCustomerNameInsideServiceLane(String customerName) {
        String checkLocator = "//span[contains(text(), '" + customerName + "')]";
        return By.xpath(checkLocator);
    }

    //Clear queue search button on VEHICLE QUEUE page
    private static By bClearSearchBtn = By.xpath("//button[@ng-click='search_text = clearQSearch()']");

    //vin number locator on vehicle queue page
    private static By bVinVehicleQueue(String vin) {
        String locator = "//div[@title='" + vin + "']";
        /*if(driver.getCurrentUrl().contains("stage2")){
            locator = "//div[contains(text(),'"+vin+"')]";
        }*/
        return By.xpath(locator);
    }

    //use to detect the page load
    private static By bTagNo = By.xpath("//div[contains(text(),'Tag#')]");

    //Promise time sort
    private static By bPromiseTimeSort = By.xpath("//div[contains(text(),'Promise time')]");

    //Details button associate with Vin
    private static By bDetailsAssociatedWithVin(String vIN) {
        String checkLocator = "//div[@title='" + vIN + "']/parent::div/parent::div//div[contains(text(),'Details')]";
        /*if(driver.getCurrentUrl().contains("test2")){
            checkLocator = "//div[@title='"+vIN+"']/parent::div/parent::div//div[contains(text(),'Details')]";
        }else{
            checkLocator = "//div[contains(text(),'"+vIN+"')]/parent::div/parent::div//div[contains(text(),'Details')]";
        }*/
        return By.xpath(checkLocator);
    }

    //delete service lane icon ont vehicle queue page
    private static By deleteServiceLaneIcon = By.xpath("//div[@class='c-sl-tools__delete']");

    //delete confirm on vehicle queue page
    private static By deleteConfirm = By.xpath("//span[contains(text(),'Delete')]");

    //button that shows only if user click the service lane open
    private static By bGeneral = By.xpath("//div[@class='c-button__inner']/div[contains(text(),'General')]");

    //check the current status for MPI
    private static By currentStatusMPI(String status) {
        String checkLocator = "//div/header/div[contains(text(),'" + status + "')]";
        return By.xpath(checkLocator);
    }

    //check the current status for MPI ready/started
    private static By currentStatusMPIReady = By.xpath("//div[@class='maintenance-progress__cell evir-created maintenance-progress__cell--current']/header/div");

    //select TI inspection template
    private static By bInspectionType(String inspectionType) {
        String checkLocator = "//div[contains(text(),'" + inspectionType + "')]";
        return By.xpath(checkLocator);
    }

    //open in TI locator
    public static By openInTI() {
        return By.xpath("//div[contains(text(),'Open in TI')]");
    }

    //OPEN IN APP
    private static By bOpenInApp = By.xpath("//div[contains(text(),'Open in app')]");

    //TECHNICIAN INSPECTION
    private static By bTI = By.xpath("//span[text()='Technician Inspection']");

    //2nd gate play button
    private static By bCompleteCheckInPlay = By.cssSelector("div.maintenance-progress__cell.maintenance-progress__cell--await.maintenance-progress__cell--not-started > button > div > svg");

    //delete svg icon
    private static By bDeleteIcon = By.cssSelector("svg.icon.icon--trash-2");

    //play button by the status name
    private static By bPlayButton(String status) {
        String checkLocator = "//*[name()='svg' and @class='icon icon--btn--play icon button-icon']/following-sibling::span[contains(text(),'" + status + "')]";
        return By.xpath(checkLocator);
    }

    //loading scroll
    private static By bLoadingOnServiceDashboardVehicleQueue = By.xpath("//*[@id='maintenance-queue-scroll-container']/div/div");

    //vin for appointment
    private static By bVinForAppointment(String vin) {
        String locator = "//div[@class='appt__inner']//span[contains(text(),'" + vin + "')]";
        return By.xpath(locator);
    }

    //appointment drag down icon
    private static By bDragDownArrow(String vin) {
        String locator = "//div[@class='appt__inner']//span[contains(text(),'" + vin + "')]/../following-sibling::div[@class='appt__controls']//*[@class='button-icon icon--btn--arrow-down icon']";
        return By.xpath(locator);
    }

    //name on the appointment with vin
    private static By bCustomerNameWithVin(String vin) {
//        String locator = "//div[@class='appt__inner']//span[contains(text(),'"+vin+"')]/../../div/span/*[contains(@class,'icon--user')]/..";
        String locator = "//div[@class='group-text']//span[contains(text(),'" + vin + "')]/parent::div/preceding-sibling::div/span[1]";
        return By.xpath(locator);
    }

    //appointment date with vin
    private static By bDateAppointment(String vin) {
        String locator = "//span[contains(text(),'" + vin + "')]/../../../../../preceding-sibling::div[contains(@class,'date')]";
        return By.xpath(locator);
    }

    //appointment time with vin
    private static By bTimeAppointment(String vin) {
        String locator = "//span[contains(text(),'" + vin + "')]/../../../../../preceding-sibling::div[contains(@class,'appointments__time-segment__time')]";
        return By.xpath(locator);
    }

    //'SHOW MINE' button
    private static By bShowMineBtn = By.xpath("//span[contains(text(), 'Show mine')]/parent::div");

    //'SHOW ALL' button
    private static By bShowAllBtn = By.xpath("//span[contains(text(), 'Show all')]/parent::div");

    //No Service Lanes symbol
    private static By bNoServiceLanesSymbol = By.cssSelector("#NG2_UPGRADE_3_0 > div > div > svg");

    //No Service Lanes message
    private static By bNoServiceLanesMsg = By.xpath("//div[contains(text(), 'No service lanes')]");

    //Username title
    private static By bUsernameTitle = By.xpath("//h2[@class='header title']");

    //New assigned service lane with VIN
    private static By bNewAssignedServiceLaneWithVIN(String vin) {
        String locator = "//div[@title='" + vin + "']/ancestor::lane-item";
        return By.xpath(locator);
    }

    //New assigned service lane with tag number
    private static By bNewAssignedServiceLaneWithTag(String tag) {
        String locator = "//span[contains(text(), '" + tag + "')]/ancestor::lane-item";
        return By.xpath(locator);
    }

    //New assigned service lane with customer name
    private static By bNewAssignedServiceLaneWithCustomerName(String customerName) {
        String locator = "//span[contains(text(), '" + customerName + "')]/ancestor::lane-item";
        return By.xpath(locator);
    }

    //Service Advisor column of a service lane with specific VIN
    private static By bServiceLaneServiceAdvisorColumn(String vin) {
        String locator = "//div[@title='" + vin + "']/parent::div/child::div[@class='c-lane-list__column c-lane-list__column-sa']";
        return By.xpath(locator);
    }

    //TEAM tab on service lane details
    private static By bTeamTab = By.xpath("//div[contains(text(), 'team')]/ancestor::button");

    //Service Technician name on service lane details
    private static By bServiceTechnicianName = By.xpath("//div[@class='c-lane-team__members-groups']/div[2]//span");

    //Service Advisor name on service lane details
    private static By bServiceAdvisorName = By.xpath("//div[@class='c-lane-team__members-groups']/div[1]//span");

    //Close icon on service lane details
    private static By bCloseIconOnServiceLaneDetails = By.xpath("//*[@id='NG2_UPGRADE_3_0']/div/div/section/div/lane-group/section/div/div/lane-item[11]/div[2]/div[1]/svg[2]");

    //Wrench button on service lane details
    private static By bWrenchBtn = By.xpath("//div[@class='c-segment-control c-segment-control--small']/div[3]");

    //Search people field on service lane details
    private static By bSearchPeople = By.id("c-lane-team-search-input");

    //Add member button on service lane details
    private static By bAddMemberBtn = By.xpath("//div[@class='c-lane-team__search-cell c-lane-team__search-cell--add']/button");

    //Primary star button on service lane details
    private static By bPrimaryStarBtn = By.xpath("//button[@class='link link--primary c-lane-team__member-priority']");

    //Delete button on service lane details
    private static By bDeleteBtn = By.xpath("//button[@class='c-sl-tools__delete-button']");

    //DELETE button on popup
    private static By bDeleteBtnOnPopup = By.xpath("//span[contains(text(), 'Delete')]");

    //Remove button after specific technician name on service lane details
    private static By bRemoveBtnAfterTechnicianName(String technicianName) {
        String locator = "//span[@class='text-overflow' and contains(text(), '" + technicianName + "')]/parent::div/following-sibling::button";
        return By.xpath(locator);
    }

    public void waitForLoadingDisappearOnServiceDashboardVehicleQueue() {
        waitForElementHasAttributeValue(bLoadingOnServiceDashboardVehicleQueue, "style", "");
    }

    public Boolean isUserOnPage(String pageName) {
        if (pageName == "VEHICLE QUEUE") {
            return dWait.until(ExpectedConditions.presenceOfElementLocated(bVehicleQueueActived)).isDisplayed();
        } else {
            return false;
        }
    }

    public void clickVinOnVehicleQueue(String vIN) {
        long startTime = System.currentTimeMillis();
        while (!isGeneralDisplayed()) {
            try {
                driver.findElement(bVinVehicleQueue(vIN)).click();
                System.out.println("<====== " + vIN + " opened ======>");
            } catch (WebDriverException e) {
                //vin already opened
            }
            if ((System.currentTimeMillis() - startTime) > 10000) {
                Assert.fail("<====== Not able to open the service lane with vin " + vIN + ", test failed ======>");
                break;
            }
        }
    }

    public boolean isGeneralDisplayed() {
        try {
            driver.findElement(bGeneral).isDisplayed();
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    public void deleteServiceLaneBeforeTest(String vIN) {
        waitForLoadingDisappearOnServiceDashboardVehicleQueue();
        try {
            sWait.until(conditionVisible(bVinVehicleQueue(vIN)));
        } catch (WebDriverException e) {
            //do nothing
        }
        if (driver.findElements(bVinVehicleQueue(vIN)).size() != 0) {
            clickVinOnVehicleQueue(vIN);
            clickElementWithException(deleteServiceLaneIcon);
            clickElementWithException(deleteConfirm);
            System.out.println("<====== The service lane " + vIN + " has been deleted ======>");
        }
    }

    public void deleteServiceLaneAfterTest() {
        dWait.until(conditionVisible(deleteServiceLaneIcon)).click();
        dWait.until(conditionVisible(deleteConfirm)).click();
    }

    public boolean isServiceLaneKilled(String vIN) {
        long startTime = System.currentTimeMillis();
        try {
            driver.findElement(bVinVehicleQueue(vIN)).click();
            deleteServiceLaneAfterTest();
        } catch (WebDriverException e) {
            System.out.println("<====== the service lane with '" + vIN + "' already deleted because I am seeing no such element exception ======>");
            return false;
        }
        while (isServiceLaneExist(vIN)) {
            sleep(1000);
            if ((System.currentTimeMillis() - startTime) > 10000) {
                //wait until the service lane disappear, it might take some time, give it 10 secs
                break;
            }
        }
        if (driver.findElements(bVinVehicleQueue(vIN)).size() != 0) {
            System.out.println("<====== the service lane with '" + vIN + "' still existed ======>");
            return true;
        } else {
            System.out.println("<====== the service lane with '" + vIN + "' already deleted ======>");
            return false;
        }
    }

    public boolean isServiceLaneExist(String vIN) {
        try {
            driver.findElement(bVinVehicleQueue(vIN)).isDisplayed();
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    public void isServiceLaneOpened(String vIN) {
        if (driver.findElements(bGeneral).size() != 0) {
        } else {
            clickVinOnVehicleQueue(vIN);
        }
    }

    public void searchVin(String searchTerm) {
        clearAndInputElementWithException(bSearchField, searchTerm);
        dWait.until(conditionVisible(bSearchField)).sendKeys(Keys.RETURN);
    }

    public void searchPeople(String peopleName) {
        clearAndInputElementWithException(bSearchPeople, peopleName);
        dWait.until(conditionVisible(bSearchPeople)).sendKeys(Keys.RETURN);
    }

    public String verifyServiceLaneStatus(String vIN, String status) {
        long startTime = System.currentTimeMillis();
        waitForTitle("Service Dashboard");
        waitForLoadingDisappearOnServiceDashboardVehicleQueue();
        String statusShow = "<====== the service lane with '" + vIN + "' shows '" + status + "' ======>";
        String statusResult = "";
        waitForElementVisibleWithException(bVehicleQueueActived);
        //waitForElementVisibleWithException(bTagNo);
        boolean noException = false;
        while (!noException) {
            try {
                sWait.until(conditionVisible(bVinVehicleQueue(vIN)));
                noException = true;
            } catch (WebDriverException e) {
                waitForElementWithExceptionWithSecs(bPromiseTimeSort, 5000);
                try {
                    driver.findElement(bPromiseTimeSort).click();
                } catch (WebDriverException ex) {
                    driver.navigate().refresh(); //the page is blank for whatever reason, refresh the page and try again
                }
                System.out.println("<====== not able to find service lane " + vIN + ", will click sort promise time and try again ======>");
                noException = false;
            }
            if ((System.currentTimeMillis() - startTime) > 30000) {
                break;
            }
        }
        if (noException) {
            isServiceLaneOpened(vIN);
            statusResult = getElementTextWithException(currentStatusMPI(status));
        } else {
            System.out.println("<====== The vin number '" + vIN + "' is not even there ======>");
        }
        return statusResult;
    }

    public void clickDetailsUnderVin(String vIN) {
        clickElementWithException(bDetailsAssociatedWithVin(vIN));
    }

    public String isPlayButtonExist() {
        String result;
        if (driver.findElements(bCompleteCheckInPlay).size() != 0) {
            result = "play button visible";
        } else {
            result = "play button not visible";
        }
        return result;
    }

    public String isDelIconExist() {
        String result;
        if (driver.findElements(bDeleteIcon).size() != 0) {
            result = "delete icon visible";
        } else {
            result = "delete icon not visible";
        }
        return result;
    }

    public void clickPlayButton(String status) {
        waitForElementWithExceptionWithSecs(bPlayButton(status), 15000);
        clickElementWithSeconds(bPlayButton(status), 10000);
        try {
            driver.findElement(bPlayButton(status)).click();
            System.out.println("<====== '" + status + "' play button clicked ======>");
        } catch (WebDriverException e) {
            //the play button will be ignored
        }
    }

    public void clickInspectionType(String inspectionType) {
        dWait.until(conditionVisible(bInspectionType(inspectionType))).click();
    }

    public boolean verifyAppointmentWithVin(String vin) {
        try {
            dWait.until(conditionVisible(bVinForAppointment(vin))).isDisplayed();
            System.out.println("<====== The appointment with " + vin + " showing ======>");
            return true;
        } catch (WebDriverException e) {
            System.out.println("<====== The appointment with " + vin + " not showing ======>");
            return false;
        }
    }

    public boolean verifyAppointmentNotShowWithVin(String vin) {
        try {
            driver.findElement(bVinForAppointment(vin)).isDisplayed();
            return false;
        } catch (WebDriverException e) {
            return true;
        }
    }

    public void waitForAppointmentDisappear(String vin) {
        long startTime = System.currentTimeMillis();
        while (!verifyAppointmentNotShowWithVin(vin)) {
            sleep(1000); //wait for appointment disappear on the service dashboard page
            try {
                driver.findElement(bDragDownArrow(vin)).click();
            } catch (WebDriverException e) {
                //the drag arrow is not there
            }
            System.out.println("<====== The appointment with " + vin + " still showing ======>");
            if ((System.currentTimeMillis() - startTime) > 30000) {
                break; //give 30 secs for appoint to disappear on the page
            }
        }
        System.out.println("<====== The appointment with " + vin + " disappeared ======>");
    }

    public String getCustomerNameOnSD(String vin) {
        sleep(2000);
        TestParameters.appointmentCustomerName = getElementTextWithException(bCustomerNameWithVin(vin));
        System.out.println("<====== The customer name on service dashboard is " + TestParameters.appointmentCustomerName + " ======>");
        return TestParameters.appointmentCustomerName;
    }

    public boolean verifyAppointmentDate(String vin) {
        String dateAppointmentSD = getElementTextWithException(bDateAppointment(vin));
        System.out.println("<====== The appointment date on the vehicle queue page is " + dateAppointmentSD + " ======>");
        try {
            Date dateAM = new SimpleDateFormat("dd MMM yyyy").parse(TestParameters.appointmentDay);
            Date dateSD = new SimpleDateFormat("dd MMM yyyy").parse(dateAppointmentSD);
            if (dateAM.equals(dateSD)) {
                return true;
            } else {
                System.out.println("<====== The date on appointment manager is " + dateAM + ", the date on service dashboard is " + dateSD + ", test failed ======>");
                return false;
            }
        } catch (ParseException p) {
            System.out.println("<====== time format on page has changed ======>");
            return false;
        }
    }

    public boolean verifyAppointmentTime(String vin) {
        long startTime = System.currentTimeMillis();
        String timeAppointmentSD = getElementTextWithException(bTimeAppointment(vin)).toUpperCase();
        System.out.println("<====== The appointment time on the vehicle queue page is " + timeAppointmentSD + " ======>");
        int idx = TestParameters.appointmentTime.indexOf(":");
        String apptime = TestParameters.appointmentTime.substring(0, idx + 1) + "00" + TestParameters.appointmentTime.substring(idx + 3);
//        tp.appointmentTime = tp.appointmentTime.replace(":30",":00").replace(":15",":00").replace(":45",":00"); //this is due to route sheet only showing time frame, not exact time
        while (!timeAppointmentSD.equals(apptime)) {
            System.out.println("<====== waiting for appointment time on the service dashboard to update ======>");
            timeAppointmentSD = getElementTextWithException(bTimeAppointment(vin)).toUpperCase();
            if (timeAppointmentSD.equals(apptime)) {
                System.out.println("<====== The appointment time on the vehicle queue page updated to " + timeAppointmentSD + " ======>");
            }
            sleep(1000);//execute the method every 1 sec
            if ((System.currentTimeMillis() - startTime) > 30000) {
                break; //give the service dashboard appointment time 10 secs to update, if not updated in 30 secs, test will fail
            }
        }
        if (timeAppointmentSD.equals(apptime)) {
            return true;
        } else {
            System.out.println("<====== The time on appointment manager is " + apptime + ", the time on service dashboard is " + timeAppointmentSD + ", test failed ======>");
            return false;
        }
    }

    public void dragDownAppointmentWithName(String vin) {
        clickElementWithException(bVinForAppointment(vin));
        clickElementWithException(bDragDownArrow(vin));
        waitForAppointmentDisappear(vin);
    }

    public void clickOpenInAPP() {
        clickElementWithException(bOpenInApp);
    }

    public void clickTI() {
        clickElementWithException(bTI);
    }

    public void openInTiButton() {
        clickElementWithException(openInTI());
    }

    public void clickDashboardBtn() {
        clickElementWithException(bDashboardBtn);
    }

    public String getCurrentLaneHeader() {
        return getElementTextWithException(bCurrentLaneFilterBtn);
    }

    public int getNumOfDisplayedLanes() {
        waitForElementVisibleWithException(bLanesDisplayed);
        return driver.findElements(bLanesDisplayed).size();
    }

    public boolean isShowMineBtnVisible() {
        return verifyIfElementVisibleOnPage(bShowMineBtn);
    }

    public boolean isShowAllBtnVisible() {return verifyIfElementVisibleOnPage(bShowAllBtn);}

    public void clickShowMineBtn() {
        clickElementWithException(bShowMineBtn);
    }

    public void clickShowAllBtn() {
        clickElementWithException(bShowAllBtn);
    }

    public void clickClearSearchBtn() {
        clickElementWithException(bClearSearchBtn);
    }

    public boolean isNoServiceLanesSymbolVisible() {
        return verifyIfElementVisibleOnPage(bNoServiceLanesSymbol);
    }

    public boolean isNoServiceLanesMsgVisible() {
        return verifyIfElementVisibleOnPage(bNoServiceLanesMsg);
    }

    public boolean isUsernameVisible() {
        return verifyIfElementVisibleOnPage(bUsernameTitle);
    }

    public String getUsername() {
        sleep(2000);
        waitForElementVisibleWithException(bUsernameTitle);
        return driver.findElement(bUsernameTitle).getAttribute("innerHTML");
    }

    public boolean isServiceLanesVisible() {
        return verifyIfElementVisibleOnPage(bLanesDisplayed);
    }

    public boolean isAssignedLaneWithVINVisible(String vin) {return verifyIfElementVisibleOnPage(bNewAssignedServiceLaneWithVIN(vin));}

    public boolean isAssignedLaneWithTagVisible(String tagNum) {return verifyIfElementVisibleOnPage(bNewAssignedServiceLaneWithTag(tagNum));}

    public boolean isAssignedLaneWithCustomerNameVisible(String customerName) {return verifyIfElementVisibleOnPage(bNewAssignedServiceLaneWithCustomerName(customerName));}

    public String getServiceLaneServiceAdvisorValue(String vin) {
        waitForElementVisibleWithException(bLanesDisplayed);
        return getElementTextWithException(bServiceLaneServiceAdvisorColumn(vin));
    }

    public void clickServiceLaneWithVIN(String vin) {
//        clickElementWithException(bNewAssignedServiceLane(vin));
        try {
            sWait.until(conditionVisible(bNewAssignedServiceLaneWithVIN(vin)));
            driver.findElement(bNewAssignedServiceLaneWithVIN(vin)).click();
        } catch (WebDriverException e) {
            System.out.println("The lane doesn't exist, skip this step.");
        }
    }

    public void clickTeamTab() {
        clickElementWithException(bTeamTab);
    }

    public String getServiceAdvisorNameOnLaneDetails() {
        return getElementTextWithException(bServiceAdvisorName);
    }

    public String getServiceTechnicianNameOnLaneDetails() {return getElementTextWithException(bServiceTechnicianName);}

    public void closeServiceLaneDetails() {
        clickElementWithException(bCloseIconOnServiceLaneDetails);
    }

    public void clickWrenchBtn() {
        clickElementWithException(bWrenchBtn);
    }

    public void clickAddMemberBtn() {
        clickElementWithException(bAddMemberBtn);
    }

    public void clickPrimaryStarBtn() {
        clickElementWithException(bPrimaryStarBtn);
    }

    public void clickDeleteBtn() {
//        clickElementWithException(bDeleteBtn);
        try {
            sWait.until(conditionVisible(bDeleteBtn));
            driver.findElement(bDeleteBtn).click();
        } catch (WebDriverException e) {
            System.out.println("Service lane may not exist so skip this step.");
        }
    }

    public void removeTechnician(String technicianName) {clickElementWithException(bRemoveBtnAfterTechnicianName(technicianName));}

    public void clickDeleteBtnOnPopup() {
//        clickElementWithException(bDeleteBtnOnPopup);
        try {
            sWait.until(conditionVisible(bDeleteBtnOnPopup));
            driver.findElement(bDeleteBtnOnPopup).click();
        } catch (WebDriverException e) {
            System.out.println("Service lane may not exist so skip this step.");
        }
    }

    public boolean isLast5DigitsHighlighted(String vin) {return getElementAttributeWithException(bLast5DigitsOfVIN(vin), "class").contains("hilight");}

    public boolean isTagNumHighlighted(String tagNum) {return getElementAttributeWithException(bTagNumInsideServiceLane(tagNum), "class").contains("hilight");}

    public boolean isCustomerNameHighlighted(String customerName) {return getElementAttributeWithException(bCustomerNameInsideServiceLane(customerName), "class").contains("hilight");}

    public boolean isInvalidTextDisplayed(String invalidText) {return verifyIfElementVisibleOnPage(bInvalidText(invalidText));}

    public boolean isWasNotFoundMsgDisplayed() {return verifyIfElementVisibleOnPage(bNotFoundMsg);}

    public boolean isNoServiceLaneVisible() {return verifyIfElementNotShowOnThePage(bLanesDisplayed);}

}