package com.automation.pages.DMPI;

import com.automation.pages.common.WebPage;
import com.automation.utils.dataProvider.TestParameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DynamicMPIPage extends WebPage {

    //locator
    //name field
    //private static By bNameField = By.xpath("//label[contains(text(),'Please enter your')]/following-sibling::div/input");
    private static By bNameField = By.xpath("//label[contains(text(),'Please enter your last name')]/following-sibling::input");    

    //enter button
    private static By bEnterOnDMPI = By.xpath("//button[contains(text(),'Enter')]");

    //Service advisor name
    private static By bServiceAdvisor = By.xpath("//div[contains(text(),'Your service advisor,')]/following-sibling::div[1]");

    //recommendation number
    private static By bRecommendationsNo = By.xpath("//span[contains(text(),'RECOMMENDATIONS')]");

    //vehicle name on DMPI
    private static By bVehicleName = By.xpath("//div[contains(text(),'for your ')]");

    //Customer concerns
    private static By bCustomerConcerns = By.xpath("//b[contains(text(),'Customer Concerns')]");

    //Guest concerns greybard
    private static By bGuestConcernsGreybard = By.xpath("//b[contains(text(),'Guest Concerns Greybard')]");

    //Customer concerns safety
    private static By bCustomerConcernsSafety = By.xpath("//b[contains(text(),'Customer Concerns Safety')]");

    //Customer concerns attention
    private static By bCustomerConcernsAttention = By.xpath("//b[contains(text(),'Customer Concerns Attention ')]");

    //Require immediate attention
    private static By bRequiresImmediateAttention = By.xpath("//b[contains(text(),'Requires Immediate Attention ')]");

    //May Need Future Attention
    private static By bMayNeedFutureAttention = By.xpath("//b[contains(text(),'May Need Future Attention')]");

    //continue button on DMPI page
    private static By bContinueOnDMPI = By.xpath("//button[contains(text(),'CONTINUE')]");
    
    //view items button on DMPI page
    private static By bViewItemsOnDMPI = By.xpath("//button[@class='button cta' and contains(text(),'View Items')]");
    
    //confirm button on DMPI page
    private static By bConfirmOnDMPI = By.xpath("//button[@class='confirm' and contains(text(),'Confirm')]");

    //count down minute on DMPI page
    private static By bCountDownMinOnDMPI = By.id("timer");

    //promise time on DMPI page
    private static By bPromiseTimeOnDMPI = By.xpath("//div[contains(text(),'To have your vehicle ready by')]/following-sibling::div");

    //concern price on inspection summary page on DMPI
    private static By bConcernPriceAssoicatedWithRecommandationName(String concernName){
        String locator = "//span[contains(text(),'"+concernName+"')]/../../../following-sibling::div/div/span[@class='price']";
        return By.xpath(locator);
    }

    //attention price on inspection summary page on DMPI
    private static By bAttentionPriceAssoicatedWithRecommandationName(String attentionName){
        String locator = "//span[contains(text(),'"+attentionName+"')]/../../following-sibling::div[2]/div/span[@class='price']";
        return By.xpath(locator);
    }

    //next button on DMPI
    private static By bNextOnDMPI = By.xpath("//div[@class='proceed-btn' and contains(text(),'Next')]");

    //bAcceptEstimate on DMPI service summary
    private static By bAcceptEstimate = By.xpath("//div[@class='proceed-btn' and contains(text(),'Accept Estimate')]");

    //click to sign
//    private static By bClickToSign = By.xpath("//div[@class='click-sign' and contains(text(),'Click to sign')]");
    private static By bClickToSign = By.xpath("//button[@class='sign' and contains(text(),'Click to sign')]");    

    //agree checkbox
//    private static By bAgreeCheckBox = By.xpath("//span[@class='box']");
    private static By bAgreeCheckBox = By.xpath("//label/p[@class='text' and contains(text(),'I acknowledge the agreement of the Dealership’s')]");    

    //approve on DMPI service summary
//    private static By bApproveOnDMPI = By.xpath("//div[contains(@class,'approve-btn') and contains(text(),'Approve')]");
    private static By bApproveOnDMPI = By.xpath("//button[@class='button cta green' and contains(text(),'Approve')]");    

    //dealer confirm message
//    private static By bDealerConfirmMsg = By.xpath("//div[contains(@class,'thanks-page')]/div[2]/h2");
    private static By bDealerConfirmMsg = By.xpath("//h2[@class='subheader']");    

    //delivery time box
    private static By bDeliveryPromisBox = By.xpath("//div[@class='ready-box']");

    public String verifyDMPIPageTiTle(String pageTitle){
        waitForTitle(pageTitle);
        return driver.getTitle();
    }

    public void inputNameOnNameField(String nameType, String name){
        clearAndInputElementWithException(bNameField,name);
    }

    public void clickEnterOnDMPIPage(){
        dWait.until(conditionClick(bEnterOnDMPI)).click();
    }

    public String getServiceAdvisorNameOnDMPI(){
        return getElementTextWithException(bServiceAdvisor);
    }

    public String getRecommendationNumberOnDMPI(){
        return getElementTextWithException(bRecommendationsNo);
    }

    public String getVehicleNameOnDMPI(){
        return getElementTextWithException(bVehicleName);
    }

    public String getCustomerConcernNumberOnDMPI(){
        return getElementTextWithException(bCustomerConcerns);
    }

    public String getGuestConcernsNumberOnDMPI(){
        return getElementTextWithException(bGuestConcernsGreybard);
    }

    public String getCustomerConcernSafetyOnDMPI(){
        return getElementTextWithException(bCustomerConcernsSafety);
    }

    public String getCustomerConcernAttentionOnDMPI(){
        return getElementTextWithException(bCustomerConcernsAttention);
    }

    public String getRequiresImmediateAttentionOnDMPI(){
        return getElementTextWithException(bRequiresImmediateAttention);
    }

    public String getMayNeedFutureAttentionOnDMPI(){
        return getElementTextWithException(bMayNeedFutureAttention);
    }

    public void clickContinueOnDMPIPage(){
        dWait.until(conditionClick(bContinueOnDMPI)).click();
    }

    public String getCountDownMinuteOnDMPI(){
        long startTime = System.currentTimeMillis();
        String wholeMin = "";
        while(!wholeMin.matches(".*\\d+.*")){
            wholeMin = getElementTextWithException(bCountDownMinOnDMPI);
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 40000){
                break;
            }
        }
        int indexStartBeforeMin = wholeMin.indexOf("h ");
        int indexEndAfterM = wholeMin.indexOf(" m");
        String startWithMin = wholeMin.substring(indexStartBeforeMin);
        String endAfterMin = wholeMin.substring(indexEndAfterM);
        TestParameters.countDownMinuteOnDMPI = startWithMin.replace(endAfterMin,"").replace("h ","").replace(" m","");
        System.out.println("<====== The timer show on the DMPI page is '"+ TestParameters.countDownMinuteOnDMPI +"' minutes ======>");
        return TestParameters.countDownMinuteOnDMPI;
    }

    public String getPromiseTimeOnDMPI(){
        Date date = new Date();
        DateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy");
        String currentDate = inputFormat.format(date);
        String pd = getElementTextWithException(bPromiseTimeOnDMPI).replace("Sept","Sep");
        if(pd.toLowerCase().contains("today")){
            TestParameters.vehicleReadyTimeOnDMPI = pd.replace("today!",""+currentDate+"");
        }else{
            TestParameters.vehicleReadyTimeOnDMPI = pd.replace("!","").replace(" on","");
        }
        return TestParameters.vehicleReadyTimeOnDMPI;
    }

    public boolean verifyCountDownMinuteOnDMPI(){
        DateFormat inputFormat = new SimpleDateFormat("MMM dd yyyy hh:mm a");
        Date date = new Date();
        Date answeredByDate = null;
        Date currentDate = null;
        try {
            answeredByDate = inputFormat.parse(TestParameters.answeredByTimeOnServiceDashboard);
            currentDate = inputFormat.parse(inputFormat.format(date));
        } catch (ParseException e) {
            System.out.println("<====== parse answered time exception happened ======>");
        }
        long timeDiff = answeredByDate.getTime()-currentDate.getTime();
        long minDiff = timeDiff/(60*1000);
        int expectedMinDiff = (int)minDiff;
        int actualMinDiff = Integer.parseInt(TestParameters.countDownMinuteOnDMPI);
        System.out.println("<====== The current system time minus answered By time from service visit is '"+actualMinDiff+"' minutes ======>");
        return (expectedMinDiff - actualMinDiff) <= 1 && (expectedMinDiff - actualMinDiff) >= 0;
    }

    public boolean verifyPromiseTimeOnDMPI(){
        DateFormat inputFormatServiceVisit = new SimpleDateFormat("MMM dd yyyy hh:mm a");
        DateFormat inputFormatDMPI = new SimpleDateFormat("hh:mm a MMM dd yyyy");
        Date promiseDateServiceVisit = null;
        Date promiseDateDMPI = null;
        try {
            promiseDateServiceVisit = inputFormatServiceVisit.parse(TestParameters.deliverByTimeOnServiceDashboard);
            promiseDateDMPI = inputFormatDMPI.parse(TestParameters.vehicleReadyTimeOnDMPI.replace(",",""));
        } catch (ParseException e) {
            System.out.println("<====== parse promise date exception happened ======>");
        }
        if(promiseDateServiceVisit.equals(promiseDateDMPI)){
            return true;
        }else{
            System.out.println("<====== The promise time on DMPI bridge "+promiseDateServiceVisit+" is not matching the promise time on DMPI page "+promiseDateDMPI+", test fail... ======>");
            return false;
        }
    }

    public String getConcernPrice(String recommendationName, String expectedPrice){
        long startTime = System.currentTimeMillis();
        String attentionPrice = "";
        while(!attentionPrice.equals(expectedPrice)){
            attentionPrice = getElementTextWithException(bConcernPriceAssoicatedWithRecommandationName(recommendationName));
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 20000){
                System.out.println("<====== The price of "+recommendationName+" is incorrect which shows "+attentionPrice+", test failed ======>");
                break;
            }
        }
        return attentionPrice;
    }

    public String getAttentionPrice(String recommendationName, String expectedPrice){
        long startTime = System.currentTimeMillis();
        String attentionPrice = "";
        while(!attentionPrice.equals(expectedPrice)){
            attentionPrice = getElementTextWithException(bAttentionPriceAssoicatedWithRecommandationName(recommendationName));
            sleep(1000);
            if((System.currentTimeMillis()-startTime)> 20000){
                System.out.println("<====== The price of "+recommendationName+" is incorrect which shows "+attentionPrice+", test failed ======>");
                break;
            }
        }
        return attentionPrice;
    }

    public void clickOnNextOnDMPI(){
        dWait.until(conditionClick(bNextOnDMPI)).click();
    }

    public void clickAcceptEstimateOnDMPIPage(){
        dWait.until(conditionClick(bAcceptEstimate)).click();
    }

    public void clickToSign(){
        dWait.until(conditionClick(bClickToSign)).click();
        sleep(3000);
    }

    public void toggleOnAgreeCheckBox(){
        dWait.until(conditionClick(bAgreeCheckBox)).click();
        sleep(3000);        
    }

    public void clickApproveOnDMPI(){
        long startTime = System.currentTimeMillis();
        while(!driver.getCurrentUrl().contains("thanks")){
            try{
                driver.findElement(bApproveOnDMPI).click();
                System.out.println("<====== The approved button clicked ======>");
            }catch (WebDriverException e){
                //the approve button is not there
            }
            sleep(1000); //click apart every 1 sec
            if((System.currentTimeMillis()-startTime)> 10000){
                break;
            }
        }
    }

    public String getConfirmMessageWithDealerName(){
        String confirmMsg = getElementTextWithException(bDealerConfirmMsg);
        return confirmMsg;
    }

    public boolean verifyDeliveryPromiseTime(){
        DateFormat inputFormatServiceVisit = new SimpleDateFormat("MMM dd yyyy hh:mm a");
        DateFormat justDatePageInputFormat = new SimpleDateFormat("MMM dd, yyyy");
        DateFormat pageInputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mma");
        Date date = new Date();
        Date promiseDateServiceVisit = null;
        Date promiseDateDMPI = null;
        String deliveryTime = getElementTextWithException(bDeliveryPromisBox);
        int indexStart = deliveryTime.indexOf("on ");
        String startWithDeliveryTime = deliveryTime.substring(indexStart);
        String finalDeliveryTime = startWithDeliveryTime.replace("on ","").replace("am","AM").replace("pm","PM");
        if(!finalDeliveryTime.contains("at ")){
            finalDeliveryTime = justDatePageInputFormat.format(date)+" "+finalDeliveryTime;
        }else{
            finalDeliveryTime = finalDeliveryTime.replace("at ","");
        }
        try {
            promiseDateServiceVisit = inputFormatServiceVisit.parse(TestParameters.deliverByTimeOnServiceDashboard);
            promiseDateDMPI = pageInputFormat.parse(finalDeliveryTime);
        } catch (ParseException e) {
            System.out.println("<====== parse promise date exception happened ======>");
        }
        if(promiseDateServiceVisit.equals(promiseDateDMPI)){
            return true;
        }else{
            System.out.println("The time on service dashboard "+promiseDateServiceVisit+" mismatched the time on DMPI "+promiseDateDMPI+"");
            return false;
        }
    }
    
    public void clickViewItemsOnDMPIPage(){
        dWait.until(conditionClick(bViewItemsOnDMPI)).click();
    }    
    
    public void clickConfirmOnDMPIPage(){
        dWait.until(conditionClick(bConfirmOnDMPI)).click();
    }       
}
