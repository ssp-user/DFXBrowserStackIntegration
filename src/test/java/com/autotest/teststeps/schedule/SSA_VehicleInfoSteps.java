package com.autotest.teststeps.schedule;

import com.automation.pages.schedule.SSA_ConcernsPage;
import com.automation.pages.schedule.SSA_SearchPage;
import com.automation.pages.schedule.SSA_VehicleInfoPage;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class SSA_VehicleInfoSteps extends BaseTestSteps {


    private static Logger log = Logger.getLogger(SSA_VehicleInfoSteps.class);

    SSA_VehicleInfoPage ssVIPage = new SSA_VehicleInfoPage();

    @And("^I decode \"([^\"]*)\" on 'Vehicle Info' page in SSA$")
    public void iDecodeVINOnVehicleInfoPage(String vin) {
        ssVIPage.handleTry();
        ssVIPage.decodeVINInfo(vin);
    }

    @And("^I input \"([^\"]*)\" to continue on 'Vehicle Info' page in SSA$")
    public void iInputClickContinueOnVehiclePage(String meter) {
        ssVIPage.handleTry();
        ssVIPage.inputOdometerContinueInfo(meter);
    }

    @And("^I enter vehicle data set on 'Vehicle Info' page in SSA$")
    public void iSetVehicleDataOnSearchPage(DataTable dataTable) {
//        cnvPage.waitForCnVPageToLoad();
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String field = map.get("FieldName").toUpperCase();
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                ssVIPage.handleTry();
                ssVIPage.chooseVehicleSpecs(field, value);
            }
        }
    }

    @And("^I verify the 'Recall list' on 'Vehicle Info' page in SSA$")
    public void iVerifyRecallListOnVehiclePage() {
        ssVIPage.handleTry();
        int recalltab = SSA_SearchSteps.recalls;
        if (recalltab > 0 ){
            CommonMethods.sleep(8000);
        }else{
            CommonMethods.sleep(1000);
        }
        int recalls = ssVIPage.getRecallListSize();
//        System.out.println("<== recalltab --- " + recalltab  + "  ,recalls  ===>" + recalls );
        verifyEquals(" verify the Recalls List on the  page " , recalltab ,recalls);
        Assert.assertEquals(" verify the Recalls List on the page " ,recalltab ,recalls);
    }

    @And("^I do not see the 'vehicle specs' on 'Vehicle Info' page in SSA$")
    public void iDontSeeVehicleSpecsOnVehiclePage() {
        ssVIPage.handleTry();
//        System.out.println("<== recalltab --- " + recalltab  + "  ,recalls  ===>" + recalls );
        verifyFalse(" verify the Vehicle Specs not on the page " , ssVIPage.showVehicleSpecs());
        Assert.assertFalse(" verify the Vehicle Specs not on the page " , ssVIPage.showVehicleSpecs());
    }


    @And("^I click \"([^\"]*)\" button on 'Vehicle Info' page in SSA$")
    public void iClickButtonOnVehiclePage(String buttonName) {
        switch (buttonName.toUpperCase()) {
            case "NEXT":
                CommonMethods.sleep(2000);
                ssVIPage.clickNextBtn();
                break;
        }
        ssVIPage.handleTry();
    }

}
