package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.DepartmentPositionDTO;
import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.entity.Industry;
import com.tiep.demoapus.entity.JobPosition;
import com.tiep.demoapus.entity.JobPositionMap;
import com.tiep.demoapus.mapper.JobPositionMapper;
import com.tiep.demoapus.repository.IndustryRepository;
import com.tiep.demoapus.repository.JobPositionMapRepository;
import com.tiep.demoapus.repository.JobPositionRepository;
import com.tiep.demoapus.service.JobPositionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPositionServiceImpl implements JobPositionService {

    private final JobPositionRepository jobPositionRepository;
    private final IndustryRepository industryRepository;
    private final JobPositionMapRepository jobPositionMapRepository;
    private final JobPositionMapper jobPositionMapper;

    @Override
    @Transactional
    public JobPositionResponseDTO addJobPosition(JobPositionRequestDTO requestDTO) {
        Industry industry = industryRepository.findById(requestDTO.getIndustryId())
                .orElseThrow(() -> new RuntimeException("Industry not found"));

        JobPosition jobPosition = new JobPosition();
        jobPosition.setCode(requestDTO.getCode());
        jobPosition.setName(requestDTO.getName());
        jobPosition.setDescription(requestDTO.getDescription());
        jobPosition.setActive(requestDTO.isActive());
        jobPosition.setIndustry(industry);
        jobPosition.setCreatedAt(LocalDateTime.now());
        jobPosition.setUpdatedAt(LocalDateTime.now());

        jobPosition = jobPositionRepository.save(jobPosition);

        if (requestDTO.getDepartmentPositions() != null) {
            for (DepartmentPositionDTO departmentPosition : requestDTO.getDepartmentPositions()) {
                Long departmentId = departmentPosition.getDepartmentId();
                if (departmentPosition.getPositionIds() != null) {
                    for (Long positionId : departmentPosition.getPositionIds()) {
                        JobPositionMap jobPositionMap = new JobPositionMap();
                        jobPositionMap.setJobPosition(jobPosition);
                        jobPositionMap.setDepartmentId(departmentId);
                        jobPositionMap.setPositionId(positionId);
                        jobPositionMapRepository.save(jobPositionMap);
                    }
                }
            }
        }

        List<JobPositionMap> lines = jobPositionMapRepository.findByJobPositionId(jobPosition.getId());
        return jobPositionMapper.toDTO(jobPosition, lines);
    }

    @Override
    public List<JobPositionResponseDTO> getAllJobPositions() {
        return jobPositionRepository.findAll()
                .stream()
                .map(job -> {
                    List<JobPositionMap> lines = jobPositionMapRepository.findByJobPositionId(job.getId());
                    return jobPositionMapper.toDTO(job, lines);
                })
                .toList();
    }

    @Override
    @Transactional
    public JobPositionResponseDTO updateJobPosition(Long id, JobPositionRequestDTO requestDTO) {
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobPosition not found"));

        Industry industry = industryRepository.findById(requestDTO.getIndustryId())
                .orElseThrow(() -> new RuntimeException("Industry not found"));

        jobPosition.setCode(requestDTO.getCode());
        jobPosition.setName(requestDTO.getName());
        jobPosition.setDescription(requestDTO.getDescription());
        jobPosition.setActive(requestDTO.isActive());
        jobPosition.setIndustry(industry);
        jobPosition.setUpdatedAt(LocalDateTime.now());

        jobPosition = jobPositionRepository.save(jobPosition);

        jobPositionMapRepository.deleteByJobPositionId(id);

        if (requestDTO.getDepartmentPositions() != null) {
            for (DepartmentPositionDTO departmentPosition : requestDTO.getDepartmentPositions()) {
                Long departmentId = departmentPosition.getDepartmentId();
                if (departmentPosition.getPositionIds() != null) {
                    for (Long positionId : departmentPosition.getPositionIds()) {
                        JobPositionMap jobPositionMap = new JobPositionMap();
                        jobPositionMap.setJobPosition(jobPosition);
                        jobPositionMap.setDepartmentId(departmentId);
                        jobPositionMap.setPositionId(positionId);
                        jobPositionMapRepository.save(jobPositionMap);
                    }
                }
            }
        }

        List<JobPositionMap> lines = jobPositionMapRepository.findByJobPositionId(jobPosition.getId());
        return jobPositionMapper.toDTO(jobPosition, lines);
    }

    @Override
    @Transactional
    public void deleteJobPosition(Long id) {
        if (!jobPositionRepository.existsById(id)) {
            throw new RuntimeException("JobPosition not found");
        }
        jobPositionMapRepository.deleteByJobPositionId(id);
        jobPositionRepository.deleteById(id);
    }

    @Override
    public JobPositionResponseDTO getJobPositionById(Long id) {
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobPosition not found"));
        List<JobPositionMap> lines = jobPositionMapRepository.findByJobPositionId(jobPosition.getId());
        return jobPositionMapper.toDTO(jobPosition, lines);
    }
}
