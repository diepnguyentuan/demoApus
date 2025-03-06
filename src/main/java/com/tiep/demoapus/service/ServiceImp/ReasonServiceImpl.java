package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.ReasonRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.dto.response.ReasonResponseDTO;
import com.tiep.demoapus.entity.ReasonEntity;
import com.tiep.demoapus.mapper.ReasonMapper;
import com.tiep.demoapus.repository.GroupReasonRepository;
import com.tiep.demoapus.repository.ReasonRepository;
import com.tiep.demoapus.service.ReasonService;
import com.tiep.demoapus.specification.GenericSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReasonServiceImpl implements ReasonService {

    private final ReasonRepository reasonRepository;
    private final GroupReasonRepository groupReasonRepository;
    private final ReasonMapper reasonMapper;

    @Override
    public PageableResponse<ReasonResponseDTO> getAllReasons(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith(":DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<ReasonEntity> reasonEntities = reasonRepository.findAll(GenericSpecification.searchByName(search), pageable);
        Page<ReasonResponseDTO> dtoPage = reasonEntities.map(reasonMapper::toDTO);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    @Override
    public ReasonResponseDTO getReasonById(Long id) {
        ReasonEntity reasonEntity = reasonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reason not found"));
        return reasonMapper.toDTO(reasonEntity);
    }

    @Override
    @Transactional
    public ReasonResponseDTO addReason(ReasonRequestDTO reason) {
        var groupReasonEntity = groupReasonRepository.findById(reason.getGroupReasonId())
                .orElseThrow(() -> new RuntimeException("Group Reason Not Found"));
        //sử dụng mapper để chuyển từ dto sang entity
        ReasonEntity reasonEntity = reasonMapper.toEntity(reason);
        reasonEntity.setGroupReason(groupReasonEntity);
        reasonEntity.setCreatedAt(LocalDateTime.now());
        reasonEntity.setUpdatedAt(LocalDateTime.now());

        //save db
        reasonEntity = reasonRepository.save(reasonEntity);

        return new ReasonResponseDTO(reasonEntity.getId());
    }

    @Override
    @Transactional
    public ReasonResponseDTO updateReason(ReasonRequestDTO reason) {
        var groupReasonEntity = groupReasonRepository.findById(reason.getGroupReasonId())
                .orElseThrow(() -> new RuntimeException("Group Reason Not Found"));
        //sử dụng mapper để chuyển từ dto sang entity
        ReasonEntity reasonEntity = reasonMapper.toEntity(reason);
        reasonEntity.setGroupReason(groupReasonEntity);
        reasonEntity.setUpdatedAt(LocalDateTime.now());


        return reasonMapper.toDTO(reasonRepository.save(reasonEntity));
    }

    @Override
    public void deleteReason(Long id) {
        if (!reasonRepository.existsById(id)) {
            throw new RuntimeException("Reason Not Found");
        }
        reasonRepository.deleteById(id);
    }

}
