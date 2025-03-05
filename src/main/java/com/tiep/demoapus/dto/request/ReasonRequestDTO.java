package com.tiep.demoapus.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReasonRequestDTO {
    private Long id;
    private String name;
    private String description;
    @JsonProperty("isActive")
    private Boolean active;
    private Long groupReasonId;
}
