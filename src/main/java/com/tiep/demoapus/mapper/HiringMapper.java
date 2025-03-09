package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.HiringRequestDTO;
import com.tiep.demoapus.dto.response.HiringResponseDTO;
import com.tiep.demoapus.entity.HiringEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {HiringTypeMinimalMapper.class})
public interface HiringMapper {
    HiringResponseDTO toDTO(HiringEntity hiringEntity);
    HiringEntity toEntity(HiringRequestDTO dto);
}
