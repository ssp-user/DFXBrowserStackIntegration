package com.automation.pages.wiadvisor;

import com.automation.utils.dataProvider.TestParameters;
import com.automation.utils.otherUtils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WiAdvisorAssignROTab extends WiAdvisorROPage {

    //locator
    //tag field
    private static By bTagField = By.id("advisorTag");

    //green continue button on Assign Repair RO tab
    private static By bContinueAssignRO = By.xpath("//button[@ng-click='repairOrder.continue()']");

    private static By bAssignROTab = By.cssSelector("li[ng-class*='assign' i]");
    private static By bAssignedAdvisor = By.id("assignedAdvisor");
    private static By bAdvisorList = By.cssSelector("div[ng-repeat*='advisor']");
    private static By bPromiseTime_Date = By.cssSelector("input[ng-model*='promiseTime']");
    private static By bCurrentMonth = By.cssSelector("button[id*='datepicker']");
    private static By bNextMonth = By.cssSelector("button[ng-click*='move(1)']");
    private static By bThirdWeekWednesday = By.cssSelector("tr[ng-repeat]:nth-child(3) td:nth-child(5) button");
    private static By bHourPlus = By.cssSelector("a[ng-click*='incrementHour']");
    private static By bCurrentHour = By.cssSelector("input[ng-model*='hour']");
    private static By bHourMinus = By.cssSelector("a[ng-click*='decrementHour']");
    private static By bAMPM = By.cssSelector("button[ng-click*='meridian' i]");


    public void enterTagAssignRo(String tag) {
        if (tag.toLowerCase().equals("random")) {
            CommonMethods cm = new CommonMethods();
            tag = cm.getRandomText(5);
        }
        clearAndInputElementWithException(bTagField, tag);
    }

    public void clickOnContinueAssignRO() {
        clickElementWithException(bContinueAssignRO);
    }

    public void selectServiceAdvisorAssignROTabOLD(String advisor) {
        waitPageLoadAssignROTab();
        pWait.until(conditionVisible(bAssignedAdvisor)).click();
        boolean check = false;
        List<WebElement> advisorList = dWait.until(conditionPresenceList(bAdvisorList));
        for (int i = 0; i < advisorList.size(); i++) {
            if (advisorList.get(i).getText().trim().equalsIgnoreCase(advisor.trim())) {
                advisorList.get(i).click();
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("Select Advisor : " + advisor + " Not Found");
        }
    }

    public void selectServiceAdvisorAssignROTab(String advisor) {
        waitPageLoadAssignROTab();
        pWait.until(conditionVisible(bAssignedAdvisor)).click();
        boolean check = false;
        List<WebElement> advisorList = dWait.until(conditionPresenceList(bAdvisorList));
        int j = advisorList.size();
        for (int i = j-1; i >= 0; i = i-1) {
            if (advisorList.get(i).getText().trim().equalsIgnoreCase(advisor.trim())) {
                String dealer = TestParameters.getDealerCode().trim();
                if (dealer.equals("62125")){
                    System.out.println(" \n Select Advisor : " + advisor + "  i is " + i);
                }
                advisorList.get(i).click();
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("Select Advisor : " + advisor + " Not Found");
        }
    }

    public void setPromiseDateInAssignRO(int nextMonths, String day) {
        WebElement elm = null;
        if (day.equalsIgnoreCase("The 3th Wednesday")) {
            elm = dWait.until(conditionVisible(bPromiseTime_Date));
        }else{
            elm = dWait.until(conditionVisible(bPromiseTime_Date));
        }
        scrollElementToCenterView(elm);
        clickElementWithException(bPromiseTime_Date);

        // select nextmonths third week Wednesday
        for (int i = 0; i < nextMonths; i++) {
            String month = dWait.until(conditionVisible(bCurrentMonth)).getText();
            clickElementWithException(bNextMonth);
            dWait.until(conditionTextNotToBe(bCurrentMonth, month));
        }
        clickElementWithException(bThirdWeekWednesday);
        dWait.until(ExpectedConditions.invisibilityOfElementLocated(bThirdWeekWednesday));
    }

    public void setPromiseTimeInAssignRO(int hour, String ap) {

        int ahour = Integer.valueOf(driver.findElement(bCurrentHour).getAttribute("value"));
        while (ahour != hour) {
            if (hour > 11 || hour < 5) {
                dWait.until(conditionVisible(bHourPlus)).click();
            } else {
                dWait.until(conditionVisible(bHourMinus)).click();
            }
            sleep(200);
            ahour = Integer.valueOf(driver.findElement(bCurrentHour).getAttribute("value"));
        }

        // set AM PM
        if (!dWait.until(conditionVisible(bAMPM)).getText().equalsIgnoreCase(ap)) {
            dWait.until(conditionVisible(bAMPM)).click();
        }
    }

    private void waitPageLoadAssignROTab() {
        pWait.until(ExpectedConditions.attributeContains(bAssignROTab, "class", "active"));
    }


}
