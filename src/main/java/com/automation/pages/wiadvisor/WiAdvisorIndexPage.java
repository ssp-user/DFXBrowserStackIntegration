package com.automation.pages.wiadvisor;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;

public class WiAdvisorIndexPage extends WebPage {

    //page body
    private static By bPageBody = By.xpath("//body");
    //locator
    private static By bAccept = By.xpath("//button[contains(text(),'Accept')]");
    //search tab
    private static By bSearchTab = By.xpath("//a[@ui-sref='vehicles.search']");
    //search tab
    private static By bWelcome = By.xpath("//a[@ui-sref='vehicles.welcome']");
    //search tab
    private static By bCompleted = By.xpath("//a[@ui-sref='vehicles.completed']");
    //search tab
    private static By bInprogress = By.xpath("//a[@ui-sref='vehicles.inprogress']");
    //search field
    private static By bSearchField = By.xpath("//input[@name='searchCustomerName']");
    //search drop down
    private static By bSearchDropDown = By.xpath("//button[@data-toggle='dropdown' and contains(text(),'Customer Name')]");
    //drop down vin
    private static By bSearchVin = By.xpath("//a[contains(@ng-click,'search') and text()='VIN']");
    //search button
    private static By bSearchButton = By.xpath("//button[contains(text(),'Search')]");
    //customer with row number
    private static By bCustomerUnderSearch(String rowNumber){
        String locator = "//tbody/tr[@aria-hidden='false']/td/strong["+rowNumber+"]";
        return By.xpath(locator);
    }

    //vehicle number from customer. This is for single vehicle
    private static By bVehicleFromCustomer(String vehicleNumber){
        String locator = "(//button[contains(text(),'Select')])["+vehicleNumber+"]";
        return By.xpath(locator);
    }

    //vehicle number from customer. This is for multiple vehicle
    private static By bMultiVehicleFromCustomer(String vehicleNumber){
        String locator = "(//button[@ng-click='searchResults.selectVehicle(vehicle.vin)'])["+vehicleNumber+"]";
        return By.xpath(locator);
    }

    //wait for dealership information load
    public void waitForDealershipInfoloading(){
        long startTime = System.currentTimeMillis();
        while(byElementHasClass(bPageBody,"modal-open")){
            System.out.println("<====== Dealership information now loading (￣▽￣) ======>");
            sleep(1500);
            if((System.currentTimeMillis()-startTime)> 15000){
                pageRefresh();
                System.out.println("<====== Dealership information loading error, will refresh page and try again (｡・`ω´・) ======>");
            }else if((System.currentTimeMillis()-startTime)> 60000){
                Assert.fail("<====== Dealership information still loading, test failed (→_→) ======>");
                break;
            }
        }
    }

    public void clickOnAcceptOnWiadvisorIndex(){
        waitForDealershipInfoloading();
        clickElementWithException(bAccept);
    }

    public void clickOnSearchTab(){
        clickElementWithException(bSearchTab);
    }

    public void clickWelcomeTab(){
        clickElementWithException(bWelcome);
    }

    public void clickInProgressTab(){
        clickElementWithException(bInprogress);
    }

    public void clickCompletedTab(){
        clickElementWithException(bCompleted);
    }

    public void searchCustomer(String customerName){
        clearAndInputElementWithException(bSearchField, customerName);
        clickElementWithException(bSearchButton);
    }

    public void searchVin(String vin){
        clearAndInputElementWithException(bSearchField, vin);
        clickElementWithException(bSearchDropDown);
        clickElementWithException(bSearchVin);
        clickElementWithException(bSearchButton);
    }

    public void selectCustomer(String rowNumber){
        if(rowNumber.equals("first")){
            rowNumber="1";
        }
        clickElementWithException(bCustomerUnderSearch(rowNumber));
    }

    public void selectVechicleFromCustomer(String vehicleNumber){
        if(vehicleNumber.equals("first")){
            vehicleNumber="1";
        }
        try{
            sWait.until(conditionVisible(bVehicleFromCustomer(vehicleNumber))).click();
        }catch(WebDriverException e){
            sWait.until(conditionVisible(bMultiVehicleFromCustomer(vehicleNumber))).click();
        }
    }
}
