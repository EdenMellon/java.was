package com.was.middleware;

import java.util.*;
import com.typesafe.config.*;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.was.middleware.Class.Server;

/**
 * Created by Eden on 2017. 8. 25..
 */
public class ServerModule {
    private static Logger logger = LoggerFactory.getLogger(Server.class);
    private Map<String, Server> srvs = new HashMap<String, Server>();
    private Server defaultServer;

    public ServerModule(List<? extends Config> servers)
        throws IllegalAccessException, ParseException {
        for(Config cv : servers) {
            Server srv = new Server(cv.getString("server_name"));
            srv.setDomain(cv.getString("domain"));
            srv.setHttproot(cv.getString("http_root"));
            srv.setSource(cv.getConfig("source"));

            srvs.put(srv.getName(), srv);
        }
    }

    public Server getDefaultServer() {
        return this.defaultServer;
    }

    /**
     * config 설정을 따라 적용되어야 되기에 String를 파라메터로 받음
     * @param srvName
     */
    public void setDefaultServer(String srvName) {
        Server srv = srvs.get(srvName);
        this.defaultServer = srv;
    }

    public Server getServer(String srvName) {
        Server srv = srvs.get(srvName);
        return srv;
    }

    public void setDefaultServerDomain(String domain) {
        if(!this.getDefaultServer().getDomain().equals(domain)) {
            for( Map.Entry<String, Server> srv : srvs.entrySet() ){
                if(srv.getValue().getDomain().equals(domain))
                    this.defaultServer = srv.getValue();
            }
        }
    }

    public Integer getServerCount() {
        return srvs.size();
    }
}