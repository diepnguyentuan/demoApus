package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.HiringRequestDTO;
import com.tiep.demoapus.dto.response.HiringResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;

public interface HiringService {
    PageableResponse<HiringResponseDTO> getAllHirings(int page, int size, String sort, String search);
    HiringResponseDTO getHiringById(Long id);
    HiringResponseDTO addHiring(HiringRequestDTO hiringRequestDTO);
    HiringResponseDTO updateHiring(HiringRequestDTO hiringRequestDTO);
    void deleteHiringById(Long id);
}
