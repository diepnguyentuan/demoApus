package com.tiep.demoapus.dto.request;

import java.util.List;

public class JobPositionRequestDTO {
    private String code;
    private String name;
    private String description;
    private boolean active;
    private Long industryId;

    // Danh sách chứa cặp {departmentId, positionIds}
    private List<DepartmentPositionDTO> departmentPositions;

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

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public List<DepartmentPositionDTO> getDepartmentPositions() {
        return departmentPositions;
    }

    public void setDepartmentPositions(List<DepartmentPositionDTO> departmentPositions) {
        this.departmentPositions = departmentPositions;
    }
}
