package com.was.middleware.Class;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eden on 2017. 8. 26..
 */
public class Router {
    private String name;
    public Map<String, String> map = new HashMap<String, String>();

    private Router(String name) {
        this.name = name;
    }

    public static Router getInstance(String name) {
        return new Router(name);
    }

    public void push(String key, String name) {
        map.put(key, name);
    }

    public String getRouter(String key) {
        return map.get(key);
    }
}
