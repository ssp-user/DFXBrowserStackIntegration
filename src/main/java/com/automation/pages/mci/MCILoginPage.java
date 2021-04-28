package com.automation.pages.mci;

import com.automation.pages.PageManager;
import com.automation.pages.appointment_manager.AM_DashboardPage;
import com.automation.pages.customer_connect.CC_DashboardPage;
import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;


import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.automation.pages.common.WebPage;

public class MCILoginPage extends WebPage {

    public void enterConfirmationCode(){    	
        clearAndSend(findElement("$.DFX_MCI.login_txt_confirmationcode"), AM_DashboardPage.appointmentNumber.substring(4));        
    }

	public void clickButton(String name) {
		switch (name.toUpperCase()) {
		case "CONTINUE":
			pWait.until(conditionVisible(findElement("$.DFX_MCI.login_btn_continue"))).click();
			dWait.until(ExpectedConditions.invisibilityOfElementLocated(findLocator("$.DFX_MCI.login_spinner")));
			break;
		default:
			return;
		}
	}
	
    public void validateApplicationinFrench(){
		sleep(5000);
        CommonMethods.verifyElementExists(findElement("$.DFX_MCI.login_img_language_fr"));
        CommonMethods.verifyElementExists(findElement("$.DFX_MCI.login_btn_continue_fr"));        
    }	

	public void unabletocheckin() {
		pWait.until(conditionVisible(findElement("$.DFX_MCI.login_btn_continue"))).click();
		sleep(10000);
		pWait.until(conditionVisible(findElement("$.DFX_MCI.login_Unabletocheckintext")));
		String actualString = findElement("$.DFX_MCI.login_Unabletocheckintext").getText();
		Assert.assertTrue(actualString.contains("unable check you in"));
		
		// TODO Auto-generated method stub
		
	}

	public void verifynocheckinforvinless() {
		pWait.until(conditionVisible(findElement("$.DFX_MCI.login_btn_continue"))).click();
		sleep(10000);
		pWait.until(conditionVisible(findElement("$.DFX_MCI.login_VinlessCustomertext")));
		String actualString = findElement("$.DFX_MCI.login_VinlessCustomertext").getText();
		Assert.assertTrue(actualString.contains("not have enough information"));
		
	}

}


