package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import java.util.List;

public interface JobPositionService {
    JobPositionResponseDTO addJobPosition(JobPositionRequestDTO dto);
    List<JobPositionResponseDTO> getAllJobPositions();
    JobPositionResponseDTO updateJobPosition(Long id, JobPositionRequestDTO requestDTO);
    void deleteJobPosition(Long id);
    JobPositionResponseDTO getJobPositionById(Long id);
}
