package com.autotest.teststeps.service_dashboard;

import com.automation.pages.service_dashboard.SD_ServiceVisit;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

public class SD_ServiceVisitSteps extends BaseTestSteps{

    SD_ServiceVisit svPage = new SD_ServiceVisit();

    @Then("^I see service lane with (.+) with \"([^\"]*)\" status on 'SERVICE VISIT' page$")
    public void assertServiceLaneStatusServiceVisit(String vin, String status){
        String actualStatus = svPage.verifyServiceLaneStatusServiceVisit(vin, status);
        assertEquals(status, actualStatus);
    }

    @And("^I click \"([^\"]*)\" tab on 'SERVICE VISIT' page$")
    public void iClickTabOnServiceVisitPage(String tabName) {
        switch (tabName) {
            case "INSPECTION":
                svPage.clickOnInspectionTab();
                break;
        }
    }
}




