package com.tiep.demoapus.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HiringTypeRequestDTO {

    private Long id;

    @NotNull(message = "Code must not be null")
    @NotBlank(message = "Code must not be blank")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$", message = "Code không được chứa ký tự đặc biệt")
    private String code;

    @NotNull(message = "Name must not be null")
    @NotBlank(message = "Name must not be blank")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    // Cho phép các ký tự chữ (bao gồm unicode), số và khoảng trắng.
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$", message = "Name không được chứa ký tự đặc biệt")
    private String name;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @JsonProperty("isActive")
    @NotNull(message = "isActive must not be null")
    private Boolean active;
}
