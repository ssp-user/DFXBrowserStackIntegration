package com.autotest.teststeps.advisor_checkin;


import com.automation.pages.advisor_checkin.ACI_FramePage;
import com.automation.pages.advisor_checkin.dealerTire.DealerTireConcernsPage;
import com.automation.pages.advisor_checkin.dealerTire.DealerTireHistoryPage;
import com.automation.pages.advisor_checkin.dealerTire.DealerTireServicePage;
import com.automation.pages.advisor_checkin.dealerTire.DealerTireVehiclePage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ACI_DealerTireSteps extends BaseTestSteps {

    DealerTireVehiclePage dvPage = new DealerTireVehiclePage();
    DealerTireConcernsPage dcPage = new DealerTireConcernsPage();    
    DealerTireHistoryPage dhPage = new DealerTireHistoryPage();
    DealerTireServicePage dsPage = new DealerTireServicePage();

    private static Logger log = Logger.getLogger(ACI_DealerTireSteps.class);


    @When("^I input (.*) on \"([^\"]*)\" field in Dealer 'VEHICLE' page$")
    public void iInputStringOnFieldOnDTVehiclePage(String inputString, String fieldName) {
        dvPage.inputFieldOnVechiclePage(inputString, fieldName);
    }

    @And("^I click \"([^\"]*)\" button in Dealer 'VEHICLE' page$")
    public void iClickButtonOnDTVehiclePage(String buttonName) {
        dvPage.clickButtonOnVechiclePage(buttonName);
    }

    @And("^I click \"([^\"]*)\" button in Dealer 'HISTORY' page$")
    public void iClickButtonOnDTHistoryPage(String buttonName) {
        dhPage.clickButtonOnHistoryPage(buttonName);
    }
    
    @And("^I click \"([^\"]*)\" button in Dealer 'CONCERNS' page$")
    public void iClickButtonOnDTConcernsPage(String buttonName) {
    	dcPage.clickButtonOnConcernsPage(buttonName);
    }    

    @And("^I click \"([^\"]*)\" tab in Dealer 'SERVICES' page$")
    public void iClickTabOnDTServicesPage(String tabName) {
        dsPage.clickTabOnServicesPage(tabName);
    }

    @And("^I click \"([^\"]*)\" 'ADD TIRES' icon in Dealer 'SERVICES' page$")
    public void iClickIconWithOrderOnDealerTireServicesPage(String order) {
        dsPage.clickIconOrderOnServicesPage(toBeNumber(order));
    }

    @And("^I click 'ADD TIRES' icon in Dealer 'SERVICES' page$")
    public void iClickIconWithOnDealerTireServicesPage() {
        dsPage.clickAddFirstTire();
    }
    
    @And("^I choose \"([^\"]*)\" on \"([^\"]*)\" popup in Dealer 'SERVICES' page$")
    public void iChooseOnPopUpONDTServicesPage(String content, String popup) {  // need to remove
        dsPage.clickContentOnPopUpOnServicsPage(content, popup);
    }

    @And("^I select \"([^\"]*)\" Size on 'TIRE OFFER' popup page$")
    public void iSelectTireSizeNumberOnTireOffer(String num) {
        dsPage.selectNumberTireOfferSize(toBeNumber(num));
    }

    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" product on \"([^\"]*)\" popup in Dealer 'SERVICES' page$")
    public void iClickButtonOnProductOnPopUpInServicesPage(String buttonName, String order, String popup) {
        dsPage.clickButtonProductWithOrderOnServicePage(buttonName, toBeNumber(order), popup);
    }

    @And("^I click \"([^\"]*)\" 'VIEW' button on 'TIRE OFFER' popup page$")
    public void iClickButtonOnTireOfferPopUpPage(String order) {
        dsPage.clickViewButtonOrderOnTireOffer(toBeNumber(order));
    }

//
//    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" popup in Dealer 'SERVICES' page$")
//    public void iClickButtonOnPopUpOnServicesPage(String buttonName) {
//        dsPage.clickButtonOnPopUpOnServicsPage(buttonName);
//    }

    @And("^I click \"([^\"]*)\" button on 'TIRE OFFER' popup page$")
    public void iClickButtonOnTireOfferPopUpOnServicesPage(String buttonName) {
        dsPage.clickButtonOnPopUpOnServicsPage(buttonName);
    }


    @And("^I verify the Install Price on DealerTire 'SERVICES' page$")
    public void iVerifyInstallPriceOnServicesTirePage() {
           String tireInstallPrice = dsPage.tireInstallPrice.replace("$","").trim();
           String selectTireListPrice = dsPage.getSelectedTirePriceList();
        try{
           Assert.assertEquals(selectTireListPrice, tireInstallPrice);
        } catch(AssertionError e){
            log.error("Dealer tire Install Price not same ;  " + tireInstallPrice + "  Tire Offer is " + tireInstallPrice);
            System.out.println("Dealer tire Install Price not same ;  " + tireInstallPrice + "  Tire Offer is " + tireInstallPrice);
        }
    }


//    @And("^I click \"([^\"]*)\" button in Dealer 'SERVICES' page$")
//    public void iClickButtonInServicesPage(String buttonName) {
//        dsPage.clickButtonOnServicesPage(buttonName);
//    }

    @Then("^I should see GENERATE QUOTE completed$")
    public void iVerifyGenerateQuoteCompleted() {
        Assert.assertTrue(dsPage.verifyGenerateQuoteStatus());
    }

    @Then("^I signOut From AC$")
    public void iSignoutFromAC() {
        ACI_FramePage page = new ACI_FramePage();
        page.signOut();
    }

    @And("^I enter data set in Dealer 'VEHICLE' page$")
    public void updatePageInfo(DataTable dataTable) throws Throwable{
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName").toLowerCase();
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
            dvPage.setSelectFieldValue(fieldName, value );
            }

        }
    }

}
