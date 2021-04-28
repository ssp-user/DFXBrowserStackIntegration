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
        , tags = {"@AM01,@AM02,@AM03,@AM04,@AM09,@AM10,@AM11,@AM12,@AM13,@AM14,@AM15,@AM16,@AM17,@AM18,@AM19",}
)

public class AMTestRunner {

}

