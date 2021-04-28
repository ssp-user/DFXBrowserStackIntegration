package com.autotest.teststeps.DMPI;

import com.automation.pages.DMPI.DynamicMPIPage;
import com.automation.pages.customer_connect.CC_MessengerPage;
import com.automation.pages.service_dashboard.SD_ServiceVisit;
import com.automation.pages.service_dashboard.SD_VehicleQueuePage;
import com.automation.pages.third_party.GmailPage;
import com.automation.pages.third_party.MightyTextPage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static com.automation.utils.dataProvider.TestParameters.phoneNumber;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DMPISteps extends BaseTestSteps{

    SD_VehicleQueuePage vPage = new SD_VehicleQueuePage();
    SD_ServiceVisit svPage = new SD_ServiceVisit();
    CC_MessengerPage cmPage = new CC_MessengerPage();
    DynamicMPIPage dmpiPage = new DynamicMPIPage();
    GmailPage gmailPage = new GmailPage();
    MightyTextPage mtPage = new MightyTextPage();

    @And("^I click Document Type \"([^\"]*)\" under Final Documents on 'SERVICE VISIT' page$")
    public void iClickSomeDocumentTypeUnderFinalDocuments(String documentType){
        svPage.getCustomerFullName();
        svPage.clickOnDocumentType(documentType);
    }

    @And("^I update \"([^\"]*)\" on \"([^\"]*)\" field on \"([^\"]*)\" popup$")
    public void iUpdateSomethingOnSomeFieldOnSomePopup(String updateValue, String fieldName, String popUpName){
        switch(fieldName){
            case "SMS":
                svPage.updateSMSOnCustomerContactInformation(updateValue);
                break;
        }
    }

    @And("^I toggle \"([^\"]*)\" on \"([^\"]*)\" field on \"([^\"]*)\" popup$")
    public void iToggleSomethingOnSomeFieldOnSomePopup(String toggleValue, String fieldName, String popUpName){
        switch(fieldName){
            case "SMS":
                svPage.toggleSMSONCustomerContactInformation(toggleValue);
                break;
            case "Email":
                svPage.toggleEmailONCustomerContactInformation(toggleValue);
                break;
        }
    }

    @And("^I select \"([^\"]*)\" on \"([^\"]*)\" field on \"([^\"]*)\" popup$")
    public void iSelectSomethingOnSomeFieldOnSomePopup(String selectValue, String fieldName, String popUpName){
        switch(fieldName){
            case "Name:":
                svPage.selectLanguageOnPopUp(selectValue);
                break;
        }
    }

    @When("^I click on URL contains \"([^\"]*)\" in received SMS on \"([^\"]*)\" page$")
    public void iClickUrlOnSMSOnPage(String urlWithinSMS, String pageName){
        switch (pageName){
            case "ReceiveaSMS.com :: Canada":
                svPage.openURLFromSMS(urlWithinSMS);
                break;
            case "mightytext.net":
                mtPage.openURLFromSMS(urlWithinSMS);
                break;
        }
    }

    @When("^I click on URL contains \"([^\"]*)\" in email on \"([^\"]*)\" page$")
    public void iClickUrlOnEmailOnPage(String urlWithinEmail, String pageName){
        gmailPage.clickOnUrlWithinEmailBody(urlWithinEmail);
    }

    @When("^I input \"([^\"]*)\" \"([^\"]*)\" in \"([^\"]*)\" field on \"([^\"]*)\" page$")
    public void iInputNameTypeWithNameInSomeFieldOnSomePage(String nameType, String name, String fieldName, String pageName){
        switch(fieldName){
            case "Please enter your last name:":
                dmpiPage.inputNameOnNameField(nameType,name);
                break;
        }
    }

    @And("^I get \"([^\"]*)\" time on \"([^\"]*)\" section on \"([^\"]*)\" page$")
    public void iGetTimeOnSectionOnPage(String time, String sectionName, String pageName){
        switch (sectionName){
            case "Promise Time":
                switch (time){
                    case "If answered by":
                        svPage.getAnsweredByTime();
                        break;
                    case "Deliver by":
                        svPage.getDeliverByTime();
                        break;
                }
                break;
        }
    }

    @And("^I adjust the \"([^\"]*)\" time \"([^\"]*)\" current time on \"([^\"]*)\" section on \"([^\"]*)\" page$")
    public void iAdjustTimeOnSectionOnPage(String timeName, String timeStatus, String sectionName, String pageName){
        switch (pageName){
            case "SERVICE VISIT":
                switch (sectionName){
                    case "Promise Time":
                        svPage.adjustPromiseTime(timeName,timeStatus);
                        break;
                }
                break;
        }
    }

    @And("^I adjust the \"([^\"]*)\" time \"([^\"]*)\" \"([^\"]*)\" time on \"([^\"]*)\" section on \"([^\"]*)\" page$")
    public void iAdjustTwoTimeOnSectionOnPage(String timeName1, String timeStatus, String timeName2, String sectionName, String pageName) throws Throwable {
        switch (pageName){
            case "SERVICE VISIT":
                switch (sectionName){
                    case "Promise Time":
                        svPage.adjustTwoTime(timeName1,timeName2,timeStatus);
                        break;
                }
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" error message \"([^\"]*)\" on \"([^\"]*)\" section on \"([^\"]*)\" page$")
    public void iSeeErrorMessageOnSectionOnPage(String errorMessage, String messageStatus, String sectionName, String pageName){
        switch (pageName){
            case "SERVICE VISIT":
                switch (sectionName){
                    case "Promise Time":
                        assertTrue(svPage.verifyErrorMessageOnServiceVisit(errorMessage,messageStatus));
                        break;
                }
                break;
        }
    }

    @And("^I should see following data in \"([^\"]*)\" page$")
    public void iVerifyDataOnPage(String pageName, DataTable dataTable){
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String expectedValue = map.get("FieldValue");
            String expectedValue2 = map.get("FieldValue2");
            String actualValue = "";
            if (StringUtils.isNotBlank(fieldName)){
                switch(fieldName){
                    case "service advisor":
                        actualValue=dmpiPage.getServiceAdvisorNameOnDMPI();
                        assertTrue(actualValue.contains(expectedValue));
                        break;
                    case "RECOMMENDATIONS":
                        actualValue=dmpiPage.getRecommendationNumberOnDMPI();
                        assertTrue(actualValue.contains(expectedValue));
                        break;
                    case "vehicle name":
                        actualValue=dmpiPage.getVehicleNameOnDMPI();
                        assertTrue(actualValue.contains(expectedValue));
                        break;
                    case "Customer Concerns":
                        actualValue=dmpiPage.getCustomerConcernNumberOnDMPI();
                        assertTrue(actualValue.contains(expectedValue));
                        break;
                    case "Guest Concerns Greybard":
                        actualValue=dmpiPage.getGuestConcernsNumberOnDMPI();
                        assertTrue(actualValue.contains(expectedValue));
                        break;
                    case "Customer Concerns Safety":
                        actualValue=dmpiPage.getCustomerConcernSafetyOnDMPI();
                        assertTrue(actualValue.contains(expectedValue));
                        break;
                    case "Customer Concerns Attention":
                        actualValue=dmpiPage.getCustomerConcernAttentionOnDMPI();
                        assertTrue(actualValue.contains(expectedValue));
                        break;
                    case "Requires Immediate Attention":
                        actualValue=dmpiPage.getRequiresImmediateAttentionOnDMPI();
                        assertTrue(actualValue.contains(expectedValue));
                        break;
                    case "May Need Future Attention":
                        actualValue=dmpiPage.getMayNeedFutureAttentionOnDMPI();
                        assertTrue(actualValue.contains(expectedValue));
                        break;
                    case "Select your services in":
                        dmpiPage.getCountDownMinuteOnDMPI();
                        assertTrue(dmpiPage.verifyCountDownMinuteOnDMPI());
                        break;
                    case "To have your vehicle ready by":
                        dmpiPage.getPromiseTimeOnDMPI();
                        assertTrue(dmpiPage.verifyPromiseTimeOnDMPI());
                        break;
                    case "CUSTOMER CONCERN(S)":
                        actualValue=dmpiPage.getConcernPrice(expectedValue,expectedValue2);
                        assertEquals(expectedValue2,actualValue);
                        break;
                    case "REQUIRES IMMEDIATE ATTENTION":
                        actualValue=dmpiPage.getAttentionPrice(expectedValue,expectedValue2);
                        assertEquals(expectedValue2,actualValue);
                        break;
                    case "MAY NEED FUTURE ATTENTION":
                        actualValue=dmpiPage.getAttentionPrice(expectedValue,expectedValue2);
                        assertEquals(expectedValue2,actualValue);
                        break;
                }
            }
        }
    }

    @And("^I toggle \"([^\"]*)\" checkbox on \"([^\"]*)\" page$")
    public void iToggleSomeCheckBoxOnSomePage(String checkboxName, String pageName){
        switch (pageName){
            case "Dynamic MPI":
                dmpiPage.toggleOnAgreeCheckBox();
                break;
        }
    }

    @Then("^I see confirm message with (.+) on \"([^\"]*)\" page$")
    public void iSeeConfirmMessageOnDMPIPage(String msgName, String pageName){
        String expectedMessage = msgName;
        String actualMessage = "";
        switch(msgName){
            case "Nissan of Anytown USA":
                actualMessage = dmpiPage.getConfirmMessageWithDealerName();
                assertEquals(expectedMessage.trim(),actualMessage.trim());
                break;
            case "DeliveryTime":
                assertTrue(dmpiPage.verifyDeliveryPromiseTime());
        }
    }

    @Then("^I see following logs on \"([^\"]*)\" section$")
    public void iSeeConfirmMessageOnDMPIPage(String sectionName, DataTable dataTable){
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String logsName = map.get("logsName");
            String expectedLog = map.get("logs");
            String expectedLog2= "";
            String actualLog = "";
            if (StringUtils.isNotBlank(logsName)){
                switch (logsName) {
                    case "Customer Approval":
                        actualLog = svPage.getStatusLog(expectedLog);
                        break;
                    case "Customer click link":
                        actualLog = svPage.getStatusLog(expectedLog);
                        break;
                    case "Email sent":
                        actualLog = svPage.getStatusLog(expectedLog);
                        break;
                    case "SMS sent":
                        expectedLog = expectedLog.replace("{current.date} at {current.time} to {cellNumber}","");
                        expectedLog2 = phoneNumber;
                        actualLog = svPage.getStatusLog(expectedLog);
                        break;
                    case "Advisor displayed":
                        actualLog = svPage.getStatusLog(expectedLog);
                        break;
                    case "Service Advisor's Approval":
                        expectedLog = expectedLog.replace("{current.date} at {current.time}","");
                        actualLog = svPage.getStatusLog(expectedLog);
                }
            }
            if(!actualLog.contains(expectedLog) || !actualLog.contains(expectedLog2)){
                Assert.fail("<====== the actual log is "+actualLog+", test failed ======>");
            }
        }
    }

    @Then("^I should see following error message on \"([^\"]*)\" concern on \"([^\"]*)\" field on \"([^\"]*)\" page$")
    public void iVerifyErrorMsgOnConcernOnFieldOnPage(String concernName, String fieldName, String pageName, DataTable dataTable){
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String expectedErrorMsg = map.get("COMMENTS FOR CUSTOMER error message");
            String actualErrorMsg = "";
            switch (pageName){
                case "SERVICE VISIT":
                    actualErrorMsg = svPage.getConcernFieldErrorMsg(concernName, fieldName);
                    break;
            }
            assertTrue(actualErrorMsg.contains(expectedErrorMsg));
        }
    }

    @And("^I input \"([^\"]*)\" comments on \"([^\"]*)\" concern on \"([^\"]*)\" field on \"([^\"]*)\" page$")
    public void iInputCommentsOnConcernOnFieldOnPage(String comments, String concernName, String fieldName, String pageName){
        switch (pageName){
            case "SERVICE VISIT":
                svPage.inputCommentsOnConcernOnField(comments,concernName,fieldName);
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" description with \"([^\"]*)\" status on \"([^\"]*)\" page$")
    public void iSeeDescriptionWithStatusOnPage(String description, String status, String pageName){
        String expectedStatus = status;
        String actualStatus = "";
        switch (pageName){
            case "SERVICE VISIT":
                actualStatus = svPage.getPartsDescriptionStatus(description);
                break;
        }
        assertTrue(actualStatus.contains(expectedStatus));
    }

    @And("^I click \"([^\"]*)\" parts status button on \"([^\"]*)\" page$")
    public void iClickPartsStatusOnPage(String description, String pageName){
        switch(pageName){
            case "SERVICE VISIT":
                svPage.clickOnPartsStatus(description);
                break;
        }
    }

    @Then("^I see following data for \"([^\"]*)\" parts availability on \"([^\"]*)\" page$")
    public void iSeeDataPartsAvailOnpage(String description, String pageName, DataTable dataTable){
        switch (pageName){
            case "SERVICE VISIT":
                List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
                for (Map<String, String> map : maps) {
                    String fieldName = map.get("FieldName");
                    String expectedValue = map.get("Value");
                    String actualValue = "";
                    String rowNumber = fieldName.substring(fieldName.indexOf("_")+1);
                    if (StringUtils.isNotBlank(expectedValue)) {
                        if (fieldName.contains("QTY")) {
                            actualValue = svPage.getQTY(expectedValue,rowNumber);
                        } else if (fieldName.contains("PART#")) {
                            actualValue = svPage.getPartNo(expectedValue,rowNumber);
                        } else if (fieldName.contains("DESCRIPTION")) {
                            actualValue = svPage.getDescription(expectedValue,rowNumber);
                        } else if (fieldName.contains("STATUS")) {
                            actualValue = svPage.getStatus(rowNumber);
                        }
                    }
                    assertEquals(expectedValue,actualValue);
                }
                break;
        }
    }

    @When("^I click \"([^\"]*)\" icon under \"([^\"]*)\" description on \"([^\"]*)\" page$")
    public void iClickIconUnderRecommendationUnderDescriptionOnPage(String iconName, String description, String pageName){
        switch (pageName){
            case "SERVICE VISIT":
                switch (iconName){
                    case "Notes":
                        svPage.clickNotesWithDescription(description);
                        break;
                }
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" role and \"([^\"]*)\" comment on \"([^\"]*)\" page$")
    public void iSeeRoleNCommentOnPage(String role, String comment, String pageName){
        String expectedRole = role;
        String expectedComment = comment;
        String actualRole = "";
        String actualComment = "";
        switch (pageName){
            case "SERVICE VISIT":
                if(role.equals("TECHNICIAN")){
                    actualRole = svPage.getTechRole();
                    actualComment = svPage.getTechComment();
                }else if(role.equals("PARTS CLERK")){
                    actualRole = svPage.getPCRole();
                    actualComment = svPage.getPCComment();
                }
                break;
        }
        assertEquals(expectedRole,actualRole);
        assertEquals(expectedComment,actualComment);
    }

    @And("^I click \"([^\"]*)\" dropdown under \"([^\"]*)\" description on \"([^\"]*)\" page$")
    public void iClickDropDownUnderDescriptionOnPage(String option, String description, String pageName){
        switch (pageName){
            case "SERVICE VISIT":
                svPage.clickONDropDownUnderDescription(option,description);
                break;
        }
    }

    @Then("^I see following dropdown option under \"([^\"]*)\" description on \"([^\"]*)\" page$")
    public void iSeeDropDownOptionOnPage(String description, String pageName, DataTable dataTable){
        switch (pageName){
            case "SERVICE VISIT":
                String expectedDropDown = "";
                String actualDropDown = "";
                List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
                for (Map<String, String> map : maps) {
                    expectedDropDown = map.get("Dropdown_option");
                    actualDropDown = svPage.getDropDownValue(description,expectedDropDown);
                }
                assertTrue(actualDropDown.contains(expectedDropDown));
                break;
        }
    }

    @And("^I click \"([^\"]*)\" from \"([^\"]*)\" dropdown under \"([^\"]*)\" description on \"([^\"]*)\" page$")
    public void iClickOptionFromDropDownUnderDescriptionOnPage(String option, String dropDown, String description, String pageName){
        switch (pageName){
            case "SERVICE VISIT":
                switch (dropDown){
                    case "APPROVE OR DEFER":
                        svPage.clickONDropDownUnderDescription(option,description);
                        break;
                }
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" selected from \"([^\"]*)\" dropdown under \"([^\"]*)\" description on \"([^\"]*)\" page$")
    public void iSeeOptionSelectedFromDropDownUnderDescriptionOnPage(String option, String dropDown, String description, String pageName){
        switch (pageName){
            case "SERVICE VISIT":
                switch (dropDown){
                    case "APPROVE OR DEFER":
                        assertTrue(svPage.isOptionSelectedONDropDownUnderDescription(option,description));
                        break;
                }
                break;
        }
    }

    @Then("^I see image \"([^\"]*)\" on \"([^\"]*)\" section under \"([^\"]*)\" description on \"([^\"]*)\" page$")
    public void iSeeImageStatusOnSectionUnderDescriptionOnPage(String status, String section, String description, String pageName){
        switch (pageName){
            case "SERVICE VISIT":
                switch (section){
                    case "IMAGE LIBRARY":
                        if(status.equals("checked")){
                            assertTrue(svPage.isImageChecked(description));
                        }else{
                            assertTrue(!svPage.isImageChecked(description));
                        }
                        break;
                }
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" button \"([^\"]*)\" on \"([^\"]*)\" page$")
    public void iSeeButtonStatusOnPage(String buttonName, String status, String pageName){
        switch (pageName){
            case "SERVICE VISIT":
                switch(buttonName){
                    case "CONFIRM":
                        switch (status){
                            case "enabled":
                                assertTrue(svPage.isConfirmButtonEnabled(status));
                                break;
                            case "disabled":
                                assertTrue(!svPage.isConfirmButtonEnabled(status));
                                break;
                        }
                        break;
                }
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on 'SERVICE VISIT' page$")
    public void iClickButtonOnServiceVisitPage(String buttonName){
        switch (buttonName){
            case "SEND FOR APPROVAL":
                svPage.clickOnSendForApproval();
                break;
            case "STATUS LOG":
                svPage.clickOnStatusLog();
                break;
            case "PRESENT TO CUSTOMER":
                svPage.clickOnPresentToCustomer();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on 'Dynamic MPI' page$")
    public void iClickButtonOnDMPIPage(String buttonName){
        switch (buttonName){
            case "ENTER":
                dmpiPage.clickEnterOnDMPIPage();
                break;
            case "CONTINUE":
                dmpiPage.clickContinueOnDMPIPage();
                break;
            case "NEXT":
                dmpiPage.clickOnNextOnDMPI();
                break;
            case "ACCEPT ESTIMATE":
                dmpiPage.clickAcceptEstimateOnDMPIPage();
                break;
            case "Click to sign":
                dmpiPage.clickToSign();
                break;
            case "Approve":
                dmpiPage.clickApproveOnDMPI();
                break;
            case "VIEW ITEMS":
                dmpiPage.clickViewItemsOnDMPIPage();
                break;      
            case "CONFIRM":
                dmpiPage.clickConfirmOnDMPIPage();
                break;                 
        }
    }

    /*@And("^I click \"Test1\" icon on 'INSPECTION SUMMARY' page$")
    public void iClickIconOnInspectionSummaryPage(String iconName){
        switch (iconName){
            case "Test1":
                break;
        }
    }*/
}




