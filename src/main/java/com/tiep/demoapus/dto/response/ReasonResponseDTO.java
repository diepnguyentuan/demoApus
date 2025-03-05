package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReasonResponseDTO {
    private Long id;
    private String name;
    private String description;

    @JsonProperty("isActive")
    private Boolean active;

    private GroupReasonResponseDTO group;

    public ReasonResponseDTO(Long id) {
        this.id = id;
    }
}
