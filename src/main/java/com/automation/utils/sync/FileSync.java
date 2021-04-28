package com.automation.utils.sync;

import com.automation.pages.PageManager;
import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.Set;

public class FileSync  {

    public static File fileLocation = new File(".\\test-output\\temp_download");

    /***
     * Wait for PDF to finish download.
     */
    public static void waitForPdfDownloaded(){
        File folder = fileLocation;
//        int totalWaitTime = 20000;	// 20 Seconds as max waiting time
        int totalWaitTime = 120000;	// 120 Seconds as max waiting time        
        int interval = 500;			// 500 ms

        for(int i = 0; i <= totalWaitTime; i=i+interval){
            if(folder.list().length > 0 && pdfDownloadCompleted(folder)){
                System.out.println("PDF downloaded");
                break;
            } else {
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     * Check to see if the PDF file name had changed, to indicate if PDF completed download
     * @param downloadFolder Target PDF Download Folder
     * @return True if file name change to ".pdf"
     */
    private static boolean pdfDownloadCompleted(File downloadFolder){
        try{
            String path = downloadFolder.listFiles()[0].getPath();
            if(path.substring(path.length()-4).equalsIgnoreCase(".pdf")){
                return true;
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException e){		// Exception occurred when download not started
            return false;
        }
    }

    /***
     * Check to see
     * @return TRUE if PDF downloaded. FALSE if PDF open from browser
     */
    public static boolean is_PDF_Download(){
        boolean result = false;
        boolean done = false;
//        int wait = 10000;
        int wait = 120000;        
        int interval = 500;
        System.out.println("<=Waiting for PDF download 120 seconds ..... =>");
        while(!done && wait >= 0) {
            if(isDownloading()){
                result = true;
                done = true;
                break;
            }
            if(isOnlinePDF()){
                result = false;
                done = true;
                break;
            }
            CommonMethods.sleep(interval);
            wait = wait - interval;
        }
        if(wait < 0){
            System.out.println("<====== Unknow error occurred on processing PDF ======>");
        }

        return result;
    }



    private static boolean isDownloading(){
        // Check to see if any file is downloading
        File targetFolder = fileLocation;
//        System.out.println("<====== downloading begin Wait ... ======>");
        CommonMethods.sleep(20000);
        if(targetFolder.list().length > 0){
            System.out.println("<====== targetFolder.list.length > 0 - file downloading or download completed ======>");
            return true;
        } else {
//            System.out.println("<== No download PDF file found  =>");
//            System.out.println("<====== "+targetFolder.getAbsolutePath()+" ======>");
//            System.out.println("<====== "+String.valueOf(targetFolder.list().length)+" ======>");
            return false;
        }
    }


    private static boolean isOnlinePDF(){																// Check to see if PDF from URL, open from Browser
        //WebDriver driver = BaseTestClass.getDriver();
        boolean result = false;
        WebDriver driver = PageManager.getDriver();
        CommonMethods.sleep(20000);

        String parentWindowID = SyncPage.parentWindowID;
        try{
            if(driver.getWindowHandles().size() > 1){
                Set<String> tabs = driver.getWindowHandles();

                for(String tab : tabs){
                    if(!tab.equalsIgnoreCase(parentWindowID) && driver.getWindowHandles().size() > 1){
                        driver.switchTo().window(tab);
                        if(driver.getPageSource().contains("ommcache")){
                            System.out.println("<====== inside the if(driver.getPageSource() method.) ======>");
                            System.out.println("<====== "+driver.getPageSource().toString()+" ======>");
                            System.out.println(driver.getCurrentUrl());

                            if(driver.getCurrentUrl().contains("ommcache")){
                                result = true;
                                break;
                            } else {
                                System.out.println("<====== New Tab URL: "+driver.getCurrentUrl()+" ======>");
                                driver.switchTo().window(parentWindowID);
                                result = false;
                            }
                        } else {
                            driver.switchTo().window(parentWindowID);
                            result = false;
                        }
                    }
                    driver.switchTo().window(parentWindowID);
                    result = false;
                }
            } else {result = false;}
        } catch(NoSuchWindowException | NullPointerException e){
            System.out.println("<====== Window closed ======>");
            driver.switchTo().window(parentWindowID);
            result = false;
        } catch(Exception | Error e){
            System.out.println("<====== Error under the isOnlinePDF method ======>");
            e.printStackTrace();
            driver.switchTo().window(parentWindowID);
            result = false;
        }
        return result;
    }



}
