@PY04 @Regression
Feature: PY04_ValidateAlreadyPaidInvoice
  Description: Create Invoice. Pay by direct payment. Login to Email and try to pay again. Validate already paid Invoice message.

  Scenario Outline: Create Invoice. Pay by direct payment. Login to Email and try to pay again. Validate already paid Invoice message.
    Given I launch browser "chrome" in "GUI" and open "<URL1>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    And I click "CREATE INVOICE" on 'Payment' page
    And I manually add the customer details on 'Create Invoice' page
    And I set customer data on 'CREATE INVOICE' page
      | FieldName      | Value                     |
      | Customer Name  | RANDOM                    |
      | Advisor        | RANDOM                    |
      | RO Number      | RANDOM                    |
      | Invoice Amount |                      1.25 |
      | Phone Number   | RANDOM                    |
      | Email          | dfxcucumbertest@yahoo.com |
      | VIN            | WAUDGAFL7BA002147         |
    And I choose Email option on 'CREATE INVOICE' page
    And I click Proceed to direct payment button on 'CREATE INVOICE' page
    And I switch back to page title "DFX Payments"
    And I validate "Send Invoice Notification" on 'Payment' page
    And I click "CLOSE FOOTER NOTIFICATION" on 'Payment' page
    And I search invoice by "RO Number" on 'Payment' page
    And I validate "INVOICE DETAILS" on 'Payment' page
    And I click "SEARCH RESULTS" on 'Payment' page
    And I choose Email option on 'CREATE INVOICE' page
    And I click Send payment request button on 'CREATE INVOICE' page
    And I validate "Successful sent invoice notification" on 'Payment' page
    And I click "CLOSE FOOTER NOTIFICATION" on 'Payment' page
    Given I launch browser "chrome" in "GUI" and open "<URL1>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I choose "<Application>" application from the 'Home' page
    And I search invoice by "RO Number" on 'Payment' page
    And I validate "INVOICE DETAILS" on 'Payment' page
    And I click "SEARCH RESULTS" on 'Payment' page
    And I choose Email option on 'CREATE INVOICE' page
    And I click Proceed to direct payment button on 'CREATE INVOICE' page
    And I switch back to page title "Checkout"
    And I validate "RO Number" on 'Guest Payment' page
    And I click "Proceed to payment" on 'Guest Payment' page
    And I enter card details on 'Guest Payment' page
      | FieldName   | Value               |
      | Card Number | 4242 4242 4242 4242 |
      | Card Expiry |                1225 |
      | CVC         |                 234 |
      | Name        | Test Card           |
      | Country     | Canada              |
      | Postal Code | V2X 342             |      
    And I click "Pay" on 'Guest Payment' page
    And I validate "Successful Payment" on 'Guest Payment' page
    Given I launch browser "chrome" in "GUI" and open "<URL2>"
    And I login page "Yahoo" as User "dfxcucumbertest" and password "dealerfxqa"
    Then I see new email for payment completion in Yahoo inbox
    Then I click Make an Online Payment button
    And I validate "RO Number" on 'Guest Payment' page
    And I validate "Already Paid" on 'Guest Payment' page

    Examples: below data set for data driven
      | URL1     | DealerName            | Application | URL2  |
      | TEST2DFX | Nissan of Anytown USA | Payments    | YAHOO |
