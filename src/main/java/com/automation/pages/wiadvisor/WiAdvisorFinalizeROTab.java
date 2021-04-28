package com.automation.pages.wiadvisor;

import com.automation.utils.sync.SyncPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Iterator;
import java.util.List;

public class WiAdvisorFinalizeROTab extends WiAdvisorROPage {
    //locator
    //signature field
    private static By bSignature = By.id("signatureCanvas");
    //green complete button on finalize RO
    private static By bCompleteFinalizeRO = By.xpath("//button[@ng-click='repairOrder.continue()' and contains(text(),'Complete')]");
    //pdf link with the name
    //RO push progress
    private static By bROPushWaitText = By.xpath("//p[contains(text(),'Pushing Repair Order. Please wait')]");
    //Recall Feedback Survey popup
    private static By bSurveyTest = By.xpath("//h2[contains(text(),'Recalls Feedback Survey')]");
    //survey checkbox
    private static By bSurveyCheckbox = By.xpath("//label[@for='Phone Call']");
    //survey submit
    private static By bSurveySubmit = By.xpath("//button[contains(text(),'Submit')]");

    private static By bTitleSurvey = By.xpath("//h2[contains(text(),'Survey')]");
    private static By bCheckBoxsSurvey = By.cssSelector("label.ng-binding");
    private static By bBtnSubmit = By.xpath("//button[contains(text(),'Submit')]");
    private static By bAllPDFLinksList = By.cssSelector(".push-ro-documents a");
    private static By bpushROStatus = By.cssSelector("div[ng-class*='pushRoModal'] p"); // Push RO Status
    private static By bpreServiceWriteUpPDFLink = By.cssSelector("div[ng-if*='writeUp' i] a"); // Write Up PDF Link
    private static By bmaintMenuPDFLink = By.cssSelector("div[ng-if*='maintMenu' i] a"); // Maint Menu PDF Link
    private static By bvipPDFLink = By.cssSelector("div[ng-if*='vipReport' i] a"); // VIP PDF Link
    private static By bcompleteTitle = By.cssSelector("[class*='push' i] h2"); // VIP PDF Link
    private static String preServiceWriteUpLink;
    private static String maintenanceMenuLink;
    private static String vipLink;

    private static By bOkBtn = By.cssSelector("button[ng-click*='close']"); // OK Button


    private static By bPDFLinkWithName(String pdfName){
        String locator = "//img[contains(@src,'pdf')]/following-sibling::a[contains(text(),'"+pdfName+"')]";
        return By.xpath(locator);
    }

    //sign on the signature field
    public void signOnRO(){
        clickElementWithException(bSignature);
    }

    public void clickOnCompleteFinalizeRO(){
        clickElementWithException(bCompleteFinalizeRO);
        handleRecallSurvey();
    }


    public void clickMaintMenuLink() {
        clickElementWithException(bmaintMenuPDFLink);
    }

    public void clickPreServiceWriteLink() {
        dWait.until(conditionClick(bpreServiceWriteUpPDFLink)).click();
    }

    public void clickOpenVIPlink() {
        clickElementWithException(bvipPDFLink);
    }


    public void waitForROPush(){
        long startTime = System.currentTimeMillis();
        System.out.println("<====== Pushing Repair Order.  Please wait.....  ======>");
        while(loadingMsgROPush()){
//            System.out.println("<====== Pushing Repair Order. Please wait... (ãƒ»Ë�ãƒ»*) ======>");
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 60000){
                System.out.println("<====== RO push failed, which means your test failed (ãƒ»Ë�ãƒ»*) ======>");
                Assert.fail("<====== RO push failed, which means your test failed (ãƒ»Ë�ãƒ»*) ======>");
                break;
            }
        }
        Boolean roReady = dWait.until(conditionVisible(bpushROStatus)).getText().toLowerCase().contains("successfully");
        SyncPage.prepareWindowPopup();
    }

    public void waitForPDFReady(){
        dWait.until(conditionVisible(bcompleteTitle));
        dWait.until(conditionClick(bpreServiceWriteUpPDFLink));
        dWait.until(conditionClick(bmaintMenuPDFLink));
        dWait.until(conditionClick(bvipPDFLink));
        SyncPage.prepareWindowPopup();
    }


    public boolean loadingMsgROPush(){
        try{
            driver.findElement(bROPushWaitText).isDisplayed();
            return true;
        }catch (WebDriverException e){
            System.out.println("<====== RO push completed ^_^ ======>");
            return false;
        }
    }

    public void waitForSurvey(){
//        setWait();  // problem eith dwait ?
        long startTime = System.currentTimeMillis();
        while(loadingMsgSurvey()){   // problem with dWait ?
            System.out.println("<====== Completing the survey (ãƒ»Ë�ãƒ»*) ======>");
            dWait.until(conditionVisible(bSurveyCheckbox)).click();
            dWait.until(conditionVisible(bSurveySubmit)).click();
            sleep(2000);
            if((System.currentTimeMillis()-startTime)> 60000){
                break;
            }
        }
    }

    public boolean loadingMsgSurvey(){
        try{
            driver.findElement(bSurveyTest).isDisplayed();
            return true;
        }catch (WebDriverException e){
            return false;
        }
    }

    public void clickOnPDFLink(String pdfName){

        switch (pdfName){
            case "pre-service write-up":
                preServiceWriteUpLink = getPDFLink(pdfName);
                break;
            case "maintenance menu":
                maintenanceMenuLink = getPDFLink(pdfName);
                break;
            case "VIP":
                vipLink = getPDFLink(pdfName);
                break;
        }
        clickElementWithException(bPDFLinkWithName(pdfName));
    }

    public String getPDFLink(String pdfName){
        String pdfLink = getElementAttributeWithException(bPDFLinkWithName(pdfName),"ng-href");
        System.out.println("<====== The pdf link is "+pdfLink+"  (Â´Â·_Â·`)  ======>");
        return pdfLink;
    }

    public boolean verifyPDF(String pdfName){
        if(pdfName.equals("pre-service write-up")){
            if(preServiceWriteUpLink.contains("newommcache")){
                return true;
            }else{
                System.out.println("<====== "+pdfName+" does not exist (Â·â–³Â·/) ======>");
                return false;
            }
        }else if(pdfName.equals("maintenance menu")){
            if(maintenanceMenuLink.contains("newommcache")){
                return true;
            }else{
                System.out.println("<====== "+pdfName+" does not exist (Â·â–³Â·/) ======>");
                return false;
            }
        }else if(pdfName.equals("VIP")){
            if(vipLink.contains("newommcache")){
                return true;
            }else{
                System.out.println("<====== "+pdfName+" does not exist (Â·â–³Â·/) ======>");
                return false;
            }
        }else{
            System.out.println("<====== "+pdfName+" does not exist (Â·â–³Â·/) ======>");
            return false;
        }
    }

    private void handleRecallSurvey(){
         sleep(500);
         try{
             if (driver.findElement(bTitleSurvey).isDisplayed()){
                 System.out.println(" Recall Survey diaplayed !");
                 dWait.until(conditionPresenceList(bCheckBoxsSurvey)).get(0).click();
                 dWait.until(conditionClick(bBtnSubmit)).click();
             }
         }catch (WebDriverException e){
             //
         }
    }

    public boolean isGeneratePDFCompleteRO(String pdfname) {        // This is to check all PDF has been generated
        List<WebElement> webElementList = driver.findElements(bAllPDFLinksList);
        boolean result = false;
        String pdftext =" ",href =" ";
        for (Iterator<WebElement> ii = webElementList.iterator(); ii.hasNext(); ) {
            WebElement elm = ii.next();
            pdftext = elm.getText().trim().toLowerCase();
            href = elm.getAttribute("href").trim().toLowerCase();
//            if (pdfname.equalsIgnoreCase("open maintenance menu")){
//                System.out.println("pdf text  .. " + pdftext + "\n");
//                System.out.println("pdf href  .. " + href+ "\n");
//                System.out.println("pdf .. " + pdfname.toLowerCase() + "\n");
//                System.out.println("pdf contain name .. " + pdftext.contains(pdfname.toLowerCase() + "\n"));
//                System.out.println("pdf contain href  .. " + href.contains(".pdf") + "\n");
//            }
            if ((pdftext.contains(pdfname.toLowerCase()))
                          && (href.contains(".pdf"))) {
                result = true;
                break;
            }else{

            }
        }
        return result;
    }

    public void clickOnOkBtnPushPopUp(){
        WebElement element = driver.findElement(bOkBtn);
        clickElementTimesTillDisappear(element,5);
    }

}
