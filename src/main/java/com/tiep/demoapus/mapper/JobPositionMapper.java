package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.entity.JobPositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IndustryJobMapper.class})
public interface JobPositionMapper {

    @Mapping(target = "industry", source = "industryEntity")
    // Không map field "lines" (sẽ được set lại trong service)
    @Mapping(target = "lines", ignore = true)
    JobPositionResponseDTO toDto(JobPositionEntity entity);

    JobPositionEntity toEntity(JobPositionRequestDTO dto);
}
