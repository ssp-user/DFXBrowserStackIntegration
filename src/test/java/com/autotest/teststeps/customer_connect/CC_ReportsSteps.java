package com.autotest.teststeps.customer_connect;

import com.automation.pages.customer_connect.CC_ReportsPage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class CC_ReportsSteps extends BaseTestSteps {

    CC_ReportsPage crPage = new CC_ReportsPage();

    @Then("^I see following reports loading properly on 'REPORTS' page$")
    public void iVerifyReportsLoadOnReportsPage(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String reportName = map.get("Report Name");
            String reportColumn = map.get("Report Column");
            String reportValue = map.get("Report Value");
            if (StringUtils.isNotBlank(reportName)) {
                crPage.clickOnReportDropDown(reportName);
                if (!crPage.verifyColumnVisible(reportColumn)) {
                    Assert.fail("<====== Report Column '" + reportColumn + "' not found ======>");
                }
                if (reportValue.equals("1st row")) {
                    if (!crPage.verifyValueVisibleOverViewReport()) {
                        Assert.fail("<====== Report Column value '" + reportValue + "' not found ======>");
                    }
                } else {
                    if (!crPage.verifyValueVisible(reportValue)) {
                        Assert.fail("<====== Report Column value '" + reportValue + "' not found ======>");
                    }
                }
            }
        }
    }

}





