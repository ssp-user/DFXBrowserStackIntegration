@TIONE16 @TIONE
Feature: Technician Inspection, RS gating moving

  @tioneflow_RSGate_Parts
  Scenario Outline: [@tiflow_RSGate_Parts] run TI from started status to ready for processing status, RS gate moving
    #delete service lane through SD as dealer admin
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenD" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    And I delete service lane with <VIN> on 'VEHICLE QUEUE' page
    Then I don't see service lane with "<VIN>" on 'VEHICLE QUEUE' page

    #Login as Service Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenT" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #Create MPI
    And I launch 'Search popup panel' in Inspection dashboard
    And I launch 'Create MPI' popup window in Inspection dashboard
    And I enter data set in Create MPI popup window
    #  for 'Customer/VIN Search' section
      | FieldName        |Value  |
      | VIN              | <VIN> |
      | Customer Name    |       |
      | Phone            |       |
    And I decode <VIN> in MPI popup windows
    And I enter data set in Create MPI popup window
    # for the 'Vehicle' Section
      | FieldName        |Value  |
      | Make             |       |
      | Year             |       |
      | Model            |       |
      | Transmission     |       |
      | Engine Size      |       |
      | Drive Train      |       |
      | Mileage          |       |
      | Mileage Value    | 65400 |
      | Months           |       |
      | Months Value     |       |
    And I enter data set in Create MPI popup window
    # for the 'Inspection Information' Section
      | FieldName         |Value      |
      | Servce Advisor    |           |
      | Technician        |           |
      | TAG               | <TAG>     |
      | RO                | flowGate1 |
      | Lot Number        |           |
      | License Plate     |           |
      | Promise Date      | +2        |
      | Promise Time      | 11:45 AM  |
      | Quarter Date      |           |
      | Quarter Time      |           |
      | Inspection Type   | <Template>|
      | Transportation    |           |
      | Save parts        |           |
      | Car Wash          |           |
      | Glove box         |           |
      | Spare tire        |           |
      | Comments          |           |
    And I submit 'Complete' in Create MPI popup window
    Then I see vehicle with "<VIN>" with "CREATED" status on 'TECHNICIAN INSPECTION Dashboard'

    #Login as service advisor, Open RS to check its in MPI started status.
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "MPI Ready" status on 'VEHICLE QUEUE' page

    #Login as Technician
    # Open the MPI & add a recommendation service with parts requested flag
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenT" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    Then I see vehicle with "<VIN>" with "CREATED" status on 'TECHNICIAN INSPECTION Dashboard'
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I 'Mark Section All OK' on 'MPI Details' page
    And I click "Red" icon on "Headlamps" inspection on 'INSPECTION' tab
    And I enter data set in addRecommendationField
      | FieldName         |Value   |
      | Recommendation    | Test1  |
      | OPcode            |        |
      | Hours             | 2.5    |
      | LaborSale         | 25.5   |
      | PartSale          | 100    |
      | Price             |        |
    And I click "plus" icon on 'Inspection Detail' page
    And I click "Parts Requested" button on 'Inspection Detail' page
    And I click "Save" button on 'Inspection Detail' page
   # Add second recommendation
    And I click "Red" icon on "Taillamps" inspection on 'INSPECTION' tab
    And I enter data set in addRecommendationField
      | FieldName         |Value   |
      | Recommendation    | Test2  |
      | OPcode            |        |
      | Hours             | 10     |
      | LaborSale         | 20     |
      | PartSale          | 300    |
      | Price             |        |
    And I click "plus" icon on 'Inspection Detail' page
    And I click "Parts Requested" button on 'Inspection Detail' page
    And I click "Save" button on 'Inspection Detail' page
    And I click "Save" button on 'MPI Details' page
    And I click "RECOMMENDATION" button on 'MPI Details' page
    Then I should see recommendation with "Test1" Description with "2.50" hours with "25.50" labor with "100.00" parts with "125.50" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "Test1" Description on 'RECOMMENDATION' tab
    And I should see recommendation with "Test2" Description with "10.00" hours with "20.00" labor with "300.00" parts with "320.00" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "Test2" Description on 'RECOMMENDATION' tab
    And I click "Mark Completed" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "PARTS REQUESTED" status on 'TECHNICIAN INSPECTION Dashboard'

    #Login as Service advisor, and check RS is in "Parts Requested" status.
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "Parts Requested" status on 'VEHICLE QUEUE' page

    #Login as Parts Clerk, complete and close the mpi
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenP" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    Then I see vehicle with "<VIN>" with "PARTS REQUESTED" status on 'TECHNICIAN INSPECTION Dashboard'
    #Open MPI, mark parts in-stock, and complete the mpi
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "PARTS REQUESTED" status on 'MPI Details' page
    When I click "In Stock" button with "Test1" Description on 'RECOMMENDATION' page
    And I click "Not In Stock" button with "Test2" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
    And I click "Parts Request Completed" button on 'RECOMMENDATION' page

    #Login as Service advisor, and check RS is in "MPI Completed" status.
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "MPI Completed" status on 'VEHICLE QUEUE' page

    #Login as Service Advisor
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    And I select "Select All" in Created By drop down in Search MPI popup
    Then I see vehicle with "<VIN>" with "COMPLETED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI and Mark review
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "COMPLETED" status on 'MPI Details' page
    When I click "Mark Reviewed" button on 'MPI Details' page
    And I click "vehicle queue" icon on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "REVIEWED" status on 'TECHNICIAN INSPECTION Dashboard'

    #Login as Service advisor, and check RS is in "Reviewed" status.
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "Reviewed" status on 'VEHICLE QUEUE' page

    #Login as Service Advisor
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    And I select "Select All" in Created By drop down in Search MPI popup
    Then I see vehicle with "<VIN>" with "REVIEWED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI, approve the service and mark confirmed
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "REVIEWED" status on 'MPI Details' page
    When I click "APPROVED" button with "Test1" Description on 'RECOMMENDATION' page
    And I click "DEFERRED" button with "Test2" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
    And I click "SHOW TOTALS" on 'footbar' tab on 'MPI Details' page
    Then I should see "RECOMMENDED" with "$445.50" on 'footbar' tab
    And I should see "APPROVED" with "$125.50" on 'footbar' tab
    When I click "Confirm" button on 'MPI Details' page
    And I click "vehicle queue" icon on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "CONFIRMED" status on 'TECHNICIAN INSPECTION Dashboard'

    #Login as Service advisor, and check RS is in "Working in Progress" status.
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "Work in Progress" status on 'VEHICLE QUEUE' page

    #Login as Service Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenT" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    Then I see vehicle with "<VIN>" with "CONFIRMED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI and move the vehicle to ready for processing
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "CONFIRMED" status on 'MPI Details' page
    And I should see "Test1" Description with "2.50" labor with "$125.50" price in 'recommendations' section on 'APPROVED SERVICES' tab
    When I click "green" toggle with "Test1" Description on 'APPROVED SERVICES' tab
    And I click "Save" button on 'MPI Details' page
    And I click "Ready for processing" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "READY FOR PROCESSING" status on 'TECHNICIAN INSPECTION Dashboard'

    #Login as Service advisor, and check RS is in "Delivery Ready" status.
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "Delivery Ready" status on 'VEHICLE QUEUE' page

    Examples: this is for data driver testing
      |URL    |DealerName |DealerCode|TAG   |VIN              |Template                   |
      |PROD1  |CHRYSLER QA|99970     |Gate1 |1C3CCBCGXDN674128|Chrysler Vehicle Inspection|


  @tioneflow_RSGate_Skip_Parts
  Scenario Outline: [@tiflow_RSGate_Skip_Parts] run TI from started status to ready for processing status, RS gate moving (skip parts requested)
    #delete service lane through SD as dealer admin
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenD" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    And I delete service lane with <VIN> on 'VEHICLE QUEUE' page
    Then I don't see service lane with "<VIN>" on 'VEHICLE QUEUE' page

    #Login as Service Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenT" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #Create MPI
    And I launch 'Search popup panel' in Inspection dashboard
    And I launch 'Create MPI' popup window in Inspection dashboard
    And I enter data set in Create MPI popup window
    #  for 'Customer/VIN Search' section 
      | FieldName        |Value  |
      | VIN              | <VIN> |
      | Customer Name    |       |
      | Phone            |       |
    And I decode <VIN> in MPI popup windows
    And I enter data set in Create MPI popup window 
    # for the 'Vehicle' Section
      | FieldName        |Value   |
      | Make             |        |
      | Year             |        |
      | Model            |        |
      | Transmission     |        |
      | Engine Size      |        |
      | Drive Train      |        |
      | Mileage          |        |
      | Mileage Value    | 65400  |
      | Months           |        |
      | Months Value     |        |
    And I enter data set in Create MPI popup window
    # for the 'Inspection Information' Section
      | FieldName         |Value      |
      | Servce Advisor    |           |
      | Technician        |           |
      | TAG               | <TAG>     |
      | RO                | flowGate1 |
      | Lot Number        |           |
      | License Plate     |           |
      | Promise Date      | +2        |
      | Promise Time      | 11:45 AM  |
      | Quarter Date      |           |
      | Quarter Time      |           |
      | Inspection Type   | <Template>|
      | Transportation    |           |
      | Save parts        |           |
      | Car Wash          |           |
      | Glove box         |           |
      | Spare tire        |           |
      | Comments          |           |
    And I submit 'Complete' in Create MPI popup window
    Then I see vehicle with "<VIN>" with "CREATED" status on 'TECHNICIAN INSPECTION Dashboard'

    #Login as service advisor, Open RS to check its in MPI started status. 
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "MPI Ready" status on 'VEHICLE QUEUE' page

    #Login as Technician
    # Open the MPI & add a recommendation service with parts requested flag
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenT" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    Then I see vehicle with "<VIN>" with "CREATED" status on 'TECHNICIAN INSPECTION Dashboard'
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I 'Mark Section All OK' on 'MPI Details' page
    And I click "Red" icon on "Headlamps" inspection on 'INSPECTION' tab
    And I enter data set in addRecommendationField
      | FieldName         |Value   |
      | Recommendation    | Test1  |
      | OPcode            |        |
      | Hours             | 2.5    |
      | LaborSale         | 25.5   |
      | PartSale          | 100    |
      | Price             |        |
    And I click "plus" icon on 'Inspection Detail' page
    And I click "Save" button on 'Inspection Detail' page
    And I click "Save" button on 'MPI Details' page
    And I click "RECOMMENDATION" button on 'MPI Details' page
    Then I should see recommendation with "Test1" Description with "2.50" hours with "25.50" labor with "100.00" parts with "125.50" price on 'RECOMMENDATION' tab
    And I click "Mark Completed" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "COMPLETED" status on 'TECHNICIAN INSPECTION Dashboard'

    #Login as Service advisor, and check RS is in "MPI Completed" status. 
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "MPI Completed" status on 'VEHICLE QUEUE' page

    #Login as Service Advisor
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    And I select "Select All" in Created By drop down in Search MPI popup
    Then I see vehicle with "<VIN>" with "COMPLETED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI and Mark review
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "COMPLETED" status on 'MPI Details' page
    When I click "Mark Reviewed" button on 'MPI Details' page
    And I click "vehicle queue" icon on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "REVIEWED" status on 'TECHNICIAN INSPECTION Dashboard'

    #Login as Service advisor, and check RS is in "Reviewed" status. 
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "Reviewed" status on 'VEHICLE QUEUE' page

    #Login as Service Advisor
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    And I select "Select All" in Created By drop down in Search MPI popup
    Then I see vehicle with "<VIN>" with "REVIEWED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI, approve the service and mark confirmed
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "REVIEWED" status on 'MPI Details' page
    When I click "APPROVED" button with "Test1" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
    And I click "SHOW TOTALS" on 'footbar' tab on 'MPI Details' page
    Then I should see "RECOMMENDED" with "$125.50" on 'footbar' tab
    And I should see "APPROVED" with "$125.50" on 'footbar' tab
    When I click "Confirm" button on 'MPI Details' page
    And I click "vehicle queue" icon on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "CONFIRMED" status on 'TECHNICIAN INSPECTION Dashboard'

    #Login as Service advisor, and check RS is in "Working in Progress" status. 
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "Work in Progress" status on 'VEHICLE QUEUE' page

    #Login as Service Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenT" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    Then I see vehicle with "<VIN>" with "CONFIRMED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI and move the vehicle to ready for processing
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "CONFIRMED" status on 'MPI Details' page
    And I should see "Test1" Description with "2.50" labor with "$125.50" price in 'recommendations' section on 'APPROVED SERVICES' tab
    When I click "green" toggle with "Test1" Description on 'APPROVED SERVICES' tab
    And I click "Save" button on 'MPI Details' page
    And I click "Ready for processing" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "READY FOR PROCESSING" status on 'TECHNICIAN INSPECTION Dashboard'

    #Login as Service advisor, and check RS is in "Delivery Ready" status. 
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenA" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "Delivery Ready" status on 'VEHICLE QUEUE' page
    Examples: this is for data driver testing
      |URL    |DealerName |DealerCode|TAG   |VIN              |Template                   |
      |PROD1  |CHRYSLER QA|99970     |Gate1 |1C3CCBCGXDN674128|Chrysler Vehicle Inspection|