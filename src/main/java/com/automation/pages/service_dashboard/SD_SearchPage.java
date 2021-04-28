package com.automation.pages.service_dashboard;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

public class SD_SearchPage extends WebPage {

    //locator
    //was not found
    private static By bNotFound(String vin){
        String locator = "//span[contains(text(),'«"+vin+"» was not found')]";
        return By.xpath(locator);
    }

    //delete icon
    private static By bDeleteIcon = By.xpath("//*[@class='icon icon--btn--trash-2 button-icon']");

    //custom field
    private static By bCustom = By.xpath("//span[text()='Custom']");

    //play button
    private static By bPlayButton = By.xpath("//*[@class='icon icon--btn--play icon button-icon']/following-sibling::span[text()='Complete Check-In']");

    //back button on the header
    private static By bBack = By.xpath("//*[contains(@class,'icon icon--btn--arrow-left button-icon')]");

    public void deleteServiceLaneIfExist(String vin){
        //ignore if service lane not found, delete service lane if existed
        if(!isVehicleNotFound(vin)){
            waitForElementWithException(bPlayButton);
            clickElementWithSeconds(bDeleteIcon,9000);
        }else{
            System.out.println("<====== delete button not found on page ======>");
        }
    }

    public boolean isDeleteIconVisible(){
        try{
            driver.findElement(bDeleteIcon).isDisplayed();
            return true;
        }catch (WebDriverException e){
            return false;
        }
    }

    public boolean isPlayButtonEnabled(){
        try{
            driver.findElement(bPlayButton).isDisplayed();
            return true;
        }catch (WebDriverException e){
            return false;
        }
    }

    public boolean isDeleIconEnabled(){
        try{
            driver.findElement(bDeleteIcon).isDisplayed();
            return true;
        }catch (WebDriverException e){
            return false;
        }
    }

    public boolean isVehicleNotFound(String vin){
        try{
            sWait.until(conditionVisible(bNotFound(vin)));
            return true;
        }catch (WebDriverException e){
            return false;
        }
    }

    public void clickOnBack(){
        long startTime = System.currentTimeMillis();
        while(!getCurrentUrl().contains("dashboard")){
            try{
                driver.findElement(bBack).click();
            }catch (WebDriverException e){
                //the back button is not there
            }
            if((System.currentTimeMillis()-startTime)> 100000){
                break;
            }
        }
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

}
