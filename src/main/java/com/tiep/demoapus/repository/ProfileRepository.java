package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long>, JpaSpecificationExecutor<ProfileEntity> {
}
