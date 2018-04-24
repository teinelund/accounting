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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "invoice", schema = "public", catalog = "accounting")
@NamedQueries({
        @NamedQuery(name = "InvoiceEntity.findInvoicesByBankAccountId", query = "SELECT a FROM InvoiceEntity a WHERE a.bankAccount.id = :id")
})
public class InvoiceEntity {
    private Long id;
    private String name;
    private Date payDate;
    private BigDecimal amount;
    private BankAccountEntity bankAccount;
    private GiroEntity giro;
    private OcrEntity ocr;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="ocr_seq", sequenceName="ocr_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="ocr_seq")
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
    @Column(name = "paydate")
    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Basic
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @ManyToOne
    @JoinColumn(name = "fk_bank_account", referencedColumnName = "id", nullable = false)
    public BankAccountEntity getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountEntity bankAccount) {
        this.bankAccount = bankAccount;
    }

    @ManyToOne
    @JoinColumn(name = "fk_giro_id", referencedColumnName = "id", nullable = false)
    public GiroEntity getGiro() {
        return giro;
    }

    public void setGiro(GiroEntity giro) {
        this.giro = giro;
    }

    @ManyToOne
    @JoinColumn(name = "fk_ocr_id", referencedColumnName = "id", nullable = false)
    public OcrEntity getOcr() {
        return ocr;
    }

    public void setOcr(OcrEntity ocr) {
        this.ocr = ocr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceEntity that = (InvoiceEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }



    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
