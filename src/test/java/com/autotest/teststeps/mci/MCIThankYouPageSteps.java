package com.autotest.teststeps.mci;

import com.automation.pages.dealerfx.HomePage;
import com.automation.pages.dealerfx.LoginPage;
import com.automation.pages.mci.MCIHomePage;
import com.automation.pages.mci.MCIThankYouPage;
import com.automation.utils.log.DfxLog;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.awt.*;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MCIThankYouPageSteps extends BaseTestSteps {
	MCIThankYouPage mciThankYouPage = new MCIThankYouPage();

	@And("^I click \"([^\"]*)\" document on 'Thank you' page$")
	public void iClickDocumentOnThankYouScreen(String documentName) {
		mciThankYouPage.clickMenu(documentName);
	}
   
	@Then("^I view \"([^\"]*)\" on 'Thank you' page$")
	public void iViewOnThankYouScreen(String element) {
		mciThankYouPage.verifyElementExists(element);
	}	
}