package com.autotest.teststeps.wiadvisor;

import com.automation.pages.wiadvisor.*;
import com.automation.utils.dataProvider.TestParameters;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

public class WIAdvisorSteps extends BaseTestSteps {

    WiAdvisorLoginPage wilPage = new WiAdvisorLoginPage();
    WiAdvisorIndexPage wii = new WiAdvisorIndexPage();

    //this is for wiadvisor login
    @When("^I login the WiAdvisor as UserID \"(.+)\" and Password \"(.+)\" and Dealer Code \"(.+)\"$")
    public void iLoginWiAdvisorWithUserNamePassWordAndDealerCode(String username, String password, String dealerCode) {
        TestParameters.setUserName(username);
        TestParameters.setDealerCode(dealerCode);
        wilPage.loginWiAdvisorWithUserID(username);
        wilPage.loginWiAdvisorWithPassword(password);
        wilPage.loginWiAdvisorWithDealerCode(dealerCode);
        wilPage.clickOnSignInButton();
       // wilPage.selectEnvironment(dealerCode);
    }

    @And("^I search customer \"([^\"]*)\" under \"([^\"]*)\" tab$")
    public void iSearchCustomer(String customerName, String tabName) {
        switch (tabName) {
            case "Search":
                wii.searchCustomer(customerName);
                break;
        }
    }

    @And("^I search \"([^\"]*)\" with \"([^\"]*)\" in 'Index-Search' tab$")
    public void iSearchWithInSearch(String fieldName, String fieldValue) {
        WiSearchPage page = new WiSearchPage();
        page.searchByDrop(fieldName, fieldValue);

    }

    @And("^I search VIN \"([^\"]*)\" under \"([^\"]*)\" tab$")
    public void iSearchVin(String vin, String tabName) {
        switch (tabName) {
            case "Search":
                wii.searchVin(vin);
                break;
        }
    }

    @And("^I select \"([^\"]*)\" customer from the list in 'Index-Search' tab$")
    public void iSelectNumLineCustomerSearchTab(String rowNumber) {
        WiAdvisorIndexPage page = new WiAdvisorIndexPage();
        page.selectCustomer(rowNumber);

    }

    @And("^I select \"([^\"]*)\" vehicle from customer under \"([^\"]*)\" tab$")
    public void iSelectVehicleFromCustomer(String vehicleNumber, String tabName) {
        switch (tabName) {
            case "Search":
                wii.selectVechicleFromCustomer(vehicleNumber);
                break;
        }
    }

    @And("^I select \"([^\"]*)\" vehicle from customer in 'Index-Search' tab$")
    public void iSelectVehicleFromCustomerInSearch(String vehicleNumber) {
        WiSearchPage page = new WiSearchPage();
        page.selectVechicleFromCustomer(vehicleNumber);
    }

    @And("^I enter \"([^\"]*)\" under \"([^\"]*)\" field in 'Assign RO' tab$")
    public void iEnterTagUnderFieldInAssinROTab(String tag, String fieldName) {
        WiAdvisorAssignROTab page = new WiAdvisorAssignROTab();
        switch (fieldName) {
            case "Tag":
                page.enterTagAssignRo(tag);
                break;
        }
    }


    @And("^I click \"([^\"]*)\" button on 'index' page$")
    public void iClickButtonOnIndexPage(String buttonName) {
        switch (buttonName) {
            case "Accept":
                wii.clickOnAcceptOnWiadvisorIndex();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button in 'index' page$")
    public void iClickButtonInIndexPage(String buttonName) {
        switch (buttonName) {
            case "Accept":
                wii.clickOnAcceptOnWiadvisorIndex();
                break;
        }
    }


    @And("^I click \"([^\"]*)\" tab in 'index' page$")
    public void iClickTabInIndexPage(String tabName) {
        WiSearchPage page = new WiSearchPage();
        switch (tabName) {
            case "Search":
                page.clickOnSearchTab();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button in 'WiAdvisorRO' page$")
    public void iClickButtonOnWiROPage(String buttonName) {
        WiAdvisorROPage page = new WiAdvisorROPage();
        page.clickButtonInROPage(buttonName);
    }

}
