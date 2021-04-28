package com.automation.pages.third_party;

import com.automation.pages.appointment_manager.AM_FramePage;
import com.automation.pages.appointment_manager.AM_TimeAndAdvisorPage;
import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class YahooPage extends WebPage {

	private static By bLoginYahooUser = By.xpath("//input[@id='login-username']");
	private static By bLoginNextButton = By.xpath("//input[@id='login-signin']");
	private static By bPassword = By.xpath("//input[@id='login-passwd']");
	private static By bPasswordNextButton = By.xpath("//button[@id='login-signin']");
    //Sender's email address
//    private static By bSenderAddress = By.xpath("//h3/span/span[@email='test@dealer-fx.com']");	
    private static By bSenderAddress = By.xpath("//span[@data-test-id = 'message-from']");    
    private static By bMessageContent = By.xpath("//div[@data-test-id='message-view-body-content']");    
    private static By bDeleteButton = By.xpath("(//span[contains(text(),'Delete')])[2]");
    private static By bSignInUser = By.xpath("//span[@role = 'presentation' and contains(text(),'DFX')]");    
    private static By bSignOutButton = By.xpath("//span[contains(text(),'Sign out')]");
    private static By bAttachment = By.xpath("//a[@data-test-id='attachment-download']");
    private static By bCheckInButton = By.xpath("//a[text() = 'CHECK IN']");
    private static By bMakeOnlinePaymentButton = By.xpath("//img[contains(@src,'%2FGeneric%2F2020%2FOnline_Payments%2Fcta.png')]");     
    //Yahoo subject with TEST+random
    private By bYahooSubjectWithTESTRandom(){
        String locator = "(//span[contains(text(),'Appointment Cancellation Confirmation')])[1]";    	
      //span[contains(text(),'TEST random')]
        return By.xpath(locator);
    }	
    private By bYahooNewBookingEmail(String dealerName){
//        String locator = "(//span[@title='Your Service Appointment at '"+dealerName+"' is Confirmed'])[1]"; // This is for Test1 environment    	
        String locator = "(//span[contains(text(),'Thank you for booking')])[1]";   // This is for Test2     
        return By.xpath(locator);
    }	    

    private By bYahooNewMCIEmail(){
      String locator = "(//span[contains(text(),'Thank you for booking')])[1]";   // This is for Test2     
      return By.xpath(locator);
    }	
    
    private By bYahooNewCCEmail(){
        String locator = "(//span[contains(text(),'Customer connect email')])[1]";   // This is for Test2     
        return By.xpath(locator);
      }	
    
    private By bYahooNewPaymentEmail(){
        String locator = "(//span[contains(text(),'Service Invoice')])[1]";   // This is for Test2     
        return By.xpath(locator);
      }	    
    
	public void loginYahoo(String userName, String passWord) {
		sleep(30000);
		clearAndInputElementWithException(bLoginYahooUser, userName);
		clickElementWithException(bLoginNextButton);
		clearAndInputElementWithException(bPassword, passWord);
		clickElementWithException(bPasswordNextButton);
		waitForTitle(userName);
	}
	
    public void clickOnEmailTESTRandom(){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                lWait.until(conditionVisible(bYahooSubjectWithTESTRandom())).click();
                noException = true;
            }catch (WebDriverException e) {
                //sWait.until(conditionClick(bRefreshOnGmail)).click();
                pageRefresh();
                noException = false;
                sleep(3000);
            }
            if((System.currentTimeMillis()-startTime)> 60000){
                Assert.fail("<====== "+ bYahooSubjectWithTESTRandom()+" email not found ======>");
                break;
            }
        }
    }
    
    public boolean senderAddress() {
    	System.out.println("******Value of driver.findElement(bSenderAddress).getText()*******--> "+ driver.findElement(bSenderAddress).getText());
        return driver.findElement(bSenderAddress).getText().contains("service@dealer-fx.net");
    }    

    public void validateEmailContents(){
    	System.out.println("Customer name is "+AM_FramePage.customerName);
    	System.out.println("Time of appointment is "+AM_TimeAndAdvisorPage.selectedTime);
    	System.out.println("******Value of driver.findElement(bMessageContent).getText()********-->"+ driver.findElement(bMessageContent).getText());
    	Assert.assertTrue(driver.findElement(bMessageContent).getText().trim().contains(AM_FramePage.customerName), "Validate message contents");    	
    	Assert.assertTrue(driver.findElement(bMessageContent).getText().trim().contains(AM_TimeAndAdvisorPage.selectedTime), "Validate message contents");
    }    

    public void clickDeleteButton() {
    	driver.findElement(bDeleteButton).click();
    }     

    public void signOutEmail() {
    	driver.findElement(bSignInUser).click();
    	driver.findElement(bSignOutButton).click();    	
    }   

    public void clickEmail(String dealerName){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                lWait.until(conditionVisible(bYahooNewBookingEmail(dealerName))).click();
                noException = true;
            }catch (WebDriverException e) {
                //sWait.until(conditionClick(bRefreshOnGmail)).click();
                pageRefresh();
                noException = false;
                sleep(3000);
            }
            if((System.currentTimeMillis()-startTime)> 60000){
                Assert.fail("<====== "+ bYahooNewBookingEmail(dealerName)+" email not found ======>");
                break;
            }
        }
    }    
    
    public void validateAttachment(){
    	CommonMethods.verifyElementExists(driver.findElement(bAttachment));      	
    }    
    
    public void clickMCIEmail(){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                lWait.until(conditionVisible(bYahooNewMCIEmail())).click();
                noException = true;
            }catch (WebDriverException e) {
                //sWait.until(conditionClick(bRefreshOnGmail)).click();
                pageRefresh();
                noException = false;
                sleep(3000);
            }
            if((System.currentTimeMillis()-startTime)> 60000){
                Assert.fail("<====== "+ bYahooNewMCIEmail()+" email not found ======>");
                break;
            }
        }
    }
    
    public void clickCheckInButton() {
    	driver.findElement(bCheckInButton).click();
    	sleep(20000);
    }     
    
    public void clickPaymentEmail(){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                lWait.until(conditionVisible(bYahooNewPaymentEmail())).click();
                noException = true;
            }catch (WebDriverException e) {
                //sWait.until(conditionClick(bRefreshOnGmail)).click();
                pageRefresh();
                noException = false;
                sleep(3000);
            }
            if((System.currentTimeMillis()-startTime)> 60000){
                Assert.fail("<====== "+ bYahooNewPaymentEmail()+" email not found ======>");
                break;
            }
        }
    }
    
    public void clickMakeOnlinePaymentButton() {
    	sleep(5000);    	
    	//driver.findElement(bMakeOnlinePaymentButton).click();
    	//sleep(3000);
    	//WebElement eDiv = driver.findElement(bMakeOnlinePaymentButton);
    	//CommonMethods.scrollToTopOfDiv(driver, eDiv);
    	sleep(2000);
    	driver.findElement(bMakeOnlinePaymentButton).click();
    	sleep(2000);
    
    }  
    
    public void clickCCEmail(){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                lWait.until(conditionVisible(bYahooNewCCEmail())).click();
                noException = true;
            }catch (WebDriverException e) {
                //sWait.until(conditionClick(bRefreshOnGmail)).click();
                pageRefresh();
                noException = false;
                sleep(3000);
            }
            if((System.currentTimeMillis()-startTime)> 60000){
                Assert.fail("<====== "+ bYahooNewCCEmail()+" email not found ======>");
                break;
            }
        }
    }    

}
