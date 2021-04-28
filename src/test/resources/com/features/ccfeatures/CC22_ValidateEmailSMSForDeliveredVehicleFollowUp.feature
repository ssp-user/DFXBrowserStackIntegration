@CC_Testcase_022
Feature: CC22_ValidateEmailSMSForDeliveredVehicleFollowUp

  @CC_Testcase_022 @Regression
  Scenario Outline: Validate Email SMS for Delivered Vehicle follow up
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "TASKS" tab on top of the page
    Then I see "Tasks List" on 'TASKS' Page
    When I select "Delivered Vehicle Follow Up" from "Campaign Dropdown" dropdown menu
    And I select "second" customer on 'TASKS' page
    Then I see 'Task Detail' page
    And I click "Modify" icon in 'Customer Details' section on 'Task Detail' page    
    And I select Email and Text checkbox if not selected already    
    And I modify "2895363513" in "Cell Phone" field on 'Customer Details' screen 
    And I modify "dfxcucumbertest@yahoo.com" in "Email" field on 'Customer Details' screen  
    And I click Save button on 'Task Detail' page   
    And I click OK button on 'Task Detail' page
    And I click Email on 'Task Detail' page  
    And I click chat submit on 'Task Detail' page     
    And I enter email address on 'Compose Message' page
    And I enter "Customer connect email" as Email Subject on 'Compose Message' page
    And I enter email contents on 'Compose Message' page
    And I click on Send button on 'Compose Message' page
    And I close the message pop up on 'Compose Message' page
    And I click "Modify" icon in 'Customer Details' section on 'Task Detail' page    
    And I select Email and Text checkbox if not selected already    
    And I modify "2895363513" in "Cell Phone" field on 'Customer Details' screen 
    And I modify "dfxcucumbertest@yahoo.com" in "Email" field on 'Customer Details' screen  
    And I click Save button on 'Task Detail' page   
    And I click OK button on 'Task Detail' page
    And I click SMS on 'Task Detail' page  
    And I click chat submit on 'Task Detail' page     
    And I enter SMS contents on 'Compose Message' page
    And I click on Send SMS button on 'Compose Message' page
    And I close the message pop up on 'Compose Message' page
    And I open page "ReceiveaSMS.com :: Canada" in the new browser tab
    Then I see SMS contains 'TEST random' associated with on 'ReceiveaSMS.com :: Canada' page 
    Given I launch browser "chrome" in "GUI" and open "YAHOO"
    And I login page "Yahoo" as User "dfxcucumbertest" and password "dealerfxqa"
    Then I see new email for customer connect in Yahoo inbox
    Then I see delete the email
    Then I signout for the application   
    
    Examples: 
      | URL      | DealerName            | Username      | Password |
      | TEST2DFX | Nissan of Anytown USA | DarrenNissanD | welcome  |
