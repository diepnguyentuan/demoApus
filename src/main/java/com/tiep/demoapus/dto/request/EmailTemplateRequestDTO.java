package com.tiep.demoapus.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplateRequestDTO {
    private Long id;
    // Cho phép các ký tự chữ (bao gồm unicode), số và khoảng trắng.
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$", message = "Name không được chứa ký tự đặc biệt")
    private String name;
    private String title;
    private String content;
}
