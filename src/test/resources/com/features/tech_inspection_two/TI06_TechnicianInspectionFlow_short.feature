@TI06 @TI
Feature: TI06_TechnicianInspectionFlow_short, main flow without parts requested
  
  @tiflow_1
  Scenario Outline: [@tiflow_1] run TI from started status to ready for processing status
  Description :
    this scenario has multiple steps:
    1.Create MPI from TI as Service Technician
    2.Complete MPI without parts requested as Service Technician
    3.Review and confirm MPI as Service Advisor
    4.Move MPI to Ready for processing as Service Technician

    #Login as Service Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "auditechca" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #Create MPI
    #And I select the Dealer with "audi of anytown usa" and "408A85US" in Dealer Page
    And I launch 'Search popup panel' in Inspection dashboard
    And I launch 'Create MPI' popup window in Inspection dashboard
    And I enter data set in Create MPI popup window
    #  for 'Customer/VIN Search' section
      | FieldName        |Value                   |
      | VIN              | WAUBFAFL5AN066849      |
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
      | FieldName         |Value                            |
      | Servce Advisor    |                                 |
      | Technician        |                                 |
      | TAG               | flow1                           |
      | RO                | ROflow1                         |
      | Lot Number        |                                 |
      | License Plate     |                                 |
      | Promise Date      | +2                              |
      | Promise Time      | 11:45 AM                        |
      | Quarter Date      |                                 |
      | Quarter Time      |                                 |
      | Inspection Type   | Audi Canada 29 Point Inspection |
      | Transportation    |                                 |
      | Save parts        |                                 |
      | Car Wash          |                                 |
      | Glove box         |                                 |
      | Spare tire        |                                 |
      | Comments          |                                 |
    And I submit 'Complete' in Create MPI popup window
    Then I see vehicle with "<VIN>" with "CREATED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I 'Mark Section All OK' on 'MPI Details' page
    And I click "Save" button on 'MPI Details' page
    And I click "Mark Completed" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "COMPLETED" status on 'TECHNICIAN INSPECTION Dashboard'
    #Login as Service Advisor
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "audiadvisorca" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    And I select "Select All" in Created By drop down in Search MPI popup
    Then I see vehicle with "<VIN>" with "COMPLETED" status on 'TECHNICIAN INSPECTION Dashboard'
    #if see “No matching records found”
    # When I click "Created By" dropdown on "popup window" page
    # And I select "Select All" in dropdown
    # And I click "Search" button on "popout window" page
    # Open the MPI and Mark review
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "COMPLETED" status on 'MPI Details' page
    When I click "Mark Reviewed" button on 'MPI Details' page
    And I click "vehicle queue" icon on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "REVIEWED" status on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI and Mark confirmed
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "REVIEWED" status on 'MPI Details' page
    When I click "Confirm" button on 'MPI Details' page
#    And I click "vehicle queue" icon on 'MPI Details' page  // Test2 return to MPI page after click 'Confirm' button
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
    When I click "Ready for processing" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "READY FOR PROCESSING" status on 'TECHNICIAN INSPECTION Dashboard'
    Examples: this is for data driver testing
     |URL        |DealerName            |DealerCode|TAG   |VIN              |
     |PRD2AUDI   |Audi of Anytown Canada|408A85CA  |flow1 |WAUBFAFL5AN066849|
#     |TST2AUDI   |Audi of Anytown Canada|408A85CA  |flow1 |WAUBFAFL5AN066849|
#     |STG2AUDI   |Audi of Anytown Canada|408A85CA  |flow1 |WAUBFAFL5AN066849|