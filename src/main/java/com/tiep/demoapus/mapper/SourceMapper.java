package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.SourceRequestDTO;
import com.tiep.demoapus.dto.response.SourceResponseDTO;
import com.tiep.demoapus.entity.SourceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper {
    SourceResponseDTO toDto(SourceEntity source);
    SourceEntity toEntity(SourceRequestDTO source);
}
