package org.teinelund.application.accounting.entities;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "bank_account", schema = "public", catalog = "accounting")
@NamedQueries({
        @NamedQuery(name = "BankAccountEntity.findBankAccountsByUserId", query = "SELECT a FROM BankAccountEntity a WHERE a.accUserByUserId.userId = :userId")
})
public class BankAccountEntity {


    private Long id;
    private String name;
    private String description;
    private AccUserEntity accUserByUserId;
    private Collection<InvoiceEntity> invoiceById;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="bank_account_seq", sequenceName="bank_account_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="bank_account_seq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "fk_acc_user_id", referencedColumnName = "id", nullable = false)
    public AccUserEntity getAccUserByUserId() {
        return accUserByUserId;
    }

    public void setAccUserByUserId(AccUserEntity accUserByUserId) {
        this.accUserByUserId = accUserByUserId;
    }

    @OneToMany(mappedBy = "bankAccount")
    public Collection<InvoiceEntity> getUserRolesByUserId() {
        return invoiceById;
    }

    public void setUserRolesByUserId(Collection<InvoiceEntity> invoiceById) {
        this.invoiceById = invoiceById;
    }
}
