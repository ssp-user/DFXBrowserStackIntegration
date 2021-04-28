@CC_Testcase_017
Feature: CC17_TestEmail, See preview of Email and Send test Email

  @CCEmail17 @Regression @Smoketest
  Scenario Outline: Verify that User is able to see preview of Email and able to send test Email
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "DarrenNissanD" and password "Welcome"
    And I note down page window handle from the 'Home' page
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "Customer Connect" application from the 'Home' page
    And I click "CAMPAIGNS" tab on top of the page
    And I select "Import Call List" from "Campaign Type" dropdown menu on 'CAMPAIGNS' page
    And I click "New Campaign" button on 'CAMPAIGNS' page
    Then I see "INFORMATION" tab is active on 'CAMPAIGNS' page
    #Following Actions Will be Performed on "Information" tab of "Campaign" page
    When I set 'Communication Type' to "Email"
    And I enter "random character" on "Campaign Name" on 'CAMPAIGNS' page
    And I enter "random character" on "Campaign Description" on 'CAMPAIGNS' page
    And I click "Next" button on 'CAMPAIGNS' page
    Then I see "EMAIL" tab is active on 'CAMPAIGNS' page
    #Following Actions Will be Performed on "Email" tab of "Campaign" page
    #Select the first option if "Email Templates" list is not empty, otherwise create a new template
    When I select "first option" or create a new template from "Email Templates" dropdown menu
    And I click "Preview and Test" button on 'CAMPAIGNS' page
    Then I see "Preview" window opened on 'CAMPAIGNS' page
    And I see "Test DFX Test Email Please Ignore" in 'Email Body Content' field if I created a new template
    When I enter "dfxcucumbertest@yahoo.com" on "Recipient" on 'Preview' popup
    And I enter "Customer connect email" on "Email Subject" on 'Preview' popup
    And I click "Send Test Email" button on 'CAMPAIGNS' page
    Given I launch browser "chrome" in "GUI" and open "YAHOO"
    And I login page "Yahoo" as User "dfxcucumbertest" and password "dealerfxqa"
    Then I see new email for customer connect in Yahoo inbox
    Then I see delete the email
    Then I signout for the application

    Examples: below data set for data driven
      | URL      | DealerName            | Field3 | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Value3 | Value4 |
