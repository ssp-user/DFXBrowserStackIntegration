package com.automation.utils.webServiceUtils;

//this class is use to send get/post web serv

import com.automation.pages.common.WebPage;
import org.openqa.selenium.JavascriptExecutor;



public class WebService extends WebPage {

    public String RetrieveServicelaneCustomers(String env, String env2){
//        return "https://"+env+"crmliteapi"+env2+".dealer-fx.com/api/Message/RetrieveServiceLaneCustomers";
        String currentURL = driver.getCurrentUrl().toLowerCase();
        int iBegin = currentURL.indexOf("dealer-fx.com/");
        int iEnd = currentURL.indexOf('?');
        String sdId = currentURL.substring(iBegin+14,iEnd);
        return "https://"+env+"crmliteapi"+env2+".dealer-fx.com/api/Message/RetrieveServiceLaneCustomers?dealerId=" + sdId;
//        return "https://"+env+"crmliteapi"+env2+".dealer-fx.com/api/Message/RetrieveServiceLaneCustomers?dealerId=";
    }

    public String getCurrentNetWorkLog(String serviceName, String environment){
        String env = "";
        String env2 = "";
        String webServicelurl = "";
        String serviceStatus = "";
        switch (environment){
            case "TEST2DFX":
                env = "test2";
                env2 = "";
                break;
            case "PRD2NISS":
                env = "";
                env2 = "";
                break;
            case "STG2NISS":
                env = "stage2";
                env2 = "";
                break;
            case "PROD1":
                env = "cga";
                env2 = "1";
                break;
            case "PILOT1":
                env = "pilot1";
                env2 = "";
                break;
            case "TEST1DFX":
                env = "testcga";
                env2 = "";
                break;
        }
        switch (serviceName){
            case "RetrieveServicelaneCustomers":
                webServicelurl = RetrieveServicelaneCustomers(env,env2);
                break;
        }
        System.out.println("<====== The webservice url is "+webServicelurl+" ======>");
        String scriptToExecute = "var resource = window.performance.getEntriesByName('"+webServicelurl+"'); return resource;";
        String netData = ((JavascriptExecutor)driver).executeScript(scriptToExecute).toString();
        System.out.println(netData);
        int index = netData.indexOf(webServicelurl);
        int count = 0;
        while (index != -1) {
            count++;
            netData = netData.substring(index + 1);
            index = netData.indexOf(webServicelurl);
        }
        if(count>1){
            serviceStatus = "200 OK";
        }else{
            serviceStatus = "500";
            System.out.println("<====== The service "+serviceName+" get 500 status, test fail ======>");
        }
        return serviceStatus;
    }

}
