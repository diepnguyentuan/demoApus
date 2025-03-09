package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkTypeResponseDTO {
    private Long id;
    private String code;
    private String name;
    @JsonProperty("isActive")
    private Boolean active;
    private String description;

    public WorkTypeResponseDTO(Long id) {
        this.id = id;
    }
}
