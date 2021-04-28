package com.autotest.teststeps.appointment_manager;

import com.automation.pages.appointment_manager.AM_AdministratioinPage;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AM_AdministratioinSteps extends BaseTestSteps {

    private static Logger log = Logger.getLogger(AM_AdministratioinSteps.class);

    private static int setMaxAppts;
    private static int sunlimit =0 ,monlimit,tuelimit,wedlimit,thulimit,frilimit,satlimit;

    AM_AdministratioinPage amadPage = new AM_AdministratioinPage();

    @And("^I set working hours data on 'Administration-employees' page$")
    public void iSetWorkingHoursOnAdminEmployee(DataTable dataTable) throws Throwable {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        CommonMethods.sleep(5000);
        for (Map<String, String> map : maps) {
            String day = map.get("Day");
            String holiday = map.get("Holiday");
            String opentime = map.get("Opentime");
            String opentimeAPM = map.get("OpentimeAPM");
            String closetime = map.get("Closetime");
            String closetimeAPM = map.get("ClosetimeAPM");
            if(day.equalsIgnoreCase("sunday")){
                day = "sun";
            }else if (day.equalsIgnoreCase("Monday")){
                day = "mon";
            }else if (day.equalsIgnoreCase("Tuesday")){
                day = "tue";
            }else if (day.equalsIgnoreCase("Wednesday")){
                day = "wed";
            }else if (day.equalsIgnoreCase("Thursday")){
                day = "thu";
            }else if (day.equalsIgnoreCase("Friday")){
                day = "fri";
            }else if (day.equalsIgnoreCase("Saturday")){
                day = "sat";
            }else{
                day ="";
            }
            if (notBlank(day)) {
                amadPage.setWorkinghours(day,holiday,opentime,opentimeAPM,closetime,closetimeAPM);
            }

        }
    }

    @And("^I set working hours data on 'Administration-transportation' page$")
    public void iSetWorkingHoursOnAdminTransportation(DataTable dataTable) throws Throwable {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        CommonMethods.sleep(5000);
        for (Map<String, String> map : maps) {
            String day = map.get("Day");
            String holiday = map.get("Holiday");
            String opentime = map.get("Opentime");
            String opentimeAPM = map.get("OpentimeAPM");
            String closetime = map.get("Closetime");
            String closetimeAPM = map.get("ClosetimeAPM");
            String daylimit = map.get("Day limit");
            if(day.equalsIgnoreCase("sunday")){
                day = "sun";
                sunlimit =  amadPage.setWorkinghoursTransport(day,holiday,opentime,opentimeAPM,closetime,closetimeAPM, daylimit);
            }else if (day.equalsIgnoreCase("Monday")){
                day = "mon";
                monlimit =  amadPage.setWorkinghoursTransport(day,holiday,opentime,opentimeAPM,closetime,closetimeAPM, daylimit);
            }else if (day.equalsIgnoreCase("Tuesday")){
                day = "tue";
                tuelimit =  amadPage.setWorkinghoursTransport(day,holiday,opentime,opentimeAPM,closetime,closetimeAPM, daylimit);
            }else if (day.equalsIgnoreCase("Wednesday")){
                day = "wed";
                wedlimit =  amadPage.setWorkinghoursTransport(day,holiday,opentime,opentimeAPM,closetime,closetimeAPM, daylimit);
            }else if (day.equalsIgnoreCase("Thursday")){
                day = "thu";
                thulimit =  amadPage.setWorkinghoursTransport(day,holiday,opentime,opentimeAPM,closetime,closetimeAPM, daylimit);
            }else if (day.equalsIgnoreCase("Friday")){
                day = "fri";
                frilimit =  amadPage.setWorkinghoursTransport(day,holiday,opentime,opentimeAPM,closetime,closetimeAPM, daylimit);
            }else if (day.equalsIgnoreCase("Saturday")){
                day = "sat";
                satlimit =  amadPage.setWorkinghoursTransport(day,holiday,opentime,opentimeAPM,closetime,closetimeAPM, daylimit);
            }else{
                //
            }
        }
        amadPage.clickSaveBtnTrans();
    }

    @And("^I see 'Day limite' updated on 'Administration-transportation' page$")
    public void iVerifyDaylimiteOnAdminTransportation(DataTable dataTable) throws Throwable {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        boolean result = true;
        for (Map<String, String> map : maps) {
            String day = map.get("Day");
            String daylimit = map.get("Day limit");
            if (daylimit.equalsIgnoreCase("yes")){
                if(day.equalsIgnoreCase("sunday")){
                    day = "sun";
                    if (sunlimit > 0){
                        verifyEquals(" verify the " + day + " 'Day limite' updated on screen ", sunlimit, amadPage.getDaylimit(day) );
                        result = result & (sunlimit == amadPage.getDaylimit(day));
                    }
                }else if (day.equalsIgnoreCase("Monday")){
                    if (monlimit > 0) {
                        day = "mon";
                        verifyEquals(" verify the " + day + " 'Day limite' updated on screen ", monlimit, amadPage.getDaylimit(day));
                        result = result & (monlimit == amadPage.getDaylimit(day));
                    }
                }else if (day.equalsIgnoreCase("Tuesday")){
                    if (tuelimit > 0) {
                        day = "tue";
                        verifyEquals(" verify the " + day + " 'Day limite' updated on screen ", tuelimit, amadPage.getDaylimit(day));
                        result = result & (tuelimit == amadPage.getDaylimit(day));
                    }
                }else if (day.equalsIgnoreCase("Wednesday")){
                    if (wedlimit > 0) {
                        day = "wed";
                        verifyEquals(" verify the " + day + " 'Day limite' updated on screen ", wedlimit, amadPage.getDaylimit(day));
                        result = result & (wedlimit == amadPage.getDaylimit(day));
                    }
                }else if (day.equalsIgnoreCase("Thursday")){
                    if (thulimit > 0) {
                        day = "thu";
                        verifyEquals(" verify the " + day + " 'Day limite' updated on screen ", thulimit, amadPage.getDaylimit(day));
                        result = result & (thulimit == amadPage.getDaylimit(day));
                    }
                }else if (day.equalsIgnoreCase("Friday")){
                    if (frilimit > 0) {
                        day = "fri";
                        verifyEquals(" verify the " + day + " 'Day limite' updated on screen ", frilimit, amadPage.getDaylimit(day));
                        result = result & (frilimit == amadPage.getDaylimit(day));
                    }
                }else if (day.equalsIgnoreCase("Saturday")){
                    if (satlimit > 0) {
                        day = "sat";
                        verifyEquals(" verify the " + day + " 'Day limite' updated on screen ", satlimit, amadPage.getDaylimit(day));
                        result = result & (satlimit == amadPage.getDaylimit(day));
                    }
                }else{
                    //
                }
            }
        }
        Assert.assertTrue(" verify the Day limit result ! ",result);
    }



    @Then("^I verify the 'Update count' on 'Administration-employees' page$")
    public void iVerifyUpdateCountAminPage() {
        int act  = Integer.valueOf(amadPage.getActions());
        verifyEquals(" Verify the Update count on Screen ", act, amadPage.changes );
        Assert.assertEquals(" Verify the Update count on Screen ", act, amadPage.changes );
    }

    @And("^I open the 'Notification Center' on 'Administration' page$")
    public void iOpenNotificationCenter() {
        amadPage.clickNotification();
    }

    @Then("^I see \"(.*)\" notification on 'Administration' page$")
    public void iVerifyUpdateCountAminPage(String notification) {
        verifyTrue(" Verify the notification on Screen ", amadPage.verifyNotification(notification));
        Assert.assertTrue(" Verify the notification on Screen ", amadPage.verifyNotification(notification));
    }

    @And("^I clear and close 'Notification Center' on 'Administration' page$")
    public void iClearCloseNotification() {
        amadPage.clearNotification();
        CommonMethods.sleep(200);
        amadPage.closeNotification();
    }

    @And("^I goto \"([^\"]*)\" tab on 'Administration' page in AM$")
    public void iGotoTabOnAdministration(String name) {
        amadPage.clickTheTabInAdmin(name);
    }

    @And("^I update \"(.*)\" Maximum appointments on 'Administration' page in AM$")
    public void iUpdateAddOnAdministration(String apt) {
        setMaxAppts  = amadPage.updateMaxAppointments(apt);
        amadPage.clickSaveBtnDept();

    }

    @Then("^I see maximum appointments updated on 'Administration' page$")
    public void iSeeMaxApptsUpdatedAdminPage() {
        amadPage.clickTheTabInAdmin("TEAMS");
        amadPage.clickTheTabInAdmin("DEPARTMENTS");
        CommonMethods.sleep(500);
        verifyEquals(" Verify the maximum appointments Update  on Screen ", setMaxAppts, amadPage.getMaxAppointments());
        Assert.assertEquals(" Verify maximum appointments Update on Screen ", setMaxAppts, amadPage.getMaxAppointments());
    }
    
    @And("^I select 'Waiter'$")
    public void iSelectWaiter() {
        CommonMethods.sleep(500);
        amadPage.clickTheTransportationOption("Waiter");        
    }    

    @And("^I disable and save checkbox for Monday if already not disabled$")
    public void iDisableAndSaveCheckBoxForMondayIfAlreadyNotDisabled() {
        CommonMethods.sleep(500);
        amadPage.disableAndSaveCheckBoxForMondayIfAlreadyNotDisabled();        
    }      
}
