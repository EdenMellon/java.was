package com.was.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Eden on 2017. 8. 26..
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpRequest.class);

    public static String getFileContent(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        int read;
        char[] bf = new char[1024];
        while ((read = br.read(bf)) > 0) {
            buffer.append(bf, 0, read);
        }
        br.close();
        is.close();

        return buffer.toString();
    }
}
