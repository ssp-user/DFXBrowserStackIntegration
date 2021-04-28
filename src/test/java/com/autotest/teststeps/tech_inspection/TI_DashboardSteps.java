package com.autotest.teststeps.tech_inspection;

import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.List;
import java.util.Map;

import com.automation.pages.tech_inspection.dashboard.TechInspectSearchPage;
import com.automation.pages.tech_inspection.dashboard.CreateMPIPage;

import static com.automation.utils.dataProvider.TestParameters.randomText;
import static org.junit.Assert.*;

public class TI_DashboardSteps extends BaseTestSteps {

    TechInspectSearchPage page = new TechInspectSearchPage();
    CreateMPIPage cPage = new CreateMPIPage();
    private static String actualSearch = "";

    @When("^I launch 'Search popup panel' in Inspection dashboard$")
    public void iOpenSearchPopupPanelInInspectionDashboard() throws Throwable {
        page.openSearchPanel();
    }

    /**
     * Load Search Data set in Serach panel, you can select any one or more
     * of fields, if Value is blank or field Name is absent means this field not input using
     * default value
     *
     * @param dataTable information about shifts should be selected<pre>
     *
     *| FieldName        |Value                              |<br>
     *| By VIN           | WAUEFGFF2F1086081                 |<br>
     *| Tag              | Tag989                            |<br>
     *| Last Name        | Gates                             |<br>
     *| RO#              | RO989                             |<br>
     *| Status           | Created,Started,Completed         |<br>
     *| Created By       | ALL SELECTED                      |<br>
     *| Assigned To      | John, Chris                       |<br>
     *| From Date Year   | -1                                |<br>
     *| From Date Month  | -2                                |<br>
     *| From Date Day    | +1                                |<br>
     *| TO   Date Year   |                                   |<br>
     *| TO   Date Month  |                                   |<br>
     *| TO   Date Day    |                                   |</pre>
     * @throws Throwable
     */
    @When("^I load search data set in search popup panel$")
    public void iLoadSearchData(DataTable dataTable) throws Throwable {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                switch (fieldName) {
                    case "By VIN":
                        page.setVIN(processText(value));
                        break;
                    case "Tag":
                        page.setTag(value);
                        break;
                    case "Last Name":
                        page.setLastName(processText(value));
                        break;
                    case "RO#":
                        page.setRO(value);
                        break;
                    case "Status":
                        page.selectStatus(value);
                        break;
                    case "Created By":
                        page.selectCreatedBy(processText(value));
                        break;
                    case "Assigned To":
                        page.selectAssignedTo(processText(value));
                        break;
                    case "From Date Year":
                        page.setFromYear(processText(value));
                        break;
                    case "From Date Month":
                        page.setFromMonth(processText(value));
                        break;
                    case "From Date Day":
                        page.setFromDay(value);
                        break;
                    case "To Date Year":
                        page.setToYear(processText(value));
                        break;
                    case "To Date Month":
                        page.setToYear(processText(value));
                        break;
                    case "To Date Day":
                        page.setToYear(value);
                        break;
                }
            }
        }
        actualSearch = page.performSearch();
    }

    /**
     * Load Search Data set in Serach panel, you can select any one or more
     * of fields, if Value is blank or field Name is absent means this field not input using
     * default value
     *
     * @param dataTable information about shifts should be selected<pre>
     *| FieldName        |Value                              |<br>
     *| By VIN           | WAUEFGFF2F1086081                 |<br>
     *| Tag              | Tag989                            |<br>
     *| Last Name        | Gates                             |<br>
     *| RO#              | RO989                             |<br>
     *| Status           | Created,Started,Completed         |<br>
     *| Created By       | ALL SELECTED                      |<br>
     *| Assigned To      | John, Chris                       |<br>
     *| From Date Year   | -1                                |<br>
     *| From Date Month  | -2                                |<br>
     *| From Date Day    | +1                                |<br>
     *| TO   Date Year   |                                   |<br>
     *| TO   Date Month  |                                   |<br>
     *| TO   Date Day    |                                   |</pre>
     * @throws Throwable
     */

    @When("^I enter data set in Create MPI popup window$")
    public void iEnterDatasetInCreateMPIpopupWindow(DataTable dataTable) throws Throwable {
        CreateMPIPage page = new CreateMPIPage();
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                if (fieldName.equals("VIN")) {
                    page.setVIN(value);
                } else if (fieldName.equals("Customer Name")) {
                    page.setCustomerName(value);
                } else if (fieldName.equals("Phone")) {
                    page.setCustomerPhone(processText(value));
                } else if (fieldName.equals("Make")) {
                    page.selectMake(processText(value));
                } else if (fieldName.equals("Year")) {
                    page.selectYear(value);
                } else if (fieldName.equals("Model")) {
                    page.selectModel(value);
                } else if (fieldName.equals("Transmission")) {
                    page.selectTransmission(processText(value));
                } else if (fieldName.equals("Engine Size")) {
                    page.selectEngineSize(processText(value));
                } else if (fieldName.equals("Drive Train")) {
                    page.selectDriveTrain(value);
                } else if (fieldName.equals("Mileage")) {
                    page.checkMileages();
                } else if (fieldName.equals("Mileage Value")) {
                    page.setMileages(processText(value));
                } else if (fieldName.equals("Months")) {
                    page.checkMonths();
                } else if (fieldName.equals("Months Value")) {
                    page.setMonths(processText(value));
                } else if (fieldName.equals("First Name")) {
                    page.setFirstName(processText(value));
                } else if (fieldName.equals("Last Name")) {
                    page.setLastName(value);
                } else if (fieldName.equals("Business Check")) {
                    page.checkBusiness(processText(value));
                } else if (fieldName.equals("Address")) {
                    page.setAddress(value);
                } else if (fieldName.equals("City")) {
                    page.setCity(processText(value));
                } else if (fieldName.equals("Country")) {
                    page.selectCountry(processText(value));
                } else if (fieldName.equals("State/Province")) {
                    page.selectState(processText(value));
                } else if (fieldName.equals("Postal Code")) {
                    page.setPostalCode(processText(value));
                } else if (fieldName.equals("County")) {
                    page.selectCounty(processText(value));
                } else if (fieldName.equals("Default Phone")) {
                    page.setDefaultPhone(value);
                } else if (fieldName.equals("Cell Phone")) {
                    page.setCellPhone(processText(value));
                } else if (fieldName.equals("Email")) {
                    page.setEmail(processText(value));
                } else if (fieldName.equals("Contact Method")) {
                    page.selectContactMethod(processText(value));
                } else if (fieldName.equals("Preferred language")) {
                    page.selectLanguage(processText(value));
                } else if (fieldName.equals("Service Advisor")) {
                    page.selectAdvisor(processText(value));
                } else if (fieldName.equals("Technician")) {
                    page.selectTechnician(processText(value));
                } else if (fieldName.equals("TAG")) {
                    page.setTag(processText(value));
                } else if (fieldName.equals("RO")) {
                    page.setRO(processText(value));
                } else if (fieldName.equals("Lot Number")) {
                    page.setLotNumber(processText(value));
                } else if (fieldName.equals("License Plate")) {
                    page.setLicensePlate(processText(value));
                } else if (fieldName.equals("Promise Date")) {
                    page.selectPromiseDate(processText(value));
                } else if (fieldName.equals("Promise Time")) {
                    page.selectPromiseTime(processText(value));
                } else if (fieldName.equals("Quarter Date")) {
                    page.selectQuarterDate(processText(value));
                } else if (fieldName.equals("Quarter Time")) {
                    page.selectQuarterTime(processText(value));
                } else if (fieldName.equals("Inspection Type")) {
                    page.selectInspectionType(processText(value));
                } else if (fieldName.equals("Transportation")) {
                    page.selectTransportation(processText(value));
                } else if (fieldName.equals("Save parts")) {
                    page.checkSaveParts(processText(value));
                } else if (fieldName.equals("Car Wash")) {
                    page.checkCarWash(value);
                } else if (fieldName.equals("Glove box")) {
                    page.checkGlovebox(processText(value));
                } else if (fieldName.equals("Spare tire")) {
                    page.checkSparetire(value);
                } else if (fieldName.equals("Comments")) {
                    page.setComments(processText(value));
                }
            }
        }
    }


    /**
     * Search Result are displayed on dashboard , you can select any one or more
     * of fields.
     *
     * @param dataTable information about shifts should be selected<pre>
     *| FieldName        |Value                      |<br>
     *| VIN              | WAUEFGFF2F1086081         |<br>
     *| TAG#             | Tag989                    |<br>
     *| CUSTOMER NAME    | Gates                     |<br>
     *| RO#              | RO989                     |<br>
     *| AdVisor          | John                      |<br>
     *| Technician       | Chris                     |<br>
     *| ARRIVAL TIME     | Feb 06 2018 9:30AM        |<br>
     *| PROMISE TIME     | Feb 08 2018 10:30AM       |<br>
     *| INSPECTION STATUS| CREATED                   |</pre>
     * @throws Throwable
     */

    @Then("^I see the record on the first line in dashboard$")
    public void iSeeTheResultOnFirstLineInDashboard(DataTable dataTable){
        /*if (page.isUserLoginWithAD().contains("Service Advisor")){
            page.pageRefresh();
            page.clearSelection();
            page.performSearch();
        }*/
        StringBuffer expect = new StringBuffer("-->");
        StringBuffer actul = new StringBuffer("-->");
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if(value.contains("TAG")){
                value = randomText;
            }
            expect.append(" " + (processEmpty(value)));
            switch (fieldName) {
                case "VIN":
                    actul.append(" " + (processEmpty(page.getVIN())));
                    break;
                case "TAG":
                    actul.append(" " + (processEmpty(page.getTag())));
                    break;
                case "CUSTOMER NAME":
                    actul.append(" " + (processEmpty(page.getCustomerName())));
                    break;
                case "RO":
                    actul.append(" " + (processEmpty(page.getRO())));
                    break;
                case "AdVisor":
                    actul.append(" " + (processEmpty(page.getAdvisor())));
                    break;
                case "Technician":
                    actul.append(" " + (processEmpty(page.getTechnician())));
                    break;
                case "ARRIVAL TIME":
                    actul.append(" " + (processEmpty(page.getArrivalTime())));
                    break;
                case "PROMISE TIME":
                    actul.append(" " + (processEmpty(page.getPromiseTime())));
                    break;
                case "INSPECTION STATUS":
                    actul.append(" " + (processEmpty(page.getInspectionStatus())));
                    break;
            }
        }
        assertEquals(expect.toString(), actul.toString());
    }

    @And ("^I decode (.+) in MPI popup windows$")
    public void iDecodeVin(String vIN){
        cPage.decodeVin();
    }

    @And("^I select \"([^\"]*)\" in Created By drop down in Search MPI popup$")
    public void iSelectCreatedBy(String dropDownName){
        page.selectCreatedByAllUser(dropDownName);
    }

    @Then("^I see vehicle with \"([^\"]*)\" with \"([^\"]*)\" status on 'TECHNICIAN INSPECTION Dashboard'$")
    public void iVerifyCarStatusWithVIN(String VIN, String status){
//        long startTime = System.currentTimeMillis();
        String actualStatus = page.getInspectionStatusByVin(VIN);
//        while (!actualStatus.equals(status)){
//            actualStatus = page.getInspectionStatusByVin(VIN);
//            if(actualStatus.equals(status)){
//                break;
//            }else if((System.currentTimeMillis()-startTime)> 10000){
//                break;
//            }
//        }
        assertEquals(status, actualStatus);
    }

    @When("^I click vehicle \"([^\"]*)\" on 'TECHNICIAN INSPECTION Dashboard'$")
    public void iClickVehicleWithVin(String VIN){
        page.clickOnStatusWithVin(VIN);
    }

    @Then("^I expect launch EVIR (.+) in 'TECHNICIAN INSPECTION Dashboard' within \"([^\"]*)\" seconds$")
    public void iExpectedLaunchEVIRINSeconds(String VIN, String expectedSeconds){
        if(expectedSeconds.equals("FIVE")){
            expectedSeconds = "5";
        }
        page.verifyEVIRLaunchTimeWithVin(VIN,expectedSeconds);
    }

    @When("^I search vehicle (.+) in 'TECHNICIAN INSPECTION Dashboard'$")
    public void iSearchVehicleWithTag(String tAG){
        page.searchVehicleWithTag(tAG);
    }

    @Then("^I see the message \"(.*)\" in the search popup panel$")
    public void iSeeMessageInsearchPopupPanel(String msg ) throws Throwable {
        assertEquals(msg, actualSearch);
    }

    @Then("^I do not see the record on the first line in dashboard$")
    public void iNotSeeTheResultOnFirstLineInDashboard(DataTable dataTable) {
        String expt, actl;
        StringBuffer expect = new StringBuffer("-->");
        StringBuffer actul = new StringBuffer("-->");
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if(value.contains("TAG")){
                value = randomText;
            }
            expect.append(" " + (processEmpty(value)));
            switch (fieldName) {
                case "VIN":
                    actul.append(" " + (processEmpty(page.getVIN())));
                    break;
                case "TAG#":
                    actul.append(" " + (processEmpty(page.getTag())));
                    break;
                case "CUSTOMER NAME":
                    actul.append(" " + (processEmpty(page.getCustomerName())));
                    break;
                case "RO#":
                    actul.append(" " + (processEmpty(page.getRO())));
                    break;
                case "AdVisor":
                    actul.append(" " + (processEmpty(page.getAdvisor())));
                    break;
                case "Technician":
                    actul.append(" " + (processEmpty(page.getTechnician())));
                    break;
                case "ARRIVAL TIME":
                    actul.append(" " + (processEmpty(page.getArrivalTime())));
                    break;
                case "PROMISE TIME":
                    actul.append(" " + (processEmpty(page.getPromiseTime())));
                    break;
                case "INSPECTION STATUS":
                    actul.append(" " + (processEmpty(page.getInspectionStatus())));
                    break;
            }
        }
        expt = expect.toString(); actl=actul.toString();
        assertNotEquals(expt, actl);
    }

    @When("^I submit 'Complete' in Create MPI popup window$")
    public void iClickCompleteButtonInCreateMPIwindow() throws Throwable {
        CreateMPIPage page = new CreateMPIPage();
        page.doComplete();
    }

    @Then("^I launch 'Create MPI' popup window in Inspection dashboard$")
    public void iOpenCreateMPIpopupWindowInInspectiondashboard() throws Throwable {
        page.createMPI();
    }

    @When("^I delete the first line record in dashboard$")
    public void iDeleteTheFirstRecordDashboard() throws Throwable {
        page.deleteFirstRecord();
        Thread.sleep(500);
    }

    @And("^I bulk delete all the record in the inspection page$")
    public void iDeleteAllRecord() throws AWTException{
        page.deleteAllRecord();
    }

    @Then("^I should see no record in the inspection page$")
    public void iVerifyRecord(){
        page.isRecordExist();
    }

    @And("^I click service lane with (.+) on 'MPI List' page$")
    public void iClickServiceLane(String vIN){
        page.clickOnServiceLane(vIN);
    }

}


