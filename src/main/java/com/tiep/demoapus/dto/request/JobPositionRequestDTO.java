package com.tiep.demoapus.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobPositionRequestDTO {
    private Long id;

    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$", message = "Code không được chứa ký tự đặc biệt")
    private String code;

    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$", message = "Name không được chứa ký tự đặc biệt")
    private String name;

    private String description;

    @JsonProperty("isActive")
    private Boolean active;

    private IndustryJobRequestDTO industryJob;

    private List<DepartmentPositionDTO> departmentPositions;
}
