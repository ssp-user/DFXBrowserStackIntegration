@AM34 @Regression2 @FCA_Regression
Feature: AM34_Regression_ValidatePremiumConciergeTransport option. This test suite is Appointment Management Regression suite
  Description: Validate Premium Concierge transport option

  @dummyDMS
  Scenario Outline: AM_Regression34, Validate Premium Concierge transport option
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I search "VIN" with "<VIN>" on 'CUSTOMER SEARCH' page
    And I click "+" button 'under vehicle' on 'CUSTOMER SEARCH' page
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    ## Verify in Service Page
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I select "<ServiceAdvisor>" in 'Advisors' section on 'TIME & ADVISOR' page
    And I validate Transportation option "<TransportationOption>" exists on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page

    Examples: below data set for data driven
      | URL      | DMS   | DealerName          | VIN               | ServiceAdvisor | Odometer | Application         | TransportationOption       |
      | TEST2DFX | Dummy | Midway Chevrolet GM | JTKJF5C78E3068330 |              1 |    35001 | Appointment Manager | Premium Concierge          |
      | TEST2DFX | Dummy | Midway Chevrolet GM | 1G6AX5SX8K0136986 |              1 |    35001 | Appointment Manager | Cadillac Premium Concierge |
