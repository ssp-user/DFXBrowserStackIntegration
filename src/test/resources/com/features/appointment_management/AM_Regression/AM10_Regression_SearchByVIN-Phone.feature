@AM10
Feature: AM10_Regression_SearchByVIN-Phone. This test suite is Appointment Management Regression suite
  Description: to verify Appointment Management function work properly

  @AM_Regression10_SearchCustomerByVIN&Phone-TEST2-AUDI
  Scenario Outline: AM_Regression10, Verify the function the application is able to search customer by the VIN or Phone
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And  I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    ## SEARCH by VIN
    And I search "VIN" with "<VIN1>" on 'CUSTOMER SEARCH' page
    And I see "VIN" is "<VIN1>" on 'CUSTOMER SEARCH' page
    And I search "VIN" with "<VIN2>" on 'CUSTOMER SEARCH' page
    And I see "VIN" is "<VIN2>" on 'CUSTOMER SEARCH' page
    ## SEARCH by PHONE
    And I search "PHONE" with "<PHONE1>" on 'CUSTOMER SEARCH' page
    And I see "PHONE" is "<PHONE1>" on 'CUSTOMER SEARCH' page
    And I search "PHONE" with "<PHONE2>" on 'CUSTOMER SEARCH' page
    And I see "PHONE" is "<PHONE2>" on 'CUSTOMER SEARCH' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS   |DealerName           |VIN1              |VIN2              | PHONE1    | PHONE2     |
      |TEST2ALAUDI  |Dummy |Audi of anytown usa  |WA1VGAFPXFA007949 |WAUVGAFR7EA019544 |4162223344 | 4162223344 |