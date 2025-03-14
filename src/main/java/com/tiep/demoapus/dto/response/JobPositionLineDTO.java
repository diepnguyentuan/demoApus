package com.tiep.demoapus.dto.response;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobPositionLineDTO {
    private DepartmentResponseDTO department;
    private List<PositionResponseDTO> position;
}
