package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.GroupReasonRequestDTO;
import com.tiep.demoapus.dto.response.GroupReasonResponseDTO;
import com.tiep.demoapus.entity.GroupReason;

public class GroupReasonMapper {

    //Chuyen tu Entity sang responseDTO
    public static GroupReasonResponseDTO toDTO(GroupReason groupReason){
        if(groupReason == null){ return null; }
        GroupReasonResponseDTO dto = new GroupReasonResponseDTO();
        dto.setId(groupReason.getId());
        dto.setCode(groupReason.getCode());
        dto.setName(groupReason.getName());
        dto.setDescription(groupReason.getDescription());
        dto.setIsActive(groupReason.getActive());
        return dto;
    }

    //Chuyen tu RequestDTO sang entity
    public static GroupReason toEntity(GroupReasonRequestDTO dto){
        if(dto == null){ return null; }
        GroupReason entity = new GroupReason();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setActive(dto.getIsActive());
        return entity;
    }
}
