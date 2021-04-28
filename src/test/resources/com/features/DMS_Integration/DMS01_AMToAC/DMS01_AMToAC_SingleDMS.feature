
Feature: Test flow from Appointment manager to Advisor-Checkin

  @DMS @DMS01 @DMS01SINGLEDMS
  Scenario Outline:  Test flow from Appointment manager to Advisor-Checkin
  Description : The purpose of this feature file is:
  1)create appointment from appointment manager
  2)using the same appointment and complete RO push in advisor checkin

    #open appointment manager and create appointment
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    #Login as MTI Admin
    When I login the Application as User "automation" and password "dealerfxqa"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Appointment Manager" Application on 'Main Page'
    And I click "CREATE" button on 'DASHBOARD' page
    And I input "<VIN>" on the search field on 'Lookup' page
    And I click "VIN" button on 'Lookup' page
    And I click "plus" button on 'CUSTOMER SEARCH' page
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName         | Value         |
      | First name        | random        |
      | Last name         | Cucumber      |
      | Default phone     | random        |
      | Street            | 80 Tiverton ct|
      | City              | Markham       |
      | ZIP/Postal Code   | L3R 0G4       |
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I goto "SERVICES" tab on 'HISTORY' page in AM
    And I add "first" concern with "0.00" price on 'SERVICES' page in AM
    And I click "NEXT" button on 'SERVICES' page in AM
    And I click "first" available time on "first" business day after "3" months on 'TIME & ADVISOR' page
    And I get "day" and "time" for the appointment on 'TIME & ADVISOR' page
    And I select "<ServiceAdvisor>" in 'Advisors' section on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    And I input comment "This is test comment from Dealerfx QA" on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    #check the appointment in the advisor check-in
    And I switch back to page title "Dealer-FX Application Framework"
    And I launch "Advisor Check-In" Application on 'Main Page'
    And I switch back to page title "Advisor Check-In"
    And I decode "<VIN>" on 'Advisor Check-In' page
    And I click "USE APPOINTMENT" button on "EXISTING APPOINTMENT DETAILS" popup on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button on 'HISTORY' page
    And I click "NEXT" button on 'SERVICES' page
    And I select "SUV/MINIVAN" on 'WALK AROUND' page
    And I click "ALL OK" button on 'WALK AROUND' page
    And I click "NEXT" button on 'WALK AROUND' page
    #And I update <TAG> on "ASSIGN R.O." page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    And I authorize and sign the agreement on 'AUTHORIZATION' page
    And I click "COMPLETE" button on 'AUTHORIZATION' page
    Then I should see RO push completed
    #delete the appointment in appointment manager before the time frame
    When I switch back to page title "Appointment Manager"
    And I click "CREATE" button on 'DASHBOARD' page
    And I input "<VIN>" on the search field on 'Lookup' page
    And I click "VIN" button on 'Lookup' page
    And I click "Delete" icon on the "Booked" appointment on 'Lookup' page
    Examples: below data set for data driven
     |URL       |DealerName        |DealerCode |Odometer   |TAG   |VIN              |ServiceAdvisor|
     |TEST2DFX |Capital Mitsubishi|80003      |50000      |random|3N1CE2CP3FL385832| first        |

