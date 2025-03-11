package com.tiep.demoapus.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HiringRequestDTO {
    private Long id;
    @NotNull(message = "Code must not be null")
    @NotBlank(message = "Code must not be blank")
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$", message = "Code không được chứa ký tự đặc biệt")
    private String code;
    @NotNull(message = "Name must not be null")
    @NotBlank(message = "Name must not be blank")
    // Cho phép các ký tự chữ (bao gồm unicode), số và khoảng trắng.
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$", message = "Name không được chứa ký tự đặc biệt")
    private String name;
    private String description;
    @JsonProperty("isActive")
    @NotNull(message = "isActive must not be null")
    private Boolean active;
    @NotNull(message = "HiringTypeId must not be null")
    private Long hiringTypeId;
}
