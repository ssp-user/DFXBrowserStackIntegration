package com.automation.pages.appointment_manager;

import com.automation.utils.otherUtils.PDFUtils;
import com.automation.utils.sync.SyncPage;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AM_ServicePageHelpPDF extends AM_FramePage {

    PDFUtils pdf = new PDFUtils();

    public String getOPENMenuPDF(){
        String content =pdf.getPDFContent();
        SyncPage.closePDFAndSwitchToParentWindow(driver);
        return content;
    }

    public String clickGetPreServiceWritePDF(){
//        clickPreServiceWriteLink();
        String content =pdf.getPDFContent();
        return content;
    }

    public void  removePDF(){
        pdf.removePDF();
    }

    public void  cleanPDF(){
        SyncPage.closePDFAndSwitchToParentWindow(driver);
        pdf.removePDF();
    }


    public static String getRegExr(String src, String regexr){
        Pattern p = Pattern.compile(regexr, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
        Matcher m = p.matcher(src);
        String result = null;
        if(m.find()){
            result = m.group().toLowerCase();
        }
        return result;
    }

    public void verifyMaintMenu(String pdfContent, String field1, String field2, String field3){
//        String reg = "(?<="+field1.toLowerCase()+".{1,20}"+field2.toLowerCase()+".{1,20}"+field3.toLowerCase()+").+?(?=(whichever comes first|"+field4+"|"+field5+"|"+field6+"))";
//        String reg = "(?<=previous.{1,20}current.{1,20}next).+?(?=(whichever comes first|Estimated Maintenance Cost|SIGNATURE AUTHORIZING|Grand Total))";
        String reg = "(?<=previous.{1,20}current.{1,20}next).+?(?=(whichever comes first|"+field1+"|"+field2+"|"+field3+"))";
        String content = getRegExr(pdfContent, reg);

        int lines = Integer.valueOf(StringUtils.countMatches(content, "\n"));
//		int countTotal = Integer.valueOf(StringUtils.countMatches(content, "total"));
        int countChar = content.length();
        if(lines > 10 && countChar > 320){
            System.out.println("<====== Maintenance Menu PDF verified ======>");
        } else {
            System.out.println("<====== Number of Lines: " + lines + " ======>");
            System.out.println("<====== Number of Characters: " + countChar + " ======>");
            Assert.fail("<====== Maintenance Menu PDF failed ======>");
        }
    }

    public void verifyMaintMenu_VW_Time(String pdfContent){
        String reg = "(?<=previous).+?(?=(Required Scheduled Services))";
        String content = getRegExr(pdfContent, reg);
        String reg2 = "(?<=CURRENT INTERVAL INSPECTION).+?(?=\\*)";
        String content2 = getRegExr(pdfContent, reg2);
        if(content.contains("months") && !content.contains("mi") && content2.contains("months") && !content2.contains("mi")){
            System.out.println("<====== Maintenance Menu - VW Time "+" Service provide based on xx months passed ======>");
        } else {
            System.out.println("<====== Maintenance Menu - VW Time "+" Service provide based on xx month "+" Fail ======>");
            System.out.println(content);
        }
    }

    public void verifyMaintMenu_VW_Mileage(String pdfContent){
        String reg = "(?<=previous).+?(?=(Required Scheduled Services))";
        String content = getRegExr(pdfContent, reg);
        String reg2 = "(?<=CURRENT INTERVAL INSPECTION).+?(?=\\*)";
        String content2 = getRegExr(pdfContent, reg2);

        if(content.contains("mi") && !content.contains("months") && content2.contains("mi") && !content2.contains("months")){
            System.out.println("<====== Maintenance Menu - VW Mileage "+" Service provide based on xx Mileage "+" Pass ======>");
        } else {
            System.out.println("<====== Maintenance Menu - VW Mileage "+" Service provide based on xx Mileage "+" Failed ======>");
            System.out.println(content);
        }
    }


}
