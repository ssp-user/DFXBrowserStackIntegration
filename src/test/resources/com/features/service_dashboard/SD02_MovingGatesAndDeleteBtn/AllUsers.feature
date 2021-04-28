@SD02 @rsMovingGatesManually @rsflow02All
Feature: SD02_MovingGatesAndDeleteBtn, Moving Gates Manually and Delete Button
Description : The purpose of this feature file is:
              1)to verify the ability to advance RS gates manually depending on the user role and the selected dealer settings
              2)to verify the ability to see Delete button and to delete a Service Lane depending on the user role

  @scn0
   Scenario Outline:  [@S1] Testing user's ability to see 'Play' button, advance gates manually and delete SL
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
    #create service lane through AC as ADVISOR anc check back in SD
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<AdvisorUser>" and password "<Password>"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Advisor Check-In" Application on 'Main Page'
    And I decode "<VIN>" on 'Advisor Check-In' page
    And I enter "60000" in "ODOMETER" field on 'CUSTOMER' page
    #login as the specified user
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<User>" and password "<Password>"
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
     | Complete Check-In | Saving Check-In |                           |
     | Create MPI        | MPI Ready       | Nissan Vehicle Inspection |
     | Start MPI         | MPI Started     |                           |
     | Request Parts     | Parts Requested |                           |
     | Complete MPI      | MPI Completed   |                           |
     | Review            | Reviewing       |                           |
     | Work in Progress  | Work in Progress|                           |
     | Finalize          | Delivery Ready  |                           |
    When I click "Delete" button on 'VEHICLE QUEUE' page
    Then I don't see service lane with "<VIN>" on 'VEHICLE QUEUE' page
    Examples: user role data and what they expected to see
    |URL     |DealerURL|User         |Password|RoleDescription|DealerName           |DealerCode    |VIN              |playButtonExpectedResult|delIconExpectedResult|AdminUser   |AdvisorUser  |
    |TST2NISS|TST2NISS |nissan.da    |welcome |Dealer Admin   |Nissan of Anytown    |DONOTCHANGE   |5N1AT2MV0HC786935|visible                 |visible              |lizannadmin2|DarrenNissanA|
    |TST2NISS|TST2NISS |BG2255       |welcome |Service Advisor|Nissan of Anytown    |DONOTCHANGE   |5N1AT2MV0HC786935|visible                 |not visible          |lizannadmin2|DarrenNissanA|
    |TST2TOYO|TST2TOYO |allongo      |welcome |Service Advisor|Anytown USA Automall |ANYUSAAUTOMALL|2T2BK1BA1DC205767|not visible             |not visible          |DarrenD     |DarrenA      |
    |TST2NISS|TST2NISS |DarrenNissanT|welcome |Technician     |Nissan of Anytown    |DONOTCHANGE   |5N1ED28Y73C649356|not visible             |not visible          |lizannadmin2|DarrenNissanA|
    |TST2NISS|TST2NISS |ash_parts    |welcome |Parts Clerk    |Nissan of Anytown    |DONOTCHANGE   |5N1ED28Y73C649356|not visible             |not visible          |lizannadmin2|DarrenNissanA|





