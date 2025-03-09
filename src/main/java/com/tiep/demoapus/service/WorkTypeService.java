package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.WorkTypeRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.WorkTypeResponseDTO;

public interface WorkTypeService {
    PageableResponse<WorkTypeResponseDTO> getAllWorkTypes(int page, int size, String sort, String search);
    WorkTypeResponseDTO getWorkTypeById(Long id);
    WorkTypeResponseDTO addWorkType(WorkTypeRequestDTO workTypeRequestDTO);
    WorkTypeResponseDTO updateWorkType(WorkTypeRequestDTO workTypeRequestDTO);
    void deleteWorkTypeById(Long id);
}
