package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.*;
import com.tiep.demoapus.entity.JobPositionEntity;
import com.tiep.demoapus.entity.JobPositionMapEntity;
import com.tiep.demoapus.exception.EntityNotFoundException;
import com.tiep.demoapus.feignClient.ApiResponse;
import com.tiep.demoapus.feignClient.DepartmentClientForJobPosition;
import com.tiep.demoapus.mapper.JobPositionMapMapper;
import com.tiep.demoapus.mapper.JobPositionMapper;
import com.tiep.demoapus.repository.IndustryRepository;
import com.tiep.demoapus.repository.JobPositionMapRepository;
import com.tiep.demoapus.repository.JobPositionRepository;
import com.tiep.demoapus.service.JobPositionService;
import com.tiep.demoapus.feignClient.PositionClient;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JobPositionServiceImpl implements JobPositionService {

    private final IndustryRepository industryRepository;
    private final JobPositionRepository jobPositionRepository;
    private final JobPositionMapRepository jobPositionMapRepository;
    private final JobPositionMapper jobPositionMapper;
    private final JobPositionMapMapper jobPositionMapMapper;
    private final DepartmentClientForJobPosition departmentClient;
    private final PositionClient positionClient;

    @Override
    @Transactional
    public JobPositionResponseDTO addJobPosition(JobPositionRequestDTO dto) {
        var industry = industryRepository.findById(dto.getIndustryJob().getId())
                .orElseThrow(() -> new EntityNotFoundException("Industry not found"));

        var entity = jobPositionMapper.toEntity(dto);
        entity.setIndustryEntity(industry);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity = jobPositionRepository.save(entity);

        if (dto.getDepartmentPositions() != null) {
            jobPositionMapRepository.saveAll(buildMapEntities(entity, dto));
        }
        return new JobPositionResponseDTO(entity.getId());
    }

    @Override
    public PageableResponse<JobPositionResponseDTO> getAllJobPositions(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith(":DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<JobPositionEntity> pageData = jobPositionRepository.findAll(
                GenericSpecification.searchByCodeOrName(search), pageRequest);
        Page<JobPositionResponseDTO> dtoPage = pageData.map(jobPositionMapper::toDto);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    @Override
    @Transactional
    public JobPositionResponseDTO updateJobPosition(JobPositionRequestDTO dto) {
        var entity = jobPositionRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("JobPosition not found"));

        var industry = industryRepository.findById(dto.getIndustryJob().getId())
                .orElseThrow(() -> new EntityNotFoundException("Industry not found"));

        // Sử dụng mapper để cập nhật các trường từ DTO sang entity
        jobPositionMapper.updateEntityFromDto(dto, entity);
        entity.setIndustryEntity(industry);
        entity.setUpdatedAt(LocalDateTime.now());
        entity = jobPositionRepository.save(entity);

        if (dto.getDepartmentPositions() != null) {
            jobPositionMapRepository.saveAll(buildMapEntities(entity, dto));
        }
        return new JobPositionResponseDTO(entity.getId());
    }

    @Override
    @Transactional
    public void deleteJobPosition(Long id) {
        if (!jobPositionRepository.existsById(id)) {
            throw new EntityNotFoundException("JobPosition not found");
        }
        jobPositionMapRepository.deleteByJobPositionId(id);
        jobPositionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public JobPositionMapResponseDTO getJobPositionById(Long id) {
        JobPositionEntity entity = jobPositionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job Position không tồn tại với id: " + id));

        JobPositionMapResponseDTO dto = jobPositionMapMapper.toDtoWithLines(entity);
        if (dto.getLines() != null && !dto.getLines().isEmpty()) {
            for (JobPositionLineDTO line : dto.getLines()) {
                Long deptId = line.getDepartment().getId();
                ApiResponse<DepartmentListResponse> deptResponse =
                        departmentClient.getDepartmentsByIds(Collections.singletonList(deptId));
                if (deptResponse != null && deptResponse.getData() != null
                        && !deptResponse.getData().getContent().isEmpty()) {
                    DepartmentResponseDTO fullDept = deptResponse.getData().getContent().get(0);
                    line.setDepartment(fullDept);
                }
                List<Long> posIds = line.getPosition().stream()
                        .map(PositionResponseDTO::getId)
                        .toList();
                ApiResponse<PositionListResponse> posResponse = positionClient.getPositionsByIds(posIds);
                if (posResponse != null && posResponse.getData() != null) {
                    line.setPosition(posResponse.getData().getContent());
                } else {
                    line.setPosition(Collections.emptyList());
                }
            }
        }
        return dto;
    }

    private List<JobPositionMapEntity> buildMapEntities(JobPositionEntity entity, JobPositionRequestDTO dto) {
        List<JobPositionMapEntity> mapEntities = new ArrayList<>();
        dto.getDepartmentPositions().forEach(dp -> {
            if (dp.getPositions() != null) {
                dp.getPositions().forEach(pos -> {
                    JobPositionMapEntity mapEntity = new JobPositionMapEntity();
                    mapEntity.setJobPosition(entity);
                    mapEntity.setDepartmentId(dp.getDepartment().getId());
                    mapEntity.setPositionId(pos.getId());
                    mapEntities.add(mapEntity);
                });
            }
        });
        return mapEntities;
    }
}
