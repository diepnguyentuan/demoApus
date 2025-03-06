package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.RecruitmentChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RecruitmentChannelRepository extends JpaRepository<RecruitmentChannelEntity, Long>, JpaSpecificationExecutor<RecruitmentChannelEntity> {
}
