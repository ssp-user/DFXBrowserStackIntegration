package com.autotest.teststeps.appointment_manager;

import com.automation.pages.appointment_manager.AM_ClientAndVehiclePage;
import com.automation.pages.appointment_manager.AM_HistoryPage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;

public class AM_HistorySteps extends BaseTestSteps {

    private static Logger log = Logger.getLogger(AM_HistorySteps.class);

    AM_HistoryPage page = new AM_HistoryPage();
    AM_ClientAndVehiclePage cnvPage = new AM_ClientAndVehiclePage();    

    @And("^I click \"([^\"]*)\" button if on 'HISTORY' page in AM$")
    public void iClickButtonIfOnHistory(String buttonName) {
        switch (buttonName) {
            case "NEXT":
                if (page.isHistoryDisplayed()) {
                    page.clickNextBtn();
                }
                break;
            case "CREATE NEW CUSTOMER":
                page.clickCreateNewCustomerBtn();
                break;   
            case "UPDATE EXISTING CUSTOMER":
                page.clickUpdateExistingCustomerBtn();
                break;                 
        }
    }

    @And("^I click \"([^\"]*)\" button on 'HISTORY' page in AM$")
    public void iClickButtonOnHistory(String buttonName) {
        switch (buttonName) {
            case "NEXT":
                page.clickNextBtn();
                break;
        }
    }


    @And("^I goto \"([^\"]*)\" tab on 'HISTORY' page in AM$")
    public void iClickTabAMOnHistory(String name) {
        page.goToTheTabInAM(name);
    }

    @And("^I goto \"([^\"]*)\" tab in AM$")
    public void iClickTabInAM(String name) {
        page.goToTheTabInAM(name);
    }

    @And("^I verify message \"([^\"]*)\" on 'HISTORY' page in AM$")
    public void iVerifyMessageonHistory(String message) {
    	System.out.println("Value of message is "+ message);
    	if (message.equals("Entered customer information matched other customer(s). What do you want to do?")) {
			message = "DuplicateCustomer";
		}
        page.verifyElementExists(message);
    }

    @And("^I verify message \"([^\"]*)\" on 'HISTORY' page in AM if Individual Customer$")
    public void iVerifyMessageonHistoryIfIndividualCustomer(String message) {
    	try {
        	if(!cnvPage.isBusinessCustomer()) {
        		iVerifyMessageonHistory(message);    		
        	}	
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("Handling Business customer scenario---NO action required");			
		}
    	

    }
    
    @And("^I click \"([^\"]*)\" button if on 'HISTORY' page in AM if Individual Customer$")
    public void iClickButtonIfOnHistoryIfIndividualCustomer(String buttonName) {
    	try {
        	if(!cnvPage.isBusinessCustomer()) {
        		iClickButtonIfOnHistory(buttonName); 		
        	}  
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("Handling Business customer scenario---NO action required");						
		}

    }    
    
}

