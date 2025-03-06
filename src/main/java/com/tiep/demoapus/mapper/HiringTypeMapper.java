package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.HiringTypeRequestDTO;
import com.tiep.demoapus.dto.response.HiringTypeResponseDTO;
import com.tiep.demoapus.entity.HiringTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HiringTypeMapper {
    HiringTypeResponseDTO toDto(HiringTypeEntity entity);
    HiringTypeEntity toEntity(HiringTypeRequestDTO dto);
}
