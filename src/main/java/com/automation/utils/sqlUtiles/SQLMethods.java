package com.automation.utils.sqlUtiles;

public class SQLMethods {

	
	public static void removeVINfromDB(String vin){
		MSSQL.dbConnect(ENV.getEnvironmentDBAddress());
		String dealerID = ENV.getDealerID();
		String vehicleID = ENV.getVehicleID(vin);
		String customerID = ENV.getCustomerID(vehicleID, dealerID); 
		
		MSSQL.deleteQuery(Query.deleteRecordCustomerVehicleDealerProgram(vehicleID, dealerID));
		MSSQL.deleteQuery(Query.deleteRecordTransactionDetails(vehicleID, customerID, dealerID));
		MSSQL.deleteQuery(Query.deleteRecordCustomer(customerID));
		MSSQL.disConnect();
	}

	public static void vinLookUp(String dealerID){
		MSSQL.execStoreProcedure(Query.testQueryFromEllen(dealerID));
	}

}
