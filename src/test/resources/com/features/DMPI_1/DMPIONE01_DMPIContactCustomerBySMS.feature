@DMPIONE01 @DMPI_SMOKE

Feature: DMPI SMS check
         DMPI, login as advisor to create service lane in Advisor checkin
         login as technician to complete the service in Technician Inspection
         login as advisor to send message to customer via SMS

  Scenario Outline: Dynamic multi point inspection, verify message to customer By Email
    #create service lane through WiAdvisor as ADVISOR
    Given I launch browser "chrome" in "GUI" and open "<URL2>"
    When I login the WiAdvisor as UserID "T2273HG" and Password "Dealer@1" and Dealer Code "<DealerCode>"
    And I click "Accept" button in 'index' page
    And I click "Search" tab in 'index' page
    And I search VIN "<VIN>" under "Search" tab
    And I select "DMS" when 'Customer Information' popup
    And I update "Odometer" with "<Odometer>" in 'Vehicle Information' tab
    And I click "Continue" button in 'WiAdvisorRO' page
    And I click "Continue" button in 'WiAdvisorRO' page
    And I click "Please Select a Service Category" dropdown on "Build RO" tab
    And I select "AC/Heating" from "Please Select a Service Category" dropdown on "Build RO" tab
    And I click "AC/Heating Concern" checkbox on "Build RO" tab
    And I click "Please Select a Service Category" dropdown on "Build RO" tab
    And I select "Battery" from "Please Select a Service Category" dropdown on "Build RO" tab
    And I click "Battery Concern" checkbox on "Build RO" tab
    And I enter "This is a test from DealerFX cucumber automaton. Please ignore this." comments under "General Comments" section under "Build RO" tab
    And I "check" "Select All" under "Factory Required Maintenance"
    Then I should see maintenance items "show" under "Selected Service"
    And I click "Continue" button in 'WiAdvisorRO' page
    And I enter "<TAG>" under "Tag" field in 'Assign RO' tab
    And I enter "<TagNumber>" under "Tag" field in 'Assign RO' tab
    And I click "Continue" button in 'WiAdvisorRO' page
    And I click "Continue" button in 'WiAdvisorRO' page
    And I click "Continue" button in 'WiAdvisorRO' page
    And I "sign" in 'Finalize RO' tab
    And I click "Complete" button in 'Finalize RO' page
    Then I see following PDF generated on 'Complete Repair Order' pop up
      |PDFName             |Status|
      |pre-service write-up|show  |
      |maintenance menu    |show  |
      |VIP                 |show  |
    #complete service and add two recommendation in Technician Inspection as Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "Hgtech3" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
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
    And I click "Red" icon on "Air Filter" inspection on 'INSPECTION' tab
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
    And I click "Orange" icon on "Battery Check" inspection on 'INSPECTION' tab
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
    When I login the Application as User "Hgadvisor3" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "MPI Completed" status on 'VEHICLE QUEUE' page
    When I click "DETAILS" button under the service lane with <VIN> on 'VEHICLE QUEUE' page
    When I open page "ReceiveaSMS.com :: Canada" in the new browser tab
    And I get "Phone#" from page title "ReceiveaSMS.com :: Canada"
    And I switch back to page title "Service Dashboard"
    And I click Document Type "Recommendations" under Final Documents on 'SERVICE VISIT' page
    And I get "If answered by" time on "Promise Time" section on "SERVICE VISIT" page
    And I get "Deliver by" time on "Promise Time" section on "SERVICE VISIT" page
    And I click "SEND FOR APPROVAL" button on 'SERVICE VISIT' page
    And I toggle "ON" on "SMS" field on "Customer Contact Information" popup
    And I toggle "OFF" on "Email" field on "Customer Contact Information" popup
    And I update "Phone#" on "SMS" field on "Customer Contact Information" popup
    And I click "SEND" button on "Customer Contact Information" popup
    #Verify url from SMS
    And I switch back to page title "ReceiveaSMS.com :: Canada"
#    Then I should see SMS contains "https://tinyurl.com" associated with "Dealer#" on "ReceiveaSMS.com :: Canada" page
    When I click on URL contains "https://tinyurl.com" in received SMS on "ReceiveaSMS.com :: Canada" page
    Then I see 'Dynamic MPI' page
    When I input "last name" "automation" in "Please enter your last name or company:" field on "Dynamic MPI" page
    And I click "ENTER" button on 'Dynamic MPI' page
    Then I should see following data in "Dynamic MPI" page
      |FieldName                    |FieldValue        |
      |service advisor              |HG Advisor3       |
      |RECOMMENDATIONS              |2                 |
      |vehicle name                 |2011 JEEP Grand Cherokee (5.7L)|
      |Customer Concerns            |2                 |
      |Requires Immediate Attention |1                 |
      |May Need Future Attention    |1                 |
    And I click "CONTINUE" button on 'Dynamic MPI' page
    Then I should see following data in "Dynampic MPI" page
      |FieldName                     |FieldValue                              |
      |Select your services in       |timer(f answered by time - current time)|
      #|To have your vehicle ready by |Deliver by time                         |
    When I click "CONTINUE" button on 'Dynamic MPI' page
    Then I should see following data in "Dynamic MPI" page
      |FieldName                     |FieldValue        |FieldValue2|
      |CUSTOMER CONCERN(S)           |AC/Heating Concern|$230.00    |
      |CUSTOMER CONCERN(S)           |Battery Concern   |$80.00     |
      |REQUIRES IMMEDIATE ATTENTION  |Test1             |$125.50    |
      |MAY NEED FUTURE ATTENTION     |Test2             |$235.50    |
    And I click "NEXT" button on 'Dynamic MPI' page
    And I click "ACCEPT ESTIMATE" button on 'Dynamic MPI' page
    And I click "Click to sign" button on 'Dynamic MPI' page
    And I toggle "I agree to the Dealership's terms and conditions" checkbox on "Dynamic MPI" page
    And I click "Approve" button on 'Dynamic MPI' page
    Then I see confirm message with <DealerName> on "Dynamic MPI" page
    #And I see confirm message with DeliveryTime on "Dynamic MPI" page
    #Back to Service dashboard and check the log
    When I switch back to page title "Service Dashboard"
    Then I see service lane with <VIN> with "Work in Progress" status on 'SERVICE VISIT' page
    When I click "STATUS LOG" button on 'SERVICE VISIT' page
    And I see following logs on "STATUS LOG" section
      |logsName           |logs                      |
      |Customer Approval  |Approved on               |
      |Customer click link|Customer clicked link on  |
      #|Email sent         |dfxcucumbertest@gmail.com |
    Examples: this is for data driver testing
    |URL         |URL2    |DMS |DealerName      |DealerCode |Odometer  |TAG     |VIN              |
    |TEST1DFX   |TST1WI  | ?  |CHRYSLER QA     |99970      |60000     |random  |1J4RR6GT0BC606832|