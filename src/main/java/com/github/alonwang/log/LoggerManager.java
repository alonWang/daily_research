package com.github.alonwang.log;

import org.apache.logging.slf4j.Log4jLoggerFactory;
import org.slf4j.Logger;


public class LoggerManager {
    private static final Log4jLoggerFactory log4jLogFactory = new Log4jLoggerFactory();

    public static Logger getLogger() {
        return log4jLogFactory.getLogger("logger");
    }

    public static void main(String[] args) {
        getLogger().info("test log");
    }
}
