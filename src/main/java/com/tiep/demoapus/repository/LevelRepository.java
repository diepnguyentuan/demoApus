package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LevelRepository extends JpaRepository<LevelEntity, Long>, JpaSpecificationExecutor<LevelEntity> {
}
