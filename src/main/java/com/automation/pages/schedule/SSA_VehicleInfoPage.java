package com.automation.pages.schedule;

import com.automation.pages.common.WebPage;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SSA_VehicleInfoPage extends SSA_FramePage {

    private static Logger log = Logger.getLogger(SSA_VehicleInfoPage.class);


//    @FindBy(xpath="//input[@id='odometerField']")
//    protected static WebElement odometer;
//
//    //	@FindBy(xpath="//div[@class='input-wrap']/div[2]/button[contains(text(),'Continue')]")
//    @FindBy(css="button.btn")
//    protected static WebElement contineBtn;
//
//    @FindBy(xpath="//input[@name='vinField']")
//    protected static WebElement vinForNewCust;
//
//    @FindBy(xpath="//button[@class='btn-verify']")
//    protected static WebElement decodeVIN;
//
//    @FindBy(xpath="//div[@class='blocks__link blocks__link--auto-selected']//span[contains(text(),'auto-selected')]")
//    protected static WebElement autoSelected;
//
//    @FindBy(xpath="//span[contains(text(),'Back')]")
//    protected static WebElement backBtn;
//
//
//	/*@FindBy(xpath="//div[@class='select-vehicle__confirm full-size-loader']")
//	protected static WebElement vehicleLoader;*/
//

//    @FindBy(xpath="//div[@class='updates-available ng-binding ng-scope']")
//    protected static WebElement getRecallCount;

//    @FindBy(xpath="//span[contains(text(),'Vehicle updates')]")
//    protected static WebElement vehicleUpdatesTab;
//

    //   imported from TestNG

//    //    @FindBy(xpath="//form[@name='searchForm']//input[@name='searchField']")
////    protected static WebElement searchField
//    private static By bSearchField = By.id("search");
//
//    //
////    @FindBy(xpath="//button[@class='button']")
////    protected static WebElement SearchBtn;
//    private static By bSearchButton = By.xpath("//button[(@class='button') or (@class='search-btn')]");
//
//    //    @FindBy(xpath="//div[@class='car-selection__inner']")
////    protected static WebElement vehicleDescription;
//    private static By bVehicle = By.cssSelector("div.car-selection__inner");

    //
//    @FindBy(xpath="//input[@id='verifyField']")
//    protected static WebElement verifyField;
    private static By bVerifyField = By.name("vinField");

    //    @FindBy(xpath="//button[@class='verify']")
//    protected static WebElement verifyBtn;
    private static By bVerifyBtn = By.cssSelector("button.btn-verify");

    //    @FindBy(xpath="//input[@id='odometerField']")
//    protected static WebElement odometer;
    private static By bOdometer = By.id("odometerField");

    //    @FindBy(xpath="//div[@class='input-wrap']/div[2]/div")
//    protected static WebElement contineBtn;
    private static By bContineBtn = By.cssSelector("button.btn");
    private static By bTryAgainBtn = By.xpath("//div[text()='Try again']");

//    //    @FindBy(xpath="//button[@class='new-customer-btn']")
////    protected static WebElement newCust;
//    private static By bnewCustomBtn = By.cssSelector("button.new-customer-btn");

    //    @FindBy(xpath="//div[@class='blocks blocks--manufacturer']/span/div/a/span")
//    protected static List<WebElement> selectMake;
    private static By bMake = By.xpath("//div[@class='blocks blocks--manufacturer']/span/div/a/span");


    //    @FindBy(xpath="//div[@class='blocks blocks--year ng-scope']/div/a/span")
//    protected static List<WebElement> selectYear;
    private static By bYear = By.xpath("//div[@class='blocks blocks--year ng-scope']/div/a/span");

    //
//    @FindBy(xpath="//div[@class='blocks blocks--model ng-scope']/div/a")
//    protected static List<WebElement> selectModel;
    private static By bModel = By.xpath("//div[@class='blocks blocks--model ng-scope']/div/a");

    //
//    @FindBy(xpath="//div[@class='centering']/span/button[contains(text(),'Transmission')]")
//    protected static WebElement transTag;
    private static By bTransmisionTag = By.xpath("//div[@class='centering']/span/button[contains(text(),'Transmission')]");
    //
//    @FindBy(xpath="//div[@class='blocks__item ng-scope']/a/span")
//    protected static List<WebElement> selectTrans;
    private static By bTransmision = By.xpath("//div[@class='blocks__item ng-scope']/a/span");

    //
//    @FindBy(xpath="//div[@class='centering']/span/button[contains(text(),'Engine')]")
//    protected static WebElement engineTag;
    private static By bEngineTag = By.xpath("//div[@class='centering']/span/button[contains(text(),'Engine')]");
    //
//    @FindBy(xpath="//div[@class='centering']/span/button[contains(text(),'Drive train')]")
//    protected static WebElement driveTag;
    private static By bDriveTag = By.xpath("//div[@class='centering']/span/button[contains(text(),'Drive train')]");
    //
//    @FindBy(xpath="//div[@class='blocks__item ng-scope']/a/span")
//    protected static List<WebElement> selectDrive;
    private static By bDrive = By.xpath("//div[@class='blocks__item ng-scope']/a/span");
    //
//    @FindBy(xpath="//div[@class='blocks__item ng-scope']/a/span")
//    protected static List<WebElement> selectEngine;
    private static By bEngine = By.xpath("//div[@class='blocks__item ng-scope']/a/span");

    //    @FindBy(xpath="//div[@recall='recall']")
//    protected static List<WebElement> recallList;
    private static By bRecallList = By.xpath("//div[@recall='recall']");
//
//    @FindBy(xpath="//div[@class='centering']/button[@ng-click='next()']")
//    protected static WebElement nextBtn;
    private static By bNextBtn = By.xpath("//div[@class='centering']/button[@ng-click='next()']");
//
//    @FindBy(xpath="//div[contains(text(),'Your vehicle has updates available')]")
//    protected static WebElement RecallText;
    private static By bRecallText = By.xpath("//div[contains(text(),'Your vehicle has updates available')]");

    //    @FindBy(xpath="//div[contains(text(),'Or choose vehicle specs')]")
//    protected static WebElement vehicleSpecs;
    private static By bVehicleSpecs = By.xpath("//div[contains(text(),'Or choose vehicle specs')]");



    public void handleTry() {
        sleep(1500);
        try {
            driver.findElement(bTryAgainBtn).click();
            System.out.println(" Try Again happen !");
            sleep(1500);
        } catch (WebDriverException we) {

        }
    }


    public void decodeVINInfo(String item) {
        clearAndInput(bVerifyField, item);
        sleep(1000);
        clickElementWithSeconds(bVerifyBtn,1000);
    }

    public void clickBtnOnVehicle(String buttonName) {
        switch (buttonName) {
            case "VERIFY":
//                handleTry();
                pWait.until(conditionClick(bVerifyBtn)).click();
                break;
            case "CANCEL":
//                clickElementWithSeconds(bCancelBtn,1000);
                break;
//            case "I AM A NEW CUSTOMER":
//                driver.switchTo().frame(0);
//                sleep(3000);
//                pWait.until(conditionVisible(bnewCustomBtn));
//                driver.findElement(bnewCustomBtn).click();
//                break;
        }

    }

    public void inputOdometerContinueInfo(String odo) {
//          handleTry();
//        WebElement element = pWait.until(conditionVisible(bOdometer));
//        element.click();
//        clearAndSend(element,odo);
        clearAndSend(bOdometer, odo);
        sleep(300);
        clickElementWithSeconds(bContineBtn, 1000);

    }


    public void chooseVehicleSpecs(String field, String value) {
        sleep(2000);
        switch (field) {
            case "MAKE":
                clickVehicleSpecs(bMake, value);
                break;
            case "YEAR":
                clickVehicleSpecs(bYear, value);
                break;
            case "MODEL":
                clickVehicleSpecs(bModel, value);
                break;
            case "TRANSMISSION":
                if (SyncPage.isElementPresentAndVisible(bTransmisionTag)) {
                    clickVehicleSpecs(bTransmision, value);
                }
                break;
            case "ENGINE":
                if (SyncPage.isElementPresentAndVisible(bEngineTag)) {
                    clickVehicleSpecs(bEngine, value);
                }
                break;
            case "DRIVE TRAIN":
                if (SyncPage.isElementPresentAndVisible(bDriveTag)) {
//                    System.out.println( " bTransmisionTag is toString    "  + driver.findElement(bDriveTag).toString());
                    clickVehicleSpecs(bDrive, value);
                }
                break;
        }

    }

    private void clickVehicleSpecs(By namelist, String value) {
        List<WebElement> elements = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(namelist));
        for (WebElement element : elements) {
            if (element.getText().equals(value)) {
                element.click();
                break;
            }
        }
    }

    public int getRecallListSize() {
        pWait.until(conditionVisible(bRecallText));
        return  driver.findElements(bRecallList).size();
    }

    public boolean showVehicleSpecs() {
        try{
            sWait.until(conditionVisible(bVehicleSpecs));
            return false;
        }catch (Exception ex){
            return false;
        }
    }

}
