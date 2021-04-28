@SSA_Regression06-OnlineScheduleSetting-TEST2
Feature: This suite is Schedule Service Appointment Online Appointment Setting function Regression suite on Test2
  Description : to verify a set of functions in Schedule Service Appointment system Online Appointment Setting function work properly

  @SSA_Regression6.1_OnlineScheduleEnableRecall-TEST2
  Scenario Outline: SSARegression6.1, Verify the function of Enable Online Schedule Recall Setting function on Test2
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
      |TEST2DFX     |Dummy |Audi of anytown usa  |              |WAUVGAFR7EA019544 |

  @SSA_Regression6.2_EnableAppointmentWithoutVIN-TEST2
  Scenario Outline: SSARegression6.2, Verify the function of Enable Appointment Without VIN Setting function on Test2
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
      |TEST2DFX     |Dummy |Audi of anytown usa  |              |WAUVGAFR7EA019544 |

  @SSA_Regression6.3_DisableAppointmentWithoutVIN-TEST2
  Scenario Outline: SSARegression6.3, Verify the function of disable Appointment Without VIN Setting function on Test2
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
      |TEST2DFX     |Dummy |Audi of anytown usa  |              |WAUVGAFR7EA019544 |
