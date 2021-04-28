package com.automation.utils.log;

import org.apache.log4j.Logger;

public class DfxLog {


    public static Logger logger;

    public static void plogError(String mesg) {                                                                                    // Log Error Msg
        System.err.println(mesg);
        logger.error(mesg);
    }

    public static void plogWarn(String mesg) {                                                                                    // Log Error Msg
        System.out.println(mesg);
        logger.warn(mesg);
    }

    public static void plogInfo(String mesg) {                                                                                    // Log Error Msg
        System.out.println(mesg);
        logger.warn(mesg);
    }

    public static void logError(String mesg) {                                                                                    // Log Error Msg
        logger.error(mesg);
    }

    public static void logWarn(String mesg) {                                                                                    // Log Error Msg
        logger.warn(mesg);
    }

    public static void logInfo(String mesg) {                                                                                    // Log Error Msg
        logger.warn(mesg);
    }

}
