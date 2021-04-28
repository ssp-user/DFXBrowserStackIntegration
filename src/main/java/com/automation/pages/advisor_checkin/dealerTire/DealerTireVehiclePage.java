package com.automation.pages.advisor_checkin.dealerTire;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class DealerTireVehiclePage extends WebPage {

    //page object
    //vin input field
    private static By bVinDealerTire = By.xpath("//input[@placeholder='VIN']");

    //vin decode button
    private static By bDecodeButton = By.xpath("//button[contains(text(),'DECODE')]");

    //odometer field
    private static By bOdoMeterField = By.xpath("//input[@name='odometer']");

    //next button on the vehicle page
    private static By bNextONVechicle = By.xpath("//button[@id='g-proceed']/span");

//    @FindBy(css = "select[name='manafacture']")
//    private static WebElement manufacture; // Manufacture Selection Field
    private static By bmanufacture = By.cssSelector("select[name='manafacture']");

//    @FindBy(css = "select[name='year']")
//    private static WebElement year; // Year Selection Field
    private static By byear = By.cssSelector("select[name='year']");

//    @FindBy(css = "select[name='model']")
//    private static WebElement model; // Model Selection Field
    private static By bmodel = By.cssSelector("select[name='model']");

//    @FindBy(css = "select[name='transmission']")
//    private static WebElement transmission; // Transmission Selection Field
    private static By btransmission = By.cssSelector("select[name='transmission']");

//    @FindBy(css = "select[name='cylinder']")
//    private static WebElement cylinder; // Cylinder Selection Field
    private static By bcylinder = By.cssSelector("select[name='cylinder']");

//    @FindBy(css = "select[name='drive']")
//    private static WebElement drive; // Drive Selection Field;
    private static By bdrive = By.cssSelector("select[name='drive']");

    public void inputFieldOnVechiclePage(String inputString, String fieldName){
        switch(fieldName){
            case "VIN":
                clearAndInputElementWithException(bVinDealerTire, inputString);
                break;
            case "ODOMETER":
            	sleep(5000);
                clearAndInputElementWithException(bOdoMeterField, inputString);
                break;
        }
    }

    public void setSelectFieldValue(String field, String value ) {
    	sleep(5000);
        field = field.toLowerCase();
        if (field.equals("maker")){
            field = "manafacture";
        }else if (field.equals("engine")){
            field = "cylinder";
        }else if (field.equals("drive train")){
            field = "drive";
        }
        By locator = By.cssSelector(String.format("select[name='%s']",field));
        WebElement element = pWait.until(conditionVisible(locator));
        selectDropListByValue(element,value);
    }

    public void clickButtonOnVechiclePage(String buttonName){
        long startTime = System.currentTimeMillis();
        switch(buttonName){
            case "DECODE":
                clickElementWithException(bDecodeButton);
                break;
            case "NEXT":
                clickElementWithException(bNextONVechicle);
                break;                
            /*case "NEXT":
                DealerTireHistoryPage dhPage= new DealerTireHistoryPage();
                while(!dhPage.isUserOnHistory()){
                    clickElementWithException(bNextONVechicle);
                    sleep(1000);
                    if((System.currentTimeMillis()-startTime)>30000){
                        Assert.fail("<====== Not able to click the next button on the vehicle page, test failed ======>");
                        break;
                    }
                }
                break;*/
        }
    }
}
