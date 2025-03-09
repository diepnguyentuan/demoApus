package com.tiep.demoapus.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenefitRequestDTO {
    private Long id;
    private String code;
    private String name;
    private String content;
    @JsonProperty("isActive")
    private Boolean active;
    private List<DepartmentDTO> lines;
}
