package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.HiringRequestDTO;
import com.tiep.demoapus.dto.response.HiringResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.entity.HiringEntity;
import com.tiep.demoapus.mapper.HiringMapper;
import com.tiep.demoapus.repository.HiringRepository;
import com.tiep.demoapus.repository.HiringTypeRepository;
import com.tiep.demoapus.service.HiringService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HiringServiceImpl implements HiringService {

    private final HiringMapper hiringMapper;
    private final HiringRepository hiringRepository;
    private final HiringTypeRepository hiringTypeRepository;

    @Override
    public PageableResponse<HiringResponseDTO> getAllHirings(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<HiringEntity> hiringEntities = hiringRepository.findAll(GenericSpecification.searchByCodeOrName(search), pageRequest);
        Page<HiringResponseDTO> dtoPage = hiringEntities.map(hiringMapper::toDTO);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    @Override
    public HiringResponseDTO getHiringById(Long id) {
        HiringEntity hiringEntity = hiringRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HiringEntity not found"));
        return hiringMapper.toDTO(hiringEntity);
    }

    @Override
    public HiringResponseDTO addHiring(HiringRequestDTO hiringRequestDTO) {
        var hiringTypeEntity = hiringTypeRepository.findById(hiringRequestDTO.getHiringTypeId())
                .orElseThrow(() -> new RuntimeException("HiringTypeEntity not found"));
        HiringEntity hiringEntity = hiringMapper.toEntity(hiringRequestDTO);
        hiringEntity.setType(hiringTypeEntity);
        hiringEntity.setCreatedAt(LocalDateTime.now());
        hiringEntity.setUpdatedAt(LocalDateTime.now());
        hiringEntity = hiringRepository.save(hiringEntity);
        return new HiringResponseDTO(hiringEntity.getId());
    }

    @Override
    public HiringResponseDTO updateHiring(HiringRequestDTO hiringRequestDTO) {
        var hiringTypeEntity = hiringTypeRepository.findById(hiringRequestDTO.getHiringTypeId())
                .orElseThrow(() -> new RuntimeException("HiringTypeEntity not found"));
        HiringEntity hiringEntity = hiringMapper.toEntity(hiringRequestDTO);
        hiringEntity.setType(hiringTypeEntity);
        hiringEntity.setUpdatedAt(LocalDateTime.now());
        hiringEntity = hiringRepository.save(hiringEntity);
        return new HiringResponseDTO(hiringEntity.getId());
    }

    @Override
    public void deleteHiringById(Long id) {
        if (!hiringRepository.existsById(id)) {
            throw new RuntimeException("HiringEntity not found");
        }
        hiringRepository.deleteById(id);
    }
}
