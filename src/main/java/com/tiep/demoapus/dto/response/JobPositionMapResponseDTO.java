package com.tiep.demoapus.dto.response;

import java.util.List;

public class JobPositionMapResponseDTO {
    private Long departmentId;
    private List<Long> positionIds;

    public JobPositionMapResponseDTO(Long departmentId, List<Long> positionIds) {
        this.departmentId = departmentId;
        this.positionIds = positionIds;
    }

    // Getters & Setters

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public List<Long> getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(List<Long> positionIds) {
        this.positionIds = positionIds;
    }
}
