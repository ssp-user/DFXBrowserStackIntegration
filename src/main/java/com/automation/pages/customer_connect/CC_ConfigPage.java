package com.automation.pages.customer_connect;

import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CC_ConfigPage extends WebPage {
	
	public static String englishTxtOtherCall;  	

	public static String getEnglishTxtOtherCall() {
		return englishTxtOtherCall;
	}

	public static void setEnglishTxtOtherCall(String englishTxtOtherCall) {
		CC_ConfigPage.englishTxtOtherCall = englishTxtOtherCall;
	}

	public static String englishTxtOtherClosedCall;  
	
    public String getEnglishTxtOtherClosedCall() {
		return englishTxtOtherClosedCall;
	}

	public void setEnglishTxtOtherClosedCall(String englishTxtOtherClosedCall) {
		this.englishTxtOtherClosedCall = englishTxtOtherClosedCall;
	}

	//Dispositions
    private static By bdispositions = By.xpath("//span[text()='Dispositions']");
    //Others
    private static By bOther = By.xpath("//div[text()='Other']");
    private static By bOtherENTxt = By.xpath("//input[@id='group-1-name-en']");    
    //Others - Closed Call
    private static By bOtherClosedCall = By.xpath("//div[text()='Other - Closed Call']");
    private static By bOtherClosedCallENTxt = By.xpath("//input[@id='group-1-code-0-name-en']");    
    //Save
    private static By bSaveButton = By.xpath("//div[@class='rightFlex horizontalFlexOrder']/button[text()='Save']");
    //OK-popup
    private static By bOKBtnOnPopupBy = By.xpath("//button[@class='alertMsgBoxMainButton ng-binding'][@ng-click='callbackAccept()']");    
    
    
    public void clickElement(String element) {
        switch (element.toUpperCase()) {
        case "DISPOSITIONS":
        	dWait.until(ExpectedConditions.elementToBeClickable(bdispositions));
            clickElementWithException(bdispositions);        	
            break;    
        case "OTHER - CLOSED CALL":
        	dWait.until(ExpectedConditions.elementToBeClickable(bOtherClosedCall));
            clickElementWithException(bOtherClosedCall);  
            break;   
        case "SAVE":
        	dWait.until(ExpectedConditions.elementToBeClickable(bSaveButton));
            clickElementWithException(bSaveButton);  
            break;      
        case "OK":
        	dWait.until(ExpectedConditions.elementToBeClickable(bOKBtnOnPopupBy));
            clickElementWithException(bOKBtnOnPopupBy);  
            break; 
        case "OTHER":
        	dWait.until(ExpectedConditions.elementToBeClickable(bOther));
            clickElementWithException(bOther);  
            break;            
        }
    }
    
    public void updateENClosedCall(String text){
        if (text.toLowerCase().equals("global")) {
        	text = CommonMethods.getRandomText(6);
        }         
        clearAndSend(bOtherClosedCallENTxt, text);
        setEnglishTxtOtherClosedCall(text);
    }    
    
    public void updateENOther(String text){
        if (text.toLowerCase().equals("global")) {
        	text = CommonMethods.getRandomText(6);
        }         
        clearAndSend(bOtherENTxt, text);
        setEnglishTxtOtherCall(text);
    }       

}
