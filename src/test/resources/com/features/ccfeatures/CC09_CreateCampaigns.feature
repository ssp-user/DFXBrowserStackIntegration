@CC_Testcase_009
Feature: CC09_CreateCampaigns, Verify Dealer admin can create campaign

  @CCCampaign01 @Regression @Sanity
  Scenario Outline: Verify Dealer admin can create campaign
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I validate "Customer Connect" as window title of the focussed screen
    When I click "CAMPAIGNS" tab on top of the page
    And I select "Import Call List" from "Campaign Type" dropdown menu on 'CAMPAIGNS' page
    And I click "New Campaign" button on 'CAMPAIGNS' page
    And I add "Darren" plus random last name in 'Campaign Name' field
    And I add "This is a test cucumber comment" in 'Campaign Description' field
    And I click "Next" button on 'CAMPAIGNS' page
    And I click "Next" button on 'CAMPAIGNS' page
    And I click "Next" button on 'CAMPAIGNS' page
    And I click "Create" button on 'CAMPAIGNS' page
    And I click "OK" button on 'CAMPAIGNS' page
    Then I see 'Darren' plus random last name in the 'Campaign list' on 'CAMPAIGNS' page
    #delete the campaign
    When I click 'Darren' plus random last name in the 'Campaign list' on 'CAMPAIGNS' page
    And I click "DELETE" button on 'CAMPAIGNS' page
    And I click "OK" button on 'CAMPAIGNS' page
    Then I don't see 'Darren' plus random last name in the 'Campaign list' on 'CAMPAIGNS' page
    #verify clicking configure on the campaign type from dashboard page
    When I click "DASHBOARD" tab on top of the page
    And I click "configure" icon on "Missed / No Show Appointments" Campaign
    Then I see "Missed / No Show Appointments" Campaign selected as default
    And I see header menu showing following status
      | Header menu | Expected status |
      | DASHBOARD   | Grey out        |
      | TASKS       | Grey out        |
      | REPORTS     | Grey out        |
      | CAMPAIGNS   | Enabled         |
      | SCRIPTS     | Grey out        |
      | MESSENGER   | Grey out        |
      | SEARCH      | Grey out        |

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |

  @CCCampaign02 @Regression @Smoketest
  Scenario Outline: Verify advisor can not config Missed/No Show Appointments campaign
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanA" and password "Welcome"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    Then I don't see "configure" icon on "Missed / No Show Appointments" Campaign

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
