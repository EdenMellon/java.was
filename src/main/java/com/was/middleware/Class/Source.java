package com.was.middleware.Class;

import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by Eden on 2017. 8. 25..
 */
public class Source {
    private static Logger logger = LoggerFactory.getLogger(Source.class);
    private String F_INDEX;
    private String F_403;
    private String F_404;
    private String F_500;
    private ArrayList<String> fList = new ArrayList<String>();

    /**
     * Instantiates a new Source.
     *
     * @param source the source
     */
    public Source(Config source) {
        this.F_403 = source.getString("403");
        this.F_404 = source.getString("404");
        this.F_500 = source.getString("500");
        this.F_INDEX = source.getString("index");
    }

    /**
     * Gets index.
     *
     * @return the index
     */
    public String getIndex() {
        return this.F_INDEX;
    }

    /**
     * Sets index.
     *
     * @param f_INDEX the f index
     */
    public void setIndex(String f_INDEX) {
        this.F_INDEX = f_INDEX;
    }

    /**
     * Gets 403.
     *
     * @return the 403
     */
    public String get403() {
        return this.F_403;
    }

    /**
     * Sets 403.
     *
     * @param f_403 the f 403
     */
    public void set403(String f_403) {
        this.F_403 = f_403;
    }

    /**
     * Gets 404.
     *
     * @return the 404
     */
    public String get404() {
        return this.F_404;
    }

    /**
     * Sets 404.
     *
     * @param f_404 the f 404
     */
    public void set404(String f_404) {
        this.F_404 = f_404;
    }

    /**
     * Gets 500.
     *
     * @return the 500
     */
    public String get500() {
        return this.F_500;
    }

    /**
     * Sets 500.
     *
     * @param f_500 the f 500
     */
    public void set500(String f_500) {
        this.F_500 = f_500;
    }

    @Override
    public String toString() {
        return "Source = {"+
            "F_INDEX: \"" + F_INDEX +"\", "+
            "F_403: \"" + F_403 +"\", "+
            "F_404: \"" + F_404 +"\", "+
            "F_500: \"" + F_500 +"\" } ";
    }
}
