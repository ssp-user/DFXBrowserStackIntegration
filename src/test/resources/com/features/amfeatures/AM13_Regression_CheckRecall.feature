@AM13 
Feature: AM13_Regression_CheckRecall. This test suite is Appointment Management Regression suite
  Description: to verify Appointment Management function work properly

  @AM_Regression13.1_RecallVehicleServiceToyota-TEST2 @Regression1 @Sanity1
  Scenario Outline: AM_Regression13.1, Verify the Appointment Management Recall function on Vehicle page and Service page
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I search "VIN" with "<VIN>" on 'CUSTOMER SEARCH' page
    And I click "+" button 'under vehicle' on 'CUSTOMER SEARCH' page
    Then I verify the 'Recall count' and 'Recall list' on 'CLIENT & VEHICLE' page
    When I set customer data on 'CLIENT & VEHICLE' page
      | FieldName       | Value                  |
      | Customer E-mail | testdealerfx@gmail.com |
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    ## Verify in Service Page
    And I goto "RECALLS" tab on 'SERVICES' page in AM
    Then I verify the 'Recall count' and 'Recall list' on 'SERVICES' page in AM
    And I verify the 'Recall count' status "<Recall>" on 'SERVICES' page in AM
    And I signout from AM

    Examples: below data set for data driven
      | URL         | DMS   | DealerName            | DealerCode | VIN               | Recall | Odometer |
      | TEST2ALTOYO | Dummy | Toyota of anytown usa |            | 1NXBR32EX5Z365791 | yes    |    51000 |
      | TEST2ALTOYO | Dummy | Toyota of anytown usa |            | 4T1BF1FK5GU226278 | no     |    51000 |

  @AM_Regression13.2_RecallVehicleServiceMitsubishi-TEST2 @Regression1 @Sanity1
  Scenario Outline: AM_Regression13.2Verify the Appointment Management Recall function on Vehicle page and Service page
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I search "VIN" with "<VIN>" on 'CUSTOMER SEARCH' page
    And I click "+" button 'under vehicle' on 'CUSTOMER SEARCH' page
    Then I verify the 'Recall count' and 'Recall list' on 'CLIENT & VEHICLE' page
    When I set customer data on 'CLIENT & VEHICLE' page
      | FieldName       | Value                  |
      | Customer E-mail | testdealerfx@gmail.com |
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    ## Verify in Service Page
    And I goto "RECALLS" tab on 'SERVICES' page in AM
    Then I verify the 'Recall count' and 'Recall list' on 'SERVICES' page in AM
    And I verify the 'Recall count' status "<Recall>" on 'SERVICES' page in AM
    And I signout from AM

    Examples: below data set for data driven
      | URL         | DMS   | DealerName         | DealerCode | VIN               | Recall | Odometer |
      | TEST2ALMITS | Dummy | Capital Mitsubishi |            | JA32U2FU4DU609656 | yes    |    52000 |
      | TEST2ALMITS | Dummy | Capital Mitsubishi |            | JA4JT3AX0BU601908 | no     |    52000 |


  @AM_Regression13.3_RecallVehicleServiceNissan-TEST2 @Regression1 @Sanity1
  Scenario Outline: AM_Regression13.3,Verify the Appointment Management Recall function on Vehicle page and Service page
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I search "VIN" with "<VIN>" on 'CUSTOMER SEARCH' page
    And I click "+" button 'under vehicle' on 'CUSTOMER SEARCH' page
    Then I verify the 'Recall count' and 'Recall list' on 'CLIENT & VEHICLE' page
    When I set customer data on 'CLIENT & VEHICLE' page
      | FieldName       | Value                  |
      | Customer E-mail | testdealerfx@gmail.com |
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    ## Verify in Service Page
    And I goto "RECALLS" tab on 'SERVICES' page in AM
    Then I verify the 'Recall count' and 'Recall list' on 'SERVICES' page in AM
    And I verify the 'Recall count' status "<Recall>" on 'SERVICES' page in AM
    And I signout from AM

    Examples: below data set for data driven
      | URL         | DMS   | DealerName  | DealerCode | VIN               | Recall | Odometer |
      | TEST2ALNISS | Dummy | Sisk Nissan |            | 1N6AA07C68N321957 | yes    |    53000 |

  @AM_Regression13.4_RecallVehicleServiceNissan-TEST2 @Regression1 @Sanity1
  Scenario Outline: AM_Regression13.4, Verify the Appointment Management Recall function on Vehicle page and Service page
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I search "VIN" with "<VIN>" on 'CUSTOMER SEARCH' page
    And I click "+" button 'under vehicle' on 'CUSTOMER SEARCH' page
    Then I verify the 'Recall count' and 'Recall list' on 'CLIENT & VEHICLE' page
    When I set customer data on 'CLIENT & VEHICLE' page
      | FieldName       | Value                  |
      | Customer E-mail | testdealerfx@gmail.com |
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    ## Verify in Service Page
    And I goto "RECALLS" tab on 'SERVICES' page in AM
    Then I verify the 'Recall count' and 'Recall list' on 'SERVICES' page in AM
    And I verify the 'Recall count' status "<Recall>" on 'SERVICES' page in AM
    And I signout from AM

    Examples: below data set for data driven
      | URL         | DMS   | DealerName  | DealerCode | VIN               | Recall | Odometer |
      | TEST2ALNISS | Dummy | Sisk Nissan |            | 1N4AA5AP4BC855303 | no     |    56000 |
      
      
      
      
      
      
   @AM_Regression13.4_RecallVehicleServiceFCA-TEST2 @FCA_Regression @FCA_Sanity
  Scenario Outline: AM_Regression13.4, Verify the Appointment Management Recall function on Vehicle page and Service page
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I search "VIN" with "<VIN>" on 'CUSTOMER SEARCH' page
    And I click "+" button 'under vehicle' on 'CUSTOMER SEARCH' page
    Then I verify the 'Recall count' and 'Recall list' on 'CLIENT & VEHICLE' page
    When I set customer data on 'CLIENT & VEHICLE' page
      | FieldName       | Value                  |
      | Customer E-mail | testdealerfx@gmail.com |
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    ## Verify in Service Page
    And I goto "RECALLS" tab on 'SERVICES' page in AM
    Then I verify the 'Recall count' and 'Recall list' on 'SERVICES' page in AM
    And I verify the 'Recall count' status "<Recall>" on 'SERVICES' page in AM
    And I signout from AM

    Examples: below data set for data driven
      | URL         | DMS   | DealerName  | DealerCode | VIN               | Recall  | Odometer |
      | TEST2ALNISS | Dummy | chrysler qa |            | 1J4GL38K43W538970 | yes     |    56000 |
      | TEST2ALNISS | Dummy | chrysler qa |            | 1G11C5SA1DF302116 | no      |    56000 |
     