package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.TagRequestDTO;
import com.tiep.demoapus.dto.response.TagResponseDTO;
import com.tiep.demoapus.entity.TagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagResponseDTO toDTO(TagEntity tag);
    TagEntity toEntity(TagRequestDTO dto);
}
