package com.was.middleware;

import com.was.middleware.Class.Router;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eden on 2017. 8. 26..
 */
public class RouterModule {
    private static Logger logger = LoggerFactory.getLogger(RouterModule.class);
    private Map<String, Router> routers = new HashMap<>();
    private Map<String, String> uriPackage = new HashMap<>();

    public RouterModule() {
    }

    public void buildRouter(JSONObject mapper) {
        JSONArray jsonArray = (JSONArray) mapper.get("mapping_package");

        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObj = (JSONObject)jsonArray.get(i);
            Router router = Router.getInstance(jsonObj.get("name").toString());
            JSONObject jsonSubArray = (JSONObject) jsonObj.get("routes");

            for (Object key : jsonSubArray.keySet()) {
                String keyStr = String.valueOf(key);
                String keyvalue = String.valueOf(jsonSubArray.get(keyStr));
                router.push(keyStr, keyvalue);
                this.uriPackage.put(keyStr, jsonObj.get("name").toString());
            }
            this.routers.put(jsonObj.get("name").toString(), router);
        }
    }

    public Router getRouter(String packageName) {
        return this.routers.get(packageName);
    }

    public Map<String, String> getUriPackages() {
        return this.uriPackage;
    }

    public String getUriPackage(String uri) {
        return this.uriPackage.get(uri);
    }
}
