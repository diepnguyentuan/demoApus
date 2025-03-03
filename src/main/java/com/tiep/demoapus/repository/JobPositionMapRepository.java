package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.JobPositionMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPositionMapRepository extends JpaRepository<JobPositionMap, Long> {
    void deleteByJobPositionId(Long id);
}
