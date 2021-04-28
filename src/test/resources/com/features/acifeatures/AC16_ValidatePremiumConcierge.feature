@AC16 @Regression1
Feature: AC16 - Premium Concierge transport Validation
  
  Description : The purpose of this feature is to validate Premium concierge transportation option

  Scenario Outline: Verify service lane documents for different dealers
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    When I enter "60000" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button in Dealer 'HISTORY' page
    And I click "NEXT" button on 'SERVICES' page
    And I click "NEXT" button on 'WALK AROUND' page
    And I validate Transportation option as "<TransportationOption>" in 'ASSIGN R.O.' page
    And I click "NEXT" button on 'ASSIGN R.O.' page

    Examples: below data set for data driven
      | URL      | DealerName          | DMS     | TransportationOption       | VIN               | Application     |
      | TEST2DFX | Midway Chevrolet GM | Recalls | PREMIUM CONCIERGE          | JTKJF5C78E3068330 | ADVISOR CHECKIN |
      | TEST2DFX | Midway Chevrolet GM | Recalls | CADILLAC PREMIUM CONCIERGE | 1G6AX5SX8K0136986 | ADVISOR CHECKIN |
