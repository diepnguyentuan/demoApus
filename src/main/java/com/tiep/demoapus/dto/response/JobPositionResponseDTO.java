package com.tiep.demoapus.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public class JobPositionResponseDTO {
    private Long id;
    private String code;
    private String name;
    private IndustryResponseDTO industry; // Đổi từ "major" sang "industry"
    private String description;

    @JsonProperty("is_active")
    private boolean is_active;

    private List<Line> lines;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public JobPositionResponseDTO() {
    }

    // Getters and Setters

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
    public IndustryResponseDTO getIndustry() {
        return industry;
    }
    public void setIndustry(IndustryResponseDTO industry) {
        this.industry = industry;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isIs_active() {
        return is_active;
    }
    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
    public List<Line> getLines() {
        return lines;
    }
    public void setLines(List<Line> lines) {
        this.lines = lines;
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

    // Inner class để đại diện cho mỗi dòng trong mảng lines
    public static class Line {
        private DepartmentResponseDTO department;
        private PositionResponseDTO position;

        public Line(DepartmentResponseDTO department, PositionResponseDTO position) {
            this.department = department;
            this.position = position;
        }

        public DepartmentResponseDTO getDepartment() {
            return department;
        }
        public void setDepartment(DepartmentResponseDTO department) {
            this.department = department;
        }
        public PositionResponseDTO getPosition() {
            return position;
        }
        public void setPosition(PositionResponseDTO position) {
            this.position = position;
        }
    }
}
