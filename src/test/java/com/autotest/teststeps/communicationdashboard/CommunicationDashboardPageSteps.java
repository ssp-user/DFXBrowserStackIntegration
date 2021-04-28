package com.autotest.teststeps.communicationdashboard;

import com.automation.pages.communicationdashboard.CommunicationDashboard;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;

public class CommunicationDashboardPageSteps extends BaseTestSteps {
	CommunicationDashboard cdPage = new CommunicationDashboard();

	@And("^I click \"([^\"]*)\" on 'Communication Dashboard' window$")
	public void iclickElementOnCommunicationDashBoardScreen(String element) {
		cdPage.click(element);
	}        
  	
	@And("I randomly select an advisor on 'Communication Dashboard' window")
	public void iRandomlySelectAdvisor() {
		cdPage.randomSelectionOfAdvisor();
	}    
	
	@And("^I send and validate \"([^\"]*)\" internal messages on 'Communication Dashboard' window$")
	public void iSendAndValidateInternalMessages(String count) {
		cdPage.sendAndValidateInternalMessages(Integer.parseInt(count));
	}     

	@And("^I enter \"([^\"]*)\" of the \"([^\"]*)\" on 'Communication Dashboard' window$")
	public void iSearchInternalOrExternalCustomer(String searchParameter, String searchType) {
		cdPage.searchInternalOrExternalCustomer(searchParameter, searchType);
	}  
	
	@And("^I send and validate \"([^\"]*)\" customer messages on 'Communication Dashboard' window$")
	public void iSendAndValidateCustomerMessages(String count) {
		cdPage.sendAndValidateCustomerMessages(Integer.parseInt(count));
	} 
	
	@And("^I enter RO on the 'Communication Dashboard' window$")
	public void iEnterRO() {
		cdPage.searchRO();
	} 	

	@And("^I validate pushed RO on 'Communication Dashboard' window$")
	public void iValidatePushedROOnCommunicationDashBoardScreen() {
		cdPage.validatePushedRO();
	} 	
	
	@And("^I validate internal message on 'Communication Dashboard' window$")
	public void iValidateInternalMessageOnCommunicationDashBoardScreen() {
		cdPage.validateInternalMessage();
	} 	   		
}