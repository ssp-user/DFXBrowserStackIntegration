package com.automation.pages.appointment_manager;

import com.automation.pages.common.WebPage;
import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class AM_FramePage extends WebPage {


    protected  ArrayList<String> selectedItemList = null;

    public static String customerName;
    public static int waiter, shuttle, rental, loaner, ownRide, pickup, repeatRepair, specialParts;
//    private static WebElement header;
    private static final By bHeader = By.cssSelector("header .dropdown");

    //   Dashboard Tab
    protected static final By bDashboardTab = By.id("header-back-to-dashboard-from-appointment-1500987066");
    //   Client & Vehicle Tab
    protected static final By bClientVehicleTab = By.id("header-customer-tab-button-1500986977");
        //   Client & Vehicle Link same as  lient & Vehicle Tab
    protected static final By bClientVehicleLink = By.xpath("//a[.='Client & Vehicle']");
     // historyTab;
    protected static final By bHistoryTab = By.xpath("//a[.='History']/..");
    // history Link
    protected static final By bHistoryLink = By.id("header-history-tab-button-1500986987");
    // Service Tab
    protected static final By bServiceTab = By.id("header-services-tab-button-1500986993");
    //service tab on the header
    // Service Link  .. same as Service Tab
    protected static final By bServiceLink = By.xpath("//a[.='Services']");
    // Time & Advisor Tab
    protected static final By bTimeAdvisorTab = By.id("header-advisor-tab-button-1500987000");
    // Time & Advisor  Link  .. same as Time & Advisor  Tab
    protected static final By bTimeAdvisorLink = By.xpath("//a[.='Time & Advisor']");

    // Summary Tab
    protected static final By bSummaryTab = By.id("header-summary-tab-button-1500987007");
    // Summary  Link  .. same as Time & Advisor  Tab
    protected static final By bSummaryLink = By.xpath("//a[.='Summary']");

    // Logout  button
    protected static final By bLogout = By.cssSelector("svg.icon--logout");

    protected static By signoutLinkLocator = By.cssSelector("ul.dropdown-menu a:nth-child(2)");

//    private static WebElement signoutLink;
     protected static By bSignoutLink = By.cssSelector("ul.dropdown-menu a:nth-child(2)");
     protected static By bDuplicateCustomerMessage = By.xpath("//*[contains(text(),'Entered customer information matched other customer(s). What do you want to do?')]");
//    private void bDashboardTab() {
//        moveToClick(bClientVehicleTab);
////         driver.findElement(bCustomerTab).click();
//    }
//
//    private void goToClientVehicleTab() {
//        moveToClick(bClientVehicleTab);
////         driver.findElement(bCustomerTab).click();
//    }
//
//    private void goToHistoryTab() {                                                      // Go to History Tab
//        dWait.until(conditionVisible(bHistoryTab)).click();
//    }
//
//    private void goToServiceTab() {                                                    // Go to Service Tab
//        dWait.until(conditionVisible(bServiceTab)).click();
//    }
//
//    private void goToTimeAdvisorTab() {                                              // Go to Walk Round Tab
//        dWait.until(conditionVisible(bTimeAdvisorTab)).click();
//    }
//
//    private void goToSummaryTab() {                                                   // Go to Assign R.O. Tab
//        dWait.until(conditionVisible(bSummaryTab)).click();  }

    public void signOut() {                                                                                                            // Sign Out
        pWait.until(conditionVisible(bLogout)).click();
    }

    public void signOutAM(){
        try{
            WebElement eHead = driver.findElement(bHeader);
            do{
                eHead.click();
            } while(SyncPage.isElementPresentAndVisible(signoutLinkLocator));					// Before 2.9
            sleep(1500);
            driver.findElement(bSignoutLink).click();
        } catch (NoSuchElementException e){
            pWait.until(conditionVisible(bLogout)).click();				// 2.9
        }

    }

    public void goToTheTabInAM(String name) {
        String tab = name.toUpperCase();
    	sleep(5000);         
        switch (tab) {
            case "DASHBOARD":
                moveToClick(bClientVehicleTab);
                break;
            case "CLIENT & VEHICLE":
                moveToClick(bClientVehicleTab);
                break;
            case "HISTORY":
                dWait.until(conditionVisible(bHistoryTab)).click();
                break;
            case "SERVICES":
                dWait.until(conditionVisible(bServiceTab)).click();
                break;
            case "TIME & ADVISOR":
                dWait.until(conditionVisible(bTimeAdvisorTab)).click();
                break;
            case "SUMMARY":
                dWait.until(conditionVisible(bSummaryTab)).click();
                break;
        }
    }


    protected ArrayList<String> chooseRandomServiceItems(int items ,List<WebElement> targetElements, List<WebElement> elementTextList, List<WebElement> elementPriceList, WebElement totalGrandAmount){

        WebElement div = targetElements.get(0).findElement(By.xpath(".//../../.."));
        int numberOfServices = CommonMethods.randomNum(1, Math.min(items, targetElements.size()));	// it originally 5, now change to 3.
        int indexList[] = new int[numberOfServices];
        indexList = CommonMethods.setIndexNumber(targetElements, numberOfServices);

//		numberOfServices = 2;
//		int indexList[] = {8, 1};

        indexList = CommonMethods.sortArrayInDesc(indexList);

//        LogUtils.logMsgOnly("+++++ Random Select Item Number Set +++++");
        StringBuilder itemSet = new StringBuilder();
        itemSet.append(" --> ");
        for(int i =0; i < indexList.length; i++){
            itemSet.append(indexList[i] + "\t");
        }
//        LogUtils.logMsgOnly(itemSet.toString());
        selectedItemList = new ArrayList<String>();
        String currentItem = null;
        String itemPrice = null;

        boolean result = false;
        int attempt = 0;
        for(int i = 0; i < numberOfServices; i++){
            attempt = 0;
            do{
                try{
                    CommonMethods.scrollToTopOfDiv(driver, div);
                    getCurrentTotalAmount(totalGrandAmount);
                    currentItem = elementTextList.get(indexList[i]).getText();
                    itemPrice = elementPriceList.get(indexList[i]).getText();
                    CommonMethods.scrollElementIntoCenterOfView(targetElements.get(indexList[i]), div);
//					selectItem(driver, targetElements.get(indexList[i]));
                    selectOneItem(targetElements.get(indexList[i]),elementTextList.get(indexList[i]),currentItem);

                    if(!itemPrice.equals("0.00")){
//                        LogUtils.logMsgOnly("The select item is : " + currentItem + " Price is : " + itemPrice);
                        pWait.until(SyncPage.condTotalAmountChanged(totalGrandAmount));
//                        LogUtils.logMsgOnly("total Amount has changed, now the total grand amount is " + totalGrandAmount.getText().trim());

                    } else {
//                        LogUtils.logMsgOnly("The item : " + currentItem + " Price is : " + itemPrice);
                        pWait.until(SyncPage.condWebElementTextDisplayed(totalGrandAmount));
                    }
                    selectedItemList.add(currentItem);
                    result = true;
                    break;
                } catch(StaleElementReferenceException e){
//                    LogUtils.logError("Stale Element Reference Exception Occurred but handled. " + e.getMessage());
                    attempt++;
                    result = false;
                } catch(WebDriverException e){
                    if(e.getMessage().contains("Element is not clickable")){
//                        LogUtils.logError("Element is not clickable exception occurred and handled. " + e.getMessage());
                        attempt++;
                        result = false;
                    } else {
                        throw new WebDriverException(e.getMessage());
                    }
                }
            } while(!result && attempt < 5);

            if(attempt >= 5) {
//                LogUtils.logError("An exception occurred at selecting services items, attempt over 5 times");
            }
        }
        CommonMethods.scrollToTopOfDiv(driver, div);
        return selectedItemList;
    }


    protected ArrayList<String> chooseOneServiceItem( String serviceItem, List<WebElement> targetElements, List<WebElement> elementTextList, List<WebElement> elementPriceList, WebElement totalGrandAmount){

        WebElement div = targetElements.get(0).findElement(By.xpath(".//../../.."));
        int numberOfServices = 1;

        int indexList[] = new int[numberOfServices];
        for(int i = 0; i < elementTextList.size(); i++){
            if(elementTextList.get(i).getText().toLowerCase().contains(serviceItem)){
                indexList[0] = i;
                break;
            }
        }
        StringBuilder itemSet = new StringBuilder();
        itemSet.append(" --> ");
        for(int i =0; i < indexList.length; i++){
            itemSet.append(indexList[i] + "\t");
        }
//        LogUtils.logMsgOnly(itemSet.toString());
        selectedItemList = new ArrayList<String>();
        String currentItem = null;
        String itemPrice = null;

        boolean result = false;
        int attempt = 0;
        CommonMethods.scrollToTopOfDiv(driver, div);
        for(int i = 0; i < numberOfServices; i++){
            attempt = 0;
            do{
                try{
                    CommonMethods.scrollElementIntoCenterOfView(targetElements.get(indexList[i]), div);
                    getCurrentTotalAmount(totalGrandAmount);
                    currentItem = elementTextList.get(indexList[i]).getText();
                    itemPrice = elementPriceList.get(indexList[i]).getText();
                    targetElements.get(indexList[i]).click();
                    if(!itemPrice.equals("0.00")){
//                        LogUtils.logMsgOnly("The select item is : " + currentItem + " Price is : " + itemPrice);
                        pWait.until(SyncPage.condTotalAmountChanged(totalGrandAmount));
                    } else {
//                        LogUtils.logMsgOnly("The item : " + currentItem + " Price is : " + itemPrice);
                        pWait.until(SyncPage.condWebElementTextDisplayed(totalGrandAmount));
                    }
                    selectedItemList.add(currentItem);
                    result = true;
                    break;
                } catch(StaleElementReferenceException e){
                    attempt++;
                    result = false;
                } catch(WebDriverException e){
                    if(e.getMessage().contains("Element is not clickable")){
                        attempt++;
                        result = false;
                    } else {
                        throw new WebDriverException(e.getMessage());
                    }
                }
            } while(!result && attempt < 5);

            if(attempt >= 5) {
                System.err.println("An exception occurred at selecting services items, attempt over 5 times");
            }
        }
        CommonMethods.scrollToTopOfDiv(driver, div);
        return selectedItemList;
    }

    private void getCurrentTotalAmount(WebElement totalAmount){
        String price = null;
        try{
            price = totalAmount.getText().trim();
        } catch (NoSuchElementException e){
            price = "0.00";
        }

        if(price.isEmpty()){
            CommonMethods.currentTotalAmount = "0.00";
        } else {
            CommonMethods.currentTotalAmount = price;
        }
    }

    private void selectOneItem(WebElement elementClick,WebElement elementItem,String textItem){
        String vTextItem = elementItem.getText().trim();
        do{
//			element.click();
            jsClick(elementClick);
//            LogUtils.logMsgOnly(" service Item clicked");
            CommonMethods.sleep(300);
            try{
                vTextItem = elementItem.getText().trim();
            } catch (Exception e){
                vTextItem = "###**";
            }
        } while(vTextItem.equalsIgnoreCase(textItem));

    }

    public void verifyElementExists(String field){
        switch (field) {
        case "DuplicateCustomer":
            dWait.until(conditionVisible(bDuplicateCustomerMessage));        	
        	CommonMethods.verifyElementExists(driver.findElement(bDuplicateCustomerMessage));  	
            break;
        default:
        	break;    	
        }    
    }     
    
}


