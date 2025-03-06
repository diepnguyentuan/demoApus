package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SourceRepository extends JpaRepository<SourceEntity, Long>, JpaSpecificationExecutor<SourceEntity> {
}
