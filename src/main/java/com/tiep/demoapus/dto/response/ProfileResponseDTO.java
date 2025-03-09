package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDTO {

    private Long id;
    private String code;
    private String name;
    @JsonProperty("isActive")
    private Boolean active;
    private String description;

    public ProfileResponseDTO(Long id) {
        this.id = id;
    }
}
