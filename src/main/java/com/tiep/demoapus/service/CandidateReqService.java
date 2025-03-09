package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.CandidateReqRequestDTO;
import com.tiep.demoapus.dto.response.CandidateReqResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;

public interface CandidateReqService {
    PageableResponse<CandidateReqResponseDTO> getAllCandidateReq(int page, int size, String sort, String search);
    CandidateReqResponseDTO getCandidateReqById(Long id);
    CandidateReqResponseDTO addCandidateReq(CandidateReqRequestDTO dto);
    CandidateReqResponseDTO updateCandidateReq(CandidateReqRequestDTO dto);
    void deleteCandidateReq(Long id);
}
