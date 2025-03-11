package com.tiep.demoapus.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentPositionDTO {
    private DepartmentDTO department;
    private List<PositionDTO> positions;
}
