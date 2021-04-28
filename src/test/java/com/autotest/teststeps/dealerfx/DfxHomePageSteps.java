package com.autotest.teststeps.dealerfx;

import com.automation.pages.dealerfx.HomePage;
import com.automation.pages.dealerfx.LoginPage;
import com.automation.pages.payment.PaymentHomePage;
import com.automation.utils.log.DfxLog;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.awt.*;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class DfxHomePageSteps extends BaseTestSteps {

    /**
     * Logs in using given credentials.
     * current page is the login page
     *
     * @param username
     * @param password
     */

    @When("^I login the Application as User \"(.*)\" and password \"(.*)\"$")
    public void iLoginDFXHomePageUserWithPassword(String username, String password) {
        LoginPage lPage = new LoginPage();
        lPage.loginHomePage(username, password);
    }

    /**
     * provide credentials in 'login' page
     *
     * @param dataTable information about credentials should be displayed<pre>
     * | UserID    | Password  |<br>
     * | dtao      | dtao      |<br>
     * @throws Throwable
     */

    @When("^I login the 'DFX' homePage as User using password$")
    public void iLoginDFXHomePageUserUsingPassword(DataTable dataTable) {
        List<List<String>> data = dataTable.raw();
        String username = data.get(1).get(0), password = data.get(1).get(1);
        LoginPage lPage = new LoginPage();
        lPage.loginHomePage(username, password);
    }

    @When("^I click 'Click Here to Select your Dealership' on 'Home' page$")
    public void iSelectDealerShipInHomepage() {
        HomePage hPage = new HomePage();
        hPage.clickSelectDealer();
    }

    //this is for select dealer with dealer name only, dealer code cause too much trouble as people change it around
    @And("^I select Dealer with \"(.*)\" in DFX Page$")
    public void iSelectDealerInDFXPage(String dealerName) {
        HomePage hPage = new HomePage();
        hPage.selectLoginDFXHomePageWithDealerNameOnly(dealerName);
    }

    @And("^I select Dealer with \"(.*)\" in Dealer Page$")
    public void iSelectDealerNameOnlyDealerPage(String dealerName) {
        HomePage hPage = new HomePage();
        hPage.selectLoginDealerHomePageWithDealerNameOnly(dealerName);
    }

    @And("^I select the Dealer with \"(.*)\" and \"(.*)\" in Dealer Page$")
    public void iSelectLoginDealerPage(String dealerName, String dealerCode) {
        HomePage hPage = new HomePage();
        hPage.selectLoginDealerHomePage(dealerName.trim(), dealerCode.trim());
    }

    @And("^I select the Dealer with \"(.*)\" or \"(.*)\" in Dealer Page$")
    public void iSelectDealerNameCodePage(String dealerName, String dealerCode) {
        HomePage hPage = new HomePage();
        hPage.selectLoginDealerHomePage(dealerName.trim(), dealerCode.trim());
    }

    @And("^I select Dealer with \"(.*)\" and \"(.*)\" on DFX Page$")
    public void iSelectLoginDFXPage(String dealerName, String dealerCode) {
        HomePage hPage = new HomePage();
        hPage.selectLoginDFXHomePage(dealerName, dealerCode);
    }

    @And("^I select the Dealer Using Name and Code$")
    public void iSelectDealerUseNameCode(DataTable dTable) {
        List<List<String>> data = dTable.raw();
        String dealerName = data.get(1).get(0), dealerCode = data.get(1).get(1);
        HomePage hPage = new HomePage();
        hPage.selectLoginDFXHomePage(dealerName, dealerCode);
    }

    @Then("^I see \"(.*)\" in the Page$")
    public void iSeeMessageInThePage(String message) {
        HomePage hPage = new HomePage();
        assertTrue(hPage.welcomeMsg().contains(message));
    }

    @Then("^I launch \"(.*)\" Application on 'Main Page'$")
    public void iLaunchApplicationOnMainPage(String app) {
        HomePage hPage = new HomePage();
        if (app.equalsIgnoreCase("Technician Inspection")){
            hPage.launchTechInspection();
        }else if (app.equalsIgnoreCase("Service Dashboard")){
            hPage.launchServiceDashboard();
        }else if(app.equalsIgnoreCase("Advisor Check-In")){
            hPage.launchAdvisorCheckIn();
        }else if(app.equalsIgnoreCase("Guest Connect")){
            hPage.launchCustomerConnect();
        }else if(app.equalsIgnoreCase("Customer Connect")){
            hPage.launchCustomerConnect();
        }else if(app.equalsIgnoreCase("Appointment Manager")){
            hPage.launchAppointmentManager();
        }else if(app.equalsIgnoreCase("OMM Admin")){
            hPage.launchOMMAdmin();
        }else{
            DfxLog.logWarn(" wrong Application Name ");
            Assert.fail(" wrong application name ");
        }
    }

    @And("^I verify login Page Pass on Dealer Page$")
    public void iVerifyLoginOK() {
        HomePage hPage = new HomePage();
        boolean ok  = hPage.isLoginOK();
        verifyTrue(" verify the login page ", ok );
    }

    @And("^I can log out$")
    public void iCanLogout() {
        HomePage hPage = new HomePage();
        hPage.logoutSystem();
    }

    @And("^I close the current tab$")
    public void closeTab()throws AWTException{
        HomePage hPage = new HomePage();
        hPage.closeCurrentTab();
    }

    @When("^I get property \"(.*)\" from outside$")
    public void iLoginDFXHomePageUserWithPassword(String name) {
        String pro = System.getProperty(name);
        System.out.println("##### System property is ...............   " + pro );
    }
    
    @And("^I move to switch dealer window on 'Home' page$")
    public void iMoveToSwitchDealerWindow() {
        HomePage hPage = new HomePage();
        hPage.switchDealer();
    } 

    @And("^I choose \"(.*)\" application from the 'Home' page$")
    public void iChooseApplicationFromHomePage(String application) throws Exception {
        HomePage hPage = new HomePage();
        hPage.openApplication(application);
    }       
    
   
    @And("^I open \"(.*)\" application from the 'Home' page$")
    public void iOpenApplicationFromHomePage(String applicationName) throws Exception {
    	HomePage hpage = new HomePage();
    	hpage.openApplication(applicationName);
    }
    
    
    @And("^I choose \"(.*)\" application in the sidebar menu from the 'Home' page$")
    public void iChooseApplicationFromSideBarMenuFromHomePage(String application) {
        HomePage hPage = new HomePage();
        hPage.moveToSideBarApplication(application);
    }		 

    @And("^I note down page window handle from the 'Home' page$")
    public void iNoteDownHomePageWindowHandle() {
        HomePage hPage = new HomePage();
        hPage.noteDownHomePageWindowHandle();
    }       
    
    @And("^I move back to 'Home' page$")
    public void iMoveBackToHomePage() {
        HomePage hPage = new HomePage();
        hPage.moveBackToHomePageWindowHandle();
    }  

    @And("^I validate \"(.*)\" logo from the 'Home' page$")
    public void iValidateLogoFromHomePage(String logo) {
        HomePage hPage = new HomePage();
        hPage.validateLogo(logo);
    }	       
    
    @And("^I click \"(.*)\" on the 'Home' page$")
    public void iClickElementOnHomePage(String element) {
        HomePage hPage = new HomePage();
        hPage.click(element);
    }    
    
	@And("^I validate \"([^\"]*)\" on 'Home' page change dealership window$")
	public void iValidateSwitchDealerScreen(String message) {
        HomePage hPage = new HomePage();
        hPage.validateSwitchDealerWindow(message);
	}       
	@And("^I validate \"([^\"]*)\" of business applications on 'Home' page$")
	public void iValidateTranslationsOfBusinessApplications(String message) {
        HomePage hPage = new HomePage();
        hPage.verifyElementExists(message);
	}
	@And("^I validate layout in \"([^\"]*)\" of 'Login' page$")
	public void iValidateLayOutOfLoginScreen(String preferredlanguage) {
        LoginPage lPage = new LoginPage();
        lPage.validateLayOutOfLoginScreen(preferredlanguage);
	} 
	@And("^I choose \"([^\"]*)\" on 'Login' page$")
	public void iChoosePreferredLanguageOnLoginScreen(String preferredlanguage) {
        LoginPage lPage = new LoginPage();		
        LoginPage.setPreferredLanguage(preferredlanguage.trim());
        lPage.choosePreferredLanguage();
	} 
	@And("^I validate error message in \"([^\"]*)\" on the 'Login' page$")
	public void iValidateErrorMessageOntheLoginScreen(String preferredlanguage) {
        LoginPage lPage = new LoginPage();
        lPage.validateErrorMessageOnTheLoginScreen(preferredlanguage);
	}	

	@And("^I close the current active tab and return to 'Home' page$")
	public void iCloseTheCurrentActiveTabAndReturnToHomePage() {
        HomePage hPage = new HomePage();
        hPage.closeActiveTab();
	}	
}