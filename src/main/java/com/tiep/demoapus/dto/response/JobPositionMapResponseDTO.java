package com.tiep.demoapus.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobPositionMapResponseDTO {
    private Long id;
    private JobPositionResponseDTO jobPosition;
    private DepartmentResponseDTO department;
    private PositionResponseDTO position;
}
