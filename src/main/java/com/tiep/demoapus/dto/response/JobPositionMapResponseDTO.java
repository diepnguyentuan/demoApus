package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobPositionMapResponseDTO {
    private Long id;
    private String code;
    private String name;
    private IndustryJobResponseDTO industry;
    private String description;
    @JsonProperty("isActive")
    private Boolean active;
    private List<JobPositionLineDTO> lines;
}
