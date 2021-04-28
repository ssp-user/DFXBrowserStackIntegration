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
        ,tags = {"@CC_Testcase_001,@CC_Testcase_002,@CC_Testcase_003,@CC_Testcase_004,@CC_Testcase_005,@CC_Testcase_006,@CC_Testcase_007,@CC_Testcase_008,@CC_Testcase_009,@CC_Testcase_010,@CC_Testcase_011,@CC_Testcase_012,@CC_Testcase_013,@CC_Testcase_014,@CC_Testcase_015,@CC_Testcase_017,@CC_Testcase_018,@CC_Testcase_019,@CC_Testcase_020",}
)

public class CCTestRunner {

}

