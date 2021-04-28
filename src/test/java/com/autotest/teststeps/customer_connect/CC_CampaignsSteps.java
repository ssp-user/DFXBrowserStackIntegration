package com.autotest.teststeps.customer_connect;

import com.automation.pages.customer_connect.CC_CampaignsPage;
import com.automation.utils.dataProvider.TestParameters;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CC_CampaignsSteps extends BaseTestSteps {

    CC_CampaignsPage ccPage = new CC_CampaignsPage();
    TestParameters tp = new TestParameters();

    @And("^I click \"([^\"]*)\" tab on 'CAMPAIGNS' page$")
    public void iClickTabOnPage(String tabName) {
        switch (tabName) {
            case "SCRIPTS":
                ccPage.clickOnScriptsTab();
                break;
            case "TASK ASSIGNMENT":
                ccPage.clickOnTaskAssignmentTab();
                break;
            case "IMPORT":
                ccPage.clickOnImportTab();
                break;
            case "CLEAR TASK":
                ccPage.clickOnClearTaskTab();
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" tab is active on 'CAMPAIGNS' page$")
    public void iSeeSomeTabActiveOnCampaignsPage(String tabName) {
        switch (tabName) {
            case "INFORMATION":
                assertTrue(ccPage.isOnTab("INFORMATION"));
                break;
            case "EMAIL":
                assertTrue(ccPage.isOnTab("Email"));
                break;
        }
    }

    @When("^I set 'Communication Type' to \"([^\"]*)\"$")
    public void iSelectCommunicationType(String communicationType) {
        ccPage.selectCommunicationType(communicationType);
    }

    @And("^I click \"([^\"]*)\" from 'Campaign List' on 'CAMPAIGNS' page$")
    public void iSelectFromListOnCampaignsPage(String campaignName) {
        ccPage.clickCampaignOnCampaignPage(campaignName);
    }

    @And("^I select \"([^\"]*)\" from \"([^\"]*)\" dropdown menu on 'CAMPAIGNS' page$")
    public void iSelectFromDropdownOnCampaignPage(String dropDownValue, String dropDown) {
        switch (dropDown) {
            case "Campaign Type":
                ccPage.selectValueFromCampaignDropDownOnCampaign(dropDownValue);
                break;
            case "Email Templates":
                ccPage.selectValueFromEmailTemplatesDropDownOnCampaign(dropDownValue);
                break;
        }
    }

    @And("^I enter \"([^\"]*)\" on \"([^\"]*)\" on 'CAMPAIGNS' page$")
    public void iInputTextOnSomeFieldOnCampaignsPage(String text, String fieldName) {
        switch (fieldName) {
            case "Campaign Name":
                ccPage.inputTextOnCampaignName(text);
                break;
            case "Campaign Description":
                ccPage.inputTextOnCampaignDescription(text);
                break;
            case "Email Subject":
                ccPage.inputTextOnEmailSubjectOnCreateScript(text);
                break;
            case "Email Body Content":
                ccPage.inputTextOnEmailBodyContent(text);
                break;
        }
    }

    @And("^I add \"([^\"]*)\" plus random last name in 'Campaign Name' field$")
    public void iAddNameOnCampaignNameField(String firstName) {
        ccPage.setCampaignName(firstName);
    }

    @And("^I add \"([^\"]*)\" in 'Campaign Description' field$")
    public void iAddDescriptionOnCampaignDescriptionField(String campaignDescription) {
        ccPage.writeCampaignDescription(campaignDescription);
    }

    @Then("^I see 'Darren' plus random last name in the 'Campaign list' on 'CAMPAIGNS' page$")
    public void iVerifyCampaignOnCampaignList() {
        if (!ccPage.verifyIfCampaignRecordCreated()) {
            Assert.fail("<====== I am not able to find campaign name '" + ccPage.campaignName + "' on CAMPAIGNS page ======>");
        }
    }

    @Then("^I don't see 'Darren' plus random last name in the 'Campaign list' on 'CAMPAIGNS' page$")
    public void iVerifyCampaignNotOnCampaignList() {
        if (!ccPage.verifyIfCampaignRecordDeleted()) {
            Assert.fail("<====== I am able to find campaign name '" + ccPage.campaignName + "' on CAMPAIGNS page, not delete yet ======>");
        }
    }

    @When("^I click 'Darren' plus random last name in the 'Campaign list' on 'CAMPAIGNS' page$")
    public void iClickCampaignOnCampaignList() {
        ccPage.clickCampaignRecord();
    }

    @Then("^I see \"([^\"]*)\" Campaign selected as default$")
    public void iVerifyCampaignSelected(String campaignType) {
        if (!ccPage.verifyCampaignSelectedAsDefault(campaignType)) {
            Assert.fail("<====== '" + campaignType + "' is not preselected, test fail ======>");
        }
    }

    @And("^I see header menu showing following status$")
    public void iVerifyHeaderMenuStatus(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String menuName = map.get("Header menu");
            String expectedStatus = map.get("Expected status");
            if (StringUtils.isNotBlank(menuName)) {
                if (menuName.equals("MESSENGER")) {
                    menuName = "Messenger";
                }
                if (!ccPage.verifyHeaderMenuStatus(menuName, expectedStatus)) {
                    Assert.fail("<====== Header menu '" + menuName + "' is not showing status '" + expectedStatus + "', test fail ======>");
                }
            }
        }
    }

    @And("^I toggle \"([^\"]*)\" to \"([^\"]*)\" under \"([^\"]*)\" section on 'CAMPAIGNS' page$")
    public void iToggleStatusUnderSectionOnPage(String fieldName, String status, String sectionName) {
        switch (sectionName) {
            case "INFORMATION":
                ccPage.toggleStatusOnField(fieldName, status);
                break;
        }
    }

    @And("^I search following BDC Agent/Manager under \"([^\"]*)\" search field on 'CAMPAIGNS' page$")
    public void iSearchBDCAgentUnderField(String fieldName, DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String agentName = map.get("Name");
            if (StringUtils.isNotBlank(agentName)) {
                switch (fieldName) {
                    case "Service Coordinators available for assignment":
                        ccPage.searchBDCAgent(agentName);
                        break;
                }
            }
        }
    }

    @And("^I upload \"([^\"]*)\" on 'CAMPAIGNS' page$")
    public void iUploadFileOnPage(String fileName) {
        ccPage.uploadCampaignFile(fileName);
    }

    @And("^I click \"([^\"]*)\" button on 'CAMPAIGNS' page$")
    public void iClickButtonOnCampaignsPage(String buttonName) {
        switch (buttonName) {
            case "New Campaign":
                ccPage.clickOnNewCampaign();
                break;
            case "Next":
                ccPage.clickOnNextOnCampaignPage();
                break;
            case "Create":
                ccPage.clickOnCreateOnCampaign();
                break;
            case "OK":
                ccPage.clickOnOKOnPopUpOnCampaign();
                break;
            case "DELETE":
                ccPage.clickDeleteOnCampaign();
                break;
            case "Save":
                ccPage.clickOnSaveOnCampaign();
                break;
            case "Apply":
                ccPage.clickOnApplyButton();
                break;
            case "Choose file":
                ccPage.clickOnChooseFile();
                break;
            case "Import":
                ccPage.clickOnImportButton();
                break;
            case "New Template":
                ccPage.clickOnNewTemplateOnCampaignPage();
                break;
            case "Preview and Test":
                ccPage.clickPreviewAndTestOnCampaignPage();
                break;
            case "Send Test Email":
                ccPage.clickSendTestEmailOnCampaignPage();
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on 'Create Script' popup window$")
    public void iClickSomethingOnCreateScriptPopup(String btnName) {
        switch (btnName) {
            case "Save":
                ccPage.clickOnSaveBtn();
                break;
        }
    }

    @Then("^\"([^\"]*)\" should be displayed under 'Email Templates' dropdown menu on 'EMAIL' tab$")
    public void iVerifySelectedOptionOnDropdown(String selectedOption) {
        switch (selectedOption) {
            case "random character":
                assertTrue("Currently selected option under Email Templates dropdown is not the 'TEST random' previously created, test failed!", ccPage.getSelectedOption().equalsIgnoreCase(tp.newCreatedEmailSubject));
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" window opened on 'CAMPAIGNS' page$")
    public void iVerifySomeWindowOpened(String windowName) {
        switch (windowName) {
            case "Preview":
                assertTrue("Send Test Email button is not displayed, test failed", ccPage.isSendTestEmailBtnDisplayed());
                break;
        }
    }

    @And("^I see \"([^\"]*)\" in 'Email Body Content' field if I created a new template$")
    public void iVerifyEmailBodyContent(String emailBodyContent) {
        if(!tp.newCreatedEmailSubject.isEmpty()) {
            assertEquals(emailBodyContent, ccPage.getEmailBodyContent());
        }
    }

    @When("^I select \"([^\"]*)\" or create a new template from \"([^\"]*)\" dropdown menu$")
    public void iSelectOrCreateEmailTemplateOnCampaignsPage(String option, String dropDown) {
        switch (dropDown) {
            case "Email Templates":
                if(ccPage.emailTemplateNotEmpty()) {
                    ccPage.selectValueFromEmailTemplatesDropDownOnCampaign(option);
                    break;
                }else {
                    ccPage.clickOnNewTemplateOnCampaignPage();
                    ccPage.inputTextOnEmailSubjectOnCreateScript("TEST random");
                    ccPage.inputTextOnEmailBodyContent("Test DFX Test Email Please Ignore");
                    ccPage.clickOnSaveBtn();
                    Assert.assertTrue("Currently selected option under Email Templates dropdown is not the 'TEST random' previously created, test failed!", ccPage.getSelectedOption().equalsIgnoreCase(tp.newCreatedEmailSubject));
                }
                break;
        }
    }

    @And("^I enter \"([^\"]*)\" on \"([^\"]*)\" on 'Preview' popup$")
    public void iEnterValueOnFieldOnPreview(String value, String fieldName) {
        switch (fieldName) {
            case "Recipient":
                ccPage.inputTextOnRecipientOnPreview(value);
                break;
            case "Email Subject":
                ccPage.inputTextOnEmailSubjectOnPreview(value);
                break;
        }
    }

}