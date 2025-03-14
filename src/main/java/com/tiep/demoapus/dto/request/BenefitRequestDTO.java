package com.tiep.demoapus.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BenefitRequestDTO {
    private Long id;

    @NotBlank(message = "Code không được để trống")
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$", message = "Code không được chứa ký tự đặc biệt")
    private String code;

    @NotBlank(message = "Name không được để trống")
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$", message = "Name không được chứa ký tự đặc biệt")
    private String name;

    private String content;

    @JsonProperty("isActive")
    private Boolean active;

    private List<Long> departmentIds;
}
