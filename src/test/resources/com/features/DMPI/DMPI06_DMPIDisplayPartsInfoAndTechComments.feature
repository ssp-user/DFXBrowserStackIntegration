@DMPI06 @DMPI_SMOKE

Feature: DMPI Display Parts Information (Part Available, See Parts List) and Comments entered by Technician and Parts Clerk
         DMPI, login as advisor to create service lane in Advisor checkin
         login as technician to complete the service in Technician Inspection
         login as advisor to send message to customer via Email

  Scenario Outline: Dynamic multi point inspection, verify message to customer By Email
    #create service lane through AC as ADVISOR
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Advisor Check-In" Application on 'Main Page'
    And I decode "<VIN>" on 'Advisor Check-In' page
    And I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I change "Hakan" in "FIRST NAME" field on "CUSTOMER" page
    And I change "Automation" in "LAST NAME" field on "CUSTOMER" page
    And I change "dfxcucumbertest@gmail.com" in "EMAIL" field on "CUSTOMER" page
    And I click "NEXT" button on 'CUSTOMER' page
    And I add following customer concerns on 'CONCERN' page
      |Concern name       |
      |AC/Heating Concern |
      |Battery Concern    |
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button on 'HISTORY' page
    And I click "NEXT" button on 'SERVICES' page
    And I select "SUV/MINIVAN" on 'WALK AROUND' page
    And I click "ALL OK" button on 'WALK AROUND' page
    And I click "NEXT" button on 'WALK AROUND' page
    And I update <TAG> on "ASSIGN R.O." page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    And I authorize and sign the agreement on 'AUTHORIZATION' page
    And I click "COMPLETE" button on 'AUTHORIZATION' page
    Then I should see RO push completed
    #complete service and add two recommendation in Technician Inspection as Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanT" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #And I search vehicle <TAG> in 'TECHNICIAN INSPECTION Dashboard'
    And I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "Red" icon on "AC/Heating Concern" concern on 'INSPECTION' tab
    And I enter data set in addRecommendationField
      | FieldName         |Value      |
      | Recommendation    | TestAC    |
      | OPcode            |           |
      | Hours             | 3         |
      | LaborSale         | 30        |
      | PartSale          | 200       |
      | Price             |           |
    And I click "plus" icon on 'Inspection Detail' page
    And I click "Parts Requested" button on 'Inspection Detail' page
    # add Internal Comments
    And I input "This is Internal Comment entered by Tech" on "Internal Comment" field under "TestAC" Description on "Inspection Detail" page
    And I click "Save" button on 'Inspection Detail' page
    And I click "Orange" icon on "Battery Concern" concern on 'INSPECTION' tab
    And I enter data set in addRecommendationField
      | FieldName         |Value          |
      | Recommendation    | TestBattery   |
      | OPcode            |               |
      | Hours             | 1             |
      | LaborSale         | 20            |
      | PartSale          | 60            |
      | Price             |               |
    And I click "plus" icon on 'Inspection Detail' page
    And I click "Parts Requested" button on 'Inspection Detail' page
    And I click "Save" button on 'Inspection Detail' page
    And I 'Mark Section All OK' on 'MPI Details' page
    And I click "Red" icon on "Horn Operation" inspection on 'INSPECTION' tab
    And I enter data set in addRecommendationField
      | FieldName         |Value                                    |
      | Recommendation    | Test1                                   |
      | OPcode            |                                         |
      | Hours             | 2.5                                     |
      | LaborSale         | 25.5                                    |
      | PartSale          | 100                                     |
      | Price             |                                         |
    And I click "plus" icon on 'Inspection Detail' page
    And I click "Parts Requested" button on 'Inspection Detail' page
    # add Internal Comments
    And I input "This is Internal Comment entered by Tech" on "Internal Comment" field under "Test1" Description on "Inspection Detail" page
    And I click "Save" button on 'Inspection Detail' page
    And I click "Orange" icon on "Left Wiper Blade" inspection on 'INSPECTION' tab
    And I enter data set in addRecommendationField
      | FieldName         |Value                                    |
      | Recommendation    | Test2                                   |
      | OPcode            |                                         |
      | Hours             | 3.0                                     |
      | LaborSale         | 35.5                                    |
      | PartSale          | 200                                     |
      | Price             |                                         |
    And I click "plus" icon on 'Inspection Detail' page
    And I click "Parts Requested" button on 'Inspection Detail' page
    And I click "Save" button on 'Inspection Detail' page
    And I click "Save" button on 'MPI Details' page
    And I click "RECOMMENDATION" button on 'MPI Details' page
    Then I should see recommendation with "Test1" Description with "2.50" hours with "25.50" labor with "100.00" parts with "125.50" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "Test1" Description on 'RECOMMENDATION' tab
    And I should see recommendation with "Test2" Description with "3.00" hours with "35.50" labor with "200.00" parts with "235.50" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "Test2" Description on 'RECOMMENDATION' tab
    And I should see recommendation with "TestAC" Description with "3.00" hours with "30.00" labor with "200.00" parts with "230.00" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "TestAC" Description on 'RECOMMENDATION' tab
    And I should see recommendation with "TestBattery" Description with "1.00" hours with "20.00" labor with "60.00" parts with "80.00" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "TestBattery" Description on 'RECOMMENDATION' tab
    And I click "Mark Completed" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "PARTS REQUESTED" status on 'TECHNICIAN INSPECTION Dashboard'
    #complete service by adding parts information in Technician Inspection as Parts Clerk
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanP" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    Then I see vehicle with "<VIN>" with "PARTS REQUESTED" status on 'TECHNICIAN INSPECTION Dashboard'
    #Open MPI, mark parts in-stock, and complete the mpi
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "PARTS REQUESTED" status on 'MPI Details' page
    # Add two parts for TEST1 in parts grid
    When I click "PARTS LIST" button with "Test1" Description on "RECOMMENDATION" tab
    And I enter data set in addPartsGridField
      | FieldName     |Value                  |
      | QTY_1         | 3                     |
      | B.O._1        |                       |
      | Part#_1       |                       |
      | Description_1 | first parts for test1 |
      | Bin/Shelf_1   |                       |
      | O.H._1        |                       |
      | Cost_1        |                       |
      | LIST_1        |                       |
      | Sale_1        | 50                    |
      | ExtSale_1     |                       |
    Then I should see "$150.00" with "Total" on "Parts Grid" tab
    When I enter data set in addPartsGridField
      | FieldName     |Value                  |
      | QTY_2         | 5                     |
      | B.O._2        |                       |
      | Part#_2       |                       |
      | Description_2 | second parts for test1|
      | Bin/Shelf_2   |                       |
      | O.H._2        |                       |
      | Cost_2        |                       |
      | LIST_2        |                       |
      | Sale_2        | 33                    |
      | ExtSale_2     |                       |
    Then I should see "$315.00" with "Total" on "Parts Grid" tab
    When I click "SAVE and CLOSE" button on "Parts Grid" tab
    Then I should see recommendation with "Test1" Description with "2.50" hours with "25.50" labor with "315.00" parts with "340.50" price on 'RECOMMENDATION' tab
    # add Internal Comments
    And I input "This is Internal Comment entered by Parts Clerk" on "Parts Clerk Comments" field under "Test1" Description on "RECOMMENDATION" page
    When I click "In Stock" button with "Test1" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
    # Add one part for TEST2 in parts grid
    When I click "PARTS LIST" button with "Test2" Description on "RECOMMENDATION" tab
    And I enter data set in addPartsGridField
      | FieldName     |Value                  |
      | QTY_1         | 2                     |
      | B.O._1        |                       |
      | Part#_1       | 26360B5200            |
      | Description_1 | WIPER BLADE           |
      | Bin/Shelf_1   |                       |
      | O.H._1        |                       |
      | Cost_1        |                       |
      | LIST_1        |                       |
      | Sale_1        |                       |
      | ExtSale_1     |                       |
    Then I should see "$33.68" with "Total" on "Parts Grid" tab
    When I click "SAVE and CLOSE" button on "Parts Grid" tab
    Then I should see recommendation with "Test2" Description with "3.00" hours with "35.50" labor with "33.68" parts with "69.18" price on 'RECOMMENDATION' tab
    When I click "In Stock" button with "Test2" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
      # Add one part for TestAC in parts grid
    When I click "PARTS LIST" button with "TestAC" Description on "RECOMMENDATION" tab
    And I enter data set in addPartsGridField
      | FieldName     |Value                  |
      | QTY_1         | 1                     |
      | B.O._1        |                       |
      | Part#_1       | 9210016E20            |
      | Description_1 | CONDENSER ASSY        |
      | Bin/Shelf_1   |                       |
      | O.H._1        |                       |
      | Cost_1        |                       |
      | LIST_1        |                       |
      | Sale_1        |                       |
      | ExtSale_1     |                       |
    Then I should see "$254.98" with "Total" on "Parts Grid" tab
    When I click "SAVE and CLOSE" button on "Parts Grid" tab
    Then I should see recommendation with "TestAC" Description with "3.00" hours with "30.00" labor with "254.98" parts with "284.98" price on 'RECOMMENDATION' tab
    When I click "In Stock" button with "TestAC" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
  # Add one part for TestBattery in parts grid
    When I click "PARTS LIST" button with "TestBattery" Description on "RECOMMENDATION" tab
    And I enter data set in addPartsGridField
      | FieldName     |Value                  |
      | QTY_1         | 1                     |
      | B.O._1        |                       |
      | Part#_1       |                       |
      | Description_1 | first parts for TestBattery      |
      | Bin/Shelf_1   |                       |
      | O.H._1        |                       |
      | Cost_1        |                       |
      | LIST_1        |                       |
      | Sale_1        | 60                    |
      | ExtSale_1     |                       |
    Then I should see "$60.00" with "Total" on "Parts Grid" tab
    When I click "SAVE and CLOSE" button on "Parts Grid" tab
    Then I should see recommendation with "TestBattery" Description with "1.00" hours with "20.00" labor with "60.00" parts with "80.00" price on 'RECOMMENDATION' tab
    # add Internal Comments
    And I input "This is Internal Comment entered by Parts Clerk" on "Parts Clerk Comments" field under "TestBattery" Description on "RECOMMENDATION" page
    When I click "Not In Stock" button with "TestBattery" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
    And I click "Parts Request Completed" button on 'RECOMMENDATION' page
    #Login as service advisor, Open DMPI bridge through service dashboard
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "MPI Completed" status on 'VEHICLE QUEUE' page
    When I click "DETAILS" button under the service lane with <VIN> on 'VEHICLE QUEUE' page
    #TestAC Concern
    And I click Document Type "Recommendations" under Final Documents on 'SERVICE VISIT' page
    Then I see "TestAC" description with "Parts Available" status on "SERVICE VISIT" page
    When I click "TestAC" parts status button on "SERVICE VISIT" page
    Then I see following data for "TestAC" parts availability on "SERVICE VISIT" page
      |FieldName    |Value         |
      |QTY_1        |1             |
      |PART#_1      |9210016E20    |
      |DESCRIPTION_1|CONDENSER ASSY|
      |STATUS_1     |Stock         |
    When I click "Notes" icon under "TestAC" description on "SERVICE VISIT" page
    Then I see "TECHNICIAN" role and "This is Internal Comment entered by Tech" comment on "SERVICE VISIT" page
    #TestBattery Concern
    And I see "TestBattery" description with "See Parts List" status on "SERVICE VISIT" page
    When I click "TestBattery" parts status button on "SERVICE VISIT" page
    Then I see following data for "TestBattery" parts availability on "SERVICE VISIT" page
      |FieldName    |Value                      |
      |QTY_1        |1                          |
      |PART#_1      |                           |
      |DESCRIPTION_1|first parts for TestBattery|
      |STATUS_1     |                           |
    When I click "Notes" icon under "TestBattery" description on "SERVICE VISIT" page
    Then I see "PARTS CLERK" role and "This is Internal Comment entered by Parts Clerk" comment on "SERVICE VISIT" page
    #Test1 Inspection Recommendation
    When I click "INSPECTION" tab on 'SERVICE VISIT' page
    Then I see "Test1" description with "Parts Available" status on "SERVICE VISIT" page
    When I click "Test1" parts status button on "SERVICE VISIT" page
    Then I see following data for "Test1" parts availability on "SERVICE VISIT" page
      |FieldName    |Value                 |
      |QTY_1        |3                     |
      |PART#_1      |                      |
      |DESCRIPTION_1|first parts for test1 |
      |STATUS_1     |Stock                 |
      |QTY_2        |5                     |
      |PART#_2      |                      |
      |DESCRIPTION_2|second parts for test1|
      |STATUS_2     |Stock                 |
    When I click "Notes" icon under "Test1" description on "SERVICE VISIT" page
    Then I see "TECHNICIAN" role and "This is Internal Comment entered by Tech" comment on "SERVICE VISIT" page
    Then I see "PARTS CLERK" role and "This is Internal Comment entered by Parts Clerk" comment on "SERVICE VISIT" page
    #Test2 Inspection
    Then I see "Test2" description with "Parts Available" status on "SERVICE VISIT" page
    When I click "Test2" parts status button on "SERVICE VISIT" page
    Then I see following data for "Test2" parts availability on "SERVICE VISIT" page
      |FieldName    |Value      |
      |QTY_1        |2          |
      |PART#_1      |26360B5200 |
      |DESCRIPTION_1|WIPER BLADE|
      |STATUS_1     |Stock      |
    Examples: this is for data driver testing
      |URL        |DMS |DealerName            |DealerCode |Odometer|TAG     |VIN              |
      |PRD2NISS   | ?  |Nissan of Anytown USA |DONOTCHANGE|60000   |random  |5N1AR2MM1EC716183|