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
        , tags = {"@rsflow03,@SD04,@SD10,@SD11,@SD12",}
)

public class SDSmokeTest {

}

