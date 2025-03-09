package com.tiep.demoapus.repository;

import com.tiep.demoapus.dto.request.BenefitRequestDTO;
import com.tiep.demoapus.entity.BenefitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BenefitRepository extends JpaRepository<BenefitEntity, Long>, JpaSpecificationExecutor<BenefitEntity> {
}
