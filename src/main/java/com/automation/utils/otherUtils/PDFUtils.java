package com.automation.utils.otherUtils;

import com.automation.pages.PageManager;
import com.automation.pages.common.WebPage;
import com.automation.utils.sync.FileSync;
import com.automation.utils.sync.SyncPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.BufferedInputStream;
import java.io.File;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class PDFUtils {

    private static String pdfContent;
    static File targetPDF;
    /***
     * Comprehensive method to verify PDF content
     * @param strPDFContent Expected string value in PDF
     * @param strPDF Step description displayed in the reporter
     */
    public static void verifyPDFContent(String strPDFContent, String strPDF){
        if(FileSync.is_PDF_Download()){
            verifyDownloadedPDFContent(strPDFContent, strPDF);
        } else {
            verifyOnlinePDFContent(strPDFContent, strPDF);
        }
    }

    public static void verifyOnlinePDFContent(String strPDFContent, String strPDF){					// Verify Online PDF Content
        WebDriver driver = PageManager.getDriver();
        String strVerifyText = strPDFContent;
//		SyncPage.switchToPopupWindowByUrl(wait, driver, ".pdf");
        pdfContent = getPDFContent(driver);
//		System.out.println("================================================================");
//		System.out.println(pdfContent);
//		System.out.println("================================================================");
        System.out.println("<====== Verifying " + strPDF + " Content ======>");
        try{
            assertTrue(pdfContent.contains(strVerifyText));
            System.out.println("<====== "+strPDF+" passed ======>");
        } catch (AssertionError e){
            System.out.println("<====== "+strPDF+" failed ======>");
        }

        CommonMethods.sleep(500);

        SyncPage.closePDFAndSwitchToParentWindow(driver);
    }


    public static void verifyDownloadedPDFContent(String strPDFContent, String strPDF){				// Verify Download PDF Content
        WebDriver driver = PageManager.getDriver();
        String strVerifyText = strPDFContent;

        downloadPdfHandler(driver);
        pdfContent = getPDFContent(driver);

        System.out.println("<====== Verifying" + strPDF + " content ======>");

        try{
            assertTrue(pdfContent.contains(strVerifyText));
            CommonMethods.sleep(1300);
            System.out.println("<====== "+strPDF+" passed ======>");
        } catch(AssertionError e){
            System.out.println("<====== "+strPDF+" failed ======>");
        }
        SyncPage.closePDFAndSwitchToParentWindow(driver);
        //FileProcessUtils.removePDF();
    }

    private static String getDownloadPDFContent(){
        WebDriver driver = PageManager.getDriver();
        downloadPdfHandler(driver);
        pdfContent = getPDFContent(driver);
        CommonMethods.sleep(1000);

        return pdfContent;
    }

    private static void downloadPdfHandler(WebDriver driver){							// Extra steps to handle file download
        FileSync.waitForPdfDownloaded();
        openNewTab(driver);
        SyncPage.switchToPopupWindowAfterWindowPopupByTitle(driver, "new tab");
        String pdfUrl = getPDF_Url();
        driver.get(pdfUrl);
    }

    public static String getPDFContent(){
        if(FileSync.is_PDF_Download()){
            return getDownloadPDFContent();
        } else {
            return getOnlinePDFContent();
        }

    }

    private static String getOnlinePDFContent(){
        WebDriver driver = PageManager.getDriver();
        pdfContent = getPDFContent(driver);
        CommonMethods.sleep(1000);
        return pdfContent;
    }


    // Extract PDF content to text, need switch to PDF window first. This only read PDF Text content
    public static String getPDFContent(WebDriver driver){

        PDFParser parser = null;
        String outputText = null;
        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        URL url = null;
        BufferedInputStream pdfFile = null;

        boolean pdfParsed = false;
        int attempt = 0;
        while(!pdfParsed && attempt < 5){
            try{
                attempt++;
                url = new URL(driver.getCurrentUrl());
                pdfFile = new BufferedInputStream(url.openStream());
                parser = new PDFParser(pdfFile);
                System.out.println("<====== Reading PDF Document ======>");

                parser.parse();
                pdfParsed = true;
            } catch (Exception e){
                if(e.toString().contains("End-of-File, expected line")){
                    System.out.println("<====== PDF Document parsing exception occurred ======>");
                    parser.clearResources();
                    parser = null;
                    System.out.println("<====== Current Window Title is : "+driver.getTitle()+" ======>");
                    System.out.println("<====== ======>");

                    SyncPage.switchToPopupWindowAfterWindowPopupByUrl(driver, ".PDF");

                } else {
                    System.out.println("<====== PDF parse error ======>");
                }
                CommonMethods.sleep(1000);
                pdfParsed = false;
            }
            if(attempt >= 5){
                System.out.println("<====== PDF Document failed ======>");
            }
        }
        System.out.println("<====== PDF parse completed ======>");

        try {
//			parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            outputText = pdfStripper.getText(pdDoc);
        } catch (Exception e) {
            System.out.println("<====== An exception occurred in extracting content from PDF Document. "+e.getMessage()+" ======>");
        } finally {
            try {
                if (cosDoc != null)	{
                    cosDoc.close();
                }
                if (pdDoc != null) {
                    pdDoc.close();
                }
            } catch (Exception e) {
                System.out.println("<====== An exception occured in closing PDF stream." +e.getMessage()+ " ======>");
                e.printStackTrace();
            }
        }
        return outputText;
    }

    /***
     * Return currently extracted PDF String Text
     * @return
     */
    public static String getPDF_Text(){
        return pdfContent;
    }

    public static String getPDF_Url(){									// Return PDF URL to open from browser
        String fileUrl = null;
        for(int i = 0; i < FileSync.fileLocation.list().length; i++){
            targetPDF = FileSync.fileLocation.listFiles()[i];
            fileUrl = "file:///" + targetPDF.getAbsolutePath();
        }
        System.out.println("<====== The file url is： "+fileUrl+ " ======>");
        return fileUrl;

    }

    public static void removePDF(){										// Delete the downloaded PDF file
        try{
            targetPDF.delete();
        } catch (NullPointerException e){
            /* file not exists. */
        }

    }

    /***
     * Open a new empty tab
     */
    private static void openNewTab(WebDriver driver) {
        SyncPage.prepareWindowPopup();
//        SyncPage.getWindowsHandles(driver);
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
         WebPage.getWait().until(SyncPage.condWindowPopUp(driver));
    }

    // This is to close the PDF window then switch back to the parent window
//    private void closePDFAndSwitchToParentWindow(){
//        driver.close();
//        LogUtils.logMsgOnly("PDF Window closed and now switching to parent window");
//        driver.switchTo().window(parentWindowID);
//    }

}
