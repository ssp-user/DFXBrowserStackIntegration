@AM_Regression-Debug
Feature: This Test suite is Appointment Management Regression suite
  Description : to verify Appointment Management function work properly

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
      |URL          |DMS     |DealerName                   |DealerCode  |AM_VersionNumber |CustomerName |Odometer |
      |TST2ALNISS   |Dummy   |Nissan of anytown usa        |nissandem2  |2.0.18065.4      |dfx          |54443    |
      |TST2ALLEXU   |Dummy   |Lexus of anytown usa         |61204US     |2.0.18065.4      |testing      |54443    |
      |TST2ALTOYO   |Dummy   |Toyota of anytown USA        |21072USA    |2.0.18065.4      |dfx          |54443    |
      |TST2ALVOLK   |Dummy   |volkswagen of naples         |405024      |2.0.18065.4      |vw test      |54443    |
      |TST2ALMITS   |Dummy   |Mitsubishi of anytown canada |MITSANYCAN  |2.0.18065.4      |dfx          |54443    |
     #### STAGE 2
      |TST2ALNISS   |Dummy   |Nissan of anytown usa        |nissandem2  |2.0.18065.4      |dfx          |54443    |
      |TST2ALLEXU   |Dummy   |Lexus of anytown usa         |61204US     |2.0.18065.4      |testing      |54443    |
      |TST2ALTOYO   |Dummy   |Toyota of anytown USA        |21072USA    |2.0.18065.4      |dfx          |54443    |
      |TST2ALVOLK   |Dummy   |volkswagen of naples         |405024      |2.0.18065.4      |vw test      |54443    |
      |TST2ALMITS   |Dummy   |Mitsubishi of anytown canada |MITSANYCAN  |2.0.18065.4      |dfx          |54443    |
     #### PROD 2
      |TST2ALNISS   |Dummy   |Nissan of anytown usa        |nissandem2  |2.0.18065.4      |dfx          |54443    |
      |TST2ALLEXU   |Dummy   |Lexus of anytown usa         |61204US     |2.0.18065.4      |testing      |54443    |
      |TST2ALTOYO   |Dummy   |Toyota of anytown USA        |21072USA    |2.0.18065.4      |dfx          |54443    |
      |TST2ALVOLK   |Dummy   |volkswagen of naples         |405024      |2.0.18065.4      |vw test      |54443    |
      |TST2ALMITS   |Dummy   |Mitsubishi of anytown canada |MITSANYCAN  |2.0.18065.4      |dfx          |54443    |

