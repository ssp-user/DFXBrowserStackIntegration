package com.appointmentmanger.testrunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty","html:target/cucumber-html-report","json:target/cucumber.json"},
		features = "src/test/resources/com/features/amfeatures/",
		glue = "com.autotest.teststeps",
		tags = "@AM01"
		)
public class AM01runnerTest extends AbstractTestNGCucumberTests{
	
}