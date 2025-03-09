package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.ExperienceRequirementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExperienceRequirementRepository extends JpaRepository<ExperienceRequirementEntity, Long>, JpaSpecificationExecutor<ExperienceRequirementEntity> {
}
