package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.IndustryRequestDTO;
import com.tiep.demoapus.dto.response.IndustryResponseDTO;
import com.tiep.demoapus.entity.IndustryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IndustryMapper {
    IndustryResponseDTO toDTO(IndustryEntity industryEntity);
    IndustryEntity toEntity(IndustryRequestDTO dto);
}
