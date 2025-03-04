package com.tiep.demoapus.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "job_position")
public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String description;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "industry_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_job_position_industry"))
    private Industry industry;

    @OneToMany(mappedBy = "jobPosition", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<JobPositionMap> jobPositionMaps = new ArrayList<>(); // Đảm bảo không bị null

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    public JobPosition() {}

    public JobPosition(Long id, String code, String name, String description, boolean active, Industry industry) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.active = active;
        this.industry = industry;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getter & Setter đảm bảo không bị null
    public List<JobPositionMap> getJobPositionMaps() {
        return jobPositionMaps != null ? jobPositionMaps : new ArrayList<>();
    }

    public void setJobPositionMaps(List<JobPositionMap> jobPositionMaps) {
        this.jobPositionMaps = jobPositionMaps;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt != null ? createdAt : LocalDateTime.now();
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt != null ? updatedAt : LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
