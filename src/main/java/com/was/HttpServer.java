package com.was;

import com.was.handler.HttpHandler;
import com.was.middleware.ConfigModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Eden on 2017. 8. 24..
 */
public class HttpServer {
    private static Logger logger = LoggerFactory.getLogger(HttpServer.class);
    private static final int NUM_THREADS = 50;
    private int port = 80;

    public HttpServer(int port) throws IOException {
        this.port = port;
    }

    public void start() throws Exception {
        // TODO:: config 설정의 서버 갯수에 따라 Server을 여러개 띄우는 로직이 구현되어야..
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                try {
                    Socket request = server.accept();
                    Runnable r = new HttpHandler(request);
                    pool.submit(r);
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    public static void main(String[] args) {
        // singleton 패턴으로 static configuration 구현.
        // server01, server02 설정 파일 분리.
        ConfigModule properties = ConfigModule.getInstance();

        int port;
        try {
            port = Integer.parseInt(String.valueOf(properties.getPort()));
            if (port < 0 || port > 65535) port = 80;
        } catch (RuntimeException e) {
            logger.warn(e.getMessage(), e);
            port = 80;
        }
        try {
            HttpServer webserver = new HttpServer(port);
            webserver.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}