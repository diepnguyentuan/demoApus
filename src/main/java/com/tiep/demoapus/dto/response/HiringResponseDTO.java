package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HiringResponseDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    @JsonProperty("isActive")
    private Boolean active;
    private HiringTypeMinimalResponseDTO hiringType;

    public HiringResponseDTO(Long id) {
        this.id = id;
    }
}
