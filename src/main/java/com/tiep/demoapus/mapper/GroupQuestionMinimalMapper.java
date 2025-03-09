package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.response.GroupQuestionResponseDTO;
import com.tiep.demoapus.entity.GroupQuestionEntity;

public interface GroupQuestionMinimalMapper {
    GroupQuestionResponseDTO toDto(GroupQuestionEntity entity);
}
