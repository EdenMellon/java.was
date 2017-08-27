package com.was.util;

import com.was.middleware.Class.Header;
import com.was.middleware.ConfigModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Eden on 2017. 8. 24..
 */
public class HttpRequest {
    private static Logger logger = LoggerFactory.getLogger(HttpRequest.class);
    private InputStream in;
    private String url;
    private Header header;

    public HttpRequest(InputStream input) {
        this.in = input;
    }

    public void parse () {
        StringBuffer rbf = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];

        try {
            i = in.read(buffer);
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
//            ie.printStackTrace();
            i = -1;
        }

        for (int j=0; j<i; j++) {
            rbf.append((char)buffer[j]);
        }

        this.url = this.parseUri(rbf.toString());

        this.checkDomain();
    }

    private String parseUri (String rs) {
        int idx1;
        idx1 = rs.indexOf(" ");

        if(idx1 != -1) {
            String[] tokens = rs.split("\\r\\n");

            this.header = new Header(tokens);

            return this.header.getAccessFile();
        }

        return null;
    }

    private void checkDomain() throws NullPointerException {
        ConfigModule properties = ConfigModule.getInstance();
        if(this.header != null) {
            properties.getServers().setDefaultServerDomain(this.header.getHost());
        }
    }

    public String getUrl () {
        return this.url;
    }

    public Header getHeader() {
        return this.header;
    }

    public String getParameter(String param) {
        return this.header.getParam(param);
    }

    @Override
    public String toString() {
        if(this.header != null) {
            return "Request: {" + this.header.toString() +
                "}";
        } else {
            return "";
        }
    }
}