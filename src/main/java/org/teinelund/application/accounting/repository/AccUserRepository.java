package org.teinelund.application.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.teinelund.application.accounting.entities.AccUserEntity;

@Repository
public interface AccUserRepository extends JpaRepository<AccUserEntity, Long> {

}
