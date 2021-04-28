package com.automation.pages.schedule;

import com.automation.pages.common.WebPage;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SSA_SearchPage extends SSA_FramePage {

    private static Logger log = Logger.getLogger(SSA_SearchPage.class);


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
//    @FindBy(xpath="//div[contains(text(),'Or choose vehicle specs')]")
//    protected static WebElement vehicleSpecs;

//
//    @FindBy(xpath="//span[contains(text(),'Back')]")
//    protected static WebElement backBtn;
//
//
//	/*@FindBy(xpath="//div[@class='select-vehicle__confirm full-size-loader']")
//	protected static WebElement vehicleLoader;*/
//

    //   imported from TestNG

    //    @FindBy(xpath="//form[@name='searchForm']//input[@name='searchField']")
//    protected static WebElement searchField
    private static By bSearchField = By.id("search");

    //
//    @FindBy(xpath="//button[@class='button']")
//    protected static WebElement SearchBtn;
    private static By bSearchButton = By.xpath("//button[(@class='button') or (@class='search-btn')]");

    //    @FindBy(xpath="//div[@class='car-selection__inner']")
//    protected static WebElement vehicleDescription;
    private static By bVehicle = By.cssSelector("div.car-selection__inner");

    //
//    @FindBy(xpath="//input[@id='verifyField']")
//    protected static WebElement verifyField;
    private static By bVerifyField = By.id("verifyField");

    //    @FindBy(xpath="//button[@class='verify']")
//    protected static WebElement verifyBtn;
    private static By bVerifyBtn = By.cssSelector("button.verify");

    //    @FindBy(xpath="//input[@id='odometerField']")
//    protected static WebElement odometer;
    private static By bOdometer = By.id("odometerField");

    //    @FindBy(xpath="//div[@class='input-wrap']/div[2]/div")
//    protected static WebElement contineBtn;
    private static By bContineBtn = By.xpath("//div[@class='input-wrap']/div[2]/div");
    private static By bTryAgainBtn = By.xpath("//div[text()='Try again']");

    //    @FindBy(xpath="//button[@class='new-customer-btn']")
//    protected static WebElement newCust;
    private static By bnewCustomBtn = By.cssSelector("button.new-customer-btn");

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

    //    @FindBy(xpath="//div[@class='updates-available ng-binding ng-scope']")
//    protected static WebElement getRecallCount;
    private static By bRecallCount = By.xpath("//div[@class='updates-available ng-binding ng-scope']");


    public void searchCustomer(String item) {
//        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.switchTo().frame(0);
        clearAndSend(bSearchField, item);
        sleep(1000);
        pWait.until(conditionClick(bSearchButton)).click();

    }

    public void clickVechicle() {
        pWait.until(conditionVisible(bVehicle)).click();
    }

    public void inputPhone(String item) {
        clearAndSend(bVerifyField, item);
    }

    public void clickBtnOnSearch(String buttonName) {
        switch (buttonName) {
            case "VERIFY":
                handleTry();
                pWait.until(conditionClick(bVerifyBtn)).click();
                break;
            case "CANCEL":
//                clickElementWithSeconds(bCancelBtn,1000);
                break;
            case "I AM A NEW CUSTOMER":
                driver.switchTo().frame(0);
                sleep(3000);
//                clickElementWithException(bnewCustomBtn);
                WebElement element = pWait.until(conditionVisible(bnewCustomBtn));
                moveToClick(element);
                clickElementTimesTillDisappear(bnewCustomBtn,6);
//                clickElementWithException(element);
//                driver.findElement(bnewCustomBtn).click();
                break;
        }

    }

    public void inputOdometerContinue(String odo) {
        handleTry();
//        WebElement element = pWait.until(conditionVisible(bOdometer));
//        element.click();
//        clearAndSend(element,odo);
        clearAndSend(bOdometer, odo);
        sleep(300);
        clickElementWithSeconds(bContineBtn, 2000);

    }

//    public void handleTry() {
//        sleep(1500);
//        try {
//            driver.findElement(bTryAgainBtn).click();
//            System.out.println(" Try Again happen !");
//            sleep(1500);
//        } catch (WebDriverException we) {
//
//        }
//    }

    public void chooseVehicleSpecs(String field, String value) {
        System.out.println( " filed and value  " + field + "  " + value  );
        sleep(3000);
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
                    System.out.println( " bTransmisionTag is OK   "  + driver.findElement(bTransmisionTag).isDisplayed() );
                    System.out.println( " bTransmisionTag is toString    "  + driver.findElement(bTransmisionTag).toString());
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
                    System.out.println( " bTransmisionTag is OK   "  + driver.findElement(bDriveTag).isDisplayed() );
                    System.out.println( " bTransmisionTag is toString    "  + driver.findElement(bDriveTag).toString());
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

    public String getRecallCount() {
        String recall = pWait.until(conditionVisible(bRecallCount)).getText().trim();
        int idx = recall.indexOf("update");
        if (idx < 0){
            return "";
        }else{
            return  recall.substring(0,idx).trim();
        }
    }


}
