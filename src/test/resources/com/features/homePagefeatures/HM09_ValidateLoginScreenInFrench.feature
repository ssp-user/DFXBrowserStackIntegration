@HM08 @Regression
Feature: HM09_ValidateLoginScreenInFrench
  Description: Validate Login Screen In French

  Scenario Outline: Open applications by Side Bar and validate its title
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I choose "<PreferredLanguage>" on 'Login' page
    And I validate layout in "<PreferredLanguage>" of 'Login' page
    When I login the Application as User "" and password ""
    And I validate error message in "<PreferredLanguage>" on the 'Login' page

    Examples: below data set for data driven
      | URL      | DealerName            | PreferredLanguage | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Français          | Value4 |
