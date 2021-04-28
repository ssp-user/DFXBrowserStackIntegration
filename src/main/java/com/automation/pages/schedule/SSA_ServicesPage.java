package com.automation.pages.schedule;

import com.automation.pages.common.WebPage;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SSA_ServicesPage extends SSA_FramePage {

    private static Logger log = Logger.getLogger(SSA_ServicesPage.class);


    private static By bTryAgainBtn = By.xpath("//div[text()='Try again']");

//    @FindBy(xpath="//div[contains(text(),'Maintenance intervals')]")
//    protected static WebElement servicePage;
      private static By bServicePage = By.xpath("//div[contains(text(),'Maintenance intervals')]");
//
//    @FindBy(xpath="//span[@class='text' and contains(text(),'Factory Required Maintenance')]")
//    protected static WebElement factoryservicePage;
//
//    @FindBy(xpath="//span[contains(text(),'Factory Scheduled Maintenance')]")
//    protected static WebElement factoryScheduledMaintenance;
//
//    @FindBy(xpath="//div[@class='centering']/button[@ng-click='next()']")
//    protected static WebElement nextBtn;
     private static By bNextBtn = By.xpath("//div[contains(text(),'Maintenance intervals')]");

//    @FindBy(xpath="//span[contains(text(),'Dealer Recommended Services')]")
//    protected static WebElement dealerservicePage;


    //   imported from TestNG


    public void waitForPageToLoad(){																							// Wait for page to finish loading
        pWait.until(SyncPage.condDomReadyState());
        dWait.until(conditionVisible(bServicePage));
    }

}
