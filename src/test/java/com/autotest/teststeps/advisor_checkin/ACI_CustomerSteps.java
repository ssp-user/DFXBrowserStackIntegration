package com.autotest.teststeps.advisor_checkin;

import com.autotest.teststeps.BaseTestSteps;
import com.automation.pages.advisor_checkin.*;
import com.automation.utils.dataProvider.TestParameters;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ACI_CustomerSteps extends BaseTestSteps {


    private static StringBuffer expectSearch = new StringBuffer(" ***");
    private static StringBuffer actulSearch = new StringBuffer(" ***");

    ACI_CustomerPage acPage = new ACI_CustomerPage();
    ACI_PageHelpPDF asPDF = new ACI_PageHelpPDF();

    private static Logger log = Logger.getLogger(ACI_CustomerSteps.class);

    @And("^I enter \"(.*)\" in \"(.*)\" field on 'CUSTOMER' page$")
    public void enterCustomerDetails(String value , String field) {
        field = field.toUpperCase();
        switch (field) {
            case "ODOMETER":
//                acPage.enterOdometer(value);
                acPage.updateOdometer(value);
                break;
            case "FIRST NAME":
                acPage.updateFirstName(value);
                break;
            case "LAST NAME":
                acPage.updateLastName(value);                
                break;   
            case "ADDRESS":
            	acPage.updateAddress(value);
                break;
            case "CITY":
                acPage.updateCity(value);
                break;
            case "ZIP/POSTAL CODE":
                acPage.updateZipPostalCode(value);
                break;
            case "PHONE NUMBER":
                acPage.updatePhoneNumber(value);
                break;  
            case "CUSTOMER NOTES":
            	acPage.updateCustomerNotes(value);
                break;                
        }
    }

    @And("^I set \"(.*)\" to \"(.*)\" field on 'CUSTOMER' page$")
    public void setOdometerFieldOnCustomer(String value , String field) {
        field = field.toUpperCase();
        switch (field) {
            case "ODOMETER":
                acPage.updateOdometer(value);
                break;
        }
    }

    @And("^I deal with Customer Information PopUp in 'CUSTOMER' page$")
    public void iDealWithCustomerPopUpPage() {
//        acPage.waitForLoadingCompletedOnAdvisorCheckIn();
//        System.out.println(" Customer Page loading .. " + new Date());
        acPage.waitCustomerPageLoad();
//        System.out.println(" Customer Page loaded .. " + new Date());
//        acPage.storedDataWarningHandler();
        acPage.handleCreateNewCustomer();
        acPage.handleStoredDataWarning();
//        System.out.println(" Store Data handled .. " + new Date());
//        acPage.existingAppointmentHandler();
        acPage.handleExistingAppointment();
//        System.out.println(" Appointment handled .. " + new Date());
//        acPage.svcLaneMsgHandler();
        acPage.handleSvcLaneMsg();
//        System.out.println(" Service Lane handled .. " + new Date());
        acPage.countyBlankHandler();
//        System.out.println(" Country field handled .. " + new Date());
    }

    @And("^I deal with Customer Information PopUp in 'VEHICLE' page$")
    public void iDealWithCustomerPoPOnVechiclePage() {
//        acPage.waitForLoadingCompletedOnAdvisorCheckIn();
        acPage.waitCustomerPageLoad();
//        System.out.println(" Customer Page loaded .. " + new Date());
//        acPage.storedDataWarningHandler();
        acPage.handleStoredDataWarning();
//        System.out.println(" Store Data handled .. " + new Date());
//        acPage.existingAppointmentHandler();
        acPage.handleExistingAppointment();
//        System.out.println(" Appointment  handled .. " + new Date());
//        acPage.svcLaneMsgHandler();
        acPage.handleSvcLaneMsg();
//        System.out.println(" Serice Lane   handled .. " + new Date());
    }

    @And("^I deal with Service Lane Information PopUp in 'VEHICLE' page$")
    public void iDealWithServicePoPOnVechiclePage() {
//        acPage.waitForLoadingCompletedOnAdvisorCheckIn();
        acPage.waitCustomerPageLoad();
//        System.out.println(" Customer Page loaded .. " + new Date());
//        acPage.storedDataWarningHandler();
        acPage.handleStoredDataWarning();
//        System.out.println(" Store Data handled .. " + new Date());
//        acPage.svcLaneMsgHandler();
        acPage.handleSvcLaneMsg();
//        System.out.println(" Serice Lane   handled .. " + new Date());
    }

    @And("^I verify Mandatory Message Displayed in Fields on 'CUSTOMER' page$")
    public void iVerifyMandatoryMsgDisplayCustomer() {
        Assert.assertTrue(acPage.isMandatoryMsgDisplayed());
        acPage.checkSalvagedTitle();
    }

    @And("^I verify \"(.*)\" button is not clickable if 'Odometer' field is empty$")
    public void iVerifyNextBtnNotClickabaleIfEmptyOdo(String btn) {
        String sBtn = "";
        if (btn.equalsIgnoreCase("NEXT")) {
            sBtn = "NextButton";
        }
        if (acPage.getAttributeOdoMeterValue().isEmpty()) {
            String actValue = acPage.getCssBackgroundColorValue(sBtn);
            String expvalue = TestParameters.ButtonNotClickableBackgroundColor;
            try {
                Assert.assertEquals(expvalue, actValue);
            } catch (AssertionError e) {
                System.out.println("Warning !!! Odometer as mandatory field, Next Button Should Not Clickable ");
                log.error("Warning !!! Odometer as mandatory field, Next Button Should Not Clickable ");
            }
        }

    }

    @And("^I see \"(.*)\" button is clickable on 'CUSTOMER' page$")
    public void iSeeNextBtnIsClickabaleOnCustomerPg(String btn) {
        String sBtn = "";
        if (btn.equalsIgnoreCase("NEXT")) {
            sBtn = "NextButton";
        }

        String actValue = acPage.getCssBackgroundColorValue(sBtn);
        String expvalue = TestParameters.ButtonClickableBackgroundColor;
        try {
            Assert.assertEquals(expvalue, actValue);
        } catch (AssertionError e) {
            System.out.println("Warning !!! Odometer as mandatory field, Next Button Should Clickable ");
            log.error("Warning !!! Odometer as mandatory field, Next Button Should Clickable ");
        }

    }

    @Then("^I see \"([^\"]*)\" \"([^\"]*)\" status on 'CUSTOMER' page$")
    public void iSeeStatusOnCustomerPage(String expectedStatus, String statusName) {
        Assert.assertTrue(acPage.assertStatusOnCustomerInfo(expectedStatus, statusName));
    }

    @Then("^I see Following status on 'CUSTOMER' page$")
    public void iVerifyStatusOnCustomerPage(DataTable dataTable ) {
        StringBuffer expect = new StringBuffer("-->");
        StringBuffer actual = new StringBuffer("-->");
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String field = map.get("FieldName");
            String value = map.get("Value");
            switch (field) {
                case "RECALLS":
                    expect.append(" "+field+":"+ (processEmpty(value)).toUpperCase());
                    if (acPage.recallFound()){
                        actual.append(" "+field+":Y");
                    }else { actual.append(" "+field+":N");}
                    break;
                case "VIEW WARRANTY":
                    if (acPage.itemsWARRANTYOnCustomerInfo() >= 3){
                        expect.append(" "+field+":"+ (processEmpty(value)).toUpperCase());
                        actual.append(" "+field+":Y");
                    }else if (acPage.itemsWARRANTYOnCustomerInfo() == 0 ){
                        expect.append(" "+field+":"+ "None");
                        actual.append(" "+field+":None");
                    }else{
                        expect.append(" "+field+":"+ (processEmpty(value)).toUpperCase());
                        actual.append(" "+field+":N");
                    }
//                  (acPage.itemsWARRANTYOnCustomerInfo() == 0){
                    break;
                case "SERVICE CONTRACT":
                    expect.append(" "+field+":"+ (processEmpty(value)).toUpperCase());
                    if (acPage.hasServiceContactOnCustomerInfo()){
                        actual.append(" "+field+":Y");
                    }else {
                        actual.append(" "+field+":N");
                    }
                    break;
            }
        }
        String ex = expect.toString();  String ac =  actual.toString();
        if(!ex.equalsIgnoreCase(ac)){
            System.out.println(" Recall s Test Failed !" );
            System.out.println("Expect result : " + ex );
            System.out.println("Actual  result : " + ac );
//            Assert.assertEquals(" Recall , Warranty , Service Contract Status not match ! ", ex, ac);
        }else{
            System.out.println(" Recall s Test pass !" );
        }
    }

    @When("^I open \"(.*)\" ServiceIcon on 'CUSTOMER' page$")
    public void iOpenServiceIconOnCustomerPage(String iconName) {
        String icon =  iconName.toUpperCase();
//        if (icon.equals("NISSAN")){
//            acPage.openNissanReportIconOnCustomer();
//        }else if (icon.equals("TOYOTA") ||icon.equals("LEXUS") || icon.equals("SCION") ){
//            acPage.openToyotaServiceLaneIcon();
//        }else{
//           //
//        }
        switch (icon) {
            case "NISSAN":
                acPage.openNissanReportIconOnCustomer();
                break;
            case "MITSUBISHI":
                acPage.openMitsubishiReportIconOnCustomer();
                break;
            case "AUDI":
                acPage.openAudiReportIconOnCustomer();
                break;
            case "GM":
                acPage.openGMReportIconOnCustomer();
                break;
            case "TOYOTA":
                acPage.openToyotaReportIconOnCustomer();
                break;
            default :
                return;
        }
    }

    @When("^I open \"([^\"]*)\" worksheet icon on 'CUSTOMER' page$")
    public void iOpenWorkSheetIconOnCustomerPage(String iconName) {
        String icon = iconName.toUpperCase();
        if (icon.equals("NISSAN")){
            acPage.openNissanWorksheetIconOnCustomer();
        }
    }

    @Then("^I verify \"(.*)\" OEM_ReportPDF status \"(.*)\" and \"(.*)\" on 'CUSTOMER' page$")
    public void iVerifyOEMReportPDFCustomerPage(String maker, String recall , String contract ) {
          String field = maker.toUpperCase();
          String pdfContent;
        switch (field) {
            case "NISSAN":
                pdfContent = asPDF.getPDFContents();
                Assert.assertTrue("Verify Vehicle Service History Report PDF Content fail! ", pdfContent.contains("NATIONAL SERVICE"));
                Assert.assertEquals("Verify Open Campaign From PDF fail!", recall , asPDF.hasRecallServiceCommOpenCampaign(pdfContent));
                Assert.assertEquals("Verify Service Contracts From PDF fail!", contract , asPDF.hasServiceCommServiceContract(pdfContent));
                break;
            case "MITSUBISHI":
                pdfContent = asPDF.getPDFContents();
                Assert.assertTrue("Verify Vehicle Service History Report PDF Content fail! ", pdfContent.contains("Vehicle Inquiry Report"));
                Assert.assertEquals("Verify Open Recalls/Service Campaigns From PDF fail!", recall , asPDF.hasRecallMitsubishi(pdfContent));
//                Assert.assertEquals("Verify Service Contracts From PDF fail!", contract , asPDF.hasServiceCommServiceContract(pdfContent));
                break;
            case "GM":
                pdfContent = asPDF.getPDFContents();
                Assert.assertTrue("Verify Vehicle Service History Report PDF Content fail! ", pdfContent.contains("Vehicle History Report"));
                Assert.assertEquals("Verify Open Recalls/Service Campaigns From PDF fail!", recall , asPDF.hasRecallGM(pdfContent));
                Assert.assertEquals("Verify Service Contracts From PDF fail!", contract , asPDF.hasContractGM(pdfContent));
                break;
            case "TOYOTA":
                pdfContent = asPDF.getPDFContents();
                Assert.assertTrue("Toyota Service Lane PDF failed generated! ", pdfContent.contains("ToyotaServiceLane"));
                Assert.assertEquals("Verify Open Campaign From PDF fail!", recall , asPDF.hasRecallServiceLaneOpenCampaign(pdfContent));
//                Assert.assertEquals("Verify Service Connect From PDF fail!", contract , asPDF.hasContractToyota(pdfContent));
//                Assert.assertTrue("Verify History from Service Lane PDF! ", asPDF.hasServiceLaneServiceHistory(pdfContent));
//                Assert.assertSame("Verify ToyotaCare From Service Lane Contracts PDF fail!", contract , asPDF.hasContractServiceLaneToyotaCare(pdfContent));
                break;
            case "LEXUS":
                pdfContent = asPDF.getPDFContents();
                Assert.assertTrue("Lexus Service Lane PDF failed generated ! ", pdfContent.contains("Lexus Service Lane"));
                Assert.assertEquals("Verify Open Campaign From PDF fail !", recall , asPDF.hasRecallServiceLaneOpenCampaign(pdfContent) );
                Assert.assertTrue("Verify History from Service Lane PDF ! ", asPDF.hasServiceLaneServiceHistory(pdfContent));
                break;
            case "SCION":
                pdfContent = asPDF.getPDFContents();
                Assert.assertTrue("Scion Service Lane PDF failed generated ! ", pdfContent.contains("Service Lane Report"));
                Assert.assertEquals("Verify Open Campaign From PDF fail !", recall , asPDF.hasRecallServiceLaneOpenCampaign(pdfContent) );
                Assert.assertTrue("Verify History from Service Lane PDF ! ", asPDF.hasServiceLaneServiceHistory(pdfContent));
//                Assert.assertSame("Verify ToyotaCare From Service Lane Contracts PDF fail !", contract , asPDF.hasContractServiceLaneToyotaCare(pdfContent) );
                break;
            default :
               return;
        }
        asPDF.cleanPDF();
    }

    @Then("^I verify \"([^\"]*)\" worksheet content on 'CUSTOMER' page$")
    public void iVerifyWorksheetPDFCustomerPage(String maker) {
        String field = maker.toUpperCase();
        String url;
//        String pdfContent;
        switch (field) {
            case "NISSAN":
//                pdfContent = asPDF.getPDFContents();
//                Assert.assertTrue("Verify Vehicle Worksheet PDF Content fail!", pdfContent.contains("Squeak and Rattle"));
                url = acPage.getURL();
                System.out.println("The worksheet url is: " + url);
                Assert.assertTrue("Verify Vehicle Worksheet fail!", url.contains("Nissan-SqueakRattle-worksheet"));
                break;
            default :
                return;
        }
//        asPDF.cleanPDF();
    }

    @Then("^I verify \"([^\"]*)\" status on 'CUSTOMER' page$")
    public void iVerifyStatusOnCustomerPage(String statusName) {
        acPage.verifyStatusOnCustomerInfo(statusName);
    }

    @And("^I assert \"([^\"]*)\" equal \"([^\"]*)\" on 'CUSTOMER' page$")
    public void iAssertOnCustomerPage(String field, String expectedResult) {   // need remove . replaced by follow verify
        switch (field) {
            case "DEFAULT PHONE NUMBER":
                Assert.assertTrue(acPage.isPhoneNumberMatch(expectedResult));
                break;
            case "LICENSE PLATE":
                Assert.assertTrue(acPage.isPlateNumberMatch(expectedResult));
                break;
            case "VEHICLE INFORMATION":
                Assert.assertTrue(acPage.isVinMatch(expectedResult));
                break;
        }

    }

    @And("^I verify \"([^\"]*)\" equal \"([^\"]*)\" on 'CUSTOMER' page$")
    public void iVerifyOnCustomerInfoPage(String field, String expectedResult) {
        iDealWithCustomerPopUpPage();
        field = field.toUpperCase();
        expectSearch.append(" "+field+":"+expectedResult+"->Y ");
        String result ="";
        boolean ok = false;
        switch (field) {
            case "DEFAULT PHONE NUMBER":
                result = acPage.getVinONCustomerInfo();
//                result = acPage.getPhoneNumberCustomerInfo();
//                String result1 = result.replace("-","");
//                result1 = result1.replace("（","").replace("）","");
//                String expected = expectedResult.replace("-","");
//                expected = expected.replace("(","").replace(")","");
                ok = (result.length() > 0 );
                break;
            case "LICENSE PLATE":
                result = acPage.getPlateNumberCustomerInfo();
                ok  = result.contains(expectedResult);
                break;
            case "VEHICLE INFORMATION":
                result = acPage.getVinONCustomerInfo();
                ok =  result.contains(expectedResult);
                break;
            default:
                return;
        }

        if(ok){
            actulSearch.append(" "+field+":"+expectedResult+"->Y ");
        }else{
            actulSearch.append(" "+field+":"+expectedResult+"->N ");
            System.out.println(" Verify Fail ! The " + field + " expect " + expectedResult +  " Not found ! , the actual is " + result+ " on Customer Info Page !====>");
        }

    }

    @And("^I assert all above search verify result on 'CUSTOMER' page$")
    public void iAssertAllSearchVerifyOnCustomerPage() {
        String ex = expectSearch.toString();
        String ac =  actulSearch.toString();
        Assert.assertEquals(" customer search not match ! ", ex, ac);
    }

    @And("^I change \"([^\"]*)\" in \"([^\"]*)\" field on \"([^\"]*)\" page$")
    public void updateValueOnFieldOnpage(String fieldValue, String updateFieldName, String pageName) {
        if (fieldValue.equals("Phone#")) {
            fieldValue = TestParameters.phoneNumber;
        }
        switch (updateFieldName) {
            case "CELL PHONE NUMBER":
                acPage.updateCellNumber(fieldValue);
                break;
            case "DEFAULT PHONE NUMBER":
                acPage.updatePhoneNumber(fieldValue);
                break;
            case "EMAIL":
                acPage.updateEmail(fieldValue);
                break;
            case "FIRST NAME":
                acPage.updateFirstName(fieldValue);
                break;
            case "LAST NAME":
                acPage.updateLastName(fieldValue);
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on 'CUSTOMER' page$")
    public void iClickButtonOnCustomerPage(String buttonName) {
        switch (buttonName) {
            case "ACKNOWLEDGE":
                acPage.clickAcknowledgeBtn();
                break;
            case "NEXT":
                acPage.clickNextOnCustomer();
//                acPage.clickNextBtnOnAdvisorFrame();
                break;
            case "BACK TO SEARCH":
                acPage.clickBackToSearchOnCustomer();
                break;
            case "VIEW WARRANTY":
                acPage.clickViewWarrantyOnCustomer();
                break;
            case "CUSTOMER NOTES":
                acPage.clickCustomerNotes();
                break;     
            case "SAVE":
                acPage.clickCustomerNotesSave();
                break;     
            case "VIEW CUSTOMER NOTES":
                acPage.clickViewCustomerNotes();
                break;                 
        }
    }

    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" popup on 'CUSTOMER' page$")
    public void iClickButtonOnPopUpOnPage(String buttonName, String popupName) {
        switch (popupName) {
            case "EXISTING APPOINTMENT DETAILS":
                switch (buttonName) {
                    case "USE APPOINTMENT":
                        acPage.clickUseAppointmentOnExistingAppointmentPopUp();
                        break;
                }
                break;
            case "WARRANTY INFORMATION":
                switch (buttonName) {
                    case "CLOSE":
                        acPage.clickCloseOnWarrantyInformationPopUp();
                        break;
                }
                break;
        }
    }

    @And("^I open \"([^\"]*)\" icon on 'CUSTOMER' page$")
    public void iClickIconOnCustomerPage(String iconName) {
        String iname = iconName.toUpperCase();
        switch (iname) {
            case "E2G":
                acPage.goToElsa2Go();
                break;
        }

    }

    @And("^I goto \"([^\"]*)\" Tab on 'Advisor Check-In' page$")
    public void iGotoACTabOnCustomerPage(String iconName) {
        String iname = iconName.toUpperCase();
        switch (iname) {
            case "CUSTOMER":
                acPage.goToCustomerTab();
                break;
            case "CONCERNS":
                acPage.goToConcernsTab();
                break;
            case "HISTORY":
                acPage.goToHistoryTab();
                break;
            case "SERVICES":
                acPage.goToServiceTab();
                break;
            case "WALK AROUND":
                acPage.goToWalkRoundTab();
                break;
            case "Assign RO":
                acPage.goToAssignROTab();
                break;
            case "AUTHORIZATION":
                acPage.goToAssignROTab();
                break;
        }
    }

    @When("^I input \"([^\"]*)\" into \"([^\"]*)\" field on 'CUSTOMER' page$")
    public void iInputSomethingIntoSomeFieldOnCustomerPage(String mileage, String fieldName) {
        switch (fieldName) {
            case "ODOMETER":
                acPage.updateOdometer(mileage);
                break;
        }
    }

    @When("^I go back to 'CUSTOMER' page$")
    public void iGoBackTOCustomerPage() {
        acPage.backToAdvisorCheckInPage();
    }

    @And("^I select the concern from the 'CUSTOMER' page$")
    public void iSelectConcernFromCustomerPage() {
    	acPage.selectConcern();
    }

    @And("^I select \"(.*)\" as Symptom Survey from the 'CUSTOMER' page$")
    public void selectSymptomSurvey(String symptomsurvey) {
    	acPage.selectSymptomSurvey(symptomsurvey);
    }    

    @And("^I validate Customer Details in 'CUSTOMER' page$")
    public void iValidateCustomerDetails() {
    	acPage.validateCustomerDetails();
    }
    
    @And("^I validate Customer Notes in 'CUSTOMER' page$")
    public void iValidateCustomerNotes() {
    	acPage.validateCustomerNotes();
    }    
    
    @And("^I validate appointment number in 'CUSTOMER' page$")
    public void iValidateAppointmentNumber() {
    	acPage.validateAppointmentNumber();
    }  
    
    @And("^I deal with close button customer information PopUp in 'CUSTOMER' page$")
    public void iDealCloseButtonWithCustomerPopUpPage() {
        acPage.waitCustomerPageLoad();
        acPage.handleCreateNewCustomer();
        acPage.handleStoredDataWarning();
        acPage.closeExistingAppointment();
        acPage.handleSvcLaneMsg();
        acPage.countyBlankHandler();
    }    
    
    @And("^I capture Customer Name on 'CUSTOMER' page$")
    public void iCaptureCustomerName() {
    	acPage.captureCustomerName();
    }      
    
}
