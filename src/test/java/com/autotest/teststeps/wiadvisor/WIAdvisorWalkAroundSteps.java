package com.autotest.teststeps.wiadvisor;

import com.automation.pages.wiadvisor.WiAdvisorIndexPage;
import com.automation.pages.wiadvisor.WiAdvisorROPage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;

public class WIAdvisorWalkAroundSteps extends BaseTestSteps {

    @And("^I click \"([^\"]*)\" button in 'Walk Around' page$")
    public void iClickButtonInWorkAround(String buttonName) {
        WiAdvisorROPage page = new WiAdvisorROPage();
        page.clickButtonInROPage(buttonName);
    }

}
