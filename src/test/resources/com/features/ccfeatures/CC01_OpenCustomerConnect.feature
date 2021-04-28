@CC_Req_001 
Feature: CC01_OpenCustomerConnect, Open Customer Connect As BDC Manager

  @CC_Testcase_001 @Regression @Sanity
  Scenario Outline: User login to application as BDC Manager
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I validate "Customer Connect" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |