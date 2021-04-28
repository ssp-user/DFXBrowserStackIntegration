# this will only work if the device have database access

Feature: this is use to send get/post web service
  @dbquery01
  Scenario: this is use to send get/post web service

    Given I open web service "app01.test2:8080/MaintenanceMenu.asmx?op=GetCustomerInfoFromDMSbyDealerIDandVIN"
    And I send post web service with dealerID "11111" and vin "22222222222222222"