package com.autotest.teststeps.schedule;

import com.automation.pages.schedule.SSA_ConfirmationPage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import org.apache.log4j.Logger;

public class SSA_ConfirmationSteps extends BaseTestSteps {

    private static Logger log = Logger.getLogger(SSA_ConfirmationSteps.class);
    SSA_ConfirmationPage ssaCfmpPage = new SSA_ConfirmationPage();

    @And("^I see \"([^\"]*)\" message on 'Confirmation' page$")
    public void iClickButtonOnAppointmentPage(String msg) {
        verifyTrue("verify the message on scrren " + msg , ssaCfmpPage.getConfirmation().contains(msg) );
    }

}
