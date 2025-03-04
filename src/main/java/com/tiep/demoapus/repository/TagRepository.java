package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.Industry;
import com.tiep.demoapus.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
