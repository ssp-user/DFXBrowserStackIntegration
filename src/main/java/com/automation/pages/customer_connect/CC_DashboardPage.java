package com.automation.pages.customer_connect;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.*;

public class CC_DashboardPage extends WebPage {

    public static String  sClosedTasksLast,sNewTasksLast,sInProgressTasksLast,sExpiredTasksLast;
    //dashboard Progress chart
    private static By loadingOnCustomerConnect = By.id("loadingSpinner");

    //dashboard Progress chart
    private static By bDashboardProgressChart = By.id("dashboardProgressChart");

    //Header (e.g. DASHBOARD, TASKS, etc.)
    private static By bHeader(String headerName) {
        String checkLocator = "//span[contains(text(), '" + headerName + "')]/ancestor::li";
        return By.xpath(checkLocator);
    }

    //TASKS Tab
    private static By bTaskTab = By.xpath("//span[contains(text(),'TASKS')]");

    //SEARCH Tab
    private static By bSearch = By.xpath("//span[contains(text(),'SEARCH')]");

    //DASHBOARD Tab
    private static By bDashboard = By.xpath("//span[contains(text(),'DASHBOARD')]");

    //MESSENGER Tab
    private static By bMessenger = By.xpath("//span[contains(text(),'Messenger')]");

    //Reports Tab
    private static By bReports = By.xpath("//span[contains(text(),'REPORTS')]");

    //CAMPAIGNS Tab
    private static By bCampaigns = By.xpath("//span[contains(text(),'CAMPAIGNS')]");

    //SCRIPTS Tab
    private static By bScripts = By.xpath("//span[contains(text(),'SCRIPTS')]");
    
    //CONFIG Tab
    private static By bConfig = By.xpath("//span[contains(text(),'CONFIG')]");    

    //chart on the dashboard page
    private static By bMyChart = By.id("myChart");

    //In progress tasks count on the dashboard page
    private static By bInProgressTaskCount = By.xpath("//div[contains(text(),'In Progress Tasks')]/preceding-sibling::div");

    //closed Tasks count on the dashboard page
    private static By bClosedTaskCount = By.xpath("//div[contains(text(),'Closed Tasks')]/preceding-sibling::div");

    //calls made count on the dashboard page
    private static By bCallsMade = By.xpath("//div[contains(text(),'Calls Made')]/preceding-sibling::div");

    //closed Tasks on campaign
    private static By bClosedTaskCampaign = By.xpath("(//div[contains(text(),'Tasks Completed')]/preceding-sibling::div[2])[1]");

    //Calls Made on campaign
    private static By bCallsMadeCampaign = By.xpath("(//div[contains(text(),'Calls Made')]/preceding-sibling::div)[2]");

    //Total tasks counts on Tasks page
    private static By bTotalTasksCount = By.xpath("//div[@class='pageHeaderText leftFlex ng-binding']");

    //total campaign tasks
    private static By bTotalCampaignTasks = By.xpath("//div[contains(text(),'Missed / No Show Appointments')]/../..//div[contains(@class,'dashboard-campaign-taskscompleted')]/div[@class='ng-binding']");

    //Export button
    private static By bExport = By.xpath("//button[@ng-click='openExportCampaignPopup()']");

    //Select all to export
    private static By bSelectAllExport = By.xpath("//button[@ng-click='selectAll()']");

    //export button on export page
    private static By bExportOnExportPage = By.xpath("//div[@onclick='exportCampaignDataJS()']");

    //Last Action sort on the Tasks page
    private static By bLastAction = By.xpath("//span[@class='tableHeaderLink' and text()='Last Action']");

    //Warning message
    private static By bWarning = By.xpath("//div[@class='taskListExportEmptyWarning topFlex leftFlex ng-scope']");

    //column name
    public static By bColumnName(String value) {
        String checkLocator = "//span[@class='tableHeaderLink' and contains(text(),'" + value + "')]";
        return By.xpath(checkLocator);
    }

    //column value
    private static By bColumnValue(String value) {
        String checkLocator = "//td[contains(text(),'" + value + "')]";
        return By.xpath(checkLocator);
    }

    private static By bColumnValueToGetName(String value) {
        String checkLocator = "//td[contains(text(),'" + value + "')]/preceding-sibling::td[6]";
        return By.xpath(checkLocator);
    }

    private static By bColumnValueToGetVehicle(String value) {
        String checkLocator = "//td[contains(text(),'" + value + "')]/preceding-sibling::td[3]";
        return By.xpath(checkLocator);
    }

    private static By bColumnValueWithNameAndVehicle(String name, String vehicle, String value) {
        String checkLocator = "//td[contains(text(),'" + name + "')]/following-sibling::td[contains(text(),'" + vehicle + "')]/following-sibling::td[contains(text(),'" + value + "')]";
        return By.xpath(checkLocator);
    }

    public By bColumnAfterSave(String name, String vehicle, String value) {
        sleep(3000);
        String noAnswerLocator = "//td[contains(text(),'" + name + "')]/following-sibling::td[contains(text(),'" + vehicle + "')]/following-sibling::td[contains(text(),'" + value + "')]";
        String lastAttemptLocator = noAnswerLocator + "/preceding-sibling::td[1]";
        String lastAttempt = dWait.until(conditionVisible(By.xpath(lastAttemptLocator))).getAttribute("innerHTML");
        String callAttemptsLocator = "//td[contains(text(),'" + name + "')]/following-sibling::td[contains(text(),'" + vehicle + "')]/following-sibling::td[contains(text(),'" + value + "')]/preceding-sibling::td[contains(text(), '" + lastAttempt +"')]" + "/preceding-sibling::td[1]";
        tp.callAttempts = dWait.until(conditionVisible(By.xpath(callAttemptsLocator))).getAttribute("innerHTML");
        System.out.println("Call Attempts locator is: " + callAttemptsLocator);
        System.out.println("Call Attempts is : " + tp.callAttempts);
        return By.xpath(noAnswerLocator);
    }

    //configure associated with campaign
    private static By bConfigureAssociatedWithCampaign(String campaignName) {
        String checkLocator = "//div[contains(@class,'dashboard-campaign-name') and contains(text(),'" + campaignName + "')]/../..//div[@class='statsByCampaign-configure']";
        return By.xpath(checkLocator);
    }

    //campaign section
    private static By bCampaignModuleOnDashboard(String campaignName) {
        String checkLocator = "//div[contains(@class,'dashboard-campaign-name') and contains(text(),'" + campaignName + "')]";
        return By.xpath(checkLocator);
    }

    //menu with status
    private static By bMenuWithStatus(String menuName) {
        String checkLocator = "//span[contains(text(),'" + menuName + "')]/parent::a/parent::li";
        return By.xpath(checkLocator);
    }

    //column value find by customerName
    private static By bValueByCustomerName(String customerName, String columnNo) {
        String checkLocator = "//td[contains(text(),'" + customerName + "')]/following-sibling::td[" + columnNo + "]";
        return By.xpath(checkLocator);
    }

    //restart session button
    private static By bRestartSession = By.xpath("//a[contains(text(),'Restart Session')]");

    //first line of the dealer on impersonate page
    private static By bDealerSelection = By.xpath("//*[@id='dImpersonate']/tbody/tr[2]/td[1]");

    //Select button ok on the imnpersonate page
    private static By bSELECTOK = By.id("btnOK");

    //input campaign search
    private static By bInputCampaignSearchOnDashboard = By.id("inputSearch");

    //Campaign section associated with name
    private static By bCampaignWithName(String campaignName) {
        String locator = "//div[@class='dashboard-campaign-name ng-binding' and contains(text(),'" + campaignName + "')]";
        return By.xpath(locator);
    }

    //use to verify if user clicked campaign
    private static By bCustomerNameOnTaskDetail = By.xpath("//span[@class='tableHeaderLink' and contains(text(),'Customer Name')]");

    public String customerName;

    public String verifyCCPageTiTle() {
        waitForCampaignTaskCompleted();
        String pageTitle = driver.getTitle();
        return pageTitle;
    }

    public void waitForLoadingCircleToDisappearCC() {
        waitForElementHasAttributeValue(loadingOnCustomerConnect, "style", "display: none;");
    }

    public void waitForCampaignTaskCompleted() {
        long startTime = System.currentTimeMillis();
        String campaignTasks = "0";
        while (campaignTasks.equals("0")) {
            campaignTasks = getElementAttributeWithException(bTotalCampaignTasks, "innerHTML").replaceAll("[^\\d.]", "").replaceAll(",", "");
            sleep(1000);
            if ((System.currentTimeMillis() - startTime) > 28000) {
                pageRefresh();
                break;
            }
        }
    }

    public boolean verifyHeaderLoadingProperlyOnDashboard() {
        waitForElementWithException(bDashboard);
        try {
            driver.findElement(bMenuWithStatus("DASHBOARD")).isDisplayed();
            driver.findElement(bMessenger).isDisplayed();
            driver.findElement(bSearch).isDisplayed();
            driver.findElement(bTaskTab).isDisplayed();
            return true;
        } catch (WebDriverException e) {
            System.out.println("<====== One of the header menu disabled, please double check ======>");
            return false;
        }
    }

    public String getCurrentURL() {
        String currentURL = "";
        try {
            currentURL = driver.getCurrentUrl();
        } catch (org.openqa.selenium.TimeoutException te) {
            driver.navigate().refresh();
        }
        return currentURL;
    }

    public void refreshCCDashboardPageIfError() {
        long startTime = System.currentTimeMillis();
        while (!getCurrentURL().contains("dealer")) {
            sleep(1000);
            if (driver.getTitle().equalsIgnoreCase("Not Found")) {
                System.out.println("<====== 404 occurred ======>");
                break;
            } else if (driver.getCurrentUrl().contains("sessionExpired")) {
                dWait.until(conditionClick(bRestartSession)).click();
                clickElementWithException(bDealerSelection);
                dWait.until(conditionClick(bSELECTOK)).click();
            } else if ((System.currentTimeMillis() - startTime) > 10000) {
                System.out.println("<====== Page has issue, the current page title is '" + driver.getTitle() + "'======>");
                break;
            }
        }
        while (!verifyHeaderLoadingProperlyOnDashboard()) {
            pageRefresh();
            sleep(5000);
            if ((System.currentTimeMillis() - startTime) > 60000) {
                break;
            }
        }
    }

    public String getTasksCountBefore() {
        long startTime = System.currentTimeMillis();
        tp.tasksCountBefore = "0";
        while (tp.tasksCountBefore.equals("0")) {
            tp.tasksCountBefore = getElementAttributeWithException(bTotalTasksCount, "innerHTML").replaceAll("[^\\d.]", "").replaceAll(",", "");
            sleep(1000);
            if ((System.currentTimeMillis() - startTime) > 20000) {
                break;
            }
        }
        System.out.println("<====== the before total Tasks count is " + tp.tasksCountBefore + " ======>");
        return tp.tasksCountBefore;
    }

    public String getTasksCountAfter() {
        long startTime = System.currentTimeMillis();
        sleep(2000);
        tp.tasksCountAfter = "0";
        while (tp.tasksCountAfter.equals("0")) {
            tp.tasksCountAfter = getElementAttributeWithException(bTotalTasksCount, "innerHTML").replaceAll("[^\\d.]", "").replaceAll(",", "");
            sleep(1000);
            if ((System.currentTimeMillis() - startTime) > 20000) {
                break;
            }
        }
        System.out.println("<====== the after total Tasks count is " + tp.tasksCountAfter + " ======>");
        return tp.tasksCountAfter;
    }

    public String getInProgressTaskCountBefore() {
        long startTime = System.currentTimeMillis();
        tp.inProgressTasksBefore = "";
        while (tp.inProgressTasksBefore.isEmpty()) {
            tp.inProgressTasksBefore = getElementAttributeWithException(bInProgressTaskCount, "innerHTML").replaceAll(",", "");
            sleep(1000);
            if ((System.currentTimeMillis() - startTime) > 100000) {
                break;
            }
        }
        System.out.println("<====== the before In Progress Tasks count is " + tp.inProgressTasksBefore + " ======>");
        return tp.inProgressTasksBefore;
    }

    public String getInProgressTasksCountAfter() {
        long startTime = System.currentTimeMillis();
        tp.inProgressTasksAfter = "";
        while (tp.inProgressTasksAfter.isEmpty()) {
            sleep(30000);
            tp.inProgressTasksAfter = getElementAttributeWithException(bInProgressTaskCount, "innerHTML").replaceAll(",", "");
//            sleep(1000);
            if ((System.currentTimeMillis() - startTime) > 20000) {
                break;
            }
        }
        System.out.println("<====== the after In Progress Tasks count is " + tp.inProgressTasksAfter + " ======>");
        return tp.inProgressTasksAfter;
    }

    public String getInProgressTasksAfter(){
        int i = 0;
        String sInProgressTasksAfter = "";
        while(i < 10 ){
            sleep(1000);
            sInProgressTasksAfter = getElementAttributeWithException(bInProgressTaskCount,"innerHTML").replaceAll(",", "");
            i++;
            if(sInProgressTasksAfter.equals("0") | sInProgressTasksAfter.equals("") | sInProgressTasksAfter.equals(tp.inProgressTasksBefore)){
                continue;
            }else{
                break;
            }
        }
        System.out.println("<==  The after In Progress Tasks count is " + sInProgressTasksAfter +"  =====>");
        return sInProgressTasksAfter;
    }

    public void saveClosedTasks() {
        sClosedTasksLast = getClosedTasks();
    }

    public String getClosedTasksBefore() {
        tp.closedTasksBefore = driver.findElement(bClosedTaskCount).getAttribute("innerHTML").replaceAll(",", "");
        System.out.println("<====== the before Closed Tasks count is " + tp.closedTasksBefore + " ======>");
        return tp.closedTasksBefore;
    }

    public String getClosedTasksAfter() {
        long startTime = System.currentTimeMillis();
        tp.closedTasksAfter = "";
        while (tp.closedTasksAfter.isEmpty()) {
            tp.closedTasksAfter = getElementAttributeWithException(bClosedTaskCount, "innerHTML").replaceAll(",", "");
            sleep(1000);
            if ((System.currentTimeMillis() - startTime) > 20000) {
                break;
            }
        }
        System.out.println("<====== the after Closed Tasks count is " + tp.closedTasksAfter + " ======>");
        return tp.closedTasksAfter;
    }

    public String getClosedTasksAfter1(){
        int i = 0;
        String att = "";
        while(i < 4 ){
            sleep(1000);
            att = getElementAttributeWithException(bClosedTaskCount,"innerHTML");
            tp.closedTasksAfter = att.replaceAll(",", "");
            i++;
            if(att.equals("") | att.equals("0") ){
                continue;
            }else{
                break;
            }
        }
        System.out.println("<====== the after Closed Tasks count is "+tp.closedTasksAfter+" ======>");
        return tp.closedTasksAfter;
    }

    public String getClosedTasks(){
        int i = 0;
        String sTasks = "";
        while(i < 4 ){
            sleep(1000);
            sTasks = getElementAttributeWithException(bClosedTaskCount,"innerHTML").replaceAll(",", "");
            i++;
            if(sTasks.equals("") | sTasks.equals("0") ){
                continue;
            }else{
                break;
            }
        }
        System.out.println("<==  the Closed Tasks count on screen is " + sTasks +"  =====>");
        return sTasks;
    }


    public String getCallsMadeBefore() {
        tp.callsMadeBefore = driver.findElement(bCallsMade).getAttribute("innerHTML").replaceAll(",", "");
        System.out.println("<====== the before Calls Made count is " + tp.callsMadeBefore + " ======>");
        return tp.callsMadeBefore;
    }

    public String getCallsMadeAfter() {
        dWait.until(conditionVisible(bCallsMade));
        tp.callsMadeAfter = driver.findElement(bCallsMade).getAttribute("innerHTML").replaceAll(",", "");
        System.out.println("<====== the after Calls Made count is " + tp.callsMadeAfter + " ======>");
        return tp.callsMadeAfter;
    }

    public String getClosedTasksCampaignBefore() {
        tp.closedTasksCampaignBefore = driver.findElement(bClosedTaskCampaign).getAttribute("innerHTML").replaceAll(",", "");
        System.out.println("<====== the before Closed Tasks Campaign count is " + tp.closedTasksCampaignBefore + " ======>");
        return tp.closedTasksCampaignBefore;
    }

    public String getClosedTasksCampaignAfter() {
        dWait.until(conditionVisible(bClosedTaskCampaign));
        tp.closedTasksCampaignAfter = driver.findElement(bClosedTaskCampaign).getAttribute("innerHTML").replaceAll(",", "");
        System.out.println("<====== the after Closed Tasks Campaign count is " + tp.closedTasksCampaignAfter + " ======>");
        return tp.closedTasksCampaignAfter;
    }

    public String getCallsMadeCampaignBefore() {
        tp.callsMadeCampaignBefore = driver.findElement(bCallsMadeCampaign).getAttribute("innerHTML").replaceAll(",", "");
        System.out.println("<====== the before Calls Made Campaign count is " + tp.callsMadeCampaignBefore + " ======>");
        return tp.callsMadeCampaignBefore;
    }

    public String getCallsMadeCampaignAfter() {
        dWait.until(conditionVisible(bCallsMadeCampaign));
        tp.callsMadeCampaignAfter = driver.findElement(bCallsMadeCampaign).getAttribute("innerHTML").replaceAll(",", "");
        System.out.println("<====== the after Calls Made Campaign count is " + tp.callsMadeCampaignAfter + " ======>");
        return tp.callsMadeCampaignAfter;
    }

//    public void clickTabOnCCDashboardPage(String tabName) {
//        refreshCCDashboardPageIfError();
//        if (verifyHeaderMenuStatus("DASHBOARD", "Enabled")) {
//            waitForCampaignTaskCompleted();
//        }
//        switch (tabName) {
//            case "TASKS":
//                getInProgressTaskCountBefore();
////                getClosedTasksBefore();  // replaced by saveClosedTasks()  on April 2019 David
//                saveClosedTasks();
//                getCallsMadeBefore();
//                getClosedTasksCampaignBefore();
//                getCallsMadeCampaignBefore();
//                dWait.until(conditionVisible(bTaskTab)).click();
//                getTasksCountBefore();
//                break;
//            case "DASHBOARD":
//                clickONDashboard();
//                break;
//            case "SEARCH":
//                clickONSearch();
//                break;
//            case "MESSENGER":
//                clickONMessenger();
//                break;
//            case "REPORTS":
//                clickONReports();
//                break;
//            case "CAMPAIGNS":
//                clickONCampaigns();
//                break;
//            case "SCRIPTS":
//                clickONScripts();
//                break;
//        }
//    }

    public void clickTabOnCCDashboardPage(String tabName) {
//        refreshCCDashboardPageIfError();
        if (verifyHeaderMenuStatus("DASHBOARD", "Enabled")) {
            waitForCampaignTaskCompleted();
        }
        switch (tabName) {
            case "TASKS":
                getInProgressTaskCountBefore();
//                getClosedTasksBefore();  // replaced by saveClosedTasks()  on April 2019 David
                saveClosedTasks();
                getCallsMadeBefore();
                getClosedTasksCampaignBefore();
                getCallsMadeCampaignBefore();
                dWait.until(conditionVisible(bTaskTab)).click();
                getTasksCountBefore();
                break;
            case "DASHBOARD":
                clickONDashboard();
                break;
            case "SEARCH":
                clickONSearch();
                break;
            case "MESSENGER":
                clickONMessenger();
                break;
            case "REPORTS":
                clickONReports();
                break;
            case "CAMPAIGNS":
                clickONCampaigns();
                break;
            case "SCRIPTS":
                clickONScripts();
                break;
            case "CONFIG":
            	clickONConfig();
                break;  
            case "TASKS FROM ANY OTHER TAB":
                dWait.until(conditionVisible(bTaskTab)).click();
                break;                
        }
    }

    public void clickONSearch() {
        pWait.until(conditionVisible(bSearch)).click();
    }

    public void clickONDashboard() {
        try {
            dWait.until(conditionVisible(bDashboard)).click();
        } catch (WebDriverException e) {
            waitForElementWithExceptionIfNotAvailableThenRefresh(bDashboard);
            dWait.until(conditionVisible(bDashboard)).click();
        }
    }

    public void clickONMessenger() {
        pWait.until(conditionVisible(bMessenger)).click();
    }

    public void clickONReports() {
        try {
            dWait.until(conditionVisible(bReports)).click();
        } catch (WebDriverException ex) {
            System.out.println("<====== The report header menu is not loading properly ======>");
            pageRefresh();
            dWait.until(conditionVisible(bReports)).click();
        }
    }

    public void clickONCampaigns() {
        waitForElementVisibleWithException(bCampaigns);
        try {
            dWait.until(conditionVisible(bCampaigns)).click();
        } catch (WebDriverException e) {
            pageRefresh();
            clickElementWithException(bCampaigns);
        }
    }

    public void clickONScripts() {
        waitForElementVisibleWithException(bScripts);
        try {
            dWait.until(conditionVisible(bScripts)).click();
        } catch (WebDriverException e) {
            pageRefresh();
            clickElementWithException(bScripts);
        }
    }

    public void clickONConfig() {
        waitForElementVisibleWithException(bConfig);
        try {
            dWait.until(conditionVisible(bConfig)).click();
        } catch (WebDriverException e) {
            pageRefresh();
            clickElementWithException(bConfig);
        }
    }    
    
    public boolean verifyIfReportsNotShow() {
        if (verifyIfElementNotShowOnThePage(bReports)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyIfScriptsNotShow() {
        if (verifyIfElementNotShowOnThePage(bScripts)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean verifyIfCampaignsNotShow() {
        if (verifyIfElementNotShowOnThePage(bCampaigns)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyIfIconNotShow(String campaignName) {
        waitForElementVisibleWithException(bCampaignModuleOnDashboard(campaignName));
        if (verifyIfElementNotShowOnThePage(bConfigureAssociatedWithCampaign(campaignName))) {
            return true;
        } else {
            return false;
        }
    }

    public void selectFromList(String columnName, String value) {
        long startTime = System.currentTimeMillis();
        switch (columnName) {
            case "Last Action":
                waitForColumnScrollDownWithException(bColumnName(columnName), bColumnValue(value));
                String name = getElementTextWithException(bColumnValueToGetName(value));
                String vehicle = getElementTextWithException(bColumnValueToGetVehicle(value));
                try {
                    driver.findElement(bColumnValueWithNameAndVehicle(name, vehicle, value));
                } catch (NoSuchElementException e) {
                    System.out.println("<====== Not able to see customer with name '" + name + "', vehicle '" + vehicle + "' and last action '" + value + "' visible, will click the last action sort and try it again ======>");
                    clickElementWithException(bLastAction);
                }
                tp.numberOfDuplicateVehicleBefore = 0;
                while (tp.numberOfDuplicateVehicleBefore == 0) {
                    tp.numberOfDuplicateVehicleBefore = driver.findElements(bColumnValueWithNameAndVehicle(name, vehicle, value)).size();
                    sleep(1000);
                    if (tp.numberOfDuplicateVehicleBefore != 0) {
                        System.out.println("<====== The number of duplicate vehicle after is " + tp.numberOfDuplicateVehicleBefore + " ======>");
                        break;
                    } else if ((System.currentTimeMillis() - startTime) > 30000) {
                        System.out.println("<====== Not able to see customer with name '" + name + "', vehicle '" + vehicle + "' and last action '" + value + "' test failed ======>");
                        break;
                    }
                }
                dWait.until(conditionVisible(bColumnValue(value))).click();
                break;
            case "Assigned":
                waitForLoadingCircleToDisappearCC();
                waitForColumnScrollDownWithException(bColumnName(columnName), bColumnValue(value));
                dWait.until(conditionVisible(bColumnValue(value))).click();
                break;
            case "Customer Name":
                //CCTasksPage tPage = new CCTasksPage();
                if (value.equals("{userName}")) {
                    value = tp.userName;
                }
                clickElementWithException(bColumnValue(value));
                break;
        }
    }

    public void clickOnExport() {
        setWait();
        dWait.until(conditionVisible(bExport)).click();
        clickElementTimesTillDisappear(bSelectAllExport, 2);
        dWait.until(conditionVisible(bExportOnExportPage)).click();
    }

    public int verifySpreadSheetRowCount() {
        sleep(5000);
        long startTime = System.currentTimeMillis();
        String fileLocation = "./test-output/temp_download/";
        String fileType = ".csv";
        String fileName = getFileName(fileLocation, fileType);
        System.out.println("Downloaded file name is: " + fileName);
        boolean noException = false;
        while (!noException) {
            try {
                if (!fileName.contains(fileType)) {
                    fileName = getFileName(fileLocation, fileType);
                }
                noException = true;
            } catch (java.lang.NullPointerException ex) {
                sleep(1000);
                noException = false;
            }
            if ((System.currentTimeMillis() - startTime) > 20000) {
                break;
            }
        }
        int rowCount = readRowNumberFromFile(fileLocation + fileName);
        sleep(8000);        
        return rowCount;
    }

    public void waitForColumnScrollDownWithException(By columnElement, By elementNotVisible) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                driver.findElement(elementNotVisible).isDisplayed();
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on wait for " + elementNotVisible + " method, will click on the column to sort the order ======>");
                dWait.until(conditionVisible(columnElement)).click();
                noException = false;
                sleep(1000);
            } catch (WebDriverException e) {
                System.out.println("<====== WebDriverException exception occurred on wait " + elementNotVisible + " attribute method, will click on the column to sort the order ======>");
                dWait.until(conditionVisible(columnElement)).click();
                noException = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 20000) {
                break;
            }
        }
    }

    public void clickConfigureIconWithCampaign(String campaignName) {
        clickElementWithException(bConfigureAssociatedWithCampaign(campaignName));
    }

    public boolean verifyHeaderMenuStatus(String menuName, String status) {
        if (status.equals("Enabled")) {
            if (byElementHasClass(bMenuWithStatus(menuName), "activeHeader")) {
                return true;
            } else {
                return false;
            }
        } else {
            if (!byElementHasClass(bMenuWithStatus(menuName), "activeHeader")) {
                return true;
        } else {
                return false;
            }
        }
    }

    public int recordAfterNumber(String columnValue) {
        tp.numberOfDuplicateVehicleAfter = driver.findElements(bColumnValueWithNameAndVehicle(tp.userName, tp.vehicleName, columnValue)).size();
        return tp.numberOfDuplicateVehicleAfter;
    }

    public boolean ifRecordExist() {
        System.out.println("<====== The number of duplicate vehicle before is " + tp.numberOfDuplicateVehicleBefore + " ======>");
        System.out.println("<====== The number of duplicate vehicle after is " + tp.numberOfDuplicateVehicleAfter + " ======>");
        if (tp.numberOfDuplicateVehicleBefore > tp.numberOfDuplicateVehicleAfter) {
            System.out.println("<====== The record has disappeared ======>");
            return true;
        } else {
            System.out.println("<====== Record still exist, not delete yet ======>");
            return false;
        }
    }

//    public String getColumnValueFromUserName(String columnName, String columnValue) {
//        String actualValue = "";
//        switch (columnName) {
//            case "Call Attempts":
//                waitForColumnScrollDownWithException(bColumnName(columnName), bCallAttemptColumnWithNameAndVehicle(tp.userName, tp.vehicleName));
//                actualValue = dWait.until(conditionVisible(bCallAttemptColumnWithNameAndVehicle(tp.userName, tp.vehicleName))).getAttribute("innerHTML");
//                break;
//            case "Last Action":
//                waitForColumnScrollDownWithException(bColumnName(columnName), bColumnValueWithNameAndVehicle(tp.userName, tp.vehicleName, columnValue));
//                actualValue = dWait.until(conditionVisible(bColumnValueWithNameAndVehicle(tp.userName, tp.vehicleName, columnValue))).getAttribute("innerHTML");
//                break;
//        }
//        return actualValue;
//    }

    public String getColumnValueAfterSave(String columnName, String columnValue) {
        String actualValue = "";
        switch (columnName) {
            case "Call Attempts":
//                waitForColumnScrollDownWithException(bColumnName(columnName), bCallAttemptColumnWithNameAndVehicle(tp.userName, tp.vehicleName));
                actualValue = tp.callAttempts;
                break;
            case "Last Action":
//                waitForColumnScrollDownWithException(bColumnName(columnName), bColumnAfterSave(tp.userName, tp.vehicleName, columnValue));
                actualValue = dWait.until(conditionVisible(bColumnAfterSave(tp.userName, tp.vehicleName, columnValue))).getAttribute("innerHTML");
                break;
        }
        return actualValue;
    }

    public void clickCampaignWithName(String campaignName) {
        long startTime = System.currentTimeMillis();
        Boolean noException = false;
        Boolean noException2 = false;
        clearAndInputElementWithException(bInputCampaignSearchOnDashboard, campaignName);
        driver.findElement(bInputCampaignSearchOnDashboard).sendKeys(Keys.RETURN);
        while (!noException) {
            try {
                sWait.until(conditionVisible(bCampaignWithName(campaignName))).click();
                noException = true;
            } catch (WebDriverException e) {
                sleep(5000);
                noException = false;
            }
            if ((System.currentTimeMillis() - startTime) > 60000) {
                break;
            }
        }
        while (!noException2) {
            try {
                driver.findElement(bCustomerNameOnTaskDetail).isDisplayed();
                noException2 = true;
            } catch (WebDriverException e) {
                try {
                    driver.findElement(bCampaignWithName(campaignName)).click();
                } catch (WebDriverException ex) {
                    //do nothing, you are already in task detail
                }
                noException2 = false;
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 80000) {
                break;
            }
        }
    }

    public boolean isHeaderActive(String headerName){
        try{
            if(driver.findElement(bHeader(headerName)).getAttribute("class").contains("activeHeader")){
                return true;
            }else{
                return false;
            }
        }catch (WebDriverException e){
            return false;
        }
    }
}
