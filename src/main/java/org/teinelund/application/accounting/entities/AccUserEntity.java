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
@Table(name = "acc_user", schema = "public", catalog = "accounting")
public class AccUserEntity {
    private long userId;
    private String userName;
    private String password;
    private int enabled;
    private Collection<UserRoleEntity> userRolesByUserId;

    @Id
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "enabled")
    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccUserEntity that = (AccUserEntity) o;
        return userId == that.userId &&
                enabled == that.enabled &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userName, password, enabled);
    }

    @OneToMany(mappedBy = "accUserByUserId")
    public Collection<UserRoleEntity> getUserRolesByUserId() {
        return userRolesByUserId;
    }

    public void setUserRolesByUserId(Collection<UserRoleEntity> userRolesByUserId) {
        this.userRolesByUserId = userRolesByUserId;
    }
}
