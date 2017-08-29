/*
 * Copyright 2017 diakogiannisa.
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
package com.gloster.cms.core.test.incontainer;

import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.sql.DataSource;
import static junit.framework.TestCase.assertNotNull;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wildfly.swarm.arquillian.DefaultDeployment;


/**
 *
 * @author diakogiannisa
 */
@RunWith(Arquillian.class)
@DefaultDeployment(type = DefaultDeployment.Type.JAR)
public class DataSourceTest {

    Logger LOG = LoggerFactory.getLogger(DataSourceTest.class);
    
    //@ArquillianResource
    //InitialContext context;

    @Test
    public void testDataSourceIsBound() throws Exception {
       
//        NamingEnumeration<NameClassPair> list = context.list("");
//        while (list.hasMore()) {
//          LOG.debug("=========================>"+list.next().getName());
//        }
//        
//        DataSource ds = (DataSource) context.lookup("java:jboss/datasources/CoreDS");
//        assertNotNull(ds);
    }

}
