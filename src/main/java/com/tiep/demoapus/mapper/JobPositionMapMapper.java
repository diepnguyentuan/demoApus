package com.tiep.demoapus.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.tiep.demoapus.dto.response.JobPositionMapResponseDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.dto.response.DepartmentResponseDTO;
import com.tiep.demoapus.dto.response.PositionResponseDTO;
import com.tiep.demoapus.entity.JobPositionEntity;
import com.tiep.demoapus.entity.JobPositionMapEntity;

@Mapper(componentModel = "spring")
public interface JobPositionMapMapper {

    @Mapping(target = "jobPosition", expression = "java(shallowJobPosition(entity.getJobPosition()))")
    @Mapping(target = "department", expression = "java(new com.tiep.demoapus.dto.response.DepartmentResponseDTO(entity.getDepartmentId(), null))")
    @Mapping(target = "position", expression = "java(new com.tiep.demoapus.dto.response.PositionResponseDTO(entity.getPositionId(), null))")
    JobPositionMapResponseDTO toDto(JobPositionMapEntity entity);

    // Phương thức mapping nông: chỉ map các trường cơ bản của JobPositionEntity,
    // bỏ qua việc ánh xạ danh sách jobPositionMaps để phá vỡ vòng lặp.
    default JobPositionResponseDTO shallowJobPosition(JobPositionEntity entity) {
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
        // Phá vỡ vòng lặp: không map trường jobPositionMaps
        dto.setJobPositionMaps(null);
        return dto;
    }
}
