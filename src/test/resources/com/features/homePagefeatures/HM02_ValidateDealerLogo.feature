@HM02 @Regression
Feature: HM02_ValidateDealerLogo
  Description: Search a dealer. Validate its logo.

  Scenario Outline: Search a dealer and validate its logo
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "Nissan of Anytown USA" or "" in Dealer Page
    And I validate "Nissan" logo from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "408A85US" or "" in Dealer Page
    And I validate "Audi" logo from the 'Home' page

    Examples: below data set for data driven
      | URL      | Field2 | Field3 | Field4 |
      | TEST2DFX | Value2 | Value3 | Value4 |
