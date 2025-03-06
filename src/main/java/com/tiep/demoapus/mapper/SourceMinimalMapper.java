package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.response.SourceMinimalDTO;
import com.tiep.demoapus.entity.SourceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMinimalMapper {
    SourceMinimalDTO toMinimalDTO(SourceEntity source);
}
