package com.simple.service;

import com.simple.servlet.SimpleServlet;
import com.was.util.HttpRequest;
import com.was.util.HttpResponse;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by Eden on 2017. 8. 26..
 */
public class Hello implements SimpleServlet {
    @Override
    public void service(HttpRequest req, HttpResponse res) throws IOException {
        System.out.print(res);
        Writer writer = res.getWriter();
        writer.write("service.Hello, ");
        writer.write(req.getParameter("name"));
        writer.flush();
    }
}
