@CC_Testcase_012
Feature: CC12_UpdateCustomerInfo, Verify advisor can update customer's information

  @CCScripts12 @Regression @Sanity
  Scenario Outline: Verify advisor can update customer's information
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "TASKS" tab on top of the page
    And I select "Missed / No Show Appointments" from "Campaign Dropdown" dropdown menu
    And I select any customer from 'tasks list' where "Assigned" equal "N/A"
    #Modify customer's information from task detail page
    And I click "Modify" icon in 'Customer Details' section on 'Task Detail' page
    And I modify following data on "Customer Details" pop up
      | FieldName      | Value          |
      | Cell Phone     |     1234567890 |
      | Address        | 80 Tiverton Ct |
      | City           | Markham        |
      | Country        | Canada         |
      | Zip            | L3R 0G4        |
      | State/Province | Ontario        |
    And I click "Save" button on "Customer Details" pop up
    And I click "OK" button on "Message" pop up
    Then I see following data in "Customer Details" section on 'Task Detail' page
      | IconName      | Value                             |
      | HomeIcon      | 80 TIVERTON CT MARKHAM ON L3R 0G4 |
      | CellPhoneIcon | (123) 456-7890                    |
    And I click "Cancel" button on 'Task Detail' page
    #Modify customer's information from Messenger page
    And I click "MESSENGER" tab on top of the page
    And I search "DFX" in "Search Customer" field on 'MESSENGER' page
    And I click "Customer name" search result contains "DFX" on 'MESSENGER' page
    And I click "modify" button on 'MESSENGER' page
    And I modify following data on "Customer Details" pop up
      | FieldName      | Value          |
      | Cell Phone     |     1234567890 |
      | Address        | 80 Tiverton Ct |
      | City           | Markham        |
      | Country        | Canada         |
      | Zip            | L3R 0G4        |
      | State/Province | Ontario        |
    And I click "Save" button on "Customer Details" pop up
    And I click "OK" button on "Message" pop up
    Then I see following data in "Customer Details" section on 'MESSENGER' page
      | IconName      | Value                             |
      | HomeIcon      | 80 TIVERTON CT Markham ON L3R 0G4 |
      | CellPhoneIcon | (123) 456-7890                    |
    #Modify customer's information from Search page
    And I click "SEARCH" tab on top of the page
    And I search "DFX" on 'SEARCH' page
    And I click "Name" button on 'SEARCH' page
    And I click "modify" icon linked with Name contains "GURPARTAP DFX" on 'SEARCH' page
    And I modify following data on "Customer Details" pop up
      | FieldName      | Value          |
      | Cell Phone     |     1234567890 |
      | Address        | 80 Tiverton Ct |
      | City           | Markham        |
      | Country        | Canada         |
      | Zip            | L3R 0G4        |
      | State/Province | Ontario        |
    And I click "Save" button on "Customer Details" pop up
    And I click "OK" button on "Message" pop up
    Then I see following data linked with name contains "DFX" on 'SEARCH' page
      | IconName      | Value                               |
      | HomeIcon      | 80 TIVERTON CT, MARKHAM, ON L3R 0G4 |
      | CellPhoneIcon | (123) 456-7890                      |
      
    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |      
