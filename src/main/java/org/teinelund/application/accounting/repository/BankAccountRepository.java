package org.teinelund.application.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.teinelund.application.accounting.entities.BankAccountEntity;

import java.util.Set;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {

    /**
     * Get all BankAccountEntity objects by user AccUserEntity.userId.
     *
     * @param userId
     * @return Set<BankAccountEntity>
     */
    @Query(name = "BankAccountEntity.findBankAccountsByUserId")
    Set<BankAccountEntity> findBankAccountsByUserId(@Param("userId") long userId);
}
