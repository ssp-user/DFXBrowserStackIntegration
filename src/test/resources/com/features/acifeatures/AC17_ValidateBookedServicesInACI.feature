@AC17 @Regression1
Feature: AC17_ValidateBookedServicesInACI
  
  Description: Validate Booked Services in ACI

  Scenario Outline: Validate that the services booked during appointment creation are displayed on ACI
    Given I capture a random VIN from a pool of VINs
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Appointment Manager" application from the 'Home' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I click "APPOINTMENT FOR A NEW CUSTOMER" button on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName          | Value                   |
      | First name         | Random                  |
      | Last name          | TestQA                  |
      | Authorized Phone   |                         |
      | Authorized Text    |                         |
      | Authorized E-mail  |                         |
      | Contact language   | English (United States) |
      | Pre-Correspondence |                         |
      | Default phone      |              9051234567 |
      | Cell phone         |              4164161234 |
      | Customer E-mail    | dfxcmbertet@yoo.com     |
    And I enter VIN and decode on 'CLIENT & VEHICLE' page
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click Email as authorized communication
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName       | Value        |
      | Street          | Peter Street |
      | City            | Toronto      |
      | Country         | Canada       |
      | ZIP/Postal Code |        34543 |
      | State           |              |
      | County          |              |
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I verify message "Entered customer information matched other customer(s). What do you want to do?" on 'HISTORY' page in AM
    And I click "CANCEL" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "Replace Spark Plugs" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I capture selected service on 'SERVICES' page in AM
    And I click "NEXT" button on 'SERVICES' page in AM
    And I set promise date as current date on 'TIME & ADVISOR' page
    And I set promise time "1th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    Then I verify selected items price on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    When I open this Appointment on 'DASHBOARD' page
    Then I capture Appointment Number on 'DASHBOARD' page
    #And I signout from AM
		And I close the current active tab and return to 'Home' page
    #Given I launch browser "chrome" in "GUI" and open "<URL>"
    #And I set "<DMS>" to the 'DMS'
    #When I login the Application as User "automation" and password "dealerfxqa"
    #And I move to switch dealer window on 'Home' page
    #And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "ADVISOR CHECKIN" application from the 'Home' page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    And I validate appointment number in 'CUSTOMER' page
    When I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I select the concern from the 'CUSTOMER' page
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button in Dealer 'HISTORY' page
    And I validate selected services on 'SERVICES' page
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
      | URL      | DMS   | DealerName           | ServiceAdvisor | Odometer | VIN    | TagNumber |
      | TEST2DFX | Dummy | Anytown USA Automall |              1 |    35001 | GLOBAL | TagACI    |