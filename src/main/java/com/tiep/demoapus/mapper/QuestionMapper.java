package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.QuestionRequestDTO;
import com.tiep.demoapus.dto.response.QuestionResponseDTO;
import com.tiep.demoapus.entity.QuestionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GroupQuestionMapper.class})
public interface QuestionMapper {
    QuestionResponseDTO toDto(QuestionEntity entity);
    QuestionEntity toEntity(QuestionRequestDTO dto);
}
