/*
 * Copyright 2017 Alexius Diakogiannis <alexius@jee.gr>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gloster.cms.core.sample.api.rest;

import javax.ejb.Stateful;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alexius Diakogiannis <alexius@jee.gr>
 */
@Stateful
@Path("/myservice")
public class MyRestService {

    Logger LOG = LoggerFactory.getLogger(MyRestService.class);
    
    @GET
    @Path("/simple")
    @Produces(MediaType.TEXT_PLAIN)
    public Response mySimpleRest() {
        LOG.debug("===== ALOHA ====");
        return Response.ok().entity("Hello World").build();
    }

}
