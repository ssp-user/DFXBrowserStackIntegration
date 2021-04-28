@AM03 @Regression1 @FCA_Regression 
Feature: AM03_Regression_DayWeekMonthView. This test suite is Appointment Management Regression suite
  Description: to verify Appointment Management function work properly

  Scenario Outline: AM_Regression03, Verify the DashBoard able to display properly View layout By DAY, WEEK, MONTH and LIST
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "" or "<DealerCode>" in Dealer Page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "DAY" tab on 'DASHBOARD' page
    Then I verify "DAY" view layout on 'DASHBOARD' page
    When I click "WEEK" tab on 'DASHBOARD' page
    Then I verify "WEEK" view layout on 'DASHBOARD' page
    When I click "MONTH" tab on 'DASHBOARD' page
    Then I verify "MONTH" view layout on 'DASHBOARD' page
    When I click "LIST" tab on 'DASHBOARD' page
    Then I verify "LIST" view layout on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS       |DealerName            |DealerCode             |ServiceAdvisor|Odometer |
      |TEST2ALNISS  |Dummy     |anytown usa automall  |ANYUSAAUTOMALL         |1             |35001    |