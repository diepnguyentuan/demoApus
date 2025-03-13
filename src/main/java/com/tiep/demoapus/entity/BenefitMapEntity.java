package com.tiep.demoapus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "benefit_map")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenefitMapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lưu trữ departmentId từ external service
    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @ManyToOne
    @JoinColumn(name = "benefit_id", referencedColumnName = "id")
    @ToString.Exclude
    private BenefitEntity benefit;
}
