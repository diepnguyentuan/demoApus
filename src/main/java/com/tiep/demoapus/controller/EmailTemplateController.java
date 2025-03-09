package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.EmailTemplateRequestDTO;
import com.tiep.demoapus.dto.response.EmailTemplateResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.EmailTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/email-template")
@RequiredArgsConstructor
public class EmailTemplateController {
    private final EmailTemplateService emailTemplateService;

    @GetMapping("/list")
    public ResponseEntity<?> getEmailTemplate(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        PageableResponse<EmailTemplateResponseDTO> data = emailTemplateService.getAllEmailTemplates(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmailTemplateById(@PathVariable Long id) {
        EmailTemplateResponseDTO response = emailTemplateService.getEmailTemplateById(id);
        return ResponseEntity.ok(new ResponseWrapper(response));
    }

    @PostMapping
    public ResponseEntity<?> createEmailTemplate(@RequestBody EmailTemplateRequestDTO emailTemplateRequestDTO) {
        EmailTemplateResponseDTO dto = emailTemplateService.createEmailTemplate(emailTemplateRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", dto.getId())));
    }

    @PutMapping
    public ResponseEntity<?> updateEmailTemplate(@RequestBody EmailTemplateRequestDTO emailTemplateRequestDTO) {
        EmailTemplateResponseDTO dto = emailTemplateService.updateEmailTemplate(emailTemplateRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", dto.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmailTemplate(@PathVariable Long id) {
        emailTemplateService.deleteEmailTemplate(id);
        return ResponseEntity.ok("Deleted email template");
    }
}
