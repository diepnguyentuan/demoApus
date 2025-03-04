package com.tiep.demoapus.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class JobPositionRequestDTO {
    private String code;
    private String name;
    private String description;
    private boolean active;
    private Long industryId;
    private List<DepartmentPositionDTO> departmentPositions;

    public JobPositionRequestDTO() {
    }
}
