package com.github.blanexie.vxpt.tracker;

import com.github.blanexie.vxpt.tracker.ioc.annotation.Ioc;
import com.github.blanexie.vxpt.ioc.web.HttpServer;

public class App {

    public static void main(String[] args) throws Exception {
        Ioc.load(App.class);
        HttpServer httpServer = new HttpServer();
        httpServer.start();
    }

}
