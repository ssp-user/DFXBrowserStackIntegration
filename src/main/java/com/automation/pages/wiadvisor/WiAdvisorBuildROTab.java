package com.automation.pages.wiadvisor;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.automation.utils.otherUtils.CommonMethods;


public class WiAdvisorBuildROTab extends WiAdvisorROPage {

    private static Logger log = Logger.getLogger(WiAdvisorBuildROTab.class.getName());
    private static String totalPartsAndLaborPrice;

    private ArrayList<String> selectedFactoryRequiredItemList = new ArrayList<String>();
    private static ArrayList<String> selectedTriageItemList = new ArrayList<String>();
    private static ArrayList<String> selectedDealerRecommendedItemList = new ArrayList<String>();
//    private static String[] performServiceItemTextList;


    //Please Select a Service Category drop down
    private static By bSelectAServiceCategoryButton = By.xpath("//button[contains(@class,'concerns-dropdown') and @data-toggle='dropdown']");

    //please select a service category
    private static By bSelectAServiceCategoryDropOption(String dropDownOption){
        String locator = "//a[contains(@ng-click,'build.selectCustomerCategory') and text()='"+dropDownOption+"']";
        return By.xpath(locator);
    }

    //concern checkbox
    private static By bCheckBoxConcern(String checkBoxName){
        String locator = "//span[text()='"+checkBoxName+"']";
        return By.xpath(locator);
    }

    // Factory mantainence  Text item
    private static By bCheckBoxMaintence(String checkBoxName){
//        String locator = String.format("(//span[contains(text(),'%s')])[1]/..",checkBoxName);
        String locator = String.format("(//div[contains(@ng-repeat,'required')]/div/label/span[contains(text(),'%s')])[1]/..",checkBoxName);
        System.out.println("locator ..  " + locator);
        return By.xpath(locator);
    }

    // Factory mantainence  Text item Status
    private static By bCheckBoxMaintenceStatus(String checkBoxName){
//        String locator = String.format("(//span[contains(text(),'%s')])[1]/../../input",checkBoxName);
        String locator = String.format("(//div[contains(@ng-repeat,'required')]/div/label/span[contains(text(),'%s')])[1]/../../input",checkBoxName);
        return By.xpath(locator);
    }

    // triage Text item
    private static By bCheckTextTriage(String checkBoxName){
        String locator = String.format("(//div[contains(@ng-repeat,'triage')]/div/label/span[contains(text(),'%s')])[1]/..",checkBoxName);
        System.out.println("locator ..  " + locator);
        return By.xpath(locator);
    }

    // triage Text item Status
    private static By bCheckTextTriageStatus(String checkBoxName){
        String locator = String.format("(//div[contains(@ng-repeat,'triage')]/div/label/span[contains(text(),'%s')])[1]/../../input",checkBoxName);
        return By.xpath(locator);
    }

    //general Comments
    private static By bGeneralComments = By.xpath("//textarea[@placeholder='General Comments']");

    private static By bFactoryRequiredCheckBoxListLocator = By.cssSelector("div[ng-repeat*='required' i] input");
    private static By bFfactoryRequiredItemTextList = By.cssSelector("div[ng-repeat*='required.items' i] label span.ng-binding");
    private static By  bFactoryRequiredItemPriceList = By.cssSelector("div[ng-repeat*='required.items' i] div[class*='cost' i]");
    // List of all Triage Item Check box
    private static By bTriageItemCheckBoxList = By.cssSelector("div[ng-repeat*='triage'] input");
    // List of all Triage Item Text
    private static By bTtriageItemTextList = By.cssSelector("div[ng-repeat*='triage'] label");
    // List of all Triage Item Price
    private static By bTriageItemPriceList = By.cssSelector("div[ng-repeat*='triage'] div:last-child");
    private static By  bTotalGrandPrice = By.cssSelector("div.grand-totals span");
    private static By  bPerformServiceItemList = By.cssSelector("div[ng-repeat*='selectedServices'] .service-title>span");
    private static By  bServiceComment = By.cssSelector("textarea[placeholder*='Add comment here' i]");

    private static By  bLaborTypeList = By.xpath("./following-sibling::ul//a");
    private static By  bLaborTypeButton = By.cssSelector("div.service-option-list button.dropdown-toggle");

    //green continue button on BuildRO
    private static By bContinueBRO = By.xpath("//button[@ng-click='repairOrder.continue()']");
    private static By bCheckBoxAllFRmaintance = By.xpath("//input[@id='requiredSelectAll']");
    private static By bCheckBoxAllStatusFRmaintance = By.xpath("//input[@id='requiredSelectAll']");
    private static By bCheckBoxSelectAllStatusDRmaintance = By.xpath("//input[@id='recommendedSelectAll']");
    private static By bCheckBoxSelectAllDRmaintance = By.xpath("//input[@id='recommendedSelectAll']/following-sibling::label");

    //Factory Required Maintenance item check status
    private static By bFactoryMaintenanceItemCheckStatus(String sectionName, String checkItem){
        String locator = "//p[contains(text(),'"+sectionName+"')]/../following-sibling::div//span[contains(text(),'"+checkItem+"')]/../preceding-sibling::input[@checked]";
        return By.xpath(locator);
    }

    //Factory Required Maintenance item
    private static By bFactoryMaintenanceItem(String sectionName, String checkItem){
        String locator = "//p[contains(text(),'"+sectionName+"')]/../following-sibling::div//span[contains(text(),'"+checkItem+"')]/../preceding-sibling::input/following-sibling::label";
        return By.xpath(locator);
    }

    public void updateCommentsUnderGeneralComments(String comments){
        clearAndInputElementWithException(bGeneralComments,comments);
    }

    public void checkFactoryRequiredMaintenance(String checkStatus, String checkItem, String sectionName){
        scrollPageDown(300);
        switch (checkStatus){
            case "check":
                if(!driver.findElements(bFactoryMaintenanceItemCheckStatus(sectionName,checkItem)).isEmpty()){
                    //do nothing, it's already checked
                    System.out.println("<====== Maintenance item '"+checkItem+"' already checked Σ( ° △ °|||)︴ ======>");
                }else{
                    clickElementWithException(bFactoryMaintenanceItem(sectionName,checkItem));
                    System.out.println("<====== Maintenance item '"+checkItem+"' checked (=￣ω￣=) ======>");
                }
                break;
            case "uncheck":
                if(!driver.findElements(bFactoryMaintenanceItemCheckStatus(sectionName,checkItem)).isEmpty()){
                    clickElementWithException(bFactoryMaintenanceItem(sectionName,checkItem));
                    System.out.println("<====== Maintenance item '"+checkItem+"' unchecked (=￣ω￣=) ======>");
                }else{
                    //do nothing, it's already unchecked
                    System.out.println("<====== Maintenance item '"+checkItem+"' already unchecked Σ( ° △ °|||)︴ ======>");
                }
                break;
        }
    }

    public void checkOnCheckBoxInFRMaintence(String checks, String checkItem){
//        scrollPageDown(300);
        WebElement element = dWait.until(conditionVisible(bCheckBoxMaintence(checkItem)));
        WebElement elemenTStatus = driver.findElement(bCheckBoxMaintenceStatus(checkItem));
        scrollElementToCenterView(element);
        if (checks.equalsIgnoreCase("check")) {
            if (!elemenTStatus.isSelected()) {
                element.click();
            }
        }else if((checks.equalsIgnoreCase("uncheck"))){
            if (elemenTStatus.isSelected()) {
                element.click();
            }
        }

    }


    public void checkOnTriageItems(String checks, String checkItem){
//        scrollPageDown(300);
        try{
            WebElement element = sWait.until(conditionVisible(bCheckTextTriage(checkItem)));
            WebElement elemenTStatus = driver.findElement(bCheckTextTriageStatus(checkItem));
            scrollElementToCenterView(element);
            if (checks.equalsIgnoreCase("check")) {
                if (!elemenTStatus.isSelected()) {
                    element.click();
                }
            }else if((checks.equalsIgnoreCase("uncheck"))){
                if (elemenTStatus.isSelected()) {
                    element.click();
                }
            }
        }catch (TimeoutException tc ){
            randomChooseItemsInTtriage(1);
        }

    }



    public void checkOnCheckBoxInDRMaintence(String checks, String checkItem){
        scrollPageDown(300);
        WebElement element = dWait.until(conditionVisible(bCheckBoxMaintence(checkItem)));
        WebElement elemenTStatus = driver.findElement(bCheckBoxMaintenceStatus(checkItem));
        if (checkItem.equalsIgnoreCase("Select All")){
            element = dWait.until(conditionVisible(bCheckBoxSelectAllDRmaintance));
            elemenTStatus = driver.findElement(bCheckBoxSelectAllStatusDRmaintance);
        }
        if (checks.equalsIgnoreCase("check")) {
            if (!elemenTStatus.isSelected()) {
                element.click();
            }
        }else if((checks.equalsIgnoreCase("uncheck"))){
            if (elemenTStatus.isSelected()) {
                element.click();
            }
        }

    }


    public String[] getItemsUnderFactoryRequireMainteance(){
        List< WebElement > iTems = driver.findElements(bFactoryMaintenanceItem("Factory Required Maintenance",""));
        int iTemsCount = iTems.size();
        String[] itemList = {"1","2","3"};
        return itemList;
    }

    public boolean verifyItemShow(String itemStatus){
        if(itemStatus.equals("show")){


        }else{

        }
        return true;
    }

    public void clickOnContinueBuildRO(){
        clickElementWithException(bContinueBRO);
    }

    public void clickServiceCategory(String dropDownName){
        switch (dropDownName){
            case "Please Select a Service Category":
                clickElementWithException(bSelectAServiceCategoryButton);
                break;
        }
    }

    public void selectServiceCategory(String dropDownOption){
        clickElementWithException(bSelectAServiceCategoryDropOption(dropDownOption));
    }

    public void clickOnConcernCheckBox(String concernCheckBoxName){
        clickElementWithException(bCheckBoxConcern(concernCheckBoxName));
        System.out.println("<====== "+concernCheckBoxName+" checked ======>");
    }

    public ArrayList<String>  getSelectedFactoryRequiredItems(){
        List<WebElement> factoryRequiredCheckBoxList = dWait.until(conditionPresenceList(bFactoryRequiredCheckBoxListLocator));
        List<WebElement> factoryRequiredItemTextList = dWait.until(conditionPresenceList(bFfactoryRequiredItemTextList));
        selectedFactoryRequiredItemList = getPreSelectedItems(factoryRequiredCheckBoxList, factoryRequiredItemTextList);
        return selectedFactoryRequiredItemList;
    }

    public ArrayList<String>  getSelectedTriageItems(){
        List<WebElement> elTriageItemCheckBoxList = driver.findElements(bTriageItemCheckBoxList);
        List<WebElement> elTtriageItemTextList = getTtriageItemTextElements();
        if (elTtriageItemTextList.size() < 1){
            selectedTriageItemList.add("No===Ttriage");
        }else{
            selectedTriageItemList = getPreSelectedItems(elTriageItemCheckBoxList, elTtriageItemTextList);
        }
        return selectedTriageItemList;
    }

    public List<WebElement>  getTtriageItemTextElements(){
        List<WebElement> elTtriageItemTextList = driver.findElements(bTtriageItemTextList);
        return  elTtriageItemTextList;
    }

    // Get Pre-Selected Items
    private static ArrayList<String> getPreSelectedItems(List<WebElement> itemListToCheckSelectedStatus, List<WebElement> itemTextList){
        ArrayList<String> selectedList = new ArrayList<String>();
        boolean result = false;
        int attempt = 0;
        do{
            try{
                selectedList = new ArrayList<String>();
                for(int i = 0; i < itemListToCheckSelectedStatus.size(); i++){
                    if(itemListToCheckSelectedStatus.get(i).isSelected()){
                        selectedList.add(itemTextList.get(i).getText());
                    }
                }
                result = true;
                break;
            }catch (StaleElementReferenceException e){
                result = false;
            }
            attempt++;
        } while(!result && attempt < 5);

        if(attempt >= 5){
            System.out.println("Tried over 5 times on getting the pre-selected item list");
        }

        if(selectedList.size() > 0){
            log.info("===== Pre-Selected Item List =====");
            for(Iterator<String> ii = selectedList.iterator(); ii.hasNext();){
                log.info(" ->-> " + ii.next());
            }
        } else {
            log.info("===== NO Pre-Selected Items =====");
        }
        return selectedList;
    }

    public String[]  getPerformServiceTextString(){
        List<WebElement> performServiceItemList = dWait.until(conditionPresenceList(bPerformServiceItemList));
        String[] performServiceItemTextList  = getWebElementsTextString(performServiceItemList);
        return performServiceItemTextList;
    }


    // This is to return WebElement List Text to a String array
    public  String[] getWebElementsTextString(List<WebElement> elementList){
        boolean result = false;
        String[] strList = null;
        int attempt = 0;
        do{
            try{
                strList = new String[elementList.size()];
                for(int i = 0; i < elementList.size(); i++){

//					strList[i] = numberHandler(elementList.get(i));
                    strList[i] = elementList.get(i).getText().trim();
                }
                result = true;
                break;
            } catch (ArrayIndexOutOfBoundsException e){
                log.error("Array Index Out Of Bounds Exception Occurred" + e.getMessage());
                result = false;
            } catch (StaleElementReferenceException e){
                log.error("Stale Element Reference Exception Occurred" + e.getMessage());
                result = false;
            } catch (IndexOutOfBoundsException e){
                log.error("Index Out Of Bounds Exception Occurred" + e.getMessage());
                result = false;

            }
            attempt++;
        } while(!result && attempt < 5);


        if(attempt >= 5){
            log.error("Attempt over 5 on retrieving item list");
        }

        log.error("Completed on retrieving item list");
        return strList;

    }


    public void  randomChooseItemsInFRMaintence(int num){
          ArrayList<String> selectedItemList = new ArrayList<String>();
        List<WebElement> fRMCheckBoxList = dWait.until(conditionPresenceList(bFactoryRequiredCheckBoxListLocator));
        List<WebElement> fRMItemTextList = dWait.until(conditionPresenceList(bFfactoryRequiredItemTextList));
        List<WebElement> fRMItemPriceList = dWait.until(conditionPresenceList(bFactoryRequiredItemPriceList));
        selectedFactoryRequiredItemList = randomChooseItems(num , fRMCheckBoxList, fRMItemTextList , fRMItemPriceList, bTotalGrandPrice );
        CommonMethods.sleep(2000);
        List<WebElement> performServiceItemList = dWait.until(conditionPresenceList(bPerformServiceItemList));
        if (performServiceItemList.size() == 0){
            System.out.println("The Selected item list need refreshed ! ");
             fRMCheckBoxList = dWait.until(conditionPresenceList(bFactoryRequiredCheckBoxListLocator));
             fRMItemTextList = dWait.until(conditionPresenceList(bFfactoryRequiredItemTextList));
             fRMItemPriceList = dWait.until(conditionPresenceList(bFactoryRequiredItemPriceList));
            uncheckItem(fRMCheckBoxList, fRMItemTextList);
            selectedFactoryRequiredItemList = randomChooseItems(num , fRMCheckBoxList, fRMItemTextList , fRMItemPriceList, bTotalGrandPrice );
        }

    }

    public void  randomChooseItemsInTtriage(int  num){
        ArrayList<String> selectedItemList = new ArrayList<String>();
        List<WebElement> elTriageItemCheckBoxList = dWait.until(conditionPresenceList(bTriageItemCheckBoxList));
        List<WebElement> elTtriageItemTextList = dWait.until(conditionPresenceList(bTtriageItemTextList));
        List<WebElement> elTriageItemPriceList = dWait.until(conditionPresenceList(bTriageItemPriceList));
//        selectedTriageItemList = randomChooseItems(num , elTriageItemCheckBoxList, elTtriageItemTextList , elTriageItemPriceList, bTotalGrandPrice );
        selectedTriageItemList = randomChooseItems(num , elTtriageItemTextList, elTtriageItemTextList , elTriageItemPriceList, bTotalGrandPrice );
    }


    /**
     * Select Items
     * @param targetElements Target Element Check box to select
     * @param elementTextList Selected item text name
     * @param elementPriceList Selected item Price
     * @param bTotalGrandAmount Total Grand Amount
     *
     */
    private ArrayList<String> randomChooseItems(int  num , List<WebElement> targetElements, List<WebElement> elementTextList, List<WebElement> elementPriceList, By bTotalGrandAmount ){
        ArrayList<String> selectedItemList = new ArrayList<String>();
//		int numberOfServices = randomNum(1, Math.min(5, targetElements.size()));	// Return random number from 1 to 5(or less than 5)
//		LogUtils.logActions("Select random number of service : " + numberOfServices);

        int numberOfServices = num;
            numberOfServices = 1;
        int indexList[] = new int[numberOfServices];

        for(int i = 0; i < elementTextList.size(); i++){
            if(elementTextList.get(i).getText().toLowerCase().contains("multi-point inspection")){
                indexList[0] = i;
                break;
            }
        }

        StringBuilder itemSet = new StringBuilder();
        itemSet.append(" --> ");
        for(int i =0; i < indexList.length; i++){
            itemSet.append(indexList[i] + "\t");
        }
        System.out.println(itemSet.toString());

        String currentItem = null;
        String itemPrice = null;
        boolean result = false;
        int attempt = 0;
        for(int i = 0; i < numberOfServices; i++){
            attempt = 0;
            do{
                try{

                    WebElement eTotalGrandAmount = pWait.until(conditionVisible(bTotalGrandAmount));
                    String totalAmount = CommonMethods.takeOffDollorSign(eTotalGrandAmount);
                    scrollElementToCenterViewClick(targetElements.get(indexList[i]));
                    currentItem = elementTextList.get(indexList[i]).getText();
                    System.out.println(elementPriceList.get(indexList[i]).getText());
                    itemPrice = CommonMethods.takeOffDollorSign(elementPriceList.get(indexList[i]));

                    if(!itemPrice.equals("0.00")){
//                        wait.until(SyncPage.condTotalAmountChanged(totalGrandAmount));
                         pWait.until(conditionTextNotToBe(bTotalGrandAmount,totalAmount));
                    } else {
                        pWait.until(conditionElementTextNotEmpty(bTotalGrandAmount));
                    }
                    selectedItemList.add(currentItem);
                    result = true;
                    break;
                } catch (StaleElementReferenceException e){
//                    System.out.println("Stale Element Reference Exception occurred but handled. " + e.getMessage());
                    attempt++;
                    result = false;
                }
            } while (!result && attempt < 5);
        }
        if(attempt >= 5){
            log.error("An exception occurred at selecting services items, attempt over 5 times");
        }

        return selectedItemList;
    }

    private void itemRefreshedHandler(int num){
        CommonMethods.sleep(2000);
        List<WebElement> performServiceItemList = dWait.until(conditionPresenceList(bPerformServiceItemList));
        if(performServiceItemList.size()==0){
            System.out.println("The Selected item list refreshed !");
            List<WebElement> factoryRequiredCheckBoxList = dWait.until(conditionPresenceList(bFactoryRequiredCheckBoxListLocator));
            List<WebElement> factoryRequiredItemTextList = dWait.until(conditionPresenceList(bFfactoryRequiredItemTextList));
            uncheckItem(factoryRequiredCheckBoxList, factoryRequiredItemTextList);
            randomChooseItemsInFRMaintence(num);
//            chooseItems(driver, factoryRequiredItemTextList, factoryRequiredItemTextList, factoryRequiredItemPriceList, totalGrandPrice);
        }
    }

    /***
     * Un-Selected the checkbox from a WebElement List
     * @param elementList
     */
    public static void uncheckItem(List<WebElement> elementList, List<WebElement> elementTextList){
        for(int i = 0; i < elementList.size(); i++){
            while(elementList.get(i).isSelected()){
                elementTextList.get(i).click();
            }
        }
    }


    public void clearServiceComment(){
        List<WebElement> cmntService = driver.findElements(bServiceComment);
        for(WebElement com:cmntService)  {
            com.clear();
        }
    }

    public void selectLaborType(int num){
            List<WebElement>  elaborTypeButtons = dWait.until(conditionPresenceList(bLaborTypeButton));
            int size = elaborTypeButtons.size();
        for(int i = 0; i < size; i++){
            scrollElementToCenterViewClick(elaborTypeButtons.get(i));
            List<WebElement> typeList = elaborTypeButtons.get(i).findElements(bLaborTypeList);
            WebElement eType = dWait.until(conditionVisible(typeList.get(num)));
            if(!eType.getText().equals("Customer Pay")){
                log.warn("Choosing Labor Type");
            }
            eType.click();
        }
    }

}
