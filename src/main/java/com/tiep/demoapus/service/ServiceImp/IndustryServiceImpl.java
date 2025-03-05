package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.IndustryRequestDTO;
import com.tiep.demoapus.dto.response.IndustryResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
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
    public PageableResponse<IndustryResponseDTO> getAllIndustries(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith(":DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<IndustryEntity> pageData = industryRepository.findAll(IndustrySpecification.searchByCodeOrName(search), pageable);
        Page<IndustryResponseDTO> dtoPage = pageData.map(industryMapper::toDTO);

        return PageableResponse.<IndustryResponseDTO>builder()
                .content(dtoPage.getContent())
                .page(dtoPage.getNumber())
                .size(dtoPage.getSize())
                .sort(sort)  // Nếu cần format lại, xử lý tại đây
                .totalElements(dtoPage.getTotalElements())
                .totalPages(dtoPage.getTotalPages())
                .numberOfElements(dtoPage.getNumberOfElements())
                .build();
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
    public IndustryResponseDTO updateIndustry(IndustryRequestDTO dto) {
        IndustryEntity industryEntity = industryRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Industry not found"));
        industryEntity.setName(dto.getName());
        industryEntity.setCode(dto.getCode());
        industryEntity.setActive(dto.getActive());
        industryEntity.setDescription(dto.getDescription());
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

    @Override
    public boolean existsById(Long id) {
        return industryRepository.existsById(id);
    }
}
