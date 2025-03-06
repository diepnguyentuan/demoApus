package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.ReasonRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ReasonResponseDTO;

public interface ReasonService {
    PageableResponse<ReasonResponseDTO> getAllReasons(int page, int size, String sort, String search);
    ReasonResponseDTO getReasonById(Long id);
    ReasonResponseDTO addReason(ReasonRequestDTO reason);
    ReasonResponseDTO updateReason(ReasonRequestDTO reason);
    void deleteReason(Long id);
}
