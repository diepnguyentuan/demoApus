package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.BenefitMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BenefitMapRepository extends JpaRepository<BenefitMapEntity, Long>, JpaSpecificationExecutor<BenefitMapEntity> {
    // Sửa lại phương thức: sử dụng findByBenefit_Id để truy xuất theo benefit.id
    List<BenefitMapEntity> findByBenefit_Id(Long benefitId);

    List<BenefitMapEntity> findByBenefit_IdIn(List<Long> benefitIds);

}
