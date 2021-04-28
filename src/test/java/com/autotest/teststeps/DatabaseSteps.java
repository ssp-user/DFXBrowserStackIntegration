package com.autotest.teststeps;

import com.automation.utils.sqlUtiles.MSSQL;
import com.automation.utils.sqlUtiles.SQLMethods;
import cucumber.api.java.en.And;

public class DatabaseSteps extends BaseTestSteps{


    @And("^I connect with database \"([^\"]*)\" with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iExecuteQuery(String dataBaseName, String userName, String passWord){
        MSSQL mssql = new MSSQL();
        mssql.dbConnectWithUserNameAndPassword(dataBaseName,userName,passWord);
    }

    @And("^I execute query with DealerId \"([^\"]*)\"$")
    public void iExecuteQuery(String dealerid){
        SQLMethods sm = new SQLMethods();
        sm.vinLookUp(dealerid);
    }

    @And("^I disconnect database \"([^\"]*)\"$")
    public void iDisconnectDatabase(String dataBaseName){
        MSSQL mssql = new MSSQL();
        mssql.disConnect();
    }
}
