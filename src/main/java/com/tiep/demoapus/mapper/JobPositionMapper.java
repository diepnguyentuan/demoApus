package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.entity.JobPositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {IndustryJobMapper.class})
public interface JobPositionMapper {

    @Mapping(target = "industry", source = "industryEntity")
    JobPositionResponseDTO toDto(JobPositionEntity entity);

    @Mapping(target = "maps", ignore = true)
    JobPositionEntity toEntity(JobPositionRequestDTO dto);

    @Mapping(target = "maps", ignore = true)
    void updateEntityFromDto(JobPositionRequestDTO dto, @MappingTarget JobPositionEntity entity);
}
