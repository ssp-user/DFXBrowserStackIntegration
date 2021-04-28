# new feature
# Tags: optional
Feature: Routesheet(service dashboard) flow

  @rsflow
  Scenario Outline: [@routesheet01] As a Advisor user, I and open the MPI search page, start inspection and complete the process
  Description :
    this scenario has multiple steps:
    1.delete service lane through SD as dealer admin
    2.create service lane through AC as ADVISOR anc check back in SD
    3.start TI for the same service lane as Technician and check status in SD
    4.complete TI process and check status in SD

    #delete service lane through SD as dealer admin
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "lizannadmin2" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    And I delete service lane with <VIN> on 'VEHICLE QUEUE' page
    Then I don't see service lane with "<VIN>" on 'VEHICLE QUEUE' page
    #create service lane through AC as ADVISOR anc check back in SD
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "Nissanadvisor" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Advisor Check-In" Application on 'Main Page'
    And I decode "<VIN>" on 'Advisor Check-In' page
    And I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I add following customer concerns on 'CONCERN' page
      |Concern name       |
      |AC/Heating Concern |
      |Battery Concern    |
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button on 'HISTORY' page
    And I click "NEXT" button on 'SERVICES' page
    And I select "SUV/MINIVAN" on 'WALK AROUND' page
    And I click "ALL OK" button on 'WALK AROUND' page
    And I click "NEXT" button on 'WALK AROUND' page
    And I update <TAG> on "ASSIGN R.O." page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    And I authorize and sign the agreement on 'AUTHORIZATION' page
    And I click "COMPLETE" button on 'AUTHORIZATION' page
    Then I should see RO push completed
    When I launch browser "chrome" in "GUI" and open "<URL>"
    And I login the Application as User "Nissanadvisor" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "MPI Ready" status on 'VEHICLE QUEUE' page
    #start TI for the same service lane as Technician and check status in SD
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "BGTech" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    And I click service lane with <VIN> on 'VEHICLE QUEUE' page
    And I click "OPEN IN TI" button on 'VEHICLE QUEUE' page
    Then I should see service lane with <VIN> with "MPI Started" status on 'VEHICLE QUEUE' page
    #complete TI process and check status in SD
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "BGTech" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    And I click service lane with <VIN> on 'MPI List' page
    And I 'Mark Section All OK' on 'MPI Details' page
    And I click "Save" button on 'MPI Details' page
    And I click "Mark Completed" button on 'MPI Details' page
    And I launch browser "chrome" in "GUI" and open "<URL>"
    And I login the Application as User "BGTech" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "MPI Completed" status on 'VEHICLE QUEUE' page
    Examples: this is for data driver testing
      |URL        |DMS |DealerName           |DealerCode|Odometer|TAG   |VIN              |
      |TST2NISS   | ?  |Nissan of Anytown    |5522      |60000   |RO99  |5N1ED28Y73C649356|

