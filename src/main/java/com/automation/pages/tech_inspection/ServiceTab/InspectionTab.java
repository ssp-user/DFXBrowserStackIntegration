package com.automation.pages.tech_inspection.ServiceTab;

import org.openqa.selenium.By;
import com.automation.pages.common.WebPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class InspectionTab extends WebPage{


    //all web element locator
    //require future attention click icon
    private static By bRequireFutureIcon = By.xpath("//div[@class='itemcommented']");

    //icon color based on sectionName
    private static By bIconColorOnInspection(String iconColor, String inspectionName){
        String colorNumber="";
        switch(iconColor){
            case "Green":
                colorNumber = "1";
                break;
            case "Orange":
                colorNumber = "2";
                break;
            case "Red":
                colorNumber = "3";
                break;
            case "Grey":
                colorNumber = "4";
                break;
        }
        String checkLocator = "//span[contains(text(),'"+inspectionName+"')]/parent::span/parent::td/following-sibling::td/div/table/tbody/tr/td["+colorNumber+"]";
        return By.xpath(checkLocator);
    }

    //concern name
    private static By bConcernName(String concernName){
        String locator = "//span[contains(@id,'InspectionTab_ucCustomerConcerns') and contains(text(),'"+concernName+"')]";
        return By.xpath(locator);
    }

    //icon color based on sectionName
    private static By bIconColorOnConcern(String iconColor, String concernName){
        String colorNumber="";
        switch(iconColor){
            case "Green":
                colorNumber = "1";
                break;
            case "Orange":
                colorNumber = "2";
                break;
            case "Red":
                colorNumber = "3";
                break;
            case "Grey":
                colorNumber = "4";
                break;
        }
        String checkLocator = "//span[contains(text(),'"+concernName+"')]/../../../following-sibling::td//td["+colorNumber+"]";
        return By.xpath(checkLocator);
    }

    //vehicle status
    private static By bInspectStatusMPI = By.id("CardInfo_evirStatusImage");

    //revised substatus
    private static By bRevisedSubStatus = By.id("CardInfo_lblRevised");

    //this is the show button
    private static By bShow = By.id("lnkVehicleCardRow");

    //this is the hide button
    private static By bHide = By.xpath("(//button[contains(text(),'Hide')])[1]");

    //mark review button
    private static By bMarkReview = By.id("btnMarkReviewed");

    //confirm button
    private static By bConfirm = By.xpath("//input[@value='Confirm']");

    //ready for processing
    private static By bReadyForProcessing = By.id("btnReadyForProcessing");

    //Vehicle queue icon
    @FindBy(id = "notif-counter-text")
    private WebElement vehicleQueueIcon;
    private static By bVehicleQueueIcon = By.id("lnkBackToVQPage");

    private static By bVehicleQueueIconAdvisor = By.xpath("//li[@class='md-trigger icon-ntbutton']");

    //this is the camera
    private static By dCamera = By.id("techcam-camerabtn-601");

    //this is the camera shutter
    private static By dCameraShutter = By.id("capture");

    //this is to close the camera
    private static By dCloseCamera = By.cssSelector("div.ui-icon.item-video-close");

    //this is the tooked photo
    private static By dPhoto = By.xpath("//img[@class='image-link']");

    //this is the first tooked photo
    private static By dPhoto1 = By.xpath("//img[@title='Photo 1']");

    //this is the error message on the photo page
    private static By dReachMaximumErrorMsg = By.id("message");

    //delete photo
    private static By dDeletePhoto = By.id("delete");

    //delete photo confirm
    private static By dDeleteConfirm = By.xpath("/html/body/div[4]/div[3]/div/button[1]");

    //add recommendation icon
    private static By dAddRecommendationIcon = By.id("InspectionTab_rpTabRef_ctl00_rpTabItem_ctl01_imAddRec");

    //recommendation comment
    private static By dRecommendation = By.xpath("//input[@title='Recommendation']");

    //internal comments
    private static By dInternalComment = By.id("InspectionTab_rpTabRef_ctl00_rpTabItem_ctl01_txtInternal");

    //customer comments
    private static By dCustomerComment = By.id("InspectionTab_rpTabRef_ctl00_rpTabItem_ctl01_txtComments");

    //save on the attention tab
    private static By dSaveButton = By.xpath("(//a[contains(text(),'Save')])[1]");

    //close the attention page
    private static By dAttentionClose = By.xpath("//div[@class='ui-icon item-panel-close']");

    //save on the inspection page
    private static By dSaveAllButton = By.id("btnSave");

    //save information update
    private static By bInformationUpdated = By.xpath("//span[@class='Message' and text()='Your information was updated.']");

    //click on require future attention
    public void clickOnRequireFutureIcon(){
        dWait.until(conditionVisible(bRequireFutureIcon)).click();
    }

    //bulk update one section
    private static By dCheckOK = By.xpath("//input[@data-original-title='Mark Section All OK']");

    //bulk update the green OK
    private static By dCheckAllOK(int sectionNo) {
        String checkLocator = "(//input[@data-original-title='Mark Section All OK'])["+sectionNo+"]";
        return By.xpath(checkLocator);
    }

    //coolant dropdown
    private static By dCoolant = By.xpath("//select[@data-original-title='Degree of Protection (F)']");

    //tireIncoming dropdown
    private static By dTireIncoming(int sectionNo){
        String checkLocator = "(//select[@data-original-title='Tire Pressure Incoming PSI'])["+sectionNo+"]";
        return By.xpath(checkLocator);
    }

    //tireAdjusted dropdown
    private static By dTireAdjusted(int sectionNo){
        String checkLocator = "(//select[@data-original-title='Tire Pressure Adjusted To PSI'])["+sectionNo+"]";
        return By.xpath(checkLocator);
    }

    //tireTreadDepth dropdown
    private static By dTireTreadDepth(int sectionNo){
        String checkLocator = "(//select[@data-original-title='Tread Depth /32'])["+sectionNo+"]";
        return By.xpath(checkLocator);
    }

    //TorqueSpecTreadDepth dropdown
    private static By dTorqueSpec = By.xpath("//select[@data-original-title='lb / ft']");

    //mark completed
    private static By markCompleted = By.id("btnMarkCompleted");

    //Revise Inspection
    private static By bReviseInspection = By.id("btnReviseInspection");

    //recommendation tab
    private static By bRecommendationTab = By.id("tbRecommendations");

    //recommendation tab
    private static By bPartsListTab = By.id("tbPartsList");

    //approved service tab
    private static By bApprovedServicesTab = By.id("tbApprovedServices");

    //walk around tab
    private static By bWalkAroundTab = By.id("tbWalkAround");

    //inspection tab
    private static By bInspectionTab = By.id("tbInspection");

    //view history tab
    private static By bViewHistoryTab = By.id("imgEVIRHistory");

    //view menu tab
    private static By bViewMenuTab = By.id("imgViewMenu");

    //status log tab
    private static By bStatusLogTab = By.id("imgUpdatesHistory");

    //loading circle
    private static By bLoadingCircle = By.xpath("//div[@id='processLoading']");
//    private static By bLoadingCircle1 = By.xpath("//div[@id='processLoading1']");

    //using for searchMPIModal to disappear
    private static By bLoadingSearchMPI = By.id("updateProgressEVIRSearch");

//    public void waitForLoadingCircleToDisappearMPI1(){
//        waitForElementHasAttributeValue(bLoadingCircle1,"style","display: none;");
//    }

    public boolean waitForLoadingCircleToDisappearMPI(){
        boolean result = false;
        setWait();
        try{
            result = pWait.until(ExpectedConditions.attributeContains(bLoadingCircle,"style","display: none;"));
        }catch (TimeoutException te){
            System.out.println( " element finding time out  ");
        }
         return result;
    }


    public void waitForMPISearchToDisappearMPI(){
        waitForElementHasAttributeValue(bLoadingSearchMPI,"style","display: none;");
    }

//    public boolean  isForLoadingCircleToDisappearMPI(){
//        waitForElementHasAttributeValue(bLoadingSearchMPI,"style","display: none;");
//       return pWait.until(ExpectedConditions.attributeContains(bLoadingCircle,"style","display: none;"));
//    }

    public boolean isInspectionTabLoaded(){
        return dWait.until(ExpectedConditions.presenceOfElementLocated(dAddRecommendationIcon)).isDisplayed();
    }

    public void clickShow(){
        waitForLoadingCircleToDisappearMPI();
        clickElementWithException(bShow);
        if(!verifyIfElementVisibleOnPage(bInspectStatusMPI)){
            clickElementWithException(bShow);
        }
    }

    public void clickHide(){
        clickElementWithException(bHide);
    }

    public void clickOnIconColorBasedOnSection(String iconColor, String sectionName){
        if(sectionName.equals("Horn Operation")){
            scrollPageUp(500);
        }else if(sectionName.equals("Left Front Wipers")){
            scrollPageUp(1000);
        }else if(sectionName.equals("Right Front Wipers")){
            scrollPageUp(1000);
        }else if(sectionName.equals("Headlamps")){
            scrollPageUp(1000);
        }else if(sectionName.equals("Taillamps")){
            scrollPageUp(1000);
        }else if(sectionName.equals("Phares de route et de croisement")){
            scrollPageUp(1000);
        }
        dWait.until(conditionClick(bIconColorOnInspection(iconColor,sectionName))).click();
    }

    public void clickOnIconColorBasedOnConcernSection(String iconColor, String concernName){
        clickElementWithException(bIconColorOnConcern(iconColor,concernName));
    }

    public void clickOnConernName(String concernName){
        clickElementWithException(bConcernName(concernName));
    }

    public void addComment(){
        String comment = "This is a test comment -- Darren";
        waitForLoadingCircleToDisappearMPI();
        clearAndSend(dRecommendation,comment);
        dWait.until(conditionVisible(dAddRecommendationIcon)).click();
        clearAndSend(dInternalComment,comment);
        clearAndSend(dCustomerComment,comment);
    }

    public void clickOnCamera(){
        dWait.until(conditionVisible(dCamera)).click();
        driver.switchTo().defaultContent();
        sleep(5000);
        keyboardTab();
    }

    public void takeCameraShot(){
        dWait.until(conditionVisible(dCameraShutter)).click();
    }

    public boolean isMsgDisplayed(){
        sleep(5000);
        return dWait.until(ExpectedConditions.presenceOfElementLocated(dReachMaximumErrorMsg)).isDisplayed();
    }

    public void closeTheCamera(){
        if(isMsgDisplayed()){
            dWait.until(conditionVisible(dPhoto1)).click();
            dWait.until(conditionVisible(dDeletePhoto)).click();
            keyboardTab();
            dWait.until(conditionVisible(dDeleteConfirm)).click();
            sleep(5000);
        }
        dWait.until(conditionVisible(dPhoto)).click();
        dWait.until(conditionVisible(dCloseCamera)).click();
    }

    public void saveRecommendation(){
        dWait.until(conditionVisible(dSaveButton)).click();
    }

    public void closeAttentionTab(){
        dWait.until(conditionVisible(dAttentionClose)).click();
    }

    public void saveAllRecommendation(){
        dWait.until(conditionVisible(dSaveAllButton)).click();
    }

    public void verifySaveAllRecommendationTime(String expectedSeconds) {
        long startTime = System.currentTimeMillis();
        long expectedSec = Integer.parseInt(expectedSeconds) * 1000;
        dWait.until(conditionVisible(dSaveAllButton)).click();
        //this is to check if user under approved service tab, if yes, then no save message will show, i have to check if loading spin completed
        if(getElementAttributeWithException(bApprovedServicesTab,"style").contains("rgb(236, 235, 235)")){
            waitForLoadingCircleToDisappearMPI();
        }else{
            waitForElementVisibleWithException(bInformationUpdated);
        }
        long endTime = System.currentTimeMillis();
        if ((endTime - startTime) < expectedSec) {
            System.out.println("<====== Save EVIR takes " + TimeUnit.MILLISECONDS.toSeconds(endTime - startTime) + " seconds, test passed ======>");
        } else {
//            Assert.fail("<====== Save EVIR takes in " + TimeUnit.MILLISECONDS.toSeconds(endTime - startTime) + " seconds, test failed ======>");
            System.err.println("<===Warning !!! Save EVIR takes in " + TimeUnit.MILLISECONDS.toSeconds(endTime - startTime) + " seconds, test failed ======>");
        }
    }

    public void bulkUpdateOK(int sectionNo){
        dWait.until(conditionVisible(dCheckAllOK(sectionNo))).click();
    }

    public void markSectionAllOK(){
        waitForTitle("MPI Details");
        waitForLoadingCircleToDisappearMPI();
        waitForElementVisibleWithException(dCheckOK);
        List<WebElement> elementCount = driver.findElements(dCheckOK);
        int totalSection = elementCount.size()+1;
        for (int i=1;i<totalSection;i++){
            scrollPageUp(1000);
            dWait.until(conditionVisible(dCheckAllOK(i))).click();
            System.out.println("<====== The '"+i+"' inspection section marked as OK ======>");
        }
    }

    public void selectCoolant(String text){
        selectDropList(dCoolant, text);
    }

    public void selectTireIncomingPsi(int sectionNo, String text) {
        scrollPageUp(1000);
        selectDropList(dTireIncoming(sectionNo), text);
    }

    public void selectTireAdjustedPsi(int sectionNo, String text) {
        selectDropList(dTireAdjusted(sectionNo), text);
    }

    public void selectTireTreadDepth(int sectionNo, String text) {
        selectDropList(dTireTreadDepth(sectionNo), text);
    }

    public void selectTorqueSpec(String text){
        selectDropList(dTorqueSpec, text);
    }

    public void clickOnMarkCompleted(){
        waitForLoadingCircleToDisappearMPI();
        dWait.until(conditionVisible(markCompleted)).click();
        waitForTitle("MPI List");
    }

    public void clickOnReviseInspection(){
        waitForLoadingCircleToDisappearMPI();
        clickElementWithException(bReviseInspection);
//        try{
//            driver.findElement(bReviseInspection).click();
//            clickElementWithSeconds(bReviseInspection,3000);
//        }catch(WebDriverException ex){
//            System.out.println("<====== Hi, this is a bug, user should land on Recommendation tab, not approved service ======>");
//            clickOnRecommendationTab();
//            clickElementWithSeconds(bReviseInspection,3000);
//        }
    }

    public void markReviewClick(){
        waitForLoadingCircleToDisappearMPI();
        dWait.until(conditionClick(bMarkReview)).click();
    }

    public void clickOnVehicleQueueIcon(){
        waitForLoadingCircleToDisappearMPI();
        sleep(2000);
        dWait.until(conditionVisible(bVehicleQueueIcon)).click();
    }

//    public void clickOnVehicleQueueIcon(){
//        waitForLoadingCircleToDisappearMPI();
//        clickElementTimesTillDisappear(driver.findElement(bVehicleQueueIcon), 10);
//    }

    public void verifyClickOnVehicleQueueIconTime(String expectedSeconds){
        long startTime = System.currentTimeMillis();
        long expectedSec = Integer.parseInt(expectedSeconds) * 1000;
        waitForLoadingCircleToDisappearMPI();
        dWait.until(conditionVisible(bVehicleQueueIcon)).click();
        waitForTitle("MPI List");
        long endTime = System.currentTimeMillis();
        if ((endTime - startTime) < expectedSec) {
            System.out.println("<====== Go back to MPI List takes " + TimeUnit.MILLISECONDS.toSeconds(endTime - startTime) + " seconds, test passed ======>");
        } else {
//            Assert.fail("<====== Go back to MPI List takes in " + TimeUnit.MILLISECONDS.toSeconds(endTime - startTime) + " seconds, test failed ======>");
            System.err.println("<====Warning !!!  Go back to MPI List takes in " + TimeUnit.MILLISECONDS.toSeconds(endTime - startTime) + " seconds, test failed ======>");
        }
    }

    public void clickOnConfirm(){
        dWait.until(conditionClick(bConfirm)).click();
    }

    public void clickOnReadyForProcessing(){
        dWait.until(conditionClick(bReadyForProcessing)).click();
        waitForTitle("MPI List");
    }

    public void clickOnRecommendationTab(){
        long startTime = System.currentTimeMillis();
        waitForLoadingCircleToDisappearMPI();
        clickElementWithException(bRecommendationTab);
        while(!isUserOnRecommendationTab()){
            driver.findElement(bRecommendationTab).click();
            if((System.currentTimeMillis()-startTime)> 5000){
                break;
            }
        }
    }

    public boolean isUserOnRecommendationTab(){
        if(getElementAttributeWithException(bRecommendationTab,"class").contains("tab-selected") && getElementAttributeWithException(bRecommendationTab,"style").contains("rgb(236, 235, 235)")){
            return true;
        }else{
            return false;
        }
    }

    public void clickOnPartsListTab(){
        waitForLoadingCircleToDisappearMPI();
        clickElementWithException(bPartsListTab);
    }

    public void clickOnApprovedServicesTab(){
        waitForLoadingCircleToDisappearMPI();
        clickElementWithException(bApprovedServicesTab);
    }

    public void clickOnWalkAroundTab(){
        waitForLoadingCircleToDisappearMPI();
        clickElementWithException(bWalkAroundTab);
    }

    public void clickOnInspectionTab(){
        waitForLoadingCircleToDisappearMPI();
        clickElementWithException(bInspectionTab);
    }

    public void clickOnViewHistory(){
        waitForLoadingCircleToDisappearMPI();
        clickElementWithException(bViewHistoryTab);
    }

    public void clickOnViewMenu(){
        waitForLoadingCircleToDisappearMPI();
        dWait.until(conditionClick(bViewMenuTab)).click();
    }

    public void clickOnStatusLog(){
        waitForLoadingCircleToDisappearMPI();
        dWait.until(conditionClick(bStatusLogTab)).click();
    }



    public String getInspectStatusOnMPIPage() {
        waitForLoadingCircleToDisappearMPI();
        String image = dWait.until(conditionVisible(bInspectStatusMPI))
                .getAttribute("src");
        String status = "";
        if (!image.isEmpty()) {
            int begin = image.indexOf("/status_");
            int end = image.indexOf(".png");
            String imgSn = image.substring(begin + 8, end);
            switch (imgSn) {
                case "1":
                    status = "CREATED";
                    break;
                case "2":
                    status = "STARTED";
                    break;
                case "3":
                    status = "COMPLETED";
                    if(driver.findElements(bRevisedSubStatus).size()!=0){
                        status = "COMPLETED - revised";
                    }
                    break;
                case "4":
                    status = "REVIEWED";
                    if(driver.findElements(bRevisedSubStatus).size()!=0){
                        status = "REVIEWED - revised";
                    }
                    break;
                case "5":
                    status = "READY";
                    break;
                case "7":
                    status = "CONFIRMED";
                    if(driver.findElements(bRevisedSubStatus).size()!=0){
                        status = "CONFIRMED - revised";
                    }
                    break;
                case "9":
                    status = "PARTS REQUESTED";
                    if(driver.findElements(bRevisedSubStatus).size()!=0){
                        status = "PARTS REQUESTED - revised";
                    }
                    break;
                case "10":
                    status = "READY FOR PROCESSING";
                    break;
                case "11":
                    status = "IN-REVISION";
                    break;
            }
        }
        return status;
    }
}
