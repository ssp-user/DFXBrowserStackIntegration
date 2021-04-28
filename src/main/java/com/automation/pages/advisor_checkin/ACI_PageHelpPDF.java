package com.automation.pages.advisor_checkin;

import com.automation.utils.otherUtils.PDFUtils;
import com.automation.utils.sync.SyncPage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ACI_PageHelpPDF extends ACI_AuthorizationPage {
	
	public static String RO = null;	

    public static String getRO() {
		return RO;
	}

	public static void setRO(String rO) {
		RO = rO;
	}

	private static Logger log = Logger.getLogger(ACI_PageHelpPDF.class.getName());
    PDFUtils pdf = new PDFUtils();

    public String clickGetOnMMPDFShow(){
        clickOnMMenuPDFShow();
        String content =pdf.getPDFContent();
        return content;
    }

    public String clickGetOnRorPDFShow(){
        clickOnWriteUpPDFShow();
        String content =pdf.getPDFContent();
        return content;
    }

    public String clickGetOnEvirPDFShow(){
        clickOnEvirPDFShow();
        String content =pdf.getPDFContent();
        return content;
    }

    public String clickGetOnPartsPickPDFShow(){
        clickOnPartsPickPDFShow();
        String content =pdf.getPDFContent();
        return content;
    }

    public String clickGetMaintMenuPDFContent(){
        clickMaintMenuBtn();
        waitForMaintMenuReady();
        String content =pdf.getPDFContent();
        SyncPage.closePDFAndSwitchToParentWindow(driver);
        return content;
    }

    public String clickShowGetCustomerQuote(){
//        SyncPage.prepareWindowPopup();
        clickShowCustomerQuote();
        String content =pdf.getPDFContent();
        return content;
    }

    public String clickShowGetInternalQuote(){
//        SyncPage.prepareWindowPopup();
        clickShowInternalQuote();
        String content =pdf.getPDFContent();
        return content;
    }

    public String clickShowGetMenuShow(){
//        SyncPage.prepareWindowPopup();
        clickShowCustomerQuote();
        String content =pdf.getPDFContent();
        return content;
    }

    public String getPDFContents(){
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

    public String verifyMaintenMenu(String field){
        String reg = "(?<=previous.{1,20}current.{1,20}next).+?(?=(whichever comes first|" +field +"|SIGNATURE AUTHORIZING|Grand Total))";
        if (!clickOnMMenuPDFShow()){
            log.warn(" Maintenance Menu PDF - Not Generated ");
            System.out.println(" Warning !!  Maintenance Menu PDF - Not Generated ");
            return "X";
        }
        String pdfContent = pdf.getPDFContent();
        String content = getRegExr(pdfContent, reg);
        int lines = Integer.valueOf(StringUtils.countMatches(content, "\n"));
        int countChar = content.length();
        if(lines > 9 && countChar > 320){
            System.out.println("<====== Maintenance Menu PDF verified ======>");
             return "Y";
        } else {
            log.error("<== Lines: " + lines + ",  Characters:  " + countChar + "  ====>");
            System.out.println("<== Lines: " + lines + ",  Characters:  " + countChar + "  ====>");
            return "N";
        }
    }

//    public String verifyMaintenMenu(String pdfContent, String field1, String field2, String field3, String field4, String field5, String field6){
//        String reg1 = "(?<="+field1.toLowerCase()+".{1,20}"+field2.toLowerCase()+".{1,20}"+field3.toLowerCase()+").+?(?=(whichever comes first|"+field4+"|"+field5+"|"+field6+"))";
//        String reg = "(?<=previous.{1,20}current.{1,20}next).+?(?=(whichever comes first|Estimated Maintenance Cost|SIGNATURE AUTHORIZING|Grand Total))";
//
//        String content = getRegExr(pdfContent, reg);
//
//        int lines = Integer.valueOf(StringUtils.countMatches(content, "\n"));
////		int countTotal = Integer.valueOf(StringUtils.countMatches(content, "total"));
//        int countChar = content.length();
//        if(lines > 10 && countChar > 400){
//            System.out.println("<====== Maintenance Menu PDF verified ======>");
//        } else {
//            System.out.println("<====== Number of Lines: " + lines + " ======>");
//            System.out.println("<====== Number of Characters: " + countChar + " ======>");
//            Assert.fail("<====== Maintenance Menu PDF failed ======>");
//        }
//    }

    public String verifyPreServiceWriteUp(String field){
        if (!clickOnWriteUpPDFShow()){
            log.warn(" Pre-Service Write-Up PDF - Not Generated ");
            System.out.println(" Warning !!  Pre-Service Write-Up PDF - Not Generated ");
            return "X";
        }
        String pdfContent = pdf.getPDFContent();
        if(pdfContent.contains(field)){
            System.out.println("<== Pre-Service Write-Up verified ===>");
        	setRO(pdfContent.substring(pdfContent.indexOf("RO #") + 6, pdfContent.indexOf("ID")).trim());
            return "Y";
        }else{
            log.error("<== Pre-Service Write-Up verified Failed !!===>");
            System.out.println("<== Pre-Service Write-Up verified Failed !!===>");
            return "N";
        }
    }

    public String verifyEVIR(String field){
        if (!clickOnEvirPDFShow()){
            log.warn(" EVIR PDF - Not Generated ");
            System.out.println(" Warning !!  EVIR PDF - Not Generated ");
            return "X";
        }
        String pdfContent = pdf.getPDFContent();
        if(pdfContent.contains(field)){
            System.out.println("<== EVIR PDF verified ===>");
            return "Y";
        }else{
            log.error("<== EVIR PDF verified Failed !!===>");
            System.err.println("<== EVIR PDF verified Failed !!===>");
            return "N";
        }

    }

    public String verifyPartsPickList(String field){
        if (!clickOnPartsPickPDFShow()){
            log.warn(" Parts pick list PDF - Not Generated ");
            System.out.println(" Warning !!  Parts pick list PDF - Not Generated ");
            return "X";
        }
        String pdfContent = pdf.getPDFContent();
        if(pdfContent.contains(field)){
            System.out.println("<== Parts pick list PDF verified ===>");
            return "Y";
        }else{
            log.error("<== Parts pick list PDF verified Failed !!===>");
            System.err.println("<== Parts pick list PDF verified Failed !!===>");
            return "N";
        }
    }

    public boolean  hasMaintMenuFields(String pdfContent, String field1, String field2, String field3, String field4, String field5, String field6){
        String reg = "(?<="+field1.toLowerCase()+".{1,20}"+field2.toLowerCase()+".{1,20}"+field3.toLowerCase()+").+?(?=(whichever comes first|"+field4+"|"+field5+"|"+field6+"))";
        String content = getRegExr(pdfContent, reg);
        boolean result = false ;
        int lines = Integer.valueOf(StringUtils.countMatches(content, "\n"));
//		int countTotal = Integer.valueOf(StringUtils.countMatches(content, "total"));
        int countChar = content.length();
        if(lines > 10 && countChar > 400){
            result = true;
        } else {
            log.error("<== Lines: " + lines + ",  Characters:  " + countChar + "  ====>");
            System.err.println("<== Lines: " + lines + ",  Characters:  " + countChar + "  ====>");
        }
        return  result ;
    }

    public boolean  okMaintMenuVWTime(){
        String pdfContent =pdf.getPDFContent();
        //String reg = "(?<=previous).+?(?=(Required Scheduled Services))";
        String reg = "(?<=PREVIOUS).+?(?=\\*)";
        String content = getRegExr(pdfContent, reg);
        //String reg2 = "(?<=CURRENT INTERVAL INSPECTION).+?(?=\\*)";
        String reg2 = "(?<=CURRENT).+?(?=\\*)";
        String content2 = getRegExr(pdfContent, reg2);
        System.out.print("\n\n regex1........."+content+"\n\n regex2..........."+content2);
        boolean result = false ;
        if(content.contains("months") && !content.contains("mi") && content2.contains("months") && !content2.contains("mi")){
            result = true;
            System.out.println("<====== Maintenance Menu - VW Time "+" Service provide based on xx months passed ======>");
        } else {
            System.out.println("<====== Maintenance Menu - VW Time "+" Service provide based on xx month "+" Fail ======>");
            System.out.println(content);
        }
        return  result ;
    }
    
    public boolean okMaintMenuVWMileage(){
        String pdfContent =pdf.getPDFContent();
        //String reg = "(?<=previous).+?(?=(Required Scheduled Services))";
        String reg = "(?<=PREVIOUS).+?(?=\\*)";
        String content = getRegExr(pdfContent, reg);
        //String reg2 = "(?<=CURRENT INTERVAL INSPECTION).+?(?=\\*)";
        String reg2 = "(?<=CURRENT).+?(?=\\*)";
        String content2 = getRegExr(pdfContent, reg2);
        System.out.print("\n\n regex1........."+content+"\n\n regex2..........."+content2);
        boolean result = false ;
        if(content.contains("mi") && !content.contains("months") && content2.contains("mi") && !content2.contains("months")){
            result = true;
            System.out.println("<====== Maintenance Menu - VW Mileage "+" Service provide based on xx Mileage "+" Pass ======>");
        } else {
            System.out.println("<====== Maintenance Menu - VW Mileage "+" Service provide based on xx Mileage "+" Failed ======>");
            System.out.println(content);
        }
        return  result ;
    }

    public String hasRecallServiceCommOpenCampaign(String pdfContent){
        String reg = "(?<=OPEN CAMPAIGN\\(S\\)).+?(?=\nSERVICE CONTRACTS)";
        Pattern p = Pattern.compile(reg, Pattern.DOTALL);
        Matcher m = p.matcher(pdfContent);
        String serviceComm = "";
        if(m.find()){
            serviceComm = m.group();
            System.out.println("In PDF I found recalls: " + serviceComm);
        } else {
            serviceComm = "NOT FOUND";
            log.error("Service Comm OPEN CAMPAIGN - NOT FOUND");
            System.out.println("Service Comm OPEN CAMPAIGN - NOT FOUND");
        }
        if(!serviceComm.contains("No information found")){
            return  "Y";
        }else{
            return  "N";
        }
    }

    public String  hasServiceCommServiceContract(String pdfContent){
        //		String reg = "(?<=SERVICE CONTRACTS).+?(?=\nWARRANTY EXCLUSIONS INFORMATION)";
        String reg = "(?<=SERVICE CONTRACTS).+?(?=\nNISSAN ONE TO ONE REWARDS)";  // updated July, on Nissan One To One
        Pattern p = Pattern.compile(reg, Pattern.DOTALL);
        Matcher m = p.matcher(pdfContent);
        String serviceComm = "";
        if(m.find()){
            serviceComm = m.group();
            System.out.println("In PDF I found contracts: " + serviceComm);
        } else {
            serviceComm = "NOT FOUND";
            log.error("Service Comm OPEN CAMPAIGN - NOT FOUND");
            System.out.println("Service Comm OPEN CAMPAIGN - NOT FOUND");
        }
        if(!serviceComm.contains("No information found")){
            return  "Y";
        }else{
            return  "N";
        }
    }

    public String hasRecallMitsubishi(String pdfContent){
        String reg = "(?<=Open Recalls / Service Campaigns).+?(?=\nPromotions)";
        Pattern p = Pattern.compile(reg, Pattern.DOTALL);
        Matcher m = p.matcher(pdfContent);
        String serviceComm = "";
        if(m.find()){
            serviceComm = m.group();
            System.out.println("In PDF I found recalls: " + serviceComm);
        } else {
            serviceComm = "NOT FOUND";
            log.error("Service Comm Open Recalls / Service Campaigns - NOT FOUND");
            System.out.println("Service Comm Open Recalls / Service Campaigns - NOT FOUND");
        }
        if(!serviceComm.contains("N/A")){
            return  "Y";
        }else{
            return  "N";
        }
    }

    public String hasRecallGM(String pdfContent){
        String reg = "(?<=Required Field Action Items).+?(?=\nClosed Recalls)";
        Pattern p = Pattern.compile(reg, Pattern.DOTALL);
        Matcher m = p.matcher(pdfContent);
        String serviceComm = "";
        if(m.find()){
            serviceComm = m.group();
            System.out.println("In PDF I found recalls: " + serviceComm);
        } else {
            serviceComm = "NOT FOUND";
            log.error("Service Comm Required Field Action Items - NOT FOUND");
            System.out.println("Service Comm Required Field Action Items - NOT FOUND");
        }
        if(serviceComm.contains("OPEN") | serviceComm.contains("INCOMPLETE")){
            return  "Y";
        }else{
            return  "N";
        }
    }

    public String hasContractGM(String pdfContent){
        String reg = "(?<=Service Contracts).+?(?=\nService Information)";
        Pattern p = Pattern.compile(reg, Pattern.DOTALL);
        Matcher m = p.matcher(pdfContent);
        String serviceComm = "";
        if(m.find()){
            serviceComm = m.group();
            System.out.println("In PDF I found contracts: " + serviceComm);
        } else {
            serviceComm = "NOT FOUND";
            log.error("Service Comm Service Contracts - NOT FOUND");
            System.out.println("Service Comm Service Contracts - NOT FOUND");
        }
        if(!serviceComm.contains("No information found")){
            return  "Y";
        }else{
            return  "N";
        }
    }

    public String hasRecallServiceLaneOpenCampaign(String pdfContent){
        String reg = "(?<=Open Campaign\\(s\\)).+?(?=\nService History)";
        /* Above Regular Expression catch Strings between Open Campaign and Service History, also .+? will catch new line as DOTALL mode */
        Pattern p = Pattern.compile(reg, Pattern.DOTALL);
        Matcher m = p.matcher(pdfContent);

        String campaignContent = "";
        if(m.find()){
            campaignContent = m.group();
        } else {
            campaignContent = "NOT FOUND";
            log.error("Open Campaigns OPEN CAMPAIGN - NOT FOUND");
            System.out.println("Open Campaigns OPEN CAMPAIGN - NOT FOUND");
        }
        if(!campaignContent.contains("No information found")){
            return  "Y";
        }else{
            return  "N";
        }
    }

    public String hasContractToyota(String pdfContent){
        String reg = "(?<=Service Connect).+?(?=\nToyotaCare)";
        Pattern p = Pattern.compile(reg, Pattern.DOTALL);
        Matcher m = p.matcher(pdfContent);
        String serviceComm = "";
        if(m.find()){
            serviceComm = m.group();
        } else {
            serviceComm = "NOT FOUND";
            log.error("Service Comm Service Contracts - NOT FOUND");
            System.out.println("Service Comm Service Contracts - NOT FOUND");
        }
        if(!serviceComm.contains("No information found")){
            return  "Y";
        }else{
            return  "N";
        }
    }

    public String hasContractServiceLaneToyotaCare(String pdfContent){
        String reg = "(?<=ToyotaCare).+?(?=\nWarranty)";
        Pattern p = Pattern.compile(reg, Pattern.DOTALL);
        Matcher m = p.matcher(pdfContent);

        String toyotaCare = "";
        if(m.find()){
            toyotaCare = m.group();
        } else {
            toyotaCare = "NOT FOUND";
            log.error("ToyotaCare HISTORY - Not Found");
            System.out.println("ToyotaCare HISTORY - Not Found");
        }
        if(!toyotaCare.contains("No information found")){
            return  "Y";
        }else{
            return  "N";
        }
    }

    public boolean  hasServiceLaneServiceHistory(String pdfContent){										// Toyota Sub Task - Service History
        String reg = "(?<=Service History).+?(?=\nService Connect)";
        Pattern p = Pattern.compile(reg, Pattern.DOTALL);
        Matcher m = p.matcher(pdfContent);

        String serviceHistory = null;
        if(m.find()){
            serviceHistory = m.group();
            System.out.println(serviceHistory);
        } else {
            serviceHistory = "NOT FOUND";
            log.error("Service History Content - Not Found");
            System.out.println("Service History Content - Not Found");
        }

        if(!serviceHistory.contains("No information found")){
            return  true;
        } else {
            return  false;
        }
    }

}
