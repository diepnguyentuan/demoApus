package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.BenefitRequestDTO;
import com.tiep.demoapus.dto.response.BenefitResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;

public interface BenefitService {
    PageableResponse<BenefitResponseDTO> getBenefits(int page, int size, String sort, String search);
    BenefitResponseDTO getBenefitById(Long id);
    BenefitResponseDTO addBenefit(BenefitRequestDTO benefit);
    BenefitResponseDTO updateBenefit(BenefitRequestDTO benefit);
    void deleteBenefit(Long id);
}
