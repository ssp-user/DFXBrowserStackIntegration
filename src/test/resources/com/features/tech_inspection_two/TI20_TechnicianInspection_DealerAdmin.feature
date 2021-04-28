@TI20 @TI
Feature: TI20_TechnicianInspection_DealerAdmin, Technician Inspection, do everything as dealer admin

  @ti_dealeradmin
  Scenario Outline: [@tiflow_parts] TI parts Grid
    #Login as Service Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "exie" and password "19890520Emily"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #Create MPI
    And I launch 'Search popup panel' in Inspection dashboard
    And I launch 'Create MPI' popup window in Inspection dashboard
    And I enter data set in Create MPI popup window
    #  for 'Customer/VIN Search' section
      | FieldName        |Value                   |
      | VIN              | WAUGGAFC8DN135881      |
      | Customer Name    |                        |
      | Phone            |                        |
    And I decode <VIN> in MPI popup windows
    And I enter data set in Create MPI popup window
    # for the 'Vehicle' Section
      | FieldName        |Value                   |
      | Make             |                        |
      | Year             |                        |
      | Model            |                        |
      | Transmission     |                        |
      | Engine Size      |                        |
      | Drive Train      |                        |
      | Mileage          |                        |
      | Mileage Value    | 65400                  |
      | Months           |                        |
      | Months Value     |                        |
    And I enter data set in Create MPI popup window
    # for the 'Inspection Information' Section
      | FieldName         |Value                                    |
      | Service Advisor   |                                         |
      | Technician        |                                         |
      | TAG               | part1                                   |
      | RO                | Parts01                                 |
      | Lot Number        |                                         |
      | License Plate     |                                         |
      | Promise Date      | +2                                      |
      | Promise Time      |                                         |
      | Quarter Date      |                                         |
      | Quarter Time      |                                         |
      | Inspection Type   | Audi Canada 29 Point Inspection         |
      | Transportation    |                                         |
      | Save parts        |                                         |
      | Car Wash          |                                         |
      | Glove box         |                                         |
      | Spare tire        |                                         |
      | Comments          |                                         |
    And I submit 'Complete' in Create MPI popup window
    Then I see vehicle with "<VIN>" with "CREATED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI & add a recommendation service with parts requested flag
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I 'Mark Section All OK' on 'MPI Details' page
    And I click "Red" icon on "Windshield Condition" inspection on 'INSPECTION' tab
#    And I enter data set in addRecommendationField
    And I enter data set in addRecommendationField under "Windshield Condition" section
      | FieldName         |Value                                    |
      | Recommendation    | Test1                                   |
      | OPcode            |                                         |
      | Hours             | 2.5                                     |
      | LaborSale         | 25.5                                    |
      | PartSale          | 100                                     |
      | Price             |                                         |
#    And I click "plus" icon on 'Inspection Detail' page
#    And I click "Parts Requested" button on 'Inspection Detail' page
#    And I click "Save" button on 'Inspection Detail' page
    And I click "plus" icon under "Windshield Condition" section on 'Inspection Detail' page
    And I click "Parts Requested" button under "Windshield Condition" section on 'Inspection Detail' page
    And I click "Save" button under "Windshield Condition" section on 'Inspection Detail' page
    # Add second recommendation
    And I click "Red" icon on "Engine drive belts" inspection on 'INSPECTION' tab
#    And I enter data set in addRecommendationField
    And I enter data set in addRecommendationField under "Engine drive belts" section
      | FieldName         |Value                                    |
      | Recommendation    | Test2                                   |
      | OPcode            |                                         |
      | Hours             | 10                                      |
      | LaborSale         | 20                                      |
      | PartSale          | 300                                     |
      | Price             |                                         |
#    And I click "plus" icon on 'Inspection Detail' page
#    And I click "Parts Requested" button on 'Inspection Detail' page
#    And I click "Save" button on 'Inspection Detail' page
    And I click "plus" icon under "Engine drive belts" section on 'Inspection Detail' page
    And I click "Parts Requested" button under "Engine drive belts" section on 'Inspection Detail' page
    And I click "Save" button under "Engine drive belts" section on 'Inspection Detail' page
    And I click "Save" button on 'MPI Details' page
    And I click "RECOMMENDATION" button on 'MPI Details' page
    Then I should see recommendation with "Test1" Description with "2.50" hours with "25.50" labor with "100.00" parts with "125.50" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "Test1" Description on 'RECOMMENDATION' tab
    And I should see recommendation with "Test2" Description with "10.00" hours with "20.00" labor with "300.00" parts with "320.00" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "Test2" Description on 'RECOMMENDATION' tab
    And I click "Mark Completed" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "PARTS REQUESTED" status on 'TECHNICIAN INSPECTION Dashboard'
    #comple and close MPI
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "PARTS REQUESTED" status on 'MPI Details' page
    When I click "HIDE" button on 'MPI Details' page
    And I click "RECOMMENDATION" button on 'MPI Details' page
    # Add two parts in parts grid
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
    When I click "In Stock" button with "Test1" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
    And I click "PARTS LIST" button on 'MPI Details' page
    Then I should see following data in 'PARTS LIST' tab
      | FieldName     | Value                 |
      | Type_1        | REC                   |
      | QTY_1         | 3                     |
      | Description_1 | first parts for test1 |
      | Status_1      | Stock                 |
      | Sale_1        | 50.00                 |
      | ExtSale_1     | 150.00                |
      | Type_2        | REC                   |
      | QTY_2         | 5                     |
      | Description_2 | second parts for test1|
      | Status_2      | Stock                 |
      | Sale_2        | 33.00                 |
      | ExtSale_2     | 165.00                |
    And I should see "$315.00" with "Total * Doesn’t include MPP pricing" on "PARTS LIST" tab
    # Add parts in parts list tab
    When I update parts in 'PARTS LIST' tab
      | FieldName     | Value                 |
      | QTY_3         | 2                     |
      | Description_3 | first parts for test2 |
      | Status_3      | NBO                   |
      | Sale_3        | 40.00                 |
    Then I should see "$395.00" with "Total * Doesn’t include MPP pricing" on "PARTS LIST" tab
    And I click "Save" button on 'PARTS LIST' page
    #check test2 price and status in recommendation tab
    And I click "RECOMMENDATION" button on 'MPI Details' page
    Then I should see recommendation with "Test2" Description with "10.00" hours with "20.00" labor with "80.00" parts with "100.00" price on 'RECOMMENDATION' tab
    And I should see "Not In Stock" toggle is "ON" with "Test2" on 'RECOMMENDATION' tab
    # edit qty in parts grid
    When I click "PARTS LIST" button with "Test2" Description on "RECOMMENDATION" tab
    And I enter data set in addPartsGridField
      | FieldName     |Value                  |
      | QTY_1         | 5                     |
    Then I should see "$200.00" with "Total" on "Parts Grid" tab
    When I click "SAVE and CLOSE" button on "Parts Grid" tab
    Then I should see recommendation with "Test2" Description with "10.00" hours with "20.00" labor with "200.00" parts with "220.00" price on 'RECOMMENDATION' tab
    # change status to Not in stock from recommendation tab
    When I click "Not In Stock" button with "Test1" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
    # check status and price on part list tab
    And I click "PARTS LIST" button on 'MPI Details' page
    Then I should see "$515.00" with "Total * Doesn’t include MPP pricing" on "PARTS LIST" tab
    Then I should see following data in 'PARTS LIST' tab
      | FieldName     | Value                 |
      | Status_1      |                       |
      | Status_2      |                       |
    # update status (Mix) from parts list tab, and update qty on parts list tab.
    When I update parts in 'PARTS LIST' tab
      | FieldName     | Value |
      | QTY_1         | 10    |
      | Status_1      | Stock |
      | Status_2      | NBO   |
      | Status_3      | NBO   |
    And I click "Save" button on 'PARTS LIST' page
    Then I should see "$865.00" with "Total * Doesn’t include MPP pricing" on "PARTS LIST" tab
    # check status and price in recommendation page
    And I click "RECOMMENDATION" button on 'MPI Details' page
    Then I should see recommendation with "Test1" Description with "2.50" hours with "25.50" labor with "665.00" parts with "690.50" price on 'RECOMMENDATION' tab
    And I should see "See Parts List Tab" toggle is "ON" with "Test1" on 'RECOMMENDATION' tab
    And I should see "Not In Stock" toggle is "ON" with "Test2" on 'RECOMMENDATION' tab
    # Complete and close from parts list tab
    And I click "PARTS LIST" button on 'MPI Details' page
    And I click "Complete and Close" button on "PARTS LIST" tab
    # Open the MPI and Mark review
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "COMPLETED" status on 'MPI Details' page
    And I click "RECOMMENDATION" button on 'MPI Details' page
    When I click "Mark Reviewed" button on 'MPI Details' page
    And I click "vehicle queue" icon on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "REVIEWED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI, approve the service and mark confirmed
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "REVIEWED" status on 'MPI Details' page
    When I click "RECOMMENDATION" button on 'MPI Details' page
    Then I should see recommendation with "Test1" Description with "2.50" hours with "25.50" labor with "665.00" parts with "690.50" price on 'RECOMMENDATION' tab
    And I should see recommendation with "Test2" Description with "10.00" hours with "20.00" labor with "200.00" parts with "220.00" price on 'RECOMMENDATION' tab
    And I should see "See Parts List Tab" toggle is "ON" with "Test1" on 'RECOMMENDATION' tab
    And I should see "Not In Stock" toggle is "ON" with "Test2" on 'RECOMMENDATION' tab
    When I click "APPROVED" button with "Test1" Description on 'RECOMMENDATION' page
    And I click "DEFERRED" button with "Test2" Description on 'RECOMMENDATION' page
    And I select "Time constraint" in dropdown under "DEFERRED" button with "Test2" Description
    And I click "Save" button on 'MPI Details' page
    And I click "SHOW TOTALS" on 'footbar' tab on 'MPI Details' page
    Then I should see "RECOMMENDED" with "$910.50" on 'footbar' tab
    And I should see "APPROVED" with "$690.50" on 'footbar' tab
    And I should see "DEFERRED" with "$220.00" on 'footbar' tab
    #When I click "Confirm" button on 'MPI Details' page
    And I click "vehicle queue" icon on 'MPI Details' page
    And I see vehicle with "<VIN>" with "CONFIRMED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI and move the vehicle to ready for processing
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "CONFIRMED" status on 'MPI Details' page
    When I click "APPROVED SERVICES" button on 'MPI Details' page
    And I should see "Test1" Description with "2.50" labor with "$690.50" price in 'recommendations' section on 'APPROVED SERVICES' tab
    When I click "green" toggle with "Test1" Description on 'APPROVED SERVICES' tab
    And I click "Save" button on 'MPI Details' page
    And I click "Ready for processing" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "READY FOR PROCESSING" status on 'TECHNICIAN INSPECTION Dashboard'
    Examples: this is for data driver testing
      |URL        |DealerName            |DealerCode|TAG   |VIN              |
#      |PRD2AUDI   |Audi of Anytown Canada|408A85CA  |part1 |WAUGGAFC8DN135881|
      |TST2AUDI   |Audi of Anytown Canada|408A85CA  |part1 |WAUGGAFC8DN135881|
#      |STG2AUDI   |Audi of Anytown Canada|408A85CA  |part1 |WAUGGAFC8DN135881|