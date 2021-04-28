package com.autotest.teststeps.schedule;

import com.automation.pages.schedule.SSA_ConcernsPage;
import com.automation.pages.schedule.SSA_SchedulePage;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import org.apache.log4j.Logger;

public class SSA_ScheduleSteps extends BaseTestSteps {


    private static Logger log = Logger.getLogger(SSA_ScheduleSteps.class);

    SSA_SchedulePage saSchPage = new SSA_SchedulePage();

    @And("^I click week \"([^\"]*)\" button \"(.*)\" times on 'Schedule' page$")
    public void iClickWeekButtonOnSchedulePage(String buttonName,int times) {
        saSchPage.handleTry();
        saSchPage.waitForPageToLoad();
        saSchPage.clickWeekButtonTimes(buttonName, times);
    }

    @And("^I choose \"([^\"]*)\" available time on 'Schedule' page$")
    public void iChooseTimeOnSchedulePage(String order) {
        int item = toBeNumber(order);
        CommonMethods.sleep(3000);
        saSchPage.clickTimeSlot(item);
    }


    @And("^I choose \"([^\"]*)\" available transportation option on 'Schedule' page$")
    public void iChooseTransportsationSchedulePage(String option) {
        String item = toBeAlpha(option);
        saSchPage.handleTry();
        saSchPage.clickTransportation(item);
    }


    @And("^I click \"([^\"]*)\" button on 'Schedule' page$")
    public void iClickButtonOSchedulePage(String buttonName) {
        saSchPage.waitForPageToLoad();
        switch (buttonName.toUpperCase()) {
            case "NEXT":
                CommonMethods.sleep(2000);
                saSchPage.clickNextBtn();
                break;
        }
    }


}
