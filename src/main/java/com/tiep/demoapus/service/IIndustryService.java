package com.tiep.demoapus.service;

import com.tiep.demoapus.entity.Industry;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Service interface định nghĩa các phương thức xử lý liên quan đến Industry.
 */
public interface IIndustryService {
    List<Industry> getAllIndustries();
    Industry getIndustryById(Long id);
    Industry addIndustry(Industry industry);
    Industry updateIndustry(Industry industry);
    Industry deleteIndustry(Long id);
    boolean existsById(Long id);
    boolean existsByCode(String code);

    Page<Industry> getIndustries(int page, int size, String sort);
}
