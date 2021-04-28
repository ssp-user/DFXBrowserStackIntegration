package com.automation.pages.appointment_manager;

import com.automation.utils.elementUI.CSSUtils;
import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AM_SummaryPage extends AM_FramePage {

    private static Logger log = Logger.getLogger(AM_SummaryPage.class);

    private String totalPriceforEachItems, totalAllItemsPrice, totalPriceAmt;

    //  ****** import element from test NG ****


//    @FindBy(css="section.loader.ng-scope figure.loader_spiner")
//    private static WebElement editSpinner;																											// Edit Loading Spinner
//
//
//    @FindBy(id="new-customer_summary")
//    private static WebElement summarySection;																										// Summary Section
//
//
//    @FindBy(css="div.content-series h4.header.ng-binding")
//    private static WebElement scheduledTime;																										// Scheduled Time Title
//    private static By scheduledTimeLocator = By.cssSelector("div.content-series h4.header.ng-binding");
//
//
//    @FindBy(css="#new-customer_summary div.new-group-items_item--fields-wrap div:nth-child(3) input")
//    private static List<WebElement> itemPriceList1;																									// Item Price List
//
//    @FindBy(xpath="(//div[@class='c-services__items']//div[@class='c-services__cell c-services__cell--price']/div/div)")
//    private static List<WebElement> itemPriceList11;
//
//
//    @FindBy(css="#new-customer_summary div[class*='--total'] div:nth-child(3)")
//    private static WebElement totalPrice1;																											// Total Price
//    private static By totalPriceLocator = By.cssSelector("#new-customer_summary div[class*='--total'] div:nth-child(3)");
//
//    @FindBy(css="div figure.radial-loader.loader_spiner")
//    private static WebElement spinner;																												// Spinner
//    private static By spinnerLocator = By.cssSelector("div figure.radial-loader.loader_spiner");
//
//    //private static By transportationoptions = By.xpath("//div[contains(text(),'Waiter') or ./text()='Own ride'  or ./text()='Rental car' or ./text()='Shuttle ride' or ./text()='Need loaner' or ./text()='Pickup & Delivery']");
//    @FindBy(xpath="//div[contains(text(),'Waiter') or ./text()='Own ride'  or ./text()='Rental car' or ./text()='Shuttle ride' or ./text()='Need loaner' or ./text()='Pickup & Delivery']")
//    private static WebElement transportationoptions;
//
//    @FindBy(css="li.active tab-heading")
//    private static WebElement currentSelectedOption1;																								// Current Selected Transportation Option
//
//    @FindBy(xpath="//div[@class='c-to-tool__cell__btn c-to-tool__cell__btn--is-selected']")
//    private static WebElement currentSelectedOptionOld;
//
//    @FindBy(xpath="//div[@class='c-to-tool__tr'][1]//div[contains(@class,'c-to-tool__cell__btn--is-disabled')]")
//    private static WebElement disabledOption;
//
//    @FindBy(xpath="//div[@class='summary__tabs']//div[contains(text(),'Confirmation')]")															// Confirmation tab
//    protected static WebElement confiramtionTab;
//
//    @FindBy(css="input[type='checkbox'][ng-model*='email']")
//    private static WebElement emailConfirmationCheckBox1;																							// Email Confirmation Checkbox
//
//    @FindBy(css="input[type='checkbox'][id='e-mail']")
//    private static WebElement emailConfirmationCheckBox;
//    // Email Confirmation Checkbox - to deselect
//
//    @FindBy(xpath="//label[@class='checkbox'][1]")
//    private static WebElement emailCheckBoxToDeselect;
//
//    @FindBy(css="c-to-tool c-to-tool--is-address")
//    private static WebElement transOptions;
//
//    @FindBy(css=".c-full-appt-info__advisor__name")
//    private static WebElement apptCreatedBy_Advisor;
//
//    @FindBy(xpath="//div[@ng-reflect-klass='c-to-tool__cell__btn']")
//    protected static List<WebElement> transOptionsList;

    //    private static List<WebElement> totalPrice;
    private static By bTotalPrice = By.xpath("//div[@class='c-services__table-total']//div[@class='c-services__cell c-services__cell--price']");

    //    private static WebElement GrandtotalPrice;
    private static By bGrandtotalPrice = By.cssSelector(".c-services__total-item:nth-child(3) div:nth-child(5)");

    //    @FindBy(css="div.c-services__cell--price input.c-services__field-self")
//    private static List<WebElement> itemPriceList;
    private static By bItemPriceList = By.cssSelector("div.c-services__cell--price input.c-services__field-self");

    //    @FindBy(xpath="//div[@tablename='Tires']//div[@class='c-comment-field__field']")
//    protected static WebElement removeTireComment;
    private static By bRemoveTireComment = By.xpath("//div[@tablename='Tires']//div[@class='c-comment-field__field']");

//    @FindBy(xpath="//div[@tablename='Tires']//a[@class='c-comment-field__cancel']")
//    protected static WebElement commentCancel;
    private static By bCommentCancel = By.xpath("//div[@tablename='Tires']//a[@class='c-comment-field__cancel']");

    //    @FindBy(xpath="//div[@tablename='Tires']/div[@class='c-services__table-total']/div[@class='c-services__cell c-services__cell--price']")
//    protected static WebElement tireService;
    private static By bTireService = By.xpath("//div[@tablename='Tires']/div[@class='c-services__table-total']/div[@class='c-services__cell c-services__cell--price']");

    //    @FindBy(xpath="//button[@id='repeat-repair-btn-1500967836']")
//    private static WebElement repeatRepair;
      private static By bRepeatRepair = By.id("repeat-repair-btn-1500967836");
//      @FindBy(xpath="//button[@id='special-order-parts-btn-1500967874']")
//      private static WebElement specialParts;
      private static By bSpecialParts = By.id("special-order-parts-btn-1500967874");

    //  ****** end import element from test NG ****


    //locator
    //create button on the summary
    private static By bCreateOnSummary = By.xpath("//span[text()='Create']");
    private static By bCreateApptBtn = By.xpath("//button[@id='create-1500972101']");
    //

    //save button on the summary
//    private static By bSaveOnSummary = By.xpath("//span[text()='Save']");
    private static By bSaveOnSummary = By.id("save-1500972117");

    //time & advisor tab on the header
    private static By bTimeNAdvisorTab = By.xpath("//a[contains(text(),'Time & Advisor')]");

    //comment field
//    private static By bCommentField = By.xpath("//div[contains(@id,'add-comment')]");
    private static By bCommentField = By.id("add-comment-1500969280");

    public void clickOnCreateBtn(){
        actionClick(bCreateApptBtn);
        CommonMethods.resetScrolledPixels();
    }

    public void clickOnSaveOnSummary(){
        clickElementWithException(bSaveOnSummary);
    }

    public void clickOnSaveBtn(){
        clickElementTimesTillDisappear(bSaveOnSummary,3);
        CommonMethods.resetScrolledPixels();
    }


    public void addComment(String comment){
        clearAndSend(bCommentField,comment);
    }

    public String getTotalAmount(){																									// Calculate total price from item list
        pWait.until(conditionVisible(bGrandtotalPrice));
        double total = 0;
        double temp;
        String price = null;
        List<WebElement> elementList = driver.findElements(bTotalPrice);
//        List<WebElement> elementList = pWait.until(conditionPresenceList(bTotalPrice));
        for(int i =0; i<elementList.size();i++){
            price = elementList.get(i).getText().trim();
            temp = Double.parseDouble(price.replaceAll(",", ""));
            total += temp;
        }
        totalPriceAmt = CommonMethods.formatHandler(total);
        System.out.println("calculated total="+totalPriceAmt);
        return totalPriceAmt;

    }

    public String getCalculatedTotal(){																									// Calculate total price from item list
        pWait.until(conditionVisible(bGrandtotalPrice));
        double total = 0;
        double temp;
        String price = null;
        String result = null;
        List<WebElement> elementList = driver.findElements(bItemPriceList);
        for(int i =0; i<elementList.size();i++){
            //price = elementList.get(i).getText().trim();				//update-2.20
            price = elementList.get(i).getAttribute("value");
            temp = Double.parseDouble(price.replaceAll(",", ""));
            total += temp;
        }

        totalPriceforEachItems = CommonMethods.formatHandler(total);
        System.out.println("calculated list price="+totalPriceforEachItems);
        return totalPriceforEachItems;

    }

    public  void waitForPageToLoad(){																										// Wait for page to load
        pWait.until(SyncPage.condDomReadyState());
        pWait.until(conditionVisible(bCommentField));
        pWait.until(conditionVisible(bGrandtotalPrice));
    }

    public void clearTireComment(){
    	sleep(5000);    
        driver.findElement(bRemoveTireComment).clear();
    }

    public void cancelTireComment(){
        clickElementWithSeconds(bCommentCancel,1000);
    }

    public String getTireService(){
        String tire ="  ";
        tire =sWait.until(conditionVisible(bTireService)).getText();
        return tire;
    }

    private boolean  clickRPOPtion(By option ){
        WebElement eOption = driver.findElement(option);
        if (eOption.getAttribute("class").contains("active")){
            eOption.click();
            return true;
        }else{
            return false;
        }
    }

    public void clickRPOptions(String name){
        WebElement rOption = driver.findElement(bRepeatRepair);
        WebElement pOption = driver.findElement(bSpecialParts);
         boolean select = false;
         if (name.contains("REPAIR")){
            if (!rOption.getAttribute("class").contains("active")){
                repeatRepair ++;
                rOption.click();
                select = true;
            }
        }else if (name.contains("PARTS")){
            if (rOption.getAttribute("class").contains("active")){
                 repeatRepair --;
                 rOption.click();
             }
            if (!pOption.getAttribute("class").contains("active")){
                specialParts ++;
                pOption.click();
                select = true;
            }
        }else{
            //
        }
        System.out.println(" ");
        System.out.println(" Option selected : "+ name + "  is " + select + "\n");
    }

}
