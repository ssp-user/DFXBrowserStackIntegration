package com.autotest.teststeps.thirdparty;

import com.automation.pages.third_party.VINGenerator;
import com.automation.pages.wiadvisor.*;
import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.util.List;
import java.util.Map;

public class VINGeneratorSteps extends BaseTestSteps {
	VINGenerator vinGenerator = new VINGenerator();

    @And("^I capture Vehicle Identification Number$")
    public void iCaptureVIN() {
    	vinGenerator.captureVIN();
    }
    
    @And("^I capture Random VIN$")
    public void iCaptureRandomVIN() {
    	vinGenerator.captureRandomVIN();
    } 
    
    @Given("^I capture a random VIN from a pool of VINs$")
    public void iCaptureRandomVINFromAPool() {
    	vinGenerator.captureRandomVINFromAPool();
    }    
    
    @Given("^I choose VIN for walk-in appointment from the pool$")
    public void iChooseVINForWalkInappointment() {
    	vinGenerator.captureWalkInVIN();
    }       
}
