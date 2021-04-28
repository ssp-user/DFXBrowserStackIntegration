@AM29 @smoketest_AM @Regression_AM @Regression2 @FCA_Regression
Feature: AM29_Regression_OwnershipTransferToDifferentCustomerType. This test suite is Appointment Management Regression suite
  Description: To verify ownership transfer process to different customer type. If current customer type is individual, scenario will convert it to business customer and vice versa

  @dummyDMS
  Scenario Outline: AM_Regression29, Ownership transfer to different customer type
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
    And I search "VIN" with "<VIN>" on 'CUSTOMER SEARCH' page
    And I click "+" button 'under vehicle' on 'CUSTOMER SEARCH' page
    And I click business checkbox to change customer type
    And I follow company name updation process if company name is available otherwise Individual Customer updation process
      | FieldName       | Value                  |
      | First name      | Random                 |
      | Last name       | Random                 |
      | Default phone   | Random                 |
      | Customer E-mail | testdealerfx@gmail.com |
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I verify message "Entered customer information matched other customer(s). What do you want to do?" on 'HISTORY' page in AM if Individual Customer
    And I click "CREATE NEW CUSTOMER" button if on 'HISTORY' page in AM if Individual Customer
    And I click "NEXT" button if on 'HISTORY' page in AM
    ## Verify in Service Page
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I select "<ServiceAdvisor>" in 'Advisors' section on 'TIME & ADVISOR' page
    And I set promise date next "2" month "The 3th Tuesday" on 'TIME & ADVISOR' page
    And I set promise time "5th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    Then I verify selected items price on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open this Appointment on 'DASHBOARD' page
    And I click "DELETE" button in this Appointment on 'DASHBOARD' page
    And I signout from AM

    Examples: below data set for data driven
      | URL         | DMS   | DealerName           | VIN               | ServiceAdvisor | Odometer |Application|
      | TEST2ALNISS | Dummy | anytown usa automall | JM3KFADM4K1631235 |              1 |    35001 |Appointment Manager|
