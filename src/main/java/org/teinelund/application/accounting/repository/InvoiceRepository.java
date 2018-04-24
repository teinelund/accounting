package org.teinelund.application.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.teinelund.application.accounting.entities.InvoiceEntity;

import java.util.Set;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    @Query(name = "InvoiceEntity.findInvoicesByBankAccountId")
    Set<InvoiceEntity> findInvoicesByBankAccountId(@Param("id") long id);
}
