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
package com.gloster.cms.core.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author diakogiannisa
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"role_name"})})
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
    , @NamedQuery(name = "Roles.findByRolesId", query = "SELECT r FROM Roles r WHERE r.rolesId = :rolesId")
    , @NamedQuery(name = "Roles.findByRoleName", query = "SELECT r FROM Roles r WHERE r.roleName = :roleName")})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "roles_id", nullable = false, length = 36)
    private String rolesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "role_name", nullable = false, length = 45)
    private String roleName;

    @JoinTable(name = "roles_permissions", 
            joinColumns = {
                            @JoinColumn(name = "roles_roles_id", 
                            referencedColumnName = "roles_id", 
                            nullable = false)
            }, 
            inverseJoinColumns = {
                @JoinColumn(name = "permissions_permission_id", 
                        referencedColumnName = "permission_id", 
                        nullable = false)
            })
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.REFRESH})
    private Collection<Permissions> permissionsCollection;

    @ManyToMany(mappedBy = "rolesCollection")
    private Collection<Users> usersCollection;

    public Roles() {
    }

    public Roles(String rolesId) {
        this.rolesId = rolesId;
    }

    public Roles(String rolesId, String roleName) {
        this.rolesId = rolesId;
        this.roleName = roleName;
    }

    public String getRolesId() {
        return rolesId;
    }

    public void setRolesId(String rolesId) {
        this.rolesId = rolesId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<Permissions> getPermissionsCollection() {
        return permissionsCollection;
    }

    public void setPermissionsCollection(Collection<Permissions> permissionsCollection) {
        this.permissionsCollection = permissionsCollection;
    }
    
    @JsonIgnore
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesId != null ? rolesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.rolesId == null && other.rolesId != null) || (this.rolesId != null && !this.rolesId.equals(other.rolesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Roles{" + "rolesId=" + rolesId + ", roleName=" + roleName + ", permissionsCollection=" + permissionsCollection + ", usersCollection=" + usersCollection + '}';
    }

}
