package com.automation.pages.advisor_checkin;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ACI_WalkAroundPage extends ACI_FramePage {

    private static Logger log = Logger.getLogger(ACI_WalkAroundPage.class);
    //page object
    //passenger car vehicle type
//    private static By bPassengerCar = By.xpath("//span[contains(text(),'Passenger Car')]");
    private static By bPassengerCar = By.id("Layer_1_passenger_step_1");

    //suv vehicle type
    private static By bSUV = By.xpath("//a[contains(@ng-click,'shared.selectedCar = 2; shared.step = 1; updateStateAndAttachMedia(2)')]");
//    private static By bSUV = By.id("Layer_1_minivan_step_1");

    //pickup vehicle type
//    private static By bPickUp = By.xpath("//span[contains(text(),'Pickup')]");
    private static By bPickUp = By.id("Layer_1_pickup_step_1");
    //vehicle position
//    private static By bFront = By.xpath("//span[contains(text(),'Front')]");
    private static By bFront = By.cssSelector("div.vehicle span[ng-class*='front']");

    //white space on the walk around page
    private static By bWhiteSpaceWalkAround = By.xpath("//form[@name='walkaroundForm']//div[@class='cars-wrap clearfix']");

    //next button on walk round page
//    private static By bNextButtonOnWalkAround = By.id("g-wproceed");
    private static By bNextButtonOnWalkAround = By.cssSelector("#g-wproceed[ng-click*='nextTab()']");
//    private static By bNextButtonOnWalkAround = By.xpath("//form[@name='walkaroundForm']//span[text()='NEXT']");

    //select All OK on walk around page
    private static By bAllOkOnWalkPage = By.xpath("//div[@class='ok-btn']");

    private static By spinnerLocator = By.cssSelector("#g-wproceed > i");											// Walk-Around loading spinner

//    // Passenger Vehicle Type
//    @FindBy(id="Layer_1_passenger_step_1")
//    private static WebElement passengerVehicleType;
    private static By bpassengerVehicleType = By.id("Layer_1_passenger_step_1");
//
//    // Minivan Vehicle Type
//    @FindBy(id="Layer_1_minivan_step_1")
//    private static WebElement minivanVehicleType;
    private static By bminivanVehicleType = By.id("Layer_1_minivan_step_1");
//
//    // Pickup Vehicle Type
//    @FindBy(id="Layer_1_pickup_step_1")
//    private static WebElement pickupVehicleType;
//
//    // General Button on Walk Around Page, used to confirm button is clicked
////	@FindBy(css="div.vehicle span.general")
//    @FindBy(css="div.vehicle span[ng-class*='front']")
//    private static WebElement frontBtn;
    private static By bfrontBtn = By.cssSelector("div.vehicle span[ng-class*='front']");

    private  void  waitWalkAroundLoad(){		// Wait for page to finish loading
        dWait.until(ExpectedConditions.visibilityOfElementLocated(bNextButtonOnWalkAround));
        dWait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
    }

    public void clickNextOnWalkAround(){
        //clickElementWithException(bWhiteSpaceWalkAround); //this is to prevent next button not active or disable
        clickElementWithException(bNextButtonOnWalkAround);
    }

    public void clickAllOkOnWalkAround(){
        clickElementWithException(bAllOkOnWalkPage);
    }

    public void selectVehicleType(String vehicleType){
        waitWalkAroundLoad();
        switch(vehicleType){
            case "PASSENGER CAR":
                dWait.until(conditionVisible(bPassengerCar)).click();
                break;
            case "SUV/MINIVAN":
                dWait.until(conditionVisible(bSUV)).click();
                break;
            case "PICKUP":
                dWait.until(conditionVisible(bPickUp)).click();
                break;
        }
    }

    public  String  getFrontBgroundColorOnWalkAroundPage(){
        String color =  dWait.until(conditionVisible(bfrontBtn)).getCssValue("background-color");
        return color;
    }

}
