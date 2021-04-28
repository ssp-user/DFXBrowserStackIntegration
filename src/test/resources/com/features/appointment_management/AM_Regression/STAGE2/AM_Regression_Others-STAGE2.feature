@AM_Regression_Others-STAGE2
Feature: This Test suite is Appointment Management others Regression suite on STAGE2
  Description : to verify Appointment Management function work properly

  @AM_Regression02_AdminSettingChanges-STAGE2
  Scenario Outline: AM_Regression02, Verify the Administration Employees,Departments, Transportation setting changes in AM
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "" or "<DealerCode>" in Dealer Page
    Then I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I change 'User Menu' to "<UserMenu>" on 'DASHBOARD' page
    And I set working hours data on 'Administration-employees' page
      |Day       | Holiday |Opentime | OpentimeAPM | Closetime | ClosetimeAPM  |
      |Sunday    | yes     |         | AM          |           |     PM        |
      |Monday    |         | 8:00    | AM          |  4:30     |     PM        |
      |Tuesday   |         | 7:00    | AM          |  3:30     |     PM        |
      |Wednesday |         | 8:00    | AM          |  4:30     |     PM        |
      |Thursday  |         | 8:00    | AM          |  5:30     |     PM        |
      |Friday    |         | 8:00    | AM          |  4:30     |     PM        |
      |Saturday  |         | 8:00    | AM          |  4:30     |     PM        |
    Then I verify the 'Update count' on 'Administration-employees' page
    When I open the 'Notification Center' on 'Administration' page
    Then I see "The employee schedule has been changed" notification on 'Administration' page
    And I clear and close 'Notification Center' on 'Administration' page
    When I goto "DEPARTMENTS" tab on 'Administration' page in AM
    And I update "+2" Maximum appointments on 'Administration' page in AM
    Then I see maximum appointments updated on 'Administration' page
    When I open the 'Notification Center' on 'Administration' page
    Then I see "The service department has been updated" notification on 'Administration' page
    And I clear and close 'Notification Center' on 'Administration' page
    When I goto "TRANSPORTATION OPTIONS" tab on 'Administration' page in AM
    And I set working hours data on 'Administration-transportation' page
      |Day       | Holiday |Opentime | OpentimeAPM | Closetime | ClosetimeAPM  | Day limit |
      |Sunday    |  yes    |         | AM          |           |     PM        |           |
      |Monday    |         | 8:00    | AM          |  4:30     |     PM        | 0         |
      |Tuesday   |         | 7:00    | AM          |  5:30     |     PM        | +1        |
      |Wednesday |         | 8:30    | AM          |  6:30     |     PM        | +2        |
      |Thursday  |         | 8:00    | AM          |  5:00     |     PM        | 16        |
      |Friday    |         | 8:30    | AM          |  6:30     |     PM        | +2        |
      |Saturday  |         | 8:00    | AM          |  4:30     |     PM        | +1        |
    Then I see 'Day limite' updated on 'Administration-transportation' page
      |Day       | Day limit  |
      |Sunday    | yes        |
      |Monday    | yes        |
      |Tuesday   | yes        |
      |Wednesday | yes        |
      |Thursday  | yes        |
      |Friday    | yes        |
      |Saturday  | yes        |
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS       |UserMenu           |DealerCode       |ServiceAdvisor|Odometer |
      |STG2ALAUDI   |Dummy     |Administration     |408A85US         |1             |35001    |

  @AM_Regression03_DayWeekMonthView-STAGE2
  Scenario Outline: AM_Regression03, Verify the DashBoard able to display properly View layout By DAY,WEEK,MONTH and LIST
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "" or "<DealerCode>" in Dealer Page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "DAY" tab on 'DASHBOARD' page
    Then I verify "DAY" view layout on 'DASHBOARD' page
    When I click "WEEK" tab on 'DASHBOARD' page
    Then I verify "WEEK" view layout on 'DASHBOARD' page
    When I click "MONTH" tab on 'DASHBOARD' page
    Then I verify "MONTH" view layout on 'DASHBOARD' page
    When I click "LIST" tab on 'DASHBOARD' page
    Then I verify "LIST" view layout on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS       |DealerName            |DealerCode       |ServiceAdvisor|Odometer |
      |STG2ALAUDI   |Dummy     |Audi of anytown usa   |408A85US         |1             |35001    |

  @AM_Regression04_OEMversionNissan-STAGE2
  Scenario Outline: AM_Regression04, Verify the AM Version is correct for OEM Nissan
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "DAY" tab on 'DASHBOARD' page
    Then I verify "DAY" view layout on 'DASHBOARD' page
    Then I verify the AM Version number "<AM_VersionNumber>" on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS       |DealerName            |DealerCode   |AM_VersionNumber |CustomerName |Odometer |
      |STG2ALNISS   |Dummy     |Nissan of anytown usa |nissandem2   |2.0.18065.4      |dfx          |54443    |

  @AM_Regression05_OEMversionLexus-STAGE2
  Scenario Outline: AM_Regression05 ,Verify the AM Version is correct for OEM Lexus
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "DAY" tab on 'DASHBOARD' page
    Then I verify "DAY" view layout on 'DASHBOARD' page
    Then I verify the AM Version number "<AM_VersionNumber>" on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL         |DMS   |DealerName           |DealerCode   |AM_VersionNumber |CustomerName     |Odometer |
      |STG2ALLEXU  |Dummy |Lexus of anytown usa |61204US      |2.0.18065.4      |testing dealer-fx|54443    |

  @AM_Regression06_OEMversionToyota-STAGE2
  Scenario Outline: AM_Regression06, Verify the AM Version is correct for OEM Toyota
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "DAY" tab on 'DASHBOARD' page
    Then I verify "DAY" view layout on 'DASHBOARD' page
    Then I verify the AM Version number "<AM_VersionNumber>" on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL         |DMS   |DealerName            |DealerCode  |AM_VersionNumber |CustomerName  |Odometer |
      |STG2ALTOYO  |Dummy |Toyota of anytown USA |21072USA    |2.0.18065.4      |dfx           |54443    |

  @AM_Regression07_OEMversionvolkswagen-STAGE2
  Scenario Outline: AM_Regression07 Verify the AM Version is correct for OEM volkswagen
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "DAY" tab on 'DASHBOARD' page
    Then I verify "DAY" view layout on 'DASHBOARD' page
    Then I verify the AM Version number "<AM_VersionNumber>" on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL         |DMS   |DealerName            |DealerCode  |AM_VersionNumber |CustomerName  |Odometer |
      |STG2ALVOLK  |Dummy |volkswagen of naples  |405024      |2.0.18065.4      |vw test       |54443    |

  @AM_Regression08_OEMversionMitsubishi-STAGE2
  Scenario Outline: AM_Regression08 Verify the AM Version is correct for OEM Mitsubishi
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "DAY" tab on 'DASHBOARD' page
    Then I verify "DAY" view layout on 'DASHBOARD' page
    Then I verify the AM Version number "<AM_VersionNumber>" on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL         |DMS   |DealerName                   |DealerCode   |AM_VersionNumber |CustomerName  |Odometer |
      |STG2ALMITS  |Dummy |Mitsubishi of anytown canada |MITSANYCAN   |2.0.18065.4      |dfx           |54443    |

  @AM_Regression09_FrechLanguage-STAGE2
  Scenario Outline: AM_Regression09, Verify the Appointment Management able to display properly French language menu
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "" or "<DealerCode>" in Dealer Page
    Then I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I change 'User Menu' to "<UserMenu>" on 'DASHBOARD' page
    And I select "French" language on 'DASHBOARD' page
    Then I see "French" language tab on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS       |UserMenu       |DealerCode       |ServiceAdvisor|Odometer |
      |STG2ALAUDI   |Dummy     |Language       |408A85US         |1             |35001    |

  @AM_Regression10_SearchCustomerByVIN&Phone-STAGE2-AUDI
  Scenario Outline: AM_Regression10, Verify the function the application is able to search customer by the VIN or Phone
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And  I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
     ## SEARCH by VIN
    And I search "VIN" with "<VIN1>" on 'CUSTOMER SEARCH' page
    And I see "VIN" is "<VIN1>" on 'CUSTOMER SEARCH' page
    And I search "VIN" with "<VIN2>" on 'CUSTOMER SEARCH' page
    And I see "VIN" is "<VIN2>" on 'CUSTOMER SEARCH' page
    ## SEARCH by PHONE
    And I search "PHONE" with "<PHONE1>" on 'CUSTOMER SEARCH' page
    And I see "PHONE" is "<PHONE1>" on 'CUSTOMER SEARCH' page
    And I search "PHONE" with "<PHONE2>" on 'CUSTOMER SEARCH' page
    And I see "PHONE" is "<PHONE2>" on 'CUSTOMER SEARCH' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS   |DealerName           |VIN1              |VIN2              | PHONE1    | PHONE2     |
      |STG2ALAUDI   |Dummy |Audi of anytown usa  |WA1VGAFPXFA007949 |WAUVGAFR7EA019544 |4162223344 | 4162223344 |

  @AM_Regression11_TransportationOptions-STAGE2-AUDI
  Scenario Outline: AM_Regression11 Verify the function of a new appointment able to update on transportation options on STAGE2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "" or "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And  I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I click "APPOINTMENT FOR A NEW CUSTOMER" button on 'CUSTOMER SEARCH' page
    And I decode "<VIN>" on 'CLIENT & VEHICLE' page
    And I choose "USE MATCHED CUSTOMER" on 'CLIENT & VEHICLE' page if customer existing
    Then I verify decode VIN with "<VIN>" on 'CLIENT & VEHICLE' page
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
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
       ### // loop  verify option  waiter
    When I open the "transportation options" icon on 'DASHBOARD' page
    And I get the transportation options status on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
    And I close the "transportation options" icon on 'DASHBOARD' page
    And I open this Appointment on 'DASHBOARD' page
    And I click "EDIT" button in this Appointment on 'DASHBOARD' page
    And I goto "TIME & ADVISOR" tab on 'SUMMARY' page
    And I choose "waiter" option if available on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    And I input comment "This test for edit  transportation waiter option by QA " on 'SUMMARY' page
    And I click "SAVE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open the "transportation options" icon on 'DASHBOARD' page
    Then I verify the transportation options count on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
           ### //   verify option  Own Ride
    When I open the "transportation options" icon on 'DASHBOARD' page
    And I get the transportation options status on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
    And I close the "transportation options" icon on 'DASHBOARD' page
    And I open this Appointment on 'DASHBOARD' page
    And I click "EDIT" button in this Appointment on 'DASHBOARD' page
    And I goto "TIME & ADVISOR" tab on 'SUMMARY' page
    And I choose "ownRide" option if available on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    And I input comment "This test for edit  transportation OwnRide option by QA " on 'SUMMARY' page
    And I click "SAVE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open the "transportation options" icon on 'DASHBOARD' page
    Then I verify the transportation options count on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
        ### //   verify option  Loaner
    When I open the "transportation options" icon on 'DASHBOARD' page
    And I get the transportation options status on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
    And I close the "transportation options" icon on 'DASHBOARD' page
    And I open this Appointment on 'DASHBOARD' page
    And I click "EDIT" button in this Appointment on 'DASHBOARD' page
    And I goto "TIME & ADVISOR" tab on 'SUMMARY' page
    And I choose "Loaner" option if available on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    And I input comment "This test for edit  transportation Loaner option by QA " on 'SUMMARY' page
    And I click "SAVE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open the "transportation options" icon on 'DASHBOARD' page
    Then I verify the transportation options count on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
           ### //   verify option  Rental
    When I open the "transportation options" icon on 'DASHBOARD' page
    And I get the transportation options status on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
    And I close the "transportation options" icon on 'DASHBOARD' page
    And I open this Appointment on 'DASHBOARD' page
    And I click "EDIT" button in this Appointment on 'DASHBOARD' page
    And I goto "TIME & ADVISOR" tab on 'SUMMARY' page
    And I choose "Rental" option if available on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    And I input comment "This test for edit  transportation Rental option by QA " on 'SUMMARY' page
    And I click "SAVE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open the "transportation options" icon on 'DASHBOARD' page
    Then I verify the transportation options count on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
         ### //   verify option  Shuttle
    When I open the "transportation options" icon on 'DASHBOARD' page
    And I get the transportation options status on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
    And I close the "transportation options" icon on 'DASHBOARD' page
    And I open this Appointment on 'DASHBOARD' page
    And I click "EDIT" button in this Appointment on 'DASHBOARD' page
    And I goto "TIME & ADVISOR" tab on 'SUMMARY' page
    And I choose "Shuttle" option if available on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    And I input comment "This test for edit  transportation Shuttle option by QA " on 'SUMMARY' page
    And I click "SAVE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open the "transportation options" icon on 'DASHBOARD' page
    Then I verify the transportation options count on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
    ### //   verify option  Own Ride
    When I open the "transportation options" icon on 'DASHBOARD' page
    And I get the transportation options status on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
    And I close the "transportation options" icon on 'DASHBOARD' page
    And I open this Appointment on 'DASHBOARD' page
    And I click "EDIT" button in this Appointment on 'DASHBOARD' page
    And I goto "TIME & ADVISOR" tab on 'SUMMARY' page
    And I choose "Pick up" option if available on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    And I input comment "This test for edit  transportation Pickup option by QA " on 'SUMMARY' page
    And I click "SAVE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open the "transportation options" icon on 'DASHBOARD' page
    Then I verify the transportation options count on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
    ### //   verify option  Repeat Repair
    When I open the "transportation options" icon on 'DASHBOARD' page
    And I get the transportation options status on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
    And I close the "transportation options" icon on 'DASHBOARD' page
    And I open this Appointment on 'DASHBOARD' page
    And I click "EDIT" button in this Appointment on 'DASHBOARD' page
    And I choose "Repeat Repair" option on 'SUMMARY' page
    And I input comment "This test for edit transportation RepeatRepair option by QA " on 'SUMMARY' page
    And I click "SAVE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open the "transportation options" icon on 'DASHBOARD' page
    Then I verify the transportation options count on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
    ### //   verify option Special Parts
    When I open the "transportation options" icon on 'DASHBOARD' page
    And I get the transportation options status on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
    And I close the "transportation options" icon on 'DASHBOARD' page
    And I open this Appointment on 'DASHBOARD' page
    And I click "EDIT" button in this Appointment on 'DASHBOARD' page
    And I choose "Special Parts" option on 'SUMMARY' page
    And I input comment "This test for edit  transportation Special Parts by QA " on 'SUMMARY' page
    And I click "SAVE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open the "transportation options" icon on 'DASHBOARD' page
    Then I verify the transportation options count on 'DASHBOARD' page
      |Transportation options : | Waiter | Own Ride | Rental | Loaner |Shuttle | Repeat Repair | Special Parts |
    And I open this Appointment on 'DASHBOARD' page
    And I click "DELETE" button in this Appointment on 'DASHBOARD' page
    Then I do not see the new Appointment on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS       |DealerCode    |VIN                |ServiceAdvisor|Odometer |
      |STG2ALAUDI   |Dummy     |408A85US      |WAUJ8GFFXG1034028  |1             |35001    |

  @AM_Regression12_ChangeDealerShipByUser-STAGE2-AUDI
  Scenario Outline: AM_Regression12,  Verify the function of dealer ship able to  be change when change user name
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "<DealerName1>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I change 'User Menu' to "<UserMenu>" on 'DASHBOARD' page
    And I select the Dealer with "<DealerName2>" or "" in Dealer Page
    Then I see Dealer is "<DealerName2>" on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS       |DealerName1           |DealerName2  |UserMenu          |
      |STG2ALAUDI   |Dummy     |Audi of anytown usa   |Audi Dallas  |Change dealership |

  @AM_Regression14_ChangeDealerTire-STAGE2-AUDI
  Scenario Outline: AM_Regression14 Verify the function of create a new appointment with change Dealer Tire the customer name on STAGE2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And  I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I search "Name" with "<CustomerName>" on 'CUSTOMER SEARCH' page
    And I select "1th" customer on 'CUSTOMER SEARCH' page
    And I click "+" button or "1th" customer vehicle if multiple on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | Customer E-mail      | testdealerfx@gmail.com |
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName      | Value         |
      | Postal Code    | M2J 1M5       |
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    And I goto "TIRES" tab on 'SERVICES' page in AM
    And I click "ADD TIRES" button on 'SERVICES-TIRES' page in AM
    And I click "1th" 'Tire Size' on 'SERVICES-TIRES' page in AM
    And I choose "1th" product catalog on 'SERVICES-TIRES' page in AM
    And I click "ADD TO QUOTE" button on 'SERVICES-TIRES' page in AM
    Then I verify the TIRES quote on 'SERVICES-TIRES' page in AM
    When I goto "TIME & ADVISOR" tab in AM
    And I select "<ServiceAdvisor>" in 'Advisors' section on 'TIME & ADVISOR' page
    And I set promise date next "2" month "The 3th Tuesday" on 'TIME & ADVISOR' page
    And I set promise time "5th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    And I clear and cancel 'Tire comment' on 'SUMMARY' page
    Then I verify 'Tires Price' on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open this Appointment on 'DASHBOARD' page
    And I click "DELETE" button in this Appointment on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS       |DealerName    |CustomerName    |ServiceAdvisor|Odometer |
      |STG2ALAUDI   |Dummy     |Audi naples   |dfx             |1             |35001    |

  @AM_Regression15_CreateNewApptWithoutVIN-STAGE2
  Scenario Outline: AM_Regression15, Verify the function of create a new appointment Without VIN
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I click "APPOINTMENT FOR A NEW CUSTOMER" button on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | First name           | DfxQA                  |
      | Last name            | Test                   |
      | Authorized Phone     |                        |
      | Authorized Text      |                        |
      | Authorized E-mail    |                        |
      | Contact language     | Random                 |
      | Pre-Correspondence   |                        |
      | Default phone        | 9051234567             |
      | Cell phone           | 4164161234             |
      | Customer E-mail      | testdealerfx@gmail.com |
    And I set vehicle data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | Make                 | Random                 |
      | Year                 | Random                 |
      | Model                | Random                 |
      | Transmission         | Random                 |
      | Engine               | Random                 |
      | Drive train          | Random                 |
    And I set vehicle data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | Odometer             | <Odometer>             |
      | License plate        |                        |
      | Color                |                        |
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    And I click "GENERATE MENU" button on 'SERVICES' page in AM
    And I click "OPEN MENU" to verify "Scheduled Maintenance Guide" in PDF generated on 'SERVICE' page in AM
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I select "1" in 'Advisors' section on 'TIME & ADVISOR' page
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
      |URL          |DMS       |DealerName           |Odometer |
      |STG2ALAUDI   |Dummy     |Audi of anytown usa  |35001    |

  @AM_Regression16_CreateNewAppointmentByVIN-STAGE2-AUDI
  Scenario Outline: @AM_Regression16 Verify the function of create a new appointment with the customer name selection by a VIN
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And  I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I click "APPOINTMENT FOR A NEW CUSTOMER" button on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | First name           | Random                 |
      | Last name            | TestQA                 |
      | Authorized Phone     |                        |
      | Authorized Text      |                        |
      | Authorized E-mail    |                        |
      | Contact language     | Random                 |
      | Pre-Correspondence   |                        |
      | Default phone        | 9051234567             |
      | Cell phone           | 4164161234             |
      | Customer E-mail      | testdealerfx@gmail.com |
    And I decode "<VIN>" on 'CLIENT & VEHICLE' page
    And I choose "USE MATCHED CUSTOMER" on 'CLIENT & VEHICLE' page if customer existing
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName      | Value         |
      | Street         | 2131          |
      | City           | North York    |
      | Country        | Canada        |
      | Postal Code    | M2J 1M5       |
      | State          |               |
      | County         |               |
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
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
      |URL          |DMS       |DealerName            |VIN                |ServiceAdvisor|Odometer |
      |STG2ALAUDI   |Dummy     |Audi of anytown usa   |WA1VGAFPXFA007949  |1             |35001    |

  @AM_Regression17_CreateNewApptDifferentDepartment-STAGE2
  Scenario Outline: AM_Regression17,Verify the function  of create a new appointment against different department choice
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I change department to "<Department2>" or "Chrysler QA - Service" on 'DASHBOARD' page
    And I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I click "APPOINTMENT FOR A NEW CUSTOMER" button on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | First name           | DfxQA                  |
      | Last name            | Test                   |
      | Authorized Phone     |                        |
      | Authorized Text      |                        |
      | Authorized E-mail    |                        |
      | Contact language     | Random                 |
      | Pre-Correspondence   |                        |
      | Default phone        | 9051234567             |
      | Cell phone           | 4164161234             |
      | Customer E-mail      | testdealerfx@gmail.com |
    And I set vehicle data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | Make                 | Random                 |
      | Year                 | Random                 |
      | Model                | Random                 |
      | Transmission         | Random                 |
      | Engine               | Random                 |
      | Drive train          | Random                 |
    And I set vehicle data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | Odometer             | <Odometer>             |
      | License plate        |                        |
      | Color                |                        |
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I select "1" in 'Advisors' section on 'TIME & ADVISOR' page
    And I set promise date next "2" month "The 3th Tuesday" on 'TIME & ADVISOR' page
    And I set promise time "5th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    Then I verify selected items price on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I change department to "<Department1>" or "Main Shop - Chrysler QA" on 'DASHBOARD' page
    Then I see this Appointment not on 'DASHBOARD' page
    Examples: below data set for data driven
      |URL          |DMS       |DealerName           |Department1   |Department2   |Odometer |
      |STG2ALAUDI   |Dummy     |Audi of anytown usa  |Main          |Express       |35001    |

  @AM_Regression18.1_SSOAL_NoNamePhoneVIN-STAGE2
  Scenario: AM_Regression18.1,Verify the function login SSO navigation to customer search page without Name,Phone,VIN
  Description: login SSO navigation to customer search page without input customer Name, customer Phone
  and customer VIN
    Given I launch browser "chrome" in "GUI" and open "STG2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 4363                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      |                                      |
      | Customer Phone     |                                      |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I click "APPOINTMENT FOR A NEW CUSTOMER" button on 'CUSTOMER SEARCH' page

  @AM_Regression18.2_SSOAL_NoPhoneVIN-STAGE2
  Scenario: AM_Regression18.2 Verify the function login SSO navigation to customer search page without Phone,VIN
  Description: login SSO navigation to customer search page without input customer Phone and customer VIN
    Given I launch browser "chrome" in "GUI" and open "STG2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 4363                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      | Test                                 |
      | Customer Phone     |                                      |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I select "1th" customer on 'CUSTOMER SEARCH' page

  @AM_Regression18.3_SSOAL_NoNameVIN-STAGE2
  Scenario: AM_Regression18.3, Verify the function login SSO navigation to customer search page without Name,VIN
  Description: login SSO navigation to customer search page without input customer Name and VIN
    Given I launch browser "chrome" in "GUI" and open "STG2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 4363                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      |                                      |
      | Customer Phone     | 7777777777                           |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see "Phone" is "7777777777" on 'CUSTOMER SEARCH' page

  @AM_Regression18.4_SSOAL_NoNamePhone-STAGE2
  Scenario: AM_Regression18.4, Verify the function login SSO navigation to customer search page without Name,Phone
  Description: login SSO navigation to customer search page without input customer Name and Phone
    Given I launch browser "chrome" in "GUI" and open "STG2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 4363                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      |                                      |
      | Customer Phone     |                                      |
      | VIN                | 5TEUU42N86Z285030                    |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see "VIN" is "5TEUU42N86Z285030" on 'CUSTOMER SEARCH' page

  @AM_Regression18.5_SSOAL_InvalidPartnerID-STAGE2
  Scenario: AM_Regression18.5,Verify the  function of error handle when login SSO with 'Invalid DFX Partner ID'
  Description: login SSO with Invalid input and able to see the error message on screen
    Given I launch browser "chrome" in "GUI" and open "STG2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                            |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF |
      | Shared Password    | T9vP2v$c*ybhX2Vk                 |
      | DFX Dealer ID      | 4363                             |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL     |
      | Customer Name      |                                  |
      | Customer Phone     |                                  |
      | VIN                |                                  |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see error message "Invalid DFX Partner ID Error!" on 'SSO' page in AM

  @AM_Regression18.6_SSOAL_InvalidPartnerID-STAGE2
  Scenario: AM_Regression18.6 Verify the  function of error handle when login SSO with Invalid Shared Password
  Description: login SSO with Invalid input and able to see the error message on screen
    Given I launch browser "chrome" in "GUI" and open "STG2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybh                         |
      | DFX Dealer ID      | 4363                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      |                                      |
      | Customer Phone     |                                      |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see error message "Invalid or Expired Security Token Error!" on 'SSO' page in AM

  @AM_Regression18.7_SSOAL_WrongDFXDealerId-STAGE2
  Scenario: AM_Regression18.7 ,Verify the  function of error handle when login SSO with Wrong DFX Dealer Id
  Description: login SSO with Invalid input and able to see the error message on screen
    Given I launch browser "chrome" in "GUI" and open "STG2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 1234                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      |                                      |
      | Customer Phone     |                                      |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see error message "Appointment Ledger is Disabled for Requested Dealer ID Error!" on 'SSO' page in AM

  @AM_Regression18.8_SSOAL_InvalidDFXDealerId-STAGE2
  Scenario: AM_Regression18.8, Verify the  function of error handle when login SSO with Invalid DFX Dealer Id
  Description: login SSO with Invalid input and able to see the error message on screen
    Given I launch browser "chrome" in "GUI" and open "STG2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 436                                  |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      |                                      |
      | Customer Phone     |                                      |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see error message "Unknown DFX Dealer Id Error!" on 'SSO' page in AM

  @AM_Regression18.9_SSOAL_InvalidDFXSystemUser-STAGE2
  Scenario: AM_Regression18.9,Verify the  function of error handle when login SSO with Invalid DFX System User ID
  Description: login SSO with Invalid input and able to see the error message on screen
    Given I launch browser "chrome" in "GUI" and open "STG2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 4363                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUT              |
      | Customer Name      |                                      |
      | Customer Phone     |                                      |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see error message "User Authentication or Authorization Error!" on 'SSO' page in AM

  @AM_Regression19.1_SSOALMit_ValidToday-STAGE2
  Scenario: AM_Regression19.1 Verify the function login Mitsubishi SSO navigation with valid Dealer Code, User ID
  Description: login SSO navigation  with valid Dealer Code, User ID on Today
    Given I launch browser "chrome" in "GUI" and open "STG2ALMITS_SSO"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'Mitsubishi SSO' page in AM
      | FieldName        | Value                   |
      | Dealer Code      | 80430                   |
      | User Id          | 136040                  |
      | Use today        | Yes                     |
      | Use previous day | No                      |
      | Use earlier date | No                      |
    And I click "Test MMSCAN SSO" button on 'Mitsubishi SSO' page in AM
    Then I see "UserName" is "Mark Czovek" on 'Mitsubishi SSO' page in AM

  @AM_Regression19.2_SSOALMit_ValidPreviousday-STAGE2
  Scenario: AM_Regression19.2, Verify the function login Mitsubishi SSO navigation with valid Dealer Code, User ID
  Description: login SSO navigation  with valid Dealer Code, User ID on previous day
    Given I launch browser "chrome" in "GUI" and open "STG2ALMITS_SSO"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'Mitsubishi SSO' page in AM
      | FieldName        | Value                   |
      | Dealer Code      | 80430                   |
      | User Id          | 136040                  |
      | Use today        | No                      |
      | Use previous day | Yes                     |
      | Use earlier date | No                      |
    And I click "Test MMSCAN SSO" button on 'Mitsubishi SSO' page in AM
    Then I see "UserName" is "Mark Czovek" on 'Mitsubishi SSO' page in AM

  @AM_Regression19.3_SSOALMit_ValidEarlierDateday-STAGE2
  Scenario: AM_Regression19.3 Verify the function login Mitsubishi SSO navigation with valid Dealer Code, User ID
  Description: login SSO navigation  with valid Dealer Code, User ID Use earlier date
    Given I launch browser "chrome" in "GUI" and open "STG2ALMITS_SSO"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'Mitsubishi SSO' page in AM
      | FieldName        | Value                   |
      | Dealer Code      | 80430                   |
      | User Id          | 96781                   |
      | Use today        | No                      |
      | Use previous day | No                      |
      | Use earlier date | Yes                     |
    And I click "Test MMSCAN SSO" button on 'Mitsubishi SSO' page in AM
    Then I see error message "Invalid security token error!" on 'Mitsubishi SSO' page in AM

  @AM_Regression19.4_SSOALMit_InvalidCode-STAGE2
  Scenario:AM_Regression19.4 Verify the function Error handle login Mitsubishi SSO navigation with invalid Dealer Code
  User ID
  Description: error Message display when login SSO navigation  with invalid Dealer Code, User ID
    Given I launch browser "chrome" in "GUI" and open "STG2ALMITS_SSO"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'Mitsubishi SSO' page in AM
      | FieldName        | Value                   |
      | Dealer Code      | 80430                   |
      | User Id          | 96781                   |
      | Use today        | Yes                     |
      | Use previous day | No                      |
      | Use earlier date | No                      |
    And I click "Test MMSCAN SSO" button on 'Mitsubishi SSO' page in AM
    Then I see error message "user with no user roles assigned error!" on 'SSO' page in AM

  @AM_Regression19.5_SSOALMit_InvalidCode-STAGE2
  Scenario: AM_Regression19.5 Verify the function Error handle login Mitsubishi SSO navigation with invalid Dealer Code
  Description: error Message display when login SSO navigation  with invalid Dealer Code
    Given I launch browser "chrome" in "GUI" and open "STG2ALMITS_SSO"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'Mitsubishi SSO' page in AM
      | FieldName        | Value                   |
      | Dealer Code      | 8043                    |
      | User Id          | 96781                   |
      | Use today        | Yes                     |
      | Use previous day | No                      |
      | Use earlier date | No                      |
    And I click "Test MMSCAN SSO" button on 'Mitsubishi SSO' page in AM
    Then I see error message "Unknown dealer code error!" on 'SSO' page in AM

  @AM_Regression19.6_SSOALMit_InvalidUser-STAGE2
  Scenario: AM_Regression19.6 Verify the function Error handle login Mitsubishi SSO navigation with invalid User ID
  Description: error Message display when login SSO navigation  with invalid User ID
    Given I launch browser "chrome" in "GUI" and open "STG2ALMITS_SSO"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'Mitsubishi SSO' page in AM
      | FieldName        | Value                   |
      | Dealer Code      | 80430                   |
      | User Id          | 9678                    |
      | Use today        | No                      |
      | Use previous day | Yes                     |
      | Use earlier date | No                      |
    And I click "Test MMSCAN SSO" button on 'Mitsubishi SSO' page in AM
    Then I see error message "Unknown user ID!" on 'SSO' page in AM
