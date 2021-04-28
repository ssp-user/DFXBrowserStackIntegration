//package com.autotest.teststeps.customer_connect;
//
//import com.automation.pages.DMPI.DynamicMPIPage;
//import com.automation.pages.appointment_manager.AM_CustomerSearchPage;
//import com.automation.pages.customer_connect.*;
//import com.automation.pages.service_dashboard.SearchPage;
//import com.automation.pages.third_party.GmailPage;
//import com.automation.pages.third_party.MightyTextPage;
//import com.automation.utils.dataProvider.TestParameters;
//import com.automation.utils.otherUtils.CommonMethods;
//import com.autotest.teststeps.BaseTestSteps;
//import cucumber.api.DataTable;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Assert;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class CustomerConnectSteps extends BaseTestSteps {
//
//    CC_DashboardPage cPage = new CC_DashboardPage();
//    CC_TasksPage tPage = new CC_TasksPage();
//    CC_SearchPage sPage = new CC_SearchPage();
//    AM_CustomerSearchPage amlPage = new AM_CustomerSearchPage();
//    CC_MessengerPage cmPage = new CC_MessengerPage();
//    CC_ReportsPage crPage = new CC_ReportsPage();
//    CC_CampaignsPage ccPage = new CC_CampaignsPage();
//    CC_ScriptsPage csPage = new CC_ScriptsPage();
//    DynamicMPIPage dmpiPage = new DynamicMPIPage();
//    GmailPage gmailPage = new GmailPage();
//    MightyTextPage mtPage = new MightyTextPage();
//    SearchPage srcPage = new SearchPage();
//    TestParameters tp = new TestParameters();
//
//    private static String numOfTasks;
//    private static String vin;
//
//    @Then("^I should be on \"([^\"]*)\" tab$")
//    public void iLandOnTab(String tabName) {
//        switch (tabName) {
//            case "INFORMATION":
//                assertTrue(ccPage.isOnTab("INFORMATION"));
//                break;
//            case "EMAIL":
//                assertTrue(ccPage.isOnTab("Email"));
//                break;
//        }
//    }
//
//    @When("^I select 'Communication Type' to \"([^\"]*)\"$")
//    public void iSelectCommunicationType(String communicationType) {
//        ccPage.selectCommunicationType(communicationType);
//    }
//
//    @Then("^I should be landing on \"([^\"]*)\" page$")
//    public void iLandONPage(String pageName) {
//        String expectedTitle = pageName;
//        String actualTitle = "";
//        switch (pageName) {
//            case "Guest Connect":
//                actualTitle = cPage.verifyCCPageTiTle();
//                assertTrue(actualTitle.contains("Connect"));
//                break;
//            case "Customer Connect":
//                actualTitle = cPage.verifyCCPageTiTle();
//                assertEquals(expectedTitle, actualTitle);
//                break;
//            case "Task Detail":
//                tPage.verifyIfUserOnTaskDetail();
//                tPage.getUserName();
//                tPage.getVehicleName();
//                tPage.getCallLogsNumber();
//                tPage.closeAppointmentChangePopUp();
//                break;
//            case "Appointment Manager":
//
//                actualTitle = amlPage.verifyAMPageTiTle(pageName);
//                assertEquals(expectedTitle, actualTitle);
//                break;
//            case "Dynamic MPI":
//                actualTitle = dmpiPage.verifyDMPIPageTiTle(pageName);
//                assertEquals(expectedTitle, actualTitle);
//                break;
//        }
//    }
//
//    @And("^I click on \"([^\"]*)\" tab on \"([^\"]*)\" page$")
//    public void iClickTabOnPage(String tabName, String pageName) {
//        if (pageName.equals("SEARCH") && tabName.equals("TASKS")) {
//            sPage.clickTaskTabOnSearchPage();
//        } else {
//            cPage.clickTabOnCCDashboardPage(tabName);
//        }
//    }
//
//    public void iClickTaskTabOnSearchPage(String tabName) {
//        sPage.clickTaskTabOnSearchPage();
//    }
//
//    @And("^I click on \"([^\"]*)\" tab on 'Customer Connect' page$")
//    public void iClickTabOnCustomerConnectPage(String tabName) {
//        cPage.clickTabOnCCDashboardPage(tabName);
//    }
//
//    //Added by Stewart
//    @And("^I click on \"([^\"]*)\" tab on 'Customer Connect Home' page$")
//    public void iClickTabOnCCHomePage(String tabName) {
//        cPage.clickTabOnCCDashboardPage(tabName);
//    }
//
//    @And("^I click on \"([^\"]*)\" tab on 'DASHBOARD' page$")
//    public void iClickTabOnDashboardPage(String tabName) {
//        cPage.clickTabOnCCDashboardPage(tabName);
//    }
//
//    @And("^I click on \"([^\"]*)\" tab on 'SEARCH' page$")
//    public void iClickTabSearchPage(String tabName) {
//        sPage.clickTaskTabOnSearchPage();
//    }
//
//    @And("^I click \"([^\"]*)\" dropdown on 'TASKS' page$")
//    public void iClickDropDownOnTasksPage(String dropDownName) {
//        tPage.clickDropDown(dropDownName);
//    }
//
//    @And("^I toggle \"([^\"]*)\" checkbox on 'TASKS' page$")
//    public void iToggleCheckBoxOnTaskPage(String checkBoxName) {
//        tPage.clickCheckBox(checkBoxName);
//    }
//
//    @And("^I select \"([^\"]*)\" customer on 'TASKS' page$")
//    public void iSelectCustomerInOrderOnTasksPage(String order) {
//        tPage.selectCustomerWithOrder(order);
//    }
//
//    @And("^I select any customer from 'tasks list' where \"([^\"]*)\" equal \"([^\"]*)\"$")
//    public void iSelectFromList(String columnName, String value) {
//        cPage.selectFromList(columnName, value);
//    }
//
//    @When("^I select \"([^\"]*)\" from \"([^\"]*)\" dropdown menu$")
//    public void iSelectFromDropDown(String value, String dropDown) {
//        tPage.selectValueFromDropDown(value, dropDown);
//    }
//
//    @And("^I click \"([^\"]*)\" from \"([^\"]*)\" on \"([^\"]*)\" page$")
//    public void iSelectFromListOnPage(String campaignName, String listName, String pageName) {
//        switch (pageName) {
//            case "CAMPAIGNS":
//                ccPage.clickCampaignOnCampaignPage(campaignName);
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" from 'Campaign List' on 'CAMPAIGNS' page$")
//    public void iSelectFromListOnCampaignsPage(String campaignName) {
//        ccPage.clickCampaignOnCampaignPage(campaignName);
//    }
//
//    @And("^I select \"([^\"]*)\" from \"([^\"]*)\" dropdown menu on \"([^\"]*)\" page$")
//    public void iSelectFromDropDownOnPage(String dropDownValue, String dropDown, String pageName) {
//        switch (pageName) {
//            case "SCRIPTS":
//                switch (dropDown) {
//                    case "Campaign Type":
//                        csPage.selectValueFromCampaignDropDownOnScripts(dropDownValue);
//                        break;
//                    case "Script Type":
//                        csPage.selectValueFromScriptTypeDropDownOnScripts(dropDownValue);
//                        break;
//                }
//                break;
//        }
//    }
//
//    @And("^I select \"([^\"]*)\" from \"([^\"]*)\" dropdown menu on 'CAMPAIGNS' page$")
//    public void iSelectFromDropDownOnCampaignPage(String dropDownValue, String dropDown) {
//        switch (dropDown) {
//            case "Campaign Type":
//                ccPage.selectValueFromCampaignDropDownOnCampaign(dropDownValue);
//                break;
//            case "Email Templates":
//                ccPage.selectValueFromEmailTemplatesDropDownOnCampaign(dropDownValue);
//                break;
//        }
//    }
//
//    @Then("^I see \"([^\"]*)\" value should change to \"([^\"]*)\" on 'TASKS' page$")
//    public void iVerifyLastActionStatus(String columnName, String expectedValue) {
////        String actualValue = cPage.getColumnValueFromUserName(columnName, expectedValue);
//        String actualValue = cPage.getColumnValueAfterSave(columnName, expectedValue);
//        assertEquals(expectedValue, actualValue);
//    }
//
//    @Then("^task where \"([^\"]*)\" equal \"([^\"]*)\" should be disappeared from 'Task List' on 'Task' page$")
//    public void iVerifyTaskExist(String columnName, String columnValue) {
//        cPage.recordAfterNumber(columnValue);
//        assertTrue(cPage.ifRecordExist());
//    }
//
//    @Then("^I should see Tasklist spread sheet downloaded$")
//    public void iShouldSeeSpreadSheetDownloaded() {
//        cPage.verifySpreadSheetRowCount();
//    }
//
//    @And("^I should see all Tasks for campaign included in exported file$")
//    public void iShouldSeeAllTasksFromFile() {
//        int beforeCount = Integer.parseInt(tp.tasksCountBefore);
//        //Because the exported .csv file has an extra line, which is the sheet header
//        int afterCount = cPage.verifySpreadSheetRowCount() - 1;
//        assertEquals(beforeCount, afterCount);
//    }
//
//    @Then("'Total Task' count on 'Task' page should decreased by \"([^\"]*)\"$")
//    public void iVerifyTotalTaskCount(String decreaseMumber) {
//        int decreaseValue = Integer.parseInt(decreaseMumber);
//        cPage.getTasksCountAfter();
//        int beforeCount = Integer.parseInt(tp.tasksCountBefore);
//        int afterCount = Integer.parseInt(tp.tasksCountAfter);
//        assertEquals(beforeCount - decreaseValue, afterCount);
//    }
//
//    @Then("^I see \"([^\"]*)\" count on 'Dashboard' page should increased by \"([^\"]*)\"$")
//    public void iVerifyDataCount(String taskName, String increaseNumber) {
//        int increaseValue = Integer.parseInt(increaseNumber);
//        int beforeCount;
//        int afterCount;
//        switch (taskName) {
//            case "In Progress Tasks":
////                 cPage.getInProgressTasksCountAfter();
//                beforeCount = Integer.parseInt(tp.inProgressTasksBefore);
//                afterCount = Integer.parseInt(cPage.getInProgressTasksAfter());
//                assertEquals(beforeCount + increaseValue, afterCount);
//                break;
//            case "Closed Tasks":
//                /** replaced by static  sClosedTasksLast , April 2019 david
//                 //                 cPage.getClosedTasksAfter1();
//                 //                 beforeCount = Integer.parseInt(tp.closedTasksBefore);
//                 //                 afterCount = Integer.parseInt(tp.closedTasksAfter);
//                 **/
//                beforeCount = Integer.parseInt(cPage.sClosedTasksLast);
//                afterCount = Integer.parseInt(cPage.getClosedTasks());
//                assertEquals(beforeCount + increaseValue, afterCount);
//                break;
//            case "Calls Made":
//                cPage.getCallsMadeAfter();
//                beforeCount = Integer.parseInt(tp.callsMadeBefore);
//                afterCount = Integer.parseInt(tp.callsMadeAfter);
//                assertEquals(beforeCount + increaseValue, afterCount);
//                break;
//            case "Closed Tasks Campaign":
//                cPage.getClosedTasksCampaignAfter();
//                beforeCount = Integer.parseInt(tp.closedTasksCampaignBefore);
//                afterCount = Integer.parseInt(tp.closedTasksCampaignAfter);
//                assertEquals(beforeCount + increaseValue, afterCount);
//                break;
//            case "Calls Made Campaign":
//                cPage.getCallsMadeCampaignAfter();
//                beforeCount = Integer.parseInt(tp.callsMadeCampaignBefore);
//                afterCount = Integer.parseInt(tp.callsMadeCampaignAfter);
//                assertEquals(beforeCount + increaseValue, afterCount);
//                break;
//        }
//    }
//
//    @Then("^I see \"([^\"]*)\" count on \"([^\"]*)\" page should increased by \"([^\"]*)\"$")
//    public void iVerifyDataCountIncrease(String countName, String pageName, String increaseNumber) {
//        int increaseValue = Integer.parseInt(increaseNumber);
//        switch (pageName) {
//            case "Task Detail":
//                switch (countName) {
//                    case "CALL LOG":
//                        int callLogNumberBefore = Integer.parseInt(tp.callLogsNumber);
//                        int callLogNumberAfter = Integer.parseInt(tPage.getCallLogsNumber());
//                        assertEquals(callLogNumberBefore + increaseValue, callLogNumberAfter);
//                        System.out.println("<====== CALL LOG number increased by 1 ======>");
//                        break;
//                }
//                break;
//        }
//    }
//
//    @And("^I search \"([^\"]*)\" in \"([^\"]*)\" field on \"([^\"]*)\" page$")
//    public void iSearch(String searchWord, String fieldName, String pageName) {
//        switch (pageName) {
//            case "SEARCH":
//                sPage.searchKeyWord(searchWord);
//                break;
//            case "MESSENGER":
//                switch (fieldName) {
//                    case "Search Customer":
//                        cmPage.searchCustomerOnMessenger(searchWord);
//                        break;
//                }
//        }
//    }
//
//    @And("^I search \"([^\"]*)\" in \"([^\"]*)\" field on 'MESSENGER' page$")
//    public void iSearchSomethingOnMessengerPage(String searchWord, String fieldName) {
//        switch(fieldName) {
//            case "Search Customer":
//                cmPage.searchCustomerOnMessenger(searchWord);
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" button on 'SEARCH' page$")
//    public void iClickBtnOnSearchPage(String buttonName) {
//        switch (buttonName) {
//            case "Name":
//                sPage.clickOnName();
//                break;
//            case "Note":
//                sPage.clickOnNoteButton();
//                break;
//            case "Email":
//                sPage.clickEmailBtn();
//                break;
//            case "Phone":
//                sPage.clickPhoneBtn();
//                break;
//            case ">":
//                sPage.clickRightArrowBtn();
//                break;
//            default:
//                sPage.clickOnSearchButton(buttonName);
//                break;
//        }
//    }
//
//    @Then("^I should see results matched \"([^\"]*)\" \"([^\"]*)\" should be displayed$")
//    public void iSeeResult(String searchWord, String fieldName) {
//        String searchResult = sPage.searchResult(searchWord, fieldName);
//        assertTrue(searchResult.contains(searchWord));
//    }
//
//    @Then("^I see \"([^\"]*)\" contains \"([^\"]*)\" displayed on 'MESSENGER' page$")
//    public void iSeeSearchResultOnMessengerPage(String searchField, String keyWord) {
//        String expectedResult = keyWord.toLowerCase();
//        String actualResult = "";
//        switch (searchField) {
//            case "Customer name":
//                actualResult = cmPage.getCustomerNameSearchResult().toLowerCase();
//                break;
//        }
//        assertTrue(actualResult.contains(expectedResult));
//    }
//
//    @And("^I get \"([^\"]*)\" from the 1st row on \"([^\"]*)\" page$")
//    public void iGetSomethingFromeRowOnPage(String name, String pageName) {
//        switch (pageName) {
//            case "MESSENGER":
//                switch (name) {
//                    case "Customer name":
//                        cmPage.getCustomerNameSearchResult();
//                        break;
//                }
//                break;
//            case "SEARCH":
//                switch (name) {
//                    case "Customer name":
//                        sPage.getCustomerNameOnSearch();
//                        break;
//                }
//                break;
//            case "TASKS":
//                switch (name) {
//                    case "Customer name":
//                        tPage.getCustomerNameOnTasks();
//                        break;
//                }
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" from the 1st row on \"([^\"]*)\" page$")
//    public void iClickSomethingFromeRowOnPage(String name, String pageName) {
//        switch (pageName) {
//            case "TASKS":
//                switch (name) {
//                    case "Customer name":
//                        tPage.clickCustomerNameOnTask();
//                        break;
//
//                }
//                break;
//        }
//    }
//
//    @And("^I enter \"([^\"]*)\" on \"([^\"]*)\" on 'MESSENGER' page$")
//    public void iInputTextOnSomeFieldOnMessengerPage(String text, String fieldName) {
//        switch (fieldName) {
//            case "Notes":
//                cmPage.inputTextOnNotes(text);
//                break;
//        }
//    }
//
//    @And("^I enter \"([^\"]*)\" on \"([^\"]*)\" on 'SEARCH' page$")
//    public void iInputTextOnSomeFieldOnSearchPage(String text, String fieldName) {
//        switch (fieldName) {
//            case "Notes":
//                sPage.inputTextOnNotes(text);
//                break;
//        }
//    }
//
//    @And("^I enter \"([^\"]*)\" on \"([^\"]*)\" on 'TASKS' page$")
//    public void iInputTextOnSomeFieldOnTasksPage(String text, String fieldName) {
//        switch (fieldName) {
//            case "Notes":
//                tPage.inputTextOnNotes(text);
//                break;
//        }
//    }
//
//    @And("^I enter \"([^\"]*)\" on \"([^\"]*)\" on 'CAMPAIGNS' page$")
//    public void iInputTextOnSomeFieldOnCampaignPage(String text, String fieldName) {
//        switch (fieldName) {
//            case "Campaign Name":
//                ccPage.inputTextOnCampaignName(text);
//                break;
//            case "Campaign Description":
//                ccPage.inputTextOnCampaignDescription(text);
//                break;
//            case "Email Subject":
//                ccPage.inputTextOnEmailSubjectOnCreateScript(text);
//                break;
//            case "Email Body Content":
//                ccPage.inputTextOnEmailBodyContent(text);
//                break;
//        }
//    }
//
//    @And("^I see \"([^\"]*)\" on \"([^\"]*)\" on \"([^\"]*)\" page$")
//    public void iVerifyTextOnNoteOnPage(String text, String sectionName, String pageName) {
//        if (text.equals("Test random")) {
//            text = tp.customerNoteTxt;
//        }
//        String expectedText = text;
//        String actualText = "";
//        switch (pageName) {
//            case "MESSENGER":
//                switch (sectionName) {
//                    case "Notes":
//                        actualText = cmPage.getTextOnNote().trim();
//                        break;
//                }
//                break;
//            case "SEARCH":
//                switch (sectionName) {
//                    case "Notes":
//                        actualText = sPage.getTextOnNote().trim();
//                        break;
//                }
//                break;
//            case "TASKS":
//                switch (sectionName) {
//                    case "Notes":
//                        actualText = tPage.getTextOnNote().trim();
//                        break;
//                }
//                break;
//        }
//        assertEquals(expectedText, actualText);
//    }
//
//    @And("^I click \"([^\"]*)\" on \"([^\"]*)\" on \"([^\"]*)\" page$")
//    public void iClickSomethingOnNoteOnPage(String iconName, String sectionName, String pageName) {
//        switch (pageName) {
//            case "MESSENGER":
//                switch (sectionName) {
//                    case "Notes":
//                        switch (iconName) {
//                            case "close":
//                                cmPage.clickOnCloseOnNotes();
//                                break;
//                        }
//                        break;
//                }
//                break;
//            case "SEARCH":
//                switch (sectionName) {
//                    case "Notes":
//                        switch (iconName) {
//                            case "close":
//                                sPage.clickOnCloseOnNotes();
//                                break;
//                        }
//                        break;
//                }
//                break;
//            case "TASKS":
//                switch (sectionName) {
//                    case "Notes":
//                        switch (iconName) {
//                            case "close":
//                                tPage.clickOnCloseOnNotes();
//                                break;
//                        }
//                        break;
//                }
//                break;
//        }
//    }
//
//    @And("^I click 'Customer name' from the 1st row on \"([^\"]*)\" page$")
//    public void iClickNameOnPage(String pageName) {
//        switch (pageName) {
//            case "MESSENGER":
//                cmPage.clickCustomerWithNameOnMsg();
//                break;
//            case "SEARCH":
//                sPage.clickCustomerWithNameOnSearch();
//                break;
//        }
//    }
//
//    @When("^I click \"([^\"]*)\" search result contains \"([^\"]*)\" on 'MESSENGER' page$")
//    public void iClickOnSearchResultContainsSearchNameOnMessengerPage(String searchField, String keyWord) {
//        switch (searchField) {
//            case "Customer name":
//                cmPage.clickOnSearchResult(keyWord);
//                break;
//        }
//    }
//
//    @And("^I get \"([^\"]*)\" from page title \"([^\"]*)\"$")
//    public void iGetSomethingOnSomePage(String stuffGetting, String pageTItle) {
//        switch (pageTItle) {
//            case "ReceiveaSMS.com :: Canada":
//                switch (stuffGetting) {
//                    case "Phone#":
//                        cmPage.getNumberOnReceiveSMS();
//                        break;
//                }
//                break;
//        }
//    }
//
//    @And("^I should get \"([^\"]*)\" on \"([^\"]*)\" page$")
//    public void iShouldGetSomeNameOnSomePage(String name, String pageName) {
//        switch (pageName) {
//            case "Task Detail":
//                switch (name) {
//                    case "VIN":
//                        tPage.getVinOnTaskDetail();
//                        break;
//                }
//                break;
//        }
//    }
//
//    @And("^I should see same \"([^\"]*)\" on \"([^\"]*)\" page$")
//    public void iShouldSeeSameSameOnSomePage(String name, String pageName) {
//        String expectedResult = tPage.vinOnTaskDetail;
//        String actualResult = "";
//        switch (pageName) {
//            case "Appointment Manager":
//                switch (name) {
//                    case "VIN":
//                        actualResult = amlPage.getVINOnAMLookup();
//                        break;
//                }
//                break;
//        }
//        Assert.assertTrue(actualResult.contains(expectedResult));
//    }
//
//    @And("^I modify \"([^\"]*)\" from \"([^\"]*)\" field on 'Customer Details' popup$")
//    public void iModifySomethingOnSomeField(String phoneNo, String fieldName) {
//        switch (fieldName) {
//            case "Cell Phone":
//                /*switch (name){
//                    case "Phone#":
//                        cmPage.modifyCellPhoneField(); //comment out because the Phone# website no longer available
//                        break;
//                }*/
//                cmPage.modifyCellPhoneField(phoneNo);
//                break;
//        }
//    }
//
//    @And("^I opt in \"([^\"]*)\" check box on 'Customer Details' page$")
//    public void iOptInCheckboxOnCustomerDetailsPage(String checkBox) {
//        cmPage.optInTextCheckBox(checkBox);
//    }
//
//    @And("^I send SMS \"([^\"]*)\" to customer on 'MESSENGER' page$")
//    public void iSendSMSOnMessengerPage(String textSMS) {
//        if (textSMS.contains("random")) {
//            CommonMethods cm = new CommonMethods();
//            tp.sms = "TEST" + " " + cm.getRandomText(5);
//        }
//        cmPage.sendMessageToCustomer(tp.sms);
//    }
//
//    @When("^I send SMS \"([^\"]*)\" to dealer on \"([^\"]*)\" page$")
//    public void iSendSMStoDealerOnPage(String textSMS, String pageName) {
//        if (textSMS.contains("random")) {
//            CommonMethods cm = new CommonMethods();
//            textSMS = textSMS.replace("random", cm.getRandomText(5));
//        }
//        switch (pageName) {
//            case "mightytext.net":
//                mtPage.sendMessageToDealer(textSMS);
//                break;
//        }
//    }
//
//    @Then("^I see SMS contains \"([^\"]*)\" associated with \"([^\"]*)\" on \"([^\"]*)\" page$")
//    public void iSeeSMSonSomePage(String textSMS, String phoneNumber, String pageName) {
//        String expecteSMS = textSMS;
//        if (textSMS.contains("random")) {
//            textSMS = textSMS.replace("random", tp.randomText);
//            expecteSMS = textSMS;
//        }
//        String actualSMS = "";
//        switch (pageName) {
//            case "ReceiveaSMS.com :: Canada":
//                actualSMS = cmPage.getSMSFromReceiveSMSPage(textSMS);
//                break;
//            case "mightytext.net":
//                actualSMS = mtPage.getSMSFromMightyTextPage();
//                break;
//            case "MESSENGER":
//                actualSMS = cmPage.getSMSFromMessengerPage(textSMS);
//                break;
//        }
//        if (!actualSMS.contains(expecteSMS)) {
//            Assert.fail("<====== The expected SMS is " + expecteSMS + ", actual SMS is " + actualSMS + ", test failed  ======>");
//        }
//    }
//
//    @Then("^I see SMS contains 'TEST random' associated with 'DealerNo#' on 'mightytext.net' page$")
//    public void iSeeSMSOnMightyTextPage() {
//        String actualSMS = mtPage.getSMSFromMightyTextPage();
//        Assert.assertEquals("Actual SMS received doesn't equal to SMS sent, test failed", tp.sms, actualSMS);
//    }
//
//    @Then("^I click Email associated with (.+) on \"([^\"]*)\" page$")
//    public void iClickEmailonSomePage(String dealerName, String pageName) {
//        switch (pageName) {
//            case "Gmail":
//                gmailPage.clickOnAssociatedEmail(dealerName);
//                break;
//        }
//    }
//
//    @And("^I delete SMS$")
//    public void iDeleteSMS() {
//        mtPage.deleteSMS();
//    }
//
//    @Then("^I should see following reports loading properly on Reports page$")
//    public void iVerifyReportsLoadOnReportsPage(DataTable dataTable) {
//        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> map : maps) {
//            String reportName = map.get("Report Name");
//            String reportColumn = map.get("Report Column");
//            String reportValue = map.get("Report Value");
//            if (StringUtils.isNotBlank(reportName)) {
//                crPage.clickOnReportDropDown(reportName);
//                if (!crPage.verifyColumnVisible(reportColumn)) {
//                    Assert.fail("<====== Report Column '" + reportColumn + "' not found ======>");
//                }
//                if (reportValue.equals("1st row")) {
//                    if (!crPage.verifyValueVisibleOverViewReport()) {
//                        Assert.fail("<====== Report Column value '" + reportValue + "' not found ======>");
//                    }
//                } else {
//                    if (!crPage.verifyValueVisible(reportValue)) {
//                        Assert.fail("<====== Report Column value '" + reportValue + "' not found ======>");
//                    }
//                }
//            }
//        }
//    }
//
//    //Comment out by Stewart,
////    @Then("^I should not see \"([^\"]*)\" tab on \"([^\"]*)\" page$")
////    public void iVerifyTabNotSeeingOnCustomerConnectPage(String tabName, String pageName){
////        switch (pageName){
////            case "Guest Connect":
////                switch (tabName){
////                    case "REPORTS":
////                        if(!cPage.verifyIfReportsNotShow()){
////                            Assert.fail("<====== This user is advisor but he saw the reports menu, fail ======>");
////                        }
////                        break;
////                    case "SCRIPTS":
////                        if(!cPage.verifyIfScriptsNotShow()){
////                            Assert.fail("<====== This user is advisor but he saw the scripts menu, fail ======>");
////                        }
////                        break;
////                }
////                break;
////        }
////    }
//
//    @Then("^I don't see \"([^\"]*)\" tab on 'Customer Connect' page$")
//    public void iVerifyTabNotSeeingOnCustomerConnectPage(String tabName) {
//        switch (tabName) {
//            case "SCRIPTS":
//                if (!cPage.verifyIfScriptsNotShow()) {
//                    Assert.fail("<====== This user is advisor but he saw the scripts menu, fail ======>");
//                }
//                break;
//            case "CAMPAIGNS":
//                if (!cPage.verifyIfCampaignsNotShow()) {
//                    Assert.fail("<====== This user is advisor but he saw the campaigns menu, fail ======>");
//                }
//                break;
//        }
//    }
//
//    @Then("^I should not see \"([^\"]*)\" icon on \"([^\"]*)\" Campaign$")
//    public void iVerifyNotSeeingIconOnCampaign(String iconName, String campaignName) {
//        switch (iconName) {
//            case "configure":
//                if (!cPage.verifyIfIconNotShow(campaignName)) {
//                    Assert.fail("<====== The configure icon show when user log in as advisor, test fail ======>");
//                }
//                break;
//        }
//    }
//
//    @And("^I add \"([^\"]*)\" plus random last name in 'Campaign Name' field$")
//    public void iAddNameOnCampaignNameField(String firstName) {
//        ccPage.setCampaignName(firstName);
//    }
//
//    @And("^I add random script name in 'Script Name' field$")
//    public void iAddNameOnScriptNameField() {
//        csPage.setScriptName();
//    }
//
//    @And("^I add \"([^\"]*)\" in 'Campaign Description' field$")
//    public void iAddDescriptionOnCampaignDescriptionField(String campaignDescription) {
//        ccPage.writeCampaignDescription(campaignDescription);
//    }
//
//    @Then("^I should see \"([^\"]*)\" plus random last name in the 'Campaign list' on \"([^\"]*)\" page$")
//    public void iVerifyCampaignOnCampaignList(String firstName, String pageName) {
//        if (!ccPage.verifyIfCampaignRecordCreated()) {
//            Assert.fail("<====== I am not able to find campaign name '" + ccPage.campaignName + "' on '" + pageName + "' page ======>");
//        }
//    }
//
//    @Then("^I should not see \"([^\"]*)\" plus random last name in the 'Campaign list' on \"([^\"]*)\" page$")
//    public void iVerifyCampaignNotOnCampaignList(String firstName, String pageName) {
//        if (!ccPage.verifyIfCampaignRecordDeleted()) {
//            Assert.fail("<====== I am able to find campaign name '" + ccPage.campaignName + "' on '" + pageName + "' page, not delete yet ======>");
//        }
//    }
//
//    @Then("^I should see random script name in the 'Script list' on \"([^\"]*)\" page$")
//    public void iVerifyScriptOnScriptList(String pageName) {
//        if (!csPage.verifyIfNewScriptCreated()) {
//            Assert.fail("<====== I am not able to find script name '" + csPage.scriptName + "' on '" + pageName + "' page ======>");
//        }
//    }
//
//    @Then("^I should not see random script name in the 'Script list' on \"([^\"]*)\" page$")
//    public void iVerifyScriptNotOnScriptList(String pageName) {
//        if (!csPage.verifyIfScriptRecordDeleted()) {
//            Assert.fail("<====== I am able to find script name '" + csPage.scriptName + "' on '" + pageName + "' page, not delete yet ======>");
//        }
//    }
//
//    @When("^I click \"([^\"]*)\" plus random last name in the 'Campaign list' on \"([^\"]*)\" page$")
//    public void iClickCampaignOnCampaignList(String firstName, String pageName) {
//        ccPage.clickCampaignRecord();
//    }
//
//    @When("^I click random script name in the 'Script list' on \"([^\"]*)\" page$")
//    public void iClickScriptOnScriptList(String pageName) {
//        csPage.clickScriptRecord();
//    }
//
//    @And("^I click \"([^\"]*)\" icon on \"([^\"]*)\" Campaign$")
//    public void iClickIconOnCampaign(String iconName, String campaignName) {
//        switch (campaignName) {
//            case "Missed / No Show Appointments":
//                switch (iconName) {
//                    case "configure":
//                        cPage.clickConfigureIconWithCampaign(campaignName);
//                        break;
//                }
//                break;
//        }
//    }
//
//    @Then("^I should see \"([^\"]*)\" Campaign selected as default$")
//    public void iVerifyCampaignSelected(String campaignType) {
//        if (!ccPage.verifyCampaignSelectedAsDefault(campaignType)) {
//            Assert.fail("<====== '" + campaignType + "' is not preselected, test fail ======>");
//        }
//    }
//
//    @And("^I should see header menu showing following status$")
//    public void iVerifyHeaderMenuStatus(DataTable dataTable) {
//        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> map : maps) {
//            String menuName = map.get("Header menu");
//            String expectedStatus = map.get("Expected status");
//            if (StringUtils.isNotBlank(menuName)) {
//                if (menuName.equals("MESSENGER")) {
//                    menuName = "Messenger";
//                }
//                if (!cPage.verifyHeaderMenuStatus(menuName, expectedStatus)) {
//                    Assert.fail("<====== Header menu '" + menuName + "' is not showing status '" + expectedStatus + "', test fail ======>");
//                }
//            }
//        }
//    }
//
//    @Then("^I should see result by select following \"([^\"]*)\" dropDownOption$")
//    public void iVerifyDropDownOnPage(String dropDownName, DataTable dataTable) {
//        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> map : maps) {
//            String dropDownOption = map.get("DropDownName");
//            String expectedInsert = map.get("Expected Insert Result");
//            if (StringUtils.isNotBlank(dropDownOption)) {
//                csPage.getScriptConentBefore();
//                csPage.selectValueFromInsertScriptVariable(dropDownOption);
//                csPage.getScriptConentAfter();
//                if (csPage.scriptContentBefore.endsWith(expectedInsert)) {
//                    Assert.fail("<====== Script contents end with '" + expectedInsert + "' before insert '" + dropDownOption + "', test fail ======>");
//                } else if (!csPage.scriptContentAfter.endsWith(expectedInsert)) {
//                    Assert.fail("<====== Script contents not end with '" + expectedInsert + "' after insert '" + dropDownOption + "', test fail ======>");
//                } else {
//                    System.out.println("<====== '" + expectedInsert + "' got appended to the end of the script contents ======>");
//                }
//            }
//        }
//    }
//
//    @Then("^I should see error message \"([^\"]*)\" on \"([^\"]*)\" page$")
//    public void iVerifyErrorMessageOnPage(String errorMsg, String pageName) {
//        switch (pageName) {
//            case "SCRIPTS":
//                if (!csPage.verifyIfErrorMsgExisted(errorMsg)) {
//                    Assert.fail("<====== '" + errorMsg + "' is not showing up, test fail ======>");
//                }
//                break;
//
//        }
//    }
//
//    @Then("^I should see record \"([^\"]*)\" tab on \"([^\"]*)\" page$")
//    public void iVerifyRecordUnderTabUnderPage(String tabName, String pageName) {
//        switch (pageName) {
//            case "Task Detail":
//                switch (tabName) {
//                    case "DEFERRED RECOMMENDATIONS":
//                        assertTrue(tPage.verifyIfRecordsUnderDeferredRecommendations());
//                        break;
//                }
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" icon linked with Name contains \"([^\"]*)\" on \"([^\"]*)\" page$")
//    public void iClickIconOnNameOnPage(String iconName, String customerName, String pageName) {
//        switch (pageName) {
//            case "SEARCH":
//                CC_SearchPage csPage = new CC_SearchPage();
//                switch (iconName) {
//                    case "modify":
//                        csPage.clickOnModifyAssociatedWithName(customerName);
//                        break;
//                }
//                break;
//        }
//    }
//
//    @Then("^I search following VIN on \"([^\"]*)\" page with \"([^\"]*)\" dealer$")
//    public void iSearchVinOnDealerOnPage(String pageName, String dealerName, DataTable dataTable) {
//        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> map : maps) {
//            String dealer = map.get("Dealer");
//            String vin = map.get("VIN");
//            if (StringUtils.isNotBlank(vin)) {
//                switch (dealerName) {
//                    case "Nissan of Anytown USA":
//                        if (dealer.equals("Nissan of Anytown USA")) {
//                            sPage.searchKeyWord(vin);
//                        } else {
//                            //do nothing
//                        }
//                        break;
//                    case "CHRYSLER QA":
//                        if (dealer.equals("CHRYSLER_QA")) {
//                            sPage.searchKeyWord(vin);
//                        } else {
//                            //do nothing
//                        }
//                        break;
//                }
//            }
//        }
//    }
//
//    @Then("^I see following VIN displayed on \"([^\"]*)\" page with \"([^\"]*)\" dealer$")
//    public void iVerifyVinOnDealerOnPage(String pageName, String dealerName, DataTable dataTable) {
//        String vinSearchResult = "";
//        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> map : maps) {
//            String dealer = map.get("Dealer");
//            String vin = map.get("VIN");
//            if (StringUtils.isNotBlank(vin)) {
//                switch (dealerName) {
//                    case "Nissan of Anytown USA":
//                        if (dealer.equals("Nissan of Anytown USA")) {
//                            vinSearchResult = sPage.searchResult(vin, "Vehicle");
//                            assertTrue(vinSearchResult.contains(vin));
//                        } else {
//                            //do nothing
//                        }
//                        break;
//                    case "CHRYSLER QA":
//                        if (dealer.equals("CHRYSLER_QA")) {
//                            vinSearchResult = sPage.searchResult(vin, "Vehicle");
//                            assertTrue(vinSearchResult.contains(vin));
//                        } else {
//                            //do nothing
//                        }
//                        break;
//                }
//            }
//        }
//    }
//
//    @And("^I toggle \"([^\"]*)\" to \"([^\"]*)\" under \"([^\"]*)\" section on \"([^\"]*)\" page$")
//    public void iToggleStatusUnderSectionOnPage(String fieldName, String status, String sectionName, String pageName) {
//        switch (pageName) {
//            case "CAMPAIGNS":
//                switch (sectionName) {
//                    case "INFORMATION":
//                        ccPage.toggleStatusOnField(fieldName, status);
//                        break;
//                }
//                break;
//        }
//    }
//
//    @And("^I search following BDC Agent/Manager under \"([^\"]*)\" search field on \"([^\"]*)\" page$")
//    public void iSearchBDCAgentUnderField(String fieldName, String pageName, DataTable dataTable) {
//        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> map : maps) {
//            String bdcRole = map.get("Role");
//            String agentName = map.get("Name");
//            if (StringUtils.isNotBlank(agentName)) {
//                switch (pageName) {
//                    case "CAMPAIGNS":
//                        CC_CampaignsPage ccPage = new CC_CampaignsPage();
//                        switch (fieldName) {
//                            case "Service Coordinators available for assignment":
//                                ccPage.searchBDCAgent(agentName);
//                                break;
//                        }
//                        break;
//                }
//            }
//        }
//    }
//
//    @And("^I upload \"([^\"]*)\" on \"([^\"]*)\" page$")
//    public void iUploadFileOnPage(String fileName, String pageName) {
//        switch (pageName) {
//            case "CAMPAIGNS":
//                CC_CampaignsPage ccPage = new CC_CampaignsPage();
//                ccPage.uploadCampaignFile(fileName);
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" campaign on \"([^\"]*)\" page$")
//    public void iClickCampaignOnPage(String campaignName, String pageName) {
//        switch (pageName) {
//            case "DASHBOARD":
//                cPage.clickCampaignWithName(campaignName);
//                break;
//        }
//    }
//
//    @Then("^I see tasks randomly assigned to following agents on \"([^\"]*)\" page$")
//    public void iShouldSeeTaskAssignedToAgentsOnPage(String pageName, DataTable dataTable) {
//        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> map : maps) {
//            String agentName = map.get("AgentName");
//            if (StringUtils.isNotBlank(agentName)) {
//                switch (pageName) {
//                    case "Task Detail":
//                        assertTrue(tPage.verifyAgentNameExistOnTask(agentName));
//                        tPage.getCustomerNameAgentNameArray(agentName);
//                        break;
//                }
//            }
//        }
//    }
//
//    @And("^I see tasks assigned to agent \"([^\"]*)\" on \"([^\"]*)\" page$")
//    public void iSeeTaskAssignToAgentOnPage(String agentName, String pageName) {
//        switch (pageName) {
//            case "Task Detail":
//                assertTrue(tPage.verifyAgentNameExistOnTask(agentName));
//                break;
//        }
//    }
//
//    @And("^I not see tasks assigned to \"([^\"]*)\" on \"([^\"]*)\" page$")
//    public void iNotSeeTasksAssignedToAgentOnPage(String agentName, String pageName) {
//        switch (pageName) {
//            case "Task Detail":
//                assertTrue(tPage.verifyAgentNameNotExistOnTask(agentName));
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" button on 'Task Detail' page$")
//    public void iClickButtonOnTaskDetailPage(String buttonName) {
//        switch (buttonName) {
//            case "Save":
//                tPage.clickOnSaveOnTaskDetail();
//                break;
//            case "Cancel":
//                tPage.clickCancelOnTaskDetail();
//                break;
//            case "Schedule Appointment":
//                tPage.clickOnScheduleAppointment();
//                tPage.clickOKBtnOnPopup();
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" button on 'TASKS' page$")
//    public void iClickButtonOnTasksPage(String buttonName) {
//        switch (buttonName) {
//            case "EXPORT":
//                cPage.clickOnExport();
//                break;
//            case "Note":
//                tPage.clickOnNoteButton();
//                break;
//            case "Clear All":
//                tPage.clickOnClearAll();
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" icon on 'MESSENGER' page$")
//    public void iClickIconOnMessengerPage(String iconName) {
//        switch (iconName) {
//            case "modify":
//                cmPage.clickOnModifyIconOnMessenger();
//                break;
//            case "Refresh":
//                cmPage.clickOnRefresh();
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" button on 'MESSENGER' page$")
//    public void iClickButtonOnMessengerPage(String buttonName) {
//        switch (buttonName) {
//            case "Customer":
//                cmPage.clickOnCustomer();
//                break;
//            case "Service Lane":
//                cmPage.clickOnServiceLane();
//                break;
//            case "Add Note":
//                cmPage.clickOnAddNoteButton();
//                break;
//            case "Note":
//                cmPage.clickOnNoteButton();
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" button on 'CAMPAIGNS' page$")
//    public void iClickButtonOnCampaignsPage(String buttonName) {
//        switch (buttonName) {
//            case "New Campaign":
//                ccPage.clickOnNewCampaign();
//                break;
//            case "Next":
//                ccPage.clickOnNextOnCampaignPage();
//                break;
//            case "Create":
//                ccPage.clickOnCreateOnCampaign();
//                break;
//            case "OK":
//                ccPage.clickOnOKOnPopUpOnCampaign();
//                break;
//            case "DELETE":
//                ccPage.clickDeleteOnCampaign();
//                break;
//            case "Save":
//                ccPage.clickOnSaveOnCampaign();
//                break;
//            case "Apply":
//                ccPage.clickOnApplyButton();
//                break;
//            case "Choose file":
//                ccPage.clickOnChooseFile();
//                break;
//            case "Import":
//                ccPage.clickOnImportButton();
//                break;
//            case "New Template":
//                ccPage.clickOnNewTemplateOnCampaignPage();
//                break;
//            case "Preview and Test":
//                ccPage.clickPreviewAndTestOnCampaignPage();
//                break;
//            case "Send Test Email":
//                ccPage.clickSendTestEmailOnCampaignPage();
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" button on 'SCRIPTS' page$")
//    public void iClickButtonOnScriptsPage(String buttonName) {
//        switch (buttonName) {
//            case "NEW SCRIPT":
//                csPage.clickOnNewScript();
//                break;
//            case "Create":
//                csPage.clickOnCreateOnScript();
//                break;
//            case "OK":
//                csPage.clickOnOKOnPopUpOnScript();
//                break;
//            case "DELETE":
//                csPage.clickDeleteOnScript();
//                break;
//        }
//    }
//
//    @And("^I input \"(.*)\" in the 'Script Content' field on 'SCRIPTS' page$")
//    public void iInputTestInScriptFieldOnScriptpage(String content) {
//        csPage.inputContentInScript(content);
//    }
//
//    @And("^I click \"(.*)\" icon in \"(.*)\" section on 'Task Detail' page$")
//    public void iClickIconOnSectionOnPage(String iconName, String sectionName) {
//        switch (iconName) {
//            case "Modify":
//                tPage.clickModifyIconOnCustomerDetails();
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" tab under Customer Name$")
//    public void iClickSomeTabUnderCustomerName(String tabName) {
//        switch (tabName) {
//            case "CUSTOMER NOTES":
//                sPage.clickOnCustomerNotesTabWithName();
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" button on 'Create Script' popup window$")
//    public void iClickSomethingOnCreateScriptPopup(String btnName) {
//        switch (btnName) {
//            case "Save":
//                ccPage.clickOnSaveBtn();
//                break;
//        }
//    }
//
//    @Then("^\"([^\"]*)\" should be displayed under 'Email Templates' dropdown menu on 'EMAIL' tab$")
//    public void iVerifySelectedOptionOnDropdown(String selectedOption) {
//        switch (selectedOption) {
//            case "random character":
//                assertTrue("Currently selected option under Email Templates dropdown is not the 'TEST random' previously created, test failed!", ccPage.getSelectedOption().equalsIgnoreCase(tp.newCreatedEmailSubject));
//                break;
//        }
//    }
//
//    @Then("^\"([^\"]*)\" window should be opened$")
//    public void iVerifySomeWindowOpened(String windowName) {
//        switch (windowName) {
//            case "Preview":
//                assertTrue("Send Test Email button is not displayed, test failed", ccPage.isSendTestEmailBtnDisplayed());
//                break;
//            case "Service History":
//                assertTrue("Service History window is not opened, test failed", sPage.isServiceHistoryWindowDisplayed());
//                break;
//        }
//    }
//
//    @And("^I see \"([^\"]*)\" in 'Email Body Content' field if I created a new template$")
//    public void iVerifyEmailBodyContent(String emailBodyContent) {
//        if(!tp.newCreatedEmailSubject.isEmpty()) {
//            assertEquals(emailBodyContent, ccPage.getEmailBodyContent());
//        }
//    }
//
//    @When("^I select \"([^\"]*)\" or create a new template from \"([^\"]*)\" dropdown menu$")
//    public void iSelectOrCreateEmailTemplateOnCampaignsPage(String option, String dropDown) {
//        switch (dropDown) {
//            case "Email Templates":
//                if(ccPage.emailTemplateNotEmpty()) {
//                    ccPage.selectValueFromEmailTemplatesDropDownOnCampaign(option);
//                    break;
//                }else {
//                    ccPage.clickOnNewTemplateOnCampaignPage();
//                    ccPage.inputTextOnEmailSubjectOnCreateScript("TEST random");
//                    ccPage.inputTextOnEmailBodyContent("Test DFX Test Email Please Ignore");
//                    ccPage.clickOnSaveBtn();
//                    Assert.assertTrue("Currently selected option under Email Templates dropdown is not the 'TEST random' previously created, test failed!", ccPage.getSelectedOption().equalsIgnoreCase(tp.newCreatedEmailSubject));
//                }
//                break;
//        }
//    }
//
//    @And("^I enter \"([^\"]*)\" on \"([^\"]*)\" on 'Preview' popup$")
//    public void iEnterValueOnFieldOnPreview(String value, String fieldName) {
//        switch (fieldName) {
//            case "Recipient":
//                ccPage.inputTextOnRecipientOnPreview(value);
//                break;
//            case "Email Subject":
//                ccPage.inputTextOnEmailSubjectOnPreview(value);
//                break;
//        }
//    }
//
//    @Then("^I see new email with subject as 'TEST random' in Gmail inbox$")
//    public void iSeeNewEmailWithSubjectAsTESTRandomInGmailInbox() {
//        gmailPage.clickOnEmailTESTRandom();
//        Assert.assertTrue(gmailPage.senderAddress());
//    }
//
//    @And("^I delete new email with subject as 'TEST random'$")
//    public void iDeleteGmail() {
//        gmailPage.deleteGmail();
//    }
//
//    @Then("^I see \"([^\"]*)\" on 'TASKS' Page$")
//    public void iSeeSomethingOnTasksPage(String element) {
//        switch (element) {
//            case "Tasks List":
//                assertTrue(tPage.ifTasksListPresented());
//                break;
//            case "Empty Tasks List":
//                assertTrue("At least one task row is shown, test failed!", tPage.isTasksListEmpty());
//        }
//    }
//
//    @When("^I select \"([^\"]*)\" random task checkboxes from Tasks List$")
//    public void iSelectRandomTasks(int numOfRandomTasks) {
//        tPage.randomlySelectTaskLists(numOfRandomTasks);
//    }
//
//    @And("^I click on \"([^\"]*)\" button on 'TASKS' page$")
//    public void iClickSomethingOnTasksPage(String elementName) {
//        switch (elementName) {
//            case "Reassign Tasks":
//                tPage.clickReassignTasksBtn();
//                break;
//            case "Apply":
//                tPage.clickApplyBtn();
//                break;
//            case "Reassign Agents":
//                tPage.clickReassignAgentsBtn();
//                break;
//            case "Include All Campaigns":
//                tPage.clickIncludeAllCampaignsArrow();
//                break;
//            case "Next":
//                tPage.clickNextBtn();
//                break;
//            case "Save":
//                tPage.clickSaveBtn();
//                break;
//            case "Clear All":
//                tPage.clickOnClearAll();
//                break;
//        }
//    }
//
//    @And("^I search agent name \"([^\"]*)\" in \"([^\"]*)\" field$")
//    public void iSearchBDCAgent(String agentName, String searchField) {
//        switch (searchField) {
//            case "Agent Search":
//                tPage.searchBDCAgent(agentName);
//                break;
//            case "Global Agent Search":
//                tPage.searchGlobalAgent(agentName);
//                break;
//        }
//    }
//
//    @And("^I click on \"([^\"]*)\" button to select BDC Agent$")
//    public void iSelectBDCAgent(String btnName) {
//        switch (btnName) {
//            case "Left Arrow":
//                tPage.clickLeftArrowBtn();
//                break;
//        }
//    }
//
//    @And("^I click on \"([^\"]*)\" button on pop up message window$")
//    public void iClickOnBtnOnPopup(String btnName) {
//        switch (btnName) {
//            case "YES":
//                tPage.clickYesBtnOnPopup();
//                break;
//            case "NO":
//                break;
//        }
//    }
//
//    @Then("^I see all \"([^\"]*)\" randomly selected tasks have \"([^\"]*)\" name as \"([^\"]*)\"$")
//    public void iSeeRandomTasksValue(int numOfRandomTasks, String fieldName, String expectedValue) {
//        switch (fieldName) {
//            case "Assigned Agent":
//                tPage.sleep(3000);
//                for(int i=0; i<numOfRandomTasks; i++){
//                    Assert.assertEquals(tPage.getAssignedAgentName(i), expectedValue);
//                }
//                break;
//        }
//    }
//
//    @When("^I select all \"([^\"]*)\" randomly selected tasks assigned to 'Darren Deng'$")
//    public void iReselectRandomTasks(int numOfRandomTasks) {
//        tPage.clickTaskListCheckboxes(numOfRandomTasks);
//    }
//
//    @And("^I click \"([^\"]*)\" button beside \"([^\"]*)\"$")
//    public void iClickButtonBesideAgentName(String btnName, String agentName) {
//        switch (btnName) {
//            case "Right Green Arrow":
//                tPage.clickRightGreenArrowBtn(agentName);
//                break;
//            case "Left Red Arrow":
//                tPage.clickLeftRedArrowBtn(agentName);
//                break;
//        }
//    }
//
//    @Then("^I see 'number' of tasks$")
//    public void iSeeNumberOfTasks() {
//        numOfTasks = tPage.getNumberOfTasks();
//    }
//
//    @Then("^I see 'number' of tasks remain the same$")
//    public void iVerifyNumberOfTasks() {
//        assertEquals("Number of tasks changed, test failed!", numOfTasks, tPage.getNumberOfTasks());
//    }
//
//    @And("^I see all tasks have \"([^\"]*)\" name \"([^\"]*)\"$")
//    public void iVerifyTaskColumnValue(String columnName, String expectedValue) {
//        for(int i=1; i<=Integer.parseInt(numOfTasks); i++){
//            assertEquals("Assigned agent name isn't " + expectedValue +", test failed!", expectedValue, tPage.getAssignedAgentColumnValue(i));
//        }
//    }
//
//    @Then("^I should be on \"([^\"]*)\" page$")
//    public void iLandedOnSomePage(String pageName) {
//        switch(pageName){
//            case "SEARCH":
//                assertTrue(cPage.isHeaderActive(pageName));
//                break;
//            case "taskDetails":
//                assertTrue("I am not on TaskDetails page, test fail", tPage.getURL().contains("taskDetails"));
//                break;
//        }
//    }
//
//    @And("^I see \"([^\"]*)\" from search result on 'SEARCH' page$")
//    public void iSeeSomethingFromSearchResult(String resultSegment) {
//        switch(resultSegment){
//            case "VIN":
//                vin = sPage.getVINFromResult();
//                break;
//        }
//    }
//
//    @And("^I click \"([^\"]*)\" on expanded view$")
//    public void iClickSomethingOnExpandedView(String elementName) {
//        switch(elementName){
//            case "TASKS":
//                sPage.clickTasksTab();
//                break;
//            case "SERVICE HISTORY":
//                sPage.clickServiceHistoryTab();
//                break;
//            case "first i":
//                sPage.clickInfoIcon(0);
//                break;
//            case "first Campaign":
//                sPage.clickCampaign(0);
//                break;
//        }
//    }
//
//    @And("^'Service History' window should have correct \"([^\"]*)\" under 'Vehicle Details'$")
//    public void iVerifySomethingOnServiceHistoryWindow(String elementName) {
//        switch(elementName){
//            case "VIN":
//                assertEquals("Wrong VIN displayed in Service History window, test failed", vin, sPage.getVINFromServiceHistoryWindow());
//        }
//    }
//
//    @And("^I see \"([^\"]*)\" on 'Task Details' page$")
//    public void iSeeSomethingOnTaskDetailsPage(String elementName) {
//        switch(elementName){
//            case "VIN":
//                assertEquals("Wrong VIN displayed in Service History window, test failed", vin, tPage.getVIN());
//                break;
//        }
//    }
//
//    @When("^I click \"([^\"]*)\" on 'Task Details' page$")
//    public void iClickSomethingOnTaskDetailsPage(String elementName) {
//        switch(elementName){
//            case "SERVICE HISTORY":
//                tPage.clickServiceHistoryTab();
//                break;
//            case "first i":
//                tPage.clickInfoIcon(0);
//                break;
//        }
//    }
//
//}