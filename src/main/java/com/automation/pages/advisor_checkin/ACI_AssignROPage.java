package com.automation.pages.advisor_checkin;

import com.automation.utils.otherUtils.CommonMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ACI_AssignROPage extends ACI_FramePage {

    public void AdvisorCheckInAssignROPage(){
        setWait();
    }

    private static Logger log = Logger.getLogger(ACI_AssignROPage.class);

    //next button on assign ro page
    public static By bNextButtonOnAssignRo = By.id("g-aproceed");

    //assign RO tag
    public static By bAssignRoTag = By.xpath("//a[contains(text(),'ASSIGN R.O.')]");

    //promise time on the assign RO page
    private static By bPromiseTime = By.xpath("//div[@date-picker-title='PROMISE TIME']/input");

    //year up arrow on promise time pop up on assign RO page
//    private static By bYearPlus = By.xpath("(//input[@class='dateCalendarinput']/..//div[@class='dwb-e dwwb dwwbp'])[3]/span");

     // updated by David
    private static By bYearPlus = By.xpath("(//div[@class='dwwl dwrc dwwl2'])[1]/div[1]/span");
    private static By bYearMus = By.xpath("(//div[@class='dwwl dwrc dwwl2'])[1]/div[2]/span");

    private static By bMonthPlus = By.xpath("(//div[@class='dwwl dwrc dwwl1'])[1]/div[1]/span");
    private static By bMonthMus = By.xpath("(//div[@class='dwwl dwrc dwwl1'])[1]/div[2]/span");

    private static By bDayPlus = By.xpath("(//div[@class='dwwl dwrc dwwl0'])[1]/div[1]/span");
    private static By bDayMus = By.xpath("(//div[@class='dwwl dwrc dwwl0'])[1]/div[2]/span");

    //green check confirm on the promise time pop up
    private static By bConfirmPromiseTime = By.id("saveDate");

    //tag input field on Assign RO page
    private static By bTag = By.xpath("//input[@name='tagNumber']");

    //comment input field on Assign RO page
    private static By bComments = By.xpath("//textarea[@title='comments']");

//    // Promise Time Picker Button
//    @FindBy(css="[date-picker-title='PROMISE TIME'] button")
//    private static WebElement promiseTimePicker;
    private static By bpromiseTimePickerLocator = By.cssSelector("[date-picker-title='PROMISE TIME'] button");
//
//    // TAG enter field
////	@FindBy(css=".span12.ng-invalid.ng-invalid-required")			// 2.7
//    @FindBy(name="tagNumber")			// 2.8
//    private static WebElement tagField;
//
//    // Tag Mandatory Message List
//    @FindBy(css=".span12.ng-invalid.ng-invalid-required+label")
//    protected static WebElement tagMandatoryMsg;
//
//    // Hour plus for Promise time
//    @FindBy(css="div.dwwl.dwrc.dwwl3>div.dwb-e.dwwb.dwwbp>span")
//    private static WebElement hourPlus;
        private static By bhourPlus = By.cssSelector("div.dwwl.dwrc.dwwl3>div.dwb-e.dwwb.dwwbp>span");
//
//    // Hour Minus for Promise time
//    @FindBy(css="div.dwwl.dwrc.dwwl3>div.dwb-e.dwwb.dwwbm>span")
//    private static WebElement hourMinus;
       private static By bhourMinus = By.cssSelector("div.dwwl.dwrc.dwwl3>div.dwb-e.dwwb.dwwbm>span");
//
//
//    // Current Hour
//    @FindBy(css=" div.modal.hide.fade.ng-scope.in div.dwwl.dwrc.dwwl3 div.dw-li.dw-v.dw-sel")
//    private static WebElement currentHour;
      private static By bcurrentHour = By.cssSelector("div.modal.hide.fade.ng-scope.in div.dwwl.dwrc.dwwl3 div.dw-li.dw-v.dw-sel");
//
//
//    // Hour At 9
//    @FindBy(css="div.dwwl.dwrc.dwwl3 div.dw-bf div:nth-child(10)")
//    private static WebElement hourAt9;
//
//    // Hour At 10
//    @FindBy(css="div.dwwl.dwrc.dwwl3 div.dw-bf div:nth-child(11)")
//    private static WebElement hourAt10;
      private static By bhourAt10 = By.cssSelector("div.dwwl.dwrc.dwwl3 div.dw-bf div:nth-child(11)");
//
//
//    // Minute At 00
//    @FindBy(css="div.dwwl.dwrc.dwwl4 div.dw-bf:nth-child(1) div.dw-li.dw-v:nth-child(1)")
//    private static WebElement minuteAtZero;
//
//
//    // Month at January
//    @FindBy(css="div.dwwl.dwrc.dwwl1 div.dw-li:nth-child(1)")
//    private static WebElement monAtJan;
//
//    // Month at February
//    @FindBy(css="div.dwwl.dwrc.dwwl1 div.dw-li:nth-child(2)")
//    private static WebElement monAtFeb;
//
//    // Month at March
//    @FindBy(css="div.dwwl.dwrc.dwwl1 div.dw-li:nth-child(3)")
//    private static WebElement monAtMarch;
//
//    // Month at April
//    @FindBy(css="div.dwwl.dwrc.dwwl1 div.dw-li:nth-child(4)")
//    private static WebElement monAtApril;
//
//    // Month at May
//    @FindBy(css="div.dwwl.dwrc.dwwl1 div.dw-li:nth-child(5)")
//    private static WebElement monAtMay;
//
//    // Month at June
//    @FindBy(css="div.dwwl.dwrc.dwwl1 div.dw-li:nth-child(6)")
//    private static WebElement monAtJune;
//
//    // Month at July
//    @FindBy(css="div.dwwl.dwrc.dwwl1 div.dw-li:nth-child(7)")
//    private static WebElement monAtJul;
//
//    // Month at August
//    @FindBy(css="div.dwwl.dwrc.dwwl1 div.dw-li:nth-child(8)")
//    private static WebElement monAtAug;
//
//    // Month at September
//    @FindBy(css="div.dwwl.dwrc.dwwl1 div.dw-li:nth-child(9)")
//    private static WebElement monAtSep;
//
//    // Month at October
//    @FindBy(css="div.dwwl.dwrc.dwwl1 div.dw-li:nth-child(10)")
//    private static WebElement monAtOct;
//
//    // Month at November
//    @FindBy(css="div.dwwl.dwrc.dwwl1 div.dw-li:nth-child(11)")
//    private static WebElement monAtNov;
//
//    // Month at December
//    @FindBy(css="div.dwwl.dwrc.dwwl1 div.dw-li:nth-child(12)")
//    private static WebElement monAtDec;
//
//    // Current Month, use getText attribute to get the month reading
//    @FindBy(css="div.dwwl.dwrc.dwwl1 div.dw-li.dw-sel.dw-v")
//    private static WebElement currentMon;
    private static By currentMonthLocator = By.cssSelector("div.dwwl.dwrc.dwwl1 div.dw-li.dw-sel.dw-v");
//
//    // Month Plus
//    @FindBy(css="div.dwwl.dwrc.dwwl1>div.dwb-e.dwwb.dwwbp>span")
//    private static WebElement monthPlus;
      private static By bmonthPlus = By.cssSelector("div.dwwl.dwrc.dwwl1>div.dwb-e.dwwb.dwwbp>span");
//
//
//    // current Date, use getText attribute to get the date reading
//    @FindBy(css="div.dwwl.dwrc.dwwl0 div.dw-li.dw-sel.dw-v")
//    private static WebElement currentDate;
     private static By currentDateLocator = By.cssSelector("div.dwwl.dwrc.dwwl0 div.dw-li.dw-sel.dw-v");
//
//
//
//    // Date Plus for Promise time
//    @FindBy(css="div.dwwl.dwrc.dwwl0>div.dwb-e.dwwb.dwwbp>span")
//    private static WebElement datePlus;
//
//    // Current Year
//    @FindBy(css=".dwwl.dwrc.dwwl2 .dw-li.dw-sel.dw-v")
//    private static WebElement currentYear;
     private static By currentYearLocator = By.cssSelector(".dwwl.dwrc.dwwl2 .dw-li.dw-sel.dw-v");
//
//
//    // Year Plus
//    @FindBy(css="div.dwwl.dwrc.dwwl2>div.dwb-e.dwwb.dwwbp>span")
//    private static WebElement yearPlus;
//
//    // AM or Pm Plus
//    @FindBy(css="div.dwwl.dwrc.dwwl5>div.dwb-e.dwwb.dwwbp>span")
//    private static WebElement amPMPlus;
      private static By bAPMPlus = By.cssSelector("div.dwwl.dwrc.dwwl5>div.dwb-e.dwwb.dwwbp>span");
//

//    @FindBy(css="div.modal.hide.fade.ng-scope.in div.dwwl.dwrc.dwwl5 div.dw-bf div.dw-li.dw-v:nth-child(1)")
//    private static WebElement aM;
      private static By bAM = By.cssSelector("div.modal.hide.fade.ng-scope.in div.dwwl.dwrc.dwwl5 div.dw-bf div.dw-li.dw-v:nth-child(1)");
//
//
//    // Next Button
//    @FindBy(id="g-aproceed")
//    protected static WebElement nextBtn;
//
//    // Promise Time Title Locator
//    private static By promiseTimeTitleLocator = By.cssSelector(".modal .modal-pad .modal-header h3.ng-binding");
//
//    // Comment Field
//    @FindBy(css="textarea[title='comments']")
//    private static WebElement commentField;
//
//    // Current selected date
//    @FindBy(id="mobiscroll1425410286881")
//    private static WebElement selectDate;
//
//    // Transportation Selection Field
//    @FindBy(css="#assign div.scrollcontent > div:nth-child(3) select")
//    private static WebElement transportationField;
//    private static Select transportation;
     private static By btransportation = By.cssSelector("#assign div.scrollcontent > div:nth-child(3) select");
//
//    // Inspection Type Field
//    @FindBy(css="#assign div.scrollcontent div:nth-child(7) .inspectiontype select")
//    private static WebElement inspectionTypeField;
//    private static Select inspectionType;
      private static By binspectionType = By.cssSelector("#assign div.scrollcontent div:nth-child(7) .inspectiontype select");
//
//    // Inspection Type Field
//    @FindBy(css="#g-viewDropdown > select")
//    private static WebElement sAdvisorSelect;
    private static By bAdvisorSelect = By.xpath("//select[@name='service_advisor']");

    private static By bTechnicianSelect = By.xpath("//select[@name='technician']");

    public void clickNextOnAssignRO(){
        clickElementWithException(bNextButtonOnAssignRo);
    }

    public void updateYearOnAssignRO(String year){
        clickElementWithException(bPromiseTime);
        int actualYear = Integer.parseInt(year);
        for(int i=0;i<actualYear;i++){
            clickElementWithException(bYearPlus);
        }
        clickElementWithException(bConfirmPromiseTime);
    }

    public void setPromiseDateTimeOnAssignRO(String aday , int ahour , String apm  ){

        moveToClick(bpromiseTimePickerLocator);
        setPromiseDay(aday);
        setHour(ahour);

        // set AM PM
        WebElement eAM = driver.findElement(bAM);
        if (apm.equalsIgnoreCase("AM")){
            if(!webElementHasClass(eAM,"dw-sel")){
                driver.findElement(bAPMPlus).click();
            }
        }else if (apm.equalsIgnoreCase("PM")){
            if(webElementHasClass(eAM,"dw-sel")){
                driver.findElement(bAPMPlus).click();
            }
        }
        dWait.until(conditionClick(bConfirmPromiseTime)).click();
    }

    private  void setHour(int ahour){		// Select Hour to 10

//        String ch = ahour.substring(0,1);
//        String num = ahour.substring(1);
//        int iNum = Integer.valueOf(num);
//        for (int i =0; i< iNum ; i++){
//            if (ch.equals("+")){
//                dWait.until(conditionVisible(bhourPlus)).click();
//            }else if (ch.equals("-")){
//                dWait.until(conditionVisible( bhourMinus)).click();
//            }
//        }
        By locator = bhourAt10;
        if (ahour==10){
            locator = bhourAt10;
        }
        int hour = Integer.parseInt(driver.findElement(bcurrentHour).getText().trim());
        while(!webElementHasClass(driver.findElement(locator),"dw-sel")){
            if(hour > 9 || hour < 3){
                dWait.until(conditionVisible(bhourPlus)).click();
            } else {
                dWait.until(conditionVisible( bhourMinus)).click();
            }
        }

    }

    private void setPromiseDay(String aday ){
        int iday = Integer.parseInt(aday);
        if (iday == 1){
          actionClick(bDayPlus);
        }

        String sDate = driver.findElement(currentYearLocator).getText().trim();
        int iYear = Integer.valueOf(sDate);
        if (iYear < 2000){  iYear = iYear + 2000;
        }
        sDate = driver.findElement(currentMonthLocator).getText().trim();
        int iMonth = Integer.valueOf(sDate.substring(0,2));
        sDate = driver.findElement(currentDateLocator).getText().trim();
        int iDay = Integer.valueOf(sDate.substring(0,2));

        if (CommonMethods.isToday(iYear,iMonth,iDay)){
            actionClick(bDayPlus);
        }

        boolean weekendFound = true;

        do{
            if(isEndOfMonthOrYear()){
                actionClick(bDayPlus);
            }

            if(isWeekend()){
                actionClick(bDayPlus);
                weekendFound = true;
            } else {
                weekendFound = false;
            }
        } while (weekendFound);
    }

    private boolean  isEndOfMonthOrYear(){

        String sDate = driver.findElement(currentMonthLocator).getText().trim();
        int iMonth = Integer.valueOf(sDate.substring(0,2));
        sDate = driver.findElement(currentDateLocator).getText().trim();
        int date = Integer.valueOf(sDate.substring(0,2));

        boolean result = false;
        switch(iMonth){
            case 1: if(date==31){
                actionClick(bmonthPlus);
                result = true;
            }
                break;
            case 2: if(date==28 || date==29){
                actionClick(bmonthPlus);
                result = true;
            }
                break;
            case 3: if(date==31){
                actionClick(bmonthPlus);
                result = true;
            }
                break;
            case 4: if(date==30){
                actionClick(bmonthPlus);
                result = true;
            }
                break;
            case 5: if(date==31){
                actionClick(bmonthPlus);
                result = true;
            }
                break;
            case 6: if(date==30){
                actionClick(bmonthPlus);
                result = true;
            }
                break;
            case 7: if(date==31){
                actionClick(bmonthPlus);
                result = true;
            }
                break;
            case 8: if(date==31){
                actionClick(bmonthPlus);
                result = true;
            }
                break;
            case 9: if(date==30){
                actionClick(bmonthPlus);
                result = true;
            }
                break;
            case 10: if(date==31){
                actionClick(bmonthPlus);
                result = true;
            }
                break;
            case 11: if(date==30){
                actionClick(bmonthPlus);
                result = true;
            }
                break;
            case 12:
                if(date==31){
                    actionClick(bYearPlus);
                    actionClick(bmonthPlus);
                    actionClick(bDayPlus);
                    result = true;
                }
                break;
        }
        return result;

    }

    private boolean  isWeekend(){

        String sDate = driver.findElement(currentYearLocator).getText().trim();
        int iYear = Integer.valueOf(sDate);
        if (iYear < 2000){  iYear = iYear + 2000;
        }
        sDate = driver.findElement(currentMonthLocator).getText().trim();
        int iMonth = Integer.valueOf(sDate.substring(0,2));
        sDate = driver.findElement(currentDateLocator).getText().trim();
        int iDay = Integer.valueOf(sDate.substring(0,2));
        return CommonMethods.isWeekend(iYear,iMonth,iDay);

    }

    public void updatePageInformation(String updateFieldName, String pageName){
        switch (pageName){
            case "ASSIGN R.O.":
                if(updateFieldName.toLowerCase().equals("random")){
                    updateFieldName = CommonMethods.getRandomText(5);
                }
                clearAndInputElementWithException(bTag,updateFieldName);
                //dWait.until(conditionVisible(bComments)).click(); //will remove in the future
                //clearAndSend(bComments, "QA Darren travel here and mark on it"); //will remove in the future, it will get override by next method
                break;
        }
    }

    public void setTagValueOnAssignROPage(String updateFieldName){
                if(updateFieldName.toLowerCase().equals("random")){
                    updateFieldName = CommonMethods.getRandomText(5);
                }
                clearAndInputElementWithException(bTag,updateFieldName);
                //dWait.until(conditionVisible(bComments)).click(); //will remove in the future
                //clearAndSend(bComments, "QA Darren travel here and mark on it"); //will remove in the future, it will get override by next method

    }

    public void updateComment(String comment){
        clearAndInputElementWithException(bComments, comment);
    }

    public  void selectServiceAdvisorInASSIGNRO(String value ) {
        sleep(1000);
        try{
            selectDropList(driver.findElement(bAdvisorSelect),value);
        } catch (NoSuchElementException e){
            log.info("Advisor not exists");
        }
    }

    public  void selectTechnicianInASSIGNRO(String value ) {
        sleep(1000);
        try{
            selectDropList(driver.findElement(bTechnicianSelect),value);
        } catch (NoSuchElementException e){
            log.info("Technician not exists");
        }
    }

    public  void setTransportationInASSIGNRO(String value ) {
        sleep(1000);
        try{
            selectDropList(driver.findElement(btransportation),value);
        } catch (NoSuchElementException e){
            log.info("Transportation not exists");
        }
    }

    public  void setInspectionTypeInASSIGNRO(String value ) {
        try{
            selectDropList(driver.findElement(binspectionType),value);
        } catch (NoSuchElementException e){
            log.info("Transportation not exists");
        }
    }
    
    public void validateTransportationOption(String transportationOption){
        CommonMethods.verifyElementExists(driver.findElement(By.xpath("//select[@name='transportationOption']/option[text()='"+transportationOption+"']")));
    }    

}
