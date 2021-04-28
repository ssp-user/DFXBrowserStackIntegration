package com.automation.pages.advisor_checkin.dealerTire;

import com.automation.pages.common.WebPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DealerTireServicePage extends WebPage {
	// page object
	// Tires tab
	private static By bTiresTab = By.xpath("//li[@*='tires.tab.clicked']");
	private static By bFirstAddTire = By.xpath("(//span[contains(@title, 'ADD TIRE(S)')])[1]"); //By.xpath("(//span[@title='ADD TIRE(S) 1'])[1]");

//    @FindBy(css="#content a.sizeTarget")
//    private static List<WebElement> tireSizeList;												// Tire Offer List
	private static By bTireSizeList = By.cssSelector("#content a.sizeTarget"); // Tire Offer List // Tire Offer List

	// Add Tires icon
	private static By bAddTireIconWithOrder(int num) {
		String order = String.valueOf(num);
		String locator = "(//span[@title='ADD TIRE(S)'])[" + order + "]";
		return By.xpath(locator);
	}

	// tire size content
	private static By bTireSize(String content) {
		String locator =  "//div[@id='content']//a"; //"//a[@class='button sizeTarget' and @data-size='" + content + "']";
		return By.xpath(locator);
	}

	// tire size number
	private static By bTireSizeNumber(String num) {
		return (By.xpath(String.format("(//a[@class='button sizeTarget'])[%s]", num)));
	}

	// ADD TO QUOTE button
//    private static By bAddToQuote = By.xpath("//header[@id='header']//a[@class='button installed']");
	private static By bAddToQuote = By.cssSelector("#dt-header a.installed");

	// view button on the tire offer pop up
	private static By bViewOrder(int num) {
		String order = String.valueOf(num);
		String locator = "(//a[@class='button' and contains(text(),'View')])[" + order + "]";
		return By.xpath(locator);
	}

	// TIRE OFFER TITLE -- use to wait till iframe show
	private static By bTireOfferTitle = By.xpath("//h3[text()='Tire Offer']");

	// pop up frame name
	private static By bPopUpFrame = By.xpath("//iframe[@iframe-post-message='recieve']");

	// QUOTE button
	private static By bQuoteButton = By.xpath("//button[@id='g-sproceed']/span[contains(text(),'Quote')]");

	// Generate Quote load
	private static By bGenerateQuoteLoad = By.xpath("//div[@class='modal-footer']//i[@class='spinner']");

	// Generate Quote error message
	// private static By bGenerateQuoteError =
	// By.xpath("//label[@ng-show='!rorCompleted && rorError']");

	// Generate Quote check mark
	private static By bGenerateQuoteCheckMark = By.xpath(
			"//div[@id='modalCustomerResult']//span[contains(@class,'custom-checkbox')]/../../../div[not(contains(@class,'ng-hide'))]");

	// Generate Quote check mark selected
	private static By bGenerateQuoteCheckMarkSelected = By
			.xpath("//div[@id='modalCustomerResult']//span[contains(@class,'custom-checkbox selected')]");

	// close button on generate quote
	private static By bClose = By.xpath("//span[text()='CLOSE']");

	private static By tireOfferSpinnerLocator = By.cssSelector("#contentId > div.spinner"); // Tire Offer Loading
																							// Spinner

//    @FindBy(id="PDFokButton")
	private static By bReminderOKBtn = By.id("PDFokButton"); // Friendly Reminder OK Button

//    @FindBy(css="#dt-header a.installed span.price")
	private static By bInstallPrice = By.cssSelector("#dt-header a.installed span.price"); // Install Price

	public static String tireInstallPrice;

//    @FindBy(css="#services input[name='servicePrice']")
//    private static List<WebElement> selectedItemPriceList;																// All Selected Line item Price List
	private static By bSelectedItemPriceList = By.cssSelector("#services input[name='servicePrice']"); // All Selected
																										// Line item
																										// Price List

	public void clickTabOnServicesPage(String tabName) {
		switch (tabName) {
		case "TIRES":
			clickElementWithException(bTiresTab);
			break;
		}
	}

	public void clickIconOrderOnServicesPage(int order) {
//        if (order.equals("first")) {
//            order = "1";
//        } else if (order.contains("th")) {
//            order = order.replace("th", "");
//        }
		clickElementWithException(bAddTireIconWithOrder(order));
	}

	public void clickAddFirstTire() {
		clickElementWithException(bFirstAddTire);
	}

	public void clickContentOnPopUpOnServicsPage(String content, String popup) {
		switch (popup) {
		case "TIRE OFFER":
			sleep(8000);
			switchToPopUpIframe();
			List<WebElement> list = driver.findElements((bTireSize(content)));
			WebElement elementLocator = list.get(list.size()-1);
			elementLocator.click();
			switchToDefaultContent();
			break;
		}
	}

	public void selectNumberTireOfferSize(int order) {
//        if (order.equals("first")) {
//            order = "1";
//        } else if (order.contains("th")) {
//            order = order.replace("th", "");
//        }
//        int iOrder = Integer.valueOf(order);
		switchToPopUpIframe();
		pWait.until(ExpectedConditions.invisibilityOfElementLocated(tireOfferSpinnerLocator));
		WebElement element = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bTireSizeList)).get(order);
		clickElementTimesTillDisappear(element, 4);
		switchToDefaultContent();
	}

	public void clickButtonOnPopUpOnServicsPage(String buttonName) {
		switchToPopUpIframe();
		switch (buttonName) {
		case "ADD TO QUOTE":
			tireInstallPrice = pWait.until(conditionVisible(bInstallPrice)).getText();
			clickElementWithSeconds(bAddToQuote,5000);
			reminderHandler();
			break;
		}
		switchToDefaultContent();
	}

	private void reminderHandler() { // Friendly Reminder Message OK Button
		sleep(5000);
		try {
			if (driver.findElement(bReminderOKBtn).isDisplayed()) {
				lWait.until(ExpectedConditions.visibilityOfElementLocated(bReminderOKBtn));			
				lWait.until(ExpectedConditions.elementToBeClickable(bReminderOKBtn));				
				driver.findElement(bReminderOKBtn).click();
			}
		} catch (NoSuchElementException e) {
//			e.printStackTrace();
			System.out.println("No reminder Shows ...");
		}
	}

	public String getSelectedTirePriceList() {
		int i = pWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bSelectedItemPriceList)).size();
		System.out.println("iiiiiii " + i);
		String tireItemPrice = driver.findElements(bSelectedItemPriceList).get(i - 1).getAttribute("value").trim();
		return tireItemPrice;
	}

	public void clickButtonProductWithOrderOnServicePage(String buttonName, int order, String popup) {
//        if (order.equals("first")) {
//            order = "1";
//        }
		switch (popup) {
		case "TIRE OFFER":
			switchToPopUpIframe();
			switch (buttonName) {
			case "VIEW":
				clickElementWithException((bViewOrder(order)));
				break;
			}
			switchToDefaultContent();
			break;
		}
	}

	public void clickViewButtonOrderOnTireOffer(int order) {
//        if (order.equals("first")) {
//            order = "1";
//        }
		switchToPopUpIframe();
		clickElementWithException((bViewOrder(order)));
		switchToDefaultContent();
	}

	public void switchToPopUpIframe() {
		waitForElementVisibleWithException(bTireOfferTitle);
		driver.switchTo().frame(driver.findElement(bPopUpFrame));
	}

	public void switchToDefaultContent() {
		sleep(5000);
		driver.switchTo().defaultContent();
	}

//    public void clickButtonOnServicesPage(String buttonName) {
//        switch (buttonName) {
//            case "QUOTE":
//                clickElementWithException(bQuoteButton);
//                break;
//        }
//    }

	public boolean verifyGenerateQuoteStatus() {
		sleep(5000);
		waitForGenerateQuoteLoad();		
		int checkMarkTotal = driver.findElements(bGenerateQuoteCheckMark).size() - 1; // minus 1 is due to note count as
																						// one field
		int checkMarkCompleted = driver.findElements(bGenerateQuoteCheckMarkSelected).size();
		/*
		 * if(driver.findElement(bGenerateQuoteError).isDisplayed()){ String errorMsg =
		 * driver.findElement(bGenerateQuoteError).getText();
		 * System.out.println("<====== "
		 * +errorMsg+" Error show, Generate Quote failed ======>"); return false; }
		 */
		if (driver.findElement(bGenerateQuoteLoad).getAttribute("style").equals("display: none;")
				&& (checkMarkTotal == checkMarkCompleted)) {
			System.out.println("<====== Generate Quote completed ======>");
			return true;
		} else {
			System.out.println("<====== Generate Quote failed ======>");
			return false;
		}
	}

	public void waitForGenerateQuoteLoad() {
		waitForElementHasAttributeValue(bGenerateQuoteLoad, "style", "display: none;");
		pWait.until(conditionClick(bClose));
	}

}
