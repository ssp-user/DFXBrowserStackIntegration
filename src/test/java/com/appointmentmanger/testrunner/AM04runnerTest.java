package com.appointmentmanger.testrunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty","html:target/cucumber-html-report","json:target/cucumber.json"},
		features = "src/test/resources/com/features/amfeatures2/",
		glue = "com.autotest.teststeps",
		tags = "@Default"
		)
public class AM04runnerTest extends AbstractTestNGCucumberTests{
	
}