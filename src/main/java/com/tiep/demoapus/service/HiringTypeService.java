package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.HiringTypeRequestDTO;
import com.tiep.demoapus.dto.response.HiringTypeResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;

public interface HiringTypeService {
    PageableResponse<HiringTypeResponseDTO> getAllHiringTypes(int page, int size, String sort, String search);
    HiringTypeResponseDTO getHiringTypeById(Long id);
    HiringTypeResponseDTO addHiringType(HiringTypeRequestDTO hiringTypeRequestDTO);
    HiringTypeResponseDTO updateHiringType(HiringTypeRequestDTO hiringTypeRequestDTO);
    void deleteHiringType(Long id);
}
