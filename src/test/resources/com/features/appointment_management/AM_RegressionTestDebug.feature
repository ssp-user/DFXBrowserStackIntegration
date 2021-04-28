@AM_Regression
Feature: This Test suite is Appointment Management Regression suite
  Description : to verify Appointment Management function work properly

  @AM_Regression_CreateNewAppointmentByVIN-TEST2-AUDI
  Scenario Outline: Verify the function of create a new appointment with the customer name selection by a VIN
    When I get the transportation options status on 'DASHBOARD' page
      |Transportation options : | Waiter | |  OwnRide | Rental | Loaner |Shuttle |  | RepeatRepaire | SpecialParts |

    Examples: below data set for data driven
      |URL          |DMS       |DealerName            |VIN                |ServiceAdvisor|Odometer |TagNumber|
      |TEST2ALAUDI   |Dummy     |Audi of anytown usa   |WA1VGAFPXFA007949  |1             |21431    |TagAu    |