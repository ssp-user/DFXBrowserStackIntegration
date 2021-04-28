@CC_Testcase_013
Feature: CC13_TaskAssigned, Verify that BDC agent is not able to see task assigned to another BDC Agent

  @CCCampaign13 @Regression @Smoketest
  Scenario Outline: Verify that BDC agent is not able to see task assigned to another BDC Agent
    #log in as dealer admin to import campaign task and assign task
    Given I launch browser "chrome" in "GUI" and open "TEST2DFX"
    When I login the Application as User "DarrenNissanD" and password "welcome"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "CAMPAIGNS" tab on top of the page
    And I select "Import Call List" from "Campaign Type" dropdown menu on 'CAMPAIGNS' page
    And I click "Teest Immport" from 'Campaign List' on 'CAMPAIGNS' page
    And I add "This is a test cucumber comment" in 'Campaign Description' field
    And I toggle "Visibility on Dashboard" to "Show" under "INFORMATION" section on 'CAMPAIGNS' page
    And I click "SCRIPTS" tab on 'CAMPAIGNS' page
    And I click "TASK ASSIGNMENT" tab on 'CAMPAIGNS' page
    And I search following BDC Agent/Manager under "Service Coordinators available for assignment" search field on 'CAMPAIGNS' page
      | Role      | Name        |
      | BDC Agent | Darren Deng |
      | BDC Agent | Kathy Guan  |
    And I click "Next" button on 'CAMPAIGNS' page
    And I click "Save" button on 'CAMPAIGNS' page
    And I click "Teest Immport" from 'Campaign List' on 'CAMPAIGNS' page
    And I click "CLEAR TASK" tab on 'CAMPAIGNS' page
    And I click "Apply" button on 'CAMPAIGNS' page
    And I click "Teest Immport" from 'Campaign List' on 'CAMPAIGNS' page
    And I click "IMPORT" tab on 'CAMPAIGNS' page
    And I click "Choose file" button on 'CAMPAIGNS' page
    And I upload "campaignImport.csv" on 'CAMPAIGNS' page
    And I click "Import" button on 'CAMPAIGNS' page
    And I click "DASHBOARD" tab on top of the page
    And I click "Teest Immport" campaign on 'DASHBOARD' page
    Then I see tasks randomly assigned to following agents on 'Task Detail' page
      | AgentName   |
      | Kathy Guan  |
      | Darren Deng |
    #login as BDC Agent No.1 to verify his/her task
    Given I launch browser "chrome" in "GUI" and open "TEST2DFX"
    When I login the Application as User "DarrenNissanK" and password "welcome"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "Teest Immport" campaign on 'DASHBOARD' page
    Then I see tasks assigned to agent "Kathy Guan" on 'Task Detail' page
    And I don't see tasks assigned to "Darren Deng" on 'Task Detail' page
    #login as BDC Agent No.2 to verify his/her task
    Given I launch browser "chrome" in "GUI" and open "TEST2DFX"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "Teest Immport" campaign on 'DASHBOARD' page
    Then I see tasks assigned to agent "Darren Deng" on 'Task Detail' page
    And I don't see tasks assigned to "Kathy Guan" on 'Task Detail' page

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
