package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.SourceRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.dto.response.SourceResponseDTO;
import com.tiep.demoapus.entity.SourceEntity;
import com.tiep.demoapus.mapper.IndustryMapper;
import com.tiep.demoapus.mapper.SourceMapper;
import com.tiep.demoapus.repository.SourceRepository;
import com.tiep.demoapus.service.SourceService;
import com.tiep.demoapus.specification.GenericSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SourceServiceImpl implements SourceService {

    private final SourceRepository sourceRepository;
    private final SourceMapper sourceMapper;
    private final IndustryMapper industryMapper;

    @Override
    public PageableResponse<SourceResponseDTO> findAllSource(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<SourceEntity> sourceEntities = sourceRepository.findAll(GenericSpecification.searchByCodeOrName(search), pageable);
        Page<SourceResponseDTO> dtoPage = sourceEntities.map(sourceMapper::toDto);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    @Override
    public SourceResponseDTO findSourceById(Long id) {
        SourceEntity sourceEntity = sourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Source Not Found"));
        return sourceMapper.toDto(sourceEntity);
    }

    @Override
    @Transactional
    public SourceResponseDTO addSource(SourceRequestDTO source) {
        SourceEntity sourceEntity = sourceMapper.toEntity(source);
        sourceEntity.setCreatedAt(LocalDateTime.now());
        sourceEntity.setUpdatedAt(LocalDateTime.now());
        sourceEntity = sourceRepository.save(sourceEntity);
        return new SourceResponseDTO(sourceEntity.getId());
    }

    @Override
    @Transactional
    public SourceResponseDTO updateSource(SourceRequestDTO source) {
        SourceEntity sourceEntity = sourceMapper.toEntity(source);
        sourceEntity.setUpdatedAt(LocalDateTime.now());
        sourceEntity = sourceRepository.save(sourceEntity);
        return new SourceResponseDTO(sourceEntity.getId());
    }

    @Override
    public void deleteSourceById(Long id) {
        if (!sourceRepository.existsById(id)) {
            throw new RuntimeException("Source Not Found");
        }
        sourceRepository.deleteById(id);
    }
}
