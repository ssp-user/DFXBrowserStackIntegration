package com.autotest.teststeps;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class BaseTestSteps {

    /**
     * Preprocessed any input text for passing to the page object's setter.
     * Handles '$blank' value.
     * @param value user provided text
     * @return processed text ready to be sent to te page object
     */
    protected String processText(String value) {
        return "$blank".equalsIgnoreCase(value) ? "" : value;
    }

    protected boolean notBlank(String value) {
        return StringUtils.isNotBlank(value);
    }

    protected String processEmpty(String value) {
        if (value.isEmpty()){
            return " ";
        }else{
            return value;
        }
    }

    protected int toBeNumber (String order ){
        if (order.equalsIgnoreCase("first")) {
            order = "1";
        } else if (order.equalsIgnoreCase("second")) {
            order = "2";
        } else if (order.endsWith("th")) {
            order = order.replace("th", "");
        }
        return  Integer.valueOf(order);
    }

    protected String toBeAlpha (String order ){
        if (order.equalsIgnoreCase("first")) {
            order = "1";
        } else if (order.equalsIgnoreCase("second")) {
            order = "2";
        } else if (order.endsWith("th")) {
            order = order.replace("th", "");
            if (!order.toUpperCase().equals(order.toLowerCase())){
                order  = order + "th";
             }
        }
        return  order;
    }

    protected void verifyTrue(String message, Boolean condition ){
        try{
            Assert.assertTrue(message , condition);
            System.out.println(message + " pass ! " );
        }catch (AssertionError ae){
            System.err.println( "\n"+ message +" fail ! " );
        }
    }

    protected void verifyFalse(String message, Boolean condition ){
        try{
            Assert.assertFalse(message , condition);
            System.out.println(message + " pass ! " );
        }catch (AssertionError ae){
            System.err.println(message +" fail ! " + ae.getMessage());
        }
    }

    protected void verifyEquals(String message, Object expect, Object actual ){
        try{
            Assert.assertEquals(message, expect, actual );
            System.out.println(message + " pass ! " );
        }catch (AssertionError ae){
            System.err.println(message +" fail ! " + ae.getMessage());
        }
    }

    protected void verifyNotEquals(String message, Object expect, Object actual ){
        try{
            Assert.assertNotEquals(message, expect, actual );
            System.out.println(message + " pass ! " );
        }catch (AssertionError ae){
            System.err.println(message +" fail ! " + ae.getMessage());
        }
    }

}
