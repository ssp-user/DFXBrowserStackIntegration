@PY07 @Regression
Feature: PY07_SearchInvoiceByDateFilter
  Description: Create Invoice. Search by Date Filter Criteria.

  Scenario Outline: Create Invoice. Search by Date Filter Criteria.
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "automation" and password "dealerfxqa"
    And I move to switch dealer window on 'Home' page
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    And I choose "<Application>" application from the 'Home' page
    And I click "CREATE INVOICE" on 'Payment' page
    And I manually add the customer details on 'Create Invoice' page
    And I set customer data on 'CREATE INVOICE' page
      | FieldName      | Value             |
      | Customer Name  | RANDOM            |
      | Advisor        | RANDOM            |
      | RO Number      | RANDOM            |
      | Invoice Amount |              1.25 |
      | Phone Number   | RANDOM            |
      | Email          | xyl@pp.com        |
      | VIN            | WAUDGAFL7BA002147 |
    And I choose Email option on 'CREATE INVOICE' page
    And I click Send payment request button on 'CREATE INVOICE' page
    And I validate "Successful sent invoice notification" on 'Payment' page
    And I click "CLOSE FOOTER NOTIFICATION" on 'Payment' page
    And I filter Invoice by "Customised Date Filter" on 'Payment' page
    And I validate "DATE FILTER INVOICE DETAILS" on 'Payment' page
    And I click "CLEAR FILTER" on 'Payment' page
  

    Examples: below data set for data driven
      | URL      | DealerName            | Application | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Payments    | Value4 |
