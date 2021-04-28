package com.automation.pages.payment;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;

public class PaymentHomePage extends WebPage {

	public static String preferredLanguage = null;	
	
	public static String getPreferredLanguage() {
		return preferredLanguage;
	}

	public static void setPreferredLanguage(String preferredLanguage) {
		PaymentHomePage.preferredLanguage = preferredLanguage;
	}

	public void click(String name) {
		dWait.until(ExpectedConditions.visibilityOfElementLocated(findLocator("$.DFX_PAYMENTS.HOME_PAGE.lnk_createinvoice")));		
		switch (name.toUpperCase()) {
		case "CREATE INVOICE":
			findElement("$.DFX_PAYMENTS.HOME_PAGE.lnk_createinvoice").click();
			dWait.until(ExpectedConditions.visibilityOfElementLocated(findLocator("$.DFX_PAYMENTS.CREATE_INVOICE.txt_customsearch")));
			break;
		case "CLOSE FOOTER NOTIFICATION":
			findElement("$.DFX_PAYMENTS.HOME_PAGE.footer_notification_close").click();
			break;
		case "SEARCH RESULTS":
			sleep(2000);
			findElement("$.DFX_PAYMENTS.HOME_PAGE.tbl_row").click();
			dWait.until(ExpectedConditions.visibilityOfElementLocated(findLocator("$.DFX_PAYMENTS.CREATE_INVOICE.txt_customer")));
			sleep(5000);			
			break;
		case "CLEAR FILTER":
			findElement("$.DFX_PAYMENTS.HOME_PAGE.lnk_clearFilter").click();
			dWait.until(ExpectedConditions.visibilityOfElementLocated(findLocator("$.DFX_PAYMENTS.HOME_PAGE.lnk_createinvoice")));			
			break;			
		default:
			return;
		}
	}

	public void validatePaymentPage(String message) {
		switch (message.toUpperCase()) {
		case "SUCCESSFUL SENT INVOICE NOTIFICATION":
			CommonMethods.verifyElementExists(findElement("$.DFX_PAYMENTS.HOME_PAGE.footer_notification"));
			String notificationText = findElement("$.DFX_PAYMENTS.HOME_PAGE.footer_notification").getText().trim();
			Assert.assertTrue(notificationText.contains("Invoice of"),"Pre script - Validate footer text for sent invoice");
			Assert.assertTrue(notificationText.contains("is successfully sent."),"Post script - Validate footer text for sent invoice");
			break;
		case "INVOICE DETAILS":
			Assert.assertTrue(findElement("$.DFX_PAYMENTS.HOME_PAGE.tbl_ro").getText().trim().equals(CreateInvoicePage.roNumber),"Validate RO Number");
			Assert.assertTrue(findElement("$.DFX_PAYMENTS.HOME_PAGE.tbl_amount").getText().trim().substring(1).equals(CreateInvoicePage.invoiceAmount),"Validate Invoice Amount");
			Assert.assertTrue(findElement("$.DFX_PAYMENTS.HOME_PAGE.tbl_status").getText().trim().equals(CreateInvoicePage.status),"Validate Payment Status");			
			break;	
		case "VOID INVOICE NOTIFICATION":
			CommonMethods.verifyElementExists(findElement("$.DFX_PAYMENTS.HOME_PAGE.footer_notification"));
			String voidText = findElement("$.DFX_PAYMENTS.HOME_PAGE.footer_notification").getText().trim();
			Assert.assertTrue(voidText.contains("Invoice of"),"Pre script - Validate footer text for void invoice");
			Assert.assertTrue(voidText.contains("is successfully voided."),"Post script - Validate footer text for void invoice");
			break;	
		case "SEND INVOICE NOTIFICATION":
			CommonMethods.verifyElementExists(findElement("$.DFX_PAYMENTS.HOME_PAGE.footer_notification"));
			String sendText = findElement("$.DFX_PAYMENTS.HOME_PAGE.footer_notification").getText().trim();
			Assert.assertTrue(sendText.contains("Invoice of"),"Pre script - Validate footer text for ready to send invoice");
			Assert.assertTrue(sendText.contains("is ready to send."),"Post script - Validate footer text for ready to send invoice");
			break;	
		case "STATUS FILTER INVOICE DETAILS":
			Assert.assertTrue(findElement("$.DFX_PAYMENTS.HOME_PAGE.tbl_status").getText().trim().equals(CreateInvoicePage.status),"Validate Payment Status");			
			break;	
		case "ADVISOR FILTER INVOICE DETAILS":
			Assert.assertTrue(findElement("$.DFX_PAYMENTS.HOME_PAGE.tbl_serviceAdvisor").getText().trim().equals(CreateInvoicePage.advisor),"Validate Advisor Name");			
			break;	
		case "DATE FILTER INVOICE DETAILS":
			String dateOnUI = findElement("$.DFX_PAYMENTS.HOME_PAGE.tbl_serviceDate").getText().trim();
			String CurrentDate = CommonMethods.returnDate(0, "MMM dd yyyy");
			int length = dateOnUI.length();
			int indexOfComma = dateOnUI.indexOf(',');
			int indexOfSpace = dateOnUI.indexOf(' ');
			Assert.assertTrue(dateOnUI.substring(0, 2).equals(CurrentDate.substring(0, 2)),"Validate Invoice Month");			
			Assert.assertTrue(dateOnUI.substring(length-4, length-1).equals(CurrentDate.substring(7, 10)),"Validate Invoice Year");
			Assert.assertTrue(Integer.parseInt(dateOnUI.substring(indexOfSpace+1, indexOfComma)) == Integer.parseInt(CurrentDate.substring(4, 6)),"Validate Invoice Day");			
			break;		
		case "SUCCESSFUL SENT FRENCH INVOICE NOTIFICATION":
			CommonMethods.verifyElementExists(findElement("$.DFX_PAYMENTS.HOME_PAGE.footer_notification"));
			String frenchNotificationText = findElement("$.DFX_PAYMENTS.HOME_PAGE.footer_notification").getText().trim();
			Assert.assertTrue(frenchNotificationText.contains("La facture d’un montant de"),"Pre script - Validate footer text for sent invoice in french language");
			Assert.assertTrue(frenchNotificationText.contains("a bien le statut envoyé."),"Post script - Validate footer text for sent invoice in french language");
			break;		
		case "INVOICE DETAILS IN FRENCH":
			Assert.assertTrue(findElement("$.DFX_PAYMENTS.HOME_PAGE.tbl_ro").getText().trim().equals(CreateInvoicePage.roNumber),"Validate RO Number");
			// -- To update for french locale(., issue) To investigate for valid bug Assert.assertTrue(findElement("$.DFX_PAYMENTS.HOME_PAGE.tbl_amount").getText().trim().substring(1).equals(CreateInvoicePage.invoiceAmount),"Validate Invoice Amount");
			Assert.assertTrue(findElement("$.DFX_PAYMENTS.HOME_PAGE.tbl_status").getText().trim().equals("Envoyé"),"Validate Payment Status");			
			break;			
		default:
			return;
		}
	}

	public void enterInSearchField(String searchParameter) {
		dWait.until(ExpectedConditions.visibilityOfElementLocated(findLocator("$.DFX_PAYMENTS.HOME_PAGE.lnk_createinvoice")));		
		String value = "";
		switch (searchParameter.toUpperCase()) {
		case "RO NUMBER":
			value = CreateInvoicePage.roNumber;
			break;
		default:
			return;
		}
		findElement("$.DFX_PAYMENTS.HOME_PAGE.txt_search").clear();
		findElement("$.DFX_PAYMENTS.HOME_PAGE.txt_search").sendKeys(value);
		sleep(5000);
	}

	public void enterInFilterCriteria(String searchParameter) {
		dWait.until(ExpectedConditions.visibilityOfElementLocated(findLocator("$.DFX_PAYMENTS.HOME_PAGE.lnk_createinvoice")));		
		String value = "";
		switch (searchParameter.toUpperCase()) {
		case "STATUS":
			value = CreateInvoicePage.status;
			findElement("$.DFX_PAYMENTS.HOME_PAGE.lst_status").clear();
			findElement("$.DFX_PAYMENTS.HOME_PAGE.lst_status").sendKeys(value);
			findElement("$.DFX_PAYMENTS.HOME_PAGE.lst_status").sendKeys(Keys.ENTER);				
			break;
		case "ADVISOR":
			value = CreateInvoicePage.advisor;
			findElement("$.DFX_PAYMENTS.HOME_PAGE.lst_advisor").clear();
			findElement("$.DFX_PAYMENTS.HOME_PAGE.lst_advisor").sendKeys(value);
			findElement("$.DFX_PAYMENTS.HOME_PAGE.lst_advisor").sendKeys(Keys.ENTER);				
			break;		
		case "CUSTOMISED DATE FILTER":
			findElement("$.DFX_PAYMENTS.HOME_PAGE.txt_dateRange").clear();
			findElement("$.DFX_PAYMENTS.HOME_PAGE.txt_dateRange").sendKeys(CommonMethods.returnDate(-1, "yyyy MMM dd") + " - " + CommonMethods.returnDate(0,"yyyy MMM dd"));
			findElement("$.DFX_PAYMENTS.HOME_PAGE.txt_dateRange").sendKeys(Keys.ENTER);				
			break;	
		case "LANGUAGE":
			value = PaymentHomePage.getPreferredLanguage();
			findElement("$.DFX_PAYMENTS.HOME_PAGE.lst_language").clear();
			findElement("$.DFX_PAYMENTS.HOME_PAGE.lst_language").sendKeys(value);
			findElement("$.DFX_PAYMENTS.HOME_PAGE.lst_language").sendKeys(Keys.ENTER);				
			break;			
		default:
			return;
		}
		sleep(3000);
	}	
}
