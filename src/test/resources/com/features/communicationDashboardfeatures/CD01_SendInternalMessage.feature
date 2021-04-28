@CD01 @Regression
Feature: CD01_InternalMessage
  Description: Internal Message

  Scenario Outline: Initiate Internal Message
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I click "Communication Dashboard" on the 'Home' page
    And I click "Internal" on 'Communication Dashboard' window
    And I click "Edit" on 'Communication Dashboard' window
    And I click "Send Internal Message" on 'Communication Dashboard' window
    And I randomly select an advisor on 'Communication Dashboard' window
    And I randomly select an advisor on 'Communication Dashboard' window
    And I click "Forward Arrow" on 'Communication Dashboard' window
    And I send and validate "<COUNT>" internal messages on 'Communication Dashboard' window
    And I click "Close" on 'Communication Dashboard' window

    Examples: below data set for data driven
      | URL      | COUNT | DealerName           | Field4 |
      | TEST2DFX |     5 | anytown usa automall | Value4 |
