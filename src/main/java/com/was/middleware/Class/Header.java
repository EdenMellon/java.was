package com.was.middleware.Class;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eden on 2017. 8. 25..
 */
public class Header {
    private static Logger logger = LoggerFactory.getLogger(Header.class);
    private String host;
    private String method;
    private String accessFile;
    private String version = "HTTP/1.0";

    private Map<String, String> params = new HashMap<String, String>();

    /**
     * Instantiates a new Head class.
     *
     * @param tokens the tokens*/
    public Header(String[] tokens) {
        String[] strHeader = tokens[0].split("\\s+");
        String[] strHost = tokens[1].split(":");
        this.host = strHost[1].trim();
        this.method = strHeader[0];
        if(strHeader[1].indexOf("?") > -1) {
            String[] accessFiles = strHeader[1].split("\\?");
            String[] queryString = accessFiles[1].split("\\&");
            for(String qs: queryString) {
                String[] query = qs.split("=");
                params.put(query[0], query[1]);
            }
            strHeader[1] = accessFiles[0];
        }
        this.accessFile = strHeader[1];

        if(strHeader.length > 2) {
            this.version = strHeader[2];
        }
    }

    /**
     * Gets host.
     *
     * @return the host
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Gets method.
     *
     * @return the method
     */
    public String getMethod() {
        return this.method;
    }

    /**
     * Gets access file.
     *
     * @return the access file
     */
    public String getAccessFile() {
        return this.accessFile;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public String getVersion() {
        return this.version;
    }

    public String getParam(String key) {
        return this.params.getOrDefault((Object) key, "");
    }

    @Override
    public String toString() {
        return "Header: {" +
            "host: " + host + ", " +
            "method: " + method + ", " +
            "accessFile: " + accessFile + ", " +
            "version: " + version + ", " +
            "params: " + params.toString() +
            "}";
    }
}
