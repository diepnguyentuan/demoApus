package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.ReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonRepository extends JpaRepository<ReasonEntity, Long>, JpaSpecificationExecutor<ReasonEntity> {
}
