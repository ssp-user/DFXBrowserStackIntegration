@DMPI05 @DMPI_SMOKE

Feature: DMPI Green concern and comments check
         DMPI, login as advisor to create service lane in Advisor checkin
         login as technician to complete the service in Technician Inspection
         login as advisor to send message to customer via SMS

  Scenario Outline: Dynamic multi point inspection, verify message to customer By Email
    #create service lane through AC as ADVISOR
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Advisor Check-In" Application on 'Main Page'
    #And I login the Application as User "DarrenNissanA" and password "Welcome"
    #And I select the Dealer with "Nissan of Anytown USA" and "DONOTCHANGE" in Dealer Page
    And I decode "<VIN>" on 'Advisor Check-In' page
    And I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    When I open page "ReceiveaSMS.com :: Canada" in the new browser tab
    And I get "Phone#" from page title "ReceiveaSMS.com :: Canada"
    And I switch back to page title "Advisor Check-In"
    And I change "Hakan" in "FIRST NAME" field on "CUSTOMER" page
    And I change "AUTOMATION" in "LAST NAME" field on "CUSTOMER" page
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
    When I login the Application as User "DarrenNissanT" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #And I login the Application as User "DarrenNissanT" and password "Welcome"
    #And I select the Dealer with "Nissan of Anytown USA" and "DONOTCHANGE" in Dealer Page
    #And I search vehicle <TAG> in 'TECHNICIAN INSPECTION Dashboard'
    And I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "Green" icon on "AC/Heating Concern" concern on 'INSPECTION' tab
    And I click "AC/Heating Concern" concern on 'INSPECTION' tab
      # add Internal Comments
    And I input "This is Internal Comment Text" on "Internal Comment" field under "TestBattery" Description on "Inspection Detail" page
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
    #Login as service advisor, Open RS to check its in MPI started status.
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" in DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    #And I login the Application as User "DarrenNissanA" and password "Welcome"
    #And I select the Dealer with "Nissan of Anytown USA" and "DONOTCHANGE" in Dealer Page
    Then I should see service lane with <VIN> with "MPI Completed" status on 'VEHICLE QUEUE' page
    When I click "DETAILS" button under the service lane with <VIN> on 'VEHICLE QUEUE' page
    And I click Document Type "Recommendations" under Final Documents on 'SERVICE VISIT' page
    And I get "If answered by" time on "Promise Time" section on "SERVICE VISIT" page
    And I get "Deliver by" time on "Promise Time" section on "SERVICE VISIT" page
    And I click "SEND FOR APPROVAL" button on 'SERVICE VISIT' page
    Then I should see following error message on "AC/Heating Concern" concern on "COMMENTS FOR CUSTOMER" field on "SERVICE VISIT" page
      |COMMENTS FOR CUSTOMER error message                                                          |
      |Please enter comments for the customer before sending for approval/presenting to the customer|
    And I input "No Problem is found" comments on "AC/Heating Concern" concern on "COMMENTS FOR CUSTOMER" field on "SERVICE VISIT" page
    And I click "SEND FOR APPROVAL" button on 'SERVICE VISIT' page
    And I toggle "ON" on "SMS" field on "Customer Contact Information" popup
    And I toggle "OFF" on "Email" field on "Customer Contact Information" popup
    And I update "Phone#" on "SMS" field on "Customer Contact Information" popup
    And I click "SEND" button on "Customer Contact Information" popup
    #Verify url from SMS
    And I open page "ReceiveaSMS.com :: Canada" in the new browser tab
#    Then I should see SMS contains "https://tinyurl.com" associated with "Dealer#" on "ReceiveaSMS.com :: Canada" page
    When I click on URL contains "https://tinyurl.com" in received SMS on "ReceiveaSMS.com :: Canada" page
    Then I see 'Dynamic MPI' page
    When I input "last name" "AUTOMATION" in "Please enter your last name or company:" field on "Dynamic MPI" page
    And I click "ENTER" button on 'Dynamic MPI' page
    Then I should see following data in "Dynamic MPI" page
      |FieldName                    |FieldValue            |
      |service advisor              |Darren Deng           |
      |RECOMMENDATIONS              |2                     |
      |vehicle name                 |2014 Pathfinder (3.5L)|
      |Customer Concerns            |2                     |
      |Requires Immediate Attention |1                     |
      |May Need Future Attention    |1                     |
    And I click "CONTINUE" button on 'Dynamic MPI' page
    Then I should see following data in "Dynamic MPI" page
      |FieldName                     |FieldValue                              |
      |Select your services in       |timer(f answered by time - current time)|
      |To have your vehicle ready by |Deliver by time                         |
    When I click "CONTINUE" button on 'Dynamic MPI' page
    Then I should see following data in "Dynamic MPI" page
      |FieldName                     |FieldValue        |FieldValue2|
      |CUSTOMER CONCERN(S)           |AC/Heating Concern|N/A        |
      |CUSTOMER CONCERN(S)           |Battery Concern   |$80.00     |
      |REQUIRES IMMEDIATE ATTENTION  |Test1             |$125.50    |
      |MAY NEED FUTURE ATTENTION     |Test2             |$235.50    |
    And I click "NEXT" button on 'Dynamic MPI' page
    And I click "ACCEPT ESTIMATE" button on 'Dynamic MPI' page
    And I click "Click to sign" button on 'Dynamic MPI' page
    And I toggle "I agree to the Dealership's terms and conditions" checkbox on "Dynamic MPI" page
    And I click "Approve" button on 'Dynamic MPI' page
    Then I see confirm message with <DealerName> on "Dynamic MPI" page
    And I see confirm message with DeliveryTime on "Dynamic MPI" page
    #Back to Service dashboard and check the log
    When I switch back to page title "Service Dashboard"
    And I click "STATUS LOG" button on 'SERVICE VISIT' page
    And I see following logs on "STATUS LOG" section
      |logsName           |logs                      |
      |Customer Approval  |Approved on               |
      |Customer click link|Customer clicked link on  |
      #|Email sent         |dfxcucumbertest@gmail.com |
    Examples: this is for data driver testing
    |URL        |DMS |DealerName            |DealerCode |Odometer|TAG     |VIN              |
    |PRD2NISS   | ?  |Nissan of Anytown USA |DONOTCHANGE|60000   |random  |5N1AR2MM1EC716183|