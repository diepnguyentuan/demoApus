package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.entity.JobPositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IndustryJobMapper.class})
public interface JobPositionMapper {

    @Mapping(target = "industry", source = "industryEntity")
    JobPositionResponseDTO toDto(JobPositionEntity entity);

    @Mapping(target = "maps", ignore = true) // Nếu DTO không có thông tin mapping, bỏ qua mapping này
    JobPositionEntity toEntity(JobPositionRequestDTO dto);
}
