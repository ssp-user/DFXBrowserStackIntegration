@AM12
Feature: AM12_Regression_ChangeDealerShip. This test suite is Appointment Management Regression suite
  Description: to verify Appointment Management function work properly

  @AM_Regression12_ChangeDealerShipByUser-TEST2-AUDI
  Scenario Outline: AM_Regression12, Verify the function of dealer ship able to  be change when change user name
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "<DealerName1>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I change 'User Menu' to "<UserMenu>" on 'DASHBOARD' page
    And I select the Dealer with "<DealerName2>" or "" in Dealer Page
    Then I see Dealer is "<DealerName2>" on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS       |DealerName1           |DealerName2  |UserMenu          |
      |TEST2ALAUDI  |Dummy     |Audi of anytown usa   |Audi Dallas  |Change dealership |