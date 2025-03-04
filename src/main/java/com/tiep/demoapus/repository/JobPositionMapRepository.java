package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.JobPositionMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPositionMapRepository extends JpaRepository<JobPositionMapEntity, Long> {
    List<JobPositionMapEntity> findByJobPositionId(Long jobPositionId);
    void deleteByJobPositionId(Long id);
}
