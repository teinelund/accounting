package org.teinelund.application.accounting.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "giro", schema = "public", catalog = "accounting")
public class GiroEntity {

    private Long id;
    private String name;
    private String gironumber;
    private GiroType giroType;
    private Collection<InvoiceEntity> invoiceById;

    public static enum GiroType {
        bankgiro, postgiro;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="giro_seq", sequenceName="giro_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="giro_seq")
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
    @Column(name = "gironumber")
    public String getGironumber() {
        return gironumber;
    }

    public void setGironumber(String gironumber) {
        this.gironumber = gironumber;
    }

    /**
     * See https://stackoverflow.com/questions/851758/java-enums-jpa-and-postgres-enums-how-do-i-make-them-work-together
     * @return
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public GiroType getGiroType() {
        return giroType;
    }

    public void setGiroType(GiroType giroType) {
        this.giroType = giroType;
    }

    @OneToMany(mappedBy = "giro")
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
        GiroEntity that = (GiroEntity) o;
        return id == that.id &&
                giroType == that.giroType &&
                Objects.equals(name, that.name) &&
                Objects.equals(gironumber, that.gironumber);
    }



    @Override
    public int hashCode() {

        return Objects.hash(id, name, gironumber, giroType);
    }

}