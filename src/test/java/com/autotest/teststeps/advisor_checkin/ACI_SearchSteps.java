package com.autotest.teststeps.advisor_checkin;

import com.autotest.teststeps.BaseTestSteps;
import com.automation.pages.DriverMaker;
import com.automation.pages.advisor_checkin.*;
import com.automation.pages.third_party.VINGenerator;
import com.automation.utils.otherUtils.PDFUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;

public class ACI_SearchSteps extends BaseTestSteps {

    ACI_SearchPage apage = new ACI_SearchPage();
    ACI_CustomerPage acPage = new ACI_CustomerPage();
    PDFUtils pdf = new PDFUtils();

    private static Logger log = Logger.getLogger(ACI_SearchSteps.class);

    @Then("^I verify Page Info \"([^\"]*)\" and Browser log in 'Advisor Check-In' page$")
    public void iVerifyPageBrowserLogOnAC(String info) {
        if (apage.isBodyPageContains(info)) {
            System.out.println(" Verify : " + "Page Body Class found ---> " + info);
            log.info(" Verify : " + "Page Body Class found ---> " + info);
        } else {
            System.out.println(" Verify : " + "Page Body Class not found ---> " + info);
            log.error(" Verify : " + "Page Body Class not found ---> " + info);
        }

        String infologs = "Angular is running in the development mode. Call enableProdMode() to enable the production mode";
        if (DriverMaker.isBrowserLogContains(infologs)) {
            System.out.println(" Verify : " + "The log found in browser Console ---> " + info);
            log.info(" Verify : " + "The log found in browser Console ---> " + info);
        } else {
            System.out.println(" Verify : " + "The log not found in browser Console ---> " + info);
            log.error(" Verify : " + "The log not found in browser Console ---> " + info);
        }

    }

    @Then("^I verify the Version number \"([^\"]*)\" on 'Advisor Check-In' page$")
    public void iVerifyAD_VersionNumberOnAC(String expectedVersionNo) {
        apage.isACVersionNoMatch(expectedVersionNo);
    }

    @Then("^I verify the AD Version number \"([^\"]*)\" on 'Advisor Check-In' page$")
    public void iVerifyADAD_VersionNumberOnAC(String expectedVersionNo) {
        String actualVersionNo = apage.getAdisorVersion();
        if (actualVersionNo.equals(expectedVersionNo)) {
            System.out.println("<====== AC version number '" + actualVersionNo + "' matches ======>");
        } else {
            System.err.println("<====== AC version number '" + actualVersionNo + "' not match expected version number '" + expectedVersionNo + "' ======>");
        }
    }


    @And("^I decode \"([^\"]*)\" on 'Advisor Check-In' page$")
    public void decodeByTheVinOnAdvisorCheckIn(String vinnumber) {
        if (vinnumber.contains("Last5")) {
            vinnumber = vinnumber.replace("Last5", "");
            vinnumber = vinnumber.substring(vinnumber.length() - 5);
        } else if (vinnumber.contains("Last8")) {
            vinnumber = vinnumber.replace("Last8", "");
            vinnumber = vinnumber.substring(vinnumber.length() - 8);
        }
        apage.decodeVIN(vinnumber);
        acPage.clickOnIgnoreInfo();
    }

    @And("^I search 'VinNumber' \"([^\"]*)\" on 'Advisor Check-In' page$")
    public void iSearchVinOnAdvisorCheckIn(String vinnumber) {
        if (vinnumber.contains("Last5")) {
            vinnumber = vinnumber.replace("Last5", "");
            vinnumber = vinnumber.substring(vinnumber.length() - 5);
        } else if (vinnumber.contains("Last8")) {
            vinnumber = vinnumber.replace("Last8", "");
            vinnumber = vinnumber.substring(vinnumber.length() - 8);
        }else if (vinnumber.contains("GLOBAL")) {
            vinnumber = VINGenerator.vinNumber;
        }
        apage.searchCustomerByVIN(vinnumber);
    }


    @And("^I search \"([^\"]*)\" with \"([^\"]*)\" in 'Advisor Check-In Search' page$")
    public void iSearchWithInSearchPage(String searchField, String searchTerm) {
        apage.clearAllSearchField();
        String sField = searchField.toUpperCase();
        switch (sField) {
            case "VEHICLE VIN":
                iSearchVinOnAdvisorCheckIn(searchTerm);
                break;
            case "LICENSE PLATE":
                apage.searchPlateNumber(searchTerm);
                apage.clickOnSearchIconWithField(sField);
                break;
            case "CUSTOMER NAME":
                apage.searchCustomerNameClick(searchTerm);
//                apage.searchCustomerName(searchTerm);
                apage.clickOnSearchIconWithField(sField);
                break;
            case "CUSTOMER PHONE":
                apage.searchCustomerNumber(searchTerm);
                apage.clickOnSearchIconWithField(sField);
                break;
        }

    }


    @And("^I click \"([^\"]*)\" icon on \"([^\"]*)\" field in 'Advisor Check-In' page$")
    public void iClickIconOnACPage(String iconName, String fieldName) {
        switch (iconName) {
            case "search":
                apage.clickOnSearchIconWithField(fieldName);
                break;
            case "right arrow":
                apage.clickOnRightArrowWithField(fieldName);
                break;
        }
    }

    @And("^I click \"([^\"]*)\" search result on 'Advisor Check-In' page$")
    public void iClickSearchResultonAcPage(String order) {
        order = toBeAlpha(order);
        apage.clickSearchResultWithOrder(order);
    }

    @And("^I select \"([^\"]*)\" search result on 'Advisor Check-In' page$")
    public void iSelectFromResultonAcPage(String order) {
            iClickSearchResultonAcPage(order);
            apage.clickNextBtnOnAdvisorFrame();
    }

    @And("^I select \"([^\"]*)\" search result if Mutiple on 'Advisor Check-In' page$")
    public void iSelectSearchMutiResultAcPage(String order) {
        if (apage.isMoreThenOneCustomer()){
            iClickSearchResultonAcPage(order);
            apage.clickNextBtnOnAdvisorFrame();
        }

    }

    @Then("^I verify search result contains \"([^\"]*)\" \"([^\"]*)\" on 'Advisor Check-In' page$")
    public void iVerifySearchResult(String searchTerm, String searchField) {
        String sField = searchField.toUpperCase();
        switch (sField) {
            case "NAME":
              verifyTrue(" not included ",apage.hasCustomerNameInSearch(searchTerm));
            default:
                if(apage.hasCustomerNameInSearch(searchTerm)){
                    System.out.println("<== The search result Customer Name Found  "+searchTerm+ "  =====>");
                }else{
                    System.out.println("<== The search result Customer Name Found  "+searchTerm+ "  =====>");
                }
        }

    }


    @And("^I click \"([^\"]*)\" button on 'Advisor Check-In' page$")
    public void iClickButtonOACPage(String buttonName) {
        switch (buttonName) {
            case "NEXT":
                apage.clickNextBtnOnAdvisorFrame();
                break;
        }
    }

}
