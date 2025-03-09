package com.tiep.demoapus.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupQuestionRequestDTO {
    private Long id;
    @NotNull(message = "Code must not be null")
    @NotBlank(message = "Code must not be blank")
    private String code;
    @NotNull(message = "Name must not be null")
    @NotBlank(message = "Name must not be blank")
    private String name;
    private String description;
    @JsonProperty("isActive")
    @NotNull(message = "isActive must not be null")
    private Boolean active;
}
