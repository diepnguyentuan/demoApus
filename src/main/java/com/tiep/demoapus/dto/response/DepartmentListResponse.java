package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentListResponse {
    private List<DepartmentResponseDTO> content;
}
