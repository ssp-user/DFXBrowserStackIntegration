package com.autotest.teststeps.advisor_checkin;


import com.automation.pages.advisor_checkin.*;
import com.automation.utils.otherUtils.PDFUtils;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class ACI_AssignROSteps extends BaseTestSteps {

    ACI_AssignROPage aROPage = new ACI_AssignROPage();
    PDFUtils pdf = new PDFUtils();

    private static Logger log = Logger.getLogger(ACI_AssignROSteps.class);


    @And("^I update (.+) on \"([^\"]*)\" page$")
    public void updatePageInfo(String updateFieldName, String pageName) {
        aROPage.updatePageInformation(updateFieldName, pageName);
    }

    @And("^I enter data set in 'ASSIGN R.O.' page$")
    public void updatePageInfo(DataTable dataTable) throws Throwable{
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            fieldName = fieldName.toUpperCase();
            String value = map.get("Value");
            if (notBlank(value)) {
                if (fieldName.equals("TAG")) {
                    aROPage.setTagValueOnAssignROPage(value);
                } else if (fieldName.equals("TECHNICIAN")) {
                    aROPage.selectTechnicianInASSIGNRO(value);
                } else if (fieldName.equals("SERVICE ADVISOR")) {
                    aROPage.selectServiceAdvisorInASSIGNRO(value);
                } else if (fieldName.equals("TRANSPORTATION")) {
                    aROPage.setTransportationInASSIGNRO(value);
                } else if (fieldName.equals("INSPECTION TYPE")) {
                    aROPage.setInspectionTypeInASSIGNRO(value);
                } else if (fieldName.equals("COMMENTS")) {
                    aROPage.updateComment(value);
                }
            }

          }
    }

    @And("^I enter \"([^\"]*)\" in \"([^\"]*)\" field on 'ASSIGN R.O.' page$")
    public void iEnterFieldValueOnASSIGNROPage(String value, String fieldName){
        switch (fieldName){
            case "TAG":
                aROPage.setTagValueOnAssignROPage(value);
                break;
            case "COMMENTS":
                aROPage.updateComment(value);
                break;
        }
         }


    @And("^I change \"([^\"]*)\" \"([^\"]*)\" year on \"([^\"]*)\" page$")
    public void updateTimeONPage(String timeField, String time, String pageName) {
        switch (pageName) {
            case "ASSIGN R.O.":
                aROPage.updateYearOnAssignRO(time);
                break;
        }
    }

    @And("^I set 'PROMISE TIME' \"(.*)\" working day \"(.*)\" hour at \"(.*) \" on 'ASSIGN R.O.' page$")
    public void iSetPromiseDateTimeOnAssignRO(String aday, int ahour, String apm) {
          aROPage.setPromiseDateTimeOnAssignRO(aday, ahour , apm  );

    }

    @And("^I write comment \"([^\"]*)\" on \"([^\"]*)\" page$")
    public void updateComment(String comment, String pageName) {
        switch (pageName) {
            case "ASSIGN R.O.":
                aROPage.updateComment(comment);
                break;
        }
    }

    @And("^I click \"([^\"]*)\" button on 'ASSIGN R.O.' page$")
    public void iClickButtonOnAssignRoPage(String buttonName) {
        switch (buttonName) {
            case "NEXT":
                aROPage.clickNextOnAssignRO();
                break;
        }
    }

    @And("^I validate Transportation option as \"([^\"]*)\" in 'ASSIGN R.O.' page$")
    public void iValidateTransportationOptionOnROPage(String transportationOption) {
    	aROPage.validateTransportationOption(transportationOption);
    }
    
}
