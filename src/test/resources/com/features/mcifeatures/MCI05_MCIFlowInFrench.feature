@MCI05  @Regression
Feature: MCI05_MCIFlowInFrench
  Description: Verify Mobile Check-In function work properly after appointment is booked by Appointment Manager

  Scenario Outline: Verify Mobile Check-In function work properly after appointment is booked by Appointment Manager in French
    Given I launch browser "chrome" in "GUI" and open "<URL1>"
    And I capture Vehicle Identification Number
    Given I launch browser "chrome" in "GUI" and open "<URL2>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Appointment Manager" application from the 'Home' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I click "APPOINTMENT FOR A NEW CUSTOMER" button on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName          | Value                   |
      | First name         | Random                  |
      | Last name          | TestQA                  |
      | Authorized Phone   |                         |
      | Authorized Text    |                         |
      | Authorized E-mail  |                         |
      | Contact language   | English (United States) |
      | Pre-Correspondence |                         |
      | Default phone      |              9051234567 |
      | Cell phone         |              4164161234 |
      | Customer E-mail    | dfxcmbertet@yoo.com     |
    And I enter VIN and decode on 'CLIENT & VEHICLE' page
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click Email as authorized communication
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName       | Value        |
      | Street          | Peter Street |
      | City            | Toronto      |
      | Country         | Canada       |
      | ZIP/Postal Code |        34543 |
      | State           |              |
      | County          |              |
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I verify message "Entered customer information matched other customer(s). What do you want to do?" on 'HISTORY' page in AM
    And I click "CANCEL" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I set promise date as current date on 'TIME & ADVISOR' page    
    And I set promise time "1th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    Then I verify selected items price on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    When I open this Appointment on 'DASHBOARD' page
    Then I capture Appointment Number on 'DASHBOARD' page
    And I signout from AM
    Given I launch browser "chrome" in "GUI" and open "<URL3>"
    And I validate application in French language on 'LOGIN' page    
    And I enter confirmation code for the appointment on 'LOGIN' page
    And I click "CONTINUE" button on 'LOGIN' page
    And I click "NEXT" button on 'HOME' page
    And I scroll to the bottom of the 'HOME' page
    And I click "NEXT" button on 'HOME' page 
    And I scroll to the bottom of the 'HOME' page       
    And I click "Confirm check-in" button on 'HOME' page
    And I mark my signature on thanks message on 'HOME' page
    And I click "Complete check-in" button on 'HOME' page
    Then I view "View my maintenance menu" on 'Thank you' page

    Examples: below data set for data driven
      | URL1         | URL2        | DMS   | DealerName           | ServiceAdvisor | Odometer | URL3           |
      | VINGENERATOR | TEST2ALAUDI | Dummy | Anytown USA Automall |              1 |    35001 | TEST2MCIAUDIFR |
