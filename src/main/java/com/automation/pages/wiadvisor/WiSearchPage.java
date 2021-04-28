package com.automation.pages.wiadvisor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

public class WiSearchPage extends WiAdvisorIndexPage {

    private static final String XPATH_SearchDrop = "//a[contains(@ng-click,'search') and text()='%s']";

    //search tab
    private static By bSearchTab = By.xpath("//a[@ui-sref='vehicles.search']");
    //search field
    private static By bSearchField = By.xpath("//input[@name='searchCustomerName']");
    //search text field
    private static By btextField = By.xpath("//input[@ng-model='search.searchQuery']");
    //search drop down --Default
    private static By bSearchDropDown = By.xpath("//button[@data-toggle='dropdown' and contains(text(),'Customer Name')]");
    //search select drop down
    private static By bSearchDrop = By.xpath("//div[@class='search-bar-container']/div[2]");

    //drop down Customer Name
    private static By bCustomerNameDrop = By.xpath("//a[contains(@ng-click,'search') and text()='Customer Name']");
    //drop down vin
    private static By bVinDrop = By.xpath("//a[contains(@ng-click,'search') and text()='VIN']");
    //drop down vin
    private static By bLicensePlateDrop = By.xpath("//a[contains(@ng-click,'search') and text()='License Plate']");
    //drop down vin
    private static By bCustomerPhoneDrop = By.xpath("//a[contains(@ng-click,'search') and text()='Customer Phone']");
    //search button
    private static By bSearchButton = By.xpath("//button[contains(text(),'Search')]");

    private static By bSelectDrop(String drop) {
        By bDrop = bCustomerNameDrop;
        String dropDown = drop.toLowerCase().trim();
        switch(dropDown){
            case "customer name": bDrop = bCustomerNameDrop;
                break;
            case "customername":  bDrop = bCustomerNameDrop;
                break;
            case "customer phone": bDrop = bCustomerPhoneDrop;
                break;
            case "customerphone": bDrop = bCustomerPhoneDrop;
                break;
            case "vin": bDrop = bVinDrop;
                break;
            case "license plate": bDrop = bLicensePlateDrop;
                break;
            case "licenseplate":  bDrop = bLicensePlateDrop;
                break;
        }
        return bDrop ;
    }


    //customer with row number
    private static By bCustomerUnderSearch(String rowNumber){
        String locator = "//tbody/tr[@aria-hidden='false']/td/strong["+rowNumber+"]";
        return By.xpath(locator);
    }

    //customer with Row Line number
    private static By bSelectRowBtn(String rowNumber){
        String locator = "//tbody["+rowNumber+"]//button[@aria-hidden='false']";
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

    public void clickOnSearchTab(){
        clickElementWithException(bSearchTab);
    }

    public void searchCustomer(String customerName){
        clearAndInputElementWithException(bSearchField, customerName);
        clickElementWithException(bSearchButton);
    }

    public void searchField(String fieldValue){
        clearAndInputElementWithException(btextField, fieldValue);
    }

    public void searchVin(String vin){
        clearAndInputElementWithException(bSearchField, vin);
        clickElementWithException(bSearchDropDown);
        clickElementWithException(bVinDrop);
        clickElementWithException(bSearchButton);
    }

    public void searchByDrop(String drop,String field){
        clickElementWithException(bSearchDrop);
        clickElementWithException(bSelectDrop(drop));
        clearAndInputElementWithException(btextField, field);
        clickElementWithException(bSearchButton);
    }

    public void selectCustomer(String rowNumber){
        if(rowNumber.equals("first")){
            rowNumber="1";
        }
        clickElementWithException(bCustomerUnderSearch(rowNumber));
    }

    public void selectLineCustomer(String rowNumber){
        if(rowNumber.equals("first")){
            rowNumber="1";
        }
        clickElementWithException(bSelectRowBtn(rowNumber));
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
