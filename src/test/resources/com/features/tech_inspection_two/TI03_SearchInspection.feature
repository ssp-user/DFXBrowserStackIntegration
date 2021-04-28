@TI03 @TI
Feature: Technician Inspection, Verify Search function in Dashboard by load search data set
  As a user, I can search properly result in the Dashboard or search popup window
  Description : The purpose of this feature is to test search function scenario

  # This is the example test Background
  Background: I am on the Login Page in Test2 environment
  Description: The is to initialize to open the properly URL
    Given I launch browser "chrome" in "GUI" and open "TEST2DFX"
  @search01
  Scenario: [@search01] I can see the search result in first line dashboard by load
  test data provided.
  Description:  This is positive test
    # This is the example test @Seacrh01
    When I login the Application as User "automation" and password "dealerfxqa"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "audi of anytown usa" and "408A85US" on DFX Page
    Then I launch "Technician Inspection" Application on 'Main Page'
    When I login the Application as User "DarrenAd" and password "Welcome"
    And I select the Dealer with "audi of anytown usa" and "408A85US" in Dealer Page
    And I launch 'Search popup panel' in Inspection dashboard
    And I load search data set in search popup panel
      | FieldName       | Value                     |
      | By VIN          |                           |
      | Tag             |                           |
      | Last Name       | GatesABG                  |
      | RO              | RO56                      |
      | Status          | Created,Started,Completed |
      | Created By      | ALL SELECTED              |
      | Assigned To     |                           |
      | From Date Year  | -3                        |
      | From Date Month | -2                        |
      | From Date Day   |                           |
      | To Date Year    |                           |
      | To Date Month   |                           |
      | To Date Day     |                           |
    Then I see the record on the first line in dashboard
      | FieldName     | Value         |
      | VIN           |               |
      #| TAG               | TAG56                             |
      | CUSTOMER NAME | Bill GatesABG |
      | RO            | RO56          |
      #| INSPECTION STATUS | CREATED                           |

  @search02
  Scenario: [@search02] I can see the 'No matching records found' message shown in
  the search popup windows by load test data provided
  Description :  This is a negative test
    # This is the example test @Seacrh02
    When I login the Application as User "automation" and password "dealerfxqa"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "audi of anytown usa" and "408A85US" on DFX Page
    Then I launch "Technician Inspection" Application on 'Main Page'
    When I login the Application as User "DarrenAd" and password "Welcome"
    And I select the Dealer with "audi of anytown usa" and "408A85US" in Dealer Page
    And I launch 'Search popup panel' in Inspection dashboard
    And I load search data set in search popup panel
      | FieldName       | Value                     |
      | By VIN          | WAUEFGFF2F1086081         |
      | Tag             | Tag480                    |
      | Last Name       | GatesABD                  |
      | RO#             | RO480                     |
      | Status          | Created,Started,Completed |
      | Created By      | ALL SELECTED              |
      | Assigned To     |                           |
      | From Date Year  | -3                        |
      | From Date Month | -2                        |
      | From Date Day   |                           |
      | To Date Year    |                           |
      | To Date Month   |                           |
      | To Date Day     |                           |
    Then I see the message "No matching records found" in the search popup panel


