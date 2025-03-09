package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.ExperienceRequirementRequestDTO;
import com.tiep.demoapus.dto.response.ExperienceRequirementResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;

public interface ExperienceRequirementService {
    PageableResponse<ExperienceRequirementResponseDTO> getAllExRequirement(int page, int size, String sort, String search);
    ExperienceRequirementResponseDTO geExperienceRequirementResponseDto(Long id);
    ExperienceRequirementResponseDTO addExperienceRequirementResponseDto(ExperienceRequirementRequestDTO dto);
    ExperienceRequirementResponseDTO updateExperienceRequirementResponseDto(ExperienceRequirementRequestDTO dto);
    void deleteExRequirement(Long id);
}
