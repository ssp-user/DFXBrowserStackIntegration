@HM03 @Regression
Feature: HM03_ValidateResetPasswordScreen
  Description: Validate reset password screen

  Scenario Outline: Validate reset password screen
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I note down page window handle from the 'Home' page
    And I click "User account" on the 'Home' page
    And I click "Reset password" on the 'Home' page
    And I validate "Change Password" as window title of the focussed screen
    And I validate username on the 'Change Password' page
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I click "User account" on the 'Home' page
    And I click "Logout" on the 'Home' page

    Examples: below data set for data driven
      | URL      | Field2 | Field3 | Field4 |
      | TEST2DFX | Value2 | Value3 | Value4 |
