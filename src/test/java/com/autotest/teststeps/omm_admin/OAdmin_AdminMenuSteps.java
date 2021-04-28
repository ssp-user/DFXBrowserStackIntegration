package com.autotest.teststeps.omm_admin;

import com.automation.pages.omm_admin.OMM_AdminFramePage;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class OAdmin_AdminMenuSteps extends BaseTestSteps {


    private static Logger log = Logger.getLogger(OAdmin_AdminMenuSteps.class);

    OMM_AdminFramePage oamPage = new OMM_AdminFramePage();

    @And("^I click \"([^\"]*)\" button on 'OMM Admin' Page$")
    public void iClickButtonOnLookUpPage(String buttonName) {
        switch (buttonName.toUpperCase()) {
            case "SAVE":
                oamPage.clickSaveBtn();
                break;
        }
    }

    @And("I goto \"([^\"]*)\" tab and select \"([^\"]*)\" on 'OMM Admin' Page")
    public void iGotoTabSelectOnOMMAdminPage(String tab, String menu) {
        oamPage.gotoTabSelectMenu(tab.toLowerCase(),menu.toLowerCase());
    }

}
