package com.autotest.teststeps.wiadvisor;

import com.automation.pages.wiadvisor.*;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class WIAdvisorFinalizeROSteps extends BaseTestSteps {

    WiAdvisorFinalizeROTab fRO = new WiAdvisorFinalizeROTab();
    WiAdvisorPageHelpPDF wAiPDF = new WiAdvisorPageHelpPDF();


    @And("^I \"([^\"]*)\" in 'Finalize RO' tab$")
    public void iSignInFinalizeROTab(String action) {
        fRO.signOnRO();
    }


    @When("^I click \"([^\"]*)\" button in 'Finalize RO' page$")
    public void iClickButtonInFinalizeRO(String buttonName){
        switch (buttonName){
            case "Complete":
                fRO.clickOnCompleteFinalizeRO();
                break;
        }
    }

    @Then("^I see following PDF generated on 'Complete Repair Order' pop up$")
    public void iSeePDFOnCompleteROPopUp(DataTable dataTable) {
        fRO.waitForSurvey();
        fRO.waitForROPush();
        fRO.waitForPDFReady();
        Boolean result = true;
        StringBuffer noPDF = new StringBuffer("No PDF Generated : ");
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String pdfName = map.get("PDFName");
            String status = map.get("Status");
            if (notBlank(status)) {
                if (!fRO.isGeneratePDFCompleteRO(pdfName)){
                    result = false;
                    noPDF.append(" " + pdfName);
                };
            }
        }
        if (!result){
            System.out.println(noPDF.toString() + " not on screen this moment !");
        }
    }

    @Then("^I see all PDF generated on 'Complete Repair Order' pop up$")
    public void iVerifyPDFOnCompleteROPopUp(DataTable dataTable) {
        fRO.waitForSurvey();
        fRO.waitForROPush();
        fRO.waitForPDFReady();
        Boolean result = true;
        StringBuffer noPDF = new StringBuffer("No PDF Generated : ");
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String pdfName = map.get("PDFName");
            String status = map.get("Status");
            if (notBlank(status)) {
                if (!fRO.isGeneratePDFCompleteRO(pdfName)){
                    result = false;
                    noPDF.append(" " + pdfName);
                };
            }
        }
        if (!result){
            System.out.println(noPDF.toString() + " not on screen this moment !");
        }
    }


    @Then("^I verify following PDF generated on 'Complete Repair Order' PopUP$")
    public void iVerifyPDFGenerateOnWiCompleteRO(DataTable dataTable) throws Throwable {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String pdfName = map.get("PDF_Name");
            String verifyField1 = map.get("VerifyField1");
            String verifyField2 = map.get("VerifyField2");
            String verifyField3 = map.get("VerifyField3");
            String pdfContent;
            if (notBlank(pdfName)) {
                if (pdfName.equalsIgnoreCase("open maintenance menu")){
                    pdfContent = wAiPDF.clickGetMaintMenuPDF();
                    wAiPDF.verifyMaintMenu(pdfContent, verifyField1, verifyField2, verifyField3);
                }else if (pdfName.equalsIgnoreCase("open pre-Service write-up")){
                    pdfContent =  wAiPDF.clickGetPreServiceWritePDF();
                    wAiPDF.verifyPreServiceWriteUp(pdfContent, verifyField1);
                }else if (pdfName.equalsIgnoreCase("open vip")){
                    pdfContent =  wAiPDF.clickGetOpenVIPlinkPDF();
                    wAiPDF.verifyOpenVIP(pdfContent, verifyField1);
                } else {
                    System.out.println(pdfName + " is not existing or wrong PDF name !" );
                };
            }
            wAiPDF.cleanPDF();
//            asPDF.removePDF();
//            numberOfWindow = asPDF.getNumberOfTabOpen();
//            asPDF.changeToPageWithTitle("Advisor Check-In", numberOfWindow);
        }
    }

    @When("^I click \"([^\"]*)\" button on 'Complete Repair Order' PopUP$")
    public void iClickButtonOnWiCompleteRO(String buttonName){
        buttonName = buttonName.toLowerCase();
        switch (buttonName){
            case "ok":
                fRO.clickOnOkBtnPushPopUp();
                break;
        }
    }

}
