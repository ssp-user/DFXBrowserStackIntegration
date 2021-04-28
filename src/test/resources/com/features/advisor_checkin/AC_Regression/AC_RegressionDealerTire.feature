@AC-Regression-DealerTire-Test2
Feature:  Advisor Check  able to generated the QUOTE with Dealer Tire selection

  @AC-Regression-DealerTire-Test2-Nissan
  Scenario Outline: Verfiy the function able to generated the QUOTE with Dealer Tire selection <OEM>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
#    When I search "CUSTOMER NAME" with "<CustomerName>" in 'Advisor Check-In Search' page
#    And I click "2th" search result on 'Advisor Check-In' page
#    And I click "NEXT" button on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    When I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button on 'HISTORY' page
    #start to add tire
    And I click "TIRES" tab in Dealer 'SERVICES' page
    And I click "1th" 'ADD TIRES' icon in Dealer 'SERVICES' page
    And I select "1th" Size on 'TIRE OFFER' popup page
    And I click "1th" 'VIEW' button on 'TIRE OFFER' popup page
    And I click "ADD TO QUOTE" button on 'TIRE OFFER' popup page
    Then I verify the Install Price on DealerTire 'SERVICES' page
    When I click "QUOTE" button on 'SERVICES' page
    Then I see 'GENERATE QUOTE' PDF completed
    And I click "CLOSE" button on 'GENERATE QUOTE' PopUp
    And I signOut From AC

    Examples: below data set for data driven
      |URL       | DMS          |DealerName                |Odometer|VIN                |OEM         |CustomerName   |
      |PROD2AD   | DealerTire   |BEAVER COUNTY NISSAN INC  |21431   |1N4AL21E59C104101  |Nissan      |TESTING        |
