package com.autotest.testruner;


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
        ,tags = {"@ACCLOUDRNR,@ACDUMMY,@ACRNR,@ACPBS,@WIAUTOSOFT, @WICDK, @WIDEALERBUILT, @WIDUMMY, @WIPBS, @WIRNR,@WIAUTOMATEPILOT1,@WICDKPILOT1,@WIDEALERTRACKPILOT1,@WIDUMMYPILOT1,@WIPBSPILOT1",}  // OR run Model
        )

public class DMSMoringPushTestRunner {

}
