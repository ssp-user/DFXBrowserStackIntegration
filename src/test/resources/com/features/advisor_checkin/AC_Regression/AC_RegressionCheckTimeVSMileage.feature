@AC-Regression-TimeVSMileage
Feature: Advisor Check able to generated  Maintenance Time&Mileage documentation function
  Description : The purpose of this feature verify Advisor Check able to generated  Maintenance Time&Mileage documentation function

  @AC-Regression-TimeVSMileage-VW
  Scenario Outline: Verify the Advisor check Maintenance Time&Mileage documentation function
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" and "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    Then I verify Mandatory Message Displayed in Fields on 'CUSTOMER' page
    And I verify "NEXT" button is not clickable if 'Odometer' field is empty
    When I enter "<Odometer1>" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I goto "SERVICES" Tab on 'Advisor Check-In' page
    And I verify 'MONTH' and 'MILES' service larger than below on 'SERVICES' page
      | FieldName       | Value        |
      | Month           | 30           |
      | Mileage         | 300000       |
    And I click "MENU" button on 'SERVICES' page
    Then I verify "Maintenance Time" service PDF generated on 'SERVICES' page
    When I goto "CUSTOMER" Tab on 'Advisor Check-In' page
    When I enter "<Odometer2>" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I goto "SERVICES" Tab on 'Advisor Check-In' page
    Then I verify 'MONTH' and 'MILES' service larger than below on 'SERVICES' page
      | FieldName       | Value        |
      | Month           | 72           |
      | Mileage         | 600000       |
    And I click "MENU" button on 'SERVICES' page
    Then I verify "Maintenance Mileage" service PDF generated on 'SERVICES' page
    Then I assert all above PDF verify result on 'SERVICES' page
    And I signOut From AC

    Examples: below data set for data driven
      | URL        | DMS         | DealerName           | DealerCode | Odometer1 | Odometer2 | VIN                |
      | TST2ADVOLK | vwFramework | Volkswagen of Naples |            | 1000      | 56478     | 1VWCV7A30FC001749  |