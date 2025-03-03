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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPositionServiceImpl implements JobPositionService {
    private final JobPositionRepository jobPositionRepository;
    private final IndustryRepository industryRepository;
    private final JobPositionMapRepository jobPositionMapRepository;

    @Autowired
    public JobPositionServiceImpl(
            JobPositionRepository jobPositionRepository,
            IndustryRepository industryRepository,
            JobPositionMapRepository jobPositionMapRepository) {
        this.jobPositionRepository = jobPositionRepository;
        this.industryRepository = industryRepository;
        this.jobPositionMapRepository = jobPositionMapRepository;
    }

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

        jobPosition = jobPositionRepository.save(jobPosition);

        // Tạo các bản ghi trong JobPositionMap
        if (requestDTO.getDepartmentPositions() != null) {
            for (DepartmentPositionDTO departmentPosition : requestDTO.getDepartmentPositions()) {
                Long departmentId = departmentPosition.getDepartmentId();
                for (Long positionId : departmentPosition.getPositionIds()) {
                    JobPositionMap jobPositionMap = new JobPositionMap();
                    jobPositionMap.setJobPosition(jobPosition);
                    jobPositionMap.setDepartmentId(departmentId);
                    jobPositionMap.setPositionId(positionId);
                    jobPositionMapRepository.save(jobPositionMap);
                }
            }
        }

        return JobPositionMapper.toDTO(jobPosition);
    }
    @Override
    public List<JobPositionResponseDTO> getAllJobPositions() {
        return jobPositionRepository.findAll()
                .stream().map(JobPositionMapper::toDTO)
                .collect(Collectors.toList());
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

        jobPosition = jobPositionRepository.save(jobPosition);

        // Xóa tất cả các bản ghi JobPositionMap cũ liên quan đến JobPosition này
        jobPositionMapRepository.deleteByJobPositionId(id);

        // Thêm các bản ghi JobPositionMap mới
        if (requestDTO.getDepartmentPositions() != null) {
            for (DepartmentPositionDTO departmentPosition : requestDTO.getDepartmentPositions()) {
                Long departmentId = departmentPosition.getDepartmentId();
                for (Long positionId : departmentPosition.getPositionIds()) {
                    JobPositionMap jobPositionMap = new JobPositionMap();
                    jobPositionMap.setJobPosition(jobPosition);
                    jobPositionMap.setDepartmentId(departmentId);
                    jobPositionMap.setPositionId(positionId);
                    jobPositionMapRepository.save(jobPositionMap);
                }
            }
        }

        return JobPositionMapper.toDTO(jobPosition);
    }

    @Override
    @Transactional
    public void deleteJobPosition(Long id) {
        if (!jobPositionRepository.existsById(id)) {
            throw new RuntimeException("JobPosition not found");
        }

        // Xóa tất cả các bản ghi JobPositionMap liên quan
        jobPositionMapRepository.deleteByJobPositionId(id);

        // Xóa JobPosition
        jobPositionRepository.deleteById(id);
    }

    @Override
    public JobPositionResponseDTO getJobPositionById(Long id) {
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobPosition not found"));

        return JobPositionMapper.toDTO(jobPosition);
    }
}
