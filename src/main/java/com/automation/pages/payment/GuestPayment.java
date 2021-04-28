package com.automation.pages.payment;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;

public class GuestPayment extends WebPage {

	public void validateGuestPaymentPage(String message) {
		switch (message.toUpperCase()) {
		case "RO NUMBER":
			Assert.assertTrue(findElement("$.DFX_PAYMENTS.GUEST_PAYMENT.lbl_ro").getText().trim().substring(11).equals(CreateInvoicePage.roNumber), "Validate RO Number on payment screen.");
			break;
		case "SUCCESSFUL PAYMENT":
			Assert.assertTrue(findElement("$.DFX_PAYMENTS.GUEST_PAYMENT.lbl_paymentStatusMessage").getText().trim().equals("Your payment is successful!"), "Validate successful payment message.");
			break;
		case "ALREADY PAID":
			Assert.assertTrue(findElement("$.DFX_PAYMENTS.GUEST_PAYMENT.lbl_paymentStatusMessage").getText().trim().equals("This invoice has already been paid for."), "Validate duplicate payment message.");
			break;			
		default:
			return;
		}
	}

	public void click(String name) {
		switch (name.toUpperCase()) {
		case "PROCEED TO PAYMENT":
			findElement("$.DFX_PAYMENTS.GUEST_PAYMENT.btn_proceedToPayment").click();
			dWait.until(ExpectedConditions.visibilityOfElementLocated(findLocator("$.DFX_PAYMENTS.GUEST_PAYMENT.txt_cardNumber")));
			break;
		case "PAY":
			findElement("$.DFX_PAYMENTS.GUEST_PAYMENT.btn_pay").click();
			sleep(12000);
			CreateInvoicePage.status = "Paid";			
			dWait.until(ExpectedConditions.visibilityOfElementLocated(findLocator("$.DFX_PAYMENTS.GUEST_PAYMENT.lbl_paymentStatusMessage")));
			break;
		default:
			return;
		}
	}

	public void inputFieldValue(String field, String value) {
		String locator = "";
		switch (field.trim().toUpperCase()) {
		case "CARD NUMBER":
			locator = "$.DFX_PAYMENTS.GUEST_PAYMENT.txt_cardNumber";
			enterCardNumber(findLocator(locator), value);
			break;
		case "CARD EXPIRY":
			locator = "$.DFX_PAYMENTS.GUEST_PAYMENT.txt_cardExpiry";
			clearAndInputElementWithException(findLocator(locator),value);			
			break;
		case "CVC":
			locator = "$.DFX_PAYMENTS.GUEST_PAYMENT.txt_cardCVC";
			clearAndInputElementWithException(findLocator(locator),value);			
			break;
		case "NAME":
			locator = "$.DFX_PAYMENTS.GUEST_PAYMENT.txt_nameOnCard";
			clearAndInputElementWithException(findLocator(locator),value);			
			break;
		case "COUNTRY":
			locator = "$.DFX_PAYMENTS.GUEST_PAYMENT.select_country";
			selectDropList(findLocator(locator), value);			
			break;
		case "POSTAL CODE":
			locator = "$.DFX_PAYMENTS.GUEST_PAYMENT.txt_postalCode";
			clearAndInputElementWithException(findLocator(locator),value);			
			break;			
		default:
			return;
		}
			
	}

}
