@AC-Regression-Elsa2GO-Test2-debug @Debug
Feature: Advisor check in Elsa2Go Integration function
  Description :  Verify the Advisor check In Elsa2Go Integration function in Test2

  @AC-Regression-Elsa2GO-Test2-Audi-Debug
  Scenario Outline: Verify the Advisor check In Elsa2Go Integration function in Test2 with <OEM>
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    #search by the customer
    When I search "CUSTOMER NAME" with "<CustomerName>" in 'Advisor Check-In Search' page
    And I click "first" search result on 'Advisor Check-In' page
    And I click "NEXT" button on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    And I verify Mandatory Message Displayed in Fields on 'CUSTOMER' page
    And I verify "NEXT" button is not clickable if 'Odometer' field is empty
    And I enter "<Odometer>" in "ODOMETER" field on 'CUSTOMER' page
    When I open "E2G" icon on 'CUSTOMER' page
    Then I see "AccessAudi" text on 'HISTORY-ELESA2GO' page
    Then I see "iAudi" text on 'HISTORY-ELESA2GO' page
    Then I see "HISTORY" tab is active on 'HISTORY-ELESA2GO' page
    Then I see "ELSA2GO" tab is active on 'HISTORY-ELESA2GO' page
    Then I signOut From AC

    Examples: below data set for data driven
      |URL          |DMS                  |DealerName             |<OEM>        |CustomerName|Odometer |
      |TEST2AD      |Elsa2Go              |Audi of anytown usa    |Audi         |dfx         |1234     |
