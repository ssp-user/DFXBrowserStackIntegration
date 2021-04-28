## This is the apart of the regression test
@AC-RegressionRecall-Test2-Debug
Feature: AC_OEMRecall, Advisor Check In Morning Health Check on RO push, Test on ENV TEST2
  Description : The purpose of this feature verify the AC function work properly on Push RO in Prod2 with diversity
  DMS name and VINs Data

  @AC-RegressionRecall-Test2-Debug-Toyota
  Scenario Outline: Advisor check Verify OEM Recalls and Contract on RO push for <DMS>
  Description :
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
#    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-IBarn' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    And I click "ACKNOWLEDGE" button on 'CUSTOMER' page
    Then I see Following status on 'CUSTOMER' page
      | FieldName         | Value        |
      | VIEW WARRANTY     |  <WARRANTY>  |
      | SERVICE CONTRACT  |  <CONTRACT>  |
      | RECALLS           |  <Recalls>   |
    When I open "<VehicleMaker>" ServiceIcon on 'CUSTOMER' page
    Then I verify "<VehicleMaker>" OEM_ReportPDF status "<Recalls>" and "<CONTRACT>" on 'CUSTOMER' page
    When I enter "60000" in "ODOMETER" field on 'CUSTOMER' page
    And I click "NEXT" button on 'CUSTOMER' page
    Then I see "CONCERNS" tab is activated on 'CUSTOMER CONCERN' page
    When I click "SERVICES" tab on 'CUSTOMER CONCERN' page
    Then I see "VEHICLE HEALTH" tab is activated on 'SERVICES' page
    And I see number of <Recalls> and recalls descriptions and status of recalls
    Examples: below data set for data driven
      |URL     |DealerName          |DMS         |VehicleMaker|VIN               |Recalls|CONTRACT | WARRANTY |
      |TEST2AD |South Dade Toyota   |Recalls     |Toyota      |JTDBT4K3XA1352478 |Y      |N        | Y        |
      |TEST2AD |South Dade Toyota   |Recalls     |Toyota      |4T4BF3EK2AR031085 |N      |N        | Y        |


#  @AC-RegressionRecall-Test2-Debug-GM
#  Scenario Outline: Advisor check Verify OEM Recalls and Contract on RO push for <DMS>
#  Description :
#    Given I launch browser "chrome" in "GUI" and open "<URL>"
#    And I set "<DMS>" to the 'DMS'
#    When I login the Application as User "automation" and password "dealerfxqa"
#    And I select the Dealer with "<DealerName>" or "" in Dealer Page
#    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
#    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
#    And I deal with Customer Information PopUp in 'CUSTOMER' page
#    Then I see Following status on 'CUSTOMER' page
#      | FieldName         | Value        |
#      | VIEW WARRANTY     |  <WARRANTY>  |
#      | SERVICE CONTRACT  |  <CONTRACT>  |
#      | RECALLS           |  <Recalls>   |
#    When I open "<VehicleMaker>" ServiceIcon on 'CUSTOMER' page
#    And I verify "<VehicleMaker>" OEM_ReportPDF status "<Recalls>" and "<CONTRACT>" on 'CUSTOMER' page
#    Examples: below data set for data driven
#      |URL     |DealerName                |DMS         |VehicleMaker|VIN              |Recalls|CONTRACT | WARRANTY |
#      |TEST2AD |Midway Chevrolet GM       |Recalls     |GM          |1GT12UEY1HF226593|Y      |N        | Y        |
#      |TEST2AD |Midway Chevrolet GM       |Recalls     |GM          |1G4ZP5SZ4JU122474|N      |N        | Y        |


  @AC-RegressionRecall-Test2-Debug-Mitsubishi
  Scenario Outline: Advisor check Verify OEM Recalls and Contract on RO push for <DMS>
  Description :
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    Then I see Following status on 'CUSTOMER' page
      | FieldName         | Value        |
      | VIEW WARRANTY     |  <WARRANTY>  |
      | SERVICE CONTRACT  |  <CONTRACT>  |
      | RECALLS           |  <Recalls>   |
    When I open "<VehicleMaker>" ServiceIcon on 'CUSTOMER' page
    And I verify "<VehicleMaker>" OEM_ReportPDF status "<Recalls>" and "<CONTRACT>" on 'CUSTOMER' page
    Examples: below data set for data driven
      |URL     |DealerName          |DMS         |VehicleMaker|VIN              |Recalls|CONTRACT | WARRANTY |
      |TEST2AD |Barrie Mitsubishi   |Recalls     |Mitsubishi  |ML32A3HJ9FH040630|Y      |N        | Y        |
#      |TEST2AD |Barrie Mitsubishi   |Recalls     |Mitsubishi  |JA32U2FU4FU602712|N      |N        | Y        |


  @AC-RegressionRecall-Test2-Debug-Nissan
  Scenario Outline: Advisor check Verify OEM Recalls and Contract on RO push for <DMS>
  Description :
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    And I set "<DMS>" to the 'DMS'
    When I login the Application as User "automation" and password "dealerfxqa"
    And I select the Dealer with "<DealerName>" or "" in Dealer Page
    Then I verify Page Info "ng-scope" and Browser log in 'Advisor Check-In' page
    When I search 'VinNumber' "<VIN>" on 'Advisor Check-In' page
    And I deal with Customer Information PopUp in 'CUSTOMER' page
    And I see Following status on 'CUSTOMER' page
      | FieldName         | Value        |
      | VIEW WARRANTY     |  <WARRANTY>  |
      | SERVICE CONTRACT  |  <CONTRACT>  |
      | RECALLS           |  <Recalls>   |
    When I open "<VehicleMaker>" ServiceIcon on 'CUSTOMER' page
    And I verify "<VehicleMaker>" OEM_ReportPDF status "<Recalls>" and "<CONTRACT>" on 'CUSTOMER' page
    When I open "<VehicleMaker>" worksheet icon on 'CUSTOMER' page
    Then I verify "<VehicleMaker>" worksheet content on 'CUSTOMER' page
    Examples: below data set for data driven
      |URL     |DealerName          |DMS         |VehicleMaker|VIN              |Recalls|CONTRACT | WARRANTY |
      |TEST2AD |AUTOCENTERS NISSAN  |Recalls     |Nissan      |1N6AA07C68N321957|Y      |N        | Y        |
#      |TEST2AD |AUTOCENTERS NISSAN  |Recalls     |Nissan      |5N1AT2MT9EC853221|N      |Y        | Y        |
      |TEST2AD |AUTOCENTERS NISSAN  |Recalls     |Nissan      |3N1CB51D56L487412|N      |N        | Y        |
      |TEST2AD |AUTOCENTERS NISSAN  |Recalls     |Nissan      |5N1AR2MN3EC692834|Y      |Y        | Y        |