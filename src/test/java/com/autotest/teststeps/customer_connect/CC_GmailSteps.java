package com.autotest.teststeps.customer_connect;

import com.automation.pages.PageManager;
import com.automation.pages.third_party.GmailPage;
import com.automation.pages.third_party.YahooPage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class CC_GmailSteps extends BaseTestSteps {

    GmailPage gmailPage = new GmailPage();
    YahooPage yahooPage = new YahooPage();

    @Then("^I click Email associated with \"([^\"]*)\" on 'Gmail' page$")
    public void iClickEmailOnGmailPage(String dealerName) {
        gmailPage.clickOnAssociatedEmail(dealerName);
    }

    @Then("^I see new email with subject as 'TEST random' in Gmail inbox$")
    public void iSeeNewEmailWithSubjectAsTESTRandomInGmailInbox() {
        gmailPage.clickOnEmailTESTRandom();
        Assert.assertTrue(gmailPage.senderAddress());
    }

    @And("^I delete new email with subject as 'TEST random'$")
    public void iDeleteGmail() {
        gmailPage.deleteGmail();
    }

    @Then("^I see new email with subject as 'Appointment Cancellation Confirmation' in Yahoo inbox$")
    public void iSeeNewEmailWithSubjectAsTESTRandomInYahooInbox() {
    	yahooPage.clickOnEmailTESTRandom();
        Assert.assertTrue(yahooPage.senderAddress());
    }   

    @Then("^I validate email contains customer name and booking time$")
    public void iSeeEmailContents() {
    	yahooPage.validateEmailContents();
    }    

    @Then("^I see delete the email$")
    public void iDeleteEmail() {
    	yahooPage.clickDeleteButton();
    }   
        
    @Then("^I signout for the application$")
    public void iSignOutFromEmail() {
    	yahooPage.signOutEmail();
    }     

    @Then("^I see new email from \"([^\"]*)\" in Yahoo inbox$")
    public void iSeeNewEmailInYahooInbox(String dealerName) {
    	yahooPage.clickEmail(dealerName.toUpperCase());
        Assert.assertTrue(yahooPage.senderAddress());
    }    
    
    @Then("^I validate email contains attachment$")
    public void iSeeEmailAttachment() {
    	yahooPage.validateAttachment();
    }    
    
    @Then("^I see new email for mobile checkin in Yahoo inbox$")
    public void iSeeNewEmailForMobileCheckIn() {
    	yahooPage.clickMCIEmail();
        Assert.assertTrue(yahooPage.senderAddress());
    }      

    @Then("^I click Check in button$")
    public void iClickCheckInButton() {
    	yahooPage.clickCheckInButton();
    	PageManager.switchWindow();
    }  

    @Then("^I see new email for payment completion in Yahoo inbox$")
    public void iSeeNewEmailForPaymentCompletion() {
    	yahooPage.clickPaymentEmail();
    }  
    
    @Then("^I click Make an Online Payment button$")
    public void iClickPaymentButton() {
    	yahooPage.clickMakeOnlinePaymentButton();
    	PageManager.switchWindow();
    }      

    @Then("^I see new email for customer connect in Yahoo inbox$")
    public void iSeeNewEmailForCustomerConnect() {
    	yahooPage.clickCCEmail();
    }      
}





