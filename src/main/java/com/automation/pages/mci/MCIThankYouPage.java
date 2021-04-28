package com.automation.pages.mci;

import com.automation.pages.PageManager;
import com.automation.pages.customer_connect.CC_DashboardPage;
import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.pages.common.WebPage;

public class MCIThankYouPage extends WebPage {

	public void clickMenu(String name) {
		sleep(10000);
		switch (name.toUpperCase()) {
		case "VIEW MY MAINTENANCE MENU":
			pWait.until(conditionVisible(findElement("$.DFX_MCI.thank_lnk_maintenance"))).click();
			break;
		case "VIEW MY PRE-SERVICE WRITE-UP":
			pWait.until(conditionVisible(findElement("$.DFX_MCI.thank_lnk_preservice"))).click();
			break;		
		default:
			return;
		}
	}
	
	public void verifyElementExists(String element) {
		sleep(20000);		
		switch (element.toUpperCase()) {
		case "VIEW MY MAINTENANCE MENU":
			CommonMethods.verifyElementExists(findElement("$.DFX_MCI.thank_lnk_maintenance"));
			break;	
		default:
			return;
		}		
	}
}


