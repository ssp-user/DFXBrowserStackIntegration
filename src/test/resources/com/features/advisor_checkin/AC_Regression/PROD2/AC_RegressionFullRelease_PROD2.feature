@AC-RegressionFullRelease-PROD2
Feature: AC function, Including the Customer Search,  etc, Regression full test on ENV PROD2
  Description : Verify AC functions including the customer Search, Elsa2Go, OEM RO push, DealerTire
                and Recalls function and check calculation for Maintenance PDF , etc

  @AC-RegressionFullRelease-CustomerSearch-PROD2
  Scenario Outline: Verify the Advisor checkin customer search function
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    #Search customer by name
    When I search "CUSTOMER NAME" with "<CustomerName>" in 'Advisor Check-In Search' page
    Then I verify search result contains "<CustomerName>" "name" on 'Advisor Check-In' page
    #Search customer by phone
    When I search "CUSTOMER PHONE" with "<PhoneNumber>" in 'Advisor Check-In Search' page
    And I select "first" search result on 'Advisor Check-In' page
    Then I verify "DEFAULT PHONE NUMBER" equal "<PhoneNumber>" on 'CUSTOMER' page
    #Search By vin ,verify full vin
    When I click "BACK TO SEARCH" button on 'CUSTOMER' page
    And I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    Then I verify "VEHICLE INFORMATION" equal "<VIN>" on 'CUSTOMER' page
    #verify vin with last 8 digits
    When I click "BACK TO SEARCH" button on 'CUSTOMER' page
    And I search 'VinNumber' "<VIN>Last8" on 'Advisor Check-In' page
    Then I verify "VEHICLE INFORMATION" equal "<VIN>" on 'CUSTOMER' page
    #verify vin with last 5 digits
    When I click "BACK TO SEARCH" button on 'CUSTOMER' page
    And I search 'VinNumber' "<VIN>Last5" on 'Advisor Check-In' page
    Then I verify "VEHICLE INFORMATION" equal "<VIN>" on 'CUSTOMER' page
    #Search by License Plate
    When I click "BACK TO SEARCH" button on 'CUSTOMER' page
    And I search "LICENSE PLATE" with "<LicensePlate>" in 'Advisor Check-In Search' page
    And I select "first" search result on 'Advisor Check-In' page
    Then I verify "LICENSE PLATE" equal "<LicensePlate>" on 'CUSTOMER' page
    # Assert All verify
    And I assert all above search verify result on 'CUSTOMER' page
    Examples: below data set for data driven
      |URL       |DealerName           |DMS                             |CustomerName|PhoneNumber    |LicensePlate   |VIN              |
      |PROD2AD   |Audi of Anytown USA  |Customer Search Functionality   |dfx         |416-493-0039   |Olga-2222      |WAUSGCFCXCN002530|

  @AC-RegressionOEM-PROD2-AUDI
  Scenario Outline: Verify the Advisor Check In Push RO properly for AUDI <OEM>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
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
      | FieldName           | Value         |
      | SERVICE ADVISOR     |               |
      | TRANSPORTATION      | 1             |
      | INSPECTION TYPE     | 1             |
      | TAG                 | <TagNumber>   |
      | TECHNICIAN          |               |
    And I enter "This is a test from dealerfx QA , please ignore" in "COMMENTS" field on 'ASSIGN R.O.' page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    Then I click 'MENU' button verify the "Maintenance" in PDF generated on 'AUTHORIZATION' page
    When I click "QUOTE" button on 'AUTHORIZATION' page
    Then I see 'GENERATE QUOTE' PDF completed
    Then I verify following in PDF generated on 'GENERATE QUOTE'
      |PDF_Name                        |VerifyField                   |
      |SAVED CUSTOMER SERVICE QUOTE    |Customer Service Quote        |
      |SAVED INTERNAL SERVICE QUOTE    |Internal Service Quote        |
    And I click "CLOSE" button on 'GENERATE QUOTE' PopUp
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
      |URL          |DMS                  |DealerName             |OEM         |AD_VersionNumber |CustomerName|Odometer |TagNumber|
      |PRD2ADAUDI   |Audi Framework       |anytown usa automall   |Audi        |2.9.19035.01     |TEST        |21431    |TagAu    |

  @AC-RegressionOEM-PROD2-Mitsubishi
  Scenario Outline: Verify the Advisor Check In Push RO properly for Mitsubishi <OEM>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
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
      | FieldName           | Value         |
      | SERVICE ADVISOR     |               |
      | TRANSPORTATION      | 1             |
      | INSPECTION TYPE     | 1             |
      | TAG                 | <TagNumber>   |
      | TECHNICIAN          |               |
    And I enter "This is a test from dealerfx QA , please ignore" in "COMMENTS" field on 'ASSIGN R.O.' page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    Then I click 'MENU' button verify the "Maintenance" in PDF generated on 'AUTHORIZATION' page
    When I click "QUOTE" button on 'AUTHORIZATION' page
    Then I see 'GENERATE QUOTE' PDF completed
    Then I verify following in PDF generated on 'GENERATE QUOTE'
      |PDF_Name                        |VerifyField                   |
      |SAVED CUSTOMER SERVICE QUOTE    |Customer Service Quote        |
      |SAVED INTERNAL SERVICE QUOTE    |Internal Service Quote        |
    And I click "CLOSE" button on 'GENERATE QUOTE' PopUp
    And I authorize and sign the agreement on 'AUTHORIZATION' page
    And I click "COMPLETE" button on 'AUTHORIZATION' page
    Then I should see RO push completed
    Then I assert RO push completed
    And I verify all links PDF generated in RO push
      |PDF_Name                |VerifyField                |
      |Maintenance Menu PDF    |Estimated Maintenance Cost |
      |Pre-Service Write-Up PDF|Pre-Service Write-Up       |
      |MULTIPOINT INSPECTION   |Inspection                 |
      |Parts Pick List         |Parts pick list            |
    Examples: below data set for data driven
      |URL          |DMS                  |DealerName             |OEM         |AD_VersionNumber |CustomerName|Odometer |TagNumber|
      |PRD2ADMITS   |Mitsubishi Framework |anytown usa automall   |Mitsubishi  |2.9.19035.01     |TEST        |21431    |TagMi    |

  @AC-RegressionOEM-PROD2-Toyota
  Scenario Outline: Verify the Advisor Check In Push RO properly for Toyota <OEM>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
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
      | FieldName           | Value         |
      | SERVICE ADVISOR     |               |
      | TRANSPORTATION      | 1             |
      | INSPECTION TYPE     | 1             |
      | TAG                 | <TagNumber>   |
      | TECHNICIAN          |               |
    And I enter "This is a test from dealerfx QA , please ignore" in "COMMENTS" field on 'ASSIGN R.O.' page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    Then I click 'MENU' button verify the "Maintenance" in PDF generated on 'AUTHORIZATION' page
    When I click "QUOTE" button on 'AUTHORIZATION' page
    Then I see 'GENERATE QUOTE' PDF completed
    Then I verify following in PDF generated on 'GENERATE QUOTE'
      |PDF_Name                        |VerifyField                   |
      |SAVED CUSTOMER SERVICE QUOTE    |Customer Service Quote        |
      |SAVED INTERNAL SERVICE QUOTE    |Internal Service Quote        |
    And I click "CLOSE" button on 'GENERATE QUOTE' PopUp
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
      |URL          |DMS                  |DealerName             |OEM         |AD_VersionNumber |CustomerName|Odometer |TagNumber|
      |PRD2ADTOYO   |Toyota Framework     |toyota of anytown usa  |Toyota      |2.9.19035.01     |TEST        |21431    |TagTo    |

  @AC-RegressionOEM-PROD2-lexus
  Scenario Outline: Verify the Advisor Check In Push RO properly for lexus <OEM>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
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
      | FieldName           | Value         |
      | SERVICE ADVISOR     |               |
      | TRANSPORTATION      | 1             |
      | INSPECTION TYPE     | 1             |
      | TAG                 | <TagNumber>   |
      | TECHNICIAN          |               |
    And I enter "This is a test from dealerfx QA , please ignore" in "COMMENTS" field on 'ASSIGN R.O.' page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    Then I click 'MENU' button verify the "Maintenance" in PDF generated on 'AUTHORIZATION' page
    When I click "QUOTE" button on 'AUTHORIZATION' page
    Then I see 'GENERATE QUOTE' PDF completed
    Then I verify following in PDF generated on 'GENERATE QUOTE'
      |PDF_Name                        |VerifyField                   |
      |SAVED CUSTOMER SERVICE QUOTE    |Customer Service Quote        |
      |SAVED INTERNAL SERVICE QUOTE    |Internal Service Quote        |
    And I click "CLOSE" button on 'GENERATE QUOTE' PopUp
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
      |URL          |DMS                  |DealerName             |OEM         |AD_VersionNumber |CustomerName|Odometer |TagNumber|
      |PRD2ADLEXU   |Lexus Framework      |lexus of anytown usa   |Lexus       |2.9.19035.01     |TEST        |21431    |Taglu    |

  @AC-RegressionOEM-PROD2-Nissan
  Scenario Outline: Verify the Advisor Check In Push RO properly for Nissan <OEM>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify the AD Version number "<AD_VersionNumber>" on 'Advisor Check-In' page
    When I search "CUSTOMER NAME" with "<CustomerName>" in 'Advisor Check-In Search' page
    And I click "2th" search result on 'Advisor Check-In' page
    And I click "NEXT" button on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    And I verify Mandatory Message Displayed in Fields on 'CUSTOMER' page
    And I verify "NEXT" button is not clickable if 'Odometer' field is empty
    When I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    Then I see "NEXT" button is clickable on 'CUSTOMER' page
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
      | FieldName           | Value         |
      | SERVICE ADVISOR     |               |
      | TRANSPORTATION      | 1             |
      | INSPECTION TYPE     | 1             |
      | TAG                 | <TagNumber>   |
      | TECHNICIAN          |               |
    And I enter "This is a test from dealerfx QA , please ignore" in "COMMENTS" field on 'ASSIGN R.O.' page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    Then I click 'MENU' button verify the "Maintenance" in PDF generated on 'AUTHORIZATION' page
    When I click "QUOTE" button on 'AUTHORIZATION' page
    Then I see 'GENERATE QUOTE' PDF completed
    Then I verify following in PDF generated on 'GENERATE QUOTE'
      |PDF_Name                        |VerifyField                   |
      |SAVED CUSTOMER SERVICE QUOTE    |Customer Service Quote        |
      |SAVED INTERNAL SERVICE QUOTE    |Internal Service Quote        |
    And I click "CLOSE" button on 'GENERATE QUOTE' PopUp
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
      |URL          |DMS                  |DealerName             |OEM         |AD_VersionNumber |CustomerName|Odometer |TagNumber|
      |PRD2ADNISS   |Nissan Framework     |nissan of anytown usa  |Nissan      |2.9.19035.01     |TEST        |21431    |TagNi    |

  @AC-RegressionOEM-PROD2-Infiniti
  Scenario Outline: Verify the Advisor Check In Push RO properly for Infiniti <OEM>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
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
      | FieldName           | Value         |
      | SERVICE ADVISOR     |               |
      | TRANSPORTATION      | 1             |
      | INSPECTION TYPE     | 1             |
      | TAG                 | <TagNumber>   |
      | TECHNICIAN          |               |
    And I enter "This is a test from dealerfx QA , please ignore" in "COMMENTS" field on 'ASSIGN R.O.' page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    Then I click 'MENU' button verify the "Maintenance" in PDF generated on 'AUTHORIZATION' page
    When I click "QUOTE" button on 'AUTHORIZATION' page
    Then I see 'GENERATE QUOTE' PDF completed
    Then I verify following in PDF generated on 'GENERATE QUOTE'
      |PDF_Name                        |VerifyField                   |
      |SAVED CUSTOMER SERVICE QUOTE    |Customer Service Quote        |
      |SAVED INTERNAL SERVICE QUOTE    |Internal Service Quote        |
    And I click "CLOSE" button on 'GENERATE QUOTE' PopUp
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
      |URL          |DMS                  |DealerName             |OEM         |AD_VersionNumber |CustomerName|Odometer |TagNumber|
      |PRD2ADINFI   |Infiniti Framework   |Anytown Infiniti USA   |Infiniti    |2.9.19035.01     |TEST        |21431    |TagIN    |

  @AC-Regression-DealerTire-PROD2-Nissan
  Scenario Outline: Verfiy the function able to generated the QUOTE with Dealer Tire selection <OEM>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
#    When I search "CUSTOMER NAME" with "<CustomerName>" in 'Advisor Check-In Search' page
#    And I click "2th" search result on 'Advisor Check-In' page
#    And I click "NEXT" button on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    When I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button on 'HISTORY' page
    #start to add tire
    And I click "TIRES" tab in Dealer 'SERVICES' page
    And I click "1th" 'ADD TIRES' icon in Dealer 'SERVICES' page
    And I select "1th" Size on 'TIRE OFFER' popup page
    And I click "1th" 'VIEW' button on 'TIRE OFFER' popup page
    And I click "ADD TO QUOTE" button on 'TIRE OFFER' popup page
    Then I verify the Install Price on DealerTire 'SERVICES' page
    When I click "QUOTE" button on 'SERVICES' page
    Then I see 'GENERATE QUOTE' PDF completed
    And I click "CLOSE" button on 'GENERATE QUOTE' PopUp
    And I signOut From AC
    Examples: below data set for data driven
      |URL       | DMS          |DealerName                |Odometer|VIN                |OEM         |CustomerName   |
      |PROD2AD   | DealerTire   |BEAVER COUNTY NISSAN INC  |21431   |1N4AL21E59C104101  |Nissan      |TESTING        |

  @AC-Regression-Elsa2GO-PROD2-Audi
  Scenario Outline: Verify the Advisor check In Elsa2Go Integration function in PROD2 with <OEM>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    #search by the customer
    When I search "CUSTOMER NAME" with "<CustomerName>" in 'Advisor Check-In Search' page
    And I click "first" search result on 'Advisor Check-In' page
    And I click "NEXT" button on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    And I verify Mandatory Message Displayed in Fields on 'CUSTOMER' page
    And I verify "NEXT" button is not clickable if 'Odometer' field is empty
    And I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    When I open "E2G" icon on 'CUSTOMER' page
    Then I see "AccessAudi" text on 'HISTORY-ELESA2GO' page
    Then I see "iAudi" text on 'HISTORY-ELESA2GO' page
    Then I see "HISTORY" tab is active on 'HISTORY-ELESA2GO' page
    Then I see "ELSA2GO" tab is active on 'HISTORY-ELESA2GO' page
    Then I signOut From AC
    Examples: below data set for data driven
      |URL          |DMS                  |DealerName             |<OEM>        |CustomerName|Odometer |
      |PROD2AD      |Elsa2Go              |Audi of anytown usa    |Audi         |dfx         |1234     |

  @AC-Regression-maintainCalculation-PROD2-Audi-noVIN
  Scenario Outline: Verify the function on Calculation of maintainance without Decode VIN
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    And I enter data set in Dealer 'VEHICLE' page
      | FieldName        |Value            |
      | Manafacture      | 1               |
      | Year             | 2               |
      | Model            | 1               |
      | Transmission     | 1               |
      | Engine           | 1               |
      | Drive            | 1               |
    And I verify "NEXT" button is not clickable if 'Odometer' field is empty
    And I input <Odometer> on "ODOMETER" field in Dealer 'VEHICLE' page
    And I click "NEXT" button in Dealer 'VEHICLE' page
    And I click "NEXT" button in Dealer 'HISTORY' page
    Then I verify selected "scheduled" services matched selected servies items
    And I keep Pre-Selected or choose random less "5" Scheduled services in 'SERVICE-FACTORY SCHEDULED MAINTENANCE' Tab if No Pre-selected
    Then I verify selected "scheduled" services matched selected servies items
    And I keep Pre-Selected or choose random less "5" Factory Required services in 'SERVICE-FACTORY SCHEDULED MAINTENANCE' Tab if No Pre-selected
    Then I verify selected "Factory Required" services matched selected servies items
    Then I verify Remove All to "Factory Required" services items on services page
    And I keep Pre-Selected or choose random less "5" Factory Required services in 'SERVICE-FACTORY SCHEDULED MAINTENANCE' Tab if No Pre-selected
    When I click "OTHER FACTORY MAINTENANCE" tab on 'SERVICES' Page
    And I keep Pre-Selected or choose random less "5" 'unScheduled' services in 'OTHER FACTORY MAINTENANCE' Tab
    Then I verify selected "unScheduled" services matched selected servies items
    Then I verify Remove All to "unScheduled" services items on services page
    And I choose random less "5" 'unScheduled' services in 'OTHER FACTORY MAINTENANCE' Tab
    When I click "RECOMMENDED SERVICES" tab on 'SERVICES' Page
    And I keep Pre-Selected or choose random less "5" 'recommend' services in 'RECOMMNED SERVICES' Tab
    Then I verify selected "RECOMMENDED" services matched selected servies items
    Then I verify Remove All to "RECOMMENDED" services items on services page
    When I choose random less "5" 'recommend' services in 'RECOMMNED SERVICES' Tab
    And I click "QUOTE" button on 'SERVICES' page
    Then I see 'GENERATE QUOTE' PDF completed
    Then I verify following in PDF generated on 'GENERATE QUOTE'
      |PDF_Name                        |VerifyField                   |
      |SAVED CUSTOMER SERVICE QUOTE    |Customer Service Quote        |
      |SAVED INTERNAL SERVICE QUOTE    |Internal Service Quote        |
    And I click "CLOSE" button on 'GENERATE QUOTE' PopUp
    And I click "MENU" button on 'SERVICES' page
    Then I verify following in PDF generated on 'GENERATE QUOTE'
      |PDF_Name                    |VerifyField                 |
      |MAINTENANCE MENU            |Maintenance Guide           |
    And I signOut From AC
    Examples: below data set for data driven
      |URL          |DMS         |DealerName                 |Odometer |
      |PRD2ADAUDI   |Menu Only   |audi of anytown usa menu   |35001    |

  @AC-Regression-maintainCalculation-PROD2-Audi-ByVIN
  Scenario Outline: Verify the function on Calculation of maintainance by Decode VIN
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    When I input <VIN> on "VIN" field in Dealer 'VEHICLE' page
    And I click "DECODE" button in Dealer 'VEHICLE' page
    And I verify "NEXT" button is not clickable if 'Odometer' field is empty
    And I input <Odometer> on "ODOMETER" field in Dealer 'VEHICLE' page
    And I click "NEXT" button in Dealer 'VEHICLE' page
    And I click "NEXT" button in Dealer 'HISTORY' page
    Then I verify selected "SCHEDULED" services matched selected servies items
    And I keep Pre-Selected or choose random less "5" Scheduled services in 'SERVICE-FACTORY SCHEDULED MAINTENANCE' Tab if No Pre-selected
    Then I verify selected "scheduled" services matched selected servies items
    And I keep Pre-Selected or choose random less "5" Factory Required services in 'SERVICE-FACTORY SCHEDULED MAINTENANCE' Tab if No Pre-selected
    Then I verify selected "Factory Required" services matched selected servies items
    Then I verify Remove All to "Factory Required" services items on services page
    And I keep Pre-Selected or choose random less "5" Factory Required services in 'SERVICE-FACTORY SCHEDULED MAINTENANCE' Tab if No Pre-selected
    When I click "OTHER FACTORY MAINTENANCE" tab on 'SERVICES' Page
    And I keep Pre-Selected or choose random less "5" 'unScheduled' services in 'OTHER FACTORY MAINTENANCE' Tab
    Then I verify selected "unScheduled" services matched selected servies items
    Then I verify Remove All to "unScheduled" services items on services page
    And I choose random less "5" 'unScheduled' services in 'OTHER FACTORY MAINTENANCE' Tab
    When I click "RECOMMENDED SERVICES" tab on 'SERVICES' Page
    And I keep Pre-Selected or choose random less "5" 'recommend' services in 'RECOMMNED SERVICES' Tab
    Then I verify selected "RECOMMENDED" services matched selected servies items
    Then I verify Remove All to "RECOMMENDED" services items on services page
    When I choose random less "5" 'recommend' services in 'RECOMMNED SERVICES' Tab
    And I click "QUOTE" button on 'SERVICES' page
    Then I see 'GENERATE QUOTE' PDF completed
    Then I verify following in PDF generated on 'GENERATE QUOTE'
      |PDF_Name                        |VerifyField                   |
      |SAVED CUSTOMER SERVICE QUOTE    |Customer Service Quote        |
      |SAVED INTERNAL SERVICE QUOTE    |Internal Service Quote        |
    And I click "CLOSE" button on 'GENERATE QUOTE' PopUp
    And I click "MENU" button on 'SERVICES' page
    And I click "SHOW" button on 'POP UP' page
    Then I verify following in PDF generated on 'GENERATE QUOTE'
      |PDF_Name                    |VerifyField                 |
      |MAINTENANCE MENU            |Maintenance Guide           |
    And I click "EDIT" button on 'POP UP' page
    And I signOut From AC
    Examples: below data set for data driven
      |URL          |DMS         |DealerName                 |Odometer |VIN               |Http                        |
      |PRD2ADAUDI   |Menu Only   |audi of anytown usa menu   |35001    |WAUJ8GFFXG1034028 |https://auditd.dealer-fx.com|

  @AC-Regression-TimeVSMileage-PROD2-VW
  Scenario Outline: Verify the Advisor check Maintenance Time&Mileage documentation function
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" and "<DealerCode>" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    Then I verify Mandatory Message Displayed in Fields on 'CUSTOMER' page
    And I verify "NEXT" button is not clickable if 'Odometer' field is empty
    When I enter "<Odometer1>" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I goto "SERVICES" Tab on 'Advisor Check-In' page
    And I verify 'MONTH' and 'MILES' service larger than below on 'SERVICES' page
      | FieldName       | Value        |
      | Month           | 30           |
      | Mileage         | 300000       |
    And I click "MENU" button on 'SERVICES' page
    Then I verify "Maintenance Time" service PDF generated on 'SERVICES' page
    When I goto "CUSTOMER" Tab on 'Advisor Check-In' page
    When I enter "<Odometer2>" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I goto "SERVICES" Tab on 'Advisor Check-In' page
    Then I verify 'MONTH' and 'MILES' service larger than below on 'SERVICES' page
      | FieldName       | Value        |
      | Month           | 72           |
      | Mileage         | 600000       |
    And I click "MENU" button on 'SERVICES' page
    Then I verify "Maintenance Mileage" service PDF generated on 'SERVICES' page
    Then I assert all above PDF verify result on 'SERVICES' page
    And I signOut From AC
    Examples: below data set for data driven
      | URL        | DMS         | DealerName                         | DealerCode | Odometer1 | Odometer2 | VIN                |
      | PRD2ADVOLK | vwFramework | ZZZ_Vxoxlxkxsxwxaxgxexn of Naples  |            | 1000      | 56478     | 1VWCV7A30FC001749  |

