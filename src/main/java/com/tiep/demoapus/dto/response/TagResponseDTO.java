package com.tiep.demoapus.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagResponseDTO {
    private Long id;
    private String name;
    private Boolean isActive;
}
