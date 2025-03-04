package com.tiep.demoapus.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupReasonRequestDTO {
    private Long id;
    private String code;
    private String name;
    private Boolean isActive;
    private String description;
}
