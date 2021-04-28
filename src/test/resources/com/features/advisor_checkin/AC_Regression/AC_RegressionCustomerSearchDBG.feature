@AC-CustomerSearch-Regression-Test2-Debug @Debug
Feature: AC function, As an User able to search the customer information by Name,Phone,Plate and VIN
  Description : Verify the Search customer Information function by Name, Phone, Plate ,and VIN


  @AC-RegressionFullRelease-CustomerSearch-PROD2
  Scenario Outline: Verify the Advisor checkin customer search function
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    #Search customer by name
    When I search "CUSTOMER NAME" with "<CustomerName>" in 'Advisor Check-In Search' page
    Then I verify search result contains "<CustomerName>" "name" on 'Advisor Check-In' page
    #Search customer by phone
    When I search "CUSTOMER PHONE" with "<PhoneNumber>" in 'Advisor Check-In Search' page
    And I select "first" search result on 'Advisor Check-In' page
    Then I verify "DEFAULT PHONE NUMBER" equal "<PhoneNumber>" on 'CUSTOMER' page
    #Search By vin ,verify full vin
    When I click "BACK TO SEARCH" button on 'CUSTOMER' page
    And I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    Then I verify "VEHICLE INFORMATION" equal "<VIN>" on 'CUSTOMER' page
    #verify vin with last 8 digits
    When I click "BACK TO SEARCH" button on 'CUSTOMER' page
    And I search 'VinNumber' "<VIN>Last8" on 'Advisor Check-In' page
    Then I verify "VEHICLE INFORMATION" equal "<VIN>" on 'CUSTOMER' page
    #verify vin with last 5 digits
    When I click "BACK TO SEARCH" button on 'CUSTOMER' page
    And I search 'VinNumber' "<VIN>Last5" on 'Advisor Check-In' page
    Then I verify "VEHICLE INFORMATION" equal "<VIN>" on 'CUSTOMER' page
    #Search by License Plate
    When I click "BACK TO SEARCH" button on 'CUSTOMER' page
    And I search "LICENSE PLATE" with "<LicensePlate>" in 'Advisor Check-In Search' page
    And I select "first" search result on 'Advisor Check-In' page
    Then I verify "LICENSE PLATE" equal "<LicensePlate>" on 'CUSTOMER' page
    # Assert All verify
    And I assert all above search verify result on 'CUSTOMER' page

    Examples: below data set for data driven
      |URL       |DealerName           |DMS                             |CustomerName|PhoneNumber    |LicensePlate   |VIN              |
      |PROD2AD   |Audi of Anytown USA  |Customer Search Functionality   |dfx         |416-493-0039   |Olga-2222      |WAUSGCFCXCN002530|