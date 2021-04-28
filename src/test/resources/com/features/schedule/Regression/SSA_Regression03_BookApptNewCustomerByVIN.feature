@SSA_Regression03-BookApptNewCustomerByVIN-TEST2
Feature: This Test suite is Schedule Service Appointment Regression suite on Test2
  Description : to verify a set of functions in Schedule Service Appointment system work properly

  @SSA_Regression03_BookApptNewCustomerByVIN-TEST2
  Scenario Outline: SSARegression03, Verify the function of book a new appointment for a new customer by VIN in Test2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I click "I am a new customer" button on 'Search' page in SSA
    And I decode "<VIN>" on 'Vehicle Info' page in SSA
    And I input "<Odometer>" to continue on 'Vehicle Info' page in SSA
    And I add "1th" concern with comment "" on 'Customer Concerns' page
    And I click "NEXT" button on 'Customer Concerns' page
    And I click "NEXT" button on 'Service' page in SSA
    And I click week ">" button "1" times on 'Schedule' page
    And I choose "1th" available time on 'Schedule' page
    And I choose "1th" available transportation option on 'Schedule' page
    And I click "NEXT" button on 'Schedule' page
    And I enter customer data set on 'Appointment Summary' page
      | FieldName      |Value                   |
      | First name     | Dfx                    |
      | Last name      | Random                 |
      | Phone number   | 4163219876             |
      | Email address  | testdealerfx@gmail.com |
    And I click "Confirm appointment" button on 'Appointment Summary' page
    Then I see "Thank you" message on 'Confirmation' page
    Examples: below data set for data driven
      |URL          |DMS     |DealerName           |VIN               |Odometer |Tranportation   |
      |TEST2SSAAUDIS |Dummy   |Audi of anytown USA  |WA1VMAFP3EA069135 |47000    |1th             |

