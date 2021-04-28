@AM09 @Regression1 @FCA_Regression 
Feature: AM09_Regression_OpenALinFrench. This test suite is Appointment Management Regression suite
  Description: to verify Appointment Management function work properly
  Scenario Outline: AM_Regression09, Verify the Appointment Management able to display properly French language menu
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerCode>" or "" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I change 'User Menu' to "<UserMenu>" on 'DASHBOARD' page
    And I select "French" language on 'DASHBOARD' page
    Then I see "French" language tab on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS       |UserMenu       |DealerCode                   |ServiceAdvisor|Odometer |Application        |
      |TEST2ALNISS  |Dummy     |Language       |ANYUSAAUTOMALL               |1             |35001    |Appointment Manager|