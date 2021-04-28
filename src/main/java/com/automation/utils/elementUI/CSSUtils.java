package com.automation.utils.elementUI;

import com.automation.utils.otherUtils.CommonMethods;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

/*
 * @Function: Methods to verify page element from their CSS value
 * @author: update By Dvid Tao
 * */


public class CSSUtils {

    protected static String getCssBackgroundPositionValue(WebElement element) {            // Background position
        String result = null;
        boolean r = false;
        do {
            try {
                result = element.getCssValue("background-position");
                r = true;
            } catch (StaleElementReferenceException e) {
                System.err.println("Stale Element Reference Exception Occurred while retrieving background position of WebElement");
                r = false;
            }
        } while (!r);
        return result;
    }


    public static String getClassAttributeValue(WebElement element) { // dead loop  happen because Stale Element Exception Occurred
        String result = null;
        boolean r = false;
        int i =  0;
        do {
            try {
                result = element.getAttribute("class");
                r = true;
            } catch (StaleElementReferenceException e) {
                System.err.println("Stale Element Reference Exception Occurred while retrieving class value of WebElement");
                r = false;
            }
            CommonMethods.sleep(500);
            i++;
            if( i > 20){
                break;
            }
        } while (!r);
        return result;
    }


    protected static String getCssBorderColor(WebElement element) {                        // Border Color
//			return element.getCssValue("border-top-color");
        String result = null;
        boolean r = false;
        do {
            try {
                result = element.getCssValue("border-color");
                r = true;
            } catch (StaleElementReferenceException e) {
                System.err.println("Stale Element Reference Exception Occurred while retrieving Border color of WebElement");
                r = false;
            }
        } while (!r);
        return result;
    }


    protected static String getCssTopValue(WebElement element) {                            // Top Value
        String result = null;
        boolean r = false;
        do {
            try {
                result = element.getCssValue("top");
                r = true;
            } catch (StaleElementReferenceException e) {
                System.err.println("Stale Element Reference Exception Occurred while retrieving Top value of WebElement");
                r = false;
            }
        } while (!r);
        return result;
    }


    protected static String getCssTransformValue(WebElement element) {                    // Transform Value
        String result = null;
        boolean r = false;
        do {
            try {
                result = element.getCssValue("transform");
                r = true;
            } catch (StaleElementReferenceException e) {
                System.err.println("Stale Element Reference Occurred while retrieving Transform value of WebElement");
                r = false;
            }
        } while (!r);
        return result;
    }


    public static String getCssDisplayValue(WebElement element) {                        // Display Value
        String result = null;
        boolean r = false;
        do {
            try {
                result = element.getCssValue("display");
                r = true;
            } catch (StaleElementReferenceException e) {
                System.err.println("Stale Element Reference Exception Occurred while retrieving Display value of WebElement");
            }
        } while (!r);
        return result;
    }


    public static String getCssBackgroundColorValue(WebElement element) {                // Background Color Value
        String result = null;
        boolean r = false;
        do {
            try {
                result = element.getCssValue("background-color");
                r = true;
            } catch (StaleElementReferenceException e) {
                System.err.println("Stale Element Reference Exception Occurred while retrieving Background Color value of WebElement");
                r = false;
            }
        } while (!r);
        return result;
    }


    public static String getCssColorValue(WebElement element) {                            // Color Value
        String result = null;
        int attempt = 0;
        String error = null;
        boolean r = false;
        do {
            try {
                result = element.getCssValue("color");
                r = true;
            } catch (StaleElementReferenceException e) {
//					LogUtils.logError("Stale Element Reference Exception Occurred while retrieving Color value of WebElement" + e.getMessage());
                r = false;
                attempt++;
                error = e.getMessage();
            }
        } while (!r && attempt < 5);
        if (attempt >= 5) {
            throw new StaleElementReferenceException(error);
        }
        return result;
    }


    public static String getCssCursorValue(WebElement element) {                        // Cursor value
        String result = null;
        int attempt = 0;
        String error = null;
        boolean r = false;
        do {
            try {
                result = element.getCssValue("cursor");
                r = true;
            } catch (StaleElementReferenceException e) {
                r = false;
                attempt++;
                error = e.getMessage();
            }
        } while (!r && attempt < 5);

        if (attempt >= 5) {
            throw new StaleElementReferenceException(error);
        }

        return result;
    }


    //  **** CSS for AM ******

    public static String getTimeAvailableColor(){
        return "rgba(94, 153, 202, 1)";			// AL 2.9 color changed to rgb(44, 98, 191, 1);
    }

    public static String getTimeUnavailableColor(){
        return "rgba(201, 201, 201, 1)";		// Unavailable color remain the same
    }



    public static String getSelectedCustomerColor(){
        return "rgba(77, 77, 77, 1)";
    }

    public static String getSelectedAMColor(){
        return "rgba(77, 77, 77, 1)";
    }

    public static String getSelectedServiceTab(){
        return "rgba(0, 0, 0, 1)";
    }

    //  *** end of CSS for AM

}
