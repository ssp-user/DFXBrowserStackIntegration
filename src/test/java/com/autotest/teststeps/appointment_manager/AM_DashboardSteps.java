package com.autotest.teststeps.appointment_manager;

import com.automation.pages.DriverMaker;
import com.automation.pages.PageManager;
import com.automation.pages.appointment_manager.AM_DashboardPage;
import com.automation.pages.appointment_manager.AM_TimeAndAdvisorPage;
import com.automation.pages.common.WebPage;
import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import com.sun.source.tree.AssertTree;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;

public class AM_DashboardSteps extends BaseTestSteps {

	AM_DashboardPage dbPage = new AM_DashboardPage();

	protected static int waiter, shuttle, rental, loaner, ownRide, pickup, repeatRepair, specialParts;
	private static int originalOpenCounter,newOpenCounter;		

	private static Logger log = Logger.getLogger(AM_DashboardSteps.class);

	@And("^I click appointment with name on 'DASHBOARD' page$")
	public void iClickAppointmentNameOnDASHBOARD() {
		dbPage.clickAppointmentWithName();
	}

	@And("^I click \"([^\"]*)\" button for the Appointment on 'DASHBOARD' page$")
	public void iClickBtnInAppointmentOnDASHBOARD(String buttonName) {
		CommonMethods.sleep(500);
		switch (buttonName) {
		case "EDIT":
			dbPage.clickEditOnAppointment();
			break;
		}
	}

	@And("^I click \"([^\"]*)\" button in this Appointment on 'DASHBOARD' page$")
	public void iClickBtnForAppointmentOnDASHBOARD(String buttonName) {
		CommonMethods.sleep(500);
		switch (buttonName) {
		case "EDIT":
			dbPage.editAppointment();
			break;
		case "DELETE":
			dbPage.deleteAppointment();
			break;
		}
	}

	@Then("^I verify Page Info \"([^\"]*)\" and Browser log on 'DASHBOARD' page$")
	public void iVerifyPageBrowserLogOnDASHBOARD(String info) {
		dbPage.waitDashboardPageLoad();
		if (dbPage.isBodyPageContains(info)) {
			System.out.println(" Verify : " + "Page Body Class found ---> " + info);
			log.info(" Verify : " + "Page Body Class found ---> " + info);
		} else {
			System.out.println(" Verify : " + "Page Body Class not found ---> " + info);
			log.info(" Verify : " + "Page Body Class not found ---> " + info);
		}
		String infologs = "Angular is running in the development mode. Call enableProdMode() to enable the production mode";
		if (DriverMaker.isBrowserLogContains(infologs)) {
			System.out.println(" Verify : " + "The log found in browser Console ---> " + info);
			log.warn(" Verify : " + "The log found in browser Console ---> " + info);
		} else {
			System.out.println(" Verify : " + "The log not found in browser Console ---> " + info);
			log.error(" Verify : " + "The log not found in browser Console ---> " + info);
		}
	}

	@And("^I verify 'UserName' is \"([^\"]*)\" on 'DASHBOARD' page$")
	public void iVerifyUserNameLogOnDASHBOARD(String name) {
		dbPage.waitDashboardPageLoad();
		String userName = dbPage.getUserName();
		verifyTrue(" verify user name on dashboard ", userName.equals(name));
	}

	@And("^I verify \"([^\"]*)\" view layout on 'DASHBOARD' page$")
	public void iVerifyLayoutOnDASHBOARD(String name) {
		CommonMethods.sleep(300);
		boolean view = (dbPage.getViewElement(name.toUpperCase()) != null);
		verifyTrue(" \n verify " + name + " view layout on dashboard ", view);
//        Assert.assertTrue(" \n verify " + name + " view layout on dashboard " , view);
	}

	@And("^I see Dealer is \"([^\"]*)\" on 'DASHBOARD' page$")
	public void iSeeDealerNameOnDASHBOARD(String name) {
		dbPage.waitDashboardPageLoad();
		String dealer = dbPage.getDealerName();
		Assert.assertTrue(" verify Dealer name" + name + " on dashboard ", dealer.contains(name));
	}

	@And("^I click \"([^\"]*)\" button on 'DASHBOARD' page$")
	public void iClickBtnOnDASHBOARD(String name) {
		CommonMethods.sleep(2000);
		dbPage.clickButton(name.toUpperCase());
		CommonMethods.sleep(500);
	}

	@And("^I click \"([^\"]*)\" tab on 'DASHBOARD' page$")
	public void iClickTabOnDASHBOARD(String name) {
		CommonMethods.sleep(1000);
		dbPage.clickTab(name.toUpperCase());
	}

	@And("^I open the \"([^\"]*)\" icon on 'DASHBOARD' page$")
	public void iOpenIconOnDASHBOARD(String name) {
		CommonMethods.sleep(1000);
		dbPage.openIcon(name.toUpperCase());
	}

	@And("^I close the \"([^\"]*)\" icon on 'DASHBOARD' page$")
	public void iCloseIconOnDASHBOARD(String name) {
		dbPage.closeIcon(name.toUpperCase());
	}

	@And("^I change department to \"([^\"]*)\" or \"([^\"]*)\" on 'DASHBOARD' page$")
	public void iChangeDeptOnDASHBOARD(String dept1, String dept2) {
		if (!dbPage.changeDepartment(dept1)) {
			CommonMethods.sleep(1500);
			dbPage.changeDepartment(dept2);
		}
	}

	@And("^I change 'User Menu' to \"([^\"]*)\" on 'DASHBOARD' page$")
	public void iChangeUserMenuOnDASHBOARD(String menu) {
		if (menu.equalsIgnoreCase("Change dealership")) {
			menu = "Change dealership";
		} else if (menu.equalsIgnoreCase("Language")) {
			menu = "Language";
		} else if (menu.equalsIgnoreCase("Administration")) {
			menu = "Administration";
		}
		if (!dbPage.changeMenuName(menu)) {
			System.out.println(" Change Menu to " + menu);
		}
		CommonMethods.sleep(1500);
	}

	@And("^I select \"([^\"]*)\" language on 'DASHBOARD' page$")
	public void iSelectLanguageOnDASHBOARD(String lang) {
		if (!dbPage.selectLanguage(lang)) {
			System.out.println(" Select Language to " + lang);
		}
		CommonMethods.sleep(1500);
	}

	@And("^I select \"([^\"]*)\" available weekday on 'DASHBOARD' page$")
	public void iSelectWeekdayOnDASHBOARD(String order) {
		CommonMethods.sleep(500);
		dbPage.selectFutureDay(toBeNumber(order));
	}

	@And("^I get the transportation options status on 'DASHBOARD' page$")
	public void iGetTransportationOPtionsOnClientPage(DataTable dataTable) {
		List<String> options = dataTable.asList(String.class);
		CommonMethods.sleep(1000);
		int size = options.size();
		for (int i = 1; i < size; i++) {
			dbPage.setOptionsCount(options.get(i));
		}
	}

	@And("^I verify the transportation options count on 'DASHBOARD' page$")
	public void iVerifyTransportationOPtionsOnClientPage(DataTable dataTable) {
		List<String> options = dataTable.asList(String.class);
		CommonMethods.sleep(1000);
		int size = options.size();
		for (int i = 1; i < size; i++) {
			verifyTrue(" verify transportation option count  " + options.get(i) + "  ",
					dbPage.verifyOptionsCount(options.get(i)));
//              Assert.assertTrue(" verify transportation option count   " + options.get(i)+ "  ",dbPage.verifyOptionsCount(options.get(i)));
		}
	}

	@And("^I verify a new Appointment created on 'DASHBOARD' page$")
	public void iVerifyAppointmentCreatedOnDASHBOARD() {
		dbPage.waitDashboardPageLoad();
		CommonMethods.sleep(1500);
		String dbDate = dbPage.getApptDate();
		verifyTrue(" verify the date of a new Appointment created ",
				dbDate.contains(AM_TimeAndAdvisorPage.selectedDate));
		CommonMethods.sleep(1500);
		boolean eAppt = (dbPage.getApptElement() != null);
		verifyTrue(" verify the selected time of a new Appointment created ", eAppt);
	}

	@And("^I see a new Appointment created on 'DASHBOARD' page$")
	public void iSeeAppointmentCreatedOnDASHBOARD() {
		dbPage.waitDashboardPageLoad();
		CommonMethods.sleep(5000);
		String dbDate = dbPage.getApptDate();
		System.out.println(
				"************ Date of appointment is *********" + dbDate.contains(AM_TimeAndAdvisorPage.selectedDate));
		Assert.assertTrue(" verify the date of a new Appointment created ",
				dbDate.contains(AM_TimeAndAdvisorPage.selectedDate));
		CommonMethods.sleep(5000);
		boolean aAppt = (dbPage.getApptElement() != null);
		System.out.println("************ Time of appointment is *********" + aAppt);
		Assert.assertTrue(" verify the selected time of a new Appointment created ", aAppt);
	}

	@And("^I see \"([^\"]*)\" language tab on 'DASHBOARD' page$")
	public void iSeeLanguageOnDASHBOARD(String lang) {
		boolean see = true;
		dbPage.waitDashboardPageLoad();
		if (lang.toUpperCase().equals("FRENCH")) {
			see = (dbPage.getDayFrench() != null);
		}
		Assert.assertTrue(" verify select language " + lang + " tab on Dashboard ", see);
	}

	@And("^I do not see the new Appointment on 'DASHBOARD' page$")
	public void iDonotSeeTheAppointmentOnDASHBOARD() {
		dbPage.waitDashboardPageLoad();
		CommonMethods.sleep(5000);
		String dbDate = dbPage.getApptDate();
		Assert.assertTrue(" verify the date of a new Appointment created ",
				dbDate.contains(AM_TimeAndAdvisorPage.selectedDate));
		CommonMethods.sleep(5000);
		boolean noAppt = (AM_TimeAndAdvisorPage.selectedTime.isEmpty() || dbPage.isAppointmentDelete());
		Assert.assertTrue(" verify the selected time of a new Appointment created ", noAppt);
	}

	@And("^I see this Appointment not on 'DASHBOARD' page$")
	public void iSeeNoTheAppointmentOnDASHBOARD() {
		dbPage.waitDashboardPageLoad();
		CommonMethods.sleep(1500);
		boolean aAppt = (dbPage.getApptElement() == null);
		Assert.assertTrue(" verify the selected time of a Appointment not on screen ", aAppt);
	}

	@And("^I open this Appointment on 'DASHBOARD' page$")
	public void iOpenThisAppointmentOnDASHBOARD() {
		dbPage.waitDashboardPageLoad();
		CommonMethods.sleep(5000);	
		dbPage.openApptDetail();
		CommonMethods.sleep(500);
	}

	@Then("^I verify the AM Version number \"([^\"]*)\" on 'DASHBOARD' page$")
	public void iVerifyADAD_VersionNumberOnAC(String expectedVersionNo) {
		String pageSource = dbPage.getPage();
		verifyTrue(" \n verify AM version " + expectedVersionNo, pageSource.contains(expectedVersionNo));
//        Assert.assertTrue(" verify AM version " + expectedVersionNo , pageSource.contains(expectedVersionNo));

	}

	@And("^I check DMS Status as \"(.*)\" for this Appointment on 'DASHBOARD' page$")
	public void iCheckDMSStatusforthisAppointmentOnDashBoard(String status) {
		dbPage.verifyElementExists(status);
	}

	@And("^I signout from AM$")
	public void iSignOutAM() {
		dbPage.signOutAM();
	}

	@And("^I see \"([^\"]*)\" number for the specific available time slot on 'DASHBOARD' page$")
	public void iSeeCounterNumberOnDASHBOARD(String valueToBeCaptured) {
		CommonMethods.sleep(500);
		switch (valueToBeCaptured) {
		case "Counter":
			originalOpenCounter = dbPage.verifyCounterValue();
			System.out.println("Original counter value is "+ originalOpenCounter);
			break;
		}
	}
	
	@Then("^I see \"([^\"]*)\" number increased by 1 for the time slot at which appointment scheduled on 'DASHBOARD' page$")
	public void iSeeCounterNumberIncreasedOnDASHBOARD(String valueToBeCaptured) {
		CommonMethods.sleep(500);
		switch (valueToBeCaptured) {
		case "Counter":
			newOpenCounter = dbPage.verifyCounterValue();
			System.out.println("New open counter is "+newOpenCounter);
			Assert.assertTrue("Validate new Open appointment value is earlier value plus 1", (newOpenCounter == (originalOpenCounter + 1)));			
			break;
		}
	}	
	
	@Then("^I capture Appointment Number on 'DASHBOARD' page$")
	public void iCaptureAppointmentNumberOnDASHBOARD() {
		CommonMethods.sleep(500);
		dbPage.captureAppointmentNumber();
	}		

	@And("^I validate captured services on 'DASHBOARD' page$")
	public void iValidateCapturedServices() {
		CommonMethods.sleep(500);
		dbPage.validateServices();
	}	 
	
	@And("^I open walk-in Appointment on 'DASHBOARD' page$")
	public void iOpenWalkInAppointmentOnDASHBOARD() {
		dbPage.waitDashboardPageLoad();
		CommonMethods.sleep(5000);	
		dbPage.openWalkInAppt();
		CommonMethods.sleep(500);
	}	
	
}
