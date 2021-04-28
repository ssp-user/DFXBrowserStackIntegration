package com.automation.pages.tech_inspection;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.event.InputEvent;

public class InspectionPage extends TechInspectionHeadPage {

    private final String attributeElement = "Value", attributeValue = "Search";

    /*
     *    Below Page Elemnts are the Buttons on the page
     */

    @FindBy(id = "imgViewMenu")      //  View Menu Button     if  enable :  has onClick="xxxx"  attribute
    private WebElement viewMenuBtn;
    private static By bViewMenuBtn = By.id("imgViewMenu");

    @FindBy(id = "#bs-example-navbar-collapse-1>div>ul>li:nth-child(3)>a")     //  Status Log  Button
    private WebElement statusLogBtn;
    private static By bStatusLogBtn = By.id("imgUpdatesHistory");

    @FindBy(id = "btnSave")                             //  Save Button
    private WebElement saveBtn;
    private static By bSaveBtn = By.id("btnSave");

    @FindBy(id = "btnMarkReviewed")         //  Mark Reviewed Button     if  disable :  disabled="disabled"

    private WebElement markReviewBtn;
    private static By bMarkReviewBtn = By.id("btnMarkReviewed");

    @FindBy(id = "btnClose")      //  Confirm Button
    private WebElement closeBtn;
    private static By bCloseBtn = By.id("btnClose");

    @FindBy(id = "ibtnEmailEVIR")       //  Email Button
    private WebElement emailBtn;
    private static By bEmailBtn = By.id("ibtnEmailEVIR");

    // OK button in Popup window`       //  Revise Inspection Button
    private static By bReviseInspection = By.id("btnReviseInspection");


    /*
     *    Below Page Elemnts are the RadioInput lable  total 54
     */

    // EXTERIOR SECTION  ELEMENTS
    @FindBy(id = "InspectionTab_rpTabRef_ctl00_rpTabItem_ctl01_rpSelection_ctl00_rbList1")      // EXTERIOR TAB  if checked : attribute disabled=""
    private WebElement exteriorTab;
    private static By bExteriorTab = By.id("InspectionTab_rpTabRef_ctl00_rpTabItem_ctl01_rpSelection_ctl00_rbList1");

    // BELOW ARE 4 ELEMENTS CHECK LABLE

    /**
     *
     *  .radioinput input:checked + label {
     *     opacity: 1;
     *     background-repeat: no-repeat;
     *     -ms-filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=100);
     *  }
     */
    @FindBy(xpath = "//label[@for='InspectionTab_rpTabRef_ctl00_rpTabItem_ctl01_rpSelection_ctl00_rbList1_0']")     // GREEN CHECK
    private WebElement tagTextBox;                                                               //
    private static By bTagTextBox = By.xpath("//label[@for='InspectionTab_rpTabRef_ctl00_rpTabItem_ctl01_rpSelection_ctl00_rbList1_0']");

    @FindBy(xpath = "//label[@for='InspectionTab_rpTabRef_ctl00_rpTabItem_ctl01_rpSelection_ctl00_rbList1_1']")      // YELLOW CHECK
    private WebElement lastNameTextBox;
    private static By bTextLastName = By.xpath("//label[@for='InspectionTab_rpTabRef_ctl00_rpTabItem_ctl01_rpSelection_ctl00_rbList1_1']");

    @FindBy(css = "label[for*='InspectionTab_rpTabRef_ctl00_rpTabItem_ctl03_rpSelection_ctl00_rbList1_2']")        // RED CHECK
    private WebElement ROtextBox;
    private static By bTxtRONumber = By.cssSelector("label[for*='InspectionTab_rpTabRef_ctl00_rpTabItem_ctl03_rpSelection_ctl00_rbList1_2']");

    @FindBy(xpath = "//label[@for='InspectionTab_rpTabRef_ctl00_rpTabItem_ctl01_rpSelection_ctl00_rbList1_3']")       // GREY CHECK
    private WebElement statusSelect;
    private static By bStatusSelect = By.xpath("//label[@for='InspectionTab_rpTabRef_ctl00_rpTabItem_ctl01_rpSelection_ctl00_rbList1_3']");



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

    @FindBy(id = "createEvirBtn")      //  Create MPI button
    private WebElement createMPIBtn;
    private static By bCreateMPIBtn = By.id("createEvirBtn");

    @FindBy(xpath = "//label[@id='lblDateCreatedFrom']/..//div[@class='dwwl dwrc dwwl2']/div[2]")
    private WebElement fromYearMinus;
    private static By bFromYearMinus = By.xpath("//label[@id='lblDateCreatedFrom']/..//div[@class='dwwl dwrc dwwl2']/div[2]/span");

    //label[@id='lblDateCreatedFrom']/..//div[@class='dwwl dwrc dwwl2']/div[1]

    @FindBy(xpath = "//label[@id='lblDateCreatedFrom']/..//div[@class='dwwl dwrc dwwl2']/div[1]")
    private WebElement fromYearPlus;
    private static By bFromYearPlus = By.xpath("//label[@id='lblDateCreatedFrom']/..//div[@class='dwwl dwrc dwwl2']/div[1]");

    @FindBy(id = "noSearchResultDiv")     // search no matched
    private WebElement noRecord;
    private static By bNoRecordMeg = By.id("noSearchResultDiv");

    private static Logger log = Logger.getLogger(InspectionPage.class.getName());

    // Constructor
    public InspectionPage() {
        PageFactory.initElements(driver, this);
    }

    private Boolean isPageLoad() {
        return dWait.until(ExpectedConditions.presenceOfElementLocated(bTxtRONumber)).isDisplayed();
    }

    @Override
    protected ExpectedCondition<WebElement> conditionForPage() {
        return ExpectedConditions.elementToBeClickable(bClearselectionBtn);
    }

    public void createMPI() {
        dWait.until(conditionClick(bCreateMPIBtn)).click();
        dWait.until(ExpectedConditions.invisibilityOfElementLocated(bCreateMPIBtn));
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
//        selectDropList(createdSelect, text);
    }

    public void selectAssignedTo(String text) {
//        selectDropList(createdSelect, text);
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

    public void setFromYear(String ch) throws Throwable {
        int change = Integer.valueOf(ch.trim().substring(1));
        for (int i = 0; i < change; i++) {
            if (ch.startsWith("-")) {
                WebElement el = driver.findElement(bFromYearMinus);
                Robot robot = new Robot();
                robot.setAutoDelay(500);
                Point pi = el.getLocation();
                int x = pi.getX();
                int y = pi.getY();
                System.out.println("loca " + pi);
                robot.mouseMove(x + 1, y + 1);
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
//                jsClick(fromYearMinus);
            } else {
                fromYearPlus.click();
            }
        }
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
        String result = "";
        dWait.until(conditionVisible(bSearchBtn)).click();
//        searchBtn.click();
        try {
            result = dWait.until(conditionVisible(bNoRecordMeg)).getText();
        } catch (NoSuchElementException nex) {
            result = " ";
        } catch (Exception ex) {
            result = "";
            ex.getStackTrace();
        }
        return result;
    }




    @Override
    protected void switchFrame() {
        driver.switchTo().frame("ccc");
    }

}



