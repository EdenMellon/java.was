package com.was.util;

import java.io.*;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eden on 2017. 8. 24..
 */
public class HttpResponse {
    private static Logger logger = LoggerFactory.getLogger(HttpResponse.class);
    private OutputStream output;
    private Writer out;
    private String version;
    private String responseCode;
    private String contentType;
    private int length;

    public HttpResponse(OutputStream output) {
        this.output = output;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void  sendHeader() throws IOException {
        Writer writer = this.getWriter();
        if (this.version.startsWith("HTTP/")) {
            writer.write(this.version + " " + this.responseCode + "\r\n");
            Date now = new Date();
            writer.write("Date: " + now + "\r\n");
            writer.write("Server: JHTTP 2.0\r\n");
            writer.write("Content-type: " + this.contentType + "\r\n\r\n");
            if(this.length > 0) {
//                writer.write("Content-length: " + this.length + "\r\n");
            }
        }
    }

    public void send(byte[] data) throws IOException {
        try {
            output.write(data);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (output != null) {
                output.flush();
            }
        }
    }

    public Writer getWriter() {
        if(this.out == null) {
            this.out = new OutputStreamWriter(this.output);
        }
        return this.out;
    }

    @Override
    public String toString() {
        return "Response: {" +
                "version: " + this.version + ", " +
                "responseCode: " + this.responseCode + ", " +
                "contentType: " + this.contentType +
            "}";
    }
}