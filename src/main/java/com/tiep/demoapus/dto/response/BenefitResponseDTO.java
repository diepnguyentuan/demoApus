package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenefitResponseDTO {
    private Long id;
    private String code;
    private String name;
    private String content;
    @JsonProperty("isActive")
    private Boolean active;
    // Danh sách các department (lines)
    private List<DepartmentResponseDTO> lines;

    public BenefitResponseDTO(Long id) {
        this.id = id;
    }
}
