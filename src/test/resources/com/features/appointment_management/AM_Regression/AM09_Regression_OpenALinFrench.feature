@AM09
Feature: AM09_Regression_OpenALinFrench. This test suite is Appointment Management Regression suite
  Description: to verify Appointment Management function work properly

  @AM_Regression09_FrenchLanguage-TEST2
  Scenario Outline: AM_Regression09, Verify the Appointment Management able to display properly French language menu
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "" or "<DealerCode>" in Dealer Page
    Then I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I change 'User Menu' to "<UserMenu>" on 'DASHBOARD' page
    And I select "French" language on 'DASHBOARD' page
    Then I see "French" language tab on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS       |UserMenu       |DealerCode       |ServiceAdvisor|Odometer |
      |TEST2ALAUDI  |Dummy     |Language       |408A85US         |1             |35001    |