package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.LevelRequestDTO;
import com.tiep.demoapus.dto.response.LevelResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.entity.LevelEntity;

public interface LevelService {
    PageableResponse<LevelResponseDTO> getAllLevels(int page, int size, String sort, String search);
    LevelResponseDTO getLevelById(Long id);
    LevelResponseDTO addLevel(LevelRequestDTO requestDTO);
    LevelResponseDTO updateLevel(LevelRequestDTO requestDTO);
    void deleteLevelById(Long id);
}
