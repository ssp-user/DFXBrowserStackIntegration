@AC06 @Regression1 @test21
Feature: AC06 - The Application Able to PUSH RO in Diversity DMS and Dealer
  Description : Verify the Advisor Check In Push RO properly

  @AC-RegressionOEM-TEST2-AUDI
  Scenario Outline: Verify the Advisor Check In Push RO properly for AUDI <OEM>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    Then I verify the AD Version number "<AD_VersionNumber>" on 'Advisor Check-In' page
    When I search "CUSTOMER NAME" with "<CustomerName>" in 'Advisor Check-In Search' page
    And I click "first" search result on 'Advisor Check-In' page
    And I click "NEXT" button on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    And I verify Mandatory Message Displayed in Fields on 'CUSTOMER' page
    And I verify "NEXT" button is not clickable if 'Odometer' field is empty
    When I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    Then I see "NEXT" button is clickable on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button on 'HISTORY' page
    And I click 'FACTORY SCHEDULED MAINTENANCE' on 'SERVICES' page
    And I keep Pre-Selected or "check" "multi-point inspection" or first in 'FACTORY SCHEDULED MAINTENANCE' Tab if No Pre-selected
    And I click "OTHER FACTORY MAINTENANCE" tab on 'SERVICES' Page
    And I click "REMOVE ALL" button on 'SERVICE-OTHER FACTORY MAINTENANCE' Page
    And I click "RECOMMENDED SERVICES" tab on 'SERVICES' Page
    And I click "REMOVE ALL" button on 'SERVICE-RECOMMENDED SERVICES' Page
    And I click "NEXT" button on 'SERVICES' page
    And I select "SUV/MINIVAN" on 'WALK AROUND' page
    And I click "ALL OK" button on 'WALK AROUND' page
    And I verify front color change correct on 'WALK AROUND' page
    And I click "NEXT" button on 'WALK AROUND' page
    And I set 'PROMISE TIME' "+1" working day "10" hour at "AM " on 'ASSIGN R.O.' page
    And I enter data set in 'ASSIGN R.O.' page
      | FieldName       | Value       |
      | SERVICE ADVISOR |             |
      | TRANSPORTATION  |           1 |
      | INSPECTION TYPE |           1 |
      | TAG             | <TagNumber> |
      | TECHNICIAN      |             |
    And I enter "This is a test from dealerfx QA , please ignore" in "COMMENTS" field on 'ASSIGN R.O.' page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    Then I click 'MENU' button verify the "Maintenance" in PDF generated on 'AUTHORIZATION' page
    When I click "QUOTE" button on 'AUTHORIZATION' page
    Then I see 'GENERATE QUOTE' PDF completed
    Then I verify following in PDF generated on 'GENERATE QUOTE'
      | PDF_Name                     | VerifyField            |
      | SAVED CUSTOMER SERVICE QUOTE | Customer Service Quote |
      | SAVED INTERNAL SERVICE QUOTE | Internal Service Quote |
    And I click "CLOSE" button on 'GENERATE QUOTE' PopUp
    And I authorize and sign the agreement on 'AUTHORIZATION' page
    And I click "COMPLETE" button on 'AUTHORIZATION' page
    Then I should see RO push completed
    Then I assert no DMS push error
    And I verify all links PDF generated in RO push
      | PDF_Name                 | VerifyField                |
      | Maintenance Menu PDF     | Estimated Maintenance Cost |
      | Pre-Service Write-Up PDF | Pre-Service Write-Up       |
      | MULTIPOINT INSPECTION    | Inspection                 |
      | Parts Pick List          | Parts pick list            |

    Examples: below data set for data driven
      | URL         | DMS            | DealerName           | OEM  | AD_VersionNumber | CustomerName | Odometer | TagNumber | Application     |
      | TEST2DFX | Audi Framework | anytown usa automall | Audi | 2.9.19035.01     | TEST         |    21431 | TagAu     | ADVISOR CHECKIN |
