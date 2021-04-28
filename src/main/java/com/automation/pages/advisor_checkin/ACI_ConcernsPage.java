package com.automation.pages.advisor_checkin;

import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ACI_ConcernsPage extends ACI_FramePage {

    private static Logger log = Logger.getLogger(ACI_ConcernsPage.class);

//    @FindBy(css="span.span10>span:nth-child(1)")
//    private static List<WebElement> concernsList;
    private static final By bConcernsList = By.cssSelector("span.span10>span:nth-child(1)");// Concern Items List, index starts from 0

//    @FindBy(css="span.span10>span:nth-child(2)")
//    private static List<WebElement> concernsTextList;
    private static final By bConcernsTextList = By.cssSelector("span.span10>span:nth-child(2)");// Concern Items Text List, index starts from 0

//    @FindBy(css="#concerns .scrollcontent .span2.ng-binding")
//    private static List<WebElement> concernsPriceList;
    private static final By bConcernsPriceList = By.cssSelector("#concerns i.spinner");// Concern Item Price List, index starts from 0

//    @FindBy(id="g-cproceed")
//    private static WebElement nextBtn;
//    private static final By bConcernnextBtn = By.id("g-cproceed");// Next Button

//    @FindBy(css="#concerns-selectedserv p")
//    private static List<WebElement> selectedConcernItemsList;																						// All Selected Concerns Items List
    By bCelectedConcernItemsListLocator = By.cssSelector("#concerns-selectedserv p");

//    @FindBy(css="#concerns-selectedserv input[placeholder='parts']")
//    private static List<WebElement> selectedConcernItemsPartsPriceList;
    private static final By BselectedConcernItemsPartsPriceList = By.cssSelector("#concerns-selectedserv input[placeholder='parts']");// List of all Selected Concerns Part Price

//    @FindBy(css="#concerns-selectedserv input[placeholder='Labor']")
//    private static List<WebElement> selectedConcernItemLaborPriceList;
    private static final By bSelectedConcernItemLaborPriceList = By.cssSelector("#concerns-selectedserv input[placeholder='Labor']");// List of all Selected Concerns Labor Price

//    @FindBy(css="#concerns-selectedserv [ng-required*='services.comments']")
//    private static List<WebElement> selectedConcernItemCommentList;
    private static final By bCselectedConcernItemCommentList = By.cssSelector("#concerns-selectedserv [ng-required*='services.comments']"); // List of all Selected Concerns Comments

//    @FindBy(css="#concerns div.span2.total-price.ng-binding")
//    protected static WebElement totalGrandAmount;
    private static final By bTotalGrandAmount = By.cssSelector("#concerns div.span2.total-price.ng-binding"); // Total Grand Amount

//    @FindBy(css="#concerns-selectedConcernsContent + div div.block-underline:nth-child(1) div.row-fluid:nth-child(1) div.span2.ng-binding")
//    protected static WebElement partsTotalPrice;
    private static final By bPartsTotalPrice = By.cssSelector("#concerns-selectedConcernsContent + div div.block-underline:nth-child(1) div.row-fluid:nth-child(1) div.span2.ng-binding"); // Parts Total Price

//    @FindBy(css="#concerns-selectedConcernsContent + div div.block-underline:nth-child(1) div.row-fluid:nth-child(2) div.span2.ng-binding")
//    protected static WebElement laborTotalPrice;
    private static final By bLaborTotalPrice = By.cssSelector("#concerns-selectedConcernsContent + div div.block-underline:nth-child(1) div.row-fluid:nth-child(2) div.span2.ng-binding");// Labor Total Price

//    @FindBy(css="#concerns-selectedConcernsContent + div div.block-underline:nth-child(1) div.row-fluid:nth-child(4) div.span2.ng-binding")
//    protected static WebElement salesTax;
    private static final By bSalesTax = By.cssSelector("#concerns-selectedConcernsContent + div div.block-underline:nth-child(1) div.row-fluid:nth-child(4) div.span2.ng-binding");// Sales Tax

//    @FindBy(css="#concerns .rightPanelNotification>span")
//    private static WebElement noSelectedServices;
    private static final By bNoSelectedServices = By.cssSelector("#concerns .rightPanelNotification>span"); // No Selected Services sign

    private static final By spinnerLocator = By.cssSelector("#concerns i.spinner");

    private static final By removeSurvey = By.xpath("(//span[@ng-click='removeSurvey(service)'])[1]");
    
    private static final By bDeleteSurvey = By.xpath("//button[@type='button' and @class='btn btn-red']/span[text()='Delete']");
    
    //next button on concern page
    public static By bNextButtonOnConcern = By.id("g-cproceed");

    //concern name
    private static By bAddConcernByName(String concernName){
        String checkLocator = "//span[@class='title ng-binding' and contains(text(),'"+concernName+"')]";
        return By.xpath(checkLocator);
    }

    public void concernItem(String concernName){
        long startTime = System.currentTimeMillis();
        ACI_CustomerPage acPage = new ACI_CustomerPage();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bAddConcernByName(concernName)).click();
                System.out.println("<====== customer concern "+concernName+" clicked ======>");
                noException = true;
            }catch (WebDriverException e) {
                acPage.pagePopUpHandle();
                sleep(1000);
            }
            if((System.currentTimeMillis()-startTime)> 60000){
                break;
            }
        }
    }

    public void waitForPageToLoad(){
        pWait.until(SyncPage.condDomReadyState());
//        wait.until(SyncPage.condTabPageSwitched(driver, concernsTab));
        pWait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
        log.info("Concerns page is finished loading");

    }


    public void clickNextOnConcernDD(){
        long startTime = System.currentTimeMillis();
        ACI_CustomerPage acPage = new ACI_CustomerPage();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bNextButtonOnConcern).click();
                noException = true;
            }catch (WebDriverException e) {
                acPage.pagePopUpHandle();
                sleep(1000);
            }
            if((System.currentTimeMillis()-startTime)> 60000){
                Assert.fail("<====== Next button on concern page is not clickable or blocked by the pop up ======>");
                break;
            }
        }
    }

    public void clickNextOnConcern(){
        WebElement element = pWait.until(conditionClick(bNextButtonOnConcern));
        clickElementTimesTillDisappear(element,20);
    }

    public void validateSymptomSurvey(String symptomSurvey){
        pWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='s-survey__title ng-binding' and contains(text(),'"+symptomSurvey+"')])[1]")));    	
        CommonMethods.verifyElementExists(driver.findElement(By.xpath("(//span[@class='s-survey__title ng-binding' and contains(text(),'"+symptomSurvey+"')])[1]")));
    }    

    public void deleteSymptomSurvey(String symptomSurvey){
        pWait.until(ExpectedConditions.visibilityOfElementLocated(removeSurvey));    	
        driver.findElement(removeSurvey).click();
        pWait.until(ExpectedConditions.visibilityOfElementLocated(bDeleteSurvey));    	
        driver.findElement(bDeleteSurvey).click();        
        
    }        
    
}
