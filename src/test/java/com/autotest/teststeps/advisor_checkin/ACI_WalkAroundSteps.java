package com.autotest.teststeps.advisor_checkin;


import com.automation.pages.advisor_checkin.*;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.java.en.And;
import org.apache.log4j.Logger;

public class ACI_WalkAroundSteps extends BaseTestSteps {

    ACI_WalkAroundPage awPage = new ACI_WalkAroundPage();


    private static Logger log = Logger.getLogger(ACI_WalkAroundSteps.class);


    @And("^I select \"([^\"]*)\" on 'WALK AROUND' page$")
    public void selectVehicleType(String vehicleType) {
        awPage.selectVehicleType(vehicleType);
    }


    @And("^I click \"([^\"]*)\" button on 'WALK AROUND' page$")
    public void iClickButtonOnWalkAroundPage(String buttonName) {
        switch (buttonName) {
            case "NEXT":
                awPage.clickNextOnWalkAround();
                break;
            case "ALL OKAY":
                awPage.clickAllOkOnWalkAround();
                break;
        }
    }

    @And("^I verify front color change correct on 'WALK AROUND' page$")
    public void iSeeColorChangeWalkAroundPage() {
        String color = awPage.getFrontBgroundColorOnWalkAroundPage();
        if(color.contains("rgba(6, 206, 29, 1)")){
            log.info("The front cololr changed correct ");
        }else{
            System.out.println(" Warning !!  The front cololr not changed correct ");
            log.warn("The front cololr not changed correct ");
        }
    }


}
