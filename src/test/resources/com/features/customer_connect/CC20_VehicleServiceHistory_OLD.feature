@CC_Testcase_020
Feature: CC20_VehicleServiceHistory, Check Vehicle service history

  @CC20_SearchPage @Regression @Smoketest
  Scenario Outline: Verify that BDC Agent is able to see vehicle service history on search page
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<Username>" and password "<Password>"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Customer Connect" Application on 'Main Page'
    And I click "SEARCH" tab on top of the page
    # Check Point 1
    Then I see 'SEARCH' page
    # Following Actions Will be Performed on "Search" Page to search csutomer
    When I search "<CustomerName>" on 'SEARCH' page
    And I click "Name" button on 'SEARCH' page
    # Check Point 2
    Then I see "Name" results matched "<CustomerName>"
    And I see "VIN" from search result on 'SEARCH' page
    When I click ">" button on 'SEARCH' page
    And I click "SERVICE HISTORY" on expanded view
    And I click "first i" on expanded view
    # Check Point 3
    Then I see "Service History" window opened on 'SEARCH' page
    And I see 'Service History' window have correct "VIN" under 'Vehicle Details'
    Examples:
      | URL            | DealerName           | Username | Password | CustomerName       |
      | TEST2DFX      | Anytown USA Automall | SA.4363  | welcome  | BESSY ALEXANDER    |
#      | STAGE2DFX       | Anytown USA Automall | SA.4363  | welcome  | BESSY ALEXANDER    |
#      | PROD2          | Anytown USA Automall | SA.4363  | welcome  | BESSY ALEXANDER    |
      | TEST1DFX      | Chrysler QA          | advisorx | welcome  | LAURENT RAM        |
#      | PROD1          | Chrysler QA          | advisorx | welcome  | LAURENT RAM        |
#      | PILOT1         | Chrysler QA          | advisorx | welcome  | BRENDAN NAVARRO    |

  @CC20_TaskDetailsPage @Regression @Smoketest
  Scenario Outline: Verify that BDC Agent is able to see vehicle service history on Task details page
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<Username>" and password "<Password>"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Customer Connect" Application on 'Main Page'
    And I click "SEARCH" tab on top of the page
    # Check Point 1
    Then I see 'SEARCH' page
    # Following Actions Will be Performed on "Search" Page to search csutomer
    When I search "<CustomerName>" on 'SEARCH' page
    And I click "Name" button on 'SEARCH' page
    # Check Point 2
    Then I see "Name" results matched "<CustomerName>"
    And I see "VIN" from search result on 'SEARCH' page
    When I click ">" button on 'SEARCH' page
    And I click "TASKS" on expanded view
    And I click "first Campaign" on expanded view
    # Check Point 3
    Then I see 'Task Details' page
    And I see "VIN" on 'Task Details' page
    When I click "SERVICE HISTORY" on 'Task Details' page
    And I click "first i" on 'Task Details' page
    # Check Point 4
    Then I see "Service History" window opened on 'SEARCH' page
    And I see 'Service History' window have correct "VIN" under 'Vehicle Details'
    Examples:
      | URL            | DealerName           | Username | Password | CustomerName       |
      | TEST2DFX      | Anytown USA Automall | SA.4363  | welcome  | BESSY ALEXANDER    |
#      | STAGE2DFX       | Anytown USA Automall | SA.4363  | welcome  | BESSY ALEXANDER    |
#      | PROD2          | Anytown USA Automall | SA.4363  | welcome  | BESSY ALEXANDER    |
      | TEST1DFX      | Chrysler QA          | advisorx | welcome  | LAURENT RAM        |
#      | PROD1          | Chrysler QA          | advisorx | welcome  | LAURENT RAM        |
#      | PILOT1         | Chrysler QA          | advisorx | welcome  | BRENDAN NAVARRO    |

  @CC20_SearchPage_MTIAdmin @Regression @Smoketest
  Scenario Outline: Verify that BDC Agent is able to see vehicle service history on search page
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<Username>" and password "<Password>"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Customer Connect" Application on 'Main Page'
    And I login the Application as User "<Username>" and password "<Password>"
    And I select Dealer with "<DealerName>" in Dealer Page
    And I click "SEARCH" tab on top of the page
    # Check Point 1
    Then I see 'SEARCH' page
    # Following Actions Will be Performed on "Search" Page to search csutomer
    When I search "<CustomerName>" on 'SEARCH' page
    And I click "Name" button on 'SEARCH' page
    # Check Point 2
    Then I see "Name" results matched "<CustomerName>"
    And I see "VIN" from search result on 'SEARCH' page
    When I click ">" button on 'SEARCH' page
    And I click "SERVICE HISTORY" on expanded view
    And I click "first i" on expanded view
    # Check Point 3
    Then I see "Service History" window opened on 'SEARCH' page
    And I see 'Service History' window have correct "VIN" under 'Vehicle Details'
    Examples:
      | URL            | DealerName           | Username | Password | CustomerName       |
      | STG2NISS       | Anytown USA Automall | SA.4363  | welcome  | BESSY ALEXANDER    |

  @CC20_TaskDetailsPage_MTIAdmin @Regression @Smoketest
  Scenario Outline: Verify that BDC Agent is able to see vehicle service history on Task details page
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<Username>" and password "<Password>"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Customer Connect" Application on 'Main Page'
    And I login the Application as User "<Username>" and password "<Password>"
    And I select Dealer with "<DealerName>" in Dealer Page
    And I click "SEARCH" tab on top of the page
    # Check Point 1
    Then I see 'SEARCH' page
    # Following Actions Will be Performed on "Search" Page to search csutomer
    When I search "<CustomerName>" on 'SEARCH' page
    And I click "Name" button on 'SEARCH' page
    # Check Point 2
    Then I see "Name" results matched "<CustomerName>"
    And I see "VIN" from search result on 'SEARCH' page
    When I click ">" button on 'SEARCH' page
    And I click "TASKS" on expanded view
    And I click "first Campaign" on expanded view
    # Check Point 3
    Then I see 'Task Details' page
    And I see "VIN" on 'Task Details' page
    When I click "SERVICE HISTORY" on 'Task Details' page
    And I click "first i" on 'Task Details' page
    # Check Point 4
    Then I see "Service History" window opened on 'SEARCH' page
    And I see 'Service History' window have correct "VIN" under 'Vehicle Details'
    Examples:
      | URL            | DealerName           | Username | Password | CustomerName       |
      | STG2NISS       | Anytown USA Automall | SA.4363  | welcome  | BESSY ALEXANDER    |