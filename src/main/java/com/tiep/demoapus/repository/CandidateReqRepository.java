package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.CandidateReqEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CandidateReqRepository extends JpaRepository<CandidateReqEntity, Long>, JpaSpecificationExecutor<CandidateReqEntity> {
}
