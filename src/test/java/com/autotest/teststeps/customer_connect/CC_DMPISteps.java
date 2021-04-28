package com.autotest.teststeps.customer_connect;

import com.automation.pages.DMPI.DynamicMPIPage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

public class CC_DMPISteps extends BaseTestSteps {

    DynamicMPIPage dmpiPage = new DynamicMPIPage();

    @Then("^I see 'Dynamic MPI' page$")
    public void iLandOnDMPIPage() {
        String actualTitle = dmpiPage.verifyDMPIPageTiTle("Dynamic MPI");
        assertEquals("Dynamic MPI", actualTitle);
    }

}





