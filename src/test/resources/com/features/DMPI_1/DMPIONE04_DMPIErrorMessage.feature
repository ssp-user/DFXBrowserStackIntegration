@DMPIONE04 @DMPI_SMOKE

Feature: DMPI present to customer for FCA environment
         DMPI, login as advisor to create service lane in Advisor checkin
         login as technician to complete the service in Technician Inspection
         login as advisor to present DMPI to customer

  Scenario Outline: Dynamic multi point inspection, verify 3 error message
    #create service lane through AC as ADVISOR
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Advisor Check-In" Application on 'Main Page'
    And I decode "<VIN>" on 'Advisor Check-In' page
    And I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
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
    #complete service and add two recommendation in Technician Inspection as Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanT" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    And I login the Application as User "DarrenNissanT" and password "Welcome"
    And I select the Dealer with "Nissan of Anytown USA" and "5522" in Dealer Page
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
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "MPI Completed" status on 'VEHICLE QUEUE' page
    When I click "DETAILS" button under the service lane with <VIN> on 'VEHICLE QUEUE' page
    And I click Document Type "Recommendations" under Final Documents on 'SERVICE VISIT' page
    And I get "If answered by" time on "Promise Time" section on "SERVICE VISIT" page
    And I get "Deliver by" time on "Promise Time" section on "SERVICE VISIT" page
    #verify "If answered by" time error message
    And I adjust the "If answered by" time "before" current time on "Promise Time" section on "SERVICE VISIT" page
    Then I see "The Respond By Time cannot be in the past" error message "show" on "Promise Time" section on "SERVICE VISIT" page
    And I adjust the "If answered by" time "after" current time on "Promise Time" section on "SERVICE VISIT" page
    Then I see "The Respond By Time cannot be in the past" error message "disappeared" on "Promise Time" section on "SERVICE VISIT" page
    #verify "Delivery by" time error message
    And I adjust the "Delivery by" time "before" current time on "Promise Time" section on "SERVICE VISIT" page
    Then I see "The Promise Time cannot be in the past" error message "show" on "Promise Time" section on "SERVICE VISIT" page
    And I adjust the "Delivery by" time "after" current time on "Promise Time" section on "SERVICE VISIT" page
    Then I see "The Promise Time cannot be in the past" error message "disappeared" on "Promise Time" section on "SERVICE VISIT" page
    #verify "If answered by" time is after "Deliver by" time error message
    And I adjust the "If answered by" time "after" "Delivery by" time on "Promise Time" section on "SERVICE VISIT" page
    Then I see "The Respond By Time must be earlier than the Promise Time" error message "show" on "Promise Time" section on "SERVICE VISIT" page
    And I adjust the "If answered by" time "before" "Delivery by" time on "Promise Time" section on "SERVICE VISIT" page
    Then I see "The Respond By Time must be earlier than the Promise Time" error message "disappeared" on "Promise Time" section on "SERVICE VISIT" page
    Examples: this is for data driver testing
    |URL        |DMS |DealerName            |DealerCode|Odometer|TAG     |VIN              |
    |TEST2DFX  | ?  |Nissan of Anytown USA |5522      |60000   |random  |3N1AB7AP7EL654767|