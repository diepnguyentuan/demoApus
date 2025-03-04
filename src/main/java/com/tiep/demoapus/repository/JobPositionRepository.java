package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.JobPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPositionRepository
        extends JpaRepository<JobPositionEntity, Long>,
        JpaSpecificationExecutor<JobPositionEntity> {
}
