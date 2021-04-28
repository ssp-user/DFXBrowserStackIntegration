package com.automation.utils.sqlUtiles;

public class Query {

	public static String testQueryFromEllen(String dealerID){
		String query = "select DMSCustomerID,cvd.CustomerID,DealerProgramID,c.firstname,c.lastname,DefaultPhone,HomePhone,CellPhone,EmailHome,EmailBusiness,cvd.DealerID,cvd.VehicleID,CustomerVehicleState,DealerID,TransactionID,VIN\n" +
				"from MTProduction.dbo.tbCustomerVehicleDealerProgram cvd " +
				"inner join MTProduction.dbo.tbvehicle v on cvd.VehicleID=v.VehicleID " +
				"inner join MTProduction.dbo.tbCustomer c on cvd.CustomerID=c.CustomerID " +
				"where dealerid="+dealerID+" and v.VIN in ('ML32F3FJ1JHF00203','ML32A4HJ8EH018848','JA4AZ2A37GZ603364','JA4AH3AU1HZ608482');";
		return query;
	}

	
	public static String clearVehicleState(String dealerID, String vin){
		String query = " declare @stateId varchar(36) "
				+ "select @stateId = s.Id "
				+ "from MTStates..StateSharePolicy_Dealer d "
				+ "join MTStates..[States] s on d.StateId=s.Id "
				+ "where dealerID = " + dealerID + " "
				+ "and Name = 'SAD-VIN" + vin + "' " 
				+ "print @stateId "
				+ "exec MTStates..usp_DeleteSharedState @id = @stateId "
				+ "";
		return query;
	}
	
	
	public static String getVehicleID(String vin){																					// Return VehicleID for given VIN
		String query = ""
					+ "select VehicleID "
					+ "from MTProduction..tbvehicle "
					+ "where VIN = '" + vin + "' "
					+ "";
		return query;
	}
	
	
	public static String getCustomerID(String vehicleID, String dealerID){															// Return CustomerID for given VehicleID		
		String query = ""
					+ "select CustomerID "
					+ "from MTProduction..tbCustomerVehicleDealerProgram "
					+ "where VehicleID = " + vehicleID + " and DealerID = " + dealerID
					+ " ";
		return query;
	}
	
	
	public static String deleteRecordCustomerVehicleDealerProgram(String vehicleID, String dealerID){								// Delete record
		String query = ""
					+ "delete "
					+ "from MTProduction..tbCustomerVehicleDealerProgram "
					+ "where VehicleID = " + vehicleID + " and DealerID = " + dealerID
					+ " "
					+ "";
		return query;
	}
	
	public static String deleteRecordTransactionDetails(String vehicleID, String customerID, String dealerID){						// Delete Transaction record
		String query = ""
					+ "delete "
					+ "from MTProduction..tbTransactionDetails "
					+ "where (VehicleID = " + vehicleID + " or CustomerID = " + customerID + ") "
					+ "and DealerID = " + dealerID
					+ " ";
		return query;
	}

	
	
	public static String deleteRecordCustomer(String customerID){																	// Delete Customer record
		String query = ""	
					+ "delete "
					+ "from MTProduction..tbCustomer "
					+ "where CustomerID = " + customerID
					+ " ";
		return query;
	}
		
	/*public static String updateMakeModelOff(String dealerid,String onoff){															// update vehicle specification as OFF/On for OSS3
		String query = ""	
					+ "update "
					+ "MTProduction..tbApplicationSettingValues "
					+ "SET Value =  "+ onoff
					+ " from MTProduction..tbApplicationSettingValues "
					+ "JOIN MTProduction..tbApplicationSettings "
					+ "ON MTProduction..tbApplicationSettingValues.settingID=MTProduction..tbApplicationSettings.settingID "
					+ "where MTProduction..tbApplicationSettingValues.dealerid = " + dealerid
					+ " and MTProduction..tbApplicationSettings.settingID=78 "
					+ " ";
		System.out.println(query);
		return query;
	}*/
	
	
/*	public static String deleteVehicle(String vehicleID){																			// Delete Vehicle record
		String query = ""
					+ "delete "
					+ "from MTProduction..tbVehicle"
					+ "where VehicleID = " + vehicleID
					+ " ";
		return query;
	}
*/
}
