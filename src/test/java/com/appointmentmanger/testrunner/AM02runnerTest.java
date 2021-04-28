package com.appointmentmanger.testrunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty","html:target2/cucumber-html-report","json:target2/cucumber.json"},
		features = "src/test/resources/com/features/amfeatures/",
		glue = "com.autotest.teststeps",
		tags = "@AM02"
		)
public class AM02runnerTest extends AbstractTestNGCucumberTests{
	
}