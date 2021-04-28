package com.automation.pages.advisor_checkin.dealerTire;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;

public class DealerTireHistoryPage extends WebPage {
    //page object
    //HISTORY tab
    private static By bHistoryTab = By.xpath("//li[@ng-click='selectForm(historyForm)']");

    //next button on the HISTORY page
    private static By bNextOnHistory = By.xpath("//button[@id='g-hproceed']");

    public boolean isUserOnHistory(){
        if(byElementHasClass(bHistoryTab,"active")){
            System.out.println("<====== User on the HISTORY page ======>");
            return true;
        }else{
            return false;
        }
    }

    public void clickButtonOnHistoryPage(String buttonName){
        switch(buttonName){
            case "NEXT":
                clickElementWithException(bNextOnHistory);
                break;
        }
    }
    
}
