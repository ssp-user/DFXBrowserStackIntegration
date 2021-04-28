package com.automation.utils.otherUtils;


import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.automation.pages.PageManager;
import com.automation.utils.dataProvider.TestParameters;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.automation.utils.log.LogUtils;
import com.automation.utils.sync.SyncPage;

    /*
     * @Function: common methods
     * @author: David Tao
     * */

    public class CommonMethods {


        private static Logger log = Logger.getLogger(CommonMethods.class);

        public static String currentTotalAmount = null;
        protected static ArrayList<String> selectedItemList;
        public static double accumulatedPrice = 0;
        public static int scrolledPixel = 0;

        // This return a random number between min and max, both are inclusive.
        public static int randomNum(int min, int max){
            Random ran = new Random();
            max+=1;
            return ran.nextInt(max-min)+min;
        }

        //use to generate random text
        public static String getRandomText(int stringLength){
            String requireChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder actualString = new StringBuilder();
            Random rnd = new Random();
            while (actualString.length() < stringLength) { // length of the random string.
                int index = (int) (rnd.nextFloat() * requireChar.length());
                actualString.append(requireChar.charAt(index));
            }
            String randomText = actualString.toString();
            TestParameters.randomText = randomText;
            return randomText;
        }

        //use to generate random number
        public static String getRandomNumber(int stringLength){
            String requireChar = "0123456789";
            StringBuilder actualString = new StringBuilder();
            Random rnd = new Random();
            while (actualString.length() < stringLength) { // length of the random string.
                int index = (int) (rnd.nextFloat() * requireChar.length());
                actualString.append(requireChar.charAt(index));
            }
            String  randomText = actualString.toString();
            TestParameters.randomText = randomText;
            return randomText;
        }

        // This return a set of random numbers that have no duplicate numbers.
        public static int[] randomNumSet(int min, int max, int numberOfRandomNumbers){
            int randomNumberList[] = new int[numberOfRandomNumbers];
            Set<Integer> generated = new LinkedHashSet<Integer>();
            int random = 0;
            while(generated.size() < numberOfRandomNumbers){
                random = randomNum(min, max);
                generated.add(random);
            }
//            System.out.println("Generated is: " + generated);
            int i = 0;
            Iterator<Integer> gen = generated.iterator();
            while(gen.hasNext()){
                randomNumberList[i] = gen.next();
//                System.out.println(randomNumberList[i]);
                i++;
            }
            return randomNumberList;
        }

        // Get a random index number
        public static int[] setIndexNumber(List<WebElement> targetWebElements, int numberOfItems){
            int numOfElements = targetWebElements.size() - 1;
//            System.out.println("targetWebElements.size() - 1  is " + numOfElements);
            return randomNumSet(0, numOfElements, numberOfItems);		//this is because size starts from 1, but index starts from 0
        }

        public static ArrayList<String> getSelectedServiceItems(){		// Return the Selected Service Items
            return selectedItemList;
        }

        public static void getCurrentTotalAmount(WebElement totalAmount){

            String price = takeOffDollorSign(totalAmount);

            if(price.isEmpty()){
                currentTotalAmount = "0.00";
            } else {
                currentTotalAmount = price;
            }
            System.out.println("Current Total Amount is " + currentTotalAmount);

        }


        /**
         * Compare String between an ArrayList to a String[], this is to validate all selected service items are correctly present in the perform service list
         * @param selectedServiceItemsTextList
         * @param performServiceItemsList
         * @return true if two arrays match; return false if doesn't match.
         */
        public static boolean isServiceItemMatchSelectedItem(ArrayList<String> selectedServiceItemsTextList, String[] performServiceItemsList){
            int matchCount = 0;
            String listA;
            String[] listB = Arrays.copyOf(performServiceItemsList, performServiceItemsList.length);
//            System.out.println("selectedServiceItemsTextList.size() is : " + selectedServiceItemsTextList.size());
//            System.out.println("selectedServiceItemsTextList  is : " + selectedServiceItemsTextList.toString());
//            System.out.println("performServiceItemsList  is : " + performServiceItemsList.length);
//            System.out.println("listB.length  is : " + listB.length);
            if(selectedServiceItemsTextList.size() <= listB.length){
                for(Iterator<String> ii = selectedServiceItemsTextList.iterator(); ii.hasNext();){
                    listA = ii.next();
//                    System.out.println(" listA  is : " +  listA);
                    for(int i = 0; i < listB.length; i++){
                        if(listA.equalsIgnoreCase(listB[i])){
                            matchCount++;
                            listB[i] = "";		// This is for duplicate items
                            break;
                        }
                    }
                }
            }

            log.info("Match Count is : " + matchCount);
//            System.out.println("Match Count is : " + matchCount);
//            for(Iterator<String> ii = selectedServiceItemsTextList.iterator(); ii.hasNext();){
//                System.out.println(" -> " + ii.next());
//            }

            if(matchCount == selectedServiceItemsTextList.size()){
                return true;
            } else {
                return false;
            }
        }

        // This is to Compare two String lists and return differences
        public static ArrayList<String> compareTwoStringArray(String[] beforeRemoveArray, String[] afterRemoveArray){

            ArrayList<String> newArray = new ArrayList<String>();
            boolean foundMatch;
            if(beforeRemoveArray.length >= afterRemoveArray.length){
                for(int i1 = 0; i1<beforeRemoveArray.length; i1++){
                    foundMatch = true;
                    for(int i2 = 0; i2<afterRemoveArray.length; i2++){
                        if(beforeRemoveArray[i1].equals(afterRemoveArray[i2])){
                            foundMatch = false;
                            afterRemoveArray[i2] = "";	// This is to prevent duplicate item in scheduled/unscheduled/required category
                            break;
                        }
                    }
                    if(foundMatch){
                        newArray.add(beforeRemoveArray[i1]);
                    }
                }
            } else {
                throw new AssertionError("Array list size is not match.");
            }

            return newArray;

        }

        /**
         * This is to compare two ArrayList.
         * @param selectedServiceItemsList Selected Service Item Text List
         * @param arrayDiffAfterRemoveItems The difference ArrayList after clicking the remove all button
         *
         * @return return the size of ArrayList difference, the return should be zero
         *
         **/
        public static int compareTwoArrayList(ArrayList<String> selectedServiceItemsList, ArrayList<String> arrayDiffAfterRemoveItems){

            ArrayList<String> listDiff = new ArrayList<String>();
            String listA;
            String listB;

            boolean foundMatch;
            if(selectedServiceItemsList.size() >= arrayDiffAfterRemoveItems.size()){
                for(Iterator<String> ii1 = selectedServiceItemsList.iterator(); ii1.hasNext();){
                    foundMatch = true;
                    listA = ii1.next();
                    int i = 0;
                    for(Iterator<String> ii2 = arrayDiffAfterRemoveItems.iterator(); ii2.hasNext();){
                        listB = ii2.next();
                        if(listA.equalsIgnoreCase(listB)){
                            foundMatch = false;
                            arrayDiffAfterRemoveItems.set(i, "");		// This is to prevent duplicate item in scheduled/unscheduled/required category
                            break;
                        }
                        i++;
                    }
                    if(foundMatch){
                        listDiff.add(listA);
                    }
                }
            } else if (selectedServiceItemsList.size() < arrayDiffAfterRemoveItems.size()) {
                for(Iterator<String> ii2 = arrayDiffAfterRemoveItems.iterator(); ii2.hasNext();){
                    foundMatch = true;
                    listB = ii2.next();
                    for(Iterator<String> ii1 = selectedServiceItemsList.iterator(); ii1.hasNext();){
                        listA = ii1.next();
                        if(listB.equalsIgnoreCase(listA)){
                            foundMatch = false;
                            break;
                        }
                    }
                    if(foundMatch){
                        listDiff.add(listB);
                    }
                }
            }

            if(listDiff.size() != 0){
                System.out.println("Something went wrong. The difference size between two array is : " + listDiff.size());
                for(Iterator<String> ii = listDiff.iterator(); ii.hasNext();){
                    listA = ii.next();
                    log.error(" --> " + listA);
                }
            }

            return listDiff.size();
        }


        // This is to Print Select Items before verifying Price
        public static void printSelectedItems(String strService, ArrayList<String> selectedItemsList){
            if(selectedItemsList.size() > 0){
                System.out.println("===== All Selected " + strService + " Items =====");
                String itemSelected;
                for(Iterator<String> ii = selectedItemsList.iterator(); ii.hasNext();){
                    itemSelected = ii.next();
                    System.out.println("  -> " + itemSelected);

                }
            }
        }

        /**
         * Print the Perform Item on screen
         * @param strCategory Service Category
         * @param performItemList String[] Array to print
         */
        public static void printPerformItems(String strCategory, String[] performItemList){
            if(performItemList.length > 0){
                System.out.println("===== Selected " + strCategory + " Items =====");
                for(int i = 0; i < performItemList.length; i++){
                    System.out.println("  -> " + performItemList[i]);
                }
            }
        }


        /**
         * Check to see specific PDF is generated
         *
         * @param elementList PDF WebElement List
         * @param pdfText PDF Title to check if the PDF is generated - IN LOWER CASE
         *
         * @return true if PDF Title Match; false if PDF Title Not Found
         * */
        public static boolean verifyPDFList(List<WebElement> elementList, String pdfText){
            boolean result = false;
            pdfText = pdfText.toLowerCase();
            for(Iterator<WebElement> ii = elementList.iterator(); ii.hasNext();){
                if(ii.next().getText().trim().toLowerCase().contains(pdfText)){
                    result = true;
                    break;
                }
                else {
                    result = false;
                }
            }
            return result;
        }


        // Return WebElement link address
        public static String getElementLink(WebElement element){
            String href = null;
            href = element.getAttribute("href");
            return href;
        }


        public static String takeOffDollorSign(WebElement element){		// Take out the Dollor Sign
            String str = null;
            try{
                str = element.getText().trim();
            } catch (NoSuchElementException e){
                str = "0.00";
            }
            if(str.contains("$")){
                str = str.substring(str.indexOf("$") + 1);
            }else if(str.isEmpty()){
                str ="0.00";
            }
            return str;
        }

        // add take $ from Sring
        public static String takeOffDollorSign(String str){		// Take out the Dollor Sign
            if(str.contains("$")){
                str = str.substring(str.indexOf("$") + 1);
            }
            return str;
        }


        /**
         * Check to see if promise time is on weekends
         *
         *  @param year int Current Year
         *  @param month int Current Month
         *  @param date int Current Date
         *
         *  @return return true if on Saturday or Sunday
         *
         */
        public static boolean isWeekend(int year, int month, int date){		// Check for weekends
            Calendar cal = Calendar.getInstance();
            cal.set(year, month-1, date);
            System.out.println("Current select time is " + cal.getTime());
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
            String dateOfWeek = formatter.format(cal.getTime());

            if(dateOfWeek.equals("Saturday") || dateOfWeek.equals("Sunday")){
                System.out.println("Weekend Found");
                return true;
            } else {
                return false;
            }
        }

        /**
         * Check and handle the end for month or end of year
//         * @param driver WebDriver
         * @param month int Current Month
         * @param date int Current Date
         * @param yearPlus WebElement Year+
         * @param monthPlus WebElement month+
         * @param datePlus WebElement day+
         * @return true if it is on the end of month or year
         * */
        public static boolean isEndOfMonthOrYear(int month, int date, WebElement yearPlus, WebElement monthPlus, WebElement datePlus){
            System.out.println("Current Month is " + month + " Current Day is " + date);
            boolean result = false;
            switch(month){
                case 1: if(date==31){
                    monthPlus.click();
                    result = true;
                }
                    break;
                case 2: if(date==28 || date==29){
                    monthPlus.click();
                    result = true;
                }
                    break;
                case 3: if(date==31){
                    monthPlus.click();
                    result = true;
                }
                    break;
                case 4: if(date==30){
                    monthPlus.click();
                    result = true;
                }
                    break;
                case 5: if(date==31){
                    monthPlus.click();
                    result = true;
                }
                    break;
                case 6: if(date==30){
                    monthPlus.click();
                    result = true;
                }
                    break;
                case 7: if(date==31){
                    monthPlus.click();
                    result = true;
                }
                    break;
                case 8: if(date==31){
                    monthPlus.click();
                    result = true;
                }
                    break;
                case 9: if(date==30){
                    monthPlus.click();
                    result = true;
                }
                    break;
                case 10: if(date==31){
                    monthPlus.click();
                    result = true;
                }
                    break;
                case 11: if(date==30){
                    monthPlus.click();
                    result = true;
                }
                    break;
                case 12:
                    if(date==31){
                        yearPlus.click();
                        monthPlus.click();
                        datePlus.click();
                        result = true;
                    }
                    break;
            }
            return result;
        }


        /***
         * Check to see if the date selected is today's date
         * @param year Selected Year
         * @param month Selected Month
         * @param date Selected Date
         * @return true if date selected is current date
         */
        public static boolean isToday(int year, int month, int date){
            Calendar today = Calendar.getInstance();
            int curDate = today.get(Calendar.DATE);
            int curMonth = today.get(Calendar.MONTH)+1;
            int curYear = today.get(Calendar.YEAR);
            if(curYear == year && curMonth == month && curDate == date){
                return true;
            } else {
                return false;
            }
        }


        /***
         * Check to see if the date selected is today's date
         * @param fmt the format like  "YYYYMMDD"
         * @return true if date selected is current date
         */
        public static String  getToday(String fmt){
            SimpleDateFormat sdf=new SimpleDateFormat(fmt);
            Date now=new Date();
            return sdf.format(now);
        }


        /**
         * Check to see which item on the li list is displayed
         * @param elementList WebElement List contains li List
         * @return displayed text
         */
        public static String getDisplayedText(List<WebElement> elementList){
            String currentSelected = null;
            for(int i = 0; i < elementList.size(); i++){
                System.out.println(elementList.get(i).getText());
                if(elementList.get(i).isSelected()){
                    currentSelected = elementList.get(i).getText().trim();
//				break;
                }
            }
            return currentSelected;
        }


        /***
         * Scroll WebElement into the Center of the browser window. Scroll the entire page
//         * @param driver WebDriver
         * @param element The target WebElement need to be scroll to the center of browser
         */
        public static void scrollElementIntoCenterOfView(WebElement element){
            WebDriver driver = PageManager.getDriver();
            Dimension d = driver.manage().window().getSize();
            int center = d.getHeight()/2;
            Point p = element.getLocation();
            int y = p.getY();
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            String jscript = "scroll(" + p.getX() + ", " + (y-center) + ");";
            js.executeScript(jscript);
        }


        /***
         * Scroll WebElement into the Center of the browser window. Just scroll within a DIV
         * Make sure to apply scrollToTopOfDiv method to reset the scrolled pixels.
//         * @param driver	WebDriver
         * @param element The target WebElement need to be scroll to the center of browser
         * @param divToScroll The scrollable DIV that contains the target WebElement
         */
        public static void scrollElementIntoCenterOfView(WebElement element, WebElement divToScroll){
            WebDriver driver = PageManager.getDriver();
            Dimension d = driver.manage().window().getSize();
            int center = d.getHeight()/2;
            Point p = element.getLocation();
            int y = p.getY();

            int pixelsToScroll = (y - center) + scrolledPixel;
//            System.out.println("pixels To scoll is : " + pixelsToScroll);
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            String jscript = "$(arguments[0]).animate({ scrollTop: \"" + pixelsToScroll + "px\"})";
            js.executeScript(jscript, divToScroll);
            scrolledPixel = pixelsToScroll;
            new WebDriverWait(driver, PageManager.waitInSeconds).until(SyncPage.condDoneScrolling(element));
        }


        /***
         * Scroll the Scrollable DIV to Top, and reset the scrolled pixels.
         * @param driver WebDriver
         * @param div The DIV WebElement
         */
        public static void scrollToTopOfDiv(WebDriver driver, WebElement div){
            String jscript = "$(arguments[0]).animate({ scrollTop: \"0px\"})";
            try{
                JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript(jscript, div);
                resetScrolledPixels();
            } catch (StaleElementReferenceException e){
                log.error("StaleElementReference Exception occurred while trying to scroll to top of the DIV");
            }
            log.info("Scroll The scrollable DIV to Top");
        }


        public static void resetScrolledPixels(){
            scrolledPixel = 0;
        }



        /**
         * Check to see if the process is running.
         * @param serviceName Process Name
         * @return true if the process is running; return false if the process not running
         */
        public static boolean isProcessRunning(String serviceName){
            Process p;
            try {
                p = Runtime.getRuntime().exec("tasklist");
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
//		  		System.out.println(line);
                    if (line.contains(serviceName)) {
                        return true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }


        /***
         * Get the Caller method's class name
         * @return
         */
        public static String getCallerClassName() {
            StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
            for (int i=1; i<stElements.length; i++) {
                StackTraceElement ste = stElements[i];
                if (!ste.getClassName().equals(CommonMethods.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
                    log.info("The Caller Class is : " + ste.getClassName());
                    return ste.getClassName();
                }
            }
            return null;
        }

        /***
         * Get the Caller method package name and method name
         * @return
         */
        public static String getFullPackageName(){
            StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
            for (int i=1; i<stElements.length; i++) {
                StackTraceElement ste = stElements[i];
                if (!ste.getClassName().equals(CommonMethods.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
                    return ste.getClassName() + "." + ste.getMethodName();
                }
            }
            return null;

        }


        /**
         * Sort an Integer array into a descending order
         * @param array int[] array
         * @return the int array with descending order
         */
        public static int[] sortArrayInDesc(int[] array){
            Arrays.sort(array);
            int aa[] = new int[array.length];
            int length = array.length-1;
            for(int i = 0; i <= length; i++){
                aa[i] = array[length-i];
            }
            return aa;
        }

        public static void sleep(int ms){
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /***
         * Un-Selected the checkbox from a WebElement List
         * @param elementList
         */
        public static void uncheckItem(List<WebElement> elementList, List<WebElement> elementTextList){
            for(int i = 0; i < elementList.size(); i++){
                while(elementList.get(i).isSelected()){
                    elementTextList.get(i).click();
                }
            }
        }

        /***
         * Append string to StringBuilder with consideration to add ", " in between
         * @param a Target StringBuilder to append string
         * @param string String text content
         */
        public static void append(StringBuilder a, String string){
            if(a.length() < 1){
                a.append(string);
            } else {
                a.append(", " + string);
            }
        }


        /***
         * Get the dropdown list Option Value with provided Text value
         * @param dropDownList Drop Down List Select Field
         * @param textToSelect Target to selected text value (contains)
         * @return value to select the item
         */
        public static String getDropdownListValue(Select dropDownList, String textToSelect){
            List<WebElement> list = dropDownList.getOptions();
            String visibleText = null;
            String value = null;
            for(int i = 0; i < list.size(); i++){
                visibleText = list.get(i).getText();
                if(visibleText.toLowerCase().contains(textToSelect.toLowerCase())){
                    value = list.get(i).getAttribute("value");
                    break;
                }
            }
            return value;
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

        public static String formatHandler(double d){							// Convert Double object to String with formatting
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(',');
            DecimalFormat formatter = new DecimalFormat("###,##0.00", symbols);
            return formatter.format(d);
        }


        /***
         * Find the WebElement index number from WebElement List that has the string value
         * @param elementList Target WebElement List
         * @param string	Selected Appointment time
         * @return the index number
         */
        public static int getListIndexNum(List<WebElement> elementList, String string){
            String slot = null;
            int index = -1;
            for(int j = 0; j < elementList.size(); j++){
                slot = elementList.get(j).getText().trim();
                if(slot.equalsIgnoreCase(string)){
                    index = j;
                    break;
                }
            }
            return index;
        }

        public static void verifyElementExists(Object expected){
        	if (((WebElement)expected).isDisplayed()) {
                System.out.println(" Pass Webelement " + expected.toString() + " is displayed." );			
    		} else {
    			throw new AssertionError();
    		}
    	}

        public static void scrollTotheBottomofWebPage(){
            WebDriver driver = PageManager.getDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }        

        public static String returnDate(int days, String format){
        	SimpleDateFormat sdf = new SimpleDateFormat(format);
        	Calendar calendar = new GregorianCalendar();
    	    calendar.add(Calendar.DAY_OF_MONTH, days);
        	return sdf.format(calendar.getTime());
    	}   
        
        public static void validateWindowTitle(String title){
        	sleep(5000);
        	assertTrue(PageManager.getDriver().getTitle().trim().equals(title.trim()) , "Validate title to current window is "+title);
    	}   
        
        public static void closeWindow(){
        	PageManager.getDriver().close();
    	}         
        
        public static void JavaScriptClick(By element) {
        	WebElement ele = PageManager.getDriver().findElement(element);
        	JavascriptExecutor js = (JavascriptExecutor) PageManager.getDriver();
        	js.executeScript("arguments[0].click();", ele);
        }
        
    }
