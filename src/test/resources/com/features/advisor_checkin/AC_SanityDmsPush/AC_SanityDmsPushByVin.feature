
@AC-SanityDmsPush
Feature: Advisor Check Able to DMS Push RO by VIN decode
  Description : The purpose of this feature verify the AC work proply DMS PUSH RO in TEST2 by vary DMS and VIN

  @AC-SanityDmsPush-Dummy
  Scenario Outline: Advisor Check Able to DMS Push RO by VIN decode for <DMS>
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
    And I verify all links PDF generated in RO push
      |PDF_Name                |VerifyField                |
      |Maintenance Menu PDF    |Estimated Maintenance Cost |
      |Pre-Service Write-Up PDF|Pre-Service Write-Up       |
      |MULTIPOINT INSPECTION   |Inspection                 |
      |Parts Pick List         |Parts pick list            |
    Examples: below data set for data driven
      | URL     | DMS          | DealerName          | DealerCode | Odometer  | TagNumber| VIN               |Advisor  | AD_VersionNumber  |
      | TEST2AD | Dummy        | audi of anytown usa | 408A85US   | 50010     | TagDm    | WAUEFGFF2F1086081 |    1    | 2.9.19035.01   |

  @AC-SanityDmsPush-PBS
  Scenario Outline: Advisor Check Able to DMS Push RO by VIN decode for <DMS>
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
    And I verify all links PDF generated in RO push
      |PDF_Name                |VerifyField                |
      |Maintenance Menu PDF    |Estimated Maintenance Cost |
      |Pre-Service Write-Up PDF|Pre-Service Write-Up       |
      |MULTIPOINT INSPECTION   |Inspection                 |
      |Parts Pick List         |Parts pick list            |
    Examples: below data set for data driven
      | URL     | DMS         | DealerName          | DealerCode | Odometer  | TagNumber| VIN               |Advisor  | AD_VersionNumber  |
      | TEST2AD | PBS         | Beaverton Toyota    | 36047      | 35006     | TagPS    | VNKKTUD39HA082562 |    1    | 2.9.19035.01   |

  @AC-SanityDmsPush-Local_R&R
  Scenario Outline: Advisor Check Able to DMS Push RO by VIN decode for <DMS>
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
    And I verify all links PDF generated in RO push
      |PDF_Name                |VerifyField                |
      |Maintenance Menu PDF    |Estimated Maintenance Cost |
      |Pre-Service Write-Up PDF|Pre-Service Write-Up       |
#      |MULTIPOINT INSPECTION   |Inspection                 |
#      |Parts Pick List         |Parts pick list            |
    Examples: below data set for data driven
      | URL     | DMS         | DealerName          | DealerCode | Odometer  | TagNumber| VIN               |Advisor  | AD_VersionNumber  |
      | TEST2AD | Local R&R   | Lexus of oakville   | 955_LOO    | 35007     | TagLR    | JT3GN86R3X0112794 |    1    | 2.9.19035.01   |

  @AC-SanityDmsPush-CDK
  Scenario Outline: Advisor Check Able to DMS Push RO by VIN decode for <DMS>
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
    And I verify all links PDF generated in RO push
      |PDF_Name                |VerifyField                |
      |Maintenance Menu PDF    |Estimated Maintenance Cost |
      |Pre-Service Write-Up PDF|Pre-Service Write-Up       |
      |MULTIPOINT INSPECTION   |Inspection                 |
      |Parts Pick List         |Parts pick list            |
    Examples: below data set for data driven
      | URL     | DMS          | DealerName          | DealerCode | Odometer  | TagNumber| VIN               |Advisor  | AD_VersionNumber  |
      | TEST2AD | CDK          | Capital Mitsubishi  | 80003      | 35003     |          | JA4JT3AX0BU601908 |    1    | 2.9.19035.01   |

  @AC-SanityDmsPush-DealerTrack
  Scenario Outline: Advisor Check Able to DMS Push RO by VIN decode for <DMS>
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
    And I verify all links PDF generated in RO push
      |PDF_Name                |VerifyField                |
      |Maintenance Menu PDF    |Estimated Maintenance Cost |
      |Pre-Service Write-Up PDF|Pre-Service Write-Up       |
      |MULTIPOINT INSPECTION   |Inspection                 |
      |Parts Pick List         |Parts pick list            |
    Examples: below data set for data driven
      | URL     | DMS          | DealerName          | DealerCode | Odometer  | TagNumber| VIN               |Advisor  | AD_VersionNumber  |
      | TEST2AD | DealerTrack  |                     | 3866       | 35005     |          | 1N4AL21E59C104101 |    2    | 2.9.19035.01   |
