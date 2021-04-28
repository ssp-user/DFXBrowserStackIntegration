package com.autotest.testruner.am;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"json:target/jsonReport/cucumber.json", "html:target/htmlReport/", "rerun:target/rerun.txt", "junit:target/xmlReport/Junit-report.xml"},
        features = "src/test/resources/com/features",
        glue = "com.autotest.teststeps"
//            ,dryRun = true
        ,strict = true
        ,monochrome = false
        ,format = {"pretty"}
//            , tags = {"~@Ignore,~@Login002"}  // And run Model
        , tags = {"@AM_Regression_DMSpushOnly-TEST2,@AM_Regression_Others-TEST2,@AM_Regression13_Recall-TEST2","~@Ignore","~@Debug"}
)

public class TestRunAMRegressionFullTEST2 {

}

