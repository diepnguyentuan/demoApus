package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentChannelResponseDTO {
    private Long id;
    private String name;
    private String description;
    @JsonProperty("isActive")
    private Boolean active;
    private SourceMinimalDTO source;

    public RecruitmentChannelResponseDTO(Long id) {
        this.id = id;
    }
}
