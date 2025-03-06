package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceResponseDTO {
    private Long id;
    private String name;
    private String code;
    @JsonProperty("isActive")
    private Boolean active;

    public SourceResponseDTO(Long id) {
        this.id = id;
    }
}
