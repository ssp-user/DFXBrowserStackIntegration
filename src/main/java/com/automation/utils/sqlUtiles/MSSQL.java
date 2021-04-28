package com.automation.utils.sqlUtiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*import com.dealerfx.log.LogUtils;
import com.itextpdf.text.log.SysoCounter;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;*/

public class MSSQL {
	
	
	static Connection conn = null;
	static ResultSet rs = null;
	static Statement statement = null;
	static PreparedStatement ps = null;
	
	
	public static void dbConnect(String db){
		try{
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	         conn = DriverManager.getConnection("jdbc:sqlserver://" + db + ";user=dfxqa;password=dfxqa;database=MTProduction");
	         //LogUtils.logConsoleMsg("DataBase Connected.");
		} catch(Exception e){
			e.printStackTrace();
		}
    }

    public static void dbConnectWithUserNameAndPassword(String dataBaseName, String userName, String passWord){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://" + dataBaseName + ";user="+userName+";password="+passWord+";database=MTProduction");
            //LogUtils.logConsoleMsg("DataBase Connected.");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

	
    /***
     * Execute provided query on Database, return as 2-dimension ListArray
     * @param query Provided query to execute
     * @return
     */
		public static List<List<String>> retrieveData(String query) {
			List<List<String>> result = new ArrayList<List<String>>();
			List<String> subResult;
			
			try{
				statement = conn.createStatement();
				rs = statement.executeQuery(query);
				ResultSetMetaData rsMetaData = rs.getMetaData();
				
				int col_count = rsMetaData.getColumnCount();
				
				while (rs.next()) {
					subResult = new ArrayList<String>();
					for(int i = 1; i <= col_count; i++){
						subResult.add(rs.getString(i));
					}
					result.add(subResult);
				}
				System.out.println("<====== SQL query execute successfully ======>");
			} catch (Exception e) {
				System.err.println(e.toString());
				e.printStackTrace();
			}
			return result;
		}
		
		
		
		public static void updateQuery(String query){
			try{
				statement = conn.createStatement();
				statement.execute(query);
				
			} catch(Exception e){
				System.err.println(e.toString());
				e.printStackTrace();
			}
		}
		
		
		public static void execStoreProcedure(String query){
			try{
				ps = conn.prepareStatement(query);
				ps.setEscapeProcessing(true);
				ResultSet rs = ps.executeQuery();
				
			} catch(Exception e){
				System.err.println(e.toString());
				e.printStackTrace();
			}
		}
		
		
		public static void deleteQuery(String query){
			try{
				ps = conn.prepareStatement(query);
				ps.execute();
				
			} catch(Exception e){
				System.err.println(e.toString());
				e.printStackTrace();
			}
		}

		public static void testQuery(String query){
			try{
				ps = conn.prepareStatement(query);
				ps.execute();

			} catch(Exception e){
				System.err.println(e.toString());
				e.printStackTrace();
			}

		}
		
		
		public static void disConnect(){
			try {
				conn.close();
				rs.close();
				statement.close();
				ps.close();
				System.out.println("<====== SQL Server - Disconnected ======>");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void disConnect1(){
			try {
				conn.close();
				statement.close();
				System.out.println("<====== SQL Server - Disconnected ======>");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
}
