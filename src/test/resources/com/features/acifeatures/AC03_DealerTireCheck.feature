@AC03 @ADVISORDASHBOARD @ADVISORCHECKIN @ACDUMMY @ACDEALERTIRE @ACREGRESSION @Regression1 @test21
Feature: AC03 - Advisor check in RO push for <OEM>

  Scenario Outline: Advisor check in RO push for <OEM>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    Then I verify the Version number "<AD_VersionNumber>" on 'Advisor Check-In' page
    When I search "CUSTOMER NAME" with "<CustomerName>" in 'Advisor Check-In Search' page
    And I click "2th" search result on 'Advisor Check-In' page
    And I click "NEXT" button on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    And I input <Odometer> on "ODOMETER" field in Dealer 'VEHICLE' page
    And I click "NEXT" button in Dealer 'VEHICLE' page
    And I click "NEXT" button in Dealer 'CONCERNS' page
    And I click "NEXT" button in Dealer 'HISTORY' page
    #start to add tire
    And I click "TIRES" tab in Dealer 'SERVICES' page
    #    And I click "first" 'ADD TIRES' icon in Dealer 'SERVICES' page
    And I click 'ADD TIRES' icon in Dealer 'SERVICES' page
    #    And I choose "235/45R18" on "TIRE OFFER" popup in Dealer 'SERVICES' page
    And I choose "Last tire" on "TIRE OFFER" popup in Dealer 'SERVICES' page
    And I click "VIEW" button on "first" product on "TIRE OFFER" popup in Dealer 'SERVICES' page
    And I click "ADD TO QUOTE" button on 'TIRE OFFER' popup page
    And I click "QUOTE" button on 'SERVICES' page
    Then I should see GENERATE QUOTE completed

    Examples: below data set for data driven
      | URL      | DealerName           | OEM    | AD_VersionNumber  | CustomerName | Odometer | VIN               | Application     |
      | TEST2DFX | anytown usa automall | Nissan | ver. 2.9.19035.01 | dfx          |    60000 | 1N4AL3AP2EC141575 | ADVISOR CHECKIN |
