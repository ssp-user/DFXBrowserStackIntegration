@HM01 @Regression
Feature: HM01_SideBarFunctionality
  Description: Open application by Side Bar. Validate application Title.

  Scenario Outline: Open applications by Side Bar and validate its title
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Payments" application in the sidebar menu from the 'Home' page
    And I validate "DFX Payments" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I choose "Appointment Manager" application in the sidebar menu from the 'Home' page
    And I validate "Appointment Manager" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I choose "Payments" application from the 'Home' page
    And I validate "DFX Payments" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I choose "Appointment Manager" application from the 'Home' page
    And I validate "Appointment Manager" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page    

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
