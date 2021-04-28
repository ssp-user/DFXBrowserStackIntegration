@HM07 @Regression
Feature: HM07_ValidateApplicationFromSideBarMoreButton.feature
  Description: Validate Application from more button

  Scenario Outline: Open application from More button in side bar
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I click "More Options" on the 'Home' page
    And I click "Technician Inspection" on the 'Home' page
    And I validate "MPI List" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I click "More Options" on the 'Home' page
    And I click "Online Maintenance Menu" on the 'Home' page
    And I validate "OMM Portal Framework - Dealer-FX CRM Integrated" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I click "More Options" on the 'Home' page
    And I click "Personalized Service Welcome" on the 'Home' page
    And I validate "Service To Sales Introduction" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page
    And I click "More Options" on the 'Home' page
    And I click "Customer Management" on the 'Home' page
    And I validate "Contact List" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
