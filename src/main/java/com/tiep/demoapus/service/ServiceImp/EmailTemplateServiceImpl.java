package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.dto.request.EmailTemplateRequestDTO;
import com.tiep.demoapus.dto.response.EmailTemplateResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.PageableResponseUtil;
import com.tiep.demoapus.entity.EmailTemplateEntity;
import com.tiep.demoapus.mapper.EmailTemplateMapper;
import com.tiep.demoapus.repository.EmailTemplateRepository;
import com.tiep.demoapus.service.EmailTemplateService;
import com.tiep.demoapus.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailTemplateServiceImpl implements EmailTemplateService {
    private final EmailTemplateRepository emailTemplateRepository;
    private final EmailTemplateMapper emailTemplateMapper;
    @Override
    public PageableResponse<EmailTemplateResponseDTO> getAllEmailTemplates(int page, int size, String sort, String search) {
        Sort.Direction direction = sort.endsWith("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = sort.split(":")[0];
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<EmailTemplateEntity> entities = emailTemplateRepository.findAll(GenericSpecification.searchByName(search), pageRequest);
        Page<EmailTemplateResponseDTO> toDto = entities.map(emailTemplateMapper::toDto);
        return PageableResponseUtil.fromPage(toDto, sort);
    }

    @Override
    public EmailTemplateResponseDTO getEmailTemplateById(Long id) {
        EmailTemplateEntity entity = emailTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Email Template Not Found"));
        return emailTemplateMapper.toDto(entity);
    }

    @Override
    public EmailTemplateResponseDTO createEmailTemplate(EmailTemplateRequestDTO emailTemplateRequestDTO) {
        EmailTemplateEntity entity = emailTemplateMapper.toEntity(emailTemplateRequestDTO);
        entity = emailTemplateRepository.save(entity);
        return new EmailTemplateResponseDTO(entity.getId());
    }

    @Override
    public EmailTemplateResponseDTO updateEmailTemplate(EmailTemplateRequestDTO emailTemplateRequestDTO) {
        EmailTemplateEntity entity = emailTemplateMapper.toEntity(emailTemplateRequestDTO);
        entity.setUpdatedAt(LocalDateTime.now());
        entity = emailTemplateRepository.save(entity);
        return new EmailTemplateResponseDTO(entity.getId());
    }

    @Override
    public void deleteEmailTemplate(Long id) {
        if (!emailTemplateRepository.existsById(id)) {
            throw new RuntimeException("Email Template Not Found");
        }
        emailTemplateRepository.deleteById(id);
    }
}
