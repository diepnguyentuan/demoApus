package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.GroupQuestionRequestDTO;
import com.tiep.demoapus.dto.response.GroupQuestionResponseDTO;
import com.tiep.demoapus.entity.GroupQuestionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupQuestionMapper {
    GroupQuestionResponseDTO toDto(GroupQuestionEntity entity);
    GroupQuestionEntity toEntity(GroupQuestionRequestDTO dto);
}
