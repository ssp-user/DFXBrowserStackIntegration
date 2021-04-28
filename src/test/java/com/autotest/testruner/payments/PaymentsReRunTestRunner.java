package com.autotest.testruner.payments;

import org.junit.runner.RunWith;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",
						retryCount = 1,
						detailedReport = true,
						detailedAggregatedReport = true,
						overviewReport = true,
						coverageReport = true,
						jsonUsageReport = "target/cucumber-usage.json",
						usageReport = true,
						toPDF = true,
						includeCoverageTags = {"@chrome"},
						outputFolder = "target")
@CucumberOptions(
		plugin = {"pretty","html:target/cucumber-html-report","json:target/cucumber.json"},
		features = "src/test/resources/com/features/paymentfeatures/",
		glue = "com.autotest.teststeps",
		tags = "@PY01"		
		)
public class PaymentsReRunTestRunner extends AbstractTestNGCucumberTests{
	
}