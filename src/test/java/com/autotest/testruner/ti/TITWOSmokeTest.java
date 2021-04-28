package com.autotest.testruner.ti;


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
        ,tags = {"@TI06,@TI07,@TI08,@TI09,@TI11,@TI12,@TI13,@TI14,@TI15,@TI17,@TI18,@TI19,@TI21,@TI22,@TI23",}  // OR run Model
        )

public class TITWOSmokeTest
{

}
