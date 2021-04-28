@AM32 @Regression2 @Sanity1 @FCA_Regression @FCA_Sanity
Feature: AM32_ValidateEmail, This test suite is Appointment Management Regression suite
  Description: to verify Appointment Management function work properly

  @AM32_ValidateEmail
  Scenario Outline: @AM_Regression32 Validate Email after creating an appointment
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I click "APPOINTMENT FOR A NEW CUSTOMER" button on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName          | Value                     |
      | First name         | RANDOM                    |
      | Last name          | RANDOM                    |
      | Authorized Phone   |                           |
      | Authorized Text    |                           |
      | Authorized E-mail  |                           |
      | Contact language   | English (United States)   |
      | Pre-Correspondence |                           |
      | Default phone      |                9051234567 |
      | Cell phone         |                4164161234 |
      | Customer E-mail    | dfxcucumbertest@yahoo.com |
    And I click Email as authorized communication
    And I set vehicle data on 'CLIENT & VEHICLE' page
      | FieldName    | Value  |
      | Make         | Random |
      | Year         | Random |
      | Model        | Random |
      | Transmission | Random |
      | Engine       | Random |
      | Drive train  | Random |
    And I set vehicle data on 'CLIENT & VEHICLE' page
      | FieldName     | Value      |
      | Odometer      | <Odometer> |
      | License plate |            |
      | Color         |            |
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I select "1" in 'Advisors' section on 'TIME & ADVISOR' page
    And I set promise date next "2" month "The 3th Tuesday" on 'TIME & ADVISOR' page
    And I set promise time "5th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    Then I verify selected items price on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open this Appointment on 'DASHBOARD' page
    And I signout from AM
    When I open page "Yahoo" in the new browser tab
    And I login page "Yahoo" as User "dfxcucumbertest" and password "dealerfxqa"
    Then I see new email from "<DealerName>" in Yahoo inbox
    Then I validate email contains customer name and booking time
    Then I validate email contains attachment
    Then I signout for the application

    Examples: below data set for data driven
      | URL         | DMS   | DealerName           | Odometer |Application|
      | TEST2ALNISS | Dummy | anytown usa automall |    35001 |Appointment Manager|
