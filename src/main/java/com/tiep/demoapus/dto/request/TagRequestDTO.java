package com.tiep.demoapus.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagRequestDTO {
    private Long id;
    private String name;
    private Boolean isActive;
}
