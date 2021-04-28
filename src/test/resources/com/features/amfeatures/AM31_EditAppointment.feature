@AM31 @Regression2 @Sanity1 @FCA_Regression @FCA_Sanity
Feature: AM31_EditAppointment, This test suite is Appointment Management Regression suite
  Description: to verify Appointment Management function work properly

  @AM_Regression31_EditAppointment-TEST2
  Scenario Outline: AM_Regression31, Verify the function to edit an appointment
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I click "APPOINTMENT FOR A NEW CUSTOMER" button on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName          | Value                  |
      | First name         | DfxQA                  |
      | Last name          | Test                   |
      | Authorized Phone   |                        |
      | Authorized Text    |                        |
      | Authorized E-mail  |                        |
      | Contact language   | Random                 |
      | Pre-Correspondence |                        |
      | Default phone      |             9051234567 |
      | Cell phone         |             4164161234 |
      | Customer E-mail    | testdealerfx@gmail.com |
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
    When I input comment "Appointment booked" on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open this Appointment on 'DASHBOARD' page
    And I click "EDIT" button in this Appointment on 'DASHBOARD' page
    And I goto "TIME & ADVISOR" tab on 'SUMMARY' page
    And I set promise date next "1" month "The 3th Tuesday" on 'TIME & ADVISOR' page
    And I set promise time "4th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    Then I verify selected items price on 'SUMMARY' page
    When I input comment "Appointment updated" on 'SUMMARY' page
    And I click "SAVE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open this Appointment on 'DASHBOARD' page
    And I click "DELETE" button in this Appointment on 'DASHBOARD' page
    And I signout from AM

    Examples: below data set for data driven
      | URL         | DMS   | DealerName           | Odometer |
      | TEST2ALNISS | Dummy | anytown usa automall |    35001 |
