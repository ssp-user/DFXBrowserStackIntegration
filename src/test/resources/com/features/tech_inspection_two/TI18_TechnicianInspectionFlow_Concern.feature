@TI18 @TI
Feature: TI18_TechnicianInspectionFlow_Concern, Technician Inspection, main flow with concern
  
  @tiflow_parts
  Scenario Outline: [@tiflow_parts] run TI from started status to ready for processing status
  Description :
    this scenario has multiple steps:
    1.do RO push from AC with adding concern
    2.Complete MPI with some concerns with parts along with recommendation requested as Service Technician
    3.Complete and Close parts requested by Parts Clerk
    4.Review and confirm MPI as Service Advisor
    5.Move MPI to Ready for processing as Service Technician
    #RO push
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "audiadvisorca" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Advisor Check-In" Application on 'Main Page'
#    And I decode <VIN> on 'Advisor Check-In' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    And I enter "50000" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I add following customer concerns on 'CONCERN' page
      |Concern name       |
      |AC Heating Concern |
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
    #Login as Service Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "auditechca" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    # Open the MPI & add a recommendation service with parts requested flag
    And I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    # Add recommendation for concern
    And I click "Red" icon on "AC Heating Concern" concern on 'INSPECTION' tab
    And I enter data set in addRecommendationField
      | FieldName         |Value      |
      | Recommendation    | TestAC    |
      | OPcode            |           |
      | Hours             | 3         |
      | LaborSale         | 30        |
      | PartSale          | 200       |
      | Price             |           |
    And I click "plus" icon on 'Inspection Detail' page
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
    And I click "Save" button on 'Inspection Detail' page
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
    And I click "Save" button on 'MPI Details' page
    And I click "RECOMMENDATION" button on 'MPI Details' page
    Then I should see recommendation with "Test1" Description with "2.50" hours with "25.50" labor with "100.00" parts with "125.50" price on 'RECOMMENDATION' tab
    And I should see "Parts Requested" flag with "ON" with "Test1" Description on 'RECOMMENDATION' tab
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
    When I click "In Stock" button with "Test1" Description on 'RECOMMENDATION' page
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
    # Open the MPI, approve the service and mark confirmed
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "SHOW" button on 'MPI Details' page
    Then I should see "REVIEWED" status on 'MPI Details' page
    When I click "APPROVED" button with "Test1" Description on 'RECOMMENDATION' page
    And I click "APPROVED" button with "TestBattery" Description on 'RECOMMENDATION' page
    And I click "APPROVED" button with "TestAC" Description on 'RECOMMENDATION' page
    And I click "Save" button on 'MPI Details' page
    And I click "SHOW TOTALS" on 'footbar' tab on 'MPI Details' page
    Then I should see "RECOMMENDED" with "$435.50" on 'footbar' tab
    And I should see "APPROVED" with "$435.50" on 'footbar' tab
    When I click "Confirm" button on 'MPI Details' page
#    And I click "vehicle queue" icon on 'MPI Details' page // TEST2 RETURN
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
    And I should see "Test1" Description with "2.50" labor with "$125.50" price in 'recommendations' section on 'APPROVED SERVICES' tab
    When I click "green" toggle with "AC Heating Concern" Description on 'APPROVED SERVICES' tab
    When I click "green" toggle with "Battery Concern" Description on 'APPROVED SERVICES' tab
    When I click "green" toggle with "Test1" Description on 'APPROVED SERVICES' tab
    When I click "green" toggle with "TestAC" Description on 'APPROVED SERVICES' tab
    When I click "green" toggle with "TestBattery" Description on 'APPROVED SERVICES' tab
    And I click "Save" button on 'MPI Details' page
    And I click "Ready for processing" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "READY FOR PROCESSING" status on 'TECHNICIAN INSPECTION Dashboard'
    Examples: this is for data driver testing
      |URL        |DealerName            |DealerCode|TAG   |VIN              |
      |PRD2AUDI   |Audi of Anytown Canada|408A85CA  |flow3 |WAUDH28D4YA098594|
#      |TST2AUDI   |Audi of Anytown Canada|408A85CA  |flow3 |WAUDH28D4YA098594|
#      |STG2AUDI   |Audi of Anytown Canada|408A85CA  |flow3 |WAUDH28D4YA098594|