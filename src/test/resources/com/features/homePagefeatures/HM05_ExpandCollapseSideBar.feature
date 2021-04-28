@HM05 @Regression
Feature: HM05_ExpandCollapseSideBar
  Description: Expand collapse side bar. Open application. Validate its title.

  Scenario Outline: Expand collapse side bar. Open application. Validate its title.
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "Nissan of Anytown USA" or "" in Dealer Page
    And I click "Logo" on the 'Home' page
    And I click "Appointment Manager" on the 'Home' page
    And I validate "Appointment Manager" as window title of the focussed screen

    Examples: below data set for data driven
      | URL      | Field2 | Field3 | Field4 |
      | TEST2DFX | Value2 | Value3 | Value4 |
