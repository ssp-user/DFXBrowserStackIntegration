package com.automation.pages.appointment_manager;

import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.elementUI.CSSUtils;
import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class AM_ServicesPage extends AM_FramePage {



    private static Logger log = Logger.getLogger(AM_ServicesPage.class);

    private static ArrayList<String> selectedScheduledServiceTextList = new ArrayList<String>();
    private static ArrayList<String> selectedUnscheduledServiceTextList = new ArrayList<String>();
    private static ArrayList<String> selectedRecommendServiceTextList = new ArrayList<String>();

    public static String quotePriceVal="";

     //  ****** import element from test NG ****

//    // Scheduled Service Tab
//    @FindBy(xpath="//a[@id='scheduled-1500966586']")
//    private static WebElement scheduledServiceTab;
//
//
//    // Recommended Service Tab
//    @FindBy(xpath="//a[@id='drs-1500966625']")
//    private static WebElement recommendedServiceTab;
//
///*
//	@FindBy(css="#serv-cs-manufacture .ng-binding:nth-child(1)")
//	protected static List<WebElement> scheduledItemTextList;*/
//
//
//    // Scheduled Service Item Price List
//    @FindBy(css="#serv-cs-manufacture .ng-binding:nth-child(4)")
//    private static List<WebElement> scheduledItemPriceList;
//
//    // Scheduled Service Item Button List
//    @FindBy(css="#serv-cs-manufacture div[class*='scroll'] button[ng-click*='Service' i]")
//    private static List<WebElement> scheduledItemButton;
//
//    // Scheduled Service Items Add All Button
//    @FindBy(css="#serv-cs-manufacture div.table-header button")
//    private static WebElement scheduledItemsAddAllButton;
//
//    @FindBy(xpath="//div[@class='c-services__outer-table-header']//a[@class='c-services__add']")
//    private static WebElement concernItemsAddAllButton;
//
///*		// Unscheduled Service Item Text List
//	@FindBy(css="#serv-cs-manufacture-unscheduled .ng-binding:nth-child(1)")
//	protected static List<WebElement> unscheduledItemTextList1;*/
//
//
//    // Unscheduled Service Item Price List
//
//    // Unscheduled Service Item Button List
//
//
///*		// Recommended Service Item Text List
//	@FindBy(css="#serv-cs-recommend .ng-binding:nth-child(1)")
//	protected static List<WebElement> recommendItemTextList;*/
//
//
//    // Recommended Service Item Price List
//    @FindBy(css="#serv-cs-recommend .ng-binding:nth-child(4)")
//    private static List<WebElement> recommendItemPriceList;
//
//    // Recommended Service Item Button List
//    @FindBy(css="#serv-cs-recommend .dynamic button")
//    private static List<WebElement> recommendItemButton;
//
//    // Scheduled Service with No item selected Image
//    @FindBy(css="#new-customer_services div[ng-if='true'] div.h")
//    protected static WebElement noItemsSelectedMsg;
//
//    // Perform Service Item List
//    @FindBy(css="#new-customer_services div[class*='scroll'] div.wrap")
//    private static List<WebElement> performServiceItemList1;
//
//    @FindBy(xpath="//div[@class='c-edit-appt-tab__selected-services']//div[@class='c-services__item']/div[1]/div[1]")
//    private static List<WebElement> performServiceItemList;
//
//
//    @FindBy(css="#new-customer_services textarea[ng-model*='service']")
//    private static List<WebElement> selectedServiceItemCommentList;															// Selected Service Items Comment List
//
//
//    // Perform Service Remove All Button
//    @FindBy(css="#new-customer_services button[ng-click*='clean']")
//    private static WebElement removedAllBtn1;
//
//    @FindBy(xpath="//a[@class='c-services__remove']")
//    private static List<WebElement> removedAllBtnAll;
//
//    @FindBy(xpath="//a[@class='c-services__remove']")
//    private static WebElement removedAllBtn;
//
//    @FindBy(xpath="view-details-change-1500970159")
//    private static WebElement removedConfirm;
//
//    private static void removeBtn()
//    {
//        for(int i=1;i<=removedAllBtnAll.size();i++)
//        {
//            JsClick(removedAllBtn);
//            JsClick(removedConfirm);
//            break;
//        }
//    }
//
//
//    // Perform Service Item Price List
//    @FindBy(css="#new-customer_services div:last-child>input.new-group-items_item--field-input")
//    private static List<WebElement> performItemPriceList;
//
//
//    // Total Amount (Parts and Labor)
//    @FindBy(css="#new-customer_services div.new-group-items--total div.new-group-items_item--result div:last-child")
//    protected static WebElement totalAmount;
//
//
//    // Next Button
//    @FindBy(css="footer button[ng-click*='nextStep(servicesForm)']")
//    private static WebElement nextBtn1;
//
//    @FindBy(id="next-1500971921")
//    private static WebElement nextBtn;
//
//    // Item List Spinner
//    @FindBy(css="#new-customer_services figure.radial-loader.loader_spiner")
//    protected static WebElement spinner;
//    protected static By spinnerLocator = By.cssSelector("#new-customer_services figure.radial-loader.loader_spiner");
//
//
//    @FindBy(css="#services div.total .span8 .row-fluid:nth-child(3) .ng-binding")										// 2.8
//    protected static WebElement totalGrandAmount;
//
//    /////////////////////////////////////////////Tire Section Objects//////////////////////////////////////////////////////////
//
//    @FindBy(xpath="//div[@class='c-loader__radial']")
//    protected static WebElement tireLoader;
//
//    @FindBy(xpath="//select[@id='trim']")
//    protected static WebElement selectTrim;
//
//    @FindBy(xpath="//select[@id='trim']/option")
//    protected static List<WebElement> trimOptions;
//
//    @FindBy(xpath="//a[@id='submitMMY']")
//    protected static WebElement trimContinue;
//
//    @FindBy(xpath="//h2[contains(text(),'tire size')]")
//    protected static WebElement tireSizeText;
//
//
//    @FindBy(xpath="//a[@class='button sizeTarget'][1]")																		//First visible size
//    protected static WebElement selectTireSize;
//
//    @FindBy(xpath="//h2[contains(text(),'product catalog')]")
//    protected static WebElement selectFromCatalog;
//
//    @FindBy(css="iFrame[ng-src*='tirework']")
//    protected static WebElement iFrame;
//
//    @FindBy(css="//h2[@class='l-tires-offer-result__title']")
//    protected static WebElement noResultTire;
//
//    @FindBy(css="#dt-header a.installed")
//    protected static WebElement addTireBtn;
//    @FindBy(id="PDFokButton")
//    protected static WebElement reminderOKBtn;
//
//    @FindBy(css="#services input[name='servicePrice']")
//    private static List<WebElement> selectedItemPriceList;
//
//	/*@FindBy(xpath="//div[@id='dt-header']//a[@class='button installed']")
//	protected static WebElement addQuote;*/
//
//    @FindBy(css="div.mtm>button:nth-child(1)")
//    private static WebElement quoteBtn;
//	/*@FindBy(css="#services [ng-click*='generateQuote']")
//	private static WebElement quoteBtn;	*/
//
//    @FindBy(xpath="//a[@id='recalls-1500966639']")
//    protected static WebElement recallTab;
       private static By bRecallTab = By.id("recalls-1500966639");
//
//    @FindBy(xpath="//a[@id='recalls-1500966639']/following-sibling::span[@class='c-tab-nav__count']")
//    protected static WebElement recallTabCount;
      private static By bRecallTabCount = By.xpath("//a[@id='recalls-1500966639']/following-sibling::span[@class='c-tab-nav__count']");
//
//    @FindBy(xpath="//div[@class='c-services__items']//div[@class='c-services__left-side-icon']")
//    protected static List<WebElement> recallListCount;
      private static By becallListCount = By.xpath("//div[@class='c-services__items']//div[@class='c-services__left-side-icon']");
      private static By bRecallList = By.xpath("//section[contains(@class,'c-comment-field--has-error')]");


//    protected static WebElement noItemsSelectedMsgConcern;
    private static By bNoItemsSelectedMsgConcern = By.xpath("//section[@sectionname='Concerns']//div[@class='c-services__no-services']");
//    private static WebElement grandTotalConcerns;
    private static By bGrandTotalConcerns = By.xpath("//section[@sectionname='Concerns' ]//div[@class='c-services__total-item']//div[@class='c-services__cell c-services__cell--price']");

    private static By bUnscheduledServiceTab1 = By.xpath("//a[@id='unscheduled-1500966601']");
    private static By bUnscheduledServiceTab = By.id("unscheduled-1500966601");
    private static WebElement unscheduledServiceTab;

//    @FindBy(xpath="//section[@sectionname='Unscheduled' ]//div[@class='c-services__total-item']//div[@class='c-services__cell c-services__cell--price']")
//    protected static WebElement grandTotalUnschedule;
    private static By bGrandTotalUnschedule = By.xpath("//section[@sectionname='Unscheduled' ]//div[@class='c-services__total-item']//div[@class='c-services__cell c-services__cell--price']");

        // Scheduled Service Grand Total
//    @FindBy(css="div.result-panel.ng-scope div.new-group-items_item--field")
//    private static WebElement grandTotal;
    private static By bGrandTotal = By.cssSelector("div.result-panel.ng-scope div.new-group-items_item--field");

//    @FindBy(xpath="//section[@sectionname='Scheduled' ]//div[@class='c-services__total-item']//div[@class='c-services__cell c-services__cell--price']")
//    protected static WebElement grandTotalSchedule;
    private static By bGrandTotalSchedule = By.xpath("//section[@sectionname='Scheduled' ]//div[@class='c-services__total-item']//div[@class='c-services__cell c-services__cell--price']");

//    @FindBy(xpath="//section[@sectionname='Dealer Recommended' ]//div[@class='c-services__total-item']//div[@class='c-services__cell c-services__cell--price']")
//    protected static WebElement grandTotalDRS;
    private static By bGrandTotalDRS = By.xpath("//section[@sectionname='Dealer Recommended' ]//div[@class='c-services__total-item']//div[@class='c-services__cell c-services__cell--price']");

//    @FindBy(xpath="//div[@class='c-services__items']//a[@class='c-services__add']")
//    private static List<WebElement> unscheduledItemButton;
    private static By bUnscheduledItemButton = By.xpath("//div[@class='c-services__items']//a[@class='c-services__add']");

    //    // Scheduled Service Item Text List
//    @FindBy(xpath="//div[@class='c-services__item']//div[@class='c-services__cell c-services__cell--desc']")
//    protected static List<WebElement> ItemTextList;
    private static By bItemTextList = By.xpath("//div[@class='c-services__item']//div[@class='c-services__cell c-services__cell--desc']");

    //
//    @FindBy(css=".c-services__item .c-services__cell--price .c-services__field-display")
//    private static List<WebElement> unscheduledItemPriceList;
    private static By bUnscheduledItemPriceList = By.cssSelector(".c-services__item .c-services__cell--price .c-services__field-display");

//        @FindBy(xpath="//section[@sectionname='Dealer Recommended']//div[@class='c-services__no-services']")
//    protected static WebElement noItemsSelectedMsgDRS;
    private static By bNoItemsSelectedMsgDRS = By.xpath("//section[@sectionname='Dealer Recommended']//div[@class='c-services__no-services']");

//    protected static WebElement noItemsSelectedMsgScheduled;
    private static By bNoItemsSelectedMsgScheduled = By.xpath("//section[@sectionname='Scheduled']//div[@class='c-services__no-services']");

//    protected static WebElement noItemsSelectedMsgUnscheduled;
    private static By bNoItemsSelectedMsgUnscheduled = By.xpath("//section[@sectionname='Unscheduled']//div[@class='c-services__no-services']");

//
//    @FindBy(xpath="//a[@id='generate-menu-1500971845']")
//    protected static WebElement generateMenu;
     private static By bGenerateMenu = By.id("generate-menu-1500971845");
//
//    @FindBy(xpath="//a[@id='open-menu-1500971870']")
//    protected static WebElement openMenu;
     private static By bOpenMenu = By.id("open-menu-1500971870");


    // ************  Dealer Tire

    //    @FindBy(xpath="//a[@id='tires-1500966656']")
//    protected static WebElement tireTab;
    private static By bTireTab = By.xpath("//a[@id='tires-1500966656']");

//    @FindBy(xpath="//button[@id='add-tires-1500966847']")
//    protected static WebElement addTire;
    private static By bAddTire = By.xpath("//button[@id='add-tires-1500966847']");

    //    @FindBy(xpath="//a[@id='PDFokButton']")
//    protected static WebElement pdfOKBtn;
    private static By bPDFOKBtn = By.xpath("//a[@id='PDFokButton']");

    //    @FindBy(xpath="//div[@class='c-services__total-item']/div[@class='c-services__cell c-services__cell--price']")
//    protected static WebElement quotePrice;
    private static By bQuotePrice = By.xpath("//div[@class='c-services__total-item']/div[@class='c-services__cell c-services__cell--price']");

    //    @FindBy(xpath="//div[@id='content']/div/a[1]")
//    protected static WebElement tireSize;
    private static By bTireSize = By.xpath("//div[@id='content']/div/a[1]");
    private static By bTireSizes(int num ){
        String sque = String.valueOf(num);
        String locator = String.format("//div[@id='content']/div/a[%s]",sque);
        System.out.println( " Size bar  .... 0000   sque  is " + sque + "   locator  000... " + locator );
//        String locator = "//div[@id='content']/div/a[1]";
        return By.xpath(locator);
    }

    //    @FindBy(xpath="//iframe[@iframepostmessage='recieve']")
//    protected static WebElement tireIframe;
    private static By bTireIframe = By.xpath("//iframe[@iframepostmessage='recieve']");

//    @FindBy(xpath="//*[@id='renderTarget']/div[1]/div[2]/div[2]/div[2]")
//    protected static WebElement viewTireBtnfirst;
    private static By bViewTireBtnfirst = By.xpath("//*[@id='renderTarget']/div[1]/div[2]/div[2]/div[2]");

//    @FindBy(css="#renderTarget div.dt-box>a")
//    protected static List<WebElement> viewTireBtnList;
     private static By bViewTireBtnList = By.cssSelector("#renderTarget div.dt-box>a");
    
     
    //    @FindBy(css="#dt-header a.installed span.price")
//    protected static WebElement installPrice;
    private static By bInstallPrice = By.cssSelector("#dt-header a.installed span.price");

    // ****** end import element

    //Concern tab
    private static By bConcernTab = By.xpath("//a[@routerlink='concerns']");
    //scheduled tab
    private static By bScheduledTab = By.xpath("//a[@routerlink='scheduled']");
    //unscheduled tab
    private static By bUnScheduledTab = By.xpath("//a[@routerlink='unscheduled']");
    //recommended services tab
    private static By bRecommendedServiceTab = By.xpath("//a[@routerlink='drs']");
    //recalls tab
    private static By bRecallsTab = By.xpath("//a[@routerlink='recalls']");

    private static By bNextButton = By.xpath("//button[@id='next-1500971921']");  // next Button

    private static By bSelectedUnScheduledServices = By.xpath("((//div[@class='c-services__table _c-services__table--has-confirmation'])[2]/..//div[@class='c-services__cell c-services__cell--desc'])[2]");
    
	public static String selectedUnScheduledService = null;    
	
    //concern Item By
    private static By bConcernItem(String concernName){
        String node = "";
        switch (concernName){
            case "AC/Heating Concern":
                node = "3";
                break;
            case "Battery Concern":
                node = "4";
                break;
        }
        String locator = "(//*[@class='icon icon--arrow-incircle-right icon--button'])["+node+"]";
        return By.xpath(locator);
    }

    private static By bConcernPrice = By.xpath("//div[@class='c-services__field-display']");

    private static By bConcernItemWithPriceOrder(String concernOrder, String price){
        String node = "";
        if(concernOrder.equals("first")){
            node = "1";
        }
        String locator = "(//div[contains(@class,'price')]/div/div[text()='"+price+"']/../../..//*[@class='icon icon--arrow-incircle-right icon--button'])["+node+"]";
        return By.xpath(locator);
    }

    public void addConcernItem(String concernName){
        clickElementWithException(bConcernItem(concernName));
        System.out.println("<====== "+concernName+" concern added ======>");
    }

    public void addConcernItemWithPrice(String concernOrder, String price){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            int i = 0;
            i++;
            try{
                driver.findElement(bConcernItemWithPriceOrder(concernOrder,price)).click();
                System.out.println("<====== Find the concern with price "+price+" ======>");
                noException = true;
            }catch (WebDriverException e) {
                switch (i){
                    case 0:
                        clickOnConcernsTab();
                        break;
                    case 1:
                        clickOnScheduledTab();
                        break;
                    case 2:
                        clickOnUnScheduledTab();
                        break;
                    case 3:
                        clickOnRecommendedServiceTab();
                        break;
                    case 4:
                        clickOnRecallsTab();
                        break;
                }
                noException = false;
                sleep(1000);
            }
            if((System.currentTimeMillis()-startTime)> 20000){
                break;
            }
        }
        //clickElementWithException(bConcernItemWithPriceOrder(concernOrder,price));
        System.out.println("<====== "+concernOrder+" concern with "+price+" added ======>");
    }

    public void clickOnConcernsTab(){
        clickElementWithException(bConcernTab);
        System.out.println("<====== concern tab clicked ======>");
    }

    public void clickOnScheduledTab(){
        clickElementWithException(bScheduledTab);
        System.out.println("<====== schedule tab clicked ======>");
    }

    public void clickOnUnScheduledTab(){
        waitServicePageToLoad();
        clickElementWithException(bUnScheduledTab);
        System.out.println("<====== unschedule tab clicked ======>");
    }

    public void clickOnRecommendedServiceTab(){
        clickElementWithException(bRecommendedServiceTab);
        System.out.println("<====== recommended tab clicked ======>");
    }

    public void clickOnRecallsTab(){
        clickElementWithException(bRecallsTab);
        System.out.println("<====== recall tab clicked ======>");

    }

    public void clickNextBtn(){
        clickElementWithSeconds(bNextButton,2000);
    }

    public void clickButtonOnService(String buttonName){
        switch (buttonName) {
            case "NEXT":
                clickElementWithSeconds(bNextButton,2000);
                break;
            case "GENERATE MENU":
                pWait.until(conditionVisible(bGenerateMenu)).click();
                sleep(1500);
//                clickElementTimesTillDisappear(bGenerateMenu,3);
                break;
            case "OPEN MENU":
                pWait.until(conditionVisible(bOpenMenu)).click();
                break;
        }

    }


    private void waitServicePageToLoad(){									// Wait for page to load
        pWait.until(SyncPage.condDomReadyState());
        pWait.until(SyncPage.condPageLoaded(bNoItemsSelectedMsgConcern, bGrandTotalConcerns));

    }

    public boolean isAnyPreselectedItems(String grand){
        grand = grand.toUpperCase();
        boolean select = true;
        try{
            switch (grand){
                case "UNSCHEDULE":
                    select = driver.findElement(bGrandTotalUnschedule).isDisplayed();
                    break;
                case "SCHEDULE":
                    select = driver.findElement(bGrandTotalSchedule).isDisplayed();
                    break;
                case "DRS":
                    select = driver.findElement(bGrandTotalDRS).isDisplayed();
                    break;
            }
        } catch(NoSuchElementException e){
        //
        }
        return select;

    }

    public void chooseItemUnScheduleService(String serviceItem) {
    	try {
          List<WebElement> targetWebElements = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bUnscheduledItemButton));
//          List<WebElement> targetWebElements = driver.findElements(bUnscheduledItemButton);
          List<WebElement> elementsTextList = driver.findElements(bItemTextList);
          List<WebElement> targetWebElementsPrice = driver.findElements(bUnscheduledItemPriceList);
          WebElement eGrandTotal = driver.findElement(bGrandTotalUnschedule);
          selectedUnscheduledServiceTextList = chooseOneServiceItem( serviceItem, targetWebElements, elementsTextList, targetWebElementsPrice, eGrandTotal);			
		} catch (org.openqa.selenium.TimeoutException ex) {
			System.out.println("********************No item found of unscheduled.********************");
		}
    }

    public void chooseRandomItemsUnSchedule(int items) {
        List<WebElement> targetWebElements = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bUnscheduledItemButton));
//        List<WebElement> targetWebElements = driver.findElements(bUnscheduledItemButton);
        List<WebElement> elementsTextList = driver.findElements(bItemTextList);
        List<WebElement> targetWebElementsPrice = driver.findElements(bUnscheduledItemPriceList);
        WebElement eGrandTotal = driver.findElement(bGrandTotalUnschedule);
        selectedUnscheduledServiceTextList = chooseRandomServiceItems( items, targetWebElements, elementsTextList, targetWebElementsPrice, eGrandTotal);
    }

    public void clickUnscheduledTab(){
        do{
            clickElementWithSeconds(bUnscheduledServiceTab,4000);
            sleep(500);
            unscheduledServiceTab= driver.findElement(bUnscheduledServiceTab);
        } while(!CSSUtils.getCssColorValue(unscheduledServiceTab).equals(CSSUtils.getSelectedServiceTab()));
        pWait.until(SyncPage.condPageLoaded(bNoItemsSelectedMsgUnscheduled, bGrandTotalUnschedule));
        sleep(1000);

  }

    public void gotoServiceTab(String tab ){
        waitServicePageToLoad();
        switch (tab){
            case "CONCERNS":
                clickUnscheduledTab();
                break;
            case "SCHEDULED":
                clickUnscheduledTab();
                break;
            case "UNSCHEDULED":
                clickUnscheduledTab();
                break;
            case "RECOMMENDED SERVICES":
                clickUnscheduledTab();
                break;
            case "RECALLS":
                clickElementWithSeconds( bRecallTab,1000);
                break;
            case "TIRES":
                clickElementWithSeconds(bTireTab,1000);
                break;
        }

    }

    public void clickBtnOnServiceTire(String btn ){
        switch (btn){
            case "ADD TIRES":
                pWait.until(conditionClick(bAddTire)).click();
                break;
            case "ADD TO QUOTE":
                pWait.until(conditionClick(bInstallPrice)).click();
                sleep(2000);
                break;
        }

    }

    public void clickTireSizeOnServiceTire(int num){
          sleep(8000);
          WebElement frame = driver.findElement(bTireIframe);
          driver.switchTo().frame(frame);
        WebElement elemnt = pWait.until(conditionVisible(bTireSizes(num)));
        elemnt.click();

    }

    public void clickTireOptionOnServiceTire(int num){
        sleep(5000);
        List<WebElement> tires = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bViewTireBtnList));
        clickElementTimesTillDisappear(tires.get(num-1),3);

    }


    public String getTireQuotePrice() {
        try {
            sWait.until(conditionVisible(bPDFOKBtn)).click();
        } catch (WebDriverException e) {
            //
        }
        sleep(1000);
        quotePriceVal = pWait.until(conditionVisible(bQuotePrice)).getText();
        return quotePriceVal ;
    }

    public String getRecallTabCount() {
           String  recall = driver.findElement(bRecallTabCount).getAttribute("data-count");
           return recall;
    }

    public int getRecallListSize() {
//        try{
//            pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bRecallList));
//        }catch (TimeoutException te){
//            log.warn(" the Recall list not found on Service page ");
//        }
        return  driver.findElements(bRecallList).size();
    }
    
	public void captureUnScheduledService() {
		selectedUnScheduledService = getElementTextWithException(bSelectedUnScheduledServices).trim();
		System.out.println("Selected Unscheduled service is ***************"+selectedUnScheduledService);
	}    

}
