package com.autotest.teststeps.customer_connect;

import com.automation.pages.PageManager;
import com.automation.pages.customer_connect.*;
import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CC_DashboardSteps extends BaseTestSteps {

    CC_DashboardPage cPage = new CC_DashboardPage();
    TestParameters tp = new TestParameters();

    @Then("^I see 'Customer Connect' page$")
    public void iLandOnCustomerConnectPage() {
        assertEquals("Customer Connect", cPage.verifyCCPageTiTle());
    }

    @And("^I click \"([^\"]*)\" tab on top of the page$")
    public void iClickTabOnCCDashboardPage(String tabName) {
        cPage.clickTabOnCCDashboardPage(tabName);
    }

    @And("^I select any customer from 'tasks list' where \"([^\"]*)\" equal \"([^\"]*)\"$")
    public void iSelectFromList(String columnName, String value) {
        cPage.selectFromList(columnName, value);
    }

    @Then("^I see Tasklist spread sheet downloaded$")
    public void iSeeSpreadSheetDownloaded() {
        cPage.verifySpreadSheetRowCount();
    }

    @And("^I see all Tasks for campaign included in exported file$")
    public void iSeeAllTasksFromFile() {
        int beforeCount = Integer.parseInt(tp.tasksCountBefore);
        //Because the exported .csv file has an extra line, which is the sheet header
        int afterCount = cPage.verifySpreadSheetRowCount() - 1;
        assertEquals(beforeCount, afterCount);
    }

    @Then("I see 'Total Task' count on 'Task' page decreased by \"([^\"]*)\"$")
    public void iVerifyTotalTaskCount(String decreaseNumber) {
        cPage.waitForLoadingCircleToDisappearCC();
        int decreaseValue = Integer.parseInt(decreaseNumber);
        cPage.getTasksCountAfter();
        int beforeCount = Integer.parseInt(tp.tasksCountBefore);
        int afterCount = Integer.parseInt(tp.tasksCountAfter);
        assertEquals(beforeCount - decreaseValue, afterCount);
    }

    @Then("^I see \"([^\"]*)\" count on 'Dashboard' page increased by \"([^\"]*)\"$")
    public void iVerifyDataCount(String taskName, String increaseNumber) {
        int increaseValue = Integer.parseInt(increaseNumber);
        int beforeCount;
        int afterCount;
        switch (taskName) {
            case "In Progress Tasks":
            	CommonMethods.sleep(10000);
//                 cPage.getInProgressTasksCountAfter();
                beforeCount = Integer.parseInt(tp.inProgressTasksBefore);
                System.out.println("Before count is "+ beforeCount);                
                afterCount = Integer.parseInt(cPage.getInProgressTasksAfter());
                System.out.println("After count is "+ afterCount);                
                assertEquals(beforeCount + increaseValue, afterCount);
                break;
            case "Closed Tasks":
                /** replaced by static  sClosedTasksLast , April 2019 david
                 //                 cPage.getClosedTasksAfter1();
                 //                 beforeCount = Integer.parseInt(tp.closedTasksBefore);
                 //                 afterCount = Integer.parseInt(tp.closedTasksAfter);
                 **/
                beforeCount = Integer.parseInt(cPage.sClosedTasksLast);
                afterCount = Integer.parseInt(cPage.getClosedTasks());
                assertEquals(beforeCount + increaseValue, afterCount);
                break;
            case "Calls Made":
                cPage.getCallsMadeAfter();
                beforeCount = Integer.parseInt(tp.callsMadeBefore);
                afterCount = Integer.parseInt(tp.callsMadeAfter);
                assertEquals(beforeCount + increaseValue, afterCount);
                break;
            case "Closed Tasks Campaign":
                cPage.getClosedTasksCampaignAfter();
                beforeCount = Integer.parseInt(tp.closedTasksCampaignBefore);
                afterCount = Integer.parseInt(tp.closedTasksCampaignAfter);
                assertEquals(beforeCount + increaseValue, afterCount);
                break;
            case "Calls Made Campaign":
                cPage.getCallsMadeCampaignAfter();
                beforeCount = Integer.parseInt(tp.callsMadeCampaignBefore);
                afterCount = Integer.parseInt(tp.callsMadeCampaignAfter);
                assertEquals(beforeCount + increaseValue, afterCount);
                break;
        }
    }

    @Then("^I don't see \"([^\"]*)\" tab on 'Customer Connect' page$")
    public void iVerifyTabNotSeeingOnCCDashboardPage(String tabName) {
        switch (tabName) {
            case "SCRIPTS":
                if (!cPage.verifyIfScriptsNotShow()) {
                    Assert.fail("<====== This user is advisor but he saw the scripts menu, fail ======>");
                }
                break;
            case "CAMPAIGNS":
                if (!cPage.verifyIfCampaignsNotShow()) {
                    Assert.fail("<====== This user is advisor but he saw the campaigns menu, fail ======>");
                }
                break;
        }
    }

    @Then("^I don't see \"([^\"]*)\" icon on \"([^\"]*)\" Campaign$")
    public void iVerifyNotSeeingIconOnCampaign(String iconName, String campaignName) {
        switch (iconName) {
            case "configure":
                if (!cPage.verifyIfIconNotShow(campaignName)) {
                    Assert.fail("<====== The configure icon show when user log in as advisor, test fail ======>");
                }
                break;
        }
    }

    @And("^I click \"([^\"]*)\" icon on \"([^\"]*)\" Campaign$")
    public void iClickIconOnCampaign(String iconName, String campaignName) {
        switch (campaignName) {
            case "Missed / No Show Appointments":
                switch (iconName) {
                    case "configure":
                        cPage.clickConfigureIconWithCampaign(campaignName);
                        break;
                }
                break;
        }
    }

    @And("^I click \"([^\"]*)\" campaign on 'DASHBOARD' page$")
    public void iClickCampaignOnPage(String campaignName) {
        cPage.clickCampaignWithName(campaignName);
    }

    @Then("^I see 'SEARCH' page$")
    public void iLandedOnSearchPage() {
        assertTrue(cPage.isHeaderActive("SEARCH"));
    }

    @When("^I open page \"([^\"]*)\" in the new browser tab$")
    public void iOpenPageTitleInNewTab(String pageTitle) {
        String evnRul = "";
        int noOfWindow = 3;
        switch (pageTitle) {
            case "ReceiveaSMS.com :: Canada":
                evnRul = "SMSCANADA";
                noOfWindow = 3;
                break;
            case "Gmail":
                evnRul = "GMAIL";
                noOfWindow = 3;
                break;
            case "mightytext.net":
                evnRul = "MIGHTYTEXT";
                noOfWindow = 3;
                break;
            case "Yahoo":
                evnRul = "YAHOO";
                noOfWindow = 3;
                break;                
        }
        String url = PageManager.getEnvURL(evnRul);
        cPage.openNewBrowserTabWithURL(url, noOfWindow);
    }

}