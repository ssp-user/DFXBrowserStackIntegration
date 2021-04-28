package com.automation.pages.appointment_manager;

import com.automation.utils.elementUI.CSSUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;

public class AM_HistoryPage extends AM_FramePage {

    private static Logger log = Logger.getLogger(AM_HistoryPage.class);

    //page object
    private static By bMaintenanceTab  =  By.cssSelector("li[heading='Maintenance Menu'] a");     // Maintenance Tab
    protected static By bpreWriteUpTab  =  By.cssSelector("li[heading='Pre-Service Write-Up'] a"); 	// Pre-Service Write-Up Tab
    protected static By bserviceHistoryTab  =  By.cssSelector("li[heading='Service History'] a"); 			// Service History Tab
    protected static By binspectionTab =  By.cssSelector("li[heading='Inspection History'] a"); 				// Inspection Tab
    protected static By bsummaryTab  =  By.cssSelector("li[heading='VIP Summary Report'] a"); 				// VIP Summary Report Tab
    protected static By bserviceHistoryItemList =  By.cssSelector("#new-customer_history div.active tr.ng-scope"); 			// Service Item List;
    protected static By bhistoryRO  =  By.cssSelector("#new-customer_history div.ng-scope.active header > h1");  // History Item RO
    protected static By bnoData1  =  By.cssSelector("#new-customer_history [class*='file-o']"); 					// No Data Image
    protected static By bnoData  =  By.xpath("//div[@class='c-no-data__description']");
    protected static By bCreateNewCustomer = By.xpath("//*[contains(text(),'Create new customer')]");
    protected static By bnextBtn1  =  By.cssSelector("#new-customer_history + footer button.btn.next");				// Next Button
    protected static By bUpdateExistingCustomer = By.xpath("//*[contains(text(),'Update existing customer')]");
//    public void clickOnAppointmentNewCustomer(){
//        clickElementWithException(bAppointmentForANewCustomer);
//    }

    private static By bNextButton = By.xpath("//button[@id='next-1500972347']");

    public void clickNextBtn(){
        sleep(2000);
        clickElementWithSeconds(bNextButton,2000);
    }

    protected static void waitForPageToLoad(){																		// Wait for Page to Load
    //    //wait.until(SyncUtils.condDomReadyState());
   //  //wait.until(SyncUtils.condPageLoaded(historyRO, noData));
//        pWait.until(ExpectedConditions.visibilityOf(nextBtn));
    }

    public void viewHistory(){
        clickElementWithSeconds(bNextButton,2000);
    }

    public boolean isHistoryDisplayed(){
        clickElementWithSeconds(bNextButton,2000);
        WebElement historyLink = driver.findElement(bHistoryLink);
        if(CSSUtils.getCssColorValue(historyLink).equals(CSSUtils.getSelectedServiceTab())){
            return true;
        } else {
            return false;
        }
    }

    public void clickCreateNewCustomerBtn(){
        dWait.until(conditionVisible(bHistoryTab)); 
        clickElementWithSeconds(bCreateNewCustomer,2000);
    }   
    
    public void clickUpdateExistingCustomerBtn(){
        dWait.until(conditionVisible(bHistoryTab)); 
        clickElementWithSeconds(bUpdateExistingCustomer,2000);
    }      

}
