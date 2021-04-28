package com.autotest.teststeps.customer_connect;

import com.automation.pages.customer_connect.CC_ScriptsPage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class CC_ScriptsSteps extends BaseTestSteps {

    CC_ScriptsPage csPage = new CC_ScriptsPage();

    @And("^I select \"([^\"]*)\" from \"([^\"]*)\" dropdown menu on 'SCRIPTS' page$")
    public void iSelectFromDropDownOnScriptsPage(String dropDownValue, String dropDown) {
        switch (dropDown) {
            case "Campaign Type":
                csPage.selectValueFromCampaignDropDownOnScripts(dropDownValue);
                break;
            case "Script Type":
                csPage.selectValueFromScriptTypeDropDownOnScripts(dropDownValue);
                break;
        }
    }

    @And("^I add random script name in 'Script Name' field$")
    public void iAddNameOnScriptNameField() {
        csPage.setScriptName();
    }

    @Then("^I see random script name in the 'Script list' on 'SCRIPTS' page$")
    public void iVerifyScriptOnScriptList() {
        if (!csPage.verifyIfNewScriptCreated()) {
            Assert.fail("<====== I am not able to find script name '" + csPage.scriptName + "' on SCRIPTS page ======>");
        }
    }

    @Then("^I don't see random script name in the 'Script list' on 'SCRIPTS' page$")
    public void iVerifyScriptNotOnScriptList() {
        if (!csPage.verifyIfScriptRecordDeleted()) {
            Assert.fail("<====== I am able to find script name '" + csPage.scriptName + "' on SCRIPTS page, not delete yet ======>");
        }
    }

    @When("^I click random script name in the 'Script list' on 'SCRIPTS' page$")
    public void iClickScriptOnScriptList() {
        csPage.clickScriptRecord();
    }

    @Then("^I see result by select following '-Insert a script variable-' dropDownOption$")
    public void iVerifyDropDownOnPage(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String dropDownOption = map.get("DropDownName");
            String expectedInsert = map.get("Expected Insert Result");
            if (StringUtils.isNotBlank(dropDownOption)) {
                csPage.getScriptContentBefore();
                csPage.selectValueFromInsertScriptVariable(dropDownOption);
                csPage.getScriptConentAfter();
                if (csPage.scriptContentBefore.endsWith(expectedInsert)) {
                    Assert.fail("<====== Script contents end with '" + expectedInsert + "' before insert '" + dropDownOption + "', test fail ======>");
                } else if (!csPage.scriptContentAfter.endsWith(expectedInsert)) {
                    Assert.fail("<====== Script contents not end with '" + expectedInsert + "' after insert '" + dropDownOption + "', test fail ======>");
                } else {
                    System.out.println("<====== '" + expectedInsert + "' got appended to the end of the script contents ======>");
                }
            }
        }
    }

    @Then("^I see error message \"([^\"]*)\" on 'SCRIPTS' page$")
    public void iVerifyErrorMessageOnScriptsPage(String errorMsg) {
        if (!csPage.verifyIfErrorMsgExisted(errorMsg)) {
            Assert.fail("<====== '" + errorMsg + "' is not showing up, test fail ======>");
        }
    }

    @And("^I click \"([^\"]*)\" button on 'SCRIPTS' page$")
    public void iClickButtonOnScriptsPage(String buttonName) {
        switch (buttonName) {
            case "NEW SCRIPT":
                csPage.clickOnNewScript();
                break;
            case "Create":
                csPage.clickOnCreateOnScript();
                break;
            case "OK":
                csPage.clickOnOKOnPopUpOnScript();
                break;
            case "DELETE":
                csPage.clickDeleteOnScript();
                break;
        }
    }

    @And("^I input \"(.*)\" in the 'Script Content' field on 'SCRIPTS' page$")
    public void iInputTextInScriptFieldOnScriptsPage(String content) {
        csPage.inputContentInScript(content);
    }

}