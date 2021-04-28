@TI04 @TI
Feature: Technician Inspection, Verify the function to add recommendation service
  As a technician , I can create recommend service in the inspection page
  Description : The purpose of this feature is to test recommand service

  Background: I am on the Login Page in Test2 environment
  Description : The is to initialize to open the properly URL
    Given I launch browser "chrome" in "GUI" and open "TEST2DFX"

  @recommand01
  Scenario: [@recommand01] As a Technician user, I and open the MPI search page, start one inspection and adding recommend service
  Description :  This is only create test
    # steps are written in ServiceTabSteps //Darren Deng
    Given I login the Application as User "DarrenD" and password "welcome"
    When  I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "audi of anytown usa" in DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    When I login the Application as User "DarrenTech" and password "Welcome"
    And I select the Dealer with "audi of anytown usa" and "408A85US" in Dealer Page
    And I choose the first result and click on it
    When I click on future attention icon to add recommend service
    And I launch inspection tab in the technician inspection page
    #And I am add comment on recommend service
    And I enter data set in addRecommendationField
      | FieldName         |Value                                    |
      | Recommendation    | Test1                                   |
      | OPcode            |                                         |
      | Hours             | 2.5                                     |
      | LaborSale         | 25.5                                    |
      | PartSale          | 100                                     |
      | Price             |                                         |
   # And I am taking photo for recommend service
    And I click on save button
    Then I click on recommendation to view the recommend service i've saved


