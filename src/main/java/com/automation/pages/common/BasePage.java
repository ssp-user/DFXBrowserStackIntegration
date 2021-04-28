package com.automation.pages.common;

import com.automation.pages.PageManager;
import org.openqa.selenium.*;

/**
 * This is a base class for all page object - like classes.
 * It serves as a high level abstraction and defines a lifecycle of page objects.
 * <p>Phases of a page object lifecycle<ul>
 * <li>instantiation
 * <li>transition - operations to ensure web driver's state is in sync with the page object
 * <li>initialization (optional) - user defined initialization
 * <p>Created on Jan-28-2018
 *
 * @autor David Tao
 */
public abstract class BasePage {

    protected static WebDriver driver = PageManager.getDriver();
    boolean initialized = false;

    /**
     * <p>Accessor for the current WebDriver. The driver should be
     * used to interact with the web page presented in a browser.
     *
     * @return the WebDriver instance
     * @throws IllegalStateException if called before page object gets initialized from another page object using goTo()
     */
    protected final WebDriver driver() {
        if (!initialized) {
            throw new IllegalStateException("Attempt to use a Page Object without properly transitioning to it. Use goTo() method");
        }
        return driver;
    }

    /**
     * Override this method to provide proper implementation for transitioning to the page represented by a page object.
     * Make sure to call super's implementation in the first statement.
     * <p>Do not invoke this method directly
     *
     * @throws IllegalStateException in invoked by any class except for SeleniumWebPage
     */
    void doTransition() {
        rejectUnsafeCallers();
    }

    private void rejectUnsafeCallers() {
        String callerClass = Thread.currentThread().getStackTrace()[2].getClassName();
        if (!BasePage.class.getCanonicalName().equals(callerClass)) {
            throw new IllegalStateException(String.format("Cannot invoke doTransition from %s", callerClass));
        }
    }

    /**
     * Triggers the transition and initialization phase of a page object's lifecycle. This method should be invoked
     * with the page object's instance right after its instantiation.
     *
     * @param webPage page object to initialize
     * @return initialized page object
     */
    public static <T extends BasePage> T goTo(T webPage) {
        webPage.initialized = true;
        webPage.doTransition();
        webPage.init();
        return webPage;
    }

    /**
     * This method is called right after transition phase is completed.
     * Override it to perform any initialization that requires use of a WebDriver
     */
    protected void init() {

    }

    /**
     * Ensures that driver is in the working frame of Page Object
     */

    public static void setDriver(WebDriver wdriver) {
        driver = wdriver;
    }

    protected void setDriver() {
        driver = PageManager.getDriver();
    }

    protected WebDriver getDriver() {
        driver = PageManager.getDriver();
        return driver;
    }

    protected abstract void switchFrame();




}
