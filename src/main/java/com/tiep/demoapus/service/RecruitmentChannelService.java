package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.RecruitmentChannelRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.RecruitmentChannelResponseDTO;

public interface RecruitmentChannelService {
    PageableResponse<RecruitmentChannelResponseDTO> getAllRecruitmentChannels(int page, int size, String sort, String search);
    RecruitmentChannelResponseDTO getRecruitmentChannel(int id);
    RecruitmentChannelResponseDTO addRecruitmentChannel(RecruitmentChannelRequestDTO requestDTO);
    RecruitmentChannelResponseDTO updateRecruitmentChannel(RecruitmentChannelRequestDTO requestDTO);
    void deleteRecruitmentChannel(Long id);
}
