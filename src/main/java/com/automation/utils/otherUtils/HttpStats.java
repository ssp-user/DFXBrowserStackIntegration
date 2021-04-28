package com.automation.utils.otherUtils;


import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

import com.automation.pages.PageManager;
import org.apache.http.Header;
import org.apache.http.client.fluent.Request;
import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HttpStats {


    private static Logger log = Logger.getLogger(HttpStats.class.getName());

    /***
     * Retrieve WebElement(image) file size
     * @param element WebElement for image
     * @return File size (byte) in integer format
     */
    public static int getContentLength(WebElement element) {
        String url = getElementSrc(element);
        try {
            return Request.Get(url).execute().returnContent().asBytes().length;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /***
     * Retrieve WebElement(URL) status code
     * @param element WebElement(URL) to check status code
     * @return Status code (200 or whatever)
     */
    public static int getResponseCode(WebElement element) {
        String url = getElementSrc(element);
        try {
            return Request.Get(url).execute().returnResponse().getStatusLine().getStatusCode();
        } catch (Exception e) {
            throw new TimeoutException();
        }
    }

    /***
     * Retrieve Target Server(Current URL) X-Node Value
     * @param driver WebDriver
     * @return X-Node Value
     */
    public static String getX_Node(WebDriver driver) {
  /*  	try{
    		String url = driver.getCurrentUrl();
    		Header[] x = Request.Get(url).execute().returnResponse().getHeaders("X-Node");
    		LogUtils.logConsoleMsg(x[0].toString());
    		return x[0].toString();
    	}catch(IndexOutOfBoundsException | SSLHandshakeException | SSLPeerUnverifiedException e){		// Header "X-Node" is not available
    		try{
    			String url = driver.getCurrentUrl();
    			Header[] x = Request.Get(url).execute().returnResponse().getHeaders("x-iis-node");
    			LogUtils.logConsoleMsg(x[0].toString());
    			return x[0].toString();
    		} catch (IndexOutOfBoundsException | SSLHandshakeException | SSLPeerUnverifiedException e1){
    			return "null";
    		} catch (Exception e1){
    			e.printStackTrace();
        		throw new RuntimeException(e);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	}
*/


        try {
            String url = driver.getCurrentUrl();
            Header[] x = Request.Get(url).execute().returnResponse().getHeaders("x-iis-node");
            return x[0].toString();
        } catch (IndexOutOfBoundsException | SSLHandshakeException | SSLPeerUnverifiedException e) {
            return "null";
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }


    }

    /***
     * Retrieve Target Server(Current URL) X-Node Value
     * @param url String
     * @return X-Node Value
     */
    public static String getX_Node(String url) {
  /*  	try{
    		String url = driver.getCurrentUrl();
    		Header[] x = Request.Get(url).execute().returnResponse().getHeaders("X-Node");
    		LogUtils.logConsoleMsg(x[0].toString());
    		return x[0].toString();
    	}catch(IndexOutOfBoundsException | SSLHandshakeException | SSLPeerUnverifiedException e){		// Header "X-Node" is not available
    		try{
    			String url = driver.getCurrentUrl();
    			Header[] x = Request.Get(url).execute().returnResponse().getHeaders("x-iis-node");
    			LogUtils.logConsoleMsg(x[0].toString());
    			return x[0].toString();
    		} catch (IndexOutOfBoundsException | SSLHandshakeException | SSLPeerUnverifiedException e1){
    			return "null";
    		} catch (Exception e1){
    			e.printStackTrace();
        		throw new RuntimeException(e);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	}
*/

        try {
            Header[] x = Request.Get(url).execute().returnResponse().getHeaders("x-iis-node");
            log.info(x[0].toString());
            return x[0].toString();
        } catch (IndexOutOfBoundsException | SSLHandshakeException | SSLPeerUnverifiedException e) {
            return "null";
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }


    }

    private static String getElementSrc(WebElement element) {
        WebDriver driver = PageManager.getDriver();
        String url = element.getAttribute("src");
        log.info("The WebElement src URL is :" + url);
        return url;
    }

}
