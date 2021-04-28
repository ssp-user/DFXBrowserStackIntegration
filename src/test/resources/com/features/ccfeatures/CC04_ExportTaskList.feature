@CC_Req_004
Feature: CC04_ExportTaskLIst, Export Task List as BDC Manager

  @CC_Testcase_004 @Regression @Smoketest
  Scenario Outline: Verify that BDC Agent is able to export task
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "TASKS" tab on top of the page
    And I select "Missed / No Show Appointments" from "Campaign Dropdown" dropdown menu
    And I click "EXPORT" button on 'TASKS' page
    Then I see Tasklist spread sheet downloaded
    #Results can be verified on downloaded file based on customer name
    And I see all Tasks for campaign included in exported file

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
