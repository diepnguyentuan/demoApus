package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.entity.Industry;
import com.tiep.demoapus.repository.IndustryRepository;
import com.tiep.demoapus.service.IIndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Triển khai các phương thức của IIndustryService.
 */
@Service
public class IndustryServiceImplement implements IIndustryService {

    @Autowired
    private IndustryRepository industryRepository;

    @Override
    public List<Industry> getAllIndustries() {
        List<Industry> industries = industryRepository.findAll();
        System.out.println("Số lượng ngành nghề tìm thấy: " + industries.size());
        return industries;
    }

    @Override
    public Industry getIndustryById(Long id) {
        return industryRepository.findById(id).orElse(null);
    }

    @Override
    public Industry addIndustry(Industry industry) {
        if (industry.getCreatedAt() == null) {
            industry.setCreatedAt(LocalDateTime.now());
        }
        return industryRepository.save(industry);
    }


    @Override
    public Industry updateIndustry(Industry industry) {
        return industryRepository.save(industry);
    }

    @Override
    public Industry deleteIndustry(Long id) {
        Industry industry = getIndustryById(id);
        if (industry != null) {
            industryRepository.delete(industry);
        }
        return industry;
    }

    @Override
    public boolean existsById(Long id) {
        return industryRepository.findById(id).isPresent();
    }

    @Override
    public boolean existsByCode(String code) {
        return industryRepository.findByCode(code).isPresent();
    }

    @Override
    public Page<Industry> getIndustries(int page, int size, String sort) {
        // Chuyển chuỗi sort ("createdAt,DESC") thành đối tượng Sort
        String[] sortParams = sort.split(":");
        String sortBy = sortParams[0];
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("DESC")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        // Tạo Pageable object
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        // Gọi repository để lấy dữ liệu có phân trang
        return industryRepository.findAll(pageable);
    }
}
