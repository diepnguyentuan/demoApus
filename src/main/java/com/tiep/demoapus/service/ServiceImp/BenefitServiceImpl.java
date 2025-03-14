package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.BenefitRequestDTO;
import com.tiep.demoapus.dto.response.*;
import com.tiep.demoapus.entity.BenefitEntity;
import com.tiep.demoapus.entity.BenefitMapEntity;
import com.tiep.demoapus.feignClient.ApiResponse;
import com.tiep.demoapus.mapper.BenefitMapper;
import com.tiep.demoapus.repository.BenefitMapRepository;
import com.tiep.demoapus.repository.BenefitRepository;
import com.tiep.demoapus.service.BenefitService;
import com.tiep.demoapus.feignClient.DepartmentClient;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BenefitServiceImpl implements BenefitService {

    private final BenefitRepository benefitRepository;
    private final BenefitMapper benefitMapper;
    private final BenefitMapRepository benefitMapRepository;
    private final DepartmentClient departmentClient;

    @Override
    public PageableResponse<BenefitResponseDTO> getBenefits(int page, int size, String sort, String search) {
        // Lấy trang BenefitEntity theo search, paging và sort
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<BenefitEntity> entityPage = benefitRepository.findAll(GenericSpecification.searchByName(search), pageRequest);

        // Map các entity sang DTO ban đầu (trường lines chưa được cập nhật đầy đủ)
        List<BenefitResponseDTO> dtoList = entityPage.getContent().stream()
                .map(benefitMapper::toDto)
                .collect(Collectors.toList());

        // Lấy danh sách benefit IDs từ trang
        List<Long> benefitIds = entityPage.getContent().stream()
                .map(BenefitEntity::getId)
                .collect(Collectors.toList());

        if (!benefitIds.isEmpty()) {
            // Lấy tất cả các mapping cho các benefit trong trang
            List<BenefitMapEntity> allMappings = benefitMapRepository.findByBenefit_IdIn(benefitIds);

            if (allMappings != null && !allMappings.isEmpty()) {
                // Nhóm các mapping theo benefit id
                Map<Long, List<BenefitMapEntity>> mappingsByBenefitId = allMappings.stream()
                        .collect(Collectors.groupingBy(map -> map.getBenefit().getId()));

                // Lấy tất cả các departmentId duy nhất
                Set<Long> deptIdSet = allMappings.stream()
                        .map(BenefitMapEntity::getDepartmentId)
                        .collect(Collectors.toSet());
                List<Long> deptIds = new ArrayList<>(deptIdSet);

                // Gọi bulk request đến external service để lấy danh sách department
                ApiResponse<DepartmentListResponse> apiResponse = departmentClient.getDepartmentsByIds(deptIds);
                DepartmentListResponse listResponse = apiResponse.getData();
                List<DepartmentResponseDTO> departmentList = (listResponse != null && listResponse.getContent() != null)
                        ? listResponse.getContent() : List.of();

                // Tạo map tra cứu từ departmentId sang DepartmentResponseDTO
                Map<Long, DepartmentResponseDTO> departmentMap = departmentList.stream()
                        .collect(Collectors.toMap(DepartmentResponseDTO::getId, dept -> dept));

                // Cập nhật trường lines cho mỗi DTO
                for (BenefitResponseDTO dto : dtoList) {
                    List<BenefitMapEntity> benefitMappings = mappingsByBenefitId.get(dto.getId());
                    if (benefitMappings != null && !benefitMappings.isEmpty()) {
                        List<DepartmentResponseDTO> deptDTOs = benefitMappings.stream()
                                .map(mapEntity -> departmentMap.get(mapEntity.getDepartmentId()))
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList());
                        dto.setLines(deptDTOs);
                    } else {
                        dto.setLines(List.of());
                    }
                }
            } else {
                // Không có mapping, set lines rỗng cho tất cả DTO
                dtoList.forEach(dto -> dto.setLines(List.of()));
            }
        }
        Page<BenefitResponseDTO> dtoPage = new PageImpl<>(
                dtoList,          // content
                pageRequest,      // thông tin paging
                entityPage.getTotalElements() // tổng số bản ghi
        );

        return PageableResponseUtil.fromPage(dtoPage, sort);

    }

    @Override
    public BenefitResponseDTO getBenefitById(Long id) {
        BenefitEntity entity = benefitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Benefit not found"));

        BenefitResponseDTO dto = benefitMapper.toDto(entity);

        // Lấy danh sách mapping theo benefit id (sử dụng cú pháp findByBenefit_Id)
        List<BenefitMapEntity> mappingList = benefitMapRepository.findByBenefit_Id(entity.getId());

        if (mappingList != null && !mappingList.isEmpty()) {
            // Gom tất cả các departmentId từ mappingList
            List<Long> deptIds = mappingList.stream()
                    .map(BenefitMapEntity::getDepartmentId)
                    .collect(Collectors.toList());
            // Gọi bulk request đến endpoint "/list" để lấy danh sách department
            ApiResponse<DepartmentListResponse> apiResponse = departmentClient.getDepartmentsByIds(deptIds);
            DepartmentListResponse listResponse = apiResponse.getData();
            List<DepartmentResponseDTO> departmentList = (listResponse != null && listResponse.getContent() != null)
                    ? listResponse.getContent()
                    : List.of();
            dto.setLines(departmentList);
        } else {
            dto.setLines(List.of());
        }
        return dto;
    }

    @Override
    public BenefitResponseDTO addBenefit(BenefitRequestDTO benefitRequestDTO) {
        BenefitEntity benefitEntity = benefitMapper.toEntity(benefitRequestDTO);
        if (benefitEntity.getMaps() != null) {
            benefitEntity.getMaps().forEach(map -> map.setBenefit(benefitEntity));
        }
        BenefitEntity savedEntity = benefitRepository.save(benefitEntity);
        return new BenefitResponseDTO(savedEntity.getId());
    }

    @Override
    public BenefitResponseDTO updateBenefit(BenefitRequestDTO benefitRequestDTO) {
        BenefitEntity benefitEntity = benefitRepository.findById(benefitRequestDTO.getId())
                .orElseThrow(() -> new RuntimeException("Benefit not found"));
        if (benefitEntity.getMaps() != null) {
            benefitEntity.getMaps().forEach(map -> map.setBenefit(benefitEntity));
        }
        BenefitEntity savedEntity = benefitRepository.save(benefitEntity);
        return new BenefitResponseDTO(savedEntity.getId());
    }

    @Override
    public void deleteBenefit(Long id) {
        if (!benefitRepository.existsById(id)) {
            throw new RuntimeException("Benefit not found");
        }
        benefitRepository.deleteById(id);
    }
}
