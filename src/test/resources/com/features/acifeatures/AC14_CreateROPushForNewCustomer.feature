@AC14 @Regression1
Feature: AC14 - RO for a new customer
  Description : The purpose of this feature is to push RO for a new customer

  Scenario Outline: Create RO push for a new customer
    Given I launch browser "chrome" in "GUI" and open "<URL1>"
    #    And I capture Random VIN
    And I capture Vehicle Identification Number
    Given I launch browser "chrome" in "GUI" and open "<URL2>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    When I enter "<FirstName>" in "FIRST NAME" field on 'CUSTOMER' page
    When I enter "<LastName>" in "LAST NAME" field on 'CUSTOMER' page
    When I enter "<Address>" in "ADDRESS" field on 'CUSTOMER' page
    When I enter "<City>" in "CITY" field on 'CUSTOMER' page
    When I enter "<Zip/PostalCode>" in "ZIP/POSTAL CODE" field on 'CUSTOMER' page
    When I enter "<PhoneNumber>" in "PHONE NUMBER" field on 'CUSTOMER' page
    When I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I select the concern from the 'CUSTOMER' page
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
    Given I launch browser "chrome" in "GUI" and open "<URL2>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    And I validate Customer Details in 'CUSTOMER' page

    Examples: below data set for data driven
      | URL1         | URL2     | DMS   | DealerName           | DealerCode | Odometer | TagNumber | VIN    | Advisor | AD_VersionNumber | Application     | FirstName | LastName | Address | City    | Zip/PostalCode | PhoneNumber |
      | VINGENERATOR | TEST2DFX | Dummy | Anytown USA Automall | 408A85US   |    50010 | TagDm     | GLOBAL |       1 | 2.9.19035.01     | ADVISOR CHECKIN | Random    | Random   | Random  | Toronto | Random         | Random      |
