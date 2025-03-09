package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.EmailTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplateEntity, Long>, JpaSpecificationExecutor<EmailTemplateEntity> {
}
