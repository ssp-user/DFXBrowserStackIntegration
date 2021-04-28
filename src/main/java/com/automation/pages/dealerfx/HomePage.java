package com.automation.pages.dealerfx;

import com.automation.pages.PageManager;
import com.automation.pages.customer_connect.CC_DashboardPage;
import com.automation.pages.payment.CreateInvoicePage;
import com.automation.utils.otherUtils.CommonMethods;
import com.automation.utils.sync.SyncPage;
import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.automation.pages.common.WebPage;

public class HomePage extends WebPage {

//    @FindBy(id="banner")
//    private static WebElement bannerFrame;																						// Banner Frame
    private static By bBannerFrame = By.id("banner");
    private static By bSwitchDealerWindow = By.id("impersonate"); 
    private static By bSwitchDealerBtn = By.xpath("//span[@class='delearInfo ng-star-inserted']");    
    private static By bMoreBtn = By.xpath("//div/img[contains(@src,'assets/images/nav/more.svg')]");    
    private static By bMoreOMMBtn = By.xpath("//a[text()='Online Maintenance Menu']");    
    private static By bMoreTechInspectionBtn = By.xpath("//a[text()='Technician Inspection']");
    private static By bMorePerSerWelBtn = By.xpath("//a[text()='Personalized Service Welcome']");
    private static By bMoreCustMgmtBtn = By.xpath("//a[text()='Customer Management']");    
    private static By bChatIconBtn = By.xpath("//span[@class='material-icons']");    

//    @FindBy(id = "contents")      //  Dealer Name
//    private static WebElement frameCont1;
    private static By bFrameCont = By.id("contents");

//    @FindBy(id = "banner")      //  Dealer Name
//    private static WebElement frameBan1;

//    @FindBy(id = "FrameworkMain")      //  Dealer Name
    private static By bFrameMain = By.id("FrameworkMain");
//    private static WebElement frameMain = driver.findElement(bFrameMain);

//    @FindBy(id="txtSearchDealerCode")
//    private static WebElement dealerCode1;
    private static By bDealerCode = By.id("txtSearchDealerCode");


//    @FindBy(css = ".whitetxt>a[onclick*='ImpersonatePage']")   // Select dealerShip link Css
//    private static WebElement threeDots;
    private static By bThreeDots = By.cssSelector(".whitetxt>a[onclick*='ImpersonatePage']");

//    @FindBy(xpath = ".//a[contains(text(),'Click Here to Select')]")   // Select dealerShip link
//    private static WebElement selectDealerLink;
    private static By bSelectDealerLink = By.xpath(".//a[contains(text(),'Click Here to Select')]");

//    @FindBy(id = "txtSearchDealerName")      //  Dealer Name
//    private static WebElement txtdName1;
    private static By bTxtdName = By.id("txtSearchDealerName");
    private static By blblChangeDealer = By.id("changeFocusLabel");    

    private static By bTitle = By.xpath("//tr[@class='SelectedLine']/td[3]");

//    @FindBy(id = "btnSignOut")      //  Dealer Name
//    private static WebElement logoutBtn1;
    private static By bLogout = By.id("btnSignOut");

//    @FindBy(id = "txtSearchDealerCode")      // dealer Code
//    private static WebElement txtdCode;

//    @FindBy(id = "btnSubmit")     // Dealr search Button
//    private static WebElement searchBtn1;
    private static By bSearchBtn = By.id("btnSubmit");

//    @FindBy(xpath = "//table[@id='dImpersonate']/tbody/tr[2]")     // first line of Dealer
//    private static WebElement firstDealer1;
    private static By bFirstDealer = By.xpath("//table[@id='dImpersonate']/tbody/tr[2]");

//    @FindBy(id = "btnOK")     // select button
//    private static WebElement selectBtn1;
    private static By bSelectBtn = By.id("btnOK");

//    @FindBy(xpath = ".//*[@id='form1']/table/tbody/tr[2]/td[2]")     // select button
//    private static WebElement titleWelcome1;
    private static By bWelcome = By.xpath(".//*[@id='form1']/table/tbody/tr[2]/td[2]");

    //use to detect if login user is MTI admin
    private static By bMTIAdmin = By.xpath("//td[contains(text(),'Administration')]");

    private static By bNissanService = By.xpath("//td[contains(text(),'Nissan')]");

    private static By bNissanServiceDashboard = By.xpath("//td[contains(text(),'Nissan')]/parent::tr/parent::tbody/parent::table/parent::div/following::div[1]//td[contains(text(),'Service Dashboard')]");

    private static By bToyotaService = By.xpath("//td[contains(text(),'Toyota Service')]");

    private static By bToyotaServiceDashboard = By.xpath("//td[contains(text(),'Toyota Service')]/parent::tr/parent::tbody/parent::table/parent::div/following-sibling::div[1]//td[contains(text(),'Service Dashboard')]");

    private static By bServiceAssistServiceDashboard = By.xpath("//td[contains(text(),'Service Assist')]/../../../../following-sibling::div//td[contains(text(),'Service Dashboard')]");

    private static By bTechInspectionLink = By.xpath(".//td[contains(text(),'Technician Inspection')]");

    private static By bServiceAssistTechInspection = By.xpath("//td[contains(text(),'Service Assist')]/parent::tr/parent::tbody/parent::table/parent::div/following-sibling::div//td[contains(text(),'Technician Inspection')]");

    private static By bToyotaTechInspection = By.xpath("//td[contains(text(),'Toyota Service')]/parent::tr/parent::tbody/parent::table/parent::div/following-sibling::div[1]//td[contains(text(),'Technician Inspection')]");

    private static By bVWService = By.xpath("//td[contains(text(),'VW Service Drive')]");

    private static By bVWServiceDashboard = By.xpath("(//td[contains(text(),'Service Dashboard')])[7]");

    private static By bServiceAssist = By.xpath("//td[contains(text(),'Service Assist')]");

    private static By bServiceDashboard = By.xpath("//td[contains(text(),'Service Dashboard')]");

    private static By bToyotaAdvisorCheckIn = By.xpath("(.//tbody//td[text()='Advisor Check-In'])[2]");

//    @FindBy(xpath = ".//tbody//td[text()='Advisor Check-In']")  // select TD App
//    private static WebElement advisorCheckInLink;
    private static By bAdvisorCheckInLink = By.xpath(".//tbody//td[text()='Advisor Check-In']");

    private static By bWiAdvisor = By.xpath("//td[text()='wiADVISOR Tools']");

    private static By bWiAdvisorCustomerConnect = By.xpath("//td[text()='wiADVISOR Tools']/../../../../following-sibling::div//td[text()='Customer Connect']");

    private static By bWiAdvisorTechnicianDashboard = By.xpath("//td[text()='wiADVISOR Tools']/../../../../following-sibling::div//td[text()='Technician Dashboard']");

    private static By bWiAdvisorTechnicianInspection = By.xpath("//td[text()='wiADVISOR Tools']/../../../../following-sibling::div//td[text()='Technician Inspection']");

    private static By bMarketingTool = By.xpath("//td[text()='Marketing Tools']");

    private static By bMarketingToolCustomerConnect = By.xpath("//td[contains(text(),'Marketing Tools')]/parent::tr/parent::tbody/parent::table/parent::div/following-sibling::div//td[contains(text(),'Customer Connect')]");

    private static By bMarketingToolServiceDashboard = By.xpath("//td[contains(text(),'Marketing Tools')]/parent::tr/parent::tbody/parent::table/parent::div/following::div[1]//td[contains(text(),'Service Dashboard')]");

    private static By bMarketingToolAppointmentManager = By.xpath("//td[contains(text(),'Marketing Tools')]/../../../../following-sibling::div//td[contains(text(),'Appointment Manager')]");

    private static By bMarketingToolAdvisorCheckIn = By.xpath("//td[text()='Marketing Tools']/../../../../following-sibling::div//td[text()='Advisor Check-In']");

    private static By bAppointmentManager = By.xpath("//td[text()='Appointment Manager']");
    
    private static By bSideBarTextAppointmentManager = By.xpath("//div[text()='Appointment Manager']");    

    private static By bGuestConnectLink = By.xpath(".//tbody//td[text()='Guest Connect']");

    private static By bCustomerConnectLink = By.xpath(".//tbody//td[text()='Customer Connect']");

    private static By bProfile = By.xpath("//td[text()='Profile']");
    
    private static By bSideBarPayments = By.xpath("//img[@src='/portal/dist-home/assets/images/nav/attach_money.svg']");    

    private static By bSideBarAppointmentManager = By.xpath("//img[@src='/portal/dist-home/assets/images/nav/event.svg']");    
    
    private static By bLogo = By.xpath("//img[@title='logo']");    

    public static String homePageWindowHandle = null;
    
    public static String getHomePageWindowHandle() {
		return homePageWindowHandle;
	}

	public static void setHomePageWindowHandle(String homePageWindowHandle) {
		HomePage.homePageWindowHandle = homePageWindowHandle;
	}

	private static By bUserOptions = By.xpath("//span[@class='material-icons person' and text()='account_circle']");  
	private static By bResetPassword = By.xpath("//a[text()='Reset Password']");	
	private static By bLog_Out = By.xpath("//a[text()='Logout' or text()='Déconnexion']");	
	private static By bFrench = By.xpath("//label[@for='french']");	
	private static By bEnglishCA = By.xpath("//label[@for='english-ca']");		

	//    @FindBy(id = "hrefLaunchApp")  // App link
//    private static WebElement appLaunch1;
    private static By bAppLaunch = By.id("hrefLaunchApp");


//    @FindBy(css="#upx_GroupHeader1_TR > td:nth-child(2)")
//    private static WebElement administrationMenu;
    private static By bAdministrationMenu = By.cssSelector("#upx_GroupHeader1_TR > td:nth-child(2)");

//    @FindBy(css="#upx_GroupHeader1_Item3 > tbody > tr > td:nth-child(2)")
//    private static WebElement ommAdmin;
    private static By bOmmAdmin = By.cssSelector("#upx_GroupHeader1_Item3 > tbody > tr > td:nth-child(2)");

    private By bApplicationSelection (String applicationPosition){
      String locator = "(//div[@class='box-area'])["+applicationPosition+"]";      
      return By.xpath(locator);
    }	

    // Marketing pop up message
    private static final By hmarketingMessage = By.xpath("//p[text()='Announcing: New Communication Tool']");   
    
    // Marketing pop up message
    private static final By hmarketingMessageDismissBtn = By.xpath("//button[text()='Dismiss']");     
    private static By bPaymentsInFrench = By.xpath("//div[@class='box-header']/h1[text() = 'Paiements']");
    private static By bCustomerConnectInFrench = By.xpath("//div[@class='box-header']/h1[text() = 'Connexion clients']");       
    private static By bChangeDealerCloseButton = By.xpath("//button[@class='close' and text()='×']");    

    private static Logger log = Logger.getLogger(HomePage.class.getName());

    private By bSelectApplication(String applicationName) {
    	String locator = "//div[@class='row launcher-box']//h1[text()='"+applicationName+"']";
    	return By.xpath(locator);
    }
    
    
    private Boolean isPageLoad() {
        return dWait.until(conditionForPage());
    }

    @Override
    protected ExpectedCondition<Boolean> conditionForPage() {
        return ExpectedConditions.titleContains("Application Framework");
    }

    public void clickSelectDealer() {
        Boolean loaded = isPageLoad();
        driver.switchTo().frame(driver.findElement(bBannerFrame));
        if(driver.findElements(bSelectDealerLink).size() > 0){
            driver.findElement(bSelectDealerLink).click();
        }
        switchFrame();
    }

    public String getDealerName(){
        driver.switchTo().frame(driver.findElement(bFrameMain));
        String dealerName = getElementAttributeWithException(bTxtdName,"value").toLowerCase();
        switchFrame();
        return dealerName;
    }

    public String getTitle(){
        driver.switchTo().frame(driver.findElement(bFrameMain));
//        String title = getElementAttributeWithException(bTxtdName,"value").toLowerCase();
        String title = getElementTextWithException(bTitle);
        switchFrame();
        return title;
    }

    public Boolean verifyIfUserIsAdmin(){
        try{
            sWait.until(conditionVisible(bServiceAssist));
            driver.findElement(bMTIAdmin).isDisplayed();
            System.out.println("<====== This user is MTI admin ======>");
            return true;
        }catch (WebDriverException ex){
            return false;
        }
    }

    public void launchTechInspection() {
        String dealerName = getDealerName();
        System.out.println("<====== The dealer name is "+dealerName+" ======>");
        driver.switchTo().frame(driver.findElement(bFrameCont));
        waitForElementVisibleWithException(bProfile);
        if(verifyIfUserIsAdmin()){
            if(dealerName.contains("audi")){
                dWait.until(conditionVisible(bServiceAssist)).click();
                dWait.until(conditionVisible(bServiceAssistTechInspection)).click();
            }else if(dealerName.contains("toyota")){
                dWait.until(conditionVisible(bToyotaService)).click();
                dWait.until(conditionVisible(bToyotaTechInspection)).click();
            }else{
                dWait.until(conditionVisible(bTechInspectionLink)).click();
            }
        }else if(dealerName.contains("chrysler")){
            dWait.until(conditionVisible(bWiAdvisor)).click();
            try{
                sWait.until(conditionVisible(bWiAdvisorTechnicianInspection)).click();
            }catch (WebDriverException e){
                sWait.until(conditionVisible(bWiAdvisorTechnicianDashboard)).click();
            }
        }else{
            dWait.until(conditionVisible(bTechInspectionLink)).click();
        }
        driver.switchTo().defaultContent().switchTo().frame(driver.findElement(bFrameMain));
        pWait.until(conditionVisible(bAppLaunch));
        PageManager.switchWindow();
    }

    public void launchServiceDashboard(){
        String dealerName = getDealerName();
        String title = getTitle();
        System.out.println("<====== The dealer name is "+dealerName+" ======>");
        System.out.println("<====== The title of current user is "+title+" ======>");
        driver.switchTo().frame(driver.findElement(bFrameCont));
        waitForElementVisibleWithException(bProfile);
        if(title.contains("MTI Admin")){
            dWait.until(conditionVisible(bMarketingTool)).click();
            dWait.until(conditionVisible(bMarketingToolServiceDashboard)).click();
        }
        else if(dealerName.contains("audi")){
            dWait.until(conditionVisible(bServiceAssist)).click();
            dWait.until(conditionVisible(bServiceAssistServiceDashboard)).click();
        }
//        else if(dealerName.contains("nissan")){
//            dWait.until(conditionVisible(bNissanService)).click();
//            dWait.until(conditionVisible(bNissanServiceDashboard)).click();
//        }
        else if(dealerName.contains("toyota")){
            dWait.until(conditionVisible(bToyotaService)).click();
            dWait.until(conditionVisible(bToyotaServiceDashboard)).click();
        }else if(dealerName.contains("anytown usa automall")){
            dWait.until(conditionVisible(bToyotaService)).click();
            dWait.until(conditionVisible(bToyotaServiceDashboard)).click();
        }else if(dealerName.contains("chrysler qa")){
            dWait.until(conditionVisible(bWiAdvisor)).click();
            dWait.until(conditionVisible(bServiceDashboard)).click();
        }else{
            dWait.until(conditionVisible(bServiceDashboard)).click();
        }
        driver.switchTo().defaultContent().switchTo().frame(driver.findElement(bFrameMain));
        pWait.until(conditionVisible(bAppLaunch));
        PageManager.switchWindow();
    }

    public void launchAdvisorCheckIn(){
        driver.switchTo().frame(driver.findElement(bFrameCont));
        try{
            if(driver.getCurrentUrl().contains("toyota")){
                sWait.until(conditionVisible(bToyotaService)).click();
                sWait.until(conditionVisible(bToyotaAdvisorCheckIn)).click();
            }else{
                sWait.until(conditionVisible(bMarketingTool)).click();
                sWait.until(conditionVisible(bMarketingToolAdvisorCheckIn)).click();
            }
        }catch (WebDriverException e){
            sWait.until(conditionVisible(bAdvisorCheckInLink)).click();
        }
        driver.switchTo().defaultContent().switchTo().frame(driver.findElement(bFrameMain));
        pWait.until(conditionVisible(bAppLaunch));
        PageManager.switchWindow();
    }

    public void launchGuestConnect(){
        driver.switchTo().frame(driver.findElement(bFrameCont));
        dWait.until(conditionVisible(bGuestConnectLink)).click();
        driver.switchTo().defaultContent().switchTo().frame(driver.findElement(bFrameMain));
        pWait.until(conditionVisible(bAppLaunch));
        PageManager.switchWindow();
    }

    public void launchCustomerConnect(){
        String dealerName = getDealerName();
        CC_DashboardPage cPage = new CC_DashboardPage();
        driver.switchTo().frame(driver.findElement(bFrameCont));
        if(dealerName.contains("chrysler")){
            dWait.until(conditionVisible(bWiAdvisor)).click();
            dWait.until(conditionVisible(bWiAdvisorCustomerConnect)).click();
        }else if(dealerName.contains("nissan of anytown usa")){
            try{
                driver.findElement(bMarketingTool).click();
                driver.findElement(bMarketingToolCustomerConnect).click();
            }catch (WebDriverException e){
                dWait.until(conditionVisible(bCustomerConnectLink)).click();
            }
        }else{
            dWait.until(conditionVisible(bMarketingTool)).click();
            dWait.until(conditionVisible(bMarketingToolCustomerConnect)).click();
        }
        driver.switchTo().defaultContent().switchTo().frame(driver.findElement(bFrameMain));
        dWait.until(conditionVisible(bAppLaunch));
        PageManager.switchWindow();
        cPage.refreshCCDashboardPageIfError();
    }

    public void launchAppointmentManager(){

        driver.switchTo().frame(driver.findElement(bFrameCont));
        try{
            sWait.until(conditionVisible(bMarketingTool)).click();
            sWait.until(conditionVisible(bMarketingToolAppointmentManager)).click();
        }catch (WebDriverException e){
            sWait.until(conditionVisible(bAppointmentManager)).click();
        }
        driver.switchTo().defaultContent().switchTo().frame(driver.findElement(bFrameMain));
        pWait.until(conditionVisible(bAppLaunch));
        PageManager.switchWindow();
    }

    public void launchOMMAdmin(){
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(bFrameCont));
        sWait.until(conditionVisible(bAdministrationMenu)).click();
        sWait.until(conditionVisible(bOmmAdmin)).click();
        driver.switchTo().defaultContent().switchTo().frame(driver.findElement(bFrameMain));
//        pWait.until(conditionVisible(bAppLaunch));
//        PageManager.switchWindow();
    }

    public String welcomeMsg() {
        driver.switchTo().frame("banner");
        String msg = driver.findElement(bWelcome).getText().trim();
        switchFrame();
        return msg;
    }

    public void selectLoginDFXHomePage(String dealerName, String dealerCode) {
        driver.switchTo().frame(driver.findElement(bFrameMain));
        long startTime = System.currentTimeMillis();
        clearAndInput(bTxtdName, dealerName);
        clearAndInput(bDealerCode, dealerCode);
        clickElementWithException(bSearchBtn);
        while(driver.findElements(bSelectBtn).isEmpty()){
            clickElementWithException(bFirstDealer);
            if(byElementHasClass(bFirstDealer,"SelectedLine")){
                break;
            }else if((System.currentTimeMillis()-startTime)> 5000){
                System.out.println("<====== Dealer not found, test fail ======>");
                break;
            }
        }
        clickElementWithException(bSelectBtn);
        switchFrame();
    }

    public void selectLoginDFXHomePageWithDealerNameOnly(String dealerName) {
        driver.switchTo().frame(driver.findElement(bFrameMain));
        long startTime = System.currentTimeMillis();
        clearAndInput(bTxtdName, dealerName);
        clickElementWithException(bSearchBtn);
        while(driver.findElements(bSelectBtn).isEmpty()){
            clickElementWithException(bFirstDealer);
            if(byElementHasClass(bFirstDealer,"SelectedLine")){
                break;
            }else if((System.currentTimeMillis()-startTime)> 5000){
                System.out.println("<====== Dealer not found, test fail ======>");
                break;
            }
        }
        clickElementWithException(bSelectBtn);
        switchFrame();
    }

    public void selectSecondLoginWithDealerNameOnly(String dealerName) {
        long startTime = System.currentTimeMillis();
        clearAndInput(bTxtdName, dealerName);
        clickElementWithException(bSearchBtn);
        while(driver.findElements(bSelectBtn).isEmpty()){
            clickElementWithException(bFirstDealer);
            if(byElementHasClass(bFirstDealer,"SelectedLine")){
                break;
            }else if((System.currentTimeMillis()-startTime)> 5000){
                System.out.println("<====== Dealer not found, test fail ======>");
                break;
            }
        }
        clickElementWithException(bSelectBtn);
    }

    public void loginAM(String dealerName) {
        long startTime = System.currentTimeMillis();
        clearAndInput(bTxtdName, dealerName);
        clickElementWithException(bSearchBtn);
        while(driver.findElements(bSelectBtn).isEmpty()){
            clickElementWithException(bFirstDealer);
            if(byElementHasClass(bFirstDealer,"SelectedLine")){
                break;
            }else if((System.currentTimeMillis()-startTime)> 5000){
                System.out.println("<====== Dealer not found, test fail ======>");
                break;
            }
        }
        clickElementWithException(bSelectBtn);
    }

    public void selectLoginDealerHomePage(String dealerName, String dealerCode) {
        if(!dealerName.isEmpty()){
            clearAndSend(bTxtdName, dealerName);
        }else if(!dealerCode.isEmpty()){
            clearAndSend(bDealerCode, dealerCode);
        }
        dWait.until(conditionClick(bSearchBtn)).click();
        sleep(3000);
        CommonMethods.JavaScriptClick(bFirstDealer); //JavaScript click is introduced as this step was failing for Firefox and Safari browsers.
        sleep(3000);        
        dWait.until(conditionClick(bSelectBtn)).click();
        switchFrame();
    }

    public void selectLoginDealerHomePageWithDealerNameOnly(String dealerName){
        clearAndSend(bTxtdName, dealerName);
        dWait.until(conditionClick(bSearchBtn)).click();
        dWait.until(conditionClick(bFirstDealer)).click();
        dWait.until(conditionClick(bSelectBtn)).click();
        switchFrame();
    }

    public void iSeeMessageInThePage(String message) throws Throwable {
        System.out.println("Login Successfully");
    }

    public void logoutSystem() {
        driver.switchTo().frame("banner");
        dWait.until(conditionClick(bLogout)).click();
    }

    @Override
    protected void switchFrame() {
        driver.switchTo().defaultContent();
    }

    public boolean isLoginOK(){
        switchFrame();
        waitForPageToLoad();
        boolean urlOk = driver.getCurrentUrl().contains("Impersonate.aspx");
        return  urlOk;
    }

    protected void waitForPageToLoad(){																							// Wait for page to finish loading
        pWait.until(SyncPage.condDomReadyState());
//		wait.until(ExpectedConditions.visibilityOf(bannerFrame));
        pWait.until(SyncPage.condPageLoaded(bBannerFrame, bDealerCode));
        log.info("Home Page finished loading");
    }
    
    public void switchDealer() {
    	clickElementWithException(bSwitchDealerBtn);
    	driver.switchTo().frame(driver.findElement(bSwitchDealerWindow));
    }

    public void moveToApplication(String application) {
    	sleep(6000);
    	int appPosition = -1;
    	switch(application.trim().toUpperCase()){
			case "SERVICE DASHBOARD":
				appPosition = 1;
			break;     				
    	    case "ADVISOR CHECKIN" :
    		   appPosition = 2;
    		break;
    	    case "APPOINTMENT MANAGER":
    			appPosition = 3;
    		break;    	
    		case "CUSTOMER CONNECT":
    			appPosition = 4;
    		break;     		
        	case "PAYMENTS":
        		appPosition = 5;
            break;
        	case "REPORTS":
        		appPosition = 6;
        		break;
        	case "DEALER PROFILE":
        		appPosition = 7;
        		break;
        	case "ADMIN SETTINGS":
        		appPosition = 8;
        		break;        		
    	} 
    	clickElementWithException(driver.findElement(bApplicationSelection(Integer.toString(appPosition))));
    	PageManager.switchWindow();
    	sleep(10000);    	
    }  
    
    public void openApplication(String applicationName) throws Exception {
    	sleep(6000);
    	handleMarketingPopUp();    	
    	String app = "";
    	switch(applicationName.trim().toUpperCase()) {
	    	case "SERVICE DASHBOARD":
	    		app = "Service Dashboard";
			break;
	    	case "SD":
	    		app = "Service Dashboard";
			break;
	    	case "CHECK-IN":
	    		app = "Check-in";
			break;
	    	case "ADVISOR CHECKIN":
	    		app = "Check-in";
			break;
	    	case "CHECK IN":
	    		app = "Check-in";
			break;
	    	case "ACI":
	    		app = "Check-in";
			break;
	    	case "APPOINTMENT MANAGER":
	    		app = "Appointment Manager";
			break;
	    	case "CUSTOMER CONNECT":
	    		app = "Customer Connect";
			break;
	    	case "PAYMENTS":
	    		app = "Payments";
			break;
	    	case "REPORTS":
	    		app = "Reports";
			break;
	    	case "DEALER PROFILE":
	    		app = "Dealer Profile";
			break;
	    	case "ADMIN SETTINGS":
	    		app = "Admin Settings";
			break;
			
			default :
			 throw new Exception("Application not found");
    	}
		if(verifyIfElementVisibleOnPage(bSelectApplication(app)))
    		clickElementWithException(bSelectApplication(app));
    	
    	else
    		throw new Exception(app+" Application not visible for logged in dealer");
    	
    	PageManager.switchWindow();
    	sleep(5000);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void moveToSideBarApplication(String application) {
    	sleep(2000);
    	switch(application.trim().toUpperCase()){
    		case "APPOINTMENT MANAGER":
    			clickElementWithException(driver.findElement(bSideBarAppointmentManager));
        	break;    	
        	case "PAYMENTS":
            	clickElementWithException(driver.findElement(bSideBarPayments));
            break;
        } 
    	PageManager.switchWindow();
    	sleep(2000);    	
    }      

    public void noteDownHomePageWindowHandle() {
    	sleep(5000);
    	setHomePageWindowHandle(driver.getWindowHandle());
    } 

    public void moveBackToHomePageWindowHandle() {
    	sleep(5000);
    	driver.switchTo().window(getHomePageWindowHandle());
    }     
    
    public void validateLogo(String logo) {
    	sleep(3000);
    	switch(logo.trim().toUpperCase()){
        	case "NISSAN":
        			Assert.assertTrue(driver.findElement(bLogo).getAttribute("src").contains("5cb8-4ed6-92ad-fd30f8ae11e1.jpg"), "Validate Nissan Logo");        			
        		break;
        	case "AUDI":
        			Assert.assertTrue(driver.findElement(bLogo).getAttribute("src").contains("d63b-4790-8ee1-85016d07b1db.jpg"), "Validate Audi Logo");        		
        		break;
    	} 
    }
    
	public void click(String name) {
		sleep(3000);
		switch (name.toUpperCase()) {
			case "USER ACCOUNT":
				driver.findElement(bUserOptions).click();
			break;
			case "RESET PASSWORD":
				driver.findElement(bResetPassword).click();
				PageManager.switchWindow();				
			break;	
			case "LOGOUT":
				driver.findElement(bLog_Out).click();
			break;		
			case "FRENCH LANGUAGE":
				driver.findElement(bFrench).click();
			break;
			case "ENGLISH - CANADA":
				driver.findElement(bEnglishCA).click();
			break;			
			case "CHANGE DEALER CLOSE BUTTON":
				driver.findElement(bChangeDealerCloseButton).click();
			break;		
			case "LOGO":
				driver.findElement(bLogo).click();
			break;	
			case "APPOINTMENT MANAGER":
				driver.findElement(bSideBarTextAppointmentManager).click();
		    	PageManager.switchWindow();
		    	sleep(5000); 				
			break;	
			case "MORE OPTIONS":
				driver.findElement(bMoreBtn).click();
			break;
			case "ONLINE MAINTENANCE MENU":
				driver.findElement(bMoreOMMBtn).click();
		    	PageManager.switchWindow();
		    	sleep(5000); 				
			break;		
			case "TECHNICIAN INSPECTION":
				driver.findElement(bMoreTechInspectionBtn).click();
		    	PageManager.switchWindow();
		    	sleep(5000); 				
			break;
			case "PERSONALIZED SERVICE WELCOME":
				driver.findElement(bMorePerSerWelBtn).click();
		    	PageManager.switchWindow();
		    	sleep(5000); 				
			break;
			case "CUSTOMER MANAGEMENT":
				driver.findElement(bMoreCustMgmtBtn).click();
		    	PageManager.switchWindow();
		    	sleep(5000); 		
			case "COMMUNICATION DASHBOARD":
				pWait.until(ExpectedConditions.visibilityOfElementLocated(bChatIconBtn));			
				driver.findElement(bChatIconBtn).click();
		    	sleep(5000); 			
			break;			
			default:
				return;
		}
	}  
	
	public void validateSwitchDealerWindow(String message) {
		sleep(5000);
		switch (message.toUpperCase()) {
		case "FRENCH TRANSLATIONS":
			Assert.assertTrue(driver.findElement(bSelectBtn).getAttribute("value").trim().equals("Changer de concession"),"Validate Switch Button text in French");
			Assert.assertTrue(driver.findElement(bTxtdName).getAttribute("placeholder").trim().equals("Recherche par nom ou code de concession"),"Validate search text in French");
			Assert.assertTrue(driver.findElement(blblChangeDealer).getText().trim().equals("Si vous souhaitez changer le compte de la concession active, veuillez sélectionner un compte sur la liste:"),"Validate change dealership text in French");			
			break;
		default:
			return;
		}
		switchFrame();
	}
	
    public void verifyElementExists(String field){
        switch (field.trim().toUpperCase()) {
        case "FRENCH TRANSLATIONS":
        	CommonMethods.verifyElementExists(driver.findElement(bPaymentsInFrench));
        	CommonMethods.verifyElementExists(driver.findElement(bCustomerConnectInFrench));        	
            break;
        default:
        	break;    	
        }    
    }  	
    
    public void closeActiveTab(){
		driver.close();
		driver.switchTo().window(PageManager.getPreHandle());    	
    }  
    
    public void handleMarketingPopUp(){																								// Handle the Stored Data Warnign Message
        sleep(2000);
        try{
			sWait.until(ExpectedConditions.visibilityOfElementLocated(hmarketingMessage));        	
            WebElement dismissMarketingBtn = sWait.until(conditionClick(hmarketingMessageDismissBtn));
            moveToClick(dismissMarketingBtn);
            System.out.println("Home page -  Marketing pop up displayed. Clicked dismiss button marketing button");
            sleep(500);
        } catch (TimeoutException te ){
        	System.out.println("Home page -  Marketing pop up is not displayed. No action required.");
        }
    }      
	
}


