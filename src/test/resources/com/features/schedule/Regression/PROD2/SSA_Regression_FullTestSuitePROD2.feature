@SSA_Regression-PROD2
Feature: This Test suite is Schedule Service Appointment Recall function Regression suite on PROD2
  Description : to verify a set of functions in Schedule Service Appointment system Recall function work properly

  @SSA_Regression01_BookApptExistingCustomerByVIN-PROD2
  Scenario Outline: SSARegression01, Verify the function of book a new appointment for an existing customer by VIN in PROD2
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
      |URL             |VIN                 |PHONE      |Odometer  |Transportation|ServiceAdvisor|
      |PROD2SSAAUDIS   |WAUDGAFL7BA002147   |9057811444 |999999    |Own ride      |1             |

  @SSA_Regression02_BookApptExistingCustomerNotVIN-PROD2
  Scenario Outline: SSARegression02, Verify the function of book a new appointment for an existing customer Not VIN in PROD2
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
      |URL           |DMS     |DealerName  |Transportation|Odometer |ServiceAdvisor|
      |PROD2SSAAUDIS |Dummy   |            |Own ride      |14500    |1             |

  @SSA_Regression03_BookApptNewCustomerByVIN-PROD2
  Scenario Outline: SSARegression03, Verify the function of book a new appointment for a new customer by VIN in PROD2
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
      |URL           |DMS     |DealerName           |VIN               |Odometer |Tranportation   |
      |PROD2SSAAUDIS |Dummy   |Audi of anytown USA  |WA1VMAFP3EA069135 |47000    |1th             |

  @SSA_Regression04_BookApptNewCustomerNotVIN-PROD2
  Scenario Outline: SSARegression04, Verify the function of book a new appointment for a new customer Not by VIN in PROD2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I click "I am a new customer" button on 'Search' page in SSA
    And I enter vehicle data set on 'Vehicle Info' page in SSA
      | FieldName        |Value                |
      | Make             | AUDI                |
      | Year             | 2016                |
      | Model            | A3 (1.8L)           |
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
      | First name     | David                  |
      | Last name      | dfxQA                  |
      | Phone number   | 9058761234             |
      | Email address  | testdealerfx@gmail.com |
    And I click "Confirm appointment" button on 'Appointment Summary' page
    Then I see "Thank you" message on 'Confirmation' page
    Examples: below data set for data driven
      |URL           |DMS     |DealerName  |Transportation|Odometer |ServiceAdvisor|
      |PROD2SSAAUDIS |Dummy   |            |Own ride      |37800    |1             |

  @SSA_Regression05_RecallVehicleService-PROD2
  Scenario Outline: SSARegression05, Verify the function of Recall function for an existing customer by VIN on PROD2
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
      |URL             |VIN                 |PHONE      |Odometer  |Recall   |Transportation|
      |PROD2SSAAUDIR   |JA3AV66VX9U607782   |4162223344 |999999    |yes      |Own ride      |

  @SSA_Regression6.1_OnlineScheduleEnableRecall-PROD2
  Scenario Outline: SSARegression6.1, Verify the function of Enable Online Schedule Recall Setting function on PROD2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    When  I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "OMM Admin" Application on 'Main Page'
    And I goto "Admin" tab and select "Online Appointment Schedule" on 'OMM Admin' Page
    And I set Configure data on 'Admin-Online Appointment Schedule' page
      | FieldName                  | Value            |
      | Enable recalls in OSS      | yes              |
    And I click "Save" button on 'OMM Admin' Page
    Examples: below data set for data driven
      |URL          |DMS   |DealerName           |DealerCode    |VIN               |
      |PROD2DFX     |Dummy |Audi of anytown usa  |              |WAUVGAFR7EA019544 |

  @SSA_Regression6.2_EnableAppointmentWithoutVIN-PROD2
  Scenario Outline: SSARegression6.2, Verify the function of Enable Appointment Without VIN Setting function on PROD2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    When  I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "OMM Admin" Application on 'Main Page'
    And I goto "Admin" tab and select "Online Appointment Schedule" on 'OMM Admin' Page
    And I set Configure data on 'Admin-Online Appointment Schedule' page
      | FieldName                                  | Value            |
      | Enable Appointment Without VIN in OSS      | yes              |
    And I click "Save" button on 'OMM Admin' Page
    Examples: below data set for data driven
      |URL          |DMS   |DealerName           |DealerCode    |VIN               |
      |PROD2DFX     |Dummy |Audi of anytown usa  |              |WAUVGAFR7EA019544 |

  @SSA_Regression6.3_DisableAppointmentWithoutVIN-PROD2
  Scenario Outline: SSARegression6.3, Verify the function of disable Appointment Without VIN Setting function on PROD2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    When  I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "OMM Admin" Application on 'Main Page'
    And I goto "Admin" tab and select "Online Appointment Schedule" on 'OMM Admin' Page
    And I set Configure data on 'Admin-Online Appointment Schedule' page
      | FieldName                                  | Value           |
      | Enable Appointment Without VIN in OSS      | no              |
    And I click "Save" button on 'OMM Admin' Page
    Examples: below data set for data driven
      |URL          |DMS   |DealerName           |DealerCode    |VIN               |
      |PROD2DFX     |Dummy |Audi of anytown usa  |              |WAUVGAFR7EA019544 |

  @SSA_Regression07_BookApptNoMakeModelByVIN-PROD2
  Scenario Outline: SSARegression07, Verify the function of book a new appointment without Make Model by VIN in PROD2
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
      |PROD2SSAAUDIR |Dummy   |Anytown USA Automall  |1G6DN57P490168257  |42890   |4363        |



