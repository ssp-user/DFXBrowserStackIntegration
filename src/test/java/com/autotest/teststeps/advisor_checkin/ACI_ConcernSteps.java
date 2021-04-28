package com.autotest.teststeps.advisor_checkin;

import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import com.automation.pages.advisor_checkin.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ACI_ConcernSteps extends BaseTestSteps {

    ACI_ConcernsPage aconPage = new ACI_ConcernsPage();

    private static Logger log = Logger.getLogger(ACI_ConcernSteps.class);

    @And("^I add following customer concerns on 'CONCERN' page$")
    public void addConcerns(DataTable dataTable) throws Throwable{
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String concernName = map.get("Concern name");
            aconPage.concernItem(concernName);
        }
    }

    @And("^I add \"([^\"]*)\" customer concerns on 'CONCERN' page$")
    public void addCustomerConcerns(String concernName) {
        aconPage.concernItem(concernName);
    }


    @And("^I click \"([^\"]*)\" button on 'CONCERN' page$")
    public void iClickButtonOnConcernPage(String buttonName) {
        CommonMethods.sleep(1000);
        aconPage.waitForPageToLoad();
        switch (buttonName) {
            case "NEXT":
                aconPage.clickNextOnConcern();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" tab on 'CUSTOMER CONCERN' page$")
    public void iClickSomeTabOnCustomerConcernsPage(String tabName) {
        String name = tabName.toUpperCase();
        switch (name) {
            case "CUSTOMER":
                aconPage.goToCustomerTab();
                break;
            case "CONCERNS":
                aconPage.goToConcernsTab();
                break;
            case "HISTORY":
                aconPage.goToHistoryTab();
                break;
            case "SERVICES":
                aconPage.goToServiceTab();
                break;
            case "WALK AROUND":
                aconPage.goToWalkRoundTab();
                break;
            case "ASSIGN R.O.":
                aconPage.goToAssignROTab();
                break;
            case "AUTHORIZATION":
                aconPage.goToAuthorizationTab();
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" tab is activated on 'CUSTOMER CONCERN' page$")
    public void iSeeSomeTabActivated(String tabName) {
        Assert.assertTrue("Verify CONCERNS tab is activated failed!", aconPage.verifyTabActivated(tabName));
    }

    @And("^I validate \"([^\"]*)\" row on 'CONCERN' page$")
    public void iValidateSymptomSurvery(String symptomSurvey) {
    	aconPage.validateSymptomSurvey(symptomSurvey);
    }	
    
    @And("^I delete \"([^\"]*)\" row on 'CONCERN' page$")
    public void iDeleteSymptomSurvery(String symptomSurvey) {
    	aconPage.deleteSymptomSurvey(symptomSurvey);
    }		
	
	
}
