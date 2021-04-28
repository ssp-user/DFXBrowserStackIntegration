@SSA_Regression02-BookApptExistingCustomerNotVIN-TEST2
Feature: This Test suite is Schedule Service Appointment Regression suite on Test2
  Description : to verify a set of functions in Schedule Service Appointment system work properly

  @SSA_Regression02_BookApptExistingCustomerNotVIN-TEST2
  Scenario Outline: SSARegression02, Verify the function of book a new appointment for an existing customer Not VIN in Test2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I click "I am a new customer" button on 'Search' page in SSA
    And I enter vehicle data set on 'Vehicle Info' page in SSA
      | FieldName        |Value                |
      | Make             | AUDI                |
      | Year             | 2016                |
      | Model            | Q5 (2.0L)           |
      | Transmission     | Automatic           |
      | Engine Size      | 4CYL                |
      | Drive Train      | quattro             |
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
      | First name     | Testoss                |
      | Last name      | donottouch             |
      | Phone number   | 2220090989             |
      | Email address  | testdealerfx@gmail.com |
    And I click "Confirm appointment" button on 'Appointment Summary' page
    Then I see and click "Yes,It is me" button on 'Appointment Summary' page
    Then I see "Thank you" message on 'Confirmation' page
    Examples: below data set for data driven
      |URL            |DMS     |DealerName  |Transportation|Odometer |ServiceAdvisor|
      |TEST2SSAAUDIS |Dummy   |            |Own ride      |14500    |1             |

