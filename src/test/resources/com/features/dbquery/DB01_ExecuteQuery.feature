# this will only work if the device have database access

Feature: this is use to execute the query and stored procedure
  @dbquery01
  Scenario: this is use to execute the query and stored procedure

    Given I connect with database "data01.prod2" with username "dfxqa" and password "dfxqa"
    When I execute query with DealerId "4453"
    And I disconnect database "data01.prod2"