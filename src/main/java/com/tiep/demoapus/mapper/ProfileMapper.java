package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.ProfileRequestDTO;
import com.tiep.demoapus.dto.response.ProfileResponseDTO;
import com.tiep.demoapus.entity.ProfileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileResponseDTO toDto(ProfileEntity profile);
    ProfileEntity toEntity(ProfileRequestDTO dto);
}
