@AC07 @Regression1 @test21
Feature: AC07 - AC_OEMRecall, Advisor Check In Morning Health Check on RO push, Test on ENV TEST2
  Description : The purpose of this feature verify the AC function work properly on Push RO in Prod2 with diversity
  DMS name and VINs Data

  @AC-RegressionRecall-Test2-Debug-Toyota
  Scenario Outline: Advisor check Verify OEM Recalls and Contract on RO push for <DMS>
    Description :

    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    #Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-IBarn' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    And I click "ACKNOWLEDGE" button on 'CUSTOMER' page
    Then I see Following status on 'CUSTOMER' page
      | FieldName        | Value      |
      | VIEW WARRANTY    | <WARRANTY> |
      | SERVICE CONTRACT | <CONTRACT> |
      | RECALLS          | <Recalls>  |
    When I open "<VehicleMaker>" ServiceIcon on 'CUSTOMER' page
    Then I verify "<VehicleMaker>" OEM_ReportPDF status "<Recalls>" and "<CONTRACT>" on 'CUSTOMER' page
    When I enter "60000" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    Then I see "CONCERNS" tab is activated on 'CUSTOMER CONCERN' page
    When I click "SERVICES" tab on 'CUSTOMER CONCERN' page
    Then I see "VEHICLE HEALTH" tab is activated on 'SERVICES' page
    And I see number of <Recalls> and recalls descriptions and status of recalls

    Examples: below data set for data driven
      | URL     | DealerName        | DMS     | VehicleMaker | VIN               | Recalls | CONTRACT | WARRANTY | Application     |
      | TEST2DFX | South Dade Toyota | Recalls | Toyota       | JTDBT4K3XA1352478 | Y       | N        | Y        | ADVISOR CHECKIN |
      | TEST2DFX | South Dade Toyota | Recalls | Toyota       | 4T4BF3EK2AR031085 | N       | N        | Y        | ADVISOR CHECKIN |
