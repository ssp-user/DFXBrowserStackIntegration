package com.autotest.teststeps.appointment_manager;

import com.automation.pages.appointment_manager.AM_ClientAndVehiclePage;
import com.automation.pages.third_party.VINGenerator;
import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AM_ClientAndVehicleSteps extends BaseTestSteps {

    AM_ClientAndVehiclePage cnvPage = new AM_ClientAndVehiclePage();
    public static int recalls;
    private static Logger log = Logger.getLogger(AM_ClientAndVehicleSteps.class);

    @And("^I enter \"([^\"]*)\" on 'CLIENT & VEHICLE' page$")
    public void iEnterSomethingOnClientAndVehiclePage(String enterText) {
        cnvPage.enterOdometer(enterText);
    }

    @And("^I set customer data on 'CLIENT & VEHICLE' page$")
    public void iSetCustomerDataOnClientPage(DataTable dataTable) {
        cnvPage.waitForCnVPageToLoad();
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                switch (fieldName) {
                    case "First name":
                        cnvPage.inputFirstName(value);
                        break;
                    case "Last name":
                        cnvPage.inputLastName(value);
                        break;
                    case "Authorized Phone":
                        break;
                    case "Authorized Text":
                        break;
                    case "Authorized E-mail":
                        break;
                    case "Contact language":
                        cnvPage.selectLanguage(value);
                        break;
                    case "Pre-Correspondence":
                        break;
                    case "Default phone":
                        cnvPage.inputDefaultPhone(value);
                        break;
                    case "Cell phone":
                        break;
                    case "Customer E-mail":
                        cnvPage.inputEmail(value);
                        break;
                    case "Street":
                        cnvPage.inputStreet(value);
                        break;
                    case "City":
                        cnvPage.inputCity(value);
                        break;
                    case "Country":
                        cnvPage.inputCountry(value);
                        break;
                    case "ZIP/Postal Code":
                        cnvPage.inputZip(value);
                        break;
                    case "State / Province":
                        break;
                    case "County":
                        break;
                    case "CustomerNote":
                    	cnvPage.enterCustomerNote(value);
                    	break;
                        
                }
            }

        }

    }


    @And("^I set vehicle data on 'CLIENT & VEHICLE' page$")
    public void iSetVehicleDataOnClientPage(DataTable dataTable) {
        cnvPage.waitForCnVPageToLoad();
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String field = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                value = toBeAlpha(value);
                cnvPage.setVehicleData(field,value);
            }
        }

    }

    @And("^I click \"([^\"]*)\" button on 'CLIENT & VEHICLE' page$")
    public void iClickButtonOnClientVehiclePage(String buttonName) {
        switch (buttonName) {
            case "NEXT":
//                cnvPage.clickOnNext();
                cnvPage.clickNextBtn();
                break;
            case "CANCEL":
              cnvPage.clickCancelBtn();
              break;                
            
            case "CustomerNote":
            	cnvPage.clickCustomerNote();
            	break;
         }
    }

    @And("^I decode \"([^\"]*)\" on 'CLIENT & VEHICLE' page$")
    public void iDecodeVINOnClientVehiclePage(String vin) {
                cnvPage.decodeVinAM(vin);
    }

    @Then("^I verify decode VIN with \"([^\"]*)\" on 'CLIENT & VEHICLE' page$")
    public void iVerifyDecodeVINOnClientVehiclePage(String vin) {
        CommonMethods.sleep(1000);
        String aVin = cnvPage.getDecodeVin();
        verifyEquals(" verify the Decoded VIN on the Dashboard " , vin , aVin);

    }

    @And("^I choose \"([^\"]*)\" on 'CLIENT & VEHICLE' page if customer existing$")
    public void iclickCustomerBtnOnClientVehiclePage(String buttonName) {
        String info = cnvPage.checkVehicleInfor();
        if (!info.equals("")){
            buttonName = buttonName.toUpperCase();
            cnvPage.clickBtncheckVehicleInfor(buttonName);
        }
    }

    @Then("^I verify the 'Recall count' and 'Recall list' on 'CLIENT & VEHICLE' page$")
    public void iVerifyRecallCountlistClientVehiclePage() {
        CommonMethods.sleep(1000);
        cnvPage.waitForCnVPageToLoad();
        String sRecall = cnvPage.getRecallCount();
        if (sRecall.isEmpty()){
            recalls= 0;
        }else{
            recalls=Integer.valueOf(sRecall);
        }
        CommonMethods.sleep(1000);
        verifyEquals(" verify the Recalls on the screen  " , recalls ,cnvPage.getRecallListSize());
        Assert.assertEquals(" verify the Recalls on the screen " , recalls ,cnvPage.getRecallListSize());

    }
 
    @Then("^I check \"([^\"]*)\" checkbox checked on 'CLIENT & VEHICLE' page$")
    public void iCheckOnClientAndVehiclePage(String field) {
    	cnvPage.verifyElementExists(field);
    }     

    @And("^I follow company name updation process if company name is available otherwise Individual Customer updation process$")
    public void iFollowCompanyOrIndividualBusinessFlow(DataTable dataTable) {
    	if(!cnvPage.isBusinessCustomer()) {
        	iSetCustomerDataOnClientPage(dataTable);    		
    	}
    }

    @And("^I click business checkbox to change customer type$")
    public void iClickBusinessCheckBox() {
        CommonMethods.sleep(200);
        cnvPage.clickOnBusinessCheckBox();
    }      

    @And("^I click Email as authorized communication$")
    public void iClickEmailCheckBox() {
        CommonMethods.sleep(200);
        cnvPage.clickOnEmailCheckBox();
    }    

    @And("^I enter VIN and decode on 'CLIENT & VEHICLE' page$")
    public void iEnterVINAndDecodeOnClientVehiclePage() {
    	iDecodeVINOnClientVehiclePage(VINGenerator.vinNumber);
    }    

    @Then("^I verify the 'CustomerNote' on 'CLIENT & VEHICLE' page$")
    public void iverifycustomernote()
    
    {
    	CommonMethods.sleep(200);
        cnvPage.verifyCutomerNote();
    }

}
