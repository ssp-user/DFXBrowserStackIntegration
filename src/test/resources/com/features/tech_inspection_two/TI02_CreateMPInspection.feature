@TI02 @TI
Feature: Technician Inspection, verify the function to create a new record and delete it in Dashboard by load data
  As a technician or advisor role , I can create new record and also can delete it in Dashboard Page
  Description: The purpose of this feature is to test create function by role

  # This is the example test background
  Background: I am on the Login Page in Test2 environment
  Description : The is to initialize to open the properly URL
    Given I launch browser "chrome" in "GUI" and open "TEST2DFX"

  @create01 @Ignore
  Scenario: [@create01] As a Technician user, I and open the MPI create window and create a new record in dashboard
  by test data provided.
  Description:  This is only create test
    # This is the example test Scenario @Create01
    Given I login the Application as User "automation" and password "dealerfxqa"
    When  I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "audi of anytown usa" and "408A85US" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #And I login the Application as User "automation" and password "dealerfxqa"
    And I login the Application as User "DarrenTech" and password "Welcome"
    And I select the Dealer with "audi of anytown usa" and "408A85US" in Dealer Page
    And I launch 'Search popup panel' in Inspection dashboard
    And I launch 'Create MPI' popup window in Inspection dashboard
    And I enter data set in Create MPI popup window
    #  for 'Customer/VIN Search' section
      | FieldName        |Value                   |
      | VIN              | 111111                 |
      | Customer Name    | Bill GatesABG          |
      | Phone            | 4162342345             |
    And I enter data set in Create MPI popup window
    # for the 'Vehicle' Section
      | FieldName        |Value                   |
      | Make             | VOLKSWAGEN             |
      | Year             | 2014                   |
      | Model            | CC (2.0L)              |
      | Transmission     |                        |
      | Engine Size      |                        |
      | Drive Train      |                        |
      | Mileage          | Y                      |
      | Mileage Value    | 65400                  |
      | Months           |                        |
      | Months Value     |                        |
    And I enter data set in Create MPI popup window
    # for the 'Customer Service / History Information' Section
      | FieldName        |Value                   |
      | First Name       | Bill                   |
      | Last Name        | GatesABG               |
      | Business Check   |                        |
      | Address          | 80 Tiverton Ct         |
      | City             | Markham                |
      | Country          | Canada                 |
      | State/Province   | Ontario                |
      | Postal Code      | L3R 0G4                |
      | County           | Ontario                |
      | Default Phone    | 4161231234             |
      | Cell Phone       | 6471231232             |
      | Email            |Darren_deng@dealer-fx.com |
      | Contact Method   |                        |
      | Prefered language|                        |
    And I enter data set in Create MPI popup window
    # for the 'Inspection Information' Section
      | FieldName         |Value                                   |
    #  | Servce Advisor    | DServiceAd TfxAA                       |
      | Servce Advisor    | AdvisorW DFX                           |
      | Technician        | QA super tech                          |
      | TAG               | TAG56                                  |
      | RO                | RO56                                   |
      | Lot Number        |                                        |
      | License Plate     | BCFX560                                |
      | Promise Date      | +2                                     |
      | Promise Time      | 11:30 AM                               |
      | Quarter Date      |                                        |
      | Quarter Time      |                                        |
      | Inspection Type   | Standard Electronic Vehicle Inspection |
      | Transportation    | Own Ride                               |
      | Save parts        |                                        |
      | Car Wash          | y                                      |
      | Glove box         |                                        |
      | Spare tire        |                                        |
      | Comments          | is vip                                 |
    And I submit 'Complete' in Create MPI popup window
    Then I see the record on the first line in dashboard
      | FieldName         |Value                  |
      | VIN               |                       |
      | TAG               | TAG56                 |
      | CUSTOMER NAME     | Bill GatesABG         |
      | RO                | RO56                  |
      | INSPECTION STATUS | CREATED               |

  @create02
  Scenario: [@create02] As a Advisor user, I and open a create window and create a new record in dashboard
  by test data provided, also can delete this record
  Description :  This is create and delete test
    # This is the example test Scenario @Create01
    Given I login the Application as User "automation" and password "dealerfxqa"
    When  I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "audi of anytown usa" and "408A85US" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #And I login the Application as User "automation" and password "dealerfxqa"
    And I login the Application as User "DarrenAd" and password "Welcome"
    And I select the Dealer with "audi of anytown usa" and "408A85US" in Dealer Page
    And I launch 'Search popup panel' in Inspection dashboard
    And I launch 'Create MPI' popup window in Inspection dashboard
    And I enter data set in Create MPI popup window
    #  for 'Customer/VIN Search' section
      | FieldName        |Value                   |
      | VIN              | WAUEFGFF2ABCD1234      |
      | Customer Name    | Bill GatesABA          |
      | Phone            | 4162342345             |
      | Make             | VOLKSWAGEN             |
      | Year             | 2014                   |
      | Model            | CC (2.0L)              |
      | Transmission     |                        |
      | Engine Size      |                        |
      | Drive Train      |                        |
      | Mileage          | Y                      |
      | Mileage Value    | 65400                  |
      | Months           |                        |
      | Months Value     |                        |
      | First Name       | Bill                   |
      | Last Name        | GatesABA               |
      | Business Check   |                        |
      | Address          | 80 Tiverton Ct         |
      | City             | Markham                |
      | Country          | Canada                 |
      | State/Province   | Ontario                |
      | Postal Code      | L3R 0G4                |
      | County           | Ontario                |
      | Default Phone    | 4161231234             |
      | Cell Phone       | 64712312324            |
      | Email            |Darren_deng@dealer-fx.com |
      | Contact Method   |                        |
      | Preferred language|                        |
    And I enter data set in Create MPI popup window
    # for the 'Customer Information / Service History' Section
      | FieldName         |Value                  |
      | Service Advisor   | AdvisorW DFX          |
      | Technician        | QA super tech         |
      | TAG               | TAG57                 |
      | RO                | RO57                  |
      | Lot Number        |                       |
      | License Plate     | BCFX570               |
      | Promise Date      | +2                    |
      | Promise Time      | 11:30 AM              |
      | Quarter Date      |                       |
      | Quarter Time      |                       |
      | Inspection Type   | 1                     |
      | Transportation    | Own Ride              |
      | Save parts        |                       |
      | Car Wash          | y                     |
      | Glove box         |                       |
      | Spare tire        |                       |
      | Comments          | is vip                |
    And I submit 'Complete' in Create MPI popup window
    Then I see the record on the first line in dashboard
      | FieldName         |Value                  |
      | VIN               |                       |
      | TAG               | TAG57                 |
      | CUSTOMER NAME     | Bill GatesABA         |
      | RO                | RO57                  |
      | INSPECTION STATUS | CREATED               |
    When I delete the first line record in dashboard
    Then I do not see the record on the first line in dashboard
      | FieldName         |Value                  |
      | VIN               |                       |
      | TAG               | TAG57                 |
      | CUSTOMER NAME     | Bill GatesABA         |
      | RO                | RO57                  |
      | INSPECTION STATUS | CREATED               |

