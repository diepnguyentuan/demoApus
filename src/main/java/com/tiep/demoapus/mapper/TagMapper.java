package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.TagRequestDTO;
import com.tiep.demoapus.dto.response.TagResponseDTO;
import com.tiep.demoapus.entity.Tag;

public class TagMapper {

    // Chuyển từ Entity sang ResponseDTO
    public static TagResponseDTO toDTO(Tag tag) {
        if (tag == null) return null;
        TagResponseDTO dto = new TagResponseDTO();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        dto.setIsActive(tag.getActive());
        return dto;
    }

    // Chuyển từ RequestDTO sang Entity
    public static Tag toEntity(TagRequestDTO dto) {
        if (dto == null) return null;
        Tag entity = new Tag();
        entity.setName(dto.getName());
        entity.setActive(dto.getIsActive());
        return entity;
    }
}
