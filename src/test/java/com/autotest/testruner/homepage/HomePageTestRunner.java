package com.autotest.testruner.homepage;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty","html:target/cucumber-html-report","json:target/cucumber.json"},
		features = "src/test/resources/com/features/homepagefeatures/",
		glue = "com.autotest.teststeps",
		tags = "@Default"		
		)
public class HomePageTestRunner extends AbstractTestNGCucumberTests{
	
}