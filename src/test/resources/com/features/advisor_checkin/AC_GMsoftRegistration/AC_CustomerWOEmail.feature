@AC_CUSTOMERWOEMAIL @AC

Feature: GM Soft Pushing RO for customer w/o email

  Scenario Outline: Pushing RO for customer w/o email
    #create service lane through AC as ADVISOR
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "admin_advisor" and password "welcome"
    And I select the Dealer with "" and "<DealerCode>" in Dealer Page
    And I decode "<VIN>" on 'Advisor Check-In' page
    And I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    And I change "Pete" in "FIRST NAME" field on "CUSTOMER" page
    And I change "<CustomerName>" in "LAST NAME" field on "CUSTOMER" page
    And I change "" in "EMAIL" field on "CUSTOMER" page
    And I click "NEXT" button on 'CUSTOMER' page
    And I click "NEXT" button on 'CONCERN' page
    And I click "NEXT" button on 'HISTORY' page
    And I click "NEXT" button on 'SERVICES' page
    And I click "NEXT" button on 'WALK AROUND' page
    And I update <TagNumber> on "ASSIGN R.O." page
    And I click "NEXT" button on 'ASSIGN R.O.' page
    And I click "I AUTHORIZE COMPLETEION OF REQUESTED SERVICES" button on 'AUTHORIZATION' page
    Then I should see following info displayed on "AUTHORIZATION" page
      |fieldName                 |
      |OWNER REGISTRATION        |
      |CUSTOMER EMAIL IS REQUIRED|
    And I sign the agreement on "AUTHORIZATION" page
    And I click "COMPLETE" button on 'AUTHORIZATION' page
    Then I should see RO push completed
    Examples: below data set for data driven
      |URL      |DMS      |DealerCode  |CustomerName|Odometer   |TagRequired|TagNumber|VIN              |
      |TEST2AD  |Autosoft |112024      |Jones       |4700       |yes        |TEST     |1G1ZC5E09CF186796|