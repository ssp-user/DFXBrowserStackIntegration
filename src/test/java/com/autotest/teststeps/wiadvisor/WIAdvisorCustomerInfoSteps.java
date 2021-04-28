package com.autotest.teststeps.wiadvisor;

import com.automation.pages.wiadvisor.*;
import com.automation.utils.dataProvider.TestParameters;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

public class WIAdvisorCustomerInfoSteps extends BaseTestSteps {

    WiAdvisorCustomerInfoTab cit = new WiAdvisorCustomerInfoTab();


    @And("^I select \"([^\"]*)\" from \"([^\"]*)\" section under \"([^\"]*)\" popup$")
    public void iSelectCustomerFromSideOnPopUp(String dataName, String sectionName, String popUpName) {
        switch (popUpName) {
            case "Customer Information":
                cit.selectCustomerFromSection(sectionName);
                break;
        }
    }

    @And("^I select \"([^\"]*)\" when 'Customer Information' popup$")
    public void iSelectInCustomerInforPopUp(String sectionName) {
        WiAdvisorCustomerInfoTab page = new WiAdvisorCustomerInfoTab();
        page.selectCustomerFromSection(sectionName);
    }

    @And("^I update Odometer to \"([^\"]*)\" under \"([^\"]*)\" section$")
    public void iUpdateOdometerUnderSection(String oDometerNo, String sectionName) {
        switch (sectionName) {
            case "Vehicle Information":
                cit.updateOdometerUnderVehicleInfo(oDometerNo);
                break;
        }
    }

    @And("^I update \"([^\"]*)\" with \"([^\"]*)\" in 'Vehicle Information' tab$")
    public void iUpdateFieldInCustomerInformation(String field, String value) {
        WiAdvisorCustomerInfoTab page = new WiAdvisorCustomerInfoTab();
        page.updateFieldInCustomerInfor(field, value);
    }

    @And("^I update Home Number to \"([^\"]*)\" under \"([^\"]*)\" section$")
    public void iUpdateNumberUnderSection(String number, String sectionName) {
        switch (sectionName) {
            case "Customer Information":
                cit.updateNumberUnderCustomerInfo(number);
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button in 'Customer Info' page$")
    public void iClickBtnInCustomerInformation(String btn) {
        WiAdvisorCustomerInfoTab page = new WiAdvisorCustomerInfoTab();
        page.clickButtonInROPage(btn);
    }

}
