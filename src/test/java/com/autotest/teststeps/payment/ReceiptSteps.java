package com.autotest.teststeps.payment;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.automation.pages.payment.GuestPayment;
import com.automation.pages.payment.ReceiptPage;
import com.autotest.teststeps.BaseTestSteps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;

public class ReceiptSteps extends BaseTestSteps {
	ReceiptPage receiptPage = new ReceiptPage();

	@And("^I validate \"([^\"]*)\" on 'Receipt' page$")
	public void iValidateReceiptPage(String validationType) {
		receiptPage.validateReceiptPage(validationType);
	}	
	
}