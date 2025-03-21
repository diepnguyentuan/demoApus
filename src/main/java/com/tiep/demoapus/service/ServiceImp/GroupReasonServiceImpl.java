package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.GroupReasonRequestDTO;
import com.tiep.demoapus.dto.response.GroupReasonResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.entity.GroupReasonEntity;
import com.tiep.demoapus.mapper.GroupReasonMapper;
import com.tiep.demoapus.repository.GroupReasonRepository;
import com.tiep.demoapus.service.GroupReasonService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GroupReasonServiceImpl implements GroupReasonService {

    private final GroupReasonRepository groupReasonRepository;
    private final GroupReasonMapper groupReasonMapper;

    @Override
    public PageableResponse<GroupReasonResponseDTO> getAllGroupReasons(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith(":DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<GroupReasonEntity> pageData = groupReasonRepository.findAll(GenericSpecification.searchByCodeOrName(search), pageable);
        Page<GroupReasonResponseDTO> dtoPage = pageData.map(groupReasonMapper::toDTO);

        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    @Override
    public GroupReasonResponseDTO getGroupReasonById(Long id) {
        GroupReasonEntity entity = groupReasonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupReason not found"));
        return groupReasonMapper.toDTO(entity);
    }

    @Override
    public GroupReasonResponseDTO addGroupReason(GroupReasonRequestDTO dto) {
        GroupReasonEntity entity = groupReasonMapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        GroupReasonEntity saved = groupReasonRepository.save(entity);
        return groupReasonMapper.toDTO(saved);
    }

    @Override
    public GroupReasonResponseDTO updateGroupReason(GroupReasonRequestDTO dto) {
        GroupReasonEntity entity = groupReasonRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("GroupReason not found"));
        // Cập nhật các trường từ dto (ví dụ)
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setActive(dto.getActive());
        entity.setDescription(dto.getDescription());
        GroupReasonEntity updated = groupReasonRepository.save(entity);
        return groupReasonMapper.toDTO(updated);
    }

    @Override
    public void deleteGroupReason(Long id) {
        if (!groupReasonRepository.existsById(id)) {
            throw new RuntimeException("GroupReason not found");
        }
        groupReasonRepository.deleteById(id);
    }
}
