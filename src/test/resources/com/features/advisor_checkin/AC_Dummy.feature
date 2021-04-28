# new feature
@AC @ADVISORDASHBOARD @ADVISORCHECKIN @ACDUMMY

Feature: Advisor check in RO push for <Dummy>

  Scenario Outline: Advisor check in RO push for <DMS>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "" and "<DealerCode>" in Dealer Page
    And I decode "<VIN>" on 'Advisor Check-In' page
    And I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I change "Test" in "FIRST NAME" field on "CUSTOMER" page
    And I change "Test123" in "LAST NAME" field on "CUSTOMER" page
    And I click "NEXT" button on 'CUSTOMER' page
    And I add following customer concerns on 'CONCERN' page
      |Concern name       |
      |AC Heating Concern |
      |Battery Concern    |
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
      |URL      |DMS      |DealerName         |DealerCode  |CustomerName|Odometer|TagRequired|TagNumber|VIN              |
      |PROD2AD  |Dummy    |audi of anytown usa|408A85US    |testing     |4700    |yes        |TEST     |WUAAU34269N001906|