package com.automation.pages.dealerfx;

import com.automation.pages.common.WebPage;
import com.automation.pages.payment.CreateInvoicePage;
import com.automation.pages.payment.PaymentHomePage;
import com.automation.utils.PageActions;
import com.automation.utils.otherUtils.CommonMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends WebPage {

    public static String loginUser = null;	

    public static String getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(String loginUser) {
		LoginPage.loginUser = loginUser;
	}
	
	public static String preferredLanguage = null;	
	
	public static String getPreferredLanguage() {
		return preferredLanguage;
	}

	public static void setPreferredLanguage(String preferredLanguage) {
		LoginPage.preferredLanguage = preferredLanguage;
	}	

	// UserName
    @FindBy(id="txtFrameworkUserName")
    private static WebElement userName;

    // PassWord
    @FindBy(id="txtFrameworkPassword")
    private static WebElement passWord;

    // Submit Button
    @FindBy(xpath="//input [@type = 'submit']")
    private static WebElement submitBtn;
    
    // Forgot your password
    @FindBy(xpath="//div/a[@id = 'hForget']")
    private static WebElement forgotPwdBtn;   
    
    // Forgot your password
    @FindBy(xpath="//div/span[@id='lMessage']")
    private static WebElement errorMessage;  
    
    // Marketing pop up message
    private static final By marketingMessage = By.xpath("//p[text()='Announcing: New Communication Tool']");   
    
    // Marketing pop up message
    private static final By marketingMessageDismissBtn = By.xpath("//button[text()='Dismiss']");        
    
    @FindBy(id="ddlLanguage")
    private static WebElement language;    

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    private  Boolean  isPageLoad(){
        return dWait.until(conditionForPage());
    }

    @Override
    protected   ExpectedCondition<Boolean> conditionForPage(){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver){
                try{
                    if(userName.isDisplayed()){
                        return true;
                    } else {
                        return null;
                    }
                } catch (Exception e){
                    return null;
                }
            }
            @Override
            public String toString(){
                return "condElementDisplay";
            }
        };
    }

    protected ExpectedCondition<WebElement> waitCondition() {
        return ExpectedConditions.elementToBeClickable(submitBtn);
    }

    private void setUserID(String userID){		// Enter user name
    	setLoginUser(userID);
        clearAndSend(userName,userID);
    }

    private  void setPassword(String pword){		// Enter Password
        clearAndSend(passWord,pword);
    }

    private  void clkSubmitBtn(){  // Click on the Submit button
        submitBtn.click();
    }

    public void loginHomePage(String username, String password) {
        String ready = (new PageActions().jsPageState());
//        dWait.until(waitCondition());
        setUserID(username);
        setPassword(password);
        clkSubmitBtn();
        handleMarketingPopUp();        
    }

    @Override
    protected void switchFrame() {
        driver().switchTo().defaultContent();
    }
    
    public void validateLayOutOfLoginScreen(String language) {
    	CommonMethods.verifyElementExists(submitBtn);
    	CommonMethods.verifyElementExists(forgotPwdBtn);
    	switch(language.toUpperCase()) {
    		case "English (CA)":
    			Assert.assertTrue(submitBtn.getText().trim().equals("Login"),"Validate Login button text");
    			Assert.assertTrue(forgotPwdBtn.getText().trim().equals("Forgot your password?"),"Validate forgot password text");    			
    		break; 
    		case "Français":
    			Assert.assertTrue(submitBtn.getText().trim().equals("Connectez-vous"),"Validate Login button text");
    			Assert.assertTrue(forgotPwdBtn.getText().trim().equals("Vous avez oublié votre mot de passe?"),"Validate forgot password text");    			
    		break;     		
    	}
    }    
    
    public void choosePreferredLanguage() {
    	language.sendKeys(LoginPage.getPreferredLanguage());
    	language.sendKeys(Keys.ENTER);	
    }   
    
    public void validateErrorMessageOnTheLoginScreen(String language) {
    	switch(language.toUpperCase()) {
    		case "English (CA)":
    			Assert.assertTrue(errorMessage.getText().trim().equals("Login failed. Please try again."),"Validate Login failure message");
    		break; 
    		case "Français":
    			Assert.assertTrue(errorMessage.getText().trim().equals("Échec de la connexion. S'il vous plaît essayez de nouveau."),"Validate Login failure message");
    		break;     		
    	}
    }
    
    public void handleMarketingPopUp(){																								// Handle the Stored Data Warnign Message
        sleep(5000);
        try{
			sWait.until(ExpectedConditions.visibilityOfElementLocated(marketingMessage));        	
            WebElement dismissMarketingBtn = sWait.until(conditionClick(marketingMessageDismissBtn));
            moveToClick(dismissMarketingBtn);
            System.out.println("Login page -  Marketing pop up displayed. Clicked dismiss button marketing button");
            sleep(500);
        } catch (TimeoutException te ){
        	System.out.println("Login page -  Marketing pop up is not displayed. No action required.");
        }
    }    

}


