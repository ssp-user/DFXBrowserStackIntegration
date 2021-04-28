@AM17
Feature: AM17_Regression_NewAppointmentDiffDepartment, This test suite is Appointment Management Regression suite
  Description: to verify Appointment Management function work properly

  @AM_Regression17_CreateNewApptDifferentDepartment-TEST2
  Scenario Outline: AM_Regression17, Verify the function  of create a new appointment against different department choice
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I change department to "<Department2>" or "Chrysler QA - Service" on 'DASHBOARD' page
    And I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I click "APPOINTMENT FOR A NEW CUSTOMER" button on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | First name           | DfxQA                  |
      | Last name            | Test                   |
      | Authorized Phone     |                        |
      | Authorized Text      |                        |
      | Authorized E-mail    |                        |
      | Contact language     | Random                 |
      | Pre-Correspondence   |                        |
      | Default phone        | 9051234567             |
      | Cell phone           | 4164161234             |
      | Customer E-mail      | testdealerfx@gmail.com |
    And I set vehicle data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | Make                 | Random                 |
      | Year                 | Random                 |
      | Model                | Random                 |
      | Transmission         | Random                 |
      | Engine               | Random                 |
      | Drive train          | Random                 |
    And I set vehicle data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | Odometer             | <Odometer>             |
      | License plate        |                        |
      | Color                |                        |
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
    When I change department to "<Department1>" or "Main Shop - Chrysler QA" on 'DASHBOARD' page
    Then I see this Appointment not on 'DASHBOARD' page
    Examples: below data set for data driven
      |URL          |DMS       |DealerName           |Department1   |Department2   |Odometer |
      |TEST2ALAUDI  |Dummy     |Audi of anytown usa  |Main          |Express       |35001    |