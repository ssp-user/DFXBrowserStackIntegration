package com.automation.pages.payment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;

public class CreateInvoicePage extends WebPage {

	public static String roNumber,invoiceAmount,status, advisor = null;	
	
	public void click(String name) {
		switch (name.toUpperCase()) {
		case "HERE":
			findElement("$.DFX_PAYMENTS.CREATE_INVOICE.lnk_manualadd").click();
			dWait.until(ExpectedConditions.visibilityOfElementLocated(findLocator("$.DFX_PAYMENTS.CREATE_INVOICE.txt_customer")));
			break;
		case "EMAIL":
			findElement("$.DFX_PAYMENTS.CREATE_INVOICE.chk_email").click();
			sleep(2000);
			break;	
		case "SEND PAYMENT REQUEST":
			findElement("$.DFX_PAYMENTS.CREATE_INVOICE.btn_sendPaymentRequest").click();
			status = "Sent";
			dWait.until(ExpectedConditions.visibilityOfElementLocated(findLocator("$.DFX_PAYMENTS.HOME_PAGE.footer_notification")));
			break;
		case "VOID":
			findElement("$.DFX_PAYMENTS.CREATE_INVOICE.btn_void").click();
			status = "Void";
			sleep(3000);
			findElement("$.DFX_PAYMENTS.CREATE_INVOICE.btn_voidInvoice").click();			
			dWait.until(ExpectedConditions.visibilityOfElementLocated(findLocator("$.DFX_PAYMENTS.HOME_PAGE.footer_notification")));
			break;	
		case "PROCEED TO DIRECT PAYMENT":
			findElement("$.DFX_PAYMENTS.CREATE_INVOICE.btn_proceedDirectPayment").click();
			sleep(3000);
			status = "Ready to Send";
			break;	
		case "VIEW RECEIPT":
			findElement("$.DFX_PAYMENTS.CREATE_INVOICE.btn_viewReceipt").click();
			sleep(2000);
			break;	
		case "SELECT FIRST RECORD":
			findElement("$.DFX_PAYMENTS.CREATE_INVOICE.element_customer").click();
			sleep(1000);
			break;				
		default:
			return;
		}
	}

	public void inputCustomerName(String customerName) {
		if (customerName.toLowerCase().equals("random")) {
			customerName = CommonMethods.getRandomText(6) + " " + CommonMethods.getRandomText(4);
		}
		clearAndInputElementWithException(findLocator("$.DFX_PAYMENTS.CREATE_INVOICE.txt_customer"), customerName);
	}

	public void inputRONumber(String roNumber) {
		if (roNumber.toLowerCase().equals("random")) {
			roNumber = CommonMethods.getRandomText(5);
			CreateInvoicePage.roNumber = roNumber;
		}
		clearAndInputElementWithException(findLocator("$.DFX_PAYMENTS.CREATE_INVOICE.txt_ronumber"), roNumber);
	}

	public void inputInvoiceAmount(String invoiceAmount) {
		clearAndInputElementWithException(findLocator("$.DFX_PAYMENTS.CREATE_INVOICE.txt_invoiceamount"),invoiceAmount);
		CreateInvoicePage.invoiceAmount = invoiceAmount;
	}

	public void inputPhoneNumber(String phone) {
		if (phone.toLowerCase().equals("random")) {
			phone = CommonMethods.getRandomNumber(10);
		}
		clearAndInputElementWithException(findLocator("$.DFX_PAYMENTS.CREATE_INVOICE.txt_phonenumber"), phone);
	}

	public void inputEmail(String email) {
		clearAndInput(findLocator("$.DFX_PAYMENTS.CREATE_INVOICE.txt_email"), email);
	}

	public void inputVIN(String vin) {
		clearAndInput(findLocator("$.DFX_PAYMENTS.CREATE_INVOICE.txt_vin"), vin);
		clickElementWithException(findLocator("$.DFX_PAYMENTS.CREATE_INVOICE.btn_decode"));
		sleep(5000);
	}

	public void inputAdvisor(String value) {
		sleep(1500);
		By field = findLocator("$.DFX_PAYMENTS.CREATE_INVOICE.slct_advisor");
		if (driver.findElements(field).size() > 0) {
			int idx = 0;
			sleep(1500);			
			driver.findElement(field).click();
			sleep(5000);
			List<WebElement> elements = driver.findElements(findLocator("$.DFX_PAYMENTS.CREATE_INVOICE.slct_advisor_options"));
			if (value.equalsIgnoreCase("Random")) {
				int size = elements.size();
				if (size > 0) {
					idx = CommonMethods.randomNum(1, size) - 1;
					CreateInvoicePage.advisor = elements.get(idx).getText().trim();
					System.out.println("Advisor selected for selection is "+ CreateInvoicePage.advisor);
					pWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@class='custom-select']/option[text()='"+CreateInvoicePage.advisor+"']")));					
					driver.findElement(field).sendKeys(CreateInvoicePage.advisor);
				}
			} else {
				CreateInvoicePage.advisor = value.trim();				
				driver.findElement(field).sendKeys(value.trim());
			}
		} else {
			//To do
		}			
	}
	
	public void validateInvoiceDetails(String searchParameter) {
		Assert.assertTrue(findElement("$.DFX_PAYMENTS.CREATE_INVOICE.txt_ronumber").getText().trim().equals(CreateInvoicePage.roNumber),"Validate RO Number");
		Assert.assertTrue(findElement("$.DFX_PAYMENTS.CREATE_INVOICE.txt_invoiceamount").getText().trim().equals(CreateInvoicePage.invoiceAmount),"Validate Invoice Amount");
	}	
	
	public void inputSearchCriteria(String criteria) {
		clearAndInputElementWithException(findLocator("$.DFX_PAYMENTS.CREATE_INVOICE.txt_customsearch"), criteria);
		sleep(1500);
	}	

}
