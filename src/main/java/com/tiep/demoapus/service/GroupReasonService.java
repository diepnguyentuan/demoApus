package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.response.GroupReasonResponseDTO;
import com.tiep.demoapus.dto.request.GroupReasonRequestDTO;
import org.springframework.data.domain.Page;

public interface GroupReasonService {
    Page<GroupReasonResponseDTO> getAllGroupReasons(int page, int size, String sort, String search);
    GroupReasonResponseDTO getGroupReasonById(Long id);
    GroupReasonResponseDTO addGroupReason(GroupReasonRequestDTO dto);
    GroupReasonResponseDTO updateGroupReason(Long id, GroupReasonRequestDTO dto);
    void deleteGroupReason(Long id);
}
