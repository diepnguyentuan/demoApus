package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.WorkTypeRequestDTO;
import com.tiep.demoapus.dto.response.WorkTypeResponseDTO;
import com.tiep.demoapus.entity.WorkTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkTypeMapper {
    WorkTypeResponseDTO toDto(WorkTypeEntity entity);
    WorkTypeEntity toEntity(WorkTypeRequestDTO dto);
}
