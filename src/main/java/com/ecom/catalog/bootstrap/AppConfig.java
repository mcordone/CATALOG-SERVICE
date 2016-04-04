/**
 * Copyright ${YEAR} Miguel Cordones
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
package com.ecom.catalog.bootstrap;

import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;

/**
 * Created by jcordones13 on 3/4/16.
 */
@ApplicationPath("api")
public class AppConfig extends ResourceConfig {
    private static Logger LOG = LoggerFactory.getLogger(AppConfig.class);

    public AppConfig() throws ServletException {
        LOG.info("************ APPLICATION BOOTSTRAP ****************");
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        register(RequestContextFilter.class);
        packages("com.ecom.catalog");
        register(LoggingFilter.class);

        //Adding swagger providers
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        //Swagger configuration
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0");
        beanConfig.setTitle("Catalog API");
        beanConfig.setDescription("Ecom Catalog RESTful API");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("/api/swagger.json");
        beanConfig.setBasePath("/catalog");
        beanConfig.setResourcePackage("com.ecom.catalog.resource");
        beanConfig.setScan(true);
    }
}
