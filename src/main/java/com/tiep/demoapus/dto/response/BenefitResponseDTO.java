package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO phản hồi cho Benefit.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BenefitResponseDTO {
    private Long id;

    private String code;

    private String name;

    private String content;

    @JsonProperty("isActive")
    private Boolean active;

    private List<DepartmentResponseDTO> lines;

    public BenefitResponseDTO(Long id) {
        this.id = id;
    }
}
