package com.automation.pages.tech_inspection.ServiceTab;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TechCamTab extends WebPage {

    InspectionTab iTab = new InspectionTab();

    //all web element locator
    //camera icon
    private static By bCameraIcon = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//input[@class='open-camera']");

    private static By bCameraPageLoading = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//div[@id='loading']");

    private static By bInspectDetailCamereLoading = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//div[@class='media-loading']");

    private static By bCameraClose = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//div[@class='ui-icon item-video-close']");

    private static By bSwitchCameraViewIcon = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//input[@id='switchCameraBtn']");

    private static By bCaptureIcon = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//input[@id='capture']");

    private static By bSelectPhotoOnTechCam = By.xpath("//img[@class='image-link']");

    private static By bDeleteIconPhotoOnInspectionDetail = By.xpath("//i[@class='pull-right ui-icon itemdetails-image-delete']");

    private static By bDeleteIcon = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//input[@id='delete']");

    private static By bConfirmYes = By.xpath("//button[contains(text(),'Yes')]");

    private static By bPhotoAttachmentRecommendation(String message){
        String checkLocator = "//select/option[contains(text(),'"+message+"')]";
        return By.xpath(checkLocator);
    }

    private static By bCameraMessage(String message){
        String checkLocator = "//div[@class='tbItemContainer' and @style='display: block;']//div[contains(text(),'"+message+"')]";
        return By.xpath(checkLocator);
    }

    private static By bPhotoAttachmentRecommendationTechCam(String message){
        String checkLocator = "//label[contains(text(),'"+message+"')]";
        return By.xpath(checkLocator);
    }

    public void waitForLoadingCircleToDisappearCamera(){
        waitForElementHasAttributeValue(bCameraPageLoading,"style","display: none;");
    }

    public void waitForLoadingCircleToDisappearCameraOnInspectionDetail(){
        waitForElementHasAttributeValue(bInspectDetailCamereLoading,"style","display: none;");
    }

    public void clickOnCameraIconFromInspectionDetail(){
        iTab.waitForLoadingCircleToDisappearMPI();
        clickElementWithException(bCameraIcon);
        iTab.waitForLoadingCircleToDisappearMPI();
    }

    public String verifySwitchCameraViewIconShow() {
        if(driver.findElements(bSwitchCameraViewIcon).isEmpty()){
            return "Icon not there";
        }else{
            return "Icon shows";
        }
    }

    public void clickOnCapture(String times){
        waitForLoadingCircleToDisappearCamera();
        dWait.until(conditionClick(bCaptureIcon));
        int timeNo = Integer.parseInt(times);
        for(int i=1;i<timeNo+1;i++){
            dWait.until(conditionClick(bCaptureIcon)).click();
            System.out.println("<====== I clicked on camera caputre "+i+" times ======>");
            waitForLoadingCircleToDisappearCamera();
        }
    }

    public String verifyMessageOnCamera(String message){
        String messageResult = "";
        if(driver.findElements(bCameraMessage(message)).size()!=0){
            messageResult = "message show";
        }else{
            messageResult = "message not show";
        }
        return messageResult;
    }

    public void selectPhoto(){
        dWait.until(conditionClick(bSelectPhotoOnTechCam)).click();
    }

    public void clickOnDeleteIconOnTechCam(){
        waitForLoadingCircleToDisappearCameraOnInspectionDetail();
        clickElementWithException(bDeleteIcon);
        dWait.until(conditionClick(bConfirmYes)).click();
    }

    public void clickOnDeleteIconOnInspectionDetail(){
        clickElementWithException(bDeleteIconPhotoOnInspectionDetail);
        clickElementWithException(bConfirmYes);
    }

    public void deletePhotoOnTechCam(String times){
        int timeNo = Integer.parseInt(times);
        for(int i=1;i<timeNo+1;i++){
            selectPhoto();
            clickOnDeleteIconOnTechCam();
            System.out.println("<====== I delete photo "+i+" times ======>");
            waitForLoadingCircleToDisappearCamera();
        }
    }

    public void deletePhotoOnInspectionDetail(String times){
        iTab.waitForLoadingCircleToDisappearMPI();
        waitForLoadingCircleToDisappearCamera();
        waitForLoadingCircleToDisappearCameraOnInspectionDetail();
        int timeNo = Integer.parseInt(times);
        for(int i=1;i<timeNo+1;i++){
            clickOnDeleteIconOnInspectionDetail();
            System.out.println("<====== I delete photo "+i+" times ======>");
            waitForLoadingCircleToDisappearCameraOnInspectionDetail();
        }
    }

    public String getPhotoNumberOnTechCamPage(){
        waitForLoadingCircleToDisappearCamera();
        List<WebElement> elementCount = driver.findElements(bSelectPhotoOnTechCam);
        int totalSection = elementCount.size();
        String numberOfphotoLeft= Integer.toString(totalSection);
        return numberOfphotoLeft;
    }

    public String getPhotoNumberOnInspectionDetailPage(){
        //dWait.until(conditionClick(bDeleteIconPhotoOnInspectionDetail));
        iTab.waitForLoadingCircleToDisappearMPI();
        waitForLoadingCircleToDisappearCameraOnInspectionDetail();
        List<WebElement> elementCount = driver.findElements(bDeleteIconPhotoOnInspectionDetail);
        int totalSection = elementCount.size();
        String numberOfphotoLeft= Integer.toString(totalSection);
        return numberOfphotoLeft;
    }

    public void clickOnCloseIcon(){
        dWait.until(conditionClick(bCameraClose)).click();
    }

    public String verifyRecommendationToThePhoto(String message){
        String result="";
        if(driver.findElements(bPhotoAttachmentRecommendation(message)).size()!=0){
            result=message;
        }else{
            result="recommendation not existed";
        }
        return result;
    }

    public void attachREcommendationToThePhoto(String message){
        dWait.until(conditionClick(bPhotoAttachmentRecommendation(message))).click();
    }

    public String verifyRecommendationToThePhotoOnTechCamPage(String message){
        return getElementTextWithException(bPhotoAttachmentRecommendationTechCam(message));
    }

}
