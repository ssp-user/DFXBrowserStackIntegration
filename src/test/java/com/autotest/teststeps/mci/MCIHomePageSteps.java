package com.autotest.teststeps.mci;

import com.automation.pages.dealerfx.HomePage;
import com.automation.pages.dealerfx.LoginPage;
import com.automation.pages.mci.MCIHomePage;
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

public class MCIHomePageSteps extends BaseTestSteps {
	MCIHomePage mciHomePage = new MCIHomePage();

	@And("^I click \"([^\"]*)\" button on 'HOME' page$")
	public void iclickButtonOnLoginScreen(String buttonName) {
		mciHomePage.clickButton(buttonName);
	}

	@And("^I scroll to the bottom of the 'HOME' page$")
	public void iScrollToTheBottom() {
		CommonMethods.scrollTotheBottomofWebPage();
	}

	@And("^I mark my signature on thanks message on 'HOME' page$")
	public void iMarkMySignature() {
		mciHomePage.markSignature();
	}     
	
	@And("^I validate captured services on 'HOME' page$")
	public void iValidateCapturedServicesOnMCIHomeScreen() {
		mciHomePage.validateFactoryServices();
	}
	
	@And("^I validate default total amount on 'HOME' page$")    
	public void iValidateDefaultTotalAmountOnMCIHomeScreen() {
		mciHomePage.captureDefaultTotal();
	}    

	@And("^I validate new total amount on 'HOME' page$")    
	public void iValidateNewTotalAmountOnMCIHomeScreen() {
		mciHomePage.captureNewTotal();
	}
	
	@And("^I add recommended service on 'HOME' page$")    
	public void iAddRecommendedServiceOnHomeScreen() {
		mciHomePage.clickRecommendedService();
	}
	
	@And("^I validate captured services on Shopping Cart 'HOME' page$")
	public void iValidateCapturedServicesOnShoppingCart() {
		mciHomePage.validateFactoryServicesOnShoppingCart();
	}
	
	@And("^I add recommended services from cart on 'HOME' page$")    
	public void iAddRecommendedServiceFromCartOnHomeScreen() {
		mciHomePage.clickRecommendedServiceFromCart();
	}	
}