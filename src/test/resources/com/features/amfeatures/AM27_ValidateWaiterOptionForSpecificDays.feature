@AM27 @Regression2 @FCA_Regression 

Feature: AM27_Regression_ValidateWaiterOptionForSpecificDays. This test suite is Appointment Management Regression suite
  Description: To verify Appointment Management function work properly for waiter option on specific days

  Scenario Outline: AM_Regression27, Verify Transportation Options setting changes in AM for waiter
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "" or "<DealerCode>" in Dealer Page
    Then I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I change 'User Menu' to "<UserMenu>" on 'DASHBOARD' page
    And I goto "TRANSPORTATION OPTIONS" tab on 'Administration' page in AM
    And I select 'Waiter'
    And I disable and save checkbox for Monday if already not disabled
    And I goto "BACK TO DASHBOARD" tab on 'Administration' page in AM
    And I click "CREATE" button on 'DASHBOARD' page
    And I search "Name" with "<CustomerName>" on 'CUSTOMER SEARCH' page
    And I select "1th" customer on 'CUSTOMER SEARCH' page
    And I click "+" button or "1th" customer vehicle if multiple on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName       | Value                  |
      | Customer E-mail | testdealerfx@gmail.com |
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName   | Value   |
      | Postal Code | M2J 1M5 |
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I select 'Waiter' in 'Transportation Options' section on 'TIME & ADVISOR' page
    And I set promise date next "2" month "The 3th Monday" on 'TIME & ADVISOR' page
    Then I see no available timeslot for Monday on 'TIME & ADVISOR' page
    And I signout from AM

    Examples: below data set for data driven
      | URL         | DMS   | UserMenu       | DealerCode       | ServiceAdvisor | Odometer | CustomerName |
      | TEST2ALNISS | Dummy | Administration | ANYUSAAUTOMALL   |              1 |    35001 | dfx          |
