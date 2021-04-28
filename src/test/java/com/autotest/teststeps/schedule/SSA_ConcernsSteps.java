package com.autotest.teststeps.schedule;

import com.automation.pages.appointment_manager.AM_CustomerSearchPage;
import com.automation.pages.schedule.SSA_ConcernsPage;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import org.apache.log4j.Logger;

public class SSA_ConcernsSteps extends BaseTestSteps {


    private static Logger log = Logger.getLogger(SSA_ConcernsSteps.class);

    SSA_ConcernsPage sacPage = new SSA_ConcernsPage();



    @And("^I add \"([^\"]*)\" concern with comment \"(.*)\" on 'Customer Concerns' page$")
    public void iAddConcernsCommentOnCustomerconcernPage(String order,String comment) {
        CommonMethods.sleep(2500);
        int item = toBeNumber(order);
        sacPage.addConcernsComment(item,comment);
    }


    @And("^I click \"([^\"]*)\" button on 'Customer Concerns' page$")
    public void iClickButtonOCustomrConcernPage(String buttonName) {
        switch (buttonName.toUpperCase()) {
            case "NEXT":
                CommonMethods.sleep(2000);
                sacPage.clickNextBtn();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button in SSA$")
    public void iClickButtonInSSA(String buttonName) {
        iClickButtonOCustomrConcernPage(buttonName);
    }

}
