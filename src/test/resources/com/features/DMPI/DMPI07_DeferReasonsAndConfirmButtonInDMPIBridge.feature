@DMPI07 @DMPI_SMOKE

Feature: DMPI EMail check
         DMPI, login as advisor to create service lane in Advisor checkin
         login as technician to complete the service in Technician Inspection
         login as advisor to send message to customer via Email

  Scenario Outline: Dynamic multi point inspection, verify message to customer By Email
    #create service lane through AC as ADVISOR
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "NissanAdvisor11" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Advisor Check-In" Application on 'Main Page'
    And I decode "<VIN>" on 'Advisor Check-In' page
    And I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I change "Hakan" in "FIRST NAME" field on "CUSTOMER" page
    And I change "Automation" in "LAST NAME" field on "CUSTOMER" page
    And I change "dfxcucumbertest@gmail.com" in "EMAIL" field on "CUSTOMER" page
    And I click "NEXT" button on 'CUSTOMER' page
    And I add following customer concerns on 'CONCERN' page
      |Concern name       |
      |AC/Heating Concern |
      |Battery Concern    |
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button on 'HISTORY' page
    And I click "NEXT" button on 'SERVICES' page
    And I select "SUV/MINIVAN" on 'WALK AROUND' page
    And I click "ALL OK" button on 'WALK AROUND' page
    And I click "NEXT" button on 'WALK AROUND' page
    And I update <TAG> on "ASSIGN R.O." page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    And I authorize and sign the agreement on 'AUTHORIZATION' page
    And I click "COMPLETE" button on 'AUTHORIZATION' page
    Then I should see RO push completed
    #complete service and add two recommendation in Technician Inspection as Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "Nissantech4" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #And I search vehicle <TAG> in 'TECHNICIAN INSPECTION Dashboard'
    And I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "Red" icon on "AC/Heating Concern" concern on 'INSPECTION' tab
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
    And I click "Red" icon on "Horn Operation" inspection on 'INSPECTION' tab
    And I enter data set in addRecommendationField
      | FieldName         |Value                                    |
      | Recommendation    | Test1                                   |
      | OPcode            |                                         |
      | Hours             | 2.5                                     |
      | LaborSale         | 25.5                                    |
      | PartSale          | 100                                     |
      | Price             |                                         |
    And I click "plus" icon on 'Inspection Detail' page
    And I click "Save" button on 'Inspection Detail' page
    And I click "Orange" icon on "Left Wiper Blade" inspection on 'INSPECTION' tab
    And I enter data set in addRecommendationField
      | FieldName         |Value                                    |
      | Recommendation    | Test2                                   |
      | OPcode            |                                         |
      | Hours             | 3.0                                     |
      | LaborSale         | 35.5                                    |
      | PartSale          | 200                                     |
      | Price             |                                         |
    And I click "plus" icon on 'Inspection Detail' page
    And I click "Save" button on 'Inspection Detail' page
    And I click "Save" button on 'MPI Details' page
    And I click "Mark Completed" button on 'MPI Details' page
    Then I see vehicle with "<VIN>" with "COMPLETED" status on 'TECHNICIAN INSPECTION Dashboard'
    #Login as service advisor, Open DMPI bridge through service dashboard
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "NissanAdvisor11" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "MPI Completed" status on 'VEHICLE QUEUE' page
    When I click "DETAILS" button under the service lane with <VIN> on 'VEHICLE QUEUE' page
    And I click Document Type "Recommendations" under Final Documents on 'SERVICE VISIT' page
    And I get "If answered by" time on "Promise Time" section on "SERVICE VISIT" page
    And I get "Deliver by" time on "Promise Time" section on "SERVICE VISIT" page
    #Check Defer reasons drop-own list for each recommendation and select one
    #TestAC concern
    When I click "--Select an option--" dropdown under "TestAC" description on "SERVICE VISIT" page
    Then I see following dropdown option under "TestAC" description on "SERVICE VISIT" page
      |Dropdown_option                               |
      |--Select an option--                          |
      |Approved                                      |
      |Deferred - Time constraint                    |
      |Deferred - Cost constraint                    |
      |Deferred - Cannot Afford                      |
      |Deferred - Changing Vehicle                   |
      |Deferred - Alternate transportation constraint|
      |Deferred - Parts not available                |
      |Deferred - Tech not available                 |
      |Deferred - No reason given                    |
      |Deferred - Declined by Advisor                |
    And I click "Approved" from "APPROVE OR DEFER" dropdown under "TestAC" description on "SERVICE VISIT" page
    Then I see "Approved" selected from "APPROVE OR DEFER" dropdown under "TestAC" description on "SERVICE VISIT" page
    #TestBattery concern
    When I click "--Select an option--" dropdown under "TestBattery" description on "SERVICE VISIT" page
    Then I see following dropdown option under "TestBattery" description on "SERVICE VISIT" page
      |Dropdown_option                               |
      |--Select an option--                          |
      |Approved                                      |
      |Deferred - Time constraint                    |
      |Deferred - Cost constraint                    |
      |Deferred - Cannot Afford                      |
      |Deferred - Changing Vehicle                   |
      |Deferred - Alternate transportation constraint|
      |Deferred - Parts not available                |
      |Deferred - Tech not available                 |
      |Deferred - No reason given                    |
      |Deferred - Declined by Advisor                |
    And I click "Deferred - Cannot Afford" from "APPROVE OR DEFER" dropdown under "TestBattery" description on "SERVICE VISIT" page
    Then I see "Deferred - Cannot Afford" selected from "APPROVE OR DEFER" dropdown under "TestBattery" description on "SERVICE VISIT" page
    When I click "INSPECTION" tab on 'SERVICE VISIT' page
    #Test1 recommendation
    When I click "--Select an option--" dropdown under "Test1" description on "SERVICE VISIT" page
    Then I see following dropdown option under "Test1" description on "SERVICE VISIT" page
      |Dropdown_option                               |
      |--Select an option--                          |
      |Approved                                      |
      |Deferred - Time constraint                    |
      |Deferred - Cost constraint                    |
      |Deferred - Cannot Afford                      |
      |Deferred - Changing Vehicle                   |
      |Deferred - Alternate transportation constraint|
      |Deferred - Parts not available                |
      |Deferred - Tech not available                 |
      |Deferred - No reason given                    |
      |Deferred - Declined by Advisor                |
    And I click "Deferred - Time constraint" from "APPROVE OR DEFER" dropdown under "Test1" description on "SERVICE VISIT" page
    Then I see "Deferred - Time constraint" selected from "APPROVE OR DEFER" dropdown under "Test1" description on "SERVICE VISIT" page
    #Test2 recommendation
    When I click "--Select an option--" dropdown under "Test2" description on "SERVICE VISIT" page
    Then I see following dropdown option under "Test2" description on "SERVICE VISIT" page
      |Dropdown_option                               |
      |--Select an option--                          |
      |Approved                                      |
      |Deferred - Time constraint                    |
      |Deferred - Cost constraint                    |
      |Deferred - Cannot Afford                      |
      |Deferred - Changing Vehicle                   |
      |Deferred - Alternate transportation constraint|
      |Deferred - Parts not available                |
      |Deferred - Tech not available                 |
      |Deferred - No reason given                    |
      |Deferred - Declined by Advisor                |
    And I click "Deferred - Alternate transportation constraint" from "APPROVE OR DEFER" dropdown under "Test2" description on "SERVICE VISIT" page
    Then I see "Deferred - Alternate transportation constraint" selected from "APPROVE OR DEFER" dropdown under "Test2" description on "SERVICE VISIT" page
    Then I see "CONFIRM" button "enabled" on "SERVICE VISIT" page
    When I click "CONFIRM" button on 'SERVICE VISIT' page
    Then I see "CONFIRM" button "disabled" on "SERVICE VISIT" page
    #After refreshing the page
    And I click "STATUS LOG" button on 'SERVICE VISIT' page
    And I see following logs on "STATUS LOG" section
      |logsName                  |logs                                                                     |
      |Service Advisor's Approval|Service Advisor confirmed selections on {current.date} at {current.time} |
    Examples: this is for data driver testing
      |URL       |DMS |DealerName            |DealerCode |Odometer|TAG     |VIN              |
      |PRD2NISS  | ?  |Nissan of Anytown USA |DONOTCHANGE|60000   |random  |5N1AR2MM1EC716183|