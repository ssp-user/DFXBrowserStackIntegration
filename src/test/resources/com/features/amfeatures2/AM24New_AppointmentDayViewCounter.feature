@AM24 @Regression2 @FCA_Regression
Feature: AM24_Regression_AL. This test suite is Appointment Management Regression suite on TEST2
  Description: To verify Open appointment counter

  @AM_Regression24
  Scenario Outline: AM_Regression24.1 <DMS>, Verify the function to check Open appointment counter
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
    And I search "Name" with "<CustomerName>" on 'CUSTOMER SEARCH' page
    And I select "1th" customer on 'CUSTOMER SEARCH' page
    And I click "+" button or "1th" customer vehicle if multiple on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName       | Value                  |
      | Customer E-mail | testdealerfx@gmail.com |
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName   | Value   |
      | Postal Code | M2J 1M5 |
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
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
    And I see "Counter" number for the specific available time slot on 'DASHBOARD' page
    And I click "DELETE" button in this Appointment on 'DASHBOARD' page
    Then I do not see the new Appointment on 'DASHBOARD' page
    Then I see "Counter" number increased by 1 for the time slot at which appointment scheduled on 'DASHBOARD' page
    And I signout from AM

    Examples: below data set for data driven
      | URL         | DMS   | DealerName           | DealerCode | CustomerName | ServiceAdvisor | Odometer |Application|
      | TEST2ALNISS | Dummy | anytown usa automall |            | dfx          |              1 |    35001 |Appointment Manager|
