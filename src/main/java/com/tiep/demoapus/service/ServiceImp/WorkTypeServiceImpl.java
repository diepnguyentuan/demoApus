package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.WorkTypeRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.dto.response.WorkTypeResponseDTO;
import com.tiep.demoapus.entity.WorkTypeEntity;
import com.tiep.demoapus.mapper.WorkTypeMapper;
import com.tiep.demoapus.repository.WorkTypeRepository;
import com.tiep.demoapus.service.WorkTypeService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WorkTypeServiceImpl implements WorkTypeService {

    private final WorkTypeRepository workTypeRepository;
    private final WorkTypeMapper workTypeMapper;
    @Override
    public PageableResponse<WorkTypeResponseDTO> getAllWorkTypes(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<WorkTypeEntity> workTypeEntities = workTypeRepository.findAll(GenericSpecification.searchByCodeOrName(search), pageRequest);
        Page<WorkTypeResponseDTO> dtoPage = workTypeEntities.map(workTypeMapper::toDto);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    @Override
    public WorkTypeResponseDTO getWorkTypeById(Long id) {
        WorkTypeEntity workTypeEntity = workTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkType not found"));
        return workTypeMapper.toDto(workTypeEntity);
    }

    @Override
    public WorkTypeResponseDTO addWorkType(WorkTypeRequestDTO workTypeRequestDTO) {
        WorkTypeEntity workTypeEntity = workTypeMapper.toEntity(workTypeRequestDTO);
        workTypeEntity.setCreatedAt(LocalDateTime.now());
        workTypeEntity.setUpdatedAt(LocalDateTime.now());
        workTypeEntity = workTypeRepository.save(workTypeEntity);
        return new WorkTypeResponseDTO(workTypeEntity.getId());
    }

    @Override
    public WorkTypeResponseDTO updateWorkType(WorkTypeRequestDTO workTypeRequestDTO) {
        WorkTypeEntity workTypeEntity = workTypeMapper.toEntity(workTypeRequestDTO);
        workTypeEntity.setUpdatedAt(LocalDateTime.now());
        workTypeEntity = workTypeRepository.save(workTypeEntity);
        return new WorkTypeResponseDTO(workTypeEntity.getId());
    }

    @Override
    public void deleteWorkTypeById(Long id) {
        if(!workTypeRepository.existsById(id)) {
            throw new RuntimeException("WorkType not found");
        }
        workTypeRepository.deleteById(id);
    }
}
