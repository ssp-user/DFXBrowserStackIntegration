package com.autotest.teststeps.customer_connect;

import com.automation.pages.third_party.MightyTextPage;
import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class CC_MightyTextSteps extends BaseTestSteps {

    MightyTextPage mtPage = new MightyTextPage();
    TestParameters tp = new TestParameters();

    @When("^I send SMS \"([^\"]*)\" to dealer on 'mightytext.net' page$")
    public void iSendSMStoDealerOnMightyTextPage(String textSMS) {
        if (textSMS.contains("random")) {
            CommonMethods cm = new CommonMethods();
            textSMS = textSMS.replace("random", cm.getRandomText(5));
        }
        mtPage.sendMessageToDealer(textSMS);
    }
       
    @Then("^I see SMS contains 'TEST random' associated with on 'ReceiveaSMS.com :: Canada' page$")
    public void iSeeSMSOnMightyTextPage() {
        mtPage.validateSMS();
    }

    @And("^I delete SMS$")
    public void iDeleteSMS() {
        mtPage.deleteSMS();
    }

}





