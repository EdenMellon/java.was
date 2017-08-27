package com.was.middleware.Class;

import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eden on 2017. 8. 25..
 */
public class Server {
    private static Logger logger = LoggerFactory.getLogger(Server.class);
    private String name;
    private String domain;
    private String httproot;
    private Source source;
    private String page403;
    private String page404;
    private String page500;

    /**
     * Instantiates a new Server.
     *
     * @param name the name
     */
    public Server(String name){
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets domain.
     *
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets domain.
     *
     * @param domain the domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Gets httproot.
     *
     * @return the httproot
     */
    public String getHttproot() {
        return httproot;
    }

    /**
     * Sets httproot.
     *
     * @param http_root the http root
     */
    public void setHttproot(String http_root) {
        this.httproot = http_root;
    }

    /**
     * Gets source.
     *
     * @return the source
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets source.
     *
     * @param source the source
     */
    public void setSource(Config source) {
        this.source = new Source(source);
    }

    /**
     * Gets page 403.
     *
     * @return the page 403
     */
    public String getPage403() {
        return this.httproot + this.source.get403();
    }

    /**
     * Gets page 404.
     *
     * @return the page 404
     */
    public String getPage404() {
        return this.httproot + this.source.get404();
    }

    /**
     * Gets page 500.
     *
     * @return the page 500
     */
    public String getPage500() {
        return this.httproot + this.source.get500();
    }

    @Override
    public String toString() {
        return "Server = {"+
            "name: \"" + name +"\", "+
            "domain: \"" + domain +"\", "+
            "httproot: \"" + httproot +"\", "+
            "source: \"" + source +"\" } ";
    }
}