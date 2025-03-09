package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.ReasonHiringRequestDTO;
import com.tiep.demoapus.dto.response.ReasonHiringResponseDTO;
import com.tiep.demoapus.entity.ReasonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReasonHiringMapper {
    ReasonHiringResponseDTO toDto(ReasonEntity reasonEntity);
    ReasonEntity toEntity(ReasonHiringRequestDTO dto);
}
