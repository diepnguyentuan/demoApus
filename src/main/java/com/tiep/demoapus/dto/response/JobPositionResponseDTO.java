package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tiep.demoapus.dto.request.IndustryJobRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPositionResponseDTO {
    private Long id;
    private String code;
    private String name;
    private IndustryJobResponseDTO industry;
    private String description;

    @JsonProperty("isActive")
    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<JobPositionMapResponseDTO> jobPositionMaps;

    public JobPositionResponseDTO(Long id) {
        this.id = id;
    }

}
