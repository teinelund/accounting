package org.teinelund.application.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.teinelund.application.accounting.entities.AccUserEntity;

@Repository
public interface AccUserRepository extends JpaRepository<AccUserEntity, Long> {

    /**
     * Get AccUserEntity by user name.
     *
     * @param userName
     * @return AllowedValues .
     */
    @Query(name = "AccUserEntity.findByUserName")
    AccUserEntity getAccUserEntityForUserName(@Param("userName") String userName);
}
