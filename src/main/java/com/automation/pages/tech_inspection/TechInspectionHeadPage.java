package com.automation.pages.tech_inspection;

import com.automation.pages.common.WebPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TechInspectionHeadPage extends WebPage {

    private final String attributeElement = "Value", attributeValue = "Search";

    /*
     *    Below Page Elemnts are hide after fire the search function
     */

    private final String CREATEIFRAME = "xxx";   //  frame id

    @FindBy(id = "AssignedTechnician")      //  Assigned to DropDown list
    private WebElement assignedSelect;
    private static By bAssignedToDrop = By.id("AssignedTechnician");

    @FindBy(css = "#bs-example-navbar-collapse-1>div.pull-right>table td:nth-child(3)")     // TD Login User
    private WebElement tdUser;
    private static By bTDUser = By.cssSelector("#bs-example-navbar-collapse-1>div.pull-right>table td:nth-child(3)");

    // Show Vehicle Button
    private static By bShowVehicleBtn = By.id("lnkVehicleCardRow");

    // searched First line Vehicle Status  information
    @FindBy(id = "shortPromiseTime")
    private WebElement promiseTime;
    private static By bPromiseTime = By.id("shortPromiseTime");

    @FindBy(xpath = "//*[@id='cardInfoRibbon']//tbody/tr/td[3]")     // Vehicle RO#
    private WebElement VehicleRO;
    private static By bvehiRO = By.xpath("//*[@id='cardInfoRibbon']//tbody/tr/td[3]");

    private static By bVIN = By.xpath("//*[@id='cardInfoRibbon']//tbody/tr/td[2]");



    private static Logger log = Logger.getLogger(TechInspectionHeadPage.class.getName());

    // Constructor
    public TechInspectionHeadPage() {
        PageFactory.initElements(driver, this);
    }

    private Boolean isPageLoad() {
        return dWait.until(ExpectedConditions.presenceOfElementLocated(bPromiseTime)).isDisplayed();
    }

    @Override
    protected ExpectedCondition<WebElement> conditionForPage() {
        return ExpectedConditions.elementToBeClickable(bAssignedToDrop);
    }

    public void selectStatus(String text) {
//        selectDropList(inpectStatus, text);
    }

    public String getVIN() {
        return dWait.until(conditionVisible(bVIN)).getText().trim();
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

    @Override
    protected void switchFrame() {
        driver.switchTo().frame(CREATEIFRAME);
    }

}


