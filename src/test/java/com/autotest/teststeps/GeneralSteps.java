package com.autotest.teststeps;

import com.automation.pages.PageManager;
import com.automation.pages.common.WebPage;
import com.automation.pages.customer_connect.*;
import com.automation.pages.service_dashboard.SD_ServiceVisit;
import com.automation.pages.tech_inspection.ServiceTab.RecommendationTab;
import com.automation.pages.third_party.GmailPage;
import com.automation.pages.third_party.YahooPage;
import com.automation.pages.wiadvisor.WiAdvisorBuildROTab;
import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.otherUtils.CommonMethods;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GeneralSteps extends BaseTestSteps {

    /**
     * Logs in using given credentials.
     * Assumes browser is on a login page
     *
     * @param browser
     * @param evnRUL
     */

    WiAdvisorBuildROTab bRO = new WiAdvisorBuildROTab();
    GmailPage gmailPage = new GmailPage();
    YahooPage yahooPage = new YahooPage();

    @Given("^I launched browser \"(.*)\" and open (.+)$")
    public void iLaunchBrowserAndOpenURLuser(String browser, String evnRUL) {
        PageManager.quitBrowser();
        PageManager.launchBrowser(browser, "no");
        String url = PageManager.getEnvURL(evnRUL);
        TestParameters.setUrlEnv(url);
        TestParameters.setBrowserName(browser);
        TestParameters.updateTestEnv(url);
        WebPage.openURL(url);
        WebPage.initializeJSONObject();        
    }

    @Given("^I launch browser \"(.*)\" in \"(.*)\" and open \"(.*)\"$")
    public void iLaunchBrowserAndOpenURLWithHeadless(String browser, String head, String evnRUL) {
    	System.out.println();
    	System.out.println("********************************");
    	System.out.println("Driver execution mode as passed from Jenkins server --> "+ head);
    	System.out.println("********************************");
    	System.out.println();    	
        PageManager.quitBrowser();
        PageManager.launchBrowser(browser, head);
        String url = PageManager.getEnvURL(evnRUL);
        TestParameters.setUrlEnv(url);
        TestParameters.setBrowserName(browser);
        TestParameters.updateTestEnv(url);
        WebPage.openURL(url);
        WebPage.initializeJSONObject();        
    }

    @When("^I login page \"([^\"]*)\" as User \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iLoginPageWithUserNameAndPassWord(String pageName, String username, String password) {
        switch (pageName) {
            case "Gmail":
                gmailPage.loginGmail(username, password);
                break;
            case "Yahoo":
            	yahooPage.loginYahoo(username, password);
                break;                
            case "mightytext":
                gmailPage.loginGoogleAccount(username, password);
                break;
        }
    }

    @And("^I set \"([^\"]*)\" to the 'DMS'$")
    public void setToTheDMS(String dms) {
        if (dms.equalsIgnoreCase("default"))
        {  String url = TestParameters.getUrlEnv();
            dms = TestParameters.get_OEM_DummyDealer(url) + " Framework" ;
        }
        TestParameters.setDMSName(dms);
        System.out.println("The current DMS is : " + dms);
    }

    @And("^I switch back to page title \"([^\"]*)\"$")
    public void iSwitchPageToSomeTitle(String pageTitle) {
        int expectedNumberOfWindows = 3;
        if (pageTitle.contains("Connect")) {
            expectedNumberOfWindows = 3;
        } else if (pageTitle.equals("ReceiveaSMS.com :: Canada")) {
            expectedNumberOfWindows = 3;
        } else if (pageTitle.equals("Service Dashboard")) {
            expectedNumberOfWindows = 3;
        } else if (pageTitle.equals("Dealer-FX Application Framework")) {
            expectedNumberOfWindows = 2;
        } else if (pageTitle.equals("Dealer-FX Application Home")) {
            expectedNumberOfWindows = 2;
        } else if (pageTitle.equals("Appointment Manager")) {
            expectedNumberOfWindows = 3;
        }
        CC_DashboardPage cPage = new CC_DashboardPage();
        cPage.changeToPageWithTitle(pageTitle, expectedNumberOfWindows);
    }

    @And("^I hit Keyboard \"([^\"]*)\"$")
    public void iHitKeyboard() {
        RecommendationTab rTab = new RecommendationTab();
        rTab.keyboardEnter();
    }

    @And("^I click \"([^\"]*)\" dropdown on \"([^\"]*)\" tab$")
    public void iClickOnDropDownOnTab(String dropDownName, String tabName) {
        switch (tabName) {
            case "Build RO":
                bRO.clickServiceCategory(dropDownName);
                break;
        }
    }

    @And("^I select \"([^\"]*)\" from \"([^\"]*)\" dropdown on \"([^\"]*)\" tab$")
    public void iSelectDropDownOnTab(String dropDownOption, String dropDownName, String tabName) {
        switch (tabName) {
            case "Build RO":
                switch (dropDownName) {
                    case "Please Select a Service Category":
                        bRO.selectServiceCategory(dropDownOption);
                        break;
                }
                break;
        }
    }

    @And("^I click \"([^\"]*)\" checkbox on \"([^\"]*)\" tab$")
    public void iClickOnCheckBoxOnTab(String checkBoxName, String tabName) {
        switch (tabName) {
            case "Build RO":
                bRO.clickOnConcernCheckBox(checkBoxName);
                break;
        }
    }
    
    @And("^I validate \"([^\"]*)\" as window title of the focussed screen$")
    public void iValidateWindowTitle(String title) {
    	CommonMethods.validateWindowTitle(title);
    }
    
    @And("^I close the current focussed window on the browser$")
    public void iCloseCurrentOpenWindowOntheBrowser() {
    	CommonMethods.closeWindow();
    }         

}
