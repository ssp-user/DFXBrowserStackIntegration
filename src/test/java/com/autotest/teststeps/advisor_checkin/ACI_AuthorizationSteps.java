package com.autotest.teststeps.advisor_checkin;


import com.automation.pages.advisor_checkin.ACI_AuthorizationPage;
import com.automation.pages.advisor_checkin.ACI_PageHelpPDF;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ACI_AuthorizationSteps extends BaseTestSteps {

    ACI_PageHelpPDF asPDF = new ACI_PageHelpPDF();
    ACI_AuthorizationPage aaPage = new ACI_AuthorizationPage();

    private static Logger log = Logger.getLogger(ACI_AuthorizationSteps.class);


    @And("^I authorize and sign the agreement on 'AUTHORIZATION' page$")
    public void authorize() {
        aaPage.authorized();
        aaPage.signed();
    }

    @And("^I sign the agreement on \"([^\"]*)\" page$")
    public void iSign(String pageName) {
        switch (pageName) {
            case "AUTHORIZATION":
                aaPage.signed();
                break;
        }

    }

    @Then("^I should see following info displayed on \"([^\"]*)\" page$")
    public void iShouldSeeSomethingAndSomethingDisplayed(String pageName, DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String field = map.get("fieldName");
            if (field.equals("OWNER REGISTRATION")) {
                field = "Owner registration";
            } else if (field.equals("CUSTOMER EMAIL IS REQUIRED")) {
                field = "Customer email is required";
            } else if (field.equals("Yes/No toggle")) {
                field = "YES";
            }
            switch (pageName) {
                case "AUTHORIZATION":
                    //verify the field use assert
                    Assert.assertTrue(aaPage.verifyRegistrationField(field));
                    break;
            }
        }
    }

    @And("^I set \"([^\"]*)\" toggle to \"([^\"]*)\" on \"([^\"]*)\" page$")
    public void iSetToggleToValueOnPage(String toggleName, String toggleValue, String pageName) {
        switch (pageName) {
            case "AUTHORIZATION":
                switch (toggleName) {
                    case "Yes/No":
                        aaPage.clickOnRegistrationYesNoToggle(toggleValue);
                        break;
                }
                break;
        }
    }

    @Then("^I should see following green checkmark on \"([^\"]*)\" page$")
    public void iShouldSeeCheckMarkonPage(String pageName, DataTable dataTable) throws Throwable{
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String field = map.get("fieldName");
            switch (pageName) {
                case "AUTHORIZATION":
                    Assert.assertTrue(aaPage.verifyRoPushTermsGreenCheckMark(field));
                    break;
            }
        }
    }

    @Then("^I should see RO push completed$")
    public void iSeeROPushCompleted() {
        if (aaPage.isGenerateROPushPdf()) {
            System.out.println("<====== RO push completed PDF verified ======> ");
        } else {
            System.out.println("<====== RO push completed has issue, test failed ======>");
            log.error("<==== RO push completed has issue, test failed =======>");
        }
    }

    @Then("^I assert RO push completed$")
    public void iAssertROPushCompleted() {
        Assert.assertTrue(" RO push Error ,  test failed !  ",aaPage.isGenerateROPushPdf());
    }

    @Then("^I verify no DMS push error$")
    public void iVerifyNoDMSError() {
        String msg = aaPage.msgPushError();
        if (!msg.equalsIgnoreCase("PASS")) {
        } else {
            System.out.println("<== RO push failed , Error  " + msg + "====>");
            log.error("<== RO push failed , Error  " + msg + "====>");
        }
    }

    @Then("^I assert no DMS push error$")
    public void iAssertNoDMSError() {
        String msg = aaPage.msgPushError();
        Assert.assertEquals("<== RO push failed , Error  " + msg + "====>","PASS",msg);
    }

    @Then("^I see 'GENERATE QUOTE' PDF completed$")
    public void iSeeGENERATEQUOTECompleted() {
        if (aaPage.isGenerateQuotePdf()) {
            System.out.println("<====== GENERATE QUOTE PDF verified ======>");
        } else {
            System.out.println("<====== GENERATE QUOTE PDF has issue, test failed ======>");
            log.error("<==== GENERATE QUOTE PDF has issue, test failed =======>");
        }

    }

    @Then("^I verify following PDF generated in RO push$")
    public void iVerifyPDFinRoPush(DataTable dataTable) throws Throwable {
        StringBuffer expect = new StringBuffer("-->");
        StringBuffer actul = new StringBuffer("-->");
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String pdfName = map.get("PDF_Name");
            String verifyField1 = map.get("VerifyField1");
            String verifyField2 = map.get("VerifyField2");
            String verifyField3 = map.get("VerifyField3");
            String verifyField4 = map.get("VerifyField4");
            String verifyField5 = map.get("VerifyField5");
            String verifyField6 = map.get("VerifyField6");
            String pdfContent;
            String  result = "";
            expect.append(" "+pdfName+":"+verifyField1+"->Y ");
            switch (pdfName) {
                case "Maintenance Menu PDF":
                    pdfContent = asPDF.clickGetOnMMPDFShow();
                    if(asPDF.hasMaintMenuFields(pdfContent, verifyField1, verifyField2, verifyField3, verifyField4, verifyField5, verifyField6)){
                        result = "Y ";
                    }else{
                        result = "N ";
                    }
//                    asPDF.verifyMaintMenu(pdfContent, verifyField1, verifyField2, verifyField3, verifyField4, verifyField5, verifyField6);
//                    Assert.assertTrue("Maintenance Menu PDF failed generated ! ",
//                            asPDF.hasMaintMenuFields(pdfContent, verifyField1, verifyField2, verifyField3, verifyField4, verifyField5, verifyField6));
                    break;
                case "Pre-Service Write-Up PDF":
                    pdfContent = asPDF.clickGetOnRorPDFShow();
                    if(pdfContent.contains(verifyField1)){
                        result = "Y ";
                    }else{
                        result = "N ";
                    }
//                    asPDF.verifyPreServiceWriteUp(pdfContent, verifyField1);
//                    Assert.assertTrue("Pre-service Write-Up PDF failed generated ! ", pdfContent.contains(verifyField1));
                    break;
                case "MULTIPOINT INSPECTION":
                    pdfContent = asPDF.clickGetOnEvirPDFShow();
                    if(pdfContent.contains(verifyField1)){
                        result = "Y ";
                    }else{
                        result = "N ";
                    }
//                    asPDF.verifyEVIR(pdfContent, verifyField1);
//                    Assert.assertTrue("EVIR PDF failed generated ! ", pdfContent.contains(verifyField1));
                    break;
                case "Parts Pick List":
                    pdfContent = asPDF.clickGetOnPartsPickPDFShow();
                    if(pdfContent.contains(verifyField1)){
                        result = "Y ";
                    }else{
                        result = "N ";
                    }
//                    asPDF.verifyPartsPickList(pdfContent, verifyField1);
//                    Assert.assertTrue("Parts Pick List DBF failed generated ! ", pdfContent.contains(verifyField1));
                    break;
                default:
                    return;
            }
            actul.append(" "+pdfName+":"+verifyField1+"->" + result );
            asPDF.cleanPDF();
        }
        String ex = expect.toString();
        String ac =  actul.toString();
        if(!ex.equals(ac)){
            System.out.println(" expect result  is : " + ex );
            System.out.println(" actul result  is : " + ac );
            Assert.assertEquals(" PDF content verify not match ! ", ex, ac);
        }

    }


    @Then("^I verify all links PDF generated in RO push$")
    public void iVerifyAllPDFLinkRoPush(DataTable dataTable) throws Throwable {
        StringBuffer expect = new StringBuffer("-->");
        StringBuffer actul = new StringBuffer("-->");
        StringBuffer failResult = new StringBuffer("-->");
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String pdfName = map.get("PDF_Name");
            String verifyField = map.get("VerifyField");
            String  result = "";
            switch (pdfName) {
                case "Maintenance Menu PDF":
                    result = asPDF.verifyMaintenMenu(verifyField);
                    break;
                case "Pre-Service Write-Up PDF":
                    result = asPDF.verifyPreServiceWriteUp(verifyField);
                    break;
                case "MULTIPOINT INSPECTION":
                    result = asPDF.verifyEVIR(verifyField);
                    break;
                case "Parts Pick List":
                    result = asPDF.verifyPartsPickList(verifyField);
                    break;
                default:
                    return;
            }
            if (result.equals("X")){
                expect.append(" "+pdfName+":X ");
            }else{
                expect.append(" "+pdfName+":Y ");
                asPDF.cleanPDF();
            }
            failResult.append(" "+pdfName+":X ");
            actul.append(" "+pdfName+":"+ result+" ");

        }
        String ex = expect.toString();
        String ac =  actul.toString();
        String fr =  failResult.toString();
        if(!ex.equals(ac)){
            System.out.println(" expect result  is : " + ex );
            System.out.println(" actul result  is : " + ac );
            Assert.assertEquals(" PDF content verify not match ! ", ex, ac);
        }
        Assert.assertNotEquals(" No PDF Generated ! ", fr, ac);
    }


    @Then("^I verify following in PDF generated on 'GENERATE QUOTE'$")
    public void iVerifyPDFGenerateQuote(DataTable dataTable) throws Throwable {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String pdfName = map.get("PDF_Name");
            String verifyField = map.get("VerifyField");
            String pdfContent;
            switch (pdfName) {
                case "SAVED CUSTOMER SERVICE QUOTE":
                    pdfContent = asPDF.clickShowGetCustomerQuote();
                    if (pdfContent.contains(verifyField)) {
                        System.out.println("<====== SAVED CUSTOMER SERVICE QUOTE PDF verified ======>");
                    } else {
                        System.out.println("<====== SAVED CUSTOMER SERVICE QUOTE PDF has issue, test failed ======>");
                        log.error("<==== SAVED CUSTOMER SERVICE QUOTE PDF has issue, test failed =======>");
                    }
                    break;
                case "SAVED INTERNAL SERVICE QUOTE":
                    pdfContent = asPDF.clickShowGetInternalQuote();
                    if (pdfContent.contains(verifyField)) {
                        System.out.println("<====== SAVED INTERNAL SERVICE QUOTE PDF verified ======>");
                    } else {
                        System.out.println("<====== SAVED INTERNAL SERVICE QUOTE PDF has issue, test failed ======>");
                        log.error("<==== SAVED INTERNAL SERVICE QUOTE PDF has issue, test failed =======>");
                    }
                    break;
                case "MAINTENANCE MENU":
                    pdfContent = asPDF.getPDFContents();
                    if (pdfContent.contains(verifyField)) {
                        System.out.println("<====== MAINTENANCE MENU PDF verified ======>");
                    } else {
                        System.out.println("<====== MAINTENANCE MENU PDF has issue, test failed ======>");
                        log.error("<==== MAINTENANCE MENU PDF has issue, test failed =======>");
                    }
                    break;

            }
            asPDF.cleanPDF();
        }

    }

    @And("^I click \"([^\"]*)\" button on 'AUTHORIZATION' page$")
    public void iClickButtonOnAuthorizationPage(String buttonName) {
        switch (buttonName) {
            case "COMPLETE":
                aaPage.clickOnCompletedAfterSign();
                break;
            case "I AUTHORIZE COMPLETION OF REQUESTED SERVICES":
                aaPage.authorized();
                break;
            case "QUOTE":
                aaPage.clickQUOTEButton();
                break;
            case "MENU":
                aaPage.clickMaintMenuBtn();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on 'GENERATE QUOTE' PopUp$")
    public void iClickCloseButtonOnGenerateQuotePop(String buttonName)
    {     buttonName = buttonName.toUpperCase();
        switch (buttonName) {
            case "CLOSE":
                aaPage.clickCloseBtnGenerateQuote();
                break;
        }
    }

    @And("^I click 'MENU' button verify the \"([^\"]*)\" in PDF generated on 'AUTHORIZATION' page$")
    public void iVerifyMenuPDFOnAuthorizationPage(String verifyField) {
        String pdfContent = asPDF.clickGetMaintMenuPDFContent();
        asPDF.removePDF();
        verifyTrue(" verify SAVED INTERNAL SERVICE QUOTE PDF ", pdfContent.contains(verifyField) );
    }

    @And("^I validate selected \"([^\"]*)\" is displayed correctly$")
    public void iValidateSurveyHeader(String surveyType)
    {     
    	aaPage.validateSurveyHeader(surveyType);
    }  


}
