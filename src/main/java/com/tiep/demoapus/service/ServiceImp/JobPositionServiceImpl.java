package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.JobPositionResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.entity.JobPositionEntity;
import com.tiep.demoapus.entity.JobPositionMapEntity;
import com.tiep.demoapus.mapper.JobPositionMapper;
import com.tiep.demoapus.repository.IndustryRepository;
import com.tiep.demoapus.repository.JobPositionMapRepository;
import com.tiep.demoapus.repository.JobPositionRepository;
import com.tiep.demoapus.service.JobPositionService;
import com.tiep.demoapus.specification.JobPositionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

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
    public JobPositionResponseDTO addJobPosition(JobPositionRequestDTO dto) {
        // Lấy Industry và validate
        var industryEntity = industryRepository.findById(dto.getIndustryId())
                .orElseThrow(() -> new RuntimeException("Industry not found"));

        JobPositionEntity jobPositionEntity = new JobPositionEntity();
        jobPositionEntity.setCode(dto.getCode());
        jobPositionEntity.setName(dto.getName());
        jobPositionEntity.setDescription(dto.getDescription());
        jobPositionEntity.setActive(dto.getActive());
        jobPositionEntity.setIndustryEntity(industryEntity);
        jobPositionEntity.setCreatedAt(LocalDateTime.now());
        jobPositionEntity.setUpdatedAt(LocalDateTime.now());

        jobPositionEntity = jobPositionRepository.save(jobPositionEntity);

        if (dto.getDepartmentPositions() != null) {
            JobPositionEntity finalJobPositionEntity = jobPositionEntity;
            dto.getDepartmentPositions().forEach(dp -> {
                if (dp.getPositionIds() != null) {
                    dp.getPositionIds().forEach(positionId -> {
                        var mapEntity = new JobPositionMapEntity();
                        mapEntity.setJobPosition(finalJobPositionEntity);
                        mapEntity.setDepartmentId(dp.getDepartmentId());
                        mapEntity.setPositionId(positionId);
                        jobPositionMapRepository.save(mapEntity);
                    });
                }
            });
        }

        List<JobPositionMapEntity> maps = jobPositionMapRepository.findByJobPositionId(jobPositionEntity.getId());
        return jobPositionMapper.toDTO(jobPositionEntity, maps);
    }

    @Override
    public PageableResponse<JobPositionResponseDTO> getAllJobPositions(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith(":DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<JobPositionEntity> pageData = jobPositionRepository.findAll(JobPositionSpecification.searchByCodeOrName(search), pageable);
        Page<JobPositionResponseDTO> dtoPage = pageData.map(jobPositionEntity -> {
            List<JobPositionMapEntity> maps = jobPositionMapRepository.findByJobPositionId(jobPositionEntity.getId());
            return jobPositionMapper.toDTO(jobPositionEntity, maps);
        });
        return PageableResponse.<JobPositionResponseDTO>builder()
                .content(dtoPage.getContent())
                .page(dtoPage.getNumber())
                .size(dtoPage.getSize())
                .sort(sort)
                .totalElements(dtoPage.getTotalElements())
                .totalPages(dtoPage.getTotalPages())
                .numberOfElements(dtoPage.getNumberOfElements())
                .build();
    }

    @Override
    @Transactional
    public JobPositionResponseDTO updateJobPosition(Long id, JobPositionRequestDTO dto) {
        JobPositionEntity jobPositionEntity = jobPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobPosition not found"));
        var industryEntity = industryRepository.findById(dto.getIndustryId())
                .orElseThrow(() -> new RuntimeException("Industry not found"));
        jobPositionEntity.setCode(dto.getCode());
        jobPositionEntity.setName(dto.getName());
        jobPositionEntity.setDescription(dto.getDescription());
        jobPositionEntity.setActive(dto.getActive());
        jobPositionEntity.setIndustryEntity(industryEntity);
        jobPositionEntity.setUpdatedAt(LocalDateTime.now());
        jobPositionEntity = jobPositionRepository.save(jobPositionEntity);

        jobPositionMapRepository.deleteByJobPositionId(id);
        if (dto.getDepartmentPositions() != null) {
            JobPositionEntity finalJobPositionEntity = jobPositionEntity;
            dto.getDepartmentPositions().forEach(dp -> {
                if (dp.getPositionIds() != null) {
                    dp.getPositionIds().forEach(positionId -> {
                        var mapEntity = new JobPositionMapEntity();
                        mapEntity.setJobPosition(finalJobPositionEntity);
                        mapEntity.setDepartmentId(dp.getDepartmentId());
                        mapEntity.setPositionId(positionId);
                        jobPositionMapRepository.save(mapEntity);
                    });
                }
            });
        }
        List<JobPositionMapEntity> maps = jobPositionMapRepository.findByJobPositionId(jobPositionEntity.getId());
        return jobPositionMapper.toDTO(jobPositionEntity, maps);
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
        JobPositionEntity jobPositionEntity = jobPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobPosition not found"));
        List<JobPositionMapEntity> maps = jobPositionMapRepository.findByJobPositionId(jobPositionEntity.getId());
        return jobPositionMapper.toDTO(jobPositionEntity, maps);
    }
}
