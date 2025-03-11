package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.response.DepartmentResponseDTO;
import com.tiep.demoapus.dto.response.JobPositionMapResponseDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.dto.response.PositionResponseDTO;
import com.tiep.demoapus.entity.JobPositionEntity;
import com.tiep.demoapus.entity.JobPositionMapEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobPositionMapMapper {

    @Mapping(target = "jobPosition", expression = "java(jobPositionEntityToDto(entity.getJobPosition()))")
    @Mapping(target = "department", expression = "java(new DepartmentResponseDTO(entity.getDepartmentId(), null))")
    @Mapping(target = "position",   expression = "java(new PositionResponseDTO(entity.getPositionId(), null))")
    JobPositionMapResponseDTO toDto(JobPositionMapEntity entity);

    default JobPositionResponseDTO jobPositionEntityToDto(JobPositionEntity entity) {
        if (entity == null) {
            return null;
        }
        JobPositionResponseDTO dto = new JobPositionResponseDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setActive(entity.getActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}
