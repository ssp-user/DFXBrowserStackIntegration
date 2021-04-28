@HM04 @Regression
Feature: HM04_ValidateApplicationInFrench
  Description: Validate UI in French language

  Scenario Outline: Validate UI in French language
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I click "User account" on the 'Home' page
    And I click "French Language" on the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I validate "French Translations" on 'Home' page change dealership window
    And I click "Change dealer close button" on the 'Home' page
    And I validate "French Translations" of business applications on 'Home' page
    And I click "User account" on the 'Home' page   
    And I click "English - Canada" on the 'Home' page     
    And I click "User account" on the 'Home' page       
    And I click "Logout" on the 'Home' page

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
