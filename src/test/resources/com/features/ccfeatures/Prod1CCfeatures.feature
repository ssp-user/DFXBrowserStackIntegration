@FCA_Sanity
Feature: ALL CC TestCases on Test1/Prod1/Pilot1

 
  Scenario Outline: User login to application as BDC Manager
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "chrysler-qa" and password "welcome"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I validate "Customer Connect" as window title of the focussed screen
    And I close the current focussed window on the browser
    And I move back to 'Home' page

    Examples: below data set for data driven
      | URL      | DealerName  | Field3 | Field4 |
      | TEST1DFX | chrysler qa  | Value3 | Value4 |
      
      
          
 
  Scenario Outline: Verify that BDC Agent is able to make task in progress
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "TASKS" tab on top of the page
    And I click "Last Action" dropdown on 'TASKS' page
    And I toggle "- None -" checkbox on 'TASKS' page
    And I click "Last Action" dropdown on 'TASKS' page
    And I select "second" customer on 'TASKS' page
    Then I see 'Task Detail' page
    #Following Actions Will be Performed on "TaskDetail" Page
    When I select "No Answer" from "Select Action" dropdown menu
    And I click "Save" button on 'Task Detail' page
    And I click "Cancel" button on 'Task Detail' page
    #Results can be verified on "Task" page
    And I click "Clear All" button on 'TASKS' page
    #Update below to "No Answer" once data is correct in environment    
    Then I see "Last Action" value changed to "No Answer" on 'TASKS' page
    #And I see "Call Attempts" value changed to "1" on 'TASKS' page
    #Results can be verified on "Dashboard" page
    When I click "DASHBOARD" tab on top of the page
    Then I see "In Progress Tasks" count on 'Dashboard' page increased by "1"

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST1DFX | Chrysler qa| Value3 | Value4 |
      
      


  Scenario Outline: Verify that BDC Agent is able to close task
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "Chrysler-qa" and password "Welcome"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    #  And I click on "TASKS" tab on "Guest Connect" page  // Replaced by below one line action
    And I click "TASKS" tab on top of the page
    And I click "Last Action" dropdown on 'TASKS' page
    And I toggle "- None -" checkbox on 'TASKS' page
    And I click "Last Action" dropdown on 'TASKS' page
    And I select "second" customer on 'TASKS' page
    Then I see 'Task Detail' page
    #Following Actions Will be Performed on "TaskDetail" Page
		#When I select "Other - Closed Call" from "Select Action" dropdown menu
    When I select "Dealership Too Far" from "Select Action" dropdown menu    
    And I click "Save" button on 'Task Detail' page
    And I click "Cancel" button on 'Task Detail' page
    #Results can be verified on "Task" page
    And I click "Clear All" button on 'TASKS' page
    Then I see 'Total Task' count on 'Task' page decreased by "1"
    #Results can be verified on "Dashboard" page
    When I click "DASHBOARD" tab on top of the page
    Then I see "Closed Tasks" count on 'Dashboard' page increased by "1"
    And I see "Calls Made" count on 'Dashboard' page increased by "1"
    And I see "Closed Tasks Campaign" count on 'Dashboard' page increased by "1"
    And I see "Calls Made Campaign" count on 'Dashboard' page increased by "1"

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST1DFX | Chrysler qa | Value3 | Value4 |


  
  Scenario Outline: Verify that BDC Agent is able to export task
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "Chrysler-qa" and password "welcome"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "TASKS" tab on top of the page
    And I select "Missed / No Show Appointments" from "Campaign Dropdown" dropdown menu
    And I click "EXPORT" button on 'TASKS' page
    Then I see Tasklist spread sheet downloaded
    #Results can be verified on downloaded file based on customer name
    And I see all Tasks for campaign included in exported file

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST1DFX | Chrysler QA | Value3 | Value4 |
      
   
 
  Scenario Outline: Verify advisor can see Deferred recommendation and call log history displayed under history tab
   Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "Chrysler-qa" and password "Welcome"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "TASKS" tab on top of the page
    And I select "Delivered Vehicle Follow Up" from "Campaign Dropdown" dropdown menu
    And I select any customer from 'tasks list' where "Assigned" equal "N/A"
    Then I see 'Task Detail' page
    And I get "VIN" on 'Task Detail' page
    And I click "Schedule Appointment" button on 'Task Detail' page
    Then I see 'Appointment Manager' page
    And I see same "VIN" on 'Appointment Manager' page

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST1DFX | Chrysler QA | Value3 | Value4 |
      
      
 

  
  Scenario Outline: Verify BDC Agent sending SMS to customer
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "Chrysler-qa" and password "Welcome"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "MESSENGER" tab on top of the page
    And I search "TEST" in "Search Customer" field on 'MESSENGER' page
    Then I see "Customer name" contains "TEST" displayed on 'MESSENGER' page
    And I click "Customer name" search result contains "TEST" on 'MESSENGER' page
    And I click "modify" button on 'MESSENGER' page
    And I modify "2895363513" in "Cell Phone" field on 'Customer Details' popup
    And I click "Save" button on 'Customer Details' popup
    And I send SMS "TEST random" to customer on 'MESSENGER' page
    And I open page "ReceiveaSMS.com :: Canada" in the new browser tab
    Then I see SMS contains 'TEST random' associated with on 'ReceiveaSMS.com :: Canada' page
    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST1DFX | Chrysler QA | Value3 | Value4 |
      
      
      


  
  Scenario Outline: Verify Dealer admin can see reports load properly
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "REPORTS" tab on top of the page
    Then I see following reports loading properly on 'REPORTS' page
      | Report Name               | Report Column       | Report Value                  |
      | BDC Agent Overview Report | First Name          | 1st row                       |
      | BDC Agent Success Report  | Campaign Name       | Missed / No Show Appointments |
      | Campaign Analysis Report  | # New Tasks         | Missed / No Show Appointments |
      | Campaign Details Report   | # In Progress Tasks | Missed / No Show Appointments |
      | Campaign Summary Report   | # Tasks             | Missed / No Show Appointments |
      

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST1DFX | Chrysler QA           | Value3 | Value4 |
      

 

  
  Scenario Outline: Verify Dealer admin can create campaign
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I validate "Customer Connect" as window title of the focussed screen
    When I click "CAMPAIGNS" tab on top of the page
    And I select "Import Call List" from "Campaign Type" dropdown menu on 'CAMPAIGNS' page
    And I click "New Campaign" button on 'CAMPAIGNS' page
    And I add "Darren" plus random last name in 'Campaign Name' field
    And I add "This is a test cucumber comment" in 'Campaign Description' field
    And I click "Next" button on 'CAMPAIGNS' page
    And I click "Next" button on 'CAMPAIGNS' page
    And I click "Next" button on 'CAMPAIGNS' page
    And I click "Create" button on 'CAMPAIGNS' page
    And I click "OK" button on 'CAMPAIGNS' page
    Then I see 'Darren' plus random last name in the 'Campaign list' on 'CAMPAIGNS' page
    #delete the campaign
    When I click 'Darren' plus random last name in the 'Campaign list' on 'CAMPAIGNS' page
    And I click "DELETE" button on 'CAMPAIGNS' page
    And I click "OK" button on 'CAMPAIGNS' page
    Then I don't see 'Darren' plus random last name in the 'Campaign list' on 'CAMPAIGNS' page
    #verify clicking configure on the campaign type from dashboard page
    When I click "DASHBOARD" tab on top of the page
    And I click "configure" icon on "Missed / No Show Appointments" Campaign
    Then I see "Missed / No Show Appointments" Campaign selected as default
    And I see header menu showing following status
      | Header menu | Expected status |
      | DASHBOARD   | Grey out        |
      | TASKS       | Grey out        |
      | REPORTS     | Grey out        |
      | CAMPAIGNS   | Enabled         |
      | SCRIPTS     | Grey out        |
      | MESSENGER   | Grey out        |
      | SEARCH      | Grey out        |

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST1DFX | Chrysler QA | Value3 | Value4 |

  

 
  Scenario Outline: Verify that BDC Manager is able to reassign Tasks from one BDC Agent to other BDC Agent
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<Username>" and password "<Password>"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "TASKS" tab on top of the page
    #Check Point 1
    Then I see "Tasks List" on 'TASKS' Page
    #Following Ac tions Will be Performed on "Tasks" Page - For Task Assignment
    When I select "5" random task checkboxes from Tasks List
    And I click "Reassign Tasks" button on 'TASKS' page
    And I search agent name "<Agent>" in "Agent Search" field
    And I click "Left Arrow" button to select BDC Agent
    And I click "Apply" button on 'TASKS' page
    And I click "YES" button on pop up message window
    #Check Point 2
    Then I see all "5" randomly selected tasks have "Assigned Agent" name as "<Agent>"
    #Following Actions Will be Performed on "Tasks" Page - For Task Unassignment
    When I select all these "5" randomly selected tasks
    And I click "Reassign Tasks" button on 'TASKS' page
    #    And I keep "Reassign Agent" list empty by not selecting any agent from right side list on "Task Reassignment" page
    And I click "Apply" button on 'TASKS' page
    And I click "YES" button on pop up message window
    #Check Point 3, # After unassignment tasks should have assigned agent name "N/A"
    Then I see all "5" randomly selected tasks have "Assigned Agent" name as "N/A"

    Examples: 
      | URL      | DealerName            | Username      | Password | Agent       |
      | TEST1DFX | Chrysler QA | Chrysler-QA | welcome  | Darren Deng |
      
      
   

 
  Scenario Outline: Verify that BDC Agent is able to see vehicle service history on search page
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "<Username>" and password "<Password>"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
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
      | URL      | DealerName           | Username   | Password   | CustomerName  |
      | TEST1DFX | chrysler qa | automation | dealerfxqa | NATOYA ALEXANDER |

  
      
  

      