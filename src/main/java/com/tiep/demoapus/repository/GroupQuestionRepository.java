package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.GroupQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupQuestionRepository extends JpaRepository<GroupQuestionEntity, Long>, JpaSpecificationExecutor<GroupQuestionEntity> {
}
