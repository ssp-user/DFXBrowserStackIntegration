@SD12 @rsDashboard
Feature: SD12_ExceptionBoard, Exception Dashboard in SD
  Description: The purpose of this feature file is an Exception Dashboard in SD

  @SD12_Scenario1
  Scenario Outline:  [@scn1] Login as Service Advisor to SD and open Exceptional Dashboard
    #Login as MTI Admin
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "bg2255" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    And I login the Application as User "bg2255" and password "welcome"
    And I select Dealer with "<DealerName>" in Dealer Page
    And I click "DASHBOARD" button on 'VEHICLE QUEUE' page
    Then I see following components on 'DASHBOARD' page
      |Component Name                 |
      |'SHOW MINE' button             |
      |'SHOW ALL' button              |
      |'All Departments' dropdown     |
      |'Missed Appointments' tile     |
      |'MPI Declined By Advisor' tile |
      |'Carry Over' tile              |
      |'Waiters' tile                 |
      |'Write up not Complete' tile   |
      |'MPI not Started' tile         |
      |'Awaiting Parts Estimate' tile |
      |'Quarter Time' tile            |
      |'Promise Time' tile            |
      |'Ready for Delivery' tile      |
    And I see "Number of Matched Lanes" on "MPI not Started" tile on 'DASHBOARD' page
    When I click "MPI not Started" tile on 'DASHBOARD' page
    And I click "SHOW LANE" button on "MPI not Started" tile
    Then I see current lane header is "MPI not Started" with 'Number of Matched Lanes' on 'VEHICLE QUEUE' page
    And I see 'Number of Matched Lanes' of service lanes displayed on 'VEHICLE QUEUE' page
    Examples: this is for data driver testing
      |URL      |DealerName             |
      |TEST2DFX|Nissan of Anytown USA  |