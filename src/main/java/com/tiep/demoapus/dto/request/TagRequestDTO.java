package com.tiep.demoapus.dto.request;

import lombok.Data;

@Data
public class TagRequestDTO {
    private Long id;
    private String name;
    private Boolean isActive;
}
