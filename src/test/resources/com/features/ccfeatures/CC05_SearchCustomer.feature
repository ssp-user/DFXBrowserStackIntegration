@CC_Req_005
Feature: CC05_SearchCustomer, Search Customer by phone, name, vin or email

  @CC_Testcase_005 @Regression @Sanity
  Scenario Outline: Verify that BDC Agent is able to Search Customer by phone, name, vin or email
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "SEARCH" tab on top of the page
    #Search by Name
    And I search "DFX" on 'SEARCH' page
    And I click "Name" button on 'SEARCH' page
    Then I see "Name" results matched "DFX"
    #Search by Phone
    When I search "(123) 456-7890" on 'SEARCH' page
    And I click "Phone" button on 'SEARCH' page
    Then I see "Phone" results matched "(123) 456-7890"
    #Search by Email
    When I search "test@dealer-fx.com" on 'SEARCH' page
    And I click "Email" button on 'SEARCH' page
    Then I see "Email" results matched "test@dealer-fx.com"
    When I search following VIN on 'SEARCH' page with "Nissan of Anytown USA" dealer
      | Dealer                | VIN               |
      | Nissan of Anytown USA | 5N1AA08A35N712443 |
    And I click "VIN" button on 'SEARCH' page
    Then I see following VIN displayed on 'SEARCH' page with "Nissan of Anytown USA" dealer
      | Dealer                | VIN               |
      | Nissan of Anytown USA | 5N1AA08A35N712443 |

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
