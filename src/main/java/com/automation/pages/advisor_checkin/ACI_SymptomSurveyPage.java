package com.automation.pages.advisor_checkin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ACI_SymptomSurveyPage extends ACI_FramePage {

    private static final By bSaveButton = By.xpath("//button[@class='s-button']/span[text()='Save']");    
    private static final By bSymptomIFrame = By.xpath("(//iframe)[1]");    
    private static final By bCheckBoxLowOutput = By.xpath("(//span[@class='s-checkbox__icon'])[1]");	
    private static final By bCheckBoxFrontRight = By.xpath("(//span[@class='s-checkbox__icon'])[7]");
    private static final By bCheckBoxHeadPhones = By.xpath("(//span[@class='s-checkbox__icon'])[12]");    
    
	public void selectSymptomSurveyDetails(String symptomSurvey) {  
		dWait.until(ExpectedConditions.visibilityOfElementLocated(bSymptomIFrame));		
    	driver.switchTo().frame(driver.findElement(bSymptomIFrame));			
		switch (symptomSurvey.toUpperCase()) {
		case "AUDIO/NAVIGATION/BLUETOOTH/TELEMATICS":	
			dWait.until(ExpectedConditions.visibilityOfElementLocated(bCheckBoxLowOutput));				
			driver.findElement(bCheckBoxLowOutput).click();
			driver.findElement(bCheckBoxFrontRight).click();
			driver.findElement(bCheckBoxHeadPhones).click();			
			break;
		default:
			return;
		}
    }
	
	public void click(String name) {		
		switch (name.toUpperCase()) {
		case "SAVE":
			dWait.until(ExpectedConditions.visibilityOfElementLocated(bSaveButton));			
			driver.findElement(bSaveButton).click();
			break;
		default:
			return;
		}
	}	
	
}
