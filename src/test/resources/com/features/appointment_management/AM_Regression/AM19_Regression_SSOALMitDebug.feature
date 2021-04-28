@AM_Regression-Debug
Feature: This Test suite is AM Regression for SSO login
  Description: to verify Appointment Management SSO login function work properly

  @AM_Regression19.1_SSOALMit_ValidToday-TEST2
  Scenario: AM_Regression19.1 Verify the function login Mitsubishi SSO navigation with valid Dealer Code, User ID
  Description: login SSO navigation  with valid Dealer Code, User ID on Today
    Given I launch browser "chrome" in "GUI" and open "TST2ALMITS_SSO"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'Mitsubishi SSO' page in AM
      | FieldName        | Value                   |
      | Dealer Code      | 80430                   |
      | User Id          | 136040                  |
      | Use today        | Yes                     |
      | Use previous day | No                      |
      | Use earlier date | No                      |
    And I click "Test MMSCAN SSO" button on 'Mitsubishi SSO' page in AM
    Then I see "UserName" is "Mark Czovek" on 'Mitsubishi SSO' page in AM

  @AM_Regression19.2_SSOALMit_ValidPreviousday-TEST2
  Scenario: AM_Regression19.2, Verify the function login Mitsubishi SSO navigation with valid Dealer Code, User ID
  Description: login SSO navigation  with valid Dealer Code, User ID on previous day
    Given I launch browser "chrome" in "GUI" and open "TST2ALMITS_SSO"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'Mitsubishi SSO' page in AM
      | FieldName        | Value                   |
      | Dealer Code      | 80430                   |
      | User Id          | 136040                  |
      | Use today        | No                      |
      | Use previous day | Yes                     |
      | Use earlier date | No                      |
    And I click "Test MMSCAN SSO" button on 'Mitsubishi SSO' page in AM
    Then I see "UserName" is "Mark Czovek" on 'Mitsubishi SSO' page in AM
