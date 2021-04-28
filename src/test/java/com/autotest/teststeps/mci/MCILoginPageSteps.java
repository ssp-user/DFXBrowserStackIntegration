package com.autotest.teststeps.mci;

import com.automation.pages.dealerfx.HomePage;
import com.automation.pages.dealerfx.LoginPage;
import com.automation.pages.mci.MCILoginPage;
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

public class MCILoginPageSteps extends BaseTestSteps {
	MCILoginPage mciLoginPage = new MCILoginPage();

	@And("^I enter confirmation code for the appointment on 'LOGIN' page$")
	public void iLoginDFXHomePageUserWithPassword() {
		mciLoginPage.enterConfirmationCode();
	}

	@And("^I click \"([^\"]*)\" button on 'LOGIN' page$")
	public void iclickButtonOnLoginScreen(String buttonName) {
		mciLoginPage.clickButton(buttonName);
	}
	
	@And("^I validate application in French language on 'LOGIN' page$")
	public void iValidateApplicationInFrench() {
		mciLoginPage.validateApplicationinFrench();
	}	

@Then ("I should click on continue button and see unable to check in message on 'LOGIN' page$")
   public void iverifyunabletocheck () {
mciLoginPage.unabletocheckin();	
	
}

@Then ("I should click on continue button and see not have enough info message on 'LOGIN' page$")

public void iverifynocheckinforvinless () {
mciLoginPage.verifynocheckinforvinless();	
	
}

}