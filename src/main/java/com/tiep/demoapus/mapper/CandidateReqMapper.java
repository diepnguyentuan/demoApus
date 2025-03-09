package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.CandidateReqRequestDTO;
import com.tiep.demoapus.dto.response.CandidateReqResponseDTO;
import com.tiep.demoapus.entity.CandidateReqEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateReqMapper {
    CandidateReqResponseDTO toDto(CandidateReqEntity entity);
    CandidateReqEntity toEntity(CandidateReqRequestDTO dto);
}
