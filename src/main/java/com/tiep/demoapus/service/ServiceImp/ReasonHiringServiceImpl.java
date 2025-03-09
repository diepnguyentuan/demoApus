package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.ReasonHiringRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.dto.response.ReasonHiringResponseDTO;
import com.tiep.demoapus.entity.ReasonEntity;
import com.tiep.demoapus.mapper.ReasonHiringMapper;
import com.tiep.demoapus.repository.ReasonRepository;
import com.tiep.demoapus.service.ReasonHiringService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReasonHiringServiceImpl implements ReasonHiringService {
    private final ReasonRepository reasonRepository;
    private final ReasonHiringMapper reasonHiringMapper;
    @Override
    public PageableResponse<ReasonHiringResponseDTO> getAllReasonHirings(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<ReasonEntity> reasonEntities = reasonRepository.findAll(GenericSpecification.searchByCodeOrName(search), pageRequest);
        Page<ReasonHiringResponseDTO> dtoPage = reasonEntities.map(reasonHiringMapper::toDto);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    @Override
    public ReasonHiringResponseDTO getReasonHiringById(Long id) {
        ReasonEntity reasonEntity = reasonRepository.findById(id).orElse(null);

        return reasonHiringMapper.toDto(reasonEntity);
    }

    @Override
    public ReasonHiringResponseDTO addReasonHiring(ReasonHiringRequestDTO dto) {
        ReasonEntity reasonEntity = reasonHiringMapper.toEntity(dto);
        reasonEntity = reasonRepository.save(reasonEntity);
        return new ReasonHiringResponseDTO(reasonEntity.getId());
    }

    @Override
    public ReasonHiringResponseDTO updateReasonHiring(ReasonHiringRequestDTO dto) {
        if (dto.getId() == null) {
            throw new RuntimeException("Reason id is null");
        }
        ReasonEntity reasonEntity = reasonRepository.findById(dto.getId()).orElse(null);
        reasonEntity.setCode(dto.getCode());
        reasonEntity.setName(dto.getName());
        reasonEntity.setDescription(dto.getDescription());
        reasonEntity.setActive(dto.getActive());
        reasonEntity = reasonRepository.save(reasonEntity);
        return new ReasonHiringResponseDTO(reasonEntity.getId());
    }

    @Override
    public void deleteReasonHiringById(Long id) {
        if (!reasonRepository.existsById(id)) {
            throw new RuntimeException("Reason not found");
        }
        reasonRepository.deleteById(id);
    }
}
