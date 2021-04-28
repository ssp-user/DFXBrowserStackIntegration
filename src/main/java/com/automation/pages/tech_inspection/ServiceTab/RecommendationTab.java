package com.automation.pages.tech_inspection.ServiceTab;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class RecommendationTab extends WebPage {

    InspectionTab iTab = new InspectionTab();

    //all web element locator

    //click on recommendation tab
    private static By dRecommendationTab = By.id("tbRecommendations");

    //tech comments field
    private static By dTechCommentsField = By.xpath("//span[contains(text(),'Tech Comments')]/following-sibling::textarea");

    //customer comments
    private static By dCustomerCommentsField = By.xpath("//span[contains(text(),'Customer Comments')]/following-sibling::textarea");

    //Recommendation Field
    private static By bRecommendation = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//input[@wmtxt='RecName']");

    private static By bRecommendation(String section) {
        String locator = "//b[text()='" + section+ "']/../../../..//input[@wmtxt='RecName']";
        return By.xpath(locator);
    }
    //op code Field
    private static By bOPcode = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//input[@wmtxt='RecOPCode']");
    private static By bOPcode(String section) {
        String locator = "//b[text()='" + section+ "']/../../../..//input[@wmtxt='RecOPCode']";
        return By.xpath(locator);
    }

    //Hours Field
    private static By bHours = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//input[@wmtxt='RecHour']");
    private static By bHours(String section) {
        String locator = "//b[text()='" + section+ "']/../../../..//input[@wmtxt='RecHour']";
        return By.xpath(locator);
    }
    //Labor Sale Field
    private static By bLaborSale = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//input[@wmtxt='RecLPrice']");
    private static By bLaborSale(String section) {
        String locator = "//b[text()='" + section+ "']/../../../..//input[@wmtxt='RecLPrice']";
        return By.xpath(locator);
    }
    //Part Sale Field
    private static By bPartSale = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//input[@wmtxt='RecPPrice']");
    private static By bPartSale(String section) {
        String locator = "//b[text()='" + section+ "']/../../../..//input[@wmtxt='RecPPrice']";
        return By.xpath(locator);
    }
    //Price Field
    private static By bPrice = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//input[@wmtxt='RecMenuPrice']");
    private static By bPrice(String section) {
        String locator = "//b[text()='" + section+ "']/../../../..//input[@wmtxt='RecMenuPrice']";
        return By.xpath(locator);
    }
    //plus sign
    private static By bPlusIcon = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//input[contains(@src,'add_recommendation_active.png')]");
    private static By bPlusIcon(String section) {
        String locator = "//b[text()='" + section+ "']/../../../..//input[contains(@src,'add_recommendation_active.png')]";
        return By.xpath(locator);
    }
    //add recommendation DISMISS button -- this is use in case the plus icon getting misclick
    private static By bDismiss = By.xpath("//button[contains(text(),'Dismiss')]");

    //internal comments
    private static By bInternalComments = By.xpath("(//div[@class='tbCommentsContainer' and @style='display: block;']//textarea)[1]");
    private static By bInternalComments(String section) {
        String locator = "//b[text()='" + section+ "']/../../../../../../following-sibling::div[1]//textarea[contains(@placeholder,'internal comments')]";
//        String locator = "//b[text()='" + section+ "']/../../../../../../..//textarea[contains(@placeholder,'internal comments')]";
        return By.xpath(locator);
    }

    //customer comments
    private static By bCustomerComments = By.xpath("(//div[@class='tbCommentsContainer' and @style='display: block;']//textarea)[2]");
    private static By bCustomerComments(String section) {
        String locator = "//b[text()='" + section+ "']/../../../../../../following-sibling::div[1]//textarea[contains(@placeholder,'customer comments')]";
//        String locator = "//b[text()='" + section+ "']/../../../../../../..//textarea[contains(@placeholder,'customer comments')]";
        return By.xpath(locator);
    }

    //Parts Clerk comments
    private static By bPartsClerkComments(String description) {
        String locator = "//div[@class='tabPage' and @style='display: block;']//span[contains(@id,'lblPartDescr') and text()='" + description + "']/../../following-sibling::tr//textarea[contains(@id,'PartQuote')]";
        return By.xpath(locator);
    }

    //plus sign
    private static By bRequestParts = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//span[contains(@class,'chechboxinputparts')]/label");
    private static By bRequestParts(String section) {
        String locator = "//b[text()='" + section+ "']/../../../..//span[contains(@class,'chechboxinputparts')]/label";
        return By.xpath(locator);
    }
    //parts request completed button on recommendation page
    private static By bPartsRequestCompleted = By.id("RecommendationsTab_btnPartsSave");
    //private static By bPartsRequestCompleted = By.xpath("//div/table/tbody/tr[2]/td/input[3]");
    //private static By bPartsRequestCompleted = By.xpath("//input[@name='RecommendationsTab$btnPartsSave']");

    //Parts request completed dialog yes
    private static By bPartsRequestDialog = By.id("yes_dialogBtn");

    //page container use to detect if survey iframe block the main page
    private static By bWidgetContainer = By.xpath("/div[@class='container']");

    //rating star for survey, click the 4th star
    private static By bRatingStar = By.xpath("(//button[@ng-click='ctrl.clickOnStar(star.rate)'])[4]");

    //rating survey iframe
    private static By bRatingIFrame = By.xpath("//iframe[contains(@src,'dealer-fx')]");

    //click rating submit
    private static By bRatingSubmit = By.xpath("//button[@ng-click='ctrl.clickOnSend()']");

    //inventory not selected error message
    private static By bSelectInbventoryStatus = By.xpath("//span[contains(text(),'Please select an inventory status.')]");

    //save button on recommendation page
    private static By bSave = By.xpath("//div[@class='tbCommentsContainer' and @style='display: block;']//a[contains(text(),'Save')]");
    private static By bSave(String section) {
        String locator = "//b[text()='" + section+ "']/../../../../../../following-sibling::div[1]//a[contains(text(),'Save')]";
        return By.xpath(locator);
    }

    //save button on recommendation page
    private static By bSaveRecommendationTab = By.id("RecommendationsTab_btnTechicianPartsSave");

    //description on recommendation tab
    private static By bDescriptionField(String description) {
        String checkLocator = "//span[contains(text(),'Description')]/../../..//span[contains(text(),'" + description + "')]";
        return By.xpath(checkLocator);
    }

    //Tech comments or customer comments on recommendation tab on title section
    private static By bCommentsTitleSection(String description, String commentSection) {
        String checkLocator = "//span[contains(text(),'" + description + "')]/parent::td/parent::tr/parent::tbody/parent::table/parent::td/parent::tr/parent::tbody/parent::table/parent::div/parent::td/parent::tr/parent::tbody/tr[1]//span[contains(text(),'" + commentSection + "')]/following-sibling::textarea";
        return By.xpath(checkLocator);
    }

    //Tech comments or customer comments on recommendation tab on description section
    private static By bCommentsDescriptionSection(String description, String commentSection) {
        String checkLocator = "//span[contains(text(),'" + description + "')]/parent::td/parent::tr/following-sibling::tr/td[2]/span[contains(text(),'" + commentSection + "')]/following-sibling::textarea";
        return By.xpath(checkLocator);
    }

    //description
    private static By bTestDescription(String description) {
        String locator = "//span[contains(@id,'RecommendationsTab') and contains(text(),'" + description + "')]";
        return By.xpath(locator);
    }

    //labor Rate field
    private static By bLaborRateField(String description) {
        String locator = "//span[contains(text(),'" + description + "')]/../following-sibling::td/input[contains(@id,'LabourRate')]";
        return By.xpath(locator);
    }

    //hours on recommendation tab
    private static By bHoursField(String description) {
        //String checkLocator = "//span[contains(text(),'"+description+"')]/parent::td/following-sibling::td/span[contains(text(),'Hours')]/following-sibling::input";
        String locator = "//span[contains(text(),'" + description + "')]/../following-sibling::td/input[contains(@id,'PartsHours')]";
        return By.xpath(locator);
    }

    //Labor Type on recommendation tab
    private static By bLaborTypeDropdown(String description) {
        String locator = "//span[contains(text(),'" + description + "')]/../following-sibling::td/select[contains(@id,'LabourPrice')]";
        return By.xpath(locator);
    }

    //labor on recommendation tab
    private static By bLaborField(String description) {
        //String checkLocator = "//span[contains(text(),'"+description+"')]/parent::td/following-sibling::td/span[text()='Labor']/following-sibling::input";
        String checkLocator = "//span[contains(text(),'" + description + "')]/../following-sibling::td/input[contains(@id,'PartsLabor')]";
        return By.xpath(checkLocator);
    }

    //parts on recommendation tab
    private static By bPartsField(String description) {
        //String checkLocator = "//span[contains(text(),'"+description+"')]/parent::td/following-sibling::td/span[contains(text(),'Parts')]/following-sibling::input";
        String checkLocator = "//span[contains(text(),'" + description + "')]/../following-sibling::td/input[contains(@id,'PartsParts')]";
        return By.xpath(checkLocator);
    }

    private static By bPartsFieldUpdate(String description) {
        String checkLocator = "//span[contains(text(),'" + description + "')]/parent::td/following-sibling::td/span[contains(text(),'Parts')]/following-sibling::input[@type='hidden']";
        return By.xpath(checkLocator);
    }

    //price on recommendation tab
    private static By bPriceField(String description) {
        //String checkLocator = "//span[contains(text(),'"+description+"')]/parent::td/following-sibling::td/span[contains(text(),'Price')]/following-sibling::input";
        String checkLocator = "//span[contains(text(),'" + description + "')]/../following-sibling::td/input[contains(@id,'PartPrice')]";
        return By.xpath(checkLocator);
    }

    //Parts list associated with description
    private static By bPartsListWithDescription(String description) {
        String checkLocator = "//span[contains(text(),'" + description + "')]/../../..//button[@data-target='#partPickListModal']";
        return By.xpath(checkLocator);
    }

    //approved associated with description
    private static By bApprovedWithDescription(String description) {
        String checkLocator = "//span[contains(text(),'" + description + "')]/parent::td/parent::tr/following-sibling::tr/descendant::div[7]";
        return By.xpath(checkLocator);
    }

    //in stock associated with description
    private static By bInStockWithDescription(String description) {
        String checkLocator = "//span[contains(text(),'" + description + "')]/parent::td/parent::tr/following-sibling::tr/td/table/tbody/tr/td[2]/table/tbody/tr/td/label/div[contains(text(),'In Stock')]";
        return By.xpath(checkLocator);
    }

    //parts request associated with description
    private static By bPartsRequiredWithDescription(String description) {
        String checkLocator = "//span[contains(text(),'" + description + "')]/../../..//input[contains(@id,'PartRequested') and @checked='checked']";
        return By.xpath(checkLocator);
    }

    //SHOW TOTALS footer
    private static By bShowFooter = By.xpath("//*[@id='collapseTotalsBar']");

    //price on recommended footer
    private static By bRecommendedPrice = By.id("calcRecommended");

    //price on approved footer
    private static By bApprovedPrice = By.id("calcApproved");

    //price on deferred footer
    private static By bDeferredPrice = By.id("calcDeferred");

    //description on approvedService
    private static By bDescriptionOnApprovedServiceField(String description) {
        String checkLocator = "//span[@class='EVIRItemTextNew' and contains(text(),'" + description + "')]";
        return By.xpath(checkLocator);
    }

    //labor on approvedService
    private static By bLaborOnApprovedServiceField(String description) {
        String checkLocator = "//span[@class='EVIRItemTextNew' and contains(text(),'" + description + "')]/parent::td/following-sibling::td[2]/span";
        return By.xpath(checkLocator);
    }

    //price on approvedService
    private static By bPriceOnApprovedServiceField(String description) {
        String checkLocator = "//span[@class='EVIRItemTextNew' and contains(text(),'" + description + "')]/parent::td/following-sibling::td[3]/span";
        return By.xpath(checkLocator);
    }

    //view history pop up title
    private static By bViewHistoryPopupTitle = By.xpath("//div[@id='dhtmlwindowholder']/div/div[2]/font/span/font");

    //view Status log popup title
    private static By bStatusLogPopTitle = By.xpath("(//div[@id='dhtmlwindowholder']/*/div[@class='drag-handle'])[2]");

    //close button on View History popUp French
    private static By bCloseViewHistoryPopupFr = By.xpath("//div/img[@title='Fermer']");

    //Close button on View History popup
    private static By bCloseViewHistoryPopup = By.xpath("//div/img[@title='Close']");

    //close button on Status Log popup
    private static By bCloseStatusLogPopUp = By.xpath("//div[@style='opacity: 1;']/div/img[2]");

    //view menu tab
    private static By bViewMenuTab = By.id("imgViewMenu");

    //Parts Request Completed On recommendation tab
    private static By bPartsRequestCompletedONRecommendationTab = By.id("RecommendationsTab_btnPartsSave");

    //this is Parts List header
    private static By bPartsListHeader = By.id("RecommendationsTab_grdPartHeader_ctl02_lblPartListHeader");

    //approved associated with description
    private static By bIconToggleOnApproveServiceWithDescription(String description) {
        String checkLocator = "//span[contains(text(),'" + description + "')]/parent::td/following-sibling::td[4]/div[@class='OPCodeSelection']";
        return By.xpath(checkLocator);
    }

    //ready for processing
    private static By bReadyForProcessing = By.id("btnReadyForProcessing");

    //select service from the list
    private static By bSelectServiceFromTheList = By.xpath("//div[@class='tbItemContainer' and @style='display: block;']//li[@class='search-field']/input");
    private static By bSelectServiceFromTheList(String section) {
        String locator = "//b[text()='" + section+ "']/../../../..//li[@class='search-field']/input";
        return By.xpath(locator);
    }

    public void clickOnRecommendationTab() {
        iTab.waitForLoadingCircleToDisappearMPI();
        clickElementWithException(dRecommendationTab);
    }

    public void setRecommendationName(String value) {
        long startTime = System.currentTimeMillis();
        clearAndInputElementWithException(bRecommendation, value);
        while (!getElementAttributeWithException(bRecommendation, "value").equals(value)) {
            clearAndInputElementWithException(bRecommendation, value);
            sleep(1000);
            if ((System.currentTimeMillis() - startTime) > 40000) {
                break;
            }
        }
    }

    public void setOPcode(String txt) {
        clearAndSend(bOPcode, txt);
    }

    public void setHours(String txt) {
        clearAndSend(bHours, txt);
    }

    public void setLaborSale(String txt) {
        clearAndSend(bLaborSale, txt);
    }

    public void setPartSale(String txt) {
        clearAndSend(bPartSale, txt);
    }

    public void setPrice(String txt) {
        clearAndSend(bPrice, txt);
    }

    public void setRecommendationName(String value, String section) {
        long startTime = System.currentTimeMillis();
        clearAndInputElementWithException(bRecommendation(section), value);
        while (!getElementAttributeWithException(bRecommendation(section), "value").equals(value)) {
            clearAndInputElementWithException(bRecommendation(section), value);
            sleep(1000);
            if ((System.currentTimeMillis() - startTime) > 40000) {
                break;
            }
        }
    }

    public void setOPcode(String txt, String section) {
        clearAndSend(bOPcode(section), txt);
    }

    public void setHours(String txt, String section) {
        clearAndSend(bHours(section), txt);
    }

    public void setLaborSale(String txt, String section) {
        clearAndSend(bLaborSale(section), txt);
    }

    public void setPartSale(String txt, String section) {
        clearAndSend(bPartSale(section), txt);
    }

    public void setPrice(String txt, String section) {
        clearAndSend(bPrice(section), txt);
    }


    public void setInternalComments(String txt) {
        clearAndSend(bInternalComments, txt);
    }

    public void setInternalComments(String txt,String section) {
        clearAndSend(bInternalComments(section), txt);
    }

    public void setCustomerComments(String txt) {
        clearAndSend(bCustomerComments, txt);
    }
    public void setCustomerComments(String txt,String section) {
        clearAndSend(bCustomerComments(section), txt);
    }

    public void setPartsClerkComments(String txt, String description) {
        clearAndInputElementWithException(bPartsClerkComments(description), txt);
    }

    public void clickElementWithExceptionOnRecommendationPage(By element) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                driver.findElement(element).click();
                noException = true;
            } catch (WebDriverException e) {
                if (!iTab.isUserOnRecommendationTab()) {
                    System.out.println("<====== user is not on the recommendation tab, will click and try it again ======>");
                    clickOnRecommendationTab();
                }
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 30000) {
                Assert.fail("<====== user not able get on the Recommendation tab page ======>");
                break;
            }
        }
    }

    public String getElementAttributeWithExceptionOnRecommendationPage(By element, String attributeName) {
        long startTime = System.currentTimeMillis();
        String elementValue = "";
        boolean noException = false;
        while (!noException) {
            try {
                elementValue = driver.findElement(element).getAttribute(attributeName);
                noException = true;
            } catch (StaleElementReferenceException e) {
                System.out.println("<====== Stale Element Reference Exception occurred on get " + element + " attribute method ======>");
                sleep(1000);
            } catch (WebDriverException e) {
                if (!iTab.isUserOnRecommendationTab()) {
                    System.out.println("<====== user is not on the recommendation tab, will click and try it again ======>");
                    clickOnRecommendationTab();
                }
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 30000) {
                Assert.fail("<====== " + element + " not able to get attribute " + attributeName + " ======>");
                break;
            }
        }
        return elementValue;
    }

    public String getDescriptionValueOnRecommendationTab(String description) {
        String descriptionValue = getElementAttributeWithExceptionOnRecommendationPage(bDescriptionField(description), "innerHTML");
        return descriptionValue;
    }

    public String getHoursValueOnRecommendationTab(String description) {
        String hoursValue = getElementAttributeWithExceptionOnRecommendationPage(bHoursField(description), "value");
        return hoursValue;
    }

    public void setHoursValueOnRecommendationTab(String description, String txt) {
        clearAndInput(bHoursField(description), txt);
        dWait.until(conditionVisible(bTestDescription(description))).click();
    }

    public String getLaborTypeValueOnRecommendationTab(String description) {
        return new Select(driver.findElement(bLaborTypeDropdown(description))).getFirstSelectedOption().getText();
    }

    public String getLaborRateValueOnRecommendationTab(String description) {
        return getElementAttributeWithExceptionOnRecommendationPage(bLaborRateField(description), "value");
    }

    public void setLaborRateValueOnRecommendationTab(String description, String txt) {
        clearAndInputElementWithException(bLaborRateField(description), txt);
    }

    public void setLaborTypeOnRecommendationTab(String description, String txt) {
        sleep(1000);
        selectDropListElementWithException(bLaborTypeDropdown(description), txt);
        dWait.until(conditionVisible(bTestDescription(description))).click();
        sleep(1000); //this for price update on non FCA environment issue, price not update correctly, see defect here: https://www.useloom.com/share/948a9743a46e4093bc054929bf739d80
    }

    public String getLaborValueOnRecommendationTab(String description) {
        String laborValue = getElementAttributeWithExceptionOnRecommendationPage(bLaborField(description), "value");
        return laborValue;
    }

    public void setLaborValueOnRecommendationTab(String description, String txt) {
        clearAndInputElementWithException(bLaborField(description), txt);
        sleep(3000);
    }

    public String getPartsValueOnRecommendationTab(String description, String parts) {
        long startTime = System.currentTimeMillis();
        iTab.waitForLoadingCircleToDisappearMPI();
        String partsValue = getElementAttributeWithExceptionOnRecommendationPage(bPartsField(description), "value");
        while (!partsValue.equals(parts)) {
            if (webElementHasClass(bPartsField(description), "PriceChanged")) {
                System.out.println("<====== Parts price changed ======>");
                partsValue = getElementAttributeWithExceptionOnRecommendationPage(bPartsField(description), "value");
            }
            if (partsValue.equals(parts)) {
                break;
            } else if ((System.currentTimeMillis() - startTime) > 40000) {
                break;
            }
        }
        return partsValue;
    }

    public void setPartsValueOnRecommendationTab(String description, String txt) {
        clearAndInput(bPartsField(description), txt);
        dWait.until(conditionVisible(bTestDescription(description))).click();
        sleep(1000); //this for price update on non FCA environment issue, price not update correctly, see defect here: https://www.useloom.com/share/948a9743a46e4093bc054929bf739d80
    }

    public String getPriceValueOnRecommendationTab(String description) {
        String priceValue = getElementAttributeWithExceptionOnRecommendationPage(bPriceField(description), "value");
        return priceValue;
    }

    public String getCommentsValueONRecommendationTabOnTitleSection(String description, String commentSection) {
        String commentValue = getElementTextWithException(bCommentsTitleSection(description, commentSection));
        return commentValue;
    }

    public String getCommentsValueONRecommendationTabOnDescriptionSection(String description, String commentSection) {
        String commentValue = getElementTextWithException(bCommentsDescriptionSection(description, commentSection));
        return commentValue;
    }

    public void setCommentsValueOnRecommendationTabOnTitleSection(String description, String commentSection, String commentText) {
        clearAndSend(bCommentsTitleSection(description, commentSection), commentText);
    }

    public void setCommentsValueOnRecommendationTabOnDescriptionSection(String description, String commentSection, String commentText) {
        clearAndSend(bCommentsDescriptionSection(description, commentSection), commentText);
    }

    public String getTechCommentsFieldText() {
        String actualTechComment = getElementTextWithException(dTechCommentsField);
        return actualTechComment;
    }

    public String getCustomerCommentsFieldText() {
        String actualCustomerComment = getElementTextWithException(dCustomerCommentsField);
        return actualCustomerComment;
    }

    public void clickOnAddRecommendationIcon() {
        dWait.until(conditionClick(bPlusIcon)).click();
//        if (webElementHasClass(bSelectServiceFromTheList, "default")) {
//            System.out.println("<====== the plus icon not getting clicked, now click again ======>");
//            dWait.until(conditionClick(bPlusIcon)).click();
//            try {
//                sWait.until(conditionVisible(bDismiss)).click();
//            } catch (WebDriverException ex) {
//                //this is used to in case plus icon getting mis click.
//            }
//        }
    }

    public void clickOnAddRecommendationIcon(String section) {
        dWait.until(conditionClick(bPlusIcon(section))).click();
//        if (webElementHasClass(bSelectServiceFromTheList(section), "default")) {
//            System.out.println("<====== the plus icon not getting clicked, now click again ======>");
//            dWait.until(conditionClick(bPlusIcon(section))).click();
//            try {
//                sWait.until(conditionVisible(bDismiss)).click();
//            } catch (WebDriverException ex) {
//                //this is used to in case plus icon getting mis click.
//            }
//        }
    }


    public void clickOnRequestParts() {
//        iTab.waitForLoadingCircleToDisappearMPI();
        dWait.until(conditionClick(bRequestParts)).click();
    }

    public void clickOnRequestParts(String section) {
//        iTab.waitForLoadingCircleToDisappearMPI();
        dWait.until(conditionClick(bRequestParts(section))).click();
    }

    public void clickOnPartsRequestCompleted() {
        long startTime = System.currentTimeMillis();
        iTab.waitForLoadingCircleToDisappearMPI();
        scrollPageDown(1000);
        dWait.until(conditionVisible(bPartsRequestCompleted)).click();
        while (!driver.getTitle().equals("MPI List")) {
            try {
                driver.findElement(bPartsRequestCompleted).click();
                System.out.println("<====== Parts request completed button clicked ======>");
            } catch (WebDriverException e) {
                //it's blocked by the prompt yes
                scrollPageDown(100);
            }
            try {
                driver.findElement(bPartsRequestDialog).click();
                System.out.println("<====== clicking the dialog prompt yes on recommendation ======>");
            } catch (WebDriverException e) {
                //the parts request completed prompt is not there, please ignore
            }
            try {
                driver.switchTo().frame(driver.findElement(bRatingIFrame));
                driver.findElement(bRatingStar).click();
                driver.findElement(bRatingSubmit).click();
                System.out.println("<====== clicked the survey rating ======>");
            } catch (WebDriverException e) {
                //the rating prompt is not there
            }
            sleep(1500);
            if ((System.currentTimeMillis() - startTime) > 60000) {
                Assert.fail("<====== Not able to click on the Parts Request Completed button, test fail ======>");
                break;
            }
        }
        clickOnInStockIfFailedPreviously();
    }

    public void clickOnPartsRequestCompletedBtn() {
        iTab.waitForLoadingCircleToDisappearMPI();
        scrollPageDown(1000);
        try {
            dWait.until(conditionVisible(bPartsRequestCompleted)).click();
            System.out.println("<====== Parts request completed button clicked ======>");
        } catch (WebDriverException e) {
            //it's blocked by the prompt yes
            scrollPageDown(100);
        }
        try {
            dWait.until(conditionVisible(bPartsRequestDialog)).click();
            System.out.println("<====== clicking the dialog prompt yes on recommendation ======>");
        } catch (WebDriverException e) {
            //the parts request completed prompt is not there, please ignore
        }
        try {
            driver.switchTo().frame(driver.findElement(bRatingIFrame));
            driver.findElement(bRatingStar).click();
            driver.findElement(bRatingSubmit).click();
            System.out.println("<====== clicked the survey rating ======>");
        } catch (WebDriverException e) {
            //the rating prompt is not there
        }
        sleep(3000);
//        clickOnInStockIfFailedPreviously();
    }

    public void clickOnInStockIfFailedPreviously() {
        if (driver.findElements(bSelectInbventoryStatus).size() != 0) {
            System.out.println("<====== Please select an inventory status. error msg displayed ======>");
            clickOnInStockAssociatedWithDescription("Test1");
            InspectionTab iTab = new InspectionTab();
            iTab.saveAllRecommendation();
            clickOnPartsRequestCompleted();
        }
    }

    public void clickOnSave() {
        dWait.until(conditionClick(bSave)).click();
    }

    public void clickOnSave(String section) {
        dWait.until(conditionClick(bSave(section))).click();
    }

    public void clickOnSaveOnRecommendationTab() {
        dWait.until(conditionClick(bSaveRecommendationTab)).click();
    }

    public void clickONPartLIstAssociatedWithDescription(String description) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                driver.findElement(bPartsListWithDescription(description)).click();
                noException = true;
                sleep(1000);
                while(!isUserOnPartsList()){
                    driver.findElement(bPartsListWithDescription(description)).click();
                    noException = true;
                    if((System.currentTimeMillis()-startTime)> 5000){
                        break;
                    }
                }
            } catch (WebDriverException e) {
                System.out.println("<====== I'm not able to click the part list associate to '" + description + "' description, so I will scroll down the page and try it again ======>");
                scrollPageDown(1500);
                sleep(1000);
                noException = false;
            }
//            if ((System.currentTimeMillis() - startTime) > 15000) {
//                if (!iTab.isUserOnRecommendationTab()) {
//                    System.out.println("<====== User is not on the recommendation tab for some reason, i will click on Recommendation tab ======>");
//                    clickOnRecommendationTab();
//                }
//            }
            if ((System.currentTimeMillis() - startTime) > 30000) {
                Assert.fail("<====== Not able to find the " + description + " description, test failed ======>");
                break;
            }
        }
    }

    public void clickOnApprovedAssociatedWithDescription(String description) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                driver.findElement(bApprovedWithDescription(description)).click();
                noException = true;
            } catch (WebDriverException e) {
                System.out.println("<====== Approve button is covered by total bar, need to scroll page down ======>");
                noException = false;
                scrollPageDown(100);
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 60000) {
                break;
            }
        }
    }

    public void clickOnInStockAssociatedWithDescription(String description) {
        long startTime = System.currentTimeMillis();
        boolean noException = false;
        while (!noException) {
            try {
                driver.findElement(bInStockWithDescription(description)).click();
                noException = true;
            } catch (WebDriverException e) {
                System.out.println("<====== In stock button is covered by total bar, need to scroll page down ======>");
                noException = false;
                scrollPageDown(100);
                sleep(1000);
            }
            if ((System.currentTimeMillis() - startTime) > 60000) {
                break;
            }
        }
    }

    public String checkIfPartsRequestedToggled(String description) {
        String partsRequested = "OFF";
        if (driver.findElements(bPartsRequiredWithDescription(description)).size() != 0) {
            partsRequested = "ON";
        }
        return partsRequested;
    }

    public void clickOnShowTotals() {
        iTab.waitForLoadingCircleToDisappearMPI();
        //dWait.until(ExpectedConditions.invisibilityOfElementLocated(bShowFooter));
        dWait.until(conditionClick(bShowFooter)).click();
//        clickElementWithException(bShowFooter);
    }

    public String getRecommendedPriceOnFooter() {
//        String recommendedPrice = dWait.until(conditionVisible(bRecommendedPrice)).getAttribute("innerHTML");
//        return recommendedPrice;
        return dWait.until(conditionVisible(bRecommendedPrice)).getText();
    }

    public String getApprovedPriceOnFooter() {
        String approvedPrice = driver.findElement(bApprovedPrice).getAttribute("innerHTML");
        return approvedPrice;
    }

    public String getDeferredPriceOnFooter() {
        String approvedPrice = driver.findElement(bDeferredPrice).getAttribute("innerHTML");
        return approvedPrice;
    }

    public String getDescriptionValueOnArrovedServiceTab(String description) {
        String descriptionValue = driver.findElement(bDescriptionOnApprovedServiceField(description)).getAttribute("innerHTML");
        return descriptionValue;
    }

    public String getLaborValueOnArrovedServiceTab(String description) {
        String laborValue = driver.findElement(bLaborOnApprovedServiceField(description)).getAttribute("innerHTML");
        return laborValue;
    }

    public String getPriceValueOnArrovedServiceTab(String description) {
        String price = driver.findElement(bPriceOnApprovedServiceField(description)).getAttribute("innerHTML");
        if (price.matches("(?i).*nbsp.*")) {
            price = price.replace("&nbsp;", " ");
        }
        return price;
    }

    public void clickOnIconBasedONDescritionOnApprovedService(String description) {
        dWait.until(conditionClick(bIconToggleOnApproveServiceWithDescription(description))).click();
    }

    public String getViewHistoryPopUpTitle() {
        iTab.waitForLoadingCircleToDisappearMPI();
        String actualTitle = getElementAttributeWithException(bViewHistoryPopupTitle, "innerHTML");
        return actualTitle;
    }

    public String getStatusLogPopUpTitle() {
        iTab.waitForLoadingCircleToDisappearMPI();
        try {
            dWait.until(conditionVisible(bStatusLogPopTitle));
        } catch (org.openqa.selenium.TimeoutException ex) {
            dWait.until(conditionVisible(bStatusLogPopTitle));
        }
        String popupTitle = driver.findElement(bStatusLogPopTitle).getAttribute("innerHTML");
        return popupTitle;
    }

    public void closeViewHistoryPopUp() {
        dWait.until(conditionClick(bCloseViewHistoryPopup)).click();
    }

    public void closeViewHistoryPopUpFr() {
        dWait.until(conditionClick(bCloseViewHistoryPopupFr)).click();
    }

    public void closeStatusLogPopUp() {
        dWait.until(conditionClick(bCloseStatusLogPopUp)).click();
    }

    public String getViewMenuPopupURL() {
        return dWait.until(conditionVisible(bViewMenuTab)).getAttribute("onclick");
    }

    public void clickONPartsRequestCompletedOnRecommendationTab() {
        iTab.waitForLoadingCircleToDisappearMPI();
//        long startTime = System.currentTimeMillis();
//        scrollPageDown(1500);
        scrollPageDown(2500);
//        while (!driver.getTitle().equals("MPI List")) {
            try {
                driver.findElement(bPartsRequestCompletedONRecommendationTab).click();
            } catch (WebDriverException ex) {
                //it's blocked by the prompt yes
                System.out.println("<====== The parts request completed not getting clicked ======>");
            }
            try {
                driver.findElement(bPartsRequestDialog).click();
                System.out.println("<====== clicking the dialog prompt yes on recommendation ======>");
                sleep(2000);
            } catch (WebDriverException ex) {
                //the parts request completed prompt is not there, please ignore
            }
//            sleep(800);
//            if ((System.currentTimeMillis() - startTime) > 30000) {
//                Assert.fail("<====== Not able to click on the Parts Request Completed button, test fail ======>");
//                break;
//            }
//        }
    }

    public String verifyReadyForProcessingStatus() {
        String status = "";
        if (dWait.until(conditionVisible(bReadyForProcessing)).getAttribute("disabled").isEmpty()) {
            return status = "On";
        } else {
            return status = "disabled";
        }
    }

    public boolean isUserOnPartsList(){
        if(driver.findElement(bPartsListHeader).isDisplayed()){
            return true;
        }else{
            return false;
        }
    }

}
