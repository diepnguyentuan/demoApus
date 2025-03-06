package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.HiringTypeRequestDTO;
import com.tiep.demoapus.dto.response.HiringTypeResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.entity.HiringTypeEntity;
import com.tiep.demoapus.mapper.HiringTypeMapper;
import com.tiep.demoapus.repository.HiringTypeRepository;
import com.tiep.demoapus.service.HiringTypeService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HiringTypeServiceImpl implements HiringTypeService {

    private final HiringTypeRepository hiringTypeRepository;
    private final HiringTypeMapper hiringTypeMapper;

    @Override
    public PageableResponse<HiringTypeResponseDTO> getAllHiringTypes(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<HiringTypeEntity> pageData = hiringTypeRepository.findAll(GenericSpecification.searchByCodeOrName(search), pageRequest);
        Page<HiringTypeResponseDTO> dtoPage = pageData.map(hiringTypeMapper::toDto);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    @Override
    public HiringTypeResponseDTO getHiringTypeById(Long id) {
        HiringTypeEntity hiringTypeEntity = hiringTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HiringTypeEntity not found"));
        return hiringTypeMapper.toDto(hiringTypeEntity);
    }

    @Override
    public HiringTypeResponseDTO addHiringType(HiringTypeRequestDTO hiringTypeRequestDTO) {
        HiringTypeEntity hiringTypeEntity = hiringTypeMapper.toEntity(hiringTypeRequestDTO);
        hiringTypeEntity.setCreatedAt(LocalDateTime.now());
        hiringTypeEntity.setUpdatedAt(LocalDateTime.now());
        hiringTypeRepository.save(hiringTypeEntity);
        return new HiringTypeResponseDTO(hiringTypeEntity.getId());
    }

    @Override
    public HiringTypeResponseDTO updateHiringType(HiringTypeRequestDTO hiringTypeRequestDTO) {
        HiringTypeEntity hiringTypeEntity = hiringTypeMapper.toEntity(hiringTypeRequestDTO);
        hiringTypeEntity.setUpdatedAt(LocalDateTime.now());
        hiringTypeRepository.save(hiringTypeEntity);
        return new HiringTypeResponseDTO(hiringTypeEntity.getId());
    }

    @Override
    public void deleteHiringType(Long id) {
        if (!hiringTypeRepository.existsById(id)) {
            throw new RuntimeException("HiringTypeEntity not found");
        }
        hiringTypeRepository.deleteById(id);
    }
}
