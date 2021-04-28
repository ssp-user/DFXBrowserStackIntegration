@PY08 @Regression
Feature: PY08_SearchCustomerbyPhoneAndCreateInvoice
  Description: Search Customer by Phone. Create Invoice for this customer

  Scenario Outline: Search Customer by Phone. Create Invoice for this customer
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    And I click "CREATE INVOICE" on 'Payment' page
    And I search customer by phone number "<PhoneNumber>" on 'CREATE INVOICE' page
    And I choose First Record in search results on 'CREATE INVOICE' page
    And I set customer data on 'CREATE INVOICE' page
      | FieldName      | Value             |
      | Advisor        | RANDOM            |
      | RO Number      | RANDOM            |
      | Invoice Amount |              1.25 |
      | Email          | contact@dfx.com   |
      | VIN            | WAUDGAFL7BA002147 |
    And I choose Email option on 'CREATE INVOICE' page
    And I click Send payment request button on 'CREATE INVOICE' page
    And I validate "Successful sent invoice notification" on 'Payment' page
    And I click "CLOSE FOOTER NOTIFICATION" on 'Payment' page
    And I search invoice by "RO Number" on 'Payment' page
    And I validate "INVOICE DETAILS" on 'Payment' page

    Examples: below data set for data driven
      | URL      | DealerName            | Application | PhoneNumber |
      | TEST2DFX | Nissan of Anytown USA | Payments    |  4555555555 |
