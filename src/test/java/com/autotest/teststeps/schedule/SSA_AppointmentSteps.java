package com.autotest.teststeps.schedule;

import com.automation.pages.appointment_manager.AM_CustomerSearchPage;
import com.automation.pages.schedule.SSA_AppointmentPage;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class SSA_AppointmentSteps extends BaseTestSteps {


    private static Logger log = Logger.getLogger(SSA_AppointmentSteps.class);

    SSA_AppointmentPage ssaApPage = new SSA_AppointmentPage();

    @And("^I get appointment time on 'Appointment Summary' page$")
    public void iClickButtonOSchedulePage() {
        System.out.println(" the Appoint time is " + ssaApPage.getAppointment());
    }

    @And("^I click \"([^\"]*)\" button on 'Appointment Summary' page$")
    public void iClickButtonOnAppointmentPage(String buttonName) {
        ssaApPage.clickBtnOnAppointment(buttonName.toUpperCase());
    }

    @And("^I enter customer data set on 'Appointment Summary' page$")
    public void iSetVehicleDataOnSearchPage(DataTable dataTable) {
//        cnvPage.waitForCnVPageToLoad();
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String field = map.get("FieldName").toUpperCase();
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                CommonMethods.sleep(500);
                ssaApPage.inputCustomerData(field, value);
            }
        }
    }

    @And("^I see and click \"([^\"]*)\" button on 'Appointment Summary' page$")
    public void iSeeClickButtonOnAppointmentPage(String buttonName) {
        boolean itsme = ssaApPage.hasClickMessageBtn(buttonName);
        verifyTrue("verify the button on screen  " + buttonName, itsme );
        Assert.assertTrue("verify the button on screen  " + buttonName, itsme );
    }


    @Then("^I see the 'Recall count' status \"([^\"]*)\" on 'Appointment Summary' page$")
    public void iSeeRecallCountAppointmentPage(String status) {
        CommonMethods.sleep(2000);
        String sRecall = ssaApPage.getRecallCount();
        if (status.equalsIgnoreCase("yes")){
//            verifyFalse(" verify the Recalls status on the screen  " , sRecall.isEmpty());
            Assert.assertFalse(" verify the Recalls status on the screen  " ,sRecall.isEmpty());
        }else if (status.equalsIgnoreCase("no")){
//            verifyTrue(" verify the Recalls status on the screen  " ,sRecall.isEmpty());
            Assert.assertTrue(" verify the Recalls status on the screen  " ,sRecall.isEmpty());
        }

    }




}
