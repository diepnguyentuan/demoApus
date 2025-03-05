package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.ReasonRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ReasonResponseDTO;
import com.tiep.demoapus.entity.ReasonEntity;
import com.tiep.demoapus.mapper.ReasonMapper;
import com.tiep.demoapus.repository.GroupReasonRepository;
import com.tiep.demoapus.repository.ReasonRepository;
import com.tiep.demoapus.service.ReasonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

        return null;
    }

    @Override
    public ReasonResponseDTO getReasonById(Long id) {
        return null;
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

        return null;
    }

    @Override
    public void deleteReason(Long id) {

    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }
}
