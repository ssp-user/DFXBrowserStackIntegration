@AM19 @Regression2
Feature: AM19_Regression_SSOALMit, This test suite is AM Regression for SSO login
  Description: to verify Appointment Management SSO login function work properly

  @AM_Regression19.1_SSOALMit_ValidToday-TEST2
  Scenario: AM_Regression19.1 Verify the function login Mitsubishi SSO navigation with valid Dealer Code, User ID
  Description: login SSO navigation  with valid Dealer Code, User ID on Today
    Given I launch browser "chrome" in "GUI" and open "TEST2ALMITS_SSO"
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
    Given I launch browser "chrome" in "GUI" and open "TEST2ALMITS_SSO"
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

  @AM_Regression19.3_SSOALMit_ValidEarlierDateday-TEST2
  Scenario: AM_Regression19.3 Verify the function login Mitsubishi SSO navigation with valid Dealer Code, User ID
  Description: login SSO navigation  with valid Dealer Code, User ID Use earlier date
    Given I launch browser "chrome" in "GUI" and open "TEST2ALMITS_SSO"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'Mitsubishi SSO' page in AM
      | FieldName        | Value                   |
      | Dealer Code      | 80430                   |
      | User Id          | 96781                   |
      | Use today        | No                      |
      | Use previous day | No                      |
      | Use earlier date | Yes                     |
    And I click "Test MMSCAN SSO" button on 'Mitsubishi SSO' page in AM
    Then I see error message "Invalid security token error!" on 'Mitsubishi SSO' page in AM

  @AM_Regression19.4_SSOALMit_InvalidCode-TEST2
  Scenario:AM_Regression19.4 Verify the function Error handle login Mitsubishi SSO navigation with invalid Dealer Code
  User ID
  Description: error Message display when login SSO navigation  with invalid Dealer Code, User ID
    Given I launch browser "chrome" in "GUI" and open "TEST2ALMITS_SSO"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'Mitsubishi SSO' page in AM
      | FieldName        | Value                   |
      | Dealer Code      | 80430                   |
      | User Id          | 96781                   |
      | Use today        | Yes                     |
      | Use previous day | No                      |
      | Use earlier date | No                      |
    And I click "Test MMSCAN SSO" button on 'Mitsubishi SSO' page in AM
    Then I see error message "user with no user roles assigned error!" on 'SSO' page in AM

  @AM_Regression19.5_SSOALMit_InvalidCode-TEST2
  Scenario: AM_Regression19.5 Verify the function Error handle login Mitsubishi SSO navigation with invalid Dealer Code
  Description: error Message display when login SSO navigation  with invalid Dealer Code
    Given I launch browser "chrome" in "GUI" and open "TEST2ALMITS_SSO"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'Mitsubishi SSO' page in AM
      | FieldName        | Value                   |
      | Dealer Code      | 8043                    |
      | User Id          | 96781                   |
      | Use today        | Yes                     |
      | Use previous day | No                      |
      | Use earlier date | No                      |
    And I click "Test MMSCAN SSO" button on 'Mitsubishi SSO' page in AM
    Then I see error message "Unknown dealer code error!" on 'SSO' page in AM

  @AM_Regression19.6_SSOALMit_InvalidUser-TEST2
  Scenario: AM_Regression19.6 Verify the function Error handle login Mitsubishi SSO navigation with invalid User ID
  Description: error Message display when login SSO navigation  with invalid User ID
    Given I launch browser "chrome" in "GUI" and open "TEST2ALMITS_SSO"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'Mitsubishi SSO' page in AM
      | FieldName        | Value                   |
      | Dealer Code      | 80430                   |
      | User Id          | 9678                    |
      | Use today        | No                      |
      | Use previous day | Yes                     |
      | Use earlier date | No                      |
    And I click "Test MMSCAN SSO" button on 'Mitsubishi SSO' page in AM
    Then I see error message "Unknown user ID!" on 'SSO' page in AM