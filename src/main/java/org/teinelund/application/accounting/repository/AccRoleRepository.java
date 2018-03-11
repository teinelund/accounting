package org.teinelund.application.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.teinelund.application.accounting.entities.AccRoleEntity;

@Repository
public interface AccRoleRepository extends JpaRepository<AccRoleEntity, Long> {
}
