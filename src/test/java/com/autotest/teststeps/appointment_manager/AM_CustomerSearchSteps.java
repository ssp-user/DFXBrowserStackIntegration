package com.autotest.teststeps.appointment_manager;

import com.automation.pages.appointment_manager.AM_CustomerSearchPage;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class AM_CustomerSearchSteps extends BaseTestSteps {


    private static Logger log = Logger.getLogger(AM_CustomerSearchSteps.class);

    AM_CustomerSearchPage amcsPage = new AM_CustomerSearchPage();

    @And("^I input \"([^\"]*)\" on the search field on 'Lookup' page$")
    public void iInputSomethingOnSearchOnPage(String searchTerm) {
        amcsPage.inputSearch(searchTerm);
    }

    @And("^I click \"([^\"]*)\" button on 'Lookup' page$")
    public void iClickButtonOnLookUpPage(String buttonName) {
        switch (buttonName) {
            case "VIN":
                amcsPage.clickOnVINButton();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on 'CUSTOMER SEARCH' page$")
    public void iClickButtonOnSearchPage(String buttonName) {
        amcsPage.clickBtnOnSearch(buttonName.toUpperCase());
    }

    @And("^I click \"([^\"]*)\" button 'under vehicle' on 'CUSTOMER SEARCH' page$")
    public void iClickButtonVehicleOnSearchPage(String buttonName) {
        if (buttonName.equals("+")){
            buttonName = "+VIN";
        }
        amcsPage.clickBtnOnSearch(buttonName.toUpperCase());
    }


    @And("I click \"([^\"]*)\" icon on the \"([^\"]*)\" appointment on 'Lookup' page")
    public void iClickIconOnAppointmentOnLookUpPage(String icon, String appointment) {
        switch (appointment) {
            case "Booked":
                switch (icon) {
                    case "Delete":
                        amcsPage.clickOnDeleteIconOnAppointment();
                        break;
                }
                break;
        }
    }

    @And("^I select \"([^\"]*)\" customer on 'CUSTOMER SEARCH' page$")
    public void iSelectCustomerOnSearchPage(String customer) {
        String number = toBeAlpha(customer);
        amcsPage.selectTheNumCustomer(number);
    }

    @And("^I search \"([^\"]*)\" with \"([^\"]*)\" on 'CUSTOMER SEARCH' page$")
    public void iSearchWithOnCustomerSearchPage(String searchField, String searchTerm) {
        String sField = searchField.toUpperCase();
        amcsPage.searchWithOnCustomerPage(sField,searchTerm);
    }

    @And("^I click \"([^\"]*)\" button or \"([^\"]*)\" customer vehicle if multiple on 'CUSTOMER SEARCH' page$")
    public void iClickBtnOrSelectVehicleOnSearchPage(String btn,String order) {
        int number = toBeNumber(order);
        amcsPage.clickBtnOrSelectCustomerVehicle(btn, number);
    }

    @And("^I see \"([^\"]*)\" is \"([^\"]*)\" on 'CUSTOMER SEARCH' page$")
    public void iSeeIsOnCustomerSearchPage(String field, String value) {
        CommonMethods.sleep(200);
        String result = amcsPage.getSearchWithCustomerPage(field.toUpperCase(),value).toUpperCase();
        verifyTrue(" Verify search " + field + " as " + value , result.contains(value.toUpperCase()));
        Assert.assertTrue(" Verify search " + field + " as " + value , result.contains(value.toUpperCase()));
    }
    
    @Then("^I check \"([^\"]*)\" on 'CUSTOMER SEARCH' page$")
    public void iCheckOnCustomerSearchPage(String field) {
        amcsPage.verifyElementExists(field);
    }    

 

}
