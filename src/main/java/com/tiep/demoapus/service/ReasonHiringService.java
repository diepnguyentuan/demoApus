package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.ReasonHiringRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ReasonHiringResponseDTO;

public interface ReasonHiringService {
    PageableResponse<ReasonHiringResponseDTO> getAllReasonHirings(int page, int size, String sort, String search);
    ReasonHiringResponseDTO getReasonHiringById(Long id);
    ReasonHiringResponseDTO addReasonHiring(ReasonHiringRequestDTO dto);
    ReasonHiringResponseDTO updateReasonHiring(ReasonHiringRequestDTO dto);
    void deleteReasonHiringById(Long id);
}
