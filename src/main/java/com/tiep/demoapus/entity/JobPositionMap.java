package com.tiep.demoapus.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "job_position_map")
public class JobPositionMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_position_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_job_position_map"))
    private JobPosition jobPosition;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "position_id")
    private Long positionId;

    public JobPositionMap() {}
    public JobPositionMap(Long id, JobPosition jobPosition, Long departmentId, Long positionId) {
        this.id = id;
        this.jobPosition = jobPosition;
        this.departmentId = departmentId;
        this.positionId = positionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
}

