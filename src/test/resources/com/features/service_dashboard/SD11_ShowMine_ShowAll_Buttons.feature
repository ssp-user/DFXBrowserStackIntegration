@SD11 @rsShowMine_ShowAll_Buttons
Feature: SD11_ShowMine_ShowAll_Buttons, New 'Show Mine' and 'Show All' functionality on SD Vehicle Queue
  Description: The purpose of this feature file is:
  1) to verify that 'Show Mine' and 'Show All' buttons are always shown independent of the user role or if there are any service lanes linked to the user
  2)'Show Mine' button can be clicked/selected to show the service lanes associated with the user
  3)'Show All' button can be clicked to show all the open service lanes

  @SD11_MTI_Admin
  Scenario Outline: Login as MTI Admin to make sure that 'Show Mine' and 'Show All' buttons on the Vehicle Queue are shown regardless of the fact that there are service lanes associated with the user
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<Username>" and password "<Password>"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    And I login the Application as User "<Username>" and password "<Password>"
    And I select Dealer with "<DealerName>" in Dealer Page
    Then I see following components on 'VEHICLE QUEUE' page
      |Component Name     |
      |'SHOW MINE' button |
      |'SHOW ALL' button  |
    When I click "SHOW MINE" button on 'VEHICLE QUEUE' page
    Then I see "an empty service lane list" on 'VEHICLE QUEUE' page
    And I see current user name is "<MTIAdmin>"
    When I click "SHOW ALL" button on 'VEHICLE QUEUE' page
    Then I see all the service lanes on 'VEHICLE QUEUE' page
    And I click new assigned service lane with "<VIN>" on 'VEHICLE QUEUE' page
    And I click "Delete" button on 'Service Lane Details'
    And I click "DELETE" button on pop-up
    Examples: Login credentials
      |URL     |Username   |Password   |RoleDescription|DealerName           |MTIAdmin        |VIN               |
      |TST2NISS|automation |dealerfxqa |MTI Admin      |Nissan of Anytown    |Automation User |3N1AB7AP7EL654767 |

  @SD11_Service_Advisor
  Scenario Outline: Login as Service Advisor
    #RO push first in Advisor Check-In application
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<Username>" and password "<Password>"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Advisor Check-In" Application on 'Main Page'
    And I decode "<VIN>" on 'Advisor Check-In' page
#    And I deal with Customer Information PopUp in 'CUSTOMER' page
    And I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I click "SERVICES" tab on 'CUSTOMER CONCERN' page
    And I select "MULTI-POINT INSPECTION" service on 'SERVICES' page
    And I click "ASSIGN R.O." tab on 'CUSTOMER CONCERN' page
    And I enter data set in 'ASSIGN R.O.' page
      | FieldName           | Value            |
      | SERVICE ADVISOR     | <ServiceAdvisor> |
      | TAG                 | <TagNumber>      |
      | TECHNICIAN          | <Technician>     |
    And I click "NEXT" button on 'ASSIGN R.O.' page
    And I authorize and sign the agreement on 'AUTHORIZATION' page
    And I click "COMPLETE" button on 'AUTHORIZATION' page
    Then I should see RO push completed
    #Validate following steps in Service Dashboard application
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<Username>" and password "<Password>"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I see following components on 'VEHICLE QUEUE' page
      |Component Name     |
      |'SHOW MINE' button |
      |'SHOW ALL' button  |
    When I click "SHOW MINE" button on 'VEHICLE QUEUE' page
    Then I see new assigned service lane with "<VIN>" and "Service Advisor" name is "<ServiceAdvisor>"
    And I see current user name is "<ServiceAdvisor>"
    When I click new assigned service lane with "<VIN>" on 'VEHICLE QUEUE' page
    And I click "TEAM" button on 'Service Lane Details'
    Then I see "Service Advisor" name is "<ServiceAdvisor>"
    And I see "Service Technician" name is "<Technician>"
    When I click "SHOW ALL" button on 'VEHICLE QUEUE' page
    Then I see all the service lanes on 'VEHICLE QUEUE' page
    When I refresh the page
    Then I see following components on 'VEHICLE QUEUE' page
      |Component Name     |
      |'SHOW MINE' button |
      |'SHOW ALL' button  |
    Examples: Login credentials
      |URL     |Username   |Password   |RoleDescription|DealerName           |VIN               |Odometer |TagNumber|ServiceAdvisor|Technician|
      |TST2NISS|BG2255     |welcome    |Service Advisor|Nissan of Anytown    |3N1AB7AP7EL654767 |21431    |TagSP    |Bob Green     |Ash Tech  |

  @SD11_Technician
  Scenario Outline: Login as Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<Username>" and password "<Password>"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I see following components on 'VEHICLE QUEUE' page
      |Component Name     |
      |'SHOW MINE' button |
      |'SHOW ALL' button  |
    When I click "SHOW MINE" button on 'VEHICLE QUEUE' page
    Then I see new assigned service lane with "<VIN>" and "Service Advisor" name is "<ServiceAdvisor>"
    And I see current user name is "<Technician>"
    When I click new assigned service lane with "<VIN>" on 'VEHICLE QUEUE' page
    And I click "TEAM" button on 'Service Lane Details'
    Then I see "Service Technician" name is "<Technician>"
    When I click "SHOW ALL" button on 'VEHICLE QUEUE' page
    Then I see all the service lanes on 'VEHICLE QUEUE' page
    When I refresh the page
    Then I see following components on 'VEHICLE QUEUE' page
      |Component Name     |
      |'SHOW MINE' button |
      |'SHOW ALL' button  |
    Examples: Login credentials
      |URL     |Username   |Password   |RoleDescription|DealerName           |Technician  |VIN               |ServiceAdvisor  |
      |TST2NISS|ash_tech   |welcome    |Tech           |Nissan of Anytown    |Ash Tech    |3N1AB7AP7EL654767 |Bob Green       |

  @SD11_Parts_Clerk
  Scenario Outline: Login as Parts Clerk
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<Username>" and password "<Password>"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I see following components on 'VEHICLE QUEUE' page
      |Component Name     |
      |'SHOW MINE' button |
      |'SHOW ALL' button  |
    When I click new assigned service lane with "<VIN>" on 'VEHICLE QUEUE' page
    And I click "TEAM" button on 'Service Lane Details'
    And I click "Wrench" button on 'Service Lane Details'
    And I search "<PartsClerk>" on 'Service Lane' details
    And I click "Add Member" button on 'Service Lane Details'
    And I click "Primary Star" button on 'Service Lane Details'
    And I refresh the page
    And I click "SHOW MINE" button on 'VEHICLE QUEUE' page
    Then I see new assigned service lane with "<VIN>" and "Service Advisor" name is "<ServiceAdvisor>"
    And I see current user name is "<PartsClerk>"
    When I click "SHOW ALL" button on 'VEHICLE QUEUE' page
    Then I see all the service lanes on 'VEHICLE QUEUE' page
    When I refresh the page
    Then I see following components on 'VEHICLE QUEUE' page
      |Component Name     |
      |'SHOW MINE' button |
      |'SHOW ALL' button  |
    When I click new assigned service lane with "<VIN>" on 'VEHICLE QUEUE' page
    And I click "TEAM" button on 'Service Lane Details'
    Then I click 'Remove' button after "<PartsClerk>" name to remove the technician from the team
    Examples: Login credentials
      |URL     |Username   |Password   |RoleDescription|DealerName           |VIN               |PartsClerk |ServiceAdvisor |
      |TST2NISS|ash_parts  |welcome    |Parts Clerk    |Nissan of Anytown    |3N1AB7AP7EL654767 |Ash Parts  |Bob Green      |

  @SD11_Dealer_Admin
  Scenario Outline: Login as Dealer Admin
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<Username>" and password "<Password>"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I see following components on 'VEHICLE QUEUE' page
      |Component Name     |
      |'SHOW MINE' button |
      |'SHOW ALL' button  |
    When I click "SHOW MINE" button on 'VEHICLE QUEUE' page
    Then I see "an empty service lane list" on 'VEHICLE QUEUE' page
    And I see current user name is "<DealerAdmin>"
    When I click "SHOW ALL" button on 'VEHICLE QUEUE' page
    Then I see all the service lanes on 'VEHICLE QUEUE' page
    When I refresh the page
    Then I see following components on 'VEHICLE QUEUE' page
      |Component Name     |
      |'SHOW MINE' button |
      |'SHOW ALL' button  |
    Examples: Login credentials
      |URL     |Username   |Password   |RoleDescription|DealerName           |DealerAdmin  |
      |TST2NISS|nissan.da  |welcome    |Dealer Admin   |Nissan of Anytown    |George DA    |