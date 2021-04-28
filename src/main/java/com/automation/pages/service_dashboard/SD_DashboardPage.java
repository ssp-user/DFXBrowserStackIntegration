package com.automation.pages.service_dashboard;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;

public class SD_DashboardPage extends WebPage {

    //all web element locator
    //DASHBOARD button
    private static By bDashboardBtn = By.xpath("//a[contains(text(), 'Dashboard')]");

    //Active tab
    private static By bActiveTab = By.xpath("//a[@class='link link--primary app-pages-nav__link active']");

    //'SHOW MINE' button
    private static By bShowMineBtn = By.xpath("//section[@id='horizontal-page--dashboard--summary']//child::span[contains(text(), 'Show mine')]//parent::div");

    //'SHOW ALL' button
    private static By bShowAllBtn = By.xpath("//section[@id='horizontal-page--dashboard--summary']//child::span[contains(text(), 'Show all')]//parent::div");

    //'All Departments' dropdown
    private static By bAllDepartmentsDropdown = By.xpath("//div[@class='form-element form-element-icons form-element-icons--small']");

    //'Missed Appointments' tile
    private static By bMissedAppointmentsTile = By.xpath("//exeption[@data-id='urn:dfx:counter:appointment-missed']");

    //'MPI Declined By Advisor' tile
    private static By bMPIDeclinedByAdvisorTile = By.xpath("//exeption[@data-id='urn:dfx:counter:service-lane-vehicle-no-inspection']");

    //'Carry Over' tile
    private static By bCarryOverTile = By.xpath("//exeption[@data-id='urn:dfx:counter:service-lane-carryover']");

    //'Waiters' tile
    private static By bWaitersTile = By.xpath("//exeption[@data-id='urn:dfx:counter:service-lane-waiters']");

    //'Write up not Complete' tile
    private static By bWriteUpNotCompleteTile = By.xpath("//exeption[@data-id='urn:dfx:counter:service-lane-write-up-not-completed']");

    //'MPI not Started' tile
    private static By bMPINotStartedTile = By.xpath("//exeption[@data-id='urn:dfx:counter:service-lane-work-not-started-total']");

    //'Awaiting Parts Estimate' tile
    private static By bAwaitingPartsEstimateTile = By.xpath("//exeption[@data-id='urn:dfx:counter:service-lane-vehicle-parts-estimate']");

    //'Quarter Time' tile
    private static By bQuarterTimeTile = By.xpath("//exeption[@data-id='urn:dfx:counter:service-lane-quarter-time-missed']");

    //'Promise Time' tile
    private static By bPromiseTimeTile = By.xpath("//exeption[@data-id='urn:dfx:counter:service-lane-promise-time-missed']");

    //'Ready for Delivery' tile
    private static By bReadyForDeliveryTile = By.xpath("//exeption[@data-id='urn:dfx:counter:service-lane-gate-delivery-loaner']");

    //'SHOW LANE' button
    private static By bShowLaneBtn = By.xpath("//exeption[@class='c-stat-counter c-stat-counter--is-open c-stat-counter--is-vertical']/div[1]/div");

    //Number of MPI not Started
    private static By bNumberOfMPINotStarted = By.xpath("//exeption[@data-id='urn:dfx:counter:service-lane-work-not-started-total']//child::counter[1]//span");

    public boolean isShowMineBtnVisible() {return verifyIfElementVisibleOnPage(bShowMineBtn);}

    public boolean isShowAllBtnVisible() {return verifyIfElementVisibleOnPage(bShowAllBtn);}

    public boolean isAllDepartmentsDropdownVisible() {return verifyIfElementVisibleOnPage(bAllDepartmentsDropdown);}

    public boolean isMissedAppointmentsTileVisible() {return verifyIfElementVisibleOnPage(bMissedAppointmentsTile);}

    public boolean isMPIDeclinedByAdvisorTileVisible() {return verifyIfElementVisibleOnPage(bMPIDeclinedByAdvisorTile);}

    public boolean isCarryOverTileVisible() {return verifyIfElementVisibleOnPage(bCarryOverTile);}

    public boolean isWaitersTileVisible() {return verifyIfElementVisibleOnPage(bWaitersTile);}

    public boolean isWriteUpNotCompleteTileVisible() {return verifyIfElementVisibleOnPage(bWriteUpNotCompleteTile);}

    public boolean isMPINotStartedTileVisible() {return verifyIfElementVisibleOnPage(bMPINotStartedTile);}

    public boolean isAwaitingPartsEstimateTileVisible() {return verifyIfElementVisibleOnPage(bAwaitingPartsEstimateTile);}

    public boolean isQuarterTimeTileVisible() {return verifyIfElementVisibleOnPage(bQuarterTimeTile);}

    public boolean isPromiseTimeTileVisible() {return verifyIfElementVisibleOnPage(bPromiseTimeTile);}

    public boolean isReadyForDeliveryTileVisible() {return verifyIfElementVisibleOnPage(bReadyForDeliveryTile);}

    public void clickMPINotStartedTile() {clickElementWithException(bMPINotStartedTile);}

    public void clickShowLaneBtn() {clickElementWithException(bShowLaneBtn);}

    public String getNumberOfMPINotStarted() {return driver.findElement(bNumberOfMPINotStarted).getText();}

    public String getActiveTabText() {return driver.findElement(bActiveTab).getText();}
}
