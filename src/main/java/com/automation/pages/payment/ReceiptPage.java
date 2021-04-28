package com.automation.pages.payment;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;

public class ReceiptPage extends WebPage {

	public void validateReceiptPage(String message) {
		switch (message.toUpperCase()) {
		case "RECEIPT DETAILS":
			Assert.assertTrue(findElement("$.DFX_PAYMENTS.RECEIPT_PAGE.lbl_amount").getText().trim().substring(1).equals(CreateInvoicePage.invoiceAmount), "Validate Invoice Amount");
			Assert.assertTrue(findElement("$.DFX_PAYMENTS.RECEIPT_PAGE.lbl_receiptHeading").getText().trim().contains("Receipt from"),"Validate receipt header");
			Assert.assertTrue(findElement("$.DFX_PAYMENTS.RECEIPT_PAGE.lbl_amountCharged").getText().trim().substring(1).equals(CreateInvoicePage.invoiceAmount), "Validate Charged Amount");			
			break;
		default:
			return;
		}
	}

}
