@CD04 @Regression
Feature: CD04_SendAndValidateInternalMessage
  Description: Send and Validate Internal Message

  Scenario Outline: Send and Validate Internal Message
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I click "Communication Dashboard" on the 'Home' page
    And I click "Internal" on 'Communication Dashboard' window
    And I click "Edit" on 'Communication Dashboard' window
    And I click "Send Internal Message" on 'Communication Dashboard' window
    And I enter "<SEARCHPARAMETER>" of the "<SEARCHTYPE>" on 'Communication Dashboard' window
    And I click "Forward Arrow" on 'Communication Dashboard' window
    And I send and validate "<COUNT>" customer messages on 'Communication Dashboard' window
    And I click "Close" on 'Communication Dashboard' window
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "sa.4363" and password "welcome"
    And I click "Communication Dashboard" on the 'Home' page       
    And I click "New Message" on 'Communication Dashboard' window
    And I validate internal message on 'Communication Dashboard' window    

    Examples: below data set for data driven
      | URL      | COUNT | DealerName           | SEARCHPARAMETER    | SEARCHTYPE |
      | TEST2DFX |     1 | anytown usa automall | QA Service Advisor | internal   |
