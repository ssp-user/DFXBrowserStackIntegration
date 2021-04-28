@AM_Regression-Debug
Feature: This Test suite is AM Regression for SSO login
  Description: to verify Appointment Management SSO login function work properly

  @AM_Regression18.1_SSOAL_NoNamePhoneVIN-TEST2
  Scenario: AM_Regression18.1,Verify the function login SSO navigation to customer search page without Name,Phone,VIN
  Description: login SSO navigation to customer search page without input customer Name, customer Phone
  and customer VIN
    Given I launch browser "chrome" in "GUI" and open "TST2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 4363                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      |                                      |
      | Customer Phone     |                                      |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I click "APPOINTMENT FOR A NEW CUSTOMER" button on 'CUSTOMER SEARCH' page

  @AM_Regression18.2_SSOAL_NoPhoneVIN-TEST2
  Scenario: AM_Regression18.2 Verify the function login SSO navigation to customer search page without Phone,VIN
  Description: login SSO navigation to customer search page without input customer Phone and customer VIN
    Given I launch browser "chrome" in "GUI" and open "TST2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 4363                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      | Test                                 |
      | Customer Phone     |                                      |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I select "1th" customer on 'CUSTOMER SEARCH' page


  @AM_Regression18.3_SSOAL_NoNameVIN-TEST2
  Scenario: AM_Regression18.3, Verify the function login SSO navigation to customer search page without Name,VIN
  Description: login SSO navigation to customer search page without input customer Name and VIN
    Given I launch browser "chrome" in "GUI" and open "TST2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 4363                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      |                                      |
      | Customer Phone     | 7777777777                           |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see "Phone" is "7777777777" on 'CUSTOMER SEARCH' page

  @AM_Regression18.4_SSOAL_NoNamePhone-TEST2
  Scenario: AM_Regression18.4, Verify the function login SSO navigation to customer search page without Name,Phone
  Description: login SSO navigation to customer search page without input customer Name and Phone
    Given I launch browser "chrome" in "GUI" and open "TST2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 4363                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      |                                      |
      | Customer Phone     |                                      |
      | VIN                | 5TEUU42N86Z285030                    |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see "VIN" is "5TEUU42N86Z285030" on 'CUSTOMER SEARCH' page

  @AM_Regression18.5_SSOAL_InvalidPartnerID-TEST2
  Scenario: AM_Regression18.5,Verify the  function of error handle when login SSO with 'Invalid DFX Partner ID'
  Description: login SSO with Invalid input and able to see the error message on screen
    Given I launch browser "chrome" in "GUI" and open "TST2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                            |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF |
      | Shared Password    | T9vP2v$c*ybhX2Vk                 |
      | DFX Dealer ID      | 4363                             |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL     |
      | Customer Name      |                                  |
      | Customer Phone     |                                  |
      | VIN                |                                  |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see error message "Invalid DFX Partner ID Error!" on 'SSO' page in AM

  @AM_Regression18.6_SSOAL_InvalidPartnerID-TEST2
  Scenario: AM_Regression18.6 Verify the  function of error handle when login SSO with Invalid Shared Password
  Description: login SSO with Invalid input and able to see the error message on screen
    Given I launch browser "chrome" in "GUI" and open "TST2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybh                         |
      | DFX Dealer ID      | 4363                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      |                                      |
      | Customer Phone     |                                      |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see error message "Invalid or Expired Security Token Error!" on 'SSO' page in AM

  @AM_Regression18.7_SSOAL_WrongDFXDealerId-TEST2
  Scenario: AM_Regression18.7 ,Verify the  function of error handle when login SSO with Wrong DFX Dealer Id
  Description: login SSO with Invalid input and able to see the error message on screen
    Given I launch browser "chrome" in "GUI" and open "TST2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 1234                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      |                                      |
      | Customer Phone     |                                      |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see error message "Appointment Ledger is Disabled for Requested Dealer ID Error!" on 'SSO' page in AM

  @AM_Regression18.8_SSOAL_InvalidDFXDealerId-TEST2
  Scenario: AM_Regression18.8, Verify the  function of error handle when login SSO with Invalid DFX Dealer Id
  Description: login SSO with Invalid input and able to see the error message on screen
    Given I launch browser "chrome" in "GUI" and open "TST2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 436                                  |
      | DFX System User ID | dmecallcenter_ANYUSAAUTOMALL         |
      | Customer Name      |                                      |
      | Customer Phone     |                                      |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see error message "Unknown DFX Dealer Id Error!" on 'SSO' page in AM

  @AM_Regression18.9_SSOAL_InvalidDFXSystemUser-TEST2
  Scenario: AM_Regression18.9,Verify the  function of error handle when login SSO with Invalid DFX System User ID
  Description: login SSO with Invalid input and able to see the error message on screen
    Given I launch browser "chrome" in "GUI" and open "TST2SSOAL"
    When I set "Dummy" to the 'DMS'
    And I set SSO data on 'SSO' page in AM
      | FieldName          | Value                                |
      | Partner ID         | 706D16AA-A377-4410-B827-A1C4ACEF81CC |
      | Shared Password    | T9vP2v$c*ybhX2Vk                     |
      | DFX Dealer ID      | 4363                                 |
      | DFX System User ID | dmecallcenter_ANYUSAAUT              |
      | Customer Name      |                                      |
      | Customer Phone     |                                      |
      | VIN                |                                      |
    And I click "Test SSO" button on 'SSO' page in AM
    Then I see error message "User Authentication or Authorization Error!" on 'SSO' page in AM