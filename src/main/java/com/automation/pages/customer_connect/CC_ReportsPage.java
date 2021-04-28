package com.automation.pages.customer_connect;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;

public class CC_ReportsPage extends WebPage {

    //locator
    //report drop down
    private static By bReportDropdown = By.xpath("//select[@ng-change='switchReport()']");

    private static By bReportsName(String reportsName){
        String checkLocator = "//option[contains(text(),'"+reportsName+"')]";
        return By.xpath(checkLocator);
    }

    private static By bReportColumn(String reportColumn){
        String checkLocator = "//div[@class='flexDiv reportContent reportTable ng-scope']//span[contains(text(), '"+reportColumn+"')]";
        return By.xpath(checkLocator);
    }

    private static By bReportValue(String reportValue){
        String checkLocator = "//div[@class='flexDiv reportContent reportTable ng-scope']//td[contains(text(),'"+reportValue+"')]";
        return By.xpath(checkLocator);
    }

    private static By bReportValueOverView = By.xpath("//td[contains(@class,'ng-binding')]");

    public void clickOnReportDropDown(String reportsName) {
        selectDropListElementWithException(bReportDropdown, reportsName);
        System.out.println("<====== I click on the report '"+reportsName+"' ======>");
    }

    public boolean verifyColumnVisible(String reportColumn){
        waitForElementVisibleWithException(bReportColumn(reportColumn));
        if(verifyIfElementVisibleOnPage(bReportColumn(reportColumn))){
            return true;
        }else{
            return false;
        }
    }

    public boolean verifyValueVisibleOverViewReport(){
        waitForElementVisibleWithException(bReportValueOverView);
        if(verifyIfElementClickableOnPage(bReportValueOverView)){
            dWait.until(conditionClick(bReportValueOverView)).click();
            return true;
        }else{
            return false;
        }
    }

    public boolean verifyValueVisible(String reportValue){
        waitForElementVisibleWithException(bReportValue(reportValue));
        if(verifyIfElementShowOnThePage(bReportValue(reportValue))){
            clickElementWithException(bReportValue(reportValue));
            return true;
        }else{
            return false;
        }
    }
}
