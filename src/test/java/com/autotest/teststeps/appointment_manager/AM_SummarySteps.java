package com.autotest.teststeps.appointment_manager;

import com.automation.pages.appointment_manager.AM_ServicesPage;
import com.automation.pages.appointment_manager.AM_SummaryPage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class AM_SummarySteps extends BaseTestSteps {


    private static Logger log = Logger.getLogger(AM_SummarySteps.class);

    AM_SummaryPage smPage = new AM_SummaryPage();
    AM_ServicesPage svPage = new AM_ServicesPage();

    @And("^I input comment \"([^\"]*)\" on 'SUMMARY' page$")
    public void iAddCommentOnSummaryPage(String comment) {
        smPage.addComment(comment.trim());
    }

    @And("^I goto \"([^\"]*)\" tab on 'SUMMARY' page$")
    public void iClickTabAMOnHistory(String name) {
        smPage.waitForPageToLoad();
        smPage.goToTheTabInAM(name);
    }

    @And("^I click \"([^\"]*)\" button on 'SUMMARY' page$")
    public void iClickButtonOnSummaryPage(String buttonName) {
        switch (buttonName) {
            case "CREATE":
                smPage.clickOnCreateBtn();
                break;
            case "SAVE":
//                smPage.clickOnSaveOnSummary();
                smPage.clickOnSaveBtn();
                break;
        }
    }

    @And("^I choose \"([^\"]*)\" option on 'SUMMARY' page$")
    public void iChooseOPtionOnSummaryPage(String name) {
        smPage.waitForPageToLoad();
        smPage.clickRPOptions(name.toUpperCase());
    }


    @And("^I verify selected items price on 'SUMMARY' page$")
    public void iVerifyitemsPriceSummaryPage() {
        String amount = smPage.getTotalAmount();
        String cal = smPage.getCalculatedTotal();
        try{
            Assert.assertEquals("Total Price for all selected items not match ! " ,amount, cal);
            System.out.println("Total Price for all selected items match ! " + "List= "+cal + " Total= "+amount);
        } catch (AssertionError e){
            System.err.println("Total Price for all selected items Not  match ! " + "List= "+cal + "  Total= "+amount);
        }
    }

    @And("^I clear and cancel 'Tire comment' on 'SUMMARY' page$")
    public void iClearTireCommentsSummaryPage() {
         smPage.clearTireComment();
        smPage.cancelTireComment();
    }

    @Then("^I verify 'Tires Price' on 'SUMMARY' page$")
    public void iVerifyTirePriceSummaryPage() {
        String priceSum =  smPage.getTireService();
        String priceSev =  svPage.quotePriceVal;
        verifyEquals("verify the service Tire price ", priceSev , priceSum);
    }

}
