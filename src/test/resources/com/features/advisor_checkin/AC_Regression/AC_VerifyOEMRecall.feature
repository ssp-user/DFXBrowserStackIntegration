# new feature
@AC @ADVISORDASHBOARD @ADVISORCHECKIN @ACDUMMY @ACOEMRECALL @ACREGRESSION

Feature: Advisor check in RO push for <OEM>

  Scenario Outline: Advisor check in RO push for <OEM>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify the Version number "<AD_VersionNumber>" on 'Advisor Check-In' page

    #decode the vin
    And I decode "<VIN>" on 'Advisor Check-In' page
    And I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    Then I see "<Recalls>" "recall" status on 'CUSTOMER' page
    And I see "<Contract>" "contract" status on 'CUSTOMER' page
    When I click "VIEW WARRANTY" button on 'CUSTOMER' page
    Then I verify "WARRANTY" status on 'CUSTOMER' page
    When I click "CLOSE" button on "WARRANTY INFORMATION" popup on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button on 'HISTORY' page
    And I click "REMOVE ALL" button on 'SERVICE-FACTORY SCHEDULED' Page
    And I click "NEXT" button on 'SERVICES' page
    And I select "SUV/MINIVAN" on 'WALK AROUND' page
    And I click "ALL OK" button on 'WALK AROUND' page
    And I click "NEXT" button on 'WALK AROUND' page
    And I change "PROMISE TIME" "+1" year on "ASSIGN R.O." page
    And I update <TagNumber> on "ASSIGN R.O." page
    And I write comment "This is a test from dealerfx cucumber automation, please ignore" on "ASSIGN R.O." page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    And I authorize and sign the agreement on 'AUTHORIZATION' page
    And I click "COMPLETE" button on 'AUTHORIZATION' page
    Then I should see RO push completed
    And I verify following PDF generated in RO push
      |PDF_Name                |VerifyField1             |VerifyField2|VerifyField3|VerifyField4              |VerifyField5         |VerifyField6|
      |Maintenance Menu PDF    |PREVIOUS                 |CURRENT     |NEXT        |Estimated Maintenance Cost|SIGNATURE AUTHORIZING|Grand Total |
      |Pre-Service Write-Up PDF|Pre-Service Write-Up     |            |            |                          |                     |            |
      |EVIR PDF                |Vehicle Inspection Report|            |            |                          |                     |            |
      |Parts Pick List         |Parts pick list          |            |            |                          |                     |            |


    Examples: below data set for data driven
      |URL     |DealerName          |DMS         |AD_VersionNumber    |VehicleMaker|Odometer|VIN              |Recalls|Contract|
      |TEST2AD |John Hine Mazda     |Recalls     |ver. 2.9.19035.01|Mazda       |60000   |1YVHP80C985M32215|Yes    |No      |
      #|TEST2AD |John Hine Mazda     |Recalls     |ver. 2.9.19035.01|Mazda       |60000   |JM1GJ1W52G1400022|Yes    |No      |
      #|TEST2AD |Audi Oakville       |Recalls     |ver. 2.9.19035.01|Audi        |60000   |WAUDK78T08A041833|No     |No      |
      #|TEST2AD |Audi Oakville       |Recalls     |ver. 2.9.19035.01|Audi        |60000   |WAUDF68E85A454160|Yes    |No      |
      #|TEST2AD |Audi Oakville       |Recalls     |ver. 2.9.19035.01|Audi        |60000   |WAUBJAFM3CA013326|Yes    |Yes     |
      #|TEST2AD |Audi Oakville       |Recalls     |ver. 2.9.19035.01|Audi        |60000   |WUAGNAFGXBN000851|No     |Yes     |
      #|TEST2AD |Barrie Mitsubishi   |Recalls     |ver. 2.9.19035.01|Mitsubishi  |60000   |JA3AV66VX9U607782|Yes    |No      |
      #|TEST2AD |Barrie Mitsubishi   |Recalls     |ver. 2.9.19035.01|Mitsubishi  |60000   |JA32V2FW7FU606462|No     |No      |
      #|TEST2AD |Regal Nissan        |Recalls     |ver. 2.9.19035.01|Nissan      |60000   |5N1AR2MN3EC692834|Yes    |Yes     |
      #|TEST2AD |Volkswagen of Naples|Recalls     |ver. 2.9.19035.01|VW          |60000   |3VWSW31C37M506413|Yes    |No      |
      #|TEST2AD |Volkswagen of Naples|Recalls     |ver. 2.9.19035.01|VW          |60000   |WVWFW8AH9DV009414|Yes    |No      |
      #|TEST2AD |Volkswagen of Naples|Recalls     |ver. 2.9.19035.01|VW          |60000   |3VWJA7AT0FM608123|Yes    |No      |
      #|TEST2AD |Volkswagen of Naples|Recalls     |ver. 2.9.19035.01|VW          |60000   |1VWBN7A37DC042324|Yes    |No      |
      #|TEST2AD |Volkswagen of Naples|Recalls     |ver. 2.9.19035.01|VW          |60000   |1VWCV7A30FC001749|Yes    |No      |

