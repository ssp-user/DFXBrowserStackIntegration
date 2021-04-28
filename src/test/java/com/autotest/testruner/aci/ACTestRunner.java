package com.autotest.testruner.aci;


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
        ,tags = {"@ACCLOUDRNR,@ACDUMMY,@AC-SanityDmsPush,@ACRNR,@ACPBS,@AC_GMREGISTERED,@AC_NOTREGISTERED,@AC_CUSTOMERWOEMAIL,@AC_REGISTERING,@ACREGRESSION",}  // OR run Model
        )

public class ACTestRunner {

}
