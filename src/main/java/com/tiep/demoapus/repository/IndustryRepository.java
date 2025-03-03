package com.tiep.demoapus.repository;

import com.tiep.demoapus.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository cho Entity Industry.
 */
@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long> {

    /**
     * Tìm Industry theo mã code.
     *
     * @param code mã của Industry
     * @return Optional chứa Industry nếu tìm thấy
     */
    Optional<Industry> findByCode(String code);
}
