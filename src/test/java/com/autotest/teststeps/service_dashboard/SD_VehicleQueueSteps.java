package com.autotest.teststeps.service_dashboard;

import com.automation.pages.service_dashboard.SD_SearchPage;
import com.automation.pages.service_dashboard.SD_VehicleQueuePage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SD_VehicleQueueSteps extends BaseTestSteps{

    SD_VehicleQueuePage vPage = new SD_VehicleQueuePage();

    public static int numOfAllServiceLanes;

    @When("^I search \"([^\"]*)\" on 'VEHICLE QUEUE' page$")
    public void iSearchSomethingOnVehicleQueuePage(String searchTerm){vPage.searchVin(searchTerm);}

    @And("^I search last 5 digits of the \"([^\"]*)\" on 'VEHICLE QUEUE' page$")
    public void iSearchLast5DigitsVIN(String vin) {vPage.searchVin(vin.substring(vin.length() - 5));}

    @Then("^I should see the \"([^\"]*)\" page displayed$")
    public void assertPageDisplayed(String pageName){
        vPage.isUserOnPage(pageName);
    }

    @And("^I delete service lane with (.+) on 'VEHICLE QUEUE' page$")
    public void deleteServiceLaneBeforeTest(String vIN){
        vPage.deleteServiceLaneBeforeTest(vIN);
    }

    @Then("^I don't see service lane with \"([^\"]*)\" on 'VEHICLE QUEUE' page$")
    public void assertServiceLaneExisted(String vIN){
        if(vPage.isServiceLaneKilled(vIN)){
            Assert.fail("<====== Service lane with "+vIN+" should be deleted but still showing, test fail ======>");
        }
    }

    @Then("^I should see service lane with (.+) with \"([^\"]*)\" status on 'VEHICLE QUEUE' page$")
    public void assertServiceLaneStatus(String vin, String status){
        String expectedStatus = status;
        String actualStatus = vPage.verifyServiceLaneStatus(vin, status);
        if((expectedStatus.equals("MPI Ready"))&&(!expectedStatus.equals(actualStatus))){
            Assert.fail("<====== The vehicle "+vin+" show up in TI as MPI Created but not show in Service Dashboard, test fail ======>");
        }
        assertEquals(expectedStatus, actualStatus);
    }

    @And("^I click \"([^\"]*)\" button under the service lane with (.+) on 'VEHICLE QUEUE' page$")
    public void clickButtonUnderVin(String buttonName, String vIN){
        switch(buttonName){
            case "DETAILS":
                vPage.clickDetailsUnderVin(vIN);
                break;
        }
    }

    @And("^I click service lane with (.+) on 'VEHICLE QUEUE' page$")
    public void clickWithVin(String vIN){
        vPage.clickVinOnVehicleQueue(vIN);
    }

    @When("^I select service lane for (.+) on 'VEHICLE QUEUE' page$")
    public void iSelectServiceLane(String vIN){
        vPage.isServiceLaneOpened(vIN);
    }

    @Then("^I should see 'Play' button (.+) on the second gate of 'VEHICLE QUEUE' page$")
    public void ISeePlayButton(String playbuttonexpectedresult){
        String result = vPage.isPlayButtonExist();
        switch(playbuttonexpectedresult){
            case "visible":
                if(result.equals("play button not visible")){
                    Assert.fail("<======play button should show for this user======>");
                }
                break;
            case "not visible":
                if(result.equals("play button visible")){
                    Assert.fail("<====== play button should not show for this user ======>");
                }
                break;
        }
    }

    @And("^I see 'Delete' button (.+) on 'VEHICLE QUEUE' page$")
    public void iSeeDeleteButton(String delIconExpectedResult){
        String result = vPage.isDelIconExist();
        switch(delIconExpectedResult){
            case "visible":
                if(result.equals("delete icon not visible")){
                    Assert.fail("<======delete icon should show for this user======>");
                }
                break;
            case "not visible":
                if(result.equals("delete icon visible")){
                    Assert.fail("<======delete icon should not show for this user======>");
                }
                break;
        }
    }

    @Then("^I see appointment with \"([^\"]*)\" created on 'VEHICLE QUEUE' page$")
    public void iSeeAppointmentWithVin(String vin){
        assertTrue(vPage.verifyAppointmentWithVin(vin));
    }

    @Then("^I verify appointment with \"([^\"]*)\" on 'VEHICLE QUEUE' page$")
    public void iVerifyAppointmentOnPage(String vin, DataTable dataTable){
        SD_SearchPage srcPage = new SD_SearchPage();
        srcPage.clickOnBack();
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String term = map.get("Verify term");
            String expectedResult = map.get("Expected result");
            switch (term){
                case "appointment":
                    if(expectedResult.equals("show")){
                        vPage.getCustomerNameOnSD(vin);
                        Assert.assertTrue(vPage.verifyAppointmentWithVin(vin));
                    }else if(expectedResult.equals("not show")){
                        vPage.waitForAppointmentDisappear(vin);
                        Assert.assertTrue(vPage.verifyAppointmentNotShowWithVin(vin));
                    }
                    break;
                case "day":
                    Assert.assertTrue(vPage.verifyAppointmentDate(vin));
                    break;
                case "time frame":
                    Assert.assertTrue(vPage.verifyAppointmentTime(vin));
                    break;
            }
        }
    }

    @Then("^I see service lane with \"([^\"]*)\" has expected status on 'VEHICLE QUEUE' page$")
    public void iVerifyCurrentStatus(String vin, DataTable dataTable) {
        vPage.isServiceLaneOpened(vin);
        String playButtonResult = vPage.isPlayButtonExist();
        if(playButtonResult.equals("play button visible")){
            List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : maps) {
                String sectionName = map.get("sectionName");
                String expectedStatus = map.get("expectedStatus");
                String inspectionType = map.get("inspectionType");
                String actualStatus;
                if (StringUtils.isNotBlank(sectionName)){
                    switch (sectionName){
                        case "Create MPI":
                            vPage.clickPlayButton(sectionName);
                            vPage.clickInspectionType(inspectionType);
                            actualStatus = vPage.verifyServiceLaneStatus(vin, expectedStatus);
                            assertEquals(expectedStatus,actualStatus);
                            break;
                        case "Finalize":
                        case "Work in Progress":
                        case "Review":
                        case "Complete MPI":
                        case "Request Parts":
                        case "Start MPI":
                        case "Complete Check-In":
                        default:
                            vPage.clickPlayButton(sectionName);
                            actualStatus = vPage.verifyServiceLaneStatus(vin, expectedStatus);
                            assertEquals(expectedStatus,actualStatus);
                            break;
                    }
                }
            }
        }else{
            System.out.println("<======the play button not show up, so this steps skipped======>");
        }
    }

    @When("^I drag down the appointment with \"([^\"]*)\" on 'VEHICLE QUEUE' page$")
    public void iDragAppointmentOnPage(String vin){
        vPage.dragDownAppointmentWithName(vin);
    }

    @And("^I click \"([^\"]*)\" button on 'VEHICLE QUEUE' page$")
    public void iClickButtonOnVehicleQueuePage(String buttonName){
        String result;
        switch (buttonName){
            case "Play":
                result = vPage.isPlayButtonExist();
                if(result.equals("play button not visible")){
                    System.out.println("<====== The following step will be skipped since user should not see play button ======>");
                }
                break;
            case "Delete":
                result = vPage.isDelIconExist();
                if(result.equals("delete icon not visible")){
                    System.out.println("<====== The following step will be skipped since user should not see delete icon ======>");
                }else{
                    vPage.deleteServiceLaneAfterTest();
                }
                break;
            case "OPEN IN TI":
                vPage.openInTiButton();
                break;
            case "TECHNICIAN INSPECTION":
                vPage.clickTI();
                break;
            case "OPEN IN APP":
                vPage.clickOpenInAPP();
                break;
            case "DASHBOARD":
                vPage.clickDashboardBtn();
                break;
            case "SHOW MINE":
                numOfAllServiceLanes = vPage.getNumOfDisplayedLanes();
                vPage.clickShowMineBtn();
                break;
            case "SHOW ALL":
                vPage.clickShowAllBtn();
                break;
            case "Clear Search":
                vPage.clickClearSearchBtn();
                break;
        }
    }

    @Then("^I see following components on 'VEHICLE QUEUE' page$")
    public void iVerifyComponentsOnVehicleQueuePage(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for(Map<String, String> map: maps) {
            String component = map.get("Component Name");
            switch (component) {
                case "'SHOW MINE' button":
                    assertTrue(vPage.isShowMineBtnVisible());
                    break;
                case "'SHOW ALL' button":
                    assertTrue(vPage.isShowAllBtnVisible());
                    break;
            }
        }
    }

    @Then("^I see \"([^\"]*)\" on 'VEHICLE QUEUE' page$")
    public void iSeeSomethingOnVehicleQueuePage(String componentName) {
        switch (componentName) {
            case "an empty service lane list":
                assertTrue(vPage.isNoServiceLanesSymbolVisible());
                assertTrue(vPage.isNoServiceLanesMsgVisible());
                break;
        }
    }

    @And("^I see current user name is \"([^\"]*)\" with 'User' on 'VEHICLE QUEUE' page$")
    public void iSeeCurrentUsernameMTIAdmin(String username) {
        assertTrue("Username is not shown on top of VEHICLE QUEUE page, test fail!", vPage.isUsernameVisible());
        assertEquals("Username shown on VEHICLE QUEUE page doesn't match login username", username.concat(" user").toLowerCase(), vPage.getUsername().toLowerCase());
    }

    @Then("^I see all the service lanes on 'VEHICLE QUEUE' page$")
    public void iVerifyNumberOfServiceLanes() {
        assertTrue("Service lanes are not shown, test fail!", vPage.isServiceLanesVisible());
        assertEquals("I see different number of all service lanes, test fail!", numOfAllServiceLanes, vPage.getNumOfDisplayedLanes());
    }

    @Then("^I see new assigned service lane with \"([^\"]*)\" and \"([^\"]*)\" name is \"([^\"]*)\"$")
    public void iSeeNewAssignedServiceLane(String vin, String columnName, String expectedValue) {
        assertTrue("The new assigned service lane is not shown, test failed!", vPage.isAssignedLaneWithVINVisible(vin));
        switch (columnName) {
            case "Service Advisor":
                assertEquals("The service advisor name is not as expected, test failed!", expectedValue, vPage.getServiceLaneServiceAdvisorValue(vin));
                break;
        }
    }

    @And("^I see current user name is \"([^\"]*)\"$")
    public void iSeeCurrentUsername(String expectedUsername) {
        assertEquals("Username shown on VEHICLE QUEUE page doesn't match the service advisor name, test failed!", expectedUsername, vPage.getUsername());
    }

    @When("^I refresh the page$")
    public void i_refresh_the_page() {vPage.pageRefresh();}

    @When("^I click new assigned service lane with \"([^\"]*)\" on 'VEHICLE QUEUE' page$")
    public void iClickServiceLane(String vin) {vPage.clickServiceLaneWithVIN(vin);}

    @And("^I click \"([^\"]*)\" button on 'Service Lane Details'$")
    public void iClickSomethingOnServiceLaneDetails(String elementName) {
        switch (elementName) {
            case "TEAM":
                vPage.clickTeamTab();
                break;
            case "Close":
                vPage.closeServiceLaneDetails();
                break;
            case "Wrench":
                vPage.clickWrenchBtn();
                break;
            case "Add Member":
                vPage.clickAddMemberBtn();
                break;
            case "Primary Star":
                vPage.clickPrimaryStarBtn();
                break;
            case "Delete":
                vPage.clickDeleteBtn();
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" name is \"([^\"]*)\"$")
    public void iVerifyElementValueOnServiceLaneDetails(String elementName, String expectedValue) {
        switch (elementName) {
            case "Service Advisor":
                assertEquals("Service Advisor name is not as expected, test failed!", expectedValue, vPage.getServiceAdvisorNameOnLaneDetails());
                break;
            case "Service Technician":
                assertEquals("Service Technician name is not as expected, test failed!", expectedValue, vPage.getServiceTechnicianNameOnLaneDetails());
                break;
        }
    }

    @And("^I search \"([^\"]*)\" on 'Service Lane' details$")
    public void iSearchPartsClerkOnServiceLaneDetails(String partsClerkName) {
        vPage.searchPeople(partsClerkName);
    }

    @Then("^I click 'Remove' button after \"([^\"]*)\" name to remove the technician from the team$")
    public void iRemoveTechnician(String technicianName) {vPage.removeTechnician(technicianName);}

    @And("^I click \"([^\"]*)\" button on pop-up$")
    public void iClickSomethingOnPopup(String elementName) {
        switch (elementName) {
            case "DELETE":
                vPage.clickDeleteBtnOnPopup();
                break;
        }
    }

    @Then("^I see service lane contains \"([^\"]*)\" with \"([^\"]*)\" value on 'VEHICLE QUEUE' page$")
    public void iSeeServiceLaneWithSomethingWithSomeValueOnVehicleQueuePage(String identifier, String identifierValue) {
        switch (identifier){
            case "VIN":
                assertTrue("Service lane with VIN is not displayed, test failed!", vPage.isAssignedLaneWithVINVisible(identifierValue));
                break;
            case "TagNumber":
                assertTrue("Service lane with tag number is not displayed, test failed!", vPage.isAssignedLaneWithTagVisible(identifierValue));
                break;
            case "CustomerName":
                assertTrue("Service lane with customer name is not displayed, test failed!", vPage.isAssignedLaneWithCustomerNameVisible(identifierValue));
                break;
        }
    }

    @And("^I see last 5 digits of the \"([^\"]*)\" are highlighted on 'VEHICLE QUEUE' page$")
    public void iSeeLast5DigitsOfVINHighlighted(String vin) {
        assertTrue("Last 5 digits of VIN are not highlighted, test failed!", vPage.isLast5DigitsHighlighted(vin));
    }

    @And("^I see \"([^\"]*)\" with \"([^\"]*)\" value highlighted on 'VEHICLE QUEUE' page$")
    public void iSeeSomethingHighlightedOnVehicleQueuePage(String itemHighlighted, String itemHighlightedValue) {
        switch (itemHighlighted){
            case "TagNumber":
                assertTrue("Tag number is not highlighted, test failed!", vPage.isTagNumHighlighted(itemHighlightedValue));
                break;
            case "CustomerName":
                assertTrue("Customer name is not highlighted, test failed!", vPage.isCustomerNameHighlighted(itemHighlightedValue));
                break;
        }
    }

    @Then("^I don't see any service lane on 'VEHICLE QUEUE' page$")
    public void iDoNotSeeAnyServiceLaneOnVehicleQueuePage() {
        assertTrue(vPage.isNoServiceLaneVisible());
    }

    @And("^I see \"([^\"]*)\" 'was not found' message on 'VEHICLE QUEUE' page$")
    public void iSeeMessageOnVehicleQueuePage(String invalidText) {
        assertTrue("InvalidText is not displayed, test failed!", vPage.isInvalidTextDisplayed(invalidText));
        assertTrue("'was not found' message is not displayed, test failed!", vPage.isWasNotFoundMsgDisplayed());
    }

}