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
package com.gloster.cms.core.authority.services;

import com.gloster.cms.core.authority.persistance.repositories.UserRepository;
import com.gloster.cms.core.model.entity.Permissions;
import com.gloster.cms.core.model.entity.Roles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alexius Diakogiannis <alexius@jee.gr>
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserService {

    Logger LOG = LoggerFactory.getLogger(UserService.class);

    @EJB
    UserRepository userRepository;

    @Resource
    private EJBContext context;
    
    public void generateDummyUserData() {
        UserTransaction utx = context.getUserTransaction();
        try {
            utx.begin();
            Collection<Permissions> permissionsCol = new ArrayList();
            Roles r = new Roles(UUID.randomUUID().toString(), "FOO_ROLE");

            
            Permissions p = new Permissions(UUID.randomUUID().toString(), "A_GOOD_PERMISSION");
            permissionsCol.add(p);
            userRepository.saveOrUpdatePermission(p);
            
            p = new Permissions(UUID.randomUUID().toString(), "A_GOOD_PERMISSION2");
            permissionsCol.add(p);
            userRepository.saveOrUpdatePermission(p);
            
            
            p = new Permissions(UUID.randomUUID().toString(), "A_GOOD_PERMISSION3");
            permissionsCol.add(p);
            userRepository.saveOrUpdatePermission(p);
            
            
            r.setPermissionsCollection(permissionsCol);
            userRepository.saveOrUpdateRole(r);
            utx.commit();
        }
        catch (Exception gpe) {
            try {
                utx.rollback();
            }
            catch (Exception ex) {
                LOG.error(ex.getMessage());
            }
            
            LOG.error(gpe.getMessage());
        }
        

    }

    public List<Roles> getAllroles() {
        List<Roles> roles = userRepository.getAllRoles();

        return roles;
    }

    public List<Roles> getAllrolesJPA() {
        List<Roles> roles = userRepository.getAllRolesJPA();

        return roles;
    }

}
