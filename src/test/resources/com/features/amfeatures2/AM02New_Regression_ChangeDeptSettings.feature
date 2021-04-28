@AM02 @Regression1 @Sanity1 @FCA_Regression @FCA_Sanity @SanityVJ2
Feature: AM02_Regression_ChangeDeptSettings. This test suite is Appointment Management Regression suite
  Description: to verify Appointment Management function work properly

  Scenario Outline: AM_Regression02, Verify the Administration Employees,Departments, Transportation setting changes in AM
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
		#Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    Then I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I change 'User Menu' to "<UserMenu>" on 'DASHBOARD' page
    And I set working hours data on 'Administration-employees' page
      | Day       | Holiday | Opentime | OpentimeAPM | Closetime | ClosetimeAPM |
      | Sunday    | yes     |          | AM          |           | PM           |
      | Monday    |         | 8:00     | AM          | 4:30      | PM           |
      | Tuesday   |         | 7:00     | AM          | 3:30      | PM           |
      | Wednesday |         | 8:00     | AM          | 4:30      | PM           |
      | Thursday  |         | 8:00     | AM          | 5:30      | PM           |
      | Friday    |         | 8:00     | AM          | 4:30      | PM           |
      | Saturday  |         | 8:00     | AM          | 4:30      | PM           |
    Then I verify the 'Update count' on 'Administration-employees' page
    When I open the 'Notification Center' on 'Administration' page
    Then I see "The employee schedule has been changed" notification on 'Administration' page
    And I clear and close 'Notification Center' on 'Administration' page
    When I goto "DEPARTMENTS" tab on 'Administration' page in AM
    And I update "+2" Maximum appointments on 'Administration' page in AM
    Then I see maximum appointments updated on 'Administration' page
    When I open the 'Notification Center' on 'Administration' page
    Then I see "The service department has been updated" notification on 'Administration' page
    And I clear and close 'Notification Center' on 'Administration' page
    When I goto "TRANSPORTATION OPTIONS" tab on 'Administration' page in AM
    And I set working hours data on 'Administration-transportation' page
      | Day       | Holiday | Opentime | OpentimeAPM | Closetime | ClosetimeAPM | Day limit |
      | Sunday    | yes     |          | AM          |           | PM           |           |
      | Monday    |         | 8:00     | AM          | 4:30      | PM           |         0 |
      | Tuesday   |         | 7:00     | AM          | 5:30      | PM           | +1        |
      | Wednesday |         | 8:30     | AM          | 6:30      | PM           | +2        |
      | Thursday  |         | 8:00     | AM          | 5:00      | PM           |        16 |
      | Friday    |         | 8:30     | AM          | 6:30      | PM           | +2        |
      | Saturday  |         | 8:00     | AM          | 4:30      | PM           | +1        |
    Then I see 'Day limite' updated on 'Administration-transportation' page
      | Day       | Day limit |
      | Sunday    | yes       |
      | Monday    | yes       |
      | Tuesday   | yes       |
      | Wednesday | yes       |
      | Thursday  | yes       |
      | Friday    | yes       |
      | Saturday  | yes       |
    And I signout from AM

    Examples: below data set for data driven
      | URL         | DMS   | UserMenu       | DealerName           | ServiceAdvisor | Odometer | Application         |
      | TEST2ALNISS | Dummy | Administration | anytown usa automall |              1 |    35001 | Appointment Manager |
