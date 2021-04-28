@CC_Testcase_0224
Feature: CC24_ValidateInboundCall

  @CC_Testcase_024 @Regression
  Scenario Outline: Validate Inbound call
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "SEARCH" tab on top of the page
    When I search following VIN on 'SEARCH' page with "<DealerName>" dealer
      | Dealer                | VIN               |
      | Nissan of Anytown USA | 5N1AA08A35N712443 |
    And I click "VIN" button on 'SEARCH' page
    And I capture customer name on 'SEARCH' page
    And I click ">" button on 'SEARCH' page
    And I click "+" button on 'SEARCH' page
    And I validate "<VIN>" on 'SEARCH' page
    And I click "CANCEL" button on 'SEARCH' page
    And I click on "TASKS" tab on 'SEARCH' page
    Then I see "Tasks List" on 'TASKS' Page
    When I select "Inbound Call List" from "Campaign Dropdown" dropdown menu
    And I search customer by name on 'TASKS' Page
    And I select "second" customer on 'TASKS' page
    When I select "Email Sent" from "Select Action" dropdown menu
    And I click "Save" button on 'Task Detail' page

    Examples: 
      | URL      | DealerName            | Username      | Password | VIN               |
      | TEST2DFX | Nissan of Anytown USA | DarrenNissanD | welcome  | 5N1AA08A35N712443 |
