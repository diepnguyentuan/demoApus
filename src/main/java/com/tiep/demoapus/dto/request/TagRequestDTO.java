package com.tiep.demoapus.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagRequestDTO {
    private Long id;
    // Cho phép các ký tự chữ (bao gồm unicode), số và khoảng trắng.
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$", message = "Name không được chứa ký tự đặc biệt")
    private String name;
    @JsonProperty("isActive")
    private Boolean active;
}

