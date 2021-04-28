@AM14
Feature: AM14_Regression_CreateAppointmentDealerTire. This test suite is Appointment Management Regression suite on TEST2
  Description: to verify Appointment Management function work properly

  @AM_Regression14_ChangeDealerTire-TEST2-AUDI
  Scenario Outline: AM_Regression14 Verify the function of create a new appointment with change Dealer Tire the customer name on TEST2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And  I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I search "Name" with "<CustomerName>" on 'CUSTOMER SEARCH' page
    And I select "1th" customer on 'CUSTOMER SEARCH' page
    And I click "+" button or "1th" customer vehicle if multiple on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | Customer E-mail      | testdealerfx@gmail.com |
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName      | Value         |
      | Postal Code    | M2J 1M5       |
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    And I goto "TIRES" tab on 'SERVICES' page in AM
    And I click "ADD TIRES" button on 'SERVICES-TIRES' page in AM
    And I click "1th" 'Tire Size' on 'SERVICES-TIRES' page in AM
    And I choose "1th" product catalog on 'SERVICES-TIRES' page in AM
    And I click "ADD TO QUOTE" button on 'SERVICES-TIRES' page in AM
    Then I verify the TIRES quote on 'SERVICES-TIRES' page in AM
    When I goto "TIME & ADVISOR" tab in AM
    And I select "<ServiceAdvisor>" in 'Advisors' section on 'TIME & ADVISOR' page
    And I set promise date next "2" month "The 3th Tuesday" on 'TIME & ADVISOR' page
    And I set promise time "5th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    And I clear and cancel 'Tire comment' on 'SUMMARY' page
    Then I verify 'Tires Price' on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open this Appointment on 'DASHBOARD' page
    And I click "DELETE" button in this Appointment on 'DASHBOARD' page
    And I signout from AM

    Examples: below data set for data driven
      |URL           |DMS       |DealerName    |CustomerName    |ServiceAdvisor|Odometer |
      |TEST2ALAUDI   |Dummy     |Audi naples   |dfx             |1             |35001    |
