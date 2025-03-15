package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.*;
import com.tiep.demoapus.entity.JobPositionEntity;
import com.tiep.demoapus.entity.JobPositionMapEntity;
import com.tiep.demoapus.exception.EntityNotFoundException;
import com.tiep.demoapus.feignClient.ApiResponse;
import com.tiep.demoapus.feignClient.DepartmentClientForJobPosition;
import com.tiep.demoapus.feignClient.PositionClient;
import com.tiep.demoapus.mapper.JobPositionMapMapper;
import com.tiep.demoapus.mapper.JobPositionMapper;
import com.tiep.demoapus.repository.IndustryRepository;
import com.tiep.demoapus.repository.JobPositionMapRepository;
import com.tiep.demoapus.repository.JobPositionRepository;
import com.tiep.demoapus.service.JobPositionService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * Thêm mới Job Position
     */
    @Override
    @Transactional
    public JobPositionResponseDTO addJobPosition(JobPositionRequestDTO dto) {
        // Lấy Industry từ DB dựa vào id từ DTO
        var industry = industryRepository.findById(dto.getIndustryJob().getId())
                .orElseThrow(() -> new EntityNotFoundException("Industry not found"));

        // Chuyển đổi DTO sang Entity và gán Industry cho Entity
        JobPositionEntity jobPosition = jobPositionMapper.toEntity(dto);
        jobPosition.setIndustryEntity(industry);
        // Lưu JobPosition vào DB
        jobPosition = jobPositionRepository.save(jobPosition);

        // Nếu có thông tin mapping giữa department và positions, lưu mapping đó
        if (dto.getDepartmentPositions() != null && !dto.getDepartmentPositions().isEmpty()) {
            List<JobPositionMapEntity> maps = buildMapEntities(jobPosition, dto);
            jobPositionMapRepository.saveAll(maps);
        }
        return new JobPositionResponseDTO(jobPosition.getId());
    }

    /**
     * Lấy danh sách Job Positions với phân trang và sắp xếp
     */
    @Override
    public PageableResponse<JobPositionResponseDTO> getAllJobPositions(int page, int size, String sort, String search) {
        // Nếu sort kết thúc bằng ":DESC", dùng sắp xếp giảm dần, ngược lại tăng dần
        Sort.Direction direction = sort.endsWith(":DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest request = PageRequest.of(page, size, Sort.by(direction, sortBy));

        // Tìm kiếm Job Position theo code hoặc name nếu search không rỗng
        Page<JobPositionEntity> entities = jobPositionRepository.findAll(
                GenericSpecification.searchByCodeOrName(search), request);
        Page<JobPositionResponseDTO> dtoPage = entities.map(jobPositionMapper::toDto);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    /**
     * Cập nhật thông tin Job Position
     */
    @Override
    @Transactional
    public JobPositionResponseDTO updateJobPosition(JobPositionRequestDTO dto) {
        // Lấy JobPosition cần update từ DB
        JobPositionEntity jobPosition = jobPositionRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("JobPosition not found"));

        // Lấy Industry mới từ DB theo id từ DTO
        var industry = industryRepository.findById(dto.getIndustryJob().getId())
                .orElseThrow(() -> new EntityNotFoundException("Industry not found"));

        // Cập nhật thông tin từ DTO sang entity (không update mapping)
        jobPositionMapper.updateEntityFromDto(dto, jobPosition);
        jobPosition.setIndustryEntity(industry);
        // Lưu lại thông tin đã update
        jobPosition = jobPositionRepository.save(jobPosition);

        // Xóa mapping cũ và thêm mapping mới nếu có thông tin từ DTO
        jobPositionMapRepository.deleteByJobPositionId(jobPosition.getId());
        if (dto.getDepartmentPositions() != null && !dto.getDepartmentPositions().isEmpty()) {
            List<JobPositionMapEntity> maps = buildMapEntities(jobPosition, dto);
            jobPositionMapRepository.saveAll(maps);
        }
        return new JobPositionResponseDTO(jobPosition.getId());
    }

    /**
     * Xóa Job Position và các mapping liên quan
     */
    @Override
    @Transactional
    public void deleteJobPosition(Long id) {
        if (!jobPositionRepository.existsById(id)) {
            throw new EntityNotFoundException("JobPosition not found");
        }
        // Xóa mapping trước khi xóa Job Position
        jobPositionMapRepository.deleteByJobPositionId(id);
        jobPositionRepository.deleteById(id);
    }

    /**
     * Lấy chi tiết Job Position theo id, bao gồm thông tin mapping của department và positions.
     */
    @Override
    @Transactional
    public JobPositionMapResponseDTO getJobPositionById(Long id) {
        JobPositionEntity jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job Position không tồn tại với id: " + id));

        // Chuyển entity sang DTO (bao gồm mapping lines)
        JobPositionMapResponseDTO dto = jobPositionMapMapper.toDtoWithLines(jobPosition);

        // Nếu có thông tin mapping, cập nhật chi tiết cho mỗi line
        if (dto.getLines() != null && !dto.getLines().isEmpty()) {
            for (JobPositionLineDTO line : dto.getLines()) {
                updateLineInfo(line);
            }
        }
        return dto;
    }

    /**
     * Helper method: Cập nhật chi tiết của 1 line bao gồm thông tin department và positions
     */
    private void updateLineInfo(JobPositionLineDTO line) {
        updateDepartmentInfo(line);
        updatePositionInfo(line);
    }

    /**
     * Gọi API để lấy thông tin chi tiết của Department và cập nhật vào line
     */
    private void updateDepartmentInfo(JobPositionLineDTO line) {
        Long deptId = line.getDepartment().getId();
        ApiResponse<DepartmentListResponse> deptResponse =
                departmentClient.getDepartmentsByIds(Collections.singletonList(deptId));
        if (deptResponse != null && deptResponse.getData() != null
                && !deptResponse.getData().getContent().isEmpty()) {
            line.setDepartment(deptResponse.getData().getContent().get(0));
        }
    }

    /**
     * Gọi API để lấy danh sách Position chi tiết và cập nhật vào line
     */
    private void updatePositionInfo(JobPositionLineDTO line) {
        List<Long> posIds = new ArrayList<>();
        for (PositionResponseDTO pos : line.getPosition()) {
            posIds.add(pos.getId());
        }
        ApiResponse<PositionListResponse> posResponse = positionClient.getPositionsByIds(posIds);
        if (posResponse != null && posResponse.getData() != null) {
            line.setPosition(posResponse.getData().getContent());
        } else {
            line.setPosition(new ArrayList<>());
        }
    }

    /**
     * Xây dựng danh sách mapping (JobPositionMapEntity) từ thông tin trong DTO
     */
    private List<JobPositionMapEntity> buildMapEntities(JobPositionEntity jobPosition, JobPositionRequestDTO dto) {
        List<JobPositionMapEntity> list = new ArrayList<>();
        if (dto.getDepartmentPositions() != null) {
            // Duyệt qua từng department mapping
            for (var dp : dto.getDepartmentPositions()) {
                if (dp.getPositions() != null) {
                    // Duyệt qua từng position trong department
                    for (var pos : dp.getPositions()) {
                        JobPositionMapEntity mapEntity = new JobPositionMapEntity();
                        mapEntity.setJobPosition(jobPosition);
                        mapEntity.setDepartmentId(dp.getDepartment().getId());
                        mapEntity.setPositionId(pos.getId());
                        list.add(mapEntity);
                    }
                }
            }
        }
        return list;
    }
}
