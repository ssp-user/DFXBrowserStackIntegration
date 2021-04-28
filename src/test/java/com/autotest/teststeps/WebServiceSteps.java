package com.autotest.teststeps;

import com.automation.utils.webServiceUtils.WebService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class WebServiceSteps extends BaseTestSteps{

    WebService ws = new WebService();

    @Given("^I open web service \"([^\"]*)\"$")
    public void iOpenWebService(String webServiceURL){

    }

    @When("^I send post web service with dealerID \"([^\"]*)\" and vin \"([^\"]*)\"$")
    public void iSendWebServiceWithParameter(String dealerid, String vin){

    }

    @Then("^I see \"([^\"]*)\" service on \"([^\"]*)\" return Status Code \"([^\"]*)\"$")
    public void iSeeServiceReturnStatus(String serviceName, String environment, String statusCode){
        String expectedStatusCdoe = statusCode;
        String actualStatusCode = ws.getCurrentNetWorkLog(serviceName, environment);
        Assert.assertEquals(expectedStatusCdoe,actualStatusCode);
    }
}
