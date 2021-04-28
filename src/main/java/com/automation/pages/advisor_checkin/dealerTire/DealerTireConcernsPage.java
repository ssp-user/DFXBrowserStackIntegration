package com.automation.pages.advisor_checkin.dealerTire;

import com.automation.pages.common.WebPage;
import org.openqa.selenium.By;

public class DealerTireConcernsPage extends WebPage {
    //page object

   //next button on the concerns page
    private static By bNextOnConcerns = By.xpath("//button[@id='g-cproceed']");

    public void clickButtonOnConcernsPage(String buttonName){
        switch(buttonName){
            case "NEXT":
                clickElementWithException(bNextOnConcerns);
                break;
        }
    }
    
}
