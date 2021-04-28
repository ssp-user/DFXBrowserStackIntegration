@CC_Testcase_014
Feature: CC14_ServiceLane, Verify if the service lane are showing under message

  @CCCampaign14 @Regression @Smoketest
  Scenario Outline: Verify if the service lane are showing under message
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "MESSENGER" tab on top of the page
    And I click "Customer" button on 'MESSENGER' page
    And I click "Service Lane" button on 'MESSENGER' page
    And I click "Refresh" button on 'MESSENGER' page
    Then I see "RetrieveServicelaneCustomers" service on "TEST2DFX" return Status Code "200 OK"

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
