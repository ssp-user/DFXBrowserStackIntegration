package com.autotest.teststeps;


import com.automation.utils.log.LogTestStats;
import com.automation.utils.log.LogUtils;
import com.autotest.testruner.ScenarioBank;
import com.autotest.utils.TestInit;
import com.google.common.base.Stopwatch;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


import javax.xml.ws.WebServiceException;
import org.apache.log4j.Logger;
import com.automation.pages.PageManager;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HooksBase extends BaseTestSteps {
    private static final Logger logger = Logger.getLogger(HooksBase.class);
    private static Stopwatch betweenScenariosStopwatch = Stopwatch.createUnstarted();
    private static final int BROWSER_RESTART_INTERVAL_MINUTES = 15;
    private static Stopwatch restartBrowserStopwatch = Stopwatch.createUnstarted();


    private void embedScreenshot(Scenario scenario) {
        try {
            byte[] screenshot = PageManager.takeScreenshot(scenario);
            scenario.embed(screenshot, "image/png");
        } catch (WebServiceException e) {
            scenario.write("WebDriver does not support screenshots.");
        }
    }

  /*
  Below are initilizing the before test starting
   */

    @Before(order = 5)
    public void loadRepotory(Scenario scenario) {
        DOMConfigurator.configure("config/log4j.xml");
    }

    @Before(order = 10)
    public void initScenarioStorage(Scenario scenario) {
        //System.out.println( scenario.getName() + " scenario  status  before test result is  "+ scenario.getStatus());
        ScenarioBank.newScenario(scenario);
    }

    @Before(order = 20)
    public void restartBrowser() {
        if (!restartBrowserStopwatch.isRunning() ||
                restartBrowserStopwatch.elapsed(TimeUnit.MINUTES) >= BROWSER_RESTART_INTERVAL_MINUTES) {
            logger.info("Starting browser");
            PageManager.quitBrowser();
            restartBrowserStopwatch.reset();
            restartBrowserStopwatch.start();
        }
    }

    @Before(order = 30)
    public void stopTimer() {
        if (!betweenScenariosStopwatch.isRunning()) return;
//        logger.info("Time between scenarios: " + betweenScenariosStopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        betweenScenariosStopwatch.reset();
    }

    @Before(order = 40)
    public void setUpSuiteEnv(){
        TestInit.testBeginHead();
        TestInit.createFolder();
    }

    @Before(order = 50)
    public void beforeMethod(){
//        LogTestStats.testCount();
    }

    /////////////////////////////// AFTER HOOKS //////////////////////

    @After(order = 30)
    public void startTimer() {
        betweenScenariosStopwatch.start();
    }

    @After(order = 20)
    public void captureWebScreenOnFailure(Scenario scenario) {
        System.out.println("  ");
        System.out.println("  ****   test scenario : '"+scenario.getName()+"'"+" is "+ scenario.getStatus()+"   **** ");
        if (scenario.isFailed()){
            logger.error(String.format("Scenario failed: %s", scenario.getName()));
            embedScreenshot(scenario);
        }
    }

    @After(order = 10)
    public void ensureLogOutAfterScenario() {
        PageManager.quitBrowser();
    }

}
