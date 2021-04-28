package com.autotest.teststeps.customer_connect;

import com.automation.pages.customer_connect.CC_MessengerPage;
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

public class CC_MessengerSteps extends BaseTestSteps {

    CC_MessengerPage cmPage = new CC_MessengerPage();
    TestParameters tp = new TestParameters();

    @And("^I search \"([^\"]*)\" in \"([^\"]*)\" field on 'MESSENGER' page$")
    public void iSearchSomethingOnMessengerPage(String searchWord, String fieldName) {
        switch (fieldName) {
            case "Search Customer":
                cmPage.searchCustomerOnMessenger(searchWord);
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" contains \"([^\"]*)\" displayed on 'MESSENGER' page$")
    public void iSeeSearchResultOnMessengerPage(String searchField, String keyWord) {
        String expectedResult = keyWord.toLowerCase();
        String actualResult = "";
        switch (searchField) {
            case "Customer name":
                actualResult = cmPage.getCustomerNameSearchResult().toLowerCase();
                break;
        }
        assertTrue(actualResult.contains(expectedResult));
    }

    @And("^I get \"([^\"]*)\" from the 1st row on 'MESSENGER' page$")
    public void iGetSomethingFromFirstRowOnMessengerPage(String name) {
        switch (name) {
            case "Customer name":
                cmPage.getCustomerNameSearchResult();
                break;
        }
    }

    @And("^I enter \"([^\"]*)\" on \"([^\"]*)\" on 'MESSENGER' page$")
    public void iInputTextOnSomeFieldOnMessengerPage(String text, String fieldName) {
        switch (fieldName) {
            case "Notes":
                cmPage.inputTextOnNotes(text);
                break;
        }
    }

    @And("^I see \"([^\"]*)\" on \"([^\"]*)\" on 'MESSENGER' page$")
    public void iVerifyTextOnNoteOnMessengerPage(String text, String sectionName) {
        if (text.equals("Test random")) {
            text = tp.customerNoteTxt;
        }
        String actualText = "";
        switch (sectionName) {
            case "Notes":
                actualText = cmPage.getTextOnNote().trim();
                break;
        }
        assertEquals(text, actualText);
    }

    @And("^I click \"([^\"]*)\" on \"([^\"]*)\" on 'MESSENGER' page$")
    public void iClickSomethingOnNoteOnMessengerPage(String iconName, String sectionName) {
        switch (sectionName) {
            case "Notes":
                switch (iconName) {
                    case "close":
                        cmPage.clickOnCloseOnNotes();
                        break;
                }
                break;
        }
    }

    @And("^I click 'Customer name' from the 1st row on 'MESSENGER' page$")
    public void iClickNameOnMessengerPage() {
        cmPage.clickCustomerWithNameOnMsg();
    }

    @When("^I click \"([^\"]*)\" search result contains \"([^\"]*)\" on 'MESSENGER' page$")
    public void iClickSearchResultContainsSearchNameOnMessengerPage(String searchField, String keyWord) {
        switch (searchField) {
            case "Customer name":
                cmPage.clickOnSearchResult(keyWord);
                break;
        }
    }

    @And("^I get \"([^\"]*)\" from page title \"([^\"]*)\"$")
    public void iGetSomethingOnSomePage(String stuffGetting, String pageTitle) {
        switch (pageTitle) {
            case "ReceiveaSMS.com :: Canada":
                switch (stuffGetting) {
                    case "Phone#":
                        cmPage.getNumberOnReceiveSMS();
                        break;
                }
                break;
        }
    }

    @And("^I modify \"([^\"]*)\" in \"([^\"]*)\" field on 'Customer Details' popup$")
    public void iModifySomethingInSomeField(String phoneNo, String fieldName) {
        switch (fieldName) {
            case "Cell Phone":
                cmPage.modifyCellPhoneField(phoneNo);
                break;
        }
    }

    @And("^I opt in \"([^\"]*)\" check box on 'Customer Details' page$")
    public void iOptInCheckboxOnCustomerDetailsPage(String checkBox) {
        cmPage.optInTextCheckBox(checkBox);
    }

    @And("^I send SMS \"([^\"]*)\" to customer on 'MESSENGER' page$")
    public void iSendSMSOnMessengerPage(String textSMS) {
        if (textSMS.contains("random")) {
            CommonMethods cm = new CommonMethods();
            tp.sms = "TEST" + " " + cm.getRandomText(5);
        }
        cmPage.sendMessageToCustomer(tp.sms);
    }

    @Then("^I see SMS contains \"([^\"]*)\" associated with \"([^\"]*)\" on 'MESSENGER' page$")
    public void iSeeSMSonSomePage(String textSMS) {
        if (textSMS.contains("random")) {
            textSMS = textSMS.replace("random", tp.randomText);
        }
        String actualSMS;
//            case "ReceiveaSMS.com :: Canada":
//                actualSMS = cmPage.getSMSFromReceiveSMSPage(textSMS);
//                break;
        actualSMS = cmPage.getSMSFromMessengerPage(textSMS);
        if (!actualSMS.contains(textSMS)) {
            Assert.fail("<====== The expected SMS is " + textSMS + ", actual SMS is " + actualSMS + ", test failed  ======>");
        }
    }

    @And("^I click \"([^\"]*)\" button on 'MESSENGER' page$")
    public void iClickButtonOnMessengerPage(String buttonName) {
        switch (buttonName) {
            case "Customer":
                cmPage.clickOnCustomer();
                break;
            case "Service Lane":
                cmPage.clickOnServiceLane();
                break;
            case "Add Note":
                cmPage.clickOnAddNoteButton();
                break;
            case "Note":
                cmPage.clickOnNoteButton();
                break;
            case "modify":
                cmPage.clickOnModifyIconOnMessenger();
                break;
            case "Refresh":
                cmPage.clickOnRefresh();
                break;
        }
    }

    @And("^I see following data in \"([^\"]*)\" section on 'MESSENGER' page$")
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
                                actualResult = cmPage.getHomeIconCustomerDetailsMessenger();
                                assertEquals(expectedResult, actualResult);
                                break;
                            case "CellPhoneIcon":
                                actualResult = cmPage.getCellPhoneIconCustomerDetailsMessenger();
                                assertEquals(expectedResult, actualResult);
                                break;
                        }
                    break;
                }
            }
        }
    }

}





