package com.automation.utils.dataProvider;

import org.openqa.selenium.WebDriver;
import com.automation.utils.otherUtils.HttpStats;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/*
 * @Function: Provider  Test Data / Parameters  for whole  project
 * @author: David  at Dealer-FX
 * */

public class TestParameters {

    public static String parentWindow;
    //customer connect variable
    //String to store before after count value
    public static String userName;
    //customer connect variable
    public static String vehicleName;
    public static String callLogsNumber;
    public static String callAttempts;
    public static String tasksCountBefore;
    public static String tasksCountAfter;
    public static String inProgressTasksBefore;
    public static String inProgressTasksAfter;
    public static String closedTasksBefore;
    public static String closedTasksAfter;
    public static String callsMadeBefore;
    public static String callsMadeAfter;
    public static String closedTasksCampaignBefore;
    public static String closedTasksCampaignAfter;
    public static String callsMadeCampaignBefore;
    public static String callsMadeCampaignAfter;
    public int numberOfDuplicateVehicleBefore;
    public int numberOfDuplicateVehicleAfter;
    public static String customerNameOnMsg;
    public static String customerNameOnSearch;
    public static String customerNameOnTask;
    public static String customerNoteTxt;
    public static String timeStampOnSMSPageBefore;
    public static String timeStampOnSMSPageAfter;
    public static String newCreatedEmailSubject = "";
    public static String sms;
    public static String vinOnTaskDetail;

    //Advisor Check-In
    public static String numOfRecalls;

    //DMPI variable
    public static String randomText;
    public static String phoneNumber;
    public static String customerFullName;
    public static String answeredByTimeOnServiceDashboard;
    public static String deliverByTimeOnServiceDashboard;
    public static String countDownMinuteOnDMPI;
    public static String vehicleReadyTimeOnDMPI;

    //appointment manager variable
    public static String appointmentDay;
    public static String appointmentTime;

    //service dashboard variable
    public static String appointmentCustomerName;

    //service dashboard variable
    public static String ButtonNotClickableBackgroundColor = "rgba(230, 230, 230, 1)";
    public static String ButtonClickableBackgroundColor = "rgba(3, 3, 3, 1)";

    public static String ServiceItemSelectedCSS = "-100px -50px" + "0px -58px";
    public static String ServiceItemDeSelectedCSS = "-150px -50px" + "0% 0%";

    public static String ConcernItemSelectedCSS = "0px -58px";

//    public static ArrayList<String> selectedItemList;

    static String oem = null;

    //Globe parameters
    private static String UserName;
    private static String Password;
    private static String DealerCode;
    private static String UrlEnv;
    private static String DMSName;
    private static String BrowserName;
    private static String TestEnv;

    private static boolean FCA;


    public static void setUserName(String userName) {
        UserName = userName;
    }

    public static void setDealerCode(String dealerCode) {
        DealerCode = dealerCode;
    }

    public static void setUrlEnv(String urlEnv) {
        UrlEnv = urlEnv;
    }

    public static void setBrowserName(String browserName) {
        BrowserName = browserName;
    }

    public static void setDMSName(String dmsName) {
        DMSName = dmsName;
    }

    public static void setTestEnv(String testEnv) {
        TestEnv = testEnv;
    }

    public static void setFCA(boolean isFCA) {
        FCA = isFCA;
    }

    // Get the Value

    public static String getUserName() {
        return UserName;
    }

    public static String getDealerCode() {
        return DealerCode;
    }

    public static String getUrlEnv() {
        return UrlEnv;
    }

    public static String getBrowserName() {
        return BrowserName;
    }

    public static String getDMSName() {
        return DMSName;
    }

    public static String getTestEnv() {
        return TestEnv;
    }

    public static boolean getFCA() {
        return FCA;
    }

    public static void updateTestEnv(String url) {
        String node = HttpStats.getX_Node(url);
        if (node.toLowerCase().contains("test2")) {
            TestEnv = "TEST 2";
            FCA = false;
        } else if (node.toLowerCase().contains("test")) {
            TestEnv = "TEST 1";
            FCA = true;
        } else if (node.toLowerCase().contains("aws.stg")) {
            TestEnv = "AWS STAGE";
            FCA = true;
        } else if (node.toLowerCase().contains("stage1")) {
            TestEnv = "STAGE1";
            FCA = true;
        } else if (node.toLowerCase().contains("stage2")) {
            TestEnv = "STAGE2";
            FCA = true;
        } else if (node.toLowerCase().contains(".prod2")) {
            TestEnv = "PROD 2";
            FCA = false;
        } else if (node.toLowerCase().contains("prod2-")) {
            TestEnv = "PROD 2(AWS)";
            FCA = false;
        } else if (node.toLowerCase().contains("pilot")) {
            TestEnv = "PILOT 1";
            FCA = true;
        } else if (node.toLowerCase().contains("prod-")) {
            TestEnv = "PROD 1";
            FCA = true;
        } else if (node.toLowerCase().contains("emea")) {
            TestEnv = "Middle East";
            FCA = true;
        } else if (!node.contains(".") && node != "null" && !node.isEmpty()) {
            TestEnv = "PROD 1";
            FCA = true;
        } else if (node.equalsIgnoreCase("null") && (url.contains("https://test.wiadvisor.dcctools.com") || url.contains("https://uidev.wiadvisor"))) {
            TestEnv = "TEST 1";
            FCA = true;
        } else if (node.equalsIgnoreCase("null") && url.contains("https://gui.staging.wiadvisor.dcctools.com")) {
            TestEnv = "STAGE";
            FCA = true;
        } else if (node.equalsIgnoreCase("null") && url.contains("https://wiadvisor.com")) {
            TestEnv = "PROD 1";
            FCA = true;
        } else if (node.equalsIgnoreCase("null") && url.contains("https://www.wiadvisor.com")) {
            TestEnv = "PROD 1(AWS)";
            FCA = true;
        } else if (node.equalsIgnoreCase("null") && url.contains("https://pilot.wiadvisor.com")) {
            TestEnv = "PILOT 1";
            FCA = true;
        } else if (node.equalsIgnoreCase("null") && url.contains("corp.internal-projects.com")) {
            TestEnv = "Iydro DEV";
            FCA = true;
        } else if (node.toLowerCase().contains("scheduler2")) {        //OSS3 on Prod 2
            TestEnv = "PROD 2";
            FCA = false;
        } else if (node.toLowerCase().contains("scheduler")) {            //OSS3 on Prod 1
            TestEnv = "PROD 1";
            FCA = true;
        } else if (node.toLowerCase().contains("pilot1scheduler")) {    //OSS3 on Pilot 1
            TestEnv = "PILOT 1";
            FCA = true;
        } else {
            TestEnv = "Unknown";
        }

    }


    public static ArrayList<String> getDMS() {                                                // Return DMS with DealerCode for OMM_Admin

        ArrayList<String> dms = new ArrayList<String>();
//		dms.add("Dummy, 99970");
        dms.add("Automate, Schaefer & Bierlein");
        dms.add("CDK, Thompson Chrysler Jeep Dodge");
        dms.add("DealerBuilt, Marshall Motor Company");
        dms.add("MPK, Dublin Auto Superstore");
        dms.add("Autosoft, Maple City Dodge, Chrysler, Jeep, Ram");
        dms.add("PBS, Big Valley Automotive, Inc.");
        dms.add("R&R, Ourisman Chrysler Dodge Jeep Ram");
        dms.add("DealerTrack, Brenner Chrysler Jeep");
        return dms;
    }

    public static String getTest_VIN() {                                                        // Return Testing VIN
//		return "WAUFFAFL6DN003670";
        return "WAUJ8GFFXG1034028";
    }


    public static String getOdometer() {                                                        // Return Vehicle Mileage
        return "35001";
    }

    public static String getDummyDealer() {                                                    // Return Dummy Dealer based on testing environment
        if (FCA) {
            return "Chrysler QA";
        } else {
            return "Audi of Anytown USA";
        }
    }


    public static String get_OEM_DummyDealer(String url) {                                                // Return OEM Specific Dummy Dealer

        if (url.contains("micar") || url.contains("mmscan") || url.contains("mitsubishicanada")) {                        // Mitsubishi Canada
            oem = "Mitsubishi";
//			return "Mitsubishi of Anytown Canada";
            return "anytown usa automall";

        } else if (url.contains("audi")) {                                                    // Audi
            oem = "Audi";
            return "anytown usa automall";

        } else if (url.contains("vw")) {                                                        // VW
            oem = "VW";
            return "VW OF ANYTOWN CANADA";

        } else if (url.contains("toyota")) {                                                    // Toyota
            oem = "Toyota";
            return "toyota of anytown usa";

        } else if (url.contains("lexus")) {                                                    // Lexus
            oem = "Lexus";
            return "lexus of anytown usa";

        } else if (url.contains("nissan")) {                                                    // Nissan
            oem = "Nissan";
            return "nissan of anytown usa";

        } else if (url.contains("infiniti")) {                                                // Infiniti
            oem = "Infiniti";
            return "Anytown Infiniti USA";

        } else if (url.contains("chrysler")) {                                                // Chrysler
            oem = "Chrysler";
            return "Chrysler QA";
        } else {
            oem = "Unknown";
            return "Chrysler QA";
        }
    }

    /*
    if(url.contains("https://test.wiadvisor.dcctools.com")){															// wiAdvisor on TEST
        env = "TEST";
    } else if(url.contains("https://gui.staging.wiadvisor.dcctools.com")){												// wiAdvisor on STAGE
        env = "STAGE";
    } else if(url.contains("https://wiadvisor.com")){																	// wiAdvisor on PROD
        env = "PROD";
    } else if(url.contains("//testservice.dealer-fx.com")){																// Advisor Dashboard on TEST
        env = "TEST";
    } else if(url.contains("//stageservice.dealer-fx.com")){															// Advisor Dashboard on STAGE
        env = "STAGE";
    } else if(url.contains("//service.dealer-fx.com")){																	// Advisor Dashboard on PROD
        env = "PROD";
    } else if(url.contains("//audi.dealer-fx.com")){																	// Audi on PROD
        env = "Audi PROD";
    } else if(url.contains("//stageaudi.dealer-fx.com")){																// Audi on STAGE
        env = "Audi STAGE";
    } else if(url.contains("//testaudi.dealer-fx.com")){																// Audi on TEST
        env = "Audi TEST";
    } else if(url.contains("//mitsubishicanada.dealer-fx.com/")){														// Mitsubishi on PROD
        env = "Mit PROD";
    } else if(url.contains("//stagemitsubishicanada.dealer-fx.com/")){													// Mitsubishi on STAGE
        env = "Mit STAGE";
    } else if(url.contains("//testmitsubishicanada.dealer-fx.com/")){													// Mitsubishi on TEST
        env = "Mit TEST";
    }
    else {
        env = "Unknown";
    }
    */
    public static String get_MDL_VIN() {                                                    // VIN for New Vehicle Arrivals (NON-FCA)
        return "WAUJ8GFFXG1034028";
    }

    public static String getOEM() {
        return oem;
    }


}
