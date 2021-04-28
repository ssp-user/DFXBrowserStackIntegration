package com.autotest.teststeps.omm_admin;

import com.automation.pages.omm_admin.Admin_OnlineApptSchedulePage;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class OAdmin_AdminSteps extends BaseTestSteps {

    private static Logger log = Logger.getLogger(OAdmin_AdminSteps.class);

    @And("^I set Configure data on 'Admin-Online Appointment Schedule' page$")
    public void iSetConfigureDataOnOnlineApptSchedulePage(DataTable dataTable) {
        Admin_OnlineApptSchedulePage page = new Admin_OnlineApptSchedulePage();
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                CommonMethods.sleep(200);
                page.setConfigureData(fieldName,value);
            }
        }
    }



}
