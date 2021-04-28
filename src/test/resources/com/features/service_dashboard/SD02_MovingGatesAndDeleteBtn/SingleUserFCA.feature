# new feature
# Tags: optional

@rsflow02-Single-FCA @rsMovingGatesManually @rsflow02Single @rsflow02SingleFCA
Feature: Moving Gates Manually and Delete Button
Description : The purpose of this feature file is:
              1)to verify the ability to advance RS gates manually depending on the user role and the selected dealer settings
              2)to verify the ability to see Delete button and to delete a Service Lane depending on the user role

  @scn0
   Scenario Outline:  [@S1] Testing user's ability to see 'Play' button,advance gates manually and delete SL  

    #delete service lane through SD as dealer admin
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<AdminUser>" and password "<Password>"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    And I search "<VIN>" on 'VEHICLE QUEUE' page
    And I "delete" the service lane "<VIN>" status on 'SEARCH' page
    And I click "Back" button on Service Dashboard 'SEARCH' page
    Then I don't see service lane with "<VIN>" on 'VEHICLE QUEUE' page
    #create service lane through Wiadvisor anc check back in SD
    Given I launch browser "chrome" in "GUI" and open "TST1WI"
    When I login the WiAdvisor as UserID "T0060VL" and Password "Vadlun1?" and Dealer Code "<DealerCode>"
    And I click "Accept" button in 'index' page
    And I click "Search" tab in 'index' page
    And I search VIN "<VIN>" under "Search" tab
    And I select "Customer data" from "VIP" section under "Customer Information" popup
    #And I update Home Number to "6470000000" under "Customer Information" section
    And I update "Odometer" with "60000" in 'Vehicle Information' tab
    And I click "Continue" button in 'WiAdvisorRO' page
    And I enter "This is a test from DealerFX cucumber automaton. Please ignore this." comments under "General Comments" section under "Build RO" tab
    And I "check" "Select All" under "Factory Required Maintenance"
    Then I should see maintenance items "show" under "Selected Service"
    And I click "Continue" button in 'WiAdvisorRO' page
    And I enter "random" under "Tag" field in 'Assign RO' tab
    And I click "Continue" button in 'WiAdvisorRO' page
    And I click "Continue" button in 'WiAdvisorRO' page
    And I click "Continue" button in 'WiAdvisorRO' page
    And I "sign" in 'Finalize RO' tab
    And I click "Complete" button in 'Finalize RO' page
    Then I see following PDF generated on 'Complete Repair Order' pop up
     |PDFName             |Status|
     |pre-service write-up|show  |
     |maintenance menu    |show  |
     |VIP                 |show  |
    #login as the specified user
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<AdminUser>" and password "<Password>"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "Arrived" status on 'VEHICLE QUEUE' page
    When I select service lane for <VIN> on 'VEHICLE QUEUE' page
    Then I should see 'Play' button <playButtonExpectedResult> on the second gate of 'VEHICLE QUEUE' page
    And I see 'Delete' button <delIconExpectedResult> on 'VEHICLE QUEUE' page
    When I click "Play" button on 'VEHICLE QUEUE' page
    Then I see service lane with "<VIN>" has expected status on 'VEHICLE QUEUE' page
     | sectionName       | expectedStatus  | inspectionType            |
     #| Complete Check-In | Saving Check-In |                           |
     #| Create MPI        | MPI Ready       | Nissan Vehicle Inspection |
     | Start MPI         | MPI Started     |                           |
     | Request Parts     | Parts Requested |                           |
     | Complete MPI      | MPI Completed   |                           |
     | Review            | Reviewing       |                           |
     | Work in Progress  | Work in Progress|                           |
     | Finalize          | Delivery Ready  |                           |
    When I click "Delete" button on 'VEHICLE QUEUE' page
    Then I don't see service lane with "<VIN>" on 'VEHICLE QUEUE' page
    Examples: user role data and what they expected to see
    |URL      |DealerURL|User         |Password  |RoleDescription|DealerName    |DealerCode  |VIN              |playButtonExpectedResult|delIconExpectedResult|AdminUser   |AdvisorUser  |
    |TEST1DFX|TST2NISS |automation   |welcome   |Dealer Admin   |Chrysler QA   |99970       |2C3CCAKG8EH273277|visible                 |visible              |DarrenD     |DarrenA      |




