@AC13 @Regression1
Feature: AC13 - Verify service lane documents for General Motors
  
  Description : The purpose of this feature is to verify service lane documents for General Motors

  Scenario Outline: Verify service lane documents for different dealers
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    When I open "<VehicleMaker>" ServiceIcon on 'CUSTOMER' page
    Then I verify "<VehicleMaker>" OEM_ReportPDF status "<Recalls>" and "<CONTRACT>" on 'CUSTOMER' page
    When I enter "60000" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    Then I see "CONCERNS" tab is activated on 'CUSTOMER CONCERN' page
    When I click "SERVICES" tab on 'CUSTOMER CONCERN' page
    Then I see "VEHICLE HEALTH" tab is activated on 'SERVICES' page

    Examples: below data set for data driven
      | URL      | DealerName          | DMS     | VehicleMaker | VIN               | Recalls | CONTRACT | Application     |
      | TEST2DFX | Midway Chevrolet GM | Recalls | GM           | 1G4ZP5SZ4JU122474 | N       | N        | ADVISOR CHECKIN |
