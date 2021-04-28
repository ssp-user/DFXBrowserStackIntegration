package com.automation.pages.tech_inspection;

import com.automation.pages.common.WebPage;
import com.automation.pages.tech_inspection.ServiceTab.InspectionTab;
import com.automation.pages.tech_inspection.dashboard.CreateMPIPage;
import com.automation.pages.tech_inspection.dashboard.TechInspectSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static com.automation.utils.dataProvider.TestParameters.randomText;

public class FrenchTIPage extends WebPage {

    InspectionTab iTab = new InspectionTab();
    CreateMPIPage cPage = new CreateMPIPage();
    TechInspectSearchPage tPage = new TechInspectSearchPage();
    //Locator

    //this element is to wait for search modal popup disappear
    private static By bSearchModal = By.id("search-modal");

    private static By bLanguageDropDown = By.id("LanguageDropdown");

    private static By bLanguageName(String language){
        String checkLocator = "//a[contains(text(),'"+language+"')]";
        return By.xpath(checkLocator);
    }

    private static By bFrenchName(String frenchText){
        String checkLocator = "//*[contains(text(),\""+frenchText+"\")]";
        return By.xpath(checkLocator);
    }

    private static By bFrenchButtonValue(String frenchText){
        String checkLocator = "//input[@value=\""+frenchText+"\"]";
        return By.xpath(checkLocator);
    }

    //get status by VIN
    private static By bInspectStatusByVinFrench(String vIN){
        //String checkLocator = "//b[contains(text(),'"+vIN+"')]/parent::td/parent::tr/parent::tbody/parent::table/parent::td/following-sibling::td[5]/div/img";
        String checkLocator = "//b[contains(text(),'"+vIN+"')]/parent::td/parent::tr/parent::tbody/parent::table/parent::td/preceding-sibling::td/table/tbody/tr/td/b[contains(text(),'"+randomText+"')]/parent::td/parent::tr/parent::tbody/parent::table/parent::td/following-sibling::td[6]/div/img";
        return By.xpath(checkLocator);
    }

    //vehicle status
    private static By bInpectStatusMPIFr = By.id("CardInfo_evirStatusImage");

    //section green dot
    private static By dCheckOKFr = By.xpath("//input[@data-original-title='Marquer la section tout OK']");

    //bulk update the green OK
    private static By dCheckAllOKFr(int sectionNo) {
        String checkLocator = "(//input[@data-original-title='Marquer la section tout OK'])["+sectionNo+"]";
        return By.xpath(checkLocator);
    }

    //mark section okay french based on section name
    private static By bMarkSectionAllOKBasedOnSectionName(String sectionName){
        String checkLocator = "//b[contains(text(),'"+sectionName+"')]/parent::a/parent::td/following-sibling::td/input[1]";
        return By.xpath(checkLocator);
    }

    //click red icon french based on section name
    private static By bClickONRedIconBasedOnSectionName(String sectionName){
        String checkLocator = "//b[contains(text(),'"+sectionName+"')]/parent::a/parent::td/parent::tr/parent::tbody/parent::table//following-sibling::div/table/tbody/tr/td[2]/div/table/tbody/tr/td[3]/label/div";
        return By.xpath(checkLocator);
    }

    //element on the MPI page, inspection tab
    private static By bInspection = By.id("tbInspection");

    //part request label on inspection detail page
//    private static By bPartRequestFr = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//span[@class='chechboxinputparts-fr']/label");
    private static By bPartRequestFr = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//span[contains(@class,'chechboxinputparts_fr-ca')]/label");

    //save button on inspection detail page
    private static By bSaveFrInspectionDetail = By.xpath("//div[@class='tbCommentsContainer' and @style='display: block;']//a[contains(text(),'Sauvegarder')]");

    //save button on MPI page
    private static By bSaveFrMPI = By.xpath("//input[@name='btnSave' and @value='Sauvegarder']");

    //recommendation tab on MPI page
    private static By bRecommendationTabFr = By.id("tbRecommendations");

    //mark completed button on MPI page
    private static By bMarkCompletedFr = By.id("btnMarkCompleted");

    //description on recommendation tab fr
    private static By bDescriptionFieldFr = By.xpath("//span[contains(text(),'Description')]/../../..//span[contains(@id,'PartDescr')]");

    //hours on recommendation tab fr
    private static By bHoursFieldFr = By.xpath("//span[contains(text(),'Heures')]/../../..//input[contains(@id,'PartsHours')]");

    //labor on recommendation tab fr
    private static By bLaborFieldFr = By.xpath("//span[contains(text(),'Main-d’œuvre')]/../../..//input[contains(@id,'PartsLabor')]");

    //parts on recommendation tab fr
    private static By bPartsFieldFr = By.xpath("//span[contains(text(),'Pièces')]/../../..//input[contains(@id,'PartsParts')]");

    //price on recommendation tab fr
    private static By bPriceFieldFr = By.xpath("//span[contains(text(),'Prix')]/../../..//input[contains(@id,'PartPrice')]");

    //show button on MPI page fr
    private static By bShowMPIFr = By.xpath("//button[@id='lnkVehicleCardRow']");

    //revise button on MPI page fr
    private static By bReviseFrMPI = By.id("btnMarkReviewed");

    //confirmer button on MPI page fr
    private static By bConfirmerFr = By.id("btnClose");

    //view history button on MPI page fr
    private static By bViewHistoryFr = By.id("imgEVIRHistory");

    //status log button on MPI page fr
    private static By bStatusLogFr = By.id("imgUpdatesHistory");

    //view menu button on MPI page fr
    private static By bViewMenuFr = By.id("imgViewMenu");

    //ready for processing on MPI page fr
    private static By bReadyForProcessingFr = By.id("btnReadyForProcessing");

    //parts request completed button on recommendation page
    private static By bPartsRequestCompletedFr = By.xpath("//input[@id='RecommendationsTab_btnPartsSave']");

    //PARTS REQUEST COMPLETED dialog
    private static By bPartsRequestPromptYesFr = By.id("yes_dialogBtn");

    //in stock associated with description fr
    private static By bInStockWithDescriptionFr (String description){
        String checkLocator = "//span[contains(text(),'"+description+"')]/ancestor::tr/following-sibling::tr/descendant::div[contains(text(),'En stock')]";
//        String checkLocator = "//span[contains(text(),'"+description+"')]/parent::td/parent::tr/following-sibling::tr/td/table/tbody/tr/td[2]/table/tbody/tr/td/label/div[contains(text(),'En stock')]";
        return By.xpath(checkLocator);
    }

    public void clickOnLanguageOnHeader(String language){
        waitForTitle("MPI List");
        cPage.waitForOpenModalDisappear();
        clickElementWithException(bLanguageDropDown);
    }

    public void selectLanguage(String language){
        clickElementWithException(bLanguageName(language));
    }

    public String verifyFrenchText(String frenchText){
        if(frenchText.equals("LISTE DES PIÈCES")){
            try{
                dWait.until(conditionVisible(bFrenchName(frenchText)));
            }catch(org.openqa.selenium.WebDriverException ex){
                System.out.println("<====== The actual French locator text '"+frenchText+"' not found ======>");
                sleep(5000);
            }
        }
        if(driver.findElements(bFrenchName(frenchText)).size()!=0){
            System.out.println("The French text '"+frenchText+"' found on page");
            return frenchText;
        }else{
            String actualFrenchLocator = getElementTextWithException(bFrenchName(frenchText));
            System.out.println("<====== The actual French locator text '"+actualFrenchLocator+"' not found ======>");
            return actualFrenchLocator;
        }
    }

    public String verifyFrenchTextInput(String frenchText){
        if(driver.findElements(bFrenchButtonValue(frenchText)).size()!=0){
            System.out.println("The French text '"+frenchText+"' found on page");
            return frenchText;
        }else{
            return "'"+frenchText+"' text not existed";
        }
    }

    public void clickOnFrenchButton(String frenchText){
        if(frenchText.equals("Créer")){
            scrollPageDown(500);
            dWait.until(conditionClick(bFrenchButtonValue(frenchText))).click();
        }else if(driver.findElements(bFrenchName(frenchText)).size()!=0){
            dWait.until(conditionClick(bFrenchName(frenchText))).click();
            if(frenchText.equals("Créer inspection")){
                switchToFrenchMPICreateIframe();
            }
        }else{
            dWait.until(conditionClick(bFrenchButtonValue(frenchText))).click();
        }
    }

    public String getInspectionStatusByVinFrench(String vIN){
        waitForTitle("Liste d'inspections");
//        iTab.waitForLoadingCircleToDisappearMPI();
        String image = getElementAttribute(bInspectStatusByVinFrench(vIN),"src");
        String status = "";
        if (!image.isEmpty()) {
            int begin = image.indexOf("/status_");
            int end = image.indexOf(".png");
            String imgSn = image.substring(begin + 8, end);
            switch (imgSn) {
                case "1":
                    status = "créé";
                    break;
                case "2":
                    status = "commencé";
                    break;
                case "3":
                    status = "terminé";
                    break;
                case "4":
                    status = "révision";
                    break;
                case "5":
                    status = "READY";
                    break;
                case "7":
                    status = "confirmé";
                    break;
                case "9":
                    status = "pièces demandées";
                    break;
                case "10":
                    status = "Prêt à traiter";
                    break;
            }
        }
        return status;
    }

    public String getInspectStatusOnMPIPageFr() {
        iTab.waitForLoadingCircleToDisappearMPI();
        String image = getElementAttributeWithException(bInpectStatusMPIFr,"src");
        String status = "";
        if (!image.isEmpty()) {
            int begin = image.indexOf("/status_");
            int end = image.indexOf(".png");
            String imgSn = image.substring(begin + 8, end);
            switch (imgSn) {
                case "1":
                    status = "créé";
                    break;
                case "2":
                    status = "commencé";
                    break;
                case "3":
                    status = "terminé";
                    break;
                case "4":
                    status = "révision";
                    break;
                case "5":
                    status = "READY";
                    break;
                case "7":
                    status = "confirmé";
                    break;
                case "9":
                    status = "pièces demandées";
                    break;
                case "10":
                    status = "Prêt à traiter";
                    break;
            }
        }
        return status;
    }

    public void markSectionAllOKFr(){
        waitForTitle("Détails de l'inspection");
        iTab.waitForLoadingCircleToDisappearMPI();
        waitForElementVisibleWithException(dCheckOKFr);
        List<WebElement> elementCount = driver.findElements(dCheckOKFr);
        int totalSection = elementCount.size()+1;
        for (int i=1;i<totalSection;i++){
            scrollPageUp(1000);
            dWait.until(conditionVisible(dCheckAllOKFr(i))).click();
            System.out.println("<====== La '"+i+"' Marquer la section tout OK ======>");
        }
    }

    public void clickONMarkSectionAllOkayBasedOnSectionName(String sectionName){
        dWait.until(conditionClick(bMarkSectionAllOKBasedOnSectionName(sectionName))).click();
    }

    public void clickONRedIconBasedOnSectionName(String sectionName){
        dWait.until(conditionClick(bClickONRedIconBasedOnSectionName(sectionName))).click();
    }

    public void clickOnPartsRequestFr(){
        dWait.until(conditionClick(bPartRequestFr)).click();
    }

    public void clickOnSaveOnInpectionDetaiFr(){
        try{
            dWait.until(conditionClick(bSaveFrInspectionDetail)).click();
        }catch(org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
            System.out.println("<====== French Save button became English, so test fail ======>");
        }
    }

    public void clickOnSaveOnMPIFr(){
        dWait.until(conditionClick(bSaveFrMPI)).click();
    }

    public void clickONRecommendationOnMPIFr(){
        long startTime = System.currentTimeMillis();
        iTab.waitForLoadingCircleToDisappearMPI();
        clickElementWithException(bRecommendationTabFr);
        while(!isUserOnRecommendationTabFr()){
            driver.findElement(bRecommendationTabFr).click();
            if((System.currentTimeMillis()-startTime)> 5000){
                break;
            }
        }
    }

    public void clickInspectionOnMPIFr() { dWait.until(conditionClick(bInspection));}

    public boolean isUserOnRecommendationTabFr(){
        if(getElementAttributeWithException(bRecommendationTabFr,"class").contains("tab-selected") && getElementAttributeWithException(bRecommendationTabFr,"style").contains("rgb(236, 235, 235)")){
            return true;
        }else{
            return false;
        }
    }

    public void clickONMarkCompletedOnMPIFr(){
        dWait.until(conditionClick(bMarkCompletedFr)).click();
        waitForTitle("Liste d'inspections");
    }

    public String getDescriptionValueOnRecommendationTabFr(){
        String descriptionValue = getElementAttributeWithException(bDescriptionFieldFr,"innerHTML");
        return descriptionValue;
    }

    public String getHoursValueOnRecommendationTabFr(){
        String hoursValue = getElementAttributeWithException(bHoursFieldFr,"value");
        return hoursValue;
    }

    public String getLaborValueOnRecommendationTabFr(){
        String laborValue = getElementAttributeWithException(bLaborFieldFr,"value");
        return laborValue;
    }

    public String getPartsValueOnRecommendationTabFr(){
        String partsValue = getElementAttributeWithException(bPartsFieldFr,"value");
        return partsValue;
    }

    public String getPriceValueOnRecommendationTabFr(){
        String priceValue = getElementAttributeWithException(bPriceFieldFr,"value");
        return priceValue;
    }

    public void clickONShowMPIFr(){
        clickElementWithException(bShowMPIFr);
    }

    public void clickOnMarkReviewFr(){
        dWait.until(conditionClick(bReviseFrMPI)).click();
    }

    public void clickOnConfirmerFr(){
        dWait.until(conditionClick(bConfirmerFr)).click();
    }

    public void clickOnViewHistoryFr(){
        iTab.waitForLoadingCircleToDisappearMPI();
//        dWait.until(conditionClick(bViewHistoryFr)).click();
        clickElementWithException(bViewHistoryFr);
    }

    public void clickOnStatusLogFr(){
        dWait.until(conditionClick(bStatusLogFr)).click();
    }

    public void clickOnViewMenuFr(){
        dWait.until(conditionClick(bViewMenuFr)).click();
    }

    public void clickOnReadyForProcessingFr(){
        dWait.until(conditionClick(bReadyForProcessingFr)).click();
    }

    public void clickOnInStockAssociatedWithDescriptionFr(String description){
        scrollPageDown(800);
        dWait.until(conditionClick(bInStockWithDescriptionFr(description))).click();
    }

    public void clickOnPartsRequestCompletedFr(){
        long startTime = System.currentTimeMillis();
        iTab.waitForLoadingCircleToDisappearMPI();
        scrollPageDown(800);
        while(!driver.getTitle().equals("Liste d'inspections")){
            try{
                driver.findElement(bPartsRequestCompletedFr).click();
            }catch (WebDriverException ex){
                //it's blocked by the prompt yes
            }
            try{
                driver.findElement(bPartsRequestPromptYesFr).click();
                System.out.println("<====== clicking the dialog prompt yes on the parts list ======>");
            }catch (WebDriverException ex){
                //the parts request completed promt is not there, please ignore
            }
            sleep(2000);
            if((System.currentTimeMillis()-startTime)> 60000){
                Assert.fail("<====== Not able to click on the complete and close button, test fail ======>");
                break;
            }
        }
    }

    public void waitForElementONMPIPage(){
        pWait.until(conditionClick(bInspection));
    }

    public void switchToFrenchMPICreateIframe(){
        driver.switchTo().frame("createEvirIframe");
    }

    public void switchContentToDefault(){
        driver.switchTo().defaultContent();
        cPage.waitForOpenModalDisappear();
    }
}
