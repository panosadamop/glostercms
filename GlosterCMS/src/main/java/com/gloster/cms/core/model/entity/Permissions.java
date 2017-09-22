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

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author diakogiannisa
 */
@Entity
@Table(catalog = "glostercmsdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permissions.findAll", query = "SELECT p FROM Permissions p")
    , @NamedQuery(name = "Permissions.findByPermissionId", query = "SELECT p FROM Permissions p WHERE p.permissionId = :permissionId")
    , @NamedQuery(name = "Permissions.findByPermissionName", query = "SELECT p FROM Permissions p WHERE p.permissionName = :permissionName")})
public class Permissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "permission_id", nullable = false, length = 16)
    private String permissionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "permission_name", nullable = false, length = 45)
    private String permissionName;
    @JoinTable(name = "roles_permissions", joinColumns = {
        @JoinColumn(name = "permissions_permission_id", referencedColumnName = "permission_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "roles_roles_id", referencedColumnName = "roles_id", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Roles> rolesCollection;

    public Permissions() {
    }

    public Permissions(String permissionId) {
        this.permissionId = permissionId;
    }

    public Permissions(String permissionId, String permissionName) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @XmlTransient
    public Collection<Roles> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permissionId != null ? permissionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissions)) {
            return false;
        }
        Permissions other = (Permissions) object;
        if ((this.permissionId == null && other.permissionId != null) || (this.permissionId != null && !this.permissionId.equals(other.permissionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Permissions{" + "permissionId=" + permissionId + ", permissionName=" + permissionName + ", rolesCollection=" + rolesCollection + '}';
    }

    
    
}
