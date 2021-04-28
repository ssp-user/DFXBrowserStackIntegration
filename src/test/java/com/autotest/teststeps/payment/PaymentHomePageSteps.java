package com.autotest.teststeps.payment;

import com.automation.pages.payment.PaymentHomePage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;

public class PaymentHomePageSteps extends BaseTestSteps {
	PaymentHomePage paymentHomePage = new PaymentHomePage();

	@And("^I click \"([^\"]*)\" on 'Payment' page$")
	public void iclickElementOnPaymentScreen(String element) {
		paymentHomePage.click(element);
	}   
      
	@And("^I validate \"([^\"]*)\" on 'Payment' page$")
	public void iValidatePaymentScreen(String message) {
		paymentHomePage.validatePaymentPage(message);
	}
	
	@And("^I search invoice by \"([^\"]*)\" on 'Payment' page$")
	public void iSearchInvoiceOnPaymentScreen(String searchParameter) {
		paymentHomePage.enterInSearchField(searchParameter);
	}
	
	@And("^I filter Invoice by \"([^\"]*)\" on 'Payment' page$")
	public void iFilterInvoiceByDifferentParameters(String searchParameter) {
		paymentHomePage.enterInFilterCriteria(searchParameter);
	}	
	 
    @And("^I choose \"(.*)\" as \"(.*)\" on 'Payment' page$")
    public void iChoosePreferredLanguageFromPaymentHomePage(String preferredlanguage,String language) {
    	PaymentHomePage.setPreferredLanguage(preferredlanguage.trim());
    	paymentHomePage.enterInFilterCriteria(language);
    }    	
	
}