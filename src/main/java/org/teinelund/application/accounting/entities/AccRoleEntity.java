package org.teinelund.application.accounting.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "acc_role", schema = "public", catalog = "accounting")
public class AccRoleEntity {
    private long roleId;
    private String roleName;
    private Collection<UserRoleEntity> userRolesByRoleId;

    @Id
    @Column(name = "role_id")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccRoleEntity that = (AccRoleEntity) o;
        return roleId == that.roleId &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, roleName);
    }

    @OneToMany(mappedBy = "accRoleByRoleId")
    public Collection<UserRoleEntity> getUserRolesByRoleId() {
        return userRolesByRoleId;
    }

    public void setUserRolesByRoleId(Collection<UserRoleEntity> userRolesByRoleId) {
        this.userRolesByRoleId = userRolesByRoleId;
    }
}
