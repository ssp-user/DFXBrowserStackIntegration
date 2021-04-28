@TI01 @TI
Feature: Technician Inspection,verify delete all the record
  As a advisor, I can bulk delete all the record
  Description : The purpose of this feature after test condition to delete all the inspection record

  Background: I am on the Login Page in Test2 environment
  Description : The is to initialize to open the properly product environment URL
  I launch browser "(.*)" in "(.*)" and open "(.*)" url
    Given I launch browser "chrome" in "GUI" and open "TEST2DFX"

  @delete01
  Scenario: [@delete01] As a Advisor user, I and open the MPI search page, start inspection and complete the process
  Description : this is after test steps to bulk delete all the inspection record

    #steps are written in TechInspectSearchPage //Darren Deng
    When I login the Application as User "DarrenA" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "audi of anytown usa" and "408A85US" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    And I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "audi of anytown usa" and "408A85US" in Dealer Page
    And I bulk delete all the record in the inspection page
    Then I should see no record in the inspection page


