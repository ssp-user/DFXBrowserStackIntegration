package com.autotest.teststeps.wiadvisor;

import com.automation.pages.wiadvisor.*;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

public class WIAdvisorHistorySteps extends BaseTestSteps {

    WiAdvisorIndexPage wii = new WiAdvisorIndexPage();

    @And("^I click \"([^\"]*)\" button in 'VIP/History' page$")
    public void iClickButtonInHistory(String buttonName) {
        WiAdvisorROPage page = new WiAdvisorROPage();
        page.clickButtonInROPage(buttonName);
    }

}
