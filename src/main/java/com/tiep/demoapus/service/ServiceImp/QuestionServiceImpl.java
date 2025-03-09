package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.QuestionRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.dto.response.QuestionResponseDTO;
import com.tiep.demoapus.entity.QuestionEntity;
import com.tiep.demoapus.mapper.QuestionMapper;
import com.tiep.demoapus.repository.GroupQuestionRepository;
import com.tiep.demoapus.repository.QuestionRepository;
import com.tiep.demoapus.service.QuestionService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final GroupQuestionRepository groupQuestionRepository;

    @Override
    public PageableResponse<QuestionResponseDTO> getAllQuestions(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<QuestionEntity> questions = questionRepository.findAll(GenericSpecification.searchByName(search), pageRequest);
        Page<QuestionResponseDTO> toDtos = questions.map(questionMapper::toDto);
        return PageableResponseUtil.fromPage(toDtos, sort);
    }

    @Override
    public QuestionResponseDTO getQuestionById(Long id) {
        QuestionEntity question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return questionMapper.toDto(question);
    }

    @Override
    public QuestionResponseDTO createQuestion(QuestionRequestDTO questionRequestDTO) {
        var groupQuestion = groupQuestionRepository.findById(questionRequestDTO.getGroupQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));
        QuestionEntity questionEntity = questionMapper.toEntity(questionRequestDTO);
        questionEntity.setGroupQuestion(groupQuestion);
        questionEntity.setCreatedAt(LocalDateTime.now());
        questionEntity.setUpdatedAt(LocalDateTime.now());
        questionEntity = questionRepository.save(questionEntity);
        return new QuestionResponseDTO(questionEntity.getId());
    }

    @Override
    public QuestionResponseDTO updateQuestion(QuestionRequestDTO questionRequestDTO) {
        var groupQuestion = groupQuestionRepository.findById(questionRequestDTO.getGroupQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));
        QuestionEntity questionEntity = questionMapper.toEntity(questionRequestDTO);
        questionEntity.setGroupQuestion(groupQuestion);
        questionEntity.setUpdatedAt(LocalDateTime.now());
        questionEntity = questionRepository.save(questionEntity);
        return new QuestionResponseDTO(questionEntity.getId());
    }

    @Override
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Question not found");
        }
        questionRepository.deleteById(id);
    }
}
