package com.automation.pages.tech_inspection.ServiceTab;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;

public class PartListTab extends WebPage {

    InspectionTab iTab = new InspectionTab();
    RecommendationTab rTab = new RecommendationTab();

    private static By bPartsListTab = By.id("tbPartsList");

    private static By bQtySheetHeader = By.id("jqgh_PartsTabGrid_Quantity");

    //parts list cell on recommendation tab
    private static By bQTYCell(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartPickListGrid_Quantity'])["+n+"]";
        return By.xpath(checkLocator);
    }
    private static By bQTYField = By.xpath("//input[@name='Quantity']");

    private static By bBOCell(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartPickListGrid_BoQuantity'])["+n+"]";
        return By.xpath(checkLocator);
    }
    private static By bBOField = By.xpath("//input[@name='BoQuantity']");

    private static By bPartCell(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartPickListGrid_PartNumber'])["+n+"]";
        return By.xpath(checkLocator);
    }
    private static By bPartField = By.xpath("//select[@name='PartNumber']/following-sibling::div/div/input");

    private static By bPartFieldValue = By.xpath("//select[@name='PartNumber']/following-sibling::div/div/div");

    private static By bDescriptionCell(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartPickListGrid_Description'])["+n+"]";
        return By.xpath(checkLocator);
    }
    private static By bDescriptionField = By.xpath("//select[@name='Description']/following-sibling::div/div/input");
    private static By bDescriptionFieldList = By.xpath("//select[@name='Description']/following-sibling::div/div/div");

    private static By bBinCell(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartPickListGrid_BinShelf'])["+n+"]";
        return By.xpath(checkLocator);
    }
    private static By bBinField = By.xpath("//input[@name='BinShelf']");

    private static By bOHCell(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartPickListGrid_OhQuantity'])["+n+"]";
        return By.xpath(checkLocator);
    }
    private static By bOHField = By.xpath("//input[@name='OhQuantity']");

    private static By bCostCell(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartPickListGrid_WholeSalePrice'])["+n+"]";
        return By.xpath(checkLocator);
    }
    private static By bCostField = By.xpath("//input[@name='WholeSalePrice']");

    private static By bListCell(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartPickListGrid_ListPrice'])["+n+"]";
        return By.xpath(checkLocator);
    }
    private static By bListField = By.xpath("//input[@name='ListPrice']");

    private static By bSaleCell(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartPickListGrid_SalePrice'])["+n+"]";
        return By.xpath(checkLocator);
    }
    private static By bSaleField = By.xpath("//input[@name='SalePrice']");

    private static By bExtCell(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartPickListGrid_ExtSalePrice'])["+n+"]";
        return By.xpath(checkLocator);
    }
    private static By bExtField = By.xpath("//input[@name='ExtSalePrice']");

    //parts grid price total
    private static By bTotalPriceForPartsGrid = By.id("TotalPartPickListLbl");

    //parts grid price total on parts grid tab
    private static By bTotalPriceForPartsGridOnPartsGridTab = By.id("partsPriceTotal");

    //parts grid save and close
    private static By bSaveAndClosePartsGrid = By.id("partPickListSaveBtn");

    //parts grid completed and close
    private static By bCompletedAndClosePartsGrid = By.id("partPickListSaveBtn");

    //parts grid completed and close
    //private static By bCompletedAndClosePartsList = By.id("PartsTab_btnPartsTabSaveComplete");
//    private static By bCompletedAndClosePartsList = By.xpath("//*[contains(@id,'PartsTab_btnPartsTab') and contains(@id,'Complete') and @value='Complete and Close']");
    private static By bCompletedAndClosePartsList = By.id("PartsTab_btnPartsTabComplete");

    //PARTS REQUEST COMPLETED dialog
    private static By bPartsRequestPromptYes = By.id("yes_dialogBtn");

    //parts grid cell on parts list tab
    private static By bTypeCellONPartsList(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartsTabGrid_IsRecommendationType'])["+n+"]";
        return By.xpath(checkLocator);
    }

    private static By bQTYCellONPartsList(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartsTabGrid_Quantity'])["+n+"]";
        return By.xpath(checkLocator);
    }

    private static By bDescriptionCellONPartsList(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartsTabGrid_Description'])["+n+"]";
        return By.xpath(checkLocator);
    }

    private static By bStatusCellONPartsList(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartsTabGrid_Status'])["+n+"]";
        return By.xpath(checkLocator);
    }
    private static By bStatusFieldONPartsList = By.xpath("//td[@aria-describedby='PartsTabGrid_Status']/span");

    private static By bSaleCellONPartsList(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartsTabGrid_SalePrice'])["+n+"]";
        return By.xpath(checkLocator);
    }

    private static By bExtSaleCellONPartsList(int rowNumber){
        String n = Integer.toString(rowNumber);
        String checkLocator = "(//td[@aria-describedby='PartsTabGrid_ExtSalePrice'])["+n+"]";
        return By.xpath(checkLocator);
    }

    //save button on part list
    private static By bSaveOnPartsList = By.id("btnSaveParts");

    //Not in Stock associated with description
    private static By bNotInStockWithDescription (String description){
        String checkLocator = "//span[contains(text(),'"+description+"')]/../../following-sibling::tr//div[contains(text(),'Not In Stock')]";
        return By.xpath(checkLocator);
    }

    private static By bNotInStockWithDescriptionStatus (String description){
        String checkLocator = "//span[contains(text(),'"+description+"')]/parent::td/parent::tr/following-sibling::tr/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td/input";
        return By.xpath(checkLocator);
    }

    private static By bSeePartsListWithDescriptionStatus (String description){
        String checkLocator = "//span[contains(text(),'"+description+"')]/parent::td/parent::tr/following-sibling::tr/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/input";
        return By.xpath(checkLocator);
    }

    private static By bDeferredWithDescription (String description){
        String checkLocator = "//span[contains(text(),'"+description+"')]/parent::td/parent::tr/following-sibling::tr/td/table/tbody/tr/td[3]/table/tbody/tr[2]/td/label";
        return By.xpath(checkLocator);
    }

    private static By bDeferredDropDownWithDescription (String description, String drowDownSelection){
        String checkLocator = "//span[contains(text(),'"+description+"')]/parent::td/parent::tr/following-sibling::tr//label[contains(text(),'"+drowDownSelection+"')]";
        return By.xpath(checkLocator);
    }

    //this parts list title is to click in order to update the price
//    private static By bPartsListTitle = By.xpath("//span[@class='partPickListHeader']");
    private static By bPartsListTitle = By.id("RecommendationsTab_grdPartHeader_ctl02_lblPartListHeader");

    //this is click to update the price on parts list grid
    public void clickOnTableGrid(){
        clickElementWithException(bPartsListTitle);
    }

    //this is click to update the price on parts list tab
    public void clickOnPartsGrid(){
        dWait.until(conditionVisible(bTotalPriceForPartsGridOnPartsGridTab)).click();
    }

    public void setQTY(String txt, String rowNumber){
        int n = Integer.parseInt(rowNumber);
        rTab.clickElementWithExceptionOnRecommendationPage(bQTYCell(n));
//        if(driver.findElements(bQTYField).isEmpty()){
//            clickElementWithException(bQTYCell(n));
//        }
//        clickElementWithException(bQTYCell(n));
        clearAndInputElementWithException(bQTYField, txt);
        clickOnTableGrid();
    }

    public void setBO(String txt,String rowNumber){
        int n = Integer.parseInt(rowNumber);
        dWait.until(conditionClick(bBOCell(n))).click();
        clearAndInputElementWithException(bBOField,txt);
    }

    public void setPart(String txt,String rowNumber){
        long startTime = System.currentTimeMillis();
        int n = Integer.parseInt(rowNumber);
        clickElementWithException(bPartCell(n));
        clearAndInputElementWithException(bPartField,txt);
        while(!partValue().equals(txt)){
            clickElementWithException(bPartCell(n));
            clearAndInputElementWithException(bPartField,txt);
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 30000){
                break;
            }
        }
    }

    public String partValue(){
        String partValue = "";
        try{
            partValue = driver.findElement(bPartFieldValue).getAttribute("data-value");
        }catch (WebDriverException e){
            //part grid clicked away
        }
        return partValue;
    }

    public void setDescription(String txt,String rowNumber){
        int n = Integer.parseInt(rowNumber);
        dWait.until(conditionClick(bDescriptionCell(n))).click();
        clearAndInputElementWithException(bDescriptionField,txt);
    }

    public void setBin(String txt,String rowNumber){
        int n = Integer.parseInt(rowNumber);
        dWait.until(conditionClick(bBinCell(n))).click();
        clearAndInputElementWithException(bBinField,txt);
    }

    public void setOH(String txt,String rowNumber){
        int n = Integer.parseInt(rowNumber);
        dWait.until(conditionClick(bOHCell(n))).click();
        clearAndInputElementWithException(bOHField,txt);
    }

    public void setCost(String txt,String rowNumber){
        int n = Integer.parseInt(rowNumber);
        dWait.until(conditionClick(bCostCell(n))).click();
        clearAndInputElementWithException(bCostField,txt);
        clickOnTableGrid();
    }

    public void setList(String txt,String rowNumber){
        int n = Integer.parseInt(rowNumber);
        dWait.until(conditionClick(bListCell(n))).click();
        clearAndInputElementWithException(bListField,txt);
        clickOnTableGrid();
    }

    public void setSale(String txt,String rowNumber){
        int n = Integer.parseInt(rowNumber);
        dWait.until(conditionClick(bSaleCell(n))).click();
        clearAndInputElementWithException(bSaleField,txt);
        clickOnTableGrid();
    }

    public void setExtSale(String txt,String rowNumber){
        int n = Integer.parseInt(rowNumber);
        dWait.until(conditionClick(bExtCell(n))).click();
        clearAndInputElementWithException(bExtField,txt);
        clickOnTableGrid();
    }

    public String getTotalPriceForPartsGrid(String price){
        String actualPrice = driver.findElement(bTotalPriceForPartsGrid).getText();
        long startTime = System.currentTimeMillis();
        while (actualPrice!=price){
            actualPrice = driver.findElement(bTotalPriceForPartsGrid).getText();
            if(actualPrice.equals(price)){
                break;
            }else if((System.currentTimeMillis()-startTime)> 10000){
                break;
            }
        }
        return actualPrice;
    }

    public String getTotalPriceForPartsGridOnPartsGridTab(String price){
        String actualPrice = driver.findElement(bTotalPriceForPartsGridOnPartsGridTab).getText();
        long startTime = System.currentTimeMillis();
        while (actualPrice!=price){
            actualPrice = driver.findElement(bTotalPriceForPartsGridOnPartsGridTab).getText();
            if(actualPrice.equals(price)){
                break;
            }else if((System.currentTimeMillis()-startTime)> 10000){
                break;
            }
        }
        return actualPrice;
    }

    public void clickOnSaveAndClose(){
        dWait.until(conditionClick(bSaveAndClosePartsGrid)).click();
    }

    public void clickOnCompleteAndClose() {dWait.until(conditionClick(bCompletedAndClosePartsGrid)).click();}

    public void clickOnCompleteAndCloseOnPartsList(){
        long startTime = System.currentTimeMillis();
        while(!driver.getTitle().equals("MPI List")){
            try{
                driver.findElement(bCompletedAndClosePartsList).click();
            }catch (WebDriverException ex){
                //it's blocked by the prompt yes
            }
            try{
                driver.findElement(bPartsRequestPromptYes).click();
                System.out.println("<====== clicking the dialog prompt yes on the parts list ======>");
            }catch (WebDriverException ex){
                //the parts request completed prompt is not there, please ignore
            }
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 60000){
                Assert.fail("<====== Not able to click on the complete and close button, test fail ======>");
                break;
            }
        }
    }

    public void clickOnCompleteAndCloseBtnOnPartsList(){
        setWait();
        try {
//            driver.findElement(bCompletedAndClosePartsList).click();
            sWait.until(conditionClick(bCompletedAndClosePartsList)).click();
        }catch (WebDriverException ex){
            //it's blocked by the prompt yes
        }
        try{
//            driver.findElement(bPartsRequestPromptYes).click();
            sWait.until(conditionClick(bPartsRequestPromptYes)).click();
            System.out.println("<====== clicking the dialog prompt yes on the parts list ======>");
        }catch (WebDriverException ex){
            //the parts request completed prompt is not there, please ignore
        }
//        sleep(5000);
        waitForTitle("MPI List");
    }

    public String getTypeOnPartsList(String rowNumber){
        int n = Integer.parseInt(rowNumber);
        dWait.until(conditionClick(bTypeCellONPartsList(n))).click();
        //dWait.until(conditionClick(bTypeCellONPartsList(n))).click();
        String typeValue = driver.findElement(bTypeCellONPartsList(n)).getAttribute("innerHTML");
        return typeValue;
    }

    public String getQTYOnPartsList(String rowNumber){
        int n = Integer.parseInt(rowNumber);
        String qTYValue = driver.findElement(bQTYCellONPartsList(n)).getAttribute("title");
        return qTYValue;
    }

    public void setQTYOnPartsList(String txt, String rowNumber){
        int n = Integer.parseInt(rowNumber);
//        clickElementWithExceptionOnPartListPage(bQTYCellONPartsList(n));
//        clearAndInputElementWithException(bQTYField, txt);
//        clickOnPartsGrid();
//        sleep(2000);

        dWait.until(conditionClick(bQTYCellONPartsList(n))).click();
        clearAndInput(bQTYField, txt);
        clickOnPartsGrid();
        sleep(1000);

        if (!driver.findElement(bQTYCellONPartsList(n)).getText().equals(txt)) {
            dWait.until(conditionClick(bQTYCellONPartsList(n))).click();
            clearAndInput(bQTYField, txt);
            clickOnPartsGrid();
        }
    }

    public String getDescriptionOnPartsList(String rowNumber){
        int n = Integer.parseInt(rowNumber);
        String descriptionValue = driver.findElement(bDescriptionCellONPartsList(n)).getText();
        return descriptionValue;
    }

    public void setDescriptionOnPartsList(String txt,String rowNumber){
        int n = Integer.parseInt(rowNumber);
//        dWait.until(conditionClick(bDescriptionCellONPartsList(n))).click();
//        clearAndInput(bDescriptionFieldList, txt);
//        clickOnPartsGrid();
//        sleep(2000);
//        for(int i = 0; i < 10; i++){
//            if (!driver.findElement(bDescriptionCellONPartsList(n)).getText().equals(txt)) {
//                dWait.until(conditionClick(bDescriptionCellONPartsList(n))).click();
//                clearAndInput(bDescriptionFieldList, txt);
//                clickOnPartsGrid();
//                sleep(1000);
//            }
//            else break;
//        }
        dWait.until(conditionClick(bDescriptionCellONPartsList(n))).click();
        clearAndInput(bDescriptionFieldList, txt);
        clickOnPartsGrid();
        sleep(1000);

        if (!driver.findElement(bDescriptionCellONPartsList(n)).getText().equals(txt)) {
            dWait.until(conditionClick(bDescriptionCellONPartsList(n))).click();
            clearAndInput(bDescriptionFieldList, txt);
            clickOnPartsGrid();
            sleep(1000);
        }
    }

    public String getStatusOnPartsList(String rowNumber){
        int n = Integer.parseInt(rowNumber);
        String statusValue = driver.findElement(bStatusCellONPartsList(n)).getAttribute("title");
        return statusValue;
    }

    public void setStatusOnPartsList(String txt, String rowNumber){
        int n = Integer.parseInt(rowNumber);
        dWait.until(conditionClick(bStatusCellONPartsList(n))).click();
        dWait.until(conditionClick(bStatusCellONPartsList(n))).click();
        clearAndInput(bStatusFieldONPartsList, txt);
        clickOnPartsGrid();
    }

    public String getSaleOnPartsList(String rowNumber){
        int n = Integer.parseInt(rowNumber);
        String saleValue = driver.findElement(bSaleCellONPartsList(n)).getAttribute("title");
        return saleValue;
    }

    public void setSaleOnPartsList(String txt, String rowNumber){
        int n = Integer.parseInt(rowNumber);
        dWait.until(conditionClick(bSaleCellONPartsList(n))).click();
        clearAndSend(bSaleField, txt);
        clickOnPartsGrid(); //this is use to update the price
     }

    public String getExtSaleOnPartsList(String rowNumber){
        int n = Integer.parseInt(rowNumber);
        String extSaleValue = driver.findElement(bExtSaleCellONPartsList(n)).getAttribute("title");
        return extSaleValue;
    }

    public void clickSaveOnPartsList(){
        dWait.until(conditionClick(bSaveOnPartsList)).click();
        iTab.waitForLoadingCircleToDisappearMPI();
    }

    public String getNotInStockToggleStatusAssociatedWithDescription(String description){
        String toggleStatus;
        if(driver.findElement(bNotInStockWithDescriptionStatus(description)).getAttribute("checked").equals("true")){
            toggleStatus = "ON";
        }else{
            toggleStatus = "OFF";
        }
        return toggleStatus;
    }

    public void clickOnNotInStockToggleAssociatedWithDescription(String description){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bNotInStockWithDescription(description)).click();
                noException = true;
            }catch (WebDriverException e) {
                System.out.println("<====== Not In stock button associated with '"+description+"' is covered by total bar, need to scroll page down ======>");
                noException = false;
                if(description.equals("Test1")){
                    scrollPageUp(500);
                }else{
                    scrollPageDown(100);
                }
                sleep(1000);
            }
            if((System.currentTimeMillis()-startTime)> 60000){
                break;
            }
        }
    }

    public String getSeePartsListStatusAssociatedWithDescription(String description){
        String toggleStatus;
        if(driver.findElement(bSeePartsListWithDescriptionStatus(description)).getAttribute("checked").equals("true")){
            toggleStatus = "ON";
        }else{
            toggleStatus = "OFF";
        }
        return toggleStatus;
    }

    public void clickOnDeferredAssociatedWithDescription(String description){
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while(!noException){
            try{
                driver.findElement(bDeferredWithDescription(description)).click();
                noException = true;
            }catch (WebDriverException e) {
                System.out.println("<====== Defer button is covered by total bar, need to scroll page down ======>");
                noException = false;
                scrollPageDown(100);
                sleep(1000);
            }
            if((System.currentTimeMillis()-startTime)> 60000){
                break;
            }
        }
    }

    public void clickOnDeferredSelectionAssociatedWithDescription(String dropDownSelection,String description){
        if(description.equals("Test1")){
            scrollPageUp(400);
        }else if(description.equals("Test2")){
            scrollPageDown(400);
        }
        dWait.until(conditionClick(bDeferredDropDownWithDescription(description,dropDownSelection))).click();
    }

    public void clickElementWithExceptionOnPartListPage(By element) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                driver.findElement(element).click();
                noException = true;
            } catch (WebDriverException e) {
                if (!isUserOnPartsListTab()) {
                    System.out.println("<====== User is not on the PARTS LIST tab, will click and try it again ======>");
                    clickOnPartsListTab();
                }
                noException = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 30000) {
                Assert.fail("<====== User not able to get on the PARTS LIST tab page ======>");
                break;
            }
        }
    }

    public boolean isUserOnPartsListTab(){
        if(getElementAttributeWithException(bPartsListTab,"class").contains("tab-selected") && getElementAttributeWithException(bPartsListTab,"style").contains("rgb(236, 235, 235)")){
            return true;
        }else{
            return false;
        }
    }

    public void clickOnPartsListTab() {
        iTab.waitForLoadingCircleToDisappearMPI();
        clickElementWithException(bPartsListTab);
    }

    //this is click to update the price on parts list
    public void clickOnQtySheetHeader(){
        clickElementWithException(bQtySheetHeader);
    }

}
