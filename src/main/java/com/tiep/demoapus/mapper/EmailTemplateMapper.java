package com.tiep.demoapus.mapper;

import com.tiep.demoapus.dto.request.EmailTemplateRequestDTO;
import com.tiep.demoapus.dto.response.EmailTemplateResponseDTO;
import com.tiep.demoapus.entity.EmailTemplateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailTemplateMapper {
    EmailTemplateResponseDTO toDto(EmailTemplateEntity entity);
    EmailTemplateEntity toEntity(EmailTemplateRequestDTO dto);
}
