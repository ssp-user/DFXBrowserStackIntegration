@SD10 @rsAppointment
Feature: SD10_CurrentDayAppointment, Current Day Appointment
  Description : The purpose of this feature file is:
  1)to verify that current day appointment is shown if it has a VIN and there is no existing service lane in the Vehicle Queue
  2)to verify that an appointment is Not shown when it's not scheduled for the current day
  3)to verify that current day appointment is Not shown there is an existing service lane in the Vehicle Queue

  Scenario Outline: to verify that current day appointment is shown if it has a VIN and there is no existing service lane in the Vehicle Queue
    #Search by VIN on the Vehicle Queue
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    #Login as Dealer Admin
    When I login the Application as User "DarrenNissanD" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "Nissan of Anytown USA" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    #delete SL if exists
    When I search "<VIN>" on 'VEHICLE QUEUE' page
    And I "delete" the service lane "<VIN>" status on 'SEARCH' page
    And I click "Back" button on Service Dashboard 'SEARCH' page
    Then I don't see service lane with "<VIN>" on 'VEHICLE QUEUE' page
    #Create an appointment for the current day through appointment manager
    When I switch back to page title "Dealer-FX Application Framework"
    And I launch "Appointment Manager" Application on 'Main Page'
    And I switch back to page title "Appointment Manager"
    And I click "CREATE" button on 'DASHBOARD' page
    And I input "<VIN>" on the search field on 'Lookup' page
    And I click "VIN" button on 'Lookup' page
    And I click "plus" button on 'CUSTOMER SEARCH' page
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'HISTORY' page in AM
    And I add following concerns on 'SERVICE' page
      |Concern name       |
      |AC/Heating Concern |
      |Battery Concern    |
    And I click "NEXT" button on 'SERVICES' page in AM
    And I click "first" available time on "today" on 'TIME & ADVISOR' page
    And I get "day" and "time" for the appointment on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    And I click "CREATE" button on 'SUMMARY' page
    And I switch back to page title "Service Dashboard"
    Then I verify appointment with "<VIN>" on 'VEHICLE QUEUE' page
      |Verify term|Expected result|
      |appointment|show           |
      |day        |match          |
      |time frame |match          |
    #Re-schedule the appointment for the current day for a different time
    And I switch back to page title "Appointment Manager"
    And I click appointment with name on 'DASHBOARD' page
    And I click "EDIT" button for the Appointment on 'DASHBOARD' page
    And I goto "TIME & ADVISOR" tab on 'SUMMARY' page
    ###And I goto "TIME & ADVISOR" tab in AM -- same as above
    And I click "first" available time on "today" on 'TIME & ADVISOR' page
    And I get "day" and "time" for the appointment on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    And I click "SAVE" button on 'SUMMARY' page
    And I switch back to page title "Service Dashboard"
    Then I verify appointment with "<VIN>" on 'VEHICLE QUEUE' page
      |Verify term|Expected result|
      |appointment|show           |
      |day        |match          |
      |time frame |match          |
    #Re-schedule the appointment for a different day
    And I switch back to page title "Appointment Manager"
    And I click appointment with name on 'DASHBOARD' page
    And I click "EDIT" button for the Appointment on 'DASHBOARD' page
    ##And I goto "TIME & ADVISOR" tab on 'SUMMARY' page -- same as below
    And I goto "TIME & ADVISOR" tab in AM
    And I click "next" available time on "tomorrow" on 'TIME & ADVISOR' page
    And I get "day" and "time" for the appointment on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    And I click "SAVE" button on 'SUMMARY' page
    And I switch back to page title "Service Dashboard"
    Then I verify appointment with "<VIN>" on 'VEHICLE QUEUE' page
      |Verify term|Expected result|
      |appointment|not show       |
    #Re-schedule an appointment for the current day (again)
    And I switch back to page title "Appointment Manager"
    And I click appointment with name on 'DASHBOARD' page
    And I click "EDIT" button for the Appointment on 'DASHBOARD' page
    ##And I goto "TIME & ADVISOR" tab on 'SUMMARY' page -- same as below
    And I goto "TIME & ADVISOR" tab in AM
    And I click "first" available time on "today" on 'TIME & ADVISOR' page
    And I get "day" and "time" for the appointment on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    And I click "SAVE" button on 'SUMMARY' page
    And I switch back to page title "Service Dashboard"
    Then I verify appointment with "<VIN>" on 'VEHICLE QUEUE' page
      |Verify term|Expected result|
      |appointment|show           |
      |day        |match          |
      |time frame |match          |
    #Drag down the appointment
    When I drag down the appointment with "<VIN>" on 'VEHICLE QUEUE' page
    Then I verify appointment with "<VIN>" on 'VEHICLE QUEUE' page
      |Verify term|Expected result|
      |appointment|not show       |
    Then I should see service lane with <VIN> with "Arrived" status on 'VEHICLE QUEUE' page
    Examples: below data set for data driven
      |URL      |DealerCode |Odometer   |VIN              |
      |TST2NISS |DONOTCHANGE|50000      |KNMAT2MV9HP601424|