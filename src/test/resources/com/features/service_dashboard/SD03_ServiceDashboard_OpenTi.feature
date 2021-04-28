@rsflow03 @rsopenti
Feature: Open technician inspection from service dashboard

  @tiflow_opentifromrs
  Scenario Outline: [@tiflow_opentifromrs] run TI from started status to ready for processing status, open ti from rs
    #delete service lane through SD as dealer admin
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanD" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    And I search "<VIN>" on 'VEHICLE QUEUE' page
    And I "delete" the service lane "<VIN>" status on 'SEARCH' page
    And I click "Back" button on Service Dashboard 'SEARCH' page
    Then I don't see service lane with "<VIN>" on 'VEHICLE QUEUE' page
    #RO push as service advisor
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Advisor Check-In" Application on 'Main Page'
    And I decode "<VIN>" on 'Advisor Check-In' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I add following customer concerns on 'CONCERN' page
      |Concern name       |
      |AC/Heating Concern |
      |Battery Concern    |
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button on 'HISTORY' page
    And I click "NEXT" button on 'SERVICES' page
    And I click "NEXT" button on 'WALK AROUND' page
    And I update random on "ASSIGN R.O." page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    And I click "I AUTHORIZE COMPLETION OF REQUESTED SERVICES" button on 'AUTHORIZATION' page
    And I sign the agreement on "AUTHORIZATION" page
    And I click "COMPLETE" button on 'AUTHORIZATION' page
    Then I should see RO push completed
    #Login as Technician
    # Open the MPI & add a recommendation service with parts requested flag
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanT" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "MPI Ready" status on 'VEHICLE QUEUE' page
    When I click "OPEN IN TI" button on 'VEHICLE QUEUE' page
    And I switch back to page title "Service Dashboard"
    Then I should see service lane with <VIN> with "MPI Started" status on 'VEHICLE QUEUE' page
    And I switch back to page title "MPI Details"
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "STARTED" status on 'MPI Details' page
    When I click "HIDE" button on 'MPI Details' page
    # Add recommendation for concern
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
    And I click "Save" button on 'MPI Details' page
    And I click "RECOMMENDATION" button on 'MPI Details' page
    Then I should see recommendation with "Test1" Description with "2.50" hours with "25.50" labor with "100.00" parts with "125.50" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "Test1" Description on 'RECOMMENDATION' tab
    And I should see recommendation with "TestAC" Description with "3.00" hours with "30.00" labor with "200.00" parts with "230.00" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "TestAC" Description on 'RECOMMENDATION' tab
    And I should see recommendation with "TestBattery" Description with "1.00" hours with "20.00" labor with "60.00" parts with "80.00" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "TestBattery" Description on 'RECOMMENDATION' tab
    And I click "Mark Completed" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "PARTS REQUESTED" status on 'TECHNICIAN INSPECTION Dashboard'
    When I switch back to page title "Service Dashboard"
    Then I should see service lane with <VIN> with "Parts Requested" status on 'VEHICLE QUEUE' page
    #Login as Parts Clerk, complete and close the mpi
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanP" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "Parts Requested" status on 'VEHICLE QUEUE' page
    When I click "OPEN IN APP" button on 'VEHICLE QUEUE' page
    And I click "TECHNICIAN INSPECTION" button on 'VEHICLE QUEUE' page
    And I switch back to page title "MPI Details"
    #Open MPI, mark parts in-stock, and complete the mpi
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
    And I click "Not In Stock" button with "TestBattery" Description on 'RECOMMENDATION' page
    And I click "Not In Stock" button with "TestAC" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
    And I click "Parts Request Completed" button on 'RECOMMENDATION' page
    When I switch back to page title "Service Dashboard"
    Then I should see service lane with <VIN> with "MPI Completed" status on 'VEHICLE QUEUE' page
    #Login as Service advisor, and check RS is in "MPI Completed" status.
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "MPI Completed" status on 'VEHICLE QUEUE' page
    When I click "OPEN IN APP" button on 'VEHICLE QUEUE' page
    And I click "TECHNICIAN INSPECTION" button on 'VEHICLE QUEUE' page
    And I switch back to page title "MPI Details"
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "COMPLETED" status on 'MPI Details' page
    When I click "Mark Reviewed" button on 'MPI Details' page
    And I switch back to page title "Service Dashboard"
    Then I should see service lane with <VIN> with "Reviewed" status on 'VEHICLE QUEUE' page
    When I switch back to page title "MPI Details"
    When I click "APPROVED" button with "Test1" Description on 'RECOMMENDATION' page
    And I click "DEFERRED" button with "TestBattery" Description on 'RECOMMENDATION' page
    And I click "DEFERRED" button with "TestAC" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
    And I click "SHOW TOTALS" on 'footbar' tab on 'MPI Details' page
    Then I should see "RECOMMENDED" with "$650.50" on 'footbar' tab
    And I should see "APPROVED" with "$340.50" on 'footbar' tab
    When I click "Confirm" button on 'MPI Details' page
    When I switch back to page title "Service Dashboard"
    Then I should see service lane with <VIN> with "Work in Progress" status on 'VEHICLE QUEUE' page
    #Login as Service Technician to work on parts
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanT" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "Work in Progress" status on 'VEHICLE QUEUE' page
    When I click "OPEN IN APP" button on 'VEHICLE QUEUE' page
    And I click "TECHNICIAN INSPECTION" button on 'VEHICLE QUEUE' page
    When I switch back to page title "MPI Details"
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "CONFIRMED" status on 'MPI Details' page
    And I should see "AC/Heating Concern" Description with "1.00" labor with "$149.00" price in 'recommendations' section on 'APPROVED SERVICES' tab
    And I should see "Battery Concern" Description with "1.00" labor with "$149.00" price in 'recommendations' section on 'APPROVED SERVICES' tab
    And I should see "Test1" Description with "2.50" labor with "$340.50" price in 'recommendations' section on 'APPROVED SERVICES' tab
    When I click "green" toggle with "AC/Heating Concern" Description on 'APPROVED SERVICES' tab
    And I click "green" toggle with "Battery Concern" Description on 'APPROVED SERVICES' tab
    And I click "green" toggle with "Test1" Description on 'APPROVED SERVICES' tab
    And I click "Save" button on 'MPI Details' page
    And I click "Ready for processing" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "READY FOR PROCESSING" status on 'TECHNICIAN INSPECTION Dashboard'
    When I switch back to page title "Service Dashboard"
    Then I should see service lane with <VIN> with "Delivery Ready" status on 'VEHICLE QUEUE' page
    Examples: this is for data driver testing
      |URL        |DealerName            |DealerCode |TAG   |VIN              |
      |TST2NISS   |Nissan of Anytown USA |DONOTCHANGE|RS03  |5N1AR2MM1EC716183|

