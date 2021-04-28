package com.automation.pages.schedule;

import com.automation.pages.common.WebPage;
import com.automation.utils.sync.SyncPage;
import cucumber.api.java.en.And;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SSA_SchedulePage extends SSA_FramePage {

    private static Logger log = Logger.getLogger(SSA_SchedulePage.class);


    private static By bTryAgainBtn = By.xpath("//div[text()='Try again']");

//    @FindBy(xpath="//div[@id='test_schedule_transportation_options']/div/div")
//    protected static List<WebElement> transportationOptions;
//
//    @FindBy(xpath="//div[@class='scroll-select scroll-select--date-and-time ng-scope']/div[contains(@class,'select__next')]")
//    protected static WebElement selectNextDate;
      private static By bWeekRightBtn = By.xpath("//div[@class='scroll-select scroll-select--date-and-time ng-scope']/div[contains(@class,'select__next')]");
//
//    @FindBy(xpath="//div[@id='test_time_segment_scroll']/div/div/div[@class='hour ng-binding ng-scope']")
//    protected static List<WebElement> selectTime;
    private static By bSelectTime = By.xpath("//div[@id='test_time_segment_scroll']/div/div/div[@class='hour ng-binding ng-scope']");
//
//	/*@FindBy(xpath="//div[@id='test_time_segment_scroll']")
//	protected static WebElement timeSection;*/
//
//    @FindBy(css="div.scroll-select--date-and-time")
//    protected static WebElement timeSection;
//
//	/*@FindBy(css="div.icon-loader--service")
//	protected static WebElement timeLoader;*/
//
//
//    @FindBy(xpath="//div[@class='centering']/button[@ng-click='next()']")
//    protected static WebElement nextBtn;
//
//
//    @FindBy(xpath="//div[@id='test_schedule_transportation_options']/div[@class='item ng-scope']")
//    protected static List<WebElement> selectEnabledTransOption;
    private static By bTransportationOPtion = By.xpath("//div[@id='test_schedule_transportation_options']/div[@class='item ng-scope']");


    //   imported from TestNG


    public void waitForPageToLoad(){																							// Wait for page to finish loading
        pWait.until(SyncPage.condDomReadyState());
        dWait.until(conditionVisible(bWeekRightBtn));
        dWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bSelectTime));
    }

//    public void handleTry() {
//        sleep(1500);
//        try {
//            driver.findElement(bTryAgainBtn).click();
//            System.out.println(" Try Again happen !");
//            sleep(1500);
//        } catch (WebDriverException we) {
//
//        }
//    }

    public void clickWeekButtonTimes(String buttonName,int times) {
        for (int i = 0; i < times ; i++){
            if (buttonName.equals(">")){
                driver.findElement(bWeekRightBtn).click();
            }else if(buttonName.equals("<")){
//                driver.findElement(bWeekLeftBtn).click();
            }else{
                //
            }
        }
    }


    public void clickTimeSlot(int item) {
        sleep(1000);
        int size = driver.findElements(bSelectTime).size();
        if (size > 0 ){
            moveToClick(driver.findElements(bSelectTime).get(item-1));
            sleep(500);
        }else{
            clickWeekButtonTimes(">",1);
            clickTimeSlot(1);
        }
    }

    public void clickTransportation(String item) {
        List<WebElement> elements = driver.findElements(bTransportationOPtion);
        if (item.toUpperCase().equals(item.toLowerCase())){
            int idx = Integer.valueOf(item) - 1;
            if (idx >= 0 ){
                elements.get(idx).click();
            }
        }else{
            for (int i = 0 ; i <  elements.size(); i++){
                if (elements.get(i).getText().equalsIgnoreCase(item)){
                    elements.get(i).click();
                    break;
                };
            }
        }

    }


}
