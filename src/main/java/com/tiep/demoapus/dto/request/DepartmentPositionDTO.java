package com.tiep.demoapus.dto.request;

import java.util.List;

public class DepartmentPositionDTO {
    private Long departmentId;
    private List<Long> positionIds;

    public DepartmentPositionDTO() {}

    public DepartmentPositionDTO(Long departmentId, List<Long> positionIds) {
        this.departmentId = departmentId;
        this.positionIds = positionIds;
    }

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

