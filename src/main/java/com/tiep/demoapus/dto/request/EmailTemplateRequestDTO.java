package com.tiep.demoapus.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplateRequestDTO {
    private Long id;
    private String name;
    private String title;
    private String content;
}
