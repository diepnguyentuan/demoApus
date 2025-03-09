package com.tiep.demoapus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "benefit_map")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenefitMapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @ManyToOne
    @JoinColumn(name = "benefit_id", referencedColumnName = "id")
    private BenefitEntity benefit;
}
