package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.ReasonRequestDTO;
import com.tiep.demoapus.dto.response.ReasonResponseDTO;
import com.tiep.demoapus.entity.ReasonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GroupReasonMapper.class})
public interface ReasonMapper {

    @Mapping(source = "groupReason", target = "group")
    ReasonResponseDTO toDTO(ReasonEntity reason);

    ReasonEntity toEntity(ReasonRequestDTO dto);
}
