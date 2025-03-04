package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import org.springframework.data.domain.Page;

public interface JobPositionService {
    JobPositionResponseDTO addJobPosition(JobPositionRequestDTO dto);
    Page<JobPositionResponseDTO> getAllJobPositions(int page, int size, String sort, String search);
    JobPositionResponseDTO updateJobPosition(Long id, JobPositionRequestDTO dto);
    void deleteJobPosition(Long id);
    JobPositionResponseDTO getJobPositionById(Long id);
}
