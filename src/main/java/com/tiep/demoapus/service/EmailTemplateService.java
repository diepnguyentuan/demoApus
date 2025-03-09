package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.request.EmailTemplateRequestDTO;
import com.tiep.demoapus.dto.response.EmailTemplateResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;

public interface EmailTemplateService {
    PageableResponse<EmailTemplateResponseDTO> getAllEmailTemplates(int page, int size, String sort, String search);
    EmailTemplateResponseDTO getEmailTemplateById(Long id);
    EmailTemplateResponseDTO createEmailTemplate(EmailTemplateRequestDTO emailTemplateRequestDTO);
    EmailTemplateResponseDTO updateEmailTemplate(EmailTemplateRequestDTO emailTemplateRequestDTO);
    void deleteEmailTemplate(Long id);
}
