@CC_Testcase_008
Feature: CC08_LoadReports, Verify Deader admin can see reports load properly

  @CCReports01 @Regression @Smoketest
  Scenario Outline: Verify Dealer admin can see reports load properly
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "REPORTS" tab on top of the page
    Then I see following reports loading properly on 'REPORTS' page
      | Report Name               | Report Column       | Report Value                  |
      | BDC Agent Overview Report | First Name          | 1st row                       |
      | BDC Agent Success Report  | Campaign Name       | Missed / No Show Appointments |
      | Campaign Analysis Report  | # New Tasks         | Missed / No Show Appointments |
      | Campaign Details Report   | # In Progress Tasks | Missed / No Show Appointments |
      | Campaign Summary Report   | # Tasks             | Missed / No Show Appointments |
      

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
      

  @CCReports02 @Regression @Smoketest
  Scenario Outline: Verify Advisor user should not see CAMPAIGNS section
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    Then I don't see "CAMPAIGNS" tab on 'Customer Connect' page

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
