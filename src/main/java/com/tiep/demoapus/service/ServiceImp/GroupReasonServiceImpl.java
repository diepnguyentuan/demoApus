package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.GroupReasonRequestDTO;
import com.tiep.demoapus.dto.response.GroupReasonResponseDTO;
import com.tiep.demoapus.entity.GroupReasonEntity;
import com.tiep.demoapus.mapper.GroupReasonMapper;
import com.tiep.demoapus.repository.GroupReasonRepository;
import com.tiep.demoapus.service.GroupReasonService;
import com.tiep.demoapus.specification.GroupReasonSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupReasonServiceImpl implements GroupReasonService {

    private final GroupReasonRepository groupReasonRepository;
    private final GroupReasonMapper groupReasonMapper;

    @Override
    public Page<GroupReasonResponseDTO> getAllGroupReasons(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith(":DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<GroupReasonEntity> groupReasonEntityPage = groupReasonRepository
                .findAll(GroupReasonSpecification.searchByCodeOrName(search), pageable);
        return groupReasonEntityPage.map(groupReasonMapper::toDTO);
    }

    @Override
    public GroupReasonResponseDTO getGroupReasonById(Long id) {
        GroupReasonEntity groupReasonEntity = groupReasonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupReason not found"));
        return groupReasonMapper.toDTO(groupReasonEntity);
    }

    @Override
    public GroupReasonResponseDTO addGroupReason(GroupReasonRequestDTO dto) {
        GroupReasonEntity groupReasonEntity = groupReasonMapper.toEntity(dto);
        // Thời gian được cập nhật tự động qua @PrePersist của entity
        GroupReasonEntity savedGroupReasonEntity = groupReasonRepository.saveAndFlush(groupReasonEntity);
        return groupReasonMapper.toDTO(savedGroupReasonEntity);
    }

    @Override
    public GroupReasonResponseDTO updateGroupReason(Long id, GroupReasonRequestDTO dto) {
        GroupReasonEntity groupReasonEntity = groupReasonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupReason not found"));
        // Cập nhật các trường cần thiết (có thể dùng MapStruct update method nếu có)
        groupReasonEntity.setCode(dto.getCode());
        groupReasonEntity.setName(dto.getName());
        groupReasonEntity.setDescription(dto.getDescription());
        groupReasonEntity.setActive(dto.getIsActive());
        GroupReasonEntity updatedGroupReasonEntity = groupReasonRepository.save(groupReasonEntity);
        return groupReasonMapper.toDTO(updatedGroupReasonEntity);
    }

    @Override
    public void deleteGroupReason(Long id) {
        if (!groupReasonRepository.existsById(id)) {
            throw new RuntimeException("GroupReason not found");
        }
        groupReasonRepository.deleteById(id);
    }
}
