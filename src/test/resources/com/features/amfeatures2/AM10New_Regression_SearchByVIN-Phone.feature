@AM10 @Regression1 @Sanity1 @FCA_Regression 
Feature: AM10_Regression_SearchByVIN-Phone. This test suite is Appointment Management Regression suite
  Description: to verify Appointment Management function work properly

  Scenario Outline: AM_Regression10, Verify the function the application is able to search customer by the VIN or Phone
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
      | URL         | DMS   | DealerName           | VIN1              | VIN2              | PHONE1     | PHONE2     |Application        |
      | TEST2ALNISS | Dummy | anytown usa automall | WAUDGAFL7BA002147 | WVGBE67L09D008002 | 4567888888 | 7777777777 |Appointment Manager|
