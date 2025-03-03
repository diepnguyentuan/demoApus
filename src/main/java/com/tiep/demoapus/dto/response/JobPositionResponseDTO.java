package com.tiep.demoapus.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class JobPositionResponseDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private boolean active;
    private List<JobPositionMapResponseDTO> lines;;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public JobPositionResponseDTO(String code, String name, IndustryResponseDTO major, String description, boolean active, List<JobPositionMapResponseDTO> lines) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<JobPositionMapResponseDTO> getLines() {
        return lines;
    }

    public void setLines(List<JobPositionMapResponseDTO> lines) {
        this.lines = lines;
    }
}
