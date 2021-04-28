package com.autotest.teststeps.service_dashboard;

import com.automation.pages.service_dashboard.SD_SearchPage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;

public class SD_SearchSteps extends BaseTestSteps{

    SD_SearchPage srcPage = new SD_SearchPage();

    @And("^I \"([^\"]*)\" the service lane \"([^\"]*)\" status on 'SEARCH' page$")
    public void iVerifyServiceLaneStatus(String action, String vin){
        switch (action){
            case "delete":
                srcPage.deleteServiceLaneIfExist(vin);
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on Service Dashboard 'SEARCH' page$")
    public void iClickBtnOnServiceDashboardSearchPage(String buttonName) {
        switch (buttonName) {
            case "Back":
                srcPage.clickOnBack();
                break;
        }
    }

}




