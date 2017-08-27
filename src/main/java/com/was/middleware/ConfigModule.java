package com.was.middleware;

import java.io.IOException;
import java.util.Arrays;

import com.was.middleware.Class.Router;
import com.was.middleware.Class.Server;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.was.util.FileUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.LoggerFactory;

/**
 * Created by Eden on 2017. 8. 24..
 */
public class ConfigModule {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ConfigModule.class);
    private static ServerModule servers;
    private static int PORT;
    private static String[] blockedExtension;
    private static ConfigModule instance = null;
    private static RouterModule routers;

    private ConfigModule() throws Exception {
        Config conf = ConfigFactory.load();

        PORT = conf.getInt("port");
        blockedExtension = String.valueOf(conf.getString("blocked_extension")).split(",");
        servers = new ServerModule(conf.getConfigList("servers"));
        servers.setDefaultServer(conf.getString("default"));

        logger.debug(servers.getServer("was").toString());
        logger.debug(servers.getServer("simple").toString());

        logger.info("*--------------------------*");
        logger.info("**      START SERVER      **");
        logger.info("**    SERVER COUNT : " + servers.getServerCount() + "    **");
        logger.info("**       PORT: " + PORT + "       **");
        logger.info("*--------------------------*");

        try {
            String fileContent = FileUtil.getFileContent("mapping.json");
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(fileContent);
            JSONObject mapper = (JSONObject) obj;
            routers = new RouterModule();
            routers.buildRouter(mapper);
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ConfigModule getInstance() {
        if(instance == null) {
            try {
                instance = new ConfigModule();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return ConfigModule.instance;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public int getPort() {
        return PORT;
    }

    /**
     * Gets servers.
     *
     * @return the servers
     */
    public ServerModule getServers() {
        return servers;
    }

    /**
     * Gets routers.
     *
     * @return the routers
     */
    public RouterModule getRouters() {
        return routers;
    }

    /**
     * Gets default server.
     *
     * @return the default server
     */
    public Server getDefaultServer() {
        return this.getServers().getDefaultServer();
    }

    /**
     * Check blocked extension boolean.
     *
     * @param fileName the file name
     * @return the boolean
     * @throws ArrayIndexOutOfBoundsException the array index out of bounds exception
     */
    public Boolean checkBlockedExtension(String fileName) throws ArrayIndexOutOfBoundsException {
        if(fileName == null) {
            return false;
        }
        if(fileName.indexOf(".") > -1) {
            String[] fileToken = fileName.split("\\.");
            return Arrays.asList(blockedExtension).contains(fileToken[1]);
        }
        if (fileName.indexOf("../") > -1) {
            return true;
        }
        return false;
    }

    /**
     * Check block root path boolean.
     *
     * @param fileName the file name
     * @return the boolean
     */
    public Boolean checkBlockRootPath(String fileName) {
        if(fileName == null) {
            return false;
        }
        if (fileName.indexOf("../") > -1) {
            return true;
        }
        return false;
    }
}
