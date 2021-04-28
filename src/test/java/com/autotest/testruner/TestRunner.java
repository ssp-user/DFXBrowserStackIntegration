package com.autotest.testruner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RunCucumberTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        System.out.println("getFailCount --> "+ result.getFailureCount());
        System.out.println("run getRunCount --> "+ result.getRunCount());
        System.out.println("run result  --> "+ result.toString());
        System.out.println("ignore Count --> "+ result.getIgnoreCount());
        System.out.println(" time  Count --> "+ result.getRunTime());
    }

}
