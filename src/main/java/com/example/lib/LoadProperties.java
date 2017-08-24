package com.example.lib;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Eden on 2017. 8. 24..
 */
public class LoadProperties {
    private static final Logger logger = Logger.getLogger(LoadProperties.class.getCanonicalName());
    private static final String __VERSION__ = "1.0.0";
    private static final String __CONFIG_PATH__ = "setting.properties";
    private static File DOC_ROOT;
    private static int PORT;

    private static LoadProperties instance;

    private LoadProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = LoadProperties.class.getClassLoader().getResourceAsStream(__CONFIG_PATH__);
        properties.load(inputStream);
        inputStream.close();

        DOC_ROOT = new File(properties.getProperty("conf.dir"));
        PORT = Integer.parseInt(properties.getProperty("conf.port"));

        System.out.println("*--------------------------*");
        System.out.println("   root: " + DOC_ROOT);
        System.out.println("   port: " + PORT);
        System.out.println("*--------------------------*");
    }

    public static LoadProperties getInstance() {
        try {
            instance = new LoadProperties();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed loaded properties", e);
        }
        return instance;
    }

    /**
     * Gets docroot.
     *
     * @return the docroot
     */
    public static File getDocRoot() {
        return DOC_ROOT;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public static int getPort() {
        return PORT;
    }
}
