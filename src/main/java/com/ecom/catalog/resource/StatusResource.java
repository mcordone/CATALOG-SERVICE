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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by jcordones13 on 3/5/16.
 */
@Component
@Path("status")
@Api(value = "/status")
public class StatusResource {

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(httpMethod = "GET", value = "Ping catalog service to check status", notes = "Ping catalog service", response = String.class)
    public String ping(){
        return "pong";
    }

    @GET
    @Path("health")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(httpMethod = "GET", value = "Catalog service health check",
            notes = "Catalog service health check", response = String.class)
    public String health(){
        //TODO implement by returning a real health status
        return "Catalog service is in good health";
    }
}
