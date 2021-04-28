package com.autotest.teststeps.payment;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.automation.pages.payment.GuestPayment;
import com.autotest.teststeps.BaseTestSteps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;

public class GuestPaymentSteps extends BaseTestSteps {
	GuestPayment guestPayment = new GuestPayment();
	
	@And("^I click \"([^\"]*)\" on 'Guest Payment' page$")
	public void iclickElementOnGuestPaymentScreen(String element) {
		guestPayment.click(element);
	}   
	
	@And("^I validate \"([^\"]*)\" on 'Guest Payment' page$")
	public void iValidateNotificationOnGuestPaymentScreen(String validationType) {
		guestPayment.validateGuestPaymentPage(validationType);
	}
	
	@And("^I enter card details on 'Guest Payment' page$")
	public void iEnterCardDetailsOnGuestPaymentScreen(DataTable dataTable) {
		List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> map : maps) {
			String fieldName = map.get("FieldName");
			String value = map.get("Value");
			if (StringUtils.isNotBlank(value)) {
				guestPayment.inputFieldValue(fieldName,value);
			}
		}
	}	
	

	
}