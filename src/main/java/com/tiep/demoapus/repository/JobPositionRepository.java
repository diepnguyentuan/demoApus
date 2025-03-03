package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {
}

