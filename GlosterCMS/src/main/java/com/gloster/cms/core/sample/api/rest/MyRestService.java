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

import com.gloster.cms.core.authority.services.UserService;
import com.gloster.cms.core.authority.exceptions.persistance.GenericPersistanceException;
import javax.ejb.EJB;
import javax.ejb.Singleton;
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
@Singleton
@Path("/myservice")
public class MyRestService {

    Logger LOG = LoggerFactory.getLogger(MyRestService.class);
    
    @EJB
    UserService userService;

    
    @GET
    @Path("/simple")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mySimpleRest() {
        
        try{
            userService.generateDummyUserData();
        }catch(Exception e){
            LOG.error(e.getLocalizedMessage());
        }
        
        return Response.ok().entity(userService.getAllroles()).build();
    }

}
