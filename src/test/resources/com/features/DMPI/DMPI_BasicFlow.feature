Feature: DMPI Basic scenario

  Scenario Outline: Dynamic multi point inspection, basic flow
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    Then I see 'Dynamic MPI' page
    When I input "last name" "DengDeng123" in "Please enter your last name:" field on "Dynamic MPI" page
    And I click "ENTER" button on 'Dynamic MPI' page
    And I click "VIEW ITEMS" button on 'Dynamic MPI' page    
    And I click "CONFIRM" button on 'Dynamic MPI' page
    And I click "Click to sign" button on 'Dynamic MPI' page
    #And I click "ACCEPT ESTIMATE" button on 'Dynamic MPI' page
    #And I click "Click to sign" button on 'Dynamic MPI' page
    And I toggle "I agree to the Dealership's terms and conditions" checkbox on "Dynamic MPI" page
    And I click "Approve" button on 'Dynamic MPI' page
    Then I see confirm message with <DealerName> on "Dynamic MPI" page

    Examples: this is for data driver testing
      | URL       | DMS | DealerName            | DealerCode  | Odometer | TAG    | VIN               |
      | DUMMYDMPI | ?   | Nissan of Anytown USA | DONOTCHANGE |    60000 | random | 3N1AB7AP7EL654767 |
