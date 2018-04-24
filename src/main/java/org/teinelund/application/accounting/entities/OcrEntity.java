package org.teinelund.application.accounting.entities;

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
@Table(name = "ocr", schema = "public", catalog = "accounting")
public class OcrEntity {
    private Long id;
    private String ocrnumber;
    private Collection<InvoiceEntity> invoiceById;

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
    @Column(name = "ocrnumber")
    public String getGironumber() {
        return ocrnumber;
    }

    public void setGironumber(String ocrnumber) {
        this.ocrnumber = ocrnumber;
    }

    @OneToMany(mappedBy = "ocr")
    public Collection<InvoiceEntity> getGiroById() {
        return invoiceById;
    }

    public void setGiroById(Collection<InvoiceEntity> invoiceById) {
        this.invoiceById = invoiceById;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OcrEntity that = (OcrEntity) o;
        return id == that.id &&
                Objects.equals(ocrnumber, that.ocrnumber);
    }



    @Override
    public int hashCode() {

        return Objects.hash(id, ocrnumber);
    }
}
