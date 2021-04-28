@TI23 @TI
Feature: TI23_TechnicianInspection_LaborType, Technician Inspection, update labor type on recommendation tab

  @ti_update_price_recTab
  Scenario Outline: [@ti_update_price_recTab] update price on recommendation tab
    #Login as Service Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "auditechca" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #Create MPI
    And I launch 'Search popup panel' in Inspection dashboard
    And I launch 'Create MPI' popup window in Inspection dashboard
    And I enter data set in Create MPI popup window
      | FieldName        |Value                   |
      | VIN              | WAU3FLFF6F1087119      |
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
      | TAG               | Rec23                                   |
      | RO                |                                         |
      | Lot Number        |                                         |
      | License Plate     |                                         |
      | Promise Date      | +2                                      |
      | Promise Time      | 11:45 AM                                |
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
    And I enter data set in addRecommendationField under "Windshield Condition" section
      | FieldName         |Value                                    |
      | Recommendation    | Test1                                   |
      | OPcode            |                                         |
      | Hours             | 2.5                                     |
      | LaborSale         | 25.5                                    |
      | PartSale          | 100                                     |
      | Price             |                                         |
    And I click "plus" icon under "Windshield Condition" section on 'Inspection Detail' page
    And I click "Parts Requested" button under "Windshield Condition" section on 'Inspection Detail' page
    And I click "Save" button on 'Inspection Detail' page
    And I click "Save" button on 'MPI Details' page
    And I click "RECOMMENDATION" button on 'MPI Details' page
    Then I should see recommendation with "Test1" Description with "2.50" hours with "25.50" labor with "100.00" parts with "125.50" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "Test1" Description on 'RECOMMENDATION' tab
    And I click "Mark Completed" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "PARTS REQUESTED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Login as Parts Clerk, complete and close the mpi
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "audipartsca" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    Then I see vehicle with "<VIN>" with "PARTS REQUESTED" status on 'TECHNICIAN INSPECTION Dashboard'
    #Open MPI, mark parts in-stock, and complete the mpi
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "PARTS REQUESTED" status on 'MPI Details' page
    When I update "2.00" in "hours" with "Test1" Description on 'RECOMMENDATION' tab
    And I update "Type1" in "Labor Type" with "Test1" Description on 'RECOMMENDATION' tab
    And I click "Save" button on 'MPI Details' page
    Then I should see recommendation with "Test1" Description with "2.00" hours with "Type1" type with "150.00" rate with "300.00" labor with "100.00" parts with "400.00" price on 'RECOMMENDATION' tab
    When I click "In Stock" button with "Test1" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
    And I click "Parts Request Completed" button on 'RECOMMENDATION' page
    # Login as Service Advisor
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "audiadvisorca" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    And I select "Select All" in Created By drop down in Search MPI popup
    Then I see vehicle with "<VIN>" with "COMPLETED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI and Mark review
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "COMPLETED" status on 'MPI Details' page
    And  I should see recommendation with "Test1" Description with "2.00" hours with "Type1" type with "150.00" rate with "300.00" labor with "100.00" parts with "400.00" price on 'RECOMMENDATION' tab
    When I update "0.50" in "hours" with "Test1" Description on 'RECOMMENDATION' tab
    And I update "Type2" in "Labor Type" with "Test1" Description on 'RECOMMENDATION' tab
    And I click "Save" button on 'MPI Details' page
    Then I should see recommendation with "Test1" Description with "0.50" hours with "Type2" type with "100.00" rate with "50.00" labor with "100.00" parts with "150.00" price on 'RECOMMENDATION' tab
    When I update "99.99" in "labor" with "Test1" Description on 'RECOMMENDATION' tab
    And I click "Save" button on 'MPI Details' page
    Then I should see recommendation with "Test1" Description with "0.50" hours with "Type2" type with "100.00" rate with "99.99" labor with "100.00" parts with "199.99" price on 'RECOMMENDATION' tab
    When I click "Mark Reviewed" button on 'MPI Details' page
    And I click "vehicle queue" icon on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "REVIEWED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI, approve the service and mark confirmed
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "REVIEWED" status on 'MPI Details' page
    And I should see recommendation with "Test1" Description with "0.50" hours with "Type2" type with "100.00" rate with "99.99" labor with "100.00" parts with "199.99" price on 'RECOMMENDATION' tab
    When I click "APPROVED" button with "Test1" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
    And I click "SHOW TOTALS" on 'footbar' tab on 'MPI Details' page
    Then I should see "RECOMMENDED" with "$199.99" on 'footbar' tab
    And I should see "APPROVED" with "$199.99" on 'footbar' tab
    When I click "Confirm" button on 'MPI Details' page
    #And I click "vehicle queue" icon on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "CONFIRMED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Login as Service Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "auditechca" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    Then I see vehicle with "<VIN>" with "CONFIRMED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI and move the vehicle to ready for processing
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "CONFIRMED" status on 'MPI Details' page
    And I should see "Test1" Description with "0.50" labor with "$199.99" price in 'recommendations' section on 'APPROVED SERVICES' tab
    When I click "green" toggle with "Test1" Description on 'APPROVED SERVICES' tab
    And I click "Save" button on 'MPI Details' page
    And I click "Ready for processing" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "READY FOR PROCESSING" status on 'TECHNICIAN INSPECTION Dashboard'
    Examples: this is for data driver testing
      |URL        |DealerName            |DealerCode|TAG   |VIN              |
      |PRD2AUDI   |Audi of Anytown Canada|408A85CA  |Rec01 |WAU3FLFF6F1087119|
#      |TST2AUDI   |Audi of Anytown Canada|408A85CA  |Rec01 |WAU3FLFF6F1087119|
#      |STG2AUDI   |Audi of Anytown Canada|408A85CA  |Rec01 |WAU3FLFF6F1087119|