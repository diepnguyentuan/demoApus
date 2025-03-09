package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.GroupQuestionRequestDTO;
import com.tiep.demoapus.dto.response.GroupQuestionResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.entity.GroupQuestionEntity;
import com.tiep.demoapus.mapper.GroupQuestionMapper;
import com.tiep.demoapus.repository.GroupQuestionRepository;
import com.tiep.demoapus.service.GroupQuestionService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GroupQuestionServiceImpl implements GroupQuestionService {
    private final GroupQuestionRepository groupQuestionRepository;
    private final GroupQuestionMapper groupQuestionMapper;
    @Override
    public PageableResponse<GroupQuestionResponseDTO> getAllGroupQuestions(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<GroupQuestionEntity> groupQuestionEntities = groupQuestionRepository.findAll(GenericSpecification.searchByCodeOrName(search), pageRequest);
        Page<GroupQuestionResponseDTO> dtoPage = groupQuestionEntities.map(groupQuestionMapper::toDto);
        return PageableResponseUtil.fromPage(dtoPage, sort);
    }

    @Override
    public GroupQuestionResponseDTO getGroupQuestionById(Long id) {
        GroupQuestionEntity groupQuestionEntity = groupQuestionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupQuestion not found"));
        return groupQuestionMapper.toDto(groupQuestionEntity);
    }

    @Override
    public GroupQuestionResponseDTO createGroupQuestion(GroupQuestionRequestDTO groupQuestionRequestDTO) {
        GroupQuestionEntity groupQuestionEntity = groupQuestionMapper.toEntity(groupQuestionRequestDTO);
        groupQuestionEntity.setCreatedAt(LocalDateTime.now());
        groupQuestionEntity.setUpdatedAt(LocalDateTime.now());
        groupQuestionEntity = groupQuestionRepository.save(groupQuestionEntity);
        return new GroupQuestionResponseDTO(groupQuestionEntity.getId());
    }

    @Override
    public GroupQuestionResponseDTO updateGroupQuestion(GroupQuestionRequestDTO groupQuestionRequestDTO) {
        GroupQuestionEntity groupQuestionEntity = groupQuestionMapper.toEntity(groupQuestionRequestDTO);
        groupQuestionEntity.setUpdatedAt(LocalDateTime.now());
        groupQuestionEntity = groupQuestionRepository.save(groupQuestionEntity);
        return new GroupQuestionResponseDTO(groupQuestionEntity.getId());
    }

    @Override
    public void deleteGroupQuestion(Long id) {
        if (!groupQuestionRepository.existsById(id)) {
            throw new RuntimeException("GroupQuestion not found");
        }
        groupQuestionRepository.deleteById(id);
    }
}
