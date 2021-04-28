package com.automation.pages.advisor_checkin;

import com.automation.pages.appointment_manager.AM_ClientAndVehiclePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ACI_HistoryPage extends ACI_FramePage {

    private static Logger log = Logger.getLogger(ACI_HistoryPage.class);

    private static final By bMaintMenuTab = By.cssSelector("#history [title*='MENU']"); // Maintenance Menu History Tab
    private static final By bHistoryItem = By.cssSelector(".date.ng-binding"); // Maintenance Menu History Item
    private static final By bMmHistoryBtn = By.cssSelector("#history [ng-click*='MenuPreWrite']"); // Maintenance Menu History Item Show Button
    private static final By bCancelBtn = By.cssSelector("#printHistory div > button.btn.btn-grey"); // Cancel Button on Menu Print History
    private static final By bElsa2GoTab = By.cssSelector("li[ng-click*='elsa2go']"); // Elsa2Go Tab
    private static final By bElsa2GoFrame = By.cssSelector("iframe[class*='elsa2go']"); // Elsa2Go Frame
    private static final By bElsa2GoContent = By.id("english_layer"); // Elsa2Go Content
    private static final By bGrandTotalAmount = By.cssSelector("#printHistory .table-body div.table-row:last-child span.ng-binding");

    private static final By bAllHistoryList = By.cssSelector("#printHistory .table-body div.table-row:last-child span.ng-binding"); // All of the Vehicle History
    private static final By spinnerMMLocator = By.cssSelector("#history div[loader='maintenanceMenuHistory'] i.spinner");				// Spinning wheel indicates page is loading
    private static final By spinnerSHLocator = By.cssSelector("#history div[loader='serviceHistory'] i.spinner");
    private static final By spinnerPSWHLocator = By.cssSelector("#history div[loader='preServiceWriteUpHistory'] i.spinner");
    private static final By spinnerEVIRLocator = By.cssSelector("#history div[loader='evirHistory'] i.spinner");

//    public static By bNextButtonOnHistory = By.xpath("//span[contains(text(), 'NEXT')]");
    private static By bNextButtonOnHistory = By.id("g-hproceed");

    public void clickNextOnHistoryOld(){
        if(driver.getCurrentUrl().contains("service-history")){
            AM_ClientAndVehiclePage cnvPage = new AM_ClientAndVehiclePage();
            cnvPage.clickOnNext();
        }else{
            clickElementWithException(bNextButtonOnHistory);
        }
    }

    public void clickNextOnHistory(){
        sleep(1000);
        WebElement element = pWait.until(conditionClick(bNextButtonOnHistory));
        clickElementTimesTillDisappear(element,10);
    }

    private void switchToElsa2GoFrame(){																							// Wait for Elsa2Go page loaded
        WebElement e2g = pWait.until(conditionVisible(bElsa2GoFrame));
        driver.switchTo().frame(e2g);
    }

    public String getElsa2GOContentText(){																							// Wait for Elsa2Go page loaded
        switchToElsa2GoFrame();
        String text = pWait.until(conditionVisible(bElsa2GoContent)).getText();
        driver.switchTo().defaultContent();
        return text;
    }

    public boolean isHistoryTabActive(String tab){																							// Wait for Elsa2Go page loaded
        String name = tab.toUpperCase();
        boolean result = false;
        switch (name) {
            case "HISTORY":
                result =  webElementHasClass(bHistoryTab,"active");
                break;
            case "ELSA2GO":
               result =  webElementHasClass(bElsa2GoTab,"active");
                break;
        }
        return result;
    }

}
