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
package com.gloster.cms.core.authority.persistance.repositories;

import com.gloster.cms.core.authority.exceptions.persistance.GenericPersistanceException;
import com.gloster.cms.core.model.entity.Permissions;
import com.gloster.cms.core.model.entity.QPermissions;
import com.gloster.cms.core.model.entity.QRoles;
import com.gloster.cms.core.model.entity.Roles;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Alexius Diakogiannis <alexius@jee.gr>
 */
@Stateless
public class UserRepository  {

    Logger LOG = LoggerFactory.getLogger(UserRepository.class);
 
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public Permissions saveOrUpdatePermission(Permissions p) throws GenericPersistanceException{
        LOG.debug("Inser/Update {}",p);
        p = em.merge(p);
        return p;
    }
    
    @Transactional
    public Roles saveOrUpdateRole(Roles r) throws GenericPersistanceException{
        LOG.debug("Inser/Update {}",r);
        r = em.merge(r);
        return r;
    }
    
    public List<Roles> getAllRoles(){
        JPAQuery query = new JPAQuery(em);
        QRoles role = QRoles.roles;
        QPermissions permission = QPermissions.permissions;
        List<Roles> roles  = query.from(role).leftJoin(role.permissionsCollection,permission).fetchJoin().fetchAll().fetch();
        return roles;
    }
    
    public List<Roles> getAllRolesJPA(){
        List<Roles> roles = new ArrayList();
        roles = em.createQuery("from Roles r left join fetch r.permissionsCollection").getResultList();
        return roles;
    }
    
}
