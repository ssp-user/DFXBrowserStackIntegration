@MCI07 @Regression
Feature: MCI07_VinlessNoCheckin.feature
  
  Scenario Outline:  Verify that user is able to see error message for vinless checkin
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Appointment Manager" application from the 'Home' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I click "APPOINTMENT FOR A NEW CUSTOMER" button on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | First name           | DfxQA                  |
      | Last name            | Test                   |
      | Authorized Phone     |                        |
      | Authorized Text      |                        |
      | Authorized E-mail    |                        |
      | Contact language     | Random                 |
      | Pre-Correspondence   |                        |
      | Default phone        | 9051234567             |
      | Cell phone           | 4164161234             |
      | Customer E-mail      | testdealerfx@gmail.com |
    And I set vehicle data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | Make                 | 2                      |
      | Year                 | Random                 |
      | Model                | Random                 |
      | Transmission         | Random                 |
      | Engine               | Random                 |
      | Drive train          | Random                 |
    And I set vehicle data on 'CLIENT & VEHICLE' page
      | FieldName            | Value                  |
      | Odometer             | <Odometer>             |
      | License plate        |                        |
      | Color                |                        |
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    And I click "GENERATE MENU" button on 'SERVICES' page in AM
    And I click "OPEN MENU" to verify "Scheduled Maintenance Guide" in PDF generated on 'SERVICE' page in AM
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
    
    ## MCI Steps
    
    Given I launch browser "chrome" in "GUI" and open "<URL3>"
    And I enter confirmation code for the appointment on 'LOGIN' page
    Then I should click on continue button and see not have enough info message on 'LOGIN' page
   
    Examples: below data set for data driven
      |URL          |DMS       |DealerName           |ServiceAdvisor |Odometer |URL3        |
      |TEST2ALAUDI  |Dummy     |anytown usa automall |1              |35001    |TEST2MCIAUDI|
      
      