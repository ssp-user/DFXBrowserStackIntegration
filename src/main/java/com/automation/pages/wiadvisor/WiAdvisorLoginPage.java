package com.automation.pages.wiadvisor;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;

public class WiAdvisorLoginPage extends WebPage {

    //locator
    //user id
    private static By bWiAdvisorUserID = By.id("username");

    //password
    private static By bWiAdvisorPassword = By.id("password");
    //dealer code
    private static By bWiAdvisorDealerCode = By.id("dealerCode");
    //green sign in button
    private static By bSignIn = By.id("signIn");
    //production selection icon
//    private static By bProdIcon = By.xpath("//img[contains(@src,'wiadvisor-prod-logo')]");
    private static By bProdIcon = By.id("371");
    //production selection icon
//    private static By bPilotIcon = By.xpath("//img[contains(@src,'wiadvisor-pilot-logo')]");
    private static By bPilotIcon = By.id("210");
//
    public void loginWiAdvisorWithUserID(String userID){
        clearAndInputElementWithException(bWiAdvisorUserID,userID);
    }

    public void loginWiAdvisorWithPassword(String passWord){
        clearAndInputElementWithException(bWiAdvisorPassword,passWord);
    }

    public void loginWiAdvisorWithDealerCode(String dealerCode){
        clearAndInputElementWithException(bWiAdvisorDealerCode,dealerCode);
    }

    public void clickOnSignInButton(){
        dWait.until(conditionClick(bSignIn)).click();
    }

    public void selectEnvironmentOld(String dealerCode){
        if(driver.getCurrentUrl().contains("www.wiadvisor.com") && dealerCode.equals("99970")){
            clickElementWithException(bProdIcon);
            changeToPageWithTitle("wiADVISOR",2);
        }else if(driver.getCurrentUrl().contains("pilot.wiadvisor.com") && dealerCode.equals("99970")){
            clickElementWithException(bPilotIcon);
            changeToPageWithTitle("wiADVISOR",2);
        }
    }

    public void selectEnvironment(String dealerCode){
        if(dealerCode.equals("99970")) {
            sleep(300);
            String url = driver.getCurrentUrl();
            if (url.contains("pilot")) {
                pWait.until(conditionVisible(bPilotIcon)).click();
                changeToPageWithTitle("wiADVISOR",2);
            }else if (url.contains("test")) {
                //
            }else if (url.contains("uidev")) {
                //
            }else {
                pWait.until(conditionVisible(bProdIcon)).click();
                changeToPageWithTitle("wiADVISOR",2);
            }
        }
    }

}
