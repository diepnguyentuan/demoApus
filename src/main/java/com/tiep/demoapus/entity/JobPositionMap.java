package com.tiep.demoapus.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "job_position_map")
public class JobPositionMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @Getter
    @Column(name = "position_id", nullable = false)
    private Long positionId;

    @ManyToOne
    @JoinColumn(name = "job_position_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_job_position_map_job_position"))
    private JobPosition jobPosition;

    public JobPositionMap() {}

    public JobPositionMap(Long departmentId, Long positionId, JobPosition jobPosition) {
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.jobPosition = jobPosition;
    }

}
