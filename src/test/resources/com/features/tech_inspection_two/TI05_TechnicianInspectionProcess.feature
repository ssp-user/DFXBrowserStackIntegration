@TI05 @TI
Feature: Technician Inspection, Verify the function complete inspection process as technician
  As a technician, I can complete the inspection process
  Description : The purpose of this feature is to test inspection process for Audi 29 Point Inspection template

  Background: I am on the Login Page in Test2 environment
  Description: The is to initialize to open the properly URL
    Given I launch browser "chrome" in "GUI" and open "TEST2DFX"

  @inspection01
  Scenario: [@inspection01] As a Technician user, I and open the MPI search page, start inspection and complete the process
  Description:  common steps+check all mandatory inspection+mark inspection completed+check mark inspection completed
    # steps are written in ServiceTabSteps //Darren Deng
    Given I login the Application as User "DarrenD" and password "welcome"
    When  I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "audi of anytown usa" and "408A85US" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    When I login the Application as User "DarrenTech" and password "Welcome"
    And I select the Dealer with "audi of anytown usa" and "408A85US" in Dealer Page
    And I choose the result with non completed status
    And I update the EXTERIOR section
    And I update the INTERIOR section
    And I update the UNDER HOOD section
    And I update the FLUIDS section
          | FieldName                    |Value                  |
          | coolantDegree                |  -33.00               |
    And I update the UNDER VEHICLE section
    And I update the TIRES section
          | FieldName                    |Value                  |
          | leftFrontTireIncoming        |  36                   |
          | rightFrontTireIncoming       |  36                   |
          | rightRearTireIncoming        |  36                   |
          | leftRearTireIncoming         |  36                   |
          | leftFrontTireAdjusted        |  34                   |
          | rightFrontTireAdjusted       |  34                   |
          | rightRearTireAdjusted        |  34                   |
          | leftRearTireAdjusted         |  34                   |
          | leftFrontTireTreadDepth      |                       |
          | rightFrontTireTreadDepth     |   7                   |
          | rightRearTireTreadDepth      |                       |
          | leftRearTireTreadDepth       |                       |
          | TorqueSpecTreadDepth         |  20.00                |
    #And I update the BRAKE CONDITION section
    And I update the COMMENTS section
    And I update the ADDITIONAL REPAIRS REQUIRED section
    And I save
    And I mark completed
    Then I should see status as completed



