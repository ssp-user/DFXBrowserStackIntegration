package com.autotest.teststeps.wiadvisor;

import com.automation.pages.wiadvisor.*;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import com.automation.utils.otherUtils.CommonMethods;

public class WIAdvisorBuildROSteps extends BaseTestSteps {


    private static Logger log = Logger.getLogger(WIAdvisorBuildROSteps.class.getName());

    WiAdvisorBuildROTab bRO = new WiAdvisorBuildROTab();

    private static ArrayList<String> preselectedFactoryRequiredItems = null;
    private static ArrayList<String> selectedTriageItemList = new ArrayList<String>();

    private static boolean sTtriage = true;

    @And("^I enter \"([^\"]*)\" comments under \"([^\"]*)\" section under \"([^\"]*)\" tab$")
    public void iEnterCommentsUnderSectionUnderTab(String comments, String sectionName, String tabName) {
        switch (tabName) {
            case "Build RO":
                switch (sectionName) {
                    case "General Comments":
                        bRO.updateCommentsUnderGeneralComments(comments);
                        break;
                }
                break;
        }
    }

    @And("^I enter \"([^\"]*)\" comments under \"([^\"]*)\" section in 'Build RO' tab$")
    public void iEnterCommentsUnderSectionInBuildROTab(String comments, String sectionName) {
        switch (sectionName) {
            case "General Comments":
                bRO.updateCommentsUnderGeneralComments(comments);
                //Clear service comment as it is causing error
                bRO.clearServiceComment();
                break;
        }
    }


    @And("^I select \"(.*)\" for all dropdown under 'Selected Services' in 'Build RO' tab$")
    public void iUpdateLaborTypeInBuildROTab(String type) {
        int num = 1;
        if (type.equalsIgnoreCase("Customer Pay")) {
            num = 1;
        }
        bRO.selectLaborType(num);
    }

    @And("^I \"([^\"]*)\" \"([^\"]*)\" under \"([^\"]*)\"$")
    public void iCheckItemUnderSection(String checkStatus, String checkItem, String sectionName) {
        switch (sectionName) {
            case "Factory Required Maintenance":
                bRO.checkFactoryRequiredMaintenance(checkStatus, checkItem, sectionName);
                break;
        }
    }

    @And("^I \"([^\"]*)\" \"([^\"]*)\" under \"([^\"]*)\" section$")
    public void iCheckItemUnderTheSection(String check, String checkItem, String sectionName) {
        switch (sectionName) {
            case "Factory Required Maintenance":
//                bRO.scrollPageDown(300);
                bRO.checkOnCheckBoxInFRMaintence(check, checkItem);
                break;
            case "Dealer Recommended Maintenance":
                bRO.checkOnCheckBoxInDRMaintence(check, checkItem);
                break;
        }
    }

    @And("^I set CheckBoxs under 'Factory Required Maintenance' section$")
    public void iCheckItemUnderTheSection(DataTable dataTable) {
//        bRO.scrollPageDown(300);
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                bRO.checkOnCheckBoxInFRMaintence(value, fieldName);
            }
        }
    }

    @And("^I accept Pre-Selected or \"(.*)\" \"(.*)\" in 'Factory Required Maintenance' section if No Pre-selected$")
    public void iAcceptPreSelectedOrInFRManintenTab(String checks, String checkItem) {
        preselectedFactoryRequiredItems = bRO.getSelectedFactoryRequiredItems();
        if (preselectedFactoryRequiredItems.size() > 0) {
            System.out.println(" Pre-Selected Factory Item exist  ");
        } else {
            bRO.checkOnCheckBoxInFRMaintence(checks, checkItem);
        }

    }

    @And("^I accept Pre-Selected or \"(.*)\" \"(.*)\" in 'Triage Item' section if exist and No Pre-selected$")
    public void iAcceptPreSelectedOrInTriage(String checks, String checkItem) {
        selectedTriageItemList = bRO.getSelectedTriageItems();
        if (selectedTriageItemList.size() > 0) {
            System.out.println(" Pre-Selected Triage Items exist  ");
        } else if (bRO.getTtriageItemTextElements().size() == 0) {
            sTtriage = false;
            System.out.println("   No Triage Items exist, skip to select it  ");
        } else if ((selectedTriageItemList.size() == 1) && (selectedTriageItemList.get(0).equals("None===Ttriage"))) {
            sTtriage = false;
        } else {
            bRO.checkOnTriageItems(checks, checkItem);
        }
    }

    @And("^I random select \"([^\"]*)\" items under 'Factory Required Maintenance' section$")
    public void iRandomSelectItemsUnderFRMaintence(int num) {
//          ArrayList<String>  selectedFactoryRequiredItemList = null;
//          selectedFactoryRequiredItemList
        bRO.randomChooseItemsInFRMaintence(num);

    }

    @Then("^I verify selected items under 'Factory Required Maintenance' section in BuildRO Page$")
    public void iverifyItemStatusUnderFRMSection() {
        String[] performServiceItemTextList = bRO.getPerformServiceTextString();
        preselectedFactoryRequiredItems = bRO.getSelectedFactoryRequiredItems();
        Boolean match = CommonMethods.isServiceItemMatchSelectedItem(preselectedFactoryRequiredItems, performServiceItemTextList);
//        Assert.assertTrue(match);
        if (!match) {
            log.error(" The selectedFactoryRequired Items are not match the Items in perform Service Item ist");
        }

    }

    @Then("^I verify selected items under 'Triage Item' section in BuildRO Page$")
    public void iverifyItemStatusUnderTriagesection() {
        if (sTtriage) {
            selectedTriageItemList = bRO.getSelectedTriageItems();
            String[] performServiceItemTextList = bRO.getPerformServiceTextString();
            Boolean match = CommonMethods.isServiceItemMatchSelectedItem(selectedTriageItemList, performServiceItemTextList);
//        Assert.assertTrue(match);
            if (!match) {
                log.error(" The Triage Items are not match the Items in perform Service Item ist");
            }
        }

    }

    @And("^I \"([^\"]*)\" \"([^\"]*)\" under 'Factory Required Maintenance' section$")
    public void iCheckItemUnderSectionFRMaintenance(String checkStatus, String checkItem) {
        bRO.checkOnCheckBoxInFRMaintence(checkStatus, checkItem);
    }

    @And("^I \"([^\"]*)\" \"([^\"]*)\" under 'Dealer Recommended Maintenance' section$")
    public void iCheckItemUnderSection(String checkStatus, String checkItem) {
        bRO.checkOnCheckBoxInDRMaintence(checkStatus, checkItem);

    }

    @Then("^I should see maintenance items \"([^\"]*)\" under \"([^\"]*)\"$")
    public void iSeeItemStatusUnderSection(String itemStatus, String sectionName) {
        switch (sectionName) {
            case "Selected Service":
                Assert.assertTrue(bRO.verifyItemShow(itemStatus));
                break;
        }
    }

    @Then("^I should see maintenance items \"([^\"]*)\" under \"([^\"]*)\" section$")
    public void iSeeItemStatusUnderTheSection(String itemStatus, String sectionName) {
        switch (sectionName) {
            case "Selected Service":
                Assert.assertTrue(bRO.verifyItemShow(itemStatus));
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button in 'Build RO' page$")
    public void iClickButtonInBuildRO(String buttonName) {
        bRO.clickButtonInROPage(buttonName);
    }

}
