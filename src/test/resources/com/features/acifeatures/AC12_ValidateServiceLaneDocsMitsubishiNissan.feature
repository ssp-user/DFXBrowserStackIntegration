@AC12 @Regression1

Feature: AC12 - Verify service lane documents for Mitsubishi and Nissan

Description : The purpose of this feature is to verify service lane documents for Mitsubishi and Nissan

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
      | URL      | DealerName                | DMS     | VehicleMaker | VIN               | Recalls | CONTRACT | Application     |
      | TEST2DFX | Capital Mitsubishi        | Recalls | MITSUBISHI   | JA4AJ3AU8JU601827 | Y       | N        | ADVISOR CHECKIN |
      | TEST2DFX | Tonkin Wilsonville Nissan | Recalls | NISSAN       | 3N1BC13E48L358426 | Y       | N        | ADVISOR CHECKIN |