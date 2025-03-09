package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.response.HiringTypeMinimalResponseDTO;
import com.tiep.demoapus.entity.HiringTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HiringTypeMinimalMapper {
    HiringTypeMinimalResponseDTO toDTO(HiringTypeEntity hiringTypeEntity);
}
