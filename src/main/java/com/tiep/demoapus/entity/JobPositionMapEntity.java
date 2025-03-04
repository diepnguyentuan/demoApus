package com.tiep.demoapus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_position_map")
public class JobPositionMapEntity {
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
    private JobPositionEntity jobPosition;


}
