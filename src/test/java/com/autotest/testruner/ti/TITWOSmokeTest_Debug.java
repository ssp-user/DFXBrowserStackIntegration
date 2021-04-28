package com.autotest.testruner.ti;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"json:target/jsonReport/cucumber.json", "html:target/htmlReport/", "rerun:target/rerun.txt", "junit:target/xmlReport/Junit-report.xml", "pretty"},
        features = "src/test/resources/com/features",
        glue = "com.autotest.teststeps"
//            ,dryRun = true
        ,strict = true
        ,monochrome = true
//            , tags = {"~@Ignore,~@Login002"}  // And run Model
        ,tags = {"@TI10"}  // OR run Model
        )

public class TITWOSmokeTest_Debug
{

}
