@CC_Testcase_015
Feature: CC15_CustomerNotes, Verify if the service advisor can add notes for customer

    @CCCampaign15  @Regression @Smoketest
  Scenario Outline: Verify if the service advisor can add notes for customer
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
      #Verify note on Messenger
      And I click "MESSENGER" tab on top of the page
      And I search "DFX" in "Search Customer" field on 'MESSENGER' page
      Then I see "Customer name" contains "DFX" displayed on 'MESSENGER' page
      And I get "Customer name" from the 1st row on 'MESSENGER' page
      And I click 'Customer name' from the 1st row on 'MESSENGER' page
      And I click "Add Note" button on 'MESSENGER' page
      And I enter "Test random" on "Notes" on 'MESSENGER' page
      And I click "close" on "Notes" on 'MESSENGER' page
      And I click "Note" button on 'MESSENGER' page
      Then I see "Test random" on "Notes" on 'MESSENGER' page
      #Verify note on Search
      And I click "SEARCH" tab on top of the page
      And I search "DFX" on 'SEARCH' page
      And I click "Name" button on 'SEARCH' page
      Then I see "Name" results matched "DFX"
      And I get "Customer name" from the 1st row on 'SEARCH' page
      And I click 'Customer name' from the 1st row on 'SEARCH' page
      And I click "CUSTOMER NOTES" tab under Customer Name
      And I click "Note" button on 'SEARCH' page
      And I enter "Test random" on "Notes" on 'SEARCH' page
      And I click "close" on "Notes" on 'MESSENGER' page
      And I click "Note" button on 'SEARCH' page
      Then I see "Test random" on "Notes" on 'SEARCH' page
      #Verify note on Task
      And I click on "TASKS" tab on 'SEARCH' page
      And I select "Delivered Vehicle Follow Up" from "Campaign Dropdown" dropdown menu
      And I get "Customer name" from the 1st row on 'TASKS' page
      And I click "Customer name" on the 1st row on 'TASKS' page
      Then I see 'Task Detail' page
      And I click "Note" button on 'TASKS' page
      And I enter "Test random" on "Notes" on 'TASKS' page
      And I click "close" on "Notes" on 'TASKS' page
      And I click "Note" button on 'TASKS' page
      Then I see "Test random" on "Notes" on 'TASKS' page

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |      