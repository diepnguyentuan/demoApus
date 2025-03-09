package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.ExperienceRequirementRequestDTO;
import com.tiep.demoapus.dto.response.ExperienceRequirementResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.entity.ExperienceRequirementEntity;
import com.tiep.demoapus.mapper.ExperienceRequirementMapper;
import com.tiep.demoapus.repository.ExperienceRequirementRepository;
import com.tiep.demoapus.service.ExperienceRequirementService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExperienceRequiImpl implements ExperienceRequirementService {

    private final ExperienceRequirementRepository experienceRequirementRepository;
    private final ExperienceRequirementMapper experienceRequirementMapper;

    @Override
    public PageableResponse<ExperienceRequirementResponseDTO> getAllExRequirement(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<ExperienceRequirementEntity> experienceRequirementEntities = experienceRequirementRepository.findAll(GenericSpecification.searchByName(search), pageRequest);
        Page<ExperienceRequirementResponseDTO> dtoPage = experienceRequirementEntities.map(experienceRequirementMapper::toDTO);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    @Override
    public ExperienceRequirementResponseDTO geExperienceRequirementResponseDto(Long id) {
        ExperienceRequirementEntity experienceRequirementEntity = experienceRequirementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ExperienceRequirement not found"));
        return experienceRequirementMapper.toDTO(experienceRequirementEntity);
    }

    @Override
    public ExperienceRequirementResponseDTO addExperienceRequirementResponseDto(ExperienceRequirementRequestDTO dto) {
        ExperienceRequirementEntity experienceRequirementEntity = experienceRequirementMapper.toEntity(dto);
        experienceRequirementEntity.setCreated_at(LocalDateTime.now());
        experienceRequirementEntity.setUpdated_at(LocalDateTime.now());
        experienceRequirementRepository.save(experienceRequirementEntity);
        return new ExperienceRequirementResponseDTO(experienceRequirementEntity.getId());
    }

    @Override
    public ExperienceRequirementResponseDTO updateExperienceRequirementResponseDto(ExperienceRequirementRequestDTO dto) {
        ExperienceRequirementEntity experienceRequirementEntity = experienceRequirementMapper.toEntity(dto);
        experienceRequirementEntity.setUpdated_at(LocalDateTime.now());
        experienceRequirementRepository.save(experienceRequirementEntity);
        return new ExperienceRequirementResponseDTO(experienceRequirementEntity.getId());
    }

    @Override
    public void deleteExRequirement(Long id) {
        if (!experienceRequirementRepository.existsById(id)) {
            throw new RuntimeException("ExperienceRequirement not found");
        }
        experienceRequirementRepository.deleteById(id);
    }
}
