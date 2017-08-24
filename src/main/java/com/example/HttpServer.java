package com.example;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.util.LoadProperties;

/**
 * Created by Eden on 2017. 8. 24..
 */
public class HttpServer {
    private static final Logger logger = Logger.getLogger(HttpServer.class.getCanonicalName());
    private static final int NUM_THREADS = 10;
    private static final String INDEX_FILE = "index.html";
    private int port = 8080;

    public HttpServer(int port) throws IOException {
        this.port = port;
    }

    public void start() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                try {
                    Socket request = server.accept();
                    Runnable r = new RequestProcessor(request);
                    pool.submit(r);
                } catch (IOException ex) {
                    logger.log(Level.WARNING, "Error accepting connection", ex);
                }
            }
        }
    }

    public static void main(String[] args) {
        // singleton 패턴으로 static configuration 구현.
        // server01, server02 설정 파일 분리.
        LoadProperties properties = LoadProperties.getInstance();

        int port;
        try {
            port = Integer.parseInt(String.valueOf(properties.getPort()));
            if (port < 0 || port > 65535) port = 80;
        } catch (RuntimeException ex) {
            port = 80;
        }
        try {
            HttpServer webserver = new HttpServer(port);
            webserver.start();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Server could not start", ex);
        }
    }
}