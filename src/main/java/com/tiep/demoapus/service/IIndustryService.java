package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.IndustryRequestDTO;
import com.tiep.demoapus.dto.response.IndustryResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;

public interface IIndustryService {
    PageableResponse<IndustryResponseDTO> getAllIndustries(int page, int size, String sort, String search);
    IndustryResponseDTO getIndustryById(Long id);
    IndustryResponseDTO addIndustry(IndustryRequestDTO dto);
    IndustryResponseDTO updateIndustry(IndustryRequestDTO dto);
    void deleteIndustry(Long id);
    boolean existsById(Long id);
}
