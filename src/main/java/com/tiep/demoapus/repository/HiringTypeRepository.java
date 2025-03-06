package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.HiringTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HiringTypeRepository extends JpaRepository<HiringTypeEntity, Long>, JpaSpecificationExecutor<HiringTypeEntity> {
}
