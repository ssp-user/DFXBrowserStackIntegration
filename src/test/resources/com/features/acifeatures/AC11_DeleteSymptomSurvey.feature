@AC11 @Regression1
Feature: AC11 - Delete Symptom Survey
  Description : The purpose of this feature is to delete a symptom feature

  @AC-DeleteSymptomSurvey
  Scenario Outline: Create Symptom Survey
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    Then I verify the AD Version number "<AD_VersionNumber>" on 'Advisor Check-In' page
    And I verify Mandatory Message Displayed in Fields on 'CUSTOMER' page
    And I verify "NEXT" button is not clickable if 'Odometer' field is empty
    When I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page        
    And I select the concern from the 'CUSTOMER' page    
    And I select "<SymptomSurvey>" as Symptom Survey from the 'CUSTOMER' page
		And I select Symptoms for "<SymptomSurvey>" from the 'SYMPTOM SURVEY' page
		And I click "SAVE" button on 'SYMPTOM SURVEY' page
		And I validate "<SymptomSurvey>" row on 'CONCERN' page
		And I delete "<SymptomSurvey>" row on 'CONCERN' page
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button in Dealer 'HISTORY' page
    And I click "NEXT" button on 'SERVICES' page
    And I click "NEXT" button on 'WALK AROUND' page
    And I enter data set in 'ASSIGN R.O.' page
      | FieldName       | Value       |
      | SERVICE ADVISOR |             |
      | TRANSPORTATION  |           1 |
      | INSPECTION TYPE |           1 |
      | TAG             | <TagNumber> |
      | TECHNICIAN      |             |
    And I enter "This is a test Morning check dealerfx QA , please ignore" in "COMMENTS" field on 'ASSIGN R.O.' page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    And I authorize and sign the agreement on 'AUTHORIZATION' page
    And I click "COMPLETE" button on 'AUTHORIZATION' page
    Then I assert RO push completed

    Examples: below data set for data driven
      | URL      | DMS   | DealerName            | DealerCode | Odometer | TagNumber | VIN               | Advisor | AD_VersionNumber | Application     | SymptomSurvey                         |
      | TEST2DFX | Dummy | Nissan of Anytown usa | 408A85US   |    50010 | TagDm     | 1N4AL21E59C104101 |       1 | 2.9.19035.01     | ADVISOR CHECKIN | AUDIO/NAVIGATION/BLUETOOTH/TELEMATICS |
