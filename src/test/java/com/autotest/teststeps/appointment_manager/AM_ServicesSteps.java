package com.autotest.teststeps.appointment_manager;

import com.automation.pages.appointment_manager.AM_ClientAndVehiclePage;
import com.automation.pages.appointment_manager.AM_ServicePageHelpPDF;
import com.automation.pages.appointment_manager.AM_ServicesPage;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AM_ServicesSteps extends BaseTestSteps {


    private static Logger log = Logger.getLogger(AM_ServicesSteps.class);
    private int recalltab;

    AM_ServicesPage svPage = new AM_ServicesPage();
    AM_ServicePageHelpPDF amsPDFPage = new AM_ServicePageHelpPDF();

    @And("^I add following concerns on 'SERVICE' page$")
    public void addConcernsService(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String concernName = map.get("Concern name");
                    svPage.addConcernItem(concernName);
        }
    }

    @And("^I add \"([^\"]*)\" concern with \"([^\"]*)\" price on 'SERVICES' page in AM$")
    public void iAddConcernWithPirceOnAMServicePage(String concernOrder, String price) {
           svPage.addConcernItemWithPrice(concernOrder, price);
    }

//    @And("^I click \"([^\"]*)\" button on 'SERVICES' page in AM$")
//    public void iClickButtonServicepage(String buttonName) {
//        switch (buttonName) {
//            case "NEXT":
//                svPage.clickNextBtn();
//                break;
//            case "GENERATE MENU":
//                svPage.clickNextBtn();
//                break;
//        }
//    }

    @And("^I click \"([^\"]*)\" button on 'SERVICES' page in AM$")
    public void iClickButtonOnServicepage(String buttonName) {
        buttonName = buttonName.toUpperCase();
         svPage.clickButtonOnService(buttonName);
    }


    @And("^I click \"([^\"]*)\" button on 'SERVICES-TIRES' page in AM$")
    public void iClickBtnOnServiceTirePage(String buttonName) {
        buttonName = buttonName.toUpperCase();
        svPage.clickBtnOnServiceTire(buttonName);
    }


    @And("^I goto \"([^\"]*)\" tab on 'SERVICES' page in AM$")
    public void iGotoTabServicepage(String tab) {
        svPage.gotoServiceTab(tab.toUpperCase());
    }

    @And("^I keep Pre-Selected or select \"([^\"]*)\" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected$")
    public void ikeepPreSelectedOrCheckOnUNSCHEDULEDPage(String checkItem) {
        CommonMethods.sleep(1000);
        if (svPage.isAnyPreselectedItems("Unschedule")){
            System.out.println( checkItem + "  Pre-Selected Item exist  ");
        } else {
            System.out.println( checkItem + " Pre-Selected Item not exist ");
            svPage.chooseItemUnScheduleService(checkItem);
        }
        CommonMethods.sleep(5000);
    }


    @And("^I keep Pre-Selected or choose random less \"(\\d+)\" services in 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected$")
    public void ikeepPreSelectedOrCheckRandomOnUNSCHEDULEDPage(int items) {
        if (svPage.isAnyPreselectedItems("Unschedule")){
            System.out.println(" Pre-Selected Factory Item exist  ");
        } else {
            System.out.println(" Factory Required Items  exist  ");
            svPage.chooseRandomItemsUnSchedule(items);
        }
    }

    @And("^I click \"([^\"]*)\" 'Tire Size' on 'SERVICES-TIRES' page in AM$")
    public void iClickTireSizeOnServiceTirePage(String order) {
        int num = toBeNumber(order);
        svPage.clickTireSizeOnServiceTire(num);
    }

    @And("^I click \"([^\"]*)\" catalog 'VIEW' button on 'SERVICES-TIRES' page in AM$")
    public void iClickTireVIEWBtnOnServiceTire(String order) {
        int num = toBeNumber(order);
        svPage.clickTireOptionOnServiceTire(num);
    }

    @And("^I choose \"([^\"]*)\" product catalog on 'SERVICES-TIRES' page in AM$")
    public void iChooseTireCatalogOnServiceTire(String order) {
        int num = toBeNumber(order);
        svPage.clickTireOptionOnServiceTire(num);
    }

    @Then("^I verify the TIRES quote on 'SERVICES-TIRES' page in AM$")
    public void iVerifyTheTireQuote() {
        String quote = svPage.getTireQuotePrice();
        verifyTrue("verify tire quote " , (quote !="") );
    }

    @And("^I click \"([^\"]*)\" to verify \"([^\"]*)\" in PDF generated on 'SERVICE' page in AM$")
    public void iVerifyopenMenuPDFOnAuthorizationPage(String button, String verifyField) {
        svPage.clickButtonOnService(button.toUpperCase());
        String pdfContent = amsPDFPage.getOPENMenuPDF();
        amsPDFPage.removePDF();
        verifyTrue(" verify SAVED INTERNAL SERVICE QUOTE PDF ", pdfContent.contains(verifyField) );
    }

    @Then("^I verify the 'Recall count' is same on 'SERVICES' page in AM$")
    public void iVerifyRecallCountTab() {
        CommonMethods.sleep(5000);
        int recalls = AM_ClientAndVehicleSteps.recalls;
        recalltab = Integer.valueOf(svPage.getRecallTabCount());
        verifyEquals(" verify the Recalls tab on the service page " ,recalls, recalltab );
        Assert.assertEquals(" verify the Recalls tab on the service page "  ,recalls, recalltab);

    }

    @Then("^I verify the 'Recall count' and 'Recall list' on 'SERVICES' page in AM$")
    public void iVerifyRecallList() {
        if (AM_ClientAndVehicleSteps.recalls > 0 ){
            CommonMethods.sleep(15000);
        }else{
            CommonMethods.sleep(5000);
        }
        int recalls = svPage.getRecallListSize();
        recalltab = Integer.valueOf(svPage.getRecallTabCount());
//        System.out.println("<== recalltab --- " + recalltab  + "  ,recalls  ===>" + recalls );
        verifyEquals(" verify the Recalls List on the service page " , recalltab ,recalls);
        Assert.assertEquals(" verify the Recalls List on the service page " ,recalltab ,recalls);
    }


    @Then("^I verify the 'Recall count' status \"([^\"]*)\" on 'SERVICES' page in AM$")
    public void iVerifyRecallCountTabServicePage(String status) {
        CommonMethods.sleep(1000);
        int recalls = AM_ClientAndVehicleSteps.recalls;
        verifyEquals(" verify the Recalls tab on the service page " ,recalls, recalltab );
        Assert.assertEquals(" verify the Recalls tab on the service page "  ,recalls, recalltab);
        if (status.equalsIgnoreCase("yes")){
//            verifyTrue(" verify the Recalls status on the screen  " ,(recalls > 0));
            Assert.assertTrue(" verify the Recalls status on the screen  " ,(recalls > 0));
        }else if (status.equalsIgnoreCase("no")){
//            verifyTrue(" verify the Recalls status on the screen  " ,(recalls == 0));
            Assert.assertTrue(" verify the Recalls status on the screen  " ,(recalls == 0));
        }

    }

    @And("I capture selected service on 'SERVICES' page in AM$")
    public void iCaptureServiceInAMServicepage() {
    	svPage.captureUnScheduledService();
    }    

}
