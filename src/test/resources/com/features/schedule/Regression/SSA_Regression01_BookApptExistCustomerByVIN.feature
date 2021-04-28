@SSA_Regression01-BookApptExistingCustomerByVIN
Feature: This Test suite is Schedule Service Appointment Regression suite on Test2
  Description : to verify a set of functions in Schedule Service Appointment system work properly

  @SSA_Regression01_BookApptExistingCustomerByVIN-TEST1
  Scenario Outline: SSARegression01, Verify the function of book a new appointment for an existing customer by VIN in TEST1
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "Dummy" to the 'DMS'
    When I search "<VIN>" on 'Search' page in SSA
    And I select vehicle on 'Search' page in SSA
    And I input "<PHONE>" 'phone' on 'Search' page in SSA
    And I click "Verify" button on 'Search' page in SSA
    And I input "<Odometer>" to continue on 'Search' page in SSA
    And I add "1th" concern with comment "" on 'Customer Concerns' page
    And I click "NEXT" button on 'Customer Concerns' page
    #And I add "1th" service with comment "" on 'Service' page in SSA
    And I click "NEXT" button on 'Service' page in SSA
    And I click week ">" button "1" times on 'Schedule' page
    And I choose "1th" available time on 'Schedule' page
    And I choose "<Transportation>" available transportation option on 'Schedule' page
    And I click "NEXT" button on 'Schedule' page
    And I get appointment time on 'Appointment Summary' page
    And I click "Confirm appointment" button on 'Appointment Summary' page
    Then I see "Thank you" message on 'Confirmation' page
    Examples: below data set for data driven
      |URL              |VIN               |PHONE      |Odometer  |Transportation|ServiceAdvisor|
      |TEST1SSAFCAS    |2B3HD56J5YH215743  |8552589985 |999999    |Own ride      |1             |

