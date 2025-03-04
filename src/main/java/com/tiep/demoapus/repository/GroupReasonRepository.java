package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.GroupReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupReasonRepository
        extends JpaRepository<GroupReasonEntity, Long>,
        JpaSpecificationExecutor<GroupReasonEntity> {
}
