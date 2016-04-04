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
package com.ecom.catalog.resource;

import com.ecom.server.TomcatServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import static org.junit.Assert.assertNotNull;

/**
 * Created by jcordones13 on 3/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class StatusResourceTest {
    private static final Logger LOG = LoggerFactory.getLogger(StatusResourceTest.class);
    private static TomcatServer tomcat;
    private static Client client;
    private static WebTarget target;
    private static String rootUrl = "/api/";

    /** */
    private static final int OK_CODE = 200;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        tomcat = new TomcatServer();
        tomcat.start();
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:" + tomcat.getPort() + rootUrl);

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        tomcat.stop();
        tomcat = null;

    }

    @Test
    public void testPing() throws Exception {

            WebTarget pingTarget = target.path("status/ping");
            Response res = pingTarget.request(MediaType.TEXT_PLAIN).get();

            Assert.assertEquals(OK_CODE, res.getStatus());
    }

    //@Test
    public void testHealth() throws Exception {

    }
}