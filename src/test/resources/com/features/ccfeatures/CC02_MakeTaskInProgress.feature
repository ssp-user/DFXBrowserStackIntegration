@CC_Req_002
Feature: CC02_MakeTaskInProgress, Make Task In Progress As BDC Agent

  @CC_Testcase_002 @Regression @Sanity
  Scenario Outline: Verify that BDC Agent is able to make task in progress
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "TASKS" tab on top of the page
    And I click "Last Action" dropdown on 'TASKS' page
    And I toggle "- None -" checkbox on 'TASKS' page
    And I click "Last Action" dropdown on 'TASKS' page
    And I select "second" customer on 'TASKS' page
    Then I see 'Task Detail' page
    #Following Actions Will be Performed on "TaskDetail" Page
    When I select "No Answersds" from "Select Action" dropdown menu
    And I click "Save" button on 'Task Detail' page
    And I click "Cancel" button on 'Task Detail' page
    #Results can be verified on "Task" page
    And I click "Clear All" button on 'TASKS' page
    #Update below to "No Answer" once data is correct in environment    
    Then I see "Last Action" value changed to "No Answersds" on 'TASKS' page
    #And I see "Call Attempts" value changed to "1" on 'TASKS' page
    #Results can be verified on "Dashboard" page
    When I click "DASHBOARD" tab on top of the page
    Then I see "In Progress Tasks" count on 'Dashboard' page increased by "1"

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
