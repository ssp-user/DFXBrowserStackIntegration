package com.autotest.teststeps.customer_connect;

import com.automation.pages.customer_connect.CC_SearchPage;
import com.automation.pages.customer_connect.CC_TasksPage;
import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.otherUtils.CommonMethods;
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

public class CC_TasksSteps extends BaseTestSteps {

    CC_TasksPage tPage = new CC_TasksPage();
    TestParameters tp = new TestParameters();

    private static String numOfTasks;
    private static String vin;

    @Then("^I see 'Task Detail' page$")
    public void iLandOnTaskDetailPage() {
        tPage.verifyIfUserOnTaskDetail();
        tPage.getUserName();
        tPage.getVehicleName();
        tPage.getCallLogsNumber();
        tPage.closeAppointmentChangePopUp();
    }

    @And("^I click \"([^\"]*)\" dropdown on 'TASKS' page$")
    public void iClickDropDownOnTasksPage(String dropDownName) {
        tPage.clickDropDown(dropDownName);
    }

    @And("^I toggle \"([^\"]*)\" checkbox on 'TASKS' page$")
    public void iToggleCheckBoxOnTaskPage(String checkBoxName) {
        tPage.clickCheckBox(checkBoxName);
    }

    @And("^I select \"([^\"]*)\" customer on 'TASKS' page$")
    public void iSelectCustomerInOrderOnTasksPage(String order) {
        tPage.selectCustomerWithOrder(order);
    }

    @When("^I select \"([^\"]*)\" from \"([^\"]*)\" dropdown menu$")
    public void iSelectFromDropDown(String value, String dropDown) {
        tPage.selectValueFromDropDown(value, dropDown);
    }

    @Then("^I see \"([^\"]*)\" value changed to \"([^\"]*)\" on 'TASKS' page$")
    public void iVerifyLastActionStatus(String columnName, String expectedValue) {
        String actualValue = tPage.getColumnValueAfterSave(columnName, expectedValue);
        assertEquals(expectedValue, actualValue);
    }

    @Then("^I see \"([^\"]*)\" count on 'Task Detail' page increased by \"([^\"]*)\"$")
    public void iVerifyDataCountIncrease(String countName, String increaseNumber) {
        int increaseValue = Integer.parseInt(increaseNumber);
        switch (countName) {
            case "CALL LOG":
                int callLogNumberBefore = Integer.parseInt(tp.callLogsNumber);
                int callLogNumberAfter = Integer.parseInt(tPage.getCallLogsNumber());
                assertEquals(callLogNumberBefore + increaseValue, callLogNumberAfter);
                System.out.println("<====== CALL LOG number increased by 1 ======>");
                break;
        }
    }

    @And("^I get \"([^\"]*)\" from the 1st row on 'TASKS' page$")
    public void iGetSomethingFromFirstRowOnTasksPage(String elementName) {
        switch (elementName) {
            case "Customer name":
                tPage.getCustomerNameOnTasks();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" on the 1st row on 'TASKS' page$")
    public void iClickSomethingOnFirstRowOnTasksPage(String elementName) {
        switch (elementName) {
            case "Customer name":
                tPage.clickCustomerNameOnTask();
                break;
        }
    }

    @And("^I enter \"([^\"]*)\" on \"([^\"]*)\" on 'TASKS' page$")
    public void iInputTextOnSomeFieldOnTasksPage(String text, String fieldName) {
        switch (fieldName) {
            case "Notes":
                tPage.inputTextOnNotes(text);
                break;
        }
    }

    @And("^I see \"([^\"]*)\" on \"([^\"]*)\" on 'TASKS' page$")
    public void iVerifyTextOnNoteOnPage(String expectedText, String sectionName) {
        if (expectedText.equals("Test random")) {
            expectedText = tp.customerNoteTxt;
        }
        String actualText = "";
        switch (sectionName) {
            case "Notes":
                actualText = tPage.getTextOnNote().trim();
                break;
        }
        assertEquals(expectedText, actualText);
    }

    @And("^I click \"([^\"]*)\" on \"([^\"]*)\" on 'TASKS' page$")
    public void iClickSomethingOnNoteOnPage(String iconName, String sectionName) {
        switch (sectionName) {
            case "Notes":
                switch (iconName) {
                    case "close":
                        tPage.clickOnCloseOnNotes();
                        break;
                }
                break;
        }
    }

    @And("^I get \"([^\"]*)\" on 'Task Detail' page$")
    public void iGetSomethingOnTaskDetailPage(String elementName) {
        switch (elementName) {
            case "VIN":
                tPage.getVinOnTaskDetail();
                break;
        }
    }

    @Then("^I see record \"([^\"]*)\" tab on 'Task Detail' page$")
    public void iVerifyRecordUnderTabUnderPage(String tabName) {
        switch (tabName) {
            case "DEFERRED RECOMMENDATIONS":
                assertTrue(tPage.verifyIfRecordsUnderDeferredRecommendations());
                break;
        }
    }

    @Then("^I see tasks randomly assigned to following agents on 'Task Detail' page$")
    public void iSeeTaskAssignedToAgentsOnPage(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String agentName = map.get("AgentName");
            if (StringUtils.isNotBlank(agentName)) {
                assertTrue(tPage.verifyAgentNameExistOnTask(agentName));
                tPage.getCustomerNameAgentNameArray(agentName);
            }
        }
    }

    @And("^I see tasks assigned to agent \"([^\"]*)\" on 'Task Detail' page$")
    public void iSeeTaskAssignToSpecificAgentOnPage(String agentName) {
        assertTrue(tPage.verifyAgentNameExistOnTask(agentName));
    }

    @And("^I don't see tasks assigned to \"([^\"]*)\" on 'Task Detail' page$")
    public void iNotSeeTasksAssignedToAgentOnPage(String agentName) {
        assertTrue(tPage.verifyAgentNameNotExistOnTask(agentName));
    }

    @And("^I click \"([^\"]*)\" button on 'Task Detail' page$")
    public void iClickButtonOnTaskDetailPage(String buttonName) {
        switch (buttonName) {
            case "Save":
                tPage.clickOnSaveOnTaskDetail();
                break;
            case "Cancel":
                tPage.clickCancelOnTaskDetail();
                break;
            case "Schedule Appointment":
                tPage.clickOnScheduleAppointment();
                tPage.clickOKBtnOnPopup();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" icon in 'Customer Details' section on 'Task Detail' page$")
    public void iClickIconInSectionOnPage(String iconName) {
        switch (iconName) {
            case "Modify":
                tPage.clickModifyIconOnCustomerDetails();
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" on 'TASKS' Page$")
    public void iSeeSomethingOnTasksPage(String element) {
        switch (element) {
            case "Tasks List":
                assertTrue(tPage.ifTasksListPresented());
                break;
            case "Empty Tasks List":
                assertTrue("At least one task row is shown, test failed!", tPage.isTasksListEmpty());
        }
    }

    @When("^I select \"([^\"]*)\" random task checkboxes from Tasks List$")
    public void iSelectRandomTasks(int numOfRandomTasks) {
        tPage.randomlySelectTaskLists(numOfRandomTasks);
    }

    @And("^I click \"([^\"]*)\" button on 'TASKS' page$")
    public void iClickSomethingOnTasksPage(String elementName) {
        switch (elementName) {
            case "Reassign Tasks":
                tPage.clickReassignTasksBtn();
                break;
            case "Apply":
                tPage.clickApplyBtn();
                break;
            case "Reassign Agents":
                tPage.clickReassignAgentsBtn();
                break;
            case "Include All Campaigns":
                tPage.clickIncludeAllCampaignsArrow();
                break;
            case "Next":
                tPage.clickNextBtn();
                break;
            case "Save":
                tPage.clickSaveBtn();
                break;
            case "Clear All":
                tPage.clickOnClearAll();
                CommonMethods.sleep(10000);
                break;
            case "EXPORT":
                tPage.clickOnExport();
                break;
            case "Note":
                tPage.clickOnNoteButton();
                break;
        }
    }

    @And("^I search agent name \"([^\"]*)\" in \"([^\"]*)\" field$")
    public void iSearchBDCAgent(String agentName, String searchField) {
        switch (searchField) {
            case "Agent Search":
                tPage.searchBDCAgent(agentName);
                break;
            case "Global Agent Search":
                tPage.searchGlobalAgent(agentName);
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button to select BDC Agent$")
    public void iSelectBDCAgent(String btnName) {
        switch (btnName) {
            case "Left Arrow":
                tPage.clickLeftArrowBtn();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on pop up message window$")
    public void iClickBtnOnPopup(String btnName) {
        switch (btnName) {
            case "YES":
                tPage.clickYesBtnOnPopup();
                break;
            case "NO":
                break;
        }
    }

    @Then("^I see all \"([^\"]*)\" randomly selected tasks have \"([^\"]*)\" name as \"([^\"]*)\"$")
    public void iSeeRandomTasksValue(int numOfRandomTasks, String fieldName, String expectedValue) {
        switch (fieldName) {
            case "Assigned Agent":
                tPage.sleep(10000);
                for(int i=0; i<numOfRandomTasks; i++){
                    Assert.assertEquals(tPage.getAssignedAgentName(i), expectedValue);
                }
                break;
        }
    }

    @When("^I select all these \"([^\"]*)\" randomly selected tasks$")
    public void iReselectRandomTasks(int numOfRandomTasks) {
        tPage.clickTaskListCheckboxes(numOfRandomTasks);
    }

    @And("^I click \"([^\"]*)\" button beside \"([^\"]*)\"$")
    public void iClickButtonBesideAgentName(String btnName, String agentName) {
        switch (btnName) {
            case "Right Green Arrow":
                tPage.clickRightGreenArrowBtn(agentName);
                break;
            case "Left Red Arrow":
                tPage.clickLeftRedArrowBtn(agentName);
                break;
        }
    }

    @Then("^I see 'number' of tasks$")
    public void iSeeNumberOfTasks() {
        numOfTasks = tPage.getNumberOfTasks();
    }

    @Then("^I see 'number' of tasks remain the same$")
    public void iVerifyNumberOfTasks() {
    	CommonMethods.sleep(10000);
        assertEquals("Number of tasks changed, test failed!", numOfTasks, tPage.getNumberOfTasks());
    }

    @And("^I see all tasks have \"([^\"]*)\" name \"([^\"]*)\"$")
    public void iVerifyTaskColumnValue(String columnName, String expectedValue) {
        switch (columnName){
            case "Assigned Agent":
                for(int i=1; i<=Integer.parseInt(numOfTasks); i++){
                    assertEquals("Assigned agent name isn't " + expectedValue +", test failed!", expectedValue, tPage.getAssignedAgentColumnValue(i));
                }
                break;
        }
    }

    @Then("^I see 'Task Details' page$")
    public void iLandedOnTaskDetailsPage() {
    	CommonMethods.sleep(10000);
        assertTrue("I am not on TaskDetails page, test fail", tPage.getURL().contains("taskDetails"));
    }

    @And("^I see \"([^\"]*)\" on 'Task Details' page$")
    public void iSeeSomethingOnTaskDetailsPage(String elementName) {
        switch(elementName){
            case "VIN":
                CC_SearchSteps sSteps = new CC_SearchSteps();
                assertEquals("Wrong VIN displayed in Service History window, test failed", sSteps.vin, tPage.getVIN());
                break;
        }
    }

    @When("^I click \"([^\"]*)\" on 'Task Details' page$")
    public void iClickSomethingOnTaskDetailsPage(String elementName) {
        switch(elementName){
            case "SERVICE HISTORY":
                tPage.clickServiceHistoryTab();
                break;
            case "first i":
                tPage.clickInfoIcon(0);
                break;
        }
    }

    @And("^I modify following data on \"([^\"]*)\" pop up$")
    public void iModifyDataOnPopUp(String popupName, DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                switch (popupName) {
                    case "Customer Details":
                        switch (fieldName) {
                            case "First Name":
                                tPage.updateFirstNameField(value);
                                break;
                            case "Last Name":
                                tPage.updateLastNameField(value);
                                break;
                            case "Cell Phone":
                                tPage.updateCellPhoneField(value);
                                break;
                            case "Address":
                                tPage.updateAddressField(value);
                                break;
                            case "City":
                                tPage.updateCityField(value);
                                break;
                            case "Country":
                                tPage.updateCountryField(value);
                                break;
                            case "Zip":
                                tPage.updateZipField(value);
                                break;
                            case "State/Province":
                                tPage.updateStateProvinceField(value);
                                break;
                        }
                        break;
                }
            }
        }
    }

    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" pop up$")
    public void iClickButtonOnPopUp(String buttonName, String popupName) {
        switch (buttonName) {
            case "Save":
                switch (popupName) {
                    case "Customer Details":
                        tPage.clickSaveOnCustomerDetailsPopup();
                        break;
                }
                break;
            case "OK":
                switch (popupName) {
                    case "Message":
                        tPage.clickOnOKOnMessagePopup();
                        break;
                }
                break;
        }
    }

    @And("^I see following data in \"([^\"]*)\" section on 'Task Detail' page$")
    public void iSeeDataOnSectionOnPage(String sectionName, DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String iconName = map.get("IconName");
            String expectedResult = map.get("Value");
            String actualResult;
            if (StringUtils.isNotBlank(expectedResult)) {
                switch (sectionName) {
                    case "Customer Details":
                        switch (iconName) {
                            case "HomeIcon":
                                actualResult = tPage.getHomeIconCustomerDetails();
                                assertEquals(expectedResult, actualResult);
                                break;
                            case "CellPhoneIcon":
                                actualResult = tPage.getCellPhoneIconCustomerDetails();
                                assertEquals(expectedResult + " ", actualResult);
                                break;
                        }
                    break;
                }
            }
        }
    }

    @And("^I click \"([^\"]*)\" tab on 'Task Detail' page$")
    public void iClickTabOnPage(String tabName) {
        switch (tabName) {
            case "DEFERRED RECOMMENDATIONS":
                tPage.clickOnDeferredRecommendationsTab();
                break;
        }
    }    

    @And("^I validate option as \"([^\"]*)\" in 'TASKS' page$")
    public void iValidateElement(String option) {
    	tPage.validateOtherEnglishOption(option);
    }     
    
    @And("^I select Email and Text checkbox if not selected already$")
    public void iSelectEmailAndTextCheckboxIfNotSelectedAlready() {
        CommonMethods.sleep(500);
        tPage.iSelectEmailAndTextCheckboxIfNotSelectedAlready();
  
    }  
    
    @And("^I modify \"([^\"]*)\" in \"([^\"]*)\" field on 'Customer Details' screen$")
    public void iModifyOnCustomerDetails(String value, String fieldName) {
        switch (fieldName) {
            case "Cell Phone":
            	tPage.modifyCellPhoneField(value);
                break;
            case "Email":
            	tPage.modifyEmailField(value);
                break;                
        }
    }
    
    @And("^I click Save button on 'Task Detail' page$")
    public void iClickSaveButton() {
        tPage.clickCustDetailsSaveBtn();
    }   
    
    @And("^I click OK button on 'Task Detail' page$")
    public void iClickOKButton() {
        tPage.clickCustDetailsOKPopUpBtn();
    }    
    
    @And("^I click Email on 'Task Detail' page$")
    public void iClickEmail() {
        tPage.clickEMail();
    }       
       
    @And("^I click chat submit on 'Task Detail' page$")
    public void iClickChatSubmit() {
        tPage.clickChatSubmitBtn();
    }      
    
    @And("^I enter email address on 'Compose Message' page$")
    public void iEnterEmail() {
        tPage.enterEmail();
    }     
    
    @And("^I enter \"([^\"]*)\" as Email Subject on 'Compose Message' page$")
    public void iEnterEmailSubject(String subject) {
    	tPage.inputTextOnEmailSubject(subject);
    }
    
    @And("^I enter email contents on 'Compose Message' page$")
    public void iEnterEmailContents() {
        tPage.enterEmailContents();
    }
    
    @And("^I click on Send button on 'Compose Message' page$")
    public void iClickSendButton() {
        tPage.clickMailSendBtn();
    }    

    @And("^I close the message pop up on 'Compose Message' page$")
    public void iCloseMessagePopUp() {
        tPage.closeMessagePopUp();
    }   
    
    @And("^I click on Send SMS button on 'Compose Message' page$")
    public void iClickSendSMSButton() {
        tPage.clickMailSendSMSBtn();
    }     
    
    @And("^I enter SMS contents on 'Compose Message' page$")
    public void iEnterSMSContents() {
        tPage.enterSMSContents();
    }    
    
    @And("^I click SMS on 'Task Detail' page$")
    public void iClickSMS() {
        tPage.clickSMS();
    } 
    
    @And("^I deselect Email and Text checkbox if not deselected already$")
    public void iDeSelectEmailAndTextCheckboxIfNotDeSelectedAlready() {
        CommonMethods.sleep(500);
        tPage.iDeSelectEmailAndTextCheckboxIfNotDeSelectedAlready();  
    }      
    
    @And("^I validate Unauthorized Email message$")
    public void iValidateUnauthorizedEmailMessage() {
    	tPage.validateUnauthorizedEmailMessage();
    }
    
    @And("^I validate Unauthorized SMS message$")
    public void iValidateUnauthorizedSMSMessage() {
    	tPage.validateUnauthorizedSMSMessage();
    }      

    @And("^I click OK button on authorization pop-up on 'Task Detail' page$")
    public void iClickAuthorizeOKButton() {
    	CommonMethods.sleep(5000);
        tPage.clickCustDetailsAuthorizeOKPopUpBtn();
    }  
    
    @And("^I search customer by name on 'TASKS' Page$")
    public void iSearchCustomerByName() {
        tPage.searchCustomerByName();
    }    
    
}