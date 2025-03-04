package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.IndustryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndustryRepository extends JpaRepository<IndustryEntity, Long>, JpaSpecificationExecutor<IndustryEntity> {
    Optional<IndustryEntity> findByCode(String code);
}
