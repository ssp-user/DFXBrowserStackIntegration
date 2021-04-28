@SD04 @rsflow04 @rsSearch
Feature: SD04_SearchVehicleQueue, Search by/for VIN, tag or Customer Name from vehicle queue when no Service Lane or an appointment fot the VIN exists
Description: The purpose of this feature file is:
            1) to verify the ability to search from the Vehicle Queue (by VIN, Customer Name etc.)
            2) to verify the ability to search from the Search page using different options/filters

   Scenario Outline: Search by VIN from vehicle queue when no Service Lane or an appointment fot the VIN exists
      #Login as Dealer Admin
      Given I launch browser "chrome" in "GUI" and open "<URL>"
      When I login the Application as User "<Username>" and password "<Password>"
      And I click 'Click Here to Select your Dealership' on 'Home' page
      And I select Dealer with "<DealerName>" in DFX Page
      And I launch "Service Dashboard" Application on 'Main Page'
      #Delete service lane if exist
      And I search "<VIN>" on 'VEHICLE QUEUE' page
      And I delete service lane with "<VIN>" on 'VEHICLE QUEUE' page
      And I refresh the page
      Then I don't see service lane with "<VIN>" on 'VEHICLE QUEUE' page
      #Push RO
      When I switch back to page title "Dealer-FX Application Framework"
      And I launch "Advisor Check-In" Application on 'Main Page'
      And I switch back to page title "Advisor Check-In"
      And I decode "<VIN>" on 'Advisor Check-In' page
#      And I deal with Customer Information PopUp in 'CUSTOMER' page
      And I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
      And I click "NEXT" button on 'CUSTOMER' page
      And I click "SERVICES" tab on 'CUSTOMER CONCERN' page
      And I select "MULTI-POINT INSPECTION" service on 'SERVICES' page
      And I click "ASSIGN R.O." tab on 'CUSTOMER CONCERN' page
      And I enter data set in 'ASSIGN R.O.' page
         | FieldName           | Value            |
         | SERVICE ADVISOR     | <ServiceAdvisor> |
         | TAG                 | <TagNumber>      |
         | TECHNICIAN          | <Technician>     |
      And I click "NEXT" button on 'ASSIGN R.O.' page
      And I authorize and sign the agreement on 'AUTHORIZATION' page
      And I click "COMPLETE" button on 'AUTHORIZATION' page
      Then I should see RO push completed
      #Search for/by the VIN from the vehicle queue
      When I switch back to page title "Service Dashboard"
      And I click "SHOW MINE" button on 'VEHICLE QUEUE' page
      And I search last 5 digits of the "<VIN>" on 'VEHICLE QUEUE' page
      Then I see service lane contains "VIN" with "<VIN>" value on 'VEHICLE QUEUE' page
      And I see last 5 digits of the "<VIN>" are highlighted on 'VEHICLE QUEUE' page
      #Search for/by Tag from the vehicle queue
      When I search "<TagNumber>" on 'VEHICLE QUEUE' page
      Then I see service lane contains "TagNumber" with "<TagNumber>" value on 'VEHICLE QUEUE' page
      And I see "TagNumber" with "<TagNumber>" value highlighted on 'VEHICLE QUEUE' page
      #Search for/by Customer Name from the vehicle queue
      When I search "<CustomerName>" on 'VEHICLE QUEUE' page
      Then I see service lane contains "CustomerName" with "<CustomerName>" value on 'VEHICLE QUEUE' page
      And I see "CustomerName" with "<CustomerName>" value highlighted on 'VEHICLE QUEUE' page
      #Search for/by invalid text from the vehicle queue
      And I search "<InvalidText>" on 'VEHICLE QUEUE' page
      Then I don't see any service lane on 'VEHICLE QUEUE' page
      And I see "<InvalidText>" 'was not found' message on 'VEHICLE QUEUE' page
      #Remove search criteria
      When I click "Clear Search" button on 'VEHICLE QUEUE' page
      And I click "SHOW ALL" button on 'VEHICLE QUEUE' page
      Then I see all the service lanes on 'VEHICLE QUEUE' page
      Examples: this is for data driver testing
       |URL        |DealerName           |VIN               |Username     |Password   |TagNumber  |Odometer |ServiceAdvisor|Technician|CustomerName |InvalidText |
       |TST2NISS   |Nissan of Anytown    |1N4AL3AP2EC141575 |nissan.da    |welcome    |SD4        |21431    |Bob Green     |Ash Tech  |YURIY SYCHOV |xyz123      |