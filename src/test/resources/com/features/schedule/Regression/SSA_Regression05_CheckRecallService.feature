@SSA_Regression05-RecallService-TEST2
Feature: This Test suite is Schedule Service Appointment Recall function Regression suite on Test2
  Description : to verify a set of functions in Schedule Service Appointment system Recall function work properly

  @SSA_Regression05_RecallVehicleService-TEST2
  Scenario Outline: SSARegression05, Verify the function of Recall function for an existing customer by VIN on Test2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "Dummy" to the 'DMS'
    When I search "<VIN>" on 'Search' page in SSA
    And I select vehicle on 'Search' page in SSA
    And I input "<PHONE>" 'phone' on 'Search' page in SSA
    And I click "Verify" button on 'Search' page in SSA
    Then I verify the 'Recall count' status "<Recall>" on 'Search' page in SSA
    When I input "<Odometer>" to continue on 'Search' page in SSA
    Then I verify the 'Recall list' on 'Vehicle Info' page in SSA
    And I click "NEXT" button on 'Vehicle Info' page in SSA
    And I add "1th" concern with comment "" on 'Customer Concerns' page
    And I click "NEXT" button on 'Customer Concerns' page
    And I click "NEXT" button on 'Service' page in SSA
    And I click "NEXT" button in SSA
    And I click week ">" button "1" times on 'Schedule' page
    And I choose "1th" available time on 'Schedule' page
    And I choose "1th" available transportation option on 'Schedule' page
    And I click "NEXT" button on 'Schedule' page
    And I see the 'Recall count' status "<Recall>" on 'Appointment Summary' page
    Examples: below data set for data driven
      |URL              |VIN                 |PHONE      |Odometer  |Recall   |Transportation|
      |PROD2SSAAUDIR    |JA3AV66VX9U607782   |4162223344 |999999    |yes      |Own ride      |

