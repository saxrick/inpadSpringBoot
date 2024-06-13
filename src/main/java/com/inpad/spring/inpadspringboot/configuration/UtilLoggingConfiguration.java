package com.inpad.spring.inpadspringboot.configuration;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class UtilLoggingConfiguration {
    private static final Logger LOGGER = Logger.getLogger(UtilLoggingConfiguration.class.getName());
    static {
        try {
            InputStream stream = UtilLoggingConfiguration.class.getClassLoader()
                    .getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
