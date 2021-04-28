@AC15 @Regression1
Feature: AC15 - Create customer Notes
  Description : The purpose of this feature is to push RO for a new customer

  Scenario Outline: Create RO push for a new customer
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I open "<Application>" application from the 'Home' page
    #And I choose "<Application>" application from the 'Home' page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    When I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I click "CUSTOMER NOTES" button on 'CUSTOMER' page
    When I enter "<CustomerNotes>" in "CUSTOMER NOTES" field on 'CUSTOMER' page
    And I click "SAVE" button on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button on 'HISTORY' page
    And I keep Pre-Selected or "check" "multi-point inspection" or first in 'FACTORY SCHEDULED MAINTENANCE' Tab if No Pre-selected
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
    #Given I launch browser "chrome" in "GUI" and open "<URL>"
    #When I login the Application as User "automation" and password "dealerfxqa"
    #And I move to switch dealer window on 'Home' page
    #And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
		And I close the current active tab and return to 'Home' page    
    And I choose "<Application>" application from the 'Home' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    And I click "VIEW CUSTOMER NOTES" button on 'CUSTOMER' page
    And I validate Customer Notes in 'CUSTOMER' page

    #And I validate Customer Details in 'CUSTOMER' page
    Examples: below data set for data driven
      | URL      | DMS   | DealerName           | DealerCode | Odometer | TagNumber | VIN               | Advisor | AD_VersionNumber | Application     | FirstName | LastName | Address | City    | Zip/PostalCode | PhoneNumber | CustomerNotes |
      | TEST2DFX | Dummy | Anytown USA Automall | 408A85US   |    50010 | TagDm     | WAUTPBFF3GA050764 |       1 | 2.9.19035.01     | ADVISOR CHECKIN | Random    | Random   | Random  | Toronto | Random         | Random      | Random        |
