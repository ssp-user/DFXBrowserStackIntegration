package com.autotest.testruner.customerconnect;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty","html:target/cucumber-html-report","json:target/cucumber.json"},
		features = "src/test/resources/com/features/ccfeatures/",
		glue = "com.autotest.teststeps",
		tags = "@Default"		
		)
public class CustomerConnectTestRunner extends AbstractTestNGCucumberTests{
	
}