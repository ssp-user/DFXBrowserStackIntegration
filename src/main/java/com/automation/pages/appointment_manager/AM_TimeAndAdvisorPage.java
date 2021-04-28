package com.automation.pages.appointment_manager;

import com.automation.pages.common.WebPage;
import com.automation.utils.elementUI.CSSUtils;
import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.LocalDate;
import java.util.List;

public class AM_TimeAndAdvisorPage extends AM_FramePage {


    private static Logger log = Logger.getLogger(AM_TimeAndAdvisorPage.class);

//    public static String selectedMonth;
    public static String selectedTime = null;
    public static String selectedDate = null;
    public static String selectedMonth = null;




    //  ****** import element from test NG ****

//    @FindBy(css="div.c-advisors__name")   // update 2018-03-08
//    private static List<WebElement> advisorList;
//
//    @FindBy(xpath="//div[@class='c-date-stats__calendar']")																						//Calendar button button-2.20 change
//    private static WebElement calendar;
//
//    @FindBy(css=".c-date-stats__loader")																						//Calendar button button-2.20 change
//    private static WebElement timeLoader;
//    // Next Month Button
//
//    @FindBy(css=".datepicker__month__btn svg.icon--angle-right")
//    private static WebElement nextMonthBtn;
//    // Current Selected Month Text
//
//    // Appointment Date List
//
//    @FindBy(css=".c-date-stats__nav__inner .c-date-stats__nav__item__day")
//    private static List<WebElement> dateListN;
//
//
//    // Tuesday on fourth week
//
//    @FindBy(css=".datepicker__days-container .datepicker__day:nth-child(27)")
//    private static WebElement fridayOnFourthWeek;
//
//    // Time Slot Spinner Loader
//
//    @FindBy(css=".c-date-stats__scrollable-space .loader_spiner")
//    private static WebElement timeSlotLoader;
//
//
//    @FindBy(css=".half-day-header div.table-col:nth-child(1)")
//    private static WebElement am;																											// AM
//
//
//    @FindBy(css="div.time-section .am div.tb-group:nth-child(3)>div:nth-child(1)")
//    private static WebElement nineOclock;																									// 9:00
//
//
//    @FindBy(css="div.time-section div.tb-group>div")

//
//    //	@FindBy(xpath="//div[@ng-reflect-klass='c-date-stats__time']")
//    @FindBy(css="div.c-date-stats__time")   // update 2018-03-08
//    private static List<WebElement> timeList;
//
//
//    @FindBy(css="div.ng-invalid-required>div:nth-child(4) svg")
//    private static WebElement noTimeAvailable;
//

    //calendar icon
//    private static By bCalendarIcon = By.xpath("//*[@class='icon icon--calendar-caret-down']");
    private static By bCalendarIcon = By.cssSelector(".c-date-stats__nav__calendar__btn");

    //calendar right arrow
//    private static By bCalendarArrowRightIcon = By.xpath("//*[@class='icon icon--angle-right']");
    private static By bCalendarArrowRightIcon = By.cssSelector("svg.icon--angle-right");


//        @FindBy(css=".c-popup span.datepicker__month__m")
    private static WebElement monthText;
    private static By bMonthText = By.cssSelector(".c-popup span.datepicker__month__m");

//    @FindBy(css=".datepicker__days-container .datepicker__day:nth-child(17)")
    private static WebElement tuesdayOnThirdWeek;
    private static By bTuesdayOnThirdWeek = By.cssSelector(".datepicker__days-container .datepicker__day:nth-child(17)");

    //set button on the calendar
//    private static By bSetCalendarBtn = By.xpath("//div[@class='c-button__inner']/span[text()='SET']");
    private static By bSetCalendarBtn = By.cssSelector(".set-btn");

//    private static List<WebElement> timeList;
    private static By bTimeList = By.cssSelector("div.c-date-stats__time");

//    private static List<WebElement> availableTimeList;
    private static By bAvailableTimeList = By.xpath("//div[(@class='c-date-stats__time') and not(c-date-stats__time--disabled)]");


//    private static List<WebElement> dateList;
    private static By bDateList = By.xpath("//div[@class='c-date-stats__nav__item__day']");

//    private static WebElement calendarBtn;
    private static By bCalendarBtn = By.cssSelector(".c-date-stats__nav__calendar__btn");

    //    @FindBy(css="#to-tool-btn-icon--waiter")
//    private static WebElement waiter;
    private static By bEnabledWaiter = By.xpath("//div[(@id='to-tool-btn-icon--waiter') and not(contains(@class,'btn--is-disabled'))]");
    //
//    @FindBy(xpath="//div[@id='to-tool-btn-icon--own-ride']")
//    private static WebElement ownRide;
    private static By bEnabledOwnRide = By.xpath("//div[(@id='to-tool-btn-icon--own-ride') and not(contains(@class,'c-to-tool__cell__btn--is-disabled'))]");
    //    /*@FindBy(xpath="//div[(@id='to-tool-btn-icon--need-loaner-1') and not(contains(@class,'c-to-tool__cell__btn--is-disabled'))]")
//    private static WebElement enabledLoaner;*/
    private static By bEnabledLoaner = By.xpath("//div[(@id='to-tool-btn-icon--need-loaner') and not(contains(@class,'btn--is-disabled'))]");
    //
//    @FindBy(css="#to-tool-btn-icon--need-rental")
//    private static WebElement rental;
    private static By bEnabledRental =  By.xpath("//div[(@id='to-tool-btn-icon--rental') and not(contains(@class,'btn--is-disabled'))]");
    //
    private static By bEenabledShuttle =  By.xpath("//div[(@id='to-tool-btn-icon--shuttle') and not(contains(@class,'btn--is-disabled'))]");
    //    @FindBy(css="#to-tool-btn-icon--need-pickup-1")
//    private static WebElement pickup;
    private static By bEenabledPickup =  By.xpath("//div[(@id='to-tool-btn-icon--pickup-and-delivery') and not(contains(@class,'btn--is-disabled'))]");
//    @FindBy(xpath="//div[@class='c-date-stats__date']//div[@class='c-date-stats__date__status']")		//updated:2.20
//    private static WebElement currentSelectedOption;
    private static By bCurrentSelectedOption = By.xpath("//div[@class='c-date-stats__date']//div[@class='c-date-stats__date__status']");
    private static By btimeSlot = By.xpath("(//div[@class='c-date-stats__time-row'])[7]/div[1]");    


    // ****** end import element



    //calendar first available date
    private static By bFirstAvailableDayOnCalendar = By.xpath("//a[@class='datepicker__day']");


    // available advisors
    private static By bAvailableAdvisors = By.xpath("//div[@class='c-advisors__department__body']/div[not(contains(@class,'disabled'))]");


    //first available time
    private static By bFirstAvailableTime = By.xpath("//div[@class='c-date-stats__time']");

    //next available time
    private static By bNextAvailableTime = By.xpath("//div[@class='c-date-stats__time c-date-stats__time--selected']/following-sibling::div[not(contains(@class,'disabled'))]");

    //next available date (tomorrow first available time)
    private static By bNextAvailableDay = By.xpath("//div[@class='c-date-stats__time c-date-stats__time--selected']/../following-sibling::div/div[@class='c-date-stats__time']");

    //selected year
    private static By bSelectedYear = By.xpath("//div[@class='c-date-stats__date__day']");

    //selected date
    private static By bSeletedDate = By.xpath("//div[@class='c-date-stats__nav__item c-date-stats__nav__item--is-selected']/div[@class='c-date-stats__nav__item__day']");

    //selected time
    private static By bSelectedTime = By.xpath("//div[contains(@class,'c-date-stats__time c-date-stats__time--selected')]");

    private static By bNextButton = By.xpath("//button[@id='next-1500972347']");
    private static By bNavigateNext = By.xpath("//div[@class='c-date-stats__nav__next']");
    private static By bWaiter = By.xpath("//div[@id='to-tool-btn-icon--waiter']");
    private static By bWaiterBackGround = By.cssSelector(".c-to-tool__cell__btn--is-selected");    
    private static By bAdvisorName(String name){
        String locator = String.format("//div[contains(text(),'%s')]",name);
        return By.xpath(locator);
    }

    public void clickOnMonthCalendar(String month){
        clickElementWithException(bCalendarIcon);
        int no = Integer.parseInt(month);
        for(int i=1;i<no+1;i++){
            clickElementWithException(bCalendarArrowRightIcon);
            System.out.println("<====== click Month right arrow '"+i+"' times");
        }
    }

    public void clickOnFirstAvailableDayOnCalendar(){
        clickElementWithException(bFirstAvailableDayOnCalendar);
        clickElementWithException(bSetCalendarBtn);
    }

//    public void clickOnFirstAvailableAdvisor(){
//        clickElementWithException(bAvailableAdvisors);
//    }
//
//    public void selectAdvisorName(String name){
//        jsClick(bAdvisorName(name));
//        sleep(500);
//    }
//
//    public void selectAdvisorOrder(int num){
//        if (num > 0){
//            num = num-1;
//            WebElement elm = driver.findElements(bAvailableAdvisors).get(num);
//            jsClick(elm);
//            sleep(500);
//        }
//
//    }

    public void selectAdvisor(String name){
        if (name.toLowerCase().equals(name.toUpperCase())){
            int num = Integer.valueOf(name);
            if (num > 0){
                num = num-1;
                WebElement elm = driver.findElements(bAvailableAdvisors).get(num);
                jsClick(elm);
                sleep(500);
            }
        }else{
            jsClick(bAdvisorName(name));
            sleep(500);
        }

    }

    public void clickOnFirstAvailableTimeOnToday(){
        clickElementWithException(bFirstAvailableTime);
    }

    public void clickOnNextAvailableTimeOnToday(){
        clickElementWithException(bNextAvailableTime);
    }

    public void clickOnNextAvailableDay(){
        clickElementWithException(bNextAvailableDay);
    }

    public String getSelectedAppointmentDay(){
        String dateOnHeader = getElementTextWithException(bSelectedYear);
        tp.appointmentDay = getElementTextWithException(bSeletedDate)+dateOnHeader.substring(dateOnHeader.lastIndexOf(' '));
        System.out.println("<====== The appointment day selected is "+tp.appointmentDay+" ======>");
        return tp.appointmentDay;
    }

    public String getSelectedAppointmentTime(){
        tp.appointmentTime = getElementTextWithException(bSelectedTime);
        System.out.println("<====== The appointment time selected is "+tp.appointmentTime+" ======>");
        return tp.appointmentTime;
    }

    public void clickNextBtn(){
        clickElementWithSeconds(bNextButton,2000);
    }


    public void setPromiseDate(int nextMonths, String day) {
        sleep(5000);
        sWait.until(SyncPage.condDomReadyState());
        jsClick(bCalendarIcon);

        // select nextmonths third week Wednesday
        for (int i = 0; i < nextMonths; i++) {
            selectedMonth = pWait.until(conditionVisible(bMonthText)).getText().trim();
            while(pWait.until(conditionVisible(bMonthText)).getText().trim().equals(selectedMonth)){
                pWait.until(conditionClick(bCalendarArrowRightIcon)).click();
            };
            log.info("clicked " + i + " next month on Calendar");
        }

        if (day.equalsIgnoreCase("The 3th Tuseday")) {
            jsClick(bTuesdayOnThirdWeek);
        }else{
            jsClick(bTuesdayOnThirdWeek);
        }

        sleep(1000);
        String dRange = driver.findElement(bTuesdayOnThirdWeek).getText();
        System.out.println("Clicked week range :  "+dRange);
        jsClick(bSetCalendarBtn);
        lWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bTimeList));

    }


    public void setPromiseTime(int seq) {
        sleep(10000);
        sWait.until(SyncPage.condDomReadyState());
        List<WebElement> timeSlots = driver.findElements(bAvailableTimeList);
        WebElement element = pWait.until(condTimeReadyToSelect(seq,timeSlots));
//        clickElementWithException(element);
        selectedTime = element.getText().trim();
        if (selectedTime.startsWith("0")){
            selectedTime = selectedTime.substring(1);
        }
        selectedTime = selectedTime.substring(0,selectedTime.length()-2).trim();
        System.out.println(" selected Time slot is  :  "+ selectedTime);
        element.click();
        sleep(2000);
        selectedDate = getSelectedDate();
    }

    private String getSelectedDate(){																									// Get Selected Date
        sleep(2000);
        List<WebElement> dateList = driver.findElements(bDateList);
        WebElement date = null;
        String color = "",day = "";
        for(int i = 0; i < dateList.size(); i++){
            date = dateList.get(i);
            color = CSSUtils.getCssBackgroundColorValue(date);
            //if(color.contains("77, 77, 77")){
            if(color.contains("105, 110, 123"))  														//update- 2.20
            {
                day = date.getText().substring(0, 2);
                break;
            }
        }
        return day;
    }

    public void waitForTimePageToLoad(){																								// Wait for Page to load
        lWait.until(SyncPage.condDomReadyState());
        lWait.until(conditionVisible(bCalendarBtn));
        lWait.until(ExpectedConditions.elementToBeClickable(bTimeList));
    }

    /***
     * Wait Condition for Dropdown List Update
     * @param  // element
     * @return
     */

    private  ExpectedCondition<WebElement> condTimeReadyToSelect(int seq, final List<WebElement> slots){
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver){
//                List <WebElement> slots  = driver.findElements(timeLocator);
                WebElement element = null;
                try{
                    for(int j = seq; j < slots.size(); j++){
                        if(!CSSUtils.getCssColorValue(slots.get(j)).equals(CSSUtils.getTimeUnavailableColor())){
                            element = slots.get(j);
                            break;
                        }
                    }
                } catch (StaleElementReferenceException e){
                    log.error("Stale Element Reference Exception Occurred on time slot .. "  + e.getMessage());
                }
                return element;
            }
            @Override
            public String toString(){
                return "condTimeReadyToSelect";
            }
        };
    }

        private boolean  clickOPtion(By option ){
        if (SyncPage.isElementPresentAndVisible(option)){
            clickElementWithSeconds(option,2000);
            return true;
        }else{
            return false;
        }
    }

    public void clickOptions(String name){
         String cOption = driver.findElement(bCurrentSelectedOption).getText().trim().toUpperCase();
         boolean select = false;
        if (name.contains("WAITER")){
            if (clickOPtion(bEnabledWaiter)){
                waiter ++;
                adjustCurrentOption(cOption);
                select = true;
            }
        }else if (name.contains("OWN")){
            if (clickOPtion(bEnabledOwnRide)){
                ownRide ++;
                adjustCurrentOption(cOption);
                select = true;
            }
        }else if (name.contains("SHUTTLE")){
            if (clickOPtion(bEenabledShuttle)){
                shuttle ++;
                adjustCurrentOption(cOption);
                select = true;
            }
        }else if (name.contains("LOANER")){
            if (clickOPtion(bEnabledLoaner)){
                loaner ++;
                adjustCurrentOption(cOption);
                select = true;
            }
        }else if (name.contains("RENTAL")){
            if (clickOPtion(bEnabledRental)){
                rental ++;
                adjustCurrentOption(cOption);
                select = true;
            }
        }else if (name.contains("PICK")){
            if (clickOPtion(bEenabledPickup)){
                pickup ++;
                adjustCurrentOption(cOption);
                select = true;
            }
        }else{
            //
        }
        System.out.println(" ");
        System.out.println(" current Option selected : "+ cOption + " selected option " + name + " is " + select +"\n");
    }

    private void adjustCurrentOption(String option){																							// Adjust Count for current select option
        if(option.contains("WAITER")){
            waiter--;
        }else if(option.contains("OWN")){
            ownRide--;
        }else if(option.contains("LOANER")){
            loaner--;
        }else if(option.contains("RENTAL")){
            rental--;
        }else if(option.contains("SHIUTTLE")){
            shuttle--;
        }else if(option.contains("PICK")){
            pickup--;
        }
       sleep(4000);
    }

    public void ClickWaiter(){
    	sleep(5000);
    	actionClick(bWaiter);
    	sleep(5000);    	
    	String precolor = CSSUtils.getCssBackgroundColorValue(driver.findElement(bWaiterBackGround));
    	System.out.println("Default Colour of TimeSlot is "+ precolor);   
    	Assert.assertTrue(precolor.equals("rgba(105, 110, 123, 1)"), "Validate default colour of Waiter");    	
    }   

    public void CheckTimeSlot(){
        sleep(2000);    	
    	actionClick(btimeSlot);
        sleep(5000);    	
    	String postcolor = CSSUtils.getCssBackgroundColorValue(driver.findElement(bWaiterBackGround));
    	System.out.println("Colour of blocked Waiter timeSlot is "+ postcolor);    	
    	Assert.assertTrue(postcolor.equals("rgba(193, 39, 45, 1)"), "Validate colour of Waiter icon when it is unavailable");   	
    }   
    
    public void setPromiseDateAsCurrentDate() {
        sleep(5000);
        LocalDate currentdate = LocalDate.now();        
        selectedMonth = currentdate.getMonth().toString();
        selectedDate = Integer.toString(currentdate.getDayOfMonth());        
    }
    
    public void validateTransportationOption(String transportOption){
        CommonMethods.verifyElementExists(driver.findElement(By.xpath("//*[contains(text(),'"+transportOption+"')]")));   
    }       
    
}
