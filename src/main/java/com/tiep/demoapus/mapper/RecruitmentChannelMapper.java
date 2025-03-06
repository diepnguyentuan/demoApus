package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.RecruitmentChannelRequestDTO;
import com.tiep.demoapus.dto.response.RecruitmentChannelResponseDTO;
import com.tiep.demoapus.entity.RecruitmentChannelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring", uses = {SourceMinimalMapper.class})
public interface RecruitmentChannelMapper {
    RecruitmentChannelResponseDTO toDTO(RecruitmentChannelEntity recruitmentChannel);

    RecruitmentChannelEntity toEntity(RecruitmentChannelRequestDTO dto);
}
