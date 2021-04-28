@SSA_Regression07-BookApptNotMakeModelByVIN-TEST2
Feature: This Test suite is Schedule Service Appointment Regression suite on Test2
  Description : to verify a set of functions in Schedule Service Appointment system work properly

  @SSA_Regression07_BookApptNoMakeModelByVIN-TEST2
  Scenario Outline: SSARegression07, Verify the function of book a new appointment without Make Model by VIN in Test2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I click "I am a new customer" button on 'Search' page in SSA
    Then I do not see the 'vehicle specs' on 'Vehicle Info' page in SSA
    When I decode "<VIN>" on 'Vehicle Info' page in SSA
    And I input "<Odometer>" to continue on 'Vehicle Info' page in SSA
    And I click "NEXT" button on 'Service' page in SSA
    And I add "1th" concern with comment "" on 'Customer Concerns' page
    And I click "NEXT" button on 'Customer Concerns' page
    Examples: below data set for data driven
      |URL          |DMS     |DealerName            |VIN                |Odometer |DealerID    |
      |TEST2SSAAUDIR |Dummy   |Anytown USA Automall  |1G6DN57P490168257  |42890    |4363        |

