package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.SourceRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.SourceResponseDTO;
import com.tiep.demoapus.entity.SourceEntity;

public interface SourceService {
    PageableResponse<SourceResponseDTO> findAllSource(int page, int size, String sort, String search);
    SourceResponseDTO findSourceById(Long id);
    SourceResponseDTO addSource(SourceRequestDTO source);
    SourceResponseDTO updateSource(SourceRequestDTO source);
    void deleteSourceById(Long id);
}
