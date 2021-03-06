package org.teinelund.application.accounting.entities;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "acc_user", schema = "public", catalog = "accounting")
@NamedQueries({
        @NamedQuery(name = "AccUserEntity.findByUserName", query = "SELECT a FROM AccUserEntity a WHERE a.userName = :userName")
})
public class AccUserEntity {
    private Long userId;
    private String userName;
    private String password;
    private short enabled;
    private Collection<UserRoleEntity> userRolesByUserId;
    private Collection<BankAccountEntity> bankAccountById;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="acc_user_seq", sequenceName="acc_user_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="acc_user_seq")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "name")
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
    public short getEnabled() {
        return enabled;
    }

    public void setEnabled(short enabled) {
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

    @OneToMany(mappedBy = "accUserByUserId")
    public Collection<BankAccountEntity> getBankAccountById() {
        return bankAccountById;
    }

    public void setBankAccountById(Collection<BankAccountEntity> bankAccountById) {
        this.bankAccountById = bankAccountById;
    }
}
