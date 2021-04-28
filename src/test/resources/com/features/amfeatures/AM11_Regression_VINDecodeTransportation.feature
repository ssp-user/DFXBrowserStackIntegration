@AM11 @Regression1 @FCA_Regression 
Feature: AM11_Regression_VINDecodeTransportation. This test suite is Appointment Management Regression suite on TEST2
  Description: to verify Appointment Management function work properly

  @AM_Regression11_TransportationOptions-TEST2-AUDI
  Scenario Outline: AM_Regression11 Verify the function of a new appointment able to update on transportation options on TEST2
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
      |URL          |DMS       |DealerCode          |VIN                |ServiceAdvisor|Odometer |
      |TEST2ALNISS  |Dummy     |ANYUSAAUTOMALL      |WAUDGAFL7BA002147  |1             |35001    |