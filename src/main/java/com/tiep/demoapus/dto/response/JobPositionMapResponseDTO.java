package com.tiep.demoapus.dto.response;


public class JobPositionMapResponseDTO {
    private DepartmentResponseDTO department;
    private PositionResponseDTO position;

    // Constructors
    public JobPositionMapResponseDTO() {}

    public JobPositionMapResponseDTO(DepartmentResponseDTO department, PositionResponseDTO position) {
        this.department = department;
        this.position = position;
    }

    // Getters and Setters

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

