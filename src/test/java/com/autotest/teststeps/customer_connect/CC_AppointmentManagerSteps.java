package com.autotest.teststeps.customer_connect;

import com.automation.pages.appointment_manager.AM_CustomerSearchPage;
import com.automation.utils.dataProvider.TestParameters;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class CC_AppointmentManagerSteps extends BaseTestSteps {

    AM_CustomerSearchPage amlPage = new AM_CustomerSearchPage();
    TestParameters tp = new TestParameters();

    @Then("^I see 'Appointment Manager' page$")
    public void iLandOnAMLPage() {
        String actualTitle = amlPage.verifyAMPageTiTle("Appointment Manager");
        assertEquals("Appointment Manager", actualTitle);
//            case "Dynamic MPI":
//                actualTitle = dmpiPage.verifyDMPIPageTiTle(pageName);
//                assertEquals(expectedTitle, actualTitle);
//                break;
    }

    @And("^I see same \"([^\"]*)\" on 'Appointment Manager' page$")
    public void iSeeSamThingOnAMPage(String name) {
        String actualResult = "";
        switch (name) {
            case "VIN":
                actualResult = amlPage.getVINOnAMLookup();
                break;
        }
        Assert.assertTrue(actualResult.contains(tp.vinOnTaskDetail));
    }

}





