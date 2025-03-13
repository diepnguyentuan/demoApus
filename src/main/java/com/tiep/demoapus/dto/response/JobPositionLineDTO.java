package com.tiep.demoapus.dto.response;

import lombok.Data;

@Data
public class JobPositionLineDTO {
    private DepartmentResponseDTO department;
    private PositionResponseDTO position;
}
