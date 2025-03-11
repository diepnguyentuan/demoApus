package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.response.IndustryJobResponseDTO;
import com.tiep.demoapus.entity.IndustryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IndustryJobMapper {

    // Chỉ map 2 trường: id và name
    IndustryJobResponseDTO toDTO(IndustryEntity industryEntity);
}
