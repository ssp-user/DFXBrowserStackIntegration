@CC_Testcase_010
Feature: CC10_Scripts, Verify Dealer admin can modify scripts

  @CCScripts01 @Regression @Smoketest
  Scenario Outline: Verify Dealer admin can modify scripts
    Given I launch browser "chrome" in "GUI" and open "TEST2DFX"
    When I login the Application as User "DarrenNissanD" and password "welcome"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "SCRIPTS" tab on top of the page
    #create new script
    And I select "Import Call List" from "Campaign Type" dropdown menu on 'SCRIPTS' page
    And I select "Phone" from "Script Type" dropdown menu on 'SCRIPTS' page
    And I click "NEW SCRIPT" button on 'SCRIPTS' page
    And I input "Test content" in the 'Script Content' field on 'SCRIPTS' page
    And I click "Create" button on 'SCRIPTS' page
    Then I see error message "Your script name is empty or too long!" on 'SCRIPTS' page
    When I click "OK" button on 'SCRIPTS' page
    And I add random script name in 'Script Name' field
    Then I see result by select following '-Insert a script variable-' dropDownOption
      | DropDownName            | Expected Insert Result    |
      | Customer First Name     | {Appointment.CustFirst}   |
      | Customer Last Name      | {Appointment.CustLast}    |
      | Vehicle Year/Make/Model | {Appointment.VehicleName} |
      | Dealership Name         | {BDCAgent.DealerName}     |
      | Dealership Phone Number | {BDCAgent.PhoneWork}      |
      | BDC Agent First Name    | {BDCAgent.ActorFirstName} |
      | BDC Agent Last Name     | {BDCAgent.ActorLastName}  |
      | Campaign Type           | {CampaignType}            |
    #        |Vehicle ID              |{VehicleID}              |
    And I click "Create" button on 'SCRIPTS' page
    And I click "OK" button on 'SCRIPTS' page
    Then I see random script name in the 'Script list' on 'SCRIPTS' page
    #delete the script
    When I click random script name in the 'Script list' on 'SCRIPTS' page
    And I click "DELETE" button on 'SCRIPTS' page
    And I click "OK" button on 'SCRIPTS' page
    And I click "OK" button on 'SCRIPTS' page
    Then I don't see random script name in the 'Script list' on 'SCRIPTS' page
    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |    

  @CCScripts02 @Regression @Smoketest
  Scenario Outline: Verify advisor can not access script page
    Given I launch browser "chrome" in "GUI" and open "TEST2DFX"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    Then I don't see "SCRIPTS" tab on 'Customer Connect' page
    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |    
