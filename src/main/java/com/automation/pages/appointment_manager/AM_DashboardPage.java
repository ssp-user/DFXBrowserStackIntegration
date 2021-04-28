package com.automation.pages.appointment_manager;

import com.automation.pages.advisor_checkin.ACI_CustomerPage;
import com.automation.pages.common.WebPage;
import com.automation.utils.elementUI.CSSUtils;
import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class AM_DashboardPage extends AM_FramePage {

	private static Logger log = Logger.getLogger(AM_DashboardPage.class);

	private static WebElement apptCustomerName, apptRemoveBtn, apptEditBtn, apptNum, apptCustomerName_Parent;
	private static String apptBooked = null;
	private static StringBuilder optionCount;
	public static String appointmentDate = null;
	public static String appointmentNumber = null;

	static int listId = -1;

	// locator
	// create button
	private static By bCreate = By.xpath("//a[@id = 'dashboard-create-bnt']");
	
	// appointment with name
	private static By bAppointmentWithName(String customerName) {
		String locator = "//div[@id='day-view-customer-name' and contains(text(),'" + customerName + "')]";
		return By.xpath(locator);
	}
	// appointment number
	private static By bAppointmentNumber = By.xpath("//div[@class='appointment-details__confirmation-code ng-binding']");
	// appointment edit with name
	private static By bEditOnAppointment = By.xpath("//a[contains(@ng-click,'editAppointment')]");
	// ***** import from testNg System element ******
	private static By bBodyPage = By.tagName("body");
	// private static WebElement header; // Header
	private static By bHeader = By.cssSelector("header .dropdown");
	// private static WebElement signoutLink; // Sign Out Link
	private static By bSignoutLinkLocator = By.cssSelector("ul.dropdown-menu a:nth-child(2)");
	// protected static WebElement emailSendErr;
	private static By bEmailSendErr = By.xpath("//h3[contains(text(),'Unable to send')]");
	// protected static WebElement goToDashboard;
	private static By bGoToDashboard = By.xpath("//a[contains(text(),'Go to Dashboard')]");
	// protected static WebElement userMenuTitle; // User Menu Title
	private static By bUserMenuTitle = By.cssSelector("button.user-menu-part small");
	// private static WebElement newCustAppointmentBtn;
	private static By bNewCustAppointmentBtn = By.cssSelector("button-el ng-isolate-scope");
	// private static WebElement signoutIcon; // Sign out Icon for 2.9
	private static By bSignoutIcon = By.cssSelector("a.signout");

//    private static WebElement addAppointmentBtn;																						// Add new appointment button
	private static By bAddAppointmentBtn = By.cssSelector(".btn.add-apps.nav-trigger");
	// private static WebElement spinner; // Page Load spinner
	private static By bSpinnerLocator = By.cssSelector(".radial-loader.loader_spiner");
	// private static WebElement innerSpinner;
	private static By bInnerSpinnerLocator = By.cssSelector(".loader_innder");
	// private static WebElement bookedSpan; // Span to the # of appointment booked
	private static By bBookedSpanLocator = By.cssSelector("div.vc-target span.booked_n");
	// private static WebElement lastTimeSlot; // Last Time slot
	private static By bLastTimeSlot = By
			.cssSelector("section:last-child > .new-time-segment_time.text-center.ng-binding");
	// private static WebElement dealerClosedMsg; // Dealer Closed Message title to
	// verify page is finished loading
	private static By bDealerClosedMsg = By.cssSelector("div[ng-show*='!departmentOpen'] [class*='-lock']");
	// private static WebElement dealerHours; // Dealer Hours = Dealer closed
	private static By bDealerHours = By.cssSelector("#day .description ul");
	// private static WebElement nextWeek; // Next Week
	private static By bNextWeek = By.cssSelector("a[ng-click*='nextWeek()']");
	// private static WebElement nextDate; // Next Date
	private static By bNextDate = By.cssSelector("li:nth-child(5) #date-picker-choose-date-days");
	// private static List<WebElement> dateList; // Dashboard Date List
	private static By bDateList = By.cssSelector("#date-picker-choose-date-days");
	// protected static WebElement timePage;
	private static By bTimePage = By.cssSelector("//li[@heading='Time & Advisor']");
	// protected static List<WebElement> timeSlotList; // Time Slot List
	private static By bTimeSlotList = By.cssSelector(".new-time-segment_time");
	// private static WebElement div; // Div to scroll
	private static By bDiv = By.cssSelector("section.data-container div.scrollable-inner");

	// Relative WebElement based on Selected Timeslot
	protected static By appointmentLocator = By
			.xpath(".//following-sibling::section//div[contains(@class, 'appointment_name')]");
	// Relative WebElement based on Selected Appointment Customer Name
	protected static By removeApptLocator = By
			.xpath("./../..//following-sibling::button[contains(@class, 'appointment_controls_delete')]"); // Before 2.9
	protected static By removeApptLocator_29 = By.xpath(
			"./../../../../../../following-sibling::div//div[contains(@class, '_delete')]/button[contains(@class, '-red-')]"); // 2.9
	protected static By removeApptLocator_2_13 = By
			.xpath("./../../../../../../following-sibling::div//*[contains(@class, '_delete')]"); // 2.13

	protected static By removeAppt_ImgSVG = By.cssSelector("svg[class*='trash']");

	// Relative WebElement based on Selected Appointment Customer Name
	protected static By bookedApptLocator = By.xpath(".//preceding-sibling::span[contains(@class, 'count')]/i");

	// Relative WebElement based on Selected Appointment Customer Name
	protected static By editApptLocator = By
			.xpath("./../..//following-sibling::button[contains(@class, 'appointment_controls_edit')]"); // Before 2.9
	protected static By editApptLocator_29 = By.xpath(
			"./../../../../../../following-sibling::div//div[contains(@class, '_edit')]/button[contains(@class, '-blue-')]"); // 2.9
	protected static By editApptLocator_2_13 = By
			.xpath("./../../../../../../following-sibling::div//*[contains(@class, '_edit')]"); // 2.13

	protected static By editAppt_ImgSVG = By.cssSelector("svg[class*='edit']");
	protected static By figureApptLocator = By.xpath("./../../../../../.."); // Parent WebElement of ApptCustomerName

	// protected static WebElement statIcon; // Open Statistics Icon
	private static By bStatIcon = By.id("stat-trigger");
	// protected static WebElement closeStatIcon; // Close Statistics Icon
	private static By bCloseStatIcon = By.cssSelector("button.c-quick-stats__closed");
	// protected static WebElement transOptionText;
	private static By bTransOptionText = By.cssSelector("div.c-quick-stats__title:nth-child(1)");
	private static By bWalkInAppt = By.xpath("//div[@class='appointment-details__abbr ng-isolate-scope']/div");
	// protected static WebElement waiterCount;
	private static By bWaiterCount = By
			.xpath("//div[@class='c-stacked-bar__value hc-black ng-scope' and contains(@ng-if,'waiter')]/span");
	// protected static WebElement shuttleCount;
	private static By bShuttleCount = By
			.xpath("//div[@class='c-stacked-bar__value hc-black ng-scope' and contains(@ng-if,'needShuttle')]/span");
	// protected static WebElement rentalCount;
	private static By bRentalCount = By
			.xpath("//div[@class='c-stacked-bar__value hc-black ng-scope' and contains(@ng-if,'needRental')]/span");
	// protected static WebElement loanerCount;
	private static By bLoanerCount = By
			.xpath("//div[@class='c-stacked-bar__value hc-black ng-scope' and contains(@ng-if,'needLoaner')]/span");
	// protected static WebElement ownRideCount;
	private static By bOwnRideCount = By
			.xpath("//div[@class='c-stacked-bar__value hc-black ng-scope' and contains(@ng-if,'ownRider')]/span");
	// protected static WebElement pickupCount;
	private static By bPickupCount = By
			.xpath("//div[@class='c-stacked-bar__value hc-black ng-scope' and contains(@ng-if,'needPickup')]/span");
	// protected static WebElement repeatRepairCount;
	private static By brepeatRepairCount = By
			.cssSelector("section.c-mono-stat:nth-child(1)  div.c-mono-stat__counter-inner");
	// protected static WebElement specialPartsCount;
	private static By bSpecialPartsCount = By
			.cssSelector("section.c-mono-stat:nth-child(2)  div.c-mono-stat__counter-inner");
	// protected static WebElement customerName;
	private static By bCustomerName = By.xpath("//div[@name='day-view-customer-name-name']");
	// protected static WebElement customerPhone;
	private static By bCustomerPhone = By.xpath("//div[@name='day-view-customer-phone-name']");
	// protected static WebElement french;
	private static By bFrench = By.xpath("//a[contains(text(),'French')]");
	private static By bFrench1 = By.xpath("//a[contains(text(),'Français')]");
	// protected static WebElement language;
	private static By bLanguage = By.xpath("//ul[@id='user_menu']//a[contains(text(),'Language')]");
	// protected static WebElement selectLang;
	private static By bSelectLang = By.xpath("//h2[contains(text(),'Select your language')]");
	// protected static WebElement dayInFrench;
	private static By bDayInFrench = By.xpath("//a[contains(text(),'Journée')]");
	// public static WebElement selectDay;
	private static By bSelectDay = By.cssSelector("div.dashboard-nav a[ng-click*='main.dashboard.day']");
	// protected static WebElement selectWeek;
	private static By bSelectWeek = By.cssSelector("div.dashboard-nav a[ng-click*='main.dashboard.week']");
	// protected static WebElement selectMonth;
	private static By bSelectMonth = By.cssSelector("div.dashboard-nav a[ng-click*='main.dashboard.month']");
	// protected static WebElement selectList;
	private static By bSelectList = By.cssSelector("div.dashboard-nav a[ng-click*='main.dashboard.list']");
	// protected static WebElement monthView;
	private static By bMonthView = By.xpath("//div[@class='content-signatures']//div[contains(text(),'Sun')]");
	// protected static WebElement weekView;
	private static By bWeekView = By.xpath("//div[@class='list-section']//span[contains(text(),'Previous month')]");
	// protected static WebElement previousweek;
	private static By bPreviousweek = By.xpath("//div[@class='list-section']//span[contains(text(),'Previous week')]");
	// protected static WebElement printBtn;
	private static By bPrintBtn = By.xpath("//span[contains(text(),'Print')]");
	// protected static WebElement mainTitleForAudi;
	private static By bMainTitleForAudi = By.xpath("//button[contains(@title,'Main')]");
	// protected static WebElement mainTitle;
	private static By bMainTitle = By.xpath("//button[@title='Tires']");
	// protected static WebElement mainToExpress;
	private static By bMainToExpress = By.xpath("//button[contains(@title,'Express')]");
	// protected static WebElement mainExecutiveForCRYQA;
	private static By bMainExecutiveForCRYQA = By.xpath("//button[@title='Chrysler QA - Service']");
	// protected static WebElement deptDropDown;
	private static By bDeptDropDown = By.xpath("//ul[@id='departments_dropdown']");
	// protected static List<WebElement> deptDropDownOptions;
	private static By bDeptDropDownOptions = By.xpath("//ul[@id='departments_dropdown']/li/button/span");
	// protected static List<WebElement> appointmentTimeList;
	private static By bAppointmentTimeList = By
			.xpath("//div[@id='search']/ui-view/div/section/div[4]/div/section/div/div");
	// protected static List<WebElement> appointmentDateList;
	private static By bAppointmentDateList = By
			.xpath("//div[@id='search']/ui-view/div/section/div[4]/div/section[@data-date]");
	// protected static WebElement changeDealership;
	private static By bChangeDealership = By.xpath("//ul[@id='user_menu']//a[contains(text(),'Change dealership')]");
	// protected static WebElement bDbStatusCompleted;
	private static By bDbStatusCompleted = By.xpath(
			"//div[@class='appointment-details__confirmation-code ng-binding']//span[@class='c-dms-state__button c-dms-state__button--ok']");
	// protected static WebElement bDbStatusError;
	private static By bDbStatusError = By.xpath(
			"//div[@class='appointment-details__confirmation-code ng-binding']//span[@class='c-dms-state__button c-dms-state__button--error']");
    private static By bSelectedServices = By.xpath("//tr/td[@class='appointment-details__table-left__title first ng-binding']");
	private By getOptionByName(String name) {
		By option = null;
		if (name.equals("WAITER")) {
			option = By
					.xpath("//div[@class='c-stacked-bar__value hc-black ng-scope' and contains(@ng-if,'waiter')]/span");
		} else if (name.equals("OWNRIDE")) {
			option = By.xpath(
					"//div[@class='c-stacked-bar__value hc-black ng-scope' and contains(@ng-if,'ownRider')]/span");
		} else if (name.equals("SHUTTLE")) {
			option = By.xpath(
					"//div[@class='c-stacked-bar__value hc-black ng-scope' and contains(@ng-if,'needShuttle')]/span");
		} else if (name.equals("LOANER")) {
			option = By.xpath(
					"//div[@class='c-stacked-bar__value hc-black ng-scope' and contains(@ng-if,'needLoaner')]/span");
		} else if (name.equals("RENTAL")) {
			option = By.xpath(
					"//div[@class='c-stacked-bar__value hc-black ng-scope' and contains(@ng-if,'needRental')]/span");
		} else if (name.equals("REPEATREPAIR")) {
			option = By.cssSelector("section.c-mono-stat:nth-child(1)  div.c-mono-stat__counter-inner");
		} else if (name.equals("PICKUP")) {
			option = By.xpath(
					"//div[@class='c-stacked-bar__value hc-black ng-scope' and contains(@ng-if,'needPickup')]/span");
		} else if (name.equals("SPAECIALPARTS")) {
			option = By.cssSelector("section.c-mono-stat:nth-child(2)  div.c-mono-stat__counter-inner");
		} else {
			//
		}
		return option;
	}

	// protected static WebElement dealershipName;
	private static By bDealershipName = By.xpath("//ul[@id='user_menu']//h1");

	// ***** end of Import Testng element *********

	// departName
	private static By bDepartmentName(String name) {
		String locator = String.format("//button[contains(@title,'%s')]", name);
		return By.xpath(locator);
	}

	// DealerName
	private static By bUserName(String name) {
		String locator = String.format("//ul[@id='user_menu']//a[contains(text(),'%s')]", name);
		return By.xpath(locator);
	}

	// Return counter count
	private static int count() {
		int counter;
		String strTime = AM_TimeAndAdvisorPage.selectedTime;
		List<WebElement> timeSlotList = driver.findElements(bTimeSlotList);
		counter = CommonMethods.getListIndexNum(timeSlotList, strTime);
		String counterPath = "(//span[@class='new-time-segment_serv-element new-time-segment_serv-element_open ng-binding ng-scope'])["+ (counter + 1) + "]";
		System.out.println("xpath of counter is **" + counterPath);
		WebElement openSlots = driver.findElement(By.xpath(counterPath));
		// Create object of a JavascriptExecutor interface
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// use executeScript() method and pass the arguments
		// Here pass values based on css style. Yellow background color with solid red color border.
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", openSlots);
		return Integer.parseInt((openSlots.getText().substring(5)).trim());
	}	

	public void clickAppointmentWithName() {
		clickElementWithException(bAppointmentWithName(tp.appointmentCustomerName));
	}

	public void clickEditOnAppointment() {
		clickElementWithException(bEditOnAppointment);
	}

	public boolean isBodyPageContains(String classValue) { // Check if a VIN is new Vehicle or existing Vehicle
//        waitForLoadingCompletedOnAdvisorCheckIn();
//        waitSpinnerInvisible();
		return webElementHasClass(bBodyPage, classValue);
	}

	public void waitDashboardPageLoad() { // Go to Customer Tab
		setWait();
//        waitSpinnerInvisible();
		waitDashboardPageReady();
	}

	private void waitDashboardPageReady() {
		lWait.until(SyncPage.condDomReadyState());
		lWait.until(ExpectedConditions.visibilityOfElementLocated(bAddAppointmentBtn));
		lWait.until(ExpectedConditions.invisibilityOfElementLocated(bSpinnerLocator));
		lWait.until(SyncPage.condPageLoaded(bLastTimeSlot, bDealerClosedMsg));
	}

	public String getUserName() { // Get the Login User Name - for valid login user and the user created
		String userTitle = driver.findElement(bUserMenuTitle).getText().trim();
		String userName = userTitle.substring(0, userTitle.indexOf("as")).trim();
		return userName;
	}

	public String getDealerName() { // Get the Login User Name - for valid login user and the user created
		String userTitle = driver.findElement(bDealershipName).getText().trim();
		return userTitle;
	}

	public WebElement getDayFrench() { // Get the Login User Name - for valid login user and the user created
		WebElement element = null;
		element = pWait.until(conditionVisible(bDayInFrench));
		return element;
	}

	public void clickButton(String name) {
		switch (name) {
		case "NEXT WEEK":
			pWait.until(conditionVisible(bNextWeek)).click();
			break;
		case "CREATE":
			clickElementWithException(bCreate);
			break;
		case "PREVIOUS WEEK":
			pWait.until(conditionVisible(bPreviousweek)).click();
			break;
		default:
			return;
		}
	}

	public void clickTab(String name) {
		switch (name) {
		case "DAY":
			pWait.until(conditionVisible(bSelectDay)).click();
			break;
		case "WEEK":
			pWait.until(conditionVisible(bSelectWeek)).click();
			break;
		case "MONTH":
			pWait.until(conditionVisible(bSelectMonth)).click();
			break;
		case "LIST":
			pWait.until(conditionVisible(bSelectList)).click();
			break;
		case "SEARCH":
			break;
		default:
			return;
		}
	}

	public void openIcon(String name) {
		switch (name) {
		case "TRANSPORTATION OPTIONS":
			if (!SyncPage.isElementPresentAndVisible(bTransOptionText)) {
				pWait.until(conditionVisible(bStatIcon)).click();
				sleep(5000);
				pWait.until(conditionVisible(bTransOptionText));
			}
			break;
		case "SEARCH":
			break;
		default:
		}
	}

	public void closeIcon(String name) {
		switch (name) {
		case "TRANSPORTATION OPTIONS":
			if (SyncPage.isElementPresentAndVisible(bCloseStatIcon)) {
				clickElementTimesTillDisappear(bCloseStatIcon, 3);
				sleep(500);
			}
			break;
		case "SEARCH":
			break;
		default:
		}
	}

	public WebElement getViewElement(String name) {
		WebElement element = null;
		try {
			switch (name) {
			case "DAY":
				element = pWait.until(conditionVisible(bPreviousweek));
				break;
			case "WEEK":
				element = pWait.until(conditionVisible(bWeekView));
				break;
			case "MONTH":
				element = pWait.until(conditionVisible(bMonthView));
				break;
			case "LIST":
				element = pWait.until(conditionVisible(bPreviousweek));
				element = pWait.until(conditionVisible(bPrintBtn));
				break;
			case "SEARCH":
				break;
			}
		} catch (TimeoutException te) {
			element = null;
		}
		return element;
	}

	public boolean changeDepartment(String name) {
		pWait.until(conditionVisible(bDeptDropDown)).click();
		sleep(1500);
		try {
			driver.findElement(bDepartmentName(name)).click();
//            pWait.until(conditionVisible(bDepartmentName(name))).click();
			return true;
		} catch (NoSuchElementException e) {
//            driver.findElement(bDeptDropDown).click();
			pWait.until(conditionVisible(bDeptDropDown)).click();
			return false;
		}
	}

	public boolean changeMenuName(String name) {
		pWait.until(conditionVisible(bUserMenuTitle)).click();
		sleep(1500);
		try {
			driver.findElement(bUserName(name)).click();
//            pWait.until(conditionVisible(bDepartmentName(name))).click();
			return true;
		} catch (NoSuchElementException e) {
//            driver.findElement(bDeptDropDown).click();
			pWait.until(conditionVisible(bUserMenuTitle)).click();
			return false;
		}
	}

	public boolean selectLanguage(String name) {
		boolean lang = false;
		pWait.until(conditionVisible(bFrench));
		switch (name.toUpperCase()) {
		case "ENGLISH":
			break;
		case "SPANISH":
			break;
		case "FRENCH":
			if (driver.findElements(bFrench).size() > 0) {
				driver.findElement(bFrench).click();
				lang = true;
			} else if (driver.findElements(bFrench1).size() > 0) {
				driver.findElement(bFrench).click();
				lang = true;
			}
			break;
		}
		return lang;
	}

	public void selectFutureDay(int order) {
		WebElement openDate = null;
		List<WebElement> dateList = driver.findElements(bDateList);
		int i = 0;
		for (WebElement date : dateList) {
			System.out.println(date.getText());
			if (date.getCssValue("cursor").contains("pointer") && !date.getText().contains("Sun")
					&& !date.getText().contains("Sat")) {
				i++;
				if (i == order) {
					openDate = date;
					break;
				}
			}
		}
		jsClick(openDate);
		waitDashboardPageReady();
	}

	public String getApptDate() {
		dWait.until(conditionVisible(bSignoutIcon));
		WebElement date = null;
		String color = null, appDate = "";
		List<WebElement> dateList = driver.findElements(bDateList);
		for (int i = 0; i < dateList.size(); i++) {
			date = dateList.get(i);
			color = CSSUtils.getCssColorValue(date);
			if (color.contains("0, 0, 0, 1")) {
				appDate = date.getText();
				int a = appDate.indexOf(" ");
				appDate = appDate.substring(a + 1);
			}
		}
		return appDate;
	}

	// Return Customer Name for Appointment for Selected Time Slot
	private WebElement seekApptElement() {
		String strTime = AM_TimeAndAdvisorPage.selectedTime;
		List<WebElement> timeSlotList = driver.findElements(bTimeSlotList);
		listId = CommonMethods.getListIndexNum(timeSlotList, strTime);
//        System.out.println(" listId  is  .. ..  :  " + listId  );
		if (listId < 0) {
			return null;
		}

		List<WebElement> apptList = timeSlotList.get(listId).findElements(appointmentLocator);
//        System.out.println(" apptList size  .. ..  :  " + apptList.size() );
		if (apptList.size() == 0) {
			return null;
		}

		int idx = CommonMethods.getListIndexNum(apptList, customerName);
//        System.out.println(" appts for the customer in this  Time slot index is .. ..  :  " + idx );
		if (idx < 0) {
			return null;
		}

		return apptList.get(idx);
	}

	// Return Customer Name for Appointment for Selected Time Slot
	public WebElement getApptElement() {
		apptCustomerName = seekApptElement();
		return apptCustomerName;
	}

	public boolean isAppointmentDelete() {
		pWait.until(SyncPage.condWebElementChangeStringValueFrom(apptNum, apptBooked));
		boolean result = (seekApptElement() == null);
		return result;
	}

	// Return Number of Booked Appointment for Selected Time Slot
	public WebElement getBookedAppt() {
//        WebElement eAppt = null;
		String strTime = AM_TimeAndAdvisorPage.selectedTime;
		List<WebElement> timeSlotList = driver.findElements(bTimeSlotList);
//        if (listId == -1) {
//            listId = CommonMethods.getListIndexNum(timeSlotList, strTime);
//        }
		listId = CommonMethods.getListIndexNum(timeSlotList, strTime);

		return timeSlotList.get(listId).findElement(bookedApptLocator);
	}

	public void openApptDetail() {
		updateApptNum();
		apptBooked = apptNum.getText().trim();
		WebElement eDiv = driver.findElement(bDiv);
		CommonMethods.scrollToTopOfDiv(driver, eDiv);
		CommonMethods.scrollElementIntoCenterOfView(apptCustomerName, eDiv);
		int i = 0;
		while (!CSSUtils.getClassAttributeValue(apptCustomerName_Parent).contains("more-info-panel") && i < 50) {
			jsClick(apptCustomerName);
			sleep(4000);
			i++;
		}
		if (i >= 50) {
			System.err.println("Try over 50 times on opening appointment detail panel");
		}

	}

	public void updateApptNum() {
		apptCustomerName = seekApptElement();
		apptNum = getBookedAppt();
		apptCustomerName_Parent = apptCustomerName.findElement(figureApptLocator);
	}

	private void retrieveElement() {
		apptCustomerName = getApptElement();
//        System.out.println("Update Element Completed.");
		WebElement removeDIV = apptCustomerName.findElement(removeApptLocator_2_13);
		apptRemoveBtn = removeDIV.findElement(removeAppt_ImgSVG);
		WebElement editDIV = apptCustomerName.findElement(editApptLocator_2_13);
		apptEditBtn = editDIV.findElement(editAppt_ImgSVG);
		updateApptNum();
	}

	public void editAppointment() { // Edit Appointment
		retrieveElement();
		sleep(1000);
//        pWait.until(conditionVisible(apptEditBtn)).click();
		log.info("Open the Appointment to click the edit button");
		boolean edit = false;
		int i = 0;
		do {
			try {
				browserClick(apptEditBtn);
				edit = true;
			} catch (WebDriverException e) {
				log.error("WebDriver Exception Occurred while chilcking the appt Edit Button" + e.getMessage());
				WebElement eDiv = driver.findElement(bDiv);
				CommonMethods.scrollElementIntoCenterOfView(apptRemoveBtn, eDiv);
				edit = false;
			}
			i++;
		} while (!edit && i < 8);
	}

	public void deleteAppointment() { // Click Appt Remove Button // Delete Appointment
		retrieveElement();
		log.info("Open the Appointment to click the remove button");
		boolean removed = false;
		pWait.until(ExpectedConditions.visibilityOf(apptRemoveBtn));
		int i = 0;
		do {
			try {
				browserClick(apptRemoveBtn);
				removed = true;
			} catch (WebDriverException e) {
				log.error("WebDriver Exception Occurred while clicking the appt remove button" + e.getMessage());
				WebElement eDiv = driver.findElement(bDiv);
				CommonMethods.scrollElementIntoCenterOfView(apptRemoveBtn, eDiv);
				removed = false;
			}
			i++;
		} while (!removed && i < 8);
	}

	private int getOptionCount(By name) {
		try {
			String snum = driver.findElement(name).getText().trim();
			return Integer.valueOf(snum);
		} catch (WebDriverException we) {
			return 0;
		}
	}

	private int getRPOptionCount(By name) {
		String snum = pWait.until(conditionVisible(name)).getAttribute("data-value").trim();
		return Integer.valueOf(snum);
	}

	public void setOptionsCount(String name) {
		name = name.toUpperCase();
		if (name.contains("WAITER")) {
			waiter = getOptionCount(bWaiterCount);
		} else if (name.contains("OWN")) {
			ownRide = getOptionCount(bOwnRideCount);
		} else if (name.contains("SHUTTLE")) {
			shuttle = getOptionCount(bShuttleCount);
		} else if (name.contains("LOANER")) {
			loaner = getOptionCount(bLoanerCount);
		} else if (name.contains("RENTAL")) {
			rental = getOptionCount(bRentalCount);
		} else if (name.contains("PICK")) {
			pickup = getOptionCount(bPickupCount);
		} else if (name.contains("REPAIR")) {
			repeatRepair = getRPOptionCount(brepeatRepairCount);
		} else if (name.contains("PARTS")) {
			specialParts = getRPOptionCount(bSpecialPartsCount);
		} else {
			//
		}
	}

	public boolean verifyOptionsCount(String name) {
		name = name.toUpperCase();
		if (name.contains("WAITER")) {
//            System.out.println(" waiter == getOptionCount(bWaiterCount) " + waiter + " " +getOptionCount(bWaiterCount));
			return (waiter == getOptionCount(bWaiterCount));
		} else if (name.contains("OWN")) {
			return (ownRide == getOptionCount(bOwnRideCount));
		} else if (name.contains("SHUTTLE")) {
			return (shuttle == getOptionCount(bShuttleCount));
		} else if (name.contains("LOANER")) {
			return (loaner == getOptionCount(bLoanerCount));
		} else if (name.contains("RENTAL")) {
			return (rental == getOptionCount(bRentalCount));
		} else if (name.contains("PICK")) {
			return (pickup == getOptionCount(bPickupCount));
		} else if (name.contains("REPAIR")) {
			return (repeatRepair == getRPOptionCount(brepeatRepairCount));
		} else if (name.contains("PARTS")) {
			return (specialParts == getRPOptionCount(bSpecialPartsCount));
		} else {
			return false;
		}
	}

	public String getPage() {
		return driver.getPageSource();
	}

	public void verifyElementExists(String status) {
		switch (status) {
		case "Completed":
			CommonMethods.verifyElementExists(driver.findElement(bDbStatusCompleted));
			break;
		case "Error":
			CommonMethods.verifyElementExists(driver.findElement(bDbStatusError));
			break;
		default:
			break;
		}
	}

	/*
	 * public int verifyCounterValue(String status) { switch (status) { case
	 * "OriginalCounter": return count();
	 * System.out.println("Original counter Value is -->"+ originalOpenCounter);
	 * break; case "NewCounter": newOpenCounter =count();
	 * System.out.println("New counter Value is -->"+ newOpenCounter); break;
	 * default: break; } }
	 */
	
	public int verifyCounterValue() {
		return count();
	}	

	public void captureAppointmentNumber() {
		appointmentNumber = getElementTextWithException(bAppointmentNumber).trim();
		System.out.println("Appointment Number is "+appointmentNumber);
	}		

	public void validateServices() {
		String selectedServicesOnDB = getElementTextWithException(bSelectedServices).trim();
		System.out.println("Selected service on DB is "+selectedServicesOnDB);
		System.out.println("Selected service from previous screen is "+AM_ServicesPage.selectedUnScheduledService);		
        Assert.assertEquals(selectedServicesOnDB, AM_ServicesPage.selectedUnScheduledService);		
	}		
	public void openWalkInAppt() {
		System.out.println("Walk-in appointment customer name is "+ ACI_CustomerPage.getCustomerName());
		apptCustomerName = driver.findElement(By.xpath("//div[text()='"+ACI_CustomerPage.getCustomerName()+"']"));
		//apptNum = getBookedAppt();
		apptCustomerName_Parent = apptCustomerName.findElement(figureApptLocator);
		
		
		
		//apptBooked = apptNum.getText().trim();
		WebElement eDiv = driver.findElement(bDiv);
		CommonMethods.scrollToTopOfDiv(driver, eDiv);
		CommonMethods.scrollElementIntoCenterOfView(apptCustomerName, eDiv);
		int i = 0;
		while (!CSSUtils.getClassAttributeValue(apptCustomerName_Parent).contains("more-info-panel") && i < 50) {
			jsClick(apptCustomerName);
			sleep(4000);
        	CommonMethods.verifyElementExists(driver.findElement(bWalkInAppt));  				
			i++;
		}
		if (i >= 50) {
			System.err.println("Try over 50 times on opening appointment detail panel");
		}

	}	
}
