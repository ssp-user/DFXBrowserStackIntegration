package com.automation.utils.sqlUtiles;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

/*import com.dealerfx.log.LogTestStats;
import com.dealerfx.log.LogUtils;
import com.dealerfx.test.BaseTestClass;*/


public class ENV {

	static String prod2 = "data01.prod2";
	static String prod2AWS = "34.207.106.75";
	static String prod1 = "data01.prod";
	static String prod1AWS = "52.205.11.135";
	static String pilot1 = "data01.pilot1";
	static String stage = "data01.stg";
	static String stageAWS = "52.3.158.54";
	static String test1 = "data01.test";
	static String test2 = "52.6.198.119";
	static String dev = "dfxdb.corp.internal-projects.com";
	
	
	public static String getEnvironmentDBAddress(){
		//String env = LogTestStats.getTestingEnv();
		String env = "PROD 2";
		String db = null;
		switch(env){
			case "PROD 1" : db = prod1; 
							break;
			case "PROD 1(AWS)" : db = prod1AWS;
							break;
			case "PILOT 1" : db = pilot1;
							break;
			case "PROD 2" : db = prod2;
							break;
			case "PROD 2(AWS)" : db = prod2AWS;
							break;
			case "STAGE 1" : db = null; 	// For future development
							break;
			case "STAGE 2" : db = null; 	// For future development
							break;
			case "AWS STAGE" : db = stageAWS;
							break;
			case "STAGE" :  db = stage;
							break;	
			case "TEST 1" : db = test1;
							break;
			case "TEST 2" : db = test2;
							break;
			case "Iydro DEV" : db = dev;
							break;
		}
		//LogUtils.logConsoleMsg("Connecting DataBase: " + env);
		System.out.println("Connecting DataBase: " + env);
		return db;
	}
	
	
	public static String getDealerID(){
		String dealerID = "100298";
		// Get Current Test DealerID
		/*WebDriver driver = BaseTestClass.getDriver();
		String url = driver.getCurrentUrl();
		String dealerID = null;

		String reg = "(/\\d+/)";		// This can handle AD and AL application
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(url);
		if(m.find()){
			dealerID = m.group();
			dealerID = dealerID.replaceAll("/", "");
			LogUtils.logConsoleMsg("DealerID is: " + dealerID);
		} else {
			LogUtils.logError("DealerID not found from the URL. URL: " + url);
		}*/
		return dealerID = "100298";
	}
	
	public static String getVehicleID(String vin){																// Get Current Test VehicleID
		/*try{
			List<List<String>> vehicldID = MSSQL.retrieveData(Query.getVehicleID(vin));
			System.out.println(vehicldID.get(0).get(0));
			return vehicldID.get(0).get(0);
		} catch (IndexOutOfBoundsException e){
			return null;
		}*/
		String vehicleID = "";
		return vehicleID;
	}
	
	public static String getCustomerID(String vehicleID, String dealerID){										// Get Current Test CustomerID
		try{
			List<List<String>> custoemrID = MSSQL.retrieveData(Query.getCustomerID(vehicleID, dealerID));
			System.out.println(custoemrID.get(0).get(0));
			return custoemrID.get(0).get(0);
		} catch(IndexOutOfBoundsException e){
			return null;
		}
	}
}
