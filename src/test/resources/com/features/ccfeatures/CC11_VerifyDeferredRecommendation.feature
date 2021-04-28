@CC_Testcase_011
Feature: CC11_VerifyDeferredRecommendation, Verify advisor can see Deferred recommendation

  @CCScripts11 @Regression @Smoketest
  Scenario Outline: Verify advisor can see Deferred recommendation and call log history displayed under history tab
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "Automation" and password "dealerfxqa"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "TASKS" tab on top of the page
    And I select "Deferred MPI Recommendations" from "Campaign Dropdown" dropdown menu
    And I select any customer from 'tasks list' where "Assigned" equal "N/A"
    Then I see 'Task Detail' page
    #verify call logs
    When I select "Left Message" from "Select Action" dropdown menu
    And I click "Save" button on 'Task Detail' page
    And I click "Cancel" button on 'Task Detail' page
    And I select any customer from 'tasks list' where "Customer Name" equal "{userName}"
    Then I see "CALL LOG" count on 'Task Detail' page increased by "1"
    #verify the DEFERRED RECOMMENDATIONS
    When I click "DEFERRED RECOMMENDATIONS" tab on 'Task Detail' page
    Then I see record "DEFERRED RECOMMENDATIONS" tab on 'Task Detail' page

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
