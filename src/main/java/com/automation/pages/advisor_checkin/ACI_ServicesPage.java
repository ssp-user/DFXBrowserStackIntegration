package com.automation.pages.advisor_checkin;

import com.automation.pages.appointment_manager.AM_ClientAndVehiclePage;
import com.automation.pages.appointment_manager.AM_ServicesPage;
import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;

import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ACI_ServicesPage extends ACI_FramePage {

    private static Logger log = Logger.getLogger(ACI_ServicesPage.class.getName());

    //next button on service page
//    public static By bNextButtonOnServices = By.xpath("//span[contains(text(), 'Next')]");
    private static By bNextButtonOnServices = By.id("g-sproceed");

    protected static ArrayList<String> selectedFactoryRequiredServiceTextList;

    protected static ArrayList<String> selectedScheduledServiceTextList;
    protected static ArrayList<String> selectedUnscheduledServiceTextList;
    protected static ArrayList<String> selectedRecommendedServiceTextList;
    protected static ArrayList<String> selectedDealerRecommendedServiceTextList;

    protected static String[] beforeRemovingItems;
    protected static String[] afterRemovingItems;

//    public static ArrayList<String> selectedItemList ;

    public static double accumulatedPrice = 0;

    public static String currentTotalAmount = "0.00";

    private static final By bRecallIcon = By.xpath("//span[@class='tab-pane-vh__title-count tab-pane-vh__title-count--red ng-binding']");

    private static final By bRecallStatus = By.xpath("//div[@class='vh_table__col-1']//following::div[@class='vh_table__status vh_table__status--true']");
    private static By bscheduledTabParent = By.cssSelector("#services-neededServices li:nth-child(2)"); // CSS Parent of Scheduled Tab  change 1 to 2 March 29
    private static final By bScheduledTabLocator = By.cssSelector("li[ui-event-tracker2*='factory.scheduled.maintenance'] a");  //  change 1 to 2 March 29
    private static final By bScheduledItemList = By.cssSelector("span[data-ng-click*=\"toggleService(service, 'required')\"]:nth-child(1)"); // List of all Scheduled Item
    private static final By bScheduledItemTextList = By.cssSelector("span[data-ng-click*=\"toggleService(service, 'required')\"] + span"); // List of all Scheduled Item Text
    private static final By bScheduledItemPriceList = By.cssSelector("#services-maintenanceServices .span2.text-right.ng-binding"); // List of all Scheduled Item Price
    private static By bRemoveAllScheduledServiceBtn = By.cssSelector("div[data-ng-click=\"removeAllServicesByType('required')\"] svg");
    private static By bUnscheduledTabParent = By.cssSelector("li[ui-event-tracker2*='factory.other.maintenance.tab']"); // CSS Parent of Unscheduled Tab  2 to ->3
    private static By bunscheduledTab = By.cssSelector("li[ui-event-tracker2*='factory.other.maintenance.tab'] a"); // Unscheduled Tab
    private static By bscheduledTab = By.xpath("//span[@class='width-schedule']"); // Scheduled Tab    
    private static By bunscheduledItemList = By.cssSelector("span[data-ng-click*=\"toggleService(service, 'unscheduled')\"]:nth-child(1)"); // List of all Unscheduled Item
    private static By bunscheduledItemTextlist = By.cssSelector("span[data-ng-click*=\"toggleService(service, 'unscheduled')\"] + span"); // List of all Unscheduled Item Text
    private static By bunscheduledItemPriceList = By.cssSelector("#services-unscheduledServices .span2.text-right.ng-binding"); // List of all Unscheduled Item Price
    private static By bremoveAllUnScheduledServiceBtn = By.cssSelector("div[data-ng-click*=\"removeAllServicesByType('unscheduled')\"] svg");
    private static By brecommendedTabParent = By.cssSelector("li[ui-event-tracker2*='dealer.recommended']"); // CSS Parent of Recommended Tab  3 to 4
    private static By brecommendedTab = By.cssSelector("li[ui-event-tracker2*='dealer.recommended'] a"); // Recommended Tab   3 to 4
    private static By bFRSTabParent = By.cssSelector("li[ui-event-tracker2*='factory.scheduled.maintenance']"); // CSS Parent of Recommended Tab  3 to 4
    private static By bFRSTab = By.cssSelector("li[ui-event-tracker2*='factory.scheduled.maintenance'] a"); // Recommended Tab   3 to 4
    private static By brecommendedItemList = By.cssSelector("span[class*='custom-checkbox icon-box'][ng-click*=\"toggleService(service, 'recommended')\"]"); // updated 2019-06/24
//    private static By brecommendedItemTextList = By.cssSelector("span[class*='custom-checkbox icon-box'][ng-click*=\"toggleService(service, 'recommended')\"]+span>span:nth-child(1)");
    private static By brecommendedItemTextList = By.cssSelector("span[class*='custom-checkbox icon-box'][ng-click*=\"toggleService(service, 'recommended')\"]+span");
    private static By brecommendedItemPriceList = By.cssSelector("#services-otherServices .span2.text-right.ng-binding"); // List of all Recommended Item Price
    private static By bremoveAllRecommendedServiceBtn = By.cssSelector("div[data-ng-click=\"removeAllServicesByType('recommended')\"] svg");
    private static By bSelectedAllServiceItemsList = By.cssSelector("#services-selectedserv p");        // All selected item list include Scheduled, Unscheduled and Factory Recommended
    private static By selectedAllServiceItemsListLocator = By.cssSelector("#services-selectedserv div div div p");
    private static By bTotalGrandAmount = By.cssSelector("#services div.total .span8 .row-fluid:nth-child(3) .ng-binding"); // Total Grand Price
    private static final By bServiceSpinner = By.cssSelector("#services i.spinner");                                            // Spinner Locator indicating page is loading
    private static By bFactoryRequiredItemList = By.cssSelector("[data-ng-repeat*='service in requiredServices'] div span span span:nth-child(1)"); // List of all Factory Required Items
    private static By bFactoryRequiredItemTextList = By.cssSelector("[data-ng-repeat*='service in requiredServices'] div span span span:nth-child(2)"); // List of all Factory Required Item Text
    private static By bFactoryRequiredItemPriceList = By.cssSelector("###"); // List of all Factory Required Item Price , but depreciated item now

    private static By bMultipointInspectionTitle = By.xpath("//div[@data-ng-repeat='service in requiredServices | filter:{scheduled: true}:true | filter: {name: search_requred_scheduled}'][2]/descendant::span[4][@title='Multi-point inspection']");
    /********************************************* Below WebElement are for MENU ONLY ***********************************************/
    private static final By bTireOfferSpinnerLocator = By.cssSelector("#contentId > div.spinner");                                // Tire Offer Loading Spinner
    private static By bQuoteBtnServices = By.cssSelector("#services [ng-click*='generateQuote']"); // Quote Button for Menu ONLY
    private static By bQuoteBtn = By.xpath("(//button[@id='g-sGenerate'])[1]");
    private static By bmenuBtn = By.cssSelector("#services [ng-disabled*='Menu']"); // Menu Button for Menu ONLY
    private static By bMaintMenuPDFshowBtn = By.cssSelector(".btn.btn-grey[ng-click='showMMPDF()']");        // PDF show Button for Maint Menu for MENU ONLY Dealer
    private static By bMainMenuPDFShowBtn = By.xpath("//button[@ng-click='showMMPDF()']");
    private static By bEditBtn = By.cssSelector("div[aria-hidden='false'] a.btn.btn-grey");

    public void waitServicePageLoad() {                                                                                                // Go to Customer Tab
        waitServiceSpinnerInvsible();
    }

    private void waitServiceSpinnerInvsible() {                                                                                                // Go to Customer Tab
//        dWait.until(ExpectedConditions.invisibilityOfElementLocated(bSpinnerCustomer));
        dWait.until(ExpectedConditions.invisibilityOfElementLocated(bServiceSpinner));
    }

    private static By bVehicleHealthTab = By.xpath("//li[@ui-event-tracker2='vehicle.heath.tab.clicked']");

    private static By bRecallDescriptions = By.xpath("//div[@class='vh_table__col-6 vh_table__description ng-binding']");

    // ******************************** Below method is used to check Scheduled template or Factory Required template ***************************//

    public boolean isScheduledTabPresent() {                                                                                    // Check to see if the Scheduled Tab present
        if (driver.findElement(bScheduledTabLocator).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void clickNextOnServices() {
        if (driver.getCurrentUrl().contains("services")) {
            AM_ClientAndVehiclePage cnvPage = new AM_ClientAndVehiclePage();
            cnvPage.clickOnNext();
        } else {
            clickElementWithException(bNextButtonOnServices);
        }
    }

    public ArrayList<String> getSelectedScheduledServiceItems() {
        List<WebElement> itemList = dWait.until(conditionPresenceList(bScheduledItemList));
        List<WebElement> itemTextList = dWait.until(conditionPresenceList(bScheduledItemTextList));
//        List<WebElement> scheduledItemPriceList = dWait.until(conditionPresenceList(bScheduledItemPriceList));
        selectedScheduledServiceTextList = getPreSelectedServiceItems(itemList, itemTextList);
        return selectedScheduledServiceTextList;
    }

    public ArrayList<String> getSelectedFactoryRequiredServiceItems() {
        List<WebElement> itemList = dWait.until(conditionPresenceList(bFactoryRequiredItemList));
        List<WebElement> itemTextList = dWait.until(conditionPresenceList(bFactoryRequiredItemTextList));
//        List<WebElement> factoryRequiredItemPriceList = dWait.until(conditionPresenceList(bFactoryRequiredItemPriceList));
        selectedFactoryRequiredServiceTextList = getPreSelectedServiceItems(itemList, itemTextList);
        return selectedFactoryRequiredServiceTextList;
    }

    public ArrayList<String> getSelectedOtherUnscheduledServiceItems() {
        List<WebElement> itemList = dWait.until(conditionPresenceList(bunscheduledItemList));
        List<WebElement> itemTextList = dWait.until(conditionPresenceList(bunscheduledItemTextlist));
//        List<WebElement> factoryRequiredItemPriceList = dWait.until(conditionPresenceList(bFactoryRequiredItemPriceList));
        selectedUnscheduledServiceTextList = getPreSelectedServiceItems(itemList, itemTextList);
        return selectedUnscheduledServiceTextList;
    }

    public ArrayList<String> getSelectedRecommendServiceItems() {
        List<WebElement> itemList = dWait.until(conditionPresenceList(brecommendedItemList));
        List<WebElement> itemTextList = dWait.until(conditionPresenceList(brecommendedItemTextList));
//        List<WebElement> factoryRequiredItemPriceList = dWait.until(conditionPresenceList(bFactoryRequiredItemPriceList));
        selectedRecommendedServiceTextList = getPreSelectedServiceItems(itemList, itemTextList);
        return selectedRecommendedServiceTextList;
    }

    // Verify if the service list item have Pre-selected
    private ArrayList<String> getPreSelectedServiceItems(List<WebElement> itemListToCheckSelectedStatus, List<WebElement> itemTextList) {
        String itemPrice = null;
//        int CommonUtils_accumulatedPrice = 0;
        ArrayList<String> selectedServiceList = new ArrayList<String>();
//        System.out.println(" inside itemListToCheckSelectedStatus size  ? --_> " +itemListToCheckSelectedStatus.size() );
//        System.out.println(" insid  itemListToCheckSelectedStatus size  ? --_> " +itemListToCheckSelectedStatus.toString());
        for (int i = 0; i < itemListToCheckSelectedStatus.size(); i++) {
            String cssValue = itemListToCheckSelectedStatus.get(i).getCssValue("background-position");
//            System.out.println(" inside cssValue  ? --_> " + cssValue );
            if (TestParameters.ServiceItemSelectedCSS.contains(cssValue)) {
//                boolean sele = webElementHasClass(itemListToCheckSelectedStatus.get(i),"selected"); System.out.println(" is elected ? --_> " +sele );
//                selectedServiceList.add(itemTextList.get(i).getText());  // update the code on 2019-06-25 by David with below
                selectedServiceList.add(itemTextList.get(i).getAttribute("title").trim());
//					itemPrice = itemPriceList.get(i).getText().trim(); CommonUtils.accumulatedPrice += Double.parseDouble(itemPrice);
            }
        }
        if (selectedServiceList.size() > 0) {
            log.info("===== Pre-Selected Item List =====");
            for (Iterator<String> iservice = selectedServiceList.iterator(); iservice.hasNext(); ) {
                String item = iservice.next();
                log.info(" ->-> " + item);
            }
        } else {
            log.info("===== NO Pre-Selected Items =====");
        }
        return selectedServiceList;
    }

    // Select only for Multi Point Inspection
    public void chooseSingleItemInScheduleService(String check, String serviceItem) {
        selectedItemList = new ArrayList<String>();
        List<WebElement> targetWebElements = driver.findElements(bScheduledItemList);
        List<WebElement> elementsTextList = driver.findElements(bScheduledItemTextList);
        List<WebElement> targetWebElementsPrice = driver.findElements(bScheduledItemPriceList);

        String currentItem = null;
        String itemPrice = null;
        accumulatedPrice = 0;
        int numberOfServices = 1;
        System.out.println("The random number to select Service is :" + numberOfServices);

        int indexList[] = new int[numberOfServices];

        for (int i = 0; i < elementsTextList.size(); i++) {
            if (elementsTextList.get(i).getText().toLowerCase().contains(serviceItem)) {
                indexList[0] = i;
                break;
            }
        }

        StringBuilder itemSet = new StringBuilder();
        itemSet.append(" --> ");

        for (int i = 0; i < indexList.length; i++) {
            itemSet.append(indexList[i] + "\t");
        }

        for (int i = 0; i < numberOfServices; i++) {
            currentItem = elementsTextList.get(indexList[i]).getText();
            itemPrice = targetWebElementsPrice.get(indexList[i]).getText().trim();

            WebElement eTotalGrandAmount = pWait.until(conditionVisible(bTotalGrandAmount));
            currentTotalAmount = CommonMethods.takeOffDollorSign(eTotalGrandAmount);

            checkItem(check, targetWebElements.get(indexList[i]));
            selectedItemList.add(currentItem);
            accumulatedPrice += Double.parseDouble(itemPrice);
        }
//        TestParameters.selectedItemList = selectedItemList;
    }

    // Select Random Service Items from Schedules Services
    public void chooseRandomMumItemInScheduleService(int num) {
        moveToClick(bScheduledTabLocator);
        List<WebElement> targetWebElements = driver.findElements(bScheduledItemList);
        List<WebElement> elementsTextList = driver.findElements(bScheduledItemTextList);
        List<WebElement> targetWebElementsPrice = driver.findElements(bScheduledItemPriceList);
        selectedScheduledServiceTextList = chooseRandomMumItemServicesConerns(num, targetWebElements, elementsTextList, targetWebElementsPrice);
    }

    // Select Random Service Items from factory required  Services
    public void chooseRandomMumItemFactoryRequiredService(int num) {
        List<WebElement> targetWebElements = driver.findElements(bFactoryRequiredItemList);
        List<WebElement> elementsTextList = driver.findElements(bFactoryRequiredItemTextList);
        List<WebElement> targetWebElementsPrice = driver.findElements(bFactoryRequiredItemPriceList);
        selectedFactoryRequiredServiceTextList = chooseRandomMumItemServicesConerns(num, targetWebElements, elementsTextList, targetWebElementsPrice);
//        selectedScheduledServiceTextList = getSelectedFactoryRequiredServiceItems();
    }

    // Select Random Service Items from UnScheduled  Services
    public void chooseRandomMumOtherUnScheduledService(int num) {
        List<WebElement> targetWebElements = driver.findElements(bunscheduledItemList);
        List<WebElement> elementsTextList = driver.findElements(bunscheduledItemTextlist);
        List<WebElement> targetWebElementsPrice = driver.findElements(bunscheduledItemPriceList);
        selectedUnscheduledServiceTextList = chooseRandomMumItemServicesConerns(num, targetWebElements, elementsTextList, targetWebElementsPrice);
//        selectedScheduledServiceTextList = getSelectedFactoryRequiredServiceItems();
    }

    // Select Random Service Items from Recommend  Services
    public void chooseRandomMumRecommendService(int num) {
        List<WebElement> targetWebElements = driver.findElements(brecommendedItemList);
        List<WebElement> elementsTextList = driver.findElements(brecommendedItemTextList);
        List<WebElement> targetWebElementsPrice = driver.findElements(brecommendedItemPriceList);
        selectedRecommendedServiceTextList = chooseRandomMumItemServicesConerns(num, targetWebElements, elementsTextList, targetWebElementsPrice);
//        selectedScheduledServiceTextList = getSelectedFactoryRequiredServiceItems();
    }

    // Select Random Service Items from Schedules Services
    private ArrayList<String> chooseRandomMumItemServicesConerns(int items, List<WebElement> targetWebElements, List<WebElement> elementsTextList, List<WebElement> targetWebElementsPrice) {
        selectedItemList = new ArrayList<String>();
        String currentItem = null;
        String itemPrice = null;
        accumulatedPrice = 0;
        int tsize = targetWebElements.size();
//        System.out.println("The target  number size is :  ...  " + tsize);
        int numberOfServices = CommonMethods.randomNum(1, Math.min(items, tsize));    // This is to get a minimum random number 5 or the list size.
        System.out.println("The random number to select Service is :" + numberOfServices);
        int indexList[] = new int[numberOfServices];

        indexList = CommonMethods.setIndexNumber(targetWebElements, numberOfServices);

        StringBuilder itemSet = new StringBuilder();
        itemSet.append(" --> ");

        for (int i = 0; i < indexList.length; i++) {
            itemSet.append(indexList[i] + "\t");
        }
        System.out.println("Chooose Service : " + itemSet.toString());

        for (int i = 0; i < numberOfServices; i++) {
            currentItem = elementsTextList.get(indexList[i]).getText();
            itemPrice = targetWebElementsPrice.get(indexList[i]).getText().trim();

            WebElement eTotalGrandAmount = pWait.until(conditionVisible(bTotalGrandAmount));
            currentTotalAmount = CommonMethods.takeOffDollorSign(eTotalGrandAmount);
            checkItem("check", targetWebElements.get(indexList[i]));
            selectedItemList.add(currentItem);
            accumulatedPrice += Double.parseDouble(itemPrice);
        }
//        TestParameters.selectedItemList = selectedItemList;
        return selectedItemList;
    }

    private void checkItem(String ch, WebElement element) {            // Select Item
        String cssValue = element.getCssValue("background-position");
        boolean selected = true;
        do {
            element.click();
            cssValue = element.getCssValue("background-position");
            if (ch.equalsIgnoreCase("check")) {
                selected = (TestParameters.ServiceItemSelectedCSS.contains(cssValue) || TestParameters.ConcernItemSelectedCSS.contains(cssValue));
            } else {
                selected = (TestParameters.ServiceItemDeSelectedCSS.contains(cssValue));
            }
        } while (!selected);
    }

    public void clkUnscheduledTab() {                                                                            // Click the Unscheduled Tab
        int i = 0;
        while (!webElementHasClass(driver.findElement(bUnscheduledTabParent), "active") & (i < 10)) {
            moveToClick(driver.findElement(bunscheduledTab));
            sleep(300);
            i++;
        }
    }

    public void removeUnscheduleItem() {                                                                            // Click the Unscheduled Tab
        WebElement btn = dWait.until(conditionVisible(bremoveAllUnScheduledServiceBtn));
        int i = 0;
        while (webElementHasClass(btn, "active") & (i < 10)) {
            actionClick(bremoveAllUnScheduledServiceBtn);
//            btn.click();
            sleep(300);
            i++;
        }
    }

    public void removeScheduleItem() {                                                                            // Click the Unscheduled Tab
        WebElement btn = dWait.until(conditionVisible(bRemoveAllScheduledServiceBtn));
        int i = 0;
        while (webElementHasClass(btn, "active") & (i < 10)) {
//            moveToClick(driver.findElement(bUnscheduledTabParent));
            btn.click();
            sleep(300);
            i++;
        }
    }

    public void clkRecommendedTab() {                                                                            // Click the Unscheduled Tab
        int i = 0;
        while (!webElementHasClass(driver.findElement(brecommendedTabParent), "active") & (i < 10)) {
            moveToClick(driver.findElement(brecommendedTab));
            sleep(300);
            i++;
        }
    }

    public void removeRecommendedItem() {                                                                            // Click the Unscheduled Tab
        WebElement btn = dWait.until(conditionVisible(bremoveAllRecommendedServiceBtn));
        int i = 0;
        while (webElementHasClass(btn, "active") & (i < 10)) {
            moveToClick(driver.findElement(bUnscheduledTabParent));
//            btn.click();
            sleep(300);
            i++;
        }
    }

//    // This is to return WebElement List Text to a String array, for perform service
//    public static String[] getWebElementsTextString(List<WebElement> webElements){
//        String[] stringText = new String[webElements.size()];
//        for(int i = 0; i < webElements.size(); i++){
//            stringText[i] = webElements.get(i).getText();
//        }
//        return stringText;
//    }

    // This is to return WebElement List Text to a String array, for perform service
    private String[] getWebElementsTextString(By locator) {
//        List<WebElement> webElements = driver.findElements(locator);
        List<WebElement> webElements = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        String[] stringText = new String[webElements.size()];
        for (int i = 0; i < webElements.size(); i++) {
            stringText[i] = webElements.get(i).getText();
        }
        return stringText;
    }

    // This is to return WebElement List Text to a String array, for perform service
    public String[] getPerformItemsBeforeRemoving() {
        return getWebElementsTextString(bSelectedAllServiceItemsList);
    }

    public boolean okRemoveAllScheduledItems() {                                                                                        // Verify Remove All Button for Scheduled Service Items
        WebElement removeAllScheduledServiceBtn = driver.findElement(bRemoveAllScheduledServiceBtn);
        return isRemoveAllItemsMatched(removeAllScheduledServiceBtn, selectedScheduledServiceTextList);
    }

    public boolean okRemoveAllFactoryRequiredItems() {                                                                                        // Verify Remove All Button for Scheduled Service Items
        WebElement removeAllScheduledServiceBtn = driver.findElement(bRemoveAllScheduledServiceBtn);
        return isRemoveAllItemsMatched(removeAllScheduledServiceBtn, selectedFactoryRequiredServiceTextList);
    }

    public boolean okRemoveAllUnScheduledItems() {                                                                                        // Verify Remove All Button for Scheduled Service Items
        WebElement removeAllScheduledServiceBtn = driver.findElement(bremoveAllUnScheduledServiceBtn);
        return isRemoveAllItemsMatched(removeAllScheduledServiceBtn, selectedUnscheduledServiceTextList);
    }

    public boolean okRemoveAllRecommendedItems() {                                                                                        // Verify Remove All Button for Scheduled Service Items
        WebElement removeAllScheduledServiceBtn = driver.findElement(bremoveAllRecommendedServiceBtn);
        return isRemoveAllItemsMatched(removeAllScheduledServiceBtn, selectedRecommendedServiceTextList);
    }

    private boolean isRemoveAllItemsMatched(WebElement removeBtn, ArrayList<String> selectedItemsList) {    // This is Based Method to verify RemoveAll button only remove items with the associated Service Items
        beforeRemovingItems = getPerformItemsBeforeRemoving();
        int compareResult = 1;
        int listSize = driver.findElements(selectedAllServiceItemsListLocator).size();

        System.out.println("listSize  ... ....     " + listSize);

        WebElement eTotalGrandAmount = pWait.until(conditionVisible(bTotalGrandAmount));
        currentTotalAmount = CommonMethods.takeOffDollorSign(eTotalGrandAmount);
//        xpathClick(removeBtn);
        moveToClick(removeBtn);

        System.out.println("Clicked the REMOVE ALL button");

        pWait.until(SyncPage.condWebElementListUpdated(driver, listSize, selectedAllServiceItemsListLocator));

        ArrayList<String> arrayDiff = new ArrayList<String>();

        if (SyncPage.isElementPresent(driver, selectedAllServiceItemsListLocator)) {
            afterRemovingItems = getPerformItemsBeforeRemoving();
            arrayDiff = CommonMethods.compareTwoStringArray(beforeRemovingItems, afterRemovingItems);
            compareResult = CommonMethods.compareTwoArrayList(selectedItemsList, arrayDiff);
        } else { //this have no more Selected Service Items
            log.info("No More Selected Service Items, Now verify the removed list.");

            for (int i = 0; i < beforeRemovingItems.length; i++) {
                arrayDiff.add(beforeRemovingItems[i]);
            }
            System.out.print("arrayDiff  ... ....     " + arrayDiff.toString());
            compareResult = CommonMethods.compareTwoArrayList(selectedItemsList, arrayDiff);
        }
        if (compareResult == 0) {
            return true;
        } else {
            System.out.print("compareResult  ... ....     " + compareResult);
            return false;
        }
    }

    public void clickButtonOnServicesPage(String buttonName) {
        switch (buttonName) {
            case "QUOTE":
//                clickElementWithSeconds(bQuoteBtn, 2000);
                clickElementWithException(bQuoteBtn);                
                break;
            case "MENU":
                clickElementWithSeconds(bmenuBtn, 2000);
                sleep(5000);
                break;
            case "NEXT":
                clickElementWithException(bNextButtonOnServices);
                break;
            case "QUOTE_SERVICES":
                clickElementWithException(bQuoteBtnServices);
                break;                
        }
    }

    public void clickMenuShowBtnOnPop() {
//        clickElementWithSeconds(bMaintMenuPDFshowBtn, 3000);
        dWait.until(conditionClick(bMainMenuPDFShowBtn)).click();
    }

    public void clickEditBtnOnPop() {
        clickElementWithSeconds(bEditBtn, 2000);
    }

    public String getFirstScheduledServiceText() {
        List<WebElement> elements = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bScheduledItemTextList));
        return elements.get(0).getText();
    }

    public boolean verifyTabActivated(String tabName) {
        return elementHasAttributeValue(bVehicleHealthTab, "class", "active");
    }

    public boolean recallFound() {
        try {
            driver.findElement(bRecallIcon);
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    public String getRecallNumbers() {
        return driver.findElement(bRecallIcon).getText();
    }

    public String getRecallDescriptionNumbers() {
        return Integer.toString(driver.findElements(bRecallDescriptions).size());
    }

    public void verifyNumOfRecallsAndDescriptions() {
        Assert.assertEquals(tp.numOfRecalls, getRecallNumbers());
        Assert.assertEquals(tp.numOfRecalls, getRecallDescriptionNumbers());
        Assert.assertEquals(tp.numOfRecalls, Integer.toString(driver.findElements(bRecallStatus).size()));
    }

    public void selectMultipointInspection() {clickElementWithException(bMultipointInspectionTitle);}
    
    public void clickScheduledTab() {     
    	moveToClick(bscheduledTab);
    	sleep(3000);
    }

	public void clkFRSTab() {
		// TODO Auto-generated method stub
		
		int i = 0;
        while (!webElementHasClass(driver.findElement(bFRSTabParent), "active") & (i < 10)) {
            moveToClick(driver.findElement(bFRSTab));
            sleep(300);
            i++;
        }
	
	}
	
    public void validateServices() {     
    	sleep(3000);
        CommonMethods.verifyElementExists(driver.findElement(By.xpath("(//p[text()='"+AM_ServicesPage.selectedUnScheduledService+"'])[1]")));        
    }	
}
