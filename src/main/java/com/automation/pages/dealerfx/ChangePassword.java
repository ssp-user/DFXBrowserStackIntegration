package com.automation.pages.dealerfx;

import com.automation.pages.common.WebPage;
import com.automation.utils.PageActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ChangePassword extends WebPage {

    private static By buserName = By.xpath("//span[@id='lUserName']");      

    public void validateUserName() {
		Assert.assertTrue(driver.findElement(buserName).getText().trim().equals(LoginPage.getLoginUser()), "Validate Login User.");    	
    }
    
}