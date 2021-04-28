@WIPBSPILOT1 @WIADVISOR
Feature: PBS wiadvisor RO push on PILOT1WI

  Scenario Outline: wiadvisor testing port from testNG
    Description: verify wiadvisor flow with different DMS

    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the WiAdvisor as UserID "T0060VL" and Password "Vadlun1?" and Dealer Code "<DealerCode>"
    And I click "Accept" button in 'index' page
    And I click "Search" tab in 'index' page
    And I search "Customer Name" with "<CustomerName>" in 'Index-Search' tab
    And I select "first" customer from the list in 'Index-Search' tab
    And I select "first" vehicle from customer in 'Index-Search' tab
    And I select "DMS" when 'Customer Information' popup
    And I update "Odometer" with "<Odometer>" in 'Vehicle Information' tab
    And I click "Continue" button in 'WiAdvisorRO' page
    And I click "Continue" button in 'WiAdvisorRO' page
    And I enter "This is a test from DealerFX cucumber automaton. Please ignore this." comments under "General Comments" section under "Build RO" tab
    And I "check" "Select All" under "Factory Required Maintenance"
    Then I should see maintenance items "show" under "Selected Service"
    And I click "Continue" button in 'WiAdvisorRO' page
    And I enter "<TagNumber>" under "Tag" field in 'Assign RO' tab
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
    Examples: below data set for data driven
      |URL      |DMS        |DealerCode|CustomerName|Odometer|TagRequired|TagNumber|
      |PILOT1WI |PBS        |45493     |testing     |86000   |yes        |TEST     |

