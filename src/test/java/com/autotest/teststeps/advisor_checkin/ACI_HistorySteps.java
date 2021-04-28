package com.autotest.teststeps.advisor_checkin;

import com.autotest.teststeps.BaseTestSteps;
import com.automation.pages.advisor_checkin.ACI_HistoryPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class ACI_HistorySteps extends BaseTestSteps {

    private static Logger log = Logger.getLogger(ACI_HistorySteps.class);

    ACI_HistoryPage ahPage = new ACI_HistoryPage();


    @And("^I click \"([^\"]*)\" button on 'HISTORY' page$")
    public void iClickButtonOnHistoryPage(String buttonName) {
        switch (buttonName) {
            case "NEXT":
                ahPage.clickNextOnHistory();
                break;
            case "SERVICES":
//                apage.clickServicesOnHeader();
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" text on 'HISTORY-ELESA2GO' page$")
    public void iSeeStatusOnOnHistoryPage(String text) {
        if(text.equals("AccessAudi") || text.equals("iAudi") ){
            String content = ahPage.getElsa2GOContentText();
            Assert.assertTrue("The " + text + " not found ", content.contains(text) );
        }

    }

    @Then("^I see \"([^\"]*)\" tab is active on 'HISTORY-ELESA2GO' page$")
    public void iSeeTabActiveOnHistoryPage(String text) {
       Assert.assertTrue("The " + text + " not found ", ahPage.isHistoryTabActive(text) );
    }

}
