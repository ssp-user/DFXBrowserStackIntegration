@AC-SanityDmsPush-Test2-Debug  @Ignore
Feature: Advisor Check In Morning Health Check on RO push
  Description : The purpose of this feature verify the AC function work proply on Push RO in Prod2 with diversity
  DMS name and VINs Data

  @AC-SanityDmsPush-Test2-DFX-Debug
  Scenario Outline: Advisor check Morning Check on RO push for <DMS>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" and "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    Then I verify the AD Version number "<AD_VersionNumber>" on 'Advisor Check-In' page
    And I verify Mandatory Message Displayed in Fields on 'CUSTOMER' page
    And I verify "NEXT" button is not clickable if 'Odometer' field is empty
    When I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button on 'HISTORY' page
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
      | SERVICE ADVISOR | <Advisor>   |
      | TRANSPORTATION  | 1           |
      | INSPECTION TYPE | 1           |
      | TAG             | <TagNumber> |
      | TECHNICIAN      |             |
    And I enter "This is a test Morning check dealerfx QA , please ignore" in "COMMENTS" field on 'ASSIGN R.O.' page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    And I authorize and sign the agreement on 'AUTHORIZATION' page
    And I click "COMPLETE" button on 'AUTHORIZATION' page
    Then I should see RO push completed
    Then I assert no DMS push error
    And I verify following PDF generated in RO push
      |PDF_Name                |VerifyField1             |VerifyField2|VerifyField3|VerifyField4              |VerifyField5         |VerifyField6|
      |Maintenance Menu PDF    |PREVIOUS                 |CURRENT     |NEXT        |Estimated Maintenance Cost|SIGNATURE AUTHORIZING|Grand Total |
      |Pre-Service Write-Up PDF|Pre-Service Write-Up     |            |            |                          |                     |            |
      |MULTIPOINT INSPECTION   |Inspection               |            |            |                          |                     |            |
      |Parts Pick List         |Parts pick list          |            |            |                          |                     |            |


    Examples: below data set for data driven
      | URL     | DMS          | DealerName          | DealerCode | Odometer  | TagNumber| VIN               |Advisor  | AD_VersionNumber  |
#      | TEST2AD | Dummy        | audi of anytown usa | 408A85US   | 35001     |          | WAUEFGFF2F1086081 |    1    | 2.9.19035.01   |
#      | TEST2AD | CDK          | Capital Mitsubishi  | 80003      | 35003     |          | JA4JT3AX0BU601908 |    1    | 2.9.19035.01   |
#      | TEST2AD | DealerTrack  |                     | 2730       | 999100    |          | 4N2ZN16T32D813485 |    2    | 2.9.19035.01   |
      | TEST2AD | Automate     |                     | 3866       | 35005     |          | 1N4AL21E59C104101 |    2    | 2.9.19035.01   |
