package com.example.util;

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
    private static int PORT;
    private static File WEB_ROOT;

    private static LoadProperties instance = null;

    private LoadProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = LoadProperties.class.getClassLoader().getResourceAsStream(__CONFIG_PATH__);
        properties.load(inputStream);
        inputStream.close();

        PORT = Integer.parseInt(properties.getProperty("conf.port"));
        WEB_ROOT = new File(properties.getProperty("conf.webroot"));

        System.out.println("*--------------------------*");
        System.out.println("**      START SERVER      **");
        System.out.println("   port: " + PORT);
        System.out.println("*--------------------------*");
    }

    public static LoadProperties getInstance() {
        if(instance == null) {
            try {
                instance = new LoadProperties();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed loaded properties", e);
            }
        }
        return instance;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public static int getPort() {
        return PORT;
    }

    /**
     * Gets webroot.
     *
     * @return the webroot
     */
    public static File getWebroot() {
        return WEB_ROOT;
    }
}
