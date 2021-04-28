@AM04 @Regression1
Feature: AM04_Regression_OEMAL. This test suite is Appointment Management Regression suite
  Description: to verify Appointment Management function work properly

  @AM_Regression04_OEMversionNissan-TEST2
  Scenario Outline: AM_Regression04, Verify the AM Version is correct for OEM Nissan
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "DAY" tab on 'DASHBOARD' page
    Then I verify "DAY" view layout on 'DASHBOARD' page
    Then I verify the AM Version number "<AM_VersionNumber>" on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL          |DMS       |DealerName            |DealerCode   |AM_VersionNumber |CustomerName |Odometer |
      |TEST2ALNISS  |Dummy     |Nissan of anytown usa |nissandem2   |2.0.18065.4      |dfx          |54443    |

  @AM_Regression05_OEMversionLexus-TEST2
  Scenario Outline: AM_Regression05 ,Verify the AM Version is correct for OEM Lexus
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "DAY" tab on 'DASHBOARD' page
    Then I verify "DAY" view layout on 'DASHBOARD' page
    Then I verify the AM Version number "<AM_VersionNumber>" on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL         |DMS   |DealerName           |DealerCode   |AM_VersionNumber |CustomerName     |Odometer |
      |TEST2ALLEXU |Dummy |Lexus of anytown usa |61204US      |2.0.18065.4      |testing dealer-fx|54443    |

  @AM_Regression06_OEMversionToyota-TEST2
  Scenario Outline: AM_Regression06, Verify the AM Version is correct for OEM Toyota
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "DAY" tab on 'DASHBOARD' page
    Then I verify "DAY" view layout on 'DASHBOARD' page
    Then I verify the AM Version number "<AM_VersionNumber>" on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL         |DMS   |DealerName            |DealerCode  |AM_VersionNumber |CustomerName  |Odometer |
      |TEST2ALTOYO |Dummy |Toyota of anytown USA |21072USA    |2.0.18065.4      |dfx           |54443    |

  @AM_Regression07_OEMversionvolkswagen-TEST2
  Scenario Outline: AM_Regression07 Verify the AM Version is correct for OEM volkswagen
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "DAY" tab on 'DASHBOARD' page
    Then I verify "DAY" view layout on 'DASHBOARD' page
    Then I verify the AM Version number "<AM_VersionNumber>" on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL         |DMS   |DealerName            |DealerCode  |AM_VersionNumber |CustomerName  |Odometer |
      |TEST2ALVOLK |Dummy |IDE Volkswagen        |405024      |2.0.18065.4      |vw test       |54443    |

  @AM_Regression08_OEMversionMitsubishi-TEST2
  Scenario Outline: AM_Regression08 Verify the AM Version is correct for OEM Mitsubishi
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    Then I verify login Page Pass on Dealer Page
    And I select the Dealer with "<DealerName>" or "<DealerCode>" in Dealer Page
    And I verify 'UserName' is "Automation User" on 'DASHBOARD' page
    When I click "DAY" tab on 'DASHBOARD' page
    Then I verify "DAY" view layout on 'DASHBOARD' page
    Then I verify the AM Version number "<AM_VersionNumber>" on 'DASHBOARD' page
    And I signout from AM
    Examples: below data set for data driven
      |URL         |DMS   |DealerName                   |DealerCode   |AM_VersionNumber |CustomerName  |Odometer |
      |TEST2ALMITS |Dummy |Mitsubishi of anytown canada |MITSANYCAN   |2.0.18065.4      |dfx           |54443    |