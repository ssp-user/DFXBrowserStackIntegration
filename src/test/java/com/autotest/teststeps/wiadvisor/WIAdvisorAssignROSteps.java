package com.autotest.teststeps.wiadvisor;

import com.automation.pages.wiadvisor.*;
import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;

import java.util.List;
import java.util.Map;

public class WIAdvisorAssignROSteps extends BaseTestSteps {

    WiAdvisorLoginPage wilPage = new WiAdvisorLoginPage();
    WiAdvisorIndexPage wii = new WiAdvisorIndexPage();
    WiAdvisorCustomerInfoTab cit = new WiAdvisorCustomerInfoTab();
    WiAdvisorAssignROTab aRO = new WiAdvisorAssignROTab();

    @And("^I select a proper or default Service Advisor in 'Assign RO' tab$")
    public void iSelectDefaultServiceAdvisorInAssignROT(DataTable dataTable) {
//        aRO.clickOnContinueAssignRO();
        String dealerID = TestParameters.getDealerCode();
        String testEnv = TestParameters.getTestEnv();
        System.out.println(" Test Env is 000000.....    " + testEnv );
        if (testEnv.equalsIgnoreCase("dt-PILOT 1")) {
            System.out.println(" Test Env 1111 is .....    " + testEnv );
            List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> map : maps) {
                String dealer = map.get("DealerCode");
                String sAdvisor = map.get("Service Advisor");
                if (dealer.equals(dealerID)) {
                    aRO.selectServiceAdvisorAssignROTab(sAdvisor);
                }
            }
        }
    }

    @And("^I select \"(.*)\" under 'Service Advisor' field in 'Assign RO' tab$")
    public void iSelectServiceAdvisorInAssignROT(String advisor) {
        CommonMethods.sleep(2000);
        if (advisor.equals("") || advisor.equalsIgnoreCase("default")) {
            // select the default advisor
         }else{
            aRO.selectServiceAdvisorAssignROTab(advisor);
        }
//        System.out.println(" the advisor is  .. " + advisor );
    }

    @And("^I set promise date next \"(.*)\" month \"(.*)\" in 'Assign RO' tab$")
    public void iSetPromiseDateNextMothsInAssignROT(int nexts,String day) {
        aRO.setPromiseDateInAssignRO(nexts,day);
    }

    @And("^I set promise time \"(.*)\" hours \"(.*)\" in 'Assign RO' tab$")
    public void iSelectServiceAdvisorInAssignROT(int hour, String ap) {
        aRO.setPromiseTimeInAssignRO(hour, ap);
    }

    @And("^I click \"([^\"]*)\" button in 'Assign RO' page$")
    public void iClickButtonInAssignRO(String buttonName) {
        aRO.clickButtonInROPage(buttonName);
    }

}
