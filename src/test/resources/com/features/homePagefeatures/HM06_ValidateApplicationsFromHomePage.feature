@HM06 @Regression
Feature: HM06_ValidateApplicationsFromHomePage.feature
  Description: Validate Applications From Home Page

  Scenario Outline: Validate Applications From Home Page
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Service Dashboard" application from the 'Home' page
    And I validate "Service Dashboard" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I choose "Advisor Checkin" application from the 'Home' page
    And I validate "Advisor Check-In" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I choose "Appointment Manager" application from the 'Home' page
    And I validate "Appointment Manager" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I choose "Customer Connect" application from the 'Home' page
    And I validate "Customer Connect" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I choose "Payments" application from the 'Home' page
    And I validate "DFX Payments" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I choose "Reports" application from the 'Home' page
    And I validate "DFX Reporting Portal / View and Generate On-Demand Reports" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I choose "Dealer Profile" application from the 'Home' page
    And I validate "General" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I choose "Admin Settings" application from the 'Home' page
    And I validate "Price Wizard" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
