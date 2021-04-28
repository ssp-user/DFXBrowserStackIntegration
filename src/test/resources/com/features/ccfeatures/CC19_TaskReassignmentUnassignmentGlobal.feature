@CC_Testcase_019
Feature: CC19_Task Reassignment at global/mass campaign Level

  @CC_Testcase_019_DealershipUser @Regression @Smoketest
  Scenario Outline: Verify that BDC Manager is able to reassign Tasks from one BDC Agent to other BDC Agent for multiple campaign
    Given I launch browser "chrome" in "GUI" and open "TEST2DFX"
    When I login the Application as User "DarrenNissanD" and password "welcome"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "TASKS" tab on top of the page
    # Check Point 1
    Then I see "Tasks List" on 'TASKS' Page
    When I select "Overdue for Service" from "Campaign Dropdown" dropdown menu
    Then I see 'number' of tasks
    # Following Actions Will be Performed on "Tasks" Page - For Task Assignment
    When I click "Reassign Agents" button on 'TASKS' page
    And I select "Overdue for Service" from "Campaign Type" dropdown menu
    And I click "Include All Campaigns" button on 'TASKS' page
    And I click "Next" button on 'TASKS' page
    And I search agent name "Darren Deng" in "Global Agent Search" field
    And I click "Right Green Arrow" button beside "Darren Deng"
    And I click "Save" button on 'TASKS' page
    And I click "YES" button on pop up message window
    # Check Point 2
    Then I see 'number' of tasks remain the same
    And I see all tasks have "Assigned Agent" name "Darren Deng"
    When I select "N/A" from "Assigned Agent" dropdown menu
    # Check Point 3
    Then I see "Empty Tasks List" on 'TASKS' Page
    And I click "Clear All" button on 'TASKS' page
    # Following Actions Will be Performed For Task Unassignment global level
    When I click "Reassign Agents" button on 'TASKS' page
    And I select "Overdue for Service" from "Campaign Type" dropdown menu
    And I click "Include All Campaigns" button on 'TASKS' page
    And I click "Next" button on 'TASKS' page
    And I search agent name "Darren Deng" in "Global Agent Search" field
    And I click "Left Red Arrow" button beside "Darren Deng"
    And I click "Save" button on 'TASKS' page
    And I click "YES" button on pop up message window
    # Check Point 4, after unassignment tasks should have assigned agent name "N/A"
    Then I see 'number' of tasks remain the same
    And I see all tasks have "Assigned Agent" name "N/A"
    And I select "Darren Deng" from "Assigned Agent" dropdown menu
    # Check Point 5
    Then I see "Empty Tasks List" on 'TASKS' Page

    Examples: 
      | URL      | DealerName            | Username      | Password |
      | TEST2DFX | Nissan of Anytown USA | DarrenNissanD | welcome  |
