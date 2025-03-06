package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HiringTypeResponseDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    @JsonProperty("isActive")
    private Boolean active;

    public HiringTypeResponseDTO(Long id) {
        this.id = id;
    }
}
