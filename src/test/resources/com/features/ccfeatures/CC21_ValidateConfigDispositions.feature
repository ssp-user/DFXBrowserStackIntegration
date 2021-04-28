@CC21
Feature: CC21_ValidateConfigDispositions, Update Config Dispositions and Validate

  @Regression @Smoketest
  Scenario Outline: Update Config Dispositions and Validate
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "CONFIG" tab on top of the page
    And I click "Dispositions" on 'CONFIG' page
    And I click "Other" on 'CONFIG' page
    And I update "<EN-Other>" for other on 'CONFIG' page
    And I click "Other - Closed Call" on 'CONFIG' page
    And I update "<EN-OtherClosedCall>" on 'CONFIG' page
    And I click "Save" on 'CONFIG' page
    And I click "Ok" on 'CONFIG' page
    And I click "TASKS FROM ANY OTHER TAB" tab on top of the page
    And I select "second" customer on 'TASKS' page
    Then I see 'Task Detail' page
    And I validate option as "<EN-Other>" in 'TASKS' page    
    When I select "<ActionValue>" from "Select Action" dropdown menu
    And I click "Save" button on 'Task Detail' page
    And I click "Cancel" button on 'Task Detail' page

    Examples: below data set for data driven
      | URL      | DealerName            | EN-OtherClosedCall | ActionValue | EN-Other |
      | TEST2DFX | Nissan of Anytown USA | GLOBAL             | GLOBAL      | GLOBAL   |
