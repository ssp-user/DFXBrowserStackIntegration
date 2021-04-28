@AM16 @Regression2 @FCA_Regression
Feature: AM16_Regression_CreateCustomerAppVin, This test suite is Appointment Management Regression suite
  Description: to verify Appointment Management function work properly

  @AM_Regression16_CreateNewAppointmentByVIN-TEST2-AUDI
  Scenario Outline: @AM_Regression16 Verify the function of create a new appointment with the customer name selection by a VIN
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And  I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I click "APPOINTMENT FOR A NEW CUSTOMER" button on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | First name           | Random                 |
      | Last name            | TestQA                 |
      | Authorized Phone     |                        |
      | Authorized Text      |                        |
      | Authorized E-mail    |                        |
      | Contact language     | Random                 |
      | Pre-Correspondence   |                        |
      | Default phone        | 9051234567             |
      | Cell phone           | 4164161234             |
      | Customer E-mail      | testdealerfx@gmail.com |
    And I decode "<VIN>" on 'CLIENT & VEHICLE' page
    And I choose "USE MATCHED CUSTOMER" on 'CLIENT & VEHICLE' page if customer existing
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName      | Value         |
      | Street         | 2131          |
      | City           | North York    |
      | Country        | Canada        |
      | Postal Code    | M2J 1M5       |
      | State          |               |
      | County         |               |
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I select "<ServiceAdvisor>" in 'Advisors' section on 'TIME & ADVISOR' page
    And I set promise date next "2" month "The 3th Tuesday" on 'TIME & ADVISOR' page
    And I set promise time "5th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    Then I verify selected items price on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open this Appointment on 'DASHBOARD' page
    And I click "DELETE" button in this Appointment on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS       |DealerName            |VIN                |ServiceAdvisor|Odometer |
      |TEST2ALNISS  |Dummy     |anytown usa automall  |1N4BL21EX8N459000  |1             |35001    |