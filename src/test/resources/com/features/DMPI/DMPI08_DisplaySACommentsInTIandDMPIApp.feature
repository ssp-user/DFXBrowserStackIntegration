Feature: Display Service Advisor comments in both TI and DMPI app
  DMPI, login as advisor to create service lane in Advisor checkin
  login as technician to complete the service in Technician Inspection
  login as advisor to send message to customer via SMS

  Scenario Outline: DMPI, verify Image Attached in DMPI bridge and App, Comments from AD, SA comments in TI and DMPI App
    #create service lane through AC as ADVISOR
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "NissanAdvisor11" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Advisor Check-In" Application on 'Main Page'
    And I decode "<VIN>" on 'Advisor Check-In' page
    And I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I change "Hakan" in "FIRST NAME" field on "CUSTOMER" page
    And I change "AUTOMATION" in "LAST NAME" field on "CUSTOMER" page
    And I change "dfxcucumbertest@gmail.com" in "EMAIL" field on "CUSTOMER" page
    And I click "NEXT" button on 'CUSTOMER' page
    And I add following customer concerns on 'CONCERN' page
      |Concern name       |
      |AC/Heating Concern |
    ##And I input "A/C is not cooling" for "AC/Heating Concern" on 'CONCERN' page
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
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #And I search vehicle <TAG> in 'TECHNICIAN INSPECTION Dashboard'
    And I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "Red" icon on "AC/Heating Concern" concern on 'INSPECTION' tab
    And I click "Camera" icon on 'Inspection Detail' page
    Then I should see "CAMERA VIEW" icon on "Tech Cam" page
    And I click "CAPTURE" icon on "Tech Cam" page "1" times
    And I click "close" icon on 'Tech Cam' page
    And I enter data set in addRecommendationField
      | FieldName         |Value      |
      | Recommendation    | TestAC    |
      | OPcode            |           |
      | Hours             | 3         |
      | LaborSale         | 30        |
      | PartSale          | 200       |
      | Price             |           |
    And I click "plus" icon on 'Inspection Detail' page
    And I add attachment "TestAC" recommendation to the photo on "Inspection Detail" page
    Then I should see "TestAC" recommendation attached to the photo on "Inspection Detail" page
    #When I click "Camera" icon on 'Inspection Detail' page
    #Then I should see "TestAC" recommendation attached to the photo on "Tech Cam" page
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
    When I login the Application as User "NissanAdvisor11" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Service Dashboard" Application on 'Main Page'
    Then I should see service lane with <VIN> with "MPI Completed" status on 'VEHICLE QUEUE' page
    When I click "DETAILS" button under the service lane with <VIN> on 'VEHICLE QUEUE' page
    And I click Document Type "Recommendations" under Final Documents on 'SERVICE VISIT' page
    And I get "If answered by" time on "Promise Time" section on "SERVICE VISIT" page
    And I get "Deliver by" time on "Promise Time" section on "SERVICE VISIT" page
    #test attached image and advisor comments
    Then I see image "checked" on "IMAGE LIBRARY" section under "TestAC" description on "SERVICE VISIT" page
    When I click "INSPECTION" tab on 'SERVICE VISIT' page
#    Then I see "Test1" description on "SERVICE VISIT" page
#    And I input "Service Advisor adds some comments here" comments on "TEST1" recommendation on "COMMENTS FOR CUSTOMER" field on "SERVICE VISIT" page
    And I click "SEND FOR APPROVAL" button on 'SERVICE VISIT' page
    And I click "SEND" button on "Customer Contact Information" popup
    #Verify url from Gmail
    And I open page "Gmail" in the new browser tab
    And I login page "Gmail" as User "dfxcucumbertest@gmail.com" and password "dealerfxqa"
    And I click Email associated with "<DealerName>" on 'Gmail' page
    And I click on URL contains "https://tinyurl.com" in email on "Gmail" page
    Then I see 'Dynamic MPI' page
    When I input "last name" "Automation" in "access code" field on "Dynamic MPI" page
    And I click "ENTER" button on 'Dynamic MPI' page
    Then I should see following data in "Dynamic MPI" page
      |FieldName                    |FieldValue        |
      |service advisor              |Nissan Advisor11      |
      |RECOMMENDATIONS              |2                 |
      |vehicle name                 |2014 Pathfinder (3.5L)|
      |Customer Concerns            |1                 |
      |Requires Immediate Attention |1                 |
      |May Need Future Attention    |1                 |
    And I click "CONTINUE" button on 'Dynamic MPI' page
    Then I should see following data in "Dynamic MPI" page
      |FieldName                     |FieldValue                              |
      |Select your services in       |timer(f answered by time - current time)|
      #|To have your vehicle ready by |Deliver by time                         |
    When I click "CONTINUE" button on 'Dynamic MPI' page
    Then I should see following data in "Dynamic MPI" page
      |FieldName                     |FieldValue        |FieldValue2|
      |CUSTOMER CONCERN(S)           |AC/Heating Concern|$230.00    |
      |REQUIRES IMMEDIATE ATTENTION  |Test1             |$125.50    |
      |MAY NEED FUTURE ATTENTION     |Test2             |$235.50    |
#    And I click "Test1" icon on 'INSPECTION SUMMARY' page
#    Then I should see following data in "Recommendation Test1" pop-up window
#      |FieldName                     |FieldValue |
#      |Recommendation                |Test1      |
#      |TEXT                          |The horn allows the driver to communicate with other drivers and pedestrians while in operation. Any malfunction in horn operation should be considered a safety issue, and should be addressed immediately. Service Advisor adds some comments here |
#      |Estimated cost of this item   |$125.50    |
#      |BUTTON                        |Approve    |
#      |BUTTON                        |Not Today  |
    ##And I click "Approve" button on "Recommendation Test1" pop-up window
    And I click "NEXT" button on 'Dynamic MPI' page
    And I click "ACCEPT ESTIMATE" button on 'Dynamic MPI' page
    And I click "Click to sign" button on 'Dynamic MPI' page
    And I toggle "I agree to the Dealership's terms and conditions" checkbox on "Dynamic MPI" page
    And I click "Approve" button on 'Dynamic MPI' page
    Then I see confirm message with <DealerName> on "Dynamic MPI" page
    #And I see confirm message with DeliveryTime on "Dynamic MPI" page
    #Checking Service Advisor comments in Technician Inspection as Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "Nissantech4" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #And I search vehicle <TAG> in 'TECHNICIAN INSPECTION Dashboard'
    And I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
#    And I click "RECOMMENDATION" Tab in 'TECHNICIAN INSPECTION Dashboard'
#    Then I should see "The horn allows the driver to communicate with other drivers and pedestrians while in operation. Any malfunction in horn operation should be considered a safety issue, and should be addressed immediately. Service Advisor adds some comments here" on "Customer Comments" field
    Examples: this is for data driver testing
      |URL        |DMS |DealerName            |DealerCode        |Odometer|TAG     |VIN              |
      |TST2NISS   | ?  |Nissan of Anytown USA |DONOTCHANGE       |60000   |random  |5N1AR2MM1EC716183|