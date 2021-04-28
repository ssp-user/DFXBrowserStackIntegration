package com.automation.pages.third_party;

import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class MightyTextPage extends WebPage {

    //locator

    //each contact by node
    private static By bContactNode (int node){
        String i = Integer.toString(node);
        String locator = "(//span[@class='threadNameOrNumber'])["+i+"]";
        return By.xpath(locator);
    }

    //message text
    private static By bSMSOnMightytext = By.xpath("(//span[@class='message-body'])[last()]");

    //message time stamp
    private static By bTimeStamp = By.xpath("(//span[@class='timestamp-msg timestampWithPopover'])[last()]");

    //message contact phone number
    private static By bReceiveNo(String dealerNumber){
        String locator = "//span[@class='threadNameOrNumber' and contains(text(),'"+dealerNumber+"')]";
        return By.xpath(locator);
    }

    //popup timestamp
    private static By bPopUpTimeStamp = By.xpath("//div[@class='popover-content']");

    //no thanks button
    private static By bNoThanks = By.id("custom-alert-and-confirm-modal-cancel-button");

    //popup close
    private static By bPopUpClose = By.xpath("//div[@role='dialog']/div/button[@class='close']");

    //message response area
    private static By bTextResponse = By.xpath("//div[@class='textResponse']");

    //send the button
    private static By bSendButton = By.xpath("//div[@class='dcsendicon']");

    //delete SMS button
    private static By bDeleteBtn = By.xpath("/html/body/div[3]/div[2]/div/div[1]/div[4]/div[4]/svg/path[1]");

    //OK button on popup
    private static By bOKBtn = By.id("custom-alert-and-confirm-modal-ok-button");

    public String getSMSFromMightyTextPage(){
        long startTime = System.currentTimeMillis();
        String msg = "";
        String dealerNumber = "6479300550";
        while(!msg.equals(tp.sms)){
//            clickElementWithException(bReceiveNo(dealerNumber));
            lWait.until(conditionVisible(bReceiveNo(dealerNumber))).click();
            msg = getElementTextWithException(bSMSOnMightytext);

            pageRefresh();
            if((System.currentTimeMillis()-startTime)> 300000){
                break;
            }
        }
        return msg;
    }

    public void sendMessageToDealer(String textSMS){
        long startTime = System.currentTimeMillis();
        while(isNoThanksPopupSHow() || isSocialMediaPopUpShow()){
            try{
                driver.findElement(bNoThanks).click();
                System.out.println("<====== No thanks button clicked ======>");
            }catch (WebDriverException ex){
                //no thanks pop up is not there
            }
            try{
                driver.findElement(bPopUpClose).click();
                System.out.println("<====== social media close clicked ======>");
            }catch (WebDriverException ex){
                //no thanks pop up is not there
            }
            sleep(2000);
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
        clearAndInputElementWithException(bTextResponse,textSMS);
        mightyTextPopupBlockHandleClick(bSendButton);
    }

    public void openURLFromSMS(String urlWithinSMS){
        String sms = getElementTextWithException(bSMSOnMightytext);
        int indexStartWithUrl = sms.indexOf("https");
        int indexEndAfterUrl = sms.indexOf("Type STOP");
        String startWithUrl = sms.substring(indexStartWithUrl);
        String endAfterUrl = sms.substring(indexEndAfterUrl);
        String url = startWithUrl.replace(endAfterUrl,"");
        System.out.println("<====== The DMPI url sent to customer from SMS is "+url+" ======>");
        driver.get(url);
        //openNewBrowserTabWithURL(url,4);
    }

    public void mightyTextPopupBlockHandleClick(By element){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(element).click();
                noException = true;
            }catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on click "+element+" method ======>");
                noException = false;
                sleep(1000);
            }catch (WebDriverException e) {
                try{
                    driver.findElement(bNoThanks).click();
                    System.out.println("<====== No thanks button clicked ======>");
                }catch (WebDriverException ex){
                    //no thanks pop up is not there
                }
                try{
                    driver.findElement(bPopUpClose).click();
                    System.out.println("<====== social media close clicked ======>");
                }catch (WebDriverException ex){
                    //no thanks pop up is not there
                }
                System.out.println("<====== wait and be patient make you healthy, now we waiting for clicking '"+element+"' (○'◡'○)  ======>");
                noException = false;
                sleep(1000);
            }
            if((System.currentTimeMillis()-startTime)> 60000){
                Assert.fail("<====== "+element+" not found on page, OR~ not clickable =.= ======>");
                break;
            }
        }
    }

    public boolean isNoThanksPopupSHow(){
        try{
            driver.findElement(bNoThanks).click();
            return true;
        }catch (WebDriverException ex){
            return false;
        }
    }

    public boolean isSocialMediaPopUpShow(){
        try{
            driver.findElement(bPopUpClose).click();
            return true;
        }catch (WebDriverException ex){
            return false;
        }
    }

    public void deleteSMS() {
        driver.findElement(bDeleteBtn).click();
        driver.findElement(bOKBtn).click();
    }
    
    public void validateSMS() {
    	CommonMethods.sleep(20000);
    	CommonMethods.verifyElementExists(driver.findElement(By.xpath("//*[text()='"+tp.sms+"']")));
    	
    }

}
