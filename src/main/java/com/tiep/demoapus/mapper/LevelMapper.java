package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.LevelRequestDTO;
import com.tiep.demoapus.dto.response.LevelResponseDTO;
import com.tiep.demoapus.entity.LevelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LevelMapper {
    LevelResponseDTO toDto(LevelEntity entity);
    LevelEntity toEntity(LevelRequestDTO dto);
}
