package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.GroupReasonRequestDTO;
import com.tiep.demoapus.dto.response.GroupReasonResponseDTO;
import com.tiep.demoapus.entity.GroupReasonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupReasonMapper {
    GroupReasonResponseDTO toDTO(GroupReasonEntity groupReasonEntity);
    GroupReasonEntity toEntity(GroupReasonRequestDTO dto);
}
