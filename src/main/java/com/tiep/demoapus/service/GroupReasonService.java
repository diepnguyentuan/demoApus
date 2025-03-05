package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.GroupReasonRequestDTO;
import com.tiep.demoapus.dto.response.GroupReasonResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;

public interface GroupReasonService {
    PageableResponse<GroupReasonResponseDTO> getAllGroupReasons(int page, int size, String sort, String search);
    GroupReasonResponseDTO getGroupReasonById(Long id);
    GroupReasonResponseDTO addGroupReason(GroupReasonRequestDTO dto);
    GroupReasonResponseDTO updateGroupReason(GroupReasonRequestDTO dto);
    void deleteGroupReason(Long id);
}
