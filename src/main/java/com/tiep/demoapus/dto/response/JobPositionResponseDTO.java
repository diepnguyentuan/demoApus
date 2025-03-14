package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public JobPositionResponseDTO(Long id) {
        this.id = id;
    }
}
