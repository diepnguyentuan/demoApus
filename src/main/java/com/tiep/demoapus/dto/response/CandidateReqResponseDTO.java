package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateReqResponseDTO {
    private Long id;
    private String name;
    @JsonProperty("isActive")
    private Boolean active;
    private Long departmentId;

    public CandidateReqResponseDTO(Long id) {
        this.id = id;
    }
}
