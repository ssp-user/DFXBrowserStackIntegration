@TI11 @TI
Feature: TI11_TechnicianInspection_Comment, Technician Inspection, main flow with Comments
  
  @tiflow_comment
  Scenario Outline: [@tiflow_comment] run TI from started status to ready for processing status with comments
  Description :
    this scenario has multiple steps:
    1.Create MPI from TI as Service Technician
    2.Complete MPI with some recommendations with parts requested and comments as Service Technician
    3.Complete and Close parts requested by Parts Clerk and update comments
    4.Review and confirm MPI as Service Advisor and update comments
    5.Move MPI to Ready for processing as Service Technician

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
    #  for 'Customer/VIN Search' section 
      | FieldName        |Value                   |
      | VIN              | WAUKJBFM7BA009140      | 
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
      | TAG               | com01                                   |
      | RO                | Comment1                                |
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
    And I input "This is Internal Comment Text" on "Internal Comment" field under "Test1" Description on "Inspection Detail" page
    # add Customer Comments
    And I input "This is Customer Comment Text" on "Customer Comment" field under "Test1" Description on "Inspection Detail" page
    And I click "Save" button on 'Inspection Detail' page
    And I click "Save" button on 'MPI Details' page
    # Check comments showing in recommendation tab
    And I click "RECOMMENDATION" button on 'MPI Details' page
    Then I should see recommendation with "Test1" Description with "2.50" hours with "25.50" labor with "100.00" parts with "125.50" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "Test1" Description on 'RECOMMENDATION' tab
    And I should see "This is Internal Comment Text" on "Tech Comments" field on "title" section with "Test1" Description on "RECOMMENDATION" tab
    And I should see "This is Customer Comment Text" on "Customer Comments" field on "title" section with "Test1" Description on "RECOMMENDATION" tab
    And I click "Mark Completed" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "PARTS REQUESTED" status on 'TECHNICIAN INSPECTION Dashboard'
    #Login as Parts Clerk, complete and close the mpi
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
    And I should see "This is Internal Comment Text" on "Tech Comments" field on "title" section with "Test1" Description on "RECOMMENDATION" tab
    And I should see "This is Customer Comment Text" on "Customer Comments" field on "title" section with "Test1" Description on "RECOMMENDATION" tab
    When I click "In Stock" button with "Test1" Description on 'RECOMMENDATION' page
    # Edit customer comment on recommendation tab
    And I input "Parts clerk edit customer comment in recommendation tab" on "Customer Comments" field on "title" section with "Test1" Description on "RECOMMENDATION" tab
    And I input "Parts Clerk edit customer comment in Description" on "Customer Comments" field on "description" section with "Test1" Description on "RECOMMENDATION" tab
    And I click "Save" button on 'MPI Details' page
    And I click "Parts Request Completed" button on 'RECOMMENDATION' page
    #Login as Service Advisor
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
    And I should see "Parts clerk edit customer comment in recommendation tab" on "Customer Comments" field on "title" section with "Test1" Description on "RECOMMENDATION" tab
    And I should see "Parts Clerk edit customer comment in Description" on "Customer Comments" field on "description" section with "Test1" Description on "RECOMMENDATION" tab
    When I click "Mark Reviewed" button on 'MPI Details' page
    And I click "vehicle queue" icon on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "REVIEWED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI, approve the service and mark confirmed
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "REVIEWED" status on 'MPI Details' page
    When I click "APPROVED" button with "Test1" Description on 'RECOMMENDATION' page
    # update comment by advisor
    And I input "Advisor edit customer comment in recommendation tab" on "Customer Comments" field on "title" section with "Test1" Description on "RECOMMENDATION" tab
    And I input "Advisor edit customer comment in Description" on "Customer Comments" field on "description" section with "Test1" Description on "RECOMMENDATION" tab
    And I click "Save" button on 'MPI Details' page
    And I click "SHOW TOTALS" on 'footbar' tab on 'MPI Details' page
    Then I should see "RECOMMENDED" with "$125.50" on 'footbar' tab
    And I should see "APPROVED" with "$125.50" on 'footbar' tab
    When I click "Confirm" button on 'MPI Details' page
#    And I click "vehicle queue" icon on 'MPI Details' page  // test2 return
    Then I see vehicle with "<VIN>" with "CONFIRMED" status on 'TECHNICIAN INSPECTION Dashboard'
    #Login as Service Technician
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
    # Check Comment in Recommendation page
    And I click "RECOMMENDATION" button on 'MPI Details' page
    Then I should see "Advisor edit customer comment in recommendation tab" on "Customer Comments" field on "title" section with "Test1" Description on "RECOMMENDATION" tab
    And I should see "Advisor edit customer comment in Description" on "Customer Comments" field on "description" section with "Test1" Description on "RECOMMENDATION" tab
    When I click "APPROVED SERVICES" button on 'MPI Details' page
    Then I should see "Test1" Description with "2.50" labor with "$125.50" price in 'recommendations' section on 'APPROVED SERVICES' tab
    When I click "green" toggle with "Test1" Description on 'APPROVED SERVICES' tab
    And I click "Save" button on 'MPI Details' page
    And I click "Ready for processing" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "READY FOR PROCESSING" status on 'TECHNICIAN INSPECTION Dashboard'
    Examples: this is for data driver testing
      |URL        |DealerName            |DealerCode|TAG   |VIN              |
      |PRD2AUDI   |Audi of Anytown Canada|408A85CA  |com01 |WAUKJBFM7BA009140|
#      |TST2AUDI   |Audi of Anytown Canada|408A85CA  |com01 |WAUKJBFM7BA009140|
#      |STG2AUDI   |Audi of Anytown Canada|408A85CA  |com01 |WAUKJBFM7BA009140|
