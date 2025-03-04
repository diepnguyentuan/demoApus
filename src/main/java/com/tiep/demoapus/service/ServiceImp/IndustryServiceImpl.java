package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.IndustryRequestDTO;
import com.tiep.demoapus.dto.response.IndustryResponseDTO;
import com.tiep.demoapus.entity.IndustryEntity;
import com.tiep.demoapus.mapper.IndustryMapper;
import com.tiep.demoapus.repository.IndustryRepository;
import com.tiep.demoapus.service.IIndustryService;
import com.tiep.demoapus.specification.IndustrySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class IndustryServiceImpl implements IIndustryService {

    private final IndustryRepository industryRepository;
    private final IndustryMapper industryMapper;

    @Override
    public Page<IndustryResponseDTO> getIndustries(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith(":DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<IndustryEntity> pageData = industryRepository.findAll(IndustrySpecification.searchByCodeOrName(search), pageable);
        return pageData.map(industryMapper::toDTO);
    }

    @Override
    public IndustryResponseDTO getIndustryById(Long id) {
        IndustryEntity industryEntity = industryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Industry not found"));
        return industryMapper.toDTO(industryEntity);
    }

    @Override
    public IndustryResponseDTO addIndustry(IndustryRequestDTO dto) {
        if (industryRepository.findByCode(dto.getCode()).isPresent()) {
            throw new RuntimeException("Industry code already exists");
        }
        IndustryEntity industryEntity = industryMapper.toEntity(dto);
        industryEntity.setCreatedAt(LocalDateTime.now());
        industryEntity.setUpdatedAt(LocalDateTime.now());
        return industryMapper.toDTO(industryRepository.save(industryEntity));
    }

    @Override
    public IndustryResponseDTO updateIndustry(Long id, IndustryRequestDTO dto) {
        IndustryEntity industryEntity = industryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Industry not found"));
        industryMapper.updateIndustryFromDTO(dto, industryEntity);
        industryEntity.setUpdatedAt(LocalDateTime.now());
        return industryMapper.toDTO(industryRepository.save(industryEntity));
    }

    @Override
    public void deleteIndustry(Long id) {
        if (!industryRepository.existsById(id)) {
            throw new RuntimeException("Industry not found");
        }
        industryRepository.deleteById(id);
    }
}
