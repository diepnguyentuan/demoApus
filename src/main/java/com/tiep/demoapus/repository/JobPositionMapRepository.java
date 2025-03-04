package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.JobPositionMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPositionMapRepository extends JpaRepository<JobPositionMap, Long> {
    List<JobPositionMap> findByJobPositionId(Long jobPositionId);
    void deleteByJobPositionId(Long id);
}
