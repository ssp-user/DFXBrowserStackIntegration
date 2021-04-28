package com.autotest.utils;

import com.automation.utils.log.LogUtils;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class TestInit {

    private static String comment = "This is a test from wiAdvisor. Please ignore this.";
    private static String comment_Prod2 = "This is a test from DealerFX. Please ignore this.";

    public static File downloadFolder = new File("test-output\\temp_download");
    final static String reportFilePath = "Test Report";

//    {
//        File file = new File("libraries/3rd_Party_Libs/atu.properties");
//        System.setProperty("atu.reporter.config", file.getAbsolutePath());
//    }


    private static void cleanUp() {
        File report = new File(reportFilePath);
        try {
            FileUtils.cleanDirectory(report);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            LogUtils.logMsgOnly("Report Not Found");
        }
    }


    public static void createFolder() {                                                                                                // Create Download Folder if not exist
        File outputFolder = new File("test-output");
        if (!outputFolder.exists()) {
            try {
                outputFolder.mkdir();
            } catch (SecurityException e) {
                LogUtils.logError(e.getMessage());
            }
        }

        if (!downloadFolder.exists()) {
            try {
                downloadFolder.mkdir();
            } catch (SecurityException e) {
                LogUtils.logError(e.getMessage());
            }
        }
    }

    private static void removeDownloadedFile() {                                                                                        // Remove previous test downloaded files
        try {
            FileUtils.cleanDirectory(downloadFolder);
        } catch (IllegalArgumentException e) {
            // Folder not exists
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void killProcess() {
        Process p;
        String chrome = "chromedriver.exe";
        String ie = "IEDriverServer.exe";
        try {
            p = Runtime.getRuntime().exec("tasklist");
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
//		  		System.out.println(line);
                if (line.contains(chrome)) {
                    Runtime.getRuntime().exec("taskkill /f /im " + chrome);
                    System.out.println("ChromeDriver process Found and Ended");

                } else if (line.contains(ie)) {
                    Runtime.getRuntime().exec("taskkill /f /im " + ie);
                    System.out.println("IE Driver process Found and Ended");

                }
            }
            System.out.println("Kill Process Completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getComment(String callerClass) {                                                                            // Return General Comment
        if (callerClass.toLowerCase().contains("prod2")) {
            return comment_Prod2;
        } else {
            return comment;
        }
    }

    public static void testBeginHead(){
        System.out.println(" ===============================================================================");
        System.out.println("\t\t\t   Start of Cucumber Test Features ");
        Date today = new Date();
        System.out.println(" ");
        System.out.println("\t\t\t     " + today );
        System.out.println(" ===============================================================================\n");
    }

}
