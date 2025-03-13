package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.JobPositionRequestDTO;
import com.tiep.demoapus.dto.response.*;
import com.tiep.demoapus.entity.IndustryEntity;
import com.tiep.demoapus.entity.JobPositionEntity;
import com.tiep.demoapus.entity.JobPositionMapEntity;
import com.tiep.demoapus.mapper.JobPositionMapMapper;
import com.tiep.demoapus.mapper.JobPositionMapper;
import com.tiep.demoapus.repository.IndustryRepository;
import com.tiep.demoapus.repository.JobPositionMapRepository;
import com.tiep.demoapus.repository.JobPositionRepository;
import com.tiep.demoapus.service.DepartmentClient;
import com.tiep.demoapus.service.JobPositionService;
import com.tiep.demoapus.service.PositionClient;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobPositionServiceImpl implements JobPositionService {

    private final IndustryRepository industryRepository;

    private final JobPositionRepository jobPositionRepository;

    private final JobPositionMapRepository jobPositionMapRepository;

    private final JobPositionMapper jobPositionMapper;

    private final JobPositionMapMapper jobPositionMapMapper;

    private final DepartmentClient departmentClient;
    private final PositionClient positionClient;

    @Override
    @Transactional
    public JobPositionResponseDTO addJobPosition(JobPositionRequestDTO dto) {
        var industry = industryRepository.findById(dto.getIndustryJob().getId())
                .orElseThrow(() -> new RuntimeException("Industry not found"));

        var entity = jobPositionMapper.toEntity(dto);
        entity.setIndustryEntity(industry);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity = jobPositionRepository.save(entity);

        if (dto.getDepartmentPositions() != null) {
            jobPositionMapRepository.saveAll(buildMapEntities(entity, dto));
        }

        // Chỉ trả về DTO chứa ID (nếu muốn đầy đủ, hãy mapper)
        return new JobPositionResponseDTO(entity.getId());
    }

    @Override
    public PageableResponse<JobPositionResponseDTO> getAllJobPositions(int page, int size, String sort, String search) {
        // Xác định hướng và trường sắp xếp từ chuỗi sort (ví dụ "createdAt:DESC")
        Sort.Direction direction = sort.endsWith(":DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));

        // Lấy trang dữ liệu từ DB
        Page<JobPositionEntity> pageData = jobPositionRepository.findAll(
                GenericSpecification.searchByCodeOrName(search), pageRequest);
        Page<JobPositionResponseDTO> dtoPage = pageData.map(jobPositionMapper::toDto);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }


    @Override
    @Transactional
    public JobPositionResponseDTO updateJobPosition(JobPositionRequestDTO dto) {
        var entity = jobPositionRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("JobPosition not found"));

        var industry = industryRepository.findById(dto.getIndustryJob().getId())
                .orElseThrow(() -> new RuntimeException("Industry not found"));

        entity.setIndustryEntity(industry);
        entity.setUpdatedAt(LocalDateTime.now());
        entity = jobPositionRepository.save(entity);

        if (dto.getDepartmentPositions() != null) {
            jobPositionMapRepository.deleteByJobPositionId(dto.getId());
            jobPositionMapRepository.saveAll(buildMapEntities(entity, dto));
        }
        return new JobPositionResponseDTO(entity.getId());
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
        // 1. Lấy JobPositionEntity theo id
        JobPositionEntity jobPositionEntity = jobPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobPosition không tồn tại"));

        // 2. Map sang DTO cơ bản – lưu ý mapper của bạn sẽ ignore trường liên quan đến map (jobPositionMaps)
        JobPositionResponseDTO responseDTO = jobPositionMapper.toDto(jobPositionEntity);

        // 3. Lấy danh sách map từ repository sử dụng tên method đúng
        List<JobPositionMapEntity> mapEntities = jobPositionMapRepository.findByJobPosition_Id(id);

        // 4. Với mỗi mapEntity, gọi Feign Client để lấy chi tiết department và position,
        // và xây dựng DTO cho “line”
//        List<JobPositionLineDTO> lines = mapEntities.stream().map(mapEntity -> {
//            DepartmentResponseDTO departmentDTO = departmentClient.getDepartmentById(mapEntity.getDepartmentId());
//            PositionResponseDTO positionDTO = positionClient.getPositionsById(mapEntity.getPositionId());
//            JobPositionLineDTO lineDTO = new JobPositionLineDTO();
//            lineDTO.setDepartment(departmentDTO);
//            lineDTO.setPosition(positionDTO);
//            return lineDTO;
//        }).collect(Collectors.toList());

        // 5. Set danh sách lines vào DTO
//        responseDTO.setLines(lines);

        return responseDTO;
    }



    /**
     * Lấy danh sách mapEntity từ requestDTO
     */
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

