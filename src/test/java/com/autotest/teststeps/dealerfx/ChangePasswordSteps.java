package com.autotest.teststeps.dealerfx;

import com.automation.pages.dealerfx.ChangePassword;
import com.automation.pages.dealerfx.HomePage;
import com.automation.pages.dealerfx.LoginPage;
import com.automation.utils.log.DfxLog;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.awt.*;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ChangePasswordSteps extends BaseTestSteps {
	ChangePassword changePassword = new ChangePassword();
    @When("^I validate username on the 'Change Password' page$")
    public void iValidateUserName() {
    	changePassword.validateUserName();
    }
}