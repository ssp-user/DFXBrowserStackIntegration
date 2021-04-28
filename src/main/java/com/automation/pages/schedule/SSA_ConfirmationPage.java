package com.automation.pages.schedule;

import com.automation.pages.common.WebPage;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class SSA_ConfirmationPage extends SSA_FramePage {

    private static Logger log = Logger.getLogger(SSA_ConfirmationPage.class);

//
//    @FindBy(xpath="//div[@class='centering']//div[contains(text(),'Confirmation')]")
//    protected static WebElement confirmText;
      private static By bConfirmText = By.xpath("//div[@class='centering']//div[contains(text(),'Confirmation Code')]");
//
//    @FindBy(xpath="//div[@class='centering']/div/div[@class='h2 ng-binding']")
//    protected static WebElement confirmThankYou;
     private static By bConfirmThankYou = By.xpath("//div[@class='centering']/div/div[@class='h2 ng-binding']");


    public String getConfirmation(){
        return  pWait.until(conditionVisible(bConfirmThankYou)).getText();
    }

    public void waitForPageToLoad(){																							// Wait for page to finish loading
        pWait.until(SyncPage.condDomReadyState());
        pWait.until(conditionVisible(bConfirmText));
    }


}
