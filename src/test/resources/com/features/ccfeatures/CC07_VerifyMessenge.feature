@CC_Req_007
Feature: CC07_VerifyMessenge, Verify BDC Agent sending SMS to customer

  @CC_Testcase_007 @Regression @Smoketest
  Scenario Outline: Verify BDC Agent sending SMS to customer
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "MESSENGER" tab on top of the page
    And I search "TEST" in "Search Customer" field on 'MESSENGER' page
    Then I see "Customer name" contains "TEST" displayed on 'MESSENGER' page
    And I click "Customer name" search result contains "TEST" on 'MESSENGER' page
    And I click "modify" button on 'MESSENGER' page
    And I modify "2895363513" in "Cell Phone" field on 'Customer Details' popup
    And I click "Save" button on 'Customer Details' popup
    And I send SMS "TEST random" to customer on 'MESSENGER' page
    And I open page "ReceiveaSMS.com :: Canada" in the new browser tab
    Then I see SMS contains 'TEST random' associated with on 'ReceiveaSMS.com :: Canada' page
    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
