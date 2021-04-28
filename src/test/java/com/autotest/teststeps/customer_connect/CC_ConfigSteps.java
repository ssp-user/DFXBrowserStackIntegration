package com.autotest.teststeps.customer_connect;

import com.automation.pages.customer_connect.CC_ConfigPage;
import com.automation.pages.customer_connect.CC_SearchPage;
import com.automation.pages.customer_connect.CC_TasksPage;
import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.otherUtils.CommonMethods;
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

public class CC_ConfigSteps extends BaseTestSteps {

	CC_ConfigPage cPage = new CC_ConfigPage();
    TestParameters tp = new TestParameters();


    @And("^I click \"([^\"]*)\" on 'CONFIG' page$")
    public void iClickOnCCConfigPage(String element) {
    	cPage.clickElement(element);
    }
    
    @And("^I update \"([^\"]*)\" on 'CONFIG' page$")
    public void iUpdateOnCCConfigPage(String text) {
    	cPage.updateENClosedCall(text);
    }
    
    @And("^I update \"([^\"]*)\" for other on 'CONFIG' page$")
    public void iUpdateForOtherOnCCConfigPage(String text) {
    	cPage.updateENOther(text);
    }       
    
}