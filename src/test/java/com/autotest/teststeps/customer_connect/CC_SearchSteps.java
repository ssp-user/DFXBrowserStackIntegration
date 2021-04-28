package com.autotest.teststeps.customer_connect;

import com.automation.pages.customer_connect.CC_SearchPage;
import com.automation.utils.dataProvider.TestParameters;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CC_SearchSteps extends BaseTestSteps {

    CC_SearchPage sPage = new CC_SearchPage();
    TestParameters tp = new TestParameters();

    public static String vin;

    @And("^I click on \"([^\"]*)\" tab on 'SEARCH' page$")
    public void iClickTabSearchPage(String tabName) {
        switch(tabName){
            case "TASKS":
                sPage.clickTaskTabOnSearchPage();
                break;
        }
    }

    @And("^I search \"([^\"]*)\" on 'SEARCH' page$")
    public void iSearch(String searchWord) {
        sPage.searchKeyWord(searchWord);
    }

    @And("^I click \"([^\"]*)\" button on 'SEARCH' page$")
    public void iClickBtnOnSearchPage(String buttonName) {
        switch (buttonName) {
            case "Name":
                sPage.clickOnName();
                break;
            case "Note":
                sPage.clickOnNoteButton();
                break;
            case "Email":
                sPage.clickEmailBtn();
                break;
            case "Phone":
                sPage.clickPhoneBtn();
                break;
            case ">":
                sPage.clickRightArrowBtn();
                break;
            case "+":
                sPage.clickAddTaskBtn();
                break;    
            case "CANCEL":
                sPage.clickCancelBtn();
                break;                  
            default:
                sPage.clickOnSearchButton(buttonName);
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" results matched \"([^\"]*)\"$")
    public void iSeeResult(String fieldName, String searchWord) {
        String searchResult = sPage.searchResult(searchWord, fieldName);
        assertTrue(searchResult.contains(searchWord));
    }

    @And("^I get \"([^\"]*)\" from the 1st row on 'SEARCH' page$")
    public void iGetSomethingFromFirstRowOnSearchPage(String elementName) {
        switch (elementName) {
            case "Customer name":
                sPage.getCustomerNameOnSearch();
                break;
        }
    }

    @And("^I enter \"([^\"]*)\" on \"([^\"]*)\" on 'SEARCH' page$")
    public void iInputTextOnSomeFieldOnSearchPage(String text, String fieldName) {
        switch (fieldName) {
            case "Notes":
                sPage.inputTextOnNotes(text);
                break;
        }
    }

    @And("^I see \"([^\"]*)\" on \"([^\"]*)\" on 'SEARCH' page$")
    public void iVerifyTextOnNoteOnSearchPage(String text, String sectionName) {
        if (text.equals("Test random")) {
            text = tp.customerNoteTxt;
        }
        String actualText = "";
        switch (sectionName) {
            case "Notes":
                actualText = sPage.getTextOnNote().trim();
                break;
        }
        assertEquals(text, actualText);
    }

    @And("^I click \"([^\"]*)\" on \"([^\"]*)\" on 'SEARCH' page$")
    public void iClickSomethingOnNoteOnSearchPage(String iconName, String sectionName) {
        switch (sectionName) {
            case "Notes":
                switch (iconName) {
                    case "close":
                        sPage.clickOnCloseOnNotes();
                        break;
                }
                break;
        }
    }

    @And("^I click 'Customer name' from the 1st row on 'SEARCH' page$")
    public void iClickNameOnSearchPage() {
        sPage.clickCustomerWithNameOnSearch();
    }

    @And("^I click \"([^\"]*)\" icon linked with Name contains \"([^\"]*)\" on 'SEARCH' page$")
    public void iClickIconOnNameOnSearchPage(String iconName, String customerName) {
        switch (iconName) {
            case "modify":
                sPage.clickOnModifyAssociatedWithName(customerName);
                break;
        }
    }

    @Then("^I search following VIN on 'SEARCH' page with \"([^\"]*)\" dealer$")
    public void iSearchVinOnDealerOnSearchPage(String dealerName, DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String dealer = map.get("Dealer");
            String vin = map.get("VIN");
            if (StringUtils.isNotBlank(vin)) {
                switch (dealerName) {
                    case "Nissan of Anytown USA":
                        if (dealer.equals("Nissan of Anytown USA")) {
                            sPage.searchKeyWord(vin);
                        } else {
                            //do nothing
                        }
                        break;
                    case "CHRYSLER QA":
                        if (dealer.equals("CHRYSLER_QA")) {
                            sPage.searchKeyWord(vin);
                        } else {
                            //do nothing
                        }
                        break;
                }
            }
        }
    }

    @Then("^I see following VIN displayed on 'SEARCH' page with \"([^\"]*)\" dealer$")
    public void iVerifyVinOnDealerOnSearchPage(String dealerName, DataTable dataTable) {
        String vinSearchResult;
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String dealer = map.get("Dealer");
            String vin = map.get("VIN");
            if (StringUtils.isNotBlank(vin)) {
                switch (dealerName) {
                    case "Nissan of Anytown USA":
                        if (dealer.equals("Nissan of Anytown USA")) {
                            vinSearchResult = sPage.searchResult(vin, "Vehicle");
                            assertTrue(vinSearchResult.contains(vin));
                        } else {
                            //do nothing
                        }
                        break;
                    case "CHRYSLER QA":
                        if (dealer.equals("CHRYSLER_QA")) {
                            vinSearchResult = sPage.searchResult(vin, "Vehicle");
                            assertTrue(vinSearchResult.contains(vin));
                        } else {
                            //do nothing
                        }
                        break;
                }
            }
        }
    }

    @And("^I click \"([^\"]*)\" tab under Customer Name$")
    public void iClickSomeTabUnderCustomerName(String tabName) {
        switch (tabName) {
            case "CUSTOMER NOTES":
                sPage.clickOnCustomerNotesTabWithName();
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" window opened on 'SEARCH' page$")
    public void iVerifySomeWindowOpenedOnSearchPage(String windowName) {
        switch (windowName) {
            case "Service History":
                assertTrue("Service History window is not opened, test failed", sPage.isServiceHistoryWindowDisplayed());
                break;
        }
    }

    @And("^I see \"([^\"]*)\" from search result on 'SEARCH' page$")
    public void iSeeSomethingFromSearchResult(String resultSegment) {
        switch(resultSegment){
            case "VIN":
                vin = sPage.getVINFromResult();
                break;
        }
    } 

    @And("^I click \"([^\"]*)\" on expanded view$")
    public void iClickSomethingOnExpandedView(String elementName) {
        switch(elementName){
            case "TASKS":
                sPage.clickTasksTab();
                break;
            case "SERVICE HISTORY":
                sPage.clickServiceHistoryTab();
                break;
            case "first i":
                sPage.clickInfoIcon(0);
                break;
            case "first Campaign":
                sPage.clickCampaign(0);
                break;
        }
    }

    @And("^I see 'Service History' window have correct \"([^\"]*)\" under 'Vehicle Details'$")
    public void iVerifySomethingOnServiceHistoryWindow(String elementName) {
        switch(elementName){
            case "VIN":
                assertEquals("Wrong VIN displayed in Service History window, test failed", vin, sPage.getVINFromServiceHistoryWindow());
        }
    }

    @And("^I see following data linked with name contains \"([^\"]*)\" on 'SEARCH' page$")
    public void iSeeDataOnPage(String name, DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String iconName = map.get("IconName");
            String expectedResult = map.get("Value");
            String actualResult = "";
            if (StringUtils.isNotBlank(expectedResult)) {
                switch (iconName) {
                    case "HomeIcon":
                        actualResult = sPage.getHomeIconCustomerDetailsSearch(name);
                        assertEquals(expectedResult, actualResult);
                        break;
                    case "CellPhoneIcon":
                        actualResult = sPage.getCellPhoneIconCustomerDetailsSearch(name);
                        assertTrue(actualResult.contains(expectedResult));
                        break;
                }
            }
        }
    }
    
    @And("^I validate \"([^\"]*)\" on 'SEARCH' page$")
    public void iValidateVINOnSearchScreen(String VIN) {
    	sPage.validateVIN(VIN);
    }     
    
    @And("^I capture customer name on 'SEARCH' page$")
    public void iCaptureCustomerName() {
        sPage.captureCustomerName();
    }    

}