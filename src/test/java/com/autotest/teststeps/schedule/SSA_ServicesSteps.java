package com.autotest.teststeps.schedule;

import com.automation.pages.appointment_manager.AM_CustomerSearchPage;
import com.automation.pages.schedule.SSA_ConcernsPage;
import com.automation.pages.schedule.SSA_ServicesPage;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import org.apache.log4j.Logger;

public class SSA_ServicesSteps extends BaseTestSteps {


    private static Logger log = Logger.getLogger(SSA_ServicesSteps.class);

    SSA_ServicesPage sasPage = new SSA_ServicesPage();
    SSA_ConcernsPage sacPage = new SSA_ConcernsPage();


    @And("^I add \"([^\"]*)\" service with comment \"(.*)\" on 'Service' page in SSA$")
    public void iAddConcernsCommentOnService(String order,String comment) {
        sasPage.waitForPageToLoad();
        int item = toBeNumber(order);
        sacPage.addConcernsComment(item,comment);
    }


    @And("^I click \"([^\"]*)\" button on 'Service' page in SSA$")
    public void iClickButtonOCustomrServicePage(String buttonName) {
        sasPage.waitForPageToLoad();
        switch (buttonName.toUpperCase()) {
            case "NEXT":
                CommonMethods.sleep(2000);
                sasPage.clickNextBtn();
                break;
        }
        sasPage.handleTry();
    }

}
