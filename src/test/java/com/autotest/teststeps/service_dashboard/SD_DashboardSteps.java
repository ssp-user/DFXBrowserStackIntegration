package com.autotest.teststeps.service_dashboard;

import com.automation.pages.service_dashboard.SD_DashboardPage;
import com.automation.pages.service_dashboard.SD_VehicleQueuePage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import org.junit.Assert;
import java.util.List;
import java.util.Map;

public class SD_DashboardSteps extends BaseTestSteps{

    SD_DashboardPage dPage = new SD_DashboardPage();
    SD_VehicleQueuePage vPage = new SD_VehicleQueuePage();

    public static String numOfMatchedLanes;

    @Then("^I see following components on 'DASHBOARD' page$")
    public void iVerifyComponentsOnDashboardPage(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for(Map<String, String> map: maps) {
            String component = map.get("Component Name");
            switch (component) {
                case "'SHOW MINE' button":
                    Assert.assertTrue(dPage.isShowMineBtnVisible());
                    break;
                case "'SHOW ALL' button":
                    Assert.assertTrue(dPage.isShowAllBtnVisible());
                    break;
                case "'All Departments' dropdown":
                    Assert.assertTrue(dPage.isAllDepartmentsDropdownVisible());
                    break;
                case "'Missed Appointments' tile":
                    Assert.assertTrue(dPage.isMissedAppointmentsTileVisible());
                    break;
                case "'MPI Declined By Advisor' tile":
                    Assert.assertTrue(dPage.isMPIDeclinedByAdvisorTileVisible());
                    break;
                case "'Carry Over' tile":
                    Assert.assertTrue(dPage.isCarryOverTileVisible());
                    break;
                case "'Waiters' tile":
                    Assert.assertTrue(dPage.isWaitersTileVisible());
                    break;
                case "'Write up not Complete' tile":
                    Assert.assertTrue(dPage.isWriteUpNotCompleteTileVisible());
                    break;
                case "'MPI not Started' tile":
                    Assert.assertTrue(dPage.isMPINotStartedTileVisible());
                    break;
                case "'Awaiting Parts Estimate' tile":
                    Assert.assertTrue(dPage.isAwaitingPartsEstimateTileVisible());
                    break;
                case "'Quarter Time' tile":
                    Assert.assertTrue(dPage.isQuarterTimeTileVisible());
                    break;
                case "'Promise Time' tile":
                    Assert.assertTrue(dPage.isPromiseTimeTileVisible());
                    break;
                case "'Ready for Delivery' tile":
                    Assert.assertTrue(dPage.isReadyForDeliveryTileVisible());
                    break;
            }
        }
    }

    @When("^I click \"([^\"]*)\" tile on 'DASHBOARD' page$")
    public void iClickSomethingOnDashboardPage(String elementName) {
        switch (elementName) {
            case "MPI not Started":
                dPage.clickMPINotStartedTile();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" tile$")
    public void iClickSomethingOnSomeTile(String elementName, String tileName) {
        switch (tileName) {
            case "MPI not Started":
                switch (elementName) {
                    case "SHOW LANE":
                        dPage.clickShowLaneBtn();
                        break;
                }
                break;
        }
    }

    @And("^I see \"([^\"]*)\" on \"([^\"]*)\" tile on 'DASHBOARD' page$")
    public void iSeeSomethingOnSomeTileOnDashboardPage(String elementName, String tileName) {
        switch (tileName) {
            case "MPI not Started":
                switch (elementName) {
                    case "Number of Matched Lanes":
                        numOfMatchedLanes = dPage.getNumberOfMPINotStarted();
                        break;
                }
                break;
        }
    }

    @Then("^I see current lane header is \"([^\"]*)\" with 'Number of Matched Lanes' on 'VEHICLE QUEUE' page$")
    public void iSeeCurrentLaneHeader(String laneName) {
        Assert.assertEquals("Actual current lane header doesn't equal to expected, test fail!", laneName.concat(" ").concat(numOfMatchedLanes), vPage.getCurrentLaneHeader());
        Assert.assertTrue("VEHICLE QUEUE is not current active tab, test fail!", dPage.getActiveTabText().equalsIgnoreCase("VEHICLE QUEUE"));
    }

    @And("^I see 'Number of Matched Lanes' of service lanes displayed on 'VEHICLE QUEUE' page$")
    public void iVerifyNumberOfDisplayedLanesOnVehicleQueuePage() {
        Assert.assertEquals("Number of service lanes displayed on VEHICLE QUEUE page doesn't match the number shown on the tile on DASHBOARD page, test fail!", Integer.parseInt(numOfMatchedLanes), vPage.getNumOfDisplayedLanes());
    }

}




