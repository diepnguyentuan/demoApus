package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.ExperienceRequirementRequestDTO;
import com.tiep.demoapus.dto.response.ExperienceRequirementResponseDTO;
import com.tiep.demoapus.entity.ExperienceRequirementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExperienceRequirementMapper {
    ExperienceRequirementResponseDTO toDTO(ExperienceRequirementEntity entity);
    ExperienceRequirementEntity toEntity(ExperienceRequirementRequestDTO dto);
}
