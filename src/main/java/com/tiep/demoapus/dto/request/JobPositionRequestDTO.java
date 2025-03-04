package com.tiep.demoapus.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPositionRequestDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    @JsonProperty("isActive")
    private Boolean active;
    private Long industryId;
    private List<DepartmentPositionDTO> departmentPositions;
}
