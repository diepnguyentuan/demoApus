package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.entity.JobPositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {IndustryJobMapper.class})
public interface JobPositionMapper {

    @Mapping(target = "industry", source = "industryEntity")
    @Mapping(target = "jobPositionMaps", source = "jobPositionMapEntities")
    JobPositionResponseDTO toDto(JobPositionEntity entity);
    JobPositionEntity toEntity(JobPositionRequestDTO dto);
}
