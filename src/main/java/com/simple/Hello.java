package com.simple;

import com.simple.servlet.SimpleServlet;
import com.was.util.HttpRequest;
import com.was.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by Eden on 2017. 8. 26..
 */
public class Hello implements SimpleServlet {
    private static Logger logger = LoggerFactory.getLogger(Hello.class);
    @Override
    public void service(HttpRequest req, HttpResponse res) throws IOException {
        Writer writer = res.getWriter();
        writer.write("Hello, ");
        writer.write(req.getParameter("name"));
    }
}
