package com.tiep.demoapus.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceRequestDTO {
    private Long id;
    private String code;
    private String name;
    @JsonProperty("isActive")
    private Boolean active;
}
