package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.HiringEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HiringRepository extends JpaRepository<HiringEntity, Long>, JpaSpecificationExecutor<HiringEntity> {
}
