@MCI06 @Regression
Feature: MCI06_Nocheckin_forappointmenttime_morethan24hours.feature
  Description: Verify that user is not able to check in if appointment time is more than 24 hours
  Scenario Outline: Verify that user is not able to check in if appointment time is more than 24 hours for existing customer with VIN
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Appointment Manager" application from the 'Home' page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    And I click "CREATE" button on 'DASHBOARD' page
    And I search "VIN" with "<VIN>" on 'CUSTOMER SEARCH' page
    And I click "+" button 'under vehicle' on 'CUSTOMER SEARCH' page
    And I set customer data on 'CLIENT & VEHICLE' page
      | FieldName       | Value                  |
      | Customer E-mail | testdealerfx@gmail.com |
    And I enter "<Odometer>" on 'CLIENT & VEHICLE' page
    And I click "NEXT" button on 'CLIENT & VEHICLE' page
    And I click "NEXT" button if on 'HISTORY' page in AM
    ## Verify in Service Page
    And I goto "UNSCHEDULED" tab on 'SERVICES' page in AM
    And I keep Pre-Selected or select "multi-point inspection" on 'SERVICES-UNSCHEDULED' page in AM if No Pre-selected
    And I click "NEXT" button on 'SERVICES' page in AM
    And I select "<ServiceAdvisor>" in 'Advisors' section on 'TIME & ADVISOR' page
    And I set promise date next "2" month "The 3th Tuesday" on 'TIME & ADVISOR' page
    And I set promise time "5th" available timeslot on 'TIME & ADVISOR' page
    And I click "NEXT" button on 'TIME & ADVISOR' page
    Then I verify selected items price on 'SUMMARY' page
    When I input comment "This is a test from dealerfx QA " on 'SUMMARY' page
    And I click "CREATE" button on 'SUMMARY' page
    Then I see a new Appointment created on 'DASHBOARD' page
    When I open this Appointment on 'DASHBOARD' page
    Then I capture Appointment Number on 'DASHBOARD' page
    And I signout from AM
    
    ## MCI Steps
    
    Given I launch browser "chrome" in "GUI" and open "<URL3>"
    And I enter confirmation code for the appointment on 'LOGIN' page
    Then I should click on continue button and see unable to check in message on 'LOGIN' page
   
    
     Examples: below data set for data driven
      |URL          |DMS       |DealerName            |VIN                |ServiceAdvisor|Odometer | URL3         |
      |TEST2ALNISS  |Dummy     |anytown usa automall   |4T1CA38P14U014741 |1             |35001    | TEST2MCIAUDI |

    
