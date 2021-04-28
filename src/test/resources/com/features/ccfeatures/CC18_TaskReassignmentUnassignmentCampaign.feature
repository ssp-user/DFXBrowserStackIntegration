@CC_Testcase_018
Feature: CC18_TaskReassignmentUnassignmentCampaign, Task Reassignment at Campaign Level

  @CCCampaign18 @Regression @Smoketest
  Scenario Outline: Verify that BDC Manager is able to reassign Tasks from one BDC Agent to other BDC Agent
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<Username>" and password "<Password>"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "TASKS" tab on top of the page
    #Check Point 1
    Then I see "Tasks List" on 'TASKS' Page
    #Following Ac tions Will be Performed on "Tasks" Page - For Task Assignment
    When I select "5" random task checkboxes from Tasks List
    And I click "Reassign Tasks" button on 'TASKS' page
    And I search agent name "<Agent>" in "Agent Search" field
    And I click "Left Arrow" button to select BDC Agent
    And I click "Apply" button on 'TASKS' page
    And I click "YES" button on pop up message window
    #Check Point 2
    Then I see all "5" randomly selected tasks have "Assigned Agent" name as "<Agent>"
    #Following Actions Will be Performed on "Tasks" Page - For Task Unassignment
    When I select all these "5" randomly selected tasks
    And I click "Reassign Tasks" button on 'TASKS' page
    #    And I keep "Reassign Agent" list empty by not selecting any agent from right side list on "Task Reassignment" page
    And I click "Apply" button on 'TASKS' page
    And I click "YES" button on pop up message window
    #Check Point 3, # After unassignment tasks should have assigned agent name "N/A"
    Then I see all "5" randomly selected tasks have "Assigned Agent" name as "N/A"

    Examples: 
      | URL      | DealerName            | Username      | Password | Agent       |
      | TEST2DFX | Nissan of Anytown USA | DarrenNissanD | welcome  | Darren Deng |
