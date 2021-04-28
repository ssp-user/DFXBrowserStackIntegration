package com.autotest.teststeps.payment;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.taskdefs.Sleep;

import com.automation.pages.PageManager;
import com.automation.pages.payment.CreateInvoicePage;
import com.autotest.teststeps.BaseTestSteps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;

public class CreateInvoiceSteps extends BaseTestSteps {
	CreateInvoicePage createInvoicePage = new CreateInvoicePage();

	@And("^I manually add the customer details on 'Create Invoice' page$")
	public void iclickElementOnCreateInvoiceScreen() {
		createInvoicePage.click("HERE");
	}

	@And("^I set customer data on 'CREATE INVOICE' page$")
	public void iSetCustomerDataOnClientPage(DataTable dataTable) {
		List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> map : maps) {
			String fieldName = map.get("FieldName");
			String value = map.get("Value");
			if (StringUtils.isNotBlank(value)) {
				switch (fieldName) {
				case "Customer Name":
					createInvoicePage.inputCustomerName(value);
					break;
				case "Advisor":
					createInvoicePage.inputAdvisor(value);
					break;
				case "RO Number":
					createInvoicePage.inputRONumber(value);
					break;
				case "Invoice Amount":
					createInvoicePage.inputInvoiceAmount(value);
					break;
				case "Phone Number":
					createInvoicePage.inputPhoneNumber(value);
					break;
				case "Email":
					createInvoicePage.inputEmail(value);
					break;
				case "VIN":
					createInvoicePage.inputVIN(value);
					break;
				}
			}
		}
	}
		
	@And("^I choose Email option on 'CREATE INVOICE' page$")
	public void ichooseEmailOptionOnInvoiceScreen() {
		createInvoicePage.click("Email");
	}   

	@And("^I click Send payment request button on 'CREATE INVOICE' page$")
	public void iclickSendPaymentRequest() {
		createInvoicePage.click("Send Payment Request");
	} 	     

	@And("^I validate \"([^\"]*)\" on 'CREATE INVOICE' page$")
	public void iValidateInvoiceDetails(String invoicedetails) {
		createInvoicePage.validateInvoiceDetails(invoicedetails);
	} 	    
	
	@And("^I click Void button on 'CREATE INVOICE' page$")
	public void iClickVoidOnInvoiceScreen() {
		createInvoicePage.click("Void");
	}	
	
	@And("^I click Proceed to direct payment button on 'CREATE INVOICE' page$")
	public void iclickDirectPayment() {
		createInvoicePage.click("Proceed to direct payment");
	}
	
	@And("^I click View Receipt on 'CREATE INVOICE' page$")
	public void iViewTheReceipt() {
		createInvoicePage.click("View Receipt");
	} 

	@And("^I search customer by phone number \"(.*)\" on 'CREATE INVOICE' page$")
	public void isearchCustomerByPhoneNumberOnInvoiceScreen(String phoneNumber) {
		createInvoicePage.inputSearchCriteria(phoneNumber);
	} 	

	@And("^I choose First Record in search results on 'CREATE INVOICE' page$")
	public void ichooseFirstRecordInSearchResultsOnInvoiceScreen() {
		createInvoicePage.click("Select First Record");
	}  	
	
	@And("^I search customer by name \"(.*)\" on 'CREATE INVOICE' page$")
	public void isearchCustomerByNameOnInvoiceScreen(String name) {
		createInvoicePage.inputSearchCriteria(name);
	} 	
}