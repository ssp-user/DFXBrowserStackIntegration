package com.autotest.teststeps.appointment_manager;

import com.automation.pages.appointment_manager.AM_SSOPage;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AM_SSOPageSteps extends BaseTestSteps {

    private static Logger log = Logger.getLogger(AM_SSOPageSteps.class);

    AM_SSOPage ssoPage = new AM_SSOPage();

    @And("^I set SSO data on 'SSO' page in AM$")
    public void iSetSearchDataOnSSOPage(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String field = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                ssoPage.setSSOData(field,value);
            }
        }
    }

    @And("^I set SSO data on 'Mitsubishi SSO' page in AM$")
    public void iSetSearchDataOnMitSSOPage(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String field = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                ssoPage.setMitSSOData(field,value);
            }
        }
    }

    @And("^I click \"([^\"]*)\" button on 'SSO' page in AM$")
    public void iClickBtnOnSSO(String name) {
        CommonMethods.sleep(1000);
        ssoPage.clickButton(name.toUpperCase());
        CommonMethods.sleep(1000);
    }

    @And("^I click \"([^\"]*)\" button on 'Mitsubishi SSO' page in AM$")
    public void iClickBtnOnMitsSSO(String name) {
        iClickBtnOnSSO(name);
    }

    @And("^I see error message \"([^\"]*)\" on 'SSO' page in AM$")
    public void iSeeErrorMessageOnSSO(String name) {
        CommonMethods.sleep(1000);
        verifyFalse(" Verify error message " + name  + " on screen " , ssoPage.getErrorMessage(name).isEmpty());
        Assert.assertFalse(" Verify error message " + name  + " on screen " , ssoPage.getErrorMessage(name).isEmpty());
    }

    @And("^I see error message \"([^\"]*)\" on 'Mitsubishi SSO' page in AM$")
    public void iSeeErrorMessageOnMitSSO(String name) {
        iSeeErrorMessageOnSSO(name);
    }

    @And("^I see \"([^\"]*)\" is \"([^\"]*)\" on 'Mitsubishi SSO' page in AM$")
    public void iSeeIsOnMitSSOPage(String field, String value) {
        CommonMethods.sleep(3000);
        String result = ssoPage.getFieldvalue(field.toUpperCase());
        verifyTrue(" Verify search " + field + " as " + value , result.contains(value));
        Assert.assertTrue(" Verify search " + field + " as " + value , result.contains(value));
    }

}
