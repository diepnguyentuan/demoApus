package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.TagRequestDTO;
import com.tiep.demoapus.dto.response.TagResponseDTO;
import org.springframework.data.domain.Page;

public interface TagService {
    Page<TagResponseDTO> getAllTags(int page, int size, String sort, String search);
    TagResponseDTO getTagById(Long id);
    TagResponseDTO addTag(TagRequestDTO dto);
    TagResponseDTO updateTag(TagRequestDTO dto);
    void deleteTag(Long id);
    boolean existsById(Long id);
}
