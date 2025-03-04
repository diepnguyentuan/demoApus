package com.tiep.demoapus.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPositionRequestDTO {
    private String code;
    private String name;
    private String description;
    private boolean active;
    private Long industryId;
    private List<DepartmentPositionDTO> departmentPositions;
}
