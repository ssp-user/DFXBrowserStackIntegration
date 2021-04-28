package com.autotest.teststeps.appointment_manager;

import com.automation.pages.appointment_manager.AM_TimeAndAdvisorPage;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;

public class AM_TimeAndAdvisorSteps extends BaseTestSteps {

    private static Logger log = Logger.getLogger(AM_TimeAndAdvisorSteps.class);

    AM_TimeAndAdvisorPage tnaPage = new AM_TimeAndAdvisorPage();

    @And("^I click \"([^\"]*)\" available time on \"([^\"]*)\" on 'TIME & ADVISOR' page$")
    public void iClickNextDateOnTimeAdviPage(String time, String day) {
        switch (day) {
            case "today":
                switch (time) {
                    case "first":
                        tnaPage.clickOnFirstAvailableTimeOnToday();
                        break;
                    case "next":
                        tnaPage.clickOnNextAvailableTimeOnToday();
                        break;
                }
                break;
            case "tomorrow":
                tnaPage.clickOnNextAvailableDay();
                break;
        }

    }

    @And("^I click \"([^\"]*)\" available time on \"([^\"]*)\" business day after \"([^\"]*)\" months on 'TIME & ADVISOR' page$")
    public void iClickNextAvailableTimeOnDateOnTimeAdviPage(String timeOrder, String dayOrder, String MonthOrder) {
        tnaPage.clickOnMonthCalendar(MonthOrder);
        switch (dayOrder) {
            case "first":
                tnaPage.clickOnFirstAvailableDayOnCalendar();
                switch (timeOrder) {
                    case "first":
                        tnaPage.clickOnFirstAvailableTimeOnToday();
                        break;
                }
                break;
        }
    }

//
//    @And("^I select \"([^\"]*)\" available advisor on 'TIME & ADVISOR' page$")
//    public void iSelectAdvisorOnTimePage(String advisorOrder) {
//        switch (advisorOrder) {
//            case "first":
//                tnaPage.clickOnFirstAvailableAdvisor();
//                break;
//        }
//    }

//    @And("^Iabc select \"([^\"]*)\" in 'Advisors' section on 'TIME & ADVISOR' page$")
//    public void abiSelectAdvisorOnTimeAdvisorPage(String advisor) {
//        CommonMethods.sleep(1500);
//        tnaPage.waitForTimePageToLoad();
//        if (advisor.toLowerCase().equals(advisor.toUpperCase())){
//            tnaPage.selectAdvisorOrder(toBeNumber(advisor));
//        }else{
//            tnaPage.selectAdvisorName(advisor);
//        }
//    }

    @And("^I select \"([^\"]*)\" in 'Advisors' section on 'TIME & ADVISOR' page$")
    public void iSelectAdvisorOnTimeAdvisorPage(String advisor) {
        tnaPage.waitForTimePageToLoad();        
        tnaPage.selectAdvisor(toBeAlpha(advisor));

    }

    @And("^I get \"([^\"]*)\" and \"([^\"]*)\" for the appointment on 'TIME & ADVISOR' page$")
    public void iGetTimeForAppointmentOnTimeAdviPage(String day, String time) {
        tnaPage.getSelectedAppointmentDay();
        tnaPage.getSelectedAppointmentTime();
    }

    @And("^I click \"([^\"]*)\" button on 'TIME & ADVISOR' page$")
    public void iClickButtonOnTimeAdvisorpage(String buttonName) {
        switch (buttonName) {
            case "NEXT":
                tnaPage.clickNextBtn();
                break;
        }
    }

    @And("^I choose \"([^\"]*)\" option if available on 'TIME & ADVISOR' page$")
    public void iClickOPtionOnSummaryPage(String name) {
        CommonMethods.sleep(4000);
        tnaPage.clickOptions(name.toUpperCase());
    }

    @And("^I set promise date next \"([^\"]*)\" month \"(.*)\" on 'TIME & ADVISOR' page$")
    public void iSetNextMonthPromiseDateTimeAdvisorPage(int nexts,String day) {
            tnaPage.setPromiseDate(nexts,day);
    }

    @And("^I set promise time \"([^\"]*)\" available timeslot on 'TIME & ADVISOR' page$")
    public void iSelectTimeSlotTimeAdvisorPage(String next) {
        tnaPage.setPromiseTime(toBeNumber(next));
        String appointmentTime = tnaPage.selectedMonth + " - " + tnaPage.selectedDate + " - " + tnaPage.selectedTime;
        System.out.println("Appointment Scheduled Time is : " + appointmentTime);
    }


    @And("^I select 'Waiter' in 'Transportation Options' section on 'TIME & ADVISOR' page$")
    public void iSelectWaiterinTransportationSection() {
    	tnaPage.ClickWaiter();
    }       

    @Then("^I see no available timeslot for Monday on 'TIME & ADVISOR' page$")
    public void iSeeNoAvailableTimeSlot() {
    	tnaPage.CheckTimeSlot();
    }   

    @And("^I set promise date as current date on 'TIME & ADVISOR' page$")
    public void iSetPromiseDateAsCurrentDate() {
            tnaPage.setPromiseDateAsCurrentDate();
    }    
    
    @And("^I validate Transportation option \"([^\"]*)\" exists on 'TIME & ADVISOR' page$")
    public void iValidateTransportOptionOnTimeAndAdvisorPage(String transportOption) {
    	tnaPage.validateTransportationOption(transportOption);
    }    
}
