@CC_Req_003
Feature: CC03_CloseTask, Close Task As BDC Agent

  @CC_Testcase_003 @Regression @Smoketest
  Scenario Outline: Verify that BDC Agent is able to close task
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    #  And I click on "TASKS" tab on "Guest Connect" page  // Replaced by below one line action
    And I click "TASKS" tab on top of the page
    And I click "Last Action" dropdown on 'TASKS' page
    And I toggle "- None -" checkbox on 'TASKS' page
    And I click "Last Action" dropdown on 'TASKS' page
    And I select "first" customer on 'TASKS' page
    Then I see 'Task Detail' page
    #Following Actions Will be Performed on "TaskDetail" Page
		#When I select "Other - Closed Call" from "Select Action" dropdown menu
    When I select "Dealership Too Far" from "Select Action" dropdown menu    
    And I click "Save" button on 'Task Detail' page
    And I click "Cancel" button on 'Task Detail' page
    #Results can be verified on "Task" page
    And I click "Clear All" button on 'TASKS' page
    Then I see 'Total Task' count on 'Task' page decreased by "1"
    #Results can be verified on "Dashboard" page
    When I click "DASHBOARD" tab on top of the page
    Then I see "Closed Tasks" count on 'Dashboard' page increased by "1"
    And I see "Calls Made" count on 'Dashboard' page increased by "1"
    And I see "Closed Tasks Campaign" count on 'Dashboard' page increased by "1"
    And I see "Calls Made Campaign" count on 'Dashboard' page increased by "1"

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
