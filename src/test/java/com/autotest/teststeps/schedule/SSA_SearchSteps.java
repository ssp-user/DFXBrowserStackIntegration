package com.autotest.teststeps.schedule;

import com.automation.pages.schedule.SSA_SearchPage;
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

public class SSA_SearchSteps extends BaseTestSteps {


    private static Logger log = Logger.getLogger(SSA_SearchSteps.class);

    public static int recalls;

    SSA_SearchPage sPage = new SSA_SearchPage();

    @And("^I search \"([^\"]*)\" on 'Search' page in SSA$")
    public void iSearchWithInSearchPage(String searchTerm) {
        sPage.searchCustomer(searchTerm);
    }

    @And("^I select vehicle on 'Search' page in SSA$")
    public void iSelectVehicleOnSearchPage() {
        sPage.clickVechicle();
    }

    @And("^I input \"([^\"]*)\" 'phone' on 'Search' page in SSA$")
    public void iInputPhoneOnSearchPage(String searchTerm) {
        sPage.inputPhone(searchTerm);
    }

    @And("^I click \"([^\"]*)\" button on 'Search' page in SSA$")
    public void iClickBtnOnSearchPage(String searchTerm) {
        sPage.clickBtnOnSearch(searchTerm.toUpperCase());
    }

    @And("^I input \"([^\"]*)\" to continue on 'Search' page in SSA$")
    public void iInputClickContinueOnSearchPage(String meter) {
        sPage.inputOdometerContinue(meter);
    }

    @Then("^I verify the 'Recall count' status \"([^\"]*)\" on 'Search' page in SSA$")
    public void iVesrifyRecallCountTabServicePage(String status) {
        CommonMethods.sleep(2000);
        String sRecall = sPage.getRecallCount();
        if (sRecall.isEmpty()){
            recalls= 0;
        }else{
            recalls=Integer.valueOf(sRecall);
        }

        if (status.equalsIgnoreCase("yes")){
            verifyFalse(" verify the Recalls status on the screen  " , sRecall.isEmpty());
//            Assert.assertFalse(" verify the Recalls status on the screen  " ,sRecall.isEmpty());
        }else if (status.equalsIgnoreCase("no")){
            verifyTrue(" verify the Recalls status on the screen  " ,sRecall.isEmpty());
//            Assert.assertTrue(" verify the Recalls status on the screen  " ,sRecall.isEmpty());
        }

    }


}
