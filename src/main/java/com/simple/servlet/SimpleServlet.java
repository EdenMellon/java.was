package com.simple.servlet;

import com.was.util.HttpRequest;
import com.was.util.HttpResponse;

/**
 * Created by Eden on 2017. 8. 26..
 */
public interface SimpleServlet {
    void service(HttpRequest req, HttpResponse res) throws Exception;
}
