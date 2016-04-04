/**
 * Copyright 2016 Miguel Cordones
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ecom.server;

import org.apache.catalina.Context;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.File;

/**
 * Created by jcordones13 on 3/5/16.
 */
public class TomcatServer {
    private Tomcat tomcat;
    private Context ctx;
    private int port;
    private String appBase;
    private String contextPath;
    private String hostname;
    private String baseDir;
    private static final Logger LOG = LoggerFactory.getLogger(TomcatServer.class);

    public TomcatServer(){
        this.port = 0;
        this.appBase = "src/main/webapp";
        this.contextPath = "";
        this.baseDir = ".";
        this.hostname = "localhost";
        this.tomcat = new Tomcat();
        this.tomcat.setPort(this.port);
        this.tomcat.setBaseDir(this.baseDir);
        this.tomcat.getHost().setAppBase(this.appBase);
        this.tomcat.setHostname(this.hostname);

        try {
            ctx = tomcat.addWebapp(contextPath, new File(appBase).getAbsolutePath());
        } catch (ServletException | NullPointerException e) {
            LOG.error("**** TOMCAT INITIALIZATION ERROR " + e.getMessage());
            e.printStackTrace();
        }

        File additionWebInfClasses = new File("build/classes");
        StandardRoot resources = new StandardRoot(ctx);
        resources.addPreResources(
                new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        // TODO check on this
        TomcatURLStreamHandlerFactory.disable();

        // Add AprLifecycleListener
        StandardServer server = (StandardServer) tomcat.getServer();
        AprLifecycleListener listener = new AprLifecycleListener();
        server.addLifecycleListener(listener);
    }

    public final void start() throws Exception {
        this.tomcat.start();
    }

    public final void stop() throws Exception {
        this.tomcat.stop();
    }

    public final int getPort() {
        return tomcat.getConnector().getLocalPort();
    }
}
