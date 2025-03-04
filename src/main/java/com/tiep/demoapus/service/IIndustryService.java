package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.response.IndustryResponseDTO;
import com.tiep.demoapus.dto.request.IndustryRequestDTO;
import org.springframework.data.domain.Page;

public interface IIndustryService {
    Page<IndustryResponseDTO> getIndustries(int page, int size, String sort, String search);
    IndustryResponseDTO getIndustryById(Long id);
    IndustryResponseDTO addIndustry(IndustryRequestDTO dto);
    IndustryResponseDTO updateIndustry(Long id, IndustryRequestDTO dto);
    void deleteIndustry(Long id);
}
