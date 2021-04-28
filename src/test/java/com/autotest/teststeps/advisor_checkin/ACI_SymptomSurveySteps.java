package com.autotest.teststeps.advisor_checkin;

import com.autotest.teststeps.BaseTestSteps;

import cucumber.api.java.en.And;

import com.automation.pages.advisor_checkin.*;



public class ACI_SymptomSurveySteps extends BaseTestSteps {

	ACI_SymptomSurveyPage acSSPage = new ACI_SymptomSurveyPage();

	
    @And("^I select Symptoms for \"(.*)\" from the 'SYMPTOM SURVEY' page$")
    public void selectSymptomSurvey(String symptomsurvey) {
    	acSSPage.selectSymptomSurveyDetails(symptomsurvey);
    }    	

	@And("^I click \"([^\"]*)\" button on 'SYMPTOM SURVEY' page$")
	public void iclickElementOnSymptomSurveyScreen(String element) {
		acSSPage.click(element);
	}   
	
}
