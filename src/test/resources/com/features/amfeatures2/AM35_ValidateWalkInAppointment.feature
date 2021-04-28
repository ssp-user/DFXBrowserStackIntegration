@AM35 @Regression2 @FCA_Regression
Feature: AM35_Regression_ValidateWalkInAppointment. This test suite is Appointment Management Regression suite
  Description: Validate walk-in appointment.

  @dummyDMS
  Scenario Outline: AM_Regression35, Validate walk-in appointment
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "<Application1>" application from the 'Home' page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    #When I click "Next week" button on 'DASHBOARD' page
    #And I select "1th" available weekday on 'DASHBOARD' page
    And I choose VIN for walk-in appointment from the pool
	  And I close the current active tab and return to 'Home' page    
    #Given I launch browser "chrome" in "GUI" and open "<URL>"
    #And I set "<DMS>" to the 'DMS'
    #When I login the Application as User "automation" and password "dealerfxqa"
    And I choose "<Application2>" application from the 'Home' page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with close button customer information PopUp in 'CUSTOMER' page
    #    When I enter "<FirstName>" in "FIRST NAME" field on 'CUSTOMER' page
    #    When I enter "<LastName>" in "LAST NAME" field on 'CUSTOMER' page
    #    When I enter "<Address>" in "ADDRESS" field on 'CUSTOMER' page
    #    When I enter "<City>" in "CITY" field on 'CUSTOMER' page
    #    When I enter "<Zip/PostalCode>" in "ZIP/POSTAL CODE" field on 'CUSTOMER' page
    #    When I enter "<PhoneNumber>" in "PHONE NUMBER" field on 'CUSTOMER' page
    When I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I capture Customer Name on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I select the concern from the 'CUSTOMER' page
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button in Dealer 'HISTORY' page
    And I click "NEXT" button on 'SERVICES' page
    And I click "NEXT" button on 'WALK AROUND' page
    And I enter data set in 'ASSIGN R.O.' page
      | FieldName       | Value               |
      | SERVICE ADVISOR | aaTest801 aaTest801 |
      | TRANSPORTATION  |                   1 |
      | INSPECTION TYPE |                   1 |
      | TAG             | <TagNumber>         |
      | TECHNICIAN      |                     |
    And I enter "This is a test Morning check dealerfx QA , please ignore" in "COMMENTS" field on 'ASSIGN R.O.' page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    And I authorize and sign the agreement on 'AUTHORIZATION' page
    And I click "COMPLETE" button on 'AUTHORIZATION' page
    Then I assert RO push completed
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I choose "<Application1>" application from the 'Home' page
    And I open walk-in Appointment on 'DASHBOARD' page

    Examples: below data set for data driven
      | URL      | DMS   | DealerName           | VIN    | ServiceAdvisor | Odometer | Application1        | Application2    | FirstName | LastName | Address | City    | Zip/PostalCode | PhoneNumber | TagNumber |
      | TEST2DFX | Dummy | anytown usa automall | GLOBAL |              1 |    35001 | Appointment Manager | ADVISOR CHECKIN | Random    | Random   | Random  | Toronto | Random         | Random      | TagDm     |
