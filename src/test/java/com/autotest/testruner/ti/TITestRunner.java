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
        ,tags = {"@TI01,@TI02,@TI03,@TI04,@TI05,@TI06,@TI07,@TI08,@TI09,@TI10,@TI11,@TI12,@TI13,@TI14,@TI15,@TI16,@TI17,@TI18,@TI19,@TI20,@TI21,@TI22,@TI23,@TIONE06,@TIONE07,@TIONE08,@TIONE09,@TIONE10,@TIONE11,@TIONE12,@TIONE13,@TIONE14,@TIONE15,@TIONE16,@TIONE17",}  // OR run Model
        )

public class TITestRunner {

}
