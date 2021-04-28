@TI13 @TI
Feature: TI13_TechnicianInspection_Techcam, Technician Inspection with Tech Cam

  @tiflow_techCam
  Scenario Outline: [@tiflow_parts] run TI from started status to ready for processing status
  Description :
  this scenario has multiple steps:
  1.Create MPI from TI as Service Technician
  2.Complete MPI with some recommendations with parts requested as Service Technician
  3.Click into Camera and take photo, delete them from both techcam page and inspection detail page
  4.Verify the recommendation attach to the photo

    #Login as technician
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
      | FieldName     | Value |
      | VIN           | <VIN> |
      | Customer Name |       |
      | Phone         |       |
    And I decode <VIN> in MPI popup windows
    And I enter data set in Create MPI popup window
    # for the 'Vehicle' Section
      | FieldName     | Value |
      | Make          |       |
      | Year          |       |
      | Model         |       |
      | Transmission  |       |
      | Engine Size   |       |
      | Drive Train   |       |
      | Mileage       |       |
      | Mileage Value | 65400 |
      | Months        |       |
      | Months Value  |       |
    And I enter data set in Create MPI popup window
    # for the 'Inspection Information' Section
      | FieldName       | Value                           |
      | Servce Advisor  |                                 |
      | Technician      |                                 |
      | TAG             | flow13                          |
      | RO              | ROflow13                        |
      | Lot Number      |                                 |
      | License Plate   |                                 |
      | Promise Date    | +2                              |
      | Promise Time    |                                 |
      | Quarter Date    |                                 |
      | Quarter Time    |                                 |
      | Inspection Type | Audi Canada 29 Point Inspection |
      | Transportation  |                                 |
      | Save parts      |                                 |
      | Car Wash        |                                 |
      | Glove box       |                                 |
      | Spare tire      |                                 |
      | Comments        |                                 |
    And I submit 'Complete' in Create MPI popup window
    Then I see vehicle with "<VIN>" with "CREATED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI & add a recommendation service and access TechCam, take 5 photos
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I 'Mark Section All OK' on 'MPI Details' page
    And I click "Red" icon on "Windshield Condition" inspection on 'INSPECTION' tab
    And I click "Camera" icon on 'Inspection Detail' page
    Then I should see "CAMERA VIEW" icon on "Tech Cam" page
    And I click "CAPTURE" icon on "Tech Cam" page "5" times
    Then I should see "You have reached the limit of 5 images per inspection item" message on "Tech Cam" page
    # Test delete photo from Tech Cam page and the inspection detail page
    And I delete "2" photos on "Tech Cam" page
    Then I should see "3" photo left on "Tech Cam" page
    And I click "close" icon on 'Tech Cam' page
    And I delete "2" photos on "Inspection Detail" page
    Then I should see "1" photo left on "Inspection Detail" page
    #Test add a recommendation
    When I enter data set in addRecommendationField
      | FieldName      | Value               |
      | Recommendation | TechCam Test Joseph |
      | OPcode         |                     |
      | Hours          | 2.5                 |
      | LaborSale      | 25.5                |
      | PartSale       | 100                 |
      | Price          |                     |
    And I click "plus" icon on 'Inspection Detail' page
    And I add attachment "TechCam Test Joseph" recommendation to the photo on "Inspection Detail" page
    Then I should see "TechCam Test Joseph" recommendation attached to the photo on "Inspection Detail" page
    When I click "Camera" icon on 'Inspection Detail' page
    Then I should see "TechCam Test Joseph" recommendation attached to the photo on "Tech Cam" page
    Examples: this is for data driver testing
      | URL      | DealerName             | DealerCode | TAG    | VIN               |
      | PRD2AUDI | Audi of Anytown Canada | 408A85CA   | flow13 | WAUFGAFB0BN005982 |
#      | TST2AUDI | Audi of Anytown Canada | 408A85CA   | flow13 | WAUFGAFB0BN005982 |
#      | STG2AUDI | Audi of Anytown Canada | 408A85CA   | flow13 | WAUFGAFB0BN005982 |
