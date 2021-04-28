@TI09 @TI
Feature: TI09_TechnicianInspection_Buttons, check buttons are working properly
  
  @tibuttons
  Scenario Outline: [@tibuttons] check TI main buttons. eg. View History, View Menu ...
  Description :
    this scenario has multiple steps:
    1.Create MPI from TI as Service Technician
    2.Check View History buttonOutline
    3.Check View Menu button
    4.Check Status log button

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
    #  for 'Customer/VIN Search' section (Test Brock)
      | FieldName        |Value                   |
      | VIN              | WAUEF78E78A162935      | 
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
      | TAG               | Butt1                                   |
      | RO                | Button1                                 |
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
    # Open the MPI
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    # Check History button 
    And I click "View History" button on 'MPI Details' page
    Then I should see "Multi Point Inspection Report" title on popup
    When I click "close" button on "View History" popup
    # Check Status log button
    And I click "Status Log" button on 'MPI Details' page
    Then I should see "Update History" title on popup
    When I click "close" button on "Status Log" popup
    # Check Menu button
    And I click "View Menu" button on 'MPI Details' page
    Then I should see "Audi of Anytown Canada Factory Scheduled Maintenance Guide" on link
    Examples: this is for data driver testing
      |URL        |DealerName            |DealerCode|TAG   |VIN              |
      |PRD2AUDI   |Audi of Anytown Canada|408A85CA  |flow2 |WAUEF78E78A162935|
#      |TST2AUDI   |Audi of Anytown Canada|408A85CA  |flow2 |WAUEF78E78A162935|
#      |STG2AUDI   |Audi of Anytown Canada|408A85CA  |flow2 |WAUEF78E78A162935|