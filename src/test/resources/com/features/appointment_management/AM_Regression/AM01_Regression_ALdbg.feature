@AM_Regression-Debug
Feature: This Test suite is Appointment Management Regression suite on Test2
  Description : to verify Appointment Management function work properly

  @AM_Regression01_AppointmentCreateAndDelete-TEST2-DMS
  Scenario Outline: AM_Regression01 FOR <DMS>, Verify the function of create a new appointment and deletion with the customer name selection on Test2
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
    And I set promise date next "2" month "The 3th Tuesday" on 'TIME & ADVISOR' page
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
      |TEST2AL      |Dummy        |              |408A85US      |dfx             |1             |35001    |
      |TEST2AL      |R&R          |audi naple    |405A52        |dfx             |1             |35002    |
      |TEST2AL      |CDK          |              |80003         |test customer   |1             |35003    |
#    ##  |TEST2AL      |DealerTrack  |              |3090          |dfx             |1             |35004    |
#      |TEST2AL      |Automate     |              |3866          |testing         |1             |35005    |
#      |TEST2AL      |PBS          |              |80130         |test             |1             |35000    |
     ######## Stage 2
#      |STAGE2AL     |Dummy        |              |408A85US      |dfx             |1             |35001    |
#      |STAGE2AL     |R&R          |audi naple    |405A52        |dfx             |1             |35002    |
#      |STAGE2AL     |CDK          |              |80003         |test customer   |1             |35003    |
#    ## |STAGE2AL     |DealerTrack  |              |3090          |dfx             |1             |35004    |
#      |STAGE2AL     |Automate     |              |3866          |testing         |1             |35005    |
#      |STAGE2AL     |PBS          |              |80130         |test           |1             |35000    |
     ######## PROD 2
      |PROD2AL      |Dummy        |                      |408A85US      |dfx             |1             |35001    |
     ## |PROD2AL      |R&R          |audi naple            |405A52        |dfx             |1             |35002    |
      |PROD2AL      |CDK          |                      |80003         |test customer   |1             |35003    |
     ## |PROD2AL      |DealerTrack  |                      |3090          |dfx             |1             |35004    |
     ## |PROD2AL      |Automate     |                      |3866          |testing         |1             |35005    |
      |PROD2AL      |PBS          |                      |80130         |test            |1             |35000    |
      |PROD2AL      |Serti        |Belleville Mitsubishi |80470         |dfx             |1             |35000    |