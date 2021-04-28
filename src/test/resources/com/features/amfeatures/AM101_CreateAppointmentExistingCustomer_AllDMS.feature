@AM101 
Feature: AM01_Regression_AL. This test suite is Appointment Management Regression suite on TEST2
  Description: to verify Appointment Management function work properly test test

@AM_Regression01.1_AppointmentCreateAndDelete-TEST2-Dummy @DMSOnly_AM
  Scenario Outline: AM_Regression01.1 <DMS>, Verify the function of create a new appointment and deletion with the customer name selection on TEST2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
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
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I select "<ServiceAdvisor>" in 'Advisors' section on 'TIME & ADVISOR' page
    And I set promise date next "7" month "The 3th Tuesday" on 'TIME & ADVISOR' page
    And I set promise time "5th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    Then I verify selected items price on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open this Appointment on 'DASHBOARD' page
    And I click "DELETE" button in this Appointment on 'DASHBOARD' page
    Then I do not see the new Appointment on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS          |DealerName    |DealerCode    |CustomerName    |ServiceAdvisor|Odometer |
      |TEST2ALNISS  |Dummy        |              |408A85US      |dfx             |1             |35001    |

  @AM_Regression01.2_AppointmentCreateAndDelete-TEST2-R&R @DMSOnly_AM  
  Scenario Outline: AM_Regression01.2 <DMS>, Verify the function of create a new appointment and deletion with the customer name selection on TEST2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
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
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I select "<ServiceAdvisor>" in 'Advisors' section on 'TIME & ADVISOR' page
    And I set promise date next "7" month "The 3th Tuesday" on 'TIME & ADVISOR' page
    And I set promise time "5th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    Then I verify selected items price on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open this Appointment on 'DASHBOARD' page
    And I click "DELETE" button in this Appointment on 'DASHBOARD' page
    Then I do not see the new Appointment on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS          |DealerName     |DealerCode   |CustomerName             |ServiceAdvisor|Odometer |
      |TEST2ALNISS  |R&R          |Woodfield Lexus|             |TEST RECALLS             |1             |35002    |

  @AM_Regression01.3_AppointmentCreateAndDelete-TEST2-CDK @DMSOnly_AM
  Scenario Outline: AM_Regression01.3 <DMS>, Verify the function of create a new appointment and deletion with the customer name selection on TEST2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
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
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I select "<ServiceAdvisor>" in 'Advisors' section on 'TIME & ADVISOR' page
    And I set promise date next "7" month "The 3th Tuesday" on 'TIME & ADVISOR' page
    And I set promise time "5th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    Then I verify selected items price on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open this Appointment on 'DASHBOARD' page
    And I click "DELETE" button in this Appointment on 'DASHBOARD' page
    Then I do not see the new Appointment on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS          |DealerName    |DealerCode    |CustomerName    |ServiceAdvisor|Odometer |
      |TEST2ALNISS  |CDK          |              |80003         |test customer   |1             |35003    |

#  @AM_Regression01.4_AppointmentCreateAndDelete-TEST2-DealerTrack
#  Scenario Outline: AM_Regression01.4 <DMS>, Verify the function of create a new appointment and deletion with the customer name selection on TEST2
#    Given I launch browser "chrome" in "GUI" and open "<URL>"
#    And I set "<DMS>" to the 'DMS'
#    When I login the Application as User "automation" and password "dealerfxqa"
#    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
#    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
#    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
#    When I click "Next week" button on 'DASHBOARD' page
#    And I select "1th" available weekday on 'DASHBOARD' page
#    And I click "CREATE" button on 'DASHBOARD' page
#    And I search "Name" with "<CustomerName>" on 'CUSTOMER SEARCH' page
#    And I select "1th" customer on 'CUSTOMER SEARCH' page
#    And I click "+" button or "1th" customer vehicle if multiple on 'CUSTOMER SEARCH' page
#    And I set customer data on 'CLIENT & VEHICLE' page
#      | FieldName            | Value                  |
#      | Customer E-mail      | testdealerfx@gmail.com |
#    And I set customer data on 'CLIENT & VEHICLE' page
#      | FieldName      | Value         |
#      | Postal Code    | M2J 1M5       |
#    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
#    And I click "NEXT" button on 'CLIENT & VEHICLE' page
#    And I click "NEXT" button if on 'HISTORY' page in AM
#    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
#    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
#    And I click "NEXT" button on 'SERVICES' page in AM
#    And I select "<ServiceAdvisor>" in 'Advisors' section on 'TIME & ADVISOR' page
#    And I set promise date next "2" month "The 3th Tuesday" on 'TIME & ADVISOR' page
#    And I set promise time "5th" available timeslot on 'TIME & ADVISOR' page
#    And I click "NEXT" button on 'TIME & ADVISOR' page
#    Then I verify selected items price on 'SUMMARY' page
#    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
#    And I click "CREATE" button on 'SUMMARY' page
#    Then I see a new Appointment created on 'DASHBOARD' page
#    When I open this Appointment on 'DASHBOARD' page
#    And I click "DELETE" button in this Appointment on 'DASHBOARD' page
#    Then I do not see the new Appointment on 'DASHBOARD' page
#    And I signout from AM
#    Examples: below data set for data driven
#      |URL          |DMS          |DealerName    |DealerCode    |CustomerName    |ServiceAdvisor|Odometer |
#      |TEST2AL      |DealerTrack  |              |3090          |dfx             |1             |35004    |

  @AM_Regression01.5_AppointmentCreateAndDelete-TEST2-Automate @DMSOnly_AM
  Scenario Outline: AM_Regression01.5 <DMS>, Verify the function of create a new appointment and deletion with the customer name selection on TEST2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
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
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I select "<ServiceAdvisor>" in 'Advisors' section on 'TIME & ADVISOR' page
    And I set promise date next "7" month "The 3th Tuesday" on 'TIME & ADVISOR' page
    And I set promise time "5th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    Then I verify selected items price on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open this Appointment on 'DASHBOARD' page
    And I click "DELETE" button in this Appointment on 'DASHBOARD' page
    Then I do not see the new Appointment on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS          |DealerName    |DealerCode    |CustomerName            |ServiceAdvisor|Odometer |
      |TEST2ALNISS  |Automate     |              |5311          |Test Testing            |1             |35005    |

  @AM_Regression01.6_AppointmentCreateAndDelete-TEST2-PBS @DMSOnly_AM
  Scenario Outline: AM_Regression01.6 <DMS>, Verify the function of create a new appointment and deletion with the customer name selection on TEST2
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log on 'DASHBOARD' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "Next week" button on 'DASHBOARD' page
    And I select "1th" available weekday on 'DASHBOARD' page
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
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I select "<ServiceAdvisor>" in 'Advisors' section on 'TIME & ADVISOR' page
    And I set promise date next "7" month "The 3th Tuesday" on 'TIME & ADVISOR' page
    And I set promise time "5th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    Then I verify selected items price on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open this Appointment on 'DASHBOARD' page
    And I click "DELETE" button in this Appointment on 'DASHBOARD' page
    Then I do not see the new Appointment on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS          |DealerName    |DealerCode    |CustomerName          |ServiceAdvisor|Odometer  |
      |TEST2ALNISS  |PBS          |              |80130         |TESTER 2              |1             |35000    |