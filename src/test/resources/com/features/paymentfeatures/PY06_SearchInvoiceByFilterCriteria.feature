@PY06 @Regression
Feature: PY06_SearchInvoiceByFilterCriteria
  Description: Create Invoice. Search by Filter Criteria Status and Advisor

  Scenario Outline: Create Invoice. Search by Filter Criteria Status and Advisor.
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
    And I filter Invoice by "Status" on 'Payment' page
    And I validate "STATUS FILTER INVOICE DETAILS" on 'Payment' page
    And I click "CLEAR FILTER" on 'Payment' page
    And I filter Invoice by "Advisor" on 'Payment' page
    And I validate "ADVISOR FILTER INVOICE DETAILS" on 'Payment' page
    And I click "CLEAR FILTER" on 'Payment' page    

    Examples: below data set for data driven
      | URL      | DealerName            | Application | Field4 |
      | TEST2DFX | Nissan of Anytown USA | Payments    | Value4 |
