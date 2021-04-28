package com.autotest.teststeps.tech_inspection;

import com.automation.pages.customer_connect.CC_MessengerPage;
import com.automation.pages.service_dashboard.SD_ServiceVisit;
import com.automation.pages.tech_inspection.ServiceTab.*;
import com.automation.pages.tech_inspection.ServiceTab.WalkAroundTab;
import com.automation.pages.tech_inspection.dashboard.TechInspectSearchPage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TI_ServiceTabSteps extends BaseTestSteps {

    TechInspectSearchPage page = new TechInspectSearchPage();
    InspectionTab iTab = new InspectionTab();
    RecommendationTab rTab = new RecommendationTab();
    PartListTab pTab = new PartListTab();
    WalkAroundTab wTab = new WalkAroundTab();
    CC_MessengerPage cmPage = new CC_MessengerPage();
    SD_ServiceVisit svPage = new SD_ServiceVisit();
    TechCamTab tTab = new TechCamTab();

    @Then("^I should see \"([^\"]*)\" status on 'MPI Details' page$")
    public void iVerifyStatusOnMPI(String status) {
        String actualStatus = iTab.getInspectStatusOnMPIPage();
        assertEquals(status, actualStatus);
    }

    @And("^I choose the first result and click on it$")
    public void firstResult() {
        page.clickOnStatus();
    }

    @When("^I click on future attention icon to add recommend service$")
    public void recommendServiceIcon() {
        iTab.clickOnRequireFutureIcon();
    }

    @And("^I click \"([^\"]*)\" icon on \"([^\"]*)\" inspection on 'INSPECTION' tab$")
    public void iClickIconOnInspectionField(String iconColor, String inspectionName) {
        iTab.clickOnIconColorBasedOnSection(iconColor, inspectionName);
    }

    @And("^I click \"([^\"]*)\" icon on \"([^\"]*)\" concern on 'INSPECTION' tab$")
    public void iClickIconOnConcernField(String iconColor, String concernName) {
        iTab.clickOnIconColorBasedOnConcernSection(iconColor, concernName);
    }

    @And("^I click \"([^\"]*)\" concern on 'INSPECTION' tab$")
    public void iClickConcernOnTab(String concernName) {
        iTab.clickOnConernName(concernName);
    }

    @And("^I enter data set in addRecommendationField$")
    public void iEnterDataSetInAddRecommendationField(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                if (fieldName.equals("Recommendation")) {
                    rTab.setRecommendationName(value);
                } else if (fieldName.equals("OPcode")) {
                    rTab.setOPcode(value);
                } else if (fieldName.equals("Hours")) {
                    rTab.setHours(processText(value));
                } else if (fieldName.equals("LaborSale")) {
                    rTab.setLaborSale(processText(value));
                } else if (fieldName.equals("PartSale")) {
                    rTab.setPartSale(value);
                } else if (fieldName.equals("Price")) {
                    rTab.setPrice(value);
                }
            }
        }
    }

    @And("^I enter data set in addRecommendationField under \"(.*)\" section$")
    public void iEnterDataSetInAddRecommendationField(String section, DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                if (fieldName.equals("Recommendation")) {
                    rTab.setRecommendationName(value, section);
                } else if (fieldName.equals("OPcode")) {
                    rTab.setOPcode(value, section);
                } else if (fieldName.equals("Hours")) {
                    rTab.setHours(processText(value), section);
                } else if (fieldName.equals("LaborSale")) {
                    rTab.setLaborSale(processText(value), section);
                } else if (fieldName.equals("PartSale")) {
                    rTab.setPartSale(value, section);
                } else if (fieldName.equals("Price")) {
                    rTab.setPrice(value, section);
                }
            }
        }
    }

    @Then("^I should see recommendation with \"([^\"]*)\" Description with \"([^\"]*)\" hours with \"([^\"]*)\" labor with \"([^\"]*)\" parts with \"([^\"]*)\" price on 'RECOMMENDATION' tab$")
    public void iVerifyRecommendationOnRecommendationTab(String description, String hours, String labor, String parts, String price) {
        String expectedDescription = description;
        String expectedHours = hours;
        String expectedLabor = labor;
        String expectedParts = parts;
        String expectedPrice = price.indexOf(".") < 0 ? price : price.replaceAll("0*$", "").replaceAll("\\.$", "");
        String actualDescription = rTab.getDescriptionValueOnRecommendationTab(description);
        String actualHours = rTab.getHoursValueOnRecommendationTab(description);
        String actualLabor = rTab.getLaborValueOnRecommendationTab(description);
        String actualParts = rTab.getPartsValueOnRecommendationTab(description, parts);
        String actualPriceBeforeTrim = rTab.getPriceValueOnRecommendationTab(description);
        String actualPrice = actualPriceBeforeTrim.indexOf(".") < 0 ? actualPriceBeforeTrim : actualPriceBeforeTrim.replaceAll("0*$", "").replaceAll("\\.$", "");
        assertEquals(expectedDescription, actualDescription);
        assertEquals(expectedHours, actualHours);
        assertEquals(expectedLabor, actualLabor);
        assertEquals(expectedParts, actualParts);
        assertEquals(expectedPrice, actualPrice);
    }

    @Then("^I should see recommendation with \"([^\"]*)\" Description with \"([^\"]*)\" hours with \"([^\"]*)\" type with \"([^\"]*)\" rate with \"([^\"]*)\" labor with \"([^\"]*)\" parts with \"([^\"]*)\" price on 'RECOMMENDATION' tab$")
    public void iVerifyAllFieldsOnRecommendationTab(String description, String hours, String laborType, String laborRate, String labor, String parts, String price) {
        String expectedPrice = price.indexOf(".") < 0 ? price : price.replaceAll("0*$", "").replaceAll("\\.$", "");

        String actualDescription = rTab.getDescriptionValueOnRecommendationTab(description);
        String actualHours = rTab.getHoursValueOnRecommendationTab(description);
        String actualLaborType = rTab.getLaborTypeValueOnRecommendationTab(description);
        String actualLaborRate = rTab.getLaborRateValueOnRecommendationTab(description);
        String actualLabor = rTab.getLaborValueOnRecommendationTab(description);
        String actualParts = rTab.getPartsValueOnRecommendationTab(description, parts);
        String actualPriceBeforeTrim = rTab.getPriceValueOnRecommendationTab(description);
        String actualPrice = actualPriceBeforeTrim.indexOf(".") < 0 ? actualPriceBeforeTrim : actualPriceBeforeTrim.replaceAll("0*$", "").replaceAll("\\.$", "");

        assertEquals(description, actualDescription);
        assertEquals(hours, actualHours);
        assertEquals(laborType, actualLaborType);
        assertEquals(laborRate, actualLaborRate);
        assertEquals(labor, actualLabor);
        assertEquals(parts, actualParts);
        assertEquals(expectedPrice, actualPrice);
    }

    @When("^I update \"([^\"]*)\" in \"([^\"]*)\" with \"([^\"]*)\" Description on 'RECOMMENDATION' tab$")
    public void iUpdateRecommendationOnRecommendationTab(String value, String sectionName, String description) {
        switch (sectionName) {
            case "Labor Rate":
                rTab.setLaborRateValueOnRecommendationTab(description, value);
                break;
            case "hours":
                rTab.setHoursValueOnRecommendationTab(description, value);
                break;
            case "labor":
                rTab.setLaborValueOnRecommendationTab(description, value);
                break;
            case "parts":
                rTab.setPartsValueOnRecommendationTab(description, value);
                break;
            case "Labor Type":
                rTab.setLaborTypeOnRecommendationTab(description, value);
                break;
        }
    }

    @And("^I should see \"([^\"]*)\" Description with \"([^\"]*)\" labor with \"([^\"]*)\" price in 'recommendations' section on 'APPROVED SERVICES' tab$")
    public void iVerifyPriceOnReApprovedServiceTab(String description, String labor, String price) {
        String expectedDescription = description;
        String expectedLabor = labor;
        String expectedPrice = price;
        String actualDescription = rTab.getDescriptionValueOnArrovedServiceTab(description);
        String actualLabor = rTab.getLaborValueOnArrovedServiceTab(description);
        String actualPrice = rTab.getPriceValueOnArrovedServiceTab(description);
        assertEquals(expectedDescription, actualDescription);
        assertEquals(expectedLabor, actualLabor);
        assertEquals(expectedPrice, actualPrice);
    }

    @And("^I should see \"([^\"]*)\" flag with \"([^\"]*)\" with \"([^\"]*)\" Description on 'RECOMMENDATION' tab$")
    public void iSeeFlagStatusWithDescription(String flagName, String status, String description) {
        String expectedStatus = status;
        String actualStatus = "";
        switch (flagName) {
            case "Parts Requested":
                actualStatus = rTab.checkIfPartsRequestedToggled(description);
                break;
        }
        assertEquals(expectedStatus, actualStatus);
    }


    @When("^I click \"([^\"]*)\" toggle with \"([^\"]*)\" Description on 'APPROVED SERVICES' tab$")
    public void i_click_something_toggle_with_something_description_on_approved_services_tab(String iconColor, String desciption) {
        switch (iconColor) {
            case "green":
                rTab.clickOnIconBasedONDescritionOnApprovedService(desciption);
                break;
        }
    }

    @When("^I click \"([^\"]*)\" button with \"([^\"]*)\" Description on 'RECOMMENDATION' page$")
    public void iClickOnButtonWithDescriptionOnRecommendation(String buttonName, String description) {
        switch (buttonName) {
            case "APPROVED":
                rTab.clickOnApprovedAssociatedWithDescription(description);
                break;
            case "In Stock":
                rTab.clickOnInStockAssociatedWithDescription(description);
                break;
            case "Not In Stock":
                pTab.clickOnNotInStockToggleAssociatedWithDescription(description);
                break;
            case "DEFERRED":
                pTab.clickOnDeferredAssociatedWithDescription(description);
                break;
        }
    }

    @And("^I click \"([^\"]*)\" on 'footbar' tab on 'MPI Details' page$")
    public void iClickSomethingOnFooter(String buttonName) {
        rTab.clickOnShowTotals();
    }

    @Then("^I should see \"([^\"]*)\" with \"([^\"]*)\" on 'footbar' tab$")
    public void iSeePriceOnFooter(String columnName, String price) {
        String expectedPrice = price;
        String actualPrice = "";
        switch (columnName) {
            case "RECOMMENDED":
                actualPrice = rTab.getRecommendedPriceOnFooter();
                break;
            case "APPROVED":
                actualPrice = rTab.getApprovedPriceOnFooter();
                break;
            case "DEFERRED":
                actualPrice = rTab.getDeferredPriceOnFooter();
                break;
        }
        assertEquals(expectedPrice, actualPrice.replaceAll(" ", ""));
    }

    @And("^I launch inspection tab in the technician inspection page$")
    public void launchInspectionTab() {
        iTab.isInspectionTabLoaded();
    }

    @And("^I am add comment on recommend service$")
    public void addComment() {
        iTab.addComment();
    }

    @And("^I am taking photo for recommend service$")
    public void takePhoto() {
        iTab.clickOnCamera();
        iTab.takeCameraShot();
        iTab.closeTheCamera();
    }

    @And("^I click on save button$")
    public void saveRecommendation() {
        iTab.saveRecommendation();
        iTab.saveAllRecommendation();
    }

    @Then("^I click on recommendation to view the recommend service i've saved$")
    public void reviewRecommendation() {
        rTab.clickOnRecommendationTab();
        String expectedTechComment = "This is a test comment -- Darren";
        String expectedCustomerComment = "This is a test comment -- Darren";
        String actualTechComment = rTab.getTechCommentsFieldText();
        String actualCustomerComment = rTab.getCustomerCommentsFieldText();
        assertEquals(expectedTechComment, actualTechComment);
        assertEquals(expectedCustomerComment, actualCustomerComment);
    }

    @And("I choose the result with non completed status$")
    public void clickNoneCompletedStatus() {
        page.clickOnNoneCompletedStatus();

    }

    @And("I update the EXTERIOR section$")
    public void updateExterior() {
        iTab.bulkUpdateOK(1);
    }

    @And("I update the INTERIOR section$")
    public void updateInterior() {
        iTab.bulkUpdateOK(2);
    }

    @And("I update the UNDER HOOD section$")
    public void updateUnderHood() {
        iTab.bulkUpdateOK(3);
    }

    @And("I update the FLUIDS section$")
    public void updateFluids(DataTable dataTable) throws Throwable {
        iTab.bulkUpdateOK(4);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                switch (fieldName) {
                    case "coolantDegree":
                        iTab.selectCoolant(processText(value));
                        break;
                }
            }
        }
    }

    @And("I update the UNDER VEHICLE section$")
    public void updateUnderVehicle() {
        iTab.bulkUpdateOK(5);
    }

    @And("I update the TIRES section$")
    public void updateTires(DataTable dataTable) throws Throwable {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                switch (fieldName) {
                    case "leftFrontTireIncoming":
                        iTab.selectTireIncomingPsi(1, processText(value));
                        break;
                    case "rightFrontTireIncoming":
                        iTab.selectTireIncomingPsi(2, processText(value));
                        break;
                    case "rightRearTireIncoming":
                        iTab.selectTireIncomingPsi(3, processText(value));
                        break;
                    case "leftRearTireIncoming":
                        iTab.selectTireIncomingPsi(4, processText(value));
                        break;
                    case "leftFrontTireAdjusted":
                        iTab.selectTireAdjustedPsi(1, processText(value));
                        break;
                    case "rightFrontTireAdjusted":
                        iTab.selectTireAdjustedPsi(2, processText(value));
                        break;
                    case "rightRearTireAdjusted":
                        iTab.selectTireAdjustedPsi(3, processText(value));
                        break;
                    case "leftRearTireAdjusted":
                        iTab.selectTireAdjustedPsi(4, processText(value));
                        break;
                    case "leftFrontTireTreadDepth":
                        iTab.selectTireTreadDepth(1, processText(value));
                        break;
                    case "rightFrontTireTreadDepth":
                        iTab.selectTireTreadDepth(2, processText(value));
                        break;
                    case "rightRearTireTreadDepth":
                        iTab.selectTireTreadDepth(3, processText(value));
                        break;
                    case "leftRearTireTreadDepth":
                        iTab.selectTireTreadDepth(4, processText(value));
                        break;
                    case "TorqueSpecTreadDepth":
                        iTab.selectTorqueSpec(processText(value));
                        break;
                }
            }
        }
        iTab.bulkUpdateOK(6);
    }

    @And("I update the BRAKE CONDITION section$")
    public void updateBrakeCondition() {
        iTab.bulkUpdateOK(7);
    }

    @And("I update the COMMENTS section$")
    public void updateComments() {
        iTab.bulkUpdateOK(7);
    }

    @And("I update the ADDITIONAL REPAIRS REQUIRED section$")
    public void updateAdditionalRepairRequired() {
        iTab.bulkUpdateOK(8);
    }

    @And("I save$")
    public void iSaves() {
        iTab.saveAllRecommendation();
    }

    @And("I mark completed$")
    public void iMarkCompleted() {
        iTab.clickOnMarkCompleted();
    }

    @And("^I 'Mark Section All OK' on 'MPI Details' page$")
    public void iMarkSectionAllOK() {
        iTab.markSectionAllOK();
    }

    @Then("I should see status as completed$")
    public void iReviewStatus() {
        String expectedStatus = "COMPLETED";
        String actualStatus = page.getInspectionStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Then("^I should see \"([^\"]*)\" title on popup$")
    public void iVerifyTitleOnPopUp(String titleName) {
        String expectedTitle = titleName;
        String actualTitle = "";
        switch (titleName) {
            case "Multi Point Inspection Report":
                actualTitle = rTab.getViewHistoryPopUpTitle();
                break;
            case "Update History":
                actualTitle = rTab.getStatusLogPopUpTitle();
                break;
        }
        assertThat(actualTitle, containsString(expectedTitle));
    }

    @When("^I click \"([^\"]*)\" button on \"([^\"]*)\" popup$")
    public void iClickSomethingOnPopup(String buttonName, String popUpName) {
        switch (popUpName) {
            case "View History":
                switch(buttonName){
                    case "close":
                        rTab.closeViewHistoryPopUp();
                        break;
                }
                break;
            case "Status Log":
                switch(buttonName){
                    case "close":
                        rTab.closeStatusLogPopUp();
                        break;
                }
                break;
            case "Customer Contact Information":
                switch(buttonName){
                    case "SEND":
                        svPage.clickOnSendOnCustomerContactInformation();
                        break;
                }
                break;
        }
    }

    @When("^I click \"([^\"]*)\" button on 'Customer Details' popup$")
    public void iClickBtnOnCustomerDetailsPopup(String btnName){
        switch (btnName) {
            case "Save":
            cmPage.clickOnSaveOnCustomerDetail();
            break;
        }
    }

    @Then("^I should see \"([^\"]*)\" on link$")
    public void iVerifyExternalLink(String text) {

        String expectedText = "";
        String actualText = "";
        expectedText = "PDF";
        actualText = rTab.getViewMenuPopupURL();
        Assert.assertThat(actualText, containsString(expectedText));
    }

    @Then("^I should see \"([^\"]*)\" icon on \"([^\"]*)\" page$")
    public void iVerifyIconOnPage(String iconName, String pageName) {
        String expectedResult = "";
        String actualResult = "";
        switch (iconName) {
            case "CAMERA VIEW":
                TechCamTab tTab = new TechCamTab();
                expectedResult = "Icon shows";
                actualResult = tTab.verifySwitchCameraViewIconShow();
                break;
        }
        assertEquals(expectedResult, actualResult);
    }

    @Then("^I should see \"([^\"]*)\" message on \"([^\"]*)\" page$")
    public void iSeeMessageOnPage(String message, String pageName) {
        String expectedResult = "message show";
        String actualResult = "";
        TechCamTab tTab = new TechCamTab();
        actualResult = tTab.verifyMessageOnCamera(message);
        assertEquals(expectedResult, actualResult);
    }

    @And("^I click \"([^\"]*)\" icon on \"([^\"]*)\" page \"([^\"]*)\" times$")
    public void iClickIconONPageXTimes(String iconName, String pageName, String times) {
        switch (iconName) {
            case "CAPTURE":
                TechCamTab tTab = new TechCamTab();
                tTab.clickOnCapture(times);
                break;
        }

    }

    @And("^I delete \"([^\"]*)\" photos on \"([^\"]*)\" page$")
    public void iDeletePhone(String times, String pageName) {
        TechCamTab tTab = new TechCamTab();
        switch (pageName) {
            case "Tech Cam":
                tTab.deletePhotoOnTechCam(times);
                break;
            case "Inspection Detail":
                tTab.deletePhotoOnInspectionDetail(times);
                break;
        }
    }

    @Then("^I should see \"([^\"]*)\" photo left on \"([^\"]*)\" page$")
    public void iVerifyPhotoLeft(String noOfPhoto, String pageName) {
        TechCamTab tTab = new TechCamTab();
        String expectedNoOfPhoto = noOfPhoto;
        String actualNoOfPhoto = "";
        switch (pageName) {
            case "Tech Cam":
                actualNoOfPhoto = tTab.getPhotoNumberOnTechCamPage();
                break;
            case "Inspection Detail":
                actualNoOfPhoto = tTab.getPhotoNumberOnInspectionDetailPage();
                break;
        }
        assertEquals(expectedNoOfPhoto, actualNoOfPhoto);
    }

    @Then("^I should see \"([^\"]*)\" recommendation attached to the photo on \"([^\"]*)\" page$")
    public void iSeeAttchmentOnThePhoto(String message, String pageName) {
        TechCamTab tTab = new TechCamTab();
        String expectedAttachmentMsg = message;
        String actualAttachmentMsg = "";
        switch (pageName) {
            case "Inspection Detail":
                actualAttachmentMsg = tTab.verifyRecommendationToThePhoto(message);
                break;
            case "Tech Cam":
                actualAttachmentMsg = tTab.verifyRecommendationToThePhotoOnTechCamPage(message);
                break;
        }
        assertEquals(expectedAttachmentMsg, actualAttachmentMsg);
    }

    @And("^I add attachment \"([^\"]*)\" recommendation to the photo on \"([^\"]*)\" page$")
    public void iSelectAttachementToThePhoto(String message, String pageName) {
        TechCamTab tTab = new TechCamTab();
        tTab.attachREcommendationToThePhoto(message);
    }


    @And("^I input \"([^\"]*)\" on \"([^\"]*)\" field under \"([^\"]*)\" Description on \"([^\"]*)\" page$")
    public void iUpdateTextOnFieldOnPage(String text, String textField, String description, String pageName) {
        switch (pageName) {
            case "Inspection Detail":
                switch (textField) {
                    case "Internal Comment":
                        rTab.setInternalComments(text);
                        break;
                    case "Customer Comment":
                        rTab.setCustomerComments(text);
                        break;
                }
                break;
            case "RECOMMENDATION":
                switch (textField) {
                    case "Parts Clerk Comments":
                        rTab.setPartsClerkComments(text, description);
                        break;
                }
                break;
        }
    }

    @And("^I input \"([^\"]*)\" in \"([^\"]*)\" field under \"([^\"]*)\" section on 'Inspection Detail' page$")
    public void iUpdateTextOnFieldOnInspectionPage(String text, String textField, String section) {
        switch (textField) {
            case "Internal Comment":
                rTab.setInternalComments(text,section);
                break;
            case "Customer Comment":
                rTab.setCustomerComments(text,section);
                break;
        }

    }


    @And("^I should see \"([^\"]*)\" on \"([^\"]*)\" field on \"([^\"]*)\" section with \"([^\"]*)\" Description on \"([^\"]*)\" tab")
    public void iSeeSomethingOnSomeFieldWIthSomeDescriptionOnSomePage(String commentText, String commentField, String sectionName, String description, String tabName) {
        String expectedComment = commentText;
        String actualComment = "";
        switch (sectionName) {
            case "title":
                actualComment = rTab.getCommentsValueONRecommendationTabOnTitleSection(description, commentField);
                break;
            case "description":
                actualComment = rTab.getCommentsValueONRecommendationTabOnDescriptionSection(description, commentField);
                break;
        }
        assertEquals(expectedComment, actualComment);
    }

    @And("^I input \"([^\"]*)\" on \"([^\"]*)\" field on \"([^\"]*)\" section with \"([^\"]*)\" Description on \"([^\"]*)\" tab$")
    public void iInputSomethingOnSomeFieldONSomeSectionWithSomeDescriptionOnSomeTab(String commentText, String commentField, String sectionName, String description, String tabName) {
        switch (sectionName) {
            case "title":
                rTab.setCommentsValueOnRecommendationTabOnTitleSection(description, commentField, commentText);
                break;
            case "description":
                rTab.setCommentsValueOnRecommendationTabOnDescriptionSection(description, commentField, commentText);
                break;
        }
    }

    @When("^I click \"([^\"]*)\" button with \"([^\"]*)\" Description on \"([^\"]*)\" tab$")
    public void iClickButtonOnSomeDescriptionOnSomeTab(String buttonName, String description, String tabName) {
        switch (tabName) {
            case "RECOMMENDATION":
                switch (buttonName) {
                    case "PARTS LIST":
                        rTab.clickONPartLIstAssociatedWithDescription(description);
                        break;
                }
                break;
        }
    }

    @And("^I enter data set in addPartsGridField$")
    public void iEnterDataSetInAddPartsGridField(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            String rowNumber = fieldName.substring(fieldName.indexOf("_") + 1);
            if (StringUtils.isNotBlank(value)) {
                if (fieldName.contains("QTY")) {
                    pTab.setQTY(value, rowNumber);
                } else if (fieldName.contains("B.O.")) {
                    pTab.setBO(value, rowNumber);
                } else if (fieldName.contains("Part#")) {
                    pTab.setPart(processText(value), rowNumber);
                } else if (fieldName.contains("Description")) {
                    pTab.setDescription(processText(value), rowNumber);
                } else if (fieldName.contains("Bin/Shelf")) {
                    pTab.setBin(value, rowNumber);
                } else if (fieldName.contains("O.H.")) {
                    pTab.setOH(value, rowNumber);
                } else if (fieldName.contains("Cost")) {
                    pTab.setCost(value, rowNumber);
                } else if (fieldName.contains("LIST")) {
                    pTab.setList(value, rowNumber);
                } else if (fieldName.contains("Sale")) {
                    pTab.setSale(value, rowNumber);
                } else if (fieldName.contains("ExtSale")) {
                    pTab.setExtSale(value, rowNumber);
                }
            }
        }
    }

    @And("^I should see following data in 'PARTS LIST' tab$")
    public void iShouldSeeFollowingDataInPartsList(DataTable dataTable) throws Throwable {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            String rowNumber = fieldName.substring(fieldName.indexOf("_") + 1);
            String expectedResult = value;
            String actualResult = "";
            if (StringUtils.isNotBlank(value)) {
                if (fieldName.contains("Type")) {
                    actualResult = pTab.getTypeOnPartsList(rowNumber);
                } else if (fieldName.contains("QTY")) {
                    actualResult = pTab.getQTYOnPartsList(rowNumber);
                } else if (fieldName.contains("Description")) {
                    actualResult = pTab.getDescriptionOnPartsList(rowNumber);
                } else if (fieldName.contains("Status")) {
                    actualResult = pTab.getStatusOnPartsList(rowNumber);
                } else if (fieldName.contains("ExtSale")) {
                    actualResult = pTab.getExtSaleOnPartsList(rowNumber);
                } else if (fieldName.contains("Sale")) {
                    actualResult = pTab.getSaleOnPartsList(rowNumber);
                }
            }
            assertEquals(expectedResult, actualResult);
        }
    }

    @And("^I update parts in 'PARTS LIST' tab$")
    public void iUpdatePartsInPartsListTab(DataTable dataTable)  {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            String rowNumber = fieldName.substring(fieldName.indexOf("_") + 1);
            if (StringUtils.isNotBlank(value)) {
                if (fieldName.contains("QTY")) {
                    pTab.setQTYOnPartsList(value, rowNumber);
                } else if (fieldName.contains("Description")) {
                    pTab.setDescriptionOnPartsList(value, rowNumber);
                } else if (fieldName.contains("Status")) {
                    pTab.setStatusOnPartsList(value, rowNumber);
                } else if (fieldName.contains("Sale")) {
                    pTab.setSaleOnPartsList(value, rowNumber);
                }
            }
        }
    }

    @Then("^I should see \"([^\"]*)\" with \"([^\"]*)\" on \"([^\"]*)\" tab$")
    public void iShouldSeePriceWIthStringOnSomeTab(String price, String section, String tabName) {
        String expectedPrice = price;
        String actualPrice = "";
        switch (tabName) {
            case "Parts Grid":
                switch (section) {
                    case "Total":
                        actualPrice = pTab.getTotalPriceForPartsGrid(price);
                        break;
                }
                break;
            case "PARTS LIST":
                switch (section) {
                    case "Total * Doesn’t include MPP pricing":
                        actualPrice = pTab.getTotalPriceForPartsGridOnPartsGridTab(price);
                        break;
                }
                break;
        }
        assertEquals(expectedPrice, actualPrice);
    }

    @When("^I click \"([^\"]*)\" button on \"([^\"]*)\" tab$")
    public void iClickSomeButtonOnSomeTab(String buttonName, String tabName) {
        switch (tabName) {
            case "Parts Grid":
                switch (buttonName) {
                    case "SAVE and CLOSE":
                        pTab.clickOnSaveAndClose();
                        break;
                    case "Complete and Close":
                        pTab.clickOnCompleteAndClose();
                        break;
                }
                break;
            case "PARTS LIST":
                switch (buttonName) {
                    case "Complete and Close":
                        pTab.clickOnCompleteAndCloseOnPartsList();
//                        pTab.clickOnCompleteAndCloseBtnOnPartsList();
                        break;
                }
                break;
            case "RECOMMENDATION":
                switch (buttonName) {
                    case "Parts Request Completed":
                        rTab.clickONPartsRequestCompletedOnRecommendationTab();
                        break;
                }
                break;
            case "WALK AROUND":
                switch (buttonName) {
                    case "Save":
                        wTab.clickOnSaveONWalkAround();
                        break;
                }
                break;
        }
    }

//    @When("^I click \"([^\"]*)\" button in 'Finalize RO' tab$")
//    public void iClickButtonInFinalizeRO(String buttonName){
//              WiAdvisorFinalizeROTab page = new WiAdvisorFinalizeROTab();
//                switch (buttonName){
//                    case "Complete":
//                        page.clickOnCompleteFinalizeRO();
//                        break;
//                }
//
//    }

    @And("^I should see \"([^\"]*)\" toggle is \"([^\"]*)\" with \"([^\"]*)\" on 'RECOMMENDATION' tab$")
    public void iShouldSeeToggleStatusWithDescription(String toggleIcon, String toggleStatus, String description) {
        String expectedToggleStatus = toggleStatus;
        String actualToggleStatus = "";
        switch (toggleIcon) {
            case "Not In Stock":
                actualToggleStatus = pTab.getNotInStockToggleStatusAssociatedWithDescription(description);
                break;
            case "See Parts List Tab":
                actualToggleStatus = pTab.getSeePartsListStatusAssociatedWithDescription(description);
                break;
        }
        assertEquals(expectedToggleStatus, actualToggleStatus);
    }

    @And("^I select \"([^\"]*)\" in dropdown under \"([^\"]*)\" button with \"([^\"]*)\" Description$")
    public void iSelectSomethingUnderButton(String dropDownSelection, String buttonName, String description) {
        switch (buttonName) {
            case "DEFERRED":
                pTab.clickOnDeferredSelectionAssociatedWithDescription(dropDownSelection, description);
                break;
        }
    }

    @Then("^I should see \"([^\"]*)\" button is \"([^\"]*)\" on 'MPI Details' page$")
    public void iShouldSeeSomeButtonOnMPIPage(String buttonName, String status) {
        String expectedStatus = status;
        String actualStatus = "";
        switch (buttonName) {
            case "Revise Inspection":
                actualStatus = rTab.verifyReadyForProcessingStatus();
                break;
        }
        assertEquals(expectedStatus, actualStatus);
    }

    @And("^I update \"([^\"]*)\" in COMMENTS with \"([^\"]*)\" Description on 'WALK AROUND' tab$")
    public void iUpdateSomeCommentsWithSomeDescriptionOnTab(String comments, String description) {
        WalkAroundTab wTab = new WalkAroundTab();
        wTab.setCommentsWithDescription(comments, description);
    }

    @Then("^I should see \"([^\"]*)\" in COMMENTS with \"([^\"]*)\" Description on 'WALK AROUND' tab$")
    public void iVerifySomeCommentsWithDescriptionOnTab(String comments, String description) throws Throwable {
        String expectedComments = comments;
        String actualComments = wTab.verifyCommentsWithDescription(description);
        assertEquals(expectedComments, actualComments);
    }

    @And("^I click \"([^\"]*)\" icon on 'Inspection Detail' page$")
    public void iClickIconOnInspectionDetailPage(String iconName) {
        switch (iconName) {
            case "plus":
                rTab.clickOnAddRecommendationIcon();
                break;
            case "Camera":
                tTab.clickOnCameraIconFromInspectionDetail();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" icon under \"([^\"]*)\" section on 'Inspection Detail' page$")
    public void iClickIconUnderSectionOnInspectionDetailPage(String iconName, String section) {
        switch (iconName) {
            case "plus":
                rTab.clickOnAddRecommendationIcon(section);
                break;
            case "Camera":
                tTab.clickOnCameraIconFromInspectionDetail();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on 'Inspection Detail' page$")
    public void iClickButtonOnInspectionDetailPage(String buttonName) {
        switch (buttonName) {
            case "Save":
                rTab.clickOnSave();
                break;
            case "Parts Requested":
                rTab.clickOnRequestParts();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button under \"([^\"]*)\" section on 'Inspection Detail' page$")
    public void iClickButtonUnderSectionOnInspectionDetailPage(String buttonName, String section) {
        switch (buttonName) {
            case "Save":
                rTab.clickOnSave(section);
                break;
            case "Parts Requested":
                rTab.clickOnRequestParts(section);
                break;
        }
    }

    @Then("^I expect click \"([^\"]*)\" button on 'MPI Details' pages within \"([^\"]*)\" seconds$")
    public void iExpectedSavebuttonTakeSeconds(String buttonName, String expectedSec) {
        if (expectedSec.equals("FIVE")) {
            expectedSec = "5";
        }
        switch (buttonName) {
            case "Save":
                iTab.verifySaveAllRecommendationTime(expectedSec);
                break;
        }
    }

    @Then("^I expect click \"([^\"]*)\" icon on 'MPI Details' pages within \"([^\"]*)\" seconds$")
    public void iExpectedClickIconTakeSeconds(String iconName, String expectedSec) {
        if (expectedSec.equals("FIVE")) {
            expectedSec = "5";
        }
        switch (iconName) {
            case "vehicle queue":
                iTab.verifyClickOnVehicleQueueIconTime(expectedSec);
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on 'MPI Details' page$")
    public void iClickButtonOnMPIDetailPage(String buttonName) {
        switch (buttonName) {
            case "Save":
                iTab.saveAllRecommendation();
                break;
            case "Mark Completed":
                iTab.clickOnMarkCompleted();
                break;
            case "SHOW":
                iTab.clickShow();
                break;
            case "HIDE":
                iTab.clickHide();
                break;
            case "RECOMMENDATION":
                iTab.clickOnRecommendationTab();
                break;
            case "Mark Reviewed":
                iTab.markReviewClick();
                break;
            case "Confirm":
                iTab.clickOnConfirm();
                break;
            case "Ready for processing":
                iTab.clickOnReadyForProcessing();
                break;
            case "View History":
                iTab.clickOnViewHistory();
                break;
            case "Status Log":
                iTab.clickOnStatusLog();
                break;
            case "View Menu":
                iTab.clickOnViewMenu();
                break;
            case "APPROVED SERVICES":
                iTab.clickOnApprovedServicesTab();
                break;
            case "PARTS LIST":
                iTab.clickOnPartsListTab();
                break;
            case "WALK AROUND":
                iTab.clickOnWalkAroundTab();
                break;
            case "Revise Inspection":
                iTab.clickOnReviseInspection();
                break;
            case "INSPECTION":
                iTab.clickOnInspectionTab();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on 'RECOMMENDATION' page$")
    public void iClickButtonOnRecommendation(String buttonName) {
        switch (buttonName) {
            case "Parts Request Completed":
                rTab.clickOnPartsRequestCompleted();
//                rTab.clickOnPartsRequestCompletedBtn();
//                rTab.clickONPartsRequestCompletedOnRecommendationTab();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" icon on 'Tech Cam' page$")
    public void iClickIconOnTechCamPage(String iconName) {
        switch (iconName) {
            case "close":
                tTab.clickOnCloseIcon();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" icon on 'MPI Details' page$")
    public void iClickIconOnMPIDetailPage(String iconName) {
        switch (iconName) {
            case "vehicle queue":
                iTab.clickOnVehicleQueueIcon();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on 'PARTS LIST' page$")
    public void iClickButtonOnPage(String buttonName) {
        switch (buttonName) {
            case "Save":
                pTab.clickSaveOnPartsList();
                break;
        }
    }
}
