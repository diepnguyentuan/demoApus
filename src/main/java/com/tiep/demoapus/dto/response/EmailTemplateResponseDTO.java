package com.tiep.demoapus.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplateResponseDTO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;

    public EmailTemplateResponseDTO(Long id) {
        this.id = id;
    }
}
