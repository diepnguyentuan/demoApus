package com.tiep.demoapus.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.tiep.demoapus.dto.response.JobPositionMapResponseDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.entity.JobPositionEntity;
import com.tiep.demoapus.entity.JobPositionMapEntity;

@Mapper(componentModel = "spring")
public interface JobPositionMapMapper {

    @Mapping(target = "jobPosition", expression = "java(shallowJobPosition(entity.getJobPosition()))")
    // Ignore department và position vì sẽ lấy lại từ external API trong service
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "position", ignore = true)
    JobPositionMapResponseDTO toDto(JobPositionMapEntity entity);

    // Phương thức hỗ trợ ánh xạ nông của JobPositionEntity
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
        // Set trường lines là null để tránh vòng lặp
        dto.setLines(null);
        return dto;
    }
}
