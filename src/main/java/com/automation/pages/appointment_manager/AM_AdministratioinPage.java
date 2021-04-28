package com.automation.pages.appointment_manager;

import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AM_AdministratioinPage extends WebPage {


    public int changes;

    private static Logger log = Logger.getLogger(AM_AdministratioinPage.class);

    //    protected static List<WebElement> advisorTime;
    private static final By bAdvisorTime = By.xpath("//div[@class='time']/button");

    //    protected static List<WebElement> openTime;
    private static final By bOpenTime = By.xpath("//div[@time-input='openTime']/div/input");

    //    protected static List<WebElement> closeTime;
    private static final By bCloseTime = By.xpath("//div[@time-input='closeTime']/div/input");

    //    protected static WebElement adminTag;
    private static final By bAdminTag = By.xpath("//section[@id='workspace--admin']");

    //    protected static WebElement administration;
    private static final By bAdministration = By.xpath("//ul[@id='user_menu']//a[contains(text(),'Administration')]");

    //    protected static WebElement userMenuTitle;
    private static final By bUserMenuTitle = By.cssSelector("button.user-menu-part small");

    //    protected static List<WebElement> AM;
    private static final By bAM = By.xpath("//div[@time-input='closeTime']//label[contains(text(),'AM')]");

    //    protected static List<WebElement> PM;
    private static final By bPM = By.xpath("//div[@time-input='openTime']//label[contains(text(),'PM')]");

    //    protected static WebElement timeIntervalView;
    private static final By bTimeIntervalView = By.xpath("//div[contains(@class,'time-interval-item') and not(contains(@class,'holiday'))]");

    //    private static WebElement addAppointmentBtn;
    private static final By bAddAppointmentBtn = By.cssSelector(".btn.add-apps.nav-trigger");

    //    protected static List<WebElement> customerList;
    private static final By bCustomerList = By.xpath("//div[@class='list full-size scroll--vertical']/div");

    //    protected static List<WebElement> holidayText;
    private static final By bHolidayTextn = By.xpath("//div[contains(@class,'time-interval-item') and contains(@class,'holiday')]");

    //    protected static WebElement holidayText1;
    private static final By bHolidayText1 = By.xpath("//div[contains(@class,'time-interval-item') and contains(@class,'holiday')]");

    //    protected static List<WebElement> checkBox;
    private static final By bCheckBox = By.xpath("//div[@class='checkbox-wrap']//label[@class='checkbox']");

    //    protected static WebElement  onlineApptText;
    private static final By bOnlineApptText = By.xpath("//span[contains(text(),'Accepts online')]");

    //    protected static WebElement  actionCount;
    private static final By bActionCount = By.xpath("//div[@class='count ng-binding']");

    //    protected static List<WebElement> notificationList;
    private static final By bNotificationList = By.xpath("//section[@class='notification-contain ng-scope success']");

    //    protected static WebElement  notificationButton;
    private static final By bNotificationButton = By.xpath("//section[@class='notification-counter-wrap visible']/button");

    //    protected static WebElement  notificationMenu;
    private static final By bNotificationMenu = By.xpath("//span[contains(text(),'Notification Center')]");

    //    protected static WebElement  clearButton;
    private static final By bClearButton = By.xpath("//button[@class='btn clear-center']");

    //    protected static WebElement  closeButton;
    private static final By bCloseButton = By.xpath("//button[contains(text(),'Close')]");

    protected static List<WebElement> tickButton;
    private static final By bTickButton = By.xpath("//div[@class='editing list-cols']//div[@class='controls']//button[@class='btn btn-link btn-ext btn-inner btn-inner-green-normal-1 btn-inner-size-40-25']");

    //    protected static WebElement  departmentTab;
    private static final By bDepartmentTab = By.xpath("//a[contains(text(),'Departments')]");

    //    protected static WebElement  teamTab;
    private static final By bTeamTab = By.xpath("//a[contains(text(),'Teams')]");

    //    protected static WebElement  transportationTab;
    private static final By bTransportationTab = By.xpath("//a[contains(text(),'Transportation Options')]");

    //    protected static WebElement  maxAppointments;
    private static final By bMaxAppointments = By.xpath("//div[@id='admin-departments']//input[@name='maxapps']");

    //    protected static WebElement  tickButtonDept;
    private static final By bTickButtonDept = By.xpath("//button[@class='btn btn-link btn-ext btn-inner btn-inner-green-normal-1 btn-inner-size-40-25 apply']");

    //    protected static WebElement  teamPage;
    private static final By bTeamPage = By.xpath("//section[@id='admin-teams__first-col']");

    //    protected static WebElement  transportationPage;
    private static final By bTransportationPage = By.xpath("//li[contains(@heading,'Rental car') or contains(@heading,'Shuttle') or contains(@heading,'Need loaner') or contains(@heading,'Pick up')]");

    //    protected static List<WebElement>  dayLimit;
    private static final By bDayLimit = By.xpath("//input[@name='quantity']");

    //    protected static WebElement  saveTransportation;
    private static final By bSaveTransportation = By.xpath("//button[@class='btn to-sections__save']");
    private static By spinnerLocator = By.cssSelector(".radial-loader.loader_spiner");
    private static By bWaiter = By.xpath("(//div[@class=@class='ng-binding' and text()='Waiter'])[1]");
    private static By bBacktoDashBoardTab = By.xpath("//*[text()='Back to Dashboard']");
    private static By bMonday1 = By.xpath("//div[contains(@ng-class,'monday')]");
    private static By bMonday2 = By.xpath("//div[contains(@ng-form,'mon')]");
    private static By bMonday = By.xpath("//div[contains(@form,'mon')]");
    private static By bMondayCheckBox1 = By.xpath("//div[contains(@ng-class,'monday')]//label[contains(@class,'checkbox')]");
    private static By bMondayCheckBox = By.xpath("//div[contains(@ng-class,'monday')]/div/label");
    private static By bMondayCheckBox2 = By.xpath("//div[contains(@ng-form,'mon')]/div[@class='checkbox-wrap']/label");
    private static By bMondayTimeBtn1 = By.xpath("//div[contains(@ng-form,'mon')]//div[@class='time']/button");
    private static By bMondayTimeBtn = By.xpath("//div[contains(@ng-class,'monday')]//div[@class='time']/button");
    private static By bMondayOpenTimeInput = By.xpath("//div[contains(@ng-class,'monday')]//div[@time-input='openTime']/div/input");
    private static By bMondayOpenTimeAPM = By.xpath("//div[contains(@ng-class,'monday')]//div[@time-input='openTime']/div/label");
    private static By bMondaycloseTimeInput = By.xpath("//div[contains(@ng-class,'monday')]//div[@time-input='closeTime']/div/input");
    private static By bMondayCloseTimeAPM = By.xpath("//div[contains(@ng-class,'monday')]//div[@time-input='closeTime']/div/label");
    private static By bMondayTimeSaveBtn = By.xpath("//div[contains(@ng-class,'monday')]//div[@class='controls']//button[contains(@ng-click,'saveChanges')]");
    private static By bMondayTimeDiscardBtn = By.xpath("//div[contains(@ng-class,'monday')]//div[@class='controls']//button[contains(@ng-click,'discardChanges')]");
    private static By bTuesday = By.xpath("//div[contains(@ng-class,'tuesday')]");
    private static By bTuesdayCheckBox = By.xpath("//label[contains(@for,'work-time-tue')]");
    private static By bMondayCheckBox3 = By.xpath("//div[@name='forms.frmmonday']");
    private static By bMondayCheckBox4 = By.xpath("(//*[@class='icon icon--btn--check button-icon checkbox__icon'])[3]");    

    private static By bMondayTrans = By.xpath("//div[contains(@name,'mon')]");
    private static By bMondayCheckTrans = By.xpath("//div[contains(@name,'mon')]//label[contains(@class,'checkbox')]");


    //  if it holiday , Attribute classs contains 'holiday'
    private static By bDayStatus(String day) {
        String locator = String.format("//div[contains(@ng-form,'%s')]", day);
        return By.xpath(locator);
    }

    private static By bDayCheckBox(String day) {
        String locator = String.format("//div[contains(@ng-form,'%s')]/div[@class='checkbox-wrap']/label", day);
        return By.xpath(locator);
    }

    private static By bDayTimeBtn(String day) {
        String locator = String.format("//div[contains(@ng-form,'%s')]//div[@class='time']/button", day);
        return By.xpath(locator);
    }

    private static By bDayOpenTimeInput(String day) {
        String locator = String.format("//div[contains(@ng-form,'%s')]//div[@time-input='openTime']/div/input", day);
        return By.xpath(locator);
    }

    private static By bDayOpenTimeAPM(String day) {
        String locator = String.format("//div[contains(@ng-form,'%s')]//div[@time-input='openTime']/div/label", day);
        return By.xpath(locator);
    }

    private static By bDayCloseTimeInput(String day) {
        String locator = String.format("//div[contains(@ng-form,'%s')]//div[@time-input='closeTime']/div/input", day);
        return By.xpath(locator);
    }

    private static By bDayCloseTimeAPM(String day) {
        String locator = String.format("//div[contains(@ng-form,'%s')]//div[@time-input='closeTime']/div/label", day);
        return By.xpath(locator);
    }

    private static By bDayTimeSaveBtn(String day) {
        String locator = String.format("//div[contains(@ng-form,'%s')]//div[@class='controls']//button[contains(@ng-click,'saveChanges')]", day);
        return By.xpath(locator);
    }

    private static By bDayTimeDiscardBtn(String day) {
        String locator = String.format("//div[contains(@ng-form,'%s')]//div[@class='controls']//button[contains(@ng-click,'discardChanges')]", day);
        return By.xpath(locator);
    }


    private static By bDayLimite(String day) {
        String locator = String.format("//div[contains(@ng-form,'%s')]//input[@name='quantity']", day);
        return By.xpath(locator);
    }



    public void setWorkinghours(String day, String holiday, String opentime, String opentimeAPM, String closetime, String closetimeAPM) {
        WebElement eBox = pWait.until(conditionVisible(bDayCheckBox(day)));
        if (holiday.equalsIgnoreCase("yes")) {
            if (!byElementHasClass(bDayStatus(day), "holiday")) {
                eBox.click();
                sleep(1000);
                changes++;
            }
        } else if (holiday.equalsIgnoreCase("no")) {
            if (byElementHasClass(bDayStatus(day), "holiday")) {
                eBox.click();
                sleep(1000);
            }
            setWorkinghour(holiday, opentime, opentimeAPM, closetime, closetimeAPM);
        } else {
            if (!byElementHasClass(bDayStatus(day), "holiday")) {
                setWorkinghour(day,opentime, opentimeAPM, closetime, closetimeAPM);
            }else{
                System.out.println( " the day " + day + " is holiday, skip it on employees setting " );
            }
        }

    }

    public int setWorkinghoursTransport(String day, String holiday, String opentime, String opentimeAPM, String closetime, String closetimeAPM, String daylimit) {
        int limit = 0;
        WebElement eBox = pWait.until(conditionVisible(bDayCheckBox(day)));
        if (holiday.equalsIgnoreCase("yes")) {
            if (!byElementHasClass(bDayStatus(day), "holiday")) {
                eBox.click();
                sleep(1000);
           }
        } else if (holiday.equalsIgnoreCase("no")) {
            if (byElementHasClass(bDayStatus(day), "holiday")) {
                eBox.click();
                sleep(1000);
            }
            limit = setWorkinghourTransport(day, opentime, opentimeAPM, closetime, closetimeAPM, daylimit);
        } else {
            if (!byElementHasClass(bDayStatus(day), "holiday")) {
                limit = setWorkinghourTransport(day, opentime, opentimeAPM, closetime, closetimeAPM, daylimit);
            }else{
                System.out.println( " the day " + day + " is holiday, skip it on trasportation setting " );
            }
        }
        return limit;
    }


    private void setWorkinghour(String day, String opentime, String opentimeAPM, String closetime, String closetimeAPM) {
        WebElement element = driver.findElement(bDayTimeBtn(day));
        if (element.isDisplayed()){
            element.click();
//            System.out.println( " the weekday btn  is 1111111 " + element.toString() );
            sleep(1000);
        }
        if (!opentime.isEmpty()){
            clearAndSend(bDayOpenTimeInput(day),opentime);
        }

        if (opentimeAPM.equalsIgnoreCase("am")){
            element = driver.findElement(bDayOpenTimeAPM(day));
            if (!element.getText().contains("AM")){
                element.click();
            }
        }else if(opentimeAPM.equalsIgnoreCase("pm")){
            element = driver.findElement(bDayOpenTimeAPM(day));
            if (!element.getText().contains("PM")){
                element.click();
            }
        }
        if (!closetime.isEmpty()){
            clearAndSend( bDayCloseTimeInput(day),closetime);
        }
        if (closetimeAPM.equalsIgnoreCase("am")){
            element = driver.findElement(bDayCloseTimeAPM(day));
            if (!element.getText().contains("AM")){
                element.click();
            }
        }else if(closetimeAPM.equalsIgnoreCase("pm")){
            element = driver.findElement(bDayCloseTimeAPM(day));
            if (!element.getText().contains("PM")){
                element.click();
            }
        }

        sleep(500);
        element = driver.findElement(bDayTimeSaveBtn(day));
        if (element.isDisplayed()){
            element.click();
            sleep(500);
            changes++;
        }

    }


    private int setWorkinghourTransport(String day, String opentime, String opentimeAPM, String closetime, String closetimeAPM , String add) {
        WebElement element ;
        if (!opentime.isEmpty()){
            clearAndSend(bDayOpenTimeInput(day),opentime);
        }

        if (opentimeAPM.equalsIgnoreCase("am")){
            element = driver.findElement(bDayOpenTimeAPM(day));
            if (!element.getText().contains("AM")){
                element.click();
            }
        }else if(opentimeAPM.equalsIgnoreCase("pm")){
            element = driver.findElement(bDayOpenTimeAPM(day));
            if (!element.getText().contains("PM")){
                element.click();
            }
        }
        if (!closetime.isEmpty()){
            clearAndSend( bDayCloseTimeInput(day),closetime);
        }
        if (closetimeAPM.equalsIgnoreCase("am")){
            element = driver.findElement(bDayCloseTimeAPM(day));
            if (!element.getText().contains("AM")){
                element.click();
            }
        }else if(closetimeAPM.equalsIgnoreCase("pm")){
            element = driver.findElement(bDayCloseTimeAPM(day));
            if (!element.getText().contains("PM")){
                element.click();
            }
        }

        int  apt1 = getDaylimit(day);
        if (!add.isEmpty()){
            int iadd = 0, apt2 = 0;
            if (add.startsWith("+")){
                iadd = Integer.valueOf(add.substring(1));
                apt2 = apt1 +iadd;
            }else if(add.startsWith("-")){
                iadd = Integer.valueOf(add.substring(1));
                apt2 = apt1 - iadd;
            }else if (add.equalsIgnoreCase("ramdon")){
                iadd = CommonMethods.randomNum(1,19);
                apt2 = iadd;
            }else {
                iadd = Integer.valueOf(add);
                if (iadd == apt1 ){
                    apt2 = iadd + 1;
                }else{
                    apt2 = iadd;
                }
            }
            if (apt2 > 19 || apt2 < 1){
                apt2 = 18;
            }
            if(apt1 == 18){
                apt2 = 15;
            }
            clearAndSend( bDayLimite(day),String.valueOf(apt2));
            apt1 = apt2;
        }
        return apt1;
    }

    public int getDaylimit(String day ) {
        String num = pWait.until(conditionVisible(bDayLimite(day))).getAttribute("value");
        return  Integer.valueOf(num);
    }


    public void clickSaveBtnTrans() {
        sleep(5000);
        WebElement element = driver.findElement(bSaveTransportation);
        if (element.isDisplayed()){
            element.click();            
            sleep(500);
            }
    }


    public String getActions() {
        return driver.findElement(bActionCount).getText();
    }

    public void clickNotification() {
        sleep(3000);
        driver.findElement(bNotificationButton).click();
//        pWait.until(conditionClick(bNotificationButton)).click();
    }

    public boolean verifyNotification(String msg) {
        sleep(500);
        boolean result = true;
        List<WebElement> elements = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bNotificationList));
        for (WebElement element : elements){
            if (!element.getText().contains(msg)){
                result = false;
                break;
            }
        }
        return  result;
    }

    public void clearNotification() {
        pWait.until(conditionVisible(bClearButton)).click();
        changes = 0;
    }

    public void closeNotification() {
        pWait.until(conditionVisible(bCloseButton)).click();
    }

    public void clickTheTabInAdmin(String name) {
        String tab = name.toUpperCase();
        switch (tab) {
            case "EMPLOYEES":
                moveToClick(bDepartmentTab);
                break;
            case "DEPARTMENTS":
                dWait.until(conditionVisible(bDepartmentTab)).click();
                break;
            case "TEAMS":
                dWait.until(conditionVisible(bTeamTab)).click();
                break;
            case "TRANSPORTATION OPTIONS":
                dWait.until(conditionVisible(bTransportationTab)).click();
                break;
            case "BACK TO DASHBOARD":
                dWait.until(conditionVisible(bBacktoDashBoardTab)).click();
                break;                
        }
    }

    public int getMaxAppointments() {
        String num = pWait.until(conditionVisible(bMaxAppointments)).getAttribute("value");
        return  Integer.valueOf(num);
    }

    public int updateMaxAppointments(String add) {
        int iadd = 0, apt2 = 0;
        int apt1 = getMaxAppointments();
        if (add.startsWith("+")){
            iadd = Integer.valueOf(add.substring(1));
            apt2 = apt1 +iadd;
         }else if(add.startsWith("-")){
            iadd = Integer.valueOf(add.substring(1));
            apt2 = apt1 - iadd;
        }else if (add.equalsIgnoreCase("ramdon")){
            iadd = CommonMethods.randomNum(1,19);
            apt2 = iadd;
        }else {
            iadd = Integer.valueOf(add);
            if (iadd == apt1 ){
                apt2 = iadd + 1;
            }else{
                apt2 = iadd;
            }
            apt2 = iadd;
        }

        if (apt2 > 19 || apt2 < 1){
            apt2 = 18;
        }
        if(apt1 == 18){
            apt2 = 15;
        }
        clearAndInput(bMaxAppointments,String.valueOf(apt2));
        return  apt2;
    }


    public  void clickSaveBtnDept() {
        sleep(1000);
        driver.findElement(bTickButtonDept).click();
        pWait.until(SyncPage.condDomReadyState());
        pWait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
        changes++;
    }

    public void clickTheTransportationOption(String option) {
        String selection = option.toUpperCase();
        switch (selection) {
            case "WAITER":
                moveToClick(bWaiter);
                break;
        }
    }    

    public void disableAndSaveCheckBoxForMondayIfAlreadyNotDisabled() {
        dWait.until(conditionVisible(bMondayCheckBox3));   
        String classValue = driver.findElement(bMondayCheckBox3).getAttribute("class").toString();
    	System.out.println("Value of bMondayCheckBox3.getClass().toString() "+ classValue);
    	if (classValue.contains("editing")) {
	        sleep(2000);    		
			System.out.println("Checkbox is enabled, make is disable");
			driver.findElement(bMondayCheckBox4).click();
	        sleep(2000);			
			clickSaveBtnTrans();		
			System.out.println("Checkbox is now disabled");			
		} 
    	if (classValue.contains("holiday")) {
			System.out.println("Checkbox is disabled. No action required");
		}    	
    }       
}


