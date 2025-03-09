package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.WorkTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorkTypeRepository extends JpaRepository<WorkTypeEntity, Long>, JpaSpecificationExecutor<WorkTypeEntity> {
}
