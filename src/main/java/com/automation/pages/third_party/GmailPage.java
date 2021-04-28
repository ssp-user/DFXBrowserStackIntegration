package com.automation.pages.third_party;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;

public class GmailPage extends WebPage {

    //locator
    //legacy google username field
    private static By bLegacyGoogleUser = By.id("Email");

    //legacy google next
    private static By bLegacyNext = By.id("next");

    //legacy google password field
    private static By bLegacyPassword = By.id("Passwd");

    //legacy google sign in button
    private static By bLegacySignIn = By.id("signIn");

    //gmail username field
    private static By bGmailUserNameField = By.id("identifierId");

    //next button on gmail login page
    private static By bNextOnLogin = By.xpath("//span[contains(text(),'Next')]");

    //gmail password field
    private static By bGmailPasswordField = By.xpath("//input[@type='password']");

    //gmail subject with DealerName
    private static By bGmailSubjectAssociatedWithDealerName(String dealerName){
        String locator = "//span/span[@class='bqe' and contains(text(),'"+dealerName+"')]";
        return By.xpath(locator);
    }

    //Gmail subject with TEST+random
    private By bGmailSubjectWithTESTRandom(){
        String locator = "//span/span[contains(text(),'"+tp.newCreatedEmailSubject+"')]";
        return By.xpath(locator);
    }

    //Sender's email address
    private static By bSenderAddress = By.xpath("//h3/span/span[@email='test@dealer-fx.com']");

    //url within the email body
    private static By bUrlWithinEmailBody(String partialURL){
        String locator = "//a[contains(@href,'"+partialURL+"')]";
        return By.xpath(locator);
    }

    //delete button on email body
    private static By bDeleteButtonOnGmailBody = By.xpath("//div[@class='iH bzn']//div[contains(@class,'ar9')]/..");

    //delete email button
    private static By bDeleteGmailBtn = By.xpath("//*[@id=':4']/div[2]/div[1]/div/div[2]/div[3]/div");

    //refresh button
    private static By bRefreshOnGmail = By.xpath("//*[@id=':5']/div/div[1]/div[1]/div/div/div[5]/div/div");

    public boolean isGoogleLoginLegacy(){
        try{
            sWait.until(conditionVisible(bGmailUserNameField)).isDisplayed();
            return false;
        }catch (WebDriverException e){
            return true;
        }
    }

    public void loginGmail(String userName, String passWord){
        if(isGoogleLoginLegacy()){
            //this is for the legacy google, for some reason google login re-direct to legacy login if running under headless
            clearAndInputElementWithException(bLegacyGoogleUser,userName);
            clickElementWithException(bLegacyNext);
            clearAndInputElementWithException(bLegacyPassword,passWord);
            clickElementWithException(bLegacySignIn);
            waitForTitle(userName);
        }else{
            clearAndInputElementWithException(bGmailUserNameField,userName);
            clickElementWithException(bNextOnLogin);
            clearAndInputElementWithException(bGmailPasswordField,passWord);
            clickElementWithException(bNextOnLogin);
            waitForTitle(userName);
        }
    }

    public void loginGoogleAccount(String userName, String passWord){
        if(isGoogleLoginLegacy()){
            //this is for the legacy google, for some reason google login re-direct to legacy login if running under headless
            clearAndInputElementWithException(bLegacyGoogleUser,userName);
            clickElementWithException(bLegacyNext);
            clearAndInputElementWithException(bLegacyPassword,passWord);
            clickElementWithException(bLegacySignIn);
        }else {
            clearAndInputElementWithException(bGmailUserNameField, userName);
            clickElementWithException(bNextOnLogin);
            clearAndInputElementWithException(bGmailPasswordField, passWord);
            clickElementWithException(bNextOnLogin);
        }
    }

    public void clickOnAssociatedEmail(String dealerName){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                sWait.until(conditionVisible(bGmailSubjectAssociatedWithDealerName(dealerName))).click();
                noException = true;
            }catch (WebDriverException e) {
                //sWait.until(conditionClick(bRefreshOnGmail)).click();
                pageRefresh();
                noException = false;
                sleep(3000);
            }
            if((System.currentTimeMillis()-startTime)> 90000){
                Assert.fail("<====== "+ bGmailSubjectAssociatedWithDealerName(dealerName)+" email not found ======>");
                break;
            }
        }
        waitForTitle(dealerName);
    }

    public void clickOnUrlWithinEmailBody(String partialURL){
        String dmpiUrl = getElementTextWithException(bUrlWithinEmailBody(partialURL));
        driver.findElement(bDeleteButtonOnGmailBody).click();
        openURL(dmpiUrl);
        try{
            driver.switchTo().alert().accept();
        }catch(org.openqa.selenium.NoAlertPresentException e){
            //no alert
        }
    }

    public void clickOnEmailTESTRandom(){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                lWait.until(conditionVisible(bGmailSubjectWithTESTRandom())).click();
                noException = true;
            }catch (WebDriverException e) {
                //sWait.until(conditionClick(bRefreshOnGmail)).click();
                pageRefresh();
                noException = false;
                sleep(3000);
            }
            if((System.currentTimeMillis()-startTime)> 60000){
                Assert.fail("<====== "+ bGmailSubjectWithTESTRandom()+" email not found ======>");
                break;
            }
        }
    }

    public boolean senderAddress() {
        return driver.findElement(bSenderAddress).getText().equalsIgnoreCase("test@dealer-fx.com");
    }

    public void deleteGmail() {
        clickElementWithException(bDeleteGmailBtn);
    }
}
