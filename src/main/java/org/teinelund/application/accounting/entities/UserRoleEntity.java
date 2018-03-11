package org.teinelund.application.accounting.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "user_role", schema = "public", catalog = "accounting")
public class UserRoleEntity {
    private long id;
    private AccUserEntity accUserByUserId;
    private AccRoleEntity accRoleByRoleId;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleEntity that = (UserRoleEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public AccUserEntity getAccUserByUserId() {
        return accUserByUserId;
    }

    public void setAccUserByUserId(AccUserEntity accUserByUserId) {
        this.accUserByUserId = accUserByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    public AccRoleEntity getAccRoleByRoleId() {
        return accRoleByRoleId;
    }

    public void setAccRoleByRoleId(AccRoleEntity accRoleByRoleId) {
        this.accRoleByRoleId = accRoleByRoleId;
    }
}
